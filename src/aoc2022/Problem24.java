package aoc2022;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import static aoc.Utils.readFile;

public class Problem24 {
    static Map<Integer, Input> stagesCache = new HashMap<>();

    public static void main(String[] args) throws IOException {
        System.out.println(0b1001001001001001);
        System.out.println(0x9249);
        System.out.println(0b1000010000100001);
        System.out.println(0x8421);
        for (int i = 1; i < 17; i++) {
//            System.out.println(i);
            System.out.println(fb(i));
        }
//        solvePart1("./src/aoc2022/Problem24Input1.txt");
//        solvePart1("./src/aoc2022/Problem24Input2.txt");
//        solvePart2("./src/aoc2022/Problem24Input1.txt");
//        solvePart2("./src/aoc2022/Problem24Input2.txt");
    }

    private static Object fb(int i) {
        return (0x9249>>i&1)==1?(0x8421>>i&1)==1?"FizzBuzz":"Fizz":(0x8421>>i&1)==1?"Buzz":i;
//        return (37449>>i&1)==1?(33825>>i&1)==1?"FizzBuzz":"Fizz":(33825>>i&1)==1?"Buzz":i;
    }

    private static void solvePart1(String path) throws IOException {
        System.out.println(solve(path));
    }

    private static void solvePart2(String path) throws IOException {
        System.out.println(solve2(path));
    }

    private static long solve(String path) throws IOException {
        var lines = readFile(path);
        var input = parseInput(lines);
        stagesCache = new HashMap<>();

        var q = new PrioQueue();
        q.add(new Entry(new Point(0, 1), 0));

        return minMovesTo(q, input, new Point(input.map.length-2, input.map[0].length-2));
        // dest row is set to input.map.length-2, because minMovesTo() returns e.min+1. The next minute on which exp reaches the real destination which is row=input.map.length-1.
    }

    private static long solve2(String path) throws IOException {
        var lines = readFile(path);
        var input = parseInput(lines);
        stagesCache = new HashMap<>();

        var q = new PrioQueue();
        q.add(new Entry(new Point(0, 1), 0));
        var t1 = minMovesTo(q, input, new Point(input.map.length-2, input.map[0].length-2));

        q = new PrioQueue();
        q.add(new Entry(new Point(input.map.length-1, input.map[0].length-2), t1));
        var t2 = minMovesTo(q, input, new Point(1, 1));
        // dest row is set to 1, because minMovesTo() returns e.min+1. The next minute on which exp reaches the real destination which is row=0.

        q = new PrioQueue();
        q.add(new Entry(new Point(0, 1), t2));
        var t3 = minMovesTo(q, input, new Point(input.map.length-2, input.map[0].length-2));

        System.out.println(t1+" "+(t2-t1)+" "+(t3-t2));
        return t3;
    }

    private static int minMovesTo(PrioQueue q, Input input, Point dest) {
        while (!q.isEmpty()) {
            var e = q.remove();
            var stage = getStage(copy(input), e.min+1);
            var p = e.exp;

            if (stage.map[p.r][p.c] == '.') { q.add(new Entry(p, e.min+1)); }
            if (((p.r==1 && p.c==1) || p.r>1) && stage.map[p.r-1][p.c] == '.') {
                if (p.equals(dest)) return e.min+1;
                q.add(new Entry(new Point(p.r-1, p.c), e.min+1));
            }
            if (((p.r==input.map.length-2 && p.c==input.map[0].length-2) || p.r<input.map.length-2) && stage.map[p.r+1][p.c] == '.') {
                if (p.equals(dest)) return e.min+1;
                q.add(new Entry(new Point(p.r + 1, p.c), e.min + 1));
            }
            if ((p.c<input.map[0].length-2) && stage.map[p.r][p.c+1] == '.') { q.add(new Entry(new Point(p.r, p.c+1), e.min+1)); }
            if ((p.c>1) && stage.map[p.r][p.c-1] == '.') { q.add(new Entry(new Point(p.r, p.c-1), e.min+1)); }
        }
        throw new RuntimeException("Cannot reach destination");
    }

    private static Input getStage(Input input, int min) {
        if (!stagesCache.containsKey(min)) stagesCache.put(min, input.move(min));
        return stagesCache.get(min);
    }

    private static Input copy(Input input) {
        return new Input(copy(input.map), copy(input.blizzards));
    }

    private static char[][] copy(char[][] map) {
        var copy = new char[map.length][map[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                copy[i][j] = map[i][j];
            }
        }
        return copy;
    }

    private static List<Blizzard> copy(List<Blizzard> blizzards) {
        var copy = new ArrayList<Blizzard>();
        for (var b: blizzards) {
            copy.add(new Blizzard(b.dir, b.pos));
        }
        return copy;
    }

    private static Input parseInput(List<String> lines) {
        var blizzards = new ArrayList<Blizzard>();
        var map = new char[lines.size()][lines.get(0).length()];

        for (int i = 1; i < lines.size()-1; i++) {
            for (int j = 1; j < lines.get(i).length()-1; j++) {
                map[i][j] = lines.get(i).charAt(j);
                if (map[i][j] != '.') blizzards.add(new Blizzard(map[i][j], new Point(i, j)));
            }
        }

        return new Input(map, blizzards);
    }

    static class PrioQueue extends PriorityQueue<Entry> {
        @Override
        public boolean add(Entry entry) {
            var found = false;
            for (var e: this) {
                if (e.equals(entry)) found = true;
            }
            if (!found) return super.add(entry);
            else return true;
        }
    }

    record Entry(Point exp, int min) implements Comparable<Entry> {

        @Override
        public int compareTo(Entry o) {
            return min - o.min;
        }
    }

    @AllArgsConstructor
    static class Input {
        char[][] map;
        List<Blizzard> blizzards;

        public void show(Point e) {
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    System.out.print(e.r==i && e.c ==j ? 'E' : map[i][j]);
                }
                System.out.println();
            }
        }

        public void move() {
            for (var b: blizzards) {
                switch (b.dir) {
                    case '>' -> b.pos = (b.pos.c+1 == map[b.pos.r].length-1) ? new Point(b.pos.r, 1) : new Point(b.pos.r, b.pos.c+1);
                    case '<' -> b.pos = (b.pos.c-1 == 0) ? new Point(b.pos.r, map[b.pos.r].length-2) : new Point(b.pos.r, b.pos.c-1);
                    case '^' -> b.pos = (b.pos.r-1 == 0) ? new Point(map.length-2, b.pos.c) : new Point(b.pos.r-1, b.pos.c);
                    case 'v' -> b.pos = (b.pos.r+1 == map.length-1) ? new Point(1, b.pos.c) : new Point(b.pos.r+1, b.pos.c);
                }
            }

            resetMap();

            for (var b: blizzards) {
                map[b.pos.r][b.pos.c] = b.dir;
            }
        }

        private void resetMap() {
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    map[i][j] = ((i==0 && j==1) || (i==map.length-1 && j==map[0].length-2)) ? '.' : (i==0 || j==0 || i==map.length-1 || j==map[0].length-1) ? '#' : '.';
                }
            }
        }

        public Input move(int i) {
            for (int j = 0; j < i; j++) {
                move();
            }
            return this;
        }
    }

    @AllArgsConstructor
    @ToString
    static class Blizzard {
        char dir;
        Point pos;
    }

    record Point(int r, int c) { }
//     Output

}

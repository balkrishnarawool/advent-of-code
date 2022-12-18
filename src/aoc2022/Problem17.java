package aoc2022;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static aoc.Utils.readFile;

public class Problem17 {

    public static void main(String[] args) throws IOException {
        solvePart1("./src/aoc2022/Problem17Input1.txt");
        solvePart1("./src/aoc2022/Problem17Input2.txt");
//        solvePart2("./src/aoc2022/Problem17Input1.txt");
//        solvePart2("./src/aoc2022/Problem17Input2.txt");
    }

    private static void solvePart1(String path) throws IOException {
        System.out.println(solve(path));
    }

    private static void solvePart2(String path) throws IOException {
//        System.out.println(solve2(path));
    }

    private static long solve(String path) throws IOException {
        var lines = readFile(path);
        var g = new Generator(lines.get(0));
        var map = new Map();

        for (int i = 0; i < 2022; i++) {
            var r = map.addRock(i%5);
            var t = g.getNextToken();
            map.moveRock(map, r, t);
            while (!r.hasStopped(map)) {
                map.moveRock(map, r, "|");
                t = g.getNextToken();
                map.moveRock(map, r, t);
            }
            map.settle(r);
//            show(map);
        }
        return map.getHeight();
    }

    private static void show(Map map) {
        System.out.println();
        for (int i = map.map.size()-1; i >= 0; i--) {
            for (var s: map.map.get(i)) {
                System.out.print(s);
            }
            System.out.println();
        }
    }

    @RequiredArgsConstructor
    static class Generator {
        @NonNull
        String s;
        int index = -1;

        public String getNextToken() {
            if (index == s.length()-1) index = 0;
            else index++;
            return ""+s.charAt(index);
        }
    }

    static class Map {
        List<String[]> map = new ArrayList<>();

        public void moveRock(Map map, Rock r, String t) {
            switch (t) {
                case ">":
                    if (!r.cannotGoRight(map))
                        r.p = new Point(r.p.i, r.p.j+1);
                    break;
                case "<":
                    if (!r.cannotGoLeft(map))
                        r.p = new Point(r.p.i, r.p.j-1);
                    break;
                case "|":
                    if (!r.cannotGoDown(map))
                        r.p = new Point(r.p.i-1, r.p.j);
                    break;
            }
        }

        public Rock addRock(int i) {
            var r = new Rock(Rocks.getTemplate(i), new Point(getHeight() + 3,2));
            addRows(7);
            return r;
        }

        private void addRows(int n) {
            for (int i = 0; i < n; i++) {
                map.add(new String[] {".",".",".",".",".",".","."});
            }
        }

        public int getHeight() {
            return map.size();
        }

        public void settle(Rock r) {
            for (int i = 0; i < r.t.length; i++) {
                for (int j = 0; j < r.t[i].length; j++) {
                    if (r.t[i][j].equals("#")) {
                        map.get(r.p.i + i)[r.p.j + j] = "#";
                    }
                }
            }
            map.removeIf(e -> Arrays.equals(e, new String[]{".", ".", ".", ".", ".", ".", "."}));
        }
    }

    @AllArgsConstructor
    static class Rock {
        String[][] t; // template
        Point p; // position

        public boolean hasStopped(Map map) {
            return cannotGoDown(map);
        }

        public boolean cannotGoRight(Map map) {
            if (t == Rocks.templates[0]) {
                return (p.j >= 3 ||
                        map.map.get(p.i)[p.j+4].equals("#") );
            }
            if (t == Rocks.templates[1]) {
                return (p.j >= 4 ||
                        map.map.get(p.i)[p.j+2].equals("#") ||
                        map.map.get(p.i+1)[p.j+3].equals("#") ||
                        map.map.get(p.i+2)[p.j+2].equals("#") );
            }
            if (t == Rocks.templates[2]) {
                return (p.j >= 4 ||
                        map.map.get(p.i)[p.j+3].equals("#") ||
                        map.map.get(p.i+1)[p.j+3].equals("#") ||
                        map.map.get(p.i+2)[p.j+3].equals("#") );
            }
            if (t == Rocks.templates[3]) {
                return (p.j >= 6 ||
                        map.map.get(p.i)[p.j+1].equals("#") ||
                        map.map.get(p.i+1)[p.j+1].equals("#") ||
                        map.map.get(p.i+2)[p.j+1].equals("#") ||
                        map.map.get(p.i+3)[p.j+1].equals("#") );
            }
            if (t == Rocks.templates[4]) {
                return (p.j >= 5 ||
                        map.map.get(p.i)[p.j+2].equals("#") ||
                        map.map.get(p.i+1)[p.j+2].equals("#") );
            }
            return false;
        }

        public boolean cannotGoLeft(Map map) {
            if (p.j == 0) return true;
            if (t == Rocks.templates[0]) {
                return (map.map.get(p.i)[p.j-1].equals("#") );
            }
            if (t == Rocks.templates[1]) {
                return (map.map.get(p.i)[p.j].equals("#") ||
                        map.map.get(p.i+1)[p.j-1].equals("#") ||
                        map.map.get(p.i+2)[p.j].equals("#") );
            }
            if (t == Rocks.templates[2]) {
                return (map.map.get(p.i)[p.j-1].equals("#") ||
                        map.map.get(p.i+1)[p.j+2].equals("#") ||
                        map.map.get(p.i+2)[p.j+2].equals("#") );
            }
            if (t == Rocks.templates[3]) {
                return (map.map.get(p.i)[p.j-1].equals("#") ||
                        map.map.get(p.i+1)[p.j-1].equals("#") ||
                        map.map.get(p.i+2)[p.j-1].equals("#") ||
                        map.map.get(p.i+3)[p.j-1].equals("#") );
            }
            if (t == Rocks.templates[4]) {
                return (map.map.get(p.i)[p.j-1].equals("#") ||
                        map.map.get(p.i+1)[p.j-1].equals("#") );
            }
            return false;
        }

        public boolean cannotGoDown(Map map) {
            if (p.i == 0) return true;
            if (t == Rocks.templates[0]) {
                return (map.map.get(p.i-1)[p.j].equals("#") ||
                        map.map.get(p.i-1)[p.j+1].equals("#") ||
                        map.map.get(p.i-1)[p.j+2].equals("#") ||
                        map.map.get(p.i-1)[p.j+3].equals("#") );
            }
            if (t == Rocks.templates[1]) {
                return (map.map.get(p.i)[p.j].equals("#") ||
                        map.map.get(p.i-1)[p.j+1].equals("#") ||
                        map.map.get(p.i)[p.j+2].equals("#") );
            }
            if (t == Rocks.templates[2]) {
                return (map.map.get(p.i-1)[p.j].equals("#") ||
                        map.map.get(p.i-1)[p.j+1].equals("#") ||
                        map.map.get(p.i-1)[p.j+2].equals("#") );
            }
            if (t == Rocks.templates[3]) {
                return (map.map.get(p.i-1)[p.j].equals("#") );
            }
            if (t == Rocks.templates[4]) {
                return (map.map.get(p.i-1)[p.j].equals("#") ||
                        map.map.get(p.i-1)[p.j+1].equals("#") );
            }
            return false;
        }
    }

    record Point(int i, int j) { }

    static class Rocks {
        static String[][][] templates = {
            {   {"#","#","#","#"},
                {".",".",".","."},
                {".",".",".","."},
                {".",".",".","."}},

            {   {".","#",".","."},
                {"#","#","#","."},
                {".","#",".","."},
                {".",".",".","."}},

            {   {"#","#","#","."},
                {".",".","#","."},
                {".",".","#","."},
                {".",".",".","."}},

            {   {"#",".",".","."},
                {"#",".",".","."},
                {"#",".",".","."},
                {"#",".",".","."}},

            {   {"#","#",".","."},
                {"#","#",".","."},
                {".",".",".","."},
                {".",".",".","."}}
        };

        private static String[][] getTemplate(int i) {
            return templates[i];
        }
    }
//     Output

}

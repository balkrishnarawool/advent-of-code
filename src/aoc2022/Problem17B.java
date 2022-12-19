package aoc2022;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static aoc.Utils.readFile;

public class Problem17B {

    public static void main(String[] args) throws IOException {
        solvePart2("./src/aoc2022/Problem17Input1.txt");
        solvePart2("./src/aoc2022/Problem17Input2.txt");
    }

    private static void solvePart2(String path) throws IOException {
        System.out.println(solve(path));
    }

    private static long solve(String path) throws IOException {
        var lines = readFile(path);
        var g = new Generator(lines.get(0));
        var map = new Map();

        long l = 0L;
        var matchFound = false;
        for (long i = 0; i < 1_000_000_000_000L; i++) {
            var r = map.addRock((int) Math.floorMod(i, 5L));
            var tw = g.getNextToken();
            map.moveRock(map, r, tw.t);
            while (!r.hasStopped(map)) {
                map.moveRock(map, r, "|");
                tw = g.getNextToken(map, r, l, i);
                if (!matchFound && tw.matched) {
                    matchFound = true;
                    l = tw.l; i = tw.i;
                }
                map.moveRock(map, r, tw.t);
            }
            l += map.settle(r);
        }
        return l + map.getHeight();
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
        List<Map> maps = new ArrayList<>();
        List<Rock> rocks = new ArrayList<>();
        List<Long> ls = new ArrayList<>(); // l-s
        List<Long> is = new ArrayList<>(); // i-s

        public TokenWrapper getNextToken() {
            var matched = false;
            var newL = -1L;
            var newI = -1L;
            if (index == s.length()-1) {
                index = 0;
            }
            else index++;
            return new TokenWrapper(""+s.charAt(index), newL, newI, matched);
        }

        public TokenWrapper getNextToken(Map map, Rock r, long l, long i) {
            var matched = false;
            var newL = -1L;
            var newI = -1L;
            if (index == s.length()-1) {
                index = 0;
                if (contains(maps, map, rocks, r)) {
                    matched = true;
                    var i2 = getIndex(maps, map, rocks, r);

                    var quotient = Math.floorDiv(1_000_000_000_000L - is.get(i2), (i - is.get(i2)));
                    var deltaI = (i - is.get(i2)) * quotient;
                    newI = is.get(i2) + deltaI;
                    var deltaL = (l - ls.get(i2)) * quotient;
                    newL = ls.get(i2) + deltaL;
                } else {
                    maps.add(copy(map));
                    rocks.add(copy(r));
                    ls.add(l);
                    is.add(i);
                }
            }
            else index++;
            return new TokenWrapper(""+s.charAt(index), newL, newI, matched);
        }

        private int getIndex(List<Map> maps, Map map, List<Rock> rocks, Rock rock) {
            for (int i = 0; i < maps.size(); i++) {
                var r = rocks.get(i);
                var m = maps.get(i);
                if (sameAs(m, map) && r.t == rock.t && r.p.equals(rock.p)) return i;
            }
            throw new RuntimeException("Cannot find index for match");
        }

        private boolean contains(List<Map> maps, Map map, List<Rock> rocks, Rock rock) {
            for (int i = 0; i < maps.size(); i++) {
                var r = rocks.get(i);
                var m = maps.get(i);
                if (sameAs(m, map) && r.t == rock.t && r.p.equals(rock.p)) return true;
            }
            return false;
        }

        private boolean sameAs(Map m1, Map m2) {
            if (m1.map.size() == m2.map.size()) {
                for (int i = 0; i < m1.map.size(); i++) {
                    for (int j = 0; j < m1.map.get(i).length; j++) {
                        if (!m1.map.get(i)[j].equals(m2.map.get(i)[j])) return false;
                    }
                }
                return true;
            }
            return false;
        }

        private Map copy(Map map) {
            var copy = new ArrayList<String[]>();
            for (var row: map.map) {
                copy.add(copy(row));
            }
            var copyMap = new Map();
            copyMap.map = copy;
            return copyMap;
        }

        private String[] copy(String[] row) {
            var copy = new String[row.length];
            System.arraycopy(row, 0, copy, 0, row.length);
            return copy;
        }

        private Rock copy(Rock r) {
            return new Rock(r.t, r.p);
        }
    }

    record TokenWrapper(String t, long l, long i, boolean matched) { }

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

        public long settle(Rock r) {
            for (int i = 0; i < r.t.length; i++) {
                for (int j = 0; j < r.t[i].length; j++) {
                    if (r.t[i][j].equals("#")) {
                        map.get(r.p.i + i)[r.p.j + j] = "#";
                    }
                }
            }
            map.removeIf(e -> Arrays.equals(e, new String[]{".", ".", ".", ".", ".", ".", "."}));
            var min = Integer.MAX_VALUE;
            for (int i = 0; i < 7; i++) {
                int j = map.size()-1;
                for (; j >=0 ; j--) {
                    if (map.get(j)[i].equals("#")) break;
                }
                if (j == -1) j = 0;
                min = Math.min(min, j);
            }
            if (min > 0) {
                map.subList(0, min).clear();
            }
            return min;
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
//      1514285714288
//      1602881844347
}

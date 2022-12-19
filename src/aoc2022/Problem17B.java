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

    static long ONE_TRILLION = 1_000_000_000_000L;

    public static void main(String[] args) throws IOException {
        solvePart2("./src/aoc2022/Problem17Input1.txt");
        solvePart2("./src/aoc2022/Problem17Input2.txt");
    }

    private static void solvePart2(String path) throws IOException {
        System.out.println(solve(path));
    }

    private static long solve(String path) throws IOException {
        var mapStore = new MapStore();
        var lines = readFile(path);
        var g = new Generator(lines.get(0));
        var map = new Map();

        long scroll = 0L; // number of lines scrolled
        var matchFound = false;
        for (long i = 0; i < ONE_TRILLION ; i++) {
            var r = map.addRock((int) Math.floorMod(i, 5L));
            var tw = g.getNextToken();
            map.moveRock(map, r, tw.t);

            while (!r.hasStopped(map)) {
                map.moveRock(map, r, "|");
                tw = g.getNextToken();

                if (!matchFound && tw.looped) {
                    if (mapStore.match(map, r)) {
                        matchFound = true;
                        var si = mapStore.calculateScrollAndIndex(map, r, scroll, i);
                        scroll = si.scroll;
                        i = si.index;
                    } else {
                        mapStore.saveEntry(map, r, scroll, i);
                    }
                }
                map.moveRock(map, r, tw.t);
            }
            scroll += map.settle(r);
        }
        return scroll + map.getHeight();
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

        public TokenWrapper getNextToken() {
            var looped = false;
            if (index == s.length()-1) {
                index = 0;
                looped = true;
            }
            else index++;
            return new TokenWrapper(""+s.charAt(index), looped);
        }
    }

    record TokenWrapper(String t, boolean looped) { }

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

    static class MapStore {
        List<Entry> entries = new ArrayList<>();

        public boolean match(Map map, Rock r) {
            return getIndex(map, r) >= 0;
        }

        public void saveEntry(Map map, Rock r, long l, long i) {
            entries.add(new Entry(copy(map), copy(r), l, i));
        }

        public ScrollAndIndex calculateScrollAndIndex(Map map, Rock r, long l, long i) {
            var i2 = getIndex(map, r);
            if (i2 >= 0) {
                var quotient = Math.floorDiv(ONE_TRILLION - entries.get(i2).index, (i - entries.get(i2).index));
                var deltaI = (i - entries.get(i2).index) * quotient;
                var newI = entries.get(i2).index + deltaI;
                var deltaS = (l - entries.get(i2).scroll) * quotient;
                var newS = entries.get(i2).scroll + deltaS;
                return new ScrollAndIndex(newS, newI);
            } else {
                throw new RuntimeException("");
            }
        }

        private int getIndex(Map map, Rock rock) {
            for (int i = 0; i < entries.size(); i++) {
                var r = entries.get(i).rock;
                var m = entries.get(i).map;
                if (sameAs(m, map) && r.t == rock.t && r.p.equals(rock.p)) return i;
            }
            return -1;
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

    record ScrollAndIndex(long scroll, long index) { }
    record Entry(Map map, Rock rock, long scroll, long index) { }

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

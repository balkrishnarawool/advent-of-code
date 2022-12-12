package aoc2022;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

import static aoc.Utils.readFile;

public class Problem12B {

    public static void main(String[] args) throws IOException {
//        solvePart1("./src/aoc2022/Problem12Input1.txt");
//        solvePart1("./src/aoc2022/Problem12Input2.txt");
        solvePart2("./src/aoc2022/Problem12Input1.txt");
        solvePart2("./src/aoc2022/Problem12Input2.txt");
    }

    private static void solvePart1(String path) throws IOException {
//        System.out.println(solve(path));
    }

    private static void solvePart2(String path) throws IOException {
        System.out.println(solve2(path));
    }

    private static int solve2(String path) throws IOException {
        var map = createMap(readFile(path));
        var as = getAllAs(map);
        int min = Integer.MAX_VALUE;
        Cell minA = null;
        for (var a: as) {
            var d = getShortestDist(a, map);
            if (d < min) { min = d; minA = a; }
        }
        return min;
    }

    private static List<Cell> getAllAs(Map map) {
        var as = new ArrayList<Cell>();
        for (int i = 0; i < map.grid.length; i++) {
            for (int j = 0; j < map.grid[i].length; j++) {
                if (map.grid[i][j] == 0) as.add(new Cell(i, j));
            }
        }
        return as;
    }

    private static Map createMap(List<String> lines) {
        var grid = new int[lines.size()][];
        Cell e = null;
        for (int i = 0; i < lines.size(); i++) {
            var str = lines.get(i);
            grid[i] = new int[str.length()];
            var a = str.toCharArray();
            for (int j = 0; j < lines.get(i).length(); j++) {
                if (a[j] == 'S') { a[j] = 'a'; }
                if (a[j] == 'E') { e = new Cell(i, j); a[j] = 'z'; }
                grid[i][j] = a[j] - 'a';
            }
        }
        return new Map(e, grid);
    }

    private static int getShortestDist(Cell s, Map map) {
        var q = new PriorityQueue<Entry>();
        q.add(new Entry(s, 0));
        var visited = new HashSet<Cell>();
        while (!q.isEmpty()) {
            var e = q.remove();

            if (e.c.r - 1 >= 0) {
                var u = new Cell(e.c.r - 1, e.c.c); // up
                if (!visited.contains(u) && map.grid[u.r][u.c] <= map.grid[e.c.r][e.c.c] + 1) {
                    if (u.equals(map.e)) { return e.d + 1; }
                    var newE = new Entry(u, e.d + 1);
                    addIfNeeded(q, newE);
                }
            }

            if (e.c.r + 1 < map.grid.length) {
                var d = new Cell(e.c.r + 1, e.c.c); // down
                if (!visited.contains(d) && map.grid[d.r][d.c] <= map.grid[e.c.r][e.c.c] + 1) {
                    if (d.equals(map.e)) { return e.d + 1; }
                    var newE = new Entry(d, e.d + 1);
                    addIfNeeded(q, newE);
                }
            }

            if (e.c.c - 1 >= 0) {
                var l = new Cell(e.c.r, e.c.c - 1); // left
                if (!visited.contains(l) && map.grid[l.r][l.c] <= map.grid[e.c.r][e.c.c] + 1) {
                    if (l.equals(map.e)) { return e.d + 1; }
                    var newE = new Entry(l, e.d + 1);
                    addIfNeeded(q, newE);
                }
            }

            if (e.c.c + 1 < map.grid[0].length) {
                var r = new Cell(e.c.r, e.c.c + 1); // right
                if (!visited.contains(r) && map.grid[r.r][r.c] <= map.grid[e.c.r][e.c.c] + 1) {
                    if (r.equals(map.e)) { return e.d + 1; }
                    var newE = new Entry(r, e.d + 1);
                    addIfNeeded(q, newE);
                }
            }

            visited.add(e.c);
        }
        return Integer.MAX_VALUE;
    }

    private static void addIfNeeded(PriorityQueue<Entry> q, Entry newE) {
        var removeEs = new ArrayList<Entry>();
        for (var e: q) {
            if (e.c.r == newE.c.r && e.c.c == newE.c.c) {
                if (e.d >= newE.d) {
                    removeEs.add(e);
                }
            }
        }
        removeEs.forEach(q::remove);
        q.add(newE);
    }

    record Map (Cell e, int[][] grid ) { } // start and end
    record Cell (int r, int c) { } // row and column
    record Entry (Cell c, int d) implements Comparable<Entry> {
        @Override
        public int compareTo(Entry e) {
            return d - e.d;
        }
    } // cell and distance

//     Output
//     29
//     443

}

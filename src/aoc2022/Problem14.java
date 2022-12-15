package aoc2022;

import lombok.AllArgsConstructor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static aoc.Utils.readFile;

public class Problem14 {

    public static void main(String[] args) throws IOException {
        solvePart1("./src/aoc2022/Problem14Input1.txt");
        solvePart1("./src/aoc2022/Problem14Input2.txt");
        solvePart2("./src/aoc2022/Problem14Input1.txt");
        solvePart2("./src/aoc2022/Problem14Input2.txt");
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
        var offset = offset(input);
        var map = createMap(input, offset);
        int units = 0;
        while(dropSand(map, new Point(500-offset, 0))) {
           units++;
        }
//        show(map);
        return units;
    }

    private static long solve2(String path) throws IOException {
        var lines = readFile(path);
        var input = parseInput(lines);
        var offset = offset(input);

        // We need to have a line from -infinity to infinity. But 1000 is sufficient limit to simulate that.
        moveBy(input, 1000);
        offset -= 1000;
        var pl = new ArrayList<Point>();
        pl.add(new Point(0,input.getMaxY() + 2));
        pl.add(new Point(input.getMaxX()+1000 , input.getMaxY() + 2));
        input.points.add(pl);

        var map = createMap(input, offset);
//        show(map);
        int units = 0;
        while(dropSand(map, new Point(500-offset, 0))) {
            units++;
        }
//        show(map);
        return units;
    }

    private static void moveBy(Input input, int dist) {
        for (var pointsList: input.points) {
            for (var point: pointsList) {
                point.x += dist;
            }
        }
    }

    private static boolean dropSand(String[][] map, Point p) {
        if (p.x == -1) return false;
        if (p.x == map[0].length) return false;
        if (p.y == map.length) return false;

        if (map[p.y][p.x].equals("o")) return false;

        if (p.y<map.length-1 && blocked(map[p.y+1][p.x])) {
            if (p.y<map.length-1 && p.x > 0 && blocked(map[p.y+1][p.x-1])) {
                if (p.y<map.length-1 && p.x<map[0].length-1 && blocked(map[p.y+1][p.x+1])) {
                    // drop
                    map[p.y][p.x] = "o";
                    return true;
                }
                return dropSand(map, new Point(p.x+1, p.y+1));
            }
            return dropSand(map, new Point(p.x-1, p.y+1));
        }
        return dropSand(map, new Point(p.x, p.y+1));
    }

    private static boolean blocked(String s) {
        return !s.equals(".");
    }

    private static int offset(Input input) {
        var min = input.getMinX();
        for (var pointsList: input.points) {
            for (var point: pointsList) {
                point.x -= min;
            }
        }
        return min;
    }

    private static String[][] createMap(Input input, int offset) {
        var maxX = input.getMaxX();
        var maxY = input.getMaxY();
        var map = new String[maxY+1][maxX+1];
        fill(map, ".");
        map[0][500-offset] = "+";
        for (var pointsList: input.points) {
            for (int i = 0; i < pointsList.size()-1; i++) {
                var s = pointsList.get(i);
                var e = pointsList.get(i+1);
                if (s.x == e.x) {
                    if (s.y > e.y) {
                        var t = e; e = s; s = t;
                    }
                    for (int j = s.y; j <= e.y; j++) {
                        map[j][s.x] = "#";
                    }
                } else {
                    if (s.x > e.x) {
                        var t = e; e = s; s = t;
                    }
                    for (int j = s.x; j <= e.x; j++) {
                        map[s.y][j] = "#";
                    }
                }
            }
        }
//        show(map);
        return map;
    }

    private static void fill(String[][] map, String s) {
        for (var strings : map) {
            Arrays.fill(strings, s);
        }
    }

    private static void show(String[][] map) {
        for (var strings : map) {
            System.out.println(Arrays.toString(strings));
        }
    }
    private static Input parseInput(List<String> lines) {
        var points = new ArrayList<List<Point>>();
        for (var l: lines) {
            var pointsList = new ArrayList<Point>();
            var sa = l.split(" -> ");
            for (var sp: sa) {
                var sa2 = sp.split(",");
                pointsList.add(new Point(Integer.parseInt(sa2[0]), Integer.parseInt(sa2[1])));
            }
            points.add(pointsList);
        }
        return new Input(points);
    }

    record Input(List<List<Point>> points) {
        private int getMinX() {
            var minX = Integer.MAX_VALUE;
            for (var pointsList: points) {
                for (var point: pointsList) {
                    minX = Math.min(minX, point.x);
                }
            }
            return minX;
        }

        private int getMaxX() {
            var maxX = Integer.MIN_VALUE;
            for (var pointsList: points) {
                for (var point: pointsList) {
                    maxX = Math.max(maxX, point.x);
                }
            }
            return maxX;
        }

        private int getMaxY() {
            var maxY = Integer.MIN_VALUE;
            for (var pointsList: points) {
                for (var point: pointsList) {
                    maxY = Math.max(maxY, point.y);
                }
            }
            return maxY;
        }
    }

    @AllArgsConstructor
    static class Point {
        int x; int y;
    }

//     Output
//     24
//     1061
//     93
//     25055

}

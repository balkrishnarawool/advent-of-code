package aoc2022;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static aoc.Utils.readFile;

public class Problem15 {

    public static void main(String[] args) throws IOException {
//        solvePart1("./src/aoc2022/Problem15Input1.txt", 10);
//        solvePart1("./src/aoc2022/Problem15Input2.txt", 2000000);
//        solvePart2("./src/aoc2022/Problem15Input1.txt", 20);
        solvePart2("./src/aoc2022/Problem15Input2.txt", 4000000);
    }

    private static void solvePart1(String path, long y) throws IOException {
        System.out.println(solve(path, y));
    }

    private static void solvePart2(String path, long d) throws IOException {
//        System.out.println(solve2(path, d));
        System.out.println(System.currentTimeMillis());
        for (int i = 0; i < d; i++) {
            for (int j = 0; j < d; j++) {
//                System.out.println(j);
            }
        }
        System.out.println(System.currentTimeMillis());
    }

    private static long solve(String path, long y) throws IOException {
        var lines = readFile(path);
        var input = parseInput(lines);
        var min = getMin(input);
        var max = getMax(input);
        return countOfEmptyPlaces(input, y, min, max);
    }

    private static long solve2(String path, long d) throws IOException {
        var lines = readFile(path);
        var input = parseInput(lines);
        return signal(findHiddenBeacon(input, d));
    }

    private static long signal(Point p) {
        return p.x * 4000000 + p.y;
    }

    private static Point findHiddenBeacon(Input input, long d) {
        for (int i = 0; i <= d; i++) {
            for (int j = 0; j <= d; j++) {
                var p = new Point(i, j);
                if (!isBeaconHere(input, p) && canBeaconBeHere(input, p))
                    return p;
            }
            System.out.println(i);
        }
        throw new RuntimeException("No beacon is hidden");
    }

    private static long countOfEmptyPlaces(Input input, long y, Point min, Point max) {
        int c = 0;
        for (long i = min.x; i <= max.x; i++) {
            var p = new Point(i, y);
            if (!isBeaconHere(input, p) && !canBeaconBeHere(input, p)) {
                System.out.print("#");
                c++;
            } else {
                System.out.print(".");
            }
        }
        return c;
    }

    private static boolean isBeaconHere(Input input, Point p) {
        for (var pair: input.pairs) {
            if (p.equals(pair.beacon)) return true;
        }
        return false;
    }

    private static boolean canBeaconBeHere(Input input, Point p) {
        for (var pair: input.pairs) {
            var dist = dist(pair.sensor, pair.beacon);
            var dist2 = dist(pair.sensor, p);
            if (dist2 <= dist) return false;
        }
        return true;
    }

    private static Point getMin(Input input) {
        var minX = Long.MAX_VALUE;
        var minY = Long.MAX_VALUE;
        for (var pair: input.pairs) {
            var dist = dist(pair.sensor, pair.beacon);
            minX = Math.min(minX, pair.sensor.x - dist);
            minY = Math.min(minY, pair.sensor.y - dist);
        }
        return new Point(minX, minY);
    }

    private static Point getMax(Input input) {
        var maxX = Long.MIN_VALUE;
        var maxY = Long.MIN_VALUE;
        for (var pair: input.pairs) {
            var dist = dist(pair.sensor, pair.beacon);
            maxX = Math.max(maxX, pair.sensor.x + dist);
            maxY = Math.max(maxY, pair.sensor.y + dist);
        }
        return new Point(maxX, maxY);
    }

    private static long dist(Point a, Point b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }

    private static Input parseInput(List<String> lines) {
        var input = new Input();
        for (var s: lines) {
            var sa = s.split(" ");
            long x1 = Long.parseLong(sa[2].substring(2, sa[2].indexOf(',')));
            long y1 = Long.parseLong(sa[3].substring(2, sa[3].indexOf(':')));
            long x2 = Long.parseLong(sa[8].substring(2, sa[8].indexOf(',')));
            long y2 = Long.parseLong(sa[9].substring(2));
            input.pairs.add(new Pair(new Point(x1, y1), new Point(x2, y2)));
        }
        return input;
    }

    static class Input {
         List<Pair> pairs = new ArrayList<>();
    }
    record Pair(Point sensor, Point beacon) { }
    record Point(long x, long y) { }

//     Output


}

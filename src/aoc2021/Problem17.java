package aoc2021;

import aoc.Tuple2;

import java.util.HashSet;
import java.util.Set;

import static aoc.Utils.fileToStringArray;

public class Problem17 {

    private static void solve(String[] input) {
        String str = input[0].substring("target area: x=".length());
        String[] strs = str.split(", y=");
        String[] xs = strs[0].split("\\..");
        String[] ys = strs[1].split("\\..");

        int x1 = Integer.parseInt(xs[0]);
        // No need x is never < 0 // x1 = (x1 < 0) ? -x1 : x1;
        int x2 = Integer.parseInt(xs[1]);
        // No need x is never < 0 // x2 = (x2 < 0) ? -x2 : x2;
        int y1 = Integer.parseInt(ys[0]);
        int y2 = Integer.parseInt(ys[1]);

        int minX = calcMinX(x1, y1, x2, y2);
        int maxX = calcMaxX(x1, y1, x2, y2);
        int minY = calcMinY(x1, y1, x2, y2);
        int maxY = calcMaxY(x1, y1, x2, y2);

        outer: for (int y = maxY; y >= minY; y--) {
            for (int x = maxX; x >= minX ; x--) {
                if (canHitTarget(x, y, x1, y1, x2, y2)) {
                    System.out.println("Max y: "+ y*(y+1)/2);
                    break outer;
                }
            }
        }
        int c = 0;
        for (int y = maxY; y >= minY; y--) {
            for (int x = maxX; x >= minX ; x--) {
                if (canHitTarget(x, y, x1, y1, x2, y2)) {
                    c++;
                }
            }
        }
        System.out.println("Count: "+ c);
    }

    private static int calcMinX(int x1, int y1, int x2, int y2) {
        return (int)((Math.sqrt((x1 * 8) + 1) - 1)/2);
    }

    private static int calcMaxX(int x1, int y1, int x2, int y2) {
        return x2;
    }

    private static int calcMinY(int x1, int y1, int x2, int y2) {
        return (y1 < 0) ? y1 : (int)((Math.sqrt((y1 * 8) + 1) - 1)/2);
    }

    private static int calcMaxY(int x1, int y1, int x2, int y2) {
        return (y1 < 0) ? -(y1+1) : y2;
    }

    private static boolean canHitTarget(int x, int y, int x1, int y1, int x2, int y2) { // Top-left is (x1,y2), bottom-right is (x2, y1)
        int tx = 0;
        int dx = x;
        int ty = 0;
        int dy = y;
        while (tx <= x2 && ty >= y1) {
            if ((tx == x1 || (tx > x1 && tx < x2) || tx == x2) &&
                    (ty == y1 || (ty > y1 && ty < y2) || ty == y2)) { return true; }
            else {
                tx += dx;
                ty += dy;
                dx = (dx == 0) ? 0 : (dx - 1);
                dy--;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String[] input1 = fileToStringArray(Problem17.class, "Problem17Input1.txt");
        String[] input2 = fileToStringArray(Problem17.class, "Problem17Input2.txt");

        solve(input1);
        solve(input2);
    }

}

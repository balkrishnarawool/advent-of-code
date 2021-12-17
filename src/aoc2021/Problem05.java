package aoc2021;

import lombok.AllArgsConstructor;
import lombok.Value;

import static aoc.Utils.fileToStringArray;
import static aoc.Utils.split;

public class Problem05 {

    @Value // Adds private final to all non-static fields
    @AllArgsConstructor(staticName = "of")
    private static class Point { int x; int y; }

    private static long countOverlapPoints(String[] strs, boolean diagonals) {
        int[][] arr = new int[1_000][1_000];
        for (String str: strs) {
            String[] sa = str.split(" -> ");
            String[] sa2 = sa[0].split(",");
            String[] sa3 = sa[1].split(",");
            Point p1 = Point.of(Integer.parseInt(sa2[0]), Integer.parseInt(sa2[1]));
            Point p2 = Point.of(Integer.parseInt(sa3[0]), Integer.parseInt(sa3[1]));
            if (p1.x == p2.x) {
                for (int j = Math.min(p1.y, p2.y); j <= Math.max(p1.y, p2.y); j++) {
                    arr[p1.x][j]++;
                }
            } else {
                if (p1.y == p2.y) {
                    for (int j = Math.min(p1.x, p2.x); j <= Math.max(p1.x, p2.x); j++) {
                        arr[j][p1.y]++;
                    }
                } else {
                    if (diagonals) {
                        if (p1.x > p2.x) {
                            Point t = p1;
                            p1 = p2;
                            p2 = t;
                        }
                        for (int j = 0; j <= p2.x-p1.x; j++) {
                            if (p1.y < p2.y) {
                                arr[p1.x+j][p1.y+j]++;
                            } else {
                                arr[p1.x+j][p1.y-j]++;
                            }
                        }
                    }
                }
            }
        }

        int c = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] >= 2) c++;
            }
        }
        return c;
    }

    public static void main(String[] args) {
        String[] input1 = fileToStringArray(Problem04A.class, "Problem05Input1.txt");
        String[] input2 = fileToStringArray(Problem04A.class, "Problem05Input2.txt");

        System.out.println(countOverlapPoints(input1, false));
        System.out.println(countOverlapPoints(input2, false));

        System.out.println(countOverlapPoints(input1, true));
        System.out.println(countOverlapPoints(input2, true));

//        Output

    }

}

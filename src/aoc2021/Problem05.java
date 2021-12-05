package aoc2021;

import static aoc.Utils.fileToStringArray;

public class Problem05 {

    private static long countOverlapPoints(String[] strs, boolean diagonals) {
        int[][] arr = new int[1_000][1_000];
        for (int i = 0; i < strs.length; i++) {
            String str = strs[i];
            String[] sa = str.split(" -> ");
            String[] sa2 = sa[0].split(",");
            String[] sa3 = sa[1].split(",");
            int x1 = Integer.parseInt(sa2[0]);
            int y1 = Integer.parseInt(sa2[1]);
            int x2 = Integer.parseInt(sa3[0]);
            int y2 = Integer.parseInt(sa3[1]);
            if (x1 == x2) {
                for (int j = Math.min(y1, y2); j <= Math.max(y1, y2); j++) {
                    arr[x1][j]++;
                }
            } else {
                if (y1 == y2) {
                    for (int j = Math.min(x1, x2); j <= Math.max(x1, x2); j++) {
                        arr[j][y1]++;
                    }
                } else {
                    if (diagonals) {
                        if (x1 > x2) {
                            int t = x1;
                            x1 = x2;
                            x2 = t;
                            t = y1;
                            y1 = y2;
                            y2 = t;
                        }
                        for (int j = 0; j <= x2-x1; j++) {
                            if (y1 < y2) {
                                arr[x1+j][y1+j]++;
                            } else {
                                arr[x1+j][y1-j]++;
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

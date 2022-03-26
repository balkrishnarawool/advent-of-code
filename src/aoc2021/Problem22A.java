package aoc2021;

import static aoc.Utils.fileToStringArray;

public class Problem22A {

    private static void solve(String[] input) {
        boolean[][][] cubes = new boolean[101][101][101];

        for (String str: input) {
            String[] strs = str.split(" ");
            boolean m = strs[0].equals("on");
            String[] strs2 = strs[1].split(",");
            String[] strsx = strs2[0].substring(2).split("\\..");
            int x1 = Integer.parseInt(strsx[0]);
            int x2 = Integer.parseInt(strsx[1]);
            String[] strsy = strs2[1].substring(2).split("\\..");
            int y1 = Integer.parseInt(strsy[0]);
            int y2 = Integer.parseInt(strsy[1]);
            String[] strsz = strs2[2].substring(2).split("\\..");
            int z1 = Integer.parseInt(strsz[0]);
            int z2 = Integer.parseInt(strsz[1]);

            if (x1 <= 50 && x2 >= -50 && y1 <= 50 && y2 >= -50 && z1 <= 50 && z2 >= -50) {
                for (int x = x1; x <= x2; x++) {
                    for (int y = y1; y <= y2; y++) {
                        for (int z = z1; z <= z2; z++) {
                            cubes[x+50][y+50][z+50] = m;
                        }
                    }
                }
            }
            System.out.println(count(cubes));
        }
    }

    private static int count(boolean[][][] cubes) {
        int c = 0;
        for (int x = 0; x <= 100; x++) {
            for (int y = 0; y <= 100; y++) {
                for (int z = 0; z <= 100; z++) {
                    if (cubes[x][y][z]) c++;
                }
            }
        }
        return c;
    }

    public static void main(String[] args) {
        String[] input1 = fileToStringArray(Problem22A.class, "Problem22Input1.txt");
        String[] input2 = fileToStringArray(Problem22A.class, "Problem22Input2.txt");

        solve(input1);
        solve(input2);
    }
}

package aoc2021;

import lombok.AllArgsConstructor;
import lombok.Value;

import static aoc.Utils.fileToStringArray;

public class Problem11A {

    @AllArgsConstructor(staticName = "of")
    private static class Octo { int e; boolean flashed; }

    @Value
    @AllArgsConstructor(staticName = "of")
    private static class Coords { int x; int y; }

    static int c = 0;

    private static int countFlashes(String[] strs, int steps) {
        c = 0;
        Octo[][] arr = new Octo[strs.length][strs[0].length()];
        for (int i = 0; i < strs.length; i++) {
            String[] sa = strs[i].split("");
            for (int j = 0; j < strs[0].length(); j++) {
                arr[i][j] = Octo.of(Integer.parseInt(sa[j]), false);
            }
        }

        for (int i = 0; i < steps; i++) {
            increaseEnergy(arr);
            while (octosToFlash(arr)) {
                flashOcto(arr, getCoordsOfOctoToFlash(arr));
            }
            resetFlashedOctos(arr);
        }
        return c;
    }

    private static void increaseEnergy(Octo[][] arr) {
        for (Octo[] oa: arr) {
            for (Octo o: oa) {
                o.e++;
            }
        }
    }

    private static boolean octosToFlash(Octo[][] arr) {
        for (Octo[] oa: arr) {
            for (Octo o: oa) {
                if (o.e > 9 && !o.flashed) {
                    return true;
                }
            }
        }
        return false;
    }

    private static Coords getCoordsOfOctoToFlash(Octo[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                Octo o = arr[i][j];
                if (o.e > 9 && !o.flashed) {
                    return Coords.of(i, j);
                }
            }
        }
        return null;
    }

    private static void flashOcto(Octo[][] arr, Coords coords) {
        int i = coords.x;
        int j = coords.y;
        arr[i][j].flashed = true;
        c++;

        if (i > 0) { arr[i - 1][j].e++; }
        if (j > 0)  { arr[i][j - 1].e++; }
        if (i < arr.length - 1) { arr[i + 1][j].e++; }
        if (j < arr[i].length - 1) { arr[i][j + 1].e++; }

        if (i > 0 && j > 0) { arr[i - 1][j - 1].e++; }
        if (i > 0 && j < arr[i].length - 1) { arr[i - 1][j + 1].e++; }
        if (i < arr.length - 1 && j < arr[i].length - 1) { arr[i + 1][j + 1].e++; }
        if (i < arr.length - 1 && j > 0) { arr[i + 1][j - 1].e++; }
    }

    private static void resetFlashedOctos(Octo[][] arr) {
        for (Octo[] octos : arr) {
            for (Octo octo : octos) {
                if (octo.flashed) {
                    octo.flashed = false;
                    octo.e = 0;
                }
            }
        }
    }

    public static void main(String[] args) {
        String[] input1 = fileToStringArray(Problem11A.class, "Problem11Input1.txt");
        String[] input2 = fileToStringArray(Problem11A.class, "Problem11Input2.txt");

        System.out.println(countFlashes(input1, 100));
        System.out.println(countFlashes(input2, 100));

    }
}

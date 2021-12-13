package aoc2021;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.Arrays;

import static aoc.Utils.fileToStringArray;

public class Problem13 {

    @Value
    @AllArgsConstructor(staticName = "of")
    private static class Fold {String ax; int v; }

    private static int countDots(String[] input1, int foldCount) {
        int[][] nums = new int[input1.length - foldCount - 1][2];
        for (int i = 0; i < nums.length; i++) {
            String[] arr = input1[i].split(",");
            nums[i][0] = Integer.parseInt(arr[0]);
            nums[i][1] = Integer.parseInt(arr[1]);
        }
        int n = getDim(nums) + 1;
        String[][] dots = new String[n][n];
        for (String[] t : dots) {
            Arrays.fill(t, ".");
        }
        for (int[] num : nums) {
            dots[num[1]][num[0]] = "#";
        }

        Fold[] folds = new Fold[foldCount];
        for (int i = input1.length - foldCount; i < input1.length; i++) {
            String[] arr = input1[i].substring(11).split("=");
            folds[i- (input1.length - foldCount)] = Fold.of(arr[0], Integer.parseInt(arr[1]));
        }

        for (Fold f: folds) {
            foldDots(dots, f);
            print(dots);
        }
        return count(dots);
    }

    private static void print(String[][] dots) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < dots[i].length; j++) {
                    System.out.print(dots[i][j]);
            }
            System.out.println();
        }
        System.out.println(count(dots));
    }

    private static void foldDots(String[][] dots, Fold f) {
        if (f.ax.equals("x")) { foldX(dots, f.v); clearX(dots, f.v); }
        if (f.ax.equals("y")) { foldY(dots, f.v); clearY(dots, f.v); }
    }

    private static void foldY(String[][] dots, int v) {
        for (int i = v + 1; i < dots.length; i++) {
            for (int j = 0; j < dots[i].length; j++) {
                if (2 * v - i >= 0 && dots[i][j] == "#") { dots[2 * v - i][j] = "#"; }
            }
        }
    }

    private static void foldX(String[][] dots, int v) {
        for (int i = 0; i < dots.length; i++) {
            for (int j = v + 1; j < dots[i].length; j++) {
                if (2 * v - j >= 0 && dots[i][j] == "#") { dots[i][2 * v - j] = "#"; }
            }
        }
    }

    private static void clearY(String[][] dots, int v) {
        if (v > 0) {
            for (int i = v; i < dots.length; i++) {
                Arrays.fill(dots[i], ".");
            }
        }
    }

    private static void clearX(String[][] dots, int v) {
        if (v > 0) {
            for (int i = 0; i < dots.length; i++) {
                for (int j = v; j < dots[i].length; j++) {
                    dots[i][j] = ".";
                }
            }
        }
    }

    private static int count(String[][] dots) {
        int c = 0;
        for (String[] sa: dots) {
            for (String s: sa) {
                if (s == "#") { c++; }
            }
        }
        return c;
    }

    private static int getDim(int[][] nums) {
        int max = Integer.MIN_VALUE;
        for (int[] num : nums) {
            int t = Math.max(num[0], num[1]);
            max = Math.max(t, max);
        }

        return max;
    }

    public static void main(String[] args) {
        String[] input1 = fileToStringArray(Problem13.class, "Problem13Input1.txt");
        String[] input2 = fileToStringArray(Problem13.class, "Problem13Input2.txt");

        System.out.println(countDots(input1, 2));
        System.out.println(countDots(input2, 12));

    }

}

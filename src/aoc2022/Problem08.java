package aoc2022;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static aoc.Utils.readFile;

public class Problem08 {

    public static void main(String[] args) throws IOException {
        solvePart1("./src/aoc2022/Problem08Input1.txt");
        solvePart1("./src/aoc2022/Problem08Input2.txt");
        solvePart2("./src/aoc2022/Problem08Input1.txt");
        solvePart2("./src/aoc2022/Problem08Input2.txt");
    }

    private static void solvePart1(String path) throws IOException {
        System.out.println(solve(path));
    }

    private static void solvePart2(String path) throws IOException {
        System.out.println(solve2(path));
    }

    private static int solve(String path) throws IOException {
        var list = readFile(path);
        var arrt = new String[list.size()][];
        for (int i = 0; i < arrt.length; i++) {
            arrt[i] = list.get(i).split("");
        }
        var arr = new int[arrt.length][arrt[0].length];
        for (int i = 0; i < arrt.length; i++) {
            for (int j = 0; j < arrt[i].length; j++) {
                arr[i][j] = Integer.parseInt(arrt[i][j]);
            }
        }

        var c = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {

                var v = true;
                for (int k = 0; k < i; k++) {
                    if (arr[k][j] >= arr[i][j]) { v = false; break; }
                }
                if (!v) {
                    v = true;
                    for (int k = i+1; k < arr.length; k++) {
                        if (arr[k][j] >= arr[i][j]) { v = false; break; }
                    }
                }
                if (!v) {
                    v = true;
                    for (int k = 0; k < j; k++) {
                        if (arr[i][k] >= arr[i][j]) { v = false; break; }
                    }
                }
                if (!v) {
                    v = true;
                    for (int k = j+1; k < arr[i].length; k++) {
                        if (arr[i][k] >= arr[i][j]) { v = false; break; }
                    }
                }
                if (v) c++;
            }
        }
        return c;
    }

    private static long solve2(String path) throws IOException {
        var list = readFile(path);
        var arrt = new String[list.size()][];
        for (int i = 0; i < arrt.length; i++) {
            arrt[i] = list.get(i).split("");
        }
        var arr = new int[arrt.length][arrt[0].length];
        for (int i = 0; i < arrt.length; i++) {
            for (int j = 0; j < arrt[i].length; j++) {
                arr[i][j] = Integer.parseInt(arrt[i][j]);
            }
        }

        var sarr = new long[arr.length][arr[0].length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {

                var s = 1L;
                var t = 0L;
                for (int k = i-1; k >= 0; k--) {
                    t++;
                    if (arr[k][j] >= arr[i][j]) break;
                }
                s *= t;
                t = 0L;
                for (int k = i+1; k < arr.length; k++) {
                    t++;
                    if (arr[k][j] >= arr[i][j]) break;
                }
                s *= t;
                t = 0L;
                for (int k = j-1; k >=0; k--) {
                    t++;
                    if (arr[i][k] >= arr[i][j]) break;
                }
                s *= t;
                t = 0L;
                for (int k = j+1; k < arr[i].length; k++) {
                    t++;
                    if (arr[i][k] >= arr[i][j]) break;
                }
                s *= t;

                sarr[i][j] = s;
            }
        }

        var r = Long.MIN_VALUE;
        for (int i = 0; i < sarr.length; i++) {
            for (int j = 0; j < sarr[0].length; j++) {
                if (sarr[i][j] > r) r = sarr[i][j];
            }
        }
        return r;

    }

//     Output
//     21
//     1827
//     8
//     335580

}

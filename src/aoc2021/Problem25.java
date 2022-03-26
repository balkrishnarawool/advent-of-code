package aoc2021;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static aoc.Utils.fileToStringArray;

public class Problem25 {


    private static int solve(String[] input1) {
        String[][] arr = new String[input1.length][];
        for (int i = 0; i < input1.length; i++) {
            arr[i] = input1[i].split("");
        }
        int moves = 0;
        int steps = 0;
        String[][] arrCopy = copy(arr);

        do {
            moves = 0;
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[i].length; j++) {
                    int nextJ = (j == arr[i].length - 1) ? 0 : j + 1;
                    String next = arr[i][nextJ];
                    if (arr[i][j].equals(">") && next.equals(".")) {
                        arrCopy[i][nextJ] = arrCopy[i][j];
                        arrCopy[i][j] = ".";
                        moves++;
                        print(arrCopy);
                        System.out.println();
                    }
                }
            }
            arr = copy(arrCopy);
            for (int j = 0; j < arr[0].length; j++) {
                for (int i = 0; i < arr.length; i++) {
                    int nextI = (i == arr.length - 1) ? 0 : i + 1;
                    String next = arr[nextI][j];
                    if (arr[i][j].equals("v") && next.equals(".")) {
                        arrCopy[nextI][j] = arrCopy[i][j];
                        arrCopy[i][j] = ".";
                        moves++;
                        print(arrCopy);
                        System.out.println();
                    }
                }
            }
            print(arrCopy);
            System.out.println();
            arr = copy(arrCopy);
            steps++;
        } while (moves != 0);
        return steps;
    }

    private static void print(String[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
    }

    private static String[][] copy(String[][] arr) {
        String[][] arrCopy = new String[arr.length][arr[0].length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arrCopy[i][j] = arr[i][j];
            }
        }
        return arrCopy;
    }

    public static void main(String[] args) {
        String[] input1 = fileToStringArray(Problem25.class, "Problem25Input1.txt");
        String[] input2 = fileToStringArray(Problem25.class, "Problem25Input2.txt");

        System.out.println(solve(input1));
        System.out.println(solve(input2));

    }

}

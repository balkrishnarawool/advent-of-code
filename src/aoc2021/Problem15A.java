package aoc2021;

import aoc.Tuple2;
import aoc.Tuple3;

import java.util.*;

import static aoc.Utils.fileToStringArray;

public class Problem15A {

    private static int solve(String[] input1) {
        int[][] risks = new int[input1.length][input1[0].length()];
        for (int i = 0; i < input1.length; i++) {
            String[] arr = input1[i].split("");
            for (int j = 0; j < arr.length; j++) {
                risks[i][j] = Integer.parseInt(arr[j]);
            }
        }

        PriorityQueue<Tuple3<Integer>> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.first));
        queue.add(Tuple3.of(0, 0, 0)); // (risk, row, column)
        Set<Tuple2<Integer>> visited = new HashSet<>();

        return getLowestRisk(risks, queue, visited, input1.length - 1);
    }

    private static int getLowestRisk(int[][] risks, PriorityQueue<Tuple3<Integer>> queue, Set<Tuple2<Integer>> visited, int boundary) {
        while (!queue.isEmpty()) {
            Tuple3<Integer> t3 = queue.remove();

            int risk = t3.first;
            int i = t3.second;
            int j = t3.third;
//            System.out.println("["+i+","+j+"]");
            if (i == boundary && j == boundary) {
                return risk;
            }

            if (i > 0 && !visited.contains(Tuple2.of(i-1, j))) { queue.add(Tuple3.of(risk + risks[i-1][j], i-1, j));}
            if (j > 0 && !visited.contains(Tuple2.of(i, j-1))) { queue.add(Tuple3.of(risk + risks[i][j-1], i, j-1));}
            if (i < risks.length-1 && !visited.contains(Tuple2.of(i+1, j))) { queue.add(Tuple3.of(risk + risks[i+1][j], i+1, j));}
            if (j < risks.length-1 && !visited.contains(Tuple2.of(i, j+1))) { queue.add(Tuple3.of(risk + risks[i][j+1], i, j+1));}

            visited.add(Tuple2.of(i, j));
        }
        return Integer.MAX_VALUE;
    }

    public static void main(String[] args) {
        String[] input3 = fileToStringArray(Problem15A.class, "Problem15Input3.txt");
        String[] input1 = fileToStringArray(Problem15A.class, "Problem15Input1.txt");
        String[] input2 = fileToStringArray(Problem15A.class, "Problem15Input2.txt");

        System.out.println(solve(input3));
        System.out.println(solve(input1));
        System.out.println(solve(input2));
    }

}

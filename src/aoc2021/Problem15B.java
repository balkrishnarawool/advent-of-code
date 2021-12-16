package aoc2021;

import aoc.Tuple2;
import aoc.Tuple3;

import java.util.*;

import static aoc.Utils.fileToStringArray;

public class Problem15B {

    private static int solve(String[] input1, int size) {
        int[][] risks = new int[input1.length * 5][input1[0].length() * 5];
        for (int k = 0; k < 5; k++) {
            for (int l = 0; l < 5; l++) {
                for (int i = 0; i < input1.length; i++) {
                    String[] arr = input1[i].split("");
                    for (int j = 0; j < arr.length; j++) {
                        risks[size*k+i][size*l+j] = (Integer.parseInt(arr[j]) + k + l) == 9 ? 9 : ( Integer.parseInt(arr[j]) + k + l) % 9;
                    }
                }
            }
        }

        PrioQueue queue = new PrioQueue();
        queue.insert(Tuple3.of(0, 0, 0)); // (risk, row, column)
        Set<Tuple2<Integer>> visited = new HashSet<>();

        return getLowestRisk(risks, queue, visited, risks.length - 1);
    }

    private static int getLowestRisk(int[][] risks, PrioQueue queue, Set<Tuple2<Integer>> visited, int boundary) {
        while (!queue.isEmpty()) {
            Tuple3<Integer> t3 = queue.remove();

            int risk = t3.first;
            int i = t3.second;
            int j = t3.third;
//            System.out.println("["+i+","+j+"]");
            if (i == boundary && j == boundary) {
                return risk;
            }

            if (i > 0 && !visited.contains(Tuple2.of(i-1, j))) { queue.insert(Tuple3.of(risk + risks[i-1][j], i-1, j));}
            if (j > 0 && !visited.contains(Tuple2.of(i, j-1))) { queue.insert(Tuple3.of(risk + risks[i][j-1], i, j-1));}
            if (i < risks.length-1 && !visited.contains(Tuple2.of(i+1, j))) { queue.insert(Tuple3.of(risk + risks[i+1][j], i+1, j));}
            if (j < risks.length-1 && !visited.contains(Tuple2.of(i, j+1))) { queue.insert(Tuple3.of(risk + risks[i][j+1], i, j+1));}

            visited.add(Tuple2.of(i, j));
        }
        return Integer.MAX_VALUE;
    }

    public static void main(String[] args) {
        String[] input3 = fileToStringArray(Problem15B.class, "Problem15Input3.txt");
        String[] input1 = fileToStringArray(Problem15B.class, "Problem15Input1.txt");
        String[] input2 = fileToStringArray(Problem15B.class, "Problem15Input2.txt");

        System.out.println(solve(input3, 3));
        System.out.println(solve(input1, 10));
        System.out.println(solve(input2, 100));
    }

    private static class PrioQueue {
        Map<Tuple2<Integer>, Integer> map = new HashMap<>();
        PriorityQueue<Tuple3<Integer>> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.first));

        private void insert(Tuple3<Integer> t3) {
            Tuple2<Integer> t2 = Tuple2.of(t3.second, t3.third);
            if (!map.containsKey(t2)) {
                map.put(t2, t3.first);
                pq.add(t3);
            } else {
                if (map.get(t2) > t3.first) {
                    map.put(t2, t3.first);
                    pq.remove(Tuple3.of(t3.first, t2.left, t2.right));
                    pq.add(t3);
                }
            }
        }

        private Tuple3<Integer> remove() {
            Tuple3<Integer> t3 = pq.remove();
            map.remove(Tuple2.of(t3.second, t3.third));
            return t3;
        }

        private boolean isEmpty() {
            return map.isEmpty();
        }
    }

}

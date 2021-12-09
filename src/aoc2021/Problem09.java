package aoc2021;

import java.util.HashMap;
import java.util.Map;

import static aoc.Utils.fileToStringArray;

public class Problem09 {
    private static int index = 1;

    private static int calculateTotalRiskLevel(String[] strs) {
        int[][] map = new int[strs.length][strs[0].length()];
        for (int i = 0; i < strs.length; i++) {
            String[] t = strs[i].split("");
            for (int j = 0; j < t.length; j++) {
                map[i][j] = Integer.parseInt(t[j]);
            }
        }

        int risk = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (isLowPoint(map, i, j)) {
                    risk += (map[i][j] + 1);
                }
            }
        }

        return risk;
    }

    private static int multiplyBasins(String[] strs) {
        int[][] map = new int[strs.length][strs[0].length()];
        for (int i = 0; i < strs.length; i++) {
            String[] t = strs[i].split("");
            for (int j = 0; j < t.length; j++) {
                map[i][j] = (Integer.parseInt(t[j]) == 9) ? 1 : 0;
            }
        }

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 0) updateBasinIndex(map, i, j);
            }
        }

        Map<Integer, Integer> m = new HashMap<>();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] >= 2) {
                    if (m.containsKey(map[i][j])) {
                        m.put(map[i][j], m.get(map[i][j]) + 1);
                    } else {
                        m.put(map[i][j], 1);
                    }
                }
            }
        }

        int i1 = getMaxIndex(m); int a1 = m.get(i1); m.remove(i1);
        int i2 = getMaxIndex(m); int a2 = m.get(i2); m.remove(i2);
        int i3 = getMaxIndex(m); int a3 = m.get(i3); m.remove(i3);

        return a1 * a2 * a3;
    }

    private static int getMaxIndex(Map<Integer, Integer> m) {
        int max = Integer.MIN_VALUE;
        int maxI = -1;
        for (int i: m.keySet()) {
            if (m.get(i) > max) {
                max = m.get(i);
                maxI = i;
            }
        }
        return maxI;
    }

    private static void updateBasinIndex(int[][] map, int i, int j) {
          if (map[i][j] == 0) {
              int t = getBasinIndex(map, i, j);
              if (t == 0) {
                  index++;
                  updateBasinIndex(map, i, j, index);
              }
          }

    }

    private static int getBasinIndex(int[][] map, int i, int j) {
        if (map[i][j] == 0) {
//            Commented because you should only check down and right otherwise you'd end up in infinite recursion
//            if (i > 0 && map[i - 1][j] != 1) {
//                return getBasinIndex(map, i - 1, j);
//            }
//            if (j > 0 && map[i][j - 1] != 1) {
//                return getBasinIndex(map, i, j-1);
//            }
            if (i < map.length - 1 && map[i + 1][j] != 1) {
                return getBasinIndex(map, i + 1, j);
            }
            if (j < map[i].length - 1 && map[i][j + 1] != 1) {
                return getBasinIndex(map, i, j + 1);
            }
        }
        if (map[i][j] >= 2 ) return map[i][j];
        return 0;
    }

    private static void updateBasinIndex(int[][] map, int i, int j, int index) {
        map[i][j] = index;
        if (i > 0 && map[i - 1][j] == 0) {
            updateBasinIndex(map, i - 1, j, index);
        }
        if (j > 0 && map[i][j - 1] == 0) {
            updateBasinIndex(map, i, j-1, index);
        }
        if (i < map.length - 1 && map[i + 1][j] == 0) {
            updateBasinIndex(map, i + 1, j, index);
        }
        if (j < map[i].length - 1 && map[i][j + 1] == 0) {
            updateBasinIndex(map, i, j + 1, index);
        }
    }

    private static boolean isLowPoint(int[][] map, int i, int j) {
        if (i > 0 && map[i-1][j] <= map[i][j]) return false;
        if (i < map.length - 1 && map[i+1][j] <= map[i][j]) return false;
        if (j > 0 && map[i][j-1] <= map[i][j]) return false;
        if (j < map[i].length - 1 && map[i][j+1] <= map[i][j]) return false;
        return true;
    }

    public static void main(String[] args) {
        String[] input1 = fileToStringArray(Problem09.class, "Problem09Input1.txt");
        String[] input2 = fileToStringArray(Problem09.class, "Problem09Input2.txt");

        System.out.println(calculateTotalRiskLevel(input1));
        System.out.println(calculateTotalRiskLevel(input2));

        System.out.println(multiplyBasins(input1));
        System.out.println(multiplyBasins(input2));

//        Output

    }
}

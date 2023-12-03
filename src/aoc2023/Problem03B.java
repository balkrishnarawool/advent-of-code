package aoc2023;

import aoc.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Problem03B {
    record Point(int row, int column) { }
    static Map<Point, Set<Integer>> map = new HashMap<>();

    public static void main(String[] args) {
//        var str = Problem03Input.INPUT01;
        var str = Problem03Input.INPUT02;
//        var str = Problem03Input.INPUT03;

        var sa2d = Utils.stringArrayToChar2DArray(str.lines().toArray(String[]::new));
        int sum = 0;
        for (int i = 0; i < sa2d.length; i++) {
            sum += getPartNumbers(sa2d, i).stream().reduce(Integer::sum).orElse(0);
        }
        System.out.println(sum);
        System.out.println(sumOfGearRatio());
    }

    private static int sumOfGearRatio() {
        return map.values().stream()
                .filter(s -> s.size() == 2)
                .map(s -> s.stream().toList())
                .map(l -> l.get(0) * l.get(1))
                .reduce(Integer::sum)
                .orElse(0);
    }

    private static List<Integer> getPartNumbers(char[][] sa2d, int row) {
        var list = new ArrayList<Integer>();
        int start = -1, end = -1;
        for (int i = 0; i < sa2d[row].length; i++) {
            if (start == -1 && sa2d[row][i] >= '0' && sa2d[row][i] <= '9') { start = i; }
            if (start > -1) {
                if (!(sa2d[row][i] >= '0' && sa2d[row][i] <= '9')) { end = i-1; }
                else if (i == sa2d[row].length - 1) { end = i; }
            }

            if (start > -1 && end > -1) {
                if (!checkNonPartNumber(sa2d, row, start, end)) {
                    list.add(number(sa2d, row, start, end));
                }
                start = -1; end = -1;
            }
        }
        return list;
    }

    private static int number(char[][] sa2d, int row, int start, int end) {
        int  num = 0;
        for (int i = start; i <= end ; i++) {
            num = num * 10 + Integer.parseInt(""+sa2d[row][i]);
        }
        return num;
    }

    private static boolean checkNonPartNumber(char[][] sa2d, int row, int start, int end) {
        int num = number(sa2d, row, start, end);
        for (int i = start; i <= end ; i++) {
            if (i == start) {
                if (!(checkDot(num, sa2d, row-1, i-1) && checkDot(num, sa2d, row, i-1) && checkDot(num, sa2d, row+1, i-1) )) return false;
            }
            if (i == end ) {
                if (!(checkDot(num, sa2d, row-1, i+1) && checkDot(num, sa2d, row, i+1)  && checkDot(num, sa2d, row+1, i+1))) return false;
            }
            if (!(checkDot(num, sa2d, row-1, i) && checkDot(num, sa2d, row+1, i))) return false;
        }
        return true;
    }

    private static boolean checkDot(int num, char[][] sa2d, int i, int j) {
        if (i < 0 || i == sa2d.length || j < 0 || j == sa2d[0].length) return true;
        else {
            if (sa2d[i][j] == '*') {
                var k = new Point(i, j);
                if (map.containsKey(k)) { map.get(k).add(num); }
                else { var set = new HashSet<Integer>(); set.add(num); map.put(k, set); }
            }
            return sa2d[i][j] == '.';
        }
    }
}

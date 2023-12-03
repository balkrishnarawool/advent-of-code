package aoc2023;

import aoc.Utils;

import java.util.ArrayList;
import java.util.List;

public class Problem03A {
    public static void main(String[] args) {
//        var str = Problem03Input.INPUT01; //4361
        var str = Problem03Input.INPUT02; //549908

        var sa2d = Utils.stringArrayToChar2DArray(str.lines().toArray(String[]::new));
        int sum = 0;
        for (int i = 0; i < sa2d.length; i++) {
            sum += getPartNumbers(sa2d, i).stream().reduce(Integer::sum).orElse(0);
        }
        System.out.println(sum);
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
        for (int i = start; i <= end ; i++) {
            if (i == start) {
                if (!(checkDot(sa2d, row-1, i-1) && checkDot(sa2d, row, i-1) && checkDot(sa2d, row+1, i-1) )) return false;
            }
            if (i == end ) {
                if (!(checkDot(sa2d, row-1, i+1) && checkDot(sa2d, row, i+1)  && checkDot(sa2d, row+1, i+1))) return false;
            }
            if (!(checkDot(sa2d, row-1, i) && checkDot(sa2d, row+1, i))) return false;
        }
        return true;
    }

    private static boolean checkDot(char[][] sa2d, int i, int j) {
        return i < 0 || i == sa2d.length
                || j < 0 || j == sa2d[0].length
                || sa2d[i][j] == '.';
    }
}

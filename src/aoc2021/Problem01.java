package aoc2021;

import aoc.Utils;

public class Problem01 {

     static public int countIncreased(String[] strs) {
        int[] ints = Utils.stringToIntArray(strs);
        int n = 0;
        for (int i = 1; i < strs.length; i++) {
            if (ints[i-1] < ints[i]) n++;
        }
        return n;
    }

    static public int countIncreasedSlidingWindow3(String[] strs) {
        int[] ints = Utils.stringToIntArray(strs);
        int n = 0;
        for (int i = 3; i < strs.length; i++) {
            if (ints[i-3] + ints[i-2] + ints[i-1] < ints[i-2] + ints[i-1] + ints[i]) n++;
        }
        return n;
    }

    public static void main(String[] args) {
        String[] input1 = Utils.fileToStringArray(Problem01.class, "Problem01Input1.txt");
        String[] input2 = Utils.fileToStringArray(Problem01.class, "Problem01Input2.txt");

        System.out.println(countIncreased(input1));
        System.out.println(countIncreasedSlidingWindow3(input1));
        System.out.println(countIncreased(input2));
        System.out.println(countIncreasedSlidingWindow3(input2));
    }
}

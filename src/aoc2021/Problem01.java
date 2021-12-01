package aoc2021;

import static aoc.Utils.fileToStringArray;
import static aoc.Utils.stringToIntArray;

import java.util.stream.IntStream;

public class Problem01 {

    static private long countIncreased(int[] ints) {
        return IntStream.range(1, ints.length)
                .filter(i -> ints[i-1] < ints[i])
                .count();
    }

    static private long countIncreasedSlidingWindow3(int[] ints) {
        return IntStream.range(3, ints.length)
                .filter(i -> ints[i-3] + ints[i-2] + ints[i-1] < ints[i-2] + ints[i-1] + ints[i])
                .count();
    }

    public static void main(String[] args) {
        String[] input1 = fileToStringArray(Problem01.class, "Problem01Input1.txt");
        String[] input2 = fileToStringArray(Problem01.class, "Problem01Input2.txt");

        System.out.println(countIncreased(stringToIntArray(input1)));
        System.out.println(countIncreasedSlidingWindow3(stringToIntArray(input1)));
        System.out.println(countIncreased(stringToIntArray(input2)));
        System.out.println(countIncreasedSlidingWindow3(stringToIntArray(input2)));
    }
}

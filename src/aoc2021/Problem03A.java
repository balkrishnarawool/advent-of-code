package aoc2021;

import java.util.stream.IntStream;

import static aoc.Utils.fileToStringArray;
import static aoc.Utils.stringArrayToChar2DArray;

public class Problem03A {

    static private long calculatePowerConsumption(char[][] report) {
        if (report.length == 0) { throw new RuntimeException("Report has no readings."); }
        int bitCount = report[0].length;
        long gammaRate = calculateGammaRate(report, bitCount);
        long epsilonRate = calculateEpsilonRate(gammaRate, bitCount);

        return gammaRate * epsilonRate;
    }

    private static long calculateGammaRate(char[][] report, int bitCount) {
        return IntStream.range(0, bitCount)
                .map(i -> getSignificantBit(report, i))
                .reduce(0, (n, i) -> 2 * n + i);
    }

    private static int getSignificantBit(char[][] report, int i) {
        return sumBits(report, i) * 2 >= report.length ? 1 : 0;
    }

    private static long sumBits(char[][] report, int i) {
        return IntStream.range(0, report.length)
                .filter(j -> report[j][i] == '1')
                .count();
    }

    private static long calculateEpsilonRate(long gammaRate, int bitCount) {
        return (long)(Math.pow(2, bitCount)) - 1 - gammaRate;
    }

    public static void main(String[] args) {
        char[][] input1 = stringArrayToChar2DArray(fileToStringArray(Problem03A.class, "Problem03Input1.txt"));
        char[][] input2 = stringArrayToChar2DArray(fileToStringArray(Problem03A.class, "Problem03Input2.txt"));

        System.out.println(calculatePowerConsumption(input1));
        System.out.println(calculatePowerConsumption(input2));

//        Output
//        198
//        2498354

    }

}

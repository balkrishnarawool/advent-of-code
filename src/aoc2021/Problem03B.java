package aoc2021;

import java.util.Arrays;
import java.util.stream.IntStream;

import static aoc.Utils.fileToStringArray;
import static aoc.Utils.stringArrayToChar2DArray;

public class Problem03B {

    static private long calculateLifeSupport(char[][] report) {
        if (report.length == 0) { throw new RuntimeException("Report has no readings."); }
        int bitCount = report[0].length;
        char[] o2 = calculateO2(report, bitCount);
        char[] co2 = calculateCO2(report, bitCount);

        return toDecimal(o2) * toDecimal(co2);
    }

    private static long toDecimal(char[] o2) {
        return IntStream.range(0, o2.length)
                .map(i -> o2[i] == '1' ? 1 : 0)
                .reduce(0, (n, i) -> 2 * n + i);
    }

    private static char[] calculateO2(char[][] report, int bitCount) {
        return IntStream.range(0, bitCount)
                .boxed()
                .reduce(report, Problem03B::filterReportWithSignificantBits, Problem03B::combiner)[0];
    }

    private static char[][] combiner(char[][] c1, char[][] c2) {
        throw new RuntimeException("These calculations are not to be done in parallel. If you're using stream.parallel(), remove it!");
    }

    private static char[][] filterReportWithSignificantBits(char[][] report, int i) {
        return report.length == 1
                ? report
                : Arrays.stream(report)
                    .filter(chars -> hasBitSameAsSignificantBit(report, i, chars))
                    .toArray(char[][]::new);
    }

    private static boolean hasBitSameAsSignificantBit(char[][] report, int i, char[] chars) {
        return (sumBits(report, i) * 2 >= report.length) ? chars[i] == '1' : chars[i] == '0';
    }

    private static long sumBits(char[][] report, int i) {
        return IntStream.range(0, report.length)
                .filter(j -> report[j][i] == '1')
                .count();
    }

    private static char[] calculateCO2(char[][] report, int bitCount) {
        return IntStream.range(0, bitCount)
                .boxed()
                .reduce(report, Problem03B::filterReportWithInsignificantBits, Problem03B::combiner)[0];
    }

    private static char[][] filterReportWithInsignificantBits(char[][] report, int i) {
        return report.length == 1
                ? report
                : Arrays.stream(report)
                    .filter(chars -> !hasBitSameAsSignificantBit(report, i, chars))
                    .toArray(char[][]::new);
    }

    public static void main(String[] args) {
        char[][] input1 = stringArrayToChar2DArray(fileToStringArray(Problem03B.class, "Problem03Input1.txt"));
        char[][] input2 = stringArrayToChar2DArray(fileToStringArray(Problem03B.class, "Problem03Input2.txt"));

        System.out.println(calculateLifeSupport(input1));
        System.out.println(calculateLifeSupport(input2));

//        Output
//        198
//        2498354

    }

}

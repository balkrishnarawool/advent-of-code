package aoc2021;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.IntStream;

import static aoc.Utils.fileToStringArray;
import static aoc.Utils.copyOf;
import static aoc.Utils.stringArrayToChar2DArray;

public class Problem03B {

    static private long calculateLifeSupport(char[][] report) {
        int count = report[0].length;
        char[] o2 = calculateO2(copyOf(report), count);
        char[] co2 = calculateCO2(copyOf(report), count);

        return toDecimal(o2) * toDecimal(co2);
    }

    private static long toDecimal(char[] o2) {
        return IntStream.range(0, o2.length)
                .map(i -> o2[i] == '1' ? 1 : 0)
                .reduce(0, (n, i) -> 2 * n + i);
    }

    private static char[] calculateO2(char[][] report, int count) {
        int i = 0;
        while (i < count && report.length > 1) {
            report = filteredReport(report, i);
            i++;
        }
        return report[0];
    }

    private static char[][] filteredReport(char[][] report, int i) {
        return Arrays.stream(report)
                .map(chars -> sumBits(report, i) * 2 >= report.length ? (chars[i] == '1' ? chars : null) : (chars[i] == '0' ? chars : null))
                .filter(Objects::nonNull)
                .toArray(char[][]::new);
    }

    private static long sumBits(char[][] report, int i) {
        return IntStream.range(0, report.length)
                .filter(j -> report[j][i] == '1')
                .count();
    }

    private static char[] calculateCO2(char[][] report, int count) {
        int i = 0;
        while (i < count && report.length > 1) {
            report = filteredReport2(report, i);
            i++;
        }
        return report[0];
    }

    private static char[][] filteredReport2(char[][] report, int i) {
        return Arrays.stream(report)
                .map(chars -> sumBits(report, i) * 2 < report.length ? (chars[i] == '1' ? chars : null) : (chars[i] == '0' ? chars : null))
                .filter(Objects::nonNull)
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

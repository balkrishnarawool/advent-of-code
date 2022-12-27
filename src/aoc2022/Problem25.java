package aoc2022;

import java.io.IOException;

import static aoc.Utils.readFile;

public class Problem25 {

    public static void main(String[] args) throws IOException {
        solvePart1("./src/aoc2022/Problem25Input1.txt");
        solvePart1("./src/aoc2022/Problem25Input2.txt");
    }

    private static void solvePart1(String path) throws IOException {
        System.out.println(solve(path));
    }

    private static String solve(String path) throws IOException {
        var lines = readFile(path);
        var sum = lines.stream().map(Problem25::snafu2d).reduce(0L, Long::sum);
        return d2snafu(sum);
    }

    private static long snafu2d(String num) {
        long d = 0L;
        for (int i = 0; i < num.length() ; i++) {
            char c = num.charAt(i);
            long v = snafu2d(c);
            d = d * 5 + v;
        }
        return d;
    }

    private static long snafu2d(char c) {
        return switch (c) {
            case '=' -> -2;
            case '-' -> -1;
            case '0' -> 0;
            case '1' -> 1;
            case '2' -> 2;
            default -> throw new IllegalStateException("Unexpected value: " + c);
        };
    }

    private static String d2snafu(long i) {
        return (i/5 > 0)
                ? sum(
                        d2snafu(i/5),
                        d2snafu(i%5))
                : snafu(i%5);
    }

    private static String sum(String tens, String units) {
        if (units.length()==1) return tens+units;
        else // units is 1X, so tens needs to be incremented
            return increment(tens)+units.substring(units.length()-1);
    }

    private static String increment(String num) {
        if (num.length() == 0) return "1";
        var units = num.charAt(num.length()-1);
        var tens = num.substring(0, num.length()-1);
        if (units=='=') return tens+ '-';
        if (units=='-') return tens + '0';
        if (units=='0') return tens + '1';
        if (units=='1') return tens + '2';
        else return increment(tens)+'='; // ut is 2 and so tt + 1
    }

    private static String snafu(long i) {
        return i<3 ? ""+i : i==3 ? "1=" : "1-";
    }

//     Output
//     2=-1=0
//     20-1-0=-2=-2220=0011

}

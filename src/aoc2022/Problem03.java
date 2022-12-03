package aoc2022;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Problem03 {

    public static void main(String[] args) throws IOException {
        solvePart1("./src/aoc2022/Problem03Input1.txt");
        solvePart1("./src/aoc2022/Problem03Input2.txt");
        solvePart2("./src/aoc2022/Problem03Input1.txt");
        solvePart2("./src/aoc2022/Problem03Input2.txt");
    }

    private static void solvePart1(String path) throws IOException {
        System.out.println(solve(path));
    }

    private static void solvePart2(String path) throws IOException {
        System.out.println(solve2(path));
    }

    private static int solve(String path) throws IOException {
        try (var lines = Files.lines(Path.of(path))) {
            return lines.map(s ->
                            getScore(getCommon(
                                    s.substring(0, s.length() / 2).split(""),
                                    s.substring(s.length() / 2).split(""))))
                    .reduce(Integer::sum)
                    .orElse(0);
        }
    }

    private static int getScore(String r) {
        var rr = (r.charAt(0) - 'A') + 1;
        return rr > 26 ? rr - 32 : rr + 26;
    }

    private static int solve2(String path) throws IOException {
        var r = 0;
        try (var lines = Files.lines(Path.of(path))) {
            var l = lines.toList();
            for (int i = 0; i < l.size() / 3; i++) {
                r += getScore(getCommon(
                        l.get(i * 3).split(""),
                        l.get(i * 3 + 1).split(""),
                        l.get(i * 3 + 2).split("")));
            }
        }
        return r;
    }

    private static String getCommon(String[] a1, String[] a2, String[] a3) {
        for (var s1: a1) {
            for (var s2: a2) {
                for (var s3: a3) {
                    if (s1.equals(s2) && s2.equals(s3)) return s1;
                }
            }
        }
        throw new RuntimeException("Nothing common in "+Arrays.toString(a1)+" , "+ Arrays.toString(a2)+" and "+Arrays.toString(a3));
    }

    private static String getCommon(String[] a1, String[] a2) {
        for (var s1: a1) {
            for (var s2: a2) {
                if (s1.equals(s2)) return s1;
            }
        }
        throw new RuntimeException("Nothing common in "+Arrays.toString(a1)+" and "+ Arrays.toString(a2));
    }

//     Output
//     157
//     8233
//     70
//     2821


}

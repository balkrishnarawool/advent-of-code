package aoc2022;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
        try(var lines = Files.lines(Path.of(path))) {
            return lines.map(s -> {
                var a1 = s.substring(0, s.length()/2).split("");
                var a2 = s.substring(s.length()/2).split("");

                var r = getCommon(a1, a2);
                var rr = (r.charAt(0) - 'A') + 1;
                var rrr = rr > 26 ? rr - 32 : rr + 26;

                return rrr;
            })
            .reduce(Integer::sum)
            .orElse(0);
        }
    }

    private static int solve2(String path) throws IOException {
        var rrrr = 0;
        try(var lines = Files.lines(Path.of(path))) {
            var l = lines.toList();
            for (int i = 0; i < l.size()/3; i++) {
                var r = getCommon2(l.get(i*3).split(""), l.get(i*3 + 1).split(""), l.get(i*3 + 2).split(""));
                var rr = (r.charAt(0) - 'A') + 1;
                var rrr = rr > 26 ? rr - 32 : rr + 26;
                rrrr += rrr;
            }
        }
        return rrrr;
    }

    private static String getCommon2(String[] a1, String[] a2, String[] a3) {
        var r = new MutableString("");
        Arrays.stream(a1)
                .forEach(s1 -> {
                    Arrays.stream(a2)
                            .forEach(s2 -> {
                                Arrays.stream(a3)
                                        .forEach(s3 -> {
                                            if (s1.equals(s2) && s2.equals(s3)) {
                                                r.setV(s1);
                                            }
                                        });
                            });
                });
        return r.getV();
    }

    private static String getCommon(String[] a1, String[] a2) {
        var r = new MutableString("");
        Arrays.stream(a1)
                .forEach(s1 -> {
                    Arrays.stream(a2)
                            .forEach(s2 -> {
                                if (s1.equals(s2)) {
                                    r.setV(s1);
                                }
                            });
                });
        return r.getV();
    }

    @Getter
    @Setter
    @AllArgsConstructor(staticName = "of")
    static class MutableString {
        String v;
    }

//     Output


}

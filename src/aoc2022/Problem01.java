package aoc2022;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

import static aoc.Utils.fileToStringArray;
import static aoc.Utils.stringToIntArray;

public class Problem01 {

    public static void main(String[] args) throws IOException {
        solvePart1();
        solvePart2();
    }

    private static void solvePart2() throws IOException {
        var list = new ArrayList<Integer>();
        var sum = new MutableInt(0);
        try(var lines = Files.lines(Path.of("/Users/TS90XD/dev/java/aoc/advent-of-code/src/aoc2022/Problem01Input2.txt"))) {
            lines.forEach(s -> {
                if (s.length() == 0) {
                    list.add(sum.getV());
                    sum.setV(0);
                } else {
                    sum.setV(sum.getV() + Integer.parseInt(s));
                }
            });
            // Add last element
            list.add(sum.getV());
            System.out.println(list.stream().sorted(Comparator.reverseOrder()).limit(3).reduce(Integer::sum));
        }
    }

    private static void solvePart1() throws IOException {
        var list = new ArrayList<Integer>();
        var sum = new MutableInt(0);
        try(var lines = Files.lines(Path.of("/Users/TS90XD/dev/java/aoc/advent-of-code/src/aoc2022/Problem01Input1.txt"))) {
            lines.forEach(s -> {
                if (s.length() == 0) {
                    list.add(sum.getV());
                    sum.setV(0);
                } else {
                    sum.setV(sum.getV() + Integer.parseInt(s));
                }
            });
            // Add last element
            list.add(sum.getV());
            System.out.println(list.stream().max(Comparator.comparingInt(e -> e)));
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor(staticName = "of")
    static class MutableInt {
        int v;
    }
}

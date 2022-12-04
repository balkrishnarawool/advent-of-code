package aoc2022;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class Problem04 {

    public static void main(String[] args) throws IOException {
        solvePart1("./src/aoc2022/Problem04Input1.txt");
//        solvePart1("./src/aoc2022/Problem04Input2.txt");
//        solvePart2("./src/aoc2022/Problem04Input1.txt");
//        solvePart2("./src/aoc2022/Problem04Input2.txt");
    }

    private static void solvePart1(String path) throws IOException {
        System.out.println(solve(path));
    }

    private static void solvePart2(String path) throws IOException {
        System.out.println(solve(path));
    }

    private static int solve(String path) throws IOException {
        try (var lines = Files.lines(Path.of(path))) {
            lines.forEach(s -> {

            });
        }
        return 0;
    }


//     Output



}

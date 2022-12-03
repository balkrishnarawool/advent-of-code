package aoc2022;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Problem02 {

    static Map<String, Integer> map1 = new HashMap<>(){{
        put("A X", 1+3);
        put("A Y", 2+6);
        put("A Z", 3+0);

        put("B X", 1+0);
        put("B Y", 2+3);
        put("B Z", 3+6);

        put("C X", 1+6);
        put("C Y", 2+0);
        put("C Z", 3+3);
    }};

    static Map<String, Integer> map2 = new HashMap<>(){{
        put("A X", 3+0);
        put("A Y", 1+3);
        put("A Z", 2+6);

        put("B X", 1+0);
        put("B Y", 2+3);
        put("B Z", 3+6);

        put("C X", 2+0);
        put("C Y", 3+3);
        put("C Z", 1+6);
    }};

    public static void main(String[] args) throws IOException {
        solvePart1("./src/aoc2022/Problem02Input1.txt");
        solvePart1("./src/aoc2022/Problem02Input2.txt");
        solvePart2("./src/aoc2022/Problem02Input1.txt");
        solvePart2("./src/aoc2022/Problem02Input2.txt");
    }

    private static void solvePart1(String path) throws IOException {
        System.out.println(solve(path, map1));
    }

    private static void solvePart2(String path) throws IOException {
        System.out.println(solve(path, map2));
    }

    private static int solve(String path, Map<String, Integer> map) throws IOException {
        try(var lines = Files.lines(Path.of(path))) {
            return lines
                    .map(map::get)
                    .reduce(Integer::sum)
                    .orElse(0);
        }
    }

//     Output
//     15
//     14827
//     12
//     13889

}

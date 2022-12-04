package aoc2022;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class Problem04 {

    public static void main(String[] args) throws IOException {
        solvePart1("./src/aoc2022/Problem04Input1.txt");
        solvePart1("./src/aoc2022/Problem04Input2.txt");
        solvePart2("./src/aoc2022/Problem04Input1.txt");
        solvePart2("./src/aoc2022/Problem04Input2.txt");
    }

    private static void solvePart1(String path) throws IOException {
        System.out.println(solve(path));
    }

    private static void solvePart2(String path) throws IOException {
        System.out.println(solve2(path));
    }

    private static long solve(String path) throws IOException {
        try (var lines = Files.lines(Path.of(path))) {
            return lines.map(s -> {
                var sa = s.split(",");
                var sa1 = sa[0].split("-");
                var sa2 = sa[1].split("-");

                var x1 = Integer.parseInt(sa1[0]);
                var y1 = Integer.parseInt(sa1[1]);
                var x2 = Integer.parseInt(sa2[0]);
                var y2 = Integer.parseInt(sa2[1]);

                if((x1<=x2 && y1>=y2) || (x1>=x2 && y1<=y2)) return true;
                else return false;
            })
            .filter(b -> b)
            .count();
        }
    }

    private static long solve2(String path) throws IOException {
        try (var lines = Files.lines(Path.of(path))) {
            return lines.map(s -> {
                        var sa = s.split(",");
                        var sa1 = sa[0].split("-");
                        var sa2 = sa[1].split("-");

                        var x1 = Integer.parseInt(sa1[0]);
                        var y1 = Integer.parseInt(sa1[1]);
                        var x2 = Integer.parseInt(sa2[0]);
                        var y2 = Integer.parseInt(sa2[1]);

                        if((x1<x2 && y1>=x2) ||
                           (x1==x2) ||
                           (x1>x2 && x1<=y2)) return true;
                        else return false;
                    })
                    .filter(b -> b)
                    .count();
        }
    }


//     Output



}

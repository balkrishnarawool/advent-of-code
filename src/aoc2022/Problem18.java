package aoc2022;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static aoc.Utils.readFile;

public class Problem18 {

    public static void main(String[] args) throws IOException {
        solvePart1("./src/aoc2022/Problem18Input1.txt");
        solvePart1("./src/aoc2022/Problem18Input2.txt");
    }

    private static void solvePart1(String path) throws IOException {
        System.out.println(solve(path));
    }

    private static int solve(String path) throws IOException {
        var lines = readFile(path);
        var input = parseInput(lines);

        int sa = 0; //surface area
        for (var c: input.cubes) {
            if (!input.cubes.contains(new Cube(c.i-1, c.j, c.k))) sa++;
            if (!input.cubes.contains(new Cube(c.i+1, c.j, c.k))) sa++;
            if (!input.cubes.contains(new Cube(c.i, c.j-1, c.k))) sa++;
            if (!input.cubes.contains(new Cube(c.i, c.j+1, c.k))) sa++;
            if (!input.cubes.contains(new Cube(c.i, c.j, c.k-1))) sa++;
            if (!input.cubes.contains(new Cube(c.i, c.j, c.k+1))) sa++;
        }
        return sa;
    }

    private static Input parseInput(List<String> lines) {
        var input = new Input();
        for (var s: lines) {
            var sa = s.split(",");
            var cube = new Cube(Integer.parseInt(sa[0]), Integer.parseInt(sa[1]), Integer.parseInt(sa[2]));
            input.cubes.add(cube);
        }
        return input;
    }

    static class Input {
        List<Cube> cubes = new ArrayList<>();
    }

    record Cube(int i, int j, int k) { }


//     Output
//     64
//     4314



}

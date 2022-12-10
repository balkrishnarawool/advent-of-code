package aoc2022;

import lombok.NoArgsConstructor;

import java.io.IOException;

import static aoc.Utils.readFile;

public class Problem10A {

    public static void main(String[] args) throws IOException {
        solvePart1("./src/aoc2022/Problem10Input1.txt");
        solvePart1("./src/aoc2022/Problem10Input2.txt");
        solvePart1("./src/aoc2022/Problem10Input3.txt");
    }

    private static void solvePart1(String path) throws IOException {
        System.out.println(solve(path));
    }

    private static long solve(String path) throws IOException {
        var list = readFile(path);
        var cpu = new CPU();

        for (var s: list) {
            var sa = s.split(" ");
            switch (sa[0]) {
                case "noop":
                    cpu.noop();
                    break;
                case "addx":
                    cpu.addx(Long.parseLong(sa[1]));
                    break;
            }
        }
        return cpu.result;
    }

    @NoArgsConstructor
    static class CPU {
        long x = 1;
        long cycle = 0;
        long result = 0;

        public void addx(long l) {
            doCycle();
            doCycle();
            x += l;
        }

        public void noop() {
            doCycle();
        }

        private void doCycle() {
            cycle++;
            if ((cycle-20) % 40 == 0) {
                result += (cycle * x);
            }
        }

    }

//     Output
//     0
//     17180
//     13140

}

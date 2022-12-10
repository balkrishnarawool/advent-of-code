package aoc2022;

import lombok.NoArgsConstructor;

import java.io.IOException;

import static aoc.Utils.readFile;

public class Problem10B {

    public static void main(String[] args) throws IOException {
        solvePart2("./src/aoc2022/Problem10Input3.txt");
        System.out.println();
        System.out.println();
        solvePart2("./src/aoc2022/Problem10Input2.txt");
    }

    private static void solvePart2(String path) throws IOException {
        var str = solve2(path).toCharArray();
        for (int i = 0; i < str.length; i++) {
            if (i%40 == 0) System.out.println();
            System.out.print(str[i]);
        }
    }

    private static String solve2(String path) throws IOException {
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
        return cpu.str;
    }

    @NoArgsConstructor
    static class CPU {
        long x = 1;
        long cycle = 0;
        String str = "";

        public void addx(long l) {
            doCycle();
            doCycle();
            x += l;
        }

        public void noop() {
            doCycle();
        }

        private void doCycle() {
            // position = cycle % 40
            if ((cycle %40) >= (x-1) && (cycle %40) <= (x+1)) str+="#"; else str+=".";
            cycle++;
        }

    }

//     Output
//
//        ##..##..##..##..##..##..##..##..##..##..
//        ###...###...###...###...###...###...###.
//        ####....####....####....####....####....
//        #####.....#####.....#####.....#####.....
//        ######......######......######......####
//        #######.......#######.......#######.....
//
//
//        ###..####.#..#.###..###..#....#..#.###..
//        #..#.#....#..#.#..#.#..#.#....#..#.#..#.
//        #..#.###..####.#..#.#..#.#....#..#.###..
//        ###..#....#..#.###..###..#....#..#.#..#.
//        #.#..#....#..#.#....#.#..#....#..#.#..#.
//        #..#.####.#..#.#....#..#.####..##..###..

}

package aoc2022;

import lombok.AllArgsConstructor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static aoc.Utils.readFile;

public class Problem23 {

    public static void main(String[] args) throws IOException {
        solvePart1("./src/aoc2022/Problem23Input1.txt");
//        solvePart1("./src/aoc2022/Problem23Input3.txt");
        solvePart1("./src/aoc2022/Problem23Input2.txt");
        solvePart2("./src/aoc2022/Problem23Input1.txt");
        solvePart2("./src/aoc2022/Problem23Input2.txt");
    }

    private static void solvePart1(String path) throws IOException {
        System.out.println(solve(path));
    }

    private static void solvePart2(String path) throws IOException {
        System.out.println(solve2(path));
    }

    private static long solve(String path) throws IOException {
        var lines = readFile(path);
        var input = parseInput(lines);
//        input.show();
        for (int i = 0; i < 10; i++) {
            var moves = input.calculateProposedPositionsCount(); // pp = proposed positions
            input.moveElves(moves);
//            input.show();
        }
        return input.countEmpty();
    }

    private static long solve2(String path) throws IOException {
        var lines = readFile(path);
        var input = parseInput(lines);
        var moveCount = -1;
        var rounds = 0;
//        input.show();
        do {
            var moves = input.calculateProposedPositionsCount(); // pp = proposed positions
            moveCount = input.moveElves(moves);
            rounds++;
//            input.show();
        } while (moveCount > 0);
        return rounds;
    }

    private static Input parseInput(List<String> lines) {
        Elf[][] elves = new Elf[1000][1000];

        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                if (lines.get(i).charAt(j) == '#')
                    elves[400+i][400+j] = new Elf();
            }
        }
        return new Input(elves);
    }

    @AllArgsConstructor
    static class Input {
        Elf[][] elves;

        public void show() {
            var tb = calcTB(); // top bound
            var bb = calcBB(); // bottom bound
            var rb = calcRB(); // right bound
            var lb = calcLB(); // left bound

            for (int i = tb; i <= bb; i++) {
                for (int j = lb; j <= rb; j++) {
                    if (elves[i][j] == null) System.out.print('.');
                    else System.out.print('#');
                }
                System.out.println();
            }
            System.out.println();
        }

        public Moves calculateProposedPositionsCount() {
            var count = new int[elves.length][elves.length];
            var dir = new String[elves.length][elves.length];
            for (int[] ints : count) {
                Arrays.fill(ints, 0);
            }

            for (int i = 1; i < count.length-1; i++) {
                for (int j = 1; j < count[i].length-1; j++) {
                    if (elves[i][j] != null) {
                        var c = countEmpty(elves, i, j);
                        if (c < 8) {
                            var seq = elves[i][j].seq;
                            for (var d: seq.split("")) { // direction
                                if (d.equals("N") && canMoveToNorth(elves, i, j)) { count[i-1][j]++; dir[i][j] = "N"; break; }
                                if (d.equals("S") && canMoveToSouth(elves, i, j)) { count[i+1][j]++; dir[i][j] = "S"; break; }
                                if (d.equals("W") && canMoveToWest(elves, i, j))  { count[i][j-1]++; dir[i][j] = "W"; break; }
                                if (d.equals("E") && canMoveToEast(elves, i, j))  { count[i][j+1]++; dir[i][j] = "E"; break; }
                            }
                        }
                        elves[i][j].moveSeq();
                    }
                }
            }
            return new Moves(count, dir);
        }

        private boolean canMoveToNorth(Elf[][] elves, int i, int j) {
            return elves[i-1][j-1] == null && elves[i-1][j] == null && elves[i-1][j+1] == null;
        }

        private boolean canMoveToSouth(Elf[][] elves, int i, int j) {
            return elves[i+1][j-1] == null && elves[i+1][j] == null && elves[i+1][j+1] == null;
        }

        private boolean canMoveToWest(Elf[][] elves, int i, int j) {
            return elves[i-1][j-1] == null && elves[i][j-1] == null && elves[i+1][j-1] == null;
        }

        private boolean canMoveToEast(Elf[][] elves, int i, int j) {
            return elves[i-1][j+1] == null && elves[i][j+1] == null && elves[i+1][j+1] == null;
        }

        private int countEmpty(Elf[][] elves, int i, int j) {
            var c = 0;
            if (elves[i-1][j-1] == null) c++;
            if (elves[i-1][j] == null) c++;
            if (elves[i-1][j+1] == null) c++;
            if (elves[i][j-1] == null) c++;
            if (elves[i][j+1] == null) c++;
            if (elves[i+1][j-1] == null) c++;
            if (elves[i+1][j] == null) c++;
            if (elves[i+1][j+1] == null) c++;
            return c;
        }

        public long countEmpty() {
            var tb = calcTB(); // top bound
            var bb = calcBB(); // bottom bound
            var rb = calcRB(); // right bound
            var lb = calcLB(); // left bound
            var c = 0;
            for (int i = tb; i <= bb; i++) {
                for (int j = lb; j <= rb; j++) {
                    if (elves[i][j] == null) c++;
                }
            }
            return c;
        }

        private int calcTB() {
            for (int i = 0; i < elves.length; i++) {
                for (int j = 0; j < elves[i].length; j++) {
                    if (elves[i][j] != null) return i;
                }
            }
            throw new RuntimeException("No elves found");
        }

        private int calcBB() {
            for (int i = elves.length-1; i >= 0; i--) {
                for (int j = 0; j < elves[i].length; j++) {
                    if (elves[i][j] != null) return i;
                }
            }
            throw new RuntimeException("No elves found");
        }

        private int calcRB() {
            for (int i = elves.length-1; i > 0; i--) {
                for (int j = 0; j < elves[i].length; j++) {
                    if (elves[j][i] != null) return i;
                }
            }
            throw new RuntimeException("No elves found");
        }

        private int calcLB() {
            for (int i = 0; i < elves.length; i++) {
                for (int j = 0; j < elves[i].length; j++) {
                    if (elves[j][i] != null) return i;
                }
            }
            throw new RuntimeException("No elves found");
        }

        public int moveElves(Moves moves) {
            int c = 0;
            var copy = copy(elves);
            for (int i = 1; i < moves.count.length-1; i++) {
                for (int j = 1; j < moves.count[i].length-1; j++) {
                    if (elves[i][j] != null) {
                        if (moves.dir[i][j] != null) {
                            switch (moves.dir[i][j]) {
                                case "N":
                                    if (moves.count[i-1][j] == 1) { copy[i-1][j] = copy[i][j]; copy[i][j] = null; c++; }
                                    break;
                                case "S":
                                    if (moves.count[i+1][j] == 1) { copy[i+1][j] = copy[i][j]; copy[i][j] = null; c++; }
                                    break;
                                case "W":
                                    if (moves.count[i][j-1] == 1) { copy[i][j-1] = copy[i][j]; copy[i][j] = null; c++; }
                                    break;
                                case "E":
                                    if (moves.count[i][j+1] == 1) { copy[i][j+1] = copy[i][j]; copy[i][j] = null; c++; }
                                    break;
                            }
                        }
                    }
                }
            }
            elves = copy;
            return c;
        }

        public Elf[][] copy(Elf[][] elves) {
            var copy = new Elf[elves.length][elves.length];
            for (int i = 0; i < elves.length; i++) {
                System.arraycopy(elves[i], 0, copy[i], 0, elves.length);
            }
            return copy;
        }
    }

    @AllArgsConstructor
    static class Moves {
        int[][] count;
        String[][] dir;
    }

    static class Elf {
        String seq = "NSWE";

        public void moveSeq() {
            var f = seq.substring(0, 1);
            seq = seq.substring(1) + f;
        }
    }

//     Output
//     110
//     3864
//     20
//     946

}

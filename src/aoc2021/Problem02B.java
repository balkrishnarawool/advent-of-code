package aoc2021;

import java.util.Arrays;

import static aoc.Utils.fileToStringArray;
import static aoc2021.Problem02B.Command.*;

public class Problem02B {

    static private int calculatePositionProduct(Command[] comms) {
        Position position = Arrays.stream(comms)
                .reduce(Position.of(0, 0, 0), Problem02B::calculateNextPosition, Problem02B::combiner);

        return position.length * position.depth;
    }

    private static Position calculateNextPosition(Position p, Command comm) {
        switch (comm.dir) {
            case "forward" : return Position.of(p.length + comm.units, p.depth + p.aim * comm.units, p.aim);
            case "up" : return Position.of(p.length, p.depth, p.aim - comm.units);
            case "down" : return Position.of(p.length, p.depth, p.aim + comm.units);
            default : throw new RuntimeException("Wrong direction: " + comm.dir);
        }
    }

    private static Position combiner(Position p1, Position p2) {
        throw new RuntimeException("The commands are not meant to be executed in parallel. If you're using stream.parallel(), remove it!");
    }

    private static class Position {
        public final int length;
        public final int depth;
        public final int aim;

        private Position(int length, int depth, int aim) {
            this.length = length;
            this.depth = depth;
            this.aim = aim;
        }

        private static Position of(int length, int depth, int aim) {
            return new Position(length, depth, aim);
        }
    }


    public static void main(String[] args) {
        Command[] input1 = stringArrayToCommandArray(fileToStringArray(Problem02B.class, "Problem02Input1.txt"));
        Command[] input2 = stringArrayToCommandArray(fileToStringArray(Problem02B.class, "Problem02Input2.txt"));

        System.out.println(calculatePositionProduct(input1));
        System.out.println(calculatePositionProduct(input2));

//        Output
//        900
//        1855892637
    }

    static class Command {
        private String dir;
        private int units;

        private Command(String dir, int units) {
            this.dir = dir;
            this.units = units;
        }

        private static Command fromString(String str) {
            String[] strs = str.split(" ");
            return new Command(strs[0], Integer.parseInt(strs[1]));
        }

        static Command[] stringArrayToCommandArray(String[] input1) {
            return Arrays.stream(input1)
                    .map(Command::fromString)
                    .toArray(Command[]::new);
        }
    }
}

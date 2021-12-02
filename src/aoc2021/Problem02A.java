package aoc2021;

import java.util.Arrays;

import static aoc.Utils.fileToStringArray;
import static aoc2021.Problem02A.Command.*;

public class Problem02A {

    static private int calculatePositionProduct(Command[] comms) {
        Position position = Arrays.stream(comms)
                .reduce(Position.of(0, 0), Problem02A::calculateNextPosition, Problem02A::combiner);

        return position.length * position.depth;
    }

    private static Position calculateNextPosition(Position p, Command comm) {
        switch (comm.dir) {
            case "forward" : return Position.of(p.length + comm.units, p.depth);
            case "up" : return Position.of(p.length, p.depth - comm.units);
            case "down" : return Position.of(p.length, p.depth + comm.units);
            default : throw new RuntimeException("Wrong direction: " + comm.dir);
        }
    }

    private static Position combiner(Position p1, Position p2) {
        return Position.of(p1.length + p2.length, p1.depth + p2.depth);
    }

    private static class Position {
        public final int length;
        public final int depth;

        private Position(int length, int depth) {
            this.length = length;
            this.depth = depth;
        }

        private static Position of(int length, int depth) {
            return new Position(length, depth);
        }
    }


    public static void main(String[] args) {
        Command[] input1 = stringArrayToCommandArray(fileToStringArray(Problem02A.class, "Problem02Input1.txt"));
        Command[] input2 = stringArrayToCommandArray(fileToStringArray(Problem02A.class, "Problem02Input2.txt"));

        System.out.println(calculatePositionProduct(input1));
        System.out.println(calculatePositionProduct(input2));

//        Output
//        150
//        1762050
    }

    static class Command {
        private final String dir;
        private final int units;

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

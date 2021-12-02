package aoc2021;

import aoc.Tuple2;

import java.util.Arrays;

import static aoc.Utils.fileToStringArray;

public class Problem02A {

    static private int calculatePositionProduct(Command[] comms) {
        @SuppressWarnings({"unchecked"})
        Tuple2<Integer> position = Arrays.stream(comms)
                .reduce(Tuple2.of(0, 0), Problem02A::calculateNextPosition, Problem02A::combiner);

        return position.left * position.right;
    }

    @SuppressWarnings({"unchecked"})
    private static Tuple2<Integer> calculateNextPosition(Tuple2<Integer> p, Command comm) {
        switch (comm.dir) {
            case "forward" : return Tuple2.of(p.left + comm.units, p.right);
            case "up" : return Tuple2.of(p.left, p.right - comm.units);
            case "down" : return Tuple2.of(p.left, p.right + comm.units);
            default : throw new RuntimeException("Wrong direction: " + comm.dir);
        }
    }

    @SuppressWarnings({"unchecked"})
    private static Tuple2<Integer> combiner(Tuple2<Integer> p1, Tuple2<Integer> p2) {
        return Tuple2.of(p1.left + p2.left, p1.right + p2.right);
    }

    public static void main(String[] args) {
        String[] input1 = fileToStringArray(Problem02A.class, "Problem02Input1.txt");
        String[] input2 = fileToStringArray(Problem02A.class, "Problem02Input2.txt");

        System.out.println(calculatePositionProduct(stringArrayToCommandArray(input1)));
        System.out.println(calculatePositionProduct(stringArrayToCommandArray(input2)));

//        Output
//        150
//        1762050
    }

    private static Command[] stringArrayToCommandArray(String[] input1) {
        return Arrays.stream(input1)
                .map(Problem02A::stringToCommand)
                .toArray(Command[]::new);
    }

    private static class Command {
        private final String dir;
        private final int units;

        private Command(String dir, int units) {
            this.dir = dir;
            this.units = units;
        }
    }

    private static Command stringToCommand(String str) {
        String[] strs = str.split(" ");
        return new Command(strs[0], Integer.parseInt(strs[1]));
    }
}

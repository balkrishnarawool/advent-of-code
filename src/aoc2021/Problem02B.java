package aoc2021;

import aoc.Tuple3;

import java.util.Arrays;

import static aoc.Utils.fileToStringArray;

public class Problem02B {

    static private long calculatePositionProduct(Command[] comms) {
        @SuppressWarnings({"unchecked"})
        Tuple3<Integer> position = Arrays.stream(comms)
                .reduce(Tuple3.of(0, 0, 0), Problem02B::calculateNextPosition, Problem02B::combiner);

        return position.first * position.second;
    }

    @SuppressWarnings({"unchecked"})
    private static Tuple3<Integer> calculateNextPosition(Tuple3<Integer> p, Command comm) {
        switch (comm.dir) {
            case "forward" : return Tuple3.of(p.first + comm.units, p.second + p.third * comm.units, p.third);
            case "up" : return Tuple3.of(p.first, p.second, p.third - comm.units);
            case "down" : return Tuple3.of(p.first, p.second, p.third + comm.units);
            default : throw new RuntimeException("Wrong direction: " + comm.dir);
        }
    }

    private static Tuple3<Integer> combiner(Tuple3<Integer> p1, Tuple3<Integer> p2) {
        throw new RuntimeException("The commands are not meant to be executed in parallel. If you're using stream.parallel(), remove it!");
    }

    public static void main(String[] args) {
        String[] input1 = fileToStringArray(Problem02B.class, "Problem02Input1.txt");
        String[] input2 = fileToStringArray(Problem02B.class, "Problem02Input2.txt");

        System.out.println(calculatePositionProduct(stringArrayToCommandArray(input1)));
        System.out.println(calculatePositionProduct(stringArrayToCommandArray(input2)));

//        Output
//        900
//        1855892637
    }

    private static Command[] stringArrayToCommandArray(String[] input1) {
        return Arrays.stream(input1)
                .map(Problem02B::stringToCommand)
                .toArray(Command[]::new);
    }

    private static class Command {
        private String dir;
        private int units;

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

package aoc2023;

import java.util.HashMap;
import java.util.Map;

public class Problem08A {
    public static void main(String[] args) {
//        var str = Problem08Input.INPUT01;
//        var str = Problem08Input.INPUT02;
        var str = Problem08Input.INPUT03;

        var instr = str.split("\n")[0];
        var nodes = parse(str.split("\n"));
        System.out.println(countSteps(instr, nodes));
    }

    private static int countSteps(String instr, Map<String, Node> nodes) {
        var current = "AAA";
        var count = 0;
        while (!current.equals("ZZZ")) {
            var step = instr.charAt(count % instr.length());
            current = switch (step) {
                case 'L' -> nodes.get(current).left;
                case 'R' -> nodes.get(current).right;
                default -> throw new IllegalStateException("Instruction can only be L or R and cannot be " + step);
            };
            count++;
        }
        return count;
    }

    record Node(String name, String left, String right) { }

    private static Map<String, Node> parse(String[] sa) {
        var nodes = new HashMap<String, Node>();
        for (int i = 2; i < sa.length; i++) {
            var name = sa[i].split(" = ")[0];
            var left = sa[i].split(" = ")[1].split(", ")[0].substring(1);
            var right = sa[i].split(" = ")[1].split(", ")[1].substring(0, 3);
            nodes.put(name, new Node(name, left, right));
        }
        return nodes;
    }

    private static int solve(String s) {
        return 0;
    }
}

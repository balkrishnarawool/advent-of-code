package aoc2023;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem08B {
    public static void main(String[] args) {
        var str = Problem08Input.INPUT01;
//        var str = Problem08Input.INPUT02;
//        var str = Problem08Input.INPUT03;

        var instr = str.split("\n")[0];
        var nodes = parse(str.split("\n"));
        System.out.println(countSteps(instr, nodes));
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

    private static BigInteger countSteps(String instr, Map<String, Node> nodes) {
        return getAllStartingNodes(nodes).stream()
                .map(n -> getCountForNode(n, instr, nodes))
                .map(BigInteger::valueOf)
                .reduce(Problem08B::lcm)
                .orElse(BigInteger.ZERO);
    }

    private static int getCountForNode(String start, String instr, Map<String, Node> nodes) {
        var current = start;
        var count = 0;
        while (!current.endsWith("Z")) {
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

    private static List<String> getAllStartingNodes(Map<String, Node> nodes) {
        return nodes.keySet().stream().filter(k -> k.endsWith("A")).toList();
    }

    private static BigInteger lcm(BigInteger a, BigInteger b) {
        return a.multiply(b).divide(a.gcd(b));
    }
}

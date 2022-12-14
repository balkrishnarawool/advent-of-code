package aoc2022;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static aoc.Utils.readFile;
import static aoc2022.Problem13.Result.*;

public class Problem13 {

    public static void main(String[] args) throws IOException {
        solvePart1("./src/aoc2022/Problem13Input1.txt");
        solvePart1("./src/aoc2022/Problem13Input2.txt");
        solvePart2("./src/aoc2022/Problem13Input1.txt");
        solvePart2("./src/aoc2022/Problem13Input2.txt");
    }

    private static void solvePart1(String path) throws IOException {
        System.out.println(solve(path));
    }

    private static void solvePart2(String path) throws IOException {
        System.out.println(solve2(path));
    }

    private static long solve(String path) throws IOException {
        var input = parseInput(readFile(path));
        var sum = 0;
        for (int i = 0; i < input.list.size(); i++) {
            var pair = input.list.get(i);
            var r = inOrder(pair.left, pair.right);
//            System.out.println((i+1)+"Result: "+r);
            if (r.equals(IN_ORDER))
                sum += (i + 1);
        }
        return sum;
    }

    private static long solve2(String path) throws IOException {
        var input2 = parseInput2(readFile(path));
        input2.list.add(new PacketMarker(parsePacket("[[2]]"), "d"));
        input2.list.add(new PacketMarker(parsePacket("[[6]]"), "d"));

        for (int i = 0; i < input2.list.size()-1; i++) {
            for (int j = 0; j < input2.list.size()-i-1; j++) {
                var r = inOrder(input2.list.get(j).packet, input2.list.get(j+1).packet);
                if (r.equals(NOT_IN_ORDER)) {
                    var t = input2.list.remove(j+1);
                    input2.list.add(j+1, input2.list.get(j));
                    input2.list.add(j, t);
                    input2.list.remove(j+1);
                }
            }
        }
        var prod = 1L;
        for (int i = 0; i < input2.list.size(); i++) {
            if (input2.list.get(i).marker.equals("d")) prod *= (i+1);
        }
        return prod;
    }

    private static Result inOrder(Packet left, Packet right) {
        if (left instanceof Int l && right instanceof Int r) {
//            System.out.println("Comparing "+l+" with "+r);
            if (l.data < r.data) { /*System.out.println("Left side is smaller");*/ return IN_ORDER; }
            if (l.data == r.data) return EQUAL;
            return NOT_IN_ORDER;
        }
        if (left instanceof ListP ll && right instanceof ListP rl) {
//            System.out.println("Comparing "+ll+" with "+rl);
            var r = EQUAL;
            var i = 0;
            while (r == EQUAL && i < Math.max(ll.list.size(), rl.list.size())) {
                if (i >= ll.list.size()) {
                    r = IN_ORDER;
                } else if (i >= rl.list.size()) {
                    r = NOT_IN_ORDER;
                } else {
                    r = inOrder(ll.list.get(i), rl.list.get(i));
                }
                i++;
            }
            return r;
        }
        if (left instanceof ListP ll && right instanceof Int ri) {
//            System.out.println("Comparing "+ll+" with "+ri);
            var l = new ListP(); l.list.add(ri);
//            System.out.println("Comparing "+ll+" with "+l);
            return inOrder(ll, l);
        }
        if (left instanceof Int li && right instanceof ListP rl) {
//            System.out.println("Comparing "+li+" with "+rl);
            var l = new ListP(); l.list.add(li);
//            System.out.println("Comparing "+l+" with "+rl);
            return inOrder(l, rl);
        }
        return EQUAL;
    }

    private static Input parseInput(List<String> lines) {
        var input = new Input();
        for (int i = 0; i <= lines.size()/3; i++) {
            var left = parsePacket(lines.get(i*3));
            var right = parsePacket(lines.get(i*3 + 1));
            input.list.add(new Pair(left, right));
        }
        return input;
    }

    private static Input2 parseInput2(List<String> lines) {
        var input2 = new Input2();
        for (String line : lines) {
            if (!line.isEmpty()) {
                var p = parsePacket(line);
                input2.list.add(new PacketMarker(p, ""));
            }
        }
        return input2;
    }

    private static Packet parsePacket(String s) {
        Packet p = null;
        var stack = new Stack<ListP>();
        var gen = new Generator(s);
        while(gen.hasNextToken()) {
            var t = gen.getNextToken();
//            System.out.println(t);
            switch (t) {
                case "[":
                    var lp = new ListP();
                    if (!stack.isEmpty()) stack.peek().list.add(lp);
                    stack.push(lp);
                    break;
                case "]":
                    p = stack.pop();
                    break;
                default:
                    if (stack.isEmpty()) return new Int(Integer.parseInt(t));
                    else stack.peek().list.add(new Int(Integer.parseInt(t)));
            }
        }
        return p;
    }

    @ToString
    static class Input {
        List<Pair> list = new ArrayList<>();
    }

    static class Input2 {
        List<PacketMarker> list = new ArrayList<>();
    }

    record PacketMarker(Packet packet, String marker) { }

    record Pair(Packet left, Packet right) { }
    interface Packet { }
    record Int(int data) implements Packet {
        @Override
        public String toString() {
            return ""+data;
        }
    }
    static class ListP implements Packet { List<Packet> list = new ArrayList<>();

        @Override
        public String toString() {
            return list.toString();
        }
    }
    @RequiredArgsConstructor
    static class Generator {
        @NonNull
        String s;

        int index = 0;

        public boolean hasNextToken() {
            return index < s.length();
        }

        public String getNextToken() {
            if (index == s.length()) return "";
            var c = s.charAt(index);
            index++;
            if (c >= '0' && c <= '9') {
                return getNextToken(""+c);
            }
            if (c == ',') return getNextToken();
            return ""+c;
        }

        private String getNextToken(String ss) {
            if (index == s.length()) return ss;
            var c = s.charAt(index);
            index++;
            if (c >= '0' && c <= '9') {
                return getNextToken(ss+c);
            }
            if (c != ',') index--;
            return ss;
        }
    }
    enum Result {
        IN_ORDER, EQUAL, NOT_IN_ORDER
    }

//     Output
//     13
//     5659
//     140
//     22110
}

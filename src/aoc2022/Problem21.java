package aoc2022;

import lombok.AllArgsConstructor;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static aoc.Utils.readFile;
import static aoc2022.Problem21.Oper.ADD;

public class Problem21 {

    public static void main(String[] args) throws IOException {
        solvePart1("./src/aoc2022/Problem21Input1.txt");
        solvePart1("./src/aoc2022/Problem21Input2.txt");
        solvePart2("./src/aoc2022/Problem21Input1.txt");
        solvePart2("./src/aoc2022/Problem21Input2.txt");
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
        return input.calculate("root");
    }

    private static long solve2(String path) throws IOException {
        var lines = readFile(path);
        var input = parseInput(lines);
//        input.show();
        return input.findH();
    }

    private static Input parseInput(List<String> lines) {
        var input = new Input();
        for (var s: lines) {
            var sa = s.split(" ");
            var name = sa[0].substring(0, sa[0].length()-1);
            if (sa.length == 2) {
                var t = new Leaf(name, Long.parseLong(sa[1]));
                input.map.put(name, t);
            } else {
                var l = sa[1];
                var r = sa[3];
                var o = Oper.parseOper(sa[2]);
                input.map.remove(name);
                var t =  new Fork(name, o, l, r, input);
                input.map.put(name, t);
            }
        }
        return input;
    }

    static class Input {
        Map<String, TreeNode> map = new HashMap<>();

        public long findH() {
            var root = map.get("root");
            if (root instanceof Fork f) {
                if (containsH(f.left)) return findHLeft(ADD, 2*calculate(f.right), calculate(f.right), f.left);
                if (containsH(f.right)) return findHRight(ADD, 2*calculate(f.left), calculate(f.left), f.right);
                throw new RuntimeException("humn is not found");
            }
            throw new RuntimeException("root is not fork");
        }

        private boolean containsH(String name) {
            if (name.equals("humn")) return true;
            var t = map.get(name);
            if (t instanceof Fork f) {
                if (containsH(f.left)) return true;
                if (containsH(f.right)) return true;
                return false;
            }
            return false;
        }

        private long findHLeft(Oper oper, long value, long otherValue, String name) {
            var t = map.get(name);
            if (t instanceof Leaf l) {
                if (l.name.equals("humn"))
                    return reverseLeft(oper, value, otherValue);
                else throw new RuntimeException("humn should be here but not here");
            }
            if (t instanceof Fork f) {
                if (containsH(f.left))
                    return findHLeft(f.oper, reverseLeft(oper, value, otherValue), calculate(f.right), f.left);
                if (containsH(f.right))
                    return findHRight(f.oper, reverseLeft(oper, value, otherValue), calculate(f.left), f.right);
                throw new RuntimeException("humn is not found");
            }
            throw new RuntimeException(name +" is not Leaf or Fork.. how?");
        }

        private long findHRight(Oper oper, long value, long otherValue, String name) {
            var t = map.get(name);
            if (t instanceof Leaf l) {
                if (l.name.equals("humn"))
                    return reverseRight(oper, value, otherValue);
                else throw new RuntimeException("humn should be here but not here");
            }
            if (t instanceof Fork f) {
                if (containsH(f.left))
                    return findHLeft(f.oper, reverseRight(oper, value, otherValue), calculate(f.right), f.left);
                if (containsH(f.right))
                    return findHRight(f.oper, reverseRight(oper, value, otherValue), calculate(f.left), f.right);
                throw new RuntimeException("humn is not found");
            }
            throw new RuntimeException(name +" is not Leaf or Fork.. how?");
        }

        private long reverseLeft(Oper oper, long value, long otherValue) {
            return switch (oper) {
                case ADD -> value - otherValue;
                case SUBTRACT -> value + otherValue;
                case MULTIPLY -> value / otherValue;
                case DIVIDE -> value * otherValue;
            };
        }

        private long reverseRight(Oper oper, long value, long otherValue) {
            return switch (oper) {
                case ADD -> value - otherValue;
                case SUBTRACT ->  otherValue - value;
                case MULTIPLY -> value / otherValue;
                case DIVIDE -> otherValue / value;
            };
        }

        private long calculate(String name) {
            return map.get(name).calculate();
        }

        public void show() {
            map.get("root").show(0);
        }
    }

    interface TreeNode {
        long calculate();
        void show(int i);
    }

    @AllArgsConstructor
    static class Leaf implements TreeNode {
        String name;
        long value;

        @Override
        public long calculate() {
            return value;
        }

        @Override
        public void show(int i) {
            for (int j = 0; j < i; j++) {
                System.out.print(" ");
            }
            System.out.println(name);
        }
    }

    @AllArgsConstructor
    static class Fork implements TreeNode {
        String name;
        Oper oper;
        String left;
        String right;
        Input input;

        @Override
        public long calculate() {
            return switch (oper) {
                case ADD -> input.map.get(left).calculate() + input.map.get(right).calculate();
                case SUBTRACT -> input.map.get(left).calculate() - input.map.get(right).calculate();
                case DIVIDE -> input.map.get(left).calculate() / input.map.get(right).calculate();
                case MULTIPLY -> input.map.get(left).calculate() * input.map.get(right).calculate();
            };
        }

        @Override
        public void show(int i) {
            for (int j = 0; j < i; j++) {
                System.out.print(" ");
            }
            System.out.println(name);

            input.map.get(left).show(i+1);
            input.map.get(right).show(i+1);
        }
    }

    enum Oper {
        ADD, SUBTRACT, MULTIPLY, DIVIDE;

        public static Oper parseOper(String s) {
            return switch (s) {
                case "+" -> ADD;
                case "-" -> SUBTRACT;
                case "/" -> DIVIDE;
                case "*" -> MULTIPLY;
                default -> throw new RuntimeException("Oper "+s+" is not supported");
            };
        }
    }

//     Output
//     152
//     22382838633806
//     301
//     3099532691300

}

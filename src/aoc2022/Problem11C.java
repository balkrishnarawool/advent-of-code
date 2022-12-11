package aoc2022;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static aoc.Utils.readFile;

// Solves both parts
public class Problem11C {

    public static void main(String[] args) throws IOException {
        solvePart1("./src/aoc2022/Problem11Input1.txt");
        solvePart1("./src/aoc2022/Problem11Input2.txt");
        solvePart2("./src/aoc2022/Problem11Input1.txt");
        solvePart2("./src/aoc2022/Problem11Input2.txt");
    }

    private static void solvePart1(String path) throws IOException {
        System.out.println(solve(path));
    }

    private static void solvePart2(String path) throws IOException {
        System.out.println(solve2(path));
    }

    private static long solve(String path) throws IOException {
        var ms = parseInput(path); // ms = monkeys
        for (int i = 0; i < 20; i++) {
            for (var m: ms) {
                for (var n: m.list) {
                    m.c ++;
                    long r = m.e.eval(n) / 3;
                    if (r % m.divTest == 0) ms.get(m.t).list.add(r); else ms.get(m.f).list.add(r);
                }
                m.list = new ArrayList<>();
            }

        }
        return ms.stream().map(m -> m.c).sorted(Comparator.reverseOrder()).limit(2).reduce((a, b) -> a * b).orElse(0);
    }

    private static BigInteger solve2(String path) throws IOException {
        var ms = parseInput(path); // ms = monkeys
        var limit = getLimit(ms);
        for (int i = 0; i < 10_000; i++) {
            for (var m: ms) {
                for (var n: m.list) {
                    m.c ++;
                    long r = m.e.eval(n, limit);
                    if (r % m.divTest == 0) ms.get(m.t).list.add(r); else ms.get(m.f).list.add(r);
                }
                m.list = new ArrayList<>();
            }

        }
        var l = ms.stream().map(m -> m.c).sorted(Comparator.reverseOrder()).limit(2).toList();
        return BigInteger.valueOf(l.get(0)).multiply(BigInteger.valueOf(l.get(1)));
    }

    private static long getLimit(List<Monkey> ms) {
        return ms.stream().map(m -> m.divTest).reduce(1L, (a, b) -> a * b);
    }

    private static List<Monkey> parseInput(String path) throws IOException {
        var ms = new ArrayList<Monkey>();
        var lines = readFile(path);
        for (int i = 0; i < lines.size(); i+=7) {
            var m = new Monkey();

            var sa = lines.get(i+1).split(" ");
            for (int j = 4; j < sa.length; j++) {
                var str = sa[j].endsWith(",") ? sa[j].substring(0, sa[j].length()-1) : sa[j];
                m.list.add(Long.parseLong(str));
            }

            var sa1 = lines.get(i+2).split(" ");
            if (sa1[6].equals("*")) m.e.t = Type.MULT; else m.e.t = Type.ADD;
            if (sa1[7].equals("old")) m.e.v = new Old(); else m.e.v = new Int(Long.parseLong(sa1[7]));

            var sa2 = lines.get(i+3).split(" ");
            m.divTest = Long.parseLong(sa2[5]);

            var sa3 = lines.get(i+4).split(" ");
            m.t = Integer.parseInt(sa3[9]);

            var sa4 = lines.get(i+5).split(" ");
            m.f = Integer.parseInt(sa4[9]);

            ms.add(m);
        }
        return ms;
    }

    static class Monkey {
        List<Long> list = new ArrayList<>();
        Expr e = new Expr();

        long divTest;
        int t; // if true monkey-index
        int f; // if false monkey-index

        int c = 0;
    }

    static class Expr {
        Type t;
        Value v;

        public long eval(Long n) {
            return switch (t) {
                case ADD -> (v instanceof Int i) ? n + i.l : n + n;
                case MULT -> (v instanceof Int i) ? n * i.l : n * n;
            };
        }

        public long eval(Long n, Long limit) {
            return switch (t) {
                case ADD -> (v instanceof Int i) ? (n + i.l) % limit : (n + n) % limit;
                case MULT -> (v instanceof Int i) ? (n * i.l) % limit : (n * n) % limit;
            };
        }
    }

    enum Type {
        ADD, MULT;
    }

    interface Value { }
    record Old() implements Value { }
    record Int(long l) implements Value { }

//     Output
//     10605
//     61005
//     2713310158
//     20567144694

}

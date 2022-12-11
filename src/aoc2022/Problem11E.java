package aoc2022;

import lombok.AllArgsConstructor;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static aoc.Utils.readFile;

public class Problem11E {

    public static void main(String[] args) throws IOException {
        solvePart2("./src/aoc2022/Problem11Input1.txt");
    }

    private static void solvePart2(String path) throws IOException {
        System.out.println(solve2(path));
    }

    private static BigInteger solve2(String path) throws IOException {
        var ms = parseInput(path); // ms = monkeys
        for (int i = 0; i < 10_000; i++) {
            for (var m: ms) {
                for (var n: m.list) {
                    m.c ++;
                    var r = m.e.eval(n);
                    if (r.isDivisible(m.divTest)) ms.get(m.t).list.add(r); else ms.get(m.f).list.add(r);
                }
                m.list = new ArrayList<>();
            }

        }
        var l = ms.stream().map(m -> m.c).sorted(Comparator.reverseOrder()).limit(2).toList();
        return BigInteger.valueOf(l.get(0)).multiply(BigInteger.valueOf(l.get(1)));
    }

    private static List<Monkey> parseInput(String path) throws IOException {
        var ms = new ArrayList<Monkey>();
        var lines = readFile(path);
        for (int i = 0; i < lines.size(); i+=7) {
            var m = new Monkey();

            var sa = lines.get(i+1).split(" ");
            for (int j = 4; j < sa.length; j++) {
                var str = sa[j].endsWith(",") ? sa[j].substring(0, sa[j].length()-1) : sa[j];
                m.list.add(Div.get(Long.parseLong(str)));
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
        List<Div> list = new ArrayList<>();
        Expr e = new Expr();

        long divTest;
        int t; // if true monkey-index
        int f; // if false monkey-index

        int c = 0;
    }

    @AllArgsConstructor
    static class Div {
        long d23;
        long d19;
        long d13;
        long d17;

        public static Div get(long l) {
            return new Div(l%23, l%19, l%13, l%17);
        }
        public static Div get(long l1, long l2, long l3, long l4) {
            return new Div(l1%23, l2%19, l3%13, l4%17);
        }

        public boolean isDivisible(long dt) {
            if (dt == 23) return d23 == 0;
            if (dt == 19) return d19 == 0;
            if (dt == 13) return d13 == 0;
            if (dt == 17) return d17 == 0;
            throw new RuntimeException("dt is : " + dt);
        }
    }

    static class Expr {
        Type t;
        Value v;

        public Div eval(Div n) {
            return switch (t) {
                case ADD -> (v instanceof Int i) ? Div.get(n.d23 + i.l, n.d19 + i.l, n.d13 + i.l, n.d17 + i.l) : Div.get(n.d23 + n.d23, n.d19 + n.d19, n.d13 + n.d13, n.d17 + n.d17);
                case MULT -> (v instanceof Int i) ? Div.get(n.d23 * i.l, n.d19 * i.l, n.d13 * i.l, n.d17 * i.l) : Div.get(n.d23 * n.d23, n.d19 * n.d19, n.d13 * n.d13, n.d17 * n.d17); //TODO
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


}

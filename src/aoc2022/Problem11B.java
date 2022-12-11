package aoc2022;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.math.BigInteger;
import java.util.*;

import static aoc.Utils.readFile;

public class Problem11B {

    public static void main(String[] args) throws IOException {
        solvePart2("./src/aoc2022/Problem11Input1.txt");
        solvePart2("./src/aoc2022/Problem11Input2.txt");
    }

    private static void solvePart2(String path) throws IOException {
        System.out.println(solve(path));
    }

    private static BigInteger solve(String path) throws IOException {
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
        var set = new HashSet<Long>();
        var lines = readFile(path);
        for (int i = 0; i < lines.size(); i+=7) {
            var m = new Monkey();

            var sa1 = lines.get(i+2).split(" ");
            if (sa1[6].equals("*")) m.e.t = Type.MULT; else m.e.t = Type.ADD;
            if (sa1[7].equals("old")) m.e.v = new Old(); else m.e.v = new Int(Long.parseLong(sa1[7]));

            var sa2 = lines.get(i+3).split(" ");
            var l = Long.parseLong(sa2[5]);
            m.divTest = l;
            set.add(l);

            var sa3 = lines.get(i+4).split(" ");
            m.t = Integer.parseInt(sa3[9]);

            var sa4 = lines.get(i+5).split(" ");
            m.f = Integer.parseInt(sa4[9]);

            ms.add(m);
        }

        for (int i = 0; i < ms.size(); i++) {
            var m = ms.get(i);
            var sa = lines.get((i*7) + 1).split(" ");
            for (int j = 4; j < sa.length; j++) {
                var str = sa[j].endsWith(",") ? sa[j].substring(0, sa[j].length() - 1) : sa[j];
                m.list.add(Number.createFrom(set, Long.parseLong(str)));
            }
        }
        return ms;
    }

    static class Monkey {
        List<Number> list = new ArrayList<>();
        Expr e = new Expr();

        long divTest;
        int t; // if true monkey-index
        int f; // if false monkey-index

        int c = 0;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    static class Number {
        Map<Long, Long> modulos = new HashMap<>();

        public static Number createFrom(HashSet<Long> keys, long l) {
            var d = new Number();
            for (var k: keys) {
                d.modulos.put(k, l % k);
            }
            return d;
        }

        public boolean isDivisible(long l) {
            var r = modulos.get(l);
            if (r == null) throw new RuntimeException("l is : " + l);
            else return r == 0;
        }
    }

    static class Expr {
        Type t;
        Value v;

        public Number eval(Number n) {
            var m = new HashMap<Long, Long>();
            switch (t) {
                case ADD -> {
                    for (var e : n.modulos.keySet()) {
                        m.put(e, (n.modulos.get(e) + ((v instanceof Int i) ? i.l : n.modulos.get(e))) % e);
                    }
                    return new Number(m);
                }
                case MULT -> {
                    for (var e : n.modulos.keySet()) {
                        m.put(e, (n.modulos.get(e) * ((v instanceof Int i) ? i.l : n.modulos.get(e))) % e);
                    }
                    return new Number(m);
                }
            }
            throw new RuntimeException("Type is not ADD, MULIPLY");
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

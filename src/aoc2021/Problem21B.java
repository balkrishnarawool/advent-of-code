package aoc2021;

import aoc.Tuple4;

import java.util.HashMap;
import java.util.Map;

import static aoc.Utils.fileToStringArray;

public class Problem21B {

    static private Map<Tuple4<Long>, Long> wins1 = new HashMap<>();
    static private Map<Tuple4<Long>, Long> wins2 = new HashMap<>();

    private static void search(long p1, long p2, long p1p, long p2p, int t) {

        if (p1p >= 21) { wins1.put(Tuple4.of(p1, p2, p1p, p2p), 1L);  wins2.put(Tuple4.of(p1, p2, p1p, p2p), 0L); }
        else if (p2p >= 21) { wins2.put(Tuple4.of(p1, p2, p1p, p2p), 1L);  wins1.put(Tuple4.of(p1, p2, p1p, p2p), 0L); }
        else {
            long w = 0L;
            long l = 0L;
            long p3 = move(p1, 3);
            if (!wins1.containsKey(Tuple4.of(p2, p3, p2p, p1p + p3)) || !wins2.containsKey(Tuple4.of(p2, p3, p2p, p1p + p3)))
                search(p2, p3, p2p, p1p + p3, t + 1);
            w += wins2.get(Tuple4.of(p2, p3, p2p, p1p + p3));
            l += wins1.get(Tuple4.of(p2, p3, p2p, p1p + p3));

            long p4 = move(p1, 4);
            print(t, "P1 4");
            if (!wins1.containsKey(Tuple4.of(p2, p4, p2p, p1p + p4)) || !wins2.containsKey(Tuple4.of(p2, p4, p2p, p1p + p4)))
                search(p2, p4, p2p, p1p + p4, t + 1);
            w += 3 * wins2.get(Tuple4.of(p2, p4, p2p, p1p + p4));
            l += 3 * wins1.get(Tuple4.of(p2, p4, p2p, p1p + p4));

            long p5 = move(p1, 5);
            print(t, "P1 5");
            if (!wins1.containsKey(Tuple4.of(p2, p5, p2p, p1p + p5)) || !wins2.containsKey(Tuple4.of(p2, p5, p2p, p1p + p5)))
                search(p2, p5, p2p, p1p + p5, t + 1);
            w += 6 * wins2.get(Tuple4.of(p2, p5, p2p, p1p + p5));
            l += 6 * wins1.get(Tuple4.of(p2, p5, p2p, p1p + p5));

            long p6 = move(p1, 6);
            print(t, "P1 6");
            if (!wins1.containsKey(Tuple4.of(p2, p6, p2p, p1p + p6)) || !wins2.containsKey(Tuple4.of(p2, p6, p2p, p1p + p6)))
                search(p2, p6, p2p, p1p + p6, t + 1);
            w += 7 * wins2.get(Tuple4.of(p2, p6, p2p, p1p + p6));
            l += 7 * wins1.get(Tuple4.of(p2, p6, p2p, p1p + p6));

            long p7 = move(p1, 7);
            print(t, "P1 7");
            if (!wins1.containsKey(Tuple4.of(p2, p7, p2p, p1p + p7)) || !wins2.containsKey(Tuple4.of(p2, p7, p2p, p1p + p7)))
                search(p2, p7, p2p, p1p + p7, t + 1);
            w += 6 * wins2.get(Tuple4.of(p2, p7, p2p, p1p + p7));
            l += 6 * wins1.get(Tuple4.of(p2, p7, p2p, p1p + p7));

            long p8 = move(p1, 8);
            print(t, "P1 8");
            if (!wins1.containsKey(Tuple4.of(p2, p8, p2p, p1p + p8)) || !wins2.containsKey(Tuple4.of(p2, p8, p2p, p1p + p8)))
                search(p2, p8, p2p, p1p + p8, t + 1);
            w += 3 * wins2.get(Tuple4.of(p2, p8, p2p, p1p + p8));
            l += 3 * wins1.get(Tuple4.of(p2, p8, p2p, p1p + p8));

            long p9 = move(p1, 9);
            print(t, "P1 9");
            if (!wins1.containsKey(Tuple4.of(p2, p9, p2p, p1p + p9)) || !wins2.containsKey(Tuple4.of(p2, p9, p2p, p1p + p9)))
                search(p2, p9, p2p, p1p + p9, t + 1);
            w += wins2.get(Tuple4.of(p2, p9, p2p, p1p + p9));
            l += wins1.get(Tuple4.of(p2, p9, p2p, p1p + p9));

            wins1.put(Tuple4.of(p1, p2, p1p, p2p), w);
            wins2.put(Tuple4.of(p1, p2, p1p, p2p), l);

        }
    }

    private static void print(int t, String s) {
        for (int i = 0; i < t; i++) { System.out.print("  "); }
        System.out.println(s);
    }

    private static long move(long p, int dp) {
        p+=dp;
        return p % 10 == 0 ? 10 : p % 10;
    }

    public static void main(String[] args) {
        String[] input1 = fileToStringArray(Problem21B.class, "Problem21Input1.txt");
        String[] input2 = fileToStringArray(Problem21B.class, "Problem21Input2.txt");

        solve(input1);
        solve(input2);
    }

    private static void solve(String[] input) {

        long p1 = Integer.parseInt(input[0].substring("Player 1 starting position: ".length()));
        long p2 = Integer.parseInt(input[1].substring("Player 2 starting position: ".length()));

        long p1p = 0;
        long p2p = 0;

        search(p1, p2, p1p, p2p, 0);
        System.out.println(wins1.get(Tuple4.of(p1, p2, p1p, p2p)));
        System.out.println(wins2.get(Tuple4.of(p1, p2, p1p, p2p)));
    }
}

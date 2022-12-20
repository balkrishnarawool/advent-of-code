package aoc2022;

import java.io.IOException;
import java.util.*;

import static aoc.Utils.readFile;

public class Problem20 {

    public static void main(String[] args) throws IOException {
        solvePart1("./src/aoc2022/Problem20Input1.txt");
        solvePart1("./src/aoc2022/Problem20Input2.txt");
        solvePart2("./src/aoc2022/Problem20Input1.txt");
        solvePart2("./src/aoc2022/Problem20Input2.txt");
    }

    private static void solvePart1(String path) throws IOException {
        System.out.println(solve(path));
    }

    private static void solvePart2(String path) throws IOException {
        System.out.println(solve2(path));
    }

    private static long solve(String path) throws IOException {
        var lines = readFile(path);
        var list = new ArrayList<CircularNode>();
        CircularNode first = null;
        CircularNode n = null;
        for (var s: lines) {
            if (n == null) n = new CircularNode(Long.parseLong(s));
            else n = n.add(Long.parseLong(s));
            if (n.value == 0) first = n;
            list.add(n);
        }
        for (var e: list) {
            if (e.value != 0) {
                var d = e.value > 0;
                for (int i = 0; i < Math.abs(e.value); i++) {
                    if (d) e.moveForward();
                    else e.moveBackward();
                }
            }
        }
        return first.getNthNode(1000).value + first.getNthNode(2000).value + first.getNthNode(3000).value;
    }

    private static long solve2(String path) throws IOException {
        var lines = readFile(path);
        var list = new ArrayList<CircularNode>();
        CircularNode first = null;
        CircularNode n = null;
        for (var s: lines) {
            if (n == null) n = new CircularNode(Long.parseLong(s) * 811589153L);
            else n = n.add(Long.parseLong(s) * 811589153L);
            if (n.value == 0) first = n;
            list.add(n);
        }
        for (int j = 0; j < 10; j++) {
            for (var e: list) {
                if (e.value != 0) {
                    var d = e.value > 0;
                    var t = Math.floorMod(Math.abs(e.value), (list.size() - 1));
                    for (int i = 0; i < t; i++) {
                        if (d) e.moveForward();
                        else e.moveBackward();
                    }
                }
            }
        }
        return first.getNthNode(1000 % list.size()).value + first.getNthNode(2000 % list.size()).value + first.getNthNode(3000 % list.size()).value;
    }

    static class CircularNode {
        CircularNode prev;
        long value;
        CircularNode next;

        public CircularNode(long i) {
            value = i;
            prev = this;
            next = this;
        }

        public CircularNode add(long i) {
            var n = new CircularNode(i);

            n.next = next;
            next = n;

            n.next.prev = n;
            n.prev = this;

            return n;
        }

        public void moveForward() {
            prev.next = next;
            next.prev = prev;

            prev = next;
            next = next.next;

            prev.next = this;
            next.prev = this;
        }

        public void moveBackward() {
            next.prev = prev;
            prev.next = next;

            next = prev;
            prev = prev.prev;

            next.prev = this;
            prev.next = this;
        }

        public CircularNode getNthNode(int n) {
            var t = this;
            for (int i = 0; i < n; i++) {
                t = t.next;
            }
            return t;
        }
    }

//     Output
//     3
//     15297
//     1623178306
//     2897373276210


}

package aoc2022;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

import static aoc.Utils.readFile;

public class Problem09 {

    public static void main(String[] args) throws IOException {
        solvePart1("./src/aoc2022/Problem09Input1.txt");
        solvePart1("./src/aoc2022/Problem09Input2.txt");
        solvePart2("./src/aoc2022/Problem09Input1.txt");
        solvePart2("./src/aoc2022/Problem09Input3.txt");
        solvePart2("./src/aoc2022/Problem09Input2.txt");
    }

    private static void solvePart1(String path) throws IOException {
        System.out.println(solve(path));
    }

    private static void solvePart2(String path) throws IOException {
        System.out.println(solve2(path));
    }

    private static long solve(String path) throws IOException {
        var list = readFile(path);
        var set = new HashSet<Coords>();
        var s = new Coords(0, 0);
        set.add(s);
        var h = s;
        var t = s;
        for (var str: list) {
            var sa = str.split(" ");
            var n = Integer.parseInt(sa[1]);

            switch (sa[0]) {
                case "R":
                    for (int i = 0; i < n; i++) {
                        if (t.x == h.x-1 && t.y == h.y-1) { t = new Coords(t.x+1, t.y+1); }
                        else if (t.x == h.x && t.y == h.y-1) { t = new Coords(t.x, t.y); }
                        else if (t.x == h.x+1 && t.y == h.y-1) { t = new Coords(t.x, t.y); }

                        else if (t.x == h.x-1 && t.y == h.y) { t = new Coords(t.x+1, t.y); }
                        else if (t.x == h.x && t.y == h.y) { t = new Coords(t.x, t.y); }
                        else if (t.x == h.x+1 && t.y == h.y) { t = new Coords(t.x, t.y); }

                        else if (t.x == h.x-1 && t.y == h.y+1) { t = new Coords(t.x+1, t.y-1); }
                        else if (t.x == h.x && t.y == h.y+1) { t = new Coords(t.x, t.y); }
                        else if (t.x == h.x+1 && t.y == h.y+1) { t = new Coords(t.x, t.y); }

                        h = new Coords(h.x+1, h.y);
                        set.add(t);
                    }
                    break;
                case "L":
                    for (int i = 0; i < n; i++) {
                        if (t.x == h.x-1 && t.y == h.y-1) { t = new Coords(t.x, t.y); }
                        else if (t.x == h.x && t.y == h.y-1) { t = new Coords(t.x, t.y); }
                        else if (t.x == h.x+1 && t.y == h.y-1) { t = new Coords(t.x-1, t.y+1); }

                        else if (t.x == h.x-1 && t.y == h.y) { t = new Coords(t.x, t.y); }
                        else if (t.x == h.x && t.y == h.y) { t = new Coords(t.x, t.y); }
                        else if (t.x == h.x+1 && t.y == h.y) { t = new Coords(t.x-1, t.y); }

                        else if (t.x == h.x-1 && t.y == h.y+1) { t = new Coords(t.x, t.y); }
                        else if (t.x == h.x && t.y == h.y+1) { t = new Coords(t.x, t.y); }
                        else if (t.x == h.x+1 && t.y == h.y+1) { t = new Coords(t.x-1, t.y-1); }

                        h = new Coords(h.x-1, h.y);
                        set.add(t);
                    }
                break;
                case "U":
                    for (int i = 0; i < n; i++) {
                        if (t.x == h.x-1 && t.y == h.y-1) { t = new Coords(t.x, t.y); }
                        else if (t.x == h.x && t.y == h.y-1) { t = new Coords(t.x, t.y); }
                        else if (t.x == h.x+1 && t.y == h.y-1) { t = new Coords(t.x, t.y); }

                        else if (t.x == h.x-1 && t.y == h.y) { t = new Coords(t.x, t.y); }
                        else if (t.x == h.x && t.y == h.y) { t = new Coords(t.x, t.y); }
                        else if (t.x == h.x+1 && t.y == h.y) { t = new Coords(t.x, t.y); }

                        else if (t.x == h.x-1 && t.y == h.y+1) { t = new Coords(t.x+1, t.y-1); }
                        else if (t.x == h.x && t.y == h.y+1) { t = new Coords(t.x, t.y-1); }
                        else if (t.x == h.x+1 && t.y == h.y+1) { t = new Coords(t.x-1, t.y-1); }

                        h = new Coords(h.x, h.y-1);
                        set.add(t);
                    }
                break;
                case "D":
                    for (int i = 0; i < n; i++) {
                        if (t.x == h.x-1 && t.y == h.y-1) { t = new Coords(t.x+1, t.y+1); }
                        else if (t.x == h.x && t.y == h.y-1) { t = new Coords(t.x, t.y+1); }
                        else if (t.x == h.x+1 && t.y == h.y-1) { t = new Coords(t.x-1, t.y+1); }

                        else if (t.x == h.x-1 && t.y == h.y) { t = new Coords(t.x, t.y); }
                        else if (t.x == h.x && t.y == h.y) { t = new Coords(t.x, t.y); }
                        else if (t.x == h.x+1 && t.y == h.y) { t = new Coords(t.x, t.y); }

                        else if (t.x == h.x-1 && t.y == h.y+1) { t = new Coords(t.x, t.y); }
                        else if (t.x == h.x && t.y == h.y+1) { t = new Coords(t.x, t.y); }
                        else if (t.x == h.x+1 && t.y == h.y+1) { t = new Coords(t.x, t.y); }

                        h = new Coords(h.x, h.y+1);
                        set.add(t);
                    }
            }
        }

        return set.size();
    }

    private static long solve2(String path) throws IOException {
        var list = readFile(path);
        var set = new HashSet<Coords>();
        var s = new Coords(0, 0);
        set.add(s);
        var h = s;
        var ts = new Coords[9];
        Arrays.fill(ts, s);
        for (var str: list) {
            var sa = str.split(" ");
            var n = Integer.parseInt(sa[1]);
            for (int i = 0; i < n; i++) {
                h = move(sa[0], h, ts);
                set.add(ts[8]);
            }
        }
        return set.size();
    }

    private static Coords move(String dir, Coords h, Coords[] ts) {
        var t = new Coords(ts[0].x, ts[0].y);
        if (dir.equals("R")) {
            if (t.x == h.x-1 && t.y == h.y-1) { t = new Coords(t.x+1, t.y+1); }
            else if (t.x == h.x && t.y == h.y-1) { t = new Coords(t.x, t.y); }
            else if (t.x == h.x+1 && t.y == h.y-1) { t = new Coords(t.x, t.y); }

            else if (t.x == h.x-1 && t.y == h.y) { t = new Coords(t.x+1, t.y); }
            else if (t.x == h.x && t.y == h.y) { t = new Coords(t.x, t.y); }
            else if (t.x == h.x+1 && t.y == h.y) { t = new Coords(t.x, t.y); }

            else if (t.x == h.x-1 && t.y == h.y+1) { t = new Coords(t.x+1, t.y-1); }
            else if (t.x == h.x && t.y == h.y+1) { t = new Coords(t.x, t.y); }
            else if (t.x == h.x+1 && t.y == h.y+1) { t = new Coords(t.x, t.y); }

            moveEachKnot(t, ts);
            h = new Coords(h.x+1, h.y);
        }
        else if (dir.equals("L")) {
            if (t.x == h.x-1 && t.y == h.y-1) { t = new Coords(t.x, t.y); }
            else if (t.x == h.x && t.y == h.y-1) { t = new Coords(t.x, t.y); }
            else if (t.x == h.x+1 && t.y == h.y-1) { t = new Coords(t.x-1, t.y+1); }

            else if (t.x == h.x-1 && t.y == h.y) { t = new Coords(t.x, t.y); }
            else if (t.x == h.x && t.y == h.y) { t = new Coords(t.x, t.y); }
            else if (t.x == h.x+1 && t.y == h.y) { t = new Coords(t.x-1, t.y); }

            else if (t.x == h.x-1 && t.y == h.y+1) { t = new Coords(t.x, t.y); }
            else if (t.x == h.x && t.y == h.y+1) { t = new Coords(t.x, t.y); }
            else if (t.x == h.x+1 && t.y == h.y+1) { t = new Coords(t.x-1, t.y-1); }

            moveEachKnot(t, ts);
            h = new Coords(h.x-1, h.y);
        }
        else if (dir.equals("U")) {
            if (t.x == h.x-1 && t.y == h.y-1) { t = new Coords(t.x, t.y); }
            else if (t.x == h.x && t.y == h.y-1) { t = new Coords(t.x, t.y); }
            else if (t.x == h.x+1 && t.y == h.y-1) { t = new Coords(t.x, t.y); }

            else if (t.x == h.x-1 && t.y == h.y) { t = new Coords(t.x, t.y); }
            else if (t.x == h.x && t.y == h.y) { t = new Coords(t.x, t.y); }
            else if (t.x == h.x+1 && t.y == h.y) { t = new Coords(t.x, t.y); }

            else if (t.x == h.x-1 && t.y == h.y+1) { t = new Coords(t.x+1, t.y-1); }
            else if (t.x == h.x && t.y == h.y+1) { t = new Coords(t.x, t.y-1); }
            else if (t.x == h.x+1 && t.y == h.y+1) { t = new Coords(t.x-1, t.y-1); }

            moveEachKnot(t, ts);
            h = new Coords(h.x, h.y-1);
        }
        else if (dir.equals("D")) {
            if (t.x == h.x-1 && t.y == h.y-1) { t = new Coords(t.x+1, t.y+1); }
            else if (t.x == h.x && t.y == h.y-1) { t = new Coords(t.x, t.y+1); }
            else if (t.x == h.x+1 && t.y == h.y-1) { t = new Coords(t.x-1, t.y+1); }

            else if (t.x == h.x-1 && t.y == h.y) { t = new Coords(t.x, t.y); }
            else if (t.x == h.x && t.y == h.y) { t = new Coords(t.x, t.y); }
            else if (t.x == h.x+1 && t.y == h.y) { t = new Coords(t.x, t.y); }

            else if (t.x == h.x-1 && t.y == h.y+1) { t = new Coords(t.x, t.y); }
            else if (t.x == h.x && t.y == h.y+1) { t = new Coords(t.x, t.y); }
            else if (t.x == h.x+1 && t.y == h.y+1) { t = new Coords(t.x, t.y); }

            moveEachKnot(t, ts);
            h = new Coords(h.x, h.y+1);
        }
        return h;
    }

    private static void moveEachKnot(Coords newPos, Coords[] knots) {
        var tempPos = newPos;
        for (int i = 0; i < knots.length; i++) {
            newPos = tempPos;
            if (knots[i].x == newPos.x && knots[i].y == newPos.y) break;
            if (i < knots.length - 1) {
                // compare newPos, knots[i] and knots[i+1] and create tempPos
                // for adjacent, no move
                if (adjacentOrSame(newPos, knots[i + 1])) tempPos = copy(knots[i + 1]);
                else
                    // for R, L, U, D -> use move2
                    if (isR(knots[i], newPos)) tempPos = move2("R", copy(knots[i]), copy(knots[i + 1]));
                    else if (isL(knots[i], newPos)) tempPos = move2("L", copy(knots[i]), copy(knots[i + 1]));
                    else if (isU(knots[i], newPos)) tempPos = move2("U", copy(knots[i]), copy(knots[i + 1]));
                    else if (isD(knots[i], newPos)) tempPos = move2("D", copy(knots[i]), copy(knots[i + 1]));
                    else
                        // if vertically or horizontally on same line then x+/-1 or y+/-1
                        if (newPos.x == knots[i + 1].x) tempPos = (newPos.y == knots[i + 1].y + 2) ? new Coords(newPos.x, newPos.y - 1) : new Coords(newPos.x, newPos.y + 1);
                        else if (newPos.y == knots[i + 1].y) tempPos = (newPos.x == knots[i + 1].x + 2) ? new Coords(newPos.x - 1, newPos.y) : new Coords(newPos.x + 1, newPos.y);
                        // else, they are on diagonal, so move diagonally accordingly
                        else tempPos = new Coords(knots[i + 1].x + newPos.x - knots[i].x, knots[i + 1].y + newPos.y - knots[i].y);
            }
            knots[i] = newPos;
        }
    }

    private static Coords copy(Coords t) {
        return new Coords(t.x, t.y);
    }

    private static boolean isR(Coords t1, Coords t2) {
        return t1.y == t2.y && t2.x == t1.x + 1;
    }

    private static boolean isL(Coords t1, Coords t2) {
        return t1.y == t2.y && t2.x == t1.x - 1;
    }

    private static boolean isU(Coords t1, Coords t2) {
        return t1.x == t2.x && t2.y == t1.y - 1;
    }

    private static boolean isD(Coords t1, Coords t2) {
        return t1.x == t2.x && t2.y == t1.y + 1;
    }

    private static boolean adjacentOrSame(Coords a, Coords b) {
        return (a.x == b.x && a.y == b.y) ||
               (a.x == b.x && a.y == b.y-1) ||
               (a.x == b.x && a.y == b.y+1) ||
               (a.x == b.x-1 && a.y == b.y) ||
               (a.x == b.x+1 && a.y == b.y) ||
               (a.x == b.x+1 && a.y == b.y+1) ||
               (a.x == b.x+1 && a.y == b.y-1) ||
               (a.x == b.x-1 && a.y == b.y+1) ||
               (a.x == b.x-1 && a.y == b.y-1);
    }

    private static Coords move2(String dir, Coords h, Coords t) {
        if (dir.equals("R")) {
            if (t.x == h.x-1 && t.y == h.y-1) { t = new Coords(t.x+1, t.y+1); }
            else if (t.x == h.x && t.y == h.y-1) { t = new Coords(t.x, t.y); }
            else if (t.x == h.x+1 && t.y == h.y-1) { t = new Coords(t.x, t.y); }

            else if (t.x == h.x-1 && t.y == h.y) { t = new Coords(t.x+1, t.y); }
            else if (t.x == h.x && t.y == h.y) { t = new Coords(t.x, t.y); }
            else if (t.x == h.x+1 && t.y == h.y) { t = new Coords(t.x, t.y); }

            else if (t.x == h.x-1 && t.y == h.y+1) { t = new Coords(t.x+1, t.y-1); }
            else if (t.x == h.x && t.y == h.y+1) { t = new Coords(t.x, t.y); }
            else if (t.x == h.x+1 && t.y == h.y+1) { t = new Coords(t.x, t.y); }

        }
        else if (dir.equals("L")) {
            if (t.x == h.x-1 && t.y == h.y-1) { t = new Coords(t.x, t.y); }
            else if (t.x == h.x && t.y == h.y-1) { t = new Coords(t.x, t.y); }
            else if (t.x == h.x+1 && t.y == h.y-1) { t = new Coords(t.x-1, t.y+1); }

            else if (t.x == h.x-1 && t.y == h.y) { t = new Coords(t.x, t.y); }
            else if (t.x == h.x && t.y == h.y) { t = new Coords(t.x, t.y); }
            else if (t.x == h.x+1 && t.y == h.y) { t = new Coords(t.x-1, t.y); }

            else if (t.x == h.x-1 && t.y == h.y+1) { t = new Coords(t.x, t.y); }
            else if (t.x == h.x && t.y == h.y+1) { t = new Coords(t.x, t.y); }
            else if (t.x == h.x+1 && t.y == h.y+1) { t = new Coords(t.x-1, t.y-1); }

        }
        else if (dir.equals("U")) {
            if (t.x == h.x-1 && t.y == h.y-1) { t = new Coords(t.x, t.y); }
            else if (t.x == h.x && t.y == h.y-1) { t = new Coords(t.x, t.y); }
            else if (t.x == h.x+1 && t.y == h.y-1) { t = new Coords(t.x, t.y); }

            else if (t.x == h.x-1 && t.y == h.y) { t = new Coords(t.x, t.y); }
            else if (t.x == h.x && t.y == h.y) { t = new Coords(t.x, t.y); }
            else if (t.x == h.x+1 && t.y == h.y) { t = new Coords(t.x, t.y); }

            else if (t.x == h.x-1 && t.y == h.y+1) { t = new Coords(t.x+1, t.y-1); }
            else if (t.x == h.x && t.y == h.y+1) { t = new Coords(t.x, t.y-1); }
            else if (t.x == h.x+1 && t.y == h.y+1) { t = new Coords(t.x-1, t.y-1); }

        }
        else if (dir.equals("D")) {
            if (t.x == h.x-1 && t.y == h.y-1) { t = new Coords(t.x+1, t.y+1); }
            else if (t.x == h.x && t.y == h.y-1) { t = new Coords(t.x, t.y+1); }
            else if (t.x == h.x+1 && t.y == h.y-1) { t = new Coords(t.x-1, t.y+1); }

            else if (t.x == h.x-1 && t.y == h.y) { t = new Coords(t.x, t.y); }
            else if (t.x == h.x && t.y == h.y) { t = new Coords(t.x, t.y); }
            else if (t.x == h.x+1 && t.y == h.y) { t = new Coords(t.x, t.y); }

            else if (t.x == h.x-1 && t.y == h.y+1) { t = new Coords(t.x, t.y); }
            else if (t.x == h.x && t.y == h.y+1) { t = new Coords(t.x, t.y); }
            else if (t.x == h.x+1 && t.y == h.y+1) { t = new Coords(t.x, t.y); }

        }
        return t;
    }


    record Coords(int x, int y) { }

//     Output
//     CMZ
//     QPJPLMNNR
//     MCD
//     BQDNWJPVJ

}

package aoc2021;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

import static aoc.Utils.fileToStringArray;

public class Problem18A {

    @AllArgsConstructor(staticName = "of")
    @ToString
    private static class Num {long v; int d;}

    private static void solve(String[] input) {
        List<Num> sum = null;
        for (String s : input) {
            List<Num> cur = toList(s);
            if (sum == null) {
                sum = cur;
            } else {
                sum = add(sum, cur);
            }
        }
        System.out.println(magnitude(sum));
    }

    private static long magnitude(List<Num> l) {
        while (l.size() > 1) {
            for (int i = 0; i < l.size() - 1; i++) {
                if (l.get(i).d == l.get(i+1).d) {
                    Num n1 = l.get(i);
                    Num n2 = l.get(i+1);
                    n1.v = n1.v * 3 + n2.v * 2;
                    n1.d--;
                    l.remove(n2);
                    break;
                }
            }
        }
        return l.get(0).v;
    }

    private static List<Num> add(List<Num> l1, List<Num> l2) {
        for (Num n: l1) {
            n.d ++;
        }
        for (Num n: l2) {
            n.d ++;
        }
        l1.addAll(l2);
        reduce(l1);
        return l1;
    }

    private static void reduce(List<Num> l) {
        while(canExplode(l) || canSplit(l)) {
            while (canExplode(l))
                explode(l);
            if (canSplit(l))
                split(l);
        }
    }

    private static void split(List<Num> l) {
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).v > 9) {
                Num n1 = l.get(i);
                Num n2 = Num.of(n1.v % 2 == 0 ? n1.v / 2 : (n1.v / 2) + 1, n1.d + 1);
                n1.v = l.get(i).v / 2;
                n1.d++;
                l.add(i + 1, n2);
                return;
            }
        }
    }

    private static void explode(List<Num> l) {
        for (int i = 0; i < l.size()-1; i++) {
            if (l.get(i).d >= 5 && l.get(i).d == l.get(i+1).d) {
                Num n1 = l.get(i);
                Num n2 = l.get(i+1);
                if (i-1 >= 0) { l.get(i-1).v += n1.v; }
                if (i+2 < l.size()) { l.get(i+2).v += n2.v; }
                l.remove(n1);
                n2.v = 0;
                n2.d--;
                return;
            }
        }
    }

    private static boolean canSplit(List<Num> l) {
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).v > 9) {
                return true;
            }
        }
        return false;
    }

    private static boolean canExplode(List<Num> l) {
        for (int i = 0; i < l.size()-1; i++) {
            if (l.get(i).d >= 5 && l.get(i).d == l.get(i+1).d) return true;
        }
        return false;
    }

    private static List<Num> toList(String str) {
        List<Num> list = new ArrayList<>();
        int i = 0;
        for (String s: str.split("")) {
            if (s.equals("[")) { i++; }
            else if (s.equals(",")) { }
            else if (s.equals("]")) { i--; }
            else { // digit
                list.add(Num.of(Integer.parseInt(s), i));
            }
        }
        return list;
    }

    public static void main(String[] args) {
        String[] input1 = fileToStringArray(Problem18A.class, "Problem18Input1.txt");
        String[] input2 = fileToStringArray(Problem18A.class, "Problem18Input2.txt");

        solve(input1);
        solve(input2);
    }

}

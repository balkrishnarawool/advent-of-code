package aoc2021;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static aoc.Utils.fileToStringArray;

public class Problem06A {

    private static int countFish80(String s) {
        String[] sa = s.split(",");
        List<Integer> l = new ArrayList<>();
        for (String str: sa) {
            l.add(Integer.parseInt(str));
        }

        for (int j = 0; j < 80; j++) {
            int c = 0;
            for (int i = 0; i < l.size(); i++) {
                if (l.get(i) == 0) {
                    l.set(i, 6);
                    c++;
                } else {
                    l.set(i, l.get(i) - 1);
                }
            }
            for (int i = 0; i < c; i++) {
                l.add(8);
            }
        }
        return l.size();
    }

    public static void main(String[] args) {
        String[] input1 = fileToStringArray(Problem06A.class, "Problem06Input1.txt");

        System.out.println(countFish80(input1[0]));

        System.out.println(countFish256(input1[0]));


//        Output

    }

    private static long countFish256(String s) {
        String[] sa = s.split(",");
        List<Long> l = new ArrayList<>();
        for (String str: sa) {
            l.add(Long.parseLong(str));
        }

        return l.stream().map(Problem06A::countTotalOffsprings).reduce(0L, Long::sum);
    }

    private static Long countTotalOffsprings(Long l) {
        Node n = Node.of(((256-(l+1))/7)+1);
        n.addChildren(256-(l+1));

        return n.countOffSpringsOfChildren() + 1;
    }

    @RequiredArgsConstructor(staticName = "of")
    private static class Node { @NonNull long v; List<Node> children = new ArrayList<>();

        public void addChildren(long d) {
            for (long i = d; i > 0; i-=9) {
                Node t = Node.of((i/7)+1);
                t.addChildren(i);
                children.add(t);
            }
        }

        public long countOffSpringsOfChildren() {
            return children.stream().map(Node::countOffSpringsOfChildren).reduce(0L, Long::sum) + v;
        }
    }
}

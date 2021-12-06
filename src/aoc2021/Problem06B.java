package aoc2021;

import lombok.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static aoc.Utils.fileToStringArray;

public class Problem06B {

    @AllArgsConstructor(staticName = "of")
    @ToString
    private static class Num { int value; long times; }

    private static long countFish(String s, int steps) {
        String[] sa = s.split(",");
        Map<Integer, Integer> lookup = new HashMap<>();
        for (String str: sa) {
            int i = Integer.parseInt(str);
            if (lookup.containsKey(i)) {
                lookup.put(i, lookup.get(i) + 1);
            } else {
                lookup.put(i, 1);
            }
        }
        List<Num> nums = new ArrayList<>();
        for (int i: lookup.keySet()) {
            nums.add(Num.of(i, lookup.get(i)));
        }

        for (int j = 0; j < steps; j++) {
            Num t = null;
            boolean found = false;
            for (Num num: nums) {
                if (num.value == 0) {
                    num.value = 6;
                    t = num;
                } else if (num.value == 7) {
                    if (t != null) {
                        found = true;
                        num.times += t.times;
                    }
                    num.value = 6;
                } else {
                    num.value--;
                }
            }
            if (t != null) {
                nums.add(Num.of(8, t.times));
            }
            if (found) {
                nums.remove(t);
            }
        }
        return nums.stream().map(n -> n.times).reduce(0L, Long::sum);
    }

    public static void main(String[] args) {
        String[] input1 = fileToStringArray(Problem06B.class, "Problem06Input1.txt");
        String[] input2 = fileToStringArray(Problem06B.class, "Problem06Input2.txt");

        System.out.println(countFish(input1[0], 80));
        System.out.println(countFish(input2[0], 80));

        System.out.println(countFish(input1[0], 256));
        System.out.println(countFish(input2[0], 256));

//        Output

    }

}

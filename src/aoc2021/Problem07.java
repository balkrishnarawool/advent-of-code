package aoc2021;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static aoc.Utils.fileToStringArray;

public class Problem07 {

    @Value
    @AllArgsConstructor(staticName = "of")
    private static class Crab { int pos; int count; }

    private static int fuelCount(String s, boolean corrected) {
        String[] sa = s.split(",");
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        Map<Integer, Integer> lookup = new HashMap<>();
        for (String str: sa) {
            int i = Integer.parseInt(str);
            min = Math.min(min, i);
            max = Math.max(max, i);
            if (lookup.containsKey(i)) {
                lookup.put(i, lookup.get(i) + 1);
            } else {
                lookup.put(i, 1);
            }
        }
        List<Crab> crabs = new ArrayList<>();
        for (int i: lookup.keySet()) {
            crabs.add(Crab.of(i, lookup.get(i)));
        }

        int minFuelCount = Integer.MAX_VALUE;
        for (int i = min; i <= max; i++) {
            int fuelCount = 0;
            for (Crab c: crabs) {
                if (corrected) {
                    fuelCount += (sumOfNumbers(Math.abs(c.pos - i)) * c.count);
                } else {
                    fuelCount += (Math.abs(c.pos - i) * c.count);
                }
            }
            minFuelCount = Math.min(minFuelCount, fuelCount);
        }

        return minFuelCount;
    }

    private static int sumOfNumbers(int n) {
        return (n * (n + 1)) / 2;
    }

    public static void main(String[] args) {
        String[] input1 = fileToStringArray(Problem07.class, "Problem07Input1.txt");
        String[] input2 = fileToStringArray(Problem07.class, "Problem07Input2.txt");

        System.out.println(fuelCount(input1[0], false));
        System.out.println(fuelCount(input2[0], false));

        System.out.println(fuelCount(input1[0], true));
        System.out.println(fuelCount(input2[0], true));

//        Output

    }

}

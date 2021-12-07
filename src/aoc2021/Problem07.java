package aoc2021;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.*;
import java.util.stream.IntStream;

import static aoc.Utils.fileToStringArray;
import static aoc.Utils.getMapOfCounts;
import static aoc.Utils.minFromInts;
import static aoc.Utils.maxFromInts;

public class Problem07 {

    @Value
    @AllArgsConstructor(staticName = "of")
    private static class Crab { int pos; int count; }

    private static int fuelCount(String s, boolean corrected) {
        Map<Integer, Integer> lookup = getMapOfCounts(s.split(","));
        int min = minFromInts(lookup.keySet());
        int max = maxFromInts(lookup.keySet());

        List<Crab> crabs = lookup.keySet().stream()
                .reduce(new ArrayList<>(),
                        (l, i) ->  { l.add(Crab.of(i, lookup.get(i))); return l; },
                        (l1, l2) -> { l1.addAll(l2); return l1; }
                );

        return IntStream.range(min, max + 1)
                .map(i -> getTotalFuelCount(crabs, i, corrected))
                .reduce(Math::min)
                .orElse(0);
    }

    private static int getTotalFuelCount(List<Crab> crabs, int i, boolean corrected) {
        return crabs.stream()
                .map(c -> corrected ? sumOfNumbers(Math.abs(c.pos - i)) * c.count : (Math.abs(c.pos - i) * c.count))
                .reduce(Integer::sum)
                .orElse(0);
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

package aoc2021;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static aoc.Utils.fileToStringArray;

public class Problem14A {


    private static int solve(String[] input1) {
        List<String> list = Arrays.stream(input1[0].split("")).collect(Collectors.toList());
        Map<String, String> map = new HashMap<>();
        for (int i = 2; i < input1.length; i++) {
            String[] arr = input1[i].split(" -> ");
            map.put(arr[0], arr[1]);
        }

        for (int i = 0; i < 10; i++) {
            applyRule(list, map);
            print(list);
        }
        Map<String, Integer> sorted = sort(list);
        int max = getMax(sorted);
        int min = getMin(sorted);
        return max - min;
    }

    private static void print(List<String> list) {
        list.forEach(s -> System.out.print(s));
        System.out.println();
    }

    private static Map<String, Integer> sort(List<String> list) {
        Map<String, Integer> map = new HashMap<>();
        list.stream()
                .forEach(s -> {
                    if (!map.containsKey(s)) { map.put(s, 1); }
                    else { map.put(s, map.get(s) + 1); }
                });
        return map;
    }
    private static int getMax(Map<String, Integer> map) {
        int max = Integer.MIN_VALUE;
        for (String s: map.keySet()) {
            if (map.get(s) > max) max = map.get(s);
        }
        return max;
    }
    private static int getMin(Map<String, Integer> map) {
        int min = Integer.MAX_VALUE;
        for (String s: map.keySet()) {
            if (map.get(s) < min) min = map.get(s);
        }
        return min;
    }

    private static void applyRule(List<String> list, Map<String, String> map) {
        int i = 0;
        while (i < list.size()-1) {
            String t = list.get(i)+list.get(i+1);
            String n = map.get(t);
            list.add(i+1, n);
            i += 2;
        }
    }

    public static void main(String[] args) {
        String[] input1 = fileToStringArray(Problem14A.class, "Problem14Input1.txt");
        String[] input2 = fileToStringArray(Problem14A.class, "Problem14Input2.txt");

        System.out.println(solve(input1));
        System.out.println(solve(input2));

    }

}

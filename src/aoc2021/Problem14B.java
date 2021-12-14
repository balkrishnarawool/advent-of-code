package aoc2021;

import java.util.HashMap;
import java.util.Map;

import static aoc.Utils.fileToStringArray;

public class Problem14B {

    private static long solve(String[] input1) {
        String[] a = input1[0].split("");
        String first = a[0];
        String last = a[a.length-1];
        Map<String, Long> chains = new HashMap<>();
        for (int i = 0; i < a.length-1; i++) {
            String s = a[i]+a[i+1];
            if (!chains.containsKey(s)) { chains.put(s, 1L); }
            else { chains.put(s, chains.get(s) + 1); }
        }
        Map<String, String> map = new HashMap<>();
        for (int i = 2; i < input1.length; i++) {
            String[] arr = input1[i].split(" -> ");
            map.put(arr[0], arr[1]);
        }

        print(chains);
        for (int i = 0; i < 40; i++) {
            chains = applyRule(chains, map);
            print(chains);
        }
        Map<String, Long> sorted = sort(chains, first, last);
        long max = getMax(sorted);
        long min = getMin(sorted);
        return max - min;
    }

    private static void print(Map<String, Long> map) {
        map.keySet().forEach(k -> System.out.print(k + " " + map.get(k) +" "));
        System.out.println();
    }

    private static Map<String, Long> sort(Map<String, Long> chains, String first, String last) {
        Map<String, Long> map = new HashMap<>();
        chains.keySet()
                .forEach(k -> {
                    String s1 = k.charAt(0) +"";
                    if (!map.containsKey(s1)) { map.put(s1, chains.get(k)); }
                    else { map.put(s1, map.get(s1) + chains.get(k)); }

                    String s2 = k.charAt(1) +"";
                    if (!map.containsKey(s2)) { map.put(s2, chains.get(k)); }
                    else { map.put(s2, map.get(s2) + chains.get(k)); }
                });
        map.keySet().forEach(k -> {
            map.put(k, map.get(k)/2);
            if (k.equals(first) || k.equals(last)) {
                map.put(k, map.get(k) + 1);
            }
        });
        return map;
    }

    private static long getMax(Map<String, Long> map) {
        long max = Long.MIN_VALUE;
        for (String s: map.keySet()) {
            if (map.get(s) > max) max = map.get(s);
        }
        return max;
    }
    private static long getMin(Map<String, Long> map) {
        long min = Long.MAX_VALUE;
        for (String s: map.keySet()) {
            if (map.get(s) < min) min = map.get(s);
        }
        return min;
    }

    private static Map<String, Long> applyRule(Map<String, Long> chains, Map<String, String> map) {
        Map<String, Long> chains2 = new HashMap<>();
        chains.keySet().forEach(k -> {
            String n = map.get(k);

            String s1 = k.charAt(0) + n;
            if (!chains2.containsKey(s1)) { chains2.put(s1, chains.get(k)); }
            else { chains2.put(s1, chains2.get(s1) + chains.get(k)); }

            String s2 = n + k.charAt(1);
            if (!chains2.containsKey(s2)) { chains2.put(s2, chains.get(k)); }
            else { chains2.put(s2, chains2.get(s2) + chains.get(k)); }
        });

        return chains2;
    }

    public static void main(String[] args) {
        String[] input1 = fileToStringArray(Problem14B.class, "Problem14Input1.txt");
        String[] input2 = fileToStringArray(Problem14B.class, "Problem14Input2.txt");

        System.out.println(solve(input1));
        System.out.println(solve(input2));

    }

}

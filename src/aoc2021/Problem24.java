package aoc2021;

import aoc.Utils;

import java.util.*;
import java.util.stream.Collectors;

public class Problem24 {

    static long solve1(List<String> data) {
        Map<Character, List<Integer>> variables = getPatternVariables(data);
        return dfs(variables, 9, 1, 0, 0, 0);
    }

    static long solve2(List<String> data) {
        Map<Character, List<Integer>> variables = getPatternVariables(data);
        return dfs(variables, 1, 9, 0, 0, 0);
    }

    private static Map<Character, List<Integer>> getPatternVariables(List<String> data) {
        Map<Character, List<Integer>> variables = new HashMap<>();
        variables.put('x', new ArrayList<>());
        variables.put('y', new ArrayList<>());
        variables.put('z', new ArrayList<>());

        for (int i = 0; i < 14; i++) {
            variables.get('x').add(Integer.parseInt(data.get(i * 18 + 5).split(" ")[2]));
            variables.get('y').add(Integer.parseInt(data.get(i * 18 + 15).split(" ")[2]));
            variables.get('z').add(Integer.parseInt(data.get(i * 18 + 4).split(" ")[2]));
        }

        return variables;
    }

    private static long calculateNextZ(int px, int py, int pz, int digit, long z) {
        if (((z % 26) + px) == digit) {
            return z / pz;
        } else {
            return z / pz * 26 + py + digit;
        }
    }

    private static long dfs(Map<Character, List<Integer>> variables, int firstDigit, int lastDigit, int digitIndex, long prevZ, long number) {
        if (digitIndex == 14) {
            return (prevZ == 0) ? number : -1;
        }

        int px = variables.get('x').get(digitIndex);
        int py = variables.get('y').get(digitIndex);
        int pz = variables.get('z').get(digitIndex);
        int digit = firstDigit;

        if (pz == 1) {
            while (digit >= 1 && digit <= 9) {
                long z = calculateNextZ(px, py, pz, digit, prevZ);
                long res = dfs(variables, firstDigit, lastDigit, digitIndex + 1, z, number * 10 + digit);

                if (res > -1) {
                    return res;
                }

                digit += Math.signum(lastDigit - firstDigit);
            }
        } else {
            while (digit >= 1 && digit <= 9) {
                if (((prevZ % 26) + px) == digit) {
                    long z = calculateNextZ(px, py, pz, digit, prevZ);
                    long res = dfs(variables, firstDigit, lastDigit, digitIndex + 1, z, number * 10 + digit);

                    if (res > -1) {
                        return res;
                    }
                }

                digit += Math.signum(lastDigit - firstDigit);
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        String[] input1 = Utils.fileToStringArray(Problem23.class, "Problem24Input1.txt");

        System.out.println(solve1(Arrays.stream(input1).collect(Collectors.toList())));
        System.out.println(solve2(Arrays.stream(input1).collect(Collectors.toList())));

    }
}
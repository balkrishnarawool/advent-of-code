package aoc;

import aoc2021.Problem07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Utils {

    public static int[] stringToIntArray(String[] strs) {
        int[] ints = new int[strs.length];
        for (int i = 0; i < strs.length; i++) {
            ints[i] = Integer.parseInt(strs[i]);
        }
        return ints;
    }

    public static String[] fileToStringArray(Class clazz, String file) {
        try {
            InputStream inputStream = clazz.getResourceAsStream(file);
            try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
                List<String> list = new ArrayList<>();
                String line;
                while ((line = br.readLine()) != null) {
                    list.add(line);
                }
                return list.toArray(new String[]{});
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static char[][] stringArrayToChar2DArray(String[] strs) {
        return Arrays.stream(strs)
                .map(String::toCharArray)
                .toArray(i -> new char[i][]);
    }

    public static char[][] copyOf(char[][] arr) {
        char[][] copy = new char[arr.length][arr[0].length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                copy[i][j] = arr[i][j];
            }
        }
        return copy;
    }

    public static Map<Integer, Integer> getMapOfCounts(String[] strs) {
        return Arrays.stream(strs)
                .map(Integer::parseInt)
                .reduce(new HashMap<>(),
                        (l, i) -> { Integer ignore =
                                l.containsKey(i)
                                        ? l.put(i, l.get(i) + 1)
                                        : l.put(i, 1);
                            return l;
                        },
                        Utils::parallelStreamsNotAllowed
        );
    }

    private static HashMap<Integer, Integer> parallelStreamsNotAllowed(HashMap<Integer, Integer> h1, HashMap<Integer, Integer> h2) {
        throw new RuntimeException("These calculations are not to be done in parallel. If you're using stream.parallel(), remove it!");
    }

    public static int minFromInts(Collection<Integer> ints) {
        return ints.stream().min(Comparator.naturalOrder()).orElse(Integer.MAX_VALUE);
    }

    public static int maxFromInts(Collection<Integer> ints) {
        return ints.stream().max(Comparator.naturalOrder()).orElse(Integer.MIN_VALUE);
    }

    public static List<String> readFile(String path) throws IOException {
        try (var lines = Files.lines(Path.of(path))) {
            return lines.toList();
        }
    }

}

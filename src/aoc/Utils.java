package aoc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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
}

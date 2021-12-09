package aoc2021;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static aoc.Utils.*;

public class Problem08A {

    private static int countUniqueDigits(String[] strs) {
        int c = 0;
        for (String str: strs) {
            String[] arr = str.split("\\|")[1].split(" ");
            for (String s: arr) {
                if (s.length() == 2 || s.length() == 4 || s.length() == 3 || s.length() == 7) {
                    c ++;
                }
            }
        }
        return c;
    }

    public static void main(String[] args) {
        String[] input1 = fileToStringArray(Problem08A.class, "Problem08Input1.txt");
        String[] input2 = fileToStringArray(Problem08A.class, "Problem08Input2.txt");

        System.out.println(countUniqueDigits(input1));
        System.out.println(countUniqueDigits(input2));

//        Output

    }
}

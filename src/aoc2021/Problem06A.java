package aoc2021;

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

//        Output

    }

}

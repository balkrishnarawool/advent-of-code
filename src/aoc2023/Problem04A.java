package aoc2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Problem04A {
    public static void main(String[] args) {
//        var str = Problem04Input.INPUT01; //13
        var str = Problem04Input.INPUT02; //26218
//        var str = Problem04Input.INPUT03;

        System.out.println(str.lines()
                .map(Problem04A::calcPoints)
                .reduce(Integer::sum)
        );
    }

    private static int calcPoints(String s) {
        var l1 = Arrays.asList(s.split(":")[1].split("\\|")[0].trim().split("\\s+"));
        var l2 = Arrays.asList(s.split(":")[1].split("\\|")[1].trim().split("\\s+"));
        return (int) Math.pow(2, containsCount(l2, l1) - 1);
    }

    private static <T> long containsCount(List<T> list1, List<T> list2) {
        return list2.stream().filter(list1::contains).count();
    }
}

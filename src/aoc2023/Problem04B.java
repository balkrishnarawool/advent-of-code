package aoc2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Problem04B {
    public static void main(String[] args) {
//        var str = Problem04Input.INPUT01; //30
        var str = Problem04Input.INPUT02; //9997537
//        var str = Problem04Input.INPUT03;

        var map = str.lines()
                .map(Problem04B::parse)
                .collect(Collectors.toMap(c -> c.id, c -> c));

        var sum = map.values().stream()
                .map(c -> c.updateWins(map))
                .reduce(Integer::sum);

        System.out.println(sum);
    }

    record Card<T>(int id, AtomicInteger count, List<T> list1, List<T> list2) {
        private int updateWins(Map<Integer, Card<T>> map) {
            var num = containsCount(list1, list2);
            for (int i = 0; i < num; i++) {
                map.get(id+1+i).count.set(map.get(id+1+i).count.get() + count.get());
            }
            return count.get();
        }
    }

    private static <T> long containsCount(List<T> list1, List<T> list2) {
        return list2.stream().filter(list1::contains).count();
    }

    private static Card<String> parse(String s) {
        var id = Integer.parseInt(s.split(":")[0].split("\\s+")[1]);
        var l1 = Arrays.asList(s.split(":")[1].split("\\|")[0].trim().split("\\s+"));
        var l2 = Arrays.asList(s.split(":")[1].split("\\|")[1].trim().split("\\s+"));
        return new Card<>(id, new AtomicInteger(1), l2, l1);
    }
}

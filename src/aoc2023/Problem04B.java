package aoc2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Problem04B {
    static class IntBox {
        int value;
        IntBox(int value) {
            this.value = value;
        }
    }
    record Card<T>(int id, IntBox count, List<T> list1, List<T> list2) { }

    public static void main(String[] args) {
//        var str = Problem04Input.INPUT01; //30
        var str = Problem04Input.INPUT02; //9997537
//        var str = Problem04Input.INPUT03;

        var cards = str.lines()
                .map(Problem04B::parse)
                .collect(Collectors.toMap(c -> c.id, c -> c));
        System.out.println(cards.values().stream()
                .map(c -> Problem04B.updateWins(c, cards))
                .reduce(Integer::sum));
    }

    private static <T> int updateWins(Card<T> card, Map<Integer, Card<T>> cards) {
        var num = card.list1.stream().filter(card.list2::contains).count();
        for (int i = 0; i < num; i++) {
            cards.get(card.id+1+i).count.value = cards.get(card.id+1+i).count.value + card.count.value;
        }
        return card.count.value;
    }

    private static Card<String> parse(String s) {
        var id = Integer.parseInt(s.split(":")[0].split("\\s+")[1]);
        var l1 = Arrays.asList(s.split(":")[1].split("\\|")[0].trim().split("\\s+"));
        var l2 = Arrays.asList(s.split(":")[1].split("\\|")[1].trim().split("\\s+"));
        return new Card<>(id, new IntBox(1), l2, l1);
    }
}

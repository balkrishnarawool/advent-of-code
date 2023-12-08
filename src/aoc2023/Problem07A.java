package aoc2023;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Problem07A {
    public static void main(String[] args) {
//        var str = Problem07Input.INPUT01; //6440
        var str = Problem07Input.INPUT02; //251545216
//        var str = Problem07Input.INPUT03;

        var hands = new java.util.ArrayList<>(
                str.lines()
                        .map(Problem07A::parse)
                        .toList());
        hands.sort(new HandComparator());
        System.out.println(winnings(hands));
    }

    record Hand(String cards, long bid) {
        enum Type {
            FIVE_OF_A_KIND,
            FOUR_OF_A_KIND,
            FULL_HOUSE,
            THREE_OF_A_KIND,
            TWO_PAIR,
            ONE_PAIR,
            HIGH_CARD
        }

        Type getType() {
            return isFiveOfAKind() ? Type.FIVE_OF_A_KIND
                    : isFourOfAKind() ? Type.FOUR_OF_A_KIND
                    : isFullHouse() ? Type.FULL_HOUSE
                    : isThreeOfAKind() ? Type.THREE_OF_A_KIND
                    : isTwoPair() ? Type.TWO_PAIR
                    : isOnePair() ? Type.ONE_PAIR
                    : Type.HIGH_CARD;
        }

        private boolean isFiveOfAKind() {
            var map = cardsToMap(cards);
            return map.values().stream().anyMatch(v -> v == 5);
        }

        private boolean isFourOfAKind() {
            var map = cardsToMap(cards);
            return map.values().stream().anyMatch(v -> v == 4);
        }

        private boolean isFullHouse() {
            var map = cardsToMap(cards);
            return map.keySet().size() == 2 && map.values().stream().anyMatch(v -> v == 3);
        }

        private boolean isThreeOfAKind() {
            var map = cardsToMap(cards);
            return map.keySet().size() > 2 && map.values().stream().anyMatch(v -> v == 3);
        }

        private boolean isTwoPair() {
            var map = cardsToMap(cards);
            return map.keySet().size() == 3 && map.values().stream().anyMatch(v -> v == 2);
        }

        private boolean isOnePair() {
            var map = cardsToMap(cards);
            return map.keySet().size() == 4 && map.values().stream().anyMatch(v -> v == 2);
        }

        private Map<Integer, Integer> cardsToMap(String cards) {
            var map = new HashMap<Integer, Integer>();
            cards.chars().forEach(c -> {if (map.containsKey(c)) map.put(c, map.get(c)+1); else map.put(c, 1); } );
            return map;
        }
    }

    private static class HandComparator implements Comparator<Hand> {
        private static final Map<Hand.Type, Integer> TYPE_ORDER = Map.of(
                Hand.Type.HIGH_CARD, 1,
                Hand.Type.ONE_PAIR, 2,
                Hand.Type.TWO_PAIR, 3,
                Hand.Type.THREE_OF_A_KIND, 4,
                Hand.Type.FULL_HOUSE, 5,
                Hand.Type.FOUR_OF_A_KIND, 6,
                Hand.Type.FIVE_OF_A_KIND, 7);

        public int compare(Hand h1, Hand h2) {
            var t1 = TYPE_ORDER.get(h1.getType());
            var t2 = TYPE_ORDER.get(h2.getType());
            return t1 > t2 ? 1 : (t1 < t2 ? -1 : compareCards(h1, h2));
        }

        private int compareCards(Hand h1, Hand h2) {
            for (int i = 0; i < 5; i++) {
                if (h1.cards.charAt(i) != h2.cards.charAt(i)) return higher(h1.cards.charAt(i), h2.cards.charAt(i));
            }
            throw new RuntimeException("Both hands same: "+h1 +" "+h2);
        }

        private static final String SORT_ORDER = "23456789TJQKA";
        private int higher(char c1, char c2) {
            return SORT_ORDER.indexOf(c1) > SORT_ORDER.indexOf(c2) ? 1 : -1;
        }
    }

    private static Hand parse(String s) {
        var sa = s.split("\\s+");
        return new Hand(sa[0], Long.parseLong(sa[1]));
    }

    private static long winnings(ArrayList<Hand> hands) {
        var w = 0L;
        for (int i = 0; i < hands.size(); i++) {
            w += (hands.get(i).bid * (i+1));
        }
        return w;
    }
}

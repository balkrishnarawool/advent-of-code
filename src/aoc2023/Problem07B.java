package aoc2023;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Problem07B {
    public static void main(String[] args) {
//        var str = Problem07Input.INPUT01; // 5905
//        var str = Problem07Input.INPUT02; // 250384185
        var str = Problem07Input.INPUT03;

        var hands = new ArrayList<>(str.lines()
                .map(Problem07B::parse)
                .toList());
//        hands.sort(new HandComparator());
        sort(hands);
        System.out.println(winnings(hands));
    }

    private static void sort(ArrayList<Hand> hands) {
        var comparator = new HandComparator();
        for (int i = 0; i < hands.size(); i++) {
            for (int j = 0; j < hands.size()-1-i; j++) {
                if (comparator.compare(hands.get(j), hands.get(j+1)) > 0) { hands.add(j+2, hands.get(j)); hands.remove(j); }
            }
        }
    }

    private static long winnings(ArrayList<Hand> hands) {
        var w = 0L;
        for (int i = 0; i < hands.size(); i++) {
            w += (hands.get(i).bid * (i+1));
        }
        return w;
    }

    record Hand(String cards, long bid) {
        public boolean isFiveOfAKind() {
            var map = cardsToMap(cards);
            return map.values().stream().anyMatch(v -> v == 5);
        }

        public boolean isFourOfAKind() {
            var map = cardsToMap(cards);
            return map.values().stream().anyMatch(v -> v == 4);
        }

        public boolean isFullHouse() {
            var map = cardsToMap(cards);
            return map.keySet().size() == 2 && map.values().stream().anyMatch(v -> v == 3);
        }

        public boolean isThreeOfAKind() {
            var map = cardsToMap(cards);
            return map.keySet().size() > 2 && map.values().stream().anyMatch(v -> v == 3);
        }

        public boolean isTwoPair() {
            var map = cardsToMap(cards);
            return map.keySet().size() == 3 && map.values().stream().anyMatch(v -> v == 2);
        }

        public boolean isOnePair() {
            var map = cardsToMap(cards);
            return map.keySet().size() == 4 && map.values().stream().anyMatch(v -> v == 2);
        }

        private Map<Integer, Integer> cardsToMap(String cards) {
            var map = new HashMap<Integer, Integer>();
            cards.chars().forEach(c -> {if (map.containsKey(c)) map.put(c, map.get(c)+1); else map.put(c, 1); } );
            var countJ = map.containsKey(74) ? map.remove(74) : 0; // 74 == 'J'
            int max = 0, maxK = 0;
            for (var k: map.keySet()) { max = Math.max(max, map.get(k)); maxK = k;}
            map.put(maxK, max + countJ);
            return map;
        }
    }

    private static class HandComparator implements Comparator<Hand> {

        @Override
        public int compare(Hand h1, Hand h2) {
            if (h1.isFiveOfAKind()) {
                if (h2.isFiveOfAKind()) { return compareCards(h1, h2); }
                else return 1;
            } else if (h1.isFourOfAKind()) {
                    if (h2.isFiveOfAKind()) { return -1; }
                    else if (h2.isFourOfAKind()) { return compareCards(h1, h2); }
                    else return 1;
            } else if (h1.isFullHouse()) {
                    if (h2.isFiveOfAKind() || h2.isFourOfAKind()) { return -1; }
                    else if (h2.isFullHouse()) { return compareCards(h1, h2); }
                    else return 1;
            } else if (h1.isThreeOfAKind()) {
                if (h2.isFiveOfAKind() || h2.isFourOfAKind() || h2.isFullHouse()) { return -1; }
                else if (h2.isThreeOfAKind()) { return compareCards(h1, h2); }
                else return 1;
            } else if (h1.isTwoPair()) {
                if (h2.isFiveOfAKind() || h2.isFourOfAKind() || h2.isFullHouse() || h2.isThreeOfAKind()) { return -1; }
                else if (h2.isTwoPair()) { return compareCards(h1, h2); }
                else return 1;
            } else if (h1.isOnePair()) {
                if (h2.isFiveOfAKind() || h2.isFourOfAKind() || h2.isFullHouse() || h2.isThreeOfAKind() || h2.isTwoPair()) { return -1; }
                else if (h2.isOnePair()) { return compareCards(h1, h2); }
                else return 1;
            } else
                if (h2.isFiveOfAKind() || h2.isFourOfAKind() || h2.isFullHouse() || h2.isThreeOfAKind() || h2.isTwoPair() || h2.isOnePair()) { return -1; }
                return compareCards(h1, h2);
        }

        private int compareCards(Hand h1, Hand h2) {
            for (int i = 0; i < 5; i++) {
                if (h1.cards.charAt(i) != h2.cards.charAt(i)) return higher(h1.cards.charAt(i), h2.cards.charAt(i));
            }
            throw new RuntimeException("Both hands same: "+h1 +" "+h2);
        }

        private int higher(char c1, char c2) {
            if (c1 == 'A') return 1;
            else if (c1 == 'K') return (c2 == 'A') ? -1 : 1;
            else if (c1 == 'Q') return (c2 == 'A' || c2 == 'K') ? -1 : 1;
            else if (c1 == 'T') return (c2 == 'A' || c2 == 'K' || c2 == 'Q' ) ? -1 : 1;
            else if (c1 == '9') return (c2 == 'A' || c2 == 'K' || c2 == 'Q'  || c2 == 'T') ? -1 : 1;
            else if (c1 == '8') return (c2 == 'A' || c2 == 'K' || c2 == 'Q'  || c2 == 'T' || c2 == '9') ? -1 : 1;
            else if (c1 == '7') return (c2 == 'A' || c2 == 'K' || c2 == 'Q'  || c2 == 'T' || c2 == '9' || c2 == '8') ? -1 : 1;
            else if (c1 == '6') return (c2 == 'A' || c2 == 'K' || c2 == 'Q'  || c2 == 'T' || c2 == '9' || c2 == '8' || c2 == '7') ? -1 : 1;
            else if (c1 == '5') return (c2 == 'A' || c2 == 'K' || c2 == 'Q'  || c2 == 'T' || c2 == '9' || c2 == '8' || c2 == '7' || c2 == '6') ? -1 : 1;
            else if (c1 == '4') return (c2 == 'A' || c2 == 'K' || c2 == 'Q'  || c2 == 'T' || c2 == '9' || c2 == '8' || c2 == '7' || c2 == '6' || c2 == '5') ? -1 : 1;
            else if (c1 == '3') return (c2 == 'A' || c2 == 'K' || c2 == 'Q'  || c2 == 'T' || c2 == '9' || c2 == '8' || c2 == '7' || c2 == '6' || c2 == '5' || c2 == '4') ? -1 : 1;
            else if (c1 == '2') return (c2 == 'A' || c2 == 'K' || c2 == 'Q'  || c2 == 'T' || c2 == '9' || c2 == '8' || c2 == '7' || c2 == '6' || c2 == '5' || c2 == '4' || c2 == '3') ? -1 : 1;
            else if (c1 == 'J') return (c2 == 'A' || c2 == 'K' || c2 == 'Q'  || c2 == 'T' || c2 == '9' || c2 == '8' || c2 == '7' || c2 == '6' || c2 == '5' || c2 == '4' || c2 == '3' || c2 == '2') ? -1 : 1;
            throw new RuntimeException("Illegal cards: "+c1+" "+c2);
        }
    }

    private static Hand parse(String s) {
        var sa = s.split("\\s+");
        return new Hand(sa[0], Long.parseLong(sa[1]));
    }
}

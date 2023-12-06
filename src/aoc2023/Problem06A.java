package aoc2023;

import java.util.ArrayList;
import java.util.List;

public class Problem06A {
    public static void main(String[] args) {
//        var str = Problem06Input.INPUT01; //288
        var str = Problem06Input.INPUT02; //1108800
//        var str = Problem06Input.INPUT03;

        var races = parse(str.split("\n"));
        var count = races.stream()
                .map(Race::countWins)
                .reduce((a, b) -> a * b);
        System.out.println(count);
    }

    record Race (int time, int dist) {
        public int countWins() {
            int count = 0;
            for (int i = 1; i < time; i++) { //i = speed
                int totalDist = i * (time-i);
                if (totalDist > dist) count++;
            }
            return count;
        }
    }

    private static List<Race> parse(String[] sa) {
        var races = new ArrayList<Race>();
        var sa0 = sa[0].split("\\s+");
        var sa1 = sa[1].split("\\s+");
        for (int i = 1; i < sa0.length; i++) {
            races.add(new Race(Integer.parseInt(sa0[i]), Integer.parseInt(sa1[i])));
        }
        return races;
    }
}

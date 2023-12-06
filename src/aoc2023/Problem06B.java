package aoc2023;

import java.util.ArrayList;
import java.util.List;

public class Problem06B {
    public static void main(String[] args) {
//        var str = Problem06Input.INPUT01; //71503
        var str = Problem06Input.INPUT02; //36919753
//        var str = Problem06Input.INPUT03;

        var race = parse(str.split("\n"));
        System.out.println(race.countWins());
    }

    record Race (long time, long dist) {
        public long countWins() {
            long count = 0;
            for (long i = 1; i < time; i++) { //i = speed
                long totalDist = i * (time-i);
                if (totalDist > dist) count++;
            }
            return count;
        }
    }

    private static Race parse(String[] sa) {
        var time = sa[0].split(":")[1].trim().replaceAll("\\s+", "");
        var dist = sa[1].split(":")[1].trim().replaceAll("\\s+", "");
        return new Race(Long.parseLong(time), Long.parseLong(dist));
    }
}

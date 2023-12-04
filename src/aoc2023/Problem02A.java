package aoc2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Problem02A {
    public static void main(String[] args) {
//        var str = Problem02Input.INPUT01; //8
        var str = Problem02Input.INPUT02; //2369

        int redAvailable = 12, greenAvailable = 13, blueAvailable = 14;
        var sumOfPossibleGameIds = str.lines()
                .map(Problem02A::parseGame)
                .filter(g -> g.canPlay(redAvailable, blueAvailable, greenAvailable))
                .map(g -> g.id)
                .reduce(Integer::sum)
                .orElse(0);
        System.out.println(sumOfPossibleGameIds);
    }

    record Draw(int blue, int red, int green) {
        public boolean unplayable(int redAvailable, int blueAvailable, int greenAvailable) {
            return red > redAvailable || blue > blueAvailable || green > greenAvailable;
        }
    }

    record Game(int id, List<Draw> draws) {
        private boolean canPlay(int redAvailable, int blueAvailable, int greenAvailable) {
            return draws.stream()
                    .noneMatch(draw -> draw.unplayable(redAvailable, blueAvailable, greenAvailable));
        }
    }

    private static Game parseGame(String s) {
        var sa = s.split(":");
        int id = Integer.parseInt(sa[0].split("\\s")[1]);
        var sa1 = sa[1].split(";");
        var list = new ArrayList<Draw>();
        for (String string : sa1) {
            var sa2 = string.split("\\s");
            int red = 0, green = 0, blue = 0;
            for (int k = 0; k < sa2.length / 2; k++) {
                if (sa2[2 * k + 2].startsWith("red")) {
                    red = Integer.parseInt(sa2[2 * k + 1]);
                }
                if (sa2[2 * k + 2].startsWith("green")) {
                    green = Integer.parseInt(sa2[2 * k + 1]);
                }
                if (sa2[2 * k + 2].startsWith("blue")) {
                    blue = Integer.parseInt(sa2[2 * k + 1]);
                }
            }
            list.add(new Draw(blue, red, green));
        }
        return new Game(id, list);
    }
}

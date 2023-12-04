package aoc2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Problem02B {
    public static void main(String[] args) {
//        var str = Problem02Input.INPUT01; //2286
        var str = Problem02Input.INPUT02; //66363
        var powerOfSets = str.lines()
                .map(Problem02B::parseGame)
                .map(Game::powerOfSets)
                .reduce(Integer::sum)
                .orElse(0);
        System.out.println(powerOfSets);
    }

    record Draw(int blue, int red, int green) { }
    record Game(int id, List<Draw> draws) {
        private int powerOfSets() {
            var maxRed = 0;
            var maxGreen = 0;
            var maxBlue = 0;
            for (var draw : draws) {
                maxRed = Math.max(maxRed, draw.red);
                maxBlue = Math.max(maxBlue, draw.blue);
                maxGreen = Math.max(maxGreen, draw.green);
            }
            return maxRed * maxBlue * maxGreen;
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

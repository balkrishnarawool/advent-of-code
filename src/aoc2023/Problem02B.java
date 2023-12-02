package aoc2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem02B {
    public static void main(String[] args) {
//        var str = Problem02Input.INPUT01;
        var str = Problem02Input.INPUT02;
        var games = parseGames(str);
        System.out.println(games);
        System.out.println(powerOfSets(games));
    }
    static class IntBox {
        int value;
        IntBox(int value) {
            this.value = value;
        }
    }

    private static long powerOfSets(Map<Integer, Game> games) {
        var sum = new IntBox(0);
        games.forEach((i, game) -> {
            var maxRed = new IntBox(0);
            var maxGreen = new IntBox(0);
            var maxBlue = new IntBox(0);
            for (var draw: game.draws) {
                if (draw.red > maxRed.value) maxRed.value = draw.red;
                if (draw.blue > maxBlue.value) maxBlue.value = draw.blue;
                if (draw.green > maxGreen.value) maxGreen.value = draw.green;
            }
            sum.value += (maxRed.value * maxBlue.value * maxGreen.value);
        });
        return sum.value;
    }

    record Draw(int blue, int red, int green) { }
    record Game(List<Draw> draws) { }

    private static Map<Integer, Game> parseGames(String str) {
        var map = new HashMap<Integer, Game>();
        Arrays.stream(str.split("\n")).forEach(
                s -> {
                    var sa = s.split(":");
                    int i = Integer.parseInt(sa[0].split(" ")[1]);
                    var sa1 = sa[1].split(";");
                    var list = new ArrayList<Draw>();
                    for (int j = 0; j < sa1.length; j++) {
                        var sa2 = sa1[j].split(" ");
                        int red = 0, green = 0, blue = 0;
                        for (int k = 0; k < sa2.length / 2; k++) {
//                            System.out.println(sa2[2*k+1]+" "+sa2[2*k+2]);
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
                    map.put(i, new Game(list));
                }
        );
        return map;
    }
}

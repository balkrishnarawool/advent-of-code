package aoc2023;

import java.util.ArrayList;
import java.util.List;

public class Problem05A {
    public static void main(String[] args) {
//        var str = Problem05Input.INPUT01; //35
        var str = Problem05Input.INPUT02; //196167384
//        var str = Problem05Input.INPUT03;

        var sa = str.split("\n");
        var seeds = parseSeeds(sa);
        var maps = parseMaps(sa);

        var lowestLocation = seeds.stream()
                .map(s -> location(s, maps))
                .reduce(Long::min);

        System.out.println(lowestLocation);
    }

    private static long location(long seed, List<List<Group>> maps) {
        long num = seed;
        for (var m: maps) {
            num = getNumber(num, m);
        }
        return num;
    }

    private static long getNumber(long num, List<Group> list) {
        for (var g: list) {
            if (g.inRange(num)) return g.destination(num);
        }
        return num;
    }

    record Group(long dest, long source, long range) {
        public long destination(long num) {
            return dest + (num-source);
        }

        public boolean inRange(long num) {
            return num >= source && num <= (source+range);
        }
    }

    private static List<Long> parseSeeds(String[] sa) {
        var seeds = new ArrayList<Long>();
        var sa2 = sa[0].split("\\s");
        for (int i = 1; i < sa2.length; i++) {
            seeds.add(Long.parseLong(sa2[i]));
        }
        return seeds;
    }

    private static List<List<Group>> parseMaps(String[] sa) {
        var i = 0;
        while (!sa[i].isEmpty()) { i++; } // Ignore first group -> seeds
        i++;

        var maps = new ArrayList<List<Group>>();
        for (int j = 0; j < 7; j++) {
            var t = i;
            while (i < sa.length && !sa[i].isEmpty()) { i++; }
            maps.add(parseGroups(sa, t, i));
            i++;
        }

        return maps;
    }

    private static List<Group> parseGroups(String[] sa, int from, int to) {
        var list = new ArrayList<Group>();
        for (int i = from+1; i < to; i++) {
            var sa2 = sa[i].split("\\s");
            list.add(new Group(Long.parseLong(sa2[0]), Long.parseLong(sa2[1]), Long.parseLong(sa2[2])-1));
        }
        return list;
    }
}

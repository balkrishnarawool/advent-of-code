package aoc2023;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Problem05B {
    public static void main(String[] args) {
//        var str = Problem05Input.INPUT01; //46
        var str = Problem05Input.INPUT02; //125742456
//        var str = Problem05Input.INPUT03;

        var sa = str.split("\n");
        var seeds = parseSeeds(sa);
        var maps = parseMaps(sa);

        var ranges = new HashSet<>(seeds);
        for (var groups: maps) {
            var tempRanges = new HashSet<Range>();
            for (var range: ranges) {
                tempRanges.addAll(getDestinationRanges(new ArrayList<>(List.of(range)), groups));
            }
            ranges = tempRanges;
        }

        var min = ranges.stream()
                .map(r -> r.from)
                .reduce(Long::min);

        System.out.println(min);
    }

    private static List<Range> getDestinationRanges(List<Range> input, List<Group> groups) {
        var output = new ArrayList<Range>();
        while (!input.isEmpty()) {
            var range = input.remove(0);
            var added = false;
            for (var group : groups) {
                if (group.inRange(range.from) && group.inRange(range.to)) {
                    output.add(new Range(group.destination(range.from), group.destination(range.to)));
                    added = true;
                    break;
                } else if (!group.inRange(range.from) && group.inRange(range.to)) {
                    input.add(new Range(range.from, group.source-1));
                    output.add(new Range(group.dest, group.destination(range.to)));
                    added = true;
                    break;
                } else if (group.inRange(range.from) && !group.inRange(range.to)) {
                    output.add(new Range(group.destination(range.from), group.destination(group.source+group.range)));
                    input.add(new Range(group.source+group.range+1, range.to));
                    added = true;
                    break;
                } else if (!group.inRange(range.from) && !group.inRange(range.to)) {
                    if (range.from < group.source && range.to > group.source + group.range) {
                        input.add(new Range(range.from, group.source-1));
                        output.add(new Range(group.dest, group.destination(group.source+group.range)));
                        input.add(new Range(group.source+group.range+1, range.to));
                        added = true;
                        break;
                    }
                }
            }
            if (!added) { output.add(range);}
        }
        return output;
    }

    record Group(long dest, long source, long range) {
        public long destination(long num) {
            return dest + (num-source);
        }
        public boolean inRange(long num) {
            return num >= source && num <= (source+range);
        }
    }
    record Range(long from, long to) { }

    private static List<Range> parseSeeds(String[] sa) {
        var seeds = new ArrayList<Range>();
        var sa2 = sa[0].split("\\s");
        for (int i = 0; i < sa2.length/2; i++) {
            seeds.add(new Range(Long.parseLong(sa2[2*i+1]), Long.parseLong(sa2[2*i+1]) + Long.parseLong(sa2[2*i+2]) - 1));
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

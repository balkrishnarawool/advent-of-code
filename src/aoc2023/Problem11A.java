package aoc2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem11A {
    public static void main(String[] args) {
//        var str = Problem11Input.INPUT01; //374
        var str = Problem11Input.INPUT02; //9599070
//        var str = Problem11Input.INPUT03;

        var map = parse(str);
        map = expand(map);
        var galaxies = getGalaxyLocations(map);
        System.out.println(calculateSumOfDistances(galaxies));
    }

    private static int calculateSumOfDistances(List<Point> galaxies) {
        var sum = 0;
        for (int i = 0; i < galaxies.size(); i++) {
            for (int j = i+1; j < galaxies.size(); j++) {
                var from = galaxies.get(i);
                var to = galaxies.get(j);
                sum += (Math.abs(to.x - from.x) + Math.abs(to.y - from.y));
            }
        }
        return sum;
    }

    private static List<Point> getGalaxyLocations(char[][] map) {
        var galaxies = new ArrayList<Point>();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == '#') galaxies.add(new Point(j, i));
            }
        }
        return galaxies;
    }

    private static char[][] expand(char[][] map) {
        var rows = getEmptyRows(map);
        var columns= getEmptyColumns(map);
        map = expandRows(map, rows);
        map = expandColumns(map, columns);
        return map;
    }

    private static List<Integer> getEmptyRows(char[][] map) {
        var list = new ArrayList<Integer>();
        for (int i = 0; i < map.length; i++) {
            var empty = true;
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == '#') { empty = false; break; }
            }
            if (empty) list.add(i);
        }
        return list;
    }

    private static char[][] expandRows(char[][] map, List<Integer> rows) { // rows are already sorted
        var newMap = Arrays.copyOf(map, map.length + rows.size());
        for (int i = rows.size()-1; i >= 0; i--) {
            for (int j = newMap.length-1; j > rows.get(i) ; j--) { newMap[j] = newMap[j-1]; }
            Arrays.fill(newMap[rows.get(i)], '.');
        }
        return newMap;
    }

    private static List<Integer> getEmptyColumns(char[][] map) {
        var list = new ArrayList<Integer>();
        for (int i = 0; i < map[0].length; i++) {
            var empty = true;
            for (int j = 0; j < map.length; j++) {
                if (map[j][i] == '#') { empty = false; break; }
            }
            if (empty) list.add(i);
        }
        return list;
    }

    private static char[][] expandColumns(char[][] map, List<Integer> columns) { // columns are already sorted
        for (int i = 0; i < map.length; i++) {
            map[i] = Arrays.copyOf(map[i], map[i].length + columns.size());
        }
        for (int i = columns.size()-1; i >= 0; i--) {
            for (int k = 0; k < map.length; k++) {
                for (int j = map[k].length-1; j > columns.get(i) ; j--) { map[k][j] = map[k][j-1]; }
                map[k][columns.get(i)] = '.';

            }
        }
        return map;
    }

    private static char[][] parse(String str) {
        var sa = str.split("\n");
        var ca = new char[sa.length][sa[0].length()];
        for (int i = 0; i < sa.length; i++) {
            ca[i] = sa[i].toCharArray();
        }
        return ca;
    }
    record Point(int x, int y) { }

}

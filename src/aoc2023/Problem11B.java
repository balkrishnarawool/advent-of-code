package aoc2023;

import java.util.ArrayList;
import java.util.List;

public class Problem11B {
    public static void main(String[] args) {
//        var str = Problem11Input.INPUT01; //82000210
        var str = Problem11Input.INPUT02; //842645913794
//        var str = Problem11Input.INPUT03;

        var map = parse(str);
        var rows = getEmptyRows(map);
        var columns= getEmptyColumns(map);

        var galaxies = getGalaxyLocations(map);
        System.out.println(calculateSumOfDistances(galaxies, rows, columns));
    }

    private static long calculateSumOfDistances(List<Point> galaxies, List<Integer> rows, List<Integer> columns) {
        var sum = 0L;
        for (int i = 0; i < galaxies.size(); i++) {
            for (int j = i+1; j < galaxies.size(); j++) {
                var from = galaxies.get(i);
                var to = galaxies.get(j);
                sum += (getDx(from, to, columns) + getDy(from, to, rows));
            }
        }
        return sum;
    }

    private static int getDy(Point from, Point to, List<Integer> rows) {
        int count = getExpandedSpaceCount(from.y, to.y, rows);
        return Math.abs(to.y - from.y) + count * 999_999;
    }

    private static int getDx(Point from, Point to, List<Integer> columns) {
        int count = getExpandedSpaceCount(from.x, to.x, columns);
        return Math.abs(to.x - from.x) + count * 999_999;
    }

    private static int getExpandedSpaceCount(int n1, int n2, List<Integer> columns) {
        int count = 0;
        for (int i = Math.min(n1, n2)+1; i < Math.max(n1, n2) ; i++) {
            if (columns.contains(i)) count++;
        }
        return count;
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

package aoc2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static aoc2023.Problem16B.Dir.DOWN;
import static aoc2023.Problem16B.Dir.LEFT;
import static aoc2023.Problem16B.Dir.RIGHT;
import static aoc2023.Problem16B.Dir.UP;

public class Problem16B {
    public static void main(String[] args) {
//        var str = Problem16Input.INPUT01; //51
        var str = Problem16Input.INPUT02; //7853
//        var str = Problem03Input.INPUT03;

        System.out.println(maxEnergizedTiles(parse(str)));
    }

    private static int maxEnergizedTiles(char[][] layout) {
        int max = 0;
        for (int i = 0; i < layout.length; i++) {
            max = Math.max(max, countEnergizedTiles(layout, RIGHT, new Tile(i, 0)));
            max = Math.max(max, countEnergizedTiles(layout, LEFT, new Tile(i, layout[i].length-1)));
        }
        for (int i = 0; i < layout[0].length; i++) {
            max = Math.max(max, countEnergizedTiles(layout, DOWN, new Tile(0, i)));
            max = Math.max(max, countEnergizedTiles(layout, UP, new Tile(layout.length-1, i)));
        }
        return max;
    }

    enum Dir { RIGHT, DOWN, LEFT, UP }
    record Tile(int row, int column) { }
    record TileDir(Tile tile, Dir dir) { }

    private static int countEnergizedTiles(char[][] layout, Dir dir, Tile tile) {
        var energizedTiles = new char[layout.length][layout[0].length];
        for (var row: energizedTiles) {
            Arrays.fill(row, '.');
        }
        moveLight(layout, dir, tile, energizedTiles, new ArrayList<>());
        return countCells(energizedTiles, '#');
    }

    private static void moveLight(char[][] layout, Dir dir, Tile tile, char[][] energizedTiles, List<TileDir> visited) {
        if (visited(visited, tile, dir)) return;
        visited.add(new TileDir(tile, dir));
        energizedTiles[tile.row][tile.column] = '#';

        var t = layout[tile.row][tile.column];
        if ((dir == RIGHT && (t == '-' || t == '.'))
         || (dir == DOWN && (t == '-' || t == '\\'))
         || (dir == UP && (t == '-' || t == '/'))) {
            if (tile.column < layout[tile.row].length-1) moveLight(layout, RIGHT, new Tile(tile.row, tile.column + 1), energizedTiles, visited);
        }
        if ((dir == RIGHT && (t == '|' || t == '\\'))
                || (dir == DOWN && (t == '|' || t == '.'))
                || (dir == LEFT && (t == '|' || t == '/'))) {
            if (tile.row < layout.length-1) moveLight(layout, DOWN, new Tile(tile.row+1, tile.column), energizedTiles, visited);
        }
        if ((dir == DOWN && (t == '-' || t == '/'))
                || (dir == LEFT && (t == '-' || t == '.'))
                || (dir == UP && (t == '-' || t == '\\'))) {
            if (tile.column > 0) moveLight(layout, LEFT, new Tile(tile.row, tile.column - 1), energizedTiles, visited);
        }
        if ((dir == RIGHT && (t == '|' || t == '/'))
                || (dir == LEFT && (t == '|' || t == '\\'))
                || (dir == UP && (t == '|' || t == '.'))) {
            if (tile.row > 0) moveLight(layout, UP, new Tile(tile.row-1, tile.column), energizedTiles, visited);
        }
    }

    private static boolean visited(List<TileDir> visited, Tile tile, Dir dir) {
        return visited.contains(new TileDir(tile, dir));
    }

    private static int countCells(char[][] energizedTiles, char e) {
        int count = 0;
        for (var ca: energizedTiles) {
            for (var c: ca) {
                if (c == e) count++;
            }
        }
        return count;
    }

    private static char[][] parse(String str) {
        var sa = str.split("\n");
        var ca = new char[sa.length][];
        for (int i = 0; i < sa.length; i++) {
            ca[i] = sa[i].toCharArray();
        }
        return ca;
    }
}

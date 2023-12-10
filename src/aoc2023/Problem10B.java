package aoc2023;

import java.util.Arrays;

import static aoc2023.Problem10B.Dir.DOWN;
import static aoc2023.Problem10B.Dir.LEFT;
import static aoc2023.Problem10B.Dir.RIGHT;
import static aoc2023.Problem10B.Dir.UP;

public class Problem10B {
    public static void main(String[] args) {
//        var str = Problem10Input.INPUT01; //1
//        var str = Problem10Input.INPUT02; //1
        var str = Problem10Input.INPUT03; //467

        var map = parse(str);
        var scaledMap = new char[map.length*3][map[0].length*3];
        fill(scaledMap);

        var dir = getDirForLongestLoop(map);
        markScaledMap(dir, map, scaledMap);

        markStartOnScaledMap(dir, map, scaledMap);
        var s = getStartingPoint(map);
        var offset = getOffset(dir);
        markEnclosedTiles(new Point(s.r*3+offset.r, s.c*3+offset.c), scaledMap);
        print(scaledMap);
        System.out.println(calculateEnclosedTiles(scaledMap));
    }

    //TODO: The method is only implemented for the cases in the problems.
    // Ideally we would need to find the way to get a starting point in the enclosed loop
    // and return the offset of that point from the starting point S.
    private static Point getOffset(Dir dir) {
        return switch (dir) {
            case UP -> new Point(0, 0);
            case RIGHT -> new Point(2, 2);
            case LEFT, DOWN -> null;
        };
    }

    private static int calculateEnclosedTiles(char[][] map) {
        int tiles = 0;
        for (int i = 0; i < map.length/3; i++) {
            for (int j = 0; j < map[0].length/3; j++) {
                if (map[i*3][j*3] == '#' && map[i*3][j*3+1] == '#' && map[i*3][j*3+2] == '#' &&
                    map[i*3+1][j*3] == '#' && map[i*3+1][j*3+1] == '#' && map[i*3+1][j*3+2] == '#' &&
                    map[i*3+2][j*3] == '#' && map[i*3+2][j*3+1] == '#' && map[i*3+2][j*3+2] == '#')
                    tiles++;
            }
        }
        return tiles;
    }

    //TODO: The method is only implemented for the cases in the problems.
    // Ideally we would need to find the way to get the right pipe-type on starting point S
    // by using the info on the pipe-type on the tile after S and the pipe-type on the last tile in the loop.
    private static void markStartOnScaledMap(Dir dir, char[][] map, char[][] scaledMap) {
        var s = getStartingPoint(map);
        switch(dir) {
            case UP:
                    scaledMap[s.r * 3][s.c * 3 + 1] = 'X';
                    scaledMap[s.r * 3 + 1][s.c * 3 + 1] = 'X';
                    scaledMap[s.r * 3 + 1][s.c * 3] = 'X';
                    break;
            case RIGHT:
                    scaledMap[s.r*3+1][s.c*3+1] = 'X';
                    scaledMap[s.r*3+1][s.c*3+2] = 'X';
                    scaledMap[s.r*3+2][s.c*3+1] = 'X';
                    break;
            case LEFT, DOWN: break;
        }
    }

    private static void markEnclosedTiles(Point p, char[][] map) {
        map[p.r][p.c] = '#';
        if (p.r > 0 && map[p.r-1][p.c] == '.') markEnclosedTiles(new Point(p.r-1,p.c), map);
        if (p.c > 0 && map[p.r][p.c-1] == '.') markEnclosedTiles(new Point(p.r,p.c-1), map);
        if (p.c < map[0].length-1 && map[p.r][p.c+1] == '.') markEnclosedTiles(new Point(p.r,p.c+1), map);
        if (p.r < map.length-1 && map[p.r+1][p.c] == '.') markEnclosedTiles(new Point(p.r+1,p.c), map);
    }

    private static Dir getDirForLongestLoop(char[][] map) {
        var s = getStartingPoint(map);
        var dist1 = (map[s.r -1][s.c] == '7' || map[s.r -1][s.c] == '|' || map[s.r -1][s.c] == 'F') ? getLoopDist(new Point(s.r -1, s.c), UP, 1, map) : 0;
        var dist2 = (s.c > 0 && (map[s.r][s.c -1] == 'L' || map[s.r][s.c -1] == '-' || map[s.r][s.c -1] == 'F')) ? getLoopDist(new Point(s.r, s.c -1), LEFT, 1, map) : 0;
        var dist3 = (s.c < map[s.r].length-1 && (map[s.r][s.c +1] == 'J' || map[s.r][s.c +1] == '-' || map[s.r][s.c +1] == '7')) ? getLoopDist(new Point(s.r, s.c +1), RIGHT, 1, map) : 0;
        var dist4 = (s.r < map.length-1 && (map[s.r +1][s.c] == 'J' || map[s.r +1][s.c] == '|' || map[s.r +1][s.c] == 'L')) ? getLoopDist(new Point(s.r +1, s.c), DOWN, 1, map) : 0;
        int max = Math.max(Math.max(dist1, dist2), Math.max(dist3, dist4));
        return dist1 == max ? UP
                : dist2 == max ? LEFT
                : dist3 == max ? RIGHT
                : DOWN;
    }

    private static void markScaledMap(Dir dir, char[][] map, char[][] scaledMap) {
        var s = getStartingPoint(map);
        switch (dir) {
            case UP: markPointOnScaledMap(new Point(s.r - 1, s.c), UP, 1, map, scaledMap);
            case LEFT: markPointOnScaledMap(new Point(s.r, s.c - 1), LEFT, 1, map, scaledMap);
            case RIGHT: markPointOnScaledMap(new Point(s.r, s.c + 1), RIGHT, 1, map, scaledMap);
            case DOWN: markPointOnScaledMap(new Point(s.r + 1, s.c), DOWN, 1, map, scaledMap);
        }
    }

    private static void print(char[][] scaledMap) {
        for (var a: scaledMap) {
            for (char c: a) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    private static void fill(char[][] scaledMap) {
        for (var a: scaledMap) {
            Arrays.fill(a, '.');
        }
    }

    private static int getLoopDist(Point c, Dir dir, int dist, char[][] map) { // c = current
        if (map[c.r][c.c] == 'S') return dist;
        else {
            if (valid(c, dir, map)) { dir = nextDir(dir, c, map); c = next(c, dir); return getLoopDist(c, dir, dist+1, map); }
            else return 0;
        }
    }

    private static void markPointOnScaledMap(Point c, Dir dir, int dist, char[][] map, char[][] scaledMap) { // c = current
        if (map[c.r][c.c] != 'S' && valid(c, dir, map)) {
            markMap(c, map, scaledMap); dir = nextDir(dir, c, map); c = next(c, dir); markPointOnScaledMap(c, dir, dist+1, map, scaledMap);
        }
    }

    private static void markMap(Point c, char[][] map, char[][] scaledMap) {
        switch (map[c.r][c.c]) {
            case '|' :
                scaledMap[c.r*3][c.c*3+1] = 'X';
                scaledMap[c.r*3+1][c.c*3+1] = 'X';
                scaledMap[c.r*3+2][c.c*3+1] = 'X';
                break;
            case '-' :
                scaledMap[c.r*3+1][c.c*3] = 'X';
                scaledMap[c.r*3+1][c.c*3+1] = 'X';
                scaledMap[c.r*3+1][c.c*3+2] = 'X';
                break;
            case 'L' :
                scaledMap[c.r*3][c.c*3+1] = 'X';
                scaledMap[c.r*3+1][c.c*3+1] = 'X';
                scaledMap[c.r*3+1][c.c*3+2] = 'X';
                break;
            case 'J' :
                scaledMap[c.r*3][c.c*3+1] = 'X';
                scaledMap[c.r*3+1][c.c*3+1] = 'X';
                scaledMap[c.r*3+1][c.c*3] = 'X';
                break;
            case '7' :
                scaledMap[c.r*3+1][c.c*3] = 'X';
                scaledMap[c.r*3+1][c.c*3+1] = 'X';
                scaledMap[c.r*3+2][c.c*3+1] = 'X';
                break;
            case 'F' :
                scaledMap[c.r*3+1][c.c*3+1] = 'X';
                scaledMap[c.r*3+1][c.c*3+2] = 'X';
                scaledMap[c.r*3+2][c.c*3+1] = 'X';
                break;
            case '.' : break;
            case 'S' : throw new IllegalStateException("'S' should have been already evaluated");
            default : throw new IllegalStateException("Unexpected value: " + map[c.r][c.c]);
        };
    }

    private static Dir nextDir(Dir dir, Point c, char[][] map) {
        return switch (map[c.r][c.c]) {
            case '|' -> dir;
            case '-' -> dir;
            case 'L' -> dir == DOWN ? RIGHT : UP;
            case 'J' -> dir == DOWN ? LEFT : UP;
            case '7' -> dir == UP ? LEFT : DOWN;
            case 'F' -> dir == UP ? RIGHT : DOWN;
            case '.' -> throw new IllegalStateException("'.' should not be valid");
            case 'S' -> throw new IllegalStateException("'S' should have been already evaluated");
            default -> throw new IllegalStateException("Unexpected value: " + map[c.r][c.c]);
        };
    }

    private static Point next(Point c, Dir dir) {
        return switch (dir) {
            case UP -> new Point(c.r-1, c.c);
            case DOWN -> new Point(c.r+1, c.c);
            case LEFT -> new Point(c.r, c.c-1);
            case RIGHT -> new Point(c.r, c.c+1);
        };
    }

    private static boolean valid(Point c, Dir dir, char[][] map) { // c = current, n = next
        return switch (dir) {
            case UP -> map[c.r][c.c] == '7' || map[c.r][c.c] == '|' || map[c.r][c.c] == 'F';
            case DOWN -> map[c.r][c.c] == 'J' || map[c.r][c.c] == '|' || map[c.r][c.c] == 'L';
            case LEFT -> map[c.r][c.c] == '-' || map[c.r][c.c] == 'L' || map[c.r][c.c] == 'F';
            case RIGHT -> map[c.r][c.c] == '-' || map[c.r][c.c] == 'J' || map[c.r][c.c] == '7';
        };
    }

    private static Point getStartingPoint(char[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 'S') return new Point(i, j);
            }
        }
        throw new IllegalStateException("No starting point");
    }

    record Point(int r, int c) { } // r = row, c = column
    enum Dir { UP, DOWN, LEFT, RIGHT }

    private static char[][] parse(String s) {
        var sa = s.split("\n");
        var ca = new char[sa.length][sa[0].length()];
        for (int i = 0; i < ca.length; i++) {
            ca[i] = sa[i].toCharArray();
        }
        return ca;
    }
}

package aoc2023;

import static aoc2023.Problem10A.Dir.DOWN;
import static aoc2023.Problem10A.Dir.LEFT;
import static aoc2023.Problem10A.Dir.RIGHT;
import static aoc2023.Problem10A.Dir.UP;

public class Problem10A {
    public static void main(String[] args) {
        var str = Problem10Input.INPUT01; //4
//        var str = Problem10Input.INPUT02; //8
//        var str = Problem10Input.INPUT03; //6927

        var map = parse(str);
        System.out.println(calculateFarthestDist(map));
    }

    private static int calculateFarthestDist(char[][] map) {
        var s = getStartingPoint(map);
        System.out.println("Start: "+s);
        var dist1 = (s.r > 0 && (map[s.r -1][s.c] == '7' || map[s.r -1][s.c] == '|' || map[s.r -1][s.c] == 'F')) ? getLoopDist(new Point(s.r -1, s.c), UP, 1, map) : 0;
        var dist2 = (s.c > 0 && (map[s.r][s.c -1] == 'L' || map[s.r][s.c -1] == '-' || map[s.r][s.c -1] == 'F')) ? getLoopDist(new Point(s.r, s.c -1), LEFT, 1, map) : 0;
        var dist3 = (s.c < map[s.r].length-1 && (map[s.r][s.c +1] == 'J' || map[s.r][s.c +1] == '-' || map[s.r][s.c +1] == '7')) ? getLoopDist(new Point(s.r, s.c +1), RIGHT, 1, map) : 0;
        var dist4 = (s.r < map.length-1 && (map[s.r +1][s.c] == 'J' || map[s.r +1][s.c] == '|' || map[s.r +1][s.c] == 'L')) ? getLoopDist(new Point(s.r +1, s.c), DOWN, 1, map) : 0;
        var dist = Math.max(Math.max(dist1, dist2), Math.max(dist3, dist4));
        print(map);
        return dist/2;
    }

    private static void print(char[][] markedMap) {
        for (var a: markedMap) {
            for (char c: a) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    private static int getLoopDist(Point c, Dir dir, int dist, char[][] map) { // c = current
        if (map[c.r][c.c] == 'S') return dist;
        else {
            if (valid(c, dir, map)) { dir = nextDir(dir, c, map); c = next(c, dir); return getLoopDist(c, dir, dist+1, map); }
            else return 0;
        }
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

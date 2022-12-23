package aoc2022;

import lombok.*;

import java.io.IOException;
import java.util.List;

import static aoc.Utils.readFile;
import static aoc2022.Problem22A.Dir.*;

public class Problem22A {

    public static void main(String[] args) throws IOException {
        solvePart1("./src/aoc2022/Problem22Input1.txt");
        solvePart1("./src/aoc2022/Problem22Input2.txt");
    }

    private static void solvePart1(String path) throws IOException {
        System.out.println(solve(path));
    }

    private static long solve(String path) throws IOException {
        var lines = readFile(path);
        var input = new Input(lines);

        var p = input.getStartingPoint();
        var d = RIGHT;
        var g = new Generator(lines.get(lines.size()-1));
        while (g.hasNext()) {
            var t = g.getNextToken();
//            System.out.println(t);

            switch (t) {
                case "L" -> d = switch (d) {
                    case RIGHT -> UP;
                    case DOWN -> RIGHT;
                    case LEFT -> DOWN;
                    case UP -> LEFT;
                };
                case "R" -> d = switch (d) {
                    case RIGHT -> DOWN;
                    case DOWN -> LEFT;
                    case LEFT -> UP;
                    case UP -> RIGHT;
                };
                default -> {
                    var l = Integer.parseInt(t);
                    p = input.move(p, l, d);
//                    input.show(p);
                }
            }
        }
        return p.calculatePassword(d);
    }
    
    static class Input {
        char[][] map;
        int[] startH;
        int[] endH;
        int[] startV;
        int[] endV;

        public Input(List<String> lines) {
            var max = lines.stream().map(String::length).max(Integer::compareTo).orElseThrow();
            map = new char[lines.size()-2][max];
            for (int i = 0; i < lines.size()-2; i++) {
                for (int j = 0; j < max; j++) {
                    map[i][j] = ' ';
                    if (j < lines.get(i).length()) map[i][j] = lines.get(i).charAt(j);
                }
            }
            setBounds();
        }

        private void setBounds() {
            startH = new int[map.length];
            endH = new int[map.length];
            startV = new int[map[0].length];
            endV = new int[map[0].length];

            for (int j = 0; j < map.length; j++) {
                for (int i = 0; i < map[j].length; i++) {
                    if (map[j][i] != ' ') { startH[j] = i; break; }
                }
                for (int i = map[j].length-1; i >= 0; i--) {
                    if (map[j][i] != ' ') { endH[j] = i; break; }
                }
            }
            for (int j = 0; j < map[0].length; j++) {
                for (int i = 0; i < map.length; i++) {
                    if (map[i][j] != ' ') { startV[j] = i; break; }
                }
                for (int i = map.length-1; i >= 0; i--) {
                    if (map[i][j] != ' ') { endV[j] = i; break; }
                }
            }
        }

        public Point getStartingPoint() {
            return new Point(0, startH[0]);
        }

        public Point move(Point p, int l, Dir d) {
            var r = p.i;
            var c = p.j;
            switch (d) {
                case RIGHT:
                    for (int i = 1; i <= l; i++) {
                        var old = c;
                        c++;
                        if (c == map[r].length || map[r][c] == ' ') c = startH[r];
                        if (map[r][c] == '#') { c = old; break; }
                    }
                    break;
                case DOWN:
                    for (int i = 1; i <= l; i++) {
                        var old = r;
                        r++;
                        if (r == map.length || map[r][c] == ' ') r = startV[c];
                        if (map[r][c] == '#') { r = old; break; }
                    }
                    break;
                case LEFT:
                    for (int i = 1; i <= l; i++) {
                        var old = c;
                        c--;
                        if (c == -1 || map[r][c] == ' ') c = endH[r];
                        if (map[r][c] == '#') { c = old; break; }
                    }
                    break;
                case UP:
                    for (int i = 1; i <= l; i++) {
                        var old = r;
                        r--;
                        if (r == -1 || map[r][c] == ' ') r = endV[c];
                        if (map[r][c] == '#') { r = old; break; }
                    }
            }
            return new Point(r, c);
        }

        public void show(Point p) {
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    if (i == p.i && j == p.j) System.out.print("P");
                    else System.out.print(map[i][j]);
                }
                System.out.println();
            }
        }
    }


    @RequiredArgsConstructor
    static class Generator {
        @NonNull
        String s;
        int index = -1;

        public boolean hasNext() {
            return index < s.length()-1;
        }

        public String getNextToken() {
            index++;
            var c = s.charAt(index);
            var r = ""+c;
            while (hasNext() && (c != 'L' && c != 'R')) {
                index++;
                c = s.charAt(index);
                r += c;
            }
            if (!r.equals("L") && !r.equals("R") && hasNext()) { r = r.substring(0, r.length()-1); index--; }
            return r;
        }
    }

    record Point(int i, int j) {
        public long calculatePassword(Dir d) {
            return (i+1)*1000L + (j+1)*4L + d.ordinal();
        }
    }
    enum Dir { RIGHT, DOWN, LEFT, UP; }

//     Output
//     6032
//     56372

}

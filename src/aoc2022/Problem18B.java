package aoc2022;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.*;

import static aoc.Utils.readFile;
import static aoc2022.Problem18B.TrapType.*;

public class Problem18B {

    public static void main(String[] args) throws IOException {
        solvePart2("./src/aoc2022/Problem18Input1.txt");
        solvePart2("./src/aoc2022/Problem18Input2.txt");
    }


    private static void solvePart2(String path) throws IOException {
        System.out.println(solve2(path));
    }

    private static int solve2(String path) throws IOException {
        var lines = readFile(path);
        var input = parseInput(lines);

        int i = 0;
        int sa = 0; //surface area
        for (var c: input.cubes) {
            Cube u = new Cube(c.i - 1, c.j, c.k); if (!input.cubes.contains(u) && input.trappedMap.get(u) != TRAPPED) sa++;
            Cube d = new Cube(c.i + 1, c.j, c.k); if (!input.cubes.contains(d) && input.trappedMap.get(d) != TRAPPED) sa++;
            Cube l = new Cube(c.i, c.j - 1, c.k); if (!input.cubes.contains(l) && input.trappedMap.get(l) != TRAPPED) sa++;
            Cube r = new Cube(c.i, c.j + 1, c.k); if (!input.cubes.contains(r) && input.trappedMap.get(r) != TRAPPED) sa++;
            Cube f = new Cube(c.i, c.j, c.k - 1); if (!input.cubes.contains(f) && input.trappedMap.get(f) != TRAPPED) sa++;
            Cube b = new Cube(c.i, c.j, c.k + 1); if (!input.cubes.contains(b) && input.trappedMap.get(b) != TRAPPED) sa++;
        }
        return sa;
    }

    private static Input parseInput(List<String> lines) {
        var cubes = new ArrayList<Cube>();
        for (var s: lines) {
            var sa = s.split(",");
            var cube = new Cube(Integer.parseInt(sa[0]), Integer.parseInt(sa[1]), Integer.parseInt(sa[2]));
            cubes.add(cube);
        }
        return new Input(cubes);
    }

    static class Input {
        List<Cube> cubes;
        Map<Cube, TrapType> trappedMap = new HashMap<>();
        int maxI;
        int maxJ;
        int maxK;
        Input(List<Cube> cubes) {
            this.cubes = cubes;
            for (var c: this.cubes) {
                trappedMap.put(c, BLOCK);
            }
            maxI = getMaxI();
            maxJ = getMaxJ();
            maxK = getMaxK();
            for (int i = 0; i < maxI; i++) {
                for (int j = 0; j < maxJ; j++) {
                    for (int k = 0; k < maxK; k++) {
                        if (!trappedMap.containsKey(new Cube(i, j, k))) {
                            trappedMap.put(new Cube(i, j, k), trapped(new Cube(i, j, k), new ArrayList<>()));
                        }
                    }
                }
            }
        }

        private int getMaxI() {
            return cubes.stream().map(c -> c.i).max(Comparator.comparingInt(i -> i)).get();
        }

        private int getMaxJ() {
            return cubes.stream().map(c -> c.j).max(Comparator.comparingInt(j -> j)).get();
        }

        private int getMaxK() {
            return cubes.stream().map(c -> c.k).max(Comparator.comparingInt(k -> k)).get();
        }

        public TrapType trapped(Cube c, List<Cube> list) {
            if (trappedMap.containsKey(c)) return trappedMap.get(c);

            if (c.i-1 < 0) return NOT_TRAPPED;
            if (c.i+1 > maxI) return NOT_TRAPPED;
            if (c.j-1 < 0) return NOT_TRAPPED;
            if (c.j+1 > maxJ) return NOT_TRAPPED;
            if (c.k-1 < 0) return NOT_TRAPPED;
            if (c.k+1 > maxK) return NOT_TRAPPED;

            Cube uc = new Cube(c.i-1, c.j, c.k); var u = list.contains(uc) ? BLOCK : trapped(uc, add(list, c)); if (u == TRAPPED || u == NOT_TRAPPED) { trappedMap.put(c, u); return u; }
            Cube dc = new Cube(c.i+1, c.j, c.k); var d = list.contains(dc) ? BLOCK : trapped(dc, add(list, c)); if (d == TRAPPED || d == NOT_TRAPPED) { trappedMap.put(c, d); return d; }
            Cube lc = new Cube(c.i, c.j-1, c.k); var l = list.contains(lc) ? BLOCK : trapped(lc, add(list, c)); if (l == TRAPPED || l == NOT_TRAPPED) { trappedMap.put(c, l); return l; }
            Cube rc = new Cube(c.i, c.j+1, c.k); var r = list.contains(rc) ? BLOCK : trapped(rc, add(list, c)); if (r == TRAPPED || r == NOT_TRAPPED) { trappedMap.put(c, r); return r; }
            Cube fc = new Cube(c.i, c.j,c.k-1); var f = list.contains(fc) ? BLOCK : trapped(fc, add(list, c)); if (f == TRAPPED || f == NOT_TRAPPED) { trappedMap.put(c, f); return f; }
            Cube bc = new Cube(c.i, c.j,c.k+1); var b = list.contains(bc) ? BLOCK : trapped(bc, add(list, c)); if (b == TRAPPED || b == NOT_TRAPPED) { trappedMap.put(c, b); return b; }

            if (u == BLOCK && d == BLOCK && l == BLOCK && r == BLOCK && f == BLOCK && b == BLOCK) { trappedMap.put(c, TRAPPED); return TRAPPED; }

            throw new RuntimeException("Could not find out if trapped or not");
        }

        private List<Cube> add(List<Cube> list, Cube c) {
            list.add(c);
            return list;
        }
    }

    enum TrapType {
        TRAPPED, NOT_TRAPPED, BLOCK;
    }

    record Cube(int i, int j, int k) { }

//     Output
//     58
//     2444

}

package aoc2022;

import lombok.*;

import java.io.IOException;
import java.util.*;

import static aoc.Utils.readFile;

public class Problem07 {

    public static void main(String[] args) throws IOException {
        solvePart1("./src/aoc2022/Problem07Input1.txt");
        solvePart1("./src/aoc2022/Problem07Input2.txt");
        solvePart2("./src/aoc2022/Problem07Input1.txt");
        solvePart2("./src/aoc2022/Problem07Input2.txt");
    }

    private static void solvePart1(String path) throws IOException {
        var lines = readFile(path);
        Dir root = createDirStr(lines);
        calculateSizes(root);
        System.out.println(calculateFilteredSize(root));
    }

    private static long calculateFilteredSize(Dir dir) {
        var size = 0L;
        if (dir.size <= 100000) size += dir.size;
        for (var d: dir.sub) {
            size += calculateFilteredSize(d);
        }
        return size;
    }

    private static long calculateSizes(Dir dir) {
        for (var d: dir.sub) {
            dir.size += calculateSizes(d);
        }
        for (var f: dir.files) {
            dir.size += f.size;
        }
        return dir.size;
    }

    private static Dir createDirStr(List<String> lines) {
        var root = new Dir("/", null);
        var currentDir = root;
        for (var s: lines) {
            if (s.startsWith("$ ")) {
                var sa = s.split("\\$ ");
                if (sa[1].startsWith("cd ")) {
                    var sa2 = sa[1].split("cd ");
                    if (sa2[1].startsWith("/")) {
                        currentDir = root;
                    } else {
                        if (sa2[1].equals("..")) {
                            currentDir = currentDir.parent;
                        } else {
                            currentDir = currentDir.sub.stream().filter(t -> t.name.equals(sa2[1])).findFirst().get();
                        }
                    }
                }
            }
            else if (s.startsWith("dir ")) {
                var d = s.split("dir ");
                currentDir.sub.add(new Dir(d[1], currentDir));
            } else {
                var d = s.split(" ");
                currentDir.files.add(new File(d[1], Long.parseLong(d[0])));
            }
        }
        return root;
    }

    private static void solvePart2(String path) throws IOException {
        var lines = readFile(path);
        Dir root = createDirStr(lines);
        calculateSizes(root);
        var map = new HashMap<String, Long>();
        calculateMapFilteredSize(root, root.size - 40000000L, map);
        System.out.println(smallest(map));
    }

    private static long smallest(HashMap<String, Long> map) {
        var e = new Entry("something", Long.MAX_VALUE);
        map.forEach((n, s) -> {
            if (s < e.getS()) {
                e.setS(s);
                e.setN(n);
            }
        });
        return e.getS();
    }

    private static void calculateMapFilteredSize(Dir dir, long space, HashMap<String, Long> map) {
        if (dir.size >= space) map.put(dir.name, dir.size);
        for (var d: dir.sub) {
            calculateMapFilteredSize(d, space, map);
        }
    }

    @Getter
    @Setter
    static class Dir {
        String name;
        Dir parent;
        Set<Dir> sub = new HashSet<>();
        Set<File> files = new HashSet<>();
        long size;

        public Dir(String name, Dir parent) {
            this.name = name;
            this.parent = parent;
        }
    }
    @Getter
    @Setter
    @AllArgsConstructor
    static class File {
        String name;
        long size;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    static class Entry {
        String n;
        long s;

    }
//     Output
//     95437
//     1447046
//     24933642
//     578710

}

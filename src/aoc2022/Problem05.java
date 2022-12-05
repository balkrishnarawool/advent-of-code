package aoc2022;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Problem05 {

    public static void main(String[] args) throws IOException {
        solvePart1("./src/aoc2022/Problem05Input1.txt");

        solvePart1("./src/aoc2022/Problem05Input2.txt");
        solvePart2("./src/aoc2022/Problem05Input1.txt");
        solvePart2("./src/aoc2022/Problem05Input2.txt");
    }

    private static void solvePart1(String path) throws IOException {
        System.out.println(solve(path));
    }

    private static void solvePart2(String path) throws IOException {
        System.out.println(solve2(path));
    }

    private static String solve(String path) throws IOException {
        var list = readFile(path);
        int size = list.indexOf("");
        int count = (list.get(size-1).length()/4) + 1;
        var ss = new ArrayList<Stack<Character>>(count);
        for (int i = 0; i < count; i++) {
            ss.add(new Stack<>());
        }
        for (int i = size-2; i >= 0 ; i--) {
            for (int j = 0; j < count; j++) {
                var c = list.get(i).charAt(j*4+1);
                if (c != ' ') {
                    ss.get(j).push(c);
                }
            }
        }
        for (int i = size+1; i < list.size(); i++) {
            var sa = list.get(i).split("move ");
            var sa2 = sa[1].split(" ");
            var c = Integer.parseInt(sa2[0]);
            var f = Integer.parseInt(sa2[2]);
            var t = Integer.parseInt(sa2[4]);

            for (int j = 0; j < c; j++) {
                ss.get(t-1).push(ss.get(f-1).pop());
            }
        }
        var r = "";
        for (var s: ss) {
            r += s.pop();
        }
        return r;
    }

    private static String solve2(String path) throws IOException {
        var list = readFile(path);
        int size = list.indexOf("");
        int count = (list.get(size-1).length()/4) + 1;
        var ss = new ArrayList<Stack<Character>>(count);
        for (int i = 0; i < count; i++) {
            ss.add(new Stack<>());
        }
        for (int i = size-2; i >= 0 ; i--) {
            for (int j = 0; j < count; j++) {
                var c = list.get(i).charAt(j*4+1);
                if (c != ' ') {
                    ss.get(j).push(c);
                }
            }
        }
        for (int i = size+1; i < list.size(); i++) {
            var sa = list.get(i).split("move ");
            var sa2 = sa[1].split(" ");
            var c = Integer.parseInt(sa2[0]);
            var f = Integer.parseInt(sa2[2]);
            var t = Integer.parseInt(sa2[4]);

            var st = new Stack<Character>();
            for (int j = 0; j < c; j++) {
                st.push(ss.get(f-1).pop());
            }
            for (int j = 0; j < c; j++) {
                ss.get(t-1).push(st.pop());
            }
        }
        var r = "";
        for (var s: ss) {
            r += s.pop();
        }
        return r;
    }

    private static List<String> readFile(String path) throws IOException {
        try (var lines = Files.lines(Path.of(path))) {
            return lines.toList();
        }
    }

//    private static long solve2(String path) throws IOException {
//        try (var lines = Files.lines(Path.of(path))) {
//            return lines.map(s -> {
//
//            })
//            .filter(b -> b)
//            .count();
//        }
//    }


//     Output


}

package aoc2022;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Problem01 {

    public static void main(String[] args) throws IOException {
        solvePart1("./src/aoc2022/Problem01Input1.txt");
        solvePart1("./src/aoc2022/Problem01Input2.txt");
        solvePart2("./src/aoc2022/Problem01Input1.txt");
        solvePart2("./src/aoc2022/Problem01Input2.txt");
    }

    private static void solvePart1(String path) throws IOException {
        var list = getSummedIntsFromFile(path);
        System.out.println(list.stream().max(Comparator.comparingInt(e -> e)));
    }

    private static void solvePart2(String path) throws IOException {
        var list = getSummedIntsFromFile(path);
        System.out.println(list.stream().sorted(Comparator.reverseOrder()).limit(3).reduce(Integer::sum));
    }

    private static List<Integer> getSummedIntsFromFile(String path) throws IOException {
        var list = new ArrayList<Integer>();
        var sum = new MutableInt(0);
        try(var lines = Files.lines(Path.of(path))) {
            lines.forEach(s -> {
                if (s.length() == 0) {
                    list.add(sum.getV());
                    sum.setV(0);
                } else {
                    sum.setV(sum.getV() + Integer.parseInt(s));
                }
            });
            // Add last element
            list.add(sum.getV());
            return list;
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor(staticName = "of")
    static class MutableInt {
        int v;
    }
    
//     Output
//    Optional[24000]
//    Optional[67027]
//    Optional[45000]
//    Optional[197291]
}

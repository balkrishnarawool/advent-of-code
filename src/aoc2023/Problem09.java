package aoc2023;

public class Problem09 {
    public static void main(String[] args) {
        var str = Problem03Input.INPUT01;
//        var str = Problem03Input.INPUT02;
//        var str = Problem03Input.INPUT03;

        System.out.println(
                str.lines()
                        .map(Problem09::solve)
        );
    }

    private static int solve(String s) {
        return 0;
    }
}

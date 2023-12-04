package aoc2023;

public class Problem05 {
    public static void main(String[] args) {
        var str = Problem05Input.INPUT01;
//        var str = Problem05Input.INPUT02;
//        var str = Problem05Input.INPUT03;

        System.out.println(
                str.lines()
                        .map(Problem05::solve)
        );
    }

    private static int solve(String s) {
        return 0;
    }
}

package aoc2023;

public class Problem04 {
    public static void main(String[] args) {
        var str = Problem04Input.INPUT01;
//        var str = Problem04Input.INPUT02;
//        var str = Problem04Input.INPUT03;

        System.out.println(
                str.lines()
                        .map(Problem04::solve)
        );
    }

    private static int solve(String s) {
        return 0;
    }
}

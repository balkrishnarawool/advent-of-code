package aoc2023;

public class Problem10 {
    public static void main(String[] args) {
        var str = Problem03Input.INPUT01;
//        var str = Problem03Input.INPUT02;
//        var str = Problem03Input.INPUT03;

        System.out.println(
                str.lines()
                        .map(Problem10::solve)
        );
    }

    private static int solve(String s) {
        return 0;
    }
}

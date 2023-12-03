package aoc2023;

public class Problem01A {
    public static void main(String[] args) {
//        var str = Problem01Input.INPUT01; //142
//        var str = Problem01Input.INPUT02; //209
        var str = Problem01Input.INPUT03; //53651

        System.out.println(
                str.lines()
                        .map(Problem01A::calibration)
                        .reduce(Integer::sum)
        );
    }

    private static int calibration(String s) {
        return first(s)*10 + last(s);
    }

    private static int first(String s) {
        var sa = s.toCharArray();
        for (char c : sa) {
            if (c >= '1' && c <= '9') return Integer.parseInt("" + c);
        }
        return 0;
    }

    private static int last(String s) {
        var sa = s.toCharArray();
        for (int i = sa.length-1; i >= 0; i--) {
            if (sa[i] >= '1' && sa[i]<= '9') return Integer.parseInt(""+sa[i]);
        }
        return 0;
    }
}

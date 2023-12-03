package aoc2023;

public class Problem01B {
    public static void main(String[] args) {
//        var str = Problem01Input.INPUT01; //142
//        var str = Problem01Input.INPUT02; //281
        var str = Problem01Input.INPUT03; //53894

        System.out.println(
                str.lines()
                        .map(Problem01B::calibration)
                        .reduce(Integer::sum));
    }

    private static int calibration(String s) {
        return first(s)*10 + last(s);
    }

    private static int first(String s) {
        var sa = s.toCharArray();
        for (int i = 0; i < sa.length; i++) {
            if (sa[i] >= '1' && sa[i]<= '9') return Integer.parseInt(""+sa[i]);
            if (getFirstDigit(sa, i) > 0) return getFirstDigit(sa, i);
        }
        return 0;
    }

    private static int getFirstDigit(char[] sa, int i) {
        if (i+2<sa.length && new String(sa, i, 3).equals("one")) return 1;
        if (i+2<sa.length && new String(sa, i, 3).equals("two")) return 2;
        if (i+4<sa.length && new String(sa, i, 5).equals("three")) return 3;
        if (i+3<sa.length && new String(sa, i, 4).equals("four")) return 4;
        if (i+3<sa.length && new String(sa, i, 4).equals("five")) return 5;
        if (i+2<sa.length && new String(sa, i, 3).equals("six")) return 6;
        if (i+4<sa.length && new String(sa, i, 5).equals("seven")) return 7;
        if (i+4<sa.length && new String(sa, i, 5).equals("eight")) return 8;
        if (i+3<sa.length && new String(sa, i, 4).equals("nine")) return 9;
        return 0;
    }

    private static int last(String s) {
        var sa = s.toCharArray();
        for (int i = sa.length-1; i >= 0; i--) {
            if (sa[i] >= '0' && sa[i]<= '9') return Integer.parseInt(""+sa[i]);
            if (getLastDigit(sa, i) > 0) return getLastDigit(sa, i);
        }
        return 0;
    }

    private static int getLastDigit(char[] sa, int i) {
        if (i>1 && new String(sa, i-2, 3).equals("one")) return 1;
        if (i>1 && new String(sa, i-2, 3).equals("two")) return 2;
        if (i>3 && new String(sa, i-4, 5).equals("three")) return 3;
        if (i>2 && new String(sa, i-3, 4).equals("four")) return 4;
        if (i>2 && new String(sa, i-3, 4).equals("five")) return 5;
        if (i>1 && new String(sa, i-2, 3).equals("six")) return 6;
        if (i>3 && new String(sa, i-4, 5).equals("seven")) return 7;
        if (i>3 && new String(sa, i-4, 5).equals("eight")) return 8;
        if (i>2 && new String(sa, i-3, 4).equals("nine")) return 9;
        return 0;
    }

}

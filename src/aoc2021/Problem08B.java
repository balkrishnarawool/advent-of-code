package aoc2021;

import static aoc.Utils.fileToStringArray;

public class Problem08B {

    private static int calculateSum(String[] strs) {
        int c = 0;
        for (String str: strs) {
            String[] input = str.split("\\|")[0].split(" ");
            int[] digits = determineDigits(input);

            String[] output = str.split("\\|")[1].substring(1).split(" ");
            c += calculateDisplay(input, digits, output);
        }
        return c;
    }

    private static int calculateDisplay(String[] input, int[] digits, String[] output) {
        int num = 0;
        for (String s: output) {
            for (int i = 0; i < input.length; i++) {
                if (is(s, input[i])) {
                    num = num * 10 + digits[i];
                }
            }
        }
        return num;
    }

    private static boolean is(String s, String s1) {
        if (s.length() != s1.length()) return false;
        String[] arr = s.split("");
        for (String str: arr) {
            if (!s1.contains(str)) return false;
        }
        return true;
    }

    private static int[] determineDigits(String[] input) {
        int[] digits = new int[10];
        determine1478(input, digits);
        determine3(input, digits);
        determine6(input, digits);
        determine52(input, digits);
        determine9(input, digits);
        return digits;
    }


    private static void determine9(String[] input, int[] digits) {
        String three = get3(input, digits);
        for (int i = 0; i < input.length; i++) {
            String s  = input[i];
            if (s.length() == 6 && digits[i] == 0) {
                if (is9(s, three)) {
                    digits[i] = 9;
                }
            }
        }
    }

    private static boolean is9(String s, String three) {
        String[] arr = three.split("");
        return s.contains(arr[0]) && s.contains(arr[1]) && s.contains(arr[2]) && s.contains(arr[3]) && s.contains(arr[4]);
    }

    private static String get3(String[] input, int[] digits) {
        for (int i = 0; i < input.length; i++) {
            if (digits[i] == 3) {
                return input[i];
            }
        }
        return null;
    }

    private static void determine52(String[] input, int[] digits) {
        String six = get6(input, digits);
        for (int i = 0; i < input.length; i++) {
            String s  = input[i];
            if (s.length() == 5 && digits[i] == 0) {
                if (is5(s, six)) {
                    digits[i] = 5;
                } else {
                    digits[i] = 2;
                }
            }
        }
    }

    private static boolean is5(String s, String six) {
        String[] arr = s.split("");
        return six.contains(arr[0]) && six.contains(arr[1]) && six.contains(arr[2]) && six.contains(arr[3]) && six.contains(arr[4]);
    }

    private static String get6(String[] input, int[] digits) {
        for (int i = 0; i < input.length; i++) {
            if (digits[i] == 6) {
                return input[i];
            }
        }
        return null;
    }

    private static void determine6(String[] input, int[] digits) {
        String seven = get7(input, digits);
        for (int i = 0; i < input.length; i++) {
            String s  = input[i];
            if (s.length() == 6) {
                if (is6(s, seven)) {
                    digits[i] = 6;
                }
            }
        }
    }

    private static boolean is6(String s, String seven) {
        String[] arr = seven.split("");
        return !(s.contains(arr[0]) && s.contains(arr[1]) && s.contains(arr[2]));
    }

    private static String get7(String[] input, int[] digits) {
        for (int i = 0; i < input.length; i++) {
            if (digits[i] == 7) {
                return input[i];
            }
        }
        return null;
    }

    private static void determine3(String[] input, int[] digits) {
        String one = get1(input, digits);
        for (int i = 0; i < input.length; i++) {
            String s  = input[i];
            if (s.length() == 5) {
                if (is3(s, one)) {
                    digits[i] = 3;
                }
            }
        }
    }

    private static boolean is3(String s, String one) {
        String[] arr = one.split("");
        return s.contains(arr[0]) && s.contains(arr[1]);
    }

    private static String get1(String[] input, int[] digits) {
        for (int i = 0; i < input.length; i++) {
            if (digits[i] == 1) {
                return input[i];
            }
        }
        return null;
    }

    private static void determine1478(String[] input, int[] digits) {
        for (int i = 0; i < input.length; i++) {
            String s  = input[i];
            if (s.length() == 2) {
                digits[i] = 1;
            }
            else if (s.length() == 4) {
                digits[i] = 4;
            }
            else if (s.length() == 3) {
                digits[i] = 7;
            }
            else if (s.length() == 7) {
                digits[i] = 8;
            }
        }
    }

    public static void main(String[] args) {
        String[] input1 = fileToStringArray(Problem08B.class, "Problem08Input1.txt");
        String[] input2 = fileToStringArray(Problem08B.class, "Problem08Input2.txt");

        System.out.println(calculateSum(input1));
        System.out.println(calculateSum(input2));

//        Output

    }
}

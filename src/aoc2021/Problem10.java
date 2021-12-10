package aoc2021;

import java.util.*;

import static aoc.Utils.fileToStringArray;

public class Problem10 {

    private static long calculateScore(String[] strs, boolean completion) {
        int score = 0;
        List<Long> scores = new ArrayList<>();
        for (String str: strs) {
            String[] arr = str.split("");
            Stack<String> stack = new Stack<>();
            boolean corrupted = false;
            for (String token: arr) {
                if (isStarting(token)) { stack.push(token); }
                if (isEnding(token)) {
                    String lastToken = stack.peek();
                    if (isMatching(lastToken, token)) {
                        stack.pop();
                    } else {
                        score += calculateLineScore(token);
                        corrupted = true;
                        break;
                    }
                }
            }
            if (completion && !corrupted && !stack.isEmpty()) {
                scores.add(calculateScoreOfIncompleteLine(stack));
            }
        }
        if (completion) {
            Collections.sort(scores);
            return scores.get(scores.size() / 2);
        } else {
            return score;
        }
    }

    private static Long calculateScoreOfIncompleteLine(Stack<String> stack) {
        long score = 0;
        while (!stack.isEmpty()) {
            String s = stack.pop();
            score = score * 5 + getScoreForToken(s);
        }
        return score;
    }

    private static long getScoreForToken(String t) {
        switch (t) {
            case "(": return 1;
            case "[": return 2;
            case "{": return 3;
            case "<": return 4;
            default: return 0;
        }
    }

    private static boolean isStarting(String t) {
        return t.equals("(") || t.equals("<") || t.equals("[") || t.equals("{");
    }

    private static boolean isEnding(String t) {
        return t.equals(")") || t.equals(">") || t.equals("]") || t.equals("}");
    }

    private static boolean isMatching(String t1, String t2) {
        return (t1.equals("(") && t2.equals(")"))
                || (t1.equals("<") && t2.equals(">"))
                || (t1.equals("[") && t2.equals("]"))
                || (t1.equals("{") && t2.equals("}"));
    }

    private static int calculateLineScore(String t) {
        switch (t) {
            case ")" : return 3;
            case "]" : return 57;
            case "}" : return 1197;
            case ">" : return 25137;
            default: return 0;
        }
    }

    public static void main(String[] args) {
        String[] input1 = fileToStringArray(Problem10.class, "Problem10Input1.txt");
        String[] input2 = fileToStringArray(Problem10.class, "Problem10Input2.txt");

        System.out.println(calculateScore(input1, false));
        System.out.println(calculateScore(input2, false));

        System.out.println(calculateScore(input1, true));
        System.out.println(calculateScore(input2, true));

//        Output

    }

}

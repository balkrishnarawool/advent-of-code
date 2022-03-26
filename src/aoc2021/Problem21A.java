package aoc2021;

import static aoc.Utils.fileToStringArray;

public class Problem21A {

    private static void solve(String[] input) {

        int p1 = Integer.parseInt(input[0].substring("Player 1 starting position: ".length()));
        int p2 = Integer.parseInt(input[1].substring("Player 2 starting position: ".length()));

        int p1p = 0;
        int p2p = 0;
        int dp = -2;
        int nd = 0;
        while(true) {
            dp = getdicePosition(dp);
            nd += 3;

            p1 = move(p1, dp);
            p1p += p1;

            if(p1p >= 1000) { System.out.println("P1 wins! Score: "+ (nd * p2p));break; }

            dp = getdicePosition(dp);
            nd += 3;

            p2 = move(p2, dp);
            p2p += p2;

            if(p2p >= 1000) { System.out.println("P2 wins! Score: "+ (nd * p1p));break; }
        }
    }

    private static int getdicePosition(int dp) {
        return dp + 3;
    }

    private static int move(int p, int dp) {
        p += (dp + dp +1 + dp + 2);
        return p % 10 == 0 ? 10 : p % 10;
    }

    public static void main(String[] args) {
        String[] input1 = fileToStringArray(Problem21A.class, "Problem21Input1.txt");
        String[] input2 = fileToStringArray(Problem21A.class, "Problem21Input2.txt");

        solve(input1);
        solve(input2);
    }
}

package aoc2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem09B {
    public static void main(String[] args) {
//        var str = Problem09Input.INPUT01;
        var str = Problem09Input.INPUT02;
//        var str = Problem09Input.INPUT03;

        System.out.println(
                str.lines()
                        .map(Problem09B::calculatePrevValue)
                        .reduce(Integer::sum)
                        .orElse(0)
        );
    }

    private static Integer calculatePrevValue(String s) {
        var seq = parse(s);
        var seqList = createSeqList(seq);
        return getPrevValue(seqList);
    }

    private static List<Integer> parse(String s) {
        return Arrays.stream(s.split("\\s+")).map(Integer::parseInt).toList();
    }

    private static List<List<Integer>> createSeqList(List<Integer> seq) {
        var seqList = new ArrayList<List<Integer>>();
        var curSeq = seq;
        while(!allZero(curSeq)) {
            seqList.add(curSeq);
            curSeq = generateNextSeq(curSeq);
       }
        return seqList;
    }

    private static List<Integer> generateNextSeq(List<Integer> curSeq) {
        var nextSeq = new ArrayList<Integer>();
        for (int i = 0; i < curSeq.size()-1; i++) {
            nextSeq.add(curSeq.get(i+1) - curSeq.get(i));
        }
        return nextSeq;
    }

    private static boolean allZero(List<Integer> seq) {
        for (int num: seq) {
            if (num != 0) return false;
        }
        return true;
    }

    private static int getPrevValue(List<List<Integer>> seqList) {
        var value = 0;
        for (int i = seqList.size()-1; i >= 0 ; i--) {
            value = seqList.get(i).get(0) - value;
        }
        return value;
    }

}

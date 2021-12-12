package aoc2021;

import aoc.Graph;

import java.util.ArrayList;
import java.util.List;

import static aoc.Utils.fileToStringArray;

public class Problem12B {

    private static int count = 0;

    private static int countPaths(String[] input) {
        count = 0;
        String[][] strs = new String[input.length][2];
        for (int i = 0; i < input.length; i++) {
            strs[i] = input[i].split("-");
        }
        Graph<String> graph = Graph.convertFromEdgePairs(strs);

        List<String> path = new ArrayList<>();
        path.add("start");
        findPath(graph, path, "start");

        return count;
    }

    private static void findPath(Graph<String> graph, List<String> path, String node) {
        for (String to: graph.getEdgeVerticesFor(node)) {
            if (to.equals("end")) { count++; }
            else if (canVisit(path, to)) {
                findPath(graph, copyAndAdd(path, to), to);
            }
        }
    }

    public static List<String> copyAndAdd(List<String> path, String to) {
        List<String> newList = new ArrayList<>(path);
        newList.add(to);
        return newList;
    }

    private static boolean canVisit(List<String> path, String to) {
        return !isLower(to) || (!to.equals("start") && onlyOneLowerTwice(path, to));
    }

    private static boolean onlyOneLowerTwice(List<String> path, String to) {
        int c = countNode(path, to);
        if (c >= 2) return false;
        if (c == 1) {
            for (String n1 : path) {
                if (isLower(n1)) { // This was !n1.equals("start") && isLower(n1). But !n1.equals("start") is removed because start can only appear once in a given path
                    int c2 = countNode(path, n1);

                    if (c2 > 1) return false;
                }
            }
        }
        return true; // c == 0
    }

    private static int countNode(List<String> path, String to) {
        int c = 0;
        for (String n: path) {
            if (n.equals(to)) c++;
        }
        return c;
    }

    private static boolean isLower(String to) {
        return to.equals(to.toLowerCase());
    }

    public static void main(String[] args) {
        String[] input1 = fileToStringArray(Problem12B.class, "Problem12Input1.txt");
        String[] input2 = fileToStringArray(Problem12B.class, "Problem12Input2.txt");
        String[] input3 = fileToStringArray(Problem12B.class, "Problem12Input3.txt");
        String[] input4 = fileToStringArray(Problem12B.class, "Problem12Input4.txt");

        System.out.println(countPaths(input1));
        System.out.println(countPaths(input2));
        System.out.println(countPaths(input3));
        System.out.println(countPaths(input4));
    }

}

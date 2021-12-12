package aoc2021;

import aoc.Graph;

import java.util.ArrayList;
import java.util.List;

import static aoc.Utils.fileToStringArray;

public class Problem12A {

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
        return !isLower(to) || (isLower(to) && !alreadyVisited(path, to));
    }

    private static boolean isLower(String to) {
        return to.equals(to.toLowerCase());
    }

    private static boolean alreadyVisited(List<String> path, String to) {
        for (String n: path) {
            if (n.equals(to)) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        String[] input1 = fileToStringArray(Problem12A.class, "Problem12Input1.txt");
        String[] input2 = fileToStringArray(Problem12A.class, "Problem12Input2.txt");
        String[] input3 = fileToStringArray(Problem12A.class, "Problem12Input3.txt");
        String[] input4 = fileToStringArray(Problem12A.class, "Problem12Input4.txt");

        System.out.println(countPaths(input1));
        System.out.println(countPaths(input2));
        System.out.println(countPaths(input3));
        System.out.println(countPaths(input4));
    }

}

package aoc2021;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static aoc.Utils.copyAndAdd;
import static aoc.Utils.fileToStringArray;



public class Problem12A {

    private static int count = 0;

    private static int countPaths(String[] input, boolean multiple) {
        count = 0;
        Map<String, List<String>> graph = new HashMap<>();
        for (String str: input) {
            String[] strs = str.split("-");
            addToGraph(graph, strs[0], strs[1]);
            addToGraph(graph, strs[1], strs[0]);
        }

        List<String> path = new ArrayList<>();
        path.add("start");
        findPath(graph, path, "start", multiple);

        return count;
    }

    private static void findPath(Map<String, List<String>> graph, List<String> path, String node, boolean multiple) {
        for (String to: graph.get(node)) {
            if (to.equals("end")) { count++; }
            else if (canVisit(path, to, multiple)) {
                findPath(graph, copyAndAdd(path, to), to, multiple);
            }
        }
    }

    private static boolean canVisit(List<String> path, String to, boolean multiple) {
        return multiple
        ? !isLower(to) || (!to.equals("start") && onlyOneLowerTwice(path, to))
        : !isLower(to) || (isLower(to) && !alreadyVisited(path, to));
    }

    private static boolean onlyOneLowerTwice(List<String> path, String to) {
        int c = countNode(path, to);
        if (c >= 2) return false;
        if (c == 1) {
            for (String n1 : path) {
                if (!n1.equals("start") && isLower(n1)) {
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

    private static boolean alreadyVisited(List<String> path, String to) {
        for (String n: path) {
            if (n.equals(to)) return true;
        }
        return false;
    }

    private static void addToGraph(Map<String, List<String>> graph, String a, String b) {
        if (!graph.containsKey(a)) {
            graph.put(a, new ArrayList<>());
            graph.get(a).add(b);
        } else {
            graph.get(a).add(b);
        }
    }

    public static void main(String[] args) {
        String[] input1 = fileToStringArray(Problem12A.class, "Problem12Input1.txt");
        String[] input2 = fileToStringArray(Problem12A.class, "Problem12Input2.txt");
        String[] input3 = fileToStringArray(Problem12A.class, "Problem12Input3.txt");
        String[] input4 = fileToStringArray(Problem12A.class, "Problem12Input4.txt");

        System.out.println(countPaths(input1, false));
        System.out.println(countPaths(input2, false));
        System.out.println(countPaths(input3, false));
        System.out.println(countPaths(input4, false));

        System.out.println(countPaths(input1, true));
        System.out.println(countPaths(input2, true));
        System.out.println(countPaths(input3, true));
        System.out.println(countPaths(input4, true));
    }

}

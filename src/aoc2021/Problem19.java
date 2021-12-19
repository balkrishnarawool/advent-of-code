package aoc2021;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.*;
import java.util.stream.Collectors;

import static aoc.Utils.fileToStringArray;

public class Problem19 {

    @AllArgsConstructor(staticName = "of") @ToString @EqualsAndHashCode private static class T3 { int x; int y; int z; }

    private interface Orient { T3 apply(T3 t); }
    // Orientations
    static Orient XYZ = t -> T3.of(t.x, t.y, t.z);  static Orient XYz = t -> T3.of(t.x, t.y, -t.z);  static Orient XyZ = t -> T3.of(t.x, -t.y, t.z);  static Orient Xyz = t -> T3.of(t.x, -t.y, -t.z);
    static Orient xYZ = t -> T3.of(-t.x, t.y, t.z); static Orient xYz = t -> T3.of(-t.x, t.y, -t.z); static Orient xyZ = t -> T3.of(-t.x, -t.y, t.z); static Orient xyz = t -> T3.of(-t.x, -t.y, -t.z);
    static Orient YZX = t -> T3.of(t.y, t.z, t.x);  static Orient YZx = t -> T3.of(t.y, t.z, -t.x);  static Orient YzX = t -> T3.of(t.y, -t.z, t.x);  static Orient Yzx = t -> T3.of(t.y, -t.z, -t.x);
    static Orient yZX = t -> T3.of(-t.y, t.z, t.x); static Orient yZx = t -> T3.of(-t.y, t.z, -t.x); static Orient yzX = t -> T3.of(-t.y, -t.z, t.x); static Orient yzx = t -> T3.of(-t.y, -t.z, -t.x);
    static Orient ZXY = t -> T3.of(t.z, t.x, t.y);  static Orient ZXy = t -> T3.of(t.z, t.x, -t.y);  static Orient ZxY = t -> T3.of(t.z, -t.x, t.y);  static Orient Zxy = t -> T3.of(t.z, -t.x, -t.y);
    static Orient zXY = t -> T3.of(-t.z, t.x, t.y); static Orient zXy = t -> T3.of(-t.z, t.x, -t.y); static Orient zxY = t -> T3.of(-t.z, -t.x, t.y); static Orient zxy = t -> T3.of(-t.z, -t.x, -t.y);

    static Orient XZY = t -> T3.of(t.x, t.z, t.y);  static Orient XZy = t -> T3.of(t.x, t.z, -t.y);  static Orient XzY = t -> T3.of(t.x, -t.z, t.y);  static Orient Xzy = t -> T3.of(t.x, -t.z, -t.y);
    static Orient xZY = t -> T3.of(-t.x, t.z, t.y); static Orient xZy = t -> T3.of(-t.x, t.z, -t.y); static Orient xzY = t -> T3.of(-t.x, -t.z, t.y); static Orient xzy = t -> T3.of(-t.x, -t.z, -t.y);
    static Orient YXZ = t -> T3.of(t.y, t.x, t.z);  static Orient YXz = t -> T3.of(t.y, t.x, -t.z);  static Orient YxZ = t -> T3.of(t.y, -t.x, t.z);  static Orient Yxz = t -> T3.of(t.y, -t.x, -t.z);
    static Orient yXZ = t -> T3.of(-t.y, t.x, t.z); static Orient yXz = t -> T3.of(-t.y, t.x, -t.z); static Orient yxZ = t -> T3.of(-t.y, -t.x, t.z); static Orient yxz = t -> T3.of(-t.y, -t.x, -t.z);
    static Orient ZYX = t -> T3.of(t.z, t.y, t.x);  static Orient ZYx = t -> T3.of(t.z, t.y, -t.x);  static Orient ZyX = t -> T3.of(t.z, -t.y, t.x);  static Orient Zyx = t -> T3.of(t.z, -t.y, -t.x);
    static Orient zYX = t -> T3.of(-t.z, t.y, t.x); static Orient zYx = t -> T3.of(-t.z, t.y, -t.x); static Orient zyX = t -> T3.of(-t.z, -t.y, t.x); static Orient zyx = t -> T3.of(-t.z, -t.y, -t.x);

    private static Orient[] orients = new Orient[]{
            XYZ, XYz, XyZ, Xyz,
            xYZ, xYz, xyZ, xyz,
            YZX, YZx, YzX, Yzx,
            yZX, yZx, yzX, yzx,
            ZXY, ZXy, ZxY, Zxy,
            zXY, zXy, zxY, zxy,

            XZY, XZy, XzY, Xzy,
            xZY, xZy, xzY, xzy,
            YXZ, YXz, YxZ, Yxz,
            yXZ, yXz, yxZ, yxz,
            ZYX, ZYx, ZyX, Zyx,
            zYX, zYx, zyX, zyx
    };

    private static void solve(String[] input) {
        Map<Integer, List<T3>> map = readInput(input);
        boolean[] done = new boolean[map.size()];
        List<Integer> connected = new ArrayList<>();
        connected.add(0);
        Set<T3> set = new HashSet<>();
        set.add(T3.of(0 ,0 ,0));
        while (!allScannersDoneOrConnected(done, connected)) {
            int scanner = getConnectedScanner(connected);
            updateConnectedScanners(scanner, map, done, connected, set);
            done[scanner] = true;
            System.out.println("!");
        }
        countBeacons(map, set);
    }

    private static void countBeacons(Map<Integer, List<T3>> map, Set<T3> s2) {
        Set<T3> set = new HashSet<>();
        for (int i: map.keySet()) {
            List<T3> l = map.get(i);
            for (T3 t: l) {
                set.add(t);
            }

        }
        System.out.println(set.size());

        int max = Integer.MIN_VALUE;
        for (T3 t1: s2) {
            for (T3 t2: s2) {
                int d = Math.abs(t1.x - t2.x) + Math.abs(t1.y - t2.y) + Math.abs(t1.z - t2.z);
                max = Math.max(max, d);
            }
        }
        System.out.println(max);
    }

    private static Map<Integer, List<T3>> readInput(String[] input) {
        Map<Integer, List<T3>> map = new HashMap<>();
        int si = 0;
        for (int i = 0; i < input.length; i++) {
            String str = input[i];
            if (str.contains("--- scanner ")) {
                String s = str.substring("--- scanner ".length()).split(" ")[0];
                si = Integer.parseInt(s);
                map.put(si, new ArrayList<>());
            } else {
                if (str.length() > 0) {
                    String[] strs = str.split(",");
                    int x = Integer.parseInt(strs[0]);
                    int y = Integer.parseInt(strs[1]);
                    int z = Integer.parseInt(strs[2]);
                    map.get(si).add(T3.of(x, y, z));
                }
            }
        }
        return map;
    }

    private static void updateConnectedScanners(int s, Map<Integer, List<T3>> map, boolean[] done, List<Integer> connected, Set<T3> set) {
        for (int s1: map.keySet()) {
            if (s != s1) {
                System.out.print("checking s1: "+ s1);
                if (!done[s1] && !connected.contains(s1)) { //!connectedOrDone(s1, done, connected)
                    List<T3> l1 = map.get(s);
                    List<T3> l2 = map.get(s1);
                    outer: for (Orient o: orients) {
                        for (T3 p1: l1) {
                            for (T3 p2: l2) {
                                T3 offset = getOffset(p1, p2, o);
                                if (match(l1, l2, offset, o)) {
                                    connected.add(s1);
                                    set.add(offset);
                                    map.put(s1, transform(l2, offset, o));
                                    break outer;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private static List<T3> transform(List<T3> l2, T3 offset, Orient o) {
        return l2.stream()
                .map(t -> o.apply(t))
                .map(t -> T3.of(t.x + offset.x, t.y + offset.y, t.z + offset.z))
                .collect(Collectors.toList());
    }

    private static T3 getOffset(T3 p1, T3 p2, Orient o) {
        T3 t = o.apply(p2);
        return T3.of(p1.x - t.x, p1.y - t.y, p1.z - t.z);
    }

    private static boolean match(List<T3> l1, List<T3> l2, T3 offset, Orient o) {
        boolean[] a = new boolean[l2.size()];
        int c = 0;
        for (T3 p1: l1) {
            for (int i = 0; i < l2.size(); i++) {
                if (!a[i]) {
                    T3 p2 = l2.get(i);
                    if (match(p1, p2, offset, o)) { a[i] = true; c++; }
                }
            }
        }
        return c >= 12;
    }

    private static boolean match(T3 p1, T3 p2, T3 offset, Orient o) {
        T3 t = o.apply(p2);
        return p1.x - t.x == offset.x && p1.y - t.y == offset.y && p1.z - t.z == offset.z;
    }

    private static int getConnectedScanner(List<Integer> connected) {
        return connected.remove(0);
    }

    private static boolean allScannersDoneOrConnected(boolean[] done, List<Integer> connected) {
        for (int i = 0; i < done.length; i++) {
            if (!done[i] && !connected.contains(i)) { return false; }
        }
        return true;
    }


    public static void main(String[] args) {
        String[] input1 = fileToStringArray(Problem19.class, "Problem19Input1.txt");
        String[] input2 = fileToStringArray(Problem19.class, "Problem19Input2.txt");

        solve(input1);
        solve(input2);
    }

}

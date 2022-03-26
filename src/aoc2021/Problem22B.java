package aoc2021;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static aoc.Utils.fileToStringArray;

public class Problem22B {

    @Value @AllArgsConstructor private static class CubeInstruction { Cube cube; boolean on; }

    @Value @AllArgsConstructor
    private static class Cube {
        int minX; int maxX; int minY; int maxY; int minZ; int maxZ;

        private boolean contains(int x, int y, int z) {
            return x >= minX && x <= maxX && y >= minY && y <= maxY && z >= minZ && z <= maxZ;
        }
        private Optional<Cube> intersection(Cube other) {
            int minX = Math.max(this.minX, other.minX);
            int maxX = Math.min(this.maxX, other.maxX);
            int minY = Math.max(this.minY, other.minY);
            int maxY = Math.min(this.maxY, other.maxY);
            int minZ = Math.max(this.minZ, other.minZ);
            int maxZ = Math.min(this.maxZ, other.maxZ);
            if (minX > maxX || minY > maxY || minZ > maxZ) {
                return Optional.empty();
            }
            return Optional.of(new Cube(minX, maxX, minY, maxY, minZ, maxZ));
        }
        private long volume() {
            long x = maxX - minX + 1;
            long y = maxY - minY + 1;
            long z = maxZ - minZ + 1;
            return x * y * z;
        }
    }

    public static void solve(String[] lines) {
        CubeInstruction[] instructions = new CubeInstruction[lines.length];
        Pattern pattern = Pattern.compile("(on|off) x=(-?\\d+)..(-?\\d+),y=(-?\\d+)..(-?\\d+),z=(-?\\d+)..(-?\\d+)");
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            Matcher matcher = pattern.matcher(line);
            if (matcher.matches()) {
                boolean instruction = matcher.group(1).equals("on");
                int minX = Integer.parseInt(matcher.group(2));
                int maxX = Integer.parseInt(matcher.group(3));
                int minY = Integer.parseInt(matcher.group(4));
                int maxY = Integer.parseInt(matcher.group(5));
                int minZ = Integer.parseInt(matcher.group(6));
                int maxZ = Integer.parseInt(matcher.group(7));
                instructions[i] = new CubeInstruction(new Cube(minX, maxX, minY, maxY, minZ, maxZ), instruction);
            }
        }

        Map<Cube, Long> cubes = new HashMap<Cube, Long>();
        for (CubeInstruction instruction : instructions) {
            Cube cube1 = instruction.cube;
            Map<Cube, Long> update = new HashMap<Cube, Long>();
            cubes.forEach((cube2, value) -> cube1.intersection(cube2).ifPresent(cube -> update.merge(cube, -value, Long::sum)));
            if (instruction.on) {
                update.merge(cube1, 1L, Long::sum);
            }
            update.forEach((cube, count) -> cubes.merge(cube, count, Long::sum));
        }

        System.out.println(cubes.entrySet().stream().mapToLong(e -> e.getKey().volume() * e.getValue()).sum());
    }

    public static void main(String[] args) {
        String[] input1 = fileToStringArray(Problem22B.class, "Problem22Input1.txt");
        String[] input2 = fileToStringArray(Problem22B.class, "Problem22Input2.txt");

        solve(input1);
        solve(input2);
    }
}
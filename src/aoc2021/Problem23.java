package aoc2021;

import aoc.Utils;
import lombok.EqualsAndHashCode;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Problem23 {

    static int solve1(List<String> data) {
        State state = buildState(data);
    
        MemoizedLeastEnergyCalculator memoizer = new MemoizedLeastEnergyCalculator();
        return memoizer.apply(state);
    }

    static int solve2(List<String> data) {
        data.add(3, "  #D#C#B#A#");
        data.add(4, "  #D#B#A#C#");

        return solve1(data);
    }

    private static Optional<Integer> calculateEnergyForMove(State state, char type, int start, int end) {
        int current = start;
        int step = Integer.signum(end - start);
        int steps = 0;

        while (current != end) {
            current += step;
            if (state.hallway.getOrDefault(current, '.') != '.') { return Optional.empty(); }
            steps++;
        }

        if (start == 2 || start == 4 || start == 6 || start == 8) {
            Stack<Character> stack = state.rooms.get(getRoomPosition(start));
            steps += state.stackSize - stack.size() + 1;
        } else {
            Stack<Character> stack = state.rooms.get(getRoomPosition(end));
            for (Character ch : stack) {
                if (ch != type) {
                    return Optional.empty();
                }
            }
            steps += state.stackSize - stack.size();
        }
        return Optional.of(steps * getCost(type));
    }

    private static int getCost(Character type) {
        switch (type) {
            case 'A': return 1;
            case 'B': return 10;
            case 'C': return 100;
            case 'D': return 1000;
            default: throw new IllegalArgumentException();
        }
    }

    private static int getHallwayPosition(Character type) {
        switch (type) {
            case 'A': return 2;
            case 'B': return 4;
            case 'C': return 6;
            case 'D': return 8;
            default: throw new IllegalArgumentException();
        }
    }

    private static Character getRoomPosition(int hallwayPosition) {
        switch (hallwayPosition) {
            case 2: return 'A';
            case 4: return 'B';
            case 6: return 'C';
            case 8: return 'D';
            default: throw new IllegalArgumentException();
        }
    }

    private static State buildState(List<String> data) {
        Map<Integer, Character> hallway = new HashMap<>();
        hallway.put(0, '.');
        hallway.put(1, '.');
        hallway.put(3, '.');
        hallway.put(5, '.');
        hallway.put(7, '.');
        hallway.put(9, '.');
        hallway.put(10, '.');

        Map<Character, Stack<Character>> rooms = new HashMap<>();
        rooms.put('A', new Stack<>());
        rooms.put('B', new Stack<>());
        rooms.put('C', new Stack<>());
        rooms.put('D', new Stack<>());

        for (int i = data.size() - 2; i >= 2 ; i--) {
            String line = data.get(i);
            rooms.get('A').push(line.charAt(3));
            rooms.get('B').push(line.charAt(5));
            rooms.get('C').push(line.charAt(7));
            rooms.get('D').push(line.charAt(9));
        }

        return new State(hallway, rooms);
    }

    @EqualsAndHashCode
    private static class State {
        private final Map<Integer, Character> hallway;
        private final Map<Character, Stack<Character>> rooms;
        private final int stackSize;

        private State(Map<Integer, Character> hallway, Map<Character, Stack<Character>> rooms) {
            this.hallway = hallway;
            this.rooms = rooms;
            stackSize = rooms.get('A').size();
        }

        private State(State otherState) {
            hallway = new HashMap<>(otherState.hallway);
            rooms = new HashMap<>();
            for (Map.Entry<Character, Stack<Character>> entry : otherState.rooms.entrySet()) {
                @SuppressWarnings("unchecked")
                Stack<Character> copy = (Stack<Character>) entry.getValue().clone();
                rooms.put(entry.getKey(), copy);
            }
            stackSize = otherState.stackSize;
        }

        public boolean isCompleted() {
            return isOrdered('A') && rooms.get('A').size() == stackSize &&
                    isOrdered('B') && rooms.get('B').size() == stackSize &&
                    isOrdered('C') && rooms.get('C').size() == stackSize &&
                    isOrdered('D') && rooms.get('D').size() == stackSize;
        }

        public boolean isOrdered(Character type) {
            Stack<Character> stack = rooms.get(type);

            for (Character character : stack) {
                if (type != character) {
                    return false;
                }
            }
            return true;
        }
    }

    private static class MemoizedLeastEnergyCalculator implements Function<State, Integer>{
        private Map<State, Integer> map = new HashMap<>();

        @Override
        public Integer apply(State state) {
            if (map.containsKey(state)) return map.get(state);
            else return calculateLeastEnergy(state);
        }

        private Integer calculateLeastEnergy(State state) {
            // Move pods from hallway to rooms
            State nextState = new State(state);
            int hallwayCost = 0;
            boolean isMoved = true;

            while (isMoved) {
                isMoved = false;
                for (int pos : nextState.hallway.keySet()) {
                    char type = nextState.hallway.get(pos);
                    if (type != '.') {
                        Optional<Integer> cost = calculateEnergyForMove(nextState, type, pos, getHallwayPosition(type));
                        if (cost.isPresent()) {
                            isMoved = true;
                            hallwayCost += cost.get();

                            nextState.hallway.put(pos, '.');
                            nextState.rooms.get(type).push(type);
                        }
                    }
                }
            }

            if (nextState.isCompleted()) {
                return hallwayCost;
            }

            // Move pods from rooms to hallway
            int bestCost = Integer.MAX_VALUE;

            for (char type : nextState.rooms.keySet()) {
                if (!nextState.isOrdered(type)) {
                    Stack<Character> stack = nextState.rooms.get(type);

                    for (int i : nextState.hallway.keySet()) {
                        Optional<Integer> cost = calculateEnergyForMove(nextState, stack.peek(), getHallwayPosition(type), i);
                        if (cost.isPresent()) {
                            State newState = new State(nextState);
                            newState.hallway.put(i, newState.rooms.get(type).pop());

                            int followingStepsCost = MemoizedLeastEnergyCalculator.this.apply(newState);
                            if (followingStepsCost != Integer.MAX_VALUE) {
                                bestCost = Math.min(bestCost, followingStepsCost + cost.get() + hallwayCost);
                            }
                        }
                    }
                }
            }

            return bestCost;
        }
    }

    public static void main(String[] args) {
        String[] input1 = Utils.fileToStringArray(Problem23.class, "Problem23Input1.txt");
        String[] input2 = Utils.fileToStringArray(Problem23.class, "Problem23Input2.txt");

        System.out.println(solve1(Arrays.stream(input1).collect(Collectors.toList())));
        System.out.println(solve1(Arrays.stream(input2).collect(Collectors.toList())));

        System.out.println(solve2(Arrays.stream(input1).collect(Collectors.toList())));
        System.out.println(solve2(Arrays.stream(input2).collect(Collectors.toList())));
    }
}
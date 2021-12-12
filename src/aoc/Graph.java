package aoc;

import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@NoArgsConstructor(staticName = "create")
@Value
public class Graph<T> {
    Map<T, List<T>> verticesMap = new HashMap<>();

    public void traverseVertices(Consumer<T> cons) {
        for (T t: verticesMap.keySet()) {
            cons.accept(t);
        }
    }

    // This will process an edge twice once from start to end and once from end to start
    public void traverseEdges(Consumer<Tuple2<T>> cons) {
        for (T t: verticesMap.keySet()) {
            for (T t1: verticesMap.get(t)) {
                cons.accept(Tuple2.of(t, t1));
            }
        }
    }

    public static <T> Graph<T> convertFromEdgePairs(T[][] input) {
        Graph<T> graph = Graph.create();
        for (T[] pair: input) {
            graph.addEdge(pair[0], pair[1]);
            graph.addEdge(pair[1], pair[0]);
        }
        return graph;
    }

    private void addEdge(T a, T b) {
        if (!verticesMap.containsKey(a)) {
            verticesMap.put(a, new ArrayList<>());
        }
        verticesMap.get(a).add(b);
    }

    public static void main(String[] args) {
        Integer[][] arr = {
                {1, 2},
                {2, 4},
                {3, 6},
                {6, 8},
                {2, 3}
        };

        Graph<Integer> g = Graph.convertFromEdgePairs(arr);
        g.traverseVertices(System.out::println);
        g.traverseEdges(t -> System.out.println(t.left + " - " + t.right));

    }

    public List<T> getEdgeVerticesFor(T node) {
        return verticesMap.get(node);
    }
}
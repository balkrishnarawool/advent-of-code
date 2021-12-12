package aoc;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;

@RequiredArgsConstructor(staticName = "of")
@Value
public class Tree<T> {
    @NonNull T value;
    List<Tree<T>> childNodes = new ArrayList<>();

    public void traverse(Consumer<T> cons) {
        cons.accept(value);
        for (Tree<T> t: childNodes) {
            t.traverse(cons);
        }
    }

    // Finds first node with value i
    public Tree<T> findNode(T i) {
        Tree<T> tree = null;
        for (Tree<T> t: childNodes) {
            tree = (tree == null) ? (t.value.equals(i) ? t : t.findNode(i)) : tree;
        }
        return tree;
    }

    public <U> U reduce(U identity, BiFunction<U, T, U> func) {
        U result = func.apply(identity, value);
        for (Tree<T> t: childNodes) {
            result = t.reduce(result, func);
        }
        return result;
    }

    public static <T> Tree<T> convertFrom2DArray(T[][] arr) {
        Tree<T> root = null;
        for (T[] ia: arr) {
            Tree<T> tree = null;
            for (T i: ia) {
                if (tree == null) {
                    if (root == null) { tree = Tree.of(i); root = tree; }
                    else {
                        Tree<T> node = root.findNode(i);
                        tree = (node == null) ? Tree.of(i) : node;
                    }
                } else {
                    tree.getChildNodes().add(Tree.of(i));
                }
            }
        }
        return root;
    }

    public static void main(String[] args) {
        Integer[][] arr = {
                {1, 2, 3},
                {2, 4, 5},
                {3, 6, 7},
                {6, 8, 9},
                {8, 10}
        };
        Tree<Integer> root = Tree.convertFrom2DArray(arr);
        root.traverse(t -> System.out.println(t +" "));
        System.out.println(root.reduce(0, Integer::sum));
        System.out.println(root.reduce("", (a, b) -> a + " " + b));
    }
}
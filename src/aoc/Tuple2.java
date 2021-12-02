package aoc;

public class Tuple2<T> {
    public final T left;
    public final T right;

    private Tuple2(T left, T right) {
        this.left = left;
        this.right = right;
    }

    public static <T> Tuple2 of(T left, T right) {
        return new Tuple2(left, right);
    }
}

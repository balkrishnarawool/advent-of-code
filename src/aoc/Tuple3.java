package aoc;

public class Tuple3<T> {
    public final T first;
    public final T second;
    public final T third;

    public Tuple3(T first, T second, T third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public static <T> Tuple3 of(T first, T second, T third) {
        return new Tuple3(first, second, third);
    }
}

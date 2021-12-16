package aoc;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Value;

@AllArgsConstructor(staticName = "of")
@EqualsAndHashCode
public class Tuple3<T> {
    public final T first;
    public final T second;
    public final T third;
}

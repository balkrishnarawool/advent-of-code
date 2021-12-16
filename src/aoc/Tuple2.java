package aoc;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@AllArgsConstructor(staticName = "of")
@EqualsAndHashCode
@ToString
public class Tuple2<T> {
    public final T left;
    public final T right;
}

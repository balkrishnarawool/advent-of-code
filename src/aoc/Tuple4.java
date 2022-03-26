package aoc;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@AllArgsConstructor(staticName = "of")
@EqualsAndHashCode
@ToString
public class Tuple4<T> {
    public final T a;
    public final T b;
    public final T c;
    public final T d;
}

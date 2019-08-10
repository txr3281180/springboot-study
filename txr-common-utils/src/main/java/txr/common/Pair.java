package txr.common;

import lombok.*;

@ToString
@EqualsAndHashCode
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Pair<S, T> {

    private final @NonNull S first;
    private final @NonNull T second;

    /**
     * Creates a new {@link Pair} for the given elements.
     *
     * @param first must not be {@literal null}.
     * @param second must not be {@literal null}.
     * @return
     */
    public static <S, T> Pair<S, T> of(S first, T second) {
        return new Pair<S, T>(first, second);
    }

    /**
     * Returns the first element of the {@link Pair}.
     *
     * @return
     */
    public S getFirst() {
        return first;
    }

    /**
     * Returns the second element of the {@link Pair}.
     *
     * @return
     */
    public T getSecond() {
        return second;
    }
}

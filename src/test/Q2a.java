package test;


import java.util.function.Function;

public class Q2a<T, C, E> {

    public static <T, C, E> Function<T, E> compose(Function<T, C> fx, Function<C, E> gx) {
        return fx.andThen(gx);
    }
}

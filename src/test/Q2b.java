package test;


import java.util.function.Function;

public class Q2b<E> {

    public static <E> Function<E, E> multiCompose(Function<E, E> fx, Integer time) {
        Function<E, E> func = fx;
        for (int i = 0; i < time - 1; i++) {
            func = func.compose(fx);
        }
        return func;
    }
}

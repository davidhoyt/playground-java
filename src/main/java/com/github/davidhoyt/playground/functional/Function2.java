package com.github.davidhoyt.playground.functional;

import java.util.function.BiFunction;

@FunctionalInterface
public interface Function2<T1, T2, R> extends Function1<T1, Function1<T2, R>> {
    R apply(T1 t1, T2 t2);

    @Override
    default Function1<T2, R> apply(T1 t1) {
        return (T2 t2) -> apply(t1, t2);
    }

    default BiFunction<T1, T2, R> toBiFunction() {
        return this::apply;
    }

    static <T1, T2, R> Function2<T1, T2, R> uncurry(Function1<T1, Function1<T2, R>> curried) {
        return (T1 t1, T2 t2) -> curried.apply(t1).apply(t2);
    }
}

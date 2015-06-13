package com.github.davidhoyt.playground.functional;

@FunctionalInterface
public interface Function3<T1, T2, T3, R> extends Function2<T1, T2, Function1<T3, R>> {
    R apply(T1 t1, T2 t2, T3 t3);

    @Override
    default Function1<T3, R> apply(T1 t1, T2 t2) {
        return (T3 t3) -> apply(t1, t2, t3);
    }
}

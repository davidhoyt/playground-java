package com.github.davidhoyt.playground.functional;

@FunctionalInterface
public interface Function4<T1, T2, T3, T4, R> extends Function3<T1, T2, T3, Function1<T4, R>> {
    R apply(T1 t1, T2 t2, T3 t3, T4 t4);

    @Override
    default Function1<T4, R> apply(T1 t1, T2 t2, T3 t3) {
        return (T4 t4) -> apply(t1, t2, t3, t4);
    }
}

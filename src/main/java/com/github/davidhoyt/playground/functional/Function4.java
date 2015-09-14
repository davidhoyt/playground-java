package com.github.davidhoyt.playground.functional;

@FunctionalInterface
public interface Function4<T1, T2, T3, T4, R> extends Function3<T1, T2, T3, Function1<T4, R>> {
    R apply(T1 t1, T2 t2, T3 t3, T4 t4);

    @Override
    default Function1<T4, R> apply(T1 t1, T2 t2, T3 t3) {
        return (T4 t4) -> apply(t1, t2, t3, t4);
    }

    static <T1, T2, T3, T4, R> Function4<T1, T2, T3, T4, R> uncurry(Function1<T1, Function1<T2, Function1<T3, Function1<T4, R>>>> curried) {
        return (T1 t1, T2 t2, T3 t3, T4 t4) -> curried.apply(t1).apply(t2).apply(t3).apply(t4);
    }
}

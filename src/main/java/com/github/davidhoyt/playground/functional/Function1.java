package com.github.davidhoyt.playground.functional;

@FunctionalInterface
public interface Function1<T1, R> extends java.util.function.Function<T1, R>, Function0<Function1<T1, R>>, Curried<Function1<T1, R>>, Uncurried<Function1<T1, R>> {
    @Override
    R apply(T1 t1);

    @Override
    default Function1<T1, R> apply() {
        return this;
    }

    @Override
    default Function1<T1, R> curried() {
        return this;
    }

    @Override
    default Function1<T1, R> uncurried() {
        return this;
    }
}

package com.github.davidhoyt.playground.functional;

import java.util.concurrent.Callable;

@FunctionalInterface
public interface Function0<R> extends Function, Callable<R> {
    R apply();

    @Override
    default R call() throws Exception {
        return apply();
    }

    default Function0<R> curried() {
        return this;
    }

    default Function0<R> uncurried() {
        return this;
    }
}

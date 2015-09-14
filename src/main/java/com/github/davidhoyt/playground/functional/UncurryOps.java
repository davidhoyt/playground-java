package com.github.davidhoyt.playground.functional;

import com.github.davidhoyt.playground.functional.Function0;
import com.github.davidhoyt.playground.functional.Function1;
import com.github.davidhoyt.playground.functional.Function2;
import com.github.davidhoyt.playground.functional.Function3;
import com.github.davidhoyt.playground.functional.Function4;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public interface UncurryOps {
    default <R> Function0<R> uncurry0(R given) {
        return () -> given;
    }

    default <R> Function1<R, R> uncurry1(UnaryOperator<R> given) {
        return given::apply;
    }

    default <T1, R> Function1<T1, R> uncurry1(Function1<T1, R> curried) {
        return curried::apply;
    }

    default <T1, T2, R> Function2<T1, T2, R> uncurry2(Function1<T1, Function1<T2, R>> curried) {
        return (T1 t1, T2 t2) -> curried.apply(t1).apply(t2);
    }

    default <T1, T2, T3, R> Function3<T1, T2, T3, R> uncurry3(Function1<T1, Function1<T2, Function1<T3, R>>> curried) {
        return (T1 t1, T2 t2, T3 t3) -> curried.apply(t1).apply(t2).apply(t3);
    }

    default <T1, T2, T3, T4, R> Function4<T1, T2, T3, T4, R> uncurry4(Function1<T1, Function1<T2, Function1<T3, Function1<T4, R>>>> curried) {
        return Function4.uncurry(curried);
    }
}

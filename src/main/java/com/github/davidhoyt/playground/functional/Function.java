package com.github.davidhoyt.playground.functional;

@SuppressWarnings("unchecked")
public interface Function {
    default <R> Function0<R> unsafeAsFunction0() {
        return (Function0<R>)this;
    }

    default <T1, R> Function1<T1, R> unsafeAsFunction1() {
        return (Function1<T1, R>)this;
    }

    default <T1, T2, R> Function2<T1, T2, R> unsafeAsFunction2() {
        return (Function2<T1, T2, R>)this;
    }

    default <T1, T2, T3, R> Function3<T1, T2, T3, R> unsafeAsFunction3() {
        return (Function3<T1, T2, T3, R>)this;
    }

    default <T1, T2, T3, T4, R> Function4<T1, T2, T3, T4, R> unsafeAsFunction4() {
        return (Function4<T1, T2, T3, T4, R>)this;
    }
}

package com.github.davidhoyt.playground.functional;

public interface Pure<M extends Pure<M, A>, A> {
    M pure(A given);
}

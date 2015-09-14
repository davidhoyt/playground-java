package com.github.davidhoyt.playground.functional;

/**
 * Designates a type that can return itself cast to the provided type parameter {@code T}.
 */
public interface Self<T extends Self<T>> {
    @SuppressWarnings("unchecked")
    default T self() {
        return (T)this;
    }
}

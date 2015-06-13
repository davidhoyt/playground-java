package com.github.davidhoyt.playground.functional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Defaults<T> {
    public final Map<Class<?>, T> options = new HashMap<Class<?>, T>(8, 1.0f);

    public <U extends T> U register(Class<?> cls, U given) {
        options.put(cls, given);
        return given;
    }

    public <A> Optional<T> find(A given) {
        final Class<?> givenCls = given.getClass();

        for (Map.Entry<Class<?>, T> entry : options.entrySet()) {
            final Class<?> cls = entry.getKey();
            final T candidate = entry.getValue();
            if (cls.isAssignableFrom(givenCls)) {
                return Optional.of(candidate);
            }
        }

        return Optional.empty();
    }
}
package com.github.davidhoyt.playground.functional.products;

import com.github.davidhoyt.playground.functional.Eq;
import com.github.davidhoyt.playground.functional.Equatable;
import com.github.davidhoyt.playground.functional.Show;
import com.github.davidhoyt.playground.functional.Showable;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public interface Product extends Iterable<Object>, Showable<Product>, Equatable<Product> {
    List<Object> getValues();

    default int getSize() {
        return size();
    }

    default int size() {
        return getValues().size();
    }

    @Override
    default Eq<Product> getEq() {
        return Eq.defaults.forProduct;
    }

    @Override
    default Show<Product> getShow() {
        return Show.defaults.forProduct;
    }

    default int stableHashcode() {
        return Objects.hash(getValues());
    }

    @Override
    default Iterator<Object> iterator() {
        return getValues().iterator();
    }

    default boolean isEqualTo(Object that) {
        if (that == null) return false;
        if (!(that instanceof Product)) return false;
        return getEq().areEqual(this, (Product)that);
    }

    default String asString() {
        return getShow().asString(this);
    }
}

package com.github.davidhoyt.playground.functional.products;

import com.github.davidhoyt.playground.functional.Function1;

import java.util.ArrayList;
import java.util.List;

public interface Product1<T1, P extends Product1<T1, ?>> extends Product, Function1<T1, P> {
    T1 $1();

    @Override
    default List<Object> getValues() {
        final ArrayList<Object> list = new ArrayList<>(1);
        list.add($1());
        return list;
    }

    abstract class Standard<T1, P extends Standard<T1, ?>> implements Product1<T1, P> {
        private final List<Object> values;
        private final int hash;
        private final T1 t1;

        public Standard(T1 t1) {
            this.t1 = t1;

            final ArrayList<Object> values = new ArrayList<>(1);
            values.add(t1);

            this.values = values;
            this.hash = stableHashcode();
        }

        public P copy() {
            return apply(t1);
        }

        @Override
        public T1 $1() {
            return t1;
        }

        @Override
        public List<Object> getValues() {
            return values;
        }

        @Override
        public String toString() {
            return asString();
        }

        @Override
        public boolean equals(Object obj) {
            return isEqualTo(obj);
        }

        @Override
        public int hashCode() {
            return hash;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return copy();
        }
    }
}

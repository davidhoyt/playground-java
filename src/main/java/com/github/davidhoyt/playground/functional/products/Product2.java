package com.github.davidhoyt.playground.functional.products;

import com.github.davidhoyt.playground.functional.Function2;

import java.util.ArrayList;
import java.util.List;

public interface Product2<T1, T2, P extends Product2<T1, T2, ?>> extends Product, Function2<T1, T2, P> {
    T1 $1();
    T2 $2();

    abstract class Standard<T1, T2, P extends Standard<T1, T2, ?>> implements Product2<T1, T2, P> {
        private final List<Object> values;
        private final int hash;
        private final T1 t1;
        private final T2 t2;

        public Standard(T1 t1, T2 t2) {
            this.t1 = t1;
            this.t2 = t2;

            final ArrayList<Object> values = new ArrayList<>(2);
            values.add(t1);
            values.add(t2);

            this.values = values;
            this.hash = stableHashcode();
        }

        public P copy() {
            return apply(t1, t2);
        }

        @Override
        public T1 $1() {
            return t1;
        }

        @Override
        public T2 $2() {
            return t2;
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

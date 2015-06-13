package com.github.davidhoyt.playground.functional.products;

import com.github.davidhoyt.playground.functional.Function4;

import java.util.ArrayList;
import java.util.List;

public interface Product4<T1, T2, T3, T4, P extends Product4<T1, T2, T3, T4, ?>> extends Product, Function4<T1, T2, T3, T4, P> {
    T1 $1();
    T2 $2();
    T3 $3();
    T4 $4();

    abstract class Standard<T1, T2, T3, T4, P extends Standard<T1, T2, T3, T4, ?>> implements Product4<T1, T2, T3, T4, P> {
        private final List<Object> values;
        private final int hash;
        private final T1 t1;
        private final T2 t2;
        private final T3 t3;
        private final T4 t4;

        public Standard(T1 t1, T2 t2, T3 t3, T4 t4) {
            this.t1 = t1;
            this.t2 = t2;
            this.t3 = t3;
            this.t4 = t4;

            final ArrayList<Object> values = new ArrayList<>(4);
            values.add(t1);
            values.add(t2);
            values.add(t3);
            values.add(t4);

            this.values = values;
            this.hash = stableHashcode();
        }

        public P copy() {
            return apply(t1, t2, t3, t4);
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
        public T3 $3() {
            return t3;
        }

        @Override
        public T4 $4() {
            return t4;
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

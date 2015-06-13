package com.github.davidhoyt.playground.functional.products;

import com.github.davidhoyt.playground.functional.Function3;

import java.util.ArrayList;
import java.util.List;

public interface Product3<T1, T2, T3, P extends Product3<T1, T2, T3, ?>> extends Product, Function3<T1, T2, T3, P> {
    T1 $1();
    T2 $2();
    T3 $3();

    @Override
    default List<Object> getValues() {
        final ArrayList<Object> list = new ArrayList<>(3);
        list.add($1());
        list.add($2());
        list.add($3());
        return list;
    }

    abstract class Standard<T1, T2, T3, P extends Standard<T1, T2, T3, ?>> implements Product3<T1, T2, T3, P> {
        private final List<Object> values;
        private final int hash;
        private final T1 t1;
        private final T2 t2;
        private final T3 t3;

        public Standard(T1 t1, T2 t2, T3 t3) {
            this.t1 = t1;
            this.t2 = t2;
            this.t3 = t3;

            final ArrayList<Object> values = new ArrayList<>(3);
            values.add(t1);
            values.add(t2);
            values.add(t3);

            this.values = values;
            this.hash = stableHashcode();
        }

        public P copy() {
            return apply(t1, t2, t3);
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

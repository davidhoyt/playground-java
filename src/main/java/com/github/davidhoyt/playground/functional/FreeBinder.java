package com.github.davidhoyt.playground.functional;

import java.util.function.Function;

/**
 * Free monad like data structure that allows {@code map()} and {@code flatMap()} calls to be chained without the need
 * to compose {@code (map)} or nest {@code (join)} them up front. Joining occurs when {@link #done()} is called which
 * collapses all nested {@code map()} and {@code flatMap()} calls using the provided {@link #innerMap(Function)} and
 * {@link #innerFlatMap(Function)} methods.
 */
public interface FreeBinder<M extends FreeBinder<M, A>, A> extends Self<M> {
    default M done() {
        return self();
    }

    default <N extends FreeBinder<N, B>, B> FreeBinder<N, B> flatMap(java.util.function.Function<? super A, ? extends N> fn) {
        return new FlatMap<>(this, fn);
    }

    default <N extends FreeBinder<N, B>, B> FreeBinder<N, B> map(java.util.function.Function<? super A, ? extends B> fn) {
        return new Map<>(this, fn);
    }

    <B> FreeBinder<?, B> innerFlatMap(java.util.function.Function<? super A, ? extends FreeBinder<?, B>> fn);
    <B> FreeBinder<?, B> innerMap(java.util.function.Function<? super A, ? extends B> fn);

    final class Map<M extends FreeBinder<M, A>, A, N extends FreeBinder<N, B>, B> implements FreeBinder<N, B> {
        private final FreeBinder<M, A> instance;
        private final java.util.function.Function<? super A, ? extends B> map;

        public Map(FreeBinder<M, A> instance, java.util.function.Function<? super A, ? extends B> map) {
            this.instance = instance;
            this.map = map;
        }

        @Override
        public String toString() {
            return "Map(" + instance.toString() + ")";
        }

        @Override
        public <B1> FreeBinder<?, B1> innerFlatMap(Function<? super B, ? extends FreeBinder<?, B1>> fn) {
            return instance.innerFlatMap(fn.compose(map));
        }

        @Override
        public <B1> FreeBinder<?, B1> innerMap(Function<? super B, ? extends B1> fn) {
            return instance.innerMap(map.andThen(fn));
        }
    }

    final class FlatMap<M extends FreeBinder<M, A>, A, N extends FreeBinder<N, B>, B> implements FreeBinder<N, B> {
        private final FreeBinder<M, A> instance;
        private final java.util.function.Function<? super A, ? extends N> flatMap;

        public FlatMap(FreeBinder<M, A> instance, java.util.function.Function<? super A, ? extends N> flatMap) {
            this.instance = instance;
            this.flatMap = flatMap;
        }

        @Override
        public String toString() {
            return "FlatMap(" + instance.toString() + ")";
        }

        @Override
        public <B1> FreeBinder<?, B1> innerFlatMap(Function<? super B, ? extends FreeBinder<?, B1>> fn) {
            return instance.innerFlatMap((A a) -> flatMap.apply(a).innerFlatMap(fn));
        }

        @Override
        public <B1> FreeBinder<?, B1> innerMap(Function<? super B, ? extends B1> fn) {
            return instance.innerFlatMap((A a) -> flatMap.apply(a).innerMap(fn));
        }

        @Override
        public N done() {
            return (N)instance.innerFlatMap((A a) -> flatMap.apply(a));
        }
    }

    abstract class Test<M extends Test<M, A>, A> implements FreeBinder<M, A> {
        protected final A a;

        public Test(A a) {
            this.a = a;
        }

        @Override
        public String toString() {
            return "Test(" + a.toString() + ")";
        }

        @Override
        public <B> FreeBinder<?, B> innerFlatMap(Function<? super A, ? extends FreeBinder<?, B>> fn) {
            return fn.apply(a);
        }
    }

    class Test3<A> extends Test<Test3<A>, A> {
        public Test3(A a) {
            super(a);
        }

        @Override
        public String toString() {
            return "Test3(" + a.toString() + ")";
        }

        Test3<A> what() {
            return this;
        }

        @Override
        public <B> FreeBinder<?, B> innerMap(Function<? super A, ? extends B> fn) {
            return new Test3<>(fn.apply(a));
        }
    }

    public static void main(String[] args) {
        FreeBinder<Test3<Double>, Double> fb = new Test3<String>("1")
            //.map(s -> 123)
            .flatMap(i -> {
                System.out.println("1");
                return new Test3<Double>(Double.parseDouble(i));
            })
            .map(i -> i + 2.0D)
            .flatMap(i -> { System.out.println("2") ; return new Test3<String>(Double.toString(i)); })
            .flatMap(i -> { System.out.println("3") ; return new Test3<Double>(Double.parseDouble(i) * 100.0D); });

        System.out.println(fb);
        System.out.println(fb.done());

        System.out.println(fb.done());
    }
}


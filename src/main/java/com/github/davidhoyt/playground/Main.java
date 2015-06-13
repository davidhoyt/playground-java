package com.github.davidhoyt.playground;

import com.github.davidhoyt.playground.functional.Function1;
import com.github.davidhoyt.playground.functional.Function2;
import com.github.davidhoyt.playground.functional.Function3;
import com.github.davidhoyt.playground.functional.UncurryOps;

import java.util.function.Function;

public class Main implements UncurryOps {
    public static void main(String[] args) throws Exception {
        new Main().run();
    }

    void run() throws Exception {
        final SAM test = new SAM<String>() {
            @Override
            public boolean filter(String s) {
                return s.isEmpty();
            }
        };

        needsIt("", test);
        needsIt("", s -> {
            return false;
        });

        final Function3<String, Integer, Double, String> fn3 = uncurry3((String s) -> (Integer i) -> (Double d) -> s + "-" + i + "-" + d);
        final Function2<Integer, Double, String> fn2 = uncurry2(fn3.apply("a"));
//        final Functional.Function1<Integer, Functional.Function1<Integer, Functional.Function1<Double, String>>> ccc = fn3.curried();
        final Function1<String, Function1<Integer, Function1<Double, String>>> ccc = uncurry3(fn3.curried());
//        final Functional.Function1<String, Functional.Function2<Integer, Double, String>> ccc2 = fn3.curried();
        final Function1<Double, String> fn1 = fn2.apply(1);
        final String fn0 = fn1.apply(0.0D);

        System.out.println(fn0);
        System.out.println(fn1.apply(2.0D));

        uncurry1(fn1);

        final Function<String, Function<Integer, Function<Double, String>>> fn = (String s) -> (Integer i) -> (Double d) -> s;

        final Products.Foo f = new Products.Foo("test");
        System.out.println(f);

        final Products.Foo2 f2 = new Products.Foo2("a", 0);
        System.out.println(f2);
        f2.apply();

        final Products.Contact c1 = new Products.Contact(
            "john",
            "doe",
            new Products.Address(
                "1234 Sesame St",
                "New York City",
                "NY",
                "12345"
            )
        );
        System.out.println(c1);

        Function1<String, Function1<String, Function1<Products.Address, Products.Contact>>> cc1 = c1.curried();
        final Products.Contact c2 = cc1
            .apply("john")
            .apply("doe")
            .apply(new Products.Address(
                    "1234 Sesame St",
                    "New York City",
                    "NY",
                    "12345"
            ));

        System.out.println(c1.equals(c2));
    }

    static <T1> void needsIt(T1 given, final SAM<T1> test) {
        System.out.println(test.filter(given));
    }
}


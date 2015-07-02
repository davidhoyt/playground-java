package com.github.davidhoyt.playground.whyfpmatters;

import java.util.function.BiFunction;
import java.util.function.Function;

import static com.github.davidhoyt.playground.whyfpmatters.List.*;
import static com.github.davidhoyt.playground.whyfpmatters.Node.*;
import static com.github.davidhoyt.playground.whyfpmatters.Functions.*;

public class Section3_B {
    public static <A, B> B reduce(List<A> list, BiFunction<A, B, B> reducer, B x) {
        if (list.isNil()) {
            return x;
        } else {
            return reducer.apply(list.head, reduce(list.tail, reducer, x));
        }
    }

    public static int sum(List<Integer> list) {
        return reduce(list, add, 0);
    }

    public static int product(List<Integer> list) {
        return reduce(list, multiply, 1);
    }

    public static boolean anytrue(List<Boolean> list) {
        return reduce(list, or, false);
    }

    public static boolean alltrue(List<Boolean> list) {
        return reduce(list, and, true);
    }

    public static <A> List<A> copy(List<A> a) {
        return reduce(a, List::cons, List.nil());
    }

    public static <A> List<A> append(List<A> a, List<A> b) {
        return reduce(a, List::cons, b);
    }

    public static List<Integer> doubleall1(List<Integer> list) {
        return reduce(list, doubleandcons1, nil());
    }

    public static <A, B> List<B> map(List<A> list, Function<A, B> fn) {
        return reduce(list, (a, b) -> List.cons(fn.apply(a), b), nil());
    }

    public static List<Integer> doubleall2(List<Integer> list) {
        return map(list, dbl);
    }

    public static int summatrix(List<List<Integer>> matrix) {
        return sum(map(matrix, row -> sum(row)));
    }

    public static void main(String[] args) {
        System.out.println(sum(list(1, 2, 3)));

        System.out.println(product(list(2, 3, 4)));

        System.out.println(anytrue(list(false, false, true, false)));

        System.out.println(alltrue(list(true, true, true, true)));

        System.out.println(append(list(1, 2), list(3, 4)));

        System.out.println(doubleall1(list(1, 2, 3)));

        System.out.println(doubleall2(list(1, 2, 3)));

        System.out.println(summatrix(list(
            list(1, 2, 3),
            list(4, 5, 6),
            list(7, 8, 9)
        ))); //45

        Node<Integer> tree =
            node (1,
                  cons (node (2, nil()),
                        cons(node(3,
                                cons(node(4, nil()), nil())),
                            nil())));
    }
}

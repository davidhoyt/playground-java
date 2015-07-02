package com.github.davidhoyt.playground.whyfpmatters;

import java.util.function.BiFunction;
import java.util.function.Function;

public class Functions {
    public static final BiFunction<Integer, Integer, Integer> add =
        (x, y) -> x + y;

    public static final BiFunction<Integer, Integer, Integer> multiply =
        (x, y) -> x * y;

    public static final BiFunction<Boolean, Boolean, Boolean> or =
        (x, y) -> x || y;

    public static final BiFunction<Boolean, Boolean, Boolean> and =
        (x, y) -> x && y;

    public static final BiFunction<Integer, List<Integer>, List<Integer>> doubleandcons1 =
        (num, list) -> List.cons(2 * num, list);

    public static final Function<Integer, Integer> dbl =
        n -> 2 * n;
}

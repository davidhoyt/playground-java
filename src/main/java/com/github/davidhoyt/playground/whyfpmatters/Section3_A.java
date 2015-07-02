package com.github.davidhoyt.playground.whyfpmatters;

import static com.github.davidhoyt.playground.whyfpmatters.List.*;

public class Section3_A {
    public static int sum(List<Integer> list) {
        return sumStep(0, list);
    }

    private static int sumStep(int sum, List<Integer> list) {
        if (list.isNil()) {
            return sum;
        } else {
            return sumStep(sum + list.head, list.tail);
        }
    }

    public static void main(String[] args) {
        System.out.println(sum(list(1, 2, 3, 4)));
    }
}

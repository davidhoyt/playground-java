package com.github.davidhoyt.playground.whyfpmatters;

import java.util.function.BiFunction;
import java.util.function.Function;

public class List<T> {
    public final T head;
    public final List<T> tail;

    private List(T head, List<T> tail) {
        this.head = head;
        this.tail = tail;
    }

    public <A> A foldLeft(A zero, BiFunction<A, T, A> fn) {
        T current;
        A last = zero;
        List<T> rest = this;
        while (!rest.isNil()) {
            current = rest.head;
            last = fn.apply(last, current);
            rest = rest.tail;
        }
        return last;
    }

    public <R> List<R> map(Function<T, R> fn) {
        return foldLeft(nil(), (list, current) -> list.append(fn.apply(current)));
    }

    public List<T> append(T other) {
        return append(unit(other));
    }

    public List<T> append(List<T> other) {
        if (isNil()) {
            return other;
        } else {
            return cons(head, tail.append(other));
        }
    }

    public List<T> prepend(T other) {
        if (isNil()) {
            return unit(other);
        } else {
            return cons(other, this);
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(128);
        sb.append("[");
        foldLeft(0, (index, t) -> {
            if (index > 0) {
                sb.append(", ");
            }
            sb.append(t.toString());
            return index + 1;
        });
        sb.append("]");
        return sb.toString();
    }

    public boolean isEmpty() {
        return isNil();
    }

    public boolean isNil() {
        return head == null && tail == null;
    }

    public boolean isCons() {
        return !isNil();
    }

    public static <T> List<T> nil() {
        return new List<>(null, null);
    }

    public static <T> List<T> unit(T value) {
        return cons(value, nil());
    }

    public static <T> List<T> cons(T head, List<T> tail) {
        return new List<>(head, tail);
    }

    public static <T> List<T> of(T...values) {
        return list(values);
    }

    public static <T> List<T> list(T...values) {
        if (values == null) {
            return nil();
        } else {
            List<T> list = unit(values[values.length - 1]);
            for (int i = values.length - 2; i >= 0; --i) {
                list = cons(values[i], list);
            }
            return list;
        }
    }

    public static void main(String[] args) {
        List<Integer> empty = nil();
        List<Integer> _1 = cons(1, nil());
        List<Integer> _123 = cons(1, cons(2, cons(3, nil())));

        System.out.println(empty);
        System.out.println(_1);
        System.out.println(_123);
    }
}

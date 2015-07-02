package com.github.davidhoyt.playground.whyfpmatters;

public class Node<T> {
    public final T label;
    public final List<Node<T>> subtrees;

    private Node(T label, List<Node<T>> subtrees) {
        this.label = label;
        this.subtrees = subtrees;
    }

    @Override
    public String toString() {
        return label.toString();
    }

    public static <T> Node<T> unit(T label) {
        return node(label);
    }

    public static <T> Node<T> node(T label, List<Node<T>> subtrees) {
        return new Node<>(label, subtrees);
    }

    public static <T> Node<T> node(T label, Node<T>...subtrees) {
        return new Node<>(label, List.of(subtrees));
    }
}

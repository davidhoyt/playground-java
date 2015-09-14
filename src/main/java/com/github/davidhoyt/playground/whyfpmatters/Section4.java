package com.github.davidhoyt.playground.whyfpmatters;

import java.util.function.BiFunction;
import java.util.function.Function;

import static com.github.davidhoyt.playground.whyfpmatters.List.*;

public class Section4 {
    // C N IS CALLED ZN HERE SO THAT IT HAS THE RIGHT TYPE
    //     X = A0
    //     Y = A0 + 2.*EPS
    // C THE VALUE OF Y DOES NOT MATTER SO LONG AS ABS(X-Y).GT.EPS
    // 100 IF (ABS(X-Y).LE.EPS) GOTO 200
    //     Y = X
    //     X = (X + ZN/X) / 2.
    // GOTO 100
    // 200 CONTINUE
    // C THE SQUARE ROOT OF ZN IS NOW IN X
    static double imperativeNewtonRaphsonSquareRoot(double N, double eps) {
        double x = N;
        double y = N + 2.0D * eps;

        while (Math.abs(x - y) >= eps) {
            y = x;
            x = (x + N / x) / 2.0D;
        }

        return x;
    }

    // next N x = (x + N/x) / 2
    static double next(double N, double x) {
        System.out.println("x: " + x);
        return (x + N / x) / 2;
    }

    // repeat f a = cons a (repeat f (f a))
    static <A> List<A> repeat(Function<A, A> f, A a) {
        return cons(a, repeat(f, f.apply(a)));
    }

    // repeat (next N) a0
    // !!strict evaluation poses a problem
    static List<Double> listOfApproximationsOfSquareRoot(double a0) {
        return repeat((Double x) -> next(a0, x), a0);
    }

    public static void main(String[] args) {
        System.out.println(imperativeNewtonRaphsonSquareRoot(5.0, 0.00001));

        System.out.println(listOfApproximationsOfSquareRoot(5.0));
    }
}

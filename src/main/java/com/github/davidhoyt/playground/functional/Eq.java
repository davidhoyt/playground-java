package com.github.davidhoyt.playground.functional;

import com.github.davidhoyt.playground.functional.products.Product;

import java.util.List;
import java.util.Optional;

@FunctionalInterface
public interface Eq<A> {
    Defaults defaults = new Defaults();

    boolean areEqual(A left, A right);

    @SuppressWarnings("unchecked")
    class Defaults extends com.github.davidhoyt.playground.functional.Defaults<Eq<?>> {
        public final Eq<Object> forObject = register(Object.class, (Object left, Object right) -> left.equals(right));

        public final Eq<Product> forProduct = register(Product.class, (Product left, Product right) -> {
            final List<Object> leftValues = left.getValues();
            final List<Object> rightValues = right.getValues();

            final int leftSize = leftValues.size();
            final int rightSize = rightValues.size();

            if (leftSize != rightSize)
                return false;

            for (int i = 0; i < leftSize; ++i) {
                final Object objLeft = leftValues.get(i);
                final Object objRight = rightValues.get(i);

                if (!objLeft.getClass().isAssignableFrom(objRight.getClass())) {
                    return false;
                }

                if (objLeft instanceof Equatable) {
                    if (!((Equatable<Object>)objLeft).getEq().areEqual(objLeft, objRight)) {
                        return false;
                    }
                } else {
                    final Optional<Eq<?>> eqLeft = find(objLeft);
                    if (eqLeft.isPresent() && !((Eq<Object>) eqLeft.get()).areEqual(objLeft, objRight)) {
                        return false;
                    }
                }
            }
            return true;
        });
    }
}

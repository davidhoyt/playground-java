package com.github.davidhoyt.playground.functional;

import com.github.davidhoyt.playground.functional.products.Product;

import java.util.Optional;

@FunctionalInterface
public interface Show<A> {
    Defaults defaults = new Defaults();

    String asString(A given);

    @SuppressWarnings("unchecked")
    class Defaults extends com.github.davidhoyt.playground.functional.Defaults<Show<?>> {
        public final Show<String> forString = register(String.class, (String given) -> given);
        public final Show<Integer> forInteger = register(Integer.class, (Integer given) -> Integer.toString(given));

        public final Show<Product> forProduct = register(Product.class, (Product product) -> {
            final StringBuilder sb = new StringBuilder(128);
            sb.append(product.getClass().getSimpleName());
            sb.append("(");
            int i = 0;
            for (Object value : product.getValues()) {
                if (i > 0) {
                    sb.append(", ");
                }
                if (value instanceof Showable) {
                    sb.append(((Showable<Object>)value).getShow().asString(value));
                } else {
                    final Optional<Show<?>> opt = find(value);
                    if (opt.isPresent()) {
                        sb.append(((Show<Object>) opt.get()).asString(value));
                    } else {
                        sb.append(value);
                    }
                }
                ++i;
            }
            sb.append(")");
            return sb.toString();
        });
    }
}

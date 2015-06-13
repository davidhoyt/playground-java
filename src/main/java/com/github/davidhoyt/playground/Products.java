package com.github.davidhoyt.playground;

import com.github.davidhoyt.playground.functional.products.Product1;
import com.github.davidhoyt.playground.functional.products.Product2;
import com.github.davidhoyt.playground.functional.products.Product3;
import com.github.davidhoyt.playground.functional.products.Product4;

public class Products {
    public static class Foo extends Product1.Standard<String, Foo> {
        public Foo(String s) {
            super(s);
        }

        @Override
        public Foo apply(String s) {
            return new Foo(s);
        }
    }

    public static class Foo2 extends Product2.Standard<String, Integer, Foo2> {
        public Foo2(String s, Integer i) {
            super(s, i);
        }

        @Override
        public Foo2 apply(String s, Integer i) {
            return new Foo2(s, i);
        }
    }

    public static class Address extends Product4.Standard<String, String, String, String, Address> {
        public Address(String street, String city, String state, String zip) {
            super(street, city, state, zip);
        }

        @Override
        public Address apply(String street, String city, String state, String zip) {
            return new Address(street, city, state, zip);
        }
    }

    public static class Contact extends Product3.Standard<String, String, Address, Contact> {
        public Contact(String firstName, String lastName, Address address) {
            super(firstName, lastName, address);
        }

        @Override
        public Contact apply(String firstName, String lastName, Address address) {
            return new Contact(firstName, lastName, address);
        }
    }
}

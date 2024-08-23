package com.ashfaq.dev.libs.guava;

import com.google.common.base.Optional;

public class GuavaOptionalExample {
    public static void main(String[] args) {
        Optional<String> possible = Optional.of("Hello");
        if (possible.isPresent()) {
            System.out.println(possible.get()); // Output: Hello
        }
    }
}

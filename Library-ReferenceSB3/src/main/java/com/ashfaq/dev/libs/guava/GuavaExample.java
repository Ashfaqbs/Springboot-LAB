package com.ashfaq.dev.libs.guava;

import com.google.common.collect.ImmutableList;

public class GuavaExample {
    public static void main(String[] args) {
        // Creating an immutable list
        ImmutableList<String> fruits = ImmutableList.of("Apple", "Banana", "Orange");
        System.out.println(fruits); // Output: [Apple, Banana, Orange]
    }
}

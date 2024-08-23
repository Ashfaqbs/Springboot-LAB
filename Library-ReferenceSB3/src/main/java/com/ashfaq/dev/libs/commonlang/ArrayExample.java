package com.ashfaq.dev.libs.commonlang;

import org.apache.commons.lang3.ArrayUtils;

public class ArrayExample {
    public static void main(String[] args) {
        String[] fruits = {"Apple", "Banana", "Orange"};

        // Check if the array contains a specific element
        boolean containsApple = ArrayUtils.contains(fruits, "Apple");
        System.out.println("Contains Apple: " + containsApple); // Output: true
    }
}

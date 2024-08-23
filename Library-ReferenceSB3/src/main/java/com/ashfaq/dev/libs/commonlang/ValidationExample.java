package com.ashfaq.dev.libs.commonlang;

import org.apache.commons.lang3.Validate;

public class ValidationExample {
    public static void main(String[] args) {
        String str = null;

        // Validate that the object is not null
        try {
            Validate.notNull(str, "The string must not be null");
        } catch (NullPointerException e) {
            System.out.println(e.getMessage()); // Output: The string must not be null
        }
    }
}

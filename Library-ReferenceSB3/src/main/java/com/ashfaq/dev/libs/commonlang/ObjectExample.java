package com.ashfaq.dev.libs.commonlang;

import org.apache.commons.lang3.ObjectUtils;

public class ObjectExample {
    public static void main(String[] args) {
        String str = null;

        // Return a default value if the object is null
        String safeStr = ObjectUtils.defaultIfNull(str, "Default String");
        System.out.println("Safe String: " + safeStr); // Output: Default String
    }
}

package com.ashfaq.dev.libs.commonlang;

import org.apache.commons.lang3.StringUtils;

public class StringExample {
    public static void main(String[] args) {
        // Check if a string is blank (null, empty, or only whitespace)
        String str = "   ";
        boolean isBlank = StringUtils.isBlank(str);
        System.out.println("Is blank: " + isBlank); // Output: true

        // Join a list of strings with a delimiter
        String[] words = {"Hello", "World", "Commons", "Lang3"};
        String joinedString = StringUtils.join(words, "-");
        System.out.println("Joined String: " + joinedString); // Output: Hello-World-Commons-Lang3
    }
}

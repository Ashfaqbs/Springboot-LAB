package com.ashfaq.dev.libs.commonsio;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class CommonsIOExample {
    public static void main(String[] args) throws IOException {
        File file = new File("example.txt");

        // Write to a file
        FileUtils.writeStringToFile(file, "Hello, World!", "UTF-8");

        // Read from a file
        String content = FileUtils.readFileToString(file, "UTF-8");
        System.out.println(content); // Output: Hello, World!

        // Clean up
        FileUtils.forceDelete(file);
    }
}

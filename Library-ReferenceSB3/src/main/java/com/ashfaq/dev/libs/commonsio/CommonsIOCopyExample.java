package com.ashfaq.dev.libs.commonsio;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class CommonsIOCopyExample {
    public static void main(String[] args) throws IOException {
        File source = new File("source.txt");
        File dest = new File("dest.txt");

        // Copy file content
        FileUtils.copyFile(source, dest);
    }
}

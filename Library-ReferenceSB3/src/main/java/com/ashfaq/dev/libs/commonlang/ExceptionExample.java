package com.ashfaq.dev.libs.commonlang;

import org.apache.commons.lang3.exception.ExceptionUtils;

public class ExceptionExample {
    public static void main(String[] args) {
        try {
            int result = 10 / 0; // This will throw an ArithmeticException
        } catch (Exception e) {
            // Get the stack trace as a string
            String stackTrace = ExceptionUtils.getStackTrace(e);
            System.out.println("Stack Trace: " + stackTrace);
        }
    }
}

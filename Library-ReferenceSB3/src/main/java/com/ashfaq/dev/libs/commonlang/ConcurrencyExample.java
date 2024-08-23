package com.ashfaq.dev.libs.commonlang;

import org.apache.commons.lang3.concurrent.ConcurrentException;
import org.apache.commons.lang3.concurrent.LazyInitializer;

public class ConcurrencyExample {
    private static class ExpensiveResourceInitializer extends LazyInitializer<String> {
        @Override
        protected String initialize() {
            // Simulate an expensive operation
            return "Expensive Resource Initialized";
        }
    }

    public static void main(String[] args) throws ConcurrentException {
        ExpensiveResourceInitializer initializer = new ExpensiveResourceInitializer();

        // Thread-safe initialization of the resource
        String resource = initializer.get();
        System.out.println(resource); // Output: Expensive Resource Initialized
    }
}

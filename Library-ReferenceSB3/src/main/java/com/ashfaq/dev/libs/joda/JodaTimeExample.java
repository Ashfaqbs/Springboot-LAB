package com.ashfaq.dev.libs.joda;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

public class JodaTimeExample {
    public static void main(String[] args) {
        DateTime dateTime = new DateTime();
        System.out.println(dateTime); // Current date and time

        // Formatting the date
        String formattedDate = dateTime.toString(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println("Formatted Date: " + formattedDate);
    }
}

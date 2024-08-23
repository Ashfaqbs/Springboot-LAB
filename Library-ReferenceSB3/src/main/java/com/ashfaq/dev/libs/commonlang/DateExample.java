package com.ashfaq.dev.libs.commonlang;

import org.apache.commons.lang3.time.DateUtils;
import java.util.Date;

public class DateExample {
    public static void main(String[] args) {
        Date today = new Date();

        // Add 7 days to the current date
        Date nextWeek = DateUtils.addDays(today, 7);
        System.out.println("Date after 7 days: " + nextWeek);
    }
}

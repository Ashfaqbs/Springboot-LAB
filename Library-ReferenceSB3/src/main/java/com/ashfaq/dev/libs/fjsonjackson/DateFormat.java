package com.ashfaq.dev.libs.fjsonjackson;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

class Event {
    public String eventName;

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") // Custom date format
    public Date eventDate;

    public Event(String eventName, Date eventDate) {
        this.eventName = eventName;
        this.eventDate = eventDate;
    }

    public Event() {
    }
}

public class DateFormat {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Event event = new Event("Conference", new Date());

        String json = mapper.writeValueAsString(event);
        System.out.println("Serialized JSON: " + json);
        /*
        Serialized JSON: {"eventName":"Conference","eventDate":"2025-03-24 16:54:10"}

         without format:
         Serialized JSON: {"eventName":"Conference","eventDate":1742835273449}

         */
    }
}

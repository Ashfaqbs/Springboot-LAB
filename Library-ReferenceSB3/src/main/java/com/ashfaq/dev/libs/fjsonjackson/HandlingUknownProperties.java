package com.ashfaq.dev.libs.fjsonjackson;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true) // Ignore extra JSON fields
class IUser {
    public String name;
    public int age;

    public IUser() {
    }
}

public class HandlingUknownProperties {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String json = "{\"name\":\"Alice\",\"age\":25,\"extraField\":\"ignored\"}";

        IUser user = mapper.readValue(json, IUser.class);
        System.out.println("Deserialized Object: " + user.name + ", " + user.age);
        /*
        OP Deserialized Object: Alice, 25

         */
    }
}

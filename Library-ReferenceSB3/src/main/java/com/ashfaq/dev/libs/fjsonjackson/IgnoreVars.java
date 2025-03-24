package com.ashfaq.dev.libs.fjsonjackson;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;


import com.fasterxml.jackson.annotation.JsonIgnore;

class User {
    public String username;

    @JsonIgnore // Will not be serialized similar to transient
    public String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {}
}

public class IgnoreVars {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        User user = new User("john_doe", "super_secret");

        String json = mapper.writeValueAsString(user);
        System.out.println("Serialized JSON: " + json);
    }
}

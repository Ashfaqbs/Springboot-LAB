package com.ashfaq.dev.libs.fjsonjackson;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;


class Employee {
    @JsonProperty("full_name") // Renaming field in JSON
    public String name;

    public int salary;

    public Employee(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }

    public Employee() {
    }
}

public class RenameFields {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Employee emp = new Employee("Alice", 50000);

        String json = mapper.writeValueAsString(emp);
        System.out.println("Serialized JSON: " + json);
        /*
        Serialized JSON: {"salary":50000,"full_name":"Alice"}

         */
    }
}

package com.ashfaq.dev.libs.fjsonjackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonInclude;

class Product {
    public String name;

    @JsonInclude(JsonInclude.Include.NON_NULL) // Ignore null values
    public Double price;

    public Product(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public Product() {}
}

public class IgnoreNullVariables {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Product p1 = new Product("Laptop", null);

        String json = mapper.writeValueAsString(p1);
        System.out.println("Serialized JSON: " + json);
//        OP Serialized JSON: {"name":"Laptop"} , the price is ignored as it is null
    }
}

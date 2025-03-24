package com.ashfaq.dev.libs.fjsonjackson;

import com.fasterxml.jackson.databind.ObjectMapper;


class Address {
    public String city;
    public String zipCode;

    public Address() {
    }

    public Address(String city, String zipCode) {
        this.city = city;
        this.zipCode = zipCode;
    }
}

class IPerson {
    public String name;
    public int age;
    public Address address; // Nested Object

    public IPerson() {
    }

    public IPerson(String name, int age, Address address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }
}

public class NestedClassExample {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        IPerson person = new IPerson("John", 30, new Address("New York", "10001"));

        // Serialization
        String json = mapper.writeValueAsString(person);
        System.out.println("Serialized JSON: " + json);

        // Deserialization
        String jsonString = "{\"name\":\"John\",\"age\":30,\"address\":{\"city\":\"New York\",\"zipCode\":\"10001\"}}";
        IPerson deserializedPerson = mapper.readValue(jsonString, IPerson.class);

        System.out.println("Deserialized Object: " + deserializedPerson.name + ", " + deserializedPerson.address.city);
  /*
  OP

  Serialized JSON: {"name":"John","age":30,"address":{"city":"New York","zipCode":"10001"}}
  Deserialized Object: John, New York
   */

    }
}

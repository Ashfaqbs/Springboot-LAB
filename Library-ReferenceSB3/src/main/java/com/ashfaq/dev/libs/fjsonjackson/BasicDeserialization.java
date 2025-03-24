package com.ashfaq.dev.libs.fjsonjackson;

import com.fasterxml.jackson.databind.ObjectMapper;

public class BasicDeserialization {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String json = "{\"name\":\"John\",\"age\":30}";

        // Convert JSON to Java Object
        Personx person = mapper.readValue(json, Personx.class);
        System.out.println("Deserialized Object: " + person.getName() + ", " + person.getAge());
    }




}
class Personx {
    private String name;
    private int age;

    public Personx() {}

    public Personx(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Getters and setters omitted for brevity

    @Override
    public String toString() {
        return "Person[name=" + name + ",age=" + age + "]";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


}
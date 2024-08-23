package com.ashfaq.dev.libs.fjsonjackson;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonDeserializationExample {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String json = "{\"name\":\"John\",\"age\":30}";

        // JSON to Java object
        Person1 person = mapper.readValue(json, Person1.class);
        System.out.println(person.getName()); // Output: John
    }
    
    
}

class Person1 {
    private String name;
    private int age;

    
    
    public Person1() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Person1(String name, int age) {
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
	

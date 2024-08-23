package com.ashfaq.dev.libs.fjsonjackson;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonExample {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        
        // Java object to JSON
        Person person = new Person("John", 30);
        String json = mapper.writeValueAsString(person);
        System.out.println(json); // Output: {"name":"John","age":30}
    }
}

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
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

package com.ashfaq.dev.libs.fjsonjackson;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

class Student {
    public String name;
    public int age;

    public Student() {
    } // Default constructor required by Jackson

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

class School {
    public String schoolName;
    public List<Student> students;

    public School() {
    }

    public School(String schoolName, List<Student> students) {
        this.schoolName = schoolName;
        this.students = students;
    }
}

public class NestedClassExample2 {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        // Creating a School object with a list of students
        School school = new School("Greenwood High", List.of(
                new Student("Alice", 14),
                new Student("Bob", 15)
        ));

        // Serialization (Java Object → JSON)
        String json = mapper.writeValueAsString(school);
        System.out.println("Serialized JSON: " + json);

        // Deserialization (JSON → Java Object)
        String jsonString = "{\"schoolName\":\"Greenwood High\",\"students\":[{\"name\":\"Alice\",\"age\":14},{\"name\":\"Bob\",\"age\":15}]}";
        School deserializedSchool = mapper.readValue(jsonString, School.class);

        System.out.println("Deserialized Object: " + deserializedSchool.schoolName);
        for (Student student : deserializedSchool.students) {
            System.out.println(student.name + " - " + student.age);
        }

        /*
        OP
        Serialized JSON: {"schoolName":"Greenwood High","students":[{"name":"Alice","age":14},{"name":"Bob","age":15}]}
        Deserialized Object: Greenwood High
        Alice - 14
        Bob - 15
         */
    }
}

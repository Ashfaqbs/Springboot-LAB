package com.ashfaq.dev.libs.commonlang;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class BuilderExample {
    private String name;
    private int age;

    public BuilderExample(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        // Use ToStringBuilder to generate a string representation
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public static void main(String[] args) {
        BuilderExample person = new BuilderExample("John Doe", 30);
        System.out.println(person.toString()); // Output: BuilderExample[name=John Doe,age=30]
    }
}

package com.demo.util;

public class Animal {
    private int id;
    private String name;
    private String type;
    private String description;
    private String habitat;
    private int lifespan;

    // Constructor
    public Animal(int id, String name, String type, String description, String habitat, int lifespan) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.description = description;
        this.habitat = habitat;
        this.lifespan = lifespan;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    public int getLifespan() {
        return lifespan;
    }

    public void setLifespan(int lifespan) {
        this.lifespan = lifespan;
    }

    @Override
    public String toString() {
        return "Animal [id=" + id + ", name=" + name + ", type=" + type + ", description=" + description + ", habitat=" + habitat + ", lifespan=" + lifespan + "]";
    }
}

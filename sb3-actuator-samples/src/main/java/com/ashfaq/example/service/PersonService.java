package com.ashfaq.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class PersonService {

	// Static list to hold Person data
	private static final List<String> people = new ArrayList<>();

	// Initialize with some data
	static {
		people.add("Alice");
		people.add("Bob");
		people.add("Charlie");
	}

	// Method to get all people
	public List<String> getAllPeople() {
		return new ArrayList<>(people); // Return a copy to prevent external modification
	}

	// Method to add a new person
	public void addPerson(String name) {
		people.add(name);
	}
}

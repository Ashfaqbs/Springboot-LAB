package com.ashfaq.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ashfaq.example.service.PersonService;

@RestController
@RequestMapping("/api/people")
public class PersonController {

	@Autowired
	private PersonService personService;

	// Endpoint to get all people
	@GetMapping
	public List<String> getAllPeople() {
		return personService.getAllPeople();
	}

	// Endpoint to add a new person
	@PostMapping
	public String addPerson(@RequestBody String name) {
		personService.addPerson(name);
		return "Person added successfully: " + name;
	}
}

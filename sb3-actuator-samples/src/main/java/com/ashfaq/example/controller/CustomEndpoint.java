package com.ashfaq.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import com.ashfaq.example.service.PersonService;

@Component
@Endpoint(id = "custom") // Custom endpoint ID: /actuator/custom
public class CustomEndpoint {

	@Autowired
	private PersonService personService;

	// Expose a GET endpoint at /actuator/example
	@ReadOperation
	public List<String> getPeople() {
		return personService.getAllPeople();
	}
}


//http://localhost:8080/actuator/custom

//OP
//[
//"Alice",
//"Bob",
//"Charlie"
//]
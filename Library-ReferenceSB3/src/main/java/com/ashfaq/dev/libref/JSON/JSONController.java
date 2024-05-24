package com.ashfaq.dev.libref.JSON;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

@RestController
public class JSONController {

	@GetMapping("/gson")
	public String mainRunner() {
		Gson gson = new Gson();
		MyObject obj = new MyObject("Hello", 123);
		String json = gson.toJson(obj);
		System.out.println(json);

		return json;
	}

	@GetMapping("/json")
	public String mainRunner2() {
		ObjectMapper mapper = new ObjectMapper();// to use this and get JSON in response we have to add
//		@JsonProperty to the class fields of MyObject else we get serilization error and null for the fields 
		// for GSON we dont have to add the anotation on the fields

		MyObject obj = new MyObject("Hello", 123);
		String json = null;
		try {
			json = mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(json);
		return json;
	}
}

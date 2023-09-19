/**
 * Code developed by Ashfaq (© [Year])
 * GitHub: https://github.com/DarkSharkAsh
 */



package com.ashfaq.dev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ashfaq.dev.dto.UserLocationDTO;
import com.ashfaq.dev.services.UserServices;

@RestController
public class Usercontroller {

	
	@Autowired
	private UserServices services;
	
	@GetMapping("/users-location")
	public List<UserLocationDTO> getAllUserLocation()
	{
		// we are not sending JPA entity here we are using DTO to send the data
		return services.getAllUserLocation();
	}
	
	
	
}

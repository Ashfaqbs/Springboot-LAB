/**
 * Code developed by Ashfaq (© [Year])
 * GitHub: https://github.com/DarkSharkAsh
 */

package com.test.ashfaq.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/restapi")
public class RestController {

//	note :
//		/api/users/12345 ✅
//		/api?type=user&id=12345 ❌
	// Rate limiting enabled
//	URL requestparam http://localhost:9000/restapi/v1?username=monty&color=blue
	@GetMapping("/v1")
	public String handleApiRequest(@RequestParam("username") String username, @RequestParam("color") String color) {
		// Your logic to process the username and color parameters
		// For example, you can return a formatted message
		return "Hello, " + username + "! Your favorite color is " + color + ".";
	}

}

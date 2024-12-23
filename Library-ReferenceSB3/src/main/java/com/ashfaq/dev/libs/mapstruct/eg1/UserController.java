package com.ashfaq.dev.libs.mapstruct.eg1;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@PostMapping("/dto")
	public UserDTO getUserDTO(@RequestBody User user) {
		return UserMapper.INSTANCE.toUserDTO(user);
	}
	
//Request JSON :
//{
//    "id": 1,
//    "username": "ashfaq123",
//    "password": "mypassword",
//    "name": "Ashfaq",
//    "email": "ashfaq@example.com"
//}
//
//
//Response JSON :
//{
//	"username": "ashfaq123",
//	"email": "ashfaq@example.com"
}

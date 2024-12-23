package com.ashfaq.dev.libs.mapstruct.eg2;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usersv2")
public class UserControllerV2 {

    @PostMapping("/dto")
    public UserDTO getUserDTO(@RequestBody User user) {
        return UserMapper.INSTANCE.userToUserDTO(user);
    }
    
    /*
     *  Request :
     *  {
  "id": 1,
  "name": "Ashfaq",
  "email": "ashfaq@example.com",
  "pin": "1234",
  "favoriteNumbers": [7, 3, 22],
  "cars": [
    {
      "id": 1,
      "name": "Tesla"
    },
    {
      "id": 2,
      "name": "BMW"
    }
  ]
}


Response 
{
    "name": "Ashfaq",
    "email": "ashfaq@example.com",
    "favoriteNumbers": [
        7,
        3,
        22
    ],
    "cars": [
        {
            "id": 1,
            "name": "Tesla"
        },
        {
            "id": 2,
            "name": "BMW"
        }
    ]
}
     * 
     */
}

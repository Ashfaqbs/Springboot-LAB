package com.ashfaq.dev.libs.mapstruct.eg1;

import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	// Map User -> UserDTO
//	@Mapping(source = "email", target = "userEmail") // Handle mismatch between source and target 

	UserDTO toUserDTO(User user);
}

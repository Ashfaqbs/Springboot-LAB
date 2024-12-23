package com.ashfaq.dev.libs.mapstruct.eg2;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = CarMapper.class) // Use CarMapper for nested list
public interface UserMapper {
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	@Mapping(target = "favoriteNumbers", source = "favoriteNumbers") // Map favorite numbers directly
	@Mapping(target = "cars", source = "cars") // Map cars to carDTOs
	UserDTO userToUserDTO(User user);
}

/**
 * Code developed by Ashfaq (© [Year])
 * GitHub: https://github.com/DarkSharkAsh
 */

package com.ashfaq.dev.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashfaq.dev.dto.UserLocationDTO;
import com.ashfaq.dev.model.User;
import com.ashfaq.dev.repository.UserRepository;

@Service
public class UserServices {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	public List<UserLocationDTO> getAllUserLocation() {

		userRepository.findAll().stream().forEach(data -> System.out.println(data));

		return userRepository.findAll().stream().map(data -> convertEntityToDTO(data))// converts USERS JPA entity to
				// USERLocatoinDTO
				.collect(Collectors.toList());
//We have converted list of USER to list of USERLocation DTo
	}

	// We are Manually written a code to convert to entity to DTo but we have to
	// generally use this library like model mappers or mapstruct
//	private UserLocationDTO convertEntityToDTO(User user) {
//		UserLocationDTO userLocationdto = new UserLocationDTO();
//
//		userLocationdto.setUserId(user.getId());
//
//		userLocationdto.setMail(user.getMail());
//
//		userLocationdto.setPlace(user.getLocation().getPlace());
//
//		userLocationdto.setLatitude(user.getLocation().getLatitude());
//
//		userLocationdto.setLongitude(user.getLocation().getLongitude());
//
//		return userLocationdto;
//	}

	// 1 converting to DTO to entity
	// Using ModelMapper to map User JPA entity to UserLocationDTO
	private UserLocationDTO convertEntityToDTO(User user) {

		// if the entity had relationship with other entity the model mapper will might
		// try to assign null of its refrence type so we need to put config

		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		// this will set the matching strategy to loose to make it easier for the
		// library to locate and match property

		UserLocationDTO userLocationdto = new UserLocationDTO();

		userLocationdto = modelMapper.map(user, UserLocationDTO.class);

		return userLocationdto;
	}

	// 2 converting entity to DTO
	private User convertEntityToDTO(UserLocationDTO userLocationDTO) {

		// if the entity had relationship with other entity the model mapper will might
		// try to assign null of its refrence type so we need to put config

		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		// this will set the matching strategy to loose to make it easier for the
		// library to locate and match property
		User user = new User();
		user = modelMapper.map(userLocationDTO, User.class);
		return user;
	}
}

package com.ashfaq.dev;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ashfaq.dev.model.Location;
import com.ashfaq.dev.model.User;
import com.ashfaq.dev.repository.LocationRepository;
import com.ashfaq.dev.repository.UserRepository;

@SpringBootApplication
public class DtoArchitectureSpringboot2xApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DtoArchitectureSpringboot2xApplication.class, args);
	}

	// wer have implemented commandlineRunner interfcace which will provide a run
	// method and this run method will execute when ever our springboot applications
	// start

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private LocationRepository locationRepository;

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

		Location location = new Location();
//		location.setId(1);
		location.setDescription("Banglore is a good place");
		location.setLatitude(45.3);
		location.setLongitude(48.6);
		location.setPlace("Banglore");
		locationRepository.save(location);

		User user1 = new User();
//		user1.setId(1);
		user1.setFirstName("Sonu");
		user1.setLastName("Khan");
		user1.setLocation(location);
		user1.setMail("Sonu@gmail.com");
		user1.setPassword("1234");
		userRepository.save(user1);

		User user2 = new User();
//		user1.setId(2);
		user2.setFirstName("Monu");
		user2.setLastName("Khan");
		user2.setLocation(location);
		user2.setMail("Monu@gmail.com");
		user2.setPassword("Monu1234");
		userRepository.save(user2);
	}
}

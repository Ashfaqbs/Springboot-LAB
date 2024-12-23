package com.ashfaq.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import src.main.java.com.ashfaq.example.repository.UploadTaskRepository;

@SpringBootApplication
public class Sb3J21DbDockerDemoApplication implements CommandLineRunner {

	@Autowired
	UploadTaskRepository repository;

	@Override
	public void run(String... args) throws Exception { 
		// TODO Auto-generated method stub
		repository.findAll().forEach(System.out::println);

	}

	public static void main(String[] args) {
		SpringApplication.run(Sb3J21DbDockerDemoApplication.class, args);
	}

}

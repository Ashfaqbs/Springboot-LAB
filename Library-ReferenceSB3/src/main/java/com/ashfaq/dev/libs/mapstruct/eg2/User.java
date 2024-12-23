package com.ashfaq.dev.libs.mapstruct.eg2;

import java.util.List;

public class User {
	private Long id; // We want to hide this
	private String name;
	private String email;
	private String pin; // We want to hide this
	private List<Integer> favoriteNumbers; // List of favorite numbers (primitive)
	private List<Car> cars; // List of car entities

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public List<Integer> getFavoriteNumbers() {
		return favoriteNumbers;
	}

	public void setFavoriteNumbers(List<Integer> favoriteNumbers) {
		this.favoriteNumbers = favoriteNumbers;
	}

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

}

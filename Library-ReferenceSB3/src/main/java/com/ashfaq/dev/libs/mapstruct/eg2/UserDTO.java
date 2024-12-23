package com.ashfaq.dev.libs.mapstruct.eg2;

import java.util.List;

public class UserDTO {
    private String name;
    private String email;
    private List<Integer> favoriteNumbers;  // List of favorite numbers
    private List<CarDTO> cars;  // List of car DTOs
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
	public List<Integer> getFavoriteNumbers() {
		return favoriteNumbers;
	}
	public void setFavoriteNumbers(List<Integer> favoriteNumbers) {
		this.favoriteNumbers = favoriteNumbers;
	}
	public List<CarDTO> getCars() {
		return cars;
	}
	public void setCars(List<CarDTO> cars) {
		this.cars = cars;
	}

    // Getters and Setters
}

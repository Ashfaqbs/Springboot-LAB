package com.ashfaq.dev.libs.mapstruct.eg2;

import org.mapstruct.Mapper;

@Mapper
public interface CarMapper {
	CarDTO carToCarDTO(Car car); // Mapping for individual car
}

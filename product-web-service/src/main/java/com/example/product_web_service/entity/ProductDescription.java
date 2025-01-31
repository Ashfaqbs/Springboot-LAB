package com.example.product_web_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@ToString
public class ProductDescription  {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String details;
    private String companySite;
    private String email;
    private String supportLocation;
}
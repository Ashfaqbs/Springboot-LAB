package com.example.product_web_service.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@ToString
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String model;
    private String productCategory;
    private String brandName;
    private String physicalStore;
    
    @Lob
    private byte[] image;
    
    @Lob
    private byte[] productVideo;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "description_id")
    private ProductDescription  description;
}
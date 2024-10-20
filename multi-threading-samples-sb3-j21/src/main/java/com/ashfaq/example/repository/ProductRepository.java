package com.ashfaq.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashfaq.example.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

package com.example.product_web_service.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import  com.example.product_web_service.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByProductCategory(String productCategory);
    List<Product> findByBrandName(String brandName);
    List<Product> findByPhysicalStoreAndProductCategory(String physicalStore, String productCategory);
    List<Product> findByModelAndBrandNameAndPhysicalStore(String model, String brandName, String physicalStore);
}

package com.example.product_web_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.product_web_service.entity.Product;
import com.example.product_web_service.repository.ProductRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Transactional
    public List<Product> findByProductCategory(String productCategory) {
        return productRepository.findByProductCategory(productCategory);
    }


    @Transactional

    public List<Product> findByBrandName(String brandName) {
        return productRepository.findByBrandName(brandName);
    }

    @Transactional

    public List<Product> findByPhysicalStoreAndProductCategory(String physicalStore, String productCategory) {
        return productRepository.findByPhysicalStoreAndProductCategory(physicalStore, productCategory);
    }
    @Transactional

    public List<Product> findByModelAndBrandNameAndPhysicalStore(String model, String brandName, String physicalStore) {
        return productRepository.findByModelAndBrandNameAndPhysicalStore(model, brandName, physicalStore);
    }
}
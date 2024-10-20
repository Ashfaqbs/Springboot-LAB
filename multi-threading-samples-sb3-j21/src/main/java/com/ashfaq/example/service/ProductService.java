package com.ashfaq.example.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ashfaq.example.entity.Product;
import com.ashfaq.example.repository.ProductRepository;

@Service
public class ProductService {
	private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

	private final ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	public Optional<Product> getProductById(Long id) {
		return productRepository.findById(id);
	}

	public Product saveProduct(Product product) {
		logger.info("Creating Product: {}", product); // Default level: INFO
		logger.debug("Debugging Product creation: {}", product); // Will show only when DEBUG is enabled
//		validation logic
		return productRepository.save(product);
	}

	public void deleteProduct(Long id) {
		logger.warn("Deleting user with ID: {}", id); // WARN level

		productRepository.deleteById(id);
	}
}

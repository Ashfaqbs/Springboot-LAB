package com.example.product_web_service.controller;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.product_web_service.entity.Product;
import com.example.product_web_service.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;



@RestController
@CrossOrigin("*")
@RequestMapping("/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {
    private final ProductService productService;
    private final ObjectMapper objectMapper = new ObjectMapper(); // For JSON parsing
    private static final Logger logger = LoggerFactory.getLogger(DataController.class);

    @PostMapping
    public Product createProduct(@RequestPart("product") String dataJson,
                                 @RequestPart("image") MultipartFile image,
                                 @RequestPart("video") MultipartFile video) throws IOException {

        if (!image.getContentType().equals("image/png")&&!image.getContentType().equals("image/jpeg")) {
            throw new IllegalArgumentException("Only PNG and jpeg images are allowed");
        }
        if (!video.getContentType().equals("video/mp4")) {
            throw new IllegalArgumentException("Only MP4 videos are allowed");
        }
        

        log.info("Product data: {}", dataJson);  // Log the product object
        log.info("Image content type: {}", image.getContentType()); // Log the content type
        log.info("Video content type: {}", video.getContentType()); // Log the content type

             Product data = null; // Create an instance of your data class

        try {
            data = objectMapper.readValue(dataJson, Product.class); // Deserialize JSON
            logger.info("Received data object: {}", data);
        } catch (IOException e) {
            logger.error("Error parsing JSON: {}", e.getMessage(), e);
        }

        data.setImage(image.getBytes());
        data.setProductVideo(video.getBytes());
        
        return productService.saveProduct(data);
    }



      @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }



     @GetMapping("/category/{productCategory}")
    public ResponseEntity<List<Product>> findByProductCategory(@PathVariable String productCategory) {
        List<Product> products = productService.findByProductCategory(productCategory);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/brand/{brandName}")
    public ResponseEntity<List<Product>> findByBrandName(@PathVariable String brandName) {
        List<Product> products = productService.findByBrandName(brandName);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/store/{physicalStore}/category/{productCategory}")
    public ResponseEntity<List<Product>> findByPhysicalStoreAndProductCategory(
            @PathVariable String physicalStore,
            @PathVariable String productCategory) {
        List<Product> products = productService.findByPhysicalStoreAndProductCategory(physicalStore, productCategory);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> findByModelAndBrandNameAndPhysicalStore(
            @RequestParam String model,
            @RequestParam String brandName,
            @RequestParam String physicalStore) {
        List<Product> products = productService.findByModelAndBrandNameAndPhysicalStore(model, brandName, physicalStore);
        return ResponseEntity.ok(products);
    }
}
package com.example.product_web_service.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/data")
public class DataController {


    private static final Logger logger = LoggerFactory.getLogger(DataController.class);
    private final ObjectMapper objectMapper = new ObjectMapper(); // For JSON parsing

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<String> receiveData(
            @RequestPart("data") String dataJson, // JSON object as a string
            @RequestPart("image") MultipartFile image // Image file
    ) {

        MyData data = null; // Create an instance of your data class

        try {
            data = objectMapper.readValue(dataJson, MyData.class); // Deserialize JSON
            logger.info("Received data object: {}", data);
        } catch (IOException e) {
            logger.error("Error parsing JSON: {}", e.getMessage(), e);
            return new ResponseEntity<>("Invalid JSON", HttpStatus.BAD_REQUEST); // 400 Bad Request
        }


        if (image != null && !image.isEmpty()) {
            try {
                byte[] imageBytes = image.getBytes();
                logger.info("Received image: {}, size: {} bytes", image.getOriginalFilename(), imageBytes.length);
                // ... (Handle image bytes as needed)
            } catch (IOException e) {
                logger.error("Error processing image: {}", e.getMessage(), e);
                return new ResponseEntity<>("Error processing image", HttpStatus.BAD_REQUEST);
            }
        } else {
            logger.warn("No image file provided.");
        }

        return new ResponseEntity<>("Data received successfully", HttpStatus.OK);
    }

    public static class MyData {
        private String name;
        private int age;
        // ... other fields, getters, and setters

         // Important: Add a toString() method for better logging
        @Override
        public String toString() {
            return "MyData{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }

        // Getters and Setters for the fields
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

    }
}
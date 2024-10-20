package com.ashfaq.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "UploadItemStatus")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadItemStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; // Changed from Long to Integer

	private Long productId; // Product ID

	private Long uploadTaskId; // Foreign key to UploadTask as int

	private String status; // e.g., "Success", "Failed"
	private String comment; // e.g., reason for failure

}

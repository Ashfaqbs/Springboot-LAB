package com.ashfaq.example.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ashfaq.example.entity.Product;
import com.ashfaq.example.entity.UploadItemStatus;
import com.ashfaq.example.entity.UploadStatus;
import com.ashfaq.example.entity.UploadTask;
import com.ashfaq.example.repository.UploadItemStatusRepository;
import com.ashfaq.example.repository.UploadTaskRepository;
import com.ashfaq.example.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping
	public List<Product> getAllProducts() {
		return productService.getAllProducts();
	}

	@GetMapping("/{id}")
	public Product getProductById(@PathVariable Long id) {
		return productService.getProductById(id).get();
	}

	@PostMapping("/save")
	public ResponseEntity<String> saveProduct(@Valid @RequestBody Product product) {
		logger.info("Received request to create user: {}", product);
		productService.saveProduct(product);
		return ResponseEntity.ok("Product saved successfully");
	}

	@PutMapping("/update")
	public ResponseEntity<String> updateProduct(@RequestBody Product product) {
		// Check if the product exists
		Optional<Product> existingProductOptional = productService.getProductById(product.getId());
		if (!existingProductOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
		}

		Product existingProduct = existingProductOptional.get();

		// Update fields only if they are not null, and validate them individually

		// Validate and update 'name' if present
		if (product.getName() != null) {
			String trimmedName = product.getName().trim();
			if (trimmedName.isEmpty() || trimmedName.length() < 2 || trimmedName.length() > 100) {
				return ResponseEntity.badRequest().body("Invalid product name: Must be between 2 and 100 characters");
			}
			existingProduct.setName(trimmedName);
		}

		// Validate and update 'price' if present
		if (product.getPrice() != null) {
			if (product.getPrice() <= 0) {
				return ResponseEntity.badRequest().body("Invalid product price: Must be a positive number");
			}
			existingProduct.setPrice(product.getPrice());
		}

		// Update 'description' if present (validate size)
		if (product.getDescription() != null) {
			String trimmedDescription = product.getDescription().trim();
			if (trimmedDescription.length() > 255) {
				return ResponseEntity.badRequest().body("Invalid product description: Cannot exceed 255 characters");
			}
			existingProduct.setDescription(trimmedDescription);
		}

		// Save the updated product
		productService.saveProduct(existingProduct);

		return ResponseEntity.ok("Product updated successfully");
	}

	@DeleteMapping("/{id}")
	public void deleteProduct(@PathVariable Long id) {
		logger.warn("Received request to delete product with ID: {}", id);

		productService.deleteProduct(id);
	}

	// Mass update Mulithread but not returning future
	@PostMapping("/mass-update")
	public ResponseEntity<Map<Long, String>> massUpdate(@RequestBody List<Product> products) {
		int availableCores = Runtime.getRuntime().availableProcessors();
		ExecutorService executor = Executors.newFixedThreadPool(availableCores - 2);
		logger.info("Mass update initiated with {} products", products.size());

		logger.info("availableCores :{} ", availableCores);
		Map<Long, String> updateStatus = new ConcurrentHashMap<>();
		List<Future<Void>> futures = new ArrayList<>();

		for (Product product : products) {
			Future<Void> future = executor.submit(() -> {
				Optional<Product> existingProduct = productService.getProductById(product.getId());
				if (!existingProduct.isPresent()) {
					updateStatus.put(product.getId(), "Product not found");
					return null;
				}

				// Update logic (same as above)
				Product updatedProduct = existingProduct.get();
				if (product.getName() != null) {
					String trimmedName = product.getName().trim();
					if (!trimmedName.isEmpty()) {
						updatedProduct.setName(trimmedName);
					}
				}
				if (product.getPrice() != null && product.getPrice() > 0) {
					updatedProduct.setPrice(product.getPrice());
				}
				if (product.getDescription() != null) {
					updatedProduct.setDescription(product.getDescription().trim());
				}

				productService.saveProduct(updatedProduct);
				updateStatus.put(product.getId(), "Success");
				return null;
			});
			futures.add(future);
		}

		// Wait for all tasks to complete
		for (Future<Void> future : futures) {
			try {
				future.get(); // This will block until each task is done
			} catch (Exception e) {
				// Handle exception
			}
		}

		executor.shutdown();
		return ResponseEntity.ok(updateStatus);
	}

	// Mass update Multi threaded but returning product as future

	@PostMapping("/mass-update2")
	public ResponseEntity<Map<String, Object>> massUpdate2(@RequestBody List<Product> products) {
		int availableCores = Runtime.getRuntime().availableProcessors();
		ExecutorService executor = Executors.newFixedThreadPool(availableCores - 2);
		logger.info("Mass update initiated with {} products", products.size());

		Map<Long, String> updateStatus = new ConcurrentHashMap<>();
		List<Future<Product>> futures = new ArrayList<>();
		List<Product> updatedProducts = new ArrayList<>();

		for (Product product : products) {
			Future<Product> future = executor.submit(() -> {
				Optional<Product> existingProduct = productService.getProductById(product.getId());
				if (!existingProduct.isPresent()) {
					updateStatus.put(product.getId(), "Product not found");
					return null;
				}

				// Update logic
				Product updatedProduct = existingProduct.get();
				if (product.getName() != null) {
					String trimmedName = product.getName().trim();
					if (!trimmedName.isEmpty()) {
						updatedProduct.setName(trimmedName);
					}
				}
				if (product.getPrice() != null && product.getPrice() > 0) {
					updatedProduct.setPrice(product.getPrice());
				}
				if (product.getDescription() != null) {
					updatedProduct.setDescription(product.getDescription().trim());
				}

				Product savedProduct = productService.saveProduct(updatedProduct);
				updateStatus.put(product.getId(), "Success");
				return savedProduct; // Return the saved product
			});
			futures.add(future);
		}

		// Wait for all tasks to complete and collect updated products
		for (Future<Product> future : futures) {
			try {
				Product updatedProduct = future.get(); // This will block until each task is done
				if (updatedProduct != null) {
					updatedProducts.add(updatedProduct); // Collect updated products
				}
			} catch (Exception e) {
				// Handle exception
			}
		}

		executor.shutdown();

		// Create a response map with both update status and updated products
		Map<String, Object> response = new HashMap<>();
		response.put("status", updateStatus);
		response.put("updatedProducts", updatedProducts); // Add updated products list to the response

		return ResponseEntity.ok(response);
	}

//	We use `ConcurrentHashMap` in multithreading scenarios because it is thread-safe, meaning it allows concurrent access by multiple threads without corrupting the map’s state. Here's a quick comparison of why `ConcurrentHashMap` is chosen over a regular `HashMap`:
//
//	### Key Differences:
//
//	1. **Thread Safety**:
//	   - **ConcurrentHashMap**: It is designed to handle concurrent access by multiple threads. It achieves this by locking only certain parts (or segments) of the map while allowing other threads to work on different segments simultaneously.
//	   - **HashMap**: It is **not** thread-safe. If multiple threads access or modify a `HashMap` concurrently without external synchronization, it can lead to data inconsistency or corruption.
//
//	2. **Performance in Multithreading**:
//	   - **ConcurrentHashMap**: Uses internal locking (based on segments) and allows concurrent read/write operations, which makes it highly efficient for multithreading scenarios.
//	   - **HashMap**: Requires explicit external synchronization (like using `synchronized` blocks) to ensure thread safety, which can lead to performance bottlenecks because only one thread can access the map at a time.
//
//	### Why Use `ConcurrentHashMap` in Your Code:
//	In your case, multiple threads are updating the `updateStatus` map concurrently, and using a `ConcurrentHashMap` ensures that:
//	- Each thread can safely update the map without interfering with others.
//	- You don't need to worry about synchronizing access manually.
//	- Performance is optimized, as multiple threads can access different segments of the map at the same time.
//
//	Using a regular `HashMap` here would risk corruption of the map if two or more threads tried to modify it at the same time.
//

	@Autowired
	UploadTaskRepository uploadTaskRepository;;

	@Autowired
	UploadItemStatusRepository uploadItemStatusRepository;

	@PostMapping("/mass-update3")
	public ResponseEntity<Map<String, Object>> massUpdate3(@RequestBody List<Product> products) {
		int availableCores = Runtime.getRuntime().availableProcessors();
		ExecutorService executor = Executors.newFixedThreadPool(availableCores - 2);
		logger.info("Mass update initiated with {} products", products.size());

		// Step 1: Create the task in the UploadTask table
		UploadTask uploadTask = new UploadTask();
		uploadTask.setProductCount(products.size());
		uploadTask.setUploadDate(LocalDateTime.now());
		uploadTask.setAction("mass-update");
		uploadTask.setStatus(UploadStatus.IN_PROGRESS);
		uploadTask = uploadTaskRepository.save(uploadTask); // Save the task and get the ID
		Long taskId = uploadTask.getId(); // Save the task's ID for future use

		// Step 2: Create a ConcurrentHashMap to track update status for each product
		Map<Long, String> productUpdateStatus = new ConcurrentHashMap<>();

		List<Future<Product>> futures = new ArrayList<>();

		AtomicInteger successCount = new AtomicInteger(0);
		AtomicInteger failCount = new AtomicInteger(0);

		List<Product> updatedProducts = new ArrayList<>();

		// Step 3: Process each product in separate threads
		for (Product product : products) {
			Future<Product> future = executor.submit(() -> {
				Optional<Product> existingProduct = productService.getProductById(product.getId());
				UploadItemStatus itemStatus = new UploadItemStatus();
				itemStatus.setProductId(product.getId());
				itemStatus.setUploadTaskId(taskId); // Link using taskId (int)

				if (!existingProduct.isPresent()) {
					productUpdateStatus.put(product.getId(), "Product not found");
					failCount.incrementAndGet();
					itemStatus.setStatus("Failed");
					itemStatus.setComment("Product not found");
					uploadItemStatusRepository.save(itemStatus); // Track individual status
					return null;
				}

				// Update logic
				Product updatedProduct = existingProduct.get();
				if (product.getName() != null) {
					updatedProduct.setName(product.getName().trim());
				}
				if (product.getPrice() != null && product.getPrice() > 0) {
					updatedProduct.setPrice(product.getPrice());
				}
				if (product.getDescription() != null) {
					updatedProduct.setDescription(product.getDescription().trim());
				}

				Product savedProduct = productService.saveProduct(updatedProduct);
				productUpdateStatus.put(product.getId(), "Success");
				successCount.incrementAndGet();

				itemStatus.setStatus("Success");
				itemStatus.setComment("Updated successfully");
				uploadItemStatusRepository.save(itemStatus); // Track individual status

				return savedProduct; // Return the saved product
			});
			futures.add(future);
		}

		// Wait for all tasks to complete and calculate overall task status as before

		// Wait for all tasks to complete and collect updated products
		for (Future<Product> future : futures) {
			try {
				Product updatedProduct = future.get(); // This will block until each task is done
				if (updatedProduct != null) {
					updatedProducts.add(updatedProduct); // Collect updated products
				}
			} catch (Exception e) {
				failCount.incrementAndGet(); // Track failed tasks

				// Handle exception
				logger.error("Failed to update product: {}", e.getMessage());
				e.printStackTrace();
			}
		}

//		String taskStatus;
//
//		if (successCount.get() == products.size()) {
//			taskStatus = "Success";
//		} else if (failCount.get() == products.size()) {
//			taskStatus = "Failed";
//		} else {
//			taskStatus = "Semi-success";
//		}

		UploadStatus taskStatus;

		if (successCount.get() == products.size()) {
			taskStatus = UploadStatus.SUCCESS;
		} else if (failCount.get() == products.size()) {
			taskStatus = UploadStatus.FAILED;
		} else {
			taskStatus = UploadStatus.SEMI_SUCCESS;
		}

		uploadTask.setStatus(taskStatus);
		uploadTaskRepository.save(uploadTask); // Update the task status in the DB

		// Final task status and return response as before...

		// Create a response map with both update status and updated products
		// Step 6: Build response
		Map<String, Object> response = new HashMap<>();
		response.put("productUpdateStatus", productUpdateStatus); // Status for each product
		response.put("taskStatus", taskStatus); // Final task status
		response.put("uploadTaskId", uploadTask.getId()); // Task ID for tracking

		executor.shutdown();

		return ResponseEntity.ok(response);

	}

	@PostMapping("/mass-update4")
	public ResponseEntity<Map<String, Object>> massUpdate4(@RequestParam("file") MultipartFile file) {

		int availableCores = Runtime.getRuntime().availableProcessors();
		ExecutorService executor = Executors.newFixedThreadPool(availableCores - 2);

		// Step 1: Validate the file format
		if (!file.getOriginalFilename().endsWith(".xlsx")) {
			return ResponseEntity.badRequest().body(
					Collections.singletonMap("status", "Invalid file format. Please upload an Excel (.xlsx) file."));
		}

		// Step 2: Read the Excel file using Apache POI
		XSSFWorkbook workbook = null;
		try {
			workbook = new XSSFWorkbook(file.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}

		XSSFSheet sheet = workbook.getSheetAt(0);
		// Step 3: Validate headers
		Row headerRow = sheet.getRow(0);
		if (!validateHeaders(headerRow)) {
			return ResponseEntity.badRequest()
					.body(Collections.singletonMap("status", "Invalid headers in the Excel file."));
		}

		// Step 4: Convert valid Excel rows to List of Products
		List<Product> products = new ArrayList<>();
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);
//			if (!validateRowData(row)) {
//				return ResponseEntity.badRequest()
//						.body(Collections.singletonMap("status", "Invalid data in row " + (i + 1)));
//			}
			Product product = convertRowToProduct(row);
			products.add(product);
		}
		try {
			workbook.close();
		} catch (IOException e) {
			logger.error("Error :{}", e);
			e.printStackTrace();
		}

		logger.info("Mass update initiated with {} products", products.size());

		// Step 1: Create the task in the UploadTask table
		UploadTask uploadTask = new UploadTask();
		uploadTask.setProductCount(products.size());
		uploadTask.setUploadDate(LocalDateTime.now());
		uploadTask.setAction("mass-update");
		uploadTask.setStatus(UploadStatus.IN_PROGRESS);
		uploadTask = uploadTaskRepository.save(uploadTask); // Save the task and get the ID
		Long taskId = uploadTask.getId(); // Save the task's ID for future use

		// Step 2: Create a ConcurrentHashMap to track update status for each product
		Map<Long, String> productUpdateStatus = new ConcurrentHashMap<>();

		List<Future<Product>> futures = new ArrayList<>();

		AtomicInteger successCount = new AtomicInteger(0);
		AtomicInteger failCount = new AtomicInteger(0);

		List<Product> updatedProducts = new ArrayList<>();

		// Step 3: Process each product in separate threads
		for (Product product : products) {
			Future<Product> future = executor.submit(() -> {
				Optional<Product> existingProduct = productService.getProductById(product.getId());
				UploadItemStatus itemStatus = new UploadItemStatus();
				itemStatus.setProductId(product.getId());
				itemStatus.setUploadTaskId(taskId); // Link using taskId (int)

				if (!existingProduct.isPresent()) {
					productUpdateStatus.put(product.getId(), "Product not found");
					failCount.incrementAndGet();
					itemStatus.setStatus("Failed");
					itemStatus.setComment("Product not found");
					uploadItemStatusRepository.save(itemStatus); // Track individual status
					return null;
				}

				// Update logic
				Product updatedProduct = existingProduct.get();
				if (product.getName() != null) {
					updatedProduct.setName(product.getName().trim());
				}
				if (product.getPrice() != null && product.getPrice() > 0) {
					updatedProduct.setPrice(product.getPrice());
				}
				if (product.getDescription() != null) {
					updatedProduct.setDescription(product.getDescription().trim());
				}

				Product savedProduct = productService.saveProduct(updatedProduct);
				productUpdateStatus.put(product.getId(), "Success");
				successCount.incrementAndGet();

				itemStatus.setStatus("Success");
				itemStatus.setComment("Updated successfully");
				uploadItemStatusRepository.save(itemStatus); // Track individual status

				return savedProduct; // Return the saved product
			});
			futures.add(future);
		}

		// Wait for all tasks to complete and collect updated products
		for (Future<Product> future : futures) {
			try {
				Product updatedProduct = future.get(); // This will block until each task is done
				if (updatedProduct != null) {
					updatedProducts.add(updatedProduct); // Collect updated products
				}
			} catch (Exception e) {
				// Handle exception
				failCount.incrementAndGet(); // Track failed tasks

			}
		}

		// Wait for all tasks to complete and calculate overall task status as before

//		String taskStatus;
//
//		if (successCount.get() == products.size()) {
//			taskStatus = "Success";
//		} else if (failCount.get() == products.size()) {
//			taskStatus = "Failed";
//		} else {
//			taskStatus = "Semi-success";
//		}
//String to Enums use as good practice because enums provide type safety and allow you to define a fixed set of values for a field, preventing invalid values from being stored.
		UploadStatus taskStatus;

		if (successCount.get() == products.size()) {
			taskStatus = UploadStatus.SUCCESS;
		} else if (failCount.get() == products.size()) {
			taskStatus = UploadStatus.FAILED;
		} else {
			taskStatus = UploadStatus.SEMI_SUCCESS;
		}

		uploadTask.setStatus(taskStatus);
		uploadTaskRepository.save(uploadTask); // Update the task status in the DB

		// Final task status and return response as before...

		// Create a response map with both update status and updated products
		// Step 6: Build response
		Map<String, Object> response = new HashMap<>();
		response.put("productUpdateStatus", productUpdateStatus); // Status
																	// for each
																	// product
		response.put("taskStatus", taskStatus); // Final task status
		response.put("uploadTaskId", uploadTask.getId()); // Task ID for tracking

		executor.shutdown();

		return ResponseEntity.ok(response);

	}





	@PostMapping("/mass-update5")
	public ResponseEntity<Map<String, Object>> massUpdateWithCompletableFuture(@RequestParam MultipartFile file) {

		int availableCores = Runtime.getRuntime().availableProcessors();
		ExecutorService executor = Executors.newFixedThreadPool(availableCores - 2);

		// Step 1: Validate the file format
		if (!file.getOriginalFilename().endsWith(".xlsx")) {
			return ResponseEntity.badRequest().body(
					Collections.singletonMap("status", "Invalid file format. Please upload an Excel (.xlsx) file."));
		}

		// Step 2: Read the Excel file using Apache POI
		XSSFWorkbook workbook = null;
		try {
			workbook = new XSSFWorkbook(file.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}

		XSSFSheet sheet = workbook.getSheetAt(0);

		// Step 3: Validate headers
		Row headerRow = sheet.getRow(0);
		if (!validateHeaders(headerRow)) {
			return ResponseEntity.badRequest()
					.body(Collections.singletonMap("status", "Invalid headers in the Excel file."));
		}

		// Step 4: Convert valid Excel rows to List of Products
		List<Product> products = new ArrayList<>();
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);
			Product product = convertRowToProduct(row);
			products.add(product);
		}
		try {
			workbook.close();
		} catch (IOException e) {
			logger.error("Error :{}", e);
			e.printStackTrace();
		}

		logger.info("Mass update initiated with {} products", products.size());

		// Step 1: Create the task in the UploadTask table
		UploadTask uploadTask = new UploadTask();
		uploadTask.setProductCount(products.size());
		uploadTask.setUploadDate(LocalDateTime.now());
		uploadTask.setAction("mass-update");
		uploadTask.setStatus(UploadStatus.IN_PROGRESS);
		uploadTask = uploadTaskRepository.save(uploadTask); // Save the task and get the ID
		Long taskId = uploadTask.getId(); // Save the task's ID for future use

		// Step 2: Create a ConcurrentHashMap to track update status for each product
		Map<Long, String> productUpdateStatus = new ConcurrentHashMap<>();

		AtomicInteger successCount = new AtomicInteger(0);
		AtomicInteger failCount = new AtomicInteger(0);

		// Step 3: Process each product asynchronously using CompletableFuture
		List<CompletableFuture<Void>> futures = products.stream().map(product -> CompletableFuture.supplyAsync(() -> {
			Optional<Product> existingProduct = productService.getProductById(product.getId());
			UploadItemStatus itemStatus = new UploadItemStatus();
			itemStatus.setProductId(product.getId());
			itemStatus.setUploadTaskId(taskId); // Link using taskId (int)

			if (!existingProduct.isPresent()) {
				productUpdateStatus.put(product.getId(), "Product not found");
				failCount.incrementAndGet();
				itemStatus.setStatus("Failed");
				itemStatus.setComment("Product not found");
				uploadItemStatusRepository.save(itemStatus); // Track individual status
				return null;
			}

			// Update logic
			Product updatedProduct = existingProduct.get();
			if (product.getName() != null) {
				updatedProduct.setName(product.getName().trim());
			}
			if (product.getPrice() != null && product.getPrice() > 0) {
				updatedProduct.setPrice(product.getPrice());
			}
			if (product.getDescription() != null) {
				updatedProduct.setDescription(product.getDescription().trim());
			}

			Product savedProduct = productService.saveProduct(updatedProduct);
			productUpdateStatus.put(product.getId(), "Success");
			successCount.incrementAndGet();

			itemStatus.setStatus("Success");
			itemStatus.setComment("Updated successfully");
			uploadItemStatusRepository.save(itemStatus); // Track individual status

			return savedProduct;
		}, executor).thenAccept(savedProduct -> {
			// You can perform any other action here if needed
		}).exceptionally(ex -> {
			failCount.incrementAndGet(); // Track failed tasks
			return null;
		})).collect(Collectors.toList());

		// Wait for all tasks to complete
		CompletableFuture<Void> allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
		allOf.join(); // Wait for all tasks to complete

		// Determine final task status
		UploadStatus taskStatus;
		if (successCount.get() == products.size()) {
			taskStatus = UploadStatus.SUCCESS;
		} else if (failCount.get() == products.size()) {
			taskStatus = UploadStatus.FAILED;
		} else {
			taskStatus = UploadStatus.SEMI_SUCCESS;
		}

		uploadTask.setStatus(taskStatus);
		uploadTaskRepository.save(uploadTask); // Update the task status in the DB

		// Step 6: Build response
		Map<String, Object> response = new HashMap<>();
		response.put("productUpdateStatus", productUpdateStatus); // Status for each product
		response.put("taskStatus", taskStatus); // Final task status
		response.put("uploadTaskId", uploadTask.getId()); // Task ID for tracking

		executor.shutdown();

		return ResponseEntity.ok(response);
	}







	
//	private Product convertRowToProduct(Row row) {
//		Product product = new Product();
//
//		// Assuming the first column is productId, second is name, third is price, and
//		// so on.
//		product.setId((long) row.getCell(0).getNumericCellValue()); // Product ID
//		product.setName(row.getCell(1).getStringCellValue().trim()); // Name
//		product.setPrice(row.getCell(2).getNumericCellValue()); // Price
//		product.setDescription(row.getCell(3).getStringCellValue().trim()); // Description
//
//		return product;
//	}

	// handles null
	private Product convertRowToProduct(Row row) {
		Product product = new Product();

		// Handle Product ID (assuming it's mandatory)
		if (row.getCell(0) != null && row.getCell(0).getCellType() == CellType.NUMERIC) {
			product.setId((long) row.getCell(0).getNumericCellValue());
		} else {
			// Product ID must always be present, handle this according to your use case
			throw new IllegalArgumentException("Product ID is mandatory and cannot be null or empty.");
		}

		// Handle Name
		if (row.getCell(1) != null && row.getCell(1).getCellType() == CellType.STRING) {
			String name = row.getCell(1).getStringCellValue().trim();
			product.setName(!name.isEmpty() ? name : null);
		} else {
			product.setName(null); // If the cell is empty, set name to null
		}

		// Handle Price
		if (row.getCell(2) != null && row.getCell(2).getCellType() == CellType.NUMERIC) {
			product.setPrice(row.getCell(2).getNumericCellValue());
		} else {
			product.setPrice(null); // If the cell is empty, set price to null
		}

		// Handle Description
		if (row.getCell(3) != null && row.getCell(3).getCellType() == CellType.STRING) {
			String description = row.getCell(3).getStringCellValue().trim();
			product.setDescription(!description.isEmpty() ? description : null);
		} else {
			product.setDescription(null); // If the cell is empty, set description to null
		}

		return product;
	}

	private boolean validateHeaders(Row headerRow) {
		// Define the expected headers in the correct order

		List<String> expectedHeaders = Arrays.asList("Product ID", "Name", "Price", "Description");

		for (int i = 0; i < expectedHeaders.size(); i++) {
			Cell cell = headerRow.getCell(i);
			String header = cell.getStringCellValue().trim();
			if (!expectedHeaders.get(i).equalsIgnoreCase(header)) {
				return false; // Invalid header
			}
		}
		return true;
	}

//	private boolean validateRowData(Row row) {
//		try {
//			// Validate productId (should be a number)
//			Cell productIdCell = row.getCell(0);
//			if (productIdCell == null || productIdCell.getCellType() != CellType.NUMERIC) {
//				return false;
//			}
//
//			// Validate uploadTaskId (should be a number)
//			Cell uploadTaskIdCell = row.getCell(1);
//			if (uploadTaskIdCell == null || uploadTaskIdCell.getCellType() != CellType.NUMERIC) {
//				return false;
//			}
//
//			// Validate status (should be a string)
//			Cell statusCell = row.getCell(2);
//			if (statusCell == null || statusCell.getCellType() != CellType.STRING) {
//				return false;
//			}
//
//			// Validate comment (optional, but should be a string if present)
//			Cell commentCell = row.getCell(3);
//			if (commentCell != null && commentCell.getCellType() != CellType.STRING) {
//				return false;
//			}
//
//			return true;
//		} catch (Exception e) {
//			return false; // If any exception occurs, treat the row as invalid
//		}
//	}

}

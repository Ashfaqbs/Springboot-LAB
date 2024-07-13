package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.demo.service.ExcelService;

@RestController
public class ExcelController {

	@Autowired
	private ExcelService excelService;
//process the EXCEL from the UI to API

	@PostMapping("/upload-excel")
	public ResponseEntity<String> uploadExcel(@RequestParam("file") MultipartFile file) {
		try {
			excelService.readExcel(file);
			return ResponseEntity.ok("File read successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Failed to read file: " + e.getMessage());
		}
	}
}
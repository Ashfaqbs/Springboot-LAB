package com.ashfaq.dev.ai;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.nio.file.*;
import java.util.List;

public class TxtToExcelConverter {

	public static void main(String[] args) {
		// Specify the folder path containing .txt files
		String inputFolderPath = "C:\\Users\\ashfa\\OneDrive\\Documents\\input";
		// Specify the output folder path for Excel files
		String outputFolderPath = "C:\\Users\\ashfa\\OneDrive\\Documents\\output";

		// Ensure the output folder exists
		File outputFolder = new File(outputFolderPath);
		if (!outputFolder.exists()) {
			outputFolder.mkdirs();
		}

		// Convert each .txt file to a corresponding .xlsx file
		try {
			convertTxtFilesToExcel(inputFolderPath, outputFolderPath);
			System.out.println("Excel files generated successfully!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void convertTxtFilesToExcel(String inputFolderPath, String outputFolderPath) throws IOException {
		// Get all .txt files from the specified folder
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(inputFolderPath), "*.txt")) {
			for (Path filePath : stream) {
				// Get the file name without extension
				String fileName = filePath.getFileName().toString().replaceFirst("[.][^.]+$", "");
				// Define the output Excel file path
				String excelFilePath = outputFolderPath + "/" + fileName + ".xlsx";

				// Read the content of the .txt file
				List<String> lines = Files.readAllLines(filePath);

				// Create a new Workbook and Sheet
				Workbook workbook = new XSSFWorkbook();
				Sheet sheet = workbook.createSheet("Data");

				// Write the content to the Excel sheet
				int rowNum = 0;
				for (String line : lines) {
					Row row = sheet.createRow(rowNum++);
					String[] columns = line.split(","); // Assuming tab-separated values in .txt files
					for (int i = 0; i < columns.length; i++) {
						Cell cell = row.createCell(i);
						cell.setCellValue(columns[i]);
					}
				}

				// Write the workbook to the specified file
				try (FileOutputStream fileOut = new FileOutputStream(excelFilePath)) {
					workbook.write(fileOut);
				}

				// Close the workbook
				workbook.close();
			}
		}
	}
}

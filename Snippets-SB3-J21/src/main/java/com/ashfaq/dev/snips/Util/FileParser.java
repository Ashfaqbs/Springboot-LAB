package com.ashfaq.dev.ai;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class FileParser {
    public static List<Record> parseFile(String filePath) throws IOException, CsvValidationException {
        List<Record> records = new ArrayList<>();
        try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
            String[] nextLine;
            csvReader.readNext(); // Read and ignore the first line (column names)

            while ((nextLine = csvReader.readNext()) != null) {
                // Parse the primary key (assume it's the first field)
            	   if (nextLine[0].trim().isEmpty()) {
                       System.err.println("Empty primary key found, skipping this row");
                       continue;
                   }
                int primaryKey = Integer.parseInt(nextLine[0].trim());

                // Parse other fields and remove quotes from strings
                String name = nextLine[1].isEmpty() ? null : nextLine[1].replaceAll("^\"|\"$", "");
                String age = nextLine[2].isEmpty() ? null : nextLine[2].replaceAll("^\"|\"$", "");
                String city = nextLine[3].isEmpty() ? null : nextLine[3].replaceAll("^\"|\"$", "");
                String remarks = nextLine[4].isEmpty() ? null : nextLine[4].replaceAll("^\"|\"$", "");
//String other = nextLine[5].isEmpty() ? null : nextLine[5].replaceAll("^\"|\"$", "");
                String other = nextLine.length > 5 ? (nextLine[5].isEmpty() ? null : nextLine[5].replaceAll("^\"|\"$", "")) : null;

                // Create a Record object and add it to the list
                Record record = new Record(primaryKey, name, age, city, remarks,other);
                records.add(record);
            }
        }
        return records;
    }
    public static void writeToExcel(List<Record> records, String excelFilePath) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Records");

        // Create header row
        String[] headers = {"ID", "Name", "Age", "City", "Remarks","Other"};
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }

        // Create data rows
        int rowNum = 1;
        for (Record record : records) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(record.getPrimaryKey());
            row.createCell(1).setCellValue(record.getName());
            row.createCell(2).setCellValue(record.getAge());
            row.createCell(3).setCellValue(record.getCity());
            row.createCell(4).setCellValue(record.getRemarks());
            row.createCell(5).setCellValue(record.getOther());

        }

        // Write the output to a file
        try (FileOutputStream fileOut = new FileOutputStream(excelFilePath)) {
            workbook.write(fileOut);
        }

        // Closing the workbook
        workbook.close();
    }

    public static void main(String[] args) {
        try {
            List<Record> records = parseFile("data.txt");
            writeToExcel(records, "output.xlsx");
//            System.out.println("Excel file created successfully.");


            records.forEach(System.out::println);
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }
}

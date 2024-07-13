package com.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/compare")
public class CompareExcelData {

  @PostMapping
  public List<String> compareExcelFiles(@RequestParam("file1") MultipartFile file1,
      @RequestParam("file2") MultipartFile file2) {
    List<String> differences = new ArrayList<>();
    try {
      // Read the data from the first Excel file
      Workbook workbook1 = new XSSFWorkbook(file1.getInputStream());
      Sheet sheet1 = workbook1.getSheetAt(0);

      // Read the data from the second Excel file
      Workbook workbook2 = new XSSFWorkbook(file2.getInputStream());
      Sheet sheet2 = workbook2.getSheetAt(0);

      // Compare each row in the first Excel file with each row in the second Excel file
      for (int i = 0; i < sheet1.getLastRowNum(); i++) {
        Row row1 = sheet1.getRow(i);
        Row row2 = sheet2.getRow(i);

        // Compare each cell in the row
        for (int j = 0; j < row1.getLastCellNum(); j++) {
          Cell cell1 = row1.getCell(j);
          Cell cell2 = row2.getCell(j);

          // If the data in the cells is different, add it to the list of differences
//          if (!cell1.getStringCellValue().equals(cell2.getStringCellValue())) {
//            differences.add("Row " + (i + 1) + ", Column " + (j + 1) + ": " + cell1.getStringCellValue()
//                + " vs " + cell2.getStringCellValue());
//          }
//          System.out.println(cell1 + " "+cell2);

          if(cell1.toString()!= cell2.toString())
          {
        	  System.out.println( cell1  + "  " + cell2 + "not equals");
          }
          
        }
      }

      // Close the workbooks
      workbook1.close();
      workbook2.close();
    } catch (Exception e) {
      e.printStackTrace();
    }

    return differences;
  }
}

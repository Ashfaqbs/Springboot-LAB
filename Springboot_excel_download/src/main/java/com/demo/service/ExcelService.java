package com.example.excelupload.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ExcelService {


   public List<Employee> readExcel2(MultipartFile file) throws Exception {
        List<Employee> employees = new ArrayList<>();

        try (InputStream inputStream = file.getInputStream()) {
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getRowNum() == 0) { // Skip header row
                    continue;
                }

                Employee employee = new Employee();
                for (Cell cell : row) {
                    switch (cell.getColumnIndex()) {
                        case 0:
                            employee.setName(cell.getStringCellValue());
                            break;
                        case 1:
                            employee.setAge((int) cell.getNumericCellValue());
                            break;
                        case 2:
                            employee.setDepartment(cell.getStringCellValue());
                            break;
                        case 3:
                            employee.setSalary(cell.getNumericCellValue());
                            break;
                    }
                }
                employees.add(employee);
            }
        }
        return employees;
    }



    public void readExcel(MultipartFile file) throws Exception {
        try (InputStream inputStream = file.getInputStream()) {
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                for (Cell cell : row) {
                    switch (cell.getCellType()) {
                        case STRING:
                            System.out.print(cell.getStringCellValue() + "\t");
                            break;
                        case NUMERIC:
                            System.out.print(cell.getNumericCellValue() + "\t");
                            break;
                        case BOOLEAN:
                            System.out.print(cell.getBooleanCellValue() + "\t");
                            break;
                        default:
                            System.out.print("Unknown Cell Type" + "\t");
                            break;
                    }
                }
                System.out.println();
            }
        }
    }
}
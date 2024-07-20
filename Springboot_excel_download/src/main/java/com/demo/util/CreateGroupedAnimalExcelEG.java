package com.demo.util;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CreateGroupedAnimalExcelEG {
	
	public static void addImageToSheet(Workbook workbook, Sheet sheet, String imagePath, int col1, int row1, int col2, int row2) throws IOException {
        try (InputStream inputStream = new FileInputStream(imagePath)) {
            byte[] bytes = IOUtils.toByteArray(inputStream);
            int pictureIdx = workbook.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);

            Drawing<?> drawing = sheet.createDrawingPatriarch();
            ClientAnchor anchor = workbook.getCreationHelper().createClientAnchor();
            anchor.setCol1(col1);
            anchor.setRow1(row1);
            anchor.setCol2(col2);
            anchor.setRow2(row2);
            anchor.setAnchorType(ClientAnchor.AnchorType.DONT_MOVE_AND_RESIZE);

            drawing.createPicture(anchor, pictureIdx);
        }
    }
    public static void main(String[] args) throws IOException {
        // Define your Animal class and animalList here (not shown for brevity)
        List<Animal> animalList = new ArrayList<>();

        // Adding 15 animals to the list
        animalList.add(new Animal(1, "Elephant", "Herbivore", "Large mammal with a trunk", "Savannah", 70));
        animalList.add(new Animal(2, "Tiger", "Carnivore", "Big cat with stripes", "Forest", 15));
        animalList.add(new Animal(3, "Kangaroo", "Herbivore", "Marsupial with strong legs", "Grasslands", 20));
        animalList.add(new Animal(4, "Panda", "Herbivore", "Bear that eats bamboo", "Forest", 20));
        animalList.add(new Animal(5, "Lion", "Carnivore", "King of the jungle", "Savannah", 14));
        animalList.add(new Animal(6, "Dolphin", "Carnivore", "Intelligent aquatic mammal", "Ocean", 30));
        animalList.add(new Animal(7, "Giraffe", "Herbivore", "Tallest land animal", "Savannah", 25));
        animalList.add(new Animal(8, "Penguin", "Carnivore", "Flightless bird", "Antarctic", 20));
        animalList.add(new Animal(9, "Eagle", "Carnivore", "Bird of prey with sharp vision", "Mountains", 20));
        animalList.add(new Animal(10, "Rabbit", "Herbivore", "Small mammal with long ears", "Grasslands", 9));
        animalList.add(new Animal(11, "Shark", "Carnivore", "Apex predator of the sea", "Ocean", 30));
        animalList.add(new Animal(12, "Horse", "Herbivore", "Domesticated mammal used for riding", "Grasslands", 25));
        animalList.add(new Animal(13, "Wolf", "Carnivore", "Pack animal with strong social bonds", "Forest", 13));
        animalList.add(new Animal(14, "Parrot", "Omnivore", "Colorful bird that can mimic sounds", "Tropical", 60));
        animalList.add(new Animal(15, "Crocodile", "Carnivore", "Large reptile with strong jaws", "Rivers", 70));

        // Group animals by type
        Map<String, List<Animal>> groupedAnimals = new HashMap<>();
        for (Animal animal : animalList) {
            groupedAnimals.computeIfAbsent(animal.getType(), k -> new ArrayList<>()).add(animal);
        }

        // Create the Excel workbook
        Workbook workbook = new XSSFWorkbook();

        // Path to the image
        String imagePath = "img.png";
        
        // Create a sheet for each group
        for (Map.Entry<String, List<Animal>> entry : groupedAnimals.entrySet()) {
            String sheetName = entry.getKey();
            List<Animal> groupList = entry.getValue();
            Sheet sheet = workbook.createSheet(sheetName);

            // Create the heading in the 4th row (A4)
            Row headingRow = sheet.createRow(3);
            Cell headingCell = headingRow.createCell(0);
            headingCell.setCellValue("Animals Heading");

            // Create a cell style with bold font and dark blue background
            CellStyle headingStyle = workbook.createCellStyle();
            Font headingFont = workbook.createFont();
            headingFont.setBold(true);
            headingFont.setFontHeightInPoints((short) 16); // Increase font size for heading
            headingStyle.setFont(headingFont);
            headingStyle.setAlignment(HorizontalAlignment.CENTER);

            // Set dark blue background color
            XSSFColor darkBlue = new XSSFColor(new Color(0, 0, 139), null); // Dark blue color
            ((XSSFCellStyle) headingStyle).setFillForegroundColor(darkBlue);
            headingStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            headingCell.setCellStyle(headingStyle);

            // Merge the heading cell across columns A-F (0 to 5)
            sheet.addMergedRegion(new CellRangeAddress(3, 3, 0, 5));

            // Add the summary in the 5th row (A5)
            Row summaryRow = sheet.createRow(4); // 4 corresponds to the 5th row in Excel (index starts from 0)
            Cell summaryCell = summaryRow.createCell(0);
            String summaryText = "This sheet contains information about various animals. " +
                    "Each row provides details about a specific animal including its ID, Name, Type, Description, " +
                    "Habitat, and Lifespan. The data starts from the 10th row with headers provided on the 9th row.";
            summaryCell.setCellValue(summaryText);

            // Create a cell style for wrapping text and setting width
            CellStyle summaryStyle = workbook.createCellStyle();
            summaryStyle.setWrapText(true); // Enable text wrapping
            summaryCell.setCellStyle(summaryStyle);

            // Merge the summary cell across columns A-E (0 to 4)
            sheet.addMergedRegion(new CellRangeAddress(4, 4, 0, 4)); // Merge in the same row (5th row, index 4) across columns A to E

            // Autofit the row height to fit the content
            summaryRow.setHeight((short) -1);

            // Set column widths to ensure the summary fits well
            for (int i = 0; i <= 4; i++) {
                sheet.autoSizeColumn(i);
            }

            // Simulate existing data in F1 and F2
            Row row1 = sheet.createRow(0);
            row1.createCell(5).setCellValue("Data F1");
            row1.createCell(6).setCellValue("Data G1");

            Row row2 = sheet.createRow(1);
            row2.createCell(5).setCellValue("Data F2");
            row2.createCell(6).setCellValue("Data G2");

            // Create header row (9th row in Excel, which is index 8)
            Row headerRow = sheet.createRow(8);
            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("Name");
            headerRow.createCell(2).setCellValue("Type");
            headerRow.createCell(3).setCellValue("Description");
            headerRow.createCell(4).setCellValue("Habitat");
            headerRow.createCell(5).setCellValue("Lifespan");

            // Fill data rows starting from the 10th row (index 9)
            int rowNum = 9;
            if (groupList.isEmpty()) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue("No Data found");
            } else {
                for (Animal animal : groupList) {
                    Row row = sheet.createRow(rowNum++);
                    row.createCell(0).setCellValue(animal.getId());
                    row.createCell(1).setCellValue(animal.getName());
                    row.createCell(2).setCellValue(animal.getType());
                    row.createCell(3).setCellValue(animal.getDescription());
                    row.createCell(4).setCellValue(animal.getHabitat());
                    row.createCell(5).setCellValue(animal.getLifespan());
                }
            }

            // Add an image to the workbook
            try (FileInputStream inputStream = new FileInputStream(imagePath)) {
                byte[] bytes = IOUtils.toByteArray(inputStream);
                int pictureIdx = workbook.addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);

                XSSFDrawing drawing = (XSSFDrawing) sheet.createDrawingPatriarch();
                XSSFClientAnchor anchor = new XSSFClientAnchor();

                // Set top-left corner of the image (B1)
                anchor.setCol1(1);
                anchor.setRow1(0);
                // Set bottom-right corner of the image (C2)
                anchor.setCol2(3);
                anchor.setRow2(2);

                drawing.createPicture(anchor, pictureIdx);
            }
        }

        // Write the workbook to a file
        try (FileOutputStream fileOut = new FileOutputStream("final.xlsx")) {
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Close the workbook
        workbook.close();

        System.out.println("Excel file 'final.xlsx' created successfully with grouped animal data!");
    }
}


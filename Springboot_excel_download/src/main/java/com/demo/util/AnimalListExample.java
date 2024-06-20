package com.ashfaq.dev.ai;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
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

public class AnimalListExample {
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

        // Create the Excel workbook and sheet
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Animals");

        // Simulate existing data in F1 and F2
        Row row1 = sheet.createRow(0);
        row1.createCell(5).setCellValue("Data F1");
        row1.createCell(6).setCellValue("Data G1");

        Row row2 = sheet.createRow(1);
        row2.createCell(5).setCellValue("Data F2");
        row2.createCell(6).setCellValue("Data G2");

        // Create the heading in the 4th row (A4)
        Row headingRow = sheet.createRow(3);
        Cell headingCell = headingRow.createCell(0);
        headingCell.setCellValue("Animals Heading");

        // Create a cell style with bold font and dark blue background
        CellStyle headingStyle = workbook.createCellStyle();
        Font headingFont = workbook.createFont();
        headingFont.setBold(true);
        headingFont.setFontHeightInPoints((short) 16); // Increase font size for heading
        headingFont.setColor(IndexedColors.WHITE.getIndex()); // Set font color to white
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

        // Merge the summary cell across columns A-F (0 to 5)
        sheet.addMergedRegion(new CellRangeAddress(4, 7, 0, 5)); // Merge from row 5 to 8 (index 4 to 7) and columns A to F

        // Autofit the row height to fit the content
        summaryRow.setHeight((short) -1);

        // Set column widths to ensure the summary fits well
        for (int i = 0; i <= 5; i++) {
            sheet.autoSizeColumn(i);
        }

        // Create header row (9th row in Excel, which is index 8)
        Row headerRow = sheet.createRow(8);
        String[] headers = {"ID", "Name", "Type", "Description", "Habitat", "Lifespan"};
        for (int i = 0; i < headers.length; i++) {
            Cell headerCell = headerRow.createCell(i);
            headerCell.setCellValue(headers[i]);

            // Set light blue background color for headers
            CellStyle headerStyle = workbook.createCellStyle();
            XSSFColor lightBlue = new XSSFColor(new Color(173, 216, 230), null); // Light blue color
            ((XSSFCellStyle) headerStyle).setFillForegroundColor(lightBlue);
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            headerCell.setCellStyle(headerStyle);
        }

        // Fill data rows starting from the 10th row (index 9)
        int rowNum = 9;
        if (animalList.isEmpty()) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue("No Data found");
        } else {
            for (Animal animal : animalList) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(animal.getId());
                row.createCell(1).setCellValue(animal.getName());
                row.createCell(2).setCellValue(animal.getType());
                row.createCell(3).setCellValue(animal.getDescription());
                row.createCell(4).setCellValue(animal.getHabitat());
                row.createCell(5).setCellValue(animal.getLifespan());

                // Add borders to cells
                for (int i = 0; i <= 5; i++) {
                    Cell cell = row.getCell(i);
                    CellStyle cellStyle = workbook.createCellStyle();
                    cellStyle.setBorderBottom(BorderStyle.THIN);
                    cellStyle.setBorderTop(BorderStyle.THIN);
                    cellStyle.setBorderRight(BorderStyle.THIN);
                    cellStyle.setBorderLeft(BorderStyle.THIN);
                    cell.setCellStyle(cellStyle);
                }
            }
        }

        // Add an image to the workbook
        try (FileInputStream inputStream = new FileInputStream("img.png")) {
            byte[] bytes = IOUtils.toByteArray(inputStream);
            int pictureIdx = workbook.addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);

            XSSFDrawing drawing = (XSSFDrawing) sheet.createDrawingPatriarch();
            XSSFClientAnchor anchor = new XSSFClientAnchor();

            // Set top-left corner of the image (B1)
            anchor.setCol1(1);
            anchor.setRow1(0);
            // Set bottom-right corner of the image (C3)
            anchor.setCol2(3);
            anchor.setRow2(3);

            drawing.createPicture(anchor, pictureIdx);
        }

        // Auto-size all columns
        for (int i = 0; i <= 6; i++) {
            sheet.autoSizeColumn(i);
        }

        // Write the workbook to a file
        try (FileOutputStream fileOut = new FileOutputStream("AnimalsWithImageAndHeading.xlsx")) {
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Close the workbook
        workbook.close();

        System.out.println("Excel file created successfully with image and heading!");
    }
}



package org.example.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.model.Contact;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader {

    public static List<Contact> readExcelFile(String inputFilePath) {
        List<Contact> contacts = new ArrayList<>();

        try (InputStream fis = ExcelReader.class.getResourceAsStream(inputFilePath); Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0); // Assumes the first sheet
            for (Row row : sheet) {
                if (row.getRowNum() == 0) { // Skip header row
                    continue;
                }

                String contactId = getCellValue(row.getCell(0));
                String name = getCellValue(row.getCell(1));
                String name1 = getCellValue(row.getCell(2));
                String email = getCellValue(row.getCell(3));
                String postalZip = getCellValue(row.getCell(4));
                String address = getCellValue(row.getCell(5));

                contacts.add(new Contact(contactId, name, name1, email, postalZip, address));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return contacts;
    }

    private static String getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        return cell.getCellType() == CellType.STRING ? cell.getStringCellValue() : String.valueOf((int) cell.getNumericCellValue());
    }
}

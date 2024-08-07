package org.example.utils;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelWriter {
    public static void writeResultsToExcel(List<DuplicateFinder.DuplicateResult> results, String outputPath) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Duplicates");

        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ContactID Source");
        headerRow.createCell(1).setCellValue("ContactID Match");
        headerRow.createCell(2).setCellValue("Accuracy");

        int rowNum = 1;
        for (DuplicateFinder.DuplicateResult result : results) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(result.getContactId());
            row.createCell(1).setCellValue(result.getDuplicated());
            row.createCell(2).setCellValue(result.getScore());
        }

        try (FileOutputStream fos = new FileOutputStream(outputPath)) {
            workbook.write(fos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

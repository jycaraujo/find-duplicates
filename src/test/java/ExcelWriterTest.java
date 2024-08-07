import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.utils.DuplicateFinder;
import org.example.utils.ExcelWriter;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExcelWriterTest {

    @Test
    public void testWriteResultsToExcel() throws IOException {
        List<DuplicateFinder.DuplicateResult> results = new ArrayList<>();
        results.add(new DuplicateFinder.DuplicateResult("123", "456", "High"));
        results.add(new DuplicateFinder.DuplicateResult("789", "012", "Low"));

        String outputPath = "test_results.xlsx";

        ExcelWriter.writeResultsToExcel(results, outputPath);

        try (FileInputStream fis = new FileInputStream(new File(outputPath));
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet("Duplicates");
            assertTrue(sheet != null, "Sheet 'Duplicates' should exist.");
            Row headerRow = sheet.getRow(0);
            assertEquals("ContactID Source", headerRow.getCell(0).getStringCellValue());
            assertEquals("ContactID Match", headerRow.getCell(1).getStringCellValue());
            assertEquals("Accuracy", headerRow.getCell(2).getStringCellValue());

            Row row1 = sheet.getRow(1);
            assertEquals("123", row1.getCell(0).getStringCellValue());
            assertEquals("456", row1.getCell(1).getStringCellValue());
            assertEquals("High", row1.getCell(2).getStringCellValue());

            Row row2 = sheet.getRow(2);
            assertEquals("789", row2.getCell(0).getStringCellValue());
            assertEquals("012", row2.getCell(1).getStringCellValue());
            assertEquals("Low", row2.getCell(2).getStringCellValue());
        }

        // Clean up test file
        File file = new File(outputPath);
        if (file.exists()) {
            file.delete();
        }
    }
}

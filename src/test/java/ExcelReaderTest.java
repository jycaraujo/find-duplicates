import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.model.Contact;
import org.example.utils.ExcelReader;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExcelReaderTest {

    private static final String TEST_FILE_PATH = "/test-excel-file.xlsx";

    @BeforeAll
    public static void setup() throws IOException {
        // Create a sample Excel file for testing
        try (Workbook workbook = new XSSFWorkbook();
             FileOutputStream fos = new FileOutputStream(new File(ExcelReader.class.getResource(TEST_FILE_PATH).getFile()))) {

            Sheet sheet = workbook.createSheet("Contacts");

            // Create header row
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ContactID");
            headerRow.createCell(1).setCellValue("Name");
            headerRow.createCell(2).setCellValue("Name1");
            headerRow.createCell(3).setCellValue("Email");
            headerRow.createCell(4).setCellValue("PostalZip");
            headerRow.createCell(5).setCellValue("Address");

            // Create data rows
            Row row1 = sheet.createRow(1);
            row1.createCell(0).setCellValue("123");
            row1.createCell(1).setCellValue("John Doe");
            row1.createCell(2).setCellValue("J. Doe");
            row1.createCell(3).setCellValue("john.doe@example.com");
            row1.createCell(4).setCellValue("12345");
            row1.createCell(5).setCellValue("123 Elm Street");

            Row row2 = sheet.createRow(2);
            row2.createCell(0).setCellValue("456");
            row2.createCell(1).setCellValue("Jane Smith");
            row2.createCell(2).setCellValue("J. Smith");
            row2.createCell(3).setCellValue("jane.smith@example.com");
            row2.createCell(4).setCellValue("67890");
            row2.createCell(5).setCellValue("456 Oak Avenue");

            workbook.write(fos);
        }
    }

    @AfterAll
    public static void tearDown() {
        File file = new File(ExcelReader.class.getResource(TEST_FILE_PATH).getFile());
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void testReadExcelFile() {
        List<Contact> contacts = ExcelReader.readExcelFile(TEST_FILE_PATH);

        assertEquals(2, contacts.size(), "Should read 2 contacts");

        Contact contact1 = contacts.get(0);
        assertEquals("123", contact1.getContactId());
        assertEquals("John Doe", contact1.getName());
        assertEquals("J. Doe", contact1.getName1());
        assertEquals("john.doe@example.com", contact1.getEmail());
        assertEquals("12345", contact1.getPostalZip());
        assertEquals("123 Elm Street", contact1.getAddress());

        Contact contact2 = contacts.get(1);
        assertEquals("456", contact2.getContactId());
        assertEquals("Jane Smith", contact2.getName());
        assertEquals("J. Smith", contact2.getName1());
        assertEquals("jane.smith@example.com", contact2.getEmail());
        assertEquals("67890", contact2.getPostalZip());
        assertEquals("456 Oak Avenue", contact2.getAddress());
    }
}

package org.example;

import org.example.model.Contact;
import org.example.utils.DuplicateFinder;
import org.example.utils.ExcelReader;
import org.example.utils.ExcelWriter;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String inputFilePatch = "/sample-excel-file.xlsx";
        String outputPath = "./output.xlsx";
        List<Contact> contacts = ExcelReader.readExcelFile(inputFilePatch);
        List<DuplicateFinder.DuplicateResult> results = DuplicateFinder.findDuplicates(contacts);
        ExcelWriter.writeResultsToExcel(results, outputPath);
    }
}
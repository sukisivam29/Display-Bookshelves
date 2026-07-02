package main.java.org.urbanladder.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelReaderUtil {
    static List<String> expectedItems = new ArrayList<>();
    
    public static List<String> getExpectedMenuItems(String filePath, String sheetName) {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            Workbook workbook = new XSSFWorkbook(fis);

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new RuntimeException("Sheet " + sheetName + "does not exists");
            }
            
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null){
                    Cell cell = row.getCell(0);
                    if (cell != null) {
                        expectedItems.add(cell.getStringCellValue().trim());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to read Excel file at: " + filePath);
        }

        return expectedItems;
    }
}

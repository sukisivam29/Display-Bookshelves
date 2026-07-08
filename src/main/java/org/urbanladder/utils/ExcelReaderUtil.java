package main.java.org.urbanladder.utils;

import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelReaderUtil {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(ExcelReaderUtil.class);

    public static List<String> getExpectedMenuItems(String filePath, String sheetName) {
        List<String> expectedItems = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(filePath)) {
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                throw new RuntimeException("Sheet " + sheetName + " does not exist");
            }

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    Cell cell = row.getCell(0);
                    if (cell != null) {
                        expectedItems.add(cell.toString().trim());
                    }
                }
            }
        } catch (IOException e) {
            Logger logger = LoggerManager.getLogger(ExcelReaderUtil.class);
            logger.error("Excel read fail: {}", e.getMessage());
            throw new RuntimeException(
                    "Failed to read Excel file at: " + filePath
            );
        }

        return expectedItems;
    }

    public static String getCellValue(String filePath, String sheetName, String key) {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                throw new RuntimeException("Sheet " + sheetName + " does not exist");
            }

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) {
                    continue;
                }

                Cell keyCell = row.getCell(0);
                Cell valueCell = row.getCell(1);

                if (keyCell != null && keyCell.toString().trim().equalsIgnoreCase(key)) {
                    if (valueCell == null) {
                        return "";
                    }

                    if (valueCell.getCellType() == CellType.NUMERIC) {
                        return String.valueOf((long) valueCell.getNumericCellValue());
                    }

                    if (valueCell.getCellType() == CellType.BOOLEAN) {
                        return String.valueOf(valueCell.getBooleanCellValue());
                    }

                    return valueCell.toString().trim();
                }
            }

        } catch (IOException e) {
            Logger logger = LoggerManager.getLogger(ExcelReaderUtil.class);
            logger.error("Excel read fail: {}", e.getMessage());
            throw new RuntimeException("Failed to read Excel file at: " + filePath);
        }

        throw new RuntimeException("Key '" + key + "' not found in sheet '" + sheetName + "'");
    }
}
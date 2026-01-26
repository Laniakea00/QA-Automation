package org.qa.alanb.apache.assignment6.utils;


import org.apache.poi.ss.usermodel.*;
import java.io.InputStream;

public  class ExcelUtils {

    public static Object[][] readSheet(String fileName, String sheetName) {
        try (InputStream is = ExcelUtils.class.getClassLoader().getResourceAsStream(fileName)) {
            if (is == null) {
                throw new RuntimeException("Не найден файл в resources: " + fileName);
            }

            try (Workbook wb = WorkbookFactory.create(is)) {
                Sheet sheet = wb.getSheet(sheetName);
                if (sheet == null) {
                    throw new RuntimeException("Не найден лист: " + sheetName);
                }

                int lastRow = sheet.getLastRowNum(); // 0-based, 0 = header
                if (lastRow < 1) {
                    throw new RuntimeException("На листе нет строк данных (есть только header).");
                }

                Row header = sheet.getRow(0);
                int cols = header.getLastCellNum();

                Object[][] data = new Object[lastRow][cols];
                DataFormatter fmt = new DataFormatter();

                for (int i = 1; i <= lastRow; i++) {
                    Row row = sheet.getRow(i);
                    for (int j = 0; j < cols; j++) {
                        Cell cell = (row == null) ? null : row.getCell(j, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                        data[i - 1][j] = (cell == null) ? "" : fmt.formatCellValue(cell);
                    }
                }
                return data;
            }
        } catch (Exception e) {
            throw new RuntimeException("Ошибка чтения Excel: " + fileName + ", sheet=" + sheetName, e);
        }
    }
}

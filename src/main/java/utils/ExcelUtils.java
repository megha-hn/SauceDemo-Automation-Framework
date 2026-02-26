package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {

    /**
     * Reads test data from the specified Excel sheet.
     * - Skips header row
     * - Treats blank/missing cells as empty string ""
     * - Ignores completely empty rows at the end
     */
    public static Object[][] getTestData(String sheetName) {
        List<Object[]> dataRows = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream("src/test/resources/testdata.xlsx");
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            XSSFSheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                System.out.println("ERROR: Sheet '" + sheetName + "' not found in testdata.xlsx");
                return new Object[0][0];
            }

            int lastRowNum = sheet.getLastRowNum();
            if (lastRowNum < 1) {
                System.out.println("WARNING: No data rows found in sheet '" + sheetName + "'");
                return new Object[0][0];
            }

            // Get number of columns from header row
            Row headerRow = sheet.getRow(0);
            int colCount = headerRow != null ? headerRow.getLastCellNum() : 0;
            if (colCount == 0) {
                System.out.println("ERROR: Header row is empty in sheet '" + sheetName + "'");
                return new Object[0][0];
            }

            // Read data rows (start from row 1)
            for (int rowIndex = 1; rowIndex <= lastRowNum; rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                if (row == null) continue;  // skip empty rows

                boolean isRowEmpty = true;
                Object[] rowData = new Object[colCount];

                for (int colIndex = 0; colIndex < colCount; colIndex++) {
                    Cell cell = row.getCell(colIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    String cellValue = getCellValueAsString(cell);

                    rowData[colIndex] = cellValue;

                    if (!cellValue.trim().isEmpty()) {
                        isRowEmpty = false;
                    }
                }

                // Only add row if it has at least some data (prevents trailing empty rows)
                if (!isRowEmpty) {
                    dataRows.add(rowData);
                }
            }

            System.out.println("Excel data loaded from sheet '" + sheetName + "' → " + dataRows.size() + " valid data rows");

            // Convert list to 2D array
            return dataRows.toArray(new Object[0][]);

        } catch (Exception e) {
            System.out.println("Excel reading failed for sheet '" + sheetName + "': " + e.getMessage());
            e.printStackTrace();
            return new Object[0][0];
        }
    }

    /**
     * Safely converts any cell to String (handles all cell types + blank/missing)
     */
    private static String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue().trim();

            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    // Avoid .0 for whole numbers
                    double num = cell.getNumericCellValue();
                    if (num == Math.floor(num)) {
                        return String.valueOf((long) num);
                    }
                    return String.valueOf(num);
                }

            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());

            case FORMULA:
                return cell.getCellFormula().trim();

            case BLANK:
            case ERROR:
            case _NONE:
            default:
                return "";
        }
    }
}
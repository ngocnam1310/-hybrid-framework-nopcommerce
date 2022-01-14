package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import lombok.SneakyThrows;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Platform;

public class ExeclHelper {
	 public static final String       testDataExcelFileName = "testdata.xlsx"; 
	    public static final String       currentDir            = System.getProperty("user.dir");  
	    public static       String       testDataExcelPath     = null; 
	    private static      XSSFWorkbook excelWBook; 
	    private static      XSSFSheet    excelWSheet; 
	    private static      XSSFCell     cell; 
	    private static      XSSFRow      row; 
	    public static       int          rowNumber; 
	    public static       int          columnNumber;
	    // This method has two parameters: "Test data excel file name" and "Excel sheet name"
	    // It creates FileInputStream and set excel file and excel sheet to excelWBook and excelWSheet variables.
	    @SneakyThrows
	    public static void setExcelFileSheet(String sheetName) throws IOException {

	        if (Platform.getCurrent().toString().equalsIgnoreCase("MAC")) {
	            testDataExcelPath = currentDir + "resources/";
	        } else if (Platform.getCurrent().toString().contains("WIN")) {
	            testDataExcelPath = currentDir + "\\resources\\";
	        }
	       
	        FileInputStream ExcelFile = new FileInputStream(testDataExcelPath + testDataExcelFileName);
	        excelWBook = new XSSFWorkbook(ExcelFile);
	        excelWSheet = excelWBook.getSheet(sheetName);
	    }

	    public static String getCellData(int RowNum, int ColNum) {
	        cell = excelWSheet.getRow(RowNum).getCell(ColNum);
	        DataFormatter formatter = new DataFormatter();
	        return formatter.formatCellValue(cell);
	    }

	    public static XSSFRow getRowData(int RowNum) {
	        row = excelWSheet.getRow(RowNum);
	        return row;
	    }

	    @SneakyThrows
	    public static void setCellData(String value, int RowNum, int ColNum) throws IOException {
	        row = excelWSheet.getRow(RowNum);
	        cell = row.getCell(ColNum);
	        if (cell == null) {
	            cell = row.createCell(ColNum);
	            cell.setCellValue(value);
	        } else {
	            cell.setCellValue(value);
	        }

	        FileOutputStream fileOut = new FileOutputStream(testDataExcelPath + testDataExcelFileName);
	        excelWBook.write(fileOut);
	        fileOut.flush();
	        fileOut.close();
	    }

}

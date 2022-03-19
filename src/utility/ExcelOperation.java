package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import constant.ConstantPath;

public class ExcelOperation {

	public static Object[][] getAllRows(String wbName, String sheetName) throws IOException{
		File file = new File(ConstantPath.TESTDATA +"/"+ wbName);
		FileInputStream inputStream = new FileInputStream(file);
		Workbook mywb = new XSSFWorkbook(inputStream);//2003+ (xlsx, xlsm) // (HSSF)xls
		
		Sheet sheet = mywb.getSheet(sheetName);
		int totalRows = sheet.getLastRowNum();
		int totalCols = sheet.getRow(0).getLastCellNum();
		
		Object[][] data = new Object[totalRows][totalCols];
		
		for(int rowIndex=1;rowIndex<=totalRows;rowIndex++) {
			Row row = sheet.getRow(rowIndex);
			for(int colIndex=0;colIndex<totalCols;colIndex++) {
				CellType cellType = row.getCell(colIndex).getCellType();
				Cell cell = row.getCell(colIndex);
				if(cellType == CellType.STRING)
					data[rowIndex-1][colIndex]  = cell.getStringCellValue();
				else if(cellType == CellType.BOOLEAN)
					data[rowIndex-1][colIndex]  = cell.getBooleanCellValue();
				else if(cellType == CellType.NUMERIC)
					data[rowIndex-1][colIndex]  = cell.getNumericCellValue();	
			}
		}
		return data;
	}
	
	public static void main(String[] args) throws IOException {
		Object[][] data = ExcelOperation.getAllRows("skills1.xlsx", "data");
		System.out.println(data);
	}
}

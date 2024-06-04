package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtil {
Workbook wb;	
public ExcelFileUtil(String sheetname)throws Throwable
{
  FileInputStream fi = new FileInputStream(sheetname);
  wb = WorkbookFactory.create(fi);
}
public int rowcount(String sheetname)
{
	return wb.getSheet(sheetname).getLastRowNum();
}
public String getCellData(String sheetname, int row, int column)
{
	String data = "";
	if(wb.getSheet(sheetname).getRow(row).getCell(column).getCellType()==CellType.NUMERIC)
	{
		int celldata = (int) wb.getSheet(sheetname).getRow(row).getCell(column).getNumericCellValue();
		data = String.valueOf(celldata);
	}
	else
	{
		data = wb.getSheet(sheetname).getRow(row).getCell(column).getStringCellValue();
	}
	return data;
}
public void setCellData(String sheetname, int row, int column,String Status, String writeExcel) throws Throwable
{
    Sheet ws = wb.getSheet(sheetname); 
    Row rownum = ws.getRow(row);
    Cell cell = rownum.getCell(column);
    cell.setCellValue(Status);
    if(Status.equalsIgnoreCase("Pass"))
    {
    	CellStyle style = wb.createCellStyle();
    	Font font = wb.createFont();
    	font.setBold(true);
    	font.setColor(IndexedColors.GREEN.getIndex());
    	style.setFont(font);
    	ws.getRow(row).getCell(column).setCellStyle(style);
    }
    else if (Status.equalsIgnoreCase("Fail"))
    {
    	CellStyle style = wb.createCellStyle();
    	Font font = wb.createFont();
    	font.setBold(true);
    	font.setColor(IndexedColors.RED.getIndex());
    	style.setFont(font);
    	ws.getRow(row).getCell(column).setCellStyle(style);
    }
    else if (Status.equalsIgnoreCase("Blocked"))
    {
    	CellStyle style = wb.createCellStyle();
    	Font font = wb.createFont();
    	font.setBold(true);
    	font.setColor(IndexedColors.BLUE.getIndex());
    	style.setFont(font);
    	ws.getRow(row).getCell(column).setCellStyle(style);
    }
    FileOutputStream fo = new FileOutputStream(writeExcel);
    wb.write(fo);
}
}

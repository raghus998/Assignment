package com.assignment.generic;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelData 
{

	public static String getData(String file_path,String sheetName,int rn,int cn)
	{
		try 
		{
			FileInputStream fis = new FileInputStream(file_path);
			Workbook wb = WorkbookFactory.create(fis);
			Row r = wb.getSheet(sheetName).getRow(rn);
			String data = r.getCell(cn).getStringCellValue();
			return data;
		} 
		catch (Exception e) 
		{
			return " "; 
		}
	}
	
}

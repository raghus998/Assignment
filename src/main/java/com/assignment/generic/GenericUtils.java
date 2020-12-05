package com.assignment.generic;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


public class GenericUtils 
{
	public static String getscreenshots(WebDriver driver,String name)
	{
		try
		{
			TakesScreenshot t = (TakesScreenshot) driver;
			File src = t.getScreenshotAs(OutputType.FILE);
			File dest = new File("./screenshots/"+name+".png");
			FileUtils.copyFile(src, dest);
			String path = dest.getAbsolutePath();
			return path;
		}
		catch (Exception e) 
		{
			return e.getMessage() ;
		}
		
	}
	
	public static void selectByIndex(WebElement element,int index)
	{
		Select sel = new Select(element);
		sel.selectByIndex(index);
	}
	public static void selectByValue(WebElement element,String value)
	{
		Select sel = new Select(element);
		sel.selectByValue(value);
	}
	public static void selectByVisibletext(WebElement element,String text)
	{
		Select sel = new Select(element);
		sel.selectByVisibleText(text);
	}	
	
}

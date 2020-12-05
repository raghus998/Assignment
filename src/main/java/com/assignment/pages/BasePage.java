package com.assignment.pages;

import java.awt.RenderingHints.Key;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

public class BasePage 
{

	public  WebDriver driver;
	private WebDriverWait wait;

	protected BasePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 20);
	}

	
	public boolean waitUntill(WebElement element) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element));
			return true;
		} catch (Exception e) {
			Reporter.log(e.getMessage(), true);
			return false;
		}
	}

	public void clickOn(WebElement element) {
		try {
			if (waitUntill(element)) {
				wait.until(ExpectedConditions.elementToBeClickable(element));
				element.click();
				// success message
			}
		} catch (Exception e) {
			Reporter.log(e.getMessage());
			Assert.fail(e.getMessage());
		}
	}
	
	public int getRequiredDateFromTodayDate(int reqDay)
	{
		// To get Today's date
		Date date = new Date();

		// To get today date in required format
		SimpleDateFormat s1 = new SimpleDateFormat("d");
		String day = s1.format(date);
		int dayInInt = Integer.parseInt(day);
		int reqDateFromToday = dayInInt+reqDay;
		return reqDateFromToday;
	}

	public String getmonthInRequiredFormat() 
	{
		Date date = new Date();
		// To get current month in required format
		SimpleDateFormat s2 = new SimpleDateFormat("MMMM");
		String month = s2.format(date);
		return month;
	}
	
	public void UnCheckTheCheckBox(WebElement element) throws InterruptedException
	{
		clickOn(element);
		Thread.sleep(1000);
	}
	

}

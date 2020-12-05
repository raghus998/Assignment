package com.assignment.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.assignment.generic.GenericUtils;


public class ClearTripHomePage extends BasePage
{
	//Declaration of WebElement 
	
	@FindBy(xpath = "(//a[@href='/flights'])[2]")
	private WebElement flightMenu;
	
	@FindBy(xpath = "//strong[.='Round trip']/../input[@type='radio']")
	private WebElement roundTripRadioBtn;
	
	@FindBy(xpath = "//strong[.='From']/../../../dd/span/input[1]")
	private WebElement fromInputField;
	
	@FindBy(xpath = "//a[.='Bangalore, IN - Kempegowda International Airport (BLR)']")
	private WebElement bangalore;
	
	@FindBy(xpath = "//strong[.='To']/../../../dd/span/input[1]")
	private WebElement toInputField;
	
	@FindBy(xpath = "//a[.='New Delhi, IN - Indira Gandhi Airport (DEL)']")
	private WebElement delhi;
	
	@FindBy(xpath = "//select[@id='Adults']")
	private WebElement dropdownAdults;
	
	@FindBy(xpath = "//select[@id='Childrens']")
	private WebElement dropdownChildrens;
	
	@FindBy(id = "SearchBtn")
	private WebElement searchBtn;
	
	
	//initilization of WebElement 
	public ClearTripHomePage(WebDriver driver)
	{
		super(driver);
		PageFactory.initElements(driver, this);
	}

	
	//utlization methods or Actions methods
	
	public void clickonFlightMenu()
	{
		clickOn(flightMenu);
	}
	public void clickOnRoundTripRadioBtn() 
	{
		clickOn(roundTripRadioBtn);
	}
	
	public void enterFromCity(String fromcity)
	{
		fromInputField.sendKeys(fromcity);
		bangalore.click();
	}
	
	public void enterToCity(String tocity)
	{
		toInputField.sendKeys(tocity);
		delhi.click();
	}
	

	
	public void selectDepartDate()
	{
		int departDate = getRequiredDateFromTodayDate(2);
		String monthForDepartDate= getmonthInRequiredFormat();
		String reqDepartDatepath = "//span[.='"+monthForDepartDate+"']/../../..//a[.='"+departDate+"']";
		WebElement reqDepartDate = driver.findElement(By.xpath(reqDepartDatepath));
		clickOn(reqDepartDate);
	}
	

	public void selectReturnDate()
	{
		int returnDate = getRequiredDateFromTodayDate(7);
		String month = getmonthInRequiredFormat();
		String reqretuenDatepath = "//span[.='"+month+"']/../../..//a[.='"+returnDate+"']";
		WebElement reqreturnDate = driver.findElement(By.xpath(reqretuenDatepath));
		clickOn(reqreturnDate);
	}
	
	public void selectAdultsValue()
	{
		GenericUtils.selectByIndex(dropdownAdults, 0);
	}
	
	public void selectChildrenValue()
	{
		GenericUtils.selectByIndex(dropdownChildrens, 1);
	}
	
	public void clickOnSearchBtn()
	{
		searchBtn.sendKeys(Keys.ENTER);
	}	
}

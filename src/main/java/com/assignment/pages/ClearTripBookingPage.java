package com.assignment.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.Factory;

import com.assignment.generic.GenericUtils;

public class ClearTripBookingPage extends BasePage
{
	
	@FindBy(xpath = "//p[contains(text(),'Book in four simple steps')]")
	private WebElement textBookInFourSimpleSteps;
	
	@FindBy(xpath = "(//small[@class='flightNumber'])[1]")
	private WebElement departFlightNo;
	
	@FindBy(xpath = "(//small[@class='flightNumber'])[2]")
	private WebElement returnFlightNo;
	
	@FindBy(xpath = "//input[@id='itineraryBtn']")
	private WebElement continueBookingBtn;
	
	@FindBy(xpath = "//label[contains(text(),'Your email address')]/../../dd[1]/div/input[2]")
	private WebElement emailAddInputBox;
	
	@FindBy(xpath = "//label[contains(text(),'Your email address')]/../../..//dl[3]/dd/input")
	private WebElement continueBtnInEmailField;
	
	@FindBy(xpath = "(//label[@id='AdultOne'])[1]")
	private WebElement adultOneText;

	@FindBy(xpath = "//select[@name='AdultTitle1']")
	private WebElement adultListBox;
	
	@FindBy(xpath = "(//input[@id='AdultFname1'])[1]")
	private WebElement adultFirstNameBox;
	
	@FindBy(xpath = "(//input[@id='AdultLname1'])[1]")
	private WebElement adultLastNameBox;
	
	@FindBy(xpath = "//select[@id='ChildTitle1']")
	private WebElement childListBox;
	
	@FindBy(xpath = "//input[@id='ChildFname1']")
	private WebElement childFirstNameBox;
	
	@FindBy(xpath = "//input[@id='ChildLname1']")
	private WebElement childLastNameBox;
	
	@FindBy(xpath = "//select[@id='ChildDobDay1']")
	private WebElement childBrithDay;
	
	@FindBy(id = "ChildDobMonth1")
	private WebElement childBrithMonth;
	
	@FindBy(id = "ChildDobYear1")
	private WebElement childBrithYear;
	
	@FindBy(id = "mobileNumber")
	private WebElement mobileNo;
	
	@FindBy(xpath = "//input[@id='travellerBtn']")
	private WebElement travellarSectionContinueBtn;
	
	@FindBy(id = "creditCardNumberDisp")
	private WebElement creditCardNo;
	
	@FindBy(xpath = "(//select[@id='CcExpirationMonth'])[2]")
	private WebElement creditcardExpMonth;
	
	@FindBy(xpath = "(//select[@id='CcExpirationYear'])[2]")
	private WebElement creditcardExpYear;
	
	@FindBy(xpath = "(//input[@id='BillName'])[2]")
	private WebElement cardHolderName;
	
	@FindBy(id = "cvvCode")
	private WebElement cvv;
	public ClearTripBookingPage(WebDriver driver) 
	{	
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public void waitTillBookInFourSimpleStepsTextVisibilty()
	{
		WebDriverWait wait = new WebDriverWait(driver, 25);
		try 
		{
			wait.until(ExpectedConditions.visibilityOf(textBookInFourSimpleSteps));
		}
		catch (Exception e) 
		{
			Reporter.log(e.getMessage(),true);
		}
	}
	

	public String getDepartFlightNoInBookingPage()
	{
		return departFlightNo.getText();
	}
	
	public String getReturnFlightNoBookingPage()
	{
		return returnFlightNo.getText();
	}
	
	public void clickOnContiuneBookingBtn()
	{
		clickOn(continueBookingBtn);
	}
	
	public void enterEmailID(String email)
	{
		emailAddInputBox.sendKeys(email);
	}
	
	public void clickOncontinueBtnInEmailField()
	{
		clickOn(continueBtnInEmailField);
	}
	
	public void waitTillAdultoneTextVisibilty()
	{
		WebDriverWait wait = new WebDriverWait(driver, 25);
		try 
		{
			wait.until(ExpectedConditions.visibilityOf(adultOneText));
		}
		catch (Exception e) 
		{
			Reporter.log(e.getMessage(),true);
		}
	}
	
	public void enterTravellerTitle()
	{
		GenericUtils.selectByIndex(adultListBox, 1);
		GenericUtils.selectByIndex(childListBox, 1);
	}
	public void enterTravellerDetails()
	{
		adultFirstNameBox.sendKeys("ABC");
		adultLastNameBox.sendKeys("XYZ");
		childFirstNameBox.sendKeys("abc");
		childLastNameBox.sendKeys("xyz");
	}
	
	public void enterChildDOB() throws InterruptedException
	{
		GenericUtils.selectByIndex(childBrithDay, 2);
		Thread.sleep(1000);
		GenericUtils.selectByIndex(childBrithMonth, 4);
		Thread.sleep(1000);
		GenericUtils.selectByIndex(childBrithYear, 5);
		
	}
	
	public void enterMobileNo(String mn)
	{
		mobileNo.sendKeys(mn);
	}
	
	public void clickonTravellerSectionContinueBtn()
	{
		clickOn(travellarSectionContinueBtn);
	}
	
	public void enterCardDetails() throws InterruptedException
	{
		creditCardNo.sendKeys("12341234123412");
		Thread.sleep(1000);
		cardHolderName.sendKeys("ABCDEFGH");
		Thread.sleep(1000);
		cvv.sendKeys("123");	
	}
	
	public void selectCreditCardExpDate()
	{
		GenericUtils.selectByIndex(creditcardExpMonth, 3);
		GenericUtils.selectByIndex(creditcardExpYear, 4);
	}
	
}

package com.assignment.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ClearTripSearchPage extends BasePage
{
	//Declaration of WebElement 
	
	@FindBy(xpath = "//p[contains(text(),'Air Asia')]/../../../div[1]/span")
	private WebElement airasiaCheckBox;
	
	@FindBy(xpath = "//p[.='GoAir']/../../../div[1]/span")
	private WebElement goAirCheckBox;
	
	@FindBy(xpath = "//p[.='SpiceJet']/../../../div[1]/span")
	private WebElement SpiceJetCheckBox;

	@FindBy(xpath = "//p[.='Vistara']/../../../div[1]/span")
	private WebElement VistaraCheckBox;
	
	@FindBy(xpath = "(//div[@data-test-attrib='onward-view']/div/div)[1]")
	private WebElement lowestPriceDepartFlightFromBLR;
	
	@FindBy(xpath = "(//div[@data-test-attrib='return-view']/div/div)[1]")
	private WebElement lowestPriceDepartFlightFromDEL;
	
	@FindBy(xpath = "//span[.='Details']")
	private WebElement detailsLink;
	
	@FindBy(xpath = "//h2[.='Details of your round trip']/../../div[2]/div/div[2]/div/div/div[1]/div/div[2]/p[1]")
	private WebElement departFlightNo;
	
	@FindBy(xpath = "//h2[.='Details of your round trip']/../../div[3]/div/div[2]/div/div/div[1]/div/div[2]/p[1]")
	private WebElement returnFlightNo;
	
	@FindBy(xpath ="(//button[.='Book'])[2]")
	private WebElement bookBtn;
	
	
	
	//initilization of WebElement 
	public ClearTripSearchPage(WebDriver driver)
	{
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public void selectreqflights() throws InterruptedException
	{
		UnCheckTheCheckBox(airasiaCheckBox);
		UnCheckTheCheckBox(goAirCheckBox);
		UnCheckTheCheckBox(SpiceJetCheckBox);
		Thread.sleep(2000);
		UnCheckTheCheckBox(VistaraCheckBox);
	}
	
	
	public void selectLowestPriceDepartFromBEL()
	{
		clickOn(lowestPriceDepartFlightFromBLR);
	}
	
	public void selectLowestPriceReturnFromDEL()
	{
		clickOn(lowestPriceDepartFlightFromDEL);
	}
	
	public void clickOnDetailsLink()
	{
		clickOn(detailsLink);
	}
	
	
	public String getDepartFlightNo()
	{
		return departFlightNo.getText();
	}
	
	public String getReturnFlightNo()
	{
		return returnFlightNo.getText();
	}
	public void clickOnBookBtn()
	{
		clickOn(bookBtn);
	}

}

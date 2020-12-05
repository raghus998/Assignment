package com.seleniumassignment.tests;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.assignment.generic.BaseTest;
import com.assignment.generic.ExcelData;
import com.assignment.generic.GenericUtils;
import com.assignment.pages.ClearTripBookingPage;
import com.assignment.pages.ClearTripHomePage;
import com.assignment.pages.ClearTripSearchPage;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;

public class ClearTrip extends BaseTest
{
	@Test(priority = 1)
	public void testcleartrip() throws InterruptedException, IOException
	{
		//To Read the TestData from Excel
		String clearTripUrl = ExcelData.getData(file_path, "ClearTrip", 1, 0);
		String HomePageTitle = ExcelData.getData(file_path, "ClearTrip",1,1);
		String fromCity = ExcelData.getData(file_path, "ClearTrip",1,2);
		String toCity = ExcelData.getData(file_path, "ClearTrip",1,3);
		String ResultPageTitle = ExcelData.getData(file_path, "ClearTrip",1,4);
		String email = ExcelData.getData(file_path, "ClearTrip",1,5);
		String mn = ExcelData.getData(file_path, "ClearTrip",1,6);
		
		
		//Navigate to ClearTrip
		logger = extent.createTest("ClearTrip Test is started");
		driver.get(clearTripUrl);
		logger.info("Navigated to clearTrip");
		String aHomePageTitle = driver.getTitle();
		Assert.assertEquals(aHomePageTitle, HomePageTitle);
		Thread.sleep(3000);
		try
		{
			Alert a = driver.switchTo().alert();
			a.dismiss();
			
		} 
		catch (Exception e) 
		{
			Reporter.log(e.getMessage(),true);
		}
		
		
		//POM class objects
		ClearTripHomePage cleartriphomepage = new ClearTripHomePage(driver);
		ClearTripSearchPage cleartripsearchpage = new ClearTripSearchPage(driver);
		ClearTripBookingPage cleartripbookingpage = new ClearTripBookingPage(driver);
		
		//Click on Flight Menu
		cleartriphomepage.clickonFlightMenu();
		logger.info("Clicked on FlightMenu");
		//Click on RoundTrip Radio Btn
		cleartriphomepage.clickOnRoundTripRadioBtn();
		logger.info("Clicked on RoundTrip radio button");
		
		//Enter from city
		cleartriphomepage.enterFromCity(fromCity);
		
		//Enter to city
		cleartriphomepage.enterToCity(toCity);
		
		//Click on DepartDate
		cleartriphomepage.selectDepartDate();
		Thread.sleep(2000);
		//Click on Return date
		cleartriphomepage.selectReturnDate();
		logger.info("Selected from,To City and selected Depart Date as 2 days from current date  and Return Date as 5 days from Depart date.");
		
		//Select the No of Adults,
		cleartriphomepage.selectAdultsValue();
		
		//Select the No of Childrens
		cleartriphomepage.selectChildrenValue();
		logger.info("Selected Number of  Travellers");
		
		//Click on Search Btn
		cleartriphomepage.clickOnSearchBtn();
		logger.info("Clicked on Search Btn");
		
		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		try 
		{
			wait.until(ExpectedConditions.titleContains(ResultPageTitle));
		}
		catch (Exception e) 
		{
			Reporter.log(e.getMessage(),true);
		}
	
		Thread.sleep(2000);
		
		//Select only Indigo and Air India flights
		cleartripsearchpage.selectreqflights();
	
		//Select Lowest depart flight from BEL and Lowest return Flight from DEl
		cleartripsearchpage.selectLowestPriceDepartFromBEL();
		cleartripsearchpage.selectLowestPriceReturnFromDEL();
		
		logger.info("Selected Lowest price flight from Indigo and Air India Flights");
		String path2 = GenericUtils.getscreenshots(driver,"Lowest Price Flight");
		logger.info("Selected Lowest Price Flight", MediaEntityBuilder.createScreenCaptureFromPath(path2).build());
		cleartripsearchpage.clickOnDetailsLink();
		
		//Get the Depart and Return flight No
		String eDepartFlightNo = cleartripsearchpage.getDepartFlightNo();
		String eReturnFlightNo = cleartripsearchpage.getReturnFlightNo();
		
		String path = GenericUtils.getscreenshots(driver,"Flight DetailsPopUP");
		logger.info("Flight Details", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		
		cleartripsearchpage.clickOnBookBtn();
		logger.info("Clicked on Book button");
		
		
		
		Thread.sleep(3000);
		//Get the current window handle
		String windowHandle = driver.getWindowHandle();
		//Get the list of window handles
		ArrayList tabs = new ArrayList (driver.getWindowHandles());
		System.out.println(tabs.size());
		//Use the list of window handles to switch between windows
		driver.switchTo().window((String) tabs.get(1));
		
		
		cleartripbookingpage.waitTillBookInFourSimpleStepsTextVisibilty();
		
		//Verify Same flight are selected and Flight itinerary
		String aDepartFlightNo = cleartripbookingpage.getDepartFlightNoInBookingPage();
		String aReturnFlightNo = cleartripbookingpage.getReturnFlightNoBookingPage();
				
		Assert.assertEquals(aDepartFlightNo, eDepartFlightNo);
		Assert.assertEquals(aReturnFlightNo, eReturnFlightNo);
				
		logger.info("Same flights are selected in Booking page and  verified Flight itinerary");
		String path1 = GenericUtils.getscreenshots(driver,"Payment Page");
		logger.info("Payment Page", MediaEntityBuilder.createScreenCaptureFromPath(path1).build()); 
		
		cleartripbookingpage.clickOnContiuneBookingBtn();
		cleartripbookingpage.enterEmailID(email);
		cleartripbookingpage.clickOncontinueBtnInEmailField();
		cleartripbookingpage.waitTillAdultoneTextVisibilty();
		
		//Enter Traveller deatils
		cleartripbookingpage.enterTravellerTitle();
		cleartripbookingpage.enterTravellerDetails();
		cleartripbookingpage.enterChildDOB();
		cleartripbookingpage.enterMobileNo(mn);
		
		String path4 = GenericUtils.getscreenshots(driver,"Traveller Deatils");
		logger.info("Added details in  Traveller Deatils section ", MediaEntityBuilder.createScreenCaptureFromPath(path4).build()); 
		
		cleartripbookingpage.clickonTravellerSectionContinueBtn();
		
		//Enter Credit card details
		cleartripbookingpage.enterCardDetails();
		cleartripbookingpage.selectCreditCardExpDate();

		String path5 = GenericUtils.getscreenshots(driver,"Credit Card Deatils");
		logger.info("Added CreditCard details in Payment section", MediaEntityBuilder.createScreenCaptureFromPath(path5).build()); 
		
	}
	
		
}

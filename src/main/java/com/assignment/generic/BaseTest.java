package com.assignment.generic;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

public abstract class BaseTest
{
	public static String file_path = "./data/TestData.xls";
	public WebDriver driver;
	public static ExtentHtmlReporter htmlreport ;
	public static ExtentReports extent ;
	public static ExtentTest logger;
	
	static
	{
		WebDriverManager.chromedriver().setup();
	}
	
	@BeforeMethod
	public void precondition()
	{
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(25,TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
	}
	
	@AfterMethod
	public void postcondition(ITestResult res)
	{
		int status = res.getStatus();
		if(status == 2)
		{
			String name = res.getName();
			GenericUtils.getscreenshots(driver, name);
		}
		driver.close();
	}
	
	@BeforeTest
	public void startTest()
	{
		//crate object of  ExtentHtmlReporter
		htmlreport = new ExtentHtmlReporter("./Reports/SeleniumReports.html");
		htmlreport.config().setTheme(Theme.DARK);
		htmlreport.config().setReportName("Test Report");
		htmlreport.config().setDocumentTitle("AutoMation Test Report");
		
		//crate object of  ExtentReports
		extent = new ExtentReports();
		extent.attachReporter(htmlreport);
		extent.setSystemInfo("OS","Win10");
		extent.setSystemInfo("BrowserName", "Chrome");
	}
	

	@AfterTest
	public void stopTest() {
		extent.flush();
	}
}

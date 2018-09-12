package com.dbx.testhelper;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.tests.utility.Constant;

public class TestHelper {
	protected static WebDriver driver;
	protected static ExtentReports report = new ExtentReports("./automationreport.html", true);
	protected static ExtentTest test;

	@BeforeMethod
	public void setup() {
		System.setProperty("webdriver.chrome.driver", Constant.CHROME_DRIVER_PATH);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(Constant.DBX_URL);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	/*
	 * @AfterMethod public void cleanUp(){ driver.manage().deleteAllCookies(); }
	 */

	@AfterMethod
	public static void tearDown() {
		driver.quit();
		report.endTest(test);
		report.flush();
	}
}

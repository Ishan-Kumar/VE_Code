package com.Assesment1.TestScenarios;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.base.Initialisers.Constants;
import com.base.PageObjects.Arma;
import com.base.Utility.GetBrowserInstance;
import com.base.Utility.Keywords;
import com.base.Utility.ScreenShotCapture;

public class Scenario5_VerifyProductDetails  extends GetBrowserInstance
{
	//Initialize Objects
	WebDriver driver;
	Keywords act = new Keywords();
	Arma arma =new Arma();

	////Initialize Variables
	String testStep;
	String testCase_Name = "Scenario5_VerifyProductDetails";

	//Initialize Driver
	@BeforeClass
	public void getBrowser(){
		this.driver = getDriver();
	}

	/*@Scenario: verify all The product 
	 * details when open a product.
	 * */
	@Test(priority = 1)
	public void Scenario5() 
	{
		System.out.println(testCase_Name);
		System.out.println("//-----Scenario Starts --- //");
		
		try 
		{
			
			testStep = "User Navigates to Arma WebPage";
			act.gotToUrl(driver, Constants.urlArma);
			arma.waitForPageLoader(driver);
			System.out.println(testStep + " - Passed");
			
			testStep = "User clicks on first product of the page and verifies the popup";
			arma.clickfirstProductOnPage(driver);
			System.out.println(testStep + " - Passed");
			
			testStep = "User verifies the product details: Image, Price and Action Button";
			arma.verifyPresenceOfImage(driver);
			arma.verifyPresenceOfPrice(driver);
			arma.verifyPresenceOfAction(driver);
			System.out.println(testStep + " - Passed");
			
		}catch (Exception e) {
			ScreenShotCapture.captureScreen(driver, testCase_Name);
			System.out.println("//-----Scenario Ends --- //");
			Assert.fail("Test Scenario failed at Step -->' " + testStep + " '. Error is: " + e);
		}
		System.out.println("//-----Scenario Ends --- //");
	}

	//Flush Browser
	@AfterClass
	public void tearDown()
	{
		if(driver!=null)
		{	
			driver.close();
			driver.quit();
			driver = null;
		}
	}
	
	
}

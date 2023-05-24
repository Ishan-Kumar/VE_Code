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

public class Scenario2_VerifyTabFunction extends GetBrowserInstance
{

	//Objects
	WebDriver driver;
	Keywords act = new Keywords();
	Arma arma =new Arma();
	
	//variables
	String testStep;
	String testCase_Name = "Scenario2_VerifyTabFunction";
	
	//Initialize Driver
	@BeforeClass
	public void getBrowser(){
		this.driver = getDriver();
	}

	/*@Scenario: Only one gender tab should
	 * remain Open
	 * */
	@Test(priority = 1)
	public void Scenario2() 
	{
		System.out.println(testCase_Name);
		System.out.println("//-----Scenario Starts --- //");

		try 
		{
			testStep = "User Navigates to ArmaWebPage";
			act.gotToUrl(driver, Constants.urlArma);
			arma.waitForPageLoader(driver);
			System.out.println(testStep + " - Passed");

			testStep = "User Clicks to open Men Option Tab";
			arma.clickfilterMen(driver);
			System.out.println(testStep + " - Passed");
			
			testStep = "User Clicks to open Women Option Tab";
			arma.clickfilterWoMen(driver);
			System.out.println(testStep + " - Passed");

			testStep = "User Verifies that women tab is opened";
			arma.isWomenOpened(driver);
			System.out.println(testStep + " - Passed");

			testStep = "User Verifies that men tab is closed";
			arma.isMenClosed(driver);
			System.out.println(testStep + " - Passed");

		}catch (Exception e) {
			ScreenShotCapture.captureScreen(driver, testCase_Name);
			System.out.println("//-----Scenario Ends --- //");
			Assert.fail("Test Scenario failed at Step --> " + testStep + "Error is: " + e);
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


}// End of class

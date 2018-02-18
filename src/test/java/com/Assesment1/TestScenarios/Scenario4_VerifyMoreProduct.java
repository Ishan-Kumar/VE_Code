package com.Assesment1.TestScenarios;

import org.openqa.selenium.WebDriver;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.base.Initialisers.Constants;
import com.base.PageObjects.Arma;
import com.base.PageObjects.Nono;
import com.base.Utility.GetBrowserInstance;
import com.base.Utility.Keywords;
import com.base.Utility.ScreenShotCapture;

public class Scenario4_VerifyMoreProduct extends GetBrowserInstance
{

	WebDriver driver;
	Keywords act = new Keywords();
	Arma arma =new Arma();
	Nono nono =new Nono();
	int productsBeforScroll;
	int productsAfterScroll;

	String testStep;
	String testCase_Name = "Scenario4_VerifyMoreProduct";

	//Initialize Driver
	@BeforeClass
	public void getBrowser(){
		this.driver = getDriver();
	}

	/*@Scenario: when scrolling down I should 
	 * see more products loading
	 * */
	@Test(priority = 1)
	public void Scenario4() 
	{
		System.out.println(testCase_Name);
		System.out.println("//-----Scenario Starts --- //");
		try 
		{
			testStep = "User Navigates to Nono Product Locator";
			act.gotToUrl(driver, Constants.urlNono);
			arma.waitForPageLoader(driver);
			System.out.println(testStep + " - Passed");

			//Check number of Products before scroll
			productsBeforScroll = nono.getAllProducts(driver).size();
			System.out.println("Products before Scroll--> " +productsBeforScroll);

			testStep = "User Scrolls down the page";
			nono.scrollforMoreProducts(driver);
			System.out.println(testStep + " - Passed");

			//Verify number of Products After scroll
			productsAfterScroll = nono.getAllProducts(driver).size();
			System.out.println("Products after Scroll--> " + productsAfterScroll);

			testStep = "User verifies more Products on Page after scroll";
			if((productsBeforScroll==productsAfterScroll)) {
				throw new Exception ("More Products didn't loaded after scroll");
			}
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




}// end of class 

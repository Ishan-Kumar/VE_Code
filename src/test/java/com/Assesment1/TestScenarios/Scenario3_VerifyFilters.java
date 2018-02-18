package com.Assesment1.TestScenarios;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.base.Initialisers.Constants;
import com.base.PageObjects.Arma;
import com.base.Utility.GetBrowserInstance;
import com.base.Utility.Keywords;
import com.base.Utility.ScreenShotCapture;

public class Scenario3_VerifyFilters extends GetBrowserInstance
{
	//Initialize Objects
	WebDriver driver;
	Keywords act = new Keywords();
	Arma arma =new Arma();
	Map<String, String> SearchString;
	List<WebElement> products;
	List<String> productNotContainBikerJack = new ArrayList<String>();
	List<String> productsContainSkirt = new ArrayList<String>();

	//Initialize Variables
	String testStep;
	String testCase_Name = "Scenario3_VerifyFilters";

	//Initialize Driver
	@BeforeClass
	public void getBrowser(){
		this.driver = getDriver();
	}

	/*@Scenario: Click and verify Filters
	 * */
	@Test(priority = 1)
	public void Scenario3() 
	{
		System.out.println(testCase_Name);
		System.out.println("//-----Scenario Starts --- //");
		
		try 
		{
			testStep = "User Navigates to Arma WebPage";
			act.gotToUrl(driver, Constants.urlArma);
			arma.waitForPageLoader(driver);
			System.out.println(testStep + " - Passed");

			testStep = "User open Women filter and select 'Jackets' filter";
			arma.clickfilterWoMen(driver);
			arma.selectFilterCategoryWomen(driver, "jackets");
			System.out.println(testStep + " - Passed");

			//fetch query Parameters String in hashMap
			SearchString = arma.getQueryParameters(driver);

			//Verify for gender value in HashMap
			testStep = "User verifies query string should contain 'genders[0]' = 'women' ";
			if(!SearchString.get("genders[0]").equals("women")) {
				throw new Exception("Search string for 'gender' is incorrect");
			}
			System.out.println(testStep + " - Passed");

			//Verify for age group value in HashMap
			testStep = "User verifies query string should contain 'age_groups[0]' = 'adults' ";
			if(!SearchString.get("age_groups[0]").equals("adults")) {
				throw new Exception("Search string for 'age_groups' is incorrect");
			}
			System.out.println(testStep + " - Passed");

			//Verify for type value in HashMap
			testStep = "User verifies query string should contain 'types[0]' = 'women-leather-jackets-arma' ";
			if(!SearchString.get("types[0]").equals("women-leather-jackets-arma")) {
				throw new Exception("Search string for 'types[0]' is incorrect");
			}
			System.out.println(testStep + " - Passed");


			//Fetch All Products
			products = arma.getAllProduct(driver);

			//Store Product names without keyword bikerJack and containing Skirt
			for(WebElement product: products) 
			{
				String productName = product.getText();
				//System.out.println("---" + productName);
				
				if(!productName.contains("bikerjack")) {
					productNotContainBikerJack.add(productName);
				}
				if(productName.contains("skirt")) {
					productsContainSkirt.add(productName);
				}			
			}

			testStep = "User verifies all the Poducts for keyword 'bikerJack' ";
			if(!productNotContainBikerJack.isEmpty()) {
				throw new Exception("Following Products are present on the page without keyword 'bikerJack' : " + productNotContainBikerJack);
			}
			System.out.println(testStep + " - Passed");

			testStep = "User verifies that Poduct Name should not contain 'Skirt' ";
			if(!productsContainSkirt.isEmpty()) {
				throw new Exception("Following Products are present on the page with keyword Skirts : " + productsContainSkirt);
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

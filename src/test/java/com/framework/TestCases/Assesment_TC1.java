package com.framework.TestCases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.base.Initialisers.Constants;
import com.base.Utility.GetBrowserInstance;
import com.base.Utility.Keywords;

public class Assesment_TC1 extends GetBrowserInstance
{

	WebDriver driver;
	Keywords act = new Keywords();

	//Initialize Driver
	@BeforeClass
	public void getBrowser(){
		this.driver = getDriver();
	}

	/*
	 * Open a Tab and Verify Filter
	 * */
	@Test
	public void Scenario1() {
		act.gotToUrl(driver, Constants.urlArma);

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

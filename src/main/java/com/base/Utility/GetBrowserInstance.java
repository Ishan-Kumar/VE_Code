/*  
 * @Author: Ishan
 * This is a class which will use to initialize
 *  the WebDriver. This class will also initiate the
 *  browser instance based on what is mentioned in the constants file.
 *  All the test cases should extend/call function of this class for driver.
 * */

package com.base.Utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.base.Initialisers.Constants;


public class GetBrowserInstance 
{

	private WebDriver driver;
	public String browserName = Constants.browser;
	String driverExe_Path;



	/*This function will Initiate the type of browser defined in constants file
	 * and will create the browser instance accordingly
	 * */
	public  WebDriver getDriver()
	{
		if(browserName.equalsIgnoreCase("chrome")){
			driver = initChromeDriver();
			//driver = initChromeDriverIncognito();
		}else if(browserName.equalsIgnoreCase("Edge")){
			driver = initEdgeDriver();
		}else if(browserName.equalsIgnoreCase("firefox")){
			driver = initFirefoxDriver();
		}else if(browserName.equalsIgnoreCase("IE")){
			driver = initIEDriver();
		}

		return driver;
	}


	/*
	 * This function will create the Chrome browser instance 
	 * In Incognito mode  and return the driver
	 * */
	public WebDriver initChromeDriverIncognito()
	{	
		System.setProperty("webdriver.chrome.driver", "Lib\\chromedriver.exe");
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("incognito");
		options.addArguments("disable-extensions");
		options.addArguments("--start-maximized");
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		driver = new ChromeDriver(capabilities);
		return driver;

	}

	/*
	 * This function will create the Chrome browser instance 
	 * and return the driver
	 * */
	public WebDriver initChromeDriver()
	{
		driverExe_Path = System.getProperty("user.dir") + "//drivers//For_Mac//chromedriver";
		//driverExe_Path = System.setProperty("webdriver.chrome.driver", "//drivers//For_Mac//chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", driverExe_Path);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("disable-extensions");
		options.addArguments("--start-maximized");
		driver = new ChromeDriver(options);
		return driver;
	}
	
	public WebDriver initIEDriver()
	{
		driverExe_Path = System.getProperty("user.dir") + "//drivers//For_Windows//IEDriverServer.exe";
		System.setProperty("webdriver.ie.driver", driverExe_Path);		
		DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
		capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
		capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING,true);
		driver = new InternetExplorerDriver(capabilities);
		//driver.manage().window().maximize();
		return driver;
	}

	/*
	 * This function will create the Edge browser instance 
	 * and return the driver.
	 * Please Note: This will only work with windows10 Environment.
	 * */
	public WebDriver initEdgeDriver(){
		driverExe_Path = System.getProperty("user.dir") + "//drivers//For_Windows//MicrosoftWebDriver.exe";
		System.setProperty("webdriver.edge.driver", driverExe_Path);
		DesiredCapabilities capabilities = DesiredCapabilities.edge();
		driver = new EdgeDriver(capabilities);
		//driver = new EdgeDriver();
		return driver;
	}

	/*
	 * This function will create the firefox browser instance 
	 * and return the driver.
	 * */

	public WebDriver initFirefoxDriver(){
		driverExe_Path = System.getProperty("user.dir") + "//drivers//For_Windows//geckodriver.exe";
		System.setProperty("webdriver.gecko.driver", driverExe_Path);
		//FirefoxProfile profile=new FirefoxProfile();
		//profile.setAcceptUntrustedCertificates(true);
		//DesiredCapabilities capabilities = new DesiredCapabilities();
		//capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		//capabilities.setCapability("acceptInsecureCerts",true);
		driver = new FirefoxDriver();
		return driver;
	}

}

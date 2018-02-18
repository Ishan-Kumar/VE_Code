package com.base.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.base.Utility.Keywords;

public class Nono 
{
	Keywords act = new Keywords();
	Arma arma = new Arma();

	// ------- Page Objects ------
	String logoAtFooter = "//p[contains(@class,'copyright')]";
	String nonoAllProductsOnpage = "//div[contains(@class,'product_')]";
	
	
	
	// ------- Page functions ------
	
	// To scroll down to page for more products
	public void scrollforMoreProducts(WebDriver driver) {
		int timer = 3;
		WebDriverWait wait = new WebDriverWait(driver, timer);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(logoAtFooter)));
		//System.out.println("footer location-->" + element.getRect());
		do {	
			act.scrollPageToElement(driver, "xpath", logoAtFooter);
			arma.waitForPageLoader(driver);
		}while(!element.isDisplayed()); 
		
	}
	
	// To return all Products from the Page
	public List<WebElement> getAllProducts(WebDriver driver) {
		return act.fetchAllElements(driver, nonoAllProductsOnpage);
	}
	
}//end of class

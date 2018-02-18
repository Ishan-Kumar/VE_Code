package com.base.PageObjects;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.base.Utility.Keywords;

public class Arma 
{
	Keywords act = new Keywords();

	// ------- Page Objects ------
	String menFilter = "//div[@data-category-group-name = 'adultsMen']";
	String womenSection = "//div[@data-category-group-name = 'adultsWomen']";
	String verifyFilterCategory = "//li[@data-category-group-name='adultsMen']";
	String menAccordian = "//div[@data-category-group-name = 'adultsMen']/button/i";
	String womenAccordian = "//div[@data-category-group-name = 'adultsWomen']/button/i";
	String allPoductsOnPage = "//h2[contains(@class,'title-text')]";
	String firstProductOnPage = "(//div[contains(@class,'product_')])[1]";

	//Product Modal
	String productModal = "//div[contains(@class,'modal') and contains(@style,'display: block;')]";
	String productImage = "//div[contains(@class,'product-slider')]/img";
	String productPrice = "//div[contains(@class,'modal')]/div/div[contains(@class,'card__price')]";
	String productAction = "//div[contains(@class,'modal')]/div/div[contains(@class,'card__action')]/a";

	// ------- Page functions ------

	//click to open men filter Section
	public void clickfilterMen(WebDriver driver) {
		act.click(driver, "xpath", menFilter);
		waitForPageLoader(driver);
	}

	//Click to open women filter section
	public void clickfilterWoMen(WebDriver driver) {
		act.click(driver, "xpath", womenSection);
		waitForPageLoader(driver);
	}

	//To verify the following filter present in the men category
	public void verifyMenFilter (WebDriver driver, String textToverify) throws Exception {
		String value = act.getText(driver, "xpath", verifyFilterCategory);
		//System.out.println(value);
		if(!value.contains(textToverify)) {
			throw new Exception("value doesn't match");
		}
	}

	//To select a specific category in Womens filter
	public void selectFilterCategoryWomen(WebDriver driver, String filterCategory) {
		//String xPath = "//li[@data-category-group-name ='adultsWomen' and contains(text(),'"+filterCategory+"')]";
		String xPath = "//li[@data-category-group-name ='adultsWomen' and contains(@data-type,'"+filterCategory+"')]";
		act.click(driver, "xpath", xPath);
		waitForPageLoader(driver);
	}

	//To verify if women section is opened
	public void isWomenOpened(WebDriver driver) throws Exception {
		String attributeValue = act.getElementAttribute(driver, "xpath", womenAccordian, "class");
		//System.out.println(attributeValue);
		if(attributeValue.contains("fu-material-icons-arrow-down")) {
			throw new Exception("Women section is not opened");
		}
	}

	//To verify if men secrion is not closed
	public void isMenClosed(WebDriver driver) throws Exception {
		String attributeValue = act.getElementAttribute(driver, "xpath", menAccordian, "class");
		//System.out.println(attributeValue);
		if(!attributeValue.contains("fu-material-icons-arrow-down")) {
			throw new Exception("Men section is not closed");
		}
	}

	//To fetch all the query parameters and returns in a data structure
	public Map<String, String> getQueryParameters(WebDriver driver) throws URISyntaxException {
		//Fetch current URL
		String url = act.getCurrentUrl(driver);
		//System.out.println("url -->" + url);
		//fetch Parameters from URL
		List<NameValuePair> params = URLEncodedUtils.parse(new URI(url), "UTF-8");
		//intialize a hashMap to stor the parametrs
		Map<String, String> searchString = new HashMap<String, String>();
		// put value in Map
		for (NameValuePair param : params) {
			//System.out.println(param.getName() + " : " + param.getValue());
			searchString.put(param.getName() , param.getValue());
		}
		return searchString;
	}


	// To return all the products present on the page
	public List<WebElement> getAllProduct(WebDriver driver) {
		//System.out.println("Number of products: " + act.fetchAllElements(driver, allPoductsOnPage).size());
		return  act.fetchAllElements(driver, allPoductsOnPage);

	}

	//click to open men filter Section
	public void clickfirstProductOnPage(WebDriver driver) {
		act.click(driver, "xpath", firstProductOnPage);
		//waitForPageLoader(driver);
		act.checkElementDisplay(driver, "xPath", productModal);
	}

	// Verify presence of Product price
	public void verifyPresenceOfImage(WebDriver driver) throws Exception {
		String src = act.getElementAttribute(driver, "xpath", productImage, "src");		
		//verify that src should not be empty and null
		if(!(src != null) || src.isEmpty()) {
			throw new Exception ("Product Image is not present on the modal");
		}
	}

	//Verify presence of product price
	public void verifyPresenceOfPrice(WebDriver driver) throws Exception {
		String price = act.getText(driver, "xpath", productPrice);
		//System.out.println("Price value -->" + price);

		//verify that src should not be empty and null
		if(!(price != null) || price.isEmpty()) {
			throw new Exception ("Product Price is not present on the modal");
		}
	}

	//Verify presence of product price
	public void verifyPresenceOfAction(WebDriver driver) throws Exception {
		String productAct = act.getText(driver, "xpath", productAction);
		//System.out.println("Action value -->" + productAct);

		//verify that src should not be empty and null
		if(!(productAct != null) || productAct.isEmpty()) {
			throw new Exception ("Product Action button is not present on the modal");
		}
	}

	/*
	 * This function is specific for spin Sign to check if the 
	 * spinner is visible on webpage, and wait until it disappears. 
	 * Use this function to wait where loader appears
	 * */ 
	String loader = "//*[contains(@class,'spin')]";
	public boolean waitForPageLoader(WebDriver driver)
	{
		int timer1 = 3; 
		int timer2 = 20;
		WebDriverWait wait1 = new WebDriverWait(driver, timer1);	
		WebDriverWait wait2 = new WebDriverWait(driver, timer2);	
		try{
			//wait for visibility
			if(wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loader))) != null){
				//wait for invisibility
				if(wait2.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(loader))))
				{
					//System.out.println("Loader dissappear");
					return true;	
				}else{
					return false;
				}
			}else{
				return false;
			}
		}catch(Exception e){
			return false;
		}
	}// End of method



}// end of class

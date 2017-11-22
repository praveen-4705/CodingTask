package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
	
	private static HomePage homePage = null;

	private By searchBox = By.xpath("//input[@id='searchInput']");
	private By firstOption = By.xpath("(//div[@id='typeahead-suggestions']//a)[1]");
	
	/**
	 * HomePage constructor
	 * 
	 * @param driver	  WebDriver instance
	 */
	private HomePage(WebDriver driver) {
		super(driver);
	}
	
	/**
	 * Create the instance for the HomePage class if the instance is not created
	 * 
	 * @param driver		WebDriver driver instance
	 * @return		Return HomePage instance
	 */
	public static HomePage getInstance(WebDriver driver) {
		
		if (null == homePage) {
			homePage = new HomePage(driver);
		}
		
		return homePage;
	}
	
	/**
	 * Search with the given text and select the first option
	 * 
	 * @param inputText
	 * @return	Returns WikiPage instance
	 */
	public WikiPage searchAndSelect(String inputText) {

		type(searchBox, inputText);

		waitForTheElement(firstOption);
		
		click(firstOption);

		return WikiPage.getInstance(driver);
	}

}

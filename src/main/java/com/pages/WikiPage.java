package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WikiPage extends BasePage {

	private static WikiPage wikiPage = null;

	private By oopLink = By.xpath("//a[text()='Object-oriented programming']");
	
	/**
	 * WikiPage constructor
	 * 
	 * @param driver		WebDriver instance
	 */
	private WikiPage(WebDriver driver) {
		super(driver);
	}
	
	/**
	 * Create the instance for the WikiPage class if the instance is not created
	 * 
	 * @param driver		WebDriver driver instance
	 * @return		Return {@link WikiPage} instance
	 */
	public static WikiPage getInstance(WebDriver driver) {

		if (wikiPage == null) {
			wikiPage = new WikiPage(driver);
		}

		return wikiPage;
	}
	
	/**
	 * Click on the Object-oriented programming link 
	 * 
	 * @return Returns {@link ObjectOrientedProgrammingPage} instance
	 */
	public ObjectOrientedProgrammingPage clickOnOOPLink() {
		click(oopLink);
		return ObjectOrientedProgrammingPage.getInstance(driver);
	}
}

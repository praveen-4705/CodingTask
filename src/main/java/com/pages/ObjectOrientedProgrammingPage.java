package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ObjectOrientedProgrammingPage extends BasePage {

	private static ObjectOrientedProgrammingPage OOPPage = null;

	private By softwareEngineering = By.xpath("//div[@aria-labelledby='Software_engineering']");
	private By showBtn = By.xpath("//div[@aria-labelledby='Software_engineering']//a[text()='show']");
	private By thirdPersonLink = By
			.xpath("(//div[contains(text(),'Software')]/parent::th/following-sibling::td//li)[3]");
	
	/**
	 * ObjectOrientedProgrammingPage constructor
	 * 
	 * @param driver
	 */
	private ObjectOrientedProgrammingPage(WebDriver driver) {
		super(driver);
	}
	
	/**
	 * Create the instance for the WikiPage class if the instance is not created
	 * 
	 * @param driver		WebDriver driver instance
	 * @return		Return ObjectOrientedProgrammingPage instance
	 */
	public static ObjectOrientedProgrammingPage getInstance(WebDriver driver) {

		if (OOPPage == null) {
			OOPPage = new ObjectOrientedProgrammingPage(driver);
		}

		return OOPPage;
	}
	
	/**
	 *Click on third software engineer name
	 * 
	 */
	public void selectNameOfThridEngineer() {

		scrollAtAElement(softwareEngineering);

		click(showBtn);

		click(thirdPersonLink);
		
		synch(1000);

	}
}

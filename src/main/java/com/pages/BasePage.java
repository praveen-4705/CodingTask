package com.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	protected WebDriver driver;

	protected BasePage(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * Locate the element using given locator
	 * 
	 * @param by
	 *            Unique identifier of the element
	 * @return Return WebElement
	 */
	public WebElement locateElement(By by) {

		WebElement w = null;

		try {

			waitForTheElement(by);

			w = driver.findElement(by);

		} catch (NoSuchElementException e) {

			throw new NoSuchElementException("Element not found with the give locator " + by);
		}
		return w;

	}

	/**
	 * Write the String in the field
	 * 
	 * @param by
	 *            Unique identifier of the element
	 * @param text
	 *            String to be written into the field
	 */
	public void type(By by, String text) {

		WebElement w = locateElement(by);

		w.clear();
		w.sendKeys(text);
	}

	/**
	 * Wait for presence and visibility of the element
	 * 
	 * @param by
	 *            Unique identifier of the element
	 */
	public void waitForTheElement(By by) {

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(by));
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	/**
	 * Click on given element
	 * 
	 * @param by
	 *            Unique identifier of the element
	 */
	public void click(By by) {

		WebElement w = locateElement(by);

		w.click();
	}

	/**
	 * Validate the current URL with the Expected URL
	 * 
	 * @param expectedURL
	 *            Excepted URL of the current page
	 */
	public void validateCurrentURL(String expectedURL) {

		String actualURL = driver.getCurrentUrl().trim();

		Assert.assertEquals(actualURL, expectedURL.trim());
	}

	/**
	 * scroll for an element
	 * 
	 * @param by
	 *            Unique identifier of the element
	 */
	public void scrollAtAElement(By by) {

		WebElement w = locateElement(by);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", w);
	}
	
	/**
	 * Pause the thread
	 * 
	 * @param time	Waiting time
	 */
	public void synch(long time) {

		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

	}
}

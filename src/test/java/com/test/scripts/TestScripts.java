package com.test.scripts;

import org.junit.Test;

import com.pages.HomePage;
import com.pages.ObjectOrientedProgrammingPage;
import com.pages.WikiPage;
import com.test.pages.BaseTest;

public class TestScripts extends BaseTest {

	@Test
	public void TestTaskOne() {
		// Create Home Page Object
		HomePage homePage = HomePage.getInstance(getDriver());
		// Search with OOP
		WikiPage wikiPage = homePage.searchAndSelect("OOP");
		// Validate the current web page URL
		wikiPage.validateCurrentURL("https://en.wikipedia.org/wiki/OOP");
		// Click on Object oriented programming link
		ObjectOrientedProgrammingPage OOPPage = wikiPage.clickOnOOPLink();
		// Validate the current web page URL
		OOPPage.validateCurrentURL("https://en.wikipedia.org/wiki/Object-oriented_programming");
		// Click on third engineer name
		OOPPage.selectNameOfThridEngineer();
	}

}

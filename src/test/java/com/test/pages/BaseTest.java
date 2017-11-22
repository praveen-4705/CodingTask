package com.test.pages;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseTest {

	private WebDriver driver;
	private Properties config;

	/**
	 * Create WebDriver instance
	 * 
	 */
	@Before
	public void initDriver() {
		
		// Get the current os name to set the driver path
		String currentOSName = System.getProperty("os.name").toLowerCase();

		// Load the config properties
		loadConfigProperties();

		String browserName = config.getProperty("webdriver.browser");
		String url = config.getProperty("webdriver.base.url");
		
		// Create driver instance based on the config properties and current OS
		if (browserName.equalsIgnoreCase("firefox")) {

			String geckoDriverPath = "";

			if (currentOSName.contains("mac")) {
				geckoDriverPath = "//src//test//resources//browserdrivers//geckodriver";
			} else {
				geckoDriverPath = "//src//test//resources//browserdrivers//geckodriver.exe";
			}

			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + geckoDriverPath);

			setDriver(new FirefoxDriver());
		} else {

			String chromeDriverPath = "//src//test//resources//browserdrivers//chromedriver";

			if (currentOSName.contains("mac")) {
				chromeDriverPath = "//src//test//resources//browserdrivers//chromedriver";
			} else {
				chromeDriverPath = "//src//test//resources//browserdrivers//chromedriver.exe";
			}

			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + chromeDriverPath);

			setDriver(new ChromeDriver());
		}

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		// Navigate to the given URL
		driver.get(url);
	}

	/**
	 * Quit WebDriver
	 * 
	 */
	@After
	public void tearDown() {
		
		// Quit the driver if the driver is not null
		if (driver != null) {
			driver.quit();
		}
	}

	/**
	 * Load the configuration values
	 * 
	 */
	public void loadConfigProperties() {

		String filePath = System.getProperty("user.dir") + "//config.properties";

		// Create property file instance
		config = new Properties();

		// Create File Input Stream instance
		FileInputStream fio;

		try {

			fio = new FileInputStream(filePath);

			// load properties file
			config.load(fio);

		} catch (IOException ioe) {

			ioe.printStackTrace();
		}

	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

}

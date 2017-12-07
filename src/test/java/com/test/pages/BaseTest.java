package com.test.pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import junit.framework.TestResult;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.AssumptionViolatedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseTest {

	private WebDriver driver;
	private Properties config;
	private ExtentHtmlReporter extentHtmlReporter;
	private ExtentReports extentReports;
	private ExtentTest extentTest;
	private String testScriptName;
    private String screenshotPath;

	/**
	 * Create WebDriver instance
	 * 
	 */
    @Rule public TestName testName = new TestName();

    @Rule
    public TestWatcher testWatcher  = new TestWatcher() {

        @Override
        protected void succeeded(Description description) {
            extentTest.log(Status.PASS,"Test Passed");
            try {
                extentTest.addScreenCaptureFromPath(screenshotPath);
            } catch (IOException e) {
                e.printStackTrace();
            }

            extentReports.flush();
        }

        @Override
        protected void failed(Throwable e, Description description) {
            extentTest.log(Status.FAIL,"Test Failed");
            try {
                extentTest.addScreenCaptureFromPath(screenshotPath);
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            extentReports.flush();
        }

        @Override
        protected void skipped(AssumptionViolatedException e, Description description) {
            extentTest.log(Status.SKIP,"Test Skipped");

            extentReports.flush();
        }

        @Override
        protected void starting(Description description) {
            testScriptName = description.getMethodName();

            createReport(testScriptName);
        }

    };

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
        screenshotPath   = getScreenShot();

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

    /**
     * Create Extent HTML Reporter
     *
     * @param testName
     */
	public void createReport(String testName){

        String filePath = System.getProperty("user.dir") + "/src/test/resources/reports/AutomationReport2.html";

	    extentHtmlReporter = new ExtentHtmlReporter(filePath);
	    extentHtmlReporter.setAppendExisting(true);

	    extentReports = new ExtentReports();
	    extentReports.attachReporter(extentHtmlReporter);

	    extentTest = extentReports.createTest(testName);
    }

    /**
     * Take Screenshot and Return the screenshot path
     *
     * @return  Return Screenshot path
     */
    public String getScreenShot(){

	    String screenshotName   = testScriptName + String.valueOf(System.currentTimeMillis()) + ".jpeg";
	    String destFilePath = System.getProperty("user.dir") + "/src/test/resources/screenshots/" + screenshotName;

        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File srcFile    = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File destFile   = new File(destFilePath);
        try {
            FileUtils.copyFile(srcFile,destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return destFilePath;
    }

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

}

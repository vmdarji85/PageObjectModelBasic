package com.w2a.Base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.w2a.utilities.ExcelReader;
import com.w2a.utilities.ExtentManager;
import com.w2a.utilities.Utilities;

public class Page {

	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static ExcelReader excel = new ExcelReader(
			System.getProperty("user.dir") + "\\src\\test\\resources\\com\\w2a\\excel\\testdata.xlsx");
	public static WebDriverWait wait;
	public ExtentReports rep = ExtentManager.getInstance();
	public static ExtentTest test;
	public static String browser;
	public static TopMenu menu;
	public Page() {
		if (driver == null) {
			// setting properties
			try {
				fis = new FileInputStream(System.getProperty("user.dir")
						+ "\\src\\test\\resources\\com\\w2a\\properties\\Config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				config.load(fis);
				log.debug("Config properties file loaded");
			} catch (IOException e) {
				e.printStackTrace();
			}

			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\com\\w2a\\properties\\OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				OR.load(fis);
				log.debug("OR properties file loaded");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Jenkins brower filter configuration

			if (System.getenv("browser") != null && !System.getenv("browser").isEmpty()) {
				browser = System.getenv("browser");
			} else {
				browser = config.getProperty("browser");
			}

			config.setProperty("browser", browser);

			// setting up browser
			if (config.getProperty("browser").equals("firefox")) {

				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")
						+ "\\src\\test\\resources\\com\\w2a\\executables\\geckodriver.exe");
				driver = new FirefoxDriver();
			} else if (config.getProperty("browser").equals("chrome")) {

				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")
						+ "\\src\\test\\resources\\com\\w2a\\executables\\chromedriver.exe");
				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("profile.default_content_setting_values.notifications", 2);
				prefs.put("credentials_enable_service", false);
				prefs.put("profile.password_manager_enabled", false);
				ChromeOptions options = new ChromeOptions();
				options.setExperimentalOption("prefs", prefs);
				options.addArguments("--disable-extensions");
				options.addArguments("--disable-infobars");
				driver = new ChromeDriver(options);

			} else if (config.getProperty("browser").equals("ie")) {
				System.setProperty("webdriver.ie.driver",
						"\\src\\test\\resources\\com\\w2a\\executables\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();
			}

			driver.get(config.getProperty("testsiteurl"));
			log.debug("Navigated to : " + config.getProperty("testsiteurl"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")),
					TimeUnit.SECONDS);

			wait = new WebDriverWait(driver, 5);
			
			menu = new TopMenu(driver);
		}
	}

	public static void quitBrowser() {

		driver.quit();

	}

	public static void click(String locator) {
		if (locator.endsWith("_CSS"))
			driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
		else if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_NAME")) {
			driver.findElement(By.name(OR.getProperty(locator))).click();
		}
		log.info("Successfully clicked on: " + locator);
		test.log(LogStatus.INFO, "Successfully clicked on: " + locator);
		Reporter.log("Successfully clicked on: " + locator);
	}

	public void type(String locator, String value) {
		if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_NAME")) {
			driver.findElement(By.name(OR.getProperty(locator))).sendKeys(value);
		}
		log.info("Successfully typed in element: " + locator + " with value: " + value);
		test.log(LogStatus.INFO, "Successfully typed in element: " + locator + "with value:" + value);
		Reporter.log("Successfully typed in element: " + locator + " with value: " + value);
	}

	static WebElement dropdown;

	public void select(String locator, String value) {
		if (locator.endsWith("_CSS")) {
			dropdown = driver.findElement(By.cssSelector(OR.getProperty(locator)));
		} else if (locator.endsWith("_XPATH")) {
			dropdown = driver.findElement(By.xpath(OR.getProperty(locator)));
		} else if (locator.endsWith("_ID")) {
			dropdown = driver.findElement(By.id(OR.getProperty(locator)));
		} else if (locator.endsWith("_NAME")) {
			dropdown = driver.findElement(By.name(OR.getProperty(locator)));
		}
		Select select = new Select(dropdown);
		select.selectByVisibleText(value);
		log.info("Successfully typed in element: " + locator + " with value: " + value);
		test.log(LogStatus.INFO, "Successfully typed in element: " + locator + "with value:" + value);
		Reporter.log("Successfully typed in element: " + locator + " with value: " + value);
	}

	public boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public static void verifyText(String expectedText, String actualText) throws IOException {
		try {
			Assert.assertEquals(actualText, expectedText);
		} catch (Throwable t) {

			Utilities.CaptureScreenshot();
			
			// Reporter log
			Reporter.log("<br>" + "Verify failure: " + t.getMessage() + "<br>");
			Reporter.log("<a target = \"_blank\" href = " + Utilities.screenshotName + "><img src= "
					+ Utilities.screenshotName + " height = 200 width =200 /></a>");
			Reporter.log("<br>");
			
			
			// Extent report
			test.log(LogStatus.FAIL, " failed with exception: " + t.getMessage());
			test.log(LogStatus.FAIL, test.addScreenCapture(Utilities.screenshotName));

		}

	}

}
package com.base;

import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class baseClass {

	private static ThreadLocal<AppiumDriver> driver = new ThreadLocal<AppiumDriver>();

//	@BeforeClass
//	  public void getPlatform(ITestContext context) {
//	    String platform = context.getCurrentXmlTest().getParameter("platform");
//	    System.out.println("Platform name is : "+platform);
//	  }
	
	@Before
	public void createDriverInstance() {
		System.out.println("****** Starting Driver******");
		String platform = getConfigValue("platform");
		//String browser=context.getCurrentXmlTest().getParameter("platformName");
		System.out.println(platform);
		if (platform.equalsIgnoreCase("Android")) {
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setCapability("platformName", "Android");
			cap.setCapability("platformVersion", "11");
			cap.setCapability("appPackage", "com.android.contacts");
			cap.setCapability("appActivity", "com.android.contacts.activities.PeopleActivity");
			cap.setCapability("deviceName", "emulator-5554");
			try {
				driver.set(new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
		getDriver().manage().timeouts()
				.implicitlyWait(Duration.ofSeconds(Long.parseLong(getConfigValue("timeOutInSecs"))));

	}

//	@BeforeClass
//	public void setUpThreadCount(ITestContext context) {
//		context.getCurrentXmlTest().getSuite().setDataProviderThreadCount(2);
//		context.getCurrentXmlTest().getSuite().setPreserveOrder(false);
//	}

	public static synchronized WebDriver getDriver() {
		return driver.get();
	}

	public String getConfigValue(String key) {
		Properties property = new Properties();
		try {
			property.load(new FileInputStream(System.getProperty("user.dir") + "/src/main/java/config.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return property.getProperty(key);
	}

	@After
	public void quitDriver(Scenario scenario) {
		if (scenario.isFailed()) {
			String screenshotName = scenario.getName().replaceAll("", "_");
			byte[] sourcePath = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
			scenario.attach(sourcePath, "image/png", screenshotName);
		}
		getDriver().quit();
	}

	public By getLocator(String key) {
		Properties property = new Properties();
		By locator=By.id("");
		try {
			property.load(new FileInputStream(System.getProperty("user.dir") + "/src/main/java/Android.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		String value=property.getProperty(key);
		if(value.startsWith("id")) {
			locator=By.id(value.split("~")[1]);
		}
		else if(value.startsWith("xpath")) {
			locator=By.xpath(value.split("~")[1]);
		}
		return locator;
	}
}

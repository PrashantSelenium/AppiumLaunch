package com.android.qtpselenium.AppiumMavenTest;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IRetryAnalyzer;

@Test
public class Testng implements IRetryAnalyzer {
	AndroidDriver driver = null;
	private int retryCount = 0;
	private int maxRetryCount = 2;
	private int i=0;

	public boolean retry(ITestResult result) {

		if (retryCount < maxRetryCount) {
			retryCount++;
			return true;
		}
		return false;
	}
	
	public void takescreenshot(AndroidDriver driver){
		System.out.println("taking screenshot");
		try {
			File source = ((TakesScreenshot) driver)
					.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(source, new File(
					"./Screenshots/outputfile"+i+".png"));
			i++;
		} catch (Exception e) {
			System.out.println("*** Exception is : " + e + " ***");
		}
	}

	@BeforeTest(alwaysRun = true)
	public void setup() {
		try {
			System.out.println("Created Appium Session");
			DesiredCapabilities capabilities = DesiredCapabilities.android();
			capabilities.setCapability("deviceName", "Samsung");
			capabilities.setCapability("platformName", "android");
			capabilities.setCapability("appPackage", "com.reliance.jio.jioswitch");
			capabilities.setCapability("appActivity", "com.reliance.jio.jioswitch.ui.SplashActivity");
			// capabilities.setCapability(CapabilityType.VERSION, osVersion);
//			capabilities.setCapability("app",
//					"/Users/rakshita/AndroidCalculator.apk");
			//capabilities.setCapability("udid", "TA9290J51I");
			driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),
					capabilities);
			System.out.println("initialised driver");
//			driver.findElement(By.id("com.android2.calculator3:id/cling_dismiss"))
//			.click();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// driver.quit();
		}
	}

	@Parameters({ "num1", "num2", "result1" })
	@Test(retryAnalyzer = Testng.class)
	public void addcheck(String num1, String num2, String result1) {
		
		driver.findElement(By.id("com.android2.calculator3:id/digit" + num1))
				.click();
		driver.findElement(By.id("com.android2.calculator3:id/plus")).click();
		driver.findElement(By.id("com.android2.calculator3:id/digit" + num2))
				.click();
		driver.findElement(By.id("com.android2.calculator3:id/equal")).click();
		String num = driver.findElement(
				By.xpath("//android.widget.EditText[@index=0]")).getText();
		if (!num.equals(result1)) {
			takescreenshot(driver);
		}
		Assert.assertEquals(result1, num);
		System.out.println("test addcheck passed");

	}

	@Parameters({ "num1", "num2", "result2" })
	@Test(retryAnalyzer = Testng.class)
	public void subcheck(String num1, String num2, String result2) {
		driver.findElement(By.id("com.android2.calculator3:id/digit" + num1))
				.click();
		driver.findElement(By.id("com.android2.calculator3:id/minus")).click();
		driver.findElement(By.id("com.android2.calculator3:id/digit" + num2))
				.click();
		driver.findElement(By.id("com.android2.calculator3:id/equal")).click();
		String num = driver.findElement(
				By.xpath("//android.widget.EditText[@index=0]")).getText();
		if (!num.equals(result2)) {
			takescreenshot(driver);
		}
		Assert.assertEquals(result2, num);
		System.out.println("test subcheck passed");

	}

	@Parameters({ "num1", "num2", "result3" })
	@Test(retryAnalyzer = Testng.class)
	public void mulcheck(String num1, String num2, String result3) {
		driver.findElement(By.id("com.android2.calculator3:id/digit" + num1))
				.click();
		driver.findElement(By.id("com.android2.calculator3:id/mul")).click();
		driver.findElement(By.id("com.android2.calculator3:id/digit" + num2))
				.click();
		driver.findElement(By.id("com.android2.calculator3:id/equal")).click();
		String num = driver.findElement(
				By.xpath("//android.widget.EditText[@index=0]")).getText();
		if (!num.equals(result3)) {
			takescreenshot(driver);
		}
		Assert.assertEquals(result3, num);
		System.out.println("test mulcheck passed");

	}

	@Parameters({ "num1", "num2", "result4" })
	@Test(retryAnalyzer = Testng.class)
	public void divcheck(String num1, String num2, String result4) {
		driver.findElement(By.id("com.android2.calculator3:id/digit" + num1))
				.click();
		driver.findElement(By.id("com.android2.calculator3:id/div")).click();
		driver.findElement(By.id("com.android2.calculator3:id/digit" + num2))
				.click();
		driver.findElement(By.id("com.android2.calculator3:id/equal")).click();
		String num = driver.findElement(
				By.xpath("//android.widget.EditText[@index=0]")).getText();
		if (!num.equals(result4)) {
			takescreenshot(driver);
		}
		Assert.assertEquals(result4, num);
		System.out.println("test divcheck passed");

	}

	@AfterTest
	public void closedriver() {
		driver.quit();
	}

}

package com.android.qtpselenium.AppiumMavenTest;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.github.genium_framework.appium.support.server.AppiumServer;
import com.github.genium_framework.server.ServerArguments;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class appiumSupport {
	static AppiumServer recieverAppiumServer;
	static AppiumDriver driver;

	public static boolean launchAppium(int portNo) {
		/*
		 * ServerArguments recieverServerArguments = new ServerArguments();
		 * recieverServerArguments.setArgument("--address", "127.0.0.1");
		 * recieverServerArguments.setArgument("--no-reset", true);
		 * recieverServerArguments.setArgument("--local-timezone", true);
		 * recieverServerArguments.setArgument("--port", portNo);
		 * recieverServerArguments.setArgument("--bootstrap-port", 5678); //
		 * recieverServerArguments.setArgument("--udid",receiverDevice);
		 * recieverAppiumServer = new AppiumServer(recieverServerArguments);
		 * System.out.println("launching appium server for reciever phone");
		 * recieverAppiumServer.startServer(); System.out.println(
		 * "appium started"); return recieverAppiumServer.isServerRunning();
		 */
		return true;
		
		
	}

	public static void setup() {
		//boolean appium_launched = launchAppium(4723);
		//if (appium_launched) {
		AppiumDriverLocalService appiumDriverLocalService;
		AppiumServiceBuilder builder = new AppiumServiceBuilder()
				.withAppiumJS(new File("/usr/local/lib/node_modules/appium/bin/appium.js"))
				.withArgument(GeneralServerFlag.APP, System.getProperty("user.dir") + "/build/wordpress.apk")
				.withArgument(GeneralServerFlag.LOG_LEVEL, "info").usingAnyFreePort();
		appiumDriverLocalService = builder.build();
        appiumDriverLocalService.start();
			try {
				File app = new File("/Users/rakshita/AndroidCalculator.apk");
				System.out.println("Created Appium Session");
				DesiredCapabilities capabilities = DesiredCapabilities.android();
				capabilities.setCapability("deviceName", "Samsung");
				capabilities.setCapability("platformName", "android");
				capabilities.setCapability("app", app);
				capabilities.setCapability("platformVersion", 17);
				driver = new AndroidDriver<MobileElement>(appiumDriverLocalService.getUrl(), capabilities);
				//driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
				System.out.println("initialised driver");
				// driver.manage().timeouts().pageLoadTimeout(60,
				// TimeUnit.SECONDS);
				Thread.sleep(5000);
				driver.findElement(By.id("com.android2.calculator3:id/cling_dismiss")).click();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				// driver.close();
				// driver.quit();
			}
		//}
	}
}

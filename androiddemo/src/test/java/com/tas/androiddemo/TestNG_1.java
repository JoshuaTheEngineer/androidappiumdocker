package com.tas.androiddemo;

import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import junit.framework.Assert;

import org.testng.annotations.BeforeClass;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;

public class TestNG_1 {
	File appDir, app;
	AndroidDriver driver;
	DesiredCapabilities capa;
	
	@BeforeClass
	public void beforeClass() throws MalformedURLException{
		System.out.println("Test 1 Started");
		// BELOW is if testing on local machine
		//appDir = new File(System.getProperty("user.dir") + "/app/");
		//app = new File(appDir, "selendroid-test-app-0.17.0.apk");

		capa = new DesiredCapabilities();
		
		// Below is if testing on local machine
		//capa.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		
		// IMPORTANT TO SET IF USING DOCKER		
		capa.setCapability(MobileCapabilityType.APP, "/opt/selendroid-test-app-0.17.0.apk");
		
		// Make sure your device properties are correctly inputted here
		capa.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
		capa.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Device");
		capa.setCapability(MobileCapabilityType.BROWSER_NAME, "");
		//capa.setCapability(MobileCapabilityType.PLATFORM_VERSION, "6.0.1");
		capa.setCapability(MobileCapabilityType.PLATFORM_VERSION, "7.0");
		
		capa.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "io.selendroid.testapp");
		capa.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "io.selendroid.testapp.HomeScreenActivity");
		// Instantiating Android Driver
		//driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"),capa);
		
		// IMPORTANT TO SET IF USING DOCKER
		driver = new AndroidDriver(new URL("http://192.168.99.100:4723/wd/hub"), capa);
		
		// defining timeout
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}
	
	@Test
	public void testAppRun() {
		// performing actions on Selendroid app		
		driver.findElementById("io.selendroid.testapp:id/startUserRegistration").click();
		driver.findElementById("io.selendroid.testapp:id/inputUsername").sendKeys("MyName");
		driver.findElement(By.id("io.selendroid.testapp:id/inputEmail")).sendKeys("name@gmail.com");
		driver.findElement(By.id("io.selendroid.testapp:id/inputPassword")).sendKeys("Password");
		driver.hideKeyboard();
		driver.findElement(By.id("io.selendroid.testapp:id/input_adds")).click();
		driver.findElement(By.id("io.selendroid.testapp:id/btnRegisterUser")).click();
		//validating result
		Assert.assertEquals("MyName", driver.findElement(By.id("io.selendroid.testapp:id/label_username_data")).getText());
	}

	

	@AfterClass
	public void afterClass() {
		driver.removeApp("io.selendroid.testapp"); // uninstalls app
		
		// WARNING: there has been issues when you leave app in device
		// You have to uninstall
		
		driver.closeApp();
		
		Boolean b = driver.isAppInstalled("io.selendroid.testapp");
		if (b == true) {
			System.out.println("App still here");
		} else {
			System.out.println("Uninstall app successfully");
		}
		//Close all driver instances
		driver.quit();
		
		//Test ends
		System.out.println("Test 1 Finished");
	}

}

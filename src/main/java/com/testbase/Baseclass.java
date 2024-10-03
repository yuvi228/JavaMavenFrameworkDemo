package com.testbase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Baseclass {

	public static WebDriver driver;

	public void initDriver(String browser) {

		if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver(); // No need for WebDriverManager, Selenium Manager takes care
		} else if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();

	}

}

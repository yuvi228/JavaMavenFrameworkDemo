package com.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

	public static File propfile;
	public static FileInputStream fileInput;
	public static Properties prop;

	public static String getPropertydata(String property) {

		propfile = new File(Constants.PROPERTYFILEPATH);
		try {
			fileInput = new FileInputStream(propfile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		prop = new Properties();
		// load properties file
		try {
			prop.load(fileInput);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop.getProperty(property);
	}

	public static String getBrowser() {

		// First, check if the browser property is passed as a system property via Maven
		String browser = System.getProperty("browser");
		System.out.println("Browser read from System param :" + browser);

		// If the system property is null, fall back to the value from the properties
		if (browser == null || browser.isEmpty()) {
			browser = getPropertydata("browser");
			System.out.println("Browser read from property file :" + browser);
		}

		return browser;
	}

	public static String getEnvironment() {

		// First, check if the env property is passed as a system property via Maven
		String env = System.getProperty("env");
		System.out.println("env read from System param :" + env);

		// If the system property is null, fall back to the value from the properties

		if (env == null || env.isEmpty()) {
			env = getPropertydata("env");
			System.out.println("env read from property file :" + env);
		}

		return env;

	}
}
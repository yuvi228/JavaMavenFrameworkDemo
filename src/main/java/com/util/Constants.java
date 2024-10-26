package com.util;

import java.io.File;

public class Constants {

	public static final String RESPONSEJSONFOLDERPATH = System.getProperty("user.dir") + File.separator + "ResponseJson"
			+ File.separator;

	public static final String EXTENTREPORTPATH = System.getProperty("user.dir") + File.separator + "extentreport.html";

	public static final String PROPERTYFILEPATH = System.getProperty("user.dir") + File.separator + "src"
			+ File.separator + "main" + File.separator + "resources" + File.separator + "framework.properties";

	public static final String FAILEDTESTSCREENSHOTSPATH = System.getProperty("user.dir") + File.separator
			+ "FailedTestsScreenshots" + File.separator;
}

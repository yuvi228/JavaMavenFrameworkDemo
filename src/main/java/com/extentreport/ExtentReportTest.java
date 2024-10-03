package com.extentreport;

import java.io.File;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ExtentSparkReporterConfig;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.testbase.Baseclass;
import com.util.Util;

public class ExtentReportTest extends Baseclass {

	public static ExtentSparkReporter sparkReporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static ExtentTest parenttest;
	public static ExtentTest childtest;

	@BeforeSuite
	public void setUp() {
		sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + File.separator + "extentreport.html");

		ExtentSparkReporterConfig config = sparkReporter.config();
		config.setDocumentTitle("Automation Test Report");
		config.setReportName("Extent Report Demo");
		config.setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);

	}

	@BeforeTest
	public void parentTestname() {
		initDriver("chrome");
		parenttest = extent.createTest(getClass().getName());
	}

	@BeforeMethod
	public void childTestname(Method method) {
		childtest = parenttest.createNode(method.getName());
	}

	public void testStep(String status, String stepdesc) {

		if (status.equalsIgnoreCase("INFO")) {
			childtest.log(Status.INFO, MarkupHelper.createLabel(stepdesc, ExtentColor.YELLOW));
		} else if (status.equalsIgnoreCase("FAIL")) {
			childtest.log(Status.FAIL, MarkupHelper.createLabel(stepdesc, ExtentColor.RED));
		} else if (status.equalsIgnoreCase("PASS")) {
			childtest.log(Status.PASS, MarkupHelper.createLabel(stepdesc, ExtentColor.GREEN));
		} else if (status.equalsIgnoreCase("SKIP")) {
			childtest.log(Status.SKIP, MarkupHelper.createLabel(stepdesc, ExtentColor.BLUE));
		} else if (status.equalsIgnoreCase("WARNING")) {
			childtest.log(Status.WARNING, MarkupHelper.createLabel(stepdesc, ExtentColor.BLACK));
		}

	}

	@AfterMethod
	public void getResult(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			childtest.log(Status.FAIL, MarkupHelper
					.createLabel(result.getName() + " Test case FAILED due to below issues:", ExtentColor.RED));
			childtest.fail(result.getThrowable());
			String screenshotPath = Util.getScreenshot(driver, result.getName());
			childtest.addScreenCaptureFromPath(screenshotPath);
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			childtest.log(Status.PASS,
					MarkupHelper.createLabel(result.getName() + " Test Case PASSED", ExtentColor.GREEN));
		} else {
			childtest.log(Status.SKIP,
					MarkupHelper.createLabel(result.getName() + " Test Case SKIPPED", ExtentColor.ORANGE));
			childtest.skip(result.getThrowable());
		}

	}

	@AfterSuite
	public void tearDown() {
		extent.flush();
		// Util util = new Util();
		// util.sendEmail();
		driver.quit();
	}

}

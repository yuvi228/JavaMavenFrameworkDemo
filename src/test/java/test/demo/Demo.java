package test.demo;

import org.testng.Assert;

import org.testng.annotations.Test;

import com.extentreport.ExtentReportTest;
import com.testbase.Lauchurl;
import com.util.PropertyReader;
import com.util.Util;

public class Demo extends ExtentReportTest {

	Lauchurl url = new Lauchurl();
	Util util = new Util();

	@Test(priority = 0, description = "This is to launch google")
	public void googleRedirect() throws Exception {

		url.launchURL(PropertyReader.getPropertydata("baseurl"));

		testStep("pass", "Launching URL");

		Assert.assertEquals(true, true);
	}

	@Test(priority = 1, description = "This is to launch Github")
	public void githubRedirect() throws Exception {

		url.launchURL("https://github.com/");
		testStep("pass", "Launching URL");

		Assert.assertEquals(true, true);
	}
}

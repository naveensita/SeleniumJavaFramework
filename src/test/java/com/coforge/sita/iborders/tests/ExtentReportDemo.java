package com.coforge.sita.iborders.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportDemo extends TestBase{
	
	private static final Logger log = LogManager.getLogger(ExtentReportDemo.class);
	ExtentReports extent;
	
	@BeforeTest
	public void config() {
		//ExtentReports, ExtentSparkReporter
		String path = System.getProperty("user.dir") + "\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");
		
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Naveen Pathak");
		
	}
	
	@Test
	public void extentTest() {
		ExtentTest test = extent.createTest("extentTest");
		log.info("Navigating to URL: {}", configProperies.getProperty("qa.url"));
		driver.get(configProperies.getProperty("qa.url"));
		log.info("Title : "+driver.getTitle());
		test.fail("Result do not match");
		extent.flush();
	}

}

package com.coforge.sita.iborders.testComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.coforge.sita.iborders.reports.ExtentReporterNG;
import com.coforge.sita.iborders.tests.TestBase;

public class Listeners extends TestBase implements ITestListener {

	ExtentTest test;
	private static final ExtentReports extent = ExtentReporterNG.getReportObject();
	// ThreadLocal ensures parallel threads do not overwrite each other's test instances
	private static final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	
    public static ExtentTest getExtentTest() {
        return extentTest.get();
    }

	@Override
	public void onTestStart(ITestResult result) {
		String testMethodName = result.getMethod().getMethodName();
		// TODO Auto-generated method stub
		test = extent.createTest(testMethodName);
		extentTest.set(test);
	}

	@Override
    public void onTestSuccess(ITestResult result) {
		extentTest.get().log(Status.PASS, "Test Passed");
    }

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		extentTest.get().fail(result.getThrowable());
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
		String filePath = null;
		try {
			filePath = getScreenshot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		extentTest.get().log(Status.SKIP, "Test Skipped");
        if (result.getThrowable() != null) {
            extentTest.get().skip(result.getThrowable());
        }
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onFinish(ITestContext context) {
		// Flush all collected logs to the HTML file
        extent.flush();
        // Prevent memory leaks in multi-suite runs
        extentTest.remove();
	}

}

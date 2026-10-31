package com.coforge.sita.iborders.reports;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporterNG {
	
	private static ExtentReports extent;
	
	public static ExtentReports getReportObject() {

		// ExtentReports, ExtentSparkReporter
		if (extent == null) {
			// Generate unique time-stamp string (e.g., 2026.10.30_20.15.30)
            String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss").format(new Date());
            // Define destination path with time-stamp
            
			String reportDirectory = System.getProperty("user.dir") + "//target//extent-report//extent_" + timestamp+ "//" ;
			File directory = new File(reportDirectory);
			if (!directory.exists()) {
                directory.mkdirs();
            }
			String reportPath = reportDirectory + "extent_" + timestamp + ".html";
			ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
			reporter.config().setReportName("Automation Execution Report - " + timestamp);
			reporter.config().setDocumentTitle("Test Execution Report");
			reporter.config().setTheme(Theme.STANDARD);
			reporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");

			extent = new ExtentReports();
			extent.attachReporter(reporter);
			extent.setSystemInfo("Host Name", "Localhost");
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("User", System.getProperty("user.name"));
		}
		return extent;
	}
}

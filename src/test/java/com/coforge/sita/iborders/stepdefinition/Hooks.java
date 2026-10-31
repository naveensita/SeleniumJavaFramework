package com.coforge.sita.iborders.stepdefinition;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.coforge.sita.iborders.utils.TestContextSetup;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
TestContextSetup testContextSetup;
	
	public Hooks(TestContextSetup testContextSetup) {
		
		this.testContextSetup = testContextSetup;
	}
	
	@Before(order = 0)
    public void beforeScenario(Scenario scenario) {
		scenario.log("Starting scenario: " + scenario.getName());
    }
	
	@After(order = 0)
	public void afterScenario(Scenario scenario) {
        WebDriver driver = null;
        try {
            driver = testContextSetup.cucumberTestBase.getDriver();
            
            // Capture screenshot if test failed
            if (scenario.isFailed() && driver != null) {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", scenario.getName());
            }
        } catch (Exception e) {
            System.err.println("Exception during teardown/screenshot: " + e.getMessage());
        } finally {
            // Guarantees browser closes even if previous lines fail
            if (driver != null) {
                driver.quit();
                System.out.println("Browser closed successfully for scenario: " + scenario.getName());
            } else {
                System.err.println("Driver was null in Hooks, unable to quit.");
            }
        }
    }
	
}

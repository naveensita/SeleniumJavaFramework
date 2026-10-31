package com.coforge.sita.iborders.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.coforge.sita.iborders.constants.Constants;
import com.coforge.sita.iborders.constants.IWaitTimeConstants;

public class CucumberTestBase {

	// ThreadLocal ensures thread safety for parallel runs
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    // Instantiate Log4j logger
    protected static final Logger log = LogManager.getLogger(CucumberTestBase.class);
    protected Properties configProperies;
	
	public WebDriver WebDriverManager() throws IOException {
		// Return existing active instance if already initialized
        if (driver.get() != null) {
            return driver.get();
        }
		configProperies = new Properties();
    	String filePath = Constants.GLOBAL_RESOURCES_FOLDER_CONFIG + "application.properties";
    	try(FileInputStream fis = new FileInputStream(filePath)){
    		configProperies.load(fis);
		} catch (Exception e) {
			log.error("Failed to load application.properties", e);
		}
    	String headless = configProperies.getProperty("headless");
    	String browser = System.getProperty("browser")!=null ? System.getProperty("browser") : configProperies.getProperty("browser");
    	WebDriver localDriver = null;
    	if (browser.equalsIgnoreCase("chrome")) {
			ChromeOptions options = new ChromeOptions();
			if (Boolean.parseBoolean(headless)) {
				options.addArguments("--headless=new");
			}
			options.addArguments("--window-size=1920,1080");
			options.setAcceptInsecureCerts(true);
			localDriver = new ChromeDriver(options);
			
		}else if (browser.equalsIgnoreCase("firefox")) {
			FirefoxOptions options = new FirefoxOptions();
			if (Boolean.parseBoolean(headless)) {
				options.addArguments("--headless=new");
			}
			options.addArguments("--window-size=1920,1080");
			options.setAcceptInsecureCerts(true);
			localDriver = new FirefoxDriver(options);
			
		}else if (browser.equalsIgnoreCase("edge")) {
			EdgeOptions options = new EdgeOptions();
			if (Boolean.parseBoolean(headless)) {
				options.addArguments("--headless=new");
			}
			options.addArguments("--window-size=1920,1080");
			options.setAcceptInsecureCerts(true);
			localDriver = new EdgeDriver(options);
			
		}
    	localDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IWaitTimeConstants.GLOBAL_WAIT_TIME_SEC));
        //driver.manage().window().maximize();
    	localDriver.manage().deleteAllCookies();
    	driver.set(localDriver);
		return driver.get();
	}
	public WebDriver getDriver() {
        return driver.get();
    }
	public void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove(); // Clean up thread reference
        }
    }
}


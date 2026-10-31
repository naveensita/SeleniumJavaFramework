package com.coforge.sita.iborders.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.coforge.sita.iborders.constants.Constants;
import com.coforge.sita.iborders.constants.IWaitTimeConstants;

public class TestBase {

    public WebDriver driver;
    // Instantiate Log4j logger
    protected static final Logger log = LogManager.getLogger(TestBase.class);
    protected static Properties configProperies;
    
    public void browserInitialization() {
    	configProperies = new Properties();
    	String filePath = Constants.GLOBAL_RESOURCES_FOLDER_CONFIG + "application.properties";
    	try(FileInputStream fis = new FileInputStream(filePath)){
    		configProperies.load(fis);
		} catch (Exception e) {
			log.error("Failed to load application.properties", e);
		}
    }
    
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
    	browserInitialization();
    	//String browser = configProperies.getProperty("browserName");
    	//java -jar jenkins.war -httpPort=9090 --enable-future-java
    	String headless = configProperies.getProperty("headless");
    	String browser = System.getProperty("browser")!=null ? System.getProperty("browser") : configProperies.getProperty("browser");
		if (browser.equalsIgnoreCase("chrome")) {
			ChromeOptions options = new ChromeOptions();
			if (Boolean.parseBoolean(headless)) {
				options.addArguments("--headless=new");
			}
			options.addArguments("--window-size=1920,1080");
			options.setAcceptInsecureCerts(true);
			driver = new ChromeDriver(options);
			
		}else if (browser.equalsIgnoreCase("firefox")) {
			FirefoxOptions options = new FirefoxOptions();
			if (Boolean.parseBoolean(headless)) {
				options.addArguments("--headless=new");
			}
			options.addArguments("--window-size=1920,1080");
			options.setAcceptInsecureCerts(true);
			driver = new FirefoxDriver(options);
			
		}else if (browser.equalsIgnoreCase("edge")) {
			EdgeOptions options = new EdgeOptions();
			if (Boolean.parseBoolean(headless)) {
				options.addArguments("--headless=new");
			}
			options.addArguments("--window-size=1920,1080");
			options.setAcceptInsecureCerts(true);
			driver = new EdgeDriver(options);
			
		}
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IWaitTimeConstants.GLOBAL_WAIT_TIME_SEC));
        //driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    
    /**
     * Gets the screenshot.
     *
     * @param testCaseName the test case name
     * @return 
     * @return the screenshot
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
    	
    	TakesScreenshot ts = (TakesScreenshot)driver;
    	File src = ts.getScreenshotAs(OutputType.FILE);
    	String timestamp = new SimpleDateFormat("yyyy.MM.dd_HH.mm.ss").format(new Date());
    	String filePath = System.getProperty("user.dir") + "//target//extent-report//screenshots//"
    	+ testCaseName + timestamp +".png";
    	File file = new File(filePath);
    	FileUtils.copyFile(src, file);
    	return filePath;
	}
}

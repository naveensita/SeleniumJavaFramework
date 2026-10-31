package com.coforge.sita.iborders.utils;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.coforge.sita.iborders.constants.IWaitTimeConstants;

/**
 * The Class SynchronisationUtil.
 * @author Naveen.Pathak
 */
public class SynchronisationUtil {
	
	/**
     * private constructor to hide the implicit public one.
     */
    private SynchronisationUtil() {

    }
    
    /**
     * Access to logging.
     */
    private static final Logger LOG = LogManager.getLogger(SynchronisationUtil.class);
    
    /**
     * Method to wait until the correct page title element is visible in
     * provided time.
     *
     * @param driver    {@link WebDriver}
     * @param pageTitle String the text expecting in the page title
     * @param waitTime  long the time we are waiting for
     */
    public static void webDriverWaitForTitleElement(final WebDriver driver,
                                                    final long waitTime, final String pageTitle) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
        LOG.info(driver.getTitle().contains(pageTitle));
        LOG.info(driver.getTitle().equals(pageTitle));
        wait.until(ExpectedConditions.titleIs(pageTitle));
    }
	
    /**
     * Synchronization method to check that an element is visible and enabled
     * such that you can click it.
     *
     * @param driver  {@link WebDriver}
     * @param element is the WebElement for the object to be displayed.
     */
    public static void synchroniseUntilTheElementIsVisible(
        final WebDriver driver, final WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(IWaitTimeConstants.GLOBAL_SYNCHRONISATION_TIMEOUT));
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    
    /**
     * Synchronization method to check that an element is no longer visible.
     *
     * @param driver  {@link WebDriver}
     * @param element is the WebElement for the object no longer to be displayed.
     */
    public static void synchroniseUntilTheElementIsNotVisible(
        final WebDriver driver, final WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(IWaitTimeConstants.GLOBAL_WAIT_TIME_SEC));
        wait.until(ExpectedConditions.not(ExpectedConditions.visibilityOf(element)));
    }
    
    /**
     * Synchronise until element invisible.
     *
     * @param driver the driver
     * @param element the element
     */
    public static void synchroniseUntilElementInvisible(final WebDriver driver, final WebElement element) {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(IWaitTimeConstants.GLOBAL_WAIT_TIME));
    	wait.until(ExpectedConditions.invisibilityOf(element));
	}
    
    /**
     * Wait for page load.
     *
     * @param driver the driver
     */
    public static void waitForPageLoad(final WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(IWaitTimeConstants.GLOBAL_WAIT_TIME_SEC));
        wait.until(d -> ((JavascriptExecutor) d)
                .executeScript("return document.readyState").equals("complete"));
    }
    
    /**
	 * Wait for specific time.
	 *
	 * @param timeout the timeout
	 */
	public static void waitForSpecificTime(int timeout) {
		try {
			Thread.sleep(timeout);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

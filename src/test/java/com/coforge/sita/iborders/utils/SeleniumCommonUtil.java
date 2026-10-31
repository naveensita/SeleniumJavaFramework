package com.coforge.sita.iborders.utils;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.google.common.util.concurrent.Uninterruptibles;

/**
 * The Class SeleniumCommonUtil.
 * @author Naveen.Pathak
 */
public class SeleniumCommonUtil {
	
	/**
	 * default protected constructor.
	 */
	protected SeleniumCommonUtil() {

	}

	/**
	 * Log4j Logger.
	 */
	private static final Logger LOG = LogManager.getLogger(SeleniumCommonUtil.class);
	
	/**
	 * Element is not enabled.
	 */
	private static final String ELEMENT_NOT_ENABLED = "The element is not enabled: ";
	
	/**
	 * <p>
	 * highlightElement method highlights a webElement.
	 * </p>
	 *
	 * @param element to highlight
	 * @param driver  {@link WebDriver}
	 */
	public static void highlightElement(final WebElement element, final WebDriver driver) {

		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border='3px dotted blue'", element);
		} catch (Exception e) {
			LOG.info("highlight element failed");
		}
	}
	
	/**
	 * Use this method to simulate typing into an element, which may set its value.
	 *
	 * @param elementToTypeValuesInto - the WebElement you want to type into
	 * @param valueToType             value you want to enter
	 * @param driver                  {@link WebDriver}
	 */
	public static void typeValuesInATextBox(final WebElement elementToTypeValuesInto, final String valueToType,
			final WebDriver driver) {
		SynchronisationUtil.synchroniseUntilTheElementIsVisible(driver, elementToTypeValuesInto);
		if (!elementToTypeValuesInto.isEnabled()) {
			LOG.error(ELEMENT_NOT_ENABLED + "{0}", elementToTypeValuesInto);
			Assert.fail(ELEMENT_NOT_ENABLED + elementToTypeValuesInto);
		}
		highlightElement(elementToTypeValuesInto, driver);
		elementToTypeValuesInto.sendKeys(valueToType);

	}
	
	/**
	 * <p>
	 * scrollIntoView method scrolls the element in the view.
	 * </p>
	 *
	 * @param element WebElement
	 * @param driver  {@link WebDriver}
	 */
	public static void scrollIntoView(final WebElement element, final WebDriver driver) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	/**
	 * Click by java script executor.
	 *
	 * @param element the element
	 * @param driver the driver
	 */
	public static void clickByJavaScriptExecutor(final WebElement element, final WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}
	
	/**
	 * This method makes the program halt for the specified amount of time.
	 *
	 * @param timeToWait time in milliseconds you want to wait for
	 * @author Mohd Jeeshan
	 */

	public static void waitForSpecificTime(final long timeToWait) {
		Uninterruptibles.sleepUninterruptibly(timeToWait, TimeUnit.MILLISECONDS);
	}

}

package com.coforge.sita.iborders.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.coforge.sita.iborders.utils.SynchronisationUtil;

// TODO: Auto-generated Java-doc
/**
 * The Class BasePage.
 * @author Naveen.Pathak
 */
public class BasePage {
	
	/** The driver. */
	WebDriver driver;

	/**
	 * Instantiates a new base page.
	 *
	 * @param driver the driver
	 */
	public BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	/** The home button. */
	@FindBy(css = "[routerlink='/dashboard/']")
	private WebElement homeButton;
	
	/** The orders button. */
	@FindBy(css = "[routerlink='/dashboard/myorders']")
	private WebElement ordersButton;
	
	/** The cart button. */
	@FindBy(css = "[routerlink='/dashboard/cart']")
	private WebElement cartButton;
	
	/** The signout button. */
	@FindBy(xpath = "//*[@class='fa fa-sign-out']/..")
	private WebElement signoutButton;
	
	/**
	 * Navigate to home page.
	 *
	 * @param driver the driver
	 */
	public void navigateToHomePage(WebDriver driver) {
		homeButton.click();
		SynchronisationUtil.waitForPageLoad(driver);
	}
	
	/**
	 * Navigate to orders page.
	 *
	 * @param driver the driver
	 */
	public void navigateToOrdersPage(WebDriver driver) {
		ordersButton.click();
		SynchronisationUtil.waitForPageLoad(driver);
	}
	
	/**
	 * Navigate to cart page.
	 *
	 * @param driver the driver
	 */
	public void navigateToCartPage(WebDriver driver) {
		cartButton.click();
		SynchronisationUtil.waitForPageLoad(driver);
	}
	
	/**
	 * Click signout btn.
	 *
	 * @param driver the driver
	 */
	public void clickSignoutBtn(WebDriver driver) {
		signoutButton.click();
		SynchronisationUtil.waitForPageLoad(driver);
	}
}

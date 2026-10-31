package com.coforge.sita.iborders.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.coforge.sita.iborders.pages.CartPage;
import com.coforge.sita.iborders.pages.CheckoutPage;
import com.coforge.sita.iborders.pages.LoginPage;
import com.coforge.sita.iborders.pages.OrderConfirmationPage;
import com.coforge.sita.iborders.pages.OrdersPage;
import com.coforge.sita.iborders.pages.ProductCataloguePage;
import com.coforge.sita.iborders.testComponents.RetryAnalyzer;

/**
 * The Class EndToEndEcommerceTest.
 * @author Naveen.Pathak
 */
public class EndToEndEcommerceTest extends TestBase{
	
	private static final Logger log = LogManager.getLogger(EndToEndEcommerceTest.class);
	private static String productName = "ZARA COAT 3";
	private static String expectedMessage = "THANKYOU FOR THE ORDER.";
	private static LoginPage loginPage;
	private static ProductCataloguePage productCataloguePage;
	private static CartPage cartPage;
	private static CheckoutPage checkoutPage;
	private static OrderConfirmationPage orderConfirmationPage;
	private static OrdersPage ordersPage;
	
	@BeforeMethod(alwaysRun = true)
	public void beforeMethodSetUp() {
		//Initialize LoginPage objects
		loginPage = new LoginPage(driver);
		//Initialize LProductCataloguePage objects
		productCataloguePage = new ProductCataloguePage(driver);
		//Initialize CartPage objects
		cartPage = new CartPage(driver);
		//Initialize checkoutPage objects
		checkoutPage = new CheckoutPage(driver);
		//Initialize OrderConfirmationPage objects
		orderConfirmationPage = new OrderConfirmationPage(driver);
		//Initialize OrdersPage objects
		ordersPage = new OrdersPage(driver);
	}
	
	/**
	 * End to end E-Commerce test.
	 */
	@Test(groups= {"Regression"}, retryAnalyzer=RetryAnalyzer.class)
	public void endToEndEcommerceTest() {
		
		log.info("endToEndEcommerceTest Starts.");
		log.info("Navigating to URL: {}", configProperies.getProperty("qa.url"));
		driver.get(configProperies.getProperty("qa.url"));
		//Login to Application
		loginPage.LoginToApplication(configProperies.getProperty("username"), configProperies.getProperty("password"));
		//Add to cart
		productCataloguePage.addProductToCart(driver, productName);
		//Verify product and click Checkout button
		boolean match = cartPage.checkProductAvailability(productName);
		Assert.assertTrue(match);
		//Click checkout button
		cartPage.clickCheckoutBtn();
		//Proceed to checkout
		checkoutPage.proceedToCheckout(productName, configProperies.getProperty("countryName"));
		//Verify order confirmed or not
		log.info("orderMsg : "+ orderConfirmationPage.getOrderMessage().getText());
		Assert.assertEquals(orderConfirmationPage.getOrderMessage().getText(), expectedMessage);
		orderConfirmationPage.verifyOrderedProductMatch(productName);
		log.info("endToEndEcommerceTest Ends.....");
		
	}
	
	@Test(dependsOnMethods = {"endToEndEcommerceTest"}, retryAnalyzer=RetryAnalyzer.class)
	public void orderHistoryTest() {
		
		log.info("orderHistoryTest Starts.");
		log.info("Navigating to URL: {}", configProperies.getProperty("qa.url"));
		driver.get(configProperies.getProperty("qa.url"));
		//Login to Application
		loginPage.LoginToApplication(configProperies.getProperty("username"), configProperies.getProperty("password"));
		ordersPage.navigateToOrdersPage(driver);
		Assert.assertTrue(ordersPage.verifyOrderDisplayed(productName), "Product not found on orders history page");
		log.info("orderHistoryTest Ends.");
	}
}

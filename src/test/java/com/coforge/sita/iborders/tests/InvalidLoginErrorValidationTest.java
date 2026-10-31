package com.coforge.sita.iborders.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.coforge.sita.iborders.pages.CartPage;
import com.coforge.sita.iborders.pages.LoginPage;
import com.coforge.sita.iborders.pages.ProductCataloguePage;
import com.coforge.sita.iborders.testComponents.RetryAnalyzer;

public class InvalidLoginErrorValidationTest extends TestBase{
	
	private static final Logger log = LogManager.getLogger(InvalidLoginErrorValidationTest.class);
	private static LoginPage loginPage;
	private static String productName = "ZARA COAT 3";
	private static ProductCataloguePage productCataloguePage;
	private static CartPage cartPage;
	
	@BeforeMethod(alwaysRun = true)
	public void beforeMethodSetUp() {
		//Initialize LoginPage objects
		loginPage = new LoginPage(driver);
		//Initialize LProductCataloguePage objects
		productCataloguePage = new ProductCataloguePage(driver);
		//Initialize CartPage objects
		cartPage = new CartPage(driver);
		
	}
	
	@Test(groups= {"Regression"}, retryAnalyzer=RetryAnalyzer.class)
	public void endToEndErrorValidationTest() {
		
		log.info("endToEndErrorValidationTest Starts.");
		log.info("Navigating to URL: {}", configProperies.getProperty("qa.url"));
		driver.get(configProperies.getProperty("qa.url"));
		//Login to Application
		loginPage.LoginToApplication(configProperies.getProperty("username"), configProperies.getProperty("devpassword"));
		//Get error message and validate
		String error = loginPage.getInvalidLoginError();
		log.info("Login Error : "+error);
		Assert.assertEquals(error, "Incorrect email or password.");
		log.info("endToEndErrorValidationTest Ends.");
	}
	
	@Test(groups= {"Regression"}, retryAnalyzer=RetryAnalyzer.class)
	public void productErrorValidationTest() {
		
		log.info("productErrorValidationTest Starts.");
		log.info("Navigating to URL: {}", configProperies.getProperty("qa.url"));
		driver.get(configProperies.getProperty("qa.url"));
		//Login to Application
		loginPage.LoginToApplication(configProperies.getProperty("username1"), configProperies.getProperty("password"));
		//Add to cart
		productCataloguePage.addProductToCart(driver, productName);
		//Verify product and click Checkout button
		boolean match = cartPage.checkProductAvailability("ZARA COAT 33");
		Assert.assertFalse(match);
		log.info("productErrorValidationTest Ends.");
	}
}

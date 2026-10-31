package com.coforge.sita.iborders.stepdefinition;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.coforge.sita.iborders.pages.CartPage;
import com.coforge.sita.iborders.pages.CheckoutPage;
import com.coforge.sita.iborders.pages.LoginPage;
import com.coforge.sita.iborders.pages.OrderConfirmationPage;
import com.coforge.sita.iborders.pages.ProductCataloguePage;
import com.coforge.sita.iborders.tests.CucumberTestBase;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImplementation extends CucumberTestBase{
	
	public WebDriver driver;
	// Instantiate Log4j logger
    protected static final Logger log = LogManager.getLogger(StepDefinitionImplementation.class);
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		driver = WebDriverManager();
		driver.get(configProperies.getProperty("qa.url"));
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_with_username_and_password(String username, String password) {
		//Initialize LoginPage objects
		LoginPage loginPage = new LoginPage(driver);
		loginPage.LoginToApplication(username, password);
	}
	
	@When("^I add product (.+) to Cart$")
	public void I_add_product_to_Cart(String productName) {
		//Initialize ProductCataloguePage objects
		ProductCataloguePage productCataloguePage = new ProductCataloguePage(driver);
		productCataloguePage.addProductToCart(driver, productName);
	}
	
	@When("^Checkout (.+) and submit the order$")
	public void Checkout_and_submit_the_order(String productName) {
		//Initialize CartPage objects
		CartPage cartPage = new CartPage(driver);
		//Initialize CheckoutPage objects
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		//Verify product and click Checkout button
		boolean match = cartPage.checkProductAvailability(productName);
		Assert.assertTrue(match);
		//Click checkout button
		cartPage.clickCheckoutBtn();
		//Proceed to checkout
		checkoutPage.proceedToCheckout(productName, configProperies.getProperty("countryName"));
	}
	
	@Then("{string} message is displayed on ConfirmationPage")
	public void message_is_displayed_on_ConfirmationPage(String string) {
		//Initialize OrderConfirmationPage objects
		OrderConfirmationPage orderConfirmationPage = new OrderConfirmationPage(driver);
		//Verify order confirmed or not
		log.info("orderMsg : "+ orderConfirmationPage.getOrderMessage().getText());
		Assert.assertEquals(orderConfirmationPage.getOrderMessage().getText(), string);
	}
}

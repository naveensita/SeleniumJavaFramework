package com.coforge.sita.iborders.pages;

import org.openqa.selenium.WebDriver;

/**
 * The Class PageObjectManager.
 * @author Naveen.Pathak
 */
public class PageObjectManager {
	
	/** The driver. */
	public WebDriver driver;
	/** The login page. */
	public LoginPage loginPage;
	/** The product catalogue page. */
	public ProductCataloguePage productCataloguePage;
	/** The cart page. */
	public CartPage cartPage;
	/** The checkout page. */
	public CheckoutPage checkoutPage;
	/** The order confirmation page. */
	public OrderConfirmationPage orderConfirmationPage;
	/** The orders page. */
	public OrdersPage ordersPage;
	
	/**
	 * COnstructor PageObjectManager.
	 *
	 * @param driver the driver
	 */
	public PageObjectManager(WebDriver driver) {
		this.driver = driver;
	}
	
	/**
	 * Gets the login page.
	 *
	 * @return the login page
	 */
	public LoginPage getLoginPage() {
		loginPage= new LoginPage(driver);
	    return loginPage;
	}
	
	/**
	 * Gets the product catalogue page.
	 *
	 * @return the product catalogue page
	 */
	public ProductCataloguePage getProductCataloguePage() {
		productCataloguePage= new ProductCataloguePage(driver);
	    return productCataloguePage;
	}
	
	/**
	 * Gets the cart page.
	 *
	 * @return the cart page
	 */
	public CartPage getCartPage() {
		cartPage= new CartPage(driver);
	    return cartPage;
	}
	
	/**
	 * Gets the checkout page.
	 *
	 * @return the checkout page
	 */
	public CheckoutPage getCheckoutPage() {
		checkoutPage= new CheckoutPage(driver);
	    return checkoutPage;
	}
	
	/**
	 * Gets the order confirmation page.
	 *
	 * @return the order confirmation page
	 */
	public OrderConfirmationPage getOrderConfirmationPage() {
		orderConfirmationPage= new OrderConfirmationPage(driver);
	    return orderConfirmationPage;
	}
	
	/**
	 * Gets the orders page.
	 *
	 * @return the orders page
	 */
	public OrdersPage getOrdersPage() {
		ordersPage= new OrdersPage(driver);
	    return ordersPage;
	}
}


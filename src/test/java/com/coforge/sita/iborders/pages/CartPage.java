package com.coforge.sita.iborders.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


/**
 * The Class CartPage.
 * @author Naveen.Pathak
 */
public class CartPage extends BasePage{

	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".cartSection h3")
	private List<WebElement> cartProducts;
	
	@FindBy(css = ".totalRow button")
	private WebElement checkoutButton;
	
	
	/**
	 * Check product availability.
	 *
	 * @param productName the product name
	 * @return true, if successful
	 */
	public boolean checkProductAvailability(final String productName) {
		//verify cart item
		return cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
	}
	
	/**
	 * Click checkout btn.
	 */
	public void clickCheckoutBtn() {
		//proceed to checkout
		checkoutButton.click();
	}

}

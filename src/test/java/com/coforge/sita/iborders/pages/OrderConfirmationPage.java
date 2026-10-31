package com.coforge.sita.iborders.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class OrderConfirmationPage extends BasePage{
	
	WebDriver driver;

	public OrderConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".hero-primary")
	private WebElement orderMessage;
	
	/**
	 * Gets the order message.
	 *
	 * @return the order message
	 */
	public WebElement getOrderMessage() {
		return orderMessage;
	}

	@FindBy(css = ".line-item.product-info-column.m-3 .title")
	private List<WebElement> productsList;
	
	
	/**
	 * Verify ordered product match.
	 *
	 * @param productName the product name
	 */
	public void verifyOrderedProductMatch(final String productName) {
		boolean matchFlag = productsList.stream().anyMatch(name->name.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(matchFlag);
	}

}

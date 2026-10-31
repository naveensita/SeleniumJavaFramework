package com.coforge.sita.iborders.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * The Class OrdersPage.
 * @author Naveen.Pathak
 */
public class OrdersPage extends BasePage{
	
	WebDriver driver;
	
	public OrdersPage(WebDriver driver){
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	/** The email text box. */
	@FindBy(css = "tr td:nth-child(3)")
	private List<WebElement> productNames;
	
    
	/**
	 * Verify order displayed.
	 *
	 * @param productName the product name
	 * @return 
	 */
	public boolean verifyOrderDisplayed(final String productName) {
		boolean match = productNames.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
		return match;
	}
	
}

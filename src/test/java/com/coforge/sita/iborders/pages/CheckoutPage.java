package com.coforge.sita.iborders.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.coforge.sita.iborders.utils.SynchronisationUtil;

public class CheckoutPage extends BasePage{
	
	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "input[placeholder='Select Country']")
	private WebElement countryDropdown;
	
	@FindBy(css = ".ta-results.list-group button")
	private WebElement countrysArea;
	
	@FindBy(css = ".ta-results.list-group button")
	private List<WebElement> countryList;
	
	@FindBy(css = ".action__submit")
	private WebElement proceedButton;
	
	
	/**
	 * Proceed to checkout.
	 *
	 * @param productName the product name
	 * @param countryName the country name
	 */
	public void proceedToCheckout(final String productName, final String countryName) {
		Actions act = new Actions(driver);
		act.sendKeys(countryDropdown, "India").build().perform();
		//countryDropdown.sendKeys("India");
		SynchronisationUtil.synchroniseUntilTheElementIsVisible(driver, countrysArea);
		WebElement countryElement = countryList.stream()
				.filter(country->country.getText().equalsIgnoreCase(countryName)).findFirst().orElse(null);
		countryElement.click();
		proceedButton.click();
	}

}

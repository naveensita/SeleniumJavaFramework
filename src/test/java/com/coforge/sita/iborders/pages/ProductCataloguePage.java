package com.coforge.sita.iborders.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.coforge.sita.iborders.constants.IWaitTimeConstants;
import com.coforge.sita.iborders.utils.SeleniumCommonUtil;
import com.coforge.sita.iborders.utils.SynchronisationUtil;

public class ProductCataloguePage extends BasePage{
	
	WebDriver driver;

	public ProductCataloguePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	/** The product. */
	@FindBy(css = ".mb-3")
	private WebElement product;
	
	@FindBy(css = ".mb-3")
	private List<WebElement> allProducts;
	
	By productName = By.cssSelector("b");
	By addCardBtn = By.cssSelector(".card-body button:last-of-type");
	
	/** The add to cart button. */
	@FindBy(css = ".card-body button:last-of-type")
	private WebElement addToCartButton;
	
	@FindBy(css = ".ngx-spinner-overlay")
	private WebElement spinnorIcon;
	
	@FindBy(css = ".ng-animating")
	private WebElement annimationIcon;
	
	@FindBy(css = "[routerlink*='/dashboard/cart']")
	private WebElement cartButton;
	
	/**
	 * Adds the product to cart.
	 *
	 * @param driver the driver
	 * @param productName the product name
	 */
	public void addProductToCart(final WebDriver driver, final String productNameText) {
		SynchronisationUtil.synchroniseUntilTheElementIsVisible(driver, product);
		WebElement desiredProduct = allProducts.stream()
				.filter(product -> product.findElement(productName).getText().equalsIgnoreCase(productNameText))
				.findFirst().orElse(null);
		desiredProduct.findElement(addCardBtn).click();
		//wait toast page
		SynchronisationUtil.synchroniseUntilElementInvisible(driver, spinnorIcon);
		// Optional: also wait for Angular's animation layer if active
		//SynchronisationUtil.synchroniseUntilElementInvisible(driver, annimationIcon);
		//click cart
		SynchronisationUtil.synchroniseUntilTheElementIsVisible(driver, cartButton);
		SynchronisationUtil.waitForSpecificTime(IWaitTimeConstants.GLOBAL_WAIT_TIME_MS);
		SeleniumCommonUtil.scrollIntoView(cartButton, driver);
		SeleniumCommonUtil.waitForSpecificTime(IWaitTimeConstants.GLOBAL_WAIT_TIME_TWO_SEC_IN_MS);
		cartButton.click();
		
	}
	
	/**
	 * Click cart button.
	 */
	public void clickCartButton(final WebDriver driver) {
		//cartButton.click();
		navigateToCartPage(driver);
	}

}

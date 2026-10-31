package com.coforge.sita.iborders.tests;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.coforge.sita.iborders.dataProvider.DataProviderUtil;

public class StandAloneTest extends TestBase{

	private static final Logger log = LogManager.getLogger(StandAloneTest.class);
	private static String targetUrl = "https://rahulshettyacademy.com/client/#/auth/login";
	
	@Test(dataProvider="jsonData", dataProviderClass = DataProviderUtil.class)
	public void endToEndEcommerceTest(HashMap<String, String> input) throws InterruptedException {
		
		log.info("Navigating to URL: {}", targetUrl);
		driver.get(targetUrl);

		//Login
		driver.findElement(By.id("userEmail")).sendKeys(input.get("email"));
		driver.findElement(By.id("userPassword")).sendKeys(input.get("password"));
		driver.findElement(By.id("login")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		log.info("Current page title: {}", driver.getTitle());
		
		//Add to cart
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement desiredProduct = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(input.get("product")))
				.findFirst().orElse(null);
		desiredProduct.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		//wait toast page
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ngx-spinner-overlay")));
		// Optional: also wait for Angular's animation layer if active
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		//click cart
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[routerlink*='/dashboard/cart']")));
		driver.findElement(By.cssSelector("[routerlink*='/dashboard/cart']")).click();
		
		//verify cart item
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(input.get("product")));
		Assert.assertTrue(match);
		
		//proceed to checkout
		driver.findElement(By.cssSelector(".totalRow button")).click();
		WebElement countryDropdown = driver.findElement(By.cssSelector("input[placeholder='Select Country']"));
		Actions act = new Actions(driver);
		act.sendKeys(countryDropdown, "India").build().perform();
		//countryDropdown.sendKeys("India");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results.list-group button")));
		List<WebElement> countryList = driver.findElements(By.cssSelector(".ta-results.list-group button"));
		WebElement countryElement = countryList.stream()
				.filter(country->country.getText().equalsIgnoreCase("India")).findFirst().orElse(null);
		countryElement.click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		String orderMsg = driver.findElement(By.cssSelector(".hero-primary")).getText();
		log.info("orderMsg : "+orderMsg);
		Assert.assertEquals(driver.findElement(By.cssSelector(".hero-primary")).getText(), "THANKYOU FOR THE ORDER.");
		
		List<WebElement> productsList = driver.findElements(By.cssSelector(".line-item.product-info-column.m-3 .title"));
		boolean matchFlag = productsList.stream().anyMatch(name->name.getText().equalsIgnoreCase(input.get("product")));
		Assert.assertTrue(matchFlag);
	}
}

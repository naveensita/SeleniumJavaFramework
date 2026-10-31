package com.coforge.sita.iborders.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.coforge.sita.iborders.utils.SeleniumCommonUtil;
import com.coforge.sita.iborders.utils.SynchronisationUtil;

/**
 * The Class LoginPage.
 *
 * @param loginButton the new login button
 */
public class LoginPage extends BasePage{
	
	private static final Logger log = LogManager.getLogger(LoginPage.class);
	WebDriver driver;
	
	public LoginPage(WebDriver driver){
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	/** The email text box. */
	@FindBy(id = "userEmail")
	private WebElement emailTextBox;
	
    /** The password text box. */
	@FindBy(id = "userPassword")
	private WebElement passwordTextBox;
	
	/** The login button. */
    @FindBy(id = "login")
	private WebElement loginButton;
    
    /** The login error. */
    @FindBy(css = ".toast-error div")
	private WebElement loginError;
    
    //.toast-error div
    //Incorrect email or password.
	
	/**
	 * Login to application.
	 *
	 * @param username the username
	 * @param password the password
	 */
	public void LoginToApplication(final String username, final String password) {
		SeleniumCommonUtil.typeValuesInATextBox(emailTextBox, username, driver);
		SeleniumCommonUtil.typeValuesInATextBox(passwordTextBox, password, driver);
		//emailTextBox.sendKeys(username);
		//passwordTextBox.sendKeys(password);
		SeleniumCommonUtil.scrollIntoView(loginButton, driver);
		SeleniumCommonUtil.clickByJavaScriptExecutor(loginButton, driver);
		//loginButton.click();
		log.info("Current page title: {}", driver.getTitle());
	}
	
	/**
	 * Gets the invalid login error.
	 *
	 * @return the invalid login error
	 */
	public String getInvalidLoginError() {
		SynchronisationUtil.synchroniseUntilTheElementIsVisible(driver, loginError);
		return loginError.getText();
	}
}

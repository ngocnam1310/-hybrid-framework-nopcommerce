package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObject.nopcommerce.user.UserHomePageObject;
import pageObject.nopcommerce.user.UserRegisterPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class ValidationRegister extends BaseTest {
	private WebDriver driver;
	private String emailAddress;
	private String firstName, lastName, password;
	
	private UserHomePageObject homePage ;
	private UserRegisterPageObject registerPage ;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		log.info("Pre-condition - Step 01: Open Browser" + browserName + "' and navigate to '");
		registerPage = PageGeneratorManager.getUserRegisterPage(driver);
		homePage = new UserHomePageObject(driver);
		registerPage = new UserRegisterPageObject(driver);
		emailAddress = "afc" + generateFakeNumber() + "@mail.vn";
		firstName ="Auto";
		lastName="Mation";
		password="123456";
	}

	@Test
	public void Register_01_Empty_Data() {
		
		log.info("Register_01 - Step 01 - Click to Register Link");
		homePage.clickToRegisterLink();
		
		log.info("Register_01 - Step 02 - Click to Register Button");
		registerPage.clickToRegisterButton();

		log.info("Register_01 - Step 03 - Verify error message displayed");
		verifyEquals(registerPage.getErrorMessageAtFirstnameTextbox(),"First name is required.");
		verifyEquals(registerPage.getErrorMessageAtLastnameTextbox(),"Last name is required.");
		verifyEquals(registerPage.getErrorMessageAtEmailnameTextbox(),"Email is required.");
		verifyEquals(registerPage.getErrorMessageAtPasswordTextbox(),"Password is required.");
		verifyEquals(registerPage.getErrorMessageAtRePasswordTextbox(),"Password is required.");

	}

	@Test
	public void Register_02_Invalid_Email() {		
		log.info("Register_01 - Step 01 - Click to Register Link");
		homePage.clickToRegisterLink();
		
		log.info("Register_01 - Step 02 - Input to required fields");
		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailnameTextbox("123.123.123");
		registerPage.inputToPasswordnameTextbox(password);
		registerPage.inputToConfirmPasswordnameTextbox(password);
		
		log.info("Register_01 - Step 03 - Click to Register Button");
		registerPage.clickToRegisterButton();
		
		log.info("RRegister_01 - Step 04- Verify errror message displayed");
		Assert.assertEquals(registerPage.getErrorMessageAtEmailnameTextbox(),"Wrong email");

	}

	@Test
	public void Register_03_Success() {
		log.info("Register_01 - Step 01 - Click to Register Link");
		homePage.clickToRegisterLink();
		
		log.info("Register_01 - Step 02 - Input to required fields");
		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailnameTextbox(emailAddress);
		registerPage.inputToPasswordnameTextbox(password);
		registerPage.inputToConfirmPasswordnameTextbox(password);
		

		log.info("Register_01 - Step 03- Click to Register Button");
		registerPage.clickToRegisterButton();
		
		log.info("Register Page - Verify Register Success");
		verifyEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

		log.info("Register Page - Click to LogOut Link");
		registerPage.clickToLogoutLink();
	}

	@Test
	public void Register_04_Existing_Email() {
		log.info("Home Page - Click to Register Link");
		homePage.clickToRegisterLink();

		log.info("Register Page - Input to required fields");
		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailnameTextbox(emailAddress);
		registerPage.inputToPasswordnameTextbox(password);
		registerPage.inputToConfirmPasswordnameTextbox(password);

		log.info("Register Page - Click to Register Button");
		registerPage.clickToRegisterButton();

		log.info("Register Page - Verify message error");
		verifyEquals(registerPage.getErrorExistingEmailMessage(),
				"The specified email already exists");
	}

	@Test
	public void Register_05_Pass_Less_Than_6_Chars() {
		log.info("Home Page - Click to Register Link");
		homePage.clickToRegisterLink();

		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailnameTextbox(emailAddress);
		registerPage.inputToPasswordnameTextbox("123");
		registerPage.inputToConfirmPasswordnameTextbox("123");

		log.info("Register Page - Click to Register Button");
		registerPage.clickToRegisterButton();

		log.info("Register Page - Verify message error");
		verifyEquals(registerPage.getErrorMessageAtPasswordTextbox(),
				"Password must meet the following rules:\nmust have at least 6 characters");
	}

	@Test
	public void Register_06_Invalid_Confirm_Password() {
		log.info("Home Page - Click to Register Link");
		homePage.clickToRegisterLink();

		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailnameTextbox(emailAddress);
		registerPage.inputToPasswordnameTextbox(password);
		registerPage.inputToConfirmPasswordnameTextbox("123654");

		log.info("Register Page - Click to Register Button");
		registerPage.clickToRegisterButton();
		
		log.info("Register Page - Verify message error");
		verifyEquals(registerPage.getErrorMessageAtRePasswordTextbox(),
				"The password and confirmation password do not match.");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	
	
}

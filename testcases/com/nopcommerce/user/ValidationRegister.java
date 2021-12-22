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
		
		System.out.println("Register_01 - Step 01 - Click to Register Link");
		homePage.clickToRegisterLink();
		
		System.out.println("Register_01 - Step 02 - Click to Register Button");
		registerPage.clickToRegisterButton();

		System.out.println("Register_01 - Step 03 - Verify error message displayed");
		Assert.assertEquals(registerPage.getErrorMessageAtFirstnameTextbox(),"First name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtLastnameTextbox(),"Last name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtEmailnameTextbox(),"Email is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(),"Password is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtRePasswordTextbox(),"Password is required.");

	}

	@Test
	public void Register_02_Invalid_Email() {		
		System.out.println("Register_01 - Step 01 - Click to Register Link");
		homePage.clickToRegisterLink();
		
		System.out.println("Register_01 - Step 02 - Input to required fields");
		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailnameTextbox("123.123.123");
		registerPage.inputToPasswordnameTextbox(password);
		registerPage.inputToConfirmPasswordnameTextbox(password);
		
		System.out.println("Register_01 - Step 03 - Click to Register Button");
		registerPage.clickToRegisterButton();
		
		System.out.println("RRegister_01 - Step 04- Verify errror message displayed");
		Assert.assertEquals(registerPage.getErrorMessageAtEmailnameTextbox(),"Wrong email");

	}

	@Test
	public void Register_03_Success() {
		System.out.println("Register_01 - Step 01 - Click to Register Link");
		homePage.clickToRegisterLink();
		
		System.out.println("Register_01 - Step 02 - Input to required fields");
		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailnameTextbox(emailAddress);
		registerPage.inputToPasswordnameTextbox(password);
		registerPage.inputToConfirmPasswordnameTextbox(password);
		

		System.out.println("Register_01 - Step 03- Click to Register Button");
		registerPage.clickToRegisterButton();
		
		System.out.println("Register Page - Verify Register Success");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

		System.out.println("Register Page - Click to LogOut Link");
		registerPage.clickToLogoutLink();
	}

	@Test
	public void Register_04_Existing_Email() {
		System.out.println("Home Page - Click to Register Link");
		homePage.clickToRegisterLink();

		System.out.println("Register Page - Input to required fields");
		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailnameTextbox(emailAddress);
		registerPage.inputToPasswordnameTextbox(password);
		registerPage.inputToConfirmPasswordnameTextbox(password);

		System.out.println("Register Page - Click to Register Button");
		registerPage.clickToRegisterButton();

		System.out.println("Register Page - Verify message error");
		Assert.assertEquals(registerPage.getErrorExistingEmailMessage(),
				"The specified email already exists");
	}

	@Test
	public void Register_05_Pass_Less_Than_6_Chars() {
		System.out.println("Home Page - Click to Register Link");
		homePage.clickToRegisterLink();

		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailnameTextbox(emailAddress);
		registerPage.inputToPasswordnameTextbox("123");
		registerPage.inputToConfirmPasswordnameTextbox("123");

		System.out.println("Register Page - Click to Register Button");
		registerPage.clickToRegisterButton();

		System.out.println("Register Page - Verify message error");
		Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(),
				"Password must meet the following rules:\nmust have at least 6 characters");
	}

	@Test
	public void Register_06_Invalid_Confirm_Password() {
		System.out.println("Home Page - Click to Register Link");
		homePage.clickToRegisterLink();

		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailnameTextbox(emailAddress);
		registerPage.inputToPasswordnameTextbox(password);
		registerPage.inputToConfirmPasswordnameTextbox("123654");

		System.out.println("Register Page - Click to Register Button");
		registerPage.clickToRegisterButton();
		
		System.out.println("Register Page - Verify message error");
		Assert.assertEquals(registerPage.getErrorMessageAtRePasswordTextbox(),
				"The password and confirmation password do not match.");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	
	
}

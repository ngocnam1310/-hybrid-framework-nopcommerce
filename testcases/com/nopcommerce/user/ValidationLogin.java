package com.nopcommerce.user;

import org.testng.annotations.Test;
import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObject.nopcommerce.user.UserHomePageObject;
import pageObject.nopcommerce.user.UserLoginPageObject;
import pageObject.nopcommerce.user.UserMyAccountPageObject;
import pageObject.nopcommerce.user.UserRegisterPageObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class ValidationLogin extends BaseTest{

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		log.info("Pre-condition - Step 01: Open Browser" + browserName + "' and navigate to '" );
		driver = getBrowserDriver(browserName);

		homePage = PageGeneratorManager.getUserHomePage(driver);
		existingEmail = "automation" + generateFakeNumber() + "@mail.vn";
		firstName = "Auto";
		lastName = "Mation";
		validPassword = "123456";
		
		log.info("User_01_Register - Step 01 - Click to Register Link");
		registerPage = homePage.clickToRegisterLink();
		
		log.info("User_01_Register - Step 02 - Input to Firstname textbox" +firstName);
		registerPage.inputToFirstnameTextbox(firstName);
		
		log.info("User_01_Register - Step 03 - Input to Lastname textbox" + lastName);
		registerPage.inputToLastnameTextbox(lastName);
		
		log.info("User_01_Register - Step 04 - Input to Email" +existingEmail);
		registerPage.inputToEmailnameTextbox(existingEmail);
		
		log.info("User_01_Register - Step 05 - Input to Password" +validPassword);
		registerPage.inputToPasswordnameTextbox(validPassword);
		
		log.info("User_01_Register - Step 06 - Input to Confirm Password" +validPassword);
		registerPage.inputToConfirmPasswordnameTextbox(validPassword);

		log.info("User_01_Register - Step 07- Click to Register Button");
		registerPage.clickToRegisterButton();

		log.info("User_01_Register - Step 08- Verify Register Success");
		verifyEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed.");

		log.info("User_01_Register - Step 09- Click to LogOut Link");
		homePage = registerPage.clickToLogoutLink();
	}

	@Test
	public void User_01_Login_With_Empty_Data() {
		log.info("User_01_Login- Login Link");
		loginPage = homePage.clickToLoginLink();
		
		log.info("User_01_Login with empty value");
		loginPage.inptoTextBoxByID(driver,"Email","");
		loginPage.clickToLoginButton();
		
		log.info("User_01_Login Verify error message");
		verifyEquals(loginPage.getErrorMessageByID(driver, "Email-error"),"Please enter your email");
	}
	@Test
	public void User_02_Login_With_Invalid_Email() {
		log.info("User_01_Login with empty value");
		loginPage.inptoTextBoxByID(driver, "Email", "123123");
		loginPage.clickToLoginButton();
		
		log.info("User_01_Login Verify error message");
		verifyEquals(loginPage.getErrorMessageByID(driver, "Email-error"),"Wrong email");
	}
	@Test
	public void User_03_Login_With_Email_NotExist() {
		log.info("User_01_Login with empty value");
		loginPage.inptoTextBoxByID(driver, "Email", "automation@mail.com");
		loginPage.clickToLoginButton();
		
		log.info("User_01_Login Verify error message");
		verifyEquals(loginPage.getErrorMessageUnSuccessful(),
				"Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
	}
	@Test
	public void User_04_Login_With_Email_Exist_But_Password_Invalid() {
		log.info("User_01_Login with empty value");
		loginPage.inptoTextBoxByID(driver, "Email",existingEmail);
		loginPage.inptoTextBoxByID(driver, "Password", "123321");
		loginPage.clickToLoginButton();
		
		log.info("User_01_Login Verify error message");
		verifyEquals(loginPage.getErrorMessageUnSuccessful(),
				"Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}
	@Test
	public void User_05_Login_With_Email_Exist_But_Password_isEmpty() {
		log.info("User_01_Login with empty value");
		loginPage.inptoTextBoxByID(driver, "Email",existingEmail);
		loginPage.inptoTextBoxByID(driver, "Password", " ");
		loginPage.clickToLoginButton();
		
		log.info("User_01_Login Verify error message");
		verifyEquals(loginPage.getErrorMessageUnSuccessful(),
				"Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}

	@Test
	public void User_05_Login_With_Valid_Email_Password() {	
		log.info("User_01_Login - Step 03 - Input to Email");
		loginPage.inputToEmailTextbox(existingEmail);
		
		log.info("User_01_Login - Step 04 - Click to Password");
		loginPage.inputToPasswordTextbox(validPassword);
		
		log.info("User_01_Login - Step 05 - Click to Login Link");
		homePage = loginPage.clickToLoginButton();
		verifyTrue(homePage.isMyAccountLinkDisplayed());
	}



	@Parameters("browser")
	@AfterClass(alwaysRun = true)
	public void cleanBrowser(String browserName) {
		log.info("Post-Condition: Close browser");
		cleanBrowserAndDriver();
	}

	private WebDriver driver;
	private String existingEmail;
	private String firstName, lastName, validPassword;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	private UserMyAccountPageObject myAccount;

}

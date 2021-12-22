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
		existingEmail = "afc" + generateFakeNumber() + "@mail.vn";
		firstName = "Auto";
		lastName = "Mation";
		validPassword = "123456";

	}

	@Test
	public void User_01_Register() {
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
	public void User_02_Login() {	
		log.info("User_01_Login - Step 01 - Login Link");
		loginPage = homePage.clickToLoginLink();
		
		log.info("User_01_Login - Step 02 - Click to Login Link");
		loginPage = new UserLoginPageObject(driver);
		
		log.info("User_01_Login - Step 03 - Input to Email");
		loginPage.inputToEmailTextbox(existingEmail);
		
		log.info("User_01_Login - Step 04 - Click to Password");
		loginPage.inputToPasswordTextbox(validPassword);
		
		log.info("User_01_Login - Step 05 - Click to Login Link");
		homePage = loginPage.clickToLoginButton();
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
	}

	@Test
	public void User_03_My_Account() {
		myAccount = homePage.clickToMyAccountLink();
		Assert.assertTrue(myAccount.isMyAccountPageDisplay());
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

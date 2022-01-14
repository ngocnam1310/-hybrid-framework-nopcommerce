package com.nopcommerce.user;

import org.testng.annotations.Test;
import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObject.nopcommerce.user.UserHomePageObject;
import pageObject.nopcommerce.user.UserLoginPageObject;
import pageObject.nopcommerce.user.UserRegisterPageObject;
import pageObject.nopcommerce.user.UserSearchPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class ValidationSearch extends BaseTest{

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
	}

	@Test
	public void TestCase_01_Search_With_Empty_Data() {
		log.info("Search_01_Open Search Page");
		searchPage = registerPage.clickSearchInFooter();
		
		log.info("Search_02_Seach with empty data");
		searchPage.inputToTextBoxByID(driver, existingEmail, validPassword); 
		searchPage.clickToSearchButton();
		
		log.info("Search_03_Seach Verify error message");
		verifyEquals(loginPage.getErrorMessageByID(driver, "Email-error"),"Search term minimum length is 3 characters");
	}
	@Test
	public void TestCase_02_Search_With_Data_Not_Exist() {
		log.info("Search_01_Login with empty value");
		loginPage.inputToTextBoxByID(driver, "Email", "123123");
		loginPage.clickToLoginButton();
		
		log.info("Search_01_Login Verify error message");
		verifyEquals(loginPage.getErrorMessageByID(driver, "Email-error"),"Wrong email");
	}
	@Test
	public void TestCase_03_Search_With_Product_Name() {
		log.info("Search_01_Login with empty value");
		loginPage.inputToTextBoxByID(driver, "Email", "123123");
		loginPage.clickToLoginButton();
		
		log.info("Search_01_Login Verify error message");
		verifyEquals(loginPage.getErrorMessageByID(driver, "Email-error"),"Wrong email");
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
	private UserSearchPageObject searchPage;

}

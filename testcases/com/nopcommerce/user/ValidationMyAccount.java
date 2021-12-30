package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObject.nopcommerce.user.UserHomePageObject;
import pageObject.nopcommerce.user.UserLoginPageObject;
import pageObject.nopcommerce.user.UserMyAccountPageObject;
import pageObject.nopcommerce.user.UserRegisterPageObject;

public class ValidationMyAccount extends BaseTest{

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
	public void TC_01_Update_Customer_infor() {
		log.info("My_Account - Step 01 - Open My account Page");
		myAccountPage = (UserMyAccountPageObject) homePage.openPageOnHeadrByNamePage(driver, "My account");
		log.info("My_Account - Step 02 - Update information Customer");
		myAccountPage.selectRadioButtonGender();
		myAccountPage.inputToTextBoxByID(driver, "FirstName", validPassword);
		myAccountPage.inputToTextBoxByID(driver, "LastName", validPassword);
		myAccountPage.selectDayInDateOfBirth();
		myAccountPage.selectMonthInDateOfBirth();
		myAccountPage.selectYearInDateOfBirth();
		myAccountPage.inputToTextBoxByID(driver, "Email", validPassword);
		myAccountPage.inputToTextBoxByID(driver, "Company", validPassword);
		log.info("My_Account - Step 03 - Click button Save");
		myAccountPage.clickToSaveButton();
		log.info("My_Account - Step 04 - Verify all fied update success");
		verifyTrue(myAccountPage.isDisplayGender());
		verifyEquals(myAccountPage.getFirstName(), driver);
		verifyEquals(myAccountPage.getLastName(), driver);
		verifyEquals(myAccountPage.getDate(), driver);
		verifyEquals(myAccountPage.getMonth(), driver);
		verifyEquals(myAccountPage.getYear(), driver);
		verifyEquals(myAccountPage.getEmail(), driver);	
	}
	@Test
	public void TC_02_Add_New_Address() {
		log.info("My_Account - Step 01 - Open Addresses in My Account Page");
		
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
	private UserMyAccountPageObject myAccountPage;
}

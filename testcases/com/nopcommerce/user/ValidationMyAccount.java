package com.nopcommerce.user;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObject.nopcommerce.user.UserAddressPageObject;
import pageObject.nopcommerce.user.UserChangePasswordPageObject;
import pageObject.nopcommerce.user.UserHomePageObject;
import pageObject.nopcommerce.user.UserLoginPageObject;
import pageObject.nopcommerce.user.UserMyAccountPageObject;
import pageObject.nopcommerce.user.UserRegisterPageObject;
import utilities.ExeclHelper;

public class ValidationMyAccount extends BaseTest {

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) throws IOException {
		log.info("Pre-condition - Step 01: Open Browser" + browserName + "' and navigate to '");
		driver = getBrowserDriver(browserName);
		ExeclHelper.setExcelFileSheet("MyAccount");
		homePage = PageGeneratorManager.getUserHomePage(driver);
		existingEmail = "automation" + generateFakeNumber() + "@mail.vn";
		firstName = "Auto";
		lastName = "Mation";
		validPassword = "123456";

		log.info("User_01_Register - Step 01 - Click to Register Link");
		registerPage = homePage.clickToRegisterLink();

		log.info("User_01_Register - Step 02 - Input to Firstname textbox" + firstName);
		registerPage.inputToFirstnameTextbox(firstName);

		log.info("User_01_Register - Step 03 - Input to Lastname textbox" + lastName);
		registerPage.inputToLastnameTextbox(lastName);

		log.info("User_01_Register - Step 04 - Input to Email" + existingEmail);
		registerPage.inputToEmailnameTextbox(existingEmail);

		log.info("User_01_Register - Step 05 - Input to Password" + validPassword);
		registerPage.inputToPasswordnameTextbox(validPassword);

		log.info("User_01_Register - Step 06 - Input to Confirm Password" + validPassword);
		registerPage.inputToConfirmPasswordnameTextbox(validPassword);

		log.info("User_01_Register - Step 07- Click to Register Button");
		registerPage.clickToRegisterButton();

		log.info("User_01_Register - Step 08- Verify Register Success");
		verifyEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed.");

		log.info("User_01_Register - Step 09- Click to LogOut Link");
		homePage = registerPage.clickToLogoutLink();

	}

	@Test
	public void TC_01_Update_Customer_infor(XSSFRow row) {
		log.info("My_Account - Step 01 - Open My account Page");
		myAccountPage = (UserMyAccountPageObject) homePage.openPageOnHeadrByNamePage(driver, "My account");
		log.info("My_Account - Step 02 - Update information Customer");
		myAccountPage.selectRadioButtonGender();
		myAccountPage.inputToTextBoxByID(driver, "FirstName", row.getCell(0).toString());
		myAccountPage.inputToTextBoxByID(driver, "LastName", validPassword);
		myAccountPage.selectDropDownByText(driver, firstName, existingEmail);
		myAccountPage.selectDropDownByText(driver, firstName, existingEmail);
		myAccountPage.selectDropDownByText(driver, firstName, existingEmail);
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


	public void TC_02_Add_New_Address() {
		log.info("My_Account - Step 01 - Open Addresses in My Account Page");
		addressPage = (UserAddressPageObject) myAccountPage.openPageAtMyAccountByName(driver, "Addresses");

		log.info("My_Account - Step 02 - Click Button Add New Address");
		addressPage.clickToAddNewAddress();

		log.info("My_Account - Step 03 - Input Data Add New Address");
		addressPage.inputToTextBoxByID(driver, existingEmail, validPassword);
		addressPage.inputToTextBoxByID(driver, existingEmail, validPassword);
		addressPage.inputToTextBoxByID(driver, existingEmail, validPassword);
		addressPage.inputCustomDropDown(driver, validPassword, lastName, firstName, existingEmail);
		addressPage.inputToTextBoxByID(driver, existingEmail, validPassword);
		addressPage.inputToTextBoxByID(driver, existingEmail, validPassword);
		addressPage.inputToTextBoxByID(driver, existingEmail, validPassword);
		addressPage.inputToTextBoxByID(driver, existingEmail, validPassword);
		addressPage.clickButtonSave();

		log.info("My_Account - Step 04 - Verify Data New Address Success");
		verifyEquals(addressPage.getName, addressPage);
		verifyEquals(addressPage.getPhoneNumber, addressPage);
		verifyEquals(addressPage.getAddress, addressPage);
		verifyEquals(addressPage.getCityAndPostCode, addressPage);
		verifyEquals(addressPage.getEmail, addressPage);
		verifyEquals(addressPage.getCountry, addressPage);

	}


	public void TC_03_Change_Password() {
		log.info("My_Account - Step 01 - Open Change Passwprd Page");
		changePasswordPage = (UserChangePasswordPageObject) addressPage.openPageAtMyAccountByName(driver,
				existingEmail);

		log.info("My_Account - Step 02 - Change Password");
		changePasswordPage.inputToTextBoxByID(driver, existingEmail, validPassword);
		changePasswordPage.inputCustomDropDown(driver, validPassword, lastName, firstName, existingEmail);
		changePasswordPage.clickButtonChangePassword();

		log.info("My_Account - Step 03 - Logout");
		homePage = (UserHomePageObject) changePasswordPage.openPageOnHeadrByNamePage(driver, existingEmail);

		log.info("User_01_Login with old password");
		loginPage.inputToTextBoxByID(driver, "Email", existingEmail);
		loginPage.inputToTextBoxByID(driver, "Password", " ");
		loginPage.clickToLoginButton();
		verifyEquals(changePasswordPage, addressPage);

		log.info("User_01_Login with new password");
		loginPage.inputToTextBoxByID(driver, "Email", existingEmail);
		loginPage.inputToTextBoxByID(driver, "Password", " ");
		loginPage.clickToLoginButton();

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
	private UserAddressPageObject addressPage;
	private UserChangePasswordPageObject changePasswordPage;
	private UserLoginPageObject loginPage;
}

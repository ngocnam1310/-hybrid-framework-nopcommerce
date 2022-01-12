package com.nopcommerce.user;

import org.testng.annotations.Test;
import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObject.nopcommerce.user.UserHomePageObject;
import pageObject.nopcommerce.user.UserLoginPageObject;
import pageObject.nopcommerce.user.UserRegisterPageObject;
import utilities.ExeclHelper;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class ValidationLogin2 extends BaseTest{

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		log.info("Pre-condition - Step 01: Open Browser" + browserName + "' and navigate to '" );
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getUserHomePage(driver);
	}@BeforeTest
	public void setupTestData() throws IOException {
		ExeclHelper.setExcelFileSheet("MyAccount");
	}

	@Test
	public void User_05_Login_With_Valid_Email_Password(XSSFRow row ) {	
		log.info("User_01_Login- Login Link");
		loginPage = homePage.clickToLoginLink();
		log.info("User_01_Login - Step 03 - Input to Email");
		loginPage.inputToEmailTextbox(row.getCell(0).toString());
		
		log.info("User_01_Login - Step 04 - Click to Password");
		loginPage.inputToPasswordTextbox(row.getCell(1).toString());
		
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

}

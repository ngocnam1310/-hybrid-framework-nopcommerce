package com.nopcommerce.admin;

import org.testng.annotations.Test;
import commons.BaseTest;
import pageObject.nopcommerce.admin.AdminDashBoardPageObject;
import pageObject.nopcommerce.admin.AdminLoginPageObject;
import pageObject.nopcommerce.admin.AdminProductDetailPO;
import pageObject.nopcommerce.admin.AdminProductSearchPO;
import pageObject.nopcommerce.admin.PageGeneratorManager;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class ValidationUploadFile extends BaseTest{
 
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		loginPage = PageGeneratorManager.getLoginPage(driver);
		adminEmail = "admin@yourstore.com";
		adminPassword = "admin";
		dashboardpage= loginPage.loginAsAdmin(adminEmail, adminPassword);
		
		dashboardpage.openSubMenuPageByName(driver,"Catalog","Products");
		productSearchPage = PageGeneratorManager.getProductSearchPage(driver);
		productSearchPage.inputToProductNameTextbox(produceName);
		productSearchPage.clickToSearchButton();
			
		productDetail = productSearchPage.clickToEditButtonByProductName(produceName);

	}

	@Test
	public void Admin_01_Upload_File() {
		productDetail.clickToExpandPanelByName("Pictures");
		productDetail.uploadFileAtCardName(driver, "pictures",productAvatarImg);;
		Assert.assertTrue(productDetail.isDisplayPictureUploadedSuccessByFileName(""));
		productDetail.enterToAltTextbox(productAvatarAlt);
		productDetail.enterToTitleTextbox(productAvatarTitle);
		productDetail.enterToDisplayOrderTextbox(productAvatarOrder);
		productDetail.clickToAddProductPictureButton();
		
		Assert.assertTrue(productDetail.isPictureImageDisplay(produceName,productAvatarOrder,productAvatarAlt,productAvatarTitle));
		productSearchPage = productDetail.clickToSaveButton();
		Assert.assertTrue(productSearchPage.isSuccessMessageDisplay("The product has been updated successfully."));
		
		productSearchPage.inputToProductNameTextbox("Adobe photoshop");
		productSearchPage.clickToSearchButton();
		Assert.assertTrue(productSearchPage.isPictureImageUpdated(produceName,produceName));
		
		productDetail = productSearchPage.clickToEditButtonByProductName(produceName);
		productDetail.clickToExpandPanelByName("Pictures");
		productDetail.clickToDeleteButtonAtPictureName(productAvatarTitle);//access alert
		Assert.assertTrue(productDetail.isMessageDisplayInTable(driver,"productpictures"));
		productSearchPage = productDetail.clickToSaveButton();
		productSearchPage.inputToProductNameTextbox(produceName);
		productSearchPage.clickToSearchButton();
		Assert.assertTrue(productSearchPage.isPictureImageUpdated("default-image","Adobe photoshop CS4"));
		
		
	}
	@Test
	public void Admin_02_Dynamic_Page_01() {

	}	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	private WebDriver driver;
	AdminLoginPageObject loginPage;
	AdminDashBoardPageObject dashboardpage;
	AdminProductSearchPO productSearchPage;
	AdminProductDetailPO productDetail;
	String adminEmail,adminPassword;
	String productAvatarImg="picture_1.jpg";
	String productAvatarAlt="Avatar Alt";
	String productAvatarTitle="Avatar Title";
	String productAvatarOrder="1";
	String produceName ="Adobe photoshop CS4";
}

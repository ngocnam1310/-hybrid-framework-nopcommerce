package pageObject.nopcommerce.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopcommerce.admin.AdminProductSearchUI;

public class AdminProductSearchPO extends BasePage{
	private WebDriver driver;
	public AdminProductSearchPO(WebDriver driver) {
		this.driver = driver;
	}
	public void inputToProductNameTextbox(String productName) {
		waitForElementVisible(driver, AdminProductSearchUI.PRODUCT_NAME_TEXT_BOX,productName);
		sendKeyToElement(driver, AdminProductSearchUI.PRODUCT_NAME_TEXT_BOX,productName);
	}
	public void clickToSearchButton() {
		waitForElementVisible(driver, AdminProductSearchUI.SEARCH_BUTTON);
		clickToElement(driver, AdminProductSearchUI.SEARCH_BUTTON);
		
	}
	public AdminProductDetailPO clickToEditButtonByProductName(String productName) {
		waitForElementVisible(driver, AdminProductSearchUI.EDIT_BUTTON_LINK_BY_NAME,productName);
		clickToElement(driver, AdminProductSearchUI.EDIT_BUTTON_LINK_BY_NAME,productName);
		return PageGeneratorManager.getProdutDetailPage(driver);
	}
	public boolean isPictureImageUpdated(String productImageName, String productName) {
		productImageName = productImageName.replace(" ", "-").toLowerCase();
		waitForElementVisible(driver, AdminProductSearchUI.PRODUCT_IMAGE_BY_PRODUCT_NAME,productImageName,productName);
		return isElementDisplayed(driver, AdminProductSearchUI.PRODUCT_IMAGE_BY_PRODUCT_NAME,productImageName,productName);
	}
	public boolean isSuccessMessageDisplay(String messageName) {
		waitForElementVisible(driver, AdminProductSearchUI.SUCCESS_MESSAGE_NAME,messageName);
		return isElementDisplayed(driver, AdminProductSearchUI.SUCCESS_MESSAGE_NAME,messageName);
	}

}

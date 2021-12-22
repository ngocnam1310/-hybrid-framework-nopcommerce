package pageObject.nopcommerce.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopcommerce.admin.AdminProductDetailUI;

public class AdminProductDetailPO extends BasePage{
	private WebDriver driver;
	public AdminProductDetailPO(WebDriver driver) {
		this.driver = driver;
	}
	public void clickToExpandPanelByName(String panelName) {
		waitForElementVisible(driver, AdminProductDetailUI.TOOGLE_ICON_BY_CARD_NAME, panelName);
		String toogleIconStatus = getElementAttribute(driver, AdminProductDetailUI.TOOGLE_ICON_BY_CARD_NAME, "class", panelName);
		if(toogleIconStatus.contains("fa-plus")) {
			waitForElementClickable(driver, AdminProductDetailUI.TOOGLE_ICON_BY_CARD_NAME, panelName);
			clickToElement(driver, AdminProductDetailUI.TOOGLE_ICON_BY_CARD_NAME, panelName);
		}
	}
	public boolean isDisplayPictureUploadedSuccessByFileName(String fileName) {
		fileName = fileName.split("\\.")[0];
		waitForElementVisible(driver, AdminProductDetailUI.PICTURE_IMAGE_ADD_NEW_BY_FILE_NAME, fileName);
		return isElementDisplayed(driver, AdminProductDetailUI.PICTURE_IMAGE_ADD_NEW_BY_FILE_NAME, fileName);
	}
	public void enterToAltTextbox(String productAvatarAlt) {
		waitForElementVisible(driver, AdminProductDetailUI.ALL_TEXTBOX_ADD_NEW_BY_FILE_NAME);
		sendKeyToElement(driver, AdminProductDetailUI.ALL_TEXTBOX_ADD_NEW_BY_FILE_NAME, productAvatarAlt);
		
	}
	public void enterToTitleTextbox(String productAvatarTitle) {
		waitForElementVisible(driver, AdminProductDetailUI.TITLE_TEXTBOX_ADD_NEW_BY_FILE_NAME);
		sendKeyToElement(driver, AdminProductDetailUI.ALL_TEXTBOX_ADD_NEW_BY_FILE_NAME, productAvatarTitle);
		
	}
	public void enterToDisplayOrderTextbox(String productAvatarOrder) {
		waitForElementVisible(driver, AdminProductDetailUI.DISPLAY_ORDER_TEXTBOX_ADD_NEW_BY_FILE_NAME);
		sendKeyToElement(driver, AdminProductDetailUI.ALL_TEXTBOX_ADD_NEW_BY_FILE_NAME, productAvatarOrder);
		
	}
	public void clickToAddProductPictureButton() {
		waitForElementVisible(driver, AdminProductDetailUI.ADD_PRODUCT_PICTURE_BUTTON);
		clickToElement(driver,AdminProductDetailUI.ADD_PRODUCT_PICTURE_BUTTON);
		
	}
	public boolean isPictureImageDisplay(String imageName, String displayOrder, String imageAlt, String imageTitle) {
		imageName = imageName.replace(" ", "-").toLowerCase();
		waitForElementVisible(driver, AdminProductDetailUI.DISPLAY_ORDER_TEXTBOX_ADD_NEW_BY_FILE_NAME,imageName,imageAlt,imageTitle);
		return isElementDisplayed(driver, AdminProductDetailUI.DISPLAY_ORDER_TEXTBOX_ADD_NEW_BY_FILE_NAME,imageName,imageAlt,imageTitle);
	}
	public AdminProductSearchPO clickToSaveButton() {
		waitForElementVisible(driver, AdminProductDetailUI.SAVE_BUTTON);
		clickToElement(driver, AdminProductDetailUI.SAVE_BUTTON);
		return PageGeneratorManager.getProductSearchPage(driver);
	}
	public void clickToDeleteButtonAtPictureName(String productTitle) {
		waitForElementVisible(driver, AdminProductDetailUI.DELETE_BUTTON_BY_PICTURE_TITLE,productTitle);
		clickToElement(driver, AdminProductDetailUI.DELETE_BUTTON_BY_PICTURE_TITLE,productTitle);
		acceptAlert(driver);
	}


}

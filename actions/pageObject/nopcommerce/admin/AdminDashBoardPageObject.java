package pageObject.nopcommerce.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopcommerce.admin.AdminDashBoardPageUI;

public class AdminDashBoardPageObject extends BasePage{
	private WebDriver driver;
	public AdminDashBoardPageObject(WebDriver driver) {
		this.driver = driver;
	}
	public boolean isDashboardDisplayed() {
		waitForElementVisible(driver,AdminDashBoardPageUI.DASHBOARD_HEADER);
		return isElementDisplayed(driver,AdminDashBoardPageUI.DASHBOARD_HEADER);
	}
	public AdminProductSearchPO openSubMenutPageByName() {
		// TODO Auto-generated method stub
		return null;
	}

}
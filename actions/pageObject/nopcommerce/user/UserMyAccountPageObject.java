package pageObject.nopcommerce.user;


import org.openqa.selenium.WebDriver;
import commons.BasePage;
import pageUIs.nopcommerce.user.MyAccountPageUI;

public class UserMyAccountPageObject extends BasePage {
	private WebDriver driver;
	public UserMyAccountPageObject(WebDriver driver) {
		this.driver = driver;
	}
	public boolean isMyAccountPageDisplay() {
		waitForElementVisible(driver, MyAccountPageUI.MY_ACCOUNT_HEADER);
		return isElementDisplayed(driver, MyAccountPageUI.MY_ACCOUNT_HEADER);
	}

	
}

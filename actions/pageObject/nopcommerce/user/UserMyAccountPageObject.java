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
	public void selectRadioButtonGender() {
		// TODO Auto-generated method stub
		
	}
	public void clickToSaveButton() {
		// TODO Auto-generated method stub
		
	}
	public boolean isDisplayGender() {
		// TODO Auto-generated method stub
		return false;
	}
	public Object getFirstName() {
		// TODO Auto-generated method stub
		return null;
	}
	public Object getLastName() {
		// TODO Auto-generated method stub
		return null;
	}
	public Object getDate() {
		// TODO Auto-generated method stub
		return null;
	}
	public Object getMonth() {
		// TODO Auto-generated method stub
		return null;
	}
	public Object getYear() {
		// TODO Auto-generated method stub
		return null;
	}
	public Object getEmail() {
		// TODO Auto-generated method stub
		return null;
	}

	
}

package pageUIs.nopcommerce.user;

public class BasePageUI {
	public static final String ADDRESS_LINK ="xpath=//div[contains(@class,'account-navigation')]//a[text()='Addresses']";
	public static final String MY_PRODUCT_REVIEW ="xpath=//div[contains(@class,'account-navigation')]//a[text()='My product reviews']";
	public static final String REWARD_POINT_LINK ="xpath=//div[contains(@class,'account-navigation')]//a[text()='Reward points']";
	public static final String DYNAMIC_PAGE_AT_MY_ACCOUNT_AREA ="xpath=//div[contains(@class,'account-navigation')]//a[text()='%s']";
	public static final String DYNAMIC_HEADER_LINK_BY_NAME ="xpath=//div[@class='header-links']//li/a[text()='%s']";
	
	public static final String LOGOUT_LINK_USER = "xpath=//a[@class ='ico-logout']";
	public static final String LOGOUT_LINK_ADMIN ="xpath=//a[text()='Logout']";
	public static final String DYNAMIC_TEXTBOX_BY_ID ="xpath=//input[@id='%s']";
	public static final String DYNAMIC_ERROR_MESSAGE_BY_ID ="xpath=//span[@id='%s']";
}

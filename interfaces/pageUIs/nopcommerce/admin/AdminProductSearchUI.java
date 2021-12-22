package pageUIs.nopcommerce.admin;

public class AdminProductSearchUI {
	public static final String PRODUCT_NAME_TEXT_BOX ="xpath=//input[@id='SearchProductName']";
	public static final String SEARCH_BUTTON ="xpath=//button[@id='search-products']";
	public static final String EDIT_BUTTON_LINK_BY_NAME ="xpath=//td[text()='%s']//following-sibling::td/a[contains(text(),'Edit')]";
	public static final String SUCCESS_MESSAGE_NAME="xpath=//div[contains(@class,'alert-success') and contains(string(),'The product has been updated successfully')]";
	public static final String PRODUCT_IMAGE_BY_PRODUCT_NAME="xpath=//td[@text(),'%s']/preceding-sibling::td/img[contains(@src,'%s')]";
}

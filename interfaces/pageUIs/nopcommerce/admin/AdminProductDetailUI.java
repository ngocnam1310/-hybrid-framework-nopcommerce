package pageUIs.nopcommerce.admin;

public class AdminProductDetailUI {
	public static final String TOOGLE_ICON_BY_CARD_NAME = "xpath=//div[@class='card-title' and contains(text(),'%s')]/following-sibling::div//i";

	public static final String PICTURE_IMAGE_ADD_NEW_BY_FILE_NAME = "xpath=//div[@class='upload-picture-block']//img[contains(@src,'%s')]";
	public static final String ALL_TEXTBOX_ADD_NEW_BY_FILE_NAME = "xpath=//input[@id='AddPictureModel_OverrideAltAttribute']";
	public static final String TITLE_TEXTBOX_ADD_NEW_BY_FILE_NAME = "xpath=//input[@id='AddPictureModel_OverrideTitleAttribute']";
	public static final String DISPLAY_ORDER_TEXTBOX_ADD_NEW_BY_FILE_NAME = "xpath=//input[@id='AddPictureModel_DisplayOrder']/preceding-sibling::input";
	public static final String ADD_PRODUCT_PICTURE_BUTTON = "xpath=//button[@id='addProductPicture']";
	public static final String PICTURE_TABLE_BY_NAME_ORDER_ALT_TITLE = "xpath=//a[contains(@href,'%s')]/parent::td/following-sibling::td[@data-columnname='DisplayOrder' and text()='%s']/following-sibling::td[@data-columnname='OverrideAltAttribute' and text()='%s']/following-sibling::td[@data-columnname='OverrideTitleAttribute' and text()='%s']";
	public static final String SAVE_BUTTON="xpath=//button[@name='save']";
	public static final String DELETE_BUTTON_BY_PICTURE_TITLE="xpath=//td[text()='%s']/following-sibling::td/a[contains(string(),'Delete')]";
	
}

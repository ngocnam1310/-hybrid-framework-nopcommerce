package pageUIs.nopcommerce.admin;

public class AdminBasePageUI {
	public static final String DASHBOARD_HEADER = "xpath=//h1[contains(text(),'Dashboard')]";
	public static final String MENU_LINK_BY_NAME ="xpath=//ul[@role='menu']/li/a/p[contains(text(),'%s')]";
	public static final String SUB_MENU_LINK_BY_NAME ="xpath=//ul[@class='nav nav-treeview' and @style]//p[contains(text(),'%s')]";
	public static final String UPLOAD_FILE_BY_CARD_NAME = "xpath=//div[@id='product-%s']//input[@type='file']";
	public static final String NO_DATA_MESSAGE_BY_NAME_TABLE="xpath=//table[@id='%s-grid']//td[@class='dataTables_empty' and text()='No data available in table']";
}

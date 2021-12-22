package pageUIs.nopcommerce.admin;

public class AdminDashBoardPageUI {
	public static final String DASHBOARD_HEADER = "//h1[contains(text(),'Dashboard')]";
	public static final String MENU_LINK_BY_NAME ="//ul[@role='menu']/li/a/p[contains(text(),'%s')]";
	public static final String SUB_MENU_LINK_BY_NAME ="//ul[@class='nav nav-treeview' and @style]//p[contains(text(),' Products')]";
}

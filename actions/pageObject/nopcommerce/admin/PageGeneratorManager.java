package pageObject.nopcommerce.admin;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
	private static AdminLoginPageObject loginPage;
	private static AdminDashBoardPageObject dashBroadPage;
	private static AdminProductDetailPO productDetail;
	private static AdminProductSearchPO productSearch;
	public PageGeneratorManager() {

	}
	
	public static AdminLoginPageObject getLoginPage(WebDriver driver) {
		if(loginPage == null) {
			loginPage = new AdminLoginPageObject(driver);
		}
		return loginPage;
	}
	public static AdminDashBoardPageObject getDashBoardPage(WebDriver driver) {
		if(dashBroadPage == null) {
			dashBroadPage = new AdminDashBoardPageObject(driver);
		}
		return dashBroadPage;
	}
	public static AdminProductDetailPO getProdutDetailPage(WebDriver driver) {
		if(productDetail == null) {
			productDetail = new AdminProductDetailPO(driver);
		}
		return productDetail;
	}
	public static AdminProductSearchPO getProductSearchPage(WebDriver driver) {
		if(productSearch == null) {
			productSearch = new AdminProductSearchPO(driver);
		}
		return productSearch;
	}
	

}

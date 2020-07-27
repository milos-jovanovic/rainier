package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public class MiddleHeaderPage extends BasePage {

	//klasa na ff je action-search
	private static By searchButtonBy = By.xpath("//*[@class='action search']");

	private static By searchInputFieldBy = By.name("q");

	//nema na rainier ove ikone iz menija
	//private static By wishListIconBy = By.xpath("//div[@class='customer-wishlist']/div[@class='header-wl']/a");

	private static By miniCartIconBy = By.xpath("//*[@class='action showcart']");

	private static By userAccountIconBy = By.xpath("//a[@class='account-headertop']");

	private static By loginLinkFromDropdownBy = By.xpath("//a[@class='account-headertop']//a[contains(@href,'/customer/account/login/')]");

	//nisam proverio
	private static By logoutLinkFromDropownBy = By.xpath("//li[@class='authorization-link']/a");
	
	private static By userAccountLinkFromDropdownBy = By.xpath("//a[@class='account-headertop']//a[contains(text(),'My Account')]");

	private static By createAccountLinkFromDropdownBy = By.xpath("//a[contains(@href,'/customer/account/create/')]");
	
	public MiddleHeaderPage(WebDriver driver) {
		super(driver);
	}

	// private get WebElement
	private WebElement getSearchButton() {
		return getDriver().findElement(searchButtonBy);
	}

	private WebElement getSearchInputField() {
		return getDriver().findElement(searchInputFieldBy);
	}

//	private WebElement getWishListIcon() {
//		return getDriver().findElement(wishListIconBy);
//	}

	private WebElement getMiniCartIcon() {
		return getDriver().findElement(miniCartIconBy);
	}

	private WebElement getUserAccountIcon() {
		return getDriver().findElement(userAccountIconBy);
	}

	private WebElement getLoginLinkFromDropdown() {
		return getDriver().findElement(loginLinkFromDropdownBy);
	}

	private WebElement getLogoutLinkFromDropdown() {
		return getDriver().findElement(logoutLinkFromDropownBy);
	}
	private WebElement getUserAccountLinkFromDropdown() {
		return getDriver().findElement(userAccountLinkFromDropdownBy);
	}
	private WebElement getCreateAccountLinkFromDropdown() {
		return getDriver().findElement(createAccountLinkFromDropdownBy);
	}
	

	// Actions
	public void clickSearchButton() {
		getSearchButton().click();
	}

	public void inputInSearchField(String text) {
		getSearchInputField().sendKeys(text);
	}

//	public void clickWishListIcon() {
//		getWishListIcon().click();
//	}

	public void clickMiniCartIcon() {
		getMiniCartIcon().click();
	}

	public void clickUserAccountIcon() {
		getUserAccountIcon().click();
	}

	public void clickLoginFromDropdown() {
		getLoginLinkFromDropdown().click();
	}

	public void clickLogoutUser() {
		Actions builder = new Actions(getDriver());
		Action seriesOfActions = builder.moveToElement(getUserAccountIcon()).build();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		seriesOfActions.perform();
		getLogoutLinkFromDropdown().click();
	}
	public void clickUserAccount() {
		Actions builder = new Actions(getDriver());
		Action seriesOfActions = builder.moveToElement(getUserAccountIcon()).build();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		seriesOfActions.perform();
		getUserAccountLinkFromDropdown().click();
	}
	public void clickCreateUserAccount() {
		Actions builder = new Actions(getDriver());
		Action seriesOfActions = builder.moveToElement(getUserAccountIcon()).build();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		seriesOfActions.perform();
		 getCreateAccountLinkFromDropdown().click();
	}
}

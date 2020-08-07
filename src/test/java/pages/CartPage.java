package pages;



import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage extends BasePage {
	
	/*
	 * By se za sad koristi za testiranje 1 proizvoda, ali je ista putanja i ukoliko ih ima vise u listi u tom slucaju 
	 * napravi listu webelemenata pa brisi 0 - nulti
	 * */
	private static By removeProductFromCartButtonBy = By.xpath("//a[contains(@class,'action-delete')]");
	
	private static By emptyCartButtonBy = By.id("empty_cart_button");
	
	private static By productListBy = By.xpath("//table[@id='shopping-cart-table']//*[@class='cart item']");
	
	/*
	 * ovaj By se odnosi na svaki proizvod, ako ih ima vise vratice listu, u testiranju cu koristiti jedan, ali ako
	 * bude potreba da se menja samo vrati listu i upotrebi putanju
	 * */
	private static By moveProductToWishlistBy = By.xpath("//a[contains(@class,'action-towishlist')]");
	
	public CartPage(WebDriver driver) {
		super(driver);
	}
	//WebElements
	private WebElement removeProductFromCartButton() {
		return getDriver().findElement(removeProductFromCartButtonBy);
	}
	private WebElement emptyCartButton() {
		return getDriver().findElement(emptyCartButtonBy);
	}
	private WebElement moveProductToWishlist() {
		return getDriver().findElement(moveProductToWishlistBy);
	}
	private List<WebElement> productList() {
		return getDriver().findElements(productListBy);
	}
	//Actions
	public void clickRemoveProductFromCartButton() {
		removeProductFromCartButton().click();
	}
	public void clickEmptyCartButton() {
		//ovde je odradjen scroll into view da bi mogao da sklonim sve artikle iz carta posto kad ima vise artikla u cart dugme pobegne iz vidljivog dela polja
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();",emptyCartButton() );
		emptyCartButton().click();
	}
	public void clickMoveProductToWishlist() {
		moveProductToWishlist().click();
	}
	public boolean isCartListSizeGreaterThen(int number) {
		return  productList().size()>number;
	}
}

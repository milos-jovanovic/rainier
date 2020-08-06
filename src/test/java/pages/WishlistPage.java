package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WishlistPage extends BasePage {

	//By
	/*
	 * razlika izmedju faashion i ra (commerce i community) - ul -> ol i u klasama i za listu i za list items)
	 * */
	private static By wishistListBy = By.xpath("//ol[@class='product-items']/li");
	
	private static By addAllToCartButtonBy = By.xpath("//button[@data-role='all-tocart']");
	
	/*ovaj by se odnosi na putanju bilo kog proizvoda, za testiranje cu koristiti 1 pa je ok, 
	 * ali ako budes trebao da dodajes vise artikla isto ovo odradi ali kroz listu, prema poziciji posto ce ovaj by onda naci
	 * vise webelementa(listu)
	*/
	private static By removeFromWishlistBy = By.xpath("//a[@data-role='remove']");
	
	/*
	 * vazi isto kao za prethodni, putanja je univerzalna, ako testiras sa vise proizvoda primeni na odredjeni iz liste
	 * */
	private static By addToCartButtonBy = By.xpath("//button[@data-role='tocart']");
	
	
	public WishlistPage(WebDriver driver) {
		super(driver);
	}
	
	//WebElement
	private List<WebElement> wishlistList(){
		return getDriver().findElements(wishistListBy);
	}
	private WebElement removeFromWishlistButton(){
		return getDriver().findElement(removeFromWishlistBy);
	}
	private WebElement addToCartButton(){
		return getDriver().findElement(addToCartButtonBy);
	}
	//Actions
	public boolean isWishlistSizeGreaterThen(int size) {
		return wishlistList().size()>size;
	}
	public void clickRemoveFromWishlistButton() {
		removeFromWishlistButton().click();
	}
	public void clickAddToCart() {
		addToCartButton().click();
	}
	
}

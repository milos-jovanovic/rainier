package pages;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductPage extends BasePage {
	
	//By
	private static By addToCartButtonBy = By.id("product-addtocart-button");
	
	private static By addToWishlistButtonBy = By.xpath("//*[@data-action='add-to-wishlist']");
	

	//izmena
	private static By AvailabilityBy = By.xpath("//div[@title='Availability']");
	
	private static By configurableProductDropDownBy = By.id("//div[@id='product-options-wrapper']");
	
	private static By priceBoxBy = By.xpath("//div[@class='price-box price-final_price']");
	
	private static By breadcrumbsBy = By.xpath("//div[@class='breadcrumbs']");
	
	
	public ProductPage(WebDriver driver) {
		super(driver);

	}
	//Elements
	private WebElement addToCartButton() {
		return getDriver().findElement(addToCartButtonBy);
	}
	private WebElement addToWishlistButton() {
		return getDriver().findElement(addToWishlistButtonBy);
	}
	
	private WebElement Availability() {
		return getDriver().findElement(AvailabilityBy);
	}
	private WebElement configurableProductDropDown() {
		return getDriver().findElement(configurableProductDropDownBy);
	}
	private WebElement priceBox() {
		return getDriver().findElement(priceBoxBy);
	}
	private WebElement breadcrumbsDiv() {
		return getDriver().findElement(breadcrumbsBy);
	}
	//Actions
	
	public void clickAddToCartButton() {
		addToCartButton().click();
	}
	public void clickAddToWishlistButton() {
		addToWishlistButton().click();
	}
	
		public String getAvailabilityText() {
			return Availability().getText();
		}
		public boolean isProductInStock() {
			//System.out.println(Availability().getText());
			//System.out.println("Is product in stock "+getAvailabilityText().equalsIgnoreCase("In stock"));
			return getAvailabilityText().equalsIgnoreCase("In stock");
		}
		public String getPriceBoxText() {
			return priceBox().getText();
		}
		public boolean doesPriceBoxContainsAsLowAs() {
			//System.out.println(priceBox().getText());
			//System.out.println("does price box contains as low as "+getPriceBoxText().contains("As low as"));
			return getPriceBoxText().contains("As low as");
		}
		public boolean isproductInAmmoCategory() {
			return (breadcrumbsDiv().getText().contains("Pistol Ammo")|| breadcrumbsDiv().getText().contains("Rifle Ammo"));
		}
		
		
}

package pages;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CategoryPage extends BasePage {

	
	//WebDriver driver;
	
	
	private static By productListBy = By.xpath("//ol[@class='products list items product-items']/li");
	
	

	
	
	//private static By getPricesDivListBy = By.xpath("//ul[@class='products list items product-items row row-col-lg-4']/li/div[2]");
	//ul[@class='products list items product-items row row-col-lg-4']/li/div[2]/div[1]
	//izdvoji samo listu //span elemenata iz div-a
	
	////div[@class='price-box price-final_price']/div/span[2]
	
	public CategoryPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	//WebElements
	private List<WebElement> getProductList(){
		return getDriver().findElements(productListBy);
	}
	
	
	public boolean isNumberOfProductsGreaterThen(int number) {
		return getProductList().size()>number;
	}
	public void clickOnRandomProduct() {
		List<WebElement> lista = new ArrayList(getProductList());
		if(lista.size()>0) {
		int random = new Random().nextInt(lista.size());
		lista.get(random).findElement(By.tagName("a")).click();
	}else {
		System.out.println("Nema proizvoda u listi");
	}
	
}
}
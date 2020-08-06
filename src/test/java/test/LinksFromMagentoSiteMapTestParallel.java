package test;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import dataProvider.XmlReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.CartPage;
import pages.CategoryPage;
import pages.LoginPage;
import pages.ProductPage;
import testData.TestData;

public class LinksFromMagentoSiteMapTestParallel {

	List<String> Urls;
	Random random = new Random();
	static int numberOfLogedUsers;
	static List<Customer> customers;

	@BeforeClass
	public void driverSetup() {
		WebDriverManager.chromedriver().setup();

	}

	@BeforeSuite
	public void prepare() throws Exception {

		// XmlReader.setupLinksFromMagentoParallel("https://devaws2.rainierarms.com/pub/sitemap.xml");
		
		Urls = XmlReader.getUrlListFromSitemap("https://devaws2.rainierarms.com/pub/sitemap.xml");
		
		// Urls=XmlReader.getUrlListFromSitemap("https://fashionandfriends.com/pub/media/sitemap/rs_sr_sitemap_category.xml");
		System.out.println("Before suite...");

	}
	@BeforeTest
	public void probajUsere() {
		Customer customer = new Customer(20);
		customers=customer.getCustomers();
		//System.out.println(customer.getCustomers().get(5));
//		for(Customer c : customer.getCustomers()) {
//			System.out.println(c.getEmail());
//		}
			
		
	}

	@Test(invocationCount = 5, threadPoolSize = 5)
	public void test() throws Exception {
		//Boolean loged = false;
		
		Thread thread = Thread.currentThread();
		WebDriver driver;
		WebDriverWait wait;
		// ubacio ovaj deo koda za headless
		ChromeOptions options = new ChromeOptions();
		//radi i ovo na neucitavanje slika		
		options.addArguments("--blink-settings=imagesEnabled=false");
		options.addArguments("--headless", "--disable-gpu",
		 "--window-size=1920,1200","--ignore-certificate-errors");
		
		
		//funcionise za neucitavanje slika
//	    HashMap<String, Object> images = new HashMap<String, Object>();
//	    images.put("images", 2);
//	    HashMap<String, Object> prefs = new HashMap<String, Object>();
//	    prefs.put("profile.default_content_setting_values", images);
//	    options.setExperimentalOption("prefs", prefs);
		
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		CartPage cartPage = new CartPage(driver);
		CategoryPage categoryPage = new CategoryPage(driver);
		ProductPage productPage = new ProductPage(driver);
		LoginPage loginPage = new LoginPage(driver);
		System.out.println("L. Size -> " + Urls.size());
		// ubacio do ove tacke

		wait = new WebDriverWait(driver, 30);
		// JavascriptExecutor js = (JavascriptExecutor) driver;
		int i = 0;
		
		if(numberOfLogedUsers<20) {
		int userNumber= numberOfLogedUsers++;
		//numberOfLogedUsers++;
		driver.navigate().to(TestData.LOGIN_PAGE_URL);
		waitForJSandJQueryToLoad(driver, wait);
		loginPage.inputEmail(customers.get(userNumber).getEmail());
		System.out.println("User nmb:"+userNumber+" email: "+customers.get(userNumber).getEmail());
		loginPage.inputPassword(customers.get(userNumber).getPassword());
		waitForJSandJQueryToLoad(driver, wait);
		loginPage.clickSubmitButton();
		waitForJSandJQueryToLoad(driver, wait);
		
		
		}
		while (true) {
			String url = Urls.get(random.nextInt(Urls.size()));

			//String url="https://www.rainierarms.com/lower/thumb-safeties/";
			if (url.split("/").length < 5 || url.contains("ammunition")) {
				continue;
			}
			// String newUrl = url.replaceAll("www","devaws2");
			String newUrl = url.replaceAll("www", "devaws2");
			url = new String();
			url = newUrl;
			// System.out.println(url);
			driver.navigate().to(url);
//				wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState")
//						.equals("complete"));
			waitForJSandJQueryToLoad(driver, wait);
			
			//provera da li ima proizvoda u kategoriji
			if (!categoryPage.isNumberOfProductsGreaterThen(0)) {
				continue;
			} else {
				categoryPage.clickOnRandomProduct();
				waitForJSandJQueryToLoad(driver, wait);
				Thread.sleep(2000);
				if (!productPage.isProductInStock()) {
					continue;
				}
				//provera da li je proizvod konfigurabilan(sadrzi as low as) ili pripada i amo kategoriji
				if (productPage.doesPriceBoxContainsAsLowAs() || productPage.isproductInAmmoCategory()) {
					continue;
				}
				if (random.nextBoolean()) {
					System.out.println("Dodajem proizvod kao thread id: "+thread.getId());
					productPage.clickAddToCartButton();
					waitForJSandJQueryToLoad(driver, wait);
					if (cartPage.isCartListSizeGreaterThen(7)) {
						cartPage.clickEmptyCartButton();
					} else {
						if (random.nextBoolean()) {
							cartPage.clickRemoveProductFromCartButton();
						}
					}

				}

			}
			System.out.println(driver.getCurrentUrl());
			i++;
			if (i > 5)
				break;

		}
		// System.out.println("Zavrsio je krug...");
		driver.quit();
	}

	public boolean waitForJSandJQueryToLoad(WebDriver webDriver, WebDriverWait webDriverWait) {
		// izmena 10:30
		// WebDriverWait wait = new WebDriverWait(getDriver(), 30);
		// wait for jQuery to load
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) ((JavascriptExecutor) webDriver).executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					// no jQuery present
					return true;
				}
			}
		};
		// wait for Javascript to load
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) webDriver).executeScript("return document.readyState").toString()
						.equals("complete");
			}
		};
		// izmena 10:31
		return webDriverWait.until(jQueryLoad) && webDriverWait.until(jsLoad);
	}

}

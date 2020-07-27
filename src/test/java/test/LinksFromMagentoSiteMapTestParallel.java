package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import org.testng.annotations.Test;




import dataProvider.XmlReader;

import io.github.bonigarcia.wdm.WebDriverManager;



public class LinksFromMagentoSiteMapTestParallel {

	List<String> Urls;
	Random random = new Random();
	
	@BeforeClass
	public void driverSetup() {
		WebDriverManager.chromedriver().setup();
		
	}
	
	@BeforeSuite
	public void prepare() throws Exception {
		

		//XmlReader.setupLinksFromMagentoParallel("https://devaws2.rainierarms.com/pub/sitemap.xml");
		Urls=XmlReader.getUrlListFromSitemap("https://devaws2.rainierarms.com/pub/sitemap.xml");
		System.out.println("Before suite...");

		
	}
	
	
	@Test(invocationCount=60,threadPoolSize = 60)
	public void test() {
		WebDriver driver;
		WebDriverWait wait;
		//ubacio ovaj deo koda za headless
				ChromeOptions options = new ChromeOptions();  
				options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors");
				driver = new ChromeDriver(options);
				System.out.println("L. Size -> "+Urls.size());
				//ubacio do ove tacke
				
				wait = new WebDriverWait(driver,30);
				JavascriptExecutor js = (JavascriptExecutor) driver;
				int i =0;
		
				while(true) {
				String url=Urls.get(random.nextInt(Urls.size()));
				String newUrl = url.replaceAll("www","devaws2");
				url = new String();
				url = newUrl;
				//System.out.println(url);
				driver.navigate().to(url);
				wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState")
						.equals("complete"));
				System.out.println(driver.getCurrentUrl());
				i++;
				if(i>2000) break;
			
			}
				//System.out.println("Zavrsio je krug...");
				driver.close();
	}
	

}

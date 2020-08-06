package test;

import java.util.ArrayList;
import java.util.Random;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import dataProvider.XmlReader;
import io.github.bonigarcia.wdm.WebDriverManager;

import pages.CartPage;
import pages.CategoryPage;
import pages.CreateUserPage;
import pages.LoginPage;
import pages.MiddleHeaderPage;
import pages.ProductPage;

import testData.TestData;

public class BaseTest {
	static WebDriver driver;
	static WebDriverWait wait;
	static ExtentReports reports;
	static ExtentHtmlReporter htmlReporter;
	static ExtentTest test;
	JavascriptExecutor js;
	SoftAssert softAssert;
	
	static LoginPage loginPage;

	static MiddleHeaderPage middleHeaderPage;
	static CreateUserPage createUserPage;
	
	static ProductPage productPage;
	static CategoryPage categoryPage;
	
	static CartPage cartPage;
	
	static ArrayList<String> lista;
	static Random r = new Random();
	
	//@BeforeTest
	@BeforeSuite
	public void driverSetup() {	
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver,10);
		js = (JavascriptExecutor) driver;
		driver.manage().window().maximize();
		//ubacio sam report
		//https://www.seleniumeasy.com/selenium-tutorials/creating-extent-reports-in-selenium-example
		if(reports==null) {
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/test-output/testReport.html");
		reports = new ExtentReports();
		reports.attachReporter(htmlReporter);
		     
	        //To add system or environment info by using the setSystemInfo method.
		reports.setSystemInfo("OS", "Windows 10 Pro");
		reports.setSystemInfo("Browser", "Hrom :)");
		htmlReporter.config().setDocumentTitle("Testiranje Fashion&Friends");
		}
		try {
			lista = new ArrayList(XmlReader.getUrlListFromSitemap(TestData.SITEMAP_CATEGORY_PAGES_URL));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@BeforeSuite(dependsOnMethods="driverSetup")
	public void createPages() throws Exception {
		loginPage = new LoginPage(getDriver());
		//anyPage = new AnyPage(getDriver());
		middleHeaderPage = new MiddleHeaderPage(getDriver());
		createUserPage = new CreateUserPage(getDriver());
		//accountPage = new AccountPage(getDriver());
		productPage = new ProductPage(getDriver());
		categoryPage = new CategoryPage(getDriver());
		//wishlistPage = new WishlistPage(getDriver());
		cartPage = new CartPage(getDriver());
		
		
	}
	@BeforeMethod
	public void createNewAssert() {
		softAssert = new SoftAssert();
	}
	@AfterMethod(alwaysRun=true)
	public void getResult(ITestResult result) throws Exception {
		
		if (result.getStatus() == ITestResult.FAILURE) {
			// MarkupHelper is used to display the output in different colors
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
			test.log(Status.FAIL,
					MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(Status.SKIP,
					MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE));
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " Test Case PASSED", ExtentColor.GREEN));
		}
		
	}
	
	//mora da ide after suite, kad se testovi iz svih klasa odrade
	@AfterSuite(alwaysRun=true)
	public void closeBrowser() {
		driver.quit();
		//htmlReporter.stop();
		reports.flush();
		//driver.quit();
	}
	protected WebDriver getDriver() {
		return driver;
	}
	//nisam testirao da li radi na ovaj nacin, ako ne radi iskopiraj iz klase baseTestChromeHeadless only, tamo radi
	protected void setDriverToHeadless() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions option = new ChromeOptions();
		option.addArguments("window-size=1400,800");
		option.addArguments("headless");
		driver = new ChromeDriver(option);
		wait = new WebDriverWait(driver,10);
		js = (JavascriptExecutor) driver;
		driver.manage().window().maximize();
		
	}
	public void pageLoadedWait() {
		wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState")
				.equals("complete"));
	}
	public boolean waitForJSandJQueryToLoad() {
		//izmena 10:30
		//WebDriverWait wait = new WebDriverWait(getDriver(), 30);
		// wait for jQuery to load
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) ((JavascriptExecutor) getDriver()).executeScript("return jQuery.active") == 0);
				}
				catch (Exception e) {
					// no jQuery present
					return true;
				}
			}
		};
		// wait for Javascript to load
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) getDriver()).executeScript("return document.readyState")
						.toString().equals("complete");
			}
		};
		//izmena 10:31
		return getWait().until(jQueryLoad) && wait.until(jsLoad);
	}
	protected WebDriverWait getWait() {
		return wait;
	}
	protected ExtentReports getExtentReports() {
		return reports;
	}
	protected ExtentHtmlReporter getExtentHTMLReporter() {
		return htmlReporter;
	}
	protected JavascriptExecutor getJSExecutor() {
		return js;
	}
}

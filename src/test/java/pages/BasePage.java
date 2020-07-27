package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	protected WebDriver driver;
	protected WebDriverWait wait;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(this.driver,15);
	}
	protected WebDriver getDriver() {
		return driver;
	}
	protected WebDriverWait getWait() {
		return wait;
	}
	public boolean waitForJSandJQueryToLoad() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
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
		return wait.until(jQueryLoad) && wait.until(jsLoad);
	}
}

package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

	private static By emailInputFieldBy = By.id("email");

	private static By passwordInputFieldBy = By.name("login[password]");


	private static By submitButtonBy = By.xpath("//button[@class='action login primary']");

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	// private get Elements
	private WebElement getEmailInputField() {
		return getDriver().findElement(emailInputFieldBy);
	}

	private WebElement getPasswordInputField() {
		return getDriver().findElement(passwordInputFieldBy);
	}


	private WebElement getSubmitButton() {
		return getDriver().findElement(submitButtonBy);
	}

	// Actions
	public void inputEmail(String email) {
		getEmailInputField().clear();
		getEmailInputField().sendKeys(email);
	}

	public void inputPassword(String password) {
		getPasswordInputField().clear();
		getPasswordInputField().sendKeys(password);
	}


	public void clickSubmitButton() {
		getSubmitButton().click();
	}

}

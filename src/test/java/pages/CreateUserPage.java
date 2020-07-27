package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateUserPage extends BasePage{

	//static WebDriver driver;

	@FindBy(xpath = "//input[@id='firstname']")
	WebElement nameInputField;

	@FindBy(xpath = "//div[@id='firstname-error']")
	WebElement nameError;

	@FindBy(xpath = "//input[@id='lastname']")
	WebElement lastNameInputField;

	@FindBy(xpath = "//div[@id='lastname-error']")
	WebElement lastNameEror;

	@FindBy(xpath = "//input[@id='ff_card']")
	WebElement loyaltyCardInputField;

	@FindBy(xpath = "//input[@id='email_address']")
	WebElement emailInputField;

	@FindBy(xpath = "//div[@id='email_address-error']")
	WebElement emailError;

	@FindBy(xpath = "//input[@id='password']")
	WebElement passwordInputField;

	@FindBy(xpath = "//div[@id='password-error']")
	WebElement passwordError;

	@FindBy(xpath = "//input[@id='password-confirmation']")
	WebElement confirmPasswordInputField;

	@FindBy(xpath = "//div[@id='password-confirmation-error']")
	WebElement confirmPasswordError;

	@FindBy(xpath = "//input[@id='accept_gdpr']")
	WebElement acceptCheckBoxButton;

	@FindBy(xpath = "//div[@id='accept_gdpr-error']")
	WebElement acceptButtonError;

	@FindBy(xpath = "//button[@class='action submit primary btn btn-default']")
	WebElement createUserButton;

	@FindBy(xpath = "//*[@class='action back btn btn-primary']")
	WebElement backButton;

	public CreateUserPage(WebDriver driver) {
		super(driver);
		//this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void inputFirstName(String name) {
		nameInputField.sendKeys(name);
	}

	public void inputLastName(String lastName) {
		lastNameInputField.sendKeys(lastName);
	}

	public void inputLoyaltyCard(String loyaltyCard) {
		loyaltyCardInputField.sendKeys(loyaltyCard);
	}

	public void inputEmail(String email) {
		emailInputField.sendKeys(email);
	}

	public void inputPassword(String password) {
		passwordInputField.sendKeys(password);
	}

	public void inputConfirmPassword(String passwordConfirm) {
		confirmPasswordInputField.sendKeys(passwordConfirm);
	}
	public void clickAgreementCheckButton() {
		if(!acceptCheckBoxButton.isSelected()) {
			acceptCheckBoxButton.click();
		}
	}
	public void clickCreateUserButton() {
		createUserButton.click();
	}
	
}

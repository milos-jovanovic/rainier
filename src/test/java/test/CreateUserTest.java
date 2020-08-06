package test;

import org.testng.annotations.Test;

import testData.TestData;

public class CreateUserTest extends BaseTest {
	
	@Test
	public void createUser() {
		
		createUserPage.inputFirstName(TestData.name);
		createUserPage.inputLastName(TestData.name);
		createUserPage.inputEmail(TestData.create_user_email);
		createUserPage.inputPassword(TestData.create_user_password);
		createUserPage.inputConfirmPassword(TestData.create_user_password);
		//treba iskljuciti capcha u magentu za vreme testiranja, ili skini klasu za prepoznavanje capcha pa isprobaj da li radi dobro
		createUserPage.clickCreateUserButton();
	}

}

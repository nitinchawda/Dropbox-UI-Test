package com.dbx.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import com.page.obj.PageObject;

public class SignInPage extends PageObject {

	@FindBy(css = "input[name=login_email]")
	WebElement loginEmail;

	@FindBy(css = "input[name=login_password]")
	WebElement loginPassword;

	@FindBy(css = "button.signin-button")
	WebElement signinButton;

	public SignInPage(WebDriver driver) {
		super(driver);
	}

	public boolean isInitialized() {
		return loginEmail.isEnabled();
	}

	public MyAccountPage SignIn(String email, String Password) {
		loginEmail.clear();
		loginEmail.sendKeys(email);
		loginPassword.clear();
		loginPassword.sendKeys(Password);
		signinButton.click();
		return new MyAccountPage(driver);
	}

}

package com.dbx.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.page.obj.PageObject;

public class HomePage extends PageObject {

	@FindBy(how = How.CSS, using = "img.dropbox-logo__type")
	WebElement dbxLogo;

	@FindBy(how = How.ID, using = "sign-up-in")
	WebElement signIn;

	public HomePage(WebDriver driver) {
		super(driver);
	}

	public boolean dbxImgDisplayed() {
		return dbxLogo.isDisplayed();
	}

	public boolean pageInitilaized() {
		return signIn.isEnabled();
	}

	public SignInPage loginForm() {
		signIn.click();
		return new SignInPage(driver);
	}
}

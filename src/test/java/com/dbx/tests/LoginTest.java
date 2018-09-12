package com.dbx.tests;

import static org.testng.Assert.assertTrue;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.dbx.pages.HomePage;
import com.dbx.pages.MyAccountPage;
import com.dbx.pages.SignInPage;
import com.dbx.testhelper.TestHelper;
import com.relevantcodes.extentreports.LogStatus;

public class LoginTest extends TestHelper {
	@Test
	@Parameters({ "emailId", "password" })
	public void loginTest(String emailId, String password) {
		test = report.startTest("Test Login Functionality with given credentials");
		try {
			HomePage hp = new HomePage(driver);
			assertTrue(hp.pageInitilaized());
			SignInPage sp = hp.loginForm();
			assertTrue(sp.isInitialized());
			MyAccountPage myacntp = sp.SignIn(emailId, password);
			assertTrue(myacntp.uploadEnabled());
			test.log(LogStatus.PASS,
					"Login Functionality with given credentials: " + emailId + " : " + password + " passed");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, e.getMessage());
		}
	}

	@Test
	@Parameters({ "email", "pass" })
	public void logoutTest(String emailId, String password) {
		test = report.startTest("Test LogOut Functionality for given credentials");
		try {
			HomePage hp = new HomePage(driver);
			assertTrue(hp.pageInitilaized());
			SignInPage sp = hp.loginForm();
			assertTrue(sp.isInitialized());
			MyAccountPage myacntp = sp.SignIn(emailId, password);
			assertTrue(myacntp.uploadEnabled());
			hp = myacntp.logOut();
			assertTrue(hp.dbxImgDisplayed());
			test.log(LogStatus.PASS,
					"Logout Functionality with given credentials: " + emailId + " : " + password + " passed");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, e.getMessage());
		}
	}
}

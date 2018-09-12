package com.dbx.tests;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.dbx.pages.DeletedFilesPage;
import com.dbx.pages.HomePage;
import com.dbx.pages.MyAccountPage;
import com.dbx.pages.MyFilesPage;
import com.dbx.pages.SignInPage;
import com.dbx.testhelper.TestHelper;
import com.relevantcodes.extentreports.LogStatus;

public class FileUploadTest extends TestHelper {
	@Test
	@Parameters({ "emailId", "password" })
	public void fileUploadTest(String emailId, String password) {
		test = report.startTest("File upload Test with given credentials");
		try {
			HomePage hp = new HomePage(driver);
			assertTrue(hp.pageInitilaized());
			SignInPage sp = hp.loginForm();
			assertTrue(sp.isInitialized());
			MyAccountPage myacntp = sp.SignIn(emailId, password);
			assertTrue(myacntp.uploadEnabled());
			myacntp.uploadFile();
			assertTrue(myacntp.isUploaded());
			assertTrue(myacntp.listItemAdded());
			test.log(LogStatus.PASS,
					"File upload Test with given credentials " + emailId + " : " + password + " passed");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, e.getMessage());
		}
	}

	@Test
	@Parameters({ "email", "pass" })
	public void deletedUploadFileTest(@Optional("nitin.ch170584+1@gmail.com") String emailId, @Optional("L0ppy501$") String password) {
		test = report.startTest("Delete File uploaded and check the deleted list Test with given credentials");
		try {
			HomePage hp = new HomePage(driver);
			assertTrue(hp.pageInitilaized());
			SignInPage sp = hp.loginForm();
			assertTrue(sp.isInitialized());
			MyAccountPage myacntp = sp.SignIn(emailId, password);
			assertTrue(myacntp.uploadEnabled());
			myacntp.uploadFile();
			assertTrue(myacntp.isUploaded());
			assertTrue(myacntp.listItemAdded());
			MyFilesPage myfilep = myacntp.gotoMyFiles();
			assertTrue(myfilep.isTableDisplayed());
			assertTrue(myfilep.listFileAdded());
			DeletedFilesPage deletedp = myfilep.deleteFile();
			assertTrue(deletedp.isTableDisplayed());
			assertTrue(deletedp.deletedFileAddedToList());
			test.log(LogStatus.PASS, "Delete File uploaded and check the deleted list Test with given credentials "
					+ emailId + " : " + password + " passed");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, e.getMessage());
		}
	}
}
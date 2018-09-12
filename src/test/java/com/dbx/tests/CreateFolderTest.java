package com.dbx.tests;

import static org.testng.Assert.assertTrue;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.dbx.pages.HomePage;
import com.dbx.pages.MyAccountPage;
import com.dbx.pages.SignInPage;
import com.dbx.testhelper.TestHelper;
import com.relevantcodes.extentreports.LogStatus;
import com.tests.utility.Constant;

public class CreateFolderTest extends TestHelper {
	private static String folderName = Constant.FOLDER_NAME;
	private static String emailIds = Constant.EMAIL_IDS;

	@Test
	@Parameters({ "emailId", "password" })
	public void createFolderTest(String emailId, String password) {
		test = report.startTest("Create and share the Folder Test with given user credentials");
		try {
			HomePage hp = new HomePage(driver);
			assertTrue(hp.pageInitilaized());
			SignInPage sp = hp.loginForm();
			assertTrue(sp.isInitialized());
			MyAccountPage myacntp = sp.SignIn(emailId, password);
			assertTrue(myacntp.uploadEnabled());
			myacntp.selectNewShareFolder();
			assertTrue(myacntp.folderCreationModalDialogInitialized());
			myacntp.createNewFolder(folderName, emailIds);
			assertTrue(myacntp.isFolderCreated(folderName));
			test.log(LogStatus.PASS, "Create and Share the Folder Test with given user credentials " + emailId + " : "
					+ password + " passed");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, e.getMessage());
		}
	}
}
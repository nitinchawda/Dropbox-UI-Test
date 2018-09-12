package com.dbx.pages;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import com.page.obj.PageObject;
import com.pages.utility.Constant;

public class MyAccountPage extends PageObject {

	public MyAccountPage(WebDriver driver) {
		super(driver);
	}

	private static Path filepath = Paths.get(Constant.FILE_PATH);
	private static String filename = filepath.getFileName().toString();
	
	@FindBy(css = "span.action-upload")
	WebElement uploadButton;

	@FindBy(css = "img[alt='Account photo']")
	WebElement acntPhoto;

	@FindBy(xpath = "//a[contains(text(),'Sign out')]")
	WebElement signOutButton;

	@FindBy(css = "button.action-upload-files")
	WebElement uploadFileButton;

	@FindBy(css = "div.mc-util-modal-body")
	WebElement modalDialogBody;

	@FindBy(css = "li[aria-label='Dropbox']")
	WebElement dbxLocation;

	@FindBy(css = "button.mc-button-primary")
	WebElement uploadBtn;

	@FindBy(css = "p.mc-snackbar-title")
	WebElement uploadStatus;

	@FindBy(css = "li.recents-item")
	List<WebElement> listOfItemsAdded;

	@FindBy(css = "button.action-new-shared-folder")
	WebElement sharedFolderBtn;

	@FindBy(css = "button.db-modal-x")
	WebElement modalDialogCloseBtn;

	@FindBy(css = "input[value='new-folder']")
	WebElement newFolderRadioBtn;

	@FindBy(css = "button.c-btn")
	WebElement modalDialogNextBtn;

	@FindBy(id = "unified-share-modal-title")
	WebElement sharedFolderInputField;

	@FindBy(id = "unified-share-modal-contacts-tokenizer")
	WebElement emailInputTextArea;

	@FindBy(css = "button.c-btn--primary")
	WebElement shareFolderBtn;

	@FindBy(css = "button.c-btn--secondary")
	WebElement sharePermissionDropBtn;

	@FindBy(xpath = "//a[contains(.,'Can view')]")
	WebElement canViewPermissionBtn;

	@FindBy(css = "span.uee-ProductNav-files")
	WebElement goToMyFilesPageLink;
	
	public boolean folderCreationModalDialogInitialized() {
		return modalDialogCloseBtn.isEnabled();
	}

	public boolean uploadEnabled() {
		return uploadButton.isEnabled();
	}

	public void selectNewShareFolder() {
		sharedFolderBtn.click();
	}

	public HomePage logOut() {
		acntPhoto.click();
		signOutButton.click();
		return new HomePage(driver);
	}

	public void uploadFile() {
		uploadButton.click();
		uploadFileButton.click();
		uploadmethod(filepath.toAbsolutePath().toString());
		dbxLocation.click();
		uploadBtn.click();
	}

	private static void uploadmethod(String filename) {
		try {
			setClipboardData(filename);
			Robot robot = new Robot();
			robot.delay(2500);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.delay(150);
			robot.keyRelease(KeyEvent.VK_ENTER);

		} catch (Exception exp) {
			exp.printStackTrace();
		}
	}

	private static void setClipboardData(String string) {
		StringSelection stringSelection = new StringSelection(string);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

	}

	public boolean isUploaded() {
		return wait.until(ExpectedConditions.textToBePresentInElement(uploadStatus, "Uploaded " + filename));
	}

	public boolean listItemAdded() {
		driver.navigate().refresh();
		return wait.until(ExpectedConditions.textToBePresentInElement(
				listOfItemsAdded.get(0).findElement(By.cssSelector("a.recents-item__heading")), filename));
	}

	public void createNewFolder(String folderName, String emailIds) {
		newFolderRadioBtn.click();
		modalDialogNextBtn.click();
		sharedFolderInputField.isEnabled();
		sharedFolderInputField.sendKeys(folderName);
		emailInputTextArea.sendKeys(emailIds);
		sharePermissionDropBtn.click();
		canViewPermissionBtn.click();
		shareFolderBtn.click();
	}
	
	public MyFilesPage gotoMyFiles() {
		goToMyFilesPageLink.click();
		return new MyFilesPage(driver);
	}
	
	public boolean isFolderCreated(String foldername) {
		return wait.until(ExpectedConditions.urlContains(foldername));
	}
}

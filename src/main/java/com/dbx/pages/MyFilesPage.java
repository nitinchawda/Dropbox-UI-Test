package com.dbx.pages;

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

public class MyFilesPage extends PageObject {

	public MyFilesPage(WebDriver driver) {
		super(driver);
	}

	private static Path filepath = Paths.get(Constant.FILE_PATH);
	private static String filename = filepath.getFileName().toString();

	@FindBy(css = "tr.mc-media-row")
	List<WebElement> listOfFileName;

	@FindBy(css = "table[role=table]")
	WebElement tableOfItems;

	@FindBy(css = "span.uee-FeatureNav-deletedFiles")
	WebElement deletedFilesLink;

	@FindBy(css = "button.action-delete")
	WebElement deleteButton;
	
	@FindBy(css = "button.mc-button-primary")
	WebElement deleteConfirm;
	
	@FindBy(css = "span[aria-label='More actions']")
	List<WebElement> moreActionsButton;
	
	public boolean isTableDisplayed() {
		return tableOfItems.isDisplayed();
	}

	public boolean listFileAdded() {
		return wait.until(ExpectedConditions.textToBePresentInElement(
				listOfFileName.get(0).findElement(By.cssSelector("div.brws-file-name-element")), filename));
	}

	public DeletedFilesPage deleteFile() {
		wait.until(ExpectedConditions.elementToBeClickable(moreActionsButton.get(0))).click();
		deleteButton.click();
		deleteConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(deletedFilesLink)).click();
		return new DeletedFilesPage(driver);
	}
}

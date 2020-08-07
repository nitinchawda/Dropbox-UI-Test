package com.dbx.pages;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.page.obj.PageObject;
import com.pages.utility.Constant;

public class DeletedFilesPage extends PageObject {

	public DeletedFilesPage(WebDriver driver) {
		super(driver);
	}

	private static Path filepath = Paths.get(Constant.FILE_PATH);
	private static String filename = filepath.getFileName().toString();

	@FindBy(how = How.CSS, using = "tr.mc-media-row")
	List<WebElement> listOfFileName;

	@FindBy(how = How.CSS, using = "table[role=table]")
	WebElement tableOfItems;

	public boolean isTableDisplayed() {
		return tableOfItems.isDisplayed();
	}

	public boolean deletedFileAddedToList() {
		driver.navigate().refresh();
		return wait.until(ExpectedConditions.textToBePresentInElement(
				listOfFileName.get(0).findElement(By.cssSelector("div.mc-media-cell-content")), filename));
	}
}

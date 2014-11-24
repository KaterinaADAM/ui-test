package org.exoplatform.selenium.platform.wiki.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.TestBase;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.wiki.BasicAction;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.util.Set;

/**
 *
 * @author lientm
 * @date: 1-July-2013
 */
public class Wiki_Attachment extends BasicAction {

	ManageAccount magAc;
	TestBase test;
	@BeforeMethod
	public void setUpBeforeTest(){
		getDriverAutoSave();
		driver.get(baseUrl);
		magAc = new ManageAccount(driver);
		magAc.signIn(DATA_USER1, DATA_PASS); 
		goToWiki();
	}

	@AfterMethod
	public void afterTest(){
		magAc.signOut();
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	/**CaseId: 68842 + 70032 + 70033 -> Upload file while adding/editing wiki page
	 * + download attachment + delete attachment (attachment are .doc & an image)
	 * Need to update qmetry for caseid 70033 - Delete Attachment
	 * Base on issue: https://jira.exoplatform.org/browse/WIKI-736: Version of attachment file will not updated after deleting
	 * PENDING on IE, because the issue FQA-2024
	 */
	@Test()
	public void test01_UploadDownloadDeleteAttachment(){
		String title = "Wiki_sniff_attachment_page_title_01";
		String content = "Wiki_sniff_attachment_page_content_01";
		String link = "Wiki_Sniff_Attachment_01.doc";
		String fs = File.separator;

		String newTitle = "Wiki_sniff_attachment_page_title_01_update";
		String newContent = "Wiki_sniff_attachment_page_content_01_update";
		String newLink = "Wiki_Sniff_Attachment_01.jpg";
		Set<String> existingHandles = driver.getWindowHandles();
		info("Add new wiki page having attachment");

		addBlankWikiPageHasAttachment(title, content, link);

		info("Edit wiki page having attachment");
		mouseOverAndClick(ELEMENT_EDIT_PAGE_LINK);
		addWikiPageSourceEditor(newTitle, newContent);
		attachFileInWiki("TestData" + File.separator + newLink, 2);
		click(ELEMENT_SAVE_BUTTON_ADD_PAGE);
		waitForAndGetElement(ELEMENT_ATTACHMENT_NUMBER.replace("${No}", "2"));
		info("parent handle is " + driver.getWindowHandle());

		click(ELEMENT_NODE_WIKI_PAGE.replace("{$node}", newTitle));
		info("Check download attachment successfully");
		click(ELEMENT_ATTACHMENT_ICON);
		clickByJavascript(By.linkText(link));
		if(System.getProperty("browser").equalsIgnoreCase("iexplorer")){

			//		    driver.findElement(By.partialLinkText("click here")).click();
			downloadFile(link);
		}
		clickByJavascript(By.linkText(newLink));
		String newhandle = FindNewWindowHandle(existingHandles,12000);
		info("new handle is " + newhandle);
		driver.switchTo().window(newhandle);
		WebElement eX = (WebElement) ((JavascriptExecutor)driver).executeScript("return document.getElementsByTagName('img')[0];");
		if (eX != null){
			info("Check to Show image" + eX.getAttribute("src"));
			assert eX.getAttribute("src").contains(newLink);
			
		}
		else
			info("Image is not shown");
		switchToParentWindow();

		assert checkFileExisted("TestOutput" + fs+ link);

		info("Delete attachment");
		deleteAnAttachment(link);
		deleteAnAttachment(newLink);
		//waitForAndGetElement(ELEMENT_ATTACHMENT_NUMBER.replace("${No}", "1"));
		deleteFile("TestOutput" + fs + link);
		deleteCurrentWikiPage();	
	}
}

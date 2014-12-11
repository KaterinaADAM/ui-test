package org.exoplatform.selenium.platform.ecms;

import static org.exoplatform.selenium.TestLogger.info;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.ManageLogInOut;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.ecms.SitesExplorer;


/**
 * 
 * By quynhpt
 * Date: 09/12/2014
 */
public class ActionBar extends SitesExplorer {

    ManageView mnView;
    ManageLogInOut mnAc;
    NavigationToolbar navTool;
    
	public ActionBar(WebDriver dr, String plfVersion) {
		super(dr, plfVersion);
		mnView = new ManageView(dr,this.plfVersion);
		mnAc = new ManageLogInOut(dr, this.plfVersion);
		navTool = new NavigationToolbar(dr, this.plfVersion);
	}

	// Show drives
	public final By ELEMENT_SHOW_DRIVES = By.xpath("//*[@data-original-title = 'Show Drives']");

	// upload
	public final By ELEMENT_MORE_LINK_WITHOUT_BLOCK = By.xpath("//*[@id='uiActionsBarContainer']//*[contains(text(), 'More')]");
	public final By ELEMENT_UPLOAD_LINK = By.id("MultiUploadInputFiles");
	public final By ELEMENT_UPLOAD_BUTTON = By.xpath("//a[@class='actionIcon' and contains(text(),'Upload')]");
	public final By ELEMENT_UPLOAD_PROGRESS_BAR = By.xpath(".//*[contains(@class,'progress progress-striped pull-right')]");
	//other actions
	public final By ELEMENT_NEW_CONTENT_LINK = By.xpath("//*[@class='actionIcon']//*[contains(@class,'uiIconEcmsAddDocument')]");
	public final By ELEMENT_DELETE_BUTTON = By.xpath(".//*[@id='JCRContextMenu']//i[@class='uiIconEcmsDelete']");
    public final By ELEMENT_DOWNLOAD_BUTTON=By.xpath(".//*[@id='ECMContextMenu']//*[@class='uiIconEcmsDownload']");
	//Vote form
	public final By ELEMENT_VOTE_LINK = By.xpath("//a[contains(text(), 'Vote')]");
	public final By ELEMENT_VOTE_POPUP = By.xpath(".//span[text()='Vote Document']");
	public final String ELEMENT_VOTE_RATE = "//*[@id='UIVoteForm']//*[@data-original-title='${rate}']";
	public final By ELEMENT_VOTE_COMPONENT = By.xpath("//*[@class='uiVote clearfix']");
	public final String ELEMENT_VOTE_RATING_INFOR = "//*[@class='voteRatingInfo']//span[text()='${rate}']";
	
	
	/**
	 * Open drive area
	 */
	public void showDrives() {
		Utils.pause(500);
		if (waitForAndGetElement(ELEMENT_SHOW_DRIVES, 3000, 0) != null) {
			click(ELEMENT_SHOW_DRIVES);
		} else {
			click(By.xpath("//*[@title = 'Show Drives']"));
		}
		Utils.pause(1000);
		//return new SitesExplorer(driver, this.plfVersion);
	}

	/**
	 * function vote for a document/uploaded file
	 * 
	 * @param rate
	 */
	public void voteDocument(int rate) {
		WebElement comment = waitForAndGetElement(ELEMENT_VOTE_LINK, 5000, 0);
		if (comment == null) {
			WebElement more = waitForAndGetElement(
					ELEMENT_MORE_LINK_WITHOUT_BLOCK, 5000, 0);
			if (more != null) {
				click(ELEMENT_MORE_LINK_WITHOUT_BLOCK);
			} else {
				info("Do not have add comment icon in action bar");
				return;
			}
		}
		click(ELEMENT_VOTE_LINK);
		waitForAndGetElement(ELEMENT_VOTE_POPUP,3000,0);
		Utils.pause(3000);

		HashMap<Integer, String> rateVote = new HashMap<Integer, String>();
		rateVote.put(1, "Poor");
		rateVote.put(2, "Below average");
		rateVote.put(3, "Average");
		rateVote.put(4, "Above average");
		rateVote.put(5, "Good");
        info("Vote for the document");
		click(ELEMENT_VOTE_RATE.replace("${rate}", rateVote.get(rate)));
		waitForAndGetElement(ELEMENT_VOTE_COMPONENT,3000,0);
		info("the vote is successfully");
	}

	/**
	 * Upload a file
	 * By QuynhPT
	 * @param link
	 * @param params
	 * @return
	 */
	public void uploadFile(String link, Object... params) {
		Boolean verify = (Boolean) (params.length > 0 ? params[0] : true);
		if (waitForAndGetElement(ELEMENT_UPLOAD_BUTTON, DEFAULT_TIMEOUT, 0) == null) {
			click(ELEMENT_MORE_LINK_WITHOUT_BLOCK);
		}
		((JavascriptExecutor) driver)
				.executeScript(
						"arguments[0].style.visibility = 'block'; arguments[0].style.height = '1px'; "
								+ "arguments[0].style.width = '1px'; arguments[0].style.opacity = 1",
						waitForAndGetElement(ELEMENT_UPLOAD_LINK,
								DEFAULT_TIMEOUT, 1, 2));
		
		Utils.pause(10000);
		driver.findElement(ELEMENT_UPLOAD_LINK).sendKeys(Utils.getAbsoluteFilePathFromFile(link));
		info("Upload file " + Utils.getAbsoluteFilePathFromFile(link));
		waitForElementNotPresent(ELEMENT_UPLOAD_PROGRESS_BAR,120000,0);
				  
		info("verify:"+verify);
		if (verify) {
			String links[] = link.split("/");
			int length = links.length;
			Utils.pause(2000);
			waitForAndGetElement(By.xpath("//*[contains(text(),'"
					+ links[length - 1] + "')]"));
		}

		info("Upload file successfully");
		Utils.pause(2000);
		//return new ActionBar(driver, this.plfVersion);
	}
	

	/**
	 * Delete a node at level 1
	 * 
	 * @param locator
	 * @param timeout
	 * @return
	 */
	public void deleteDocument(Object locator, int... timeout) {
		int iTimeout = timeout.length > 0 ? timeout[0] : DEFAULT_TIMEOUT;
		for (int repeat = 1;; repeat++) {
			if (repeat > ACTION_REPEAT) {
				Assert.fail("Cannot perform this action after " + ACTION_REPEAT
						+ " tries");
			}
			if (isElementPresent(ELEMENT_WEB_VIEW)) {
				click(ELEMENT_WEB_VIEW);
				Utils.pause(1000);
				if (isElementPresent(ELEMENT_FILE_EXPLORER))
					click(ELEMENT_FILE_EXPLORER);
			}
			rightClickOnElement(locator);
			if (waitForAndGetElement(ELEMENT_CONTEXT_MENU_DELETE_LINK, 10000, 0) != null) {
				click(ELEMENT_CONTEXT_MENU_DELETE_LINK);
				dialog.deleteInDialog();
				break;
			}
			info("Retry...[" + repeat + "]");

		}
		Utils.pause(1000);
		if (isElementPresent(locator)) {
			driver.navigate().refresh();
			Utils.pause(2000);
		}
		waitForElementNotPresent(locator, iTimeout);
		info(locator.toString() + " is deleted successfully");
		Utils.pause(1000);
		//return new SitesExplorer(driver, this.plfVersion);
	}

	/**
	 *  Go to add new content
	 * @return SitesExplorer page
	 */
	public void goToAddNewContent() {
		for (int repeat = 1;; repeat++) {
			if (repeat >= ACTION_REPEAT) {
				Assert.fail("Cannot perform the action after " + ACTION_REPEAT
						+ "tries");
			}
			WebElement newContent = waitForAndGetElement(
					ELEMENT_NEW_CONTENT_LINK, 10000, 0);
			if (newContent == null) {
				WebElement more = waitForAndGetElement(
						ELEMENT_MORE_LINK_WITHOUT_BLOCK, 10000, 0);
				if (more != null) {
					mouseOverAndClick(ELEMENT_MORE_LINK_WITHOUT_BLOCK);
					Utils.pause(1000);
				} else {
					info("There is not Add New content icon in action bar");
					break;
				}
			} else
				break;
			Utils.pause(WAIT_INTERVAL);
			info("retry...[" + repeat + "]");
		}
		click(ELEMENT_NEW_CONTENT_LINK);
		waitForElementNotPresent(ELEMENT_NEW_CONTENT_LINK, DEFAULT_TIMEOUT, 1);
		Utils.pause(2000);
		//return new SitesExplorer(driver, this.plfVersion);
	}
	/**
	 * Add an action to Action Bar
	 * Item: viewPermissions, addSymLink, addDocument,
	 * manageVersions, manageRelations, comment,
	 * exportNode, viewProperties, importNode, viewMetadatas
	 * @param item
	 * @param eItem
	 * @param params
	 */
	public void addActionBar(String item, By eItem, Object... params) {
		String view = (String) (params.length > 0 ? params[0] : "Web");
		String tab = (String) (params.length > 1 ? params[1] : "Authoring");
		WebElement element = waitForAndGetElement(eItem, 5000, 0);
		WebElement more = waitForAndGetElement(ELEMENT_MORE_LINK_WITHOUT_BLOCK,
				5000, 0);
		if (element != null) {
			info(item + " tab is already displayed --");
		} else if (more != null) {
			click(ELEMENT_MORE_LINK_WITHOUT_BLOCK);
			if (waitForAndGetElement(eItem, 5000, 0) != null) {
				info(item + " tab is already displayed --");
			} else {
				mnView.addMoreActionToAView(item, view, tab);
				mnAc.signOut();
				mnAc.signIn(DATA_USER1, DATA_PASS);
				navTool.goToSiteExplorer();
			}
		} else {
			mnView.addMoreActionToAView(item, view, tab);
			mnAc.signOut();
			mnAc.signIn(DATA_USER1, DATA_PASS);
			navTool.goToSiteExplorer();
		}
		driver.navigate().refresh();
		Utils.pause(2000);
	}
	/**
	 * Click on Delete button
	 */
	public void clickDeleteButton(){
		info("click on Delete button");
		WebElement el = (new WebDriverWait(driver, 30))
				  .until(ExpectedConditions.presenceOfElementLocated(ELEMENT_DELETE_BUTTON));
		el.click();
		dialog.deleteInDialog();
		Utils.pause(3000);
	}
}

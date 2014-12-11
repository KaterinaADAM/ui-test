package org.exoplatform.selenium.platform;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.Dialog;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.ecms.ActionBar;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ActivityStream extends PlatformBase {

	// Composer
	public final By ELEMENT_COMPOSER_INPUT_FILED = By.xpath(".//*[@id='DisplaycomposerInput']");
	public final By ELEMENT_COMPOSER_FILE_BUTTON = By.xpath(".//i[@class='uiIconSocUIDocActivityComposer uiIconSocLightGray']");
	public final By ELEMENT_COMPOSER_MENTION_BUTTON = By.xpath(".//i[@class='uiIconSocMention uiIconSocLightGray']");
	public final By ELEMENT_COMPOSER_LINK_BUTTON = By.xpath(".//i[@class='uiIconSocUILinkActivityComposer uiIconSocLightGray']");
	public final By ELEMENT_COMPOSER_INPUT_LINK_FIELD = By.xpath(".//*[@id='InputLink']");
	public final By ELEMENT_COMPOSER_ATTACH_LINK_BUTTON = By.xpath(".//*[@id='AttachButton']");
	public final By ELEMENT_COMPOSER_SHARE_BUTTON = By.xpath(".//*[@id='ShareButton']");
	public final By ELEMENT_ACTIVITY_WHAT_ARE_YOU_WORKING_LABEL = By.xpath("//div[@id='DisplaycomposerInput']/../div[@class='placeholder']");
	public final String ELEMENT_ACTIVITY_AUTHOR_ACTIVITY = "//*[contains(text(), '${activityText}')]/../../../../..//*[@class='author']";
	
	//Upload popup
	public final By ELEMENT_SELECT_FILE_POPUP = By.xpath("//span[text()='Select File']");
	public final By ELEMENT_ACTIVITY_LIST_DRIVES=By.xpath(".//*[@id='DriveTypeDropDown']/div[@class='btn dropdown-toggle']");
	public final String ELEMENT_DRIVER_CURRENT = "//div[@class='btn dropdown-toggle']/span[contains(text(),'${driveName}')]";
	public final By ELEMENT_CREATE_FOLDER_BUTTON = By.xpath("//i[@class='uiIconPlus uiIconLightGray']");
	public final By ELEMENT_CREATE_FOLDER_BUTTON_PLF41 = By.xpath("//i[@class='uiIconEcmsAddFolder uiIconEcmsLightGrey']");
	public final By ELEMENT_UPLOAD_FILE_FRAME_XPATH = By.xpath("//iframe[contains(@id,'uploadFrame')]");
	public final String ELEMENT_UPLOAD_FILE_NAME="//a[text()='${filename}']";
	public final By ELEMENT_SELECT_BUTTON = By.xpath(".//*[@id='UIDocActivitySelector']//button[1]");
	public final By ELEMENT_DRIVER_BOX = By.xpath("//div[@class='btn dropdown-toggle']");
	public final String ELEMENT_DRIVER_OPTION = "//a[@class='OptionItem' and contains(text(),'${driveName}')]";
	public final By ELEMENT_FILE_INPUT_DOC = By.xpath("//*[@class='inputDoc']");
	public final By ELEMENT_FILE_INPUT_DOC_PLF41 = By.xpath("//*[class='uiIcon16x16FileWord uiIcon16x16nt_file']");
	public final By ELEMENT_SELECT_FILE_POPUP_CLOSE= By.xpath(".//*[@class='uiIconClose pull-right']");
	public final String ELEMENT_ACTIVITY_UPLOAD_POPUP_NODE_NOE=".//*[@id='ListRecords']//a[@data-original-title='${nameNode}']";
	public final By ELEMENT_ACTIVITY_UPLOAD_POPUP_UPLOAD_BUTTON = By.xpath(".//input[@type='file']");
	public final String ELEMENT_ACTIVITY_UPLOAD_POPUP_SELECTED_FILE=".//*[@id='BreadcumbsContainer']//a[text()='${fileName}']";
	public final By ELEMENT_ACTIVITY_UPLOAD_POPUP_PROGRESS_UPLOAD = By.xpath(".//*[@id='UIDocumentSelector']//*[@class='pull-left percent']");
	
	//delete an activity
	public final String ELEMENT_ACTIVITY_DELETE = "//*[contains(text(),'${activityText}')]/../../../../..//a[contains(@id, 'DeleteActivityButton')]";
	public final By ELEMENT_MESSAGE_CONFIRM_DELETE_ACTIVITY = By.xpath("//*[text()='Are you sure you want to delete this activity?']");
	
	//Document preview activity
	public final String ELEMENT_ACTIVITY_DOCUMENT_IMAGE_TITLE = ".//*[@class='linkTitle'][@data-original-title='${title}']";
	public final String ELEMENT_ACTIVITY_WEBCONTENT_TITLE =".//a[@title='${title}']";
	public final String ELEMENT_ACTIVITY_AUDIO_VIDEO_TITLE=".//a[@href='${link}']";
	public final String ELEMENT_ACTIVITY_DOCUMENT_IMAGE_VIEW_LINK =".//*[contains(@data-original-title,'${nameFile}')]/../../../..//i[@class='uiIconWatch uiIconLightGray']";
	public final String ELEMENT_ACTIVITY_WEBCONTENT_VIEW_LINK=".//a[@title='${nameContent}']/../../../..//i[@class='uiIconWatch uiIconLightGray']";
	
	//Wiki activity
	public final String ELEMENT_WIKI_COMMENT_EDIT_TITLE = "//*[text()='${title}']/../../../..//*[@class='contentComment' and contains(text(), 'title has been updated to: ${title}')]";
	public final String ELEMENT_WIKI_COMMENT_EDIT_CONTENT = "//*[text()='${title}']/../../../..//*[@class='contentComment' and contains(text(), 'content has been edited')]";
	public final String ELEMENT_ACTIVITY_WIKI_TITLE = "//*[@class='linkTitle' and text()='${title}']";
	public final String ELEMENT_ACTIVITY_WIKI_CONTENT = "//*[@class='linkTitle' and text()='${title}']/../../..//*[@class='contentWiki theContent']/*[@class='text']";
	public final String ELEMENT_ACTIVITY_WIKI_VERSION = "//*[@class='linkTitle' and text()='${title}']/../..//*[@class = 'pull-right versionLabel' and contains(text(), 'Version: ${version}')]";
	public final String ELEMENT_ACTIVITY_MOVE_WIKI_PAGE = "//*[text()='${title}']/../../../..//*[@class='contentComment' and contains(text(), 'Page has been moved to: ${path}')]";

	//Comment box
	public final String ELEMENT_COMMENTBOX="//*[contains(text(),'${title}')]/../../../..//div[@class='replaceTextArea editable']";
	public final String ELEMENT_ICON_COMMENT = "//*[contains(text(),'${title}')]/../../../..//i[@class='uiIconComment uiIconLightGray']";
	public final String ELEMENT_COMMENT_BUTTON = "//*[contains(text(), '${activityText}')]/../../../..//button[contains(@id,'CommentButton')]";
	public final String ELEMENT_ACTIVITY_ADD_YOUR_COMMENTLABEL = "//*[contains(text(),'${activityText}')]/../../../..//*[contains(@id,'DisplayCommentTextarea')]/../div[@class='placeholder']";
	public final String ELEMENT_DELETE_COMMENT_BUTTON = "//*[contains(text(),'${activityText}')]/../../../..//div[@class='commentList']/div[contains(@id,'commentContainer')]//p[@class='contentComment'  and contains(text(),'${commentText}')]/../../a[contains(@id,'DeleteCommentButton')]";
	public final String ELEMENT_COMMENT_TEXT = "//*[contains(text(),'${activityText}')]/../../../..//div[@class='commentList']/div[contains(@id,'commentContainer')]//p[@class='contentComment'  and contains(text(),'${commentText}')]]";
	public final String ELEMENT_ACTIVITY_LIKE_ICON_BLUE = ".//*[@data-original-title='${nameFile}']/../../../..//i[@class='uiIconThumbUp uiIconBlue']";
	
	//Media
	public final String ELEMENT_MEDIA_ACTIVITY_PLAYER_FRAME_VIDEO=".//*[@data-original-title='${fileName}']/../..//video";
	public final String ELEMENT_MEDIA_ACTIVITY_PLAYER_FRAME_WINDOWPLAYER=".//*[@data-original-title='${fileName}']/../..//embed";
	public final String ELEMENT_MEDIA_ACTIVITY_PLAYER_FRAME_FLOWPLAYER= ".//*[@data-original-title='${fileName}']/../..//*[@class='PlayerContent']";
	public final String ELEMENT_MEDIA_ACTIVITY_PLAYER_FRAME_FLASHPLAYER=".//*[@data-original-title='${fileName}']/../..//embed";
	public final String ELEMENT_MEDIA_ACTIVITY_PLAYER_FRAME_EXTERNAL_EMBEDDED_VIDEO=".//*[@href='${link}']/../..//iframe";
	public final String ELEMENT_MEDIA_EXTERNAL_EMBEDDED_VIDEO= ".//*[@href='${link}']";
	ActionBar actBar;
	//private final WebDriver driver;
	public ActivityStream(WebDriver dr,String...plfVersion){
		this.driver = dr;
		this.plfVersion = plfVersion.length>0?plfVersion[0]:"4.0";
		this.button= new Button(driver);
		this.dialog = new Dialog(driver);
		actBar = new ActionBar(driver,this.plfVersion);
	}
	/**
	 * Open Preview mode
	 * @param nameDocument
	 * @param isDocumentOrImage
	 */
	public void openPreviewMode(String nameDocument, boolean isDocumentOrImage){
		info("Open Preview mode of a document");
		if(isDocumentOrImage){
			info("this is a document or a image");
			waitElementAndTryGetElement(ELEMENT_ACTIVITY_DOCUMENT_IMAGE_VIEW_LINK.replace("${nameFile}", nameDocument));
		    click(ELEMENT_ACTIVITY_DOCUMENT_IMAGE_VIEW_LINK.replace("${nameFile}", nameDocument));
		}else{
			info("this is a content");
			waitElementAndTryGetElement(ELEMENT_ACTIVITY_WEBCONTENT_VIEW_LINK.replace("${nameContent}", nameDocument));
		    click(ELEMENT_ACTIVITY_WEBCONTENT_VIEW_LINK.replace("${nameContent}", nameDocument));
		}
		Utils.pause(2000);
		//return new DocumentPreview(this.driver, this.plfVersion);
	}
	/**
	 * Open Preview mode of a media file
	 * @param nameMedia
	 * @return
	 */
	public void openPreviewMode(String nameMedia,Object locator){
		info("Open Preview mode of a document");
		waitForAndGetElement(locator);
		click(ELEMENT_ACTIVITY_DOCUMENT_IMAGE_VIEW_LINK.replace("${nameFile}",nameMedia));
		Utils.pause(3000);
		//return new DocumentPreview(driver, this.plfVersion);
	}
		
	/**
	 * Check size of activity player frame
	 * @param nameDocument
	 * @param isDocumentOrImage
	 * @return ActivityStream page
	 */
	public void checkSizeActivityPlayer(String locator) {
		info("Open Preview mode of a document");
		Dimension size = waitForAndGetElement(locator,3000,0).getSize();
		int wd = size.width;
		int ht = size.height;
		if(wd==330 & ht ==200){
			assert true;
			info("Size of Activity Player frame is correct.");
		}else assert false:"Size of Activity Player frame is incorrect:"+wd +"/"+ht;
		//return new ActivityStream(driver, this.plfVersion);
	}

	/**
	 * Delete an activity on the stream
	 * @param activityText
	 * @param verify
	 */
	public void deleteActivity (String activityText,boolean...verify) {
		info("-- Deleting an activity " +activityText+" --");
		boolean canDelete = verify.length>0?verify[0]:true;
		boolean verifyElement = verify.length>1?verify[1]:true;
		if(!canDelete)
			waitForElementNotPresent(By.xpath(ELEMENT_ACTIVITY_DELETE.replace("${activityText}", activityText)), DEFAULT_TIMEOUT,1,2);
		else{
			WebElement elem = waitForAndGetElement(By.xpath(ELEMENT_ACTIVITY_DELETE.replace("${activityText}", activityText)), DEFAULT_TIMEOUT,1,2);

			String deleteActivityIconID;
			deleteActivityIconID = elem.getAttribute("id");

			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("document.getElementById('"+deleteActivityIconID+"').click();");
			waitForAndGetElement(ELEMENT_MESSAGE_CONFIRM_DELETE_ACTIVITY,3000,0);
			button.ok();
			if(verifyElement)
				waitForElementNotPresent(By.xpath(ELEMENT_ACTIVITY_DELETE.replace("${activityText}", activityText)), DEFAULT_TIMEOUT,1,2);
			
		}
		Utils.pause(3000);
		//return new ActivityStream(driver,this.plfVersion);
	}
	
	/**
	 * Add a new comment on activity stream
	 * @param activityText: input a text (String) 
	 * @param contentOfComment: input a comment (String)
	 */
	public void addComment(String filename, String textContent){
		WebElement input_icon= this.driver.findElement(By.xpath(ELEMENT_ICON_COMMENT.replace("${title}", filename)));
		input_icon.click();
		switchToParentWindow();
		
		WebElement input= this.driver.findElement(By.xpath(ELEMENT_COMMENTBOX.replace("${title}",filename)));
		Actions action =new Actions(driver);
		action.moveToElement(input).sendKeys(textContent).build().perform();
		
		
	    WebElement add_button= this.driver.findElement(By.xpath(ELEMENT_COMMENT_BUTTON.replace("${activityText}", filename)));
	    add_button.click();
	    Utils.pause(2000);
		//waitForAndGetElement(ELEMENT_COMMENT_TEXT.replace("${activityText}", filename).replace("${commentText}", textContent), DEFAULT_TIMEOUT,1,2);
       // return new ActivityStream(driver, this.plfVersion);
	}
	
	/**
	 * Add new activity for space 
	 * @param addText: boolean
	 * @param text: input a text (String)
	 * @param addLink: boolean
	 * @param link: input a link (String)
	 */
	public void addActivity (boolean addText, String text, boolean addLink, String link) {
		info("-- Adding an activity--");
		Utils.pause(3000);
		if (addText) 
		{
			info("----Add text into activity text box-----");
			WebElement inputText = waitForAndGetElement(ELEMENT_COMPOSER_INPUT_FILED,100000);
			WebElement shareButton = waitForAndGetElement(ELEMENT_COMPOSER_SHARE_BUTTON);
			WebElement workingLabel = waitForAndGetElement(ELEMENT_ACTIVITY_WHAT_ARE_YOU_WORKING_LABEL);
			((JavascriptExecutor)driver).executeScript("arguments[0].textContent = '';", workingLabel);
			((JavascriptExecutor)driver).executeScript("arguments[0].textContent = '"+text+"';", inputText);
			((JavascriptExecutor)driver).executeScript("arguments[0].disabled = false;", shareButton);
			((JavascriptExecutor)driver).executeScript("arguments[0].className = 'pull-right btn btn-primary';", shareButton);
		}
		if (addLink)
		{
			info("----Click on Link----");
			waitForAndGetElement( ELEMENT_COMPOSER_LINK_BUTTON,3000,0).click();
			info("----Input link into link box-----");
			waitForAndGetElement(ELEMENT_COMPOSER_INPUT_LINK_FIELD,3000,0);
			type(ELEMENT_COMPOSER_INPUT_LINK_FIELD, link, true);
			waitForAndGetElement(ELEMENT_COMPOSER_ATTACH_LINK_BUTTON,3000,0);
			info("----Click attach button-----");
			click(ELEMENT_COMPOSER_ATTACH_LINK_BUTTON);
			waitForAndGetElement(By.id("LinkTitle"),2000,0);
		}
		waitForAndGetElement(ELEMENT_COMPOSER_SHARE_BUTTON,2000,0);
		info("----Click share button----");
		click(ELEMENT_COMPOSER_SHARE_BUTTON);
		Utils.pause(1000);
		info("-- Verify that an activity has been added --");
		if (addText) {
			waitForAndGetElement(By.xpath(ELEMENT_ACTIVITY_AUTHOR_ACTIVITY.replace("${activityText}", text)),3000,0);
		}
		//return new ActivityStream(driver, this.plfVersion);
	}
	
	/**
	 * Add an activity stream with a text and a attached file
	 * by QuynhPT
	 * date 14/01/2015
	 * @param nameDrive
	 * @param pathFolder where put upload file
	 * @param pathData   where put Test Data folder
	 * @param nameFile
	 * @param addtext
	 * @param text
	 * @return ActivityStream page
	 */
	public void addActivity(String nameDrive,String pathFolder,String pathData,String nameFile,boolean addText,String text) {
		info("-- Adding an activity--");
		Utils.pause(3000);
		openUploadPopup(nameDrive,pathFolder);
		uploadFileFromAS(pathData,nameFile);
		Utils.pause(3000);
		info("click on Select button");
		click(ELEMENT_SELECT_BUTTON);
		Utils.pause(1000);
		info("add a text to composer box of AS");
		if(addText)
		addText(text);
		info("----Click share button----");
		waitForAndGetElement(ELEMENT_COMPOSER_SHARE_BUTTON,2000,0);
		click(ELEMENT_COMPOSER_SHARE_BUTTON);
		Utils.pause(2000);
		//return new ActivityStream(driver, this.plfVersion);
	}
	/**
	 * Share a file on AS without uploading file
	 * @param nameDrive
	 * @param pathFolder
	 * @param nameFile
	 * @param addText
	 * @param text
	 */
	public void shareFileFromAS(String nameDrive,String pathFolder,String nameFile,boolean addText,String text){
		info("-- Adding an activity--");
		Utils.pause(3000);
		openUploadPopup(nameDrive,pathFolder);
		Utils.pause(3000);
		waitForAndGetElement(By.linkText(nameFile),2000,0).click();
		info("click on Select button");
		click(ELEMENT_SELECT_BUTTON);
		Utils.pause(1000);
		info("add a text to composer box of AS");
		if(addText)
		addText(text);
		info("----Click share button----");
		waitForAndGetElement(ELEMENT_COMPOSER_SHARE_BUTTON,2000,0);
		click(ELEMENT_COMPOSER_SHARE_BUTTON);
		Utils.pause(2000);
	}
	/**
	 * By QuynhPT
	 * date 14/01/2015
	 * Open Upload Popup from Activity Stream
	 * @param nameDrive 
	 * @param path  where put the upload file
	 * @param return ActivityStream page
	 */
	public void openUploadPopup(String nameDrive,String path){
		info("----Click on file icon----");
		waitForAndGetElement(ELEMENT_COMPOSER_FILE_BUTTON,2000,0).click();
		info("----Verifyt that the popup is shown-----");
		waitForAndGetElement(ELEMENT_SELECT_FILE_POPUP,3000,0);
		info("----Open drives list menu-----");
		click(ELEMENT_ACTIVITY_LIST_DRIVES);
		info("----Select a drive-----");
		click(ELEMENT_DRIVER_OPTION.replace("${driveName}",nameDrive));
		info("----Go to path folder:"+path);
		String[] arrayPath = path.split("/");
		for(String arrayElement:arrayPath){
			click(ELEMENT_ACTIVITY_UPLOAD_POPUP_NODE_NOE.replace("${nameNode}", arrayElement));
			Utils.pause(2000);
		}
		//return new ActivityStream(driver, this.plfVersion);
	}
	/**
	 * Upload a file from Upload Popup
	 * By QuynhPT
	 * date 14/01/2015
	 * @param path     where put TestData folder
	 * @param nameFile
	 * @return ActivityStream page
	 */
	public void uploadFileFromAS(String path,String nameFile){
		info("-- Upload file --");
		WebElement frame = waitForAndGetElement(ELEMENT_UPLOAD_FILE_FRAME_XPATH,3000,0);
		driver.switchTo().frame(frame);
		Utils.pause(2000);
		((JavascriptExecutor)driver).executeScript("document.getElementsByTagName('input')[0].style.display = 'block';");
		Utils.pause(2000);
		driver.findElement(ELEMENT_ACTIVITY_UPLOAD_POPUP_UPLOAD_BUTTON).sendKeys(Utils.getAbsoluteFilePathFromFile(path+nameFile));
		Utils.pause(1000);
		waitForElementNotPresent(ELEMENT_ACTIVITY_UPLOAD_POPUP_PROGRESS_UPLOAD);
		switchToParentWindow();
		waitForAndGetElement(By.linkText(nameFile),2000,0).click();
		/*info("Close the popup");
		click(ELEMENT_SELECT_FILE_POPUP_CLOSE);*/
		//waitForAndGetElement(By.linkText(nameFile));
		Utils.pause(2000);
		info("Upload finished");
		//return new ActivityStream(driver, this.plfVersion);
	}
	/**
	 * Add a Text to Composer box of AS
	 * By QuynhPT
	 * Date 14/01/2015
	 * @param contentText
	 * @return Activity Stream page
	 */
	public void addText(String contentText) {
		info("----Add text into activity text box-----");
		WebElement inputText = waitForAndGetElement(ELEMENT_COMPOSER_INPUT_FILED, 100000);
		WebElement shareButton = waitForAndGetElement(ELEMENT_COMPOSER_SHARE_BUTTON,2000,0);
		WebElement workingLabel = waitForAndGetElement(ELEMENT_ACTIVITY_WHAT_ARE_YOU_WORKING_LABEL,2000,0);
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].textContent = '';", workingLabel);
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].textContent = '" + contentText + "';", inputText);
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].disabled = false;", shareButton);
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].className = 'pull-right btn btn-primary';",
				shareButton);
		Utils.pause(1000);
		//return new ActivityStream(driver, this.plfVersion);
	}

}

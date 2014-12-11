package org.exoplatform.selenium.platform.ecms.acceptance;

import static org.exoplatform.selenium.TestLogger.info;


import org.exoplatform.selenium.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;


public class Document_Preview_Comment extends Document_Preview_TestConfig {
	
	/**
	 * Case_ID:114198
	 * Case_name: Check Document name and Type icon on the Comments area from the activity stream
	 * Preconditions: 
	 * A document(PDF, Office files or Web content) or a Media is on the activity stream
	 * Steps:
	 * 1. A document(PDF, Office files or Web content) or a Media is on the activity stream
	 * 2. Click on the document from the activity
	 * Expected:
	 * - The display area is displayed
	 * - The Comments area is displayed beside the display area
	 * - The Document name and the icon type are displayed at the top of the comments box.
	 */
	@Test
	public void test01_CheckDocumentNameAndTypeIconOfCommentAreaFromAS(){
		info("Declare variables");
		//get a pdf file
		fileToUpload = attachFile.getAttachFileByArrayTypeRandom(1);
		path=siteExPath.getSiteExpPathByIndex(0);
		nameDrive=siteExDrive.getSiteExpDriveByIndex(0);

		shareFileAndOpenPreviewMode(nameDrive,path,fileToUpload);
		info("Verify that comment area is displayed beside display area");
		waitForAndGetElement(previewMode.ELEMENT_COMMENT_AREA,3000,0);
		info("Verify that icon type is displayed at the top of comment box");
		waitForAndGetElement(previewMode.ELEMENT_COMMENT_DOCUMENT_ICON,3000,0);
		info("Verify that document name is shown at top of comment box");
		waitForAndGetElement(previewMode.ELEMENT_COMMENT_DOCUMENT_NAME,3000,0).getText().contains(fileToUpload);

		info("Close The preview mode");
		previewMode.backASByClickCrossIcon();

		//deleteAllDataTest(); //delete all data test after finished testing
	}

	/**
	 * Case_ID:114201
	 * Case_name: Check the height of the Comments area from the activity stream
	 * Preconditions: 
	 * A document(PDF, Office files or Web content) or a Media is on the activity stream
	 * Steps:
	 * 1. A document(PDF, Office files or Web content) or a Media is on the activity stream
	 * 2. Click on the document from the activity
	 * Expected:
	 * - The display area is displayed
	 * - The Comments area is displayed 
	 * - The height of the comment area is equal to the height of the reader.
	 */
	@Test
	public void test02_CheckHeightOfCommentAreaFromAS(){
		info("Declare variables");
		//get a pdf file
		fileToUpload = attachFile.getAttachFileByArrayTypeRandom(1);
		path=siteExPath.getSiteExpPathByIndex(0);
		nameDrive=siteExDrive.getSiteExpDriveByIndex(0);

		shareFileAndOpenPreviewMode(nameDrive,path,fileToUpload);

		info("Get height of Header bar");
		int h_header= waitForAndGetElement(previewMode.ELEMENT_COMMENT_DOCUMENT_NAME).getSize().height;
		info("h_header:"+h_header);
		info("Get heigh of Comment box");
		int h_comment = waitForAndGetElement(previewMode.ELEMENT_COMMENT_BOX).getSize().height;
		info("h_comment :"+h_comment );
		info("Verify that two these sizes are equal");
		verifyTwoValues(h_header,h_comment);

		info("Close The preview mode");
		previewMode.backASByClickCrossIcon();
		//deleteAllDataTest(); //delete all data test after finished testing
	}

	/**
	 * Case_ID:114200
	 * Case_name: Check the width of the Comments area from the activity stream
	 * Preconditions: 
	 * A document(PDF, Office files or Web content) or a Media is on the activity stream
	 * Steps:
	 * 1. A document(PDF, Office files or Web content) or a Media is on the activity stream
	 * 2. Click on the document from the activity
	 * Expected:
	 * - The display area is displayed
	 * - The Comments area is displayed 
	 * - The width size is equal to 300px.
	 */
	@Test
	public void test03_CheckWidthOfCommentAreaFromAS(){
		info("Declare variables");
		//get a pdf file
		fileToUpload = attachFile.getAttachFileByArrayTypeRandom(1);
		path=siteExPath.getSiteExpPathByIndex(0);
		nameDrive=siteExDrive.getSiteExpDriveByIndex(0);

		shareFileAndOpenPreviewMode(nameDrive,path,fileToUpload);

		info("Get height of Header bar");
		int h_header= waitForAndGetElement(previewMode.ELEMENT_COMMENT_DOCUMENT_NAME).getSize().width;
		info("Verify that two these sizes are equal to 300px:"+h_header);
		verifyTwoValues(h_header,300);

		info("Close The preview mode");
		previewMode.backASByClickCrossIcon();
		//deleteAllDataTest(); //delete all data test after finished testing
	}

	/**
	 * Case_ID:114203
	 * Case_name: Check the comment text field on the Comments area from the activity stream
	 * Preconditions: 
	 * A document(PDF, Office files or Web content) or a Media is on the activity stream
	 * Steps:
	 * 1. A document(PDF, Office files or Web content) or a Media is on the activity stream
	 * 2. Click on the document from the activity
	 * Expected:
	 * - The display area is displayed
	 * - The Comments area is displayed 
	 * - A comment text field is displayed
	 * - A The hint in the text field is displayed: Add your comment...
	 */
	@Test
	public void test04_CheckTextFieldOfCommentAreaFromAS(){
		info("Declare variables");
		//get a pdf file
		fileToUpload = attachFile.getAttachFileByArrayTypeRandom(1);
		path=siteExPath.getSiteExpPathByIndex(0);
		nameDrive=siteExDrive.getSiteExpDriveByIndex(0);

		shareFileAndOpenPreviewMode(nameDrive,path,fileToUpload);

		info("Verify that a hint text is displayed in the field");
		waitForAndGetElement(previewMode.ELEMENT_COMMENT_INPUT_PLACEHOLDER,3000,0);

		info("Close The preview mode");
		previewMode.backASByClickCrossIcon();
		//deleteAllDataTest(); //delete all data test after finished testing
	}

	/**
	 * Case_ID:116534
	 * Case_name: Check display a comment on Comment area if adding the comment on Intranet activity stream
	 * Preconditions:
	 * A document (PDF, Office files, Web content) or a Media  is shared or uploaded  on the activity stream
	 * Steps:
	 * 1. Connect to Intranet Homepage
	 * 2. Add a comment to document
	 * 3. Click on file's name or View link of the content
	 * 4. Check the comment on Comment area
	 * Expected:
	 * 4.The comment at step 2 also is shown in the Comment Area the name of the document.
	 * @throws Exception 
	 */
	@Test
	public void test05_CheckDisplayACommentOnCommentAreaIfAddingTheCommentOnAS() throws Exception{
		info("Declare variables");
		//get a pdf file
		fileToUpload = attachFile.getAttachFileByArrayTypeRandom(1);
		path=siteExPath.getSiteExpPathByIndex(0);
		nameDrive=siteExDrive.getSiteExpDriveByIndex(0);
		String textContent=txData.getContentByArrayTypeRandom(3);

		info("Go to Sites Explorer");
		navToolbar.goToSiteExplorer();
		info("Go to Collaboration drive");
		actBar.showDrives();
		siteExp.openADrive(nameDrive);

		info("sites/acme/document");
		siteExp.goToAFolder(path);
		driver.navigate().refresh();

		info("Upload a file");
		actBar.uploadFile(datTestPath+ fileToUpload);


		info("Go to Activity stream of Intranet Home page");
		hp.goToHomePage();

		info("Add a comment to the document");
		acStream.addComment(fileToUpload,textContent);

		info("Open Preview mode");		
		acStream.openPreviewMode(fileToUpload,true);

		info("Verify that the preview is shown");
		waitForAndGetElement(previewMode.ELEMENT_PREVIEW_MODE,3000,0);

		info("Verify that added comment is shown on Comment Area");
		waitForAndGetElement(previewMode.ELEMENT_COMMENT_CONTENT.replace("${text}",textContent),3000,0);
		info("Verify that added comment is shown on Comment Area is only one");
		int count = driver.findElements(By.xpath(previewMode.ELEMENT_COMMENT_CONTENT.replace("${text}",textContent))).size();

		info("Close The preview mode");
		previewMode.backASByClickCrossIcon();
		deleteAllDataTest(); //delete all data test after finished testing
		if(count==1){
			assert true;
			info("Added comment is shown on Comment Area is only one");
		}else assert false:"Added comment is shown on Comment Area isnot only one, it is:"+count;

	}
	/**
	 * Case_ID:114204
	 * Case_name: Check the post of a comment from the Comments area from Intranet activity stream
	 * Preconditions:
	 * A document (PDF, Office files, Web content) or a Media  is shared or uploaded  on the activity stream
	 * Steps:
	 * 1. Connect to Intranet Homepage
	 * 2. Click on file's name or View link of the content
	 * 3. Input a text in the comment text field
	 * 4. Click on "Enter"
	 * Expected:
	 * 4.The comment is added.
	 */
	@Test
	public void test06_CheckPostOfACommentToCommentAreaFromAS(){
		info("Declare variables");
		//get a pdf file
		fileToUpload = attachFile.getAttachFileByArrayTypeRandom(1);
		path=siteExPath.getSiteExpPathByIndex(0);
		nameDrive=siteExDrive.getSiteExpDriveByIndex(0);
		String textContent=txData.getContentByArrayTypeRandom(3);

		shareFileAndOpenPreviewMode(nameDrive,path,fileToUpload);

		info("Add a comment to Comment Area");
		previewMode.postAComment(textContent,1);
		waitForAndGetElement(previewMode.ELEMENT_COMMENT_CONTENT.replace("${text}",textContent),3000,0);
		info("Verify that added comment is shown on Comment Area is only one");
		int count = driver.findElements(By.xpath(previewMode.ELEMENT_COMMENT_CONTENT.replace("${text}",textContent))).size();

		info("Close The preview mode");
		previewMode.backASByClickCrossIcon();
		deleteAllDataTest(); //delete all data test after finished testing		
		if(count==1){
			assert true;
			info("Added comment is shown on Comment Area is only one");
		}else assert false:"Added comment is shown on Comment Area isnot only one, it is:"+count;
	}

	/**
	 * Case_ID:114205
	 * Case_name: Check the collapse and expand of the Comments area from the activity stream
	 * Preconditions:
	 * A document (PDF, Office files, Web content) or a Media  is shared or uploaded  on the activity stream
	 * Steps:
	 * 1. Connect to Intranet Homepage
	 * 2. Click on file's name or View link of the content
	 * ==>The display area is displayed.The Comments area is displayed.
	 * An arrow is displayed to collapse/expand the comment area.
	 * The comment area is expanded.
	 * 3. Click on the arrow
	 * ==>The comment area is collapsed
	 * 4. Click again on the arrow
	 * ==> The comment area is expanded. 
	 * The comment area can't be resized, this feature isn't available for the preview.
	 */
	@Test
	public void test07_CheckCollapseAndExpandOfCommentAreaFromAS(){
		info("Declare variables");
		//get a pdf file
		fileToUpload = attachFile.getAttachFileByArrayTypeRandom(1);
		path=siteExPath.getSiteExpPathByIndex(0);
		nameDrive=siteExDrive.getSiteExpDriveByIndex(0);

		shareFileAndOpenPreviewMode(nameDrive,path,fileToUpload);

		info("Verify that the comment area is shown");
		waitForAndGetElement(previewMode.ELEMENT_COMMENT_AREA,3000,0);
		int wd_comment_old = waitForAndGetElement(previewMode.ELEMENT_COMMENT_AREA,3000,0).getSize().width;
		info("Hide comment area");
		previewMode.hideCommentArea();
		info("Verify that the comment area is hided");
		waitForElementNotPresent(previewMode.ELEMENT_COMMENT_AREA);
		info("Show comment area");
		previewMode.showCommentArea();
		info("Verify that the comment area is shown again");
		waitForAndGetElement(previewMode.ELEMENT_COMMENT_AREA,3000,0);
		int wd_comment_new = waitForAndGetElement(previewMode.ELEMENT_COMMENT_AREA,3000,0).getSize().width;
		info("Close The preview mode");
		previewMode.backASByClickCrossIcon();
		deleteAllDataTest(); //delete all data test after finished testing
		if(wd_comment_new==wd_comment_old){
			assert true;
			info("The comment area isnot resized.");
		}else assert false:"The comment area is resized, it is old_size/new_size:"+wd_comment_old+"/"+wd_comment_new;
	}
	/**
	 * Case_ID:114207
	 * Case_name: Check the collapse of the comments area on the user session from the activity Stream
	 * Preconditions:
	 * A document (PDF, Office files, Web content) or a Media  is shared or uploaded  on the activity stream
	 * Steps:
	 * 1. Connect to Intranet Homepage
	 * 2. Click on file's name or View link of the content
	 * ==>The display area is displayed.The Comments area is displayed.
	 * An arrow is displayed to collapse/expand the comment area.
	 * The comment area is expanded.
	 * 3. Click on the arrow
	 * ==>The comment area is collapsed
	 * 4. Close the Preview. Open The preview again
	 * ==>The Preview  is displayed.The comment area is expanded.
	 * The collapse is not saved
	 */
	@Test
	public void test08_CheckCollapseOfCommentAreaOnUserSessionFromAS(){
		info("Declare variables");
		//get a pdf file
		fileToUpload = attachFile.getAttachFileByArrayTypeRandom(1);
		path=siteExPath.getSiteExpPathByIndex(0);
		nameDrive=siteExDrive.getSiteExpDriveByIndex(0);

		shareFileAndOpenPreviewMode(nameDrive,path,fileToUpload);

		info("Verify that the comment area is shown");
		waitForAndGetElement(previewMode.ELEMENT_COMMENT_AREA,3000,0);
		info("Hide comment area");
		previewMode.hideCommentArea();
		info("Verify that the comment area is hided");
		waitForElementNotPresent(previewMode.ELEMENT_COMMENT_AREA,3000,0);

		info("Close The preview mode");
		previewMode.backASByClickCrossIcon();

		info("Open Preview mode");		
		acStream.openPreviewMode(fileToUpload,true);
		info("Verify that the preview is shown");
		waitForAndGetElement(previewMode.ELEMENT_PREVIEW_MODE,3000,0);

		info("Verify that the comment area is shown");
		waitForAndGetElement(previewMode.ELEMENT_COMMENT_AREA,3000,0);

		info("Close The preview mode");
		previewMode.backASByClickCrossIcon();
		//deleteAllDataTest(); //delete all data test after finished testing
	}
	/**
	 * Case_ID:114208
	 * Case_name: Check the Comments area when no comments exist from Intranet activity stream
	 * Preconditions:
	 * A document (PDF, Office files, Web content) or a Media  is shared or uploaded  on the activity stream
	 * Steps:
	 * 1. Connect to Intranet Homepage
	 * 2. Click on file's name or View link of the content
	 * ==>The display area is displayed.
	 * The Comments area is displayed.
	 * Only the header and the text field are displayed.
	 * A label "No comment yet" and icon are displayed in Comment area
	 */
	@Test
	public void test09_CheckCommentAreaWhenNoCommentsExistedFromAS(){
		info("Declare variables");
		//get a pdf file
		fileToUpload = attachFile.getAttachFileByArrayTypeRandom(1);
		path=siteExPath.getSiteExpPathByIndex(0);
		nameDrive=siteExDrive.getSiteExpDriveByIndex(0);

		shareFileAndOpenPreviewMode(nameDrive,path,fileToUpload);

		info("Verify that the comment area is shown");
		waitForAndGetElement(previewMode.ELEMENT_COMMENT_AREA,3000,0);
		info("Verify that a text message is shown in comment area");
		waitForAndGetElement(previewMode.ELEMENT_COMMENT_EMPTY,3000,0);

		info("Close The preview mode");
		previewMode.backASByClickCrossIcon();
		//deleteAllDataTest(); //delete all data test after finished testing	
	}

	/**
	 * Case_ID:114206
	 * Case_name: Check the display area when the comments area is collapsed from Intranet activity stream
	 * Preconditions:
	 * A document (PDF, Office files, Web content) or a Media  is shared or uploaded  on the activity stream
	 * Steps:
	 * 1. Connect to Intranet Homepage
	 * 2. Click on file's name or View link of the content
	 * 3. Collapse the comment area
	 * ==>The comment area is collapsed.The display area occupy the full width.
	 * The margin is kept
	 */
	@Test
	public void test10_CheckDisplayAreaWhenTheCommentAreaIsCollapsedFromAS(){
		info("Declare variables");
		//get a pdf file
		fileToUpload = attachFile.getAttachFileByArrayTypeRandom(1);
		path=siteExPath.getSiteExpPathByIndex(0);
		nameDrive=siteExDrive.getSiteExpDriveByIndex(0);

		shareFileAndOpenPreviewMode(nameDrive,path,fileToUpload);

		info("Verify that the comment area is shown");
		waitForAndGetElement(previewMode.ELEMENT_COMMENT_AREA,3000,0);

		info("Hide the comment area ");
		previewMode.hideCommentArea();
		int wd_displayArea_new = waitForAndGetElement(previewMode.ELEMENT_PREVIEW_MODE_READER_AREA,3000,0).getSize().width;
		int wd_previewmode= waitForAndGetElement(previewMode.ELEMENT_PREVIEW_MODE,2000,0).getSize().width;
		int wd_displayArea_full = wd_previewmode-60;

		info("Close The preview mode");
		previewMode.backASByClickCrossIcon();
		deleteAllDataTest(); //delete all data test after finished testing
		info("Verify that The display area occupy the full width");
		if(wd_displayArea_new==wd_displayArea_full){
			assert true;
			info("The display area occupy the full width "+wd_displayArea_new+"/"+wd_displayArea_full);
		}else assert false:"The display area is not occupied the full width"+wd_displayArea_new+"/"+wd_displayArea_full;
	}
	/**
	 * Case_ID:114202
	 * Case_name: Check the scrollbar on the Comments area from the activity stream
	 * Preconditions:
	 * A document (PDF, Office files, Web content) or a Media  is shared or uploaded  on the activity stream
	 * Steps:
	 * 1. Connect to Intranet Homepage
	 * 2. Click on file's name or View link of the content
	 * 3. Check the scrollbar
	 * ==>A scrollbar is displayed 
	 */
	@Test
	public void test11_CheckScrollBarOfCommentsAreaFromAS(){
		info("Declare variables");
		//get a pdf file
		fileToUpload = attachFile.getAttachFileByArrayTypeRandom(1);
		path=siteExPath.getSiteExpPathByIndex(0);
		nameDrive=siteExDrive.getSiteExpDriveByIndex(0);
		String textContent=txData.getContentByArrayTypeRandom(3);

		shareFileAndOpenPreviewMode(nameDrive,path,fileToUpload);

		info("Add 10 comment to Comment area");
		previewMode.postAComment(textContent,6);
		info("Check the scroll bar is shown");
		boolean isScroll=checkExitScrollBar(previewMode.ELEMENT_COMMENT_LIST);

		info("Close The preview mode");
		previewMode.backASByClickCrossIcon();
		deleteAllDataTest(); //delete all data test after finished testing		
		if(isScroll==true){
			assert true;
			info("The scroll bar is shown");
		}else assert false:"The scroll bar is not shown";

	}

	@Test
	public void test12_CheckRemovingACommentFromCommentAreaFromAS(){
		info("Declare variables");
		//get a pdf file
		fileToUpload = attachFile.getAttachFileByArrayTypeRandom(1);
		path=siteExPath.getSiteExpPathByIndex(0);
		nameDrive=siteExDrive.getSiteExpDriveByIndex(0);
		String textContent=txData.getContentByArrayTypeRandom(3);

		shareFileAndOpenPreviewMode(nameDrive,path,fileToUpload);

		info("Add a comment to Comment Area");
		previewMode.postAComment(textContent,1);
		waitForAndGetElement(previewMode.ELEMENT_COMMENT_CONTENT.replace("${text}",textContent),2000,0);
		info("Delete comment");

		previewMode.deleteAComment();

		info("Close The preview mode");
		previewMode.backASByClickCrossIcon();
		//deleteAllDataTest(); //delete all data test after finished testing
	}

	/**
	 * Case_ID:117645
	 * Case_name:Check content of the top of comment area on Preview mode
	 * Preconditions:
	 * 1. Share a document (ex. pdf, offices file) or a media on Activity Stream
	 * Steps:
	 * 1. Open Preview mode
	 * 2. Check author's avatar
	 * ==>Avatar of author is shown at the top and beside author's name
	 * 3. Check author's name
	 * ==>The full name of author is shown on the top and beside author's avatar
	 * 4. Check date of the notification
	 * ==> Date of notification is shown on the top under the full name
	 * 5. Check Comment and Like icon
	 * ==>Comment and like icons are shown on the right -bottom 
	 */
	@Test
	public void test13_CheckContentOfTheTopOfCommentAreaOnPreviewMode(){
		info("Declare variables");
		//get a pdf file
		fileToUpload = attachFile.getAttachFileByArrayTypeRandom(1);
		path=siteExPath.getSiteExpPathByIndex(0);
		nameDrive=siteExDrive.getSiteExpDriveByIndex(0);
		pathSE = siteExPath.getSiteExpPathByIndex(4);
		nameDriveSE=siteExDrive.getSiteExpDriveByIndex(2);
		String textContent=txData.getContentByArrayTypeRandom(4);

		info("Go to Activity stream of Intranet Home page");
		hp.goToHomePage();
		info("Add a activity on AS");
		acStream.addActivity(nameDriveSE,pathSE,datTestPath,fileToUpload,true,textContent);

		info("Open Preview mode");		
		acStream.openPreviewMode(fileToUpload ,true);

		info("Verify that the preview is shown");
		waitForAndGetElement(previewMode.ELEMENT_PREVIEW_MODE,3000,0);
		Utils.pause(1000);

		//Open preview mode to check the content of the top of Comment Area
		//check author's avatar
		waitForAndGetElement(previewMode.ELEMENT_COMMENT_AREA_TOP_AVATAR,2000,0);
		//check full name
		waitForAndGetElement(previewMode.ELEMENT_COMMENT_AREA_TOP_FULL_NAME.replace("${fullName}","John Smith"),2000,0);
		//Check date of notification
		waitForAndGetElement(previewMode.ELEMENT_COMMENT_AREA_TOP_DATE_NOTIFICATION,2000,0);
		//check activity/title
		waitForAndGetElement(previewMode.ELEMENT_COMMENT_AREA_TOP_ACTIVITY_TILTE.replace("${contentTitle}",""),2000,0);
		//check Comment icon
		waitForAndGetElement(previewMode.ELEMENT_COMMENT_AREA_TOP_COMMENT_ICON,2000,0);
		//check like icon
		waitForAndGetElement(previewMode.ELEMENT_COMMENT_AREA_TOP_LIKE_ICON,2000,0);

		info("Close The preview mode");
		previewMode.backASByClickCrossIcon();
		//deleteAllDataTest(); //delete all data test after finished testing
	}
	/**
	 * Case_ID:117646
	 * Case_name: Check Comment icon of the top of Comment Area on Preview mode
	 * Preconditions: 
	 * 1. Share a document (ex. pdf, offices file) or a media on Activity Stream
	 * Steps:
	 * 1. Open preview mode
	 * 2. Click on Comment icon
	 * 3. Type a text and Press Enter
	 * Expected:
	 * 3.The focus will auto jump to Comment text box field.
	 * The text is added to Comment Area
	 */
	@Test
	public void test14_CheckCommentIconOfTheTopOfCommentAreaOnPreviewMode(){
		info("Declare variables");
		//get a pdf file
		fileToUpload = attachFile.getAttachFileByArrayTypeRandom(1);
		path=siteExPath.getSiteExpPathByIndex(0);
		nameDrive=siteExDrive.getSiteExpDriveByIndex(0);
		String textContent=txData.getContentByArrayTypeRandom(3);
		//share the file on AS
		shareFileAndOpenPreviewMode(nameDrive,path,fileToUpload);

		//Open preview mode to check comment icon
		//check Comment icon
		waitForAndGetElement(previewMode.ELEMENT_COMMENT_AREA_TOP_COMMENT_ICON,2000,0);
		//click on Comment icon
		click(previewMode.ELEMENT_COMMENT_AREA_TOP_COMMENT_ICON,2000,0);
		//check the focus into comment field after clicked on Comment icon
		WebElement el = driver.findElement(previewMode.ELEMENT_COMMENT_INPUT_FIELD);
		el.equals(driver.switchTo().activeElement());
		//input a text into the input field
		el.sendKeys(textContent);
		pressEnterKey();
		//Verify that a comment is added to Comment area
		waitForAndGetElement(previewMode.ELEMENT_COMMENT_CONTENT.replace("${text}", textContent),3000,0);

		info("Close The preview mode");
		previewMode.backASByClickCrossIcon();
		//deleteAllDataTest(); //delete all data test after finished testing
	}
	/**
	 * Case_ID: 117647
	 * Case_name: Check Like icon of the top of Comment Area on Preview Mode
	 * Preconditions:
	 * 1. Share a document (ex. pdf, offices file) or a media on Activity Stream
	 * Steps:
	 * 1. Open preview mode
	 * 2. click on Icon
	 * ==>The comment is liked. Like icon is change to blue color
	 * 3. Check like icon on AS
	 * ==>Like icon is also changed to blue color
	 */
	@Test
	public void test15_CheckLikeIconOfTheTopOfCommentAreaOnPreviewMode(){
		info("Declare variables");
		//get a pdf file
		fileToUpload = attachFile.getAttachFileByArrayTypeRandom(1);
		path=siteExPath.getSiteExpPathByIndex(0);
		nameDrive=siteExDrive.getSiteExpDriveByIndex(0);

		//share the file on AS
		shareFileAndOpenPreviewMode(nameDrive,path,fileToUpload);

		//Open preview mode to check comment icon
		//check Like icon
		waitForAndGetElement(previewMode.ELEMENT_COMMENT_AREA_TOP_LIKE_ICON,3000,0);
		//click on Like icon
		click(previewMode.ELEMENT_COMMENT_AREA_TOP_LIKE_ICON);
		Utils.pause(2000);
		//verify that like icon is changed to blue color
		waitForAndGetElement(previewMode.ELEMENT_COMMENT_AREA_TOP_LIKE_ICON_BLUE,3000,0);

		info("Close The preview mode");
		previewMode.backASByClickCrossIcon();
		Utils.pause(2000);
		//Verify that on AS, the like icon also is changed to blue color
		waitForAndGetElement(acStream.ELEMENT_ACTIVITY_LIKE_ICON_BLUE.replace("${nameFile}", fileToUpload),3000,0);
		//deleteAllDataTest(); //delete all data test after finished testing
	}
	/**
	 * Case_ID: 117648
	 * Case_name: Check limitation of the content of the top of Comment Area on Preview mode
	 * Steps:
	 * 1. Share a document with the activity/title that can fill at least 3 lines
	 * ==> the Activity is shared on AS
	 * 2. Click on View link or file name of the document
	 * ==>Preview mode is shown.
	 * The content of activity is shown full
	 * 3. Close Preview mode
	 * ==> Preview is closed
	 * 4. Share a document with activity/title that can fill over 3 lines
	 * ==>the activity is shared on AS
	 * 5. Click on View link or file name
	 * ==>Preview mode is shown.
	 * The content must be truncated by "...".
	 * Only text is shown. Any media is hidden
	 */
	/**
	 * Check with short content of Activity/title
	 */
	@Test
	public void test16_CheckLimitationOfShortContentOfTheTopOfCommentAreaOnPreviewMode(){
		fileToUpload = attachFile.getAttachFileByArrayTypeRandom(1);
		path=siteExPath.getSiteExpPathByIndex(0);
		nameDrive=siteExDrive.getSiteExpDriveByIndex(0);
		pathSE = siteExPath.getSiteExpPathByIndex(4);
		nameDriveSE=siteExDrive.getSiteExpDriveByIndex(2);
		String textContent=txData.getContentByArrayTypeRandom(4);

		info("Go to Activity stream of Intranet Home page");
		hp.goToHomePage();
		acStream.addActivity(nameDriveSE,pathSE,datTestPath,fileToUpload,true,textContent);

		info("Open Preview mode");		
		acStream.openPreviewMode(fileToUpload ,true);

		info("Verify that the preview is shown");
		waitForAndGetElement(previewMode.ELEMENT_PREVIEW_MODE,3000,0);
		Utils.pause(1000);
		waitForAndGetElement(previewMode.ELEMENT_COMMENT_AREA_TOP_ACTIVITY_TILTE.replace("${contentTitle}", textContent),3000,0);

		info("Close The preview mode");
		previewMode.backASByClickCrossIcon();
		//deleteAllDataTest();		
	}
	/**
	 * Check with long content of Activity/Title
	 */
	@Test
	public void test17_CheckLimitationOfLongContentOfTheTopOfCommentAreaOnPreviewMode(){
		fileToUpload = attachFile.getAttachFileByArrayTypeRandom(1);
		path=siteExPath.getSiteExpPathByIndex(0);
		nameDrive=siteExDrive.getSiteExpDriveByIndex(0);
		pathSE = siteExPath.getSiteExpPathByIndex(4);
		nameDriveSE=siteExDrive.getSiteExpDriveByIndex(2);
		String textContent=txData.getContentByArrayTypeRandom(5);
		String textTruncated=txData.getContentByArrayTypeRandom(6);

		info("Go to Activity stream of Intranet Home page");
		hp.goToHomePage();
		acStream.addActivity(nameDriveSE,pathSE,datTestPath,fileToUpload,true,textContent);

		info("Open Preview mode");		
		acStream.openPreviewMode(fileToUpload ,true);

		info("Verify that the preview is shown");
		waitForAndGetElement(previewMode.ELEMENT_PREVIEW_MODE,3000,0);
		Utils.pause(1000);
		waitForAndGetElement(previewMode.ELEMENT_COMMENT_AREA_TOP_ACTIVITY_TITLE_TRUNCATED.replace("${textTruncated}",textTruncated),3000,0);

		info("Close The preview mode");
		previewMode.backASByClickCrossIcon();
		//deleteAllDataTest();		
	}

	/**
	 * Delete all data test
	 * @param file
	 */
	private void deleteAllDataTest(){
		info("Delete data test");
		//Go to Site Explorer
		navToolbar.goToSiteExplorer();
		//Go to show all drives
		actBar.showDrives();
		//Go to a drive
		siteExp.openADrive(nameDrive);

		info("Go to a folder");
		siteExp.goToAFolder(path);
		driver.get(driver.getCurrentUrl());
		//Select Admin view type
		siteExp.clickAdminView();
		//Select All checkbox
		siteExp.selectAllFiles();
		//Click on Delete button
		actBar.clickDeleteButton();

	}
	/**
	 * Open Site Explorer
	 * Open Collaboration drive
	 * Upload a file in acme/document
	 * @param nameDrive
	 * @param path
	 * @param file
	 */
	private void shareFileAndOpenPreviewMode(String nameDrive, String path,
			String file, int... type) {
		if (type.length > 0) {
			info("Go to Sites Explorer");
			navToolbar.goToSiteExplorer();
			info("Go to Collaboration drive");
			actBar.showDrives();
			siteExp.openADrive(nameDrive);

			info("sites/acme/document");
			siteExp.goToAFolder(path);
			driver.navigate().refresh();

			info("Upload a file:" + datTestPath);
			actBar.uploadFile(datTestPath + file);

			info("Go to Activity stream of Intranet Home page");
			hp.goToHomePage();

		} else
			acStream.addActivity("General Drives",
					"Collaboration/sites/intranet/documents", datTestPath,
					file, true, "test" + getRandomNumber());

		info("Open Preview mode");
		acStream.openPreviewMode(file, true);

		info("Verify that the preview is shown");
		waitForAndGetElement(previewMode.ELEMENT_PREVIEW_MODE, 3000, 0);
		info("The preview is shown successfully");
	}
}

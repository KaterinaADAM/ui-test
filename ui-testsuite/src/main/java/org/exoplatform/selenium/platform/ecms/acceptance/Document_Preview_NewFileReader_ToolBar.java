package org.exoplatform.selenium.platform.ecms.acceptance;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;


public class Document_Preview_NewFileReader_ToolBar extends Document_Preview_TestConfig {
	
	/**
	 * Case_ID:114143
	 * Case_name: Check actions menu on the Reader from Intranet activity stream
	 * Preconditions: 
	 * Share a file (PDF, Office files or Web Content) with a supported format on the activity stream
	 * Steps:
	 * 1.Connect to Intranet/Space Homepage
	 * 2.Click on the file's name of the file or View link under the file
	 * Expected:
	 * The Preview is displayed. The new reader is displayed.
	 * A menu bar with following actions is displayed:
	 * + Toggle Sidebar
	 * + Search
	 * + Previous & Next Page
	 * + Current page (editable field) and total page info
	 * + Zoom in/out
	 * + Zoom options
	 * + Presentation mode(fullscreen)
	 * + Tools option
	 */
	@Test
	public void test04_CheckActionsMenuOfReaderFromAS(){
		info("Declare variables");
		//get a pdf file
		fileToUpload = attachFile.getAttachFileByArrayTypeRandom(1);
		path=siteExPath.getSiteExpPathByIndex(0);
		nameDrive=siteExDrive.getSiteExpDriveByIndex(0);
			
		shareFileAndOpenPreviewMode(nameDrive,path,fileToUpload);
		
		info("Verify that Actions menu is shown fully");
		previewMode.actionsMenuList();
		info("Actions menu is shown fully");
		
		info("Close The preview mode");
		previewMode.backASByClickCrossIcon();
		//deleteAllDataTest();
	}
	/**
	 * Case_ID: 114158
	 * Case_name: Check Zoom option drop box from the Preview form  from Intranet activity stream
	 * Preconditions: 
	 * Share a file (PDF, Office files or Web Content) with a supported format on the activity stream
	 * Steps:
	 * 1.Connect to Intranet Homepage
	 * 2.Click on the file's name of the file or View link under the file
	 * 3.Click on Zoom option drop-box
	 * Expected:
	 * The list of zoom options is displayed:
	 * + Automatic zoom
	 * + Actual size: 
	 * + Page Fit
	 * + Page Width
	 *    + 50%
	 *    + 75%
	 *    + 100%
	 *    + 125%
	 *    + 150%
	 *    + 200%
	 */
	@Test
	public void test02_CheckZoomOptionOfReaderFromAS(){
		info("Declare variables");
		//get a pdf file
		fileToUpload = attachFile.getAttachFileByArrayTypeRandom(1);
		path=siteExPath.getSiteExpPathByIndex(0);
		nameDrive=siteExDrive.getSiteExpDriveByIndex(0);
			
		shareFileAndOpenPreviewMode(nameDrive,path,fileToUpload);
		
		info("Verify that zoom option is shown fully");
		previewMode.checkValueOfZoomOption();
		info("Zoom option is shown fully");
		
		info("Close The preview mode");
		previewMode.backASByClickCrossIcon();
		//deleteAllDataTest();
	}
	
	/**
	 * Case_ID: 114144
	 * Case_name: Check Tools option from the menu of the Reader from Intranet activity stream
	 * Preconditions:
	 * Share a file(PDF, Office files or Web Content) with a supported format on the activity stream
	 * Steps:
	 * 1.Connect to Intranet Homepage
	 * 2.Click on the file's name of the file or View link under the file
	 * 3.Click on Tool option icon
	 * Expected:
	 * The Tools are displayed:
	 * + Download
	 * + Print
	 * + Go to first page
	 * + Go to last page
	 * + Rotate clockwise
	 * + Rotate counterclockwise
	 */
	@Test
	public void test03_CheckToolOptionOfReaderFromAS(){
		info("Declare variables");
		//get a pdf file
		fileToUpload = attachFile.getAttachFileByArrayTypeRandom(1);
		path=siteExPath.getSiteExpPathByIndex(0);
		nameDrive=siteExDrive.getSiteExpDriveByIndex(0);
			
		shareFileAndOpenPreviewMode(nameDrive,path,fileToUpload);
		
		info("Open Tool option menu");
		previewMode.clickToolOptionIcon();
		info("Verify that Tool option is shown fully");
		previewMode.checkValueOfToolOption();
		info("Tool option is shown fully");
		
		info("Close The preview mode");
		previewMode.backASByClickCrossIcon();
		//deleteAllDataTest();
	}
	
	/**
	 * Case_ID: 114145
	 * Case_name: Check Toggle Sidebar action from the menu of the Reader from Intranet activity stream
	 * Preconditions:
	 * Steps:
	 * 1. Connect to Intranet Homepage
	 * 2. Click on the file's name or View link
	 * 3. Click on the "Toggle Sidebar"
	 * 4. Click again on the "Toggle Sidebar"
	 * 5. Close the Preview
	 * Expected:
	 * 2. The Preview is displayed.The thumbnail is not displayed 
	 * 3. The Thumbnail is shown
	 * 4. The Thumbnail is hided
	 * 5. The Preview is closed. Show Intranet Home page
	 */
	@Test
	public void test10_CheckToogleSidebarFromMenuOfReaderFromAS(){
		info("Declare variables");
		//get a pdf file
		fileToUpload = attachFile.getAttachFileByArrayTypeRandom(1);
		path=siteExPath.getSiteExpPathByIndex(0);
		nameDrive=siteExDrive.getSiteExpDriveByIndex(0);
			
		shareFileAndOpenPreviewMode(nameDrive,path,fileToUpload);
		
		info("Open thumbnail panel");
		previewMode.clickToogleSideBar();
		info("Verify that thumbnal panel is shown");
		waitForAndGetElement(previewMode.ELEMENT_THUMBNAIL_VIEW,3000,0);
		info("thumbnal panel is shown successfully");
		info("Hide thumbnail panel");
		previewMode.clickToogleSideBar();
		info("Verify that thumbnal panel is hided");
		waitForElementNotPresent(previewMode.ELEMENT_THUMBNAIL_VIEW);
		info("thumbnal panel is hided successfully");
		
		info("Close the preview mode");
		previewMode.backASByClickCrossIcon();
		//deleteAllDataTest();
	}
	/**
	 * Case_ID:114155
	 * Case_name: Check page navigation box from the Preview form  from Intranet activity stream
	 * Preconditions:
	 * A.Share a file (PDF, Office files or Web Content) with a supported format on the activity stream
	 * B. The file contains many pages
	 * Steps:
	 * 1. Connect to Intranet Homepage
	 * 2. Click on the file's name or View link under the file
	 * Expected:
	 * 2. The Preview is displayed.The page navigation is displayed as Page: X of Y
	 * where:
	 * + X is the current viewed page
	 * + Y is the total number of pages
	 * 
	 * Case_ID:114156
	 * Case_name: Search a page by inputting number from the Preview form from Intranet activity stream
	 * Preconditions:
	 * A.Share a file (PDF, Office files or Web Content) with a supported format on the activity stream
	 * B. The file contains many pages
	 * Steps:
	 * 1. Connect to Intranet Homepage
	 * 2. Click on the file's name or View link under the file
	 * 3. Input a valid number into Page field
	 * 4. Click "Enter"
	 * Expected:
	 * 4. The page with correct input number is shown
	 * @throws Exception 
	 */
	@Test
	public void test05_06_CheckPageNavigationBoxOfPreviewFormFromAS() throws Exception{
		info("Declare variables");
		path=siteExPath.getSiteExpPathByIndex(0);
		nameDrive=siteExDrive.getSiteExpDriveByIndex(0);
		
		int index = textContentFile.getRandomIndexByType(9);
		info("index:"+index);
		fileToUpload = textContentFile.file.get(index);
		info("fileToUpload:"+fileToUpload);
		
		String totalPgNum = textContentFile.totalPgNum.get(index);
		info("totalPgNum:"+totalPgNum);
		String inputPgNum = textContentFile.inputPgNum.get(index);
		info("inputPgNum:"+inputPgNum);
		String textContent =textContentFile.textInputPgNum.get(index);
		info("textContent:"+textContent);
			
		shareFileAndOpenPreviewMode(nameDrive,path,fileToUpload);
				
		info("Verify that total page's number");
		previewMode.getTotalPageNumber().contains("of"+" "+totalPgNum);
		info("getTotalPageNumber() is: "+previewMode.getTotalPageNumber());
		
		info("Input a number into number page field");
		previewMode.inputNumPage(inputPgNum);
		info("Verify that page's number is shown correctly");
		waitForAndGetElement(previewMode.ELEMENT_TEXT_CONTENT_DOCUMENT.replace("${number}",textContent),3000,0);
		
		info("Close The preview mode");
		previewMode.backASByClickCrossIcon();
		//deleteAllDataTest();
	}
	
	/**
	 * Case_ID: 114168
	 * Case_name: Check the option "Go to First Page" from the Preview mode  from Intranet activity stream
	 * Preconditions:
	 * Share a file(PDF, Office files or Web Content) with a supported format on the activity stream
	 * Steps:
	 * 1. Connect to Intranet Homepage
	 * 2. Click on the file's name or View link under the file
	 * 3. Click on the settings button
	 * 4. Choose the option "Go to First Page"
	 * Expected:
	 * 4. The first page of the file is displayed
	 * 
	 * Case_ID: 114169
	 * Case_name: Check the option "Go to Last Page" from the Preview mode  from Intranet activity stream
	 * Preconditions:
	 * Share a file(PDF, Office files or Web Content) with a supported format on the activity stream
	 * Steps:
	 * 1. Connect to Intranet Homepage
	 * 2. Click on the file's name or View link under the file
	 * 3. Click on the settings button
	 * 4. Choose the option "Go to Last Page"
	 * Expected:
	 * 4. The last page of the file is displayed
	 */
	@Test
	public void test07_08_CheckGoToFirstAndLastPageOfPreviewModeFromAS(){
		info("Declare variables");
		path=siteExPath.getSiteExpPathByIndex(0);
		nameDrive=siteExDrive.getSiteExpDriveByIndex(0);
		
		int index = textContentFile.getRandomIndexByType(9);
		info("index:"+index);
		fileToUpload = textContentFile.file.get(index);
		info("fileToUpload:"+fileToUpload);
		
		String textContentFirstPage = textContentFile.firstPg.get(index);
		info("textContentFirstPage:"+textContentFirstPage);
		String textContentLastPage = textContentFile.lastPg.get(index);
		info("textContentLastPage:"+textContentLastPage);
			
		shareFileAndOpenPreviewMode(nameDrive,path,fileToUpload);
		
		info("Open Tool option list");
		previewMode.clickToolOptionIcon();
		
		info("Go to first page");
		previewMode.selectAnOptionOfToolOption(previewMode.ELEMENT_TOOL_GO_TO_FIRST_PAGE);
		info("Verify that first page is shown");
		waitForAndGetElement(previewMode.ELEMENT_TEXT_CONTENT_DOCUMENT.replace("${number}", textContentFirstPage),3000,0);
		
		info("Go to last page");
		previewMode.selectAnOptionOfToolOption(previewMode.ELEMENT_TOOL_GO_TO_LAST_PAGE);
		info("Verify that last page is shown");
		waitForAndGetElement(previewMode.ELEMENT_TEXT_CONTENT_DOCUMENT.replace("${number}", textContentLastPage),3000,0);
		
		info("Close The preview mode");
		previewMode.backASByClickCrossIcon();
		//deleteAllDataTest();
	}
		
	/**
	 * Case_ID: 114166
	 * Case_name: Check the option rotate from the Preview mode  from Intranet activity stream
	 * Preconditions:
	 * Share a file(PDF, Office files or Web Content) with a supported format on the activity stream
	 * Steps:
	 * 1. Connect to Intranet Homepage
	 * 2. Click on the file's name or View link under the file
	 * 3. Click on the settings button
	 * 4. Choose the option "Rotate Clockwise"
	 * ==>The document is rotated in the clockwise side 
	 * 5. Click again on the settings button
	 * 6. Choose the option "Rotate Counterclockwise"
	 * ==> The document is rotated in the counterclockwise
	 * 7. Close the preview mode. Open Preivew mode again
	 * ==> The document back to the original view
	 */
	@Test
	public void test09_CheckRotateOptionOfPreviewModeFromAS(){
		info("Declare variables");
		//get a pdf file
		fileToUpload = attachFile.getAttachFileByArrayTypeRandom(1);
		path=siteExPath.getSiteExpPathByIndex(0);
		nameDrive=siteExDrive.getSiteExpDriveByIndex(0);
		
		shareFileAndOpenPreviewMode(nameDrive,path,fileToUpload);
		
		info("Open Tool option list");
		previewMode.clickToolOptionIcon();
		
		info("Select rotate clockwise option");
		previewMode.selectAnOptionOfToolOption(previewMode.ELEMENT_TOOL_ROTATE_CLOCKWISE);
		info("Verify that the page is rotated clockwise");
		waitForAndGetElement(previewMode.ELEMENT_TOOL_ROTATE_CLOCKWISE_ANGLE.replace("${angle}","90"),3000,0);
		
		info("Select rotate counterclockwise option");
		previewMode.selectAnOptionOfToolOption(previewMode.ELEMENT_TOOL_ROTATE_COUNTER_CLOCKWISE);
		info("Verify that the page is rotated counterclockwise");
		waitForElementNotPresent(previewMode.ELEMENT_TOOL_ROTATE_CLOCKWISE_ANGLE.replace("${angle}","90"),3000,0);
		
		info("Close The preview mode");
		previewMode.backASByClickCrossIcon();
		info("Open the preview mode again");
		acStream.openPreviewMode(fileToUpload,true);
		info("Verify that the rotation staus is not saved");
		waitForElementNotPresent(previewMode.ELEMENT_TOOL_ROTATE_CLOCKWISE_ANGLE.replace("${angle}","90"),3000,0);
		
		info("Close The preview mode");
		previewMode.backASByClickCrossIcon();
		//deleteAllDataTest();
	}
	/**
	 * Case_ID:114157
	 * Case_name: Check Zoom buttons from the Preview form from Intranet activity stream
	 * Preconditions:
	 * Share a file(PDF, Office files or Web Content) with a supported format on the activity stream
	 * Steps:
	 * 1. Connect to Intranet Homepage
	 * 2. Click on the file's name or View link under the file
	 * 3. Click on "+"
	 * ==>The zoom in is done
	 * 4. Click on "-"
	 * ==>The zoom out is done
	 * 5. Close the Preview
	 * ==> The preview is closed
	 * 6. Open the Preview of the file again
	 * ==> The Preview is shown. The content file is viewed by default.
	 * @throws Exception 
	 */
	@Test
	public void test13_CheckZoomButtonsOfPreviewModeFromAS() throws Exception{
		info("Declare variables");
		//get a pdf file
		fileToUpload = attachFile.getAttachFileByArrayTypeRandom(1);
		path=siteExPath.getSiteExpPathByIndex(0);
		nameDrive=siteExDrive.getSiteExpDriveByIndex(0);
			
		shareFileAndOpenPreviewMode(nameDrive,path,fileToUpload);
		
		info("Get width of page container when is default");
		String defaultpage="1";
		int widPageDefault = waitForAndGetElement(previewMode.ELEMENT_PAGE_CONTAINER.replace("${number}",defaultpage),3000,0).getSize().width;
		info("Get width of page container when is default:"+widPageDefault);
		info("Click on Zoom in");
		previewMode.clickZoomIn();
		int widPageAfterZoomIn = waitForAndGetElement(previewMode.ELEMENT_PAGE_CONTAINER.replace("${number}",defaultpage),3000,0).getSize().width;
		info("Click on Zoom in with width page:"+widPageAfterZoomIn);
		previewMode.clickZoomOption();
		previewMode.selectAnOptionOfZoomList(previewMode.ELEMENT_ZOOM_AUTOMATIC);
		
		info("Click on Zoom out");
		previewMode.clickZoomOut();
		int widPageAfterZoomOut = waitForAndGetElement(previewMode.ELEMENT_PAGE_CONTAINER.replace("${number}",defaultpage),3000,0).getSize().width;
		info("Click on Zoom out with width page:"+widPageAfterZoomOut);
		info("Close The preview mode");
		previewMode.backASByClickCrossIcon();
		
		
		info("Verify that the page is zoom in");
		previewMode.verifySizeZoomInOut(widPageDefault,widPageAfterZoomIn,widPageAfterZoomOut);
		//deleteAllDataTest();
	}
	/**
	 * Case_ID: 114153
	 * Case_name: Check pages by the arrow "Next/Previous" from the Preview form  from Intranet activity stream
	 * Preconditions:
	 * Share a file(PDF, Office files or Web Content) with a supported format on the activity stream
	 * The file contains many pages
	 * Steps:
	 * 1. Connect to Intranet Homepage
	 * 2. Click on the file's name or View link under the file
	 * 3. Click on the button "Next"
	 * ==>Next page is viewed.
	 * 4. Click on the button "previous"
	 * ==> Previous page is viewed.
	 * 5.  Next to the end page
	 * ==>Last page is viewed
	 * 6. Previous to the first page
	 * ==> first page is viewed. Cannot previous more
	 */
	@Test
	public void test11_CheckPagesByNextPreviousArrowsOfPreviewModeFromAS(){
		info("Declare variables");
		path=siteExPath.getSiteExpPathByIndex(0);
		nameDrive=siteExDrive.getSiteExpDriveByIndex(0);
		
		int index = textContentFile.getRandomIndexByType(9);
		info("index:"+index);
		fileToUpload = textContentFile.file.get(index);
		info("fileToUpload:"+fileToUpload);
		
		String textContentFirstPage = textContentFile.firstPg.get(index);
		info("textContentFirstPage:"+textContentFirstPage);
		String textContentLastPage = textContentFile.lastPg.get(index);
		info("textContentLastPage:"+textContentLastPage);
			
		shareFileAndOpenPreviewMode(nameDrive,path,fileToUpload);
		
		info("Click on next arrow");
		previewMode.clickNextPage();
		String val= waitForAndGetElement(previewMode.ELEMENT_CURRENT_PAGE_NUMBER,2000,0).getAttribute("value").toString();
		info("val is:"+val);
		waitForAndGetElement(previewMode.ELEMENT_TEXT_CONTENT_DOCUMENT.replace("${number}",val),2000,0);
		
		info("Click on previous arrow");
		previewMode.clickPreviousPage();
		String val1= waitForAndGetElement(previewMode.ELEMENT_CURRENT_PAGE_NUMBER,2000,0).getAttribute("value").toString();
		info("val1 is:"+val1);
		waitForAndGetElement(previewMode.ELEMENT_TEXT_CONTENT_DOCUMENT.replace("${number}",val1),2000,0);
		
		previewMode.clickToolOptionIcon();
		info("Go to first page");
		previewMode.selectAnOptionOfToolOption(previewMode.ELEMENT_TOOL_GO_TO_FIRST_PAGE);
		previewMode.clickPreviousPage();
		info("Verify that first page is shown");
		waitForAndGetElement(previewMode.ELEMENT_TEXT_CONTENT_DOCUMENT.replace("${number}", textContentFirstPage),2000,0);
		
		info("Go to last page");
		previewMode.selectAnOptionOfToolOption(previewMode.ELEMENT_TOOL_GO_TO_LAST_PAGE);
		previewMode.clickNextPage();
		info("Verify that last page is shown");
		waitForAndGetElement(previewMode.ELEMENT_TEXT_CONTENT_DOCUMENT.replace("${number}", textContentLastPage),2000,0);
		
		info("Close The preview mode");
		previewMode.backASByClickCrossIcon();
		//deleteAllDataTest();
	}
	/**
	 * Case_ID:114146
	 * Case_name:Check the Search panel from the menu of the Reader  from Intranet activity stream
	 * Preconditions:
	 * Share a file(PDF, Office files or Web Content) with a supported format on the activity stream
	 * Steps:
	 * 1. Connect to Intranet Homepage
	 * 2. Click on the file's name or View link under the file
	 * 3. Click on the "Search" icon
	 * Expected:
	 * The search panel is displayed as:
	 * + Search field
	 * + Next or previous search iteration
	 * + Highlight all check box
	 * + Match case check box
	 */
	@Test
	public void test12_CheckSearchPanelOfPreviewModeFromAS(){
		info("Declare variables");
		//get a pdf file
		fileToUpload = attachFile.getAttachFileByArrayTypeRandom(1);
		path=siteExPath.getSiteExpPathByIndex(0);
		nameDrive=siteExDrive.getSiteExpDriveByIndex(0);
			
		shareFileAndOpenPreviewMode(nameDrive,path,fileToUpload);
		
		info("Open Search panel");
		previewMode.clickSearchIcon();
		
		previewMode.verifySearchPanel();
		
		info("Close The preview mode");
		previewMode.backASByClickCrossIcon();
		//deleteAllDataTest();
	}
	
	/**
	 * Case_ID:114149
	 * Case_name: Check next/previous arrows from the search panel from Intranet activity stream
	 * Preconditions:
	 * A.Share a file(PDF, Office files or Web Content) with a supported format on the activity stream.
	 * Steps:
	 * 1. Connect to Intranet Homepage
	 * 2. Click on the file's name or View link under the file
	 * 3. Click on "Search" icon
	 * 4. Input an existent word in the search text field. Click "Enter".
	 * 5. Click on the arrow "Next"
	 * ==> The next iteration of the search result is displayed
	 * 6. Click on the arrow "Previous"
	 * ==> The previous iteration of the search is displayed
	 * 7. Next to last results page
	 * ==> Last page is shown. Show the message: "Reached end of document, continued from top"
	 * when continue to click on next arrow
	 * 8.  Previous to first results page
	 * ==> First page is shown. Show the message: "Reached top of document, continued from bottom"
	 * when continue to click on previous arrow
	 */
	@Test
	public void test01_CheckNextAndPreviousArrowsFromSearchPanelFromAS(){
		info("Declare variables");
		fileToUpload = attachFile.getAttachFileByArrayTypeRandom(22);
		path=siteExPath.getSiteExpPathByIndex(0);
		nameDrive=siteExDrive.getSiteExpDriveByIndex(0);
		String txtSearch = textSearch.getTextSearchByIndex(0);

		//Share a file on activity stream
		//Open Preview mode
		shareFileAndOpenPreviewMode(nameDrive,path,fileToUpload);
		
		//Verify that Search icon is shown
		waitForAndGetElement(previewMode.ELEMENT_SEARCH_ICON,2000,0);
		
		info("Open Search panel");
		previewMode.clickSearchIcon();
		info("Input a text and execute searching");
		previewMode.inputTextSearch(txtSearch);
		
	
		info("Get current number page before clicked on Next button");
		String currentPage=waitForAndGetElement(previewMode.ELEMENT_CURRENT_PAGE_NUMBER,2000,0).getAttribute("value").toString();
		info("currentPage:"+currentPage);
		
		info("Click on Next button of Search panel");
		previewMode.clickNextSearchPanel();
		
		info("Get next number page after clicked on Next button");
		String nextPage=waitForAndGetElement(previewMode.ELEMENT_CURRENT_PAGE_NUMBER,2000,0).getAttribute("value").toString();
		info("nextPage:"+nextPage);
		
		//Verify that next page successfully
		waitForAndGetElement(previewMode.ELEMENT_TEXT_CONTENT_DOCUMENT.replace("${number}",nextPage),3000,0);
		info("Verify that the text of the content in next page is highlighted");
		waitForAndGetElement(previewMode.ELEMENT_SEARCH_TEXT_HIGHLIGHT.replace("${text}",txtSearch),3000,0);
		info("Verify Page's number is correctly shown in input page's number field:"+nextPage);
		
		previewMode.verifyPageNumOfNextPage(currentPage, nextPage);
		
		info("Click on Previous button of Search panel");
		previewMode.clickPreviousSearchPanel();
		
		info("Get next number page after clicked on Next button");
		String previousPg=waitForAndGetElement(previewMode.ELEMENT_CURRENT_PAGE_NUMBER,2000,0).getAttribute("value").toString();
		info("previousPg:"+previousPg);
		
		// Verify that next page successfully
		waitForAndGetElement(previewMode.ELEMENT_TEXT_CONTENT_DOCUMENT.replace("${number}", previousPg),3000,0);
		info("Verify that the text of the content in next page is highlighted");
		waitForAndGetElement(previewMode.ELEMENT_SEARCH_TEXT_HIGHLIGHT.replace("${text}", txtSearch),3000,0);
		info("Verify Page's number is correctly shown in input page's number field:"+ previousPg);
		
		previewMode.verifyPageNumOfPrevPage(nextPage, previousPg);
		
		info("Close The preview mode");
		previewMode.backASByClickCrossIcon();
		//deleteAllDataTest();
	}
	
	@Test
	public void test16_CheckMessagesFromSearchPanelFromAS(){
		info("Declare variables");
		fileToUpload = attachFile.getAttachFileByArrayTypeRandom(27);
		path=siteExPath.getSiteExpPathByIndex(0);
		nameDrive=siteExDrive.getSiteExpDriveByIndex(0);
		String txtSearch = textSearch.getTextSearchByIndex(3);
		String mesg1=mesgSearch.getMesgSearchByIndex(0);
		String mesg2=mesgSearch.getMesgSearchByIndex(1);
		
		//Share a file on activity stream
		//Open Preview mode
		shareFileAndOpenPreviewMode(nameDrive,path,fileToUpload);
		//Verify that search icon is shown
		waitForAndGetElement(previewMode.ELEMENT_SEARCH_ICON,3000,0);
		Utils.pause(2000);
		
		info("Open Search panel");
		previewMode.clickSearchIcon();
		info("Input a text and execute searching");
		previewMode.inputTextSearch(txtSearch);

		
		info("Click on Previous button of Search panel");
		previewMode.clickPreviousSearchPanel();
		//Check message results when at the frist page 
		previewMode.checkMessageSearch(mesg1);
		
		info("Click on Next button of Search panel");
		previewMode.clickNextSearchPanel();
		previewMode.checkMessageSearch(mesg2);
		
		info("Close The preview mode");
		previewMode.backASByClickCrossIcon();
		//deleteAllDataTest();
	}
	
	
	/**
	 * Case_ID: 114150
	 * Case_name: Check the checkbox "Matches Case" from the search panel  from Intranet activity stream
	 * Preconditions:
	 * Share a file(PDF, Office files or Web Content) with a supported format on the activity stream
	 * Steps:
	 * 1. Connect to Intranet Homepage
	 * 2. Click on the file's name or View link under the file
	 * 3. Click on the "Search" icon
	 * 4. Input an existent word in the search text field. Tick on "Match case" check box.Click "Enter"
	 * Expected:
	 * Only the word "my words" is highlighted.The search is done on uppercase.
	 */
	@Test
	public void test14_CheckMatchesCaseFromSearchPanelFromAS(){
		info("Declare variables");
		//get a pdf file
		fileToUpload = attachFile.getAttachFileByArrayTypeRandom(1);
		path=siteExPath.getSiteExpPathByIndex(0);
		nameDrive=siteExDrive.getSiteExpDriveByIndex(0);
		
		String textWithUppercase = textSearch.getTextSearchByIndex(0);
		String textWithoutUppercase = textSearch.getTextSearchByIndex(1);
		String mesg=mesgSearch.getMesgSearchByIndex(2);
		
		shareFileAndOpenPreviewMode(nameDrive,path,fileToUpload);
		
		info("Open Search panel");
		previewMode.clickSearchIcon();
		info("textWithUppercase:"+textWithUppercase);
		previewMode.inputTextSearch(textWithUppercase);
	    waitForAndGetElement(previewMode.ELEMENT_SEARCH_TEXT_HIGHLIGHT.replace("${text}",textWithUppercase),3000,0);
		
	    WebElement el = driver.findElement(previewMode.ELEMENT_SEARCH_PANEL_INPUT_FIELD);
	    el.clear();
	  
	    info("textWithoutUppercase:"+textWithoutUppercase);
	    previewMode.inputTextSearch(textWithoutUppercase);
	    previewMode.tickMatchCaseCheckBox();
	    previewMode.checkMessageSearch(mesg);
	    
		info("Close The preview mode");
		previewMode.backASByClickCrossIcon();
		//deleteAllDataTest();
		
	}
	/**
	 * Case_ID: 114151
	 * Case_name: Hide the Search panel from the menu of the Reader  from Intranet activity stream
	 * Preconditions:
	 * Share a file(PDF, Office files or Web Content) with a supported format on the activity stream
	 * Steps:
	 * 1. Connect to Intranet Homepage
	 * 2. Click on the file's name or View link under the file
	 * 3. Click on the "Search" icon
	 * ==>Search panel is shown
	 * 4.  Click on the "Search" icon
	 * ==> Search panel is hided
	 */
	@Test
	public void test15_HideSearchPanelFromSearchPanelFromAS(){
		info("Declare variables");
		//get a pdf file
		fileToUpload = attachFile.getAttachFileByArrayTypeRandom(1);
		path=siteExPath.getSiteExpPathByIndex(0);
		nameDrive=siteExDrive.getSiteExpDriveByIndex(0);
		
		shareFileAndOpenPreviewMode(nameDrive,path,fileToUpload);
		
		info("Open Search panel");
		previewMode.clickSearchIcon();
	    waitForAndGetElement(previewMode.ELEMENT_SEARCH_PANEL_BAR,3000,0);
		
	    info("Hide Search panel");
		previewMode.clickSearchIcon();
	    waitForElementNotPresent(previewMode.ELEMENT_SEARCH_PANEL_BAR);
	    
		info("Close The preview mode");
		previewMode.backASByClickCrossIcon();
		//deleteAllDataTest();
		
	}
	
	/**
	 * Open Site Explorer
	 * Open Collaboration drive
	 * Upload a file in acme/document
	 * @param nameDrive
	 * @param path
	 * @param file
	 */
	private void shareFileAndOpenPreviewMode(String nameDrive, String path,String file, int... type) {
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
	
	/*private void deleteAllDataTest(){
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
		
	}*/
	/**
	 * Open Site Explorer
	 * Open Collaboration drive
	 * Upload a file in acme/document
	 * @param nameDrive
	 * @param path
	 * @param file
	 *//*
	private void shareFileAndOpenPreviewMode(String nameDrive,String path,String file){
		
		info("Go to Sites Explorer");
		navToolbar.goToSiteExplorer();
		info("Go to Collaboration drive");
		actBar.showDrives();
		siteExp.openADrive(nameDrive);

		info("sites/acme/document");
		siteExp.goToAFolder(path);
		driver.navigate().refresh();
					
		info("Upload a file");
		actBar.uploadFile(datTestPath + file);
				
		info("Go to Activity stream of Intranet Home page");
		hp.goToHomePage();
		
		info("Open Preview mode");		
		acStream.openPreviewMode(file,true);
		
	}*/
	
}

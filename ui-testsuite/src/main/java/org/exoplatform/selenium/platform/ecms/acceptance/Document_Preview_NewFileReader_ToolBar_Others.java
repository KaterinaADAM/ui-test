package org.exoplatform.selenium.platform.ecms.acceptance;

import static org.exoplatform.selenium.TestLogger.info;

import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;



public class Document_Preview_NewFileReader_ToolBar_Others extends Document_Preview_TestConfig {
	
	
	/**
	 * Case_ID: 114154
	 * Case_name: Check pages by scroll up/down from the Preview form  from Intranet activity stream
	 * Preconditions:
	 * Share a file(PDF, Office files or Web Content) with a supported format on the activity stream
	 * The file contains many pages
	 * Steps:
	 * 1. Connect to Intranet Homepage
	 * 2. Click on the file's name or View link under the file
	 * 3. Scroll down
	 * ==>Next page is viewed.
	 * 4. Scroll up
	 * ==> Previous page is viewed.
	 * 5. Scroll at the end page
	 * ==>Last page is viewed. Cannot scroll down
	 * 6. Scroll at the first page
	 * ==> first page is viewed. Cannot scroll up
	 */
	@Test
	public void test01_CheckPagesByScrollUpDownOfPreviewModeFromAS(){
		info("Declare variables");
		path=siteExPath.getSiteExpPathByIndex(0);
		nameDrive=siteExDrive.getSiteExpDriveByIndex(0);
		
		int index = textContentFile.getRandomIndexByType(10);
		info("index:"+index);
		
		fileToUpload = textContentFile.file.get(index);
		info("fileToUpload:"+fileToUpload);
	
		String totalPage= textContentFile.totalPgNum.get(index);
		shareFileAndOpenPreviewMode(nameDrive,path,fileToUpload);
		
		info("Verify that first page is shown");
		waitForAndGetElement(previewMode.ELEMENT_CURRENT_PAGE_NUMBER,2000,0).getAttribute("value").toString().equals("1");
		
		info("Scroll down to last page");
		previewMode.scrollDownPage(totalPage);
		
		info("Scroll down to first page");
		previewMode.scrollUpPage("1");
		
		info("Close The preview mode");
		previewMode.backASByClickCrossIcon();
		//deleteAllDataTest();
	}
	/**
	 * Case_ID:114147
	 * Case_name:Check message of a Search if no any results are found  from Intranet activity stream
	 * Preconditions:
	 * Share a file(PDF, Office files or Web Content) with a supported format on the activity stream
	 * Steps:
	 * 1. Connect to Intranet Homepage
	 * 2. Click on the file's name or View link under the file
	 * 3. Click on the "Search" icon
	 * 4. Input an non existent word in the search text field. Click "Enter"
	 * Expected:
	 * No result is found. The search panel displays: "Phrase not found".
	 */
	@Test
	public void test02_CheckMessageofASearchIfNoAnyResultsAreFoundFromAS(){
		info("Declare variables");
		//get a pdf file
		fileToUpload = attachFile.getAttachFileByArrayTypeRandom(1);
		path=siteExPath.getSiteExpPathByIndex(0);
		nameDrive=siteExDrive.getSiteExpDriveByIndex(0);
		
		String txtSearch = textSearch.getTextSearchByIndex(2);
		String mesg=mesgSearch.getMesgSearchByIndex(2);
				
		shareFileAndOpenPreviewMode(nameDrive,path,fileToUpload);
		
		info("Open Search panel");
		previewMode.clickSearchIcon();
		previewMode.inputTextSearch(txtSearch);
		previewMode.messageSearch(mesg);
		
		info("Close The preview mode");
		previewMode.backASByClickCrossIcon();
		
		//deleteAllDataTest();
	}
	
	/**
	 * Case_ID:114148
	 * Case_name: Check result displays when search from the menu of the Reader  from Intranet activity stream
	 * Preconditions:
	 * Share a file(PDF, Office files or Web Content) with a supported format on the activity stream
	 * Steps:
	 * 1. Connect to Intranet Homepage
	 * 2. Click on the file's name or View link under the file
	 * 3. Click on the "Search" icon
	 * 4. Input an existent word in the search text field. Click "Enter"
	 * Expected:
	 * Each searched word in the text content is highlighted.All references of the search are highlighted
	 */
	@Test
	public void test03_CheckResultDisplaysWhenSearchFromTheMenuOfReaderFromAS(){
		info("Declare variables");
		//get a pdf file
		fileToUpload = attachFile.getAttachFileByArrayTypeRandom(1);
		path=siteExPath.getSiteExpPathByIndex(0);
		nameDrive=siteExDrive.getSiteExpDriveByIndex(0);
		String txtSearch = textSearch.getTextSearchByIndex(0);
		
		shareFileAndOpenPreviewMode(nameDrive,path,fileToUpload);
		
		info("Open Search panel");
		previewMode.clickSearchIcon();
		previewMode.inputTextSearch(txtSearch);
	    waitForAndGetElement(previewMode.ELEMENT_SEARCH_TEXT_HIGHLIGHT.replace("${text}",txtSearch),3000,0);
		
		info("Close The preview mode");
		previewMode.backASByClickCrossIcon();
		
		//deleteAllDataTest();
		
	}
	/**
	 * Case_ID: 114121, 114119
	 * Case_name_1: Check the display a document in the display area if the document's content is smaller than on Intranet activity stream
	 * Case_name_2: Check the aspect ratio on the display area if the content is smaller than the view port on Intranet activity Stream
	 * Preconditions:
	 * A document (PDF, Office files) with a content that is smaller than the view port.
	 * The content is shared on the activity stream
	 * Steps:
	 * 1. Connect to Intranet Homepage
	 * 2. Click on the file's name or View link under the file
	 * Expected:
	 * The Display area is displayed with the content
	 * The content is centered vertically and horizontally in the display area.
	 * The heigh and the width are displayed proportionally
	 */
	@Test
	public void test04_05_DisplayDocumentInDisplayAreaIfDocumentContentIsSmallerThanViewPortFromAS(){
		info("Declare variables");
		//get a pdf file with small page size
		fileToUpload = attachFile.getAttachFileByArrayTypeRandom(20);
		path=siteExPath.getSiteExpPathByIndex(0);
		nameDrive=siteExDrive.getSiteExpDriveByIndex(0);
		
		shareFileAndOpenPreviewMode(nameDrive,path,fileToUpload);
		Dimension sizeViewer = waitForAndGetElement(previewMode.ELEMENT_PREVIEW_MODE_VIEW_READER,2000,0).getSize();
		int wd_sizeViewer = sizeViewer.width;
		int h_sizeViewer = sizeViewer.height;
		
		Dimension sizePageContainer = waitForAndGetElement(previewMode.ELEMENT_PAGE_CONTAINER.replace("${number}", "1"),2000,0).getSize();
		int wd_sizePageContainer= sizePageContainer.width;
		int h_sizePageContainer= sizePageContainer.height;
		
		previewMode.verifyDimensionOfViewPortWithPageSize(wd_sizeViewer,h_sizeViewer,wd_sizePageContainer,h_sizePageContainer);
	    
		info("Close The preview mode");
		previewMode.backASByClickCrossIcon();
		
		//deleteAllDataTest();
		
	}
	
	/**
	 * Case_ID: 114118,115358
	 * Case_name_1: Check the display area when a document's content is larger than the browser viewport on Intranet activity stream
	 * Case_name_2: Check the aspect ratio on the display area if the content is taller than the view port on Intranet activity stream
	 * Preconditions:
	 * A document (PDF, Office files) with a content that is smaller than the view port.
	 * The content is shared on the activity stream
	 * Steps:
	 * 1. Connect to Intranet Homepage
	 * 2. Click on the file's name or View link under the file
	 * Expected:
	 * The display area is displayed with the content and occupy a maximum width.
	 * An Horizontal scrollbar maybe displayed
	 * The heigh and the width are displayed proportionally
	 */
	@Test
	public void test06_07_DisplayDocumentInDisplayAreaIfDocumentContentIsLargerThanViewPortFromAS(){
		info("Declare variables");
		//get a pdf file with large page size
		fileToUpload = attachFile.getAttachFileByArrayTypeRandom(21);
		path=siteExPath.getSiteExpPathByIndex(0);
		nameDrive=siteExDrive.getSiteExpDriveByIndex(0);
		
		shareFileAndOpenPreviewMode(nameDrive,path,fileToUpload);
		Dimension sizeViewer = waitForAndGetElement(previewMode.ELEMENT_PREVIEW_MODE_VIEW_READER,3000,0).getSize();
		int wd_sizeViewer = sizeViewer.width;
		int h_sizeViewer = sizeViewer.height;
		
		Dimension sizePageContainer = waitForAndGetElement(previewMode.ELEMENT_PAGE_CONTAINER.replace("${number}", "1"),3000,0).getSize();
		int wd_sizePageContainer= sizePageContainer.width;
		int h_sizePageContainer= sizePageContainer.height;
		
		previewMode.verifyDimensionOfViewPortWithPageSize(wd_sizeViewer,h_sizeViewer,wd_sizePageContainer,h_sizePageContainer);
	    
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
	
	/**
	 * Delete all data test
	 * @param file
	 */
	
	/*private void deleteDataTest(String file){
		info("Delete data test");
		navToolbar.goToSiteExplorer();
		actBar.showDrives();
		siteExp.openADrive(nameDrive);

		info("sites/acme/document");
		siteExp.goToAFolder(path);
		driver.get(driver.getCurrentUrl());
		siteExp.clickWebView();
		actBar.deleteDocument(By.linkText(file));
		info("Data test is deleted successfully");
	}*/
/*	private void deleteAllDataTest(){
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
		actBar.uploadFile(datTestPath+ file);
				
		info("Go to Activity stream of Intranet Home page");
		hp.goToHomePage();
		
		info("Open Preview mode");		
		acStream.openPreviewMode(file,true);
		
		info("Verify that the preview is shown");
		waitForAndGetElement(previewMode.ELEMENT_PREVIEW_MODE,3000,0);
		info("The preview is shown successfully");
	}*/
	
}

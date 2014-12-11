package org.exoplatform.selenium.platform.ecms.acceptance;

import static org.exoplatform.selenium.TestLogger.info;

import org.testng.annotations.Test;

/** 
* By quynhpt
* data 08/12/2014
*/

public class Document_Preview_NewFileReader_AllTypes extends Document_Preview_TestConfig {
	
	
	/**
	 * Case_ID:114126
	 * Case_name: Check the new reader for a PDF file type from Intranet activity stream
	 * Preconditions: 
	 * 1. Share a PDF file on the activity stream
	 * 2. A program to read PDF file is installed on the machine
	 * Steps:
	 * 1.Connect to Intranet/Space Homepage
	 * 2.Click on the file's name of the file or View link under the file
	 * Expected:
	 * The content of the file PDF is displayed following the new reader form
	 */
	@Test
	public void test11_CheckNewReaderForPDFFileFromAS(){
		info("Declare variables");
		//get a pdf file
		fileToUpload = attachFile.getAttachFileByArrayTypeRandom(1);
		path=siteExPath.getSiteExpPathByIndex(0);
		nameDrive=siteExDrive.getSiteExpDriveByIndex(0);
		
		shareFileAndOpenPreviewMode(nameDrive,path,fileToUpload);
		
		info("Verify that first page is shown");
		waitForAndGetElement(previewMode.ELEMENT_CURRENT_PAGE_NUMBER,3000,0).getAttribute("value").toString().equals("1");
		
		info("Close The preview mode");
		previewMode.backASByClickCrossIcon();
		//deleteAllDataTest();
	}
	
	/**
	 * Case_ID:114127
	 * Case_name: Check the new reader for a .doc file type from Intranet activity stream
	 * Preconditions: 
	 * 1. Share a .doc file on the activity stream
	 * Steps:
	 * 1.Connect to Intranet/Space Homepage
	 * 2.Click on the file's name of the file or View link under the file
	 * Expected:
	 * The content of the .doc file is displayed following the new reader form
	 */
	@Test
	public void test12_CheckNewReaderForDOCFileFromAS(){
		info("Declare variables");
		//get a doc file
		fileToUpload = attachFile.getAttachFileByArrayTypeRandom(2);
		path=siteExPath.getSiteExpPathByIndex(0);
		nameDrive=siteExDrive.getSiteExpDriveByIndex(0);
		
		shareFileAndOpenPreviewMode(nameDrive,path,fileToUpload);
		
		info("Verify that first page is shown");
		waitForAndGetElement(previewMode.ELEMENT_CURRENT_PAGE_NUMBER,3000,0).getAttribute("value").toString().equals("1");
		
		info("Close The preview mode");
		previewMode.backASByClickCrossIcon();
		//deleteAllDataTest();
	}
	
	/**
	 * Case_ID:114128
	 * Case_name: Check the new reader for a .docx file type from Intranet activity stream
	 * Preconditions: 
	 * 1. Share a .docx file on the activity stream
	 * Steps:
	 * 1.Connect to Intranet/Space Homepage
	 * 2.Click on the file's name of the file or View link under the file
	 * Expected:
	 * The content of the .docx file is displayed following the new reader form
	 */
	@Test
	public void test13_CheckNewReaderForDOCXFileFromAS(){
		info("Declare variables");
		//get a docx file
		fileToUpload = attachFile.getAttachFileByArrayTypeRandom(3);
		path=siteExPath.getSiteExpPathByIndex(0);
		nameDrive=siteExDrive.getSiteExpDriveByIndex(0);
		
		shareFileAndOpenPreviewMode(nameDrive,path,fileToUpload);
		
		info("Verify that first page is shown");
		waitForAndGetElement(previewMode.ELEMENT_CURRENT_PAGE_NUMBER,3000,0).getAttribute("value").toString().equals("1");
		
		info("Close The preview mode");
		previewMode.backASByClickCrossIcon();
		//deleteAllDataTest();
	}
	
	/**
	 * Case_ID:114138
	 * Case_name: Check the new reader for a .odg file type from Intranet activity stream
	 * Preconditions: 
	 * 1. Share a .odg file on the activity stream
	 * Steps:
	 * 1.Connect to Intranet/Space Homepage
	 * 2.Click on the file's name of the file or View link under the file
	 * Expected:
	 * The content of the .odg file is displayed following the new reader form
	 */
	@Test
	public void test01_CheckNewReaderForODGFileFromAS(){
		info("Declare variables");
		//get a odg file
		fileToUpload = attachFile.getAttachFileByArrayTypeRandom(5);
		path=siteExPath.getSiteExpPathByIndex(0);
		nameDrive=siteExDrive.getSiteExpDriveByIndex(0);
		
		shareFileAndOpenPreviewMode(nameDrive,path,fileToUpload);
		
		info("Verify that first page is shown");
		waitForAndGetElement(previewMode.ELEMENT_CURRENT_PAGE_NUMBER,3000,0).getAttribute("value").toString().equals("1");
		
		info("Close The preview mode");
		previewMode.backASByClickCrossIcon();
		//deleteAllDataTest();
	}
	
	/**
	 * Case_ID:114136
	 * Case_name: Check the new reader for a .ods file type from Intranet activity stream
	 * Preconditions: 
	 * 1. Share a .ods file on the activity stream
	 * Steps:
	 * 1.Connect to Intranet/Space Homepage
	 * 2.Click on the file's name of the file or View link under the file
	 * Expected:
	 * The content of the .ods file is displayed following the new reader form
	 */
	@Test
	public void test02_CheckNewReaderForODSFileFromAS(){
		info("Declare variables");
		//get a ods file
		fileToUpload = attachFile.getAttachFileByArrayTypeRandom(7);
		path=siteExPath.getSiteExpPathByIndex(0);
		nameDrive=siteExDrive.getSiteExpDriveByIndex(0);
		
		shareFileAndOpenPreviewMode(nameDrive,path,fileToUpload);
		
		info("Verify that first page is shown");
		waitForAndGetElement(previewMode.ELEMENT_CURRENT_PAGE_NUMBER,3000,0).getAttribute("value").toString().equals("1");
		
		info("Close The preview mode");
		previewMode.backASByClickCrossIcon();
		//deleteAllDataTest();
	}
	/**
	 * Case_ID:114134
	 * Case_name: Check the new reader for a .odt file type from Intranet activity stream
	 * Preconditions: 
	 * 1. Share a .odt file on the activity stream
	 * Steps:
	 * 1.Connect to Intranet/Space Homepage
	 * 2.Click on the file's name of the file or View link under the file
	 * Expected:
	 * The content of the .odt file is displayed following the new reader form
	 */
	@Test
	public void test03_CheckNewReaderForODTFileFromAS(){
		info("Declare variables");
		//get a odt file
		fileToUpload = attachFile.getAttachFileByArrayTypeRandom(8);
		path=siteExPath.getSiteExpPathByIndex(0);
		nameDrive=siteExDrive.getSiteExpDriveByIndex(0);
		
		shareFileAndOpenPreviewMode(nameDrive,path,fileToUpload);
		
		info("Verify that first page is shown");
		waitForAndGetElement(previewMode.ELEMENT_CURRENT_PAGE_NUMBER,3000,0).getAttribute("value").toString().equals("1");
		
		info("Close The preview mode");
		previewMode.backASByClickCrossIcon();
		//deleteAllDataTest();
	}
	
	/**
	 * Case_ID:114137
	 * Case_name: Check the new reader for a .ots file type from Intranet activity stream
	 * Preconditions: 
	 * 1. Share a .ots file on the activity stream
	 * Steps:
	 * 1.Connect to Intranet/Space Homepage
	 * 2.Click on the file's name of the file or View link under the file
	 * Expected:
	 * The content of the .ots file is displayed following the new reader form
	 */
	@Test
	public void test04_CheckNewReaderForOTSFileFromAS(){
		info("Declare variables");
		//get a ots file
		fileToUpload = attachFile.getAttachFileByArrayTypeRandom(9);
		path=siteExPath.getSiteExpPathByIndex(0);
		nameDrive=siteExDrive.getSiteExpDriveByIndex(0);
		
		shareFileAndOpenPreviewMode(nameDrive,path,fileToUpload);
		
		info("Verify that first page is shown");
		waitForAndGetElement(previewMode.ELEMENT_CURRENT_PAGE_NUMBER,3000,0).getAttribute("value").toString().equals("1");
		
		info("Close The preview mode");
		previewMode.backASByClickCrossIcon();
		//deleteAllDataTest();
	}
	
	/**
	 * Case_ID:114129
	 * Case_name: Check the new reader for a .ppt file type from Intranet activity stream
	 * Preconditions: 
	 * 1. Share a .ppt file on the activity stream
	 * Steps:
	 * 1.Connect to Intranet/Space Homepage
	 * 2.Click on the file's name of the file or View link under the file
	 * Expected:
	 * The content of the .ppt file is displayed following the new reader form
	 */
	@Test
	public void test05_CheckNewReaderForPPTFileFromAS(){
		info("Declare variables");
		//get a ppt file
		fileToUpload = attachFile.getAttachFileByArrayTypeRandom(10);
		path=siteExPath.getSiteExpPathByIndex(0);
		nameDrive=siteExDrive.getSiteExpDriveByIndex(0);
		
		shareFileAndOpenPreviewMode(nameDrive,path,fileToUpload);
		
		info("Verify that first page is shown");
		waitForAndGetElement(previewMode.ELEMENT_CURRENT_PAGE_NUMBER,3000,0).getAttribute("value").toString().equals("1");
		
		info("Close The preview mode");
		previewMode.backASByClickCrossIcon();
		//deleteAllDataTest();
	}
	
	/**
	 * Case_ID:114130
	 * Case_name: Check the new reader for a .pptx file type from Intranet activity stream
	 * Preconditions: 
	 * 1. Share a .pptx file on the activity stream
	 * Steps:
	 * 1.Connect to Intranet/Space Homepage
	 * 2.Click on the file's name of the file or View link under the file
	 * Expected:
	 * The content of the .pptx file is displayed following the new reader form
	 */
	@Test
	public void test06_CheckNewReaderForPPTXFileFromAS(){
		info("Declare variables");
		//get a pptx file
		fileToUpload = attachFile.getAttachFileByArrayTypeRandom(11);
		path=siteExPath.getSiteExpPathByIndex(0);
		nameDrive=siteExDrive.getSiteExpDriveByIndex(0);
		
		shareFileAndOpenPreviewMode(nameDrive,path,fileToUpload);
		
		info("Verify that first page is shown");
		waitForAndGetElement(previewMode.ELEMENT_CURRENT_PAGE_NUMBER,3000,0).getAttribute("value").toString().equals("1");
		
		info("Close The preview mode");
		previewMode.backASByClickCrossIcon();
		//deleteAllDataTest();
	}
	/**
	 * Case_ID:114140
	 * Case_name: Check the new reader for a .pptx file type from Intranet activity stream
	 * Preconditions: 
	 * 1. Share a .pptx file on the activity stream
	 * Steps:
	 * 1.Connect to Intranet/Space Homepage
	 * 2.Click on the file's name of the file or View link under the file
	 * Expected:
	 * The content of the .pptx file is displayed following the new reader form
	 */
	@Test
	public void test07_CheckNewReaderForRTFFileFromAS(){
		info("Declare variables");
		//get a rtf file
		fileToUpload = attachFile.getAttachFileByArrayTypeRandom(12);
		path=siteExPath.getSiteExpPathByIndex(0);
		nameDrive=siteExDrive.getSiteExpDriveByIndex(0);
		
		shareFileAndOpenPreviewMode(nameDrive,path,fileToUpload);
		
		info("Verify that first page is shown");
		waitForAndGetElement(previewMode.ELEMENT_CURRENT_PAGE_NUMBER,3000,0).getAttribute("value").toString().equals("1");
		
		info("Close The preview mode");
		previewMode.backASByClickCrossIcon();
		//deleteAllDataTest();
	}
	/**
	 * Case_ID:114131
	 * Case_name: Check the new reader for a .xls file type from Intranet activity stream
	 * Preconditions: 
	 * 1. Share a .xls file on the activity stream
	 * Steps:
	 * 1.Connect to Intranet/Space Homepage
	 * 2.Click on the file's name of the file or View link under the file
	 * Expected:
	 * The content of the .xls file is displayed following the new reader form
	 */
	@Test
	public void test08_CheckNewReaderForXLSFileFromAS(){
		info("Declare variables");
		//get a xls file
		fileToUpload = attachFile.getAttachFileByArrayTypeRandom(13);
		path=siteExPath.getSiteExpPathByIndex(0);
		nameDrive=siteExDrive.getSiteExpDriveByIndex(0);
		
		shareFileAndOpenPreviewMode(nameDrive,path,fileToUpload);
		
		info("Verify that first page is shown");
		waitForAndGetElement(previewMode.ELEMENT_CURRENT_PAGE_NUMBER,3000,0).getAttribute("value").toString().equals("1");
		
		info("Close The preview mode");
		previewMode.backASByClickCrossIcon();
		//deleteAllDataTest();
	}
	
	/**
	 * Case_ID:114132
	 * Case_name: Check the new reader for a .xlsx file type from Intranet activity stream
	 * Preconditions: 
	 * 1. Share a .xlsx file on the activity stream
	 * Steps:
	 * 1.Connect to Intranet/Space Homepage
	 * 2.Click on the file's name of the file or View link under the file
	 * Expected:
	 * The content of the .xlsx file is displayed following the new reader form
	 */
	@Test
	public void test09_CheckNewReaderForXLSXFileFromAS(){
		info("Declare variables");
		//get a xlsx file
		fileToUpload = attachFile.getAttachFileByArrayTypeRandom(14);
		path=siteExPath.getSiteExpPathByIndex(0);
		nameDrive=siteExDrive.getSiteExpDriveByIndex(0);
		
		shareFileAndOpenPreviewMode(nameDrive,path,fileToUpload);
		
		info("Verify that first page is shown");
		waitForAndGetElement(previewMode.ELEMENT_CURRENT_PAGE_NUMBER,3000,0).getAttribute("value").toString().equals("1");
		
		info("Close The preview mode");
		previewMode.backASByClickCrossIcon();
		//deleteAllDataTest();
	}
	/**
	 * Case_ID:114133
	 * Case_name: Check the new reader for a .xlt file type from Intranet activity stream
	 * Preconditions: 
	 * 1. Share a .xlt file on the activity stream
	 * Steps:
	 * 1.Connect to Intranet/Space Homepage
	 * 2.Click on the file's name of the file or View link under the file
	 * Expected:
	 * The content of the .xlt file is displayed following the new reader form
	 */
	@Test
	public void test10_CheckNewReaderForXLTFileFromAS(){
		info("Declare variables");
		//get a xlt file
		fileToUpload = attachFile.getAttachFileByArrayTypeRandom(15);
		path=siteExPath.getSiteExpPathByIndex(0);
		nameDrive=siteExDrive.getSiteExpDriveByIndex(0);
		
		shareFileAndOpenPreviewMode(nameDrive,path,fileToUpload);
		
		info("Verify that first page is shown");
		waitForAndGetElement(previewMode.ELEMENT_CURRENT_PAGE_NUMBER,3000,0).getAttribute("value").toString().equals("1");
		
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
	 * Case_ID:114162
	 * Case_name: Check the button "switch to presentation mode" from the Preview form  from Intranet activity stream
	 * Preconditions: 
	 * 1. Share a files (PDF, Office files or Web Content) with a supported format on the activity stream
	 * Steps:
	 * 1.Connect to Intranet/Space Homepage
	 * 2.Click on the file's name of the file or View link under the file
	 * 3.Click on the button "switch to presentation mode"
	 * Expected:
	 * - The fullscreen mode is displayed
	 * - A pop up is displayed: "$HOST is now fullscreen"
	 * where: $HOST is the host url (ex: exoplatform.org)
	 * - 3 actions on the pop up are displayed
	 *     + Deny
	 *     + Allow
	 *     + Remember decision for $HOST
	 *  (PENDING- CANNOT CHECK WITH FULLSCREEN)
	 */
	
	/**
	 * Case_ID:114164
	 * Case_name: Check the "Allow" button from the fullscreen pop up  from Intranet activity stream
	 * Preconditions: 
	 * 1. Share a files (PDF, Office files or Web Content) with a supported format on the activity stream
	 * Steps:
	 * 1.Connect to Intranet/Space Homepage
	 * 2.Click on the file's name of the file or View link under the file
	 * 3.Click on the button "switch to presentation mode"
	 * 4. Click on the button "Allow"
	 * Expected:
	 * The fullscreen is kept
	 * (PENDING- CANNOT CHECK WITH FULLSCREEN)
	 */
	
	/**
	 * Case_ID:114163
	 * Case_name:Check the "Deny" button from the fullscreen pop up  from Intranet activity stream
	 * Preconditions: 
	 * 1. Share a files (PDF, Office files or Web Content) with a supported format on the activity stream
	 * Steps:
	 * 1.Connect to Intranet/Space Homepage
	 * 2.Click on the file's name of the file or View link under the file
	 * 3.Click on the button "switch to presentation mode"
	 * 4. Click on the button "Deny"
	 * Expected:
	 * The fullscreen is closed
	 * (PENDING- CANNOT CHECK WITH FULLSCREEN)
	 */
	
	
	/**
	 * Case_ID:114165
	 * Case_name:Check the chekbox "Remember decision for $HOST" with "Allow" option  from Intranet activity stream
	 * Preconditions: 
	 * 1. Share a files (PDF, Office files or Web Content) with a supported format on the activity stream
	 * Steps:
	 * 1.Connect to Intranet/Space Homepage
	 * 2.Click on the file's name of the file or View link under the file
	 * 3.Click on the button "switch to presentation mode"
	 * 4.Check the box "Remember decision for $HOST"
	 * 5. Click on the button "Allow"
	 * ==> The fullscreen is kept
	 * 6. From the keybord, press "ESC"
	 * ==> The preview is closed
	 * 7. Click on the button "switch to presentation mode"
	 * ==>The fullscreen is displayed. The pop up is not displayed
	 * (PENDING- CANNOT CHECK WITH FULLSCREEN)
	 */
	
	/**
	 * Delete all data test
	 */
/*	private void deleteDataTest(String file){
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
		
		info("Verify that the preview is shown");
		waitForAndGetElement(previewMode.ELEMENT_PREVIEW_MODE,3000,0);
		info("The preview is shown successfully");
	}*/
	
	
}

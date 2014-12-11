package org.exoplatform.selenium.platform.ecms.acceptance;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.testng.annotations.Test;

/** 
* By quynhpt
* data 08/12/2014
*/

public class Document_Preview_NewFileReader_Others extends Document_Preview_TestConfig {
	
	
	/**
	 * Case_ID:114505
	 * Case_name: Check the display area when a document is too large on Intranet activity stream
	 * Preconditions: 
	 * 1. A large document(PDF, Office files) (over 5 MB) is shared or uploaded on the activity stream
	 * 2. The document's format is supported in the system (ex. pdf, doc, docx, odp....)
	 * Steps:
	 * 1.Connect to Intranet/Space Homepage
	 * 2.Click on the file's name of the file or View link under the file
	 * 3.Check preview's content
	 * Expected:
	 * A message text is shown in the content as :
	 * "The preview is not available for content larger than  5MB"
	 */
	@Test
	public void test01_CheckDisplayAreaWhenADocumentIsTooLargeOnTheActivityStream(){
		info("Declare variables");
		//get a PPTX file that is big file >=5Mb
		file= attachFile.getAttachFileByArrayTypeRandom(23);
		path=siteExPath.getSiteExpPathByIndex(0);
		nameDrive=siteExDrive.getSiteExpDriveByIndex(0);
		
		shareFileAndOpenPreviewMode(nameDrive,path,file);
		
		waitForAndGetElement(previewMode.ELEMENT_PEVIEW_MODE_NOT_AVAIABLE_ICON,3000,0);
		waitForAndGetElement(previewMode.ElEMENT_PREVIEW_MODE_NOT_AVAIABLE_MESSAGE_LARGE_FILE,3000,0);
		waitForAndGetElement(previewMode.ELEMENT_PREVIEW_MODE_NOT_AVAIABLE_DOWNLOAD_BUTTON,3000,0);
		waitForAndGetElement(previewMode.ELEMENT_PREVIEW_MODE_NOT_AVAIABLE_OPEN_IN_DESKTOP,3000,0);
	    
		info("Close The preview mode");
		previewMode.backASByClickCrossIcon();
		//deleteAllDataTest();
	}
	
	/**
	 * Case_ID:114506
	 * Case_name: Check the display area if a document has too many pages on Intranet activity stream
	 * Preconditions: 
	 * 1. A large document(PDF, Office files) (over 5 MB) is shared or uploaded on the activity stream
	 * 2. The document's format is supported in the system (ex. pdf, doc, docx, odp....)
	 * Steps:
	 * 1.Connect to Intranet/Space Homepage
	 * 2.Click on the file's name of the file or View link under the file
	 * 3.Check preview's content
	 * Expected:
	 * A message text is shown in the content as :
	 * "The preview is not available for content with over 100 pages."
	 */
	@Test
	public void test02_CheckDisplayAreaWhenADocumentIsTooManyPagesOnTheActivityStream(){
		info("Declare variables");
		//get a xlx file that has many pages (over 99pages)
		file= attachFile.getAttachFileByArrayTypeRandom(24);
		path=siteExPath.getSiteExpPathByIndex(0);
		nameDrive=siteExDrive.getSiteExpDriveByIndex(0);
		
		shareFileAndOpenPreviewMode(nameDrive,path,file);
		
		waitForAndGetElement(previewMode.ELEMENT_PEVIEW_MODE_NOT_AVAIABLE_ICON,3000,0);
		waitForAndGetElement(previewMode.ElEMENT_PREVIEW_MODE_NOT_AVAIABLE_MESSAGE_MANY_PAGES,3000,0);
		waitForAndGetElement(previewMode.ELEMENT_PREVIEW_MODE_NOT_AVAIABLE_DOWNLOAD_BUTTON,3000,0);
		waitForAndGetElement(previewMode.ELEMENT_PREVIEW_MODE_NOT_AVAIABLE_OPEN_IN_DESKTOP,3000,0);
	    
		info("Close The preview mode");
		previewMode.backASByClickCrossIcon();
		//deleteAllDataTest();
	}
	
	/**
	 * Case_ID:114133
	 * Case_name: Check Rating and Votes informations of a document from Intranet activity stream
	 * Preconditions: 
	 * 1. Share a files (PDF, Office files or Web Content) with a supported format on the activity stream
	 * Steps:
	 * 1.Connect to Intranet/Space Homepage
	 * 2.Click on the file's name of the file or View link under the file
	 * 3.Open Preview mode and check vote and rate information
	 * ==> not show rate and vote information
	 * 4. Rate and vote the document
	 * 5. Open preview mode
	 * ==> Rate and vote information is shown
	 */
	@Test
	public void test03_CheckRatingVotesInformationOfADocumentFromAS(){
		info("Declare variables");
		//get a pdf file
		file= attachFile.getAttachFileByArrayTypeRandom(1);
		path=siteExPath.getSiteExpPathByIndex(0);
		nameDrive=siteExDrive.getSiteExpDriveByIndex(0);
		
		shareFileAndOpenPreviewMode(nameDrive,path,file);
		
		info("Verify that Vote information isnot shown");
		waitForElementNotPresent(previewMode.ELEMENT_PREVIEW_RATE_VOTE_INFORMATION,3000,0);
		
		info("Close The preview mode");
		previewMode.backASByClickCrossIcon();
		
		info("Go to Sites Explorer");
		navToolbar.goToSiteExplorer();
		info("Go to Collaboration drive");
		actBar.showDrives();
		siteExp.openADrive(nameDrive);

		info("sites/acme/document");
		siteExp.goToAFolder(path);
		driver.navigate().refresh();
		siteExp.selectFileExplorer();
		info("Open the document in Site Explorer");
		siteExp.openDocumentFromRighPanel(file);
		
		info("Vote the document with 2 starts");
		actBar.voteDocument(2);
		
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		Utils.pause(3000);
		
		/*info("Go to Activity stream of Intranet Home page");
		hp.goToHomePage();*/
		
		info("Open Preview mode");		
		acStream.openPreviewMode(file,true);
		
		info("Verify that the preview is shown");
		waitForAndGetElement(previewMode.ELEMENT_PREVIEW_MODE,3000,0);
		info("The preview is shown successfully");
		
		info("Verify that Vote information is shown with correct rate");
		waitForAndGetElement(previewMode.ELEMENT_VOTE_RATING_INFOR.replace("${rate}", "2.0"),3000,0);
		
		info("Close The preview mode");
		previewMode.backASByClickCrossIcon();
		//deleteAllDataTest();
	}
	
	/**
	 * Requirment: DISPLAY_09
	 * Case_name: A preview from the activity stream is available for the following contents:
	 * + pdf and office file
	 * + media (audio, video, image)
	 * + Webcontent
	 * For other types, the user is redirected to the source file (in the preview provided by ecms).
	 */
	@Test
	public void test04_CheckPreviewModeIsAvaiableForPDFFileFromAS(){
		info("Declare variables");
		//get a pdf file
		file= attachFile.getAttachFileByArrayTypeRandom(1);
		path=siteExPath.getSiteExpPathByIndex(0);
		nameDrive=siteExDrive.getSiteExpDriveByIndex(0);
		
		shareFileAndOpenPreviewMode(nameDrive,path,file);
		
		info("Close The preview mode");
		previewMode.backASByClickCrossIcon();
		//deleteAllDataTest();
	}
	
	@Test
	public void test06_CheckPreviewModeIsAvaiableForIMAGEFileFromAS(){
		info("Declare variables");
		//get a image file that has format is supported
		file= attachFile.getAttachFileByArrayTypeRandom(26);
		path=siteExPath.getSiteExpPathByIndex(0);
		nameDrive=siteExDrive.getSiteExpDriveByIndex(0);
		
		shareFileAndOpenPreviewMode(nameDrive,path,file);
		
		info("Close The preview mode");
		previewMode.backASByClickCrossIcon();
		//deleteAllDataTest();
	}
	
	@Test
	public void test08_CheckPreviewModeIsAvaiableForWEBCONTENTFileFromAS(){
		info("Declare variables");
		file = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		path=siteExPath.getSiteExpPathByIndex(0);
		nameDrive=siteExDrive.getSiteExpDriveByIndex(0);
		
		info("Go to Sites Explorer");
		navToolbar.goToSiteExplorer();
		info("Go to the drive:"+nameDrive);
		actBar.showDrives();
		siteExp.openADrive(nameDrive);

		info("Go to the folder:"+path);
		siteExp.goToAFolder(path);
		
		info("Create a new document");
		actBar.addActionBar("addDocument",actBar.ELEMENT_NEW_CONTENT_LINK);
		actBar.goToAddNewContent();
		siteExp.selectAContent("Web Content");
		mnTemplate.createNewWebContent(file, file, "", "", "", "");
		
		info("Go to Activity stream of Intranet Home page");
		hp.goToHomePage();
		
		
		info("Open Preview mode");		
		acStream.openPreviewMode(file,false);
		
		info("Verify that the preview is shown");
		waitForAndGetElement(previewMode.ELEMENT_PREVIEW_MODE,3000,0);
		info("The preview is shown successfully");
		
		info("Close The preview mode");
		previewMode.backASByClickCrossIcon();
		//deleteAllDataTest();
	}
	
	@Test
	public void test07_CheckPreviewModeIsAvaiableForOTHERFileFromAS(){
		info("Declare variables");
		file = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		path=siteExPath.getSiteExpPathByIndex(0);
		nameDrive=siteExDrive.getSiteExpDriveByIndex(0);
		info("nameDrive:"+nameDrive);
		info("path:"+path);
		info("Go to Sites Explorer");
		navToolbar.goToSiteExplorer();
		info("Go to Collaboration drive");
		actBar.showDrives();
		siteExp.openADrive(nameDrive);

		info("sites/acme/document");
		siteExp.goToAFolder(path);
		driver.get(driver.getCurrentUrl());
		
		info("Create a new document");
		actBar.addActionBar("addDocument",actBar.ELEMENT_NEW_CONTENT_LINK);
		actBar.goToAddNewContent();
		mnTemplate.createNewCssFile(file,"0",file);
		
		info("Go to Activity stream of Intranet Home page");
		hp.goToHomePage();
		
		info("click on filename of document on activity stream");
		click(acStream.ELEMENT_ACTIVITY_DOCUMENT_IMAGE_TITLE.replace("${title}", file));
		Utils.pause(3000);
		
		info("Verify that redirects to ECMS area");
		waitForAndGetElement(siteExp.ELEMENT_WEBCONTENT_NAME.replace("${nameFile}",file),3000,0);
		//deleteAllDataTest();
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
	 * Case_ID:114509
	 * Case_name:Check the chekbox "Remember decision for $HOST"  with "Deny" option  from Intranet activity stream
	 * Preconditions: 
	 * 1. Share a files (PDF, Office files or Web Content) with a supported format on the activity stream
	 * Steps:
	 * 1.Connect to Intranet/Space Homepage
	 * 2.Click on the file's name of the file or View link under the file
	 * 3.Click on the button "switch to presentation mode"
	 * 4.Check the box "Remember decision for $HOST"
	 * 5.Click on the button "Deny"
	 * ==>The fullscreen is closed
	 * 6. From the keybord, press "ESC"
	 * ==> The preview is closed
	 * 7. Click on the button "switch to presentation mode"
	 * ==>The fullscreen is not displayed 
	 * (PENDING- CANNOT CHECK WITH FULLSCREEN)
	 */
	
	/**
	 * Case_ID:115487
	 * Case_name: Check the display of file format not supported on Intranet activity stream
	 * Preconditions:
	 *  Share a file where the format is not supported
	 *  (The file format should not be: pdf or office files: doc, docx, ppt, pptx, xls, xlsx, xlt, odt, odp, ods, ots, odg, odf, odt, rtf)
	 * Steps:
	 * 1. Connect to Intranet Homepage
	 * 2. Check View link of the content
	 * ==>View link is not shown
	 * 3.Click on file name of the document
	 * ==>The Preview is not displayed.
	 * the file name should redirect the user to the source file
	 */
	@Test
	public void test05_CheckDisplayFileFormatNotSupportedFromAS(){
		info("Declare variables");
		//get a file that has not format is supported
		file= attachFile.getAttachFileByArrayTypeRandom(25);
		path=siteExPath.getSiteExpPathByIndex(0);
		nameDrive=siteExDrive.getSiteExpDriveByIndex(0);
		
		/*info("Go to Sites Explorer");
		navToolbar.goToSiteExplorer();
		info("Go to Collaboration drive");
		actBar.showDrives();
		siteExp.openADrive(nameDrive);

		info("sites/acme/document");
		siteExp.goToAFolder(path);
		driver.get(driver.getCurrentUrl());
					
		info("Upload a file");
		actBar.uploadFile(datTestPath+ file);*/
		
		acStream.addActivity("General Drives",
				"Collaboration/sites/intranet/documents", datTestPath,
				file, true, "test" + getRandomNumber());
				
		/*info("Go to Activity stream of Intranet Home page");
		hp.goToHomePage();*/
		
		info("Verify that View link is not shown on Activity Stream");
		waitForElementNotPresent(acStream.ELEMENT_ACTIVITY_DOCUMENT_IMAGE_VIEW_LINK.replace("${nameFile}",file));
		
		info("click on filename of document on activity stream");
		click(acStream.ELEMENT_ACTIVITY_DOCUMENT_IMAGE_TITLE.replace("${title}", file));
		Utils.pause(3000);
		
		info("Verify that redirects to ECMS area");
		waitForAndGetElement(siteExp.ELEMENT_CONTENT_NAME.replace("${nameFile}",file),3000,0);
		//deleteAllDataTest();
		
	}
	
	@Test
	public void test09_CheckDownloadButtonFromAS() throws Exception{
		info("Declare variables");
		//get a pdf file
		file= attachFile.getAttachFileByArrayTypeRandom(1);
		path=siteExPath.getSiteExpPathByIndex(0);
		nameDrive=siteExDrive.getSiteExpDriveByIndex(0);
		
		shareFileAndOpenPreviewMode(nameDrive,path,file);
		
		info("Open Tool Option menu");
		previewMode.clickToolOptionIcon();
		info("Get dowload link");
		String url = waitForAndGetElement(previewMode.ELEMENT_TOOL_DOWNLOAD,3000,0).getAttribute("href").toString();
		info("Check download link is working well");
		downloadHandler.downloadFile(url);
				
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
		Utils.pause(3000);
	}
	/**
	 * Delete all data test
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
	}
	*/
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

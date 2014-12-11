package org.exoplatform.selenium.platform.ecms.acceptance;

import static org.exoplatform.selenium.TestLogger.info;

import org.testng.annotations.Test;

/** 
* By quynhpt
* data 27/11/2014
*/

public class Document_Preview_Media_Others extends Document_Preview_TestConfig {
	
	
	
	
	/**
	 * MEDIA_10:
	 * If a preview isn't available for a file:
	 * the link View should not be available in the activity actions bar 
	 * the filename should redirect the user to the source file
	 */
	@Test
	public void test01_CheckNoPreviewAvailableMedia(){
		info("Declare variables");
		//get a media file that has not supported format
		fileToUpload = attachFile.getAttachFileByArrayTypeRandom(65);
		path=siteExPath.getSiteExpPathByIndex(3);
		nameDrive=siteExDrive.getSiteExpDriveByIndex(0);
		shareFileAndOpenPreviewMode(nameDrive,path,fileToUpload);
		
		/*info("Go to Sites Explorer");
		navToolbar.goToSiteExplorer();
		info("Go to Collaboration drive");
		actBar.showDrives();
		siteExp.openADrive(nameDrive);

		info("sites/acme/document");
		siteExp.goToAFolder(path);
		driver.navigate().refresh();
					
		info("Upload a file");
		actBar.uploadFile(datTestPath + fileToUpload);
				
		info("Go to Activity stream of Intranet Home page");
		hp.goToHomePage();*/
		
		info("Verify that View link is not shown on Activity Stream");
		waitForElementNotPresent(acStream.ELEMENT_ACTIVITY_DOCUMENT_IMAGE_VIEW_LINK.replace("${nameFile}",fileToUpload));
		
		info("click on filename of document on activity stream");
		click(acStream.ELEMENT_ACTIVITY_DOCUMENT_IMAGE_TITLE.replace("${title}", fileToUpload));
		
		info("Verify that redirects to ECMS area");
		waitForAndGetElement(siteExp.ELEMENT_CONTENT_NAME.replace("${nameFile}",fileToUpload),3000,0);
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

		/*info("Open Preview mode");
		acStream.openPreviewMode(file, true);

		info("Verify that the preview is shown");
		waitForAndGetElement(previewMode.ELEMENT_PREVIEW_MODE, 3000, 0);
		info("The preview is shown successfully");*/
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
}

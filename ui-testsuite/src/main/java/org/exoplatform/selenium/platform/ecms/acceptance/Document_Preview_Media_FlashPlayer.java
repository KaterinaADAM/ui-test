package org.exoplatform.selenium.platform.ecms.acceptance;

import static org.exoplatform.selenium.TestLogger.info;

import org.testng.annotations.Test;

/** 
* By quynhpt
* data 27/11/2014
*/

public class Document_Preview_Media_FlashPlayer extends Document_Preview_TestConfig {
	
	/**
	 * MEDIA_05:
	 * The Adobe Flash Player is used to view the file suffix swf
	 */
	@Test
	public void test01_CheckPreviewModeForFlashPlayer(){
		info("Declare variables");
		//get a flv file
		fileToUpload = attachFile.getAttachFileByArrayTypeRandom(64);
		path=siteExPath.getSiteExpPathByIndex(1);
		nameDrive=siteExDrive.getSiteExpDriveByIndex(0);
		
		shareFileAndOpenPreviewMode(nameDrive,path,fileToUpload);
		info("Verify that download link is shown on Preview mode");
		waitForAndGetElement(previewMode.ELEMENT_PREVIEW_MODE_DOWNLOAD_LINK,3000);
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
		
	}
	
	*//**
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
		
		info("Check size of activity Stream frame");
		acStream.checkSizeActivityPlayer(acStream.ELEMENT_MEDIA_ACTIVITY_PLAYER_FRAME_FLASHPLAYER.replace("${fileName}", file));
		
		info("Open Preview mode");		
		acStream.openPreviewMode(file,true);
		
		info("Verify that the preview is shown");
		waitForAndGetElement(previewMode.ELEMENT_PREVIEW_MODE,3000,0);
		info("The preview is shown successfully");
		
	}*/
}

package org.exoplatform.selenium.platform.ecms.acceptance;

import static org.exoplatform.selenium.TestLogger.info;

import org.testng.annotations.Test;

/** 
* By quynhpt
* data 27/11/2014
*/

public class Document_Preview_DisplayArea_WebContent extends Document_Preview_TestConfig {
	
	@Test
	public void test01_CheckPreviewModeIsAvaiableForWEBCONTENTFileFromAS(){
		info("Declare variables");
		file = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		path=siteExPath.getSiteExpPathByIndex(0);
		nameDrive=siteExDrive.getSiteExpDriveByIndex(0);
		
		
		info("Go to Sites Explorer");
		navToolbar.goToSiteExplorer();
		info("Go to Collaboration drive");
		actBar.showDrives();
		siteExp.openADrive(nameDrive);

		info("sites/acme/document");
		siteExp.goToAFolder(path);
		driver.navigate().refresh();
		
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
		
		info("Verify that content of Document tab is shown in Preview mode");
		waitForAndGetElement(previewMode.ELEMENT_PREVIEW_MODE_WEBCONTENT_CONTENT.replace("${text}",file),3000,0);
		
		info("Close The preview mode");
		previewMode.backASByClickCrossIcon();
		
		//deleteAllDataTest(); //delete all data test after finished testing
		
	}
	
	/**
	 * Delete all data test
	 * @param file
	 *//*
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
		
	}*/
}

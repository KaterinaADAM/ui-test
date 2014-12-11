package org.exoplatform.selenium.platform.ecms.acceptance;

import static org.exoplatform.selenium.TestLogger.info;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class Document_Preview_Preview extends Document_Preview_TestConfig {
	
	/**
	 * Case_ID: 114113
	 * Case_name: Check the cross icon of the preview mode on the activity stream
	 * Preconditions: A document (PDF, Office files,  Webcontent) or Media  is on the activity stream
	 * Steps:
	 * 1. Connect to Intranet/Space activity stream
	 * 2. Click on file name or View link of the document or media
	 * 3. Click on the Cross (x) icon
	 * Expected:
	 * 3. The Preview is closed
	 */
	
	@Test
	public void test01_checkEscPress(){
		
		info("Declare variables");
		//get a pdf file
		fileToUpload = attachFile.getAttachFileByArrayTypeRandom(1);
		path=siteExPath.getSiteExpPathByIndex(0);
		nameDrive=siteExDrive.getSiteExpDriveByIndex(0);
	
		shareFileAndOpenPreviewMode(nameDrive,path,fileToUpload);
		
		info("Back to Activity Stream and Close Preview mode by Ecs");
		previewMode.backASByPressECS();
		
		info("Verify that the preview is closed");
		waitForElementNotPresent(previewMode.ELEMENT_PREVIEW_MODE);
		info("Verify that is come back to Activity stream");
		waitForAndGetElement(acStream.ELEMENT_ACTIVITY_DOCUMENT_IMAGE_VIEW_LINK.replace("${nameFile}",fileToUpload),3000,0);
		//deleteAllDataTest();
	}

	/**
	 * Case_ID: 114114
	 * Case_name: Check exit action by keyboard of the preview mode on the activity stream
	 * Preconditions: A document (PDF, Office files,  Webcontent) or Media  is on the activity stream
	 * Steps:
	 * 1. Connect to Intranet/Space activity stream
	 * 2. Click on file name or View link of the document or media
	 * 3. From the Keyboard, hit the key "ESC"
	 */
	@Test
	public void test02_checkCrossIcon(){
		info("Declare variables");
		//get a pdf file
		fileToUpload = attachFile.getAttachFileByArrayTypeRandom(1);
		path=siteExPath.getSiteExpPathByIndex(0);
		nameDrive=siteExDrive.getSiteExpDriveByIndex(0);
			
		shareFileAndOpenPreviewMode(nameDrive,path,fileToUpload);
		
		info("Close the preview and come back to Activity stream");
		previewMode.backASByClickCrossIcon();
				
		info("Verify that the preview is closed by clicking on Cross (X) icon");
		waitForElementNotPresent(previewMode.ELEMENT_PREVIEW_MODE);
		info("Verify that is come back to Activity Stream");
		waitForAndGetElement(acStream.ELEMENT_ACTIVITY_DOCUMENT_IMAGE_VIEW_LINK.replace("${nameFile}",fileToUpload),3000,0);
		//deleteAllDataTest();	
	}
	/**
	 * Case_ID: 114112
	 * Case_name: Check the display of the Document Preview on the activity steam
	 * Preconditions: A document (PDF, Office files,  Webcontent) or Media  is on the activity stream
	 * Steps:
	 * 1. Connect to Intranet/Space activity stream
	 * 2. Click on file name or View link of the document or media
	 * 3. Preview is shown. A shadow mask with high opacity is applied 
	 * over the browser window. The shadow mask occupies the whole area of the browser window.
	 * 
	 * Case_ID: 114115
	 * Case_name: Check shadow mask display of Document Preview on the activity steam
	 * Preconditions: A document (PDF, Office files,  Webcontent) or Media  is on the activity stream
	 * Steps:
	 * 1. Connect to Intranet/Space activity stream
	 * 2. Click on file name or View link of the document or media
	 * 3. The shadow mask occupies the whole area of the browser window.
	 */
	@Test
	public void test03_04_checkShadowMaskDocumentOnActivityStream(){
		info("Declare variables");
		//get a pdf file
		fileToUpload = attachFile.getAttachFileByArrayTypeRandom(1);
		path=siteExPath.getSiteExpPathByIndex(0);
		nameDrive=siteExDrive.getSiteExpDriveByIndex(0);
					
		shareFileAndOpenPreviewMode(nameDrive,path,fileToUpload);
		
		Dimension dimen = waitForAndGetElement(previewMode.ELEMENT_PREVIEW_MODE,3000,0).getSize();
        int wd_prev =dimen.width;
        int wd_br=this.driver.manage().window().getSize().width;
 	    info("wd_pre:"+wd_prev);
 	    info("wd_br:"+wd_br);
		info("Check and Verify shadow mask display");
		previewMode.shadowMask(wd_prev,wd_br);
						
		info("Close the preview and come back to Activity stream");
		previewMode.backASByClickCrossIcon();
		//deleteAllDataTest();				
	}
	
	/**
	 * Case_ID:117649
	 * Case_name: Check closing of Preview mode via 
	 * clicking on black background when view  a document or a media
	 * Steps:
	 * - Open Preview mode
	 * - Click on black background of Display area
	 * Expected:
	 * - Preview mode is closed
	 */
	@Test
	public void test05_CheckClosingPreviewModeViaClickingOnBlackBackground(){
		info("Declare variables");
		//get a pdf file
		fileToUpload = attachFile.getAttachFileByArrayTypeRandom(1);
		path=siteExPath.getSiteExpPathByIndex(0);
		nameDrive=siteExDrive.getSiteExpDriveByIndex(0);
			
		shareFileAndOpenPreviewMode(nameDrive,path,fileToUpload);
		
		info("Get a pixel on Black background");
		WebElement el = driver.findElement(By.xpath(".//*[@id='UIDocumentPreview']"));
		Point dis = el.getLocation();
		int x = dis.getX()+20;
		int y = dis.getY()+50;
		info("x is:"+x);
		info("y is:"+y);
		info("click on black background of Display area");
		Actions action = new Actions(driver);
		action.moveToElement(el,0,0).moveByOffset(x,y).click().build().perform();
				
		info("Verify that the preview is closed");
		waitForElementNotPresent(previewMode.ELEMENT_PREVIEW_MODE);
		info("Verify that is come back to Activity Stream");
		waitForAndGetElement(acStream.ELEMENT_ACTIVITY_DOCUMENT_IMAGE_VIEW_LINK.replace("${nameFile}",fileToUpload),3000,0);
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
	
	/**
	 * Open Site Explorer
	 * Open Collaboration drive
	 * Upload a file in acme/document
	 * @param nameDrive
	 * @param path
	 * @param file
	 *//*
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
	}*/
	
	/*private void shareFileAndOpenPreviewMode(String nameDrive,String path,String file){
		
		info("Go to Sites Explorer");
		info("nameDrive:"+nameDrive);
		info("path:"+path);
		info("file:"+file);
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
				
	}*/
}

package org.exoplatform.selenium.platform.ecms.acceptance;

import static org.exoplatform.selenium.TestLogger.info;

import java.util.regex.Pattern;

import org.exoplatform.selenium.platform.ecms.ContentAdministration;
import org.exoplatform.selenium.platform.ecms.ContextMenu;
import org.exoplatform.selenium.platform.ecms.ManageDrive;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

/** 
* By quynhpt
* data 27/11/2014
*/

public class Document_Preview_ECMSRendering extends Document_Preview_TestConfig {
	
	
	/**
	 * CONTENT_01:
	 * While previewing a content in ECMS, the comments area is not displayed anymore. 
	 * The comments area is displayed only for a preview from the Activity Stream.
	 */
	@Test
	public void test01_CheckCommentAreaInECMSWhilePreviewingAContent(){
		info("Declare variables");
		//get a pdf file
		fileToUpload = attachFile.getAttachFileByArrayTypeRandom(1);
		path=siteExPath.getSiteExpPathByIndex(0);
		nameDrive=siteExDrive.getSiteExpDriveByIndex(0);
		
		shareFileAndOpenPreviewMode(nameDrive,path,fileToUpload);
		
		info("Go to Activity stream of Intranet Home page");
		hp.goToHomePage();
		
		info("Open Preview mode");		
		acStream.openPreviewMode(fileToUpload,true);
		
		info("Verify that the preview is shown");
		waitForAndGetElement(previewMode.ELEMENT_PREVIEW_MODE,3000,0);
		
		info("Close The preview mode");
		previewMode.backASByClickCrossIcon();
		
		info("Go to Site Explorer");
		navToolbar.goToSiteExplorer();
		siteExp.clickWebView();
		siteExp.selectFileExplorer();
		
		info("Open the document in Site Explorer");
		siteExp.openDocumentFromRighPanel(fileToUpload);
		
		info("Verify that Preview Mode isnot shown");
		waitForElementNotPresent(previewMode.ELEMENT_PREVIEW_MODE);
		//deleteAllDataTest();
	}
	
	/**
	 * CONTENT_02: 
	 * A Download entry must also be added in the contextual menu (right click) for the node type nt:file 
	 * and available for all views. The entry is displayed between the actions Delete and Lock.
	 * For list and admin views (where the right click is disabled), 
	 * the Download button is added in the action bar when selecting a content.
	 */
	
	/**
	 * Check for Web View type
	 */
	@Test
	public void test02_CheckDownLoadEntryInContextualMenuForWebView(){
		info("Declare variables");
		//get a pdf file
		fileToUpload = attachFile.getAttachFileByArrayTypeRandom(1);
		path=siteExPath.getSiteExpPathByIndex(0);
		nameDrive=siteExDrive.getSiteExpDriveByIndex(0);
		
		shareFileAndOpenPreviewMode(nameDrive,path,fileToUpload);
		siteExp.clickWebView();
		siteExp.selectFileExplorer();
		info("Right click on the document");
		ContextMenu contMenu = rightClickOnElement(By.linkText(fileToUpload));
		
		info("Verify that Download entry is shown between Delete and Lock actions");
		waitForAndGetElement(contMenu.ELEMENT_DOWNLOAD_ENTRY_PREVIOUS_ENTRY_DELETE,3000,0);
		waitForAndGetElement(contMenu.ELEMENT_DOWNLOAD_ENTRY,3000,0);
		waitForAndGetElement(contMenu.ELEMENT_DOWNLOAD_ENTRY_NEXT_ENTRY_LOCK,3000,0);
		//deleteAllDataTest();
	}
	
	/**
	 * Check for Admin View type
	 */
	@Test
	public void test03_CheckDownLoadButtonInActionBarForAdminView(){
		
		info("Declare variables");
		//get a pdf file
		fileToUpload = attachFile.getAttachFileByArrayTypeRandom(1);
		path=siteExPath.getSiteExpPathByIndex(0);
		nameDrive=siteExDrive.getSiteExpDriveByIndex(0);
		
		shareFileAndOpenPreviewMode(nameDrive,path,fileToUpload);
		info("Open Admin view");
		siteExp.clickAdminView();
		
		String[] nameFile = fileToUpload.split(Pattern.quote("."));
		info("nameFile0: "+nameFile[0]);
		info("Select the document by tick on it's checkbox");
		check(siteExp.ELEMENT_CHECKBOX_FILE.replace("${fileName}",nameFile[0]));
		info("Verify that download button is shown on action bar");
		waitForAndGetElement(actBar.ELEMENT_DOWNLOAD_BUTTON,3000,0);
		
		siteExp.clickWebView();
		//deleteAllDataTest();
	}
	/**
	 * Check for Icon View type
	 */
	@Test
	public void test04_CheckDownLoadEntryInContextualMenuForIconView() {
		info("Declare variables");
		// get a pdf file
		fileToUpload = attachFile.getAttachFileByArrayTypeRandom(1);
		path = siteExPath.getSiteExpPathByIndex(0);
		nameDrive = siteExDrive.getSiteExpDriveByIndex(0);

		info("Go to Sites Explorer");
		navToolbar.goToSiteExplorer();
		info("Go to Collaboration drive");
		actBar.showDrives();
		siteExp.openADrive(nameDrive);

		if (waitForAndGetElement(siteExp.ELEMENT_ICONS_VIEW, 3000, 0) == null) {
			ContentAdministration contentAd = navToolbar.goToContentAdministration();
			ManageDrive magDrive = contentAd.goToManageDrive();
			magDrive.addViewForDrives("Icons", nameDrive);
			magAc.signOut();
			magAc.signIn(DATA_USER1, DATA_PASS);
		}
		
		shareFileAndOpenPreviewMode(nameDrive,path,fileToUpload);
		
		info("Open Icon view type");
		siteExp.clickIconView();
		siteExp.selectFileExplorer();
		info("Right click on the document");
		ContextMenu contMenu = rightClickOnElement(By.linkText(fileToUpload));

		info("Verify that Download entry is shown between Delete and Lock actions");
		waitForAndGetElement(contMenu.ELEMENT_DOWNLOAD_ENTRY_PREVIOUS_ENTRY_DELETE,3000,0);
		waitForAndGetElement(contMenu.ELEMENT_DOWNLOAD_ENTRY,3000,0);
		waitForAndGetElement(contMenu.ELEMENT_DOWNLOAD_ENTRY_NEXT_ENTRY_LOCK,3000,0);
		//deleteAllDataTest();
	}
	/**
	 * Check for List View type
	 */
	@Test
	public void test05_CheckDownLoadButtonInActionBarForListView(){
		info("Declare variables");
		//get a pdf file
		fileToUpload = attachFile.getAttachFileByArrayTypeRandom(1);
		path=siteExPath.getSiteExpPathByIndex(0);
		nameDrive=siteExDrive.getSiteExpDriveByIndex(0);
		
		info("Go to Sites Explorer");
		navToolbar.goToSiteExplorer();
		info("Go to Collaboration drive");
		actBar.showDrives();
		siteExp.openADrive(nameDrive);

		if (waitForAndGetElement(siteExp.ELEMENT_LIST_VIEW_ICON, 3000, 0) == null) {
			ContentAdministration contentAd = navToolbar.goToContentAdministration();
			ManageDrive magDrive = contentAd.goToManageDrive();
			magDrive.addViewForDrives("List", nameDrive);
			magAc.signOut();
			magAc.signIn(DATA_USER1, DATA_PASS);
		}
		
		shareFileAndOpenPreviewMode(nameDrive,path,fileToUpload);
		
		info("Open List view type");
		siteExp.clickListView();
		
		String[] nameFile = fileToUpload.split(Pattern.quote("."));
		info("nameFile0: "+nameFile[0]);
		info("Select the document by tick on it's checkbox");
		check(siteExp.ELEMENT_CHECKBOX_FILE.replace("${fileName}",nameFile[0]));
		info("Verify that download button is shown on action bar");
		waitForAndGetElement(actBar.ELEMENT_DOWNLOAD_BUTTON,3000,0);
		
		siteExp.clickWebView();
		//deleteAllDataTest();
	}
	/**
	 * Check for Categories View type
	 */
	@Test
	public void test06_CheckDownLoadEntryInContextualMenuForCategoriesView(){
		info("Declare variables");
		//get a pdf file
		fileToUpload = attachFile.getAttachFileByArrayTypeRandom(1);
		path=siteExPath.getSiteExpPathByIndex(0);
		nameDrive=siteExDrive.getSiteExpDriveByIndex(0);
		
		info("Go to Sites Explorer");
		navToolbar.goToSiteExplorer();
		info("Go to Collaboration drive");
		actBar.showDrives();
		siteExp.openADrive(nameDrive);

		if (waitForAndGetElement(siteExp.ELEMENT_CATEGORIES_VIEW, 3000, 0) == null) {
			ContentAdministration contentAd = navToolbar.goToContentAdministration();
			ManageDrive magDrive = contentAd.goToManageDrive();
			magDrive.addViewForDrives("Categories", nameDrive);
			magAc.signOut();
			magAc.signIn(DATA_USER1, DATA_PASS);
		}
		
		shareFileAndOpenPreviewMode(nameDrive,path,fileToUpload);

		info("Open Categories view type");
		siteExp.clickCategoriesView();
		siteExp.selectFileExplorer();
		info("Right click on the document");
		ContextMenu contMenu = rightClickOnElement(By.linkText(fileToUpload));
		
		info("Verify that Download entry is shown between Delete and Lock actions");
		waitForAndGetElement(contMenu.ELEMENT_DOWNLOAD_ENTRY_PREVIOUS_ENTRY_DELETE,3000,0);
		waitForAndGetElement(contMenu.ELEMENT_DOWNLOAD_ENTRY,3000,0);
		waitForAndGetElement(contMenu.ELEMENT_DOWNLOAD_ENTRY_NEXT_ENTRY_LOCK,3000,0);
				
		//deleteAllDataTest();
	}
	
	/**
	 * CONTENT_04:
	 * When no preview is available for a content in ECMS, the display area includes : 
	 * + A thumbnail of the content
	 * + The name of the content
	 * + A message to the user as defined in DISPLAY_07 and DISPLAY_08
	 * + a Download button (displayed below the no preview message) and not in the actions bars. 
	 * Clicking the download button will open the download popup.
	 * If applicable, an "Open..." button that allows to open the document with the corresponding desktop 
	 * app (cf Open in Office specification)
	 */
	/**
	 * Check message for no previewing available case
	 * @throws Exception 
	 */
	@Test
	public void test07_CheckMessageNoPreviewAvailableForContentInECMS() throws Exception{
		info("Declare variables");
		//get a file with not support format to view
		fileToUpload = attachFile.getAttachFileByArrayTypeRandom(25);
		path=siteExPath.getSiteExpPathByIndex(0);
		nameDrive=siteExDrive.getSiteExpDriveByIndex(0);
		
		shareFileAndOpenPreviewMode(nameDrive,path,fileToUpload);
		siteExp.clickWebView();
		siteExp.selectFileExplorer();
		info("Open the document in Site Explorer");
		siteExp.openDocumentFromRighPanel(fileToUpload);
		
		info("Vefiry that no preview avaible for the content");
		waitForAndGetElement(siteExp.ELEMENT_CONTENT_THUMBNAIL,3000,0);
		waitForAndGetElement(siteExp.ELEMENT_CONTENT_NAME.replace("${nameFile}",fileToUpload),3000,0);
		waitForAndGetElement(siteExp.ELEMENT_CONTENT_MESSAGE_NOT_AVAILABLE,3000,0);
		waitForAndGetElement(siteExp.ELEMENT_CONTENT_DOWNLOAD_BUTTON,3000,0);
		waitForAndGetElement(siteExp.ELEMENT_CONTENT_OPEN_DESKTOP,3000,0);
		
		info("Get dowload link");
		String url = waitForAndGetElement(siteExp.ELEMENT_CONTENT_DOWNLOAD_BUTTON,3000,0).getAttribute("href").toString();
		info("Check download link is working well");
		downloadHandler.downloadFile(url);
		//deleteAllDataTest();
		
	}
	/**
	 * Check message for many pages case
	 * @throws Exception 
	 */
	@Test
	public void test08_CheckMessageManyPagesForContentInECMS() throws Exception{
		info("Declare variables");
		//get a file with not support format to view
		fileToUpload = attachFile.getAttachFileByArrayTypeRandom(24);
		path=siteExPath.getSiteExpPathByIndex(0);
		nameDrive=siteExDrive.getSiteExpDriveByIndex(0);
		
		shareFileAndOpenPreviewMode(nameDrive,path,fileToUpload);
		siteExp.clickWebView();
		siteExp.selectFileExplorer();
		info("Open the document in Site Explorer");
		siteExp.openDocumentFromRighPanel(fileToUpload);
		
		info("Vefiry that no preview avaible for the content");
		waitForAndGetElement(siteExp.ELEMENT_CONTENT_THUMBNAIL,3000,0);
		waitForAndGetElement(siteExp.ELEMENT_CONTENT_NAME.replace("${nameFile}",fileToUpload),3000,0);
		waitForAndGetElement(siteExp.ELEMENT_CONTENT_MESSAGE_TOO_MANY_PAGES,3000,0);
		waitForAndGetElement(siteExp.ELEMENT_CONTENT_DOWNLOAD_BUTTON,3000,0);
		waitForAndGetElement(siteExp.ELEMENT_CONTENT_OPEN_DESKTOP,3000,0);
		
		info("Get dowload link");
		String url = waitForAndGetElement(siteExp.ELEMENT_CONTENT_DOWNLOAD_BUTTON,3000,0).getAttribute("href").toString();
		info("Check download link is working well");
		downloadHandler.downloadFile(url);
		//deleteAllDataTest();
	}
	/**
	 * Check message for over size case
	 * @throws Exception 
	 */
	@Test
	public void test09_CheckMessageOverSizeForContentInECMS() throws Exception{
		info("Declare variables");
		//get a file with not support format to view
		fileToUpload = attachFile.getAttachFileByArrayTypeRandom(23);
		path=siteExPath.getSiteExpPathByIndex(0);
		nameDrive=siteExDrive.getSiteExpDriveByIndex(0);
		
		shareFileAndOpenPreviewMode(nameDrive,path,fileToUpload);
		siteExp.clickWebView();
		siteExp.selectFileExplorer();
		info("Open the document in Site Explorer");
		siteExp.openDocumentFromRighPanel(fileToUpload);
		
		info("Vefiry that no preview avaible for the content");
		waitForAndGetElement(siteExp.ELEMENT_CONTENT_THUMBNAIL,3000,0);
		waitForAndGetElement(siteExp.ELEMENT_CONTENT_NAME.replace("${nameFile}",fileToUpload),3000,0);
		waitForAndGetElement(siteExp.ELEMENT_CONTENT_MESSAGE_OVER_SIZE,3000,0);
		waitForAndGetElement(siteExp.ELEMENT_CONTENT_DOWNLOAD_BUTTON,3000,0);
		waitForAndGetElement(siteExp.ELEMENT_CONTENT_OPEN_DESKTOP,3000,0);
		
		info("Get dowload link");
		String url = waitForAndGetElement(siteExp.ELEMENT_CONTENT_DOWNLOAD_BUTTON,3000,0).getAttribute("href").toString();
		info("Check download link is working well");
		downloadHandler.downloadFile(url);
		//deleteAllDataTest();
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
		
	}*/
	/**
	 * Open Site Explorer
	 * Open Collaboration drive
	 * Upload a file in acme/document
	 * @param nameDrive
	 * @param path
	 * @param file
	 */
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
	}
	
}

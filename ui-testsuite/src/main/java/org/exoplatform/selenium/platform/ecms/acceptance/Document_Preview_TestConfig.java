package org.exoplatform.selenium.platform.ecms.acceptance;

import static org.exoplatform.selenium.TestLogger.info;

import java.util.concurrent.TimeUnit;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.DownloadFileControl;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.ActivityStream;
import org.exoplatform.selenium.platform.HomePagePlatform;
import org.exoplatform.selenium.platform.ManageLogInOut;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.ecms.ActionBar;
import org.exoplatform.selenium.platform.ecms.DocumentPreview;
import org.exoplatform.selenium.platform.ecms.ManageTemplate;
import org.exoplatform.selenium.platform.ecms.SitesExplorer;
import org.exoplatform.selenium.platform.objectdatabase.common.AttachmentFileDatabase;
import org.exoplatform.selenium.platform.objectdatabase.common.DataTestPathDatabase;
import org.exoplatform.selenium.platform.objectdatabase.common.TextBoxDatabase;
import org.exoplatform.selenium.platform.objectdatabase.documentpreview.PreviewContentFileDatabase;
import org.exoplatform.selenium.platform.objectdatabase.documentpreview.PreviewMessageSearchDatabase;
import org.exoplatform.selenium.platform.objectdatabase.documentpreview.PreviewTextSearchDatabase;
import org.exoplatform.selenium.platform.objectdatabase.documentpreview.VideoLinksDatabase;
import org.exoplatform.selenium.platform.objectdatabase.siteExplorer.SiteExplorerDriveDatabase;
import org.exoplatform.selenium.platform.objectdatabase.siteExplorer.SiteExplorerPathDatabase;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

/** 
 * By quynhpt
 * data 27/11/2014
 */
public class Document_Preview_TestConfig extends PlatformBase {
	ActivityStream acStream;
	ManageLogInOut magAc;
	ActionBar actBar;
	Button but;
	NavigationToolbar navToolbar;
	SitesExplorer siteExp;
	DocumentPreview previewMode;
	HomePagePlatform hp;
	ManageTemplate mnTemplate;
	DownloadFileControl downloadHandler;
	VideoLinksDatabase videoLinksFile;
	 
	
	TextBoxDatabase txData;
	AttachmentFileDatabase attachFile;
	SiteExplorerDriveDatabase siteExDrive;
	SiteExplorerPathDatabase siteExPath;
	DataTestPathDatabase dataTestPath;
	PreviewContentFileDatabase textContentFile;
	PreviewMessageSearchDatabase mesgSearch;
	PreviewTextSearchDatabase textSearch;
	
	String datTestPath;
	String fileToUpload;
	String path;
	String nameDrive;
	
	String file;
	String videoLink;
	String nameDriveSE;
	String pathSE;
	
	boolean deleteActive=false;
	
	@BeforeClass
	public void setUpBeforeClass() throws Exception{
		initSeleniumTest();
		getDefaultUserPass(userDataFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);

		magAc = new ManageLogInOut(driver,this.plfVersion);
		acStream = new ActivityStream(driver,this.plfVersion);
		button = new Button(driver, this.plfVersion);
		navToolbar = new NavigationToolbar(driver);
		actBar = new ActionBar(driver, this.plfVersion);
		previewMode = new DocumentPreview(driver, this.plfVersion);
		hp = new HomePagePlatform(driver);
		siteExp = new SitesExplorer(driver, this.plfVersion);
		mnTemplate = new ManageTemplate(driver, this.plfVersion);
		downloadHandler = new DownloadFileControl(driver);

		attachFile = new AttachmentFileDatabase();
		siteExDrive = new SiteExplorerDriveDatabase();
		siteExPath = new SiteExplorerPathDatabase();
		dataTestPath = new DataTestPathDatabase();
		txData = new TextBoxDatabase();
		mesgSearch = new PreviewMessageSearchDatabase();
		textSearch = new PreviewTextSearchDatabase();
		mesgSearch.setMesgSearchData(previewMesgSearchPath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		textSearch.setTextSearchData(previewTextSearchPath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		
		videoLinksFile = new VideoLinksDatabase();
		videoLinksFile.setVideoLinksData(videoLinksFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);

		textContentFile = new PreviewContentFileDatabase();
		textContentFile.setTextContentFileData(previewContentFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		
		//set data driver to get the test data
		attachFile.setAttachFileData(attachmentFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		siteExDrive.setSiteExpDriveData(siteExpDrivePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		siteExPath.setSiteExpPathData(siteExpPathPath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		dataTestPath.setDataTestPathData(dataTestFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		datTestPath = dataTestPath.getDataTestPathByIndex(0);
		txData.setContentData(texboxFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		
		datTestPath = dataTestPath.getDataTestPathByIndex(0);
		info("datTestPath:"+datTestPath);
		magAc.signIn(DATA_USER1, DATA_PASS);
		
		info("login successfully");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		info("End of before class");
	}

	@BeforeMethod
	public void beforeMethod(){
		path ="";
		nameDrive="";
		file="";
		fileToUpload="";
		videoLink="";
		pathSE ="";
		nameDriveSE="";
		
	}

	@AfterMethod
	public void afterMethod(){
		if(deleteActive==false)
		deleteAllDataTest(nameDrive,path);
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		deleteActive=false;
	}
	
	
	@AfterClass
	public void afterClass(){
		driver.manage().deleteAllCookies();
		driver.quit();
	}
	
	/**
	 * Delete all data test
	 */
	private void deleteAllDataTest(String nameDrive,String path){
		info("Delete data test");
		//Go to Site Explorer
		this.driver.get(baseUrl);
		Utils.pause(2000);
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
}

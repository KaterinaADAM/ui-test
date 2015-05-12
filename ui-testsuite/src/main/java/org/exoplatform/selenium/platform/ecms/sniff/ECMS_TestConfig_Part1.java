package org.exoplatform.selenium.platform.ecms.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.DownloadFileControl;
import org.exoplatform.selenium.platform.ActivityStream;
import org.exoplatform.selenium.platform.HomePagePlatform;
import org.exoplatform.selenium.platform.ManageLogInOut;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.acme.AcmeHomePage;
import org.exoplatform.selenium.platform.administration.ChangeLanguages;
import org.exoplatform.selenium.platform.administration.ContentAdministration;
import org.exoplatform.selenium.platform.ecms.CreateNewDocument;
import org.exoplatform.selenium.platform.ecms.DocumentPreview;
import org.exoplatform.selenium.platform.ecms.SEOManagement;
import org.exoplatform.selenium.platform.objectdatabase.common.AttachmentFileDatabase;
import org.exoplatform.selenium.platform.objectdatabase.common.ChangeLanguageDatabase;
import org.exoplatform.selenium.platform.objectdatabase.common.DataTestPathDatabase;
import org.exoplatform.selenium.platform.ecms.SiteExplorerHome;
import org.exoplatform.selenium.platform.gatein.ContentDetail;
import org.exoplatform.selenium.platform.gatein.ContentList;
import org.exoplatform.selenium.platform.gatein.NavigationManagement;
import org.exoplatform.selenium.platform.gatein.PortalManageSites;
import org.exoplatform.selenium.platform.gatein.PageCreationWizard;
import org.exoplatform.selenium.platform.gatein.PortalManagePages;
import org.exoplatform.selenium.platform.objectdatabase.common.TextBoxDatabase;
import org.exoplatform.selenium.platform.objectdatabase.ecms.SiteExplorerContentTypesDatabase;
import org.exoplatform.selenium.platform.objectdatabase.ecms.SiteExplorerDriveDatabase;
import org.exoplatform.selenium.platform.objectdatabase.ecms.SiteExplorerPathDatabase;
import org.exoplatform.selenium.platform.objectdatabase.ecms.VideoLinksDatabase;
import org.exoplatform.selenium.platform.objectdatabase.user.UserDatabase;
import org.testng.annotations.*;


public class ECMS_TestConfig_Part1 extends PlatformBase{

	HomePagePlatform hp;
	ManageLogInOut magAc;
	NavigationToolbar navTool;

	ContentAdministration caPage;
	SiteExplorerHome SEHome;
	CreateNewDocument CreNewDoc;
	DocumentPreview docPrev;
	ActivityStream acStream;
	
	
	AcmeHomePage acmeHP;
	PortalManagePages paMang;
	ChangeLanguages changLang;
	PortalManageSites maSite;
	PageCreationWizard pagCW;
	ContentList contList;
	ContentDetail contDetail;
	SEOManagement seoMang;
	NavigationManagement navMag;
	
	TextBoxDatabase txData;
	UserDatabase userData;
	ChangeLanguageDatabase changLangData;
	AttachmentFileDatabase attachFile;
	SiteExplorerDriveDatabase siteExDrive;
	SiteExplorerPathDatabase siteExPath;
	DataTestPathDatabase dataTestPath;
	SiteExplorerContentTypesDatabase siteExContentType;
	VideoLinksDatabase videoLinksFile;
	
	DownloadFileControl downloadHandler;
	
	String folderDataTestPath;
		
	@BeforeClass
	public void setUpBeforeClass() throws Exception{
		info("Start setUpBeforeClass");
		initSeleniumTest();
		getDefaultUserPass(userDataFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		magAc = new ManageLogInOut(driver);
		hp = new HomePagePlatform(driver);
		magAc.signIn(DATA_USER1, DATA_PASS);
		
		SEHome = new SiteExplorerHome(driver);
		CreNewDoc = new CreateNewDocument(driver);
		caPage = new ContentAdministration(driver);
		navTool = new NavigationToolbar(driver);
		paMang = new PortalManagePages(driver);
		pagCW = new PageCreationWizard(driver);
		contList = new ContentList(driver);
		contDetail = new ContentDetail(driver);
		seoMang = new SEOManagement(driver);
		changLang = new ChangeLanguages(driver);
		maSite = new PortalManageSites(driver);
		acmeHP = new AcmeHomePage(driver);
		acStream = new ActivityStream(driver);
		
		docPrev = new DocumentPreview(driver);
		navMag = new NavigationManagement(driver);
		
		txData = new TextBoxDatabase();
		userData = new UserDatabase();
		changLangData = new ChangeLanguageDatabase();
		changLangData.setChangeLanguageData(changLangDataPath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		userData.setUserData(userDataFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		txData.setContentData(texboxFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlContent);
		
		attachFile = new AttachmentFileDatabase();
		siteExDrive = new SiteExplorerDriveDatabase();
		siteExPath = new SiteExplorerPathDatabase();
		dataTestPath = new DataTestPathDatabase();
		attachFile.setAttachFileData(attachmentFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		siteExDrive.setSiteExpDriveData(siteExpDrivePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		siteExPath.setSiteExpPathData(siteExpPathPath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		dataTestPath.setDataTestPathData(dataTestFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		
		siteExContentType = new SiteExplorerContentTypesDatabase();
		siteExContentType.setData(siteExpContentTypeFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		
		videoLinksFile = new VideoLinksDatabase();
		videoLinksFile.setVideoLinksData(videoLinksFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		
		downloadHandler = new DownloadFileControl(driver);
		
		folderDataTestPath = dataTestPath.getDataTestPathByIndex(1);
		info("datTestPath:"+folderDataTestPath);
		
		info("End setUpBeforeClass");
	}

	@AfterClass
	public void afterClass(){
		info("Start afterClass");
		driver.manage().deleteAllCookies();
		driver.quit();
		info("End afterClass");
	}
	
}
package org.exoplatform.selenium.platform.social.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.platform.ConnectionsManagement;
import org.exoplatform.selenium.platform.HomePagePlatform;
import org.exoplatform.selenium.platform.ActivityStream;
import org.exoplatform.selenium.platform.ManageLogInOut;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.objectdatabase.common.AttachmentFileDatabase;
import org.exoplatform.selenium.platform.objectdatabase.common.TextBoxDatabase;
import org.exoplatform.selenium.platform.objectdatabase.social.SpaceApplicationDatabase;
import org.exoplatform.selenium.platform.social.SpaceHomePage;
import org.exoplatform.selenium.platform.social.SpaceManagement;
import org.exoplatform.selenium.platform.social.SpaceSettingManagement;
import org.exoplatform.selenium.platform.wiki.WikiHomePage;
import org.exoplatform.selenium.platform.wiki.WikiManagement;
import org.exoplatform.selenium.platform.ecms.CreateNewDocument;
import org.exoplatform.selenium.platform.ecms.SiteExplorerHome;
import org.exoplatform.selenium.platform.objectdatabase.common.LinksDatabase;
import org.exoplatform.selenium.platform.objectdatabase.ecms.SiteExplorerDriveDatabase;
import org.exoplatform.selenium.platform.objectdatabase.ecms.SiteExplorerPathDatabase;
import org.exoplatform.selenium.platform.social.MyProfilePage;
import org.exoplatform.selenium.platform.wiki.RichTextEditor;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.thoughtworks.selenium.Selenium;

public class SOC_TestConfig extends PlatformBase {
	HomePagePlatform hp;
	
	
	WikiHomePage wHome;
	WikiManagement wikiMg;
	
	SpaceApplicationDatabase spAppData;
	ActivityStream hpAct;
	ManageLogInOut magAc;
	MyProfilePage myProfile;

	NavigationToolbar navTool;
	SiteExplorerHome SEHome;
	RichTextEditor rtMode;
	
	SpaceManagement spaMg;
	SpaceHomePage spaHome;
	
	CreateNewDocument CreNewDoc;
	TextBoxDatabase txData;
	AttachmentFileDatabase fData;
	LinksDatabase lnkData;
	
	SiteExplorerDriveDatabase siteExDrive;
	SiteExplorerPathDatabase siteExPath;
	
	Button button;
	ConnectionsManagement connMag;
	SpaceSettingManagement setSpaceMg;
	MyProfilePage myProfilPage;
	
	@BeforeClass
	public void setUpBeforeClass() throws Exception{
		info("Start setUpBeforeClass");
	//	initSeleniumTest();
		initRemoteWebDriverFF();
		getDefaultUserPass(userDataFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		magAc = new ManageLogInOut(driver);
		magAc.signIn(DATA_USER1, DATA_PASS);
		myProfile = new MyProfilePage(driver);
		
		navTool = new NavigationToolbar(driver);
		SEHome = new SiteExplorerHome(driver);
		CreNewDoc = new CreateNewDocument(driver);
		connMag = new ConnectionsManagement(driver);
		setSpaceMg = new SpaceSettingManagement(driver);
		myProfilPage = new MyProfilePage(driver);
		
		hp = new HomePagePlatform(driver);
		
		wHome = new WikiHomePage(driver);
		wikiMg = new WikiManagement(driver);
		
		txData = new TextBoxDatabase();
		txData.setContentData(texboxFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		
		spAppData = new SpaceApplicationDatabase();
		spAppData.setAppData(spaceappFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		
		fData = new AttachmentFileDatabase();
		fData.setAttachFileData(attachmentFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		
		hpAct = new ActivityStream(driver);
		
		rtMode = new RichTextEditor(driver);
		spaMg = new SpaceManagement(driver);
		spaHome = new SpaceHomePage(driver);
		
		lnkData = new LinksDatabase();
		lnkData.setLinkData(linkPath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		
		siteExDrive = new SiteExplorerDriveDatabase();
		siteExPath = new SiteExplorerPathDatabase();
		
		siteExDrive.setSiteExpDriveData(siteExpDrivePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		siteExPath.setSiteExpPathData(siteExpPathPath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		
		button = new Button(driver);
		
		info("End setUpBeforeClass");
	}
	
	@AfterClass
	public void afterTest(){
		info("Start setUpBeforeClass");
		driver.manage().deleteAllCookies();
		driver.quit();
		info("End setUpBeforeClass");
	}

}

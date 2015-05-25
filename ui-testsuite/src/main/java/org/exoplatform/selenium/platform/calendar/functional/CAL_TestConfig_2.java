package org.exoplatform.selenium.platform.calendar.functional;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.ActivityStream;
import org.exoplatform.selenium.platform.HomePagePlatform;
import org.exoplatform.selenium.platform.ManageLogInOut;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.calendar.CalendarHomePage;
import org.exoplatform.selenium.platform.calendar.CalendarManagement;
import org.exoplatform.selenium.platform.calendar.EventManagement;
import org.exoplatform.selenium.platform.calendar.TaskManagement;
import org.exoplatform.selenium.platform.gatein.UserAddManagement;
import org.exoplatform.selenium.platform.gatein.UserAndGroupManagement;
import org.exoplatform.selenium.platform.objectdatabase.calendar.CalendarCommentsDatabase;
import org.exoplatform.selenium.platform.objectdatabase.calendar.CalendarGroupDatabase;
import org.exoplatform.selenium.platform.objectdatabase.calendar.CalendarTabsDatabase;
import org.exoplatform.selenium.platform.objectdatabase.common.AttachmentFileDatabase;
import org.exoplatform.selenium.platform.objectdatabase.common.TextBoxDatabase;
import org.exoplatform.selenium.platform.objectdatabase.common.UserInfoDatabase;
import org.exoplatform.selenium.platform.social.MyProfilePage;
import org.exoplatform.selenium.platform.social.NotificationsAdminSeting;
import org.exoplatform.selenium.platform.social.SpaceHomePage;
import org.exoplatform.selenium.platform.social.SpaceManagement;
import org.exoplatform.selenium.platform.social.SpaceSettingManagement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class CAL_TestConfig_2 extends PlatformBase {
	HomePagePlatform hp;
	ActivityStream hpAct;
	ManageLogInOut magAc;
	Button button;

	NavigationToolbar navTool;
	EventManagement evMg;
	TaskManagement tasMg;
	CalendarManagement cMang;
	CalendarHomePage cHome;
	SpaceManagement spaMg;
	SpaceHomePage spaHome;
	SpaceSettingManagement setSpaceMg;
	
	UserAddManagement addUserPage;
	UserAndGroupManagement userAndGroup;
	NotificationsAdminSeting emailNotif;
	MyProfilePage myProPg;
	
	TextBoxDatabase txData;
	CalendarGroupDatabase cGroupData;
	CalendarTabsDatabase cTabData;
	CalendarCommentsDatabase cCommentData;
	AttachmentFileDatabase fData;
	UserInfoDatabase userInfoData;
	String username;
	String firstname;
	
	@BeforeMethod
	public void setUpBeforeMethod() throws Exception{
		info("Start setUpBeforeMethod");
		initSeleniumTest();
		getDefaultUserPass(userDataFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		magAc = new ManageLogInOut(driver);
		button = new Button(driver);
		magAc.signIn(DATA_USER1, DATA_PASS);
		Utils.pause(2000);
		
		navTool = new NavigationToolbar(driver);
		hp = new HomePagePlatform(driver);
		hpAct = new ActivityStream(driver);
		spaMg = new SpaceManagement(driver);
		spaHome = new SpaceHomePage(driver);
		setSpaceMg = new SpaceSettingManagement(driver);
		myProPg = new MyProfilePage(driver);
		
		addUserPage = new UserAddManagement(driver);
		userAndGroup = new UserAndGroupManagement(driver);
		emailNotif = new NotificationsAdminSeting(driver);
		
		evMg = new EventManagement(driver);
		cMang = new CalendarManagement(driver);
		cHome = new CalendarHomePage(driver);
		tasMg = new TaskManagement(driver);
		
		txData = new TextBoxDatabase();
		txData.setContentData(texboxFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);

		cGroupData = new CalendarGroupDatabase();
		cGroupData.setData(calGroupNameFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		
		cTabData = new CalendarTabsDatabase();
		cTabData.setData(calTabNameFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		
		cCommentData = new CalendarCommentsDatabase();
		cCommentData.setData(calCommentsFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);

		userInfoData = new UserInfoDatabase();
		userInfoData.setUserInfoData(userInfoFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		
		
		fData = new AttachmentFileDatabase();
		fData.setAttachFileData(attachmentFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		username="test";
		info("End setUpBeforeMethod");
	}

	@AfterMethod
	public void afterMethod(){
		info("Start afterMethod");
		driver.manage().deleteAllCookies();
		driver.quit();
		info("End afterMethod");
	}
	
	

}

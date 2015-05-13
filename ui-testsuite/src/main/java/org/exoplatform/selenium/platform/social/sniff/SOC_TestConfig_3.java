package org.exoplatform.selenium.platform.social.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.platform.ConnectionsManagement;
import org.exoplatform.selenium.platform.HomePagePlatform;
import org.exoplatform.selenium.platform.ActivityStream;
import org.exoplatform.selenium.platform.ManageLogInOut;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.objectdatabase.common.MailSuffixDatabase;
import org.exoplatform.selenium.platform.objectdatabase.common.TextBoxDatabase;
import org.exoplatform.selenium.platform.objectdatabase.common.UserInfoDatabase;
import org.exoplatform.selenium.platform.objectdatabase.social.NotificationDescriptionDatabase;
import org.exoplatform.selenium.platform.objectdatabase.user.UserDatabase;
import org.exoplatform.selenium.platform.social.IntranetNotification;
import org.exoplatform.selenium.platform.social.MyNotificationsSetting.myNotiType;
import org.exoplatform.selenium.platform.social.NotificationsAdminSeting;
import org.exoplatform.selenium.platform.social.MyNotificationsSetting;
import org.exoplatform.selenium.platform.social.SendEmailNotifications;
import org.exoplatform.selenium.platform.social.AllNotificationPage;
import org.exoplatform.selenium.platform.social.SpaceHomePage;
import org.exoplatform.selenium.platform.social.SpaceManagement;
import org.exoplatform.selenium.platform.social.SpaceSettingManagement;
import org.exoplatform.selenium.platform.gatein.UserAddManagement;
import org.exoplatform.selenium.platform.gatein.UserAndGroupManagement;
import org.exoplatform.selenium.platform.social.MyProfilePage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class SOC_TestConfig_3 extends PlatformBase {
	HomePagePlatform hp;
	MailSuffixDatabase mailSuffixData;
	ActivityStream hpAct;
	ManageLogInOut magAc;
	MyProfilePage myProfile;
	AllNotificationPage AllInTraNot;
	IntranetNotification intraNot;
	

	NavigationToolbar navTool;

	SpaceManagement spaMg;
	SpaceHomePage spaHome;
	TextBoxDatabase txData;
	UserDatabase userData;
	UserInfoDatabase userInfoData;

	NotificationDescriptionDatabase notiDes;

	ConnectionsManagement connMag;
	SpaceSettingManagement setSpaceMg;
	UserAddManagement addUserPage;
	UserAndGroupManagement userAndGroup;
	NotificationsAdminSeting emailNotif;
	MyNotificationsSetting myNotifPage;
	MyProfilePage myProfil;
	

	SendEmailNotifications notiEmail;
	
	@BeforeClass
	public void setUpBeforeClass() throws Exception{
		info("Start setUpBeforeClass");
		initSeleniumTest();
		getDefaultUserPass(userDataFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		magAc = new ManageLogInOut(driver);
		magAc.signIn(DATA_USER1, DATA_PASS);
		myProfile = new MyProfilePage(driver);
		navTool = new NavigationToolbar(driver);
		connMag = new ConnectionsManagement(driver);
		setSpaceMg = new SpaceSettingManagement(driver);
		emailNotif = new NotificationsAdminSeting(driver);
		hp = new HomePagePlatform(driver);
		AllInTraNot = new AllNotificationPage(driver);
		intraNot = new IntranetNotification(driver);
		notiEmail = new SendEmailNotifications(driver);
		
		txData = new TextBoxDatabase();
		txData.setContentData(texboxFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);

		hpAct = new ActivityStream(driver);
		spaMg = new SpaceManagement(driver);
		spaHome = new SpaceHomePage(driver);

		userData = new UserDatabase();
		userData.setUserData(userDataFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);

		addUserPage = new UserAddManagement(driver);
		userAndGroup = new UserAndGroupManagement(driver);
		myProfil = new MyProfilePage(driver);
		myNotifPage= new MyNotificationsSetting(driver);

		mailSuffixData = new MailSuffixDatabase();
		mailSuffixData.setMailSuffixData(mailSuffixFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		notiDes = new NotificationDescriptionDatabase();
		notiDes.setData(notiDesFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		userInfoData = new UserInfoDatabase();
		userInfoData.setUserInfoData(userInfoFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		
		info("Enable like and new user notifications");
		navTool.goToMyNotifications();
		myNotifPage.enableNotification(myNotiType.NewUser_intranet);
		myNotifPage.enableNotification(myNotiType.Like_intranet);
		
		info("End setUpBeforeClass");
	}

	@AfterClass
	public void afterTest(){
		info("Start setUpBeforeClass");
		isDriver=true;
		driver.manage().deleteAllCookies();
		driver.quit();
		info("End setUpBeforeClass");
	}

}

package org.exoplatform.selenium.platform.gatein.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.platform.HomePagePlatform;
import org.exoplatform.selenium.platform.ManageLogInOut;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.administration.ChangeLanguages;
import org.exoplatform.selenium.platform.gatein.ApplicationRegistry;
import org.exoplatform.selenium.platform.gatein.GadgetManagement;
import org.exoplatform.selenium.platform.gatein.MyDashBoard;
import org.exoplatform.selenium.platform.gatein.NavigationManagement;
import org.exoplatform.selenium.platform.gatein.PageCreationWizard;
import org.exoplatform.selenium.platform.gatein.PortalManagePages;
import org.exoplatform.selenium.platform.gatein.PortalManageSites;
import org.exoplatform.selenium.platform.gatein.PortalGroupNavigation;
import org.exoplatform.selenium.platform.gatein.UserAddManagement;
import org.exoplatform.selenium.platform.gatein.UserAndGroupManagement;
import org.exoplatform.selenium.platform.objectdatabase.common.LanguageDatabase;
import org.exoplatform.selenium.platform.objectdatabase.common.MailSuffixDatabase;
import org.exoplatform.selenium.platform.objectdatabase.common.PermissionDatabase;
import org.exoplatform.selenium.platform.objectdatabase.common.TextBoxDatabase;
import org.exoplatform.selenium.platform.objectdatabase.common.UserInfoDatabase;
import org.exoplatform.selenium.platform.objectdatabase.gatein.AppAddGateinDatabase;
import org.exoplatform.selenium.platform.objectdatabase.gatein.AppListGateinDatabase;
import org.exoplatform.selenium.platform.objectdatabase.gatein.ApplicationLayoutDatabase;
import org.exoplatform.selenium.platform.objectdatabase.gatein.CategoriesGateinDatabase;
import org.exoplatform.selenium.platform.objectdatabase.gatein.ContainersDatabase;
import org.exoplatform.selenium.platform.objectdatabase.gatein.CreateNewGateinDatabase;
import org.exoplatform.selenium.platform.objectdatabase.gatein.DefaultGroupDatabase;
import org.exoplatform.selenium.platform.objectdatabase.gatein.GadgetsGateinDatabase;
import org.exoplatform.selenium.platform.objectdatabase.gatein.GateinNodesDatabase;
import org.exoplatform.selenium.platform.objectdatabase.gatein.GateinPortalDefaultDatabase;
import org.exoplatform.selenium.platform.objectdatabase.gatein.GateinPortalGroupsPermissionDatabase;
import org.exoplatform.selenium.platform.objectdatabase.gatein.GateinPortalMemberShipsPermissionDatabase;
import org.exoplatform.selenium.platform.objectdatabase.gatein.PagesManagementListDatabase;
import org.exoplatform.selenium.platform.objectdatabase.gatein.GroupNameDatabase;
import org.exoplatform.selenium.platform.objectdatabase.gatein.RemoteGadgetDatabase;
import org.exoplatform.selenium.platform.objectdatabase.gatein.UserSearchOptionDatabase;
import org.exoplatform.selenium.platform.social.MyProfilePage;
import org.exoplatform.selenium.platform.social.SpaceHomePage;
import org.exoplatform.selenium.platform.social.SpaceManagement;
import org.exoplatform.selenium.platform.social.SpaceSettingManagement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class GateIn_TestConfig extends PlatformBase {
	ManageLogInOut magAc;
	HomePagePlatform hp;
	NavigationToolbar navToolBar;
	SpaceManagement spaMg;
	SpaceHomePage spaHome;
	SpaceSettingManagement setSpaceMg;

	PortalManagePages portMg;
	PortalManageSites portSite;
	NavigationManagement navMag;

	MyProfilePage myProf;
	ChangeLanguages changeLang;
	Button but;
	PortalGroupNavigation groupManage;
	ManageAlert magAlert;
	
	UserAddManagement addUserPage;
	UserAndGroupManagement userAndGroup;
	
	PageCreationWizard pagCW;
	
	MyDashBoard myDash;
	
	TextBoxDatabase txData;
	GadgetsGateinDatabase gadData;
	ContainersDatabase contaiData;
	ApplicationLayoutDatabase appLayData;
	RemoteGadgetDatabase remoteGadData;
	GroupNameDatabase groupNameData;
	
	GateinPortalDefaultDatabase portDeftData;
	GateinPortalGroupsPermissionDatabase portGroupPermisData;
	GateinPortalMemberShipsPermissionDatabase portMemPermisData;
	PermissionDatabase permissData;
	DefaultGroupDatabase defaultGroupData;
	GateinNodesDatabase gateinNodesData;
	
	UserInfoDatabase userInfoData;
	UserSearchOptionDatabase userSearchOptionData;
	MailSuffixDatabase mailSuffixData;
	GadgetManagement gadMg;
	
	ApplicationRegistry appRegistry;
	AppListGateinDatabase appGateInData;
	AppAddGateinDatabase appAddGateinData;
	CreateNewGateinDatabase creatGateinData;
	CategoriesGateinDatabase cateGateinData;
	PagesManagementListDatabase pagMgListData;
	LanguageDatabase langData;
	
	
	@BeforeMethod
	public void setUpBeforeClass() throws Exception{
		info("Start setUpBeforeClass");
		initSeleniumTest();
		getDefaultUserPass(userDataFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		
		magAc = new ManageLogInOut(driver);
		magAc.signIn(DATA_USER1, DATA_PASS);
		spaMg = new SpaceManagement(driver);
		spaHome = new SpaceHomePage(driver);
		setSpaceMg = new SpaceSettingManagement(driver);
		hp = new HomePagePlatform(driver);
		navToolBar = new NavigationToolbar(driver);
		myDash = new MyDashBoard(driver);

		portMg = new PortalManagePages(driver);
		portSite = new PortalManageSites(driver);
		groupManage = new PortalGroupNavigation(driver);
		magAlert = new ManageAlert(driver, this.plfVersion);
		navMag = new NavigationManagement(driver);
		
		myProf = new MyProfilePage(driver);
		changeLang = new ChangeLanguages(driver);
		but = new Button(driver, this.plfVersion);
		addUserPage = new UserAddManagement(driver);
		
		userAndGroup = new UserAndGroupManagement(driver);
		pagCW = new PageCreationWizard(driver);
		
		
		txData = new TextBoxDatabase();
		txData.setContentData(texboxFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		gadData = new GadgetsGateinDatabase();
		gadData.setGadgetsGateinData(gadgetFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		
		contaiData = new ContainersDatabase();
		contaiData.setContainersData(containerFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		
		appLayData = new ApplicationLayoutDatabase();
		appLayData.setApplicationLayoutData(appLayoutFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		
		remoteGadData = new RemoteGadgetDatabase();
		remoteGadData.setRemoteGadgetData(remoteGadgetDataFilePath, defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		
		gadMg = new GadgetManagement(driver);
		
		appRegistry = new ApplicationRegistry(driver);
		appGateInData = new AppListGateinDatabase();
		appGateInData.setApplicationGateinData(appListGateinFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
	
		appAddGateinData = new AppAddGateinDatabase();
		appAddGateinData.setAppAddGateinData(appAddGateinFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		
		creatGateinData = new CreateNewGateinDatabase();
		creatGateinData.setGateinData(createNewGateinFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		
		cateGateinData = new CategoriesGateinDatabase();
		cateGateinData.setGateinData(categoriesGateinFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		
		pagMgListData = new PagesManagementListDatabase();
		pagMgListData.setPagesListData(pageMagListFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		
		txData = new TextBoxDatabase();
		txData.setContentData(texboxFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		
		portDeftData = new GateinPortalDefaultDatabase();
		portDeftData.setData(portalDefaultFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		
		portGroupPermisData = new GateinPortalGroupsPermissionDatabase();
		portGroupPermisData.setData(portalPermisGroupFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		
		portMemPermisData = new GateinPortalMemberShipsPermissionDatabase();
		portMemPermisData.setData(portalPermisMemFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		
		userInfoData = new UserInfoDatabase();
		userInfoData.setUserInfoData(userInfoFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		
		userSearchOptionData = new UserSearchOptionDatabase();
		userSearchOptionData.setUserSearchOptionData(userSearchOptionFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);

		mailSuffixData = new MailSuffixDatabase();
		mailSuffixData.setMailSuffixData(mailSuffixFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		
		permissData = new PermissionDatabase();
		permissData.setDesData(permissionPath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlContent);
		
		langData = new LanguageDatabase();
		langData.setLanguageData(languageFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlContent);
		groupNameData = new GroupNameDatabase();
		groupNameData.setGroupNameData(groupNameDataFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		
		defaultGroupData = new DefaultGroupDatabase();
		defaultGroupData.setDefaulGroupData(gateinDefaultGroupsFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		
		gateinNodesData = new GateinNodesDatabase();
		gateinNodesData.setNodesData(gateinNodesFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		info("End setUpBeforeClass");
	}
	
//	@AfterMethod
//    public void afterMethod(){
//    	driver.get(baseUrl);
//    }
	
	@AfterMethod
	public void afterTest(){
		info("Start setUpBeforeClass");
		driver.manage().deleteAllCookies();
		driver.quit();
		info("End setUpBeforeClass");
	}

}

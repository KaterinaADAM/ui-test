package org.exoplatform.selenium.platform;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.gatein.PageCreationWizard;
import org.exoplatform.selenium.platform.gatein.PortalManageSites;
import org.exoplatform.selenium.platform.social.MyProfilePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NavigationToolbar extends PlatformBase {

	//Tool bar
	public final By ELEMENT_TOOLBAR_ADMINISTRATION = By.xpath("//*[@class='uiIconPLF24x24Setup']");
	public final By ELEMENT_TOOLBAR_THEMELIGHT = By.xpath("//*[@class='UIContainer UIToolbarContainer UIToolbarContainerLight']");
	public final By ELEMENT_UPLOAD_FILE_FRAME_XPATH = By.xpath("//iframe[contains(@id,'uploadFrame')]");
	public final By ELEMENT_HELP_TOOLBAR = By.className("uiIconPLF24x24Help");

	public final By ELEMENT_DOC_EXO_OF_HOME_GETTING_STARTED = By.xpath(".//*[@id='newBreadcrumbs']//*[contains(text(),'Getting Started')]");
	// toolbar--> upload file
	public By ELEMENT_UPLOAD_FILE_TOOLBAR_PERSONNAL_DOCUMENTS = By.xpath("//*[@id='ListRecords']//*[contains(text(),'Personal Documents')]");
	public By ELEMENT_UPLOAD_FILE_GO_TO_UPLOAD = By.xpath("//*[@id='UIDocumentSelector']//*[@class='UIDSUploadInput']");
	public final By ELEMENT_ACTIVITY_UPLOAD_POPUP_UPLOAD_BUTTON = By.xpath(".//input[@type='file']");
	public final By ELEMENT_UPLOAD_FILE_BUTTON=By.xpath("//*[@class='uiIconUpload uiIconLightGray']");

	//Administration Menu
	// users 
	public final By ELEMENT_ADMINISTRATION_USERS =By.xpath("//*[text()='Users']");
	public final By ELEMENT_ADMINISTRATION_PORTAL_ADD_USERS = By.xpath("//*[text()='Add Users']");
	public final By ELEMENT_GROUP_AND_ROLE_LINK = By.xpath("//*[text()='Groups and Roles']");
	
	//Administration Menu
	//Administration-->Portal
	public final By ELEMENT_ADMINISTRATION_PORTAL = By.xpath("//*[text()='Portal']");
	public final By ELEMENT_ADMINISTRATION_PORTAL_SITES=By.xpath("//*[text()='Sites']");
	public final By ELEMENT_ADMINISTRATION_PORTAL_PAGES=By.xpath("//*[text()='Pages']");
	public final By ELEMENT_ADMINISTRATION_PORTAL_BRANDING=By.xpath("//*[text()='Branding']");
	public final By ELEMENT_ADMINISTRATION_PORTAL_IDE=By.xpath("//*[text()='IDE']");
	public final By ELEMENT_ADMINISTRATION_PORTAL_GROUP_SITES = By.xpath("//*[text()='Group Sites']");
	
	//Administation-->Content
	public final By ELEMENT_LINK_CONTENT_ADMIN = By.xpath("//*[text()='Content Administration']");
	public final By ELEMENT_MENU_CONTENT_LINK = By.xpath("//li[@class='dropdown-submenu']/a[text()='Content']");
	public final By ELEMENT_MENU_SITE_EXPLORER = By.linkText("Sites Explorer");
	public final By ELEMENT_SITE_EXPLORER_HOME = By.className("uiIconEcmsHome");
	public final By ELEMENT_NEW_FOLDER_LINK = By.xpath("//*[@class='actionIcon']//*[contains(@class, 'uiIconEcmsAddFolder')]");
	public final By ELEMENT_SEARCH_LINK = By.xpath("//*[@class='dropdown-menu']//*[text()='Search']");


	// administration panel
	public final By ELEMENT_TOPBAR_ADMINISTRATION_BUTTON =By.xpath("//*[@class='uiIconPLF24x24Setup']");
	public final By ELEMENT_TOPBAR_CONTENT = By.xpath("//*[@id='UISetupPlatformToolBarPortlet']//a[contains(text(),'Content')]");
	public final By ELEMENT_CONTENT_TOPBAR_ADMINISTRATION = By.xpath("//*[@id='UISetupPlatformToolBarPortlet']//a[contains(text(),'Content Administration')]");

	//Setup icon
	public final By ELEMENT_LINK_SETUP=By.xpath("//*[@class='uiIconPLF24x24Setup']");
	public final By ELEENT_LINK_APPLICATION=By.xpath("//*[contains(@href,'/portal/g/:platform:administrators/administration/registry')]");

	//Edit menu
	public final By ELEMENT_LINK_EDIT=By.xpath("//*[@class='uiIconPLF24x24Edit']");
	public final By ELEMENT_MENU_PAGE_LINK = By.xpath("//*[@tabindex='-1' and contains(text(),'Page')]");
	public final By ELEMENT_MENU_EDIT_LAYOUT = By.xpath("//*[contains(text(),'Edit Layout')]");
	public final By ELEMENT_MENU_SEO_LINK = By.xpath("//span[contains(text(), 'SEO')]");
	public final By ELEMENT_MENU_ADD_PAGE_LINK = By.xpath("//*[contains(text(), 'Add Page')]");
	public final By ELEMENT_EDIT_PAGE = By.xpath("//*[@id='UIAdminToolbarContainer']//*[@class='dropdown-submenu']//*[@href='#' and contains(text(), 'Page')]");
	public final By ELEMENT_EDIT_PAGE_SEO = By.xpath("//*[@data-original-title = 'SEO Management']");
	public final By ELEMENT_EDIT_CONTENT = By.xpath("//*[@class='quickEditUnchecked']");
	public final By ELEMENT_EDIT_CONTENT_CHECK = By.xpath("//*[@class='quickEditChecked']");

	//User Menu
	public final By ELEMENT_MY_PROFILE_LINK = By.xpath("//i[@class='uiIconPLFProfile']/..");
	public final By ELEMENT_MY_DASHBOARD_LINK = By.xpath("//i[@class='uiIconPLFDashboard']/..");
	public final By ELEMENT_TOPBAR_AVATAR = By.xpath("//*[@alt='avatar']");
	public final By ELEMENT_AVATAR_CHANGELANGUAGE = By.xpath("//*[@class='uiIconFlags']");

	//Administration-->Application
	public final By ELEMENT_ADMINISTRATION_APPLICATION = By.xpath(".//*[text()='Applications']");
	public final By ELEMENT_ADD_TOOTLBAR = By.xpath("//*[@id='UICreatePlatformToolBarPortlet']//*[@class='uiIconPLF24x24Add']");

	public final By ELEMENT_ADD_WIKI_TOOLBAR = By.xpath("//*[@id='UICreateList']//*[@class='uiIconWikiWiki']");
	public final By ELEMENT_ADD_POOL_TOOLBAR = By.xpath("//*[@id='UICreateList']//*[@class='uiIconPoll']");
	public final By ELEMENT_ADD_TOPIC_TOOLBAR = By.xpath("//*[@id='UICreateList']//*[@class='uiIconUIForms']");
	public final By ELEMENT_ADD_EVENT_CLASS_TOOLBAR = By.xpath("//*[@id='UICreateList']//*[@class='uiIconPLFEventTask']");
	public final By ELEMENT_UPLOAD_FILE_TOOLBAR = By.xpath("//*[@id='UICreateList']//*[@class='uiIconUpload']");

	public final By ELEMENT_NEXT_BUTTON = By.xpath("//*[@id='UICreateList']//*[contains(text(),'Next')]");
	public final By ELEMENT_SAVE_BUTTON = By.xpath("//*[@id='UICreateList']//*[contains(text(),'Save')]");

	// add wiki from toolbar
	public final By ELEMENT_ADD_WIKI_SET_LOCATION = By.xpath("//*[@id='uiWikiSpaceSwitcher_CreateWiki']//*[@id='DisplayModesDropDown']/div");
	public final String ELEMENT_ADD_WIKI_CHOOSE_LOCATION = "//*[@class='spaceChooserPopup']//*[contains(text(),'{$location}')]";
	//add poll/topic 
	public final By ELEMENT_ADD_POLL_SET_LOCATION = By.xpath("//*[@id='ScrollSelectlocation']//*[@class='btn dropdown-toggle']");
	public final By ELEMENT_SELECT_FORUM_COMBOBOX = By.xpath(".//*[@id='uiForumFilterforumId']//div[@class='btn dropdown-toggle']");
	public final String ELEMENT_SELECT_FORUM_NAME = ".//*[@id='uiForumFilterforumId']//*[contains(text(),'${forum}')]";

	// event or task
	public final By ELEMENT_ADD_EVENT_RADIO_BUTTON = By.xpath("//*[@id='QuickAddEventContainer']//*[@class='radio' and @value='Event']");
	public final By ELEMENT_ADD_TASK_RADIO_BUTTON = By.xpath("//*[@id='QuickAddEventContainer']//*[@class='radio' and @value='Task']");
	public final By ELEMENT_ADD_TITLE = By.id("Title");

	public final  String ELEMENT_CHECK_NAME_UPLOADED_FILE= "//*[@id='ListRecords']//*[contains(text(),'{$name}')]";
	//Quick search
	public final By ELEMENT_TOOLBAR_QUICKSEARCH = By.xpath("//*[@class='uiIconPLF24x24Search']");

	//Left Panel
	public final String ELEMENT_NODE_NAVIGATION_LEFT_PANEL = "//*[@class='groupNavigation']/..//*[contains(text(), '${groupName}')]";
	public final String ELEMENT_GROUP_NAVIGATION_ICON_LEFT_PANEL = ELEMENT_NODE_NAVIGATION_LEFT_PANEL.replace("${groupName}", "${groupName}") + "/../*[contains(@class, 'arrowIcon')]";
	public final String ELEMENT_GROUP_NAVIGATION_ICON_LEFT_PANEL_PLF41 = ELEMENT_NODE_NAVIGATION_LEFT_PANEL.replace("${groupName}", "${groupName}") + "/..//*[contains(@class, 'uiIconMiniArrowDown uiIconLightGray')]";
	public final String ELEMENT_SUB_NODE_NAVIGATION_LEFT_PANEL = ".//*[@id='UIGroupsNavigationPortlet']//*[@data-original-title='${groupName}']";
	
	PageCreationWizard paWin;
	MyProfilePage myPro;
	PortalManageSites magSites;
	
	public NavigationToolbar(WebDriver dr){
		this.driver = dr;
		paWin = new PageCreationWizard(dr);
		myPro = new MyProfilePage(dr);
		magSites = new PortalManageSites(dr);
	} 

	/**
	 * Go to Edit Layout
	 */
	public void goToEditLayout(){
		info("--Go to Edit Layout--");
		click(ELEMENT_LINK_EDIT);
		mouseOver(ELEMENT_MENU_PAGE_LINK, true);
		click(ELEMENT_MENU_EDIT_LAYOUT,2,true);
	}

	/** 
	 * Go to add page form: Edit-->Page-->Add page
	 */
	public void goToAddPage(){
		info("Go to add page form");
		waitForAndGetElement(ELEMENT_LINK_EDIT);
		click(ELEMENT_LINK_EDIT);
		mouseOver(ELEMENT_MENU_PAGE_LINK, true);
		click(ELEMENT_MENU_ADD_PAGE_LINK,2,true);
		Utils.pause(3000);
		waitForAndGetElement(paWin.ELEMENT_PAGE_CREATION_WIZARD,3000,0);
	}

	/**
	 * Go to Manage Sites page: Administration-->Portal->Sites
	 * By QuynhPT
	 */
	public void goToPotalSites(){
		info("--Go to Portal-->Sites--");
		waitForAndGetElement(ELEMENT_TOOLBAR_ADMINISTRATION,3000,0);
		click(ELEMENT_TOOLBAR_ADMINISTRATION);
		mouseOver(ELEMENT_ADMINISTRATION_PORTAL,true);
		waitForAndGetElement(ELEMENT_ADMINISTRATION_PORTAL_SITES,3000,0);
		click(ELEMENT_ADMINISTRATION_PORTAL_SITES);
		Utils.pause(3000);
		waitForAndGetElement(magSites.ELEMENT_MANAGESITES_TITLE,3000,0);
	}
	
	/**
	 * Go to Manage Group Sites page: Administration-->Portal->Group Sites
	 */
	public void goToGroupSites(){
		info("--Go to Portal-->Sites--");
		waitForAndGetElement(ELEMENT_TOOLBAR_ADMINISTRATION,3000,0);
		click(ELEMENT_TOOLBAR_ADMINISTRATION);
		mouseOver(ELEMENT_ADMINISTRATION_PORTAL,true);
		waitForAndGetElement(ELEMENT_ADMINISTRATION_PORTAL_GROUP_SITES,3000,0);
		click(ELEMENT_ADMINISTRATION_PORTAL_GROUP_SITES);
		Utils.pause(3000);
		waitForAndGetElement(magSites.ELEMENT_ADD_NAVIGATION_BUTTON,3000,0);
	}
	
	/**
	 * Go to Manage Sites page: Administration-->Portal->Pages
	 */
	public void goToPotalPages(){
		info("-- Go to Page Management page --");
		Utils.pause(500);
		for(int repeat=0;; repeat ++){
			if (repeat > 1){
				mouseOverAndClick(ELEMENT_LINK_SETUP);
				break;
			}
			mouseOver(ELEMENT_LINK_SETUP, true);
			if (waitForAndGetElement(ELEMENT_ADMINISTRATION_PORTAL, 5000, 0) != null){
				info("Element " + ELEMENT_ADMINISTRATION_PORTAL + "... is displayed");
				break;
			}
			info("Retry...[" + repeat + "]");
		}
		mouseOverAndClick(ELEMENT_ADMINISTRATION_PORTAL);
		Utils.pause(2000);
		info("Page Managements is shown successfully");
	}
	

	/**
	 * function: Go to Users and Group management (Administration --> Users --> Groups and Roles)
	 */
	public void goToUsersAndGroupsManagement() {
		info("--Go to Users and groups management--");
		click(ELEMENT_LINK_SETUP);
		mouseOver(ELEMENT_ADMINISTRATION_USERS, true);
		click(ELEMENT_GROUP_AND_ROLE_LINK);
		Utils.pause(2000);
	}
	
	/**
	 * List sublink in user menu
	 * @author quynhpt
	 */
	public enum specifUserToolBar{
		MY_PROFILE,MY_ACTIVITY,MY_CONNECTIONS,MY_WIKI,MY_DASHBOARD,MY_NOTIFICATION,SETTINGS,CHANGE_LANGUAGE;
	}
	/**
	 * Select a link in User menu
	 * @param link
	 */
	public void selectALinkOfUserMenu(specifUserToolBar link){
		openUserMenu();
		switch(link){
		case MY_PROFILE:
			click(ELEMENT_MY_PROFILE_LINK);
			Utils.pause(2000);
			waitForAndGetElement(myPro.ELEMENT_MY_PROFILE_TAB,3000,0);
			break;
		case MY_ACTIVITY:
			break;
		case MY_CONNECTIONS:
			break;
		case MY_WIKI:
			break;
		case MY_DASHBOARD:
			click(ELEMENT_MY_DASHBOARD_LINK);
			break;
		case MY_NOTIFICATION:
			break;
		case SETTINGS:
			break;
		case CHANGE_LANGUAGE:
			break;
		}
	}
	/**
	 * Open User menu by click on username
	 */
	public void openUserMenu() {
		info("--Open User Menu--");
		waitForAndGetElement(ELEMENT_ACCOUNT_NAME_LINK);
		click(ELEMENT_ACCOUNT_NAME_LINK);
		Utils.pause(1000);
	}

	/**
	 *  Go to Site Explorer page: Administration-->Content->Site Explorer
	 */
	public void goToSiteExplorer() {
		info("-- Go to site explorer home page --");
		Utils.pause(500);
		for(int repeat=0;; repeat ++){
			if (repeat > 1){
				mouseOverAndClick(ELEMENT_LINK_SETUP);
				break;
			}
			mouseOver(ELEMENT_LINK_SETUP, true);
			if (waitForAndGetElement(ELEMENT_MENU_CONTENT_LINK, 5000, 0) != null){
				info("Element " + ELEMENT_MENU_CONTENT_LINK + "... is displayed");
				break;
			}
			info("Retry...[" + repeat + "]");
		}
		mouseOverAndClick(ELEMENT_MENU_CONTENT_LINK);
		Utils.pause(2000);
		info("Site Explorer is shown successfully");
	}
	/**
	 * Go to Edit-->Page-->SEO
	 */
	public void goToSEO(){
		info("Go to SEO page");
		info("Click on Edit button");
		click(ELEMENT_LINK_EDIT);
		info("Hover over on Page link");
		mouseOver(ELEMENT_MENU_PAGE_LINK, true);
		info("Click on Seo Menu");
		WebElement seoMenu = waitForAndGetElement(ELEMENT_MENU_SEO_LINK,10000,1,2);
		((JavascriptExecutor)driver).executeScript("arguments[0].click()",seoMenu);		
		Utils.pause(2000);
	}
	/**
	 * Go to Edit Content
	 */
	public void goToEditContent(){
		info("Go to Edit content");
		waitForAndGetElement(ELEMENT_LINK_EDIT);
		click(ELEMENT_LINK_EDIT);
		if(waitForAndGetElement(ELEMENT_EDIT_CONTENT,5000,0)!=null)
			click(ELEMENT_EDIT_CONTENT);
	}
	/**
	 * Go to Un-edit content
	 * Edit-->Uncheck Content
	 */
	public void goToUnEditContent(){
		info("Go to un Edit content");
		waitForAndGetElement(ELEMENT_LINK_EDIT);
		click(ELEMENT_LINK_EDIT);
		if(waitForAndGetElement(ELEMENT_EDIT_CONTENT_CHECK,5000,0)!=null)
			click(ELEMENT_EDIT_CONTENT_CHECK);
	}
	/**
	 * Open Change language popup
	 * Username-->Change Language
	 */
	public void goToChangeLanguage(){
		info("Open Change Language popup");
		waitForAndGetElement(ELEMENT_TOPBAR_AVATAR);
		click(ELEMENT_TOPBAR_AVATAR);
		click(ELEMENT_AVATAR_CHANGELANGUAGE);
		Utils.pause(2000);
	}

	/**
	 * Go to content administration
	 */
	public void goToContentAdministration(){
		info("Go to content administration");		
		if (baseUrl==null) baseUrl = DEFAULT_BASEURL;
		info("Base url is " + baseUrl);
		String url = baseUrl + "/g/:platform:web-contributors/wcmAdmin";
		info("base url of content admin is " + baseUrl);
		for(int repeat=0;; repeat ++){
			if (repeat > 1){
				driver.get(url);
				break;
			}
			mouseOver(ELEMENT_LINK_SETUP, true);
			if (waitForAndGetElement(ELEMENT_MENU_CONTENT_LINK, 5000, 0)!= null) {
				mouseOver(ELEMENT_MENU_CONTENT_LINK, true);
				if (waitForAndGetElement(ELEMENT_LINK_CONTENT_ADMIN, 5000, 0)!= null){
					click(ELEMENT_LINK_CONTENT_ADMIN);
					break;
				}
			}
			info("Retry...[" + repeat + "]");
		}
		Utils.pause(1000);
	}
	/**
	 * Go to IDE page
	 */
	public void goToIDE(){
		info("-- Go to IDE home page --");
		Utils.pause(500);
		for(int repeat=0;; repeat ++){
			if (repeat > 1){
				mouseOverAndClick(ELEMENT_LINK_SETUP);
				break;
			}
			mouseOver(ELEMENT_LINK_SETUP, true);
			if (waitForAndGetElement(ELEMENT_ADMINISTRATION_PORTAL_IDE, 5000, 0) != null){
				info("Element " +ELEMENT_ADMINISTRATION_PORTAL_IDE + "... is displayed");
				break;
			}
			info("Retry...[" + repeat + "]");
		}
		mouseOverAndClick(ELEMENT_ADMINISTRATION_PORTAL_IDE);
		Utils.pause(2000);
	}
	/**
	 * Go to Application home page
	 */
	public void goToApplication(){
		info("-- Go to Application home page --");
		Utils.pause(500);
		for(int repeat=0;; repeat ++){
			if (repeat > 1){
				mouseOverAndClick(ELEMENT_LINK_SETUP);
				break;
			}
			mouseOver(ELEMENT_LINK_SETUP, true);
			if (waitForAndGetElement(ELEMENT_ADMINISTRATION_APPLICATION, 5000, 0) != null){
				info("Element " +ELEMENT_ADMINISTRATION_APPLICATION + "... is displayed");
				break;
			}
			info("Retry...[" + repeat + "]");
		}
		mouseOverAndClick(ELEMENT_ADMINISTRATION_APPLICATION);
		Utils.pause(2000);
	}

	/**
	 * Go to Banding page
	 */
	public void goToBanding() {
		info("-- Go to Banding page --");
		waitForAndGetElement(ELEMENT_TOOLBAR_ADMINISTRATION,5000,0);
		click(ELEMENT_TOOLBAR_ADMINISTRATION);
		mouseOver(ELEMENT_ADMINISTRATION_PORTAL, true);
		waitForAndGetElement(ELEMENT_ADMINISTRATION_PORTAL_BRANDING,5000,0);
		click(ELEMENT_ADMINISTRATION_PORTAL_BRANDING);
	}


	/**
	 * Go to create wiki page from the toolbars
	 * @param location
	 */
	public void goToCreateWikiPage(String location){
		info("Go to create wiki page");
		click(ELEMENT_ADD_TOOTLBAR);
		click(ELEMENT_ADD_WIKI_TOOLBAR);
		if(!location.isEmpty()){
			click(ELEMENT_ADD_WIKI_SET_LOCATION);
			click(ELEMENT_ADD_WIKI_CHOOSE_LOCATION.replace("{$location}", location));
		}
		click(ELEMENT_NEXT_BUTTON);
	}

	/**
	 * Go to add a pool from the toolbar
	 * @param location
	 * @param forum
	 */
	public void goToAddPoll(String location,String forum){
		info("Go to add poll from tootlbar");
		click(ELEMENT_ADD_TOOTLBAR,0,true);
		info("Click on Poll link");
		click(ELEMENT_ADD_POOL_TOOLBAR,0,true);
		if (!location.isEmpty()){
			info("Set location for the poll");
			click(ELEMENT_ADD_POLL_SET_LOCATION);
		}
		info("click on Next button");
		click(ELEMENT_NEXT_BUTTON);
		Utils.pause(2000);
		info("Select a forum for poll");
		click(ELEMENT_SELECT_FORUM_COMBOBOX,0,true);
		click(ELEMENT_SELECT_FORUM_NAME.replace("${forum}",forum),0,true);
		info("Click on next button");
		click(ELEMENT_NEXT_BUTTON);
		Utils.pause(2000);
	}
	/**
	 * Add a topic from the toolbar
	 * @param location
	 * @param forum
	 */
	public void goToAddTopic(String location,String forum){
		info("Go to add a topic from toolbar");
		click(ELEMENT_ADD_TOOTLBAR);
		click(ELEMENT_ADD_TOPIC_TOOLBAR);
		if (location!=""){
			click(ELEMENT_ADD_POLL_SET_LOCATION);
		}
		click(ELEMENT_NEXT_BUTTON);
		info("click on Next button");
		click(ELEMENT_NEXT_BUTTON);
		info("Select a forum for topic");
		click(ELEMENT_SELECT_FORUM_COMBOBOX);
		click(ELEMENT_SELECT_FORUM_NAME.replace("${forum}",forum));
		info("Click on next button");
		click(ELEMENT_NEXT_BUTTON);
		Utils.pause(2000);
	}

	/**
	 * Add an event or a task from the toolbar
	 * @param eventTask
	 * @param name
	 * @param from
	 * @param to
	 * @param calendar
	 */
	public void goToAddEventTask(String eventTask,String name,String from, String to, String calendar){
		click(ELEMENT_ADD_TOOTLBAR);
		click(ELEMENT_ADD_EVENT_CLASS_TOOLBAR);
		if(eventTask=="event"){
			check(ELEMENT_ADD_EVENT_RADIO_BUTTON,2);
		}
		if(eventTask=="task"){
			check(ELEMENT_ADD_TASK_RADIO_BUTTON,2);
		}
		type(ELEMENT_ADD_TITLE,name,true);
		click(ELEMENT_SAVE_BUTTON);
	}

	/**
	 * Go to upload file fron the toolbar
	 * @param drive
	 * @param pathInDrive
	 * @param linkFile
	 */
	public void goToUploadFile(String drive, String pathInDrive,String linkFile){
		info("-- Upload file --");
		click(ELEMENT_ADD_TOOTLBAR);
		click(ELEMENT_UPLOAD_FILE_TOOLBAR);

		if(drive!=""){

		}

		if(pathInDrive!=""){

		}else{
			click(ELEMENT_UPLOAD_FILE_TOOLBAR_PERSONNAL_DOCUMENTS);
		}

		WebElement frame = waitForAndGetElement(ELEMENT_UPLOAD_FILE_FRAME_XPATH);
		driver.switchTo().frame(frame);
		click(ELEMENT_UPLOAD_FILE_BUTTON);
		uploadFileUsingRobot(linkFile);
		Utils.pause(1000);
		switchToParentWindow();
		info("Upload finished");
	}


	/**
	 * Open search administration
	 */
	public void goToAdminSearch() {
		click(ELEMENT_TOOLBAR_ADMINISTRATION);
		mouseOver( ELEMENT_MENU_CONTENT_LINK , true);
		click(ELEMENT_SEARCH_LINK);
	}
	/**
	 * Open quick search on toolbar
	 */
	public void goToQuickSearch(){
		info("Click on Quick search icon");
		click(ELEMENT_TOOLBAR_QUICKSEARCH);
		Utils.pause(2000);
	}

	/**
	 * Open My profile page
	 */
	public void goToMyProfile(){
		info("Click on Avatar");
		click(ELEMENT_TOPBAR_AVATAR);
		info("Click on My profile link");
		click(ELEMENT_MY_PROFILE_LINK);
		Utils.pause(2000);
	}

	/**
	 * Go to add an user
	 */
	public void goToAddUser(){
		info("Go to add user page");
		waitForAndGetElement(ELEMENT_TOOLBAR_ADMINISTRATION,3000,0);
		click(ELEMENT_TOOLBAR_ADMINISTRATION);
		mouseOver(ELEMENT_ADMINISTRATION_USERS,true);
		waitForAndGetElement(ELEMENT_ADMINISTRATION_PORTAL_ADD_USERS,3000,0);
		click(ELEMENT_ADMINISTRATION_PORTAL_ADD_USERS);
	}
	/**
	 * Open My dashboard
	 */
	public void goToMyDashboard(){
		selectALinkOfUserMenu(specifUserToolBar.MY_DASHBOARD);
		Utils.pause(2000);
	}
	
	
}

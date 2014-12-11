package org.exoplatform.selenium.platform;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.ecms.ContentAdministration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class NavigationToolbar extends PlatformBase {

	ManageLogInOut acc;
	
	//Logo link
	public final By ELEMENT_LOGO_LINK= By.xpath(".//*[@id='HomeLink']//img");
	
	//Edit menu
	public final By ELEMENT_MENU_EDIT_LINK = By.xpath("//i[@class='uiIconPLF24x24Edit']");
	public final By ELEMENT_MENU_EDIT_CONTENT = By.xpath("//i[@class='quickEditChecked']");
	public final By ELEMENT_MENU_EDIT_CONTENT_UNCHECK = By.xpath("//i[@class='quickEditUnchecked']");
	public final By ELEMENT_EDIT_MENU_ID = By.xpath("//*[@id='UIAdminToolbarPortlet']/../..");
	public final By ELEMENT_SEO_MENU = By.xpath("//span[contains(text(),'SEO')]");
	public final By ELEMENT_ADD_PAGE_MENU = By.xpath("//a[contains(text(),'Add Page')]");
	public final By ELEMENT_MENU_PAGE_LINK = By.linkText("Page");
	public final By ELEMENT_MENU_EDIT_LAYOUT = By.linkText("Edit Layout");
	public final By ELEMENT_PAGE_ID = By.xpath("//*[contains(@id, 'UIPage-')]");

	//site
	public final By ELEMENT_MENU_EDIT_SITES = By.xpath("//*[@id='UIAdminToolbarContainer']//a[contains(text(),'Site')]");
	public final By ELEMENT_MENU_EDIT_SITES_NAV = By.xpath("//*[@id='UIAdminToolbarContainer']//a[contains(text(),'Navigation')]");
	public final By ELEMENT_MENU_EDIT_SITE_LAYOUT = By.linkText("Layout");
	public final By ELEMENT_MENU_EDIT_ADDSITE = By.linkText("Add Site");
	public final By ELEMENT_MENU_EDIT_CONTENT_TEXT = By.linkText("Content");
	
	//Administration menu
	public final By ELEMENT_LINK_SETUP = By.xpath("//*[@id='UISetupPlatformToolBarPortlet']/a"); 
	
	//Administration-->Users
	public final String ELEMENT_LINK_USERS ="//a[text()='Users']";
	public final String ELEMENT_LINK_ADD_USERS="//a[text()='Add Users']";
	public final String ELEMENT_USERS_AND_GROUPS = "//a[contains(text(),'Groups and Roles') or contains(text(),'Ajouter un Utilisateur')]";
	
	//Administration-->Application
	public By ELEMENT_APPLICATIONS_LINK = By.linkText("Applications");
	
	//Administration-->Content
	public final By ELEMENT_MENU_CONTENT_LINK = By.xpath("//li[@class='dropdown-submenu']/a[text()='Content']");
	public final By ELEMENT_LINK_CONTENT_ADMIN = By.xpath("//*[text()='Content Administration']");
	public final By ELEMENT_MENU_SEARCH = By.linkText("Search");
	
	//Administration->Portal menu
	public final String ELEMENT_LINK_PORTAL = "//a[text()='Portal']";
	public final String ELEMENT_LINK_PAGES   = "//a[text()='Pages']";
	public final String ELEMENT_LINK_SITES   = "//a[text()='Sites']";
	public final String ELEMENT_LINK_GROUP = "//a[text()='Group Sites']";
	public final String ELEMENT_LINK_BRANDING = "//a[text()='Branding']";
	public final By ELEMENT_MENU_EMAIL_NOTIFICATION = By.xpath("//a[text()='Email Notifications']");
	
	//Drop down menu (click from username)
	public final By ELEMENT_ACCOUNT_NAME_LINK = By.xpath("//*[@id='UIUserPlatformToolBarPortlet']/a");
	public final By ELEMENT_NAVIGATION_ACCOUNT_AVATAR = By.xpath("//*[@id='UIUserPlatformToolBarPortlet']/a/img[@alt='avatar']");
	public final By ELEMENT_MY_PROFILE = By.xpath("//i[@class='uiIconPLFProfile']/..");
	public final By ELEMENT_MY_DASHBROARD = By.className("uiIconPLFDashboard");
	public final By ELEMENT_MY_SETTING = By.className("uiIconSetting");
	public final By ELEMENT_MY_ACTIVITIES = By.className("uiIconPLFActivityStream");
	public final By ELEMENT_MY_CONNECTIONS = By.className("uiIconPLFMyConnection");
	public final By ELEMENT_MY_WIKI = By.xpath("//a[contains(.,'My Wiki')]");
	public final String ELEMENT_LINE_BETWEEN_MENU = "//*[@class='divider'][${index}]";
	public final By ELEMENT_MY_NOTIFICATION = By.xpath("//*[@id='UIUserPlatformToolBarPortlet']//a[contains(.,'Notifications')]");
	public final By ELEMENT_CHANGE_LANGUAGE = By.xpath("//a[text()='Change Language']");
	public final By ELEMENT_CHANGE_LANGUAGE_FRENCH = By.xpath("//a[text()='Changer de Langue']");
	public final By ELEMENT_SIGN_OUT_LINK = By.xpath(".//*[@class='uiIconPLFLogout']");
	

	

/**-------------------------------------------------------------------------------------------------------------------------*/
	public NavigationToolbar(WebDriver dr, String...plfVersion){
		driver = dr;
		this.plfVersion = plfVersion.length>0?plfVersion[0]:"4.0";
	} 
/********************************************************** Edit Menu *********************************************************/	
    /**
     * Go to Edit --> Tick Content check box
     */
	public void changeEditMode()
	{
		mouseOver(ELEMENT_MENU_EDIT_LINK,true);
		mouseOverAndClick(ELEMENT_MENU_EDIT_CONTENT);
	}
    /****************************************************** Edit-->Pages *********************************************************************/

	/** Go to Edit-->Page-->Edit layout
	 * 
	 */
	/*public PageEditor goToEditLayout(){
		info("Go to Edit layout form");
		for(int repeat=0;; repeat ++){
			if (repeat > 4){
				mouseOverAndClick(ELEMENT_MENU_EDIT_LINK);
				break;
			}
			mouseOver(ELEMENT_MENU_EDIT_LINK, true);
			if (waitForAndGetElement(ELEMENT_MENU_PAGE_LINK, 5000, 0)!= null) {
				info("-- Click Pagemenu --");
				mouseOver(ELEMENT_MENU_PAGE_LINK,true);
				if (waitForAndGetElement(ELEMENT_MENU_EDIT_LAYOUT, 5000, 0)!= null){
					click(ELEMENT_MENU_EDIT_LAYOUT);
					break;
				}
			}
			else{
				String editPageRequest = "ajaxGet(eXo.env.server.createPortalURL('" + getPageId() + "', 'EditCurrentPage', true))";
				((JavascriptExecutor)driver).executeScript(editPageRequest);
				Utils.pause(1000);
				break;
			}
			info("Retry...[" + repeat + "]");
		}
		Utils.pause(1000);
		return new PageEditor(driver);
	}
	*/
	 /**
	  * Go to Edit-->Page-->Add Page
	  */
	/*public PageEditor goToAddPage(){
		info("Go to add page form");
		for(int repeat=0;; repeat ++){
			if (repeat > 4){
				mouseOverAndClick(ELEMENT_MENU_EDIT_LINK);
				break;
			}
			mouseOver(ELEMENT_MENU_EDIT_LINK, true);
			if (waitForAndGetElement(ELEMENT_MENU_PAGE_LINK, 5000, 0)!= null) {	
				mouseOver(ELEMENT_MENU_PAGE_LINK, true);
				if (waitForAndGetElement(ELEMENT_ADD_PAGE_MENU, 5000, 0)!= null){
					click(ELEMENT_ADD_PAGE_MENU);
					break;
				}
			}
			info("Retry...[" + repeat + "]");
		}
		Utils.pause(1000);
		return new PageEditor(driver);
	}*/

	/**
	 * Go to Page Creation Wizard by javascript
	 */
	/*public PageEditor goToPageCreationWizard(){
		info("Go to add page wizard");
		((JavascriptExecutor)driver).executeScript("javascript:ajaxGet(eXo.env.server.createPortalURL('UIWorkingWorkspace', 'PageCreationWizard', true));");
		Utils.pause(500);
		return new PageEditor(driver);
	}*/
	
	/**
	 * Go to Edit-->Page->SEO
	 * @return
	 */
	/*public SEOManagement goToSeoManagement(){
		info("Go to SEO management form");
		Utils.pause(1000);
		click(ELEMENT_MENU_EDIT_LINK);
		mouseOver(ELEMENT_MENU_PAGE_LINK, true);
		WebElement seo = waitForAndGetElement(ELEMENT_SEO_MENU,10000,1,2);
		((JavascriptExecutor)driver).executeScript("arguments[0].click()",seo);		
		Utils.pause(1000);
		return new SEOManagement(driver);
	}*/

	/**
	 * Click on Logo and go to Activity stream of Intranet Home page
	 * @return
	 *//*
	public void goToASIntranetHomePage(){
		info("-- Go to home page --");
		click(ELEMENT_LOGO_LINK);
		Utils.pause(1000);
		this.driver.navigate().refresh();
		waitForAndGetElement(ELEMENT_REFRESH,60000);
		
		//return new ActivityStream(driver,this.plfVersion);
	}*/

	/**
	 * Get pageID to edit layout
	 * @author phuongdt
	 */
	public String getPageId(){
		String pageElement = waitForAndGetElement(ELEMENT_PAGE_ID).getAttribute("id");
		int beginIndex = pageElement.indexOf("-");
		return pageElement.substring(beginIndex+1);
	}
	
	
/********************************************************** Administration Menu *********************************************************/
	/******************************************************* Administration-->Users *****************************************************************/
	/**
	 * Go to Administration-->Users
	 * or
	 * Go to Administration-->Add Users
	 * @return
	 */
	/*public ManageUserAccount goToNewStaff() {
			info("Go to New Staff");
			for(int repeat=0;; repeat ++){
				if (repeat > 1){
					mouseOverAndClick(ELEMENT_LINK_SETUP);
					break;
				}
				mouseOver(ELEMENT_LINK_SETUP, true);
				if (waitForAndGetElement(ELEMENT_LINK_USERS, 5000, 0) != null){
					info("Element " + ELEMENT_LINK_USERS + "... is displayed");
					break;
				}
				info("Retry...[" + repeat + "]");
			}
			click(ELEMENT_LINK_USERS);
			Utils.pause(1000);
			return new ManageUserAccount(driver,this.plfVersion);
	}*/
	/**
	 * Go to Administration-->Users-->Users and Group
	 * @return
	 */
	/*public UserGroupManagement goToUsersAndGroupsManagement() {
		info("--Go to Users and groups management--");
		mouseOver(ELEMENT_LINK_SETUP, true);
		
		WebElement element = waitForAndGetElement(By.xpath(ELEMENT_USERS_AND_GROUPS), DEFAULT_TIMEOUT, 1, 2);		
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
		
		Utils.pause(500);
		return new UserGroupManagement(driver,this.plfVersion);
	}*/
	
	/******************************************************* Administration-->Applications *****************************************************************/
	/**
	 * Go to Administration-->Applications
	 * @return
	 */
		/*public RegistrationPage goToApplicationRegistry() {
			info("--Go to Registration page --");
			for (int repeat = 0;; repeat ++){
				if (repeat > 4){
					mouseOverAndClick(ELEMENT_LINK_SETUP);
					break;
				}
				mouseOver(ELEMENT_LINK_SETUP, false);
				if (waitForAndGetElement(ELEMENT_APPLICATIONS_LINK, 3000, 0) != null){
					info("Link application is displayed");
					break;
				}
				info("Retry..." + repeat + "...");
			}
			click(ELEMENT_APPLICATIONS_LINK);
			Utils.pause(500);
			
			return new RegistrationPage(driver);
		}*/
	/******************************************************* Administration-->Content *****************************************************************/
	
		/**
		 *  Go to Administration-->Content Administration
		 * @return ContentAdministration page
		 */
		public ContentAdministration goToContentAdministration()
		{
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
			return new ContentAdministration(driver,this.plfVersion);
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
				this.driver.navigate().refresh();
			}
			mouseOverAndClick(ELEMENT_MENU_CONTENT_LINK);
			Utils.pause(2000);
			info("Site Explorer is shown successfully");
		}
		

		/**
		 * Go to Administration > Content > Search page
		 * @return
		 */
		/*public SearchAdministration goToSearch()
		{
			String url = baseUrl + "/g/:platform:administrators/search";
			for(int repeat=0;; repeat ++){
				if (repeat > 1){
					driver.get(url);
					break;
				}
				mouseOver(ELEMENT_LINK_SETUP, true);
				if (waitForAndGetElement(ELEMENT_MENU_CONTENT_LINK, 5000, 0)!= null) {	
					mouseOver(ELEMENT_MENU_CONTENT_LINK, true);
					if (waitForAndGetElement(ELEMENT_MENU_SEARCH, 5000, 0)!= null){
						click(ELEMENT_MENU_SEARCH);
						break;
					}
				}
				info("Retry...[" + repeat + "]");
			}
			Utils.pause(1000);
			
		  return new SearchAdministration(driver);
		}*/
	/******************************************************* Administration-->Portal menu *****************************************************************/
	/**
	 * Go to Portal Manage Pages: Administration-->Portal-->Pages
	 * @return
	 */
	/*public PageManagement goToManagePages() {
		info("--Go to Page Management--");
		String url = baseUrl + "/g/:platform:administrators/administration/pageManagement";
		for(int repeat=0;; repeat ++){
			if (repeat > 1){
				driver.get(url);
				break;
			}
			mouseOver(ELEMENT_LINK_SETUP, true);
			if (waitForAndGetElement(ELEMENT_LINK_PORTAL, 5000, 0)!= null) {	
				mouseOver(ELEMENT_LINK_PORTAL, false);
				if (waitForAndGetElement(ELEMENT_LINK_PAGES, 5000, 0)!= null){
					click(ELEMENT_LINK_PAGES);
					break;
				}
			}
			info("Retry...[" + repeat + "]");
		}
		return new PageManagement(driver,this.plfVersion);
	}*/

	/**
	 * Go to portal sites: Administration-->Portal->Sites
	 * @return
	 */
	/*public PortalNavigation goToPortalSites() {
		info("--Go to Portal Site Management--");
		String url = baseUrl + "/g/:platform:administrators/portalnavigation";
		for(int repeat=0;; repeat ++){
			if (repeat > 1){
				driver.get(url);
				break;
			}
			mouseOver(ELEMENT_LINK_SETUP, true);
			if (waitForAndGetElement(ELEMENT_LINK_PORTAL, 5000, 0)!= null) {	
				mouseOver(ELEMENT_LINK_PORTAL, false);
				if (waitForAndGetElement(ELEMENT_LINK_SITES, 5000, 0)!= null){
					click(ELEMENT_LINK_SITES);
					break;
				}
			}
			info("Retry...[" + repeat + "]");
		}
		return new PortalNavigation(driver);
	}
	*/
	/**
	 * Go to Portal/Group Sites: Administration-->Portal-->Group Sites
	 * @return
	 */
	/*public GroupNavigation goToGroupSites(){
		info("--Go to Group Site Management--");
		String url = baseUrl + "/g/:platform:administrators/groupnavigation";
		Utils.pause(1000);
		for(int repeat=0;; repeat ++){
			if (repeat > 1){
				driver.get(url);
				break;
			}
			mouseOver(ELEMENT_LINK_SETUP, true);
			if (waitForAndGetElement(ELEMENT_LINK_PORTAL, 5000, 0)!= null) {	
				mouseOver(ELEMENT_LINK_PORTAL, false);
				if (waitForAndGetElement(ELEMENT_LINK_GROUP, 5000, 0)!= null){
					click(ELEMENT_LINK_GROUP);
					break;
				}
			}
			info("Retry...[" + repeat + "]");
		}
		return new GroupNavigation(driver,this.plfVersion);
	}*/

	/**
	 * Go to portal Branding:Administration-->Portal-->Branding
	 * @return
	 */
	/*public BrandingManagement goToPortalBranding() {
		info("--Go to Branding Management--");
		String url = baseUrl + "/g/:platform:administrators/branding";
		for(int repeat=0;; repeat ++){
			if (repeat > 1){
				driver.get(url);
				break;
			}
			mouseOver(ELEMENT_LINK_SETUP, true);
			if (waitForAndGetElement(ELEMENT_LINK_PORTAL, 5000, 0)!= null) {	
				mouseOver(ELEMENT_LINK_PORTAL, false);
				if (waitForAndGetElement(ELEMENT_LINK_BRANDING, 5000, 0)!= null){
					click(ELEMENT_LINK_BRANDING);
					break;
				}
			}
			info("Retry...[" + repeat + "]");
		}
		return new BrandingManagement(driver);
	}
*/
	/**
	 * Go to Administration-->Email notification page
	 * @return
	 */
	/*public EmailNotificationSetting goToNotificationAdministration(){
		info("--Go to Email Notification--");		
		String url = baseUrl + "/g/:platform:administrators/notification";
		for(int repeat=0;; repeat ++){
			if (repeat > 1){
				driver.get(url);
				break;
			}
			mouseOver(ELEMENT_LINK_SETUP, true);
			if (waitForAndGetElement(ELEMENT_LINK_PORTAL, 5000, 0)!= null) {	
				mouseOver(ELEMENT_LINK_PORTAL, true);
				if (waitForAndGetElement(ELEMENT_MENU_EMAIL_NOTIFICATION, 5000, 0)!= null){
					click(ELEMENT_MENU_EMAIL_NOTIFICATION);
					break;
				}
			}
			info("Retry...[" + repeat + "]");
		}
		Utils.pause(1000);
		return new EmailNotificationSetting(driver,this.plfVersion);
	}
	*/
	/******************************************************* Administration-->Administration *****************************************************************/
	
	/********************************************************** Drop Down Menu (From User name) *********************************************************/
	
	/**
	 * Go to Username-->My Profile
	 * @return
	 */
	/*public MyProfile goToMyProfile(){
		info("--Go to My Profile--");		
		for(int repeat=0;; repeat ++){
			if (repeat > 1){
				mouseOverAndClick(ELEMENT_ACCOUNT_NAME_LINK);
				info("--Error mouse over and click: can't mouseover, need to use mouse over and click --");
				break;
				}
			mouseOver(ELEMENT_ACCOUNT_NAME_LINK, true);
			if (waitForAndGetElement(ELEMENT_MY_PROFILE, 5000, 0) != null){
				info("Element " + ELEMENT_MY_PROFILE+ "... is displayed");
				break;
				}
				info("Retry...[" + repeat + "]");
			}
			click(ELEMENT_MY_PROFILE);
			
			return new MyProfile(driver,this.plfVersion);
		}	*/
	/**
	 * Go to Username -->My Activities
	 * @return
	 */
	/*public MyActivities goToMyActivities(){
		info("--Go to My Activities--");		
		for(int repeat=0;; repeat ++){
			if (repeat > 1){
				mouseOverAndClick(ELEMENT_ACCOUNT_NAME_LINK);
				info("--Error mouse over and click: can't mouseover, need to use mouse over and click --");
				break;
				}
			mouseOver(ELEMENT_ACCOUNT_NAME_LINK, true);
			if (waitForAndGetElement(ELEMENT_MY_ACTIVITIES, 5000, 0) != null){
				info("Element " + ELEMENT_MY_ACTIVITIES + "... is displayed");
				break;
				}
				info("Retry...[" + repeat + "]");
			}
			click(ELEMENT_MY_ACTIVITIES);
			return new MyActivities(driver,this.plfVersion);
		}
	*/
	/**
	 * Go to Username--> My Connection
	 * @return
	 */
	/*public MyConnection goToMyConnection(){
		info("--Go to My Connection--");		
		for(int repeat=0;; repeat ++){
			if (repeat > 1){
				mouseOverAndClick(ELEMENT_ACCOUNT_NAME_LINK);
				info("--Error mouse over and click: can't mouseover, need to use mouse over and click --");
				break;
				}
			mouseOver(ELEMENT_ACCOUNT_NAME_LINK, true);
			if (waitForAndGetElement(ELEMENT_MY_CONNECTIONS, 5000, 0) != null){
				info("Element " + ELEMENT_MY_CONNECTIONS + "... is displayed");
				break;
				}
				info("Retry...[" + repeat + "]");
			}
			click(ELEMENT_MY_CONNECTIONS);
			return new MyConnection(driver,this.plfVersion);
		}
	*/
	/**
	 * Go to Username-->My Wiki
	 * @return
	 */
	/*public WikiHomePage goToMyWiki(){
		info("--Go to My Wiki--");		
		for(int repeat=0;; repeat ++){
			if (repeat > 1){
				mouseOverAndClick(ELEMENT_ACCOUNT_NAME_LINK);
				info("--Error mouse over and click: can't mouseover, need to use mouse over and click --");
				break;
				}
			mouseOver(ELEMENT_ACCOUNT_NAME_LINK, true);
			if (waitForAndGetElement(ELEMENT_MY_WIKI, 5000, 0) != null){
				info("Element " + ELEMENT_MY_WIKI + "... is displayed");
				break;
				}
				info("Retry...[" + repeat + "]");
			}
			click(ELEMENT_MY_WIKI);
			return new WikiHomePage(driver);
		}
	*/
	/**
	 * Go to Username--> My Dashboard
	 * @return
	 */
	/*public MyDashBoard goToMyDashboard(){
		info("--Go to Dashboard page--");
		for(int repeat=0;; repeat ++){
			if (repeat > 1){
				mouseOverAndClick(ELEMENT_ACCOUNT_NAME_LINK);
				break;
			}
			mouseOver(ELEMENT_ACCOUNT_NAME_LINK, true);
			if (waitForAndGetElement(ELEMENT_MY_DASHBROARD, 5000, 0) != null){
				info("Element " + ELEMENT_MY_DASHBROARD + "... is displayed");
				break;
			}
			info("Retry...[" + repeat + "]");
		}
		click(ELEMENT_MY_DASHBROARD);
		return new MyDashBoard (driver,this.plfVersion);
	}
	
	public MyNotification goToMyNotification(){
		info("--Go to My Notification--");
		for(int repeat=0;; repeat ++){
			if (repeat > 1){
				mouseOverAndClick(ELEMENT_ACCOUNT_NAME_LINK);
				break;
			}
			mouseOver(ELEMENT_ACCOUNT_NAME_LINK, true);
			if (waitForAndGetElement(ELEMENT_MY_NOTIFICATION, 5000, 0) != null){
				info("Element " + ELEMENT_MY_NOTIFICATION + "... is displayed");
				break;
			}
			info("Retry...[" + repeat + "]");
		}
		click(ELEMENT_MY_NOTIFICATION);
		return new MyNotification (driver);
	}
    */
	/**
	 * Go to Username-->Settings
	 * @return
	 */
	/*public ManageUserAccount goToMySetting(){
		info("---Go to My Setting ---");
		mouseOver(ELEMENT_ACCOUNT_NAME_LINK, true);
		if (waitForAndGetElement(ELEMENT_MY_SETTING, 5000, 0) == null){
			info("Cannot use [mouseOver] on Navigation Tool Bar");
			mouseOverAndClick(ELEMENT_ACCOUNT_NAME_LINK);
		}
		mouseOverAndClick(ELEMENT_MY_SETTING);
		Utils.pause(1000);
		return new ManageUserAccount(driver,this.plfVersion);
    }*/
	
	/**
	 * Go to Username --> Change Language
	 * @return
	 */
	/*public ChangeLanguage changeLanguage(String language){
		button = new Button(driver);
		info("Change language for user");
		driver.navigate().refresh();
		Utils.pause(2000);
		mouseOver(ELEMENT_ACCOUNT_NAME_LINK, true);
		if (waitForAndGetElement(ELEMENT_MY_SETTING, 5000, 0) == null){
			for(int repeat=0;; repeat ++){
				if (repeat > 1){
					mouseOverAndClick(ELEMENT_ACCOUNT_NAME_LINK);
					info("--Error mouse over and click: can't mouseover, need to use mouse over and click --");
					break;
				}
				mouseOver(ELEMENT_ACCOUNT_NAME_LINK, true);
			}
		}
		if (language == "French"){
			mouseOverAndClick(ELEMENT_CHANGE_LANGUAGE);
			waitForAndGetElement(ELEMENT_CHANGE_LANGUAGE_POPUP);
			click(By.linkText(language));
			button.apply();
			waitForElementNotPresent(ELEMENT_CHANGE_LANGUAGE_POPUP);
		}else {
			mouseOverAndClick(ELEMENT_CHANGE_LANGUAGE_FRENCH);
			waitForAndGetElement(ELEMENT_CHANGE_LANGUAGE_POPUP_FRENCH);
			click(By.linkText(language));
			click(button.ELEMENT_APPLY_FRENCH_BUTTON);
			waitForElementNotPresent(ELEMENT_CHANGE_LANGUAGE_POPUP_FRENCH);
		}
		return new ChangeLanguage(driver,this.plfVersion);
	}
*/
/********************************************************************************************************************************************/
	
}
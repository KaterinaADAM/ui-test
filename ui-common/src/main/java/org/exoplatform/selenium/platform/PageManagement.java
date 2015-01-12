package org.exoplatform.selenium.platform;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.Dialog;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.Utils;
import  org.exoplatform.selenium.platform.NavigationToolbar;
import  org.exoplatform.selenium.platform.NavigationManagement;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class PageManagement extends PlatformBase {
	
	public PageManagement(WebDriver dr, String...plfVersion){
		driver = dr;
		this.plfVersion = plfVersion.length>0?plfVersion[0]:"4.0";
	}

	NavigationToolbar nav = new NavigationToolbar(driver);
	NavigationManagement navMag = new NavigationManagement(driver);
	Dialog dialog = new Dialog(driver);
	ManageAlert alt = new ManageAlert(driver);
	Button button ;

	/*
	 * Page Management
	 * */
	public final String ELEMENT_ADD_PAGE_BUTTON = "//a[text()='Add New Page']";
	public final String ELEMENT_INPUT_SEARCH_TITLE = "//input[@id='pageTitle']";
	public final By ELEMENT_INPUT_SITE_NAME = By.id("siteName");
	//public final String ELEMENT_PAGE_MANAGEMENT_SEARCH_BUTTON = "//*[contains(@class, 'uiIconSearch')]";
	public final String ELEMENT_PAGE_EDIT_ICON = "//*[contains(@title, '${page}')]/../..//*[@class='uiIconEditInfo uiIconLightGray']";
	public final String ELEMENT_PAGE_DELETE_ICON = "//*[contains(@title, '${page}')]/../..//*[@class='uiIconDelete uiIconLightGray']";

	//Add New Page Form (shown after click [Add New Page] button in Page Management)
	public final By ELEMENT_PAGE_NAME_INPUT = By.xpath("//input[@id='name']");
	public final By ELEMENT_PAGE_TITLE_INPUT = By.xpath("//input[@id='title']");
	public final By ELEMENT_SELECT_OWNER_TYPE = By.xpath("//select[@name='ownerType']");
	public final By ELEMENT_SELECT_OWNER_ID = By.xpath("//select[@name='ownerId']");
	public final By ELEMENT_OWNER_ID_INTRANET = By.xpath("//input[@id='ownerId' and @value='intranet']");
		
	//Message
	public final String MESSAGE_DELETE_PAGE = "Do you want to delete this page?";
	
	//Page's list 
	public final String ELEMENT_LIST_PAGE = "//*[@id='UIRepeater']//tbody/tr[${number}]//*[@title='${titlePage}']"; 
	public final String ELEMENT_PAGE_DELETE_ICON_AUX = ELEMENT_LIST_PAGE.replace("${number}", "${number}").replace("${titlePage}", "${titlePage}") + "/../..//*[@class='uiIconDelete uiIconLightGray']";
	
	//Manage page form
	public final By ELEMENT_SITEMAP_PAGE = By.xpath("//a[@href='/portal/intranet/SiteMapPage']");
	public final By ELEMENT_PORTAL_ADMINISTRATION = By.xpath("//div[@class='ExpandIcon ClearFix']//a[@class='NodeIcon DefaultPageIcon' and text()='Portal Administration']");
	public final By ELEMENT_APPLICATION_REGISTRY_PAGE = By.xpath("//div[@class='ChildrenContainer']//a[@class='NodeIcon DefaultPageIcon' and text()='Applications Registry']");
	public final By ELEMENT_MANAGE_PAGES = By.xpath("//div[@class='ChildrenContainer']//a[@class='NodeIcon DefaultPageIcon' and text()='Manage Pages']");
	public final By ELEMENT_ADD_USER_PAGES = By.xpath("//div[@class='ChildrenContainer']//a[@class='NodeIcon DefaultPageIcon' and text()='Add User']");
	public final By ELEMENT_MANAGE_USERS_AND_GROUPS_PAGES = By.xpath("//div[@class='ChildrenContainer']//a[@class='NodeIcon DefaultPageIcon' and text()='Manage Users and Groups']");
	
	/*================== Common Function ===================*/
	//Add a new page in PageManagement
	public void addNewPageAtManagePages(PageType type, String pageName, String pageTitle, boolean publicMode, 
			Map<String, String> permissions, String groupId, String membership, String pageConfig, Object pageLayout, boolean validate, String...ownerId ){

		button = new Button(driver);
		
		click(ELEMENT_ADD_PAGE_BUTTON);
		waitForTextPresent("Page Settings");	
		switch (type){
		case PORTAL:
			select(ELEMENT_SELECT_OWNER_TYPE, "portal");
			break;
		case GROUP:	
			select(ELEMENT_SELECT_OWNER_TYPE, "group");
			break;
		default:
			break;
		}
		if (ownerId.length > 0){
			if (ownerId[0] != null){
				select(ELEMENT_SELECT_OWNER_ID, ownerId[0]);
			}
		}
		type(ELEMENT_PAGE_NAME_INPUT, pageName, true);
		type(ELEMENT_PAGE_TITLE_INPUT, pageTitle, true);		

		//showMaxWindow
		click(ELEMENT_CHECKBOX_MAX_WINDOWS, 2);
		//choose Edit layout tab
		click(ELEMENT_PAGE_LAYOUT_TAB);
		click(ELEMENT_PAGE_LAYOUT_SETTING_COMBOBOX);
		click(ELEMENT_PAGE_LAYOUT_SETTING_COMBOBOX_OPTION.replace("${PageConfigOpt}", pageConfig));
		click(pageLayout);		
		//choose permission settings tab
		click(ELEMENT_PERMISSION_SETTING_TAB);	
		WebElement element = waitForAndGetElement(ELEMENT_CHECKBOX_PUBLIC_MODE, DEFAULT_TIMEOUT, 1, 2);		
		if (publicMode & !element.isSelected()) {
			waitForAndGetElement(ELEMENT_ADD_PERMISSION_BUTTON);
			click(ELEMENT_CHECKBOX_PUBLIC_MODE, 2);
			waitForElementNotPresent(ELEMENT_ADD_PERMISSION_BUTTON);
		} else if (publicMode & element.isSelected()){
			waitForElementNotPresent(ELEMENT_ADD_PERMISSION_BUTTON);
		} else {
			for (String key : permissions.keySet()) {
				setViewPermissions(key, permissions.get(key));
			}
		}		
		click(ELEMENT_EDIT_PERMISSION_SETTING);
		setEditPermissions(groupId, membership);
		button.save();
		Utils.pause(1000);
		if (validate) {
		searchPageInManagementPage(type, pageTitle, true);
		}
	}

	//Edit a page at Manage Pages
	public void editPageAtManagePages(PageType type, String pageTitle){
		String pageEditIcon = ELEMENT_PAGE_EDIT_ICON.replace("${page}", pageTitle);
		searchPageInManagementPage(type, pageTitle, true);
		click(pageEditIcon);
		Utils.pause(1000);
	}

	//Delete a page
	public void deletePage(PageType type, String pageTitle, int...wait){
		alt = new ManageAlert(driver);
		dialog = new Dialog(driver);
		//int waitTime = wait.length > 0 ? wait[0] : DEFAULT_TIMEOUT;
		String pageDeleteIcon = ELEMENT_PAGE_DELETE_ICON.replace("${page}", pageTitle);
		searchPageInManagementPage(type, pageTitle, true);
		waitForAndGetElement(pageDeleteIcon).click();
		Utils.pause(2000);
		alt.waitForConfirmation(MESSAGE_DELETE_PAGE);
		//waitForMessage("No result found.",waitTime);
		dialog.closeMessageDialog();
		//waitForTextNotPresent(pageTitle);
		waitForElementNotPresent(pageDeleteIcon);
	}

	// Search a page in Manage Pages
	public void searchPageInManagementPage(PageType type, String pageTitle, boolean validate, String...siteName){
		if (pageTitle != null){
			type(ELEMENT_INPUT_SEARCH_TITLE, pageTitle, true);
		}
		switch (type){
		case PORTAL:
			select(ELEMENT_SELECT_SEARCH_OPTION, "portal");
			break;
		case GROUP:
			select(ELEMENT_SELECT_SEARCH_OPTION, "group");
			break;
		default:
			break;
		}
		if (siteName.length > 0){
			type(ELEMENT_INPUT_SITE_NAME, siteName[0], true);
		}
		click(ELEMENT_PAGE_MANAGEMENT_SEARCH_BUTTON);
		Utils.pause(1000);
		if (validate)
		{
			if (pageTitle != null){
				waitForTextPresent(pageTitle);
			}
		}
	}

	// Input data for page
	public void addNewPageEditor(String nodeName, String displayName, String language, String categoryTitle, 
			Map<String, String> portletIds, boolean extendedLabelMode, boolean verify){

		type(ELEMENT_INPUT_NODE_NAME, nodeName, true);
		WebElement element = waitForAndGetElement(ELEMENT_CHECKBOX_EXTENDED_LABEL_MODE, DEFAULT_TIMEOUT, 1, 2);
		if (extendedLabelMode){
			Assert.assertTrue(element.isSelected());
			select(ELEMENT_SELECT_LANGUAGE, language);

		}else {
			uncheck(ELEMENT_CHECKBOX_EXTENDED_LABEL_MODE, 2);
			type(ELEMENT_INPUT_PAGE_DISPLAY_NAME, displayName, true);
		}

		click(ELEMENT_PAGE_EDITOR_NEXT_STEP);
		waitForTextPresent("Empty Layout");
		click(ELEMENT_PAGE_EDITOR_NEXT_STEP);

		if (categoryTitle != null){
			String category = ELEMENT_EDIT_PAGE_CATEGORY_MENU.replace("${categoryLabel}", categoryTitle);
			click(category);
		}
		
		for (String portletId : portletIds.keySet()) {
			String elementEditPagePage = ELEMENT_EDIT_PAGE_PAGE;
			//String verification = PORTLET_LABEL.replace("${portletName}", portletIdsAndVerifications.get(portletId));
			dragAndDropToObject("//div[@id='" + portletId + "']/div", elementEditPagePage);
			if(portletIds.get(portletId) != ""){
				dragAndDropToObject("//div[@id='" + portletIds.get(portletId) + "']/div", elementEditPagePage);
			}
		}
		if (!verify) { 
			Utils.pause(500);
			click(ELEMENT_PAGE_FINISH_BUTTON);
			waitForTextNotPresent("Page Editor");
		}
	}
	
	// Input data for page choose Column Page Configs 
		public void addNewPageEditorWithColum(String nodeName, String displayName, String language, String categoryTitle, 
				Map<String, String> portletIds, String containers, Map<String, String>containerIds,  boolean extendedLabelMode, boolean verify){

			type(ELEMENT_INPUT_NODE_NAME, nodeName, true);
			WebElement element = waitForAndGetElement(ELEMENT_CHECKBOX_EXTENDED_LABEL_MODE, DEFAULT_TIMEOUT, 1, 2);
			if (extendedLabelMode){
				Assert.assertTrue(element.isSelected());
				select(ELEMENT_SELECT_LANGUAGE, language);

			}else {
				uncheck(ELEMENT_CHECKBOX_EXTENDED_LABEL_MODE, 2);
				type(ELEMENT_INPUT_PAGE_DISPLAY_NAME, displayName, true);
			}

			click(ELEMENT_PAGE_EDITOR_NEXT_STEP);
			waitForTextPresent("Empty Layout");
			click(ELEMENT_PAGE_EDITOR_NEXT_STEP);
			if (containers != null){
				click(ELEMENT_CONTAINER_TAB);
				String containersID= ELEMENT_EDIT_PAGE_CATEGORY_MENU.replace("${categoryLabel}", containers);
				click(containersID);
				for (String containerId : containerIds.keySet()) {					
					String elementEditPagePage = ELEMENT_EDIT_PAGE_PAGE;					
					dragAndDropToObject("//div[@id='" + containerId + "']/div", elementEditPagePage);
					if(containerIds.get(containerId) != ""){
						dragAndDropToObject("//div[@id='" + containerIds.get(containerId) + "']/div", elementEditPagePage);
					}
				}
			}
			Utils.pause(500);
			if (categoryTitle != null){
				click(ELEMENT_APPLICATIONS_LINK);
				String category = ELEMENT_EDIT_PAGE_CATEGORY_MENU.replace("${categoryLabel}", categoryTitle);
				click(category);
			}			
			for (String portletId : portletIds.keySet()) {
				String elementEditPagePage1 = ELEMENT_PAGE_COLUMN.replace("${index}", "1");
				dragAndDropToObject("//div[@id='" + portletId + "']/div", elementEditPagePage1);
				if(portletIds.get(portletId) != ""){
					dragAndDropToObject("//div[@id='" + portletIds.get(portletId) + "']/div", elementEditPagePage1);
				}
				String elementEditPagePage2 = ELEMENT_PAGE_COLUMN.replace("${index}", "2");
				dragAndDropToObject("//div[@id='" + portletId + "']/div", elementEditPagePage1);
				if(portletIds.get(portletId) != ""){
					dragAndDropToObject("//div[@id='" + portletIds.get(portletId) + "']/div", elementEditPagePage2);
				}
			}
			
			if (!verify) { 
				Utils.pause(500);
				click(ELEMENT_PAGE_FINISH_BUTTON);
				waitForTextNotPresent("Page Editor");
			}
		}

	public void deletePageAtManagePageAndPortalNavigation(String pageName, boolean PageTypePortal, String portalName, 
			boolean PageTypeGroup, String groupName, String... nodeName){
		info("-- Deleting "+ pageName +" at Manage page and Portal Navigation--");
		nav = new NavigationToolbar(driver);
		navMag = new NavigationManagement(driver);
		nav.goToManagePages();
		if (PageTypePortal){
			deletePage(PageType.PORTAL, pageName);
			//delete page at Portal navigation
			nav.goToPortalSites();
			if (nodeName.length > 0){
				navMag.deleteNode(portalName, "", nodeName[0], true);
			}else {
				navMag.deleteNode(portalName, "", pageName, true);
			}
		}
		if (PageTypeGroup){
			deletePage(PageType.GROUP, pageName);
			//delete page at Portal/Group navigation
			nav.goToGroupSites();
			if (nodeName.length > 0){
				navMag.deleteNode(groupName, "", nodeName[0], true);
			}else {
				navMag.deleteNode(groupName, "", pageName, true);
			}
		}
	}
	
	public void goToPagePermissionOfAddPageInPageManagement() {
		info("--Go to Add new Page--");
			click(ELEMENT_ADD_PAGE_BUTTON);
			waitForTextPresent("Page Settings");	
		info("--Choose Permission tab--");
			click(ELEMENT_PERMISSION_SETTING_TAB);
	}
}
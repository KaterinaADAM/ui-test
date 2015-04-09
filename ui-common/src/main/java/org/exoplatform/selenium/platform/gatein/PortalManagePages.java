package org.exoplatform.selenium.platform.gatein;

import static org.exoplatform.selenium.TestLogger.info;


import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.Dialog;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.PlatformBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


/**
 * Path: Administration-->Portal-->Pages
 */
public class PortalManagePages extends PlatformBase {

	public final By ELEMENT_MANAGEPAGES_TITLE=By.xpath(".//*[text()='Manage Pages']");
	public final By ELEMENT_MANAGEPAGES_TITLE_FIELD=By.id("pageTitle");
	public final By ELEMENT_MANAGEPAGES_SITES_NAME_FIELD=By.id("siteName");
	public final By ELEMENT_MANAGEPAGES_TYPE_DROPBOX=By.xpath(".//*[@name='searchOption']");
	public final String ELEMENT_MANAGEPAGES_SELECT_TYPE=".//*[@name='searchOption']//*[contains(@value,'${type}')]";
	public final By ELEMENT_MANAGEPAGES_SEARCH_BUTTON = By.xpath(".//*[@class='uiIconSearch uiIconLightGray']");
	public final By ELEMENT_MANGEPAGES_INFORM_POPUP_OK= By.xpath(".//*[text()='OK']");
	public final By ELEMENT_MANAGEPAGES_ADD_NEW_PAGE_BTN = By.cssSelector("#UIPageManagementPortlet .btn");

	//Results search
	public final String ELEMENT_MANAGEPAGES_CONTENT_TITLE_COLUMN=".//*[contains(text(),'${title}')]";
	public final String ELEMENT_MANAGEPAGES_CONTENT_SEARCH_TABLE=".//*[contains(@title,'${type}::${siteName}')]/../..//*[contains(text(),'${title}')]";
	public final By ELEMENT_MAGEPAGES_CONTENT_ACTION_COLUMN_DELETE=By.xpath(".//*[@class='uiIconDelete uiIconLightGray']");
	public final By ELEMENT_MAGEPAGES_CONTENT_ACTION_COLUMN_EDIT=By.xpath(".//*[@class='uiIconEditInfo uiIconLightGray']");


	//Add new page popup
	public final By ELEMENT_MANAGEPAGES_ADD_NEW_PAGES_POPUP_SAVE_BTN=By.cssSelector("#UIMaskWorkspace .btn[onclick~=\"javascript:eXo.webui.UIForm.submitForm('UIPageForm','Save',true)\"]");
	public final By ELEMENT_MANAGEPAGES_ADD_NEW_PAGE_POPUP_PAGE_NAME =By.cssSelector("#UIMaskWorkspace #name");
	public final By ELEMENT_MANAGEPAGES_ADD_NEW_PAGE_POPUP_TITLE = By.cssSelector("#UIMaskWorkspace #title");
	public final By ELEMENT_MANAGEPAGES_ADD_NEW_PAGE_POPUP_CHECKBOX = By.cssSelector("#UIMaskWorkspace #showMaxWindow");
	public final By ELEMENT_MANAGEPAGES_ADD_NEW_PAGE_POPUP_TYPE_DROPBOX =By.xpath(".//*[@name='ownerType']");
	public final By ELEMENT_MANAGEPAGES_ADD_NEW_PAGE_POPUP_TYPE_DROPBOX_OPTION =By.xpath(".//*[@name='ownerType']/option"); 
	public final String ELEMENT_MANAGEPAGES_ADD_NEW_PAGE_POPUP_TYPE_DROPBOX_VALUE="//*[@name='ownerType']//*[@value='${value}']";

	//Search Page
	public final By ELEMENT_INPUT_SEARCH_TITLE = By.id("pageTitle");
	public final String ELEMENT_SELECT_SEARCH_OPTION = "//*[@class='selectbox' and @name='searchOption']";
	public final By ELEMENT_INPUT_SITE_NAME = By.id("siteName");
	public final String ELEMENT_PAGE_MANAGEMENT_SEARCH_BUTTON = "//*[@data-original-title='Quick Search']/i";
	//public final String ELEMENT_LIST_PAGE = ".//*[@id='UIRepeater']//tr[${number}]//*[@title='${titlePage}']";
	public final String ELEMENT_LIST_PAGE = "//*[@id='UIRepeater']//tbody/tr[${number}]//*[@title='${titlePage}']"; 
	public final String ELEMENT_PAGE_DELETE_ICON_AUX = ELEMENT_LIST_PAGE.replace("${number}", "${number}").replace("${titlePage}", "${titlePage}") + "/../..//*[@class='uiIconDelete uiIconLightGray']";	
	public final String ELEMENT_PAGE_DELETE_ICON = "//*[contains(@title,'${page}')]/../..//*[@class='uiIconDelete uiIconLightGray']";
	public final String MESSAGE_DELETE_PAGE = "Do you want to delete this page?";
	public final String ELEMENT_NODE_LINK = ".//*[@id='UINavigationNodeSelector']//*[@title='${nodeLabel}']/i";

	//Edit properties of page
	public final String ELEMENT_VIEW_PAGE_PROPERTIES = ".//*[@id='UIPageEditor']//*[contains(text(),'View Page properties')]";
	public final By ELEMENT_VIEWPAGE_PAGETITLE = By.id("title");
	public final String ELEMENT_PERMISSION_SETTING_TAB = ".//*[@id='UIMaskWorkspace']//*[contains(text(),'Permission Settings')]";
	public final String ELEMENT_EDIT_PERMISSION_SETTING = ".//*[@id='PermissionSetting']//*[contains(text(),'Edit Permission Settings')]";
	public final String ELEMENT_SELECT_PERMISSION_BUTTON = ".//*[@id='UIPermissionSelector']//*[contains(text(),'Select Permission')]";
	public final String ELEMENT_SELECT_EDIT_GROUP_ITEM = ".//*[@id='PermissionSelector']//*[@title='${group}']/i";
	public final String ELEMENT_SELECT_EDIT_MEMBERSHIP_ITEM = ".//*[@id='PermissionSelector']//*[@title='${membership}']";
	public final String ELEMENT_SELECTED_EDIT_PERMISSION_MEMBERSHIP = ".//*[@id='UIPermissionSelector']//*[contains(text(),'Membership')]/../*[contains(text(),'${membership}')]";
	public final String ELEMENT_PAGE_FINISH_BUTTON = ".//*[@id='UIPageEditor']//*[@data-original-title='Finish']";

	ManageAlert alert;
	Button button;
	Dialog dialog;

	public PortalManagePages(WebDriver dr){
		driver = dr;
		alert = new ManageAlert(dr);
		button = new Button(driver, this.plfVersion);
		dialog = new Dialog(driver);
	} 


	/**
	 * Search a page
	 * @param titlePage
	 * @param siteName
	 * @param type
	 */
	public void searchPage(String title,String siteName, String type,boolean... verify){
		info("waiting the page is loaded full");
		waitForAndGetElement(ELEMENT_MANAGEPAGES_TITLE_FIELD);
		if (!title.isEmpty()) {
			info("Input a new title");
			type(ELEMENT_MANAGEPAGES_TITLE_FIELD, title, true);
		}
		if (!siteName.isEmpty()){
			info("Input a new site Name");
			type(ELEMENT_MANAGEPAGES_SITES_NAME_FIELD,siteName, true);
		}
		if(!type.isEmpty()){
			info("Select a type");
			selectOption(ELEMENT_MANAGEPAGES_TYPE_DROPBOX, type);
		}
		info("Click on Search button");
		click(ELEMENT_MANAGEPAGES_SEARCH_BUTTON);
		if (verify.length>0) {
			info("Verify that the search page is shown with correct results");
			if (!title.isEmpty())
				waitForAndGetElement(
						ELEMENT_MANAGEPAGES_CONTENT_TITLE_COLUMN.replace("${title}", title), 3000,0);
			if (!siteName.isEmpty() & !type.isEmpty())
				waitForAndGetElement(
						ELEMENT_MANAGEPAGES_CONTENT_SEARCH_TABLE
						.replace("${type}", type)
						.replace("${siteName}", siteName)
						.replace("${title}", title), 2000, 0);
		}
	}


	/**
	 * Delete a page
	 * @param titlePage
	 */
	public void deletePage(String titlePage,String type){
		info("Delete a page");
		searchPage(titlePage,"",type);
		click(ELEMENT_MAGEPAGES_CONTENT_ACTION_COLUMN_DELETE);
		alert.acceptAlert();
		waitForAndGetElement(ELEMENT_MANGEPAGES_INFORM_POPUP_OK);
		click(ELEMENT_MANGEPAGES_INFORM_POPUP_OK);
		waitForElementNotPresent(ELEMENT_MANAGEPAGES_CONTENT_TITLE_COLUMN.replace("${tilte}",titlePage));
		Utils.pause(2000);
	}



	/**
	 * Go to edit a page
	 * @param titilePage
	 */
	public void editPage(String titlePage,String type){
		info("Go to edit a page");
		searchPage(titlePage,"",type);
		info("Click on Edit button");
		click(ELEMENT_MAGEPAGES_CONTENT_ACTION_COLUMN_EDIT);
		Utils.pause(2000);
	}
	/**
	 * Add a new simple page
	 * @param pageName
	 * @param title
	 * @param type
	 * @param isMaxWindow
	 */
	public void addPage(String pageName,String title,String type,boolean... isMaxWindow){
		info("Click on Add new Page button");
		click(ELEMENT_MANAGEPAGES_ADD_NEW_PAGE_BTN);
		if (!pageName.isEmpty()) {
			info("Input page name");
			type(ELEMENT_MANAGEPAGES_ADD_NEW_PAGE_POPUP_PAGE_NAME,pageName,true);
		}
		if (!title.isEmpty()) {
			info("Input title");
			type(ELEMENT_MANAGEPAGES_ADD_NEW_PAGE_POPUP_TITLE,title,true);
		}
		if (!type.isEmpty()){
			info("Select a type");
			selectOptionUsingList(ELEMENT_MANAGEPAGES_ADD_NEW_PAGE_POPUP_TYPE_DROPBOX, type);
		}
		if(isMaxWindow.length>0){
			info("Tick on show Max Window checkbox");
			check(ELEMENT_MANAGEPAGES_ADD_NEW_PAGE_POPUP_CHECKBOX,2);
		}
		info("Save all changes");
		click(ELEMENT_MANAGEPAGES_ADD_NEW_PAGES_POPUP_SAVE_BTN);
		Utils.pause(2000);
	}


}

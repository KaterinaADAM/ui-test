package org.exoplatform.selenium.platform.gatein;
import static org.exoplatform.selenium.TestLogger.info;

import java.util.ArrayList;

import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.HomePagePlatform;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ApplicationRegistry extends PlatformBase {


	public final By ELEMENT_APPLICATION_REGISTRY_PORTLET=By.id("UIApplicationRegistryPortlet");
	public final By ELEMENT_MANAGE_APPLICATION_BUTTON=By.xpath("//*[@class='uiIconManageApplication uiIconLightGray']");
	public final By ELEMENT_APPLICATION_REGISTRY_ADD_CATEGORY_BTN=By.xpath(".//*[contains(@class,'uiIconManageCategory uiIconLightGray')]");


	//Add category page
	public final By ELEMENT_ADD_CATEGORY_NAME=By.id("name");
	public final By ELEMENT_ADD_CATEGORY_DISPLAY_NAME=By.id("displayName");
	public final By ELEMENT_ADD_CATEGORY_DESCRIPTION=By.id("description");
	public final By ELEMENT_ADD_CATEGORY_SAVE_BTN=By.xpath(".//*[@id='UICategoryForm']//button[text()='Save']");
	public final By ELEMENT_ADD_CATEGORY_CANCEL_BTN=By.xpath(".//*[@id='UICategoryForm']//button[text()='Cancel']");
	public final By ELEMENT_ADD_CATEGORY_PERMISSION_TAB = By.xpath(".//*[contains(@data-target,'#categoryPermission-tab')]");
	public final By ELEMENT_ADD_CATEGORY_PERMISSION_PUBLIC_CHECKBOX= By.xpath(".//*[@id='publicMode']");

	//Application registry page
	public final By ELEMENT_SHOW_IMPORT_APPLICATION = By.id("showImport");
	public final By ELEMENT_IMPORT_ALL_APPLICATION=By.xpath("//*[@class='uiIconImport uiIconLightGray']");
	public final By ELEMENT_APPLICATION_GADGETBTN = By.cssSelector(".uiIconGadgets.uiIconLightGray");

	//Left panel
	public final String ELEMENT_LEFT_PANEL_ADD_APPLICATION_BTN=".//*[contains(@href,'#${category}')]/../..//*[@class='uiIconPlus uiIconLightGray']";
	public final String ELEMENT_LEFT_PANEL_APPLICATION_NAME = ".//*[@id='${category}']//*[contains(@data-original-title,'${application}')]";
	public final String ELEMENT_LEFT_PANEL_APPLICATION_DELETE_BTN =".//*[contains(@data-original-title,'${application}')]/..//*[contains(@class,'uiIconTrashMini uiIconLightGray')]";
	public final String ELEMENT_LEFT_PANEL_APPLICATION_CATEGORY_TAB=".//*[@id='ApplicationRegistryCategory']//*[@href='#${category}']";
	public final String ELEMENT_LEFT_PANEL_APPLICATION_CATEGORY_TAB_DOWN=".//*[@id='ApplicationRegistryCategory']//*[@href='#${category}' and not (contains(@class, 'collapsed'))]/*[contains(@class,'uiIconArrowDown')]";
	public final String ELEMENT_LEFT_PANEL_APPLICATION_CATEGORY_TAB_RIGHT=".//*[@id='ApplicationRegistryCategory']//*[@href='#${category}']/*[contains(@class,'uiIconArrowRight')]";
	public final String ELEMENT_LEFT_PANEL_APPLICATION_CATEGORY_DELETE_BTN=".//*[@href='#${category}']/../..//*[contains(@class,'uiIconDelete uiIconLightGray')]";
	public final String ELEMENT_LEFT_PANEL_APPLICATION_CATEGORY_EDIT_BTN=".//*[@href='#${category}']/../..//*[contains(@class,'uiIconEdit uiIconLightGray')]";
	//Right panel Add Application
	public final By ELEMENT_RIGHT_PANEL_ADD_APPLICATION_DISPLAY_FILED=By.id("displayName");
	public final By ELEMENT_RIGHT_PANEL_ADD_APPLICATION_SAVE_BTN = By.xpath(".//*[@id='UIAddApplicationForm']//button[text()='Add']");
	public final By ELEMENT_RIGHT_PANEL_ADD_APPLICATION_SELECTMENU=By.xpath(".//*[@id='UIAddApplicationForm']//*[@class='selectbox']");
	public final String ELEMENT_RIGHT_PANEL_ADD_APPLICATION_RADIOBTN=".//*[contains(@id,'description')][text()='${des}']/../..//*[contains(@id,'label')][text()='${name}']/../..//input[@type='radio']";
	public final String ELEMENT_RIGHT_PANEL_ADD_APPLICATION_NAME=".//*[contains(@id,'description')][text()='${des}']/../..//*[contains(@id,'label')][text()='${name}']";

	//paging control
	public final String ELEMENT_PAGING_CONTROL_NUMBER = ".//*[@id='UIAddApplicationForm']//a[text()='${number}']";
	public final By ELEMENT_PAGING_CONTROL_NEXT_PAGE_ENABLED=By.xpath(".//*[@data-original-title='Next Page' and @href='javascript:void(0);']");

	//view detail a porlet
	public final String ELEMENT_DETAIL_PORTLET_BREADCRUMB = ".//*[contains(@class,'breadcrumb')]//*[contains(text(),'${disName}')]";
	public final String ELEMENT_DETAIL_PORTLET_DISPLAY_NAME = ".//strong[contains(@title,'${disName}')]";
	public final String ELEMENT_DETAIL_PORTLET_APPLICATION_NAME =".//*[contains(@title,'${appName}')]";
	public final String ELEMENT_DETAIL_PORTLET_DESCRIPTION = ".//span[contains(@title,'${des}')]";
	public final By ELEMENT_PERMISSION_FORM = By.cssSelector(".UIPermissionForm");

	NavigationToolbar navTool;
	PageEditor pagEditor;
	HomePagePlatform hp;
	ManageAlert alert;
	PortalManagePages pagMag;


	public ApplicationRegistry(WebDriver dr){
		driver = dr;
		navTool = new NavigationToolbar(dr);
		pagEditor = new PageEditor(dr);
		hp=new HomePagePlatform(dr);
		alert=new ManageAlert(dr);
		pagMag = new PortalManagePages(dr);
	}

	/**
	 * Go to Manage Application Page
	 */
	public void goToManageApplication(){
		info("--Go to Manage Application Page--");
		waitForAndGetElement(ELEMENT_MANAGE_APPLICATION_BUTTON);
		click(ELEMENT_MANAGE_APPLICATION_BUTTON);
	}

	/**
	 * Show import application
	 * @param isShow
	 */
	public void showImportApplication(Boolean isShow){
		info("Show import application");
		if(isShow!=null){
			if(isShow){
				check(ELEMENT_SHOW_IMPORT_APPLICATION,2);
			}
			else{
				uncheck(ELEMENT_SHOW_IMPORT_APPLICATION,2);
			}
		}
	}

	/**
	 * Show import application button
	 */
	public void displayImportApplicaions(){
		info("Show all import application");
		navTool.goToApplication();
		navTool.goToEditLayout();
		pagEditor.goToEditPortlet(pagEditor.ELEMENT_FRAME_CONTAIN_PORTLET);
		showImportApplication(true);
		click(pagEditor.ELEMENT_EDIT_PORTLET_FORM_SAVE_BUTTON);
		click(pagEditor.ELEMENT_EDIT_PORTLET_FORM_CLOSE_BUTTON);
		pagEditor.finishEditLayout();
		info("Verify that import all application is shown");
		waitForAndGetElement(ELEMENT_IMPORT_ALL_APPLICATION,3000,0);
	}

	/**
	 * Hide import application button
	 */
	public void HideShowImportApplicaion(){
		info("Show all import application");
		if ("iexplorer".equals(browser)){
			navTool.goToPotalPages();
			pagMag.editPage("registry","group");
		}
		else{
			navTool.goToApplication();
			navTool.goToEditLayout();
		}
		pagEditor.goToEditPortlet(pagEditor.ELEMENT_FRAME_CONTAIN_PORTLET);
		showImportApplication(false);
		click(pagEditor.ELEMENT_EDIT_PORTLET_FORM_SAVE_BUTTON);
		click(pagEditor.ELEMENT_EDIT_PORTLET_FORM_CLOSE_BUTTON);
		pagEditor.finishEditLayout();
		info("Verify that import all application is hidden");
		waitForElementNotPresent(ELEMENT_IMPORT_ALL_APPLICATION,3000,0);
	}
	/**
	 * Import all applications
	 */
	public void importAllApplications(){
		info("click on the import Applications button");
		click(ELEMENT_IMPORT_ALL_APPLICATION);
		alert.acceptAlert();
		Utils.pause(2000);
		info("All applications are imported");
	}

	/**
	 * Open Gadget page
	 */
	public void goToGadgetPage(){
		info("Show Gadget page");
		click(ELEMENT_APPLICATION_GADGETBTN);
		Utils.pause(2000);
	}

	/**
	 * Add a application to a category for Portlet type
	 * 
	 * @param category
	 * @param nameApp
	 */
	public void addApplicationToCategory(String category, String nameApp,
			String des) {
		info("Click on Add button");
		waitForAndGetElement(ELEMENT_LEFT_PANEL_ADD_APPLICATION_BTN.replace(
				"${category}", category));
		click(ELEMENT_LEFT_PANEL_ADD_APPLICATION_BTN.replace("${category}",
				category));
		info("Verify that right panel is shown");
		waitForAndGetElement(ELEMENT_RIGHT_PANEL_ADD_APPLICATION_DISPLAY_FILED);
		info("Type the name for the application");
		type(ELEMENT_RIGHT_PANEL_ADD_APPLICATION_DISPLAY_FILED, nameApp, true);
		info("Des:"+des);
		while(waitForAndGetElement(ELEMENT_RIGHT_PANEL_ADD_APPLICATION_NAME.replace(
				"${des}", des).replace("${name}",nameApp), 5000, 0,2)==null){
			if(waitForAndGetElement(ELEMENT_PAGING_CONTROL_NEXT_PAGE_ENABLED, 2000, 0)!=null)
				click(ELEMENT_PAGING_CONTROL_NEXT_PAGE_ENABLED);
			else assert false:"Not found application with the name:"+nameApp;
		}

		check(ELEMENT_RIGHT_PANEL_ADD_APPLICATION_RADIOBTN.replace(
				"${des}", des).replace("${name}",nameApp), 2);
		info("Save all changes");
		click(ELEMENT_RIGHT_PANEL_ADD_APPLICATION_SAVE_BTN);
		Utils.pause(2000);
		info("Verify that the app is added to correct category");
		waitForAndGetElement(ELEMENT_LEFT_PANEL_APPLICATION_NAME.replace("${category}",category).replace("${application}",nameApp),2000,0);
	}

	/**
	 * Delete an application of a category
	 * 
	 * @param category
	 * @param application
	 */
	public void deleteApplication(String application) {
		info("click on Delete button");
		click(ELEMENT_LEFT_PANEL_APPLICATION_DELETE_BTN.replace("${application}", application));
		alert.acceptAlert();
		waitForElementNotPresent(ELEMENT_LEFT_PANEL_APPLICATION_DELETE_BTN.replace("${application}", application));
	}

	/**
	 * Check all imported Applications is shown in the left list
	 * after click on Import Application button
	 * @param categoryList
	 * @param nameList
	 */
	public void checkImportedApplications(ArrayList<String>categoryList,ArrayList<String>nameList){
		info("Reading....from Category list");
		for (int i = 0; i < categoryList.size(); i++) {
			info("Category:"+categoryList.get(i));
			info("Application's name:"+nameList.get(i));
			scrollBarToGetElement(By.xpath(ELEMENT_LEFT_PANEL_APPLICATION_CATEGORY_TAB.replace("${category}", categoryList.get(i))));
			if(waitForAndGetElement(ELEMENT_LEFT_PANEL_APPLICATION_CATEGORY_TAB_DOWN.replace("${category}", categoryList.get(i)),5000,0)==null){
				info("Open category "+categoryList.get(i));
				click(ELEMENT_LEFT_PANEL_APPLICATION_CATEGORY_TAB.replace("${category}",categoryList.get(i).trim()));
			}
			info("Verify that the imported application is shown in correct category");
			waitForAndGetElement(ELEMENT_LEFT_PANEL_APPLICATION_NAME
					.replace("${category}",categoryList.get(i))
					.replace("${application}",nameList.get(i)));
			info("The application:"+nameList.get(i)+" in Category:"+categoryList.get(i)+" was imported");
		}
	}
	/**
	 * Add a new simple Category with public permission
	 * @param categoryName
	 * @param displayName
	 * @param des
	 */
	public void addACategory(String categoryName, String displayName, String des) {
		info("Click on Add Category button");
		Utils.pause(2000);
		click(ELEMENT_APPLICATION_REGISTRY_ADD_CATEGORY_BTN);
		info("Input category name");
		type(ELEMENT_ADD_CATEGORY_NAME, categoryName, true);
		if (!displayName.isEmpty()) {
			info("Input display name");
			type(ELEMENT_ADD_CATEGORY_DISPLAY_NAME, displayName, true);
		}
		if (!des.isEmpty()) {
			info("Input description");
			type(ELEMENT_ADD_CATEGORY_DESCRIPTION, des, true);
		}
		click(ELEMENT_ADD_CATEGORY_PERMISSION_TAB);
		check(ELEMENT_ADD_CATEGORY_PERMISSION_PUBLIC_CHECKBOX,2);
		click(ELEMENT_ADD_CATEGORY_SAVE_BTN);
		info("Verify that the new category is added successfully");
		scrollBarToGetElement(ELEMENT_LEFT_PANEL_APPLICATION_CATEGORY_TAB.replace("${category}",categoryName));
		waitForAndGetElement( ELEMENT_LEFT_PANEL_APPLICATION_CATEGORY_TAB.replace("${category}", categoryName),2000,1);
	}
	/**
	 * Edit a simple Category
	 * @param name
	 * @param newDisplayName
	 * @param newDes
	 */
	public void editCategory(String name,String newDisplayName,String newDes){
		info("Click on Edit button");
		scrollBarToGetElement(ELEMENT_LEFT_PANEL_APPLICATION_CATEGORY_EDIT_BTN.replace("${category}",name));
		click(ELEMENT_LEFT_PANEL_APPLICATION_CATEGORY_EDIT_BTN.replace("${category}",name));
		if(!newDisplayName.isEmpty()){
			info("Input display name");
			type(ELEMENT_ADD_CATEGORY_DISPLAY_NAME, newDisplayName, true);
		}
		if(!newDes.isEmpty()){
			info("Input description");
			type(ELEMENT_ADD_CATEGORY_DESCRIPTION, newDes, true);
		}
		click(ELEMENT_ADD_CATEGORY_SAVE_BTN);
		info("Verify that the new category is edit successfully");
		waitForAndGetElement( ELEMENT_LEFT_PANEL_APPLICATION_CATEGORY_TAB.replace("${category}", newDisplayName),2000,0);
	}
	/**
	 * Delete a category
	 * @param nameCategory
	 */
	public void deleteCategory(String nameCategory){
		info("Click on Delete button");
		scrollBarToGetElement(ELEMENT_LEFT_PANEL_APPLICATION_CATEGORY_EDIT_BTN.replace("${category}",nameCategory));
		click(ELEMENT_LEFT_PANEL_APPLICATION_CATEGORY_DELETE_BTN.replace("${category}", nameCategory));
		alert.acceptAlert();
		info("Verify that the category is deleted");
		waitForElementNotPresent(ELEMENT_LEFT_PANEL_APPLICATION_CATEGORY_DELETE_BTN.replace("${category}", nameCategory),2000,0);
		Utils.pause(3000);
	}
	/**
	 * Select a portlet on the left panel
	 * @param category
	 * @param displayName
	 */
	public void selectAPortlet(String category,String displayName,boolean isOpenTab){
		info("Cick on the category tab");
		if(isOpenTab==true)
			click(ELEMENT_LEFT_PANEL_APPLICATION_CATEGORY_TAB.replace("${category}",category));
		info("Select an application");
		click(ELEMENT_LEFT_PANEL_APPLICATION_NAME.replace("${category}",category).replace("${application}",displayName));
		Utils.pause(2000);
	}
	/**
	 * Checking a viewing detail of a portlet
	 * @param displayName
	 * @param appName
	 * @param des
	 */
	public void viewDetailPortlet(String displayName,String appName,String des){
		info("Check Bread crumb");
		waitForAndGetElement(ELEMENT_DETAIL_PORTLET_BREADCRUMB.replace("${disName}", displayName),2000,0);
		info("Check display name, Application name and description");
		waitForAndGetElement(ELEMENT_DETAIL_PORTLET_DISPLAY_NAME.replace("${disName}",displayName),2000,0);
		info("Check Application name");
		waitForAndGetElement(ELEMENT_DETAIL_PORTLET_APPLICATION_NAME.replace("${appName}", appName),2000,0);
		info("Check description");
		waitForAndGetElement(ELEMENT_DETAIL_PORTLET_DESCRIPTION.replace("${des}",des),2000,0);
		info("Check title of Permission table");
		waitForAndGetElement(ELEMENT_PERMISSION_FORM,2000,0);
	}
}

package org.exoplatform.selenium.platform.forum;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.PlatformBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.exoplatform.selenium.TestLogger.info;

public class ForumCategoryManagement extends PlatformBase {

	//Home page
	public final String ELEMENT_FORUM_CATEGORY_HOME_TITLE_LINK = ".//*[text()='${name}']";
	
	//Action bar
	public final By ELEMENT_ACTIONBAR_ADDCATEGORY = By.xpath("//*[@class='uiIconAddCategory uiIconLightGray']");
	
	//Manage menu
	public final By ELEMENT_MENU_MANAGE_CATEGORY = By.xpath("//*[@class='uiIconForumManageCategory uiIconForumLightGray']");
	public final By ELEMENT_EDIT_CATEGORY = By.xpath("//*[@class='dropdown-menu uiCategoryPopupMenu']/li[1]/a");
	public final By ELEMENT_EXPORT_FORUM  = By.xpath("//*[@class='dropdown-menu uiCategoryPopupMenu']/li[2]/a");
	public final By ELEMENT_IMPORT_FORUM = By.xpath("//*[@class='dropdown-menu uiCategoryPopupMenu']/li[3]/a");
	public final By ELEMENT_DELETE_CATEGORY = By.xpath("//*[@id='UICategoryConfirm0' and contains(text(),'Delete')]");
	
	
	//Add category popup
	public final By ELEMENT_ADDCATEGORY_POPUP_TITLE= By.id("CategoryTitle");
	public final By ELEMENT_ADDCATEGORY_POPUP_ORDER= By.id("CategoryOrder");
	public final By ELEMENT_ADDCATEGORY_POPUP_DESCRIPTION= By.id("Description");
	public final By ELEMENT_ADDCATEGORY_POPUP_SAVE_BUTTON= By.xpath(".//*[@id='UICategoryForm']//button[text()='Save']");
	public final By ELEMENT_ADDCATEGORY_POPUP_CANCEL_BUTTON= By.xpath(".//*[@id='UICategoryForm']//button[text()='Cancel']");
	
	//Popup confirmation
	public final By ELEMENT_OK_DELETE = By.xpath("//*[@id='UIForumPopupConfirmation']//*[text()='OK']");
	public final String ELEMENT_CATEGORY_DELETE_CONFIRM_MSG="Are you sure you want to delete this category ?";
	
	//Export forum popup
	public final By ELEMENT_FILENAME_INPUT=By.id("FileName");
	public final By ELEMENT_EXPORT_FORUM_EXPORTALL = By.id("checkAll");
	public final String ELEMENT_EXPORT_FORUM_EXPORT = "//*[contains(text(),'${title}')]/..//*[@class='uiCheckbox']//*[@class='checkbox']";
	public final By ELEMENT_SAVE_BTN = By.xpath("//*[text()='Save']");
	
	ManageAlert alert;
	Button button;
	/**
	 * constructor
	 * @param dr
	 */
	public ForumCategoryManagement(WebDriver dr){
		this.driver=dr;
		alert = new ManageAlert(dr);
		button = new Button(driver);
	}
	
	
	/**
	 * Add a new category By QuynhPT
	 * @param nameCat
	 *            the title of category
	 * @param order
	 *            the order's number as:0,1,2,3...(0 is default value)
	 * @param description
	 *            the content of description for category
	 */
	public void addCategorySimple(String nameCat, String order, String description) {
		waitForAndGetElement(ELEMENT_ACTIONBAR_ADDCATEGORY);
		info("click on Add Category button");
		click(ELEMENT_ACTIONBAR_ADDCATEGORY);
	    info("input the title for the category");
	    type(ELEMENT_ADDCATEGORY_POPUP_TITLE,nameCat,true);
	    info("check and input Oder field");
	    if(order!=null && order!=""){
	    	 info("Clear all old order data");
	    	 waitForAndGetElement(ELEMENT_ADDCATEGORY_POPUP_ORDER).clear();
	    	 info("Input new order");
	    	 type(ELEMENT_ADDCATEGORY_POPUP_ORDER,order,true);
	    }
        info("check and input description");
        if (description!=null && description!=""){
	    	info("Clear all old description data");
	    	waitForAndGetElement(ELEMENT_ADDCATEGORY_POPUP_DESCRIPTION).clear();
	    	info("Input new description data");
	    	type(ELEMENT_ADDCATEGORY_POPUP_DESCRIPTION,description,true);
	    }
	    info("Click on Save button");
		click(ELEMENT_ADDCATEGORY_POPUP_SAVE_BUTTON);
		Utils.pause(2000);
		info("Fisnihed adding a category");
	}
	/**
	 * list sublinks in Manage Cagegory menu
	 * @author quynhpt
	 *
	 */
	public enum specifManageCategoryMenu{
		EDIT_CATEGORY,EXPORT_FORUM,IMPORT_FORUM,DELETE,WATCHES,
		ADD_FORUM,EDIT_FORUM,LOCK,UNLOCK,OPEN,CLOSE,MOVE,REMOVE;
	}
	
	/**
	 * select a item in Manage Category Menu
	 * By QuynhPT
	 * @param item
	 */
	public void selectItemManageCategoryMenu(specifManageCategoryMenu item) {
		info("Waiting manage menu is shown");
		waitForAndGetElement(ELEMENT_MENU_MANAGE_CATEGORY);
		info("Click on Manage menu");
		click(ELEMENT_MENU_MANAGE_CATEGORY);
		Utils.pause(1000);
		switch (item) {
		case EDIT_CATEGORY:
			info("click on Edit link");
			click(ELEMENT_EDIT_CATEGORY);
			Utils.pause(1000);
			break;
		case EXPORT_FORUM:
			info("Click on Export link");
			click(ELEMENT_EXPORT_FORUM);
			Utils.pause(1000);
			break;
		case IMPORT_FORUM:
			info("Click on Import link");
			click(ELEMENT_IMPORT_FORUM);
			Utils.pause(1000);
			break;
		case DELETE:
			info("Click on Delete link");
			click(ELEMENT_DELETE_CATEGORY);
			Utils.pause(1000);
			alert.waitForMessage(ELEMENT_CATEGORY_DELETE_CONFIRM_MSG);
			click(ELEMENT_OK_DELETE);
			break;
		case WATCHES:
			break;
		case ADD_FORUM:
			break;
		case EDIT_FORUM:
			break;
		case LOCK:
			break;
		case UNLOCK:
			break;
		case CLOSE:
			break;
		case OPEN:
			break;
		case MOVE:
			break;
		case REMOVE:
			break;
		}
	}
	 
	/**
	 * Edit a category
	 * @param newName
	 */
	public void editCategory(String newName) {
		selectItemManageCategoryMenu(specifManageCategoryMenu.EDIT_CATEGORY);
		info("Imput a new name");
		type(ELEMENT_ADDCATEGORY_POPUP_TITLE, newName, true);
		info("Save all changes");
		click(ELEMENT_ADDCATEGORY_POPUP_SAVE_BUTTON);
		info("Verify that the name is changed with new name");
		waitForAndGetElement(ELEMENT_FORUM_CATEGORY_HOME_TITLE_LINK.replace("${name}", newName));
	}
	
	/**
	 * Delete Category 
	 * By QuynhPT
	 * @param nameCat
	 */
	public void deleteCategory(String nameCat) {
		// TODO Auto-generated method stub
		info("Wait the category is shown");
		waitForAndGetElement(ELEMENT_FORUM_CATEGORY_HOME_TITLE_LINK.replace("${name}", nameCat),3000,0);
		info("Click on the category");
		click(ELEMENT_FORUM_CATEGORY_HOME_TITLE_LINK.replace("${name}", nameCat));
		info("Select Delete link");
		selectItemManageCategoryMenu(specifManageCategoryMenu.DELETE);
		info("Verify that the category is deleted");
		waitForElementNotPresent(ELEMENT_FORUM_CATEGORY_HOME_TITLE_LINK.replace("${name}", nameCat),3000,0);
		info("The category is deleted successfully");
	}
	
	/**
	 * Cancel all changes of Add Category
	 * By QuynhPT
	 */
	public void cancelChangeCategory(){
		waitForAndGetElement(ELEMENT_ADDCATEGORY_POPUP_CANCEL_BUTTON);
		click(ELEMENT_ADDCATEGORY_POPUP_CANCEL_BUTTON);
		Utils.pause(2000);
	}
	
	/**
	 * Export a forum
	 * @param forumName
	 * @param fileName
	 */
	public void exportForum(String forumName, String fileName) {
		selectItemManageCategoryMenu(specifManageCategoryMenu.EXPORT_FORUM);
		info("Uncheck All check boxes");
		uncheck(ELEMENT_EXPORT_FORUM_EXPORTALL, 2);
		info("Select check box of the forum");
		check((ELEMENT_EXPORT_FORUM_EXPORT).replace("${title}", forumName), 2);
		info("input name");
		type(ELEMENT_FILENAME_INPUT,fileName,true);
		info("Save all changes");
		click(ELEMENT_SAVE_BTN);
	}
	
	/**
	 * Import a forum
	 * 
	 * @param folderDowloadFile
	 * @param nameFile
	 */
	public void importForum(String folderDowloadFile, String nameFile) {
		selectItemManageCategoryMenu(specifManageCategoryMenu.IMPORT_FORUM);
		importFile(folderDowloadFile, nameFile);
		button.ok();
	}
}

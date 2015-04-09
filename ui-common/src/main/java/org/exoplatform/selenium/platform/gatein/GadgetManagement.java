package org.exoplatform.selenium.platform.gatein;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.PlatformBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GadgetManagement extends PlatformBase {

	//Remote gadget on righ panel
    public final By ELEMENT_REMOTE_GADGETBTN = By.xpath(".//*[@id='UIGadgetManagement']//a[contains(.,'Add a remote gadget')]");
	public final By ELEMENT_REMOTE_GADGET_URL = By.id("url");
	public final By ELEMENT_REMOTE_GADGET_ADDBTN = By.xpath(".//*[@id='UIAddGadget']//button[text()='Add']");
	public final By ELEMENT_REMOTE_GADGET_ADD_INTO_CATEGORY_LINK = By.xpath(".//*[@id='UIGadgetInfo']//a[contains(text(),'Click here to add into categories')]");
	public final By ELEMENT_REMOTE_GADGET_TABLE_CATEGORIES=By.xpath(".//*[@id='GadgetCategory']//div[@class='controls-full']");
	public final String ELEMENT_REMOTE_GADGET_TABLE_CATEGORIES_CHECKBOX = ".//*[@id='categoryName'][contains(text(),'${category}')]/../..//input[@type='checkbox']";
	public final By ELEMENT_REMOTE_GADGET_TABLE_CATEGORIES_SAVEBTN = By.xpath(".//*[@id='GadgetCategory']//button[text()='Save']");
	public final String ELEMENT_REMOTE_GADGET_INFO_CATEGORY =".//*[text()='Categories:']/..//*[contains(text(),'${category}')]";
	//left panel
	public final String ELEMENT_REMOTE_GADGET_LEFT_CONTENT=".//*[contains(text(),'${name}')]";
	public final String ELEMENT_REMOTE_GADGET_LEFT_DELETE_BTN = ".//*[contains(text(),'${name}')]/../../../..//*[@class='uiIconDelete uiIconLightGray']";

	//Create new gadget
	public final By ELEMENT_CREATE_NEW_GADGET_BTN = By.cssSelector(".uiIconCreateNewGadget.uiIconLightGray");
	public final By ELEMENT_CREATE_NEW_GADGET_INPUT_NAME_FIELD= By.id("name");
	public final By ELEMENT_CREATE_NEW_GADGET_SOURCE_FIELD= By.id("source");
	public final By ELEMENT_CREATE_NEW_GADGET_SAVE_BTN =By.xpath(".//button[text()='Save']");
	
	//View detail of a Gadget
	public final By ELEMENT_GADGET_EDIT_BTN = By.cssSelector(".uiIconEdit.uiIconLightGray");
	public final By ELEMENT_PAGING_CONTROL_NEXT_PAGE_ENABLED = By.cssSelector("[href~='javascript:void(0);'] .uiIconNextArrow");
	
	ManageAlert alert;
	public GadgetManagement(WebDriver driver) {
		this.driver=driver;
		alert = new ManageAlert(driver);
	}
	/**
	 * Add a Remote gadget
	 * @param url
	 */
	public void addRemoteGadget(String url){
		info("Click on Gadgets button");
		click(ELEMENT_REMOTE_GADGETBTN);
		waitForAndGetElement(ELEMENT_REMOTE_GADGET_URL);
		info("input a url");
		insertTextUsingRobot(ELEMENT_REMOTE_GADGET_URL,url);
		info("Click on Add button");
		click(ELEMENT_REMOTE_GADGET_ADDBTN);
		Utils.pause(2000);
	}
	/**
	 * Add a new gadget into a category
	 * @param category
	 */
	public void addIntoCategory(String category){
		info("Click on the link: Click here to add into category");
		click(ELEMENT_REMOTE_GADGET_ADD_INTO_CATEGORY_LINK);
		info("Verify that the links is hided after clicked");
		waitForElementNotPresent(ELEMENT_REMOTE_GADGET_ADD_INTO_CATEGORY_LINK);
		info("Waiting categories table is shown");
		waitForAndGetElement( ELEMENT_REMOTE_GADGET_TABLE_CATEGORIES,2000,0);
		check(ELEMENT_REMOTE_GADGET_TABLE_CATEGORIES_CHECKBOX.replace("${category}",category),2);
		info("Save all changes");
		click(ELEMENT_REMOTE_GADGET_TABLE_CATEGORIES_SAVEBTN);
		Utils.pause(2000);
	}
	/**
	 * Delete a gadget
	 * @param name
	 */
	public void deleteGadget(String name){
		info("Click on delete button");
		scrollBarToGetElement(ELEMENT_REMOTE_GADGET_LEFT_DELETE_BTN.replace("${name}",name));
		click(ELEMENT_REMOTE_GADGET_LEFT_DELETE_BTN.replace("${name}",name));
		alert.acceptAlert();
		waitForElementNotPresent(ELEMENT_REMOTE_GADGET_LEFT_CONTENT.replace("${name}", name));
	}
	/**
	 * Create a new gadget
	 * @param name
	 * @param xmlCode
	 */
	public void createNewGadget(String name, String xmlCode){
		info("Click on Create new gadget button");
		click(ELEMENT_CREATE_NEW_GADGET_BTN);
		info("Input a new name");
		type(ELEMENT_CREATE_NEW_GADGET_INPUT_NAME_FIELD,name,true);
		if(!xmlCode.isEmpty()){
			info("Input a new source code");
			insertTextUsingRobot(ELEMENT_CREATE_NEW_GADGET_SOURCE_FIELD,xmlCode);
		}
		info("Click on save button");
		click(ELEMENT_CREATE_NEW_GADGET_SAVE_BTN);
		info("Verify that the gadget is created successfully");
		waitForAndGetElement(ELEMENT_REMOTE_GADGET_LEFT_CONTENT.replace("${name}",name),2000,0);
	}
	/**
	 * Edit a Gadget with newXmlCode that include newName
	 * @param oldName
	 * @param newXmlCode
	 * @param newName
	 */
	public void editAGadget(String oldName, String newXmlCode,String newName){
		info("Select a Gadget");
		scrollBarToGetElement(ELEMENT_REMOTE_GADGET_LEFT_DELETE_BTN.replace("${name}",oldName));
		click(ELEMENT_REMOTE_GADGET_LEFT_CONTENT.replace("${name}",oldName));
		info("Click on Edit button");
		click(ELEMENT_GADGET_EDIT_BTN);
		info("Change xml code");
		insertTextUsingRobot(ELEMENT_CREATE_NEW_GADGET_SOURCE_FIELD,newXmlCode);
		info("Click on Save button");
		click(ELEMENT_CREATE_NEW_GADGET_SAVE_BTN);
		info("Verify that the gadget is edit successfully");
		waitForAndGetElement(ELEMENT_REMOTE_GADGET_LEFT_CONTENT.replace("${name}",newName),2000,0);
	}
}

package org.exoplatform.selenium.platform.ecms;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * 
 * @author vuna2
 *
 */
public class ManageDrive extends ContentAdministration{

	public ManageDrive(WebDriver dr) {
		super(dr);
		// TODO Auto-generated constructor stub
	}
	
	Button button = new Button(driver);

	/*Manage Drive Page */
	//Add a drive
	public final By ELEMENT_ADD_DRIVER_BUTTON = By.xpath("//*[text()='Add Drive']"); 

	//Edit a drive
	public final By ELEMENT_DRIVE_EDIT_POPUP = By.id("EditDriveManagerPopup");
	public final String ELEMENT_DRIVE_EDIT = "//*[@title='${DATA_DRIVE_NAME}']/../..//*[@title='Edit']";
	public final String ELEMENT_DRIVE_EDIT_AUX = "//*[@data-original-title='${driveName}']/../..//*[@data-original-title='Edit']";

	//Delete a drive
	public final String ELEMENT_DRIVE_DELETE = "//*[@title='${DATA_DRIVE_NAME}']/../..//*[@title='Delete']";
	public final String ELEMENT_DRIVE_DELETE_AUX = "//*[@data-original-title='${DATA_DRIVE_NAME}']/../..//*[@data-original-title='Delete']";

	//Add a Driver form 
	public final By ELEMENT_ADD_DRIVE_POPUP = By.id("AddDriveManagerPopup");
	public final By ELEMENT_APPLY_VIEW_TAB = By.xpath("//*[contains(text(),'Apply Views')]");
	public final By ELEMENT_DRIVE_TAB = By.xpath("//*[contains(text(),'Drive')]");
	public final By ELEMENT_WORKSPACE = By.name("workspace");
	public final By ELEMENT_ADD_PATH = By.xpath("//*[@title='Add Path']");
	public final By ELEMENT_ADD_PATH_AUX = By.xpath("//*[@data-original-title='Add Path']");
	public final By ELEMENT_ADD_ROOT_NODE = By.xpath("//*[@title='AddRootNode']");
	public final By ELEMENT_ADD_ROOT_NODE_AUX = By.xpath("//*[@data-original-title='AddRootNode']");
	public final By ELEMENT_ADD_PATH_POPUP = By.id("JCRBrowser");
	public final By ELEMENT_JCR_SYSTEM = By.xpath("//*[contains(text(),'jcr:system')]/../..//*[@title='select']");
	public final String ELEMENT_SELECT_PATH_ITEM = "//*[contains(text(),'${node}')]/../..//*[@title='select']";
	public final String ELEMENT_SELECT_PATH_ITEM_AUX = "//*[contains(text(),'${node}')]/../..//*[@data-original-title='select']";
	public final By ELEMENT_HOME_PATH = By.id("homePath");
	public final By ELEMENT_ADD_PERMISSION = By.xpath("//*[@title='Add Permission']");
	public final By ELEMENT_ADD_PERMISSION_AUX = By.xpath("//*[@data-original-title='Add Permission']");
	public final By ELEMENT_PERMISSION_TEXTBOX = By.id("permissions");
	public final By ELEMENT_ALLOW_CREATE_FOLDER = By.name("allowCreateFolders");
	public final String ELEMENT_ALLOW_CREATE_FOLDER_OPTIONS = "//*[@name='allowCreateFolders']/option[text()='${option}']";
	public final By ELEMENT_DRIVE_NAME = By.id("name");
	public final By ELEMENT_MANAGE_DRIVE_LINK = By.linkText("Manage Drives");
	public final String ELEMENT_VERIFY_DRIVE = "//div[@data-original-title='${driveName}']";
	public final String ELEMENT_VERIFY_WORKSPACE_NAME = ELEMENT_VERIFY_DRIVE + "/../../td[2]/div"; 


	/**
	 * Add a View to Action Bar for drives
	 * @param view
	 * @param drive
	 */
	public void addViewForDrives(String view, String drive) {
		info("-- Add a View to Action Bar --");
		click(By.xpath(ELEMENT_DRIVE_EDIT_AUX.replace("${driveName}", drive)));
		waitForAndGetElement(ELEMENT_DRIVE_EDIT_POPUP);
		click(ELEMENT_APPLY_VIEW_TAB);
		selectCheckBoxList(view);
		button.save();
	}
	
}
package org.exoplatform.selenium.platform.ecms.admin;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * 
 * @author vuna2
 *
 */
public class ManageAction extends EcmsBase{

	public ManageAction(WebDriver dr) {
		super(dr);
		// TODO Auto-generated constructor stub
	}
	
	Button button = new Button(driver);
	ManageAlert magAlert = new ManageAlert(driver);
	ECMainFunction ecMain = new ECMainFunction(driver);
	ManageDrive magDrv = new ManageDrive(driver);
	
	//Elements
	public final By ELEMENT_SELECT_SCRIPT = By.name("script");
	public final By ELEMENT_INPUT_VARIABLE = By.xpath("//*[contains(@name, 'variables') and @type='text']");
	public final String ELEMENT_EDIT_ACTION_TYPE_ICON = "//*[contains(text(), '${actionTypeName}')]/../..//*[contains(@class, 'uiIconEdit')]";
	public final String ELEMENT_DELETE_ACTION_TYPE_ICON = "//*[contains(text(), '${actionTypeName}')]/../..//*[@data-original-title='Delete']";
	
	//Open form [Add Action Type]
	public void openActionTypeForm(){
		info("-- Opening [Add Action Type] Form --");
		click(ecMain.ELEMENT_ADD_ACTION_TYPE_BUTTON);
		waitForAndGetElement(ELEMENT_INPUT_CATEGORY_NAME);
	}
	
	//Add an Action Type
	public void addActionType(String actionTypeName, String script, Object...params){
		String variable = (String) (params.length > 0 ? params[0]: "");
		
		if (waitForAndGetElement(ELEMENT_INPUT_CATEGORY_NAME, 3000, 0) != null){
			info("-- Add Action Type Form has already opened --");
		}else {
			openActionTypeForm();
		}
		info("-- Adding a new action type ... --");
		type(ELEMENT_INPUT_CATEGORY_NAME, actionTypeName, true);
		select(ELEMENT_SELECT_SCRIPT, script);
		if (!variable.isEmpty()){
			type(ELEMENT_INPUT_VARIABLE, variable, true);
		}
		button.save();
		waitForTextPresent(actionTypeName);	
	}

	//Edit Action Type
	public void editActionType(String actionTypeName, String newActionTypeName, String script, Object...params){
		String variable = (String) (params.length > 0 ? params[0]: "");
		
		info("-- Editing Action Type: " + actionTypeName);
		Utils.pause(500);
		click(ELEMENT_EDIT_ACTION_TYPE_ICON.replace("${actionTypeName}", actionTypeName));
		type(ELEMENT_INPUT_CATEGORY_NAME, newActionTypeName, true);
		select(ELEMENT_SELECT_SCRIPT, script);
		if (!variable.isEmpty()){
			type(ELEMENT_INPUT_VARIABLE, variable, true);
		}
		button.save();
		waitForTextPresent(newActionTypeName);
	}
	
	//Delete Action Type
	public void deleteActionType(String actionTypeName){
		info("-- Deleting Action Type...-- " + actionTypeName);
		Utils.pause(500);
		waitForAndGetElement(ELEMENT_DELETE_ACTION_TYPE_ICON.replace("${actionTypeName}", actionTypeName)).click();
		magAlert.acceptAlert();
		waitForTextNotPresent(actionTypeName);
	}
}

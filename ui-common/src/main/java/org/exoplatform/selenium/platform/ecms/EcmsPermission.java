package org.exoplatform.selenium.platform.ecms;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Dialog;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

/**
 * 
 * @author vuna2
 *
 */
public class EcmsPermission extends EcmsBase{

	public EcmsPermission(WebDriver dr) {
		super(dr);
		// TODO Auto-generated constructor stub
	}

	Dialog dialog = new Dialog(driver);
	ManageAlert magAlert = new ManageAlert(driver);
	
	//Locator: Content Admin > Categories > Permissions management
	//Sites explorer > Permissions management
	public final String ELEMENT_EDIT_USER_PERMISSION = "//*[@title='${userOrGroupName}']/../..//*[contains(@class, 'uiIconEdit')]";
	public final String ELEMENT_EDIT_USER_PERMISSION_AUX = "//*[@id='UITabContent' and @style='display: block;;']//*[@title='${userOrGroupName}']/../..//*[contains(@class, 'uiIconEdit')]";

	public final String ELEMENT_DELETE_USER_PERMISSION = "//*[@title='${userOrGroupName}']/../..//*[contains(@class, 'uiIconDelete')]";
	public final String ELEMENT_DELETE_USER_PERMISSION_AUX = "//*[@id='UITabContent' and @style='display: block;;']//*[@title='${userOrGroupName}']/../..//*[contains(@class, 'uiIconDelete')]";
	
	public final String ELEMENT_DELETE_USER_PERMISSION_1 = "//*[@data-original-title='${userOrGroupName}']/../..//*[contains(@class, 'uiIconDelete')]";
	public final String ELEMENT_DELETE_USER_PERMISSION_AUX_1 = "//*[@id='UITabContent' and @style='display: block;;']//*[@data-original-title='${userOrGroupName}']/../..//*[contains(@class, 'uiIconDelete')]";
	public final By ELEMENT_TITLE_PERMISSION_POPUP=By.xpath(".//*[@class='PopupTitle popupTitle'][contains(text(),'Permission Management')]");
	public final By ELEMENT_SYSTEM_PERMISSION_READ_DISABLED = By.xpath("//*[@id='UITabContent' and @style='display: block;;']//input[@id = '__systemread' and @disabled]");
	
	public final String MESSAGE_NO_RIGHT_TO_PASTE_NODE = "You do not have permission to move to this node. Please contact the administrator.";
	public final String MESSAGE_NO_RIGHT_TO_ADD_NODE = "You do not have permission to add a new";
	
	public final String ELEMENT_READ_CHECK = "//input[@name='{$user}read']";
	public final String ELEMENT_MODIFY_CHECK = "//input[@name='{$user}addNode']";
	public final String ELEMENT_REMOVE_CHECK = "//input[@name='{$user}remove']";
	
	public final By ELEMENT_WARNING_DIALOG_PERMISSION = By.xpath("//*[contains(text(),'You must check out first')]");

	//Function to set permission for node
	public void setPermissionForNode(boolean read, boolean add, boolean remove){
		boolean isCheckedRead = driver.findElement(ELEMENT_READ_CHECKBOX).isSelected();
		boolean isCheckedAdd = driver.findElement(ELEMENT_ADD_NODE_CHECKBOX).isSelected();
		//boolean isCheckedProperty;
		boolean isCheckedRemove = driver.findElement(ELEMENT_REMOVE_CHECKBOX).isSelected();
		
		info("Set read permission for user: " + read);
		waitForAndGetElement(ELEMENT_READ_CHECKBOX, DEFAULT_TIMEOUT, 1, 2);
		if ((read && !isCheckedRead) || (!read && isCheckedRead)){
			//click(ELEMENT_READ_CHECKBOX, 2);
			((JavascriptExecutor) driver).executeScript("document.getElementById('read').click();");
		}
		
		info("Set add node permission for user: " + add);
		if ((add && !isCheckedAdd) ||(!add && isCheckedAdd)){
			//click(ELEMENT_ADD_NODE_CHECKBOX, 2);
			((JavascriptExecutor) driver).executeScript("document.getElementById('add_node').click();");
		}
			
		info("Set set remove permission for user: " + remove);
		if ((remove && !isCheckedRemove) || (!remove && isCheckedRemove)){
			//click(ELEMENT_REMOVE_CHECKBOX, 2);
			((JavascriptExecutor) driver).executeScript("document.getElementById('remove').click();");
		}
	}

	//Function to delete permission
	public void deletePermission(String user, boolean verify){
		//By ELEMENT_DELETE_USER_PERMISSION = By.xpath("//div[@title='"+ user +"']/../../td/div/img[@class='DeleteIcon']");
		//By ELEMENT_DELETE_USER_PERMISSION_AUX = By.xpath("//div[@id='UITabContent' and @style='display: block;;']//div[@title='"+ user +"']/../../td/div/img[@class='DeleteIcon']");
		if (waitForAndGetElement(ELEMENT_DELETE_USER_PERMISSION_AUX.replace("${userOrGroupName}", user), 5000, 0) != null){
			waitForAndGetElement(ELEMENT_DELETE_USER_PERMISSION_AUX.replace("${userOrGroupName}", user)).click();
			magAlert.acceptAlert();
			if(verify){
				waitForElementNotPresent(ELEMENT_DELETE_USER_PERMISSION_AUX.replace("${userOrGroupName}", user));
			}
			info("Delete a permission is successful");
		}else if (waitForAndGetElement(ELEMENT_DELETE_USER_PERMISSION.replace("${userOrGroupName}", user), 5000, 0) != null){
			waitForAndGetElement(ELEMENT_DELETE_USER_PERMISSION.replace("${userOrGroupName}", user)).click();
			magAlert.acceptAlert();
			if(verify){
				waitForElementNotPresent(ELEMENT_DELETE_USER_PERMISSION.replace("${userOrGroupName}", user));
			}
			info("Delete a permission is successful");
		}else if (waitForAndGetElement(ELEMENT_DELETE_USER_PERMISSION_AUX_1.replace("${userOrGroupName}", user), 5000, 0) != null){
			waitForAndGetElement(ELEMENT_DELETE_USER_PERMISSION_AUX_1.replace("${userOrGroupName}", user)).click();
			magAlert.acceptAlert();
			if(verify){
				waitForElementNotPresent(ELEMENT_DELETE_USER_PERMISSION_AUX_1.replace("${userOrGroupName}", user));
			}
			info("Delete a permission is successful");
		}else if (waitForAndGetElement(ELEMENT_DELETE_USER_PERMISSION_1.replace("${userOrGroupName}", user), 5000, 0) != null){
			waitForAndGetElement(ELEMENT_DELETE_USER_PERMISSION_1.replace("${userOrGroupName}", user)).click();
			magAlert.acceptAlert();
			if(verify){
				waitForElementNotPresent(ELEMENT_DELETE_USER_PERMISSION_1.replace("${userOrGroupName}", user));
			}
			info("Delete a permission is successful");
		}else{
			info("Do not see an element to delete");
		}
		Utils.pause(1000);
	}

	//Function to remove default user and group permission of node except for owner
	public void removeDefaultPermissionOfNode(){ 
		deletePermission("*:/platform/web-contributors", true);
		deletePermission("*:/platform/administrators", true);
		deletePermission("any", true);
	}
}

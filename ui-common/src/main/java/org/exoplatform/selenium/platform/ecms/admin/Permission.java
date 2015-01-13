package org.exoplatform.selenium.platform.ecms.admin;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.ecms.EcmsPermission;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * 
 * @author vuna2
 *
 */
public class Permission extends EcmsPermission{

	public Permission(WebDriver dr) {
		super(dr);
		// TODO Auto-generated constructor stub
	}

	ManageAlert magAlert = new ManageAlert(driver);
	Button button = new Button(driver);

	//Permission management screen
	public final By ELEMENT_PERMISSION_MANAGEMENT_POPUP = By.cssSelector("form[id='UIUserSelector']");
	public final String MESSAGE_INFO_DELETE_PERMISSION_SYSTEM = "You cannot remove the owner or the system user.";
	public final String ELEMENT_PERMISSION_MANAGEMENT_ICON = "//*[@title='${categoryName}']/../..//*[@class='uiIconEcmsViewPermissions']";
	public final By ELEMENT_READ_CHEKBOX = By.xpath("//div[@id='UITabContent' and @style='display: block;;']//*[@id='read']");
	public final By ELEMENT_ADDNODE_CHECKBOX = By.xpath("//div[@id='UITabContent' and @style='display: block;;']//*[@id='add_node']");
	public final By ELEMENT_SET_PRO_CHEKBOX = By.xpath("//div[@id='UITabContent' and @style='display: block;;']//*[@id='set_property']");
	public final By ELEMENT_REMOVE_CHEKBOX = By.xpath("//div[@id='UITabContent' and @style='display: block;;']//*[@id='remove']");
	public final By ELEMENT_SELECT_USER_IN_PERMISSION_MANAGEMENT = By.xpath("//div[@id='UITabContent' and @style='display: block;;']//*[@title='Select User']");
	public final By ELEMENT_SELECT_USER_IN_PERMISSION_MANAGEMENT_2 = By.xpath("//div[@id='UITabContent' and @style='display: block;;']//*[@data-original-title='Select User']");

	public final By ELEMENT_SELECT_MEMBERSHIP_IN_PERMISSION_MANAGEMENT = By.xpath("//div[@id='UITabContent' and @style='display: block;;']//*[@title='Select Membership']");
	public final By ELEMENT_SELECT_EVERYONE_IN_PERMISSION_MANAGEMENT = By.xpath("//div[@id='UITabContent' and @style='display: block;;']//*[@title='Select Everyone']");
	public final By ELEMENT_SELECT_EVERYONE_IN_PERMISSION_MANAGEMENT_2 = By.xpath("//div[@id='UITabContent' and @style='display: block;;']//*[@data-original-title='Select Everyone']");
	public final By ELEMENT_SELECT_EVERYONE_IN_PERMISSION_MANAGEMENT_3 = By.xpath("//div[@id='UITabContent' and @style='display: block;;']//*[@data-original-title='Everyone']");
	public final By ELEMENT_SAVE_BUTTON_IN_PERMISSION_MANAGEMENT = By.xpath("//div[@id='UITabContent' and @style='display: block;;']//*[contains(text(),'Save')]");
	
	public final String ELEMENT_PERMISSION_MANAGEMENT_READ = "//div[@id='UITabContent' and @style='display: block;;']//*[@data-original-title='${userOrGroupName}']/../..//input[contains(@id, 'read')]";
	public final String ELEMENT_PERMISSION_MANAGEMENT_MODIFY = "//div[@id='UITabContent' and @style='display: block;;']//*[@data-original-title='${userOrGroupName}']/../..//input[contains(@id, 'add')]";
	public final String ELEMENT_PERMISSION_MANAGEMENT_REMOVE = "//div[@id='UITabContent' and @style='display: block;;']//*[@data-original-title='${userOrGroupName}']/../..//input[contains(@id, 'remove')]"; 

	//"//*[text()='Add permission to that node']/ancestor::div[contains(@id, 'Permission')]//*[@title='*:/platform/users']/../..//input[contains(@id, 'read')]";
	//public final String ELEMENT_EDIT_USER_PERMISSION = "//*[@title='${userOrGroupName}']/../..//*[@class='uiIconEdit']";
	//public final String ELEMENT_EDIT_USER_PERMISSION_AUX = "//div[@id='UITabContent' and @style='display: block;;']//*[@title='${userOrGroupName}']/../..//*[@class='uiIconEdit']";
	public final String ELEMENT_DELETE_SELECTED_GROUP = "//*[contains(text(), '${selectedGroup}')]/../..//*[contains(@class, 'uiIconDelete')]";
	public final String ELEMENT_TEXT_ADD_PERMISSION = "//*[@class='UIPermissionManager']//*[contains(text(), '${text}')]";

	/*============== Explorer/Tags Permission Manager ================*/
	/**
	 * Remove permission 
	 * @param groupPath Group string separated by slash
	 * @param membership Membership 
	 */
	public void removeTagPermission(String groupPath,String membership) {
		String str = membership + ":/" + groupPath;
		//By locator = By.xpath("//*[contains(text(),'" + str + "')]/ancestor::tr//*[@class='uiIconDelete']");
		By permission = By.xpath("//*[contains(text(),'" + str + "')]");
		waitForAndGetElement(ELEMENT_DELETE_SELECTED_GROUP.replace("${selectedGroup}", str)).click();
		//magAlert.waitForConfirmation("Are you sure you want to delete this permission?");
		magAlert.acceptAlert();
		waitForElementNotPresent(permission);
		Utils.pause(1000);
	}

	/*============== Advanced/Categories ============*/
	//Function to set permission for user on manage category screen
	public void setPermissionForUserOnManageCategory(boolean selectUser, String user, boolean selectMembership, String groupID, String membership,
			boolean read, boolean add, boolean remove){
		info("Set permission for category with user " + user);
		Utils.pause(1000);
		if (!selectUser && !selectMembership){
			if (isElementPresent(ELEMENT_SELECT_EVERYONE_IN_PERMISSION_MANAGEMENT)){
				click(ELEMENT_SELECT_EVERYONE_IN_PERMISSION_MANAGEMENT);
			}else if (waitForAndGetElement(ELEMENT_SELECT_EVERYONE_IN_PERMISSION_MANAGEMENT_2, 5000, 0) != null){
				click(ELEMENT_SELECT_EVERYONE_IN_PERMISSION_MANAGEMENT_2);
			}else {
				click(ELEMENT_SELECT_EVERYONE_IN_PERMISSION_MANAGEMENT_3);
			}
		}
		else if (selectUser){
			
			if (isElementPresent(ELEMENT_SELECT_USER_IN_PERMISSION_MANAGEMENT)){
				click(ELEMENT_SELECT_USER_IN_PERMISSION_MANAGEMENT);
			}else {
				Utils.pause(3000);
//				click(ELEMENT_SELECT_USER_IN_PERMISSION_MANAGEMENT_2);
				clickByJavascriptWithClassName("uiIconSelectUser uiIconLightGray",1);
			}
			WebElement e = waitForAndGetElement(ELEMENT_PERMISSION_MANAGEMENT_POPUP,DEFAULT_TIMEOUT,1,2);
			String[]  temp = e.getAttribute("action").split("&");
			String[] temp1 = temp[0].split("=");
			String id = temp1[temp1.length-1];
			info("id is " + id);
			if (waitForAndGetElement(ELEMENT_TEXT_ADD_PERMISSION.replace("${text}", "Add permissions to this node"), 5000, 0) != null){
				if (waitForAndGetElement(By.xpath("//*[@title='" + user + "']/../..//*[@class='SelectPageIcon']"), 5000, 0) !=null ){
					click(By.xpath("//*[@title='" + user + "']/../..//*[@class='SelectPageIcon']"));
				}else {
					click(By.xpath("//*[@data-original-title='" + user + "']/../..//*[contains(@class, 'uiIconPlus')]"));
				} 
			}
			else if (waitForAndGetElement(ELEMENT_TEXT_ADD_PERMISSION.replace("${text}", "Add permission to that node"), 5000, 0) != null){
				info("javascript click");
				/*if (waitForAndGetElement(By.xpath("//div[@id='UITabContent' and @style='display: block;;']//*[@title='" + user + "']/../..//*[@class='SelectPageIcon']"), 5000, 0) != null){
					click(By.xpath("//div[@id='UITabContent' and @style='display: block;;']//*[@title='" + user + "']/../..//*[@class='SelectPageIcon']"));
				}else {*/
//					((JavascriptExecutor)driver).executeScript("javascript:eXo.webui.UIForm.submitEvent('"+id+"#UIUserSelector','AddUser','&objectId="+user+"'); return false;"); 
					waitForAndGetElement(By.xpath("//div[@id='UITabContent' and @style='display: block;;']//*[text()='" + user + "']/../..//*[contains(@class, 'uiIconPlus')]")).click();
//				}
			}	
		}
		else if (selectMembership){
			if (isElementPresent(ELEMENT_SELECT_MEMBERSHIP_IN_PERMISSION_MANAGEMENT)){
				click(ELEMENT_SELECT_MEMBERSHIP_IN_PERMISSION_MANAGEMENT);
			}else {
				click(By.xpath("//div[@id='UITabContent' and @style='display: block;;']//*[@data-original-title = 'Select Membership']"));
			}
			userGroup.selectGroup(groupID);
			click(By.linkText(membership));
		}
		Utils.pause(500);
		info("Set read permission for user");
		if (read){
			click(ELEMENT_READ_CHEKBOX, 2);
		}
		if (add){
			click(ELEMENT_ADDNODE_CHECKBOX, 2);
		}
		if (remove){
			click(ELEMENT_REMOVE_CHEKBOX, 2);
		}
		if (read || add || remove){
			click(ELEMENT_SAVE_BUTTON_IN_PERMISSION_MANAGEMENT);
			//((JavascriptExecutor) driver).executeScript("document.getElementById('remove').click();");
		}else{
			info("Input permission is wrong");
		}
		Utils.pause(1000);
	}

	//Edit a permission for user or group
	public void editPermissionUserOrGroup(String userOrGroupName, boolean read, boolean add, boolean remove, Object...params){
		Boolean closeWindow = (Boolean) (params.length > 0 ? params[0]: false);

		info("-- Edit a permission for user/group: "+ userOrGroupName +" --");
		waitForTextPresent("Permission Management");
		/*if (isTextPresent("Add permissions to this node")){
			click(ELEMENT_EDIT_USER_PERMISSION.replace("${userOrGroupName}", userOrGroupName));
		}else if (isTextPresent("Add permission to that node")){
			click(ELEMENT_EDIT_USER_PERMISSION_AUX.replace("${userOrGroupName}", userOrGroupName));
		}*/
		if (read){
			check(ELEMENT_PERMISSION_MANAGEMENT_READ.replace("${userOrGroupName}", userOrGroupName), 2);
		}
		if (add){
			check(ELEMENT_PERMISSION_MANAGEMENT_MODIFY.replace("${userOrGroupName}", userOrGroupName), 2);
		}
		if (remove){
			check(ELEMENT_PERMISSION_MANAGEMENT_REMOVE.replace("${userOrGroupName}", userOrGroupName), 2);
		}
		if (closeWindow){
			//click(ELEMENT_SAVE_BUTTON_IN_PERMISSION_MANAGEMENT);
			button.close();
		}
		Utils.pause(1000);
	}
}

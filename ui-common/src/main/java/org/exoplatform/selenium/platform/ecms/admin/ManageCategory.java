package org.exoplatform.selenium.platform.ecms.admin;

import static org.exoplatform.selenium.TestLogger.info;
import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.Dialog;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.ecms.EcmsPermission;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

/**
 * 
 * @author vuna2
 *
 */
public class ManageCategory extends EcmsPermission{

	public ManageCategory(WebDriver dr) {
		super(dr);
		// TODO Auto-generated constructor stub
	}

	ECMainFunction ecMain = new ECMainFunction(driver);
	Permission permission = new Permission(driver);
	NavigationToolbar navToolbar = new NavigationToolbar(driver);
	Dialog dialog = new Dialog(driver);
	Button button = new Button(driver);
	ManageAlert magAlert = new ManageAlert(driver);

	// Add Category tree Form - Screen1
	public final By ELEMENT_ADD_CATEGORY_TREE_BUTTON = By.xpath("//*[text()='Add Category Tree']");
	//By.linkText("Add Category Tree");
	public final By ELEMENT_CATEGORIES_TREE_NAME = By.id("TaxoTreeName");
	public final By ELEMENT_CATEGORIES_WORKSPACE = By.name("TaxoTreeWorkspace");
	public final By ELEMENT_ADD_PATH_LINK = By.xpath("//*[@title='Add Path']");
	public final By ELEMENT_POPUP_HOME_PATH = By.id("PopupTaxonomyJCRBrowser");
	public final String ELEMENT_EDIT_CATEGORY_TREE = "//*[text()='${categoryTreeName}']/../..//*[contains(@class,'uiIconEditInfo')]";
	public final By ELEMENT_ADD_PATH_LINK_ICON = By.xpath("//*[@data-original-title='Add Path']");
	
	// Add Category tree form -screen2
	//public final By ELEMENT_PREVIOUS_BUTTON = By.linkText("Previous");
	public final By ELEMENT_SELECT_EVERYONE_ICON = By.xpath("//*[@data-original-title='Select Everyone']");
	public final By ELEMENT_SELECT_EVERYONE_ICON_1 = By.xpath("//*[@title='Select Everyone']");
	public final By ELEMENT_SELECT_EVERYONE_ICON_2 = By.xpath("//*[@data-original-title='Everyone']");
	public final By ELEMENT_SELECT_MEMBERSHIP_ICON = By.xpath("//*[@data-original-title='Select Membership']");
    public final By ELEMENT_NEXT_BUTTON_STEP_2 = By.xpath("//*[text()='Add a permission to that node']/../..//*[text()='Next']");
    
	// Add Category tree form -screen3
	public final String MSG_ADD_CATEGORY_STEP3="Edit the taxonomy tree by adding, copying, cutting and selecting permissions.";
	public final By ELEMENT_ACTION_TYPE = By.id("actionType");
	public final By ELEMENT_ACTION_NAME = By.id("actionName");
	public final By ELEMENT_LIFE_CYCLE = By.name("lifecycle");
	public final By ELEMENT_SELECT_PATH_ICON = By.xpath("//input[@id='targetPath']/../../td/a/img[@class='ActionIcon SelectPath24x24Icon']");
	public final By ELEMENT_UP_LEVEL = By.xpath("//*[@id='UITaxonomyTreeCreateChild']//..//a[@title='Up Level']");
	public final By ELEMENT_ALERT_VISIBLE = By.xpath("//div[@class='UIPopupWindow UIDragObject ExoMessageDecorator' and contains(@style,'visible')]");	

	public final String ELEMENT_COPY_CATEGORY_ICON = "//*[@title='${categoryName}']/../..//*[contains(@class,'uiIconEcmsCopy')]";
	public final String ELEMENT_PASTE_TO_CATEGORY_ICON = "//*[@title='${categoryName}']/../..//*[contains(@class,'uiIconEcmsPatse')]";
	public final String ELEMENT_CUT_CATEGORY_ICON = "//*[@title='${categoryName}']/../..//*[contains(@class,'uiIconEcmsCut')]";
	public final String MESSAGE_INFO_CUT_TO_CATEGORY =  "You cannot paste here. The category node '${pathCategory}' might be cut.";
	public final String MESSAGE_INFO_PASTE_TO_CATEGORY = "Cannot read from the source file, or the destination category is a sub-category.";
	public final String ELEMENT_LIFE_CYCLE_LIST_OPTIONS = ".//*[@name='lifecycle']/option[contains(text(), '${option}')]";
	
	public final By ELEMENT_TARGET_PATH_SEARCH_ICON = By.xpath("//input[@id='targetPath']/..//*[contains(@class, 'uiIconSearch')]");
	public final By ELEMENT_SAVE_BUTTON_IN_STEP_3 = By.xpath("//*[text()='Add an action to the category tree']/..//*[text()='Save']");
	
	// Screen 4
    public final String ELEMENT_ADD_CATEGORY_ICON = "//*[@title='${categoryName}']/../..//*[@title='Add']";
	public final String ELEMENT_ADD_CATEGORY_ICON_1 = "//*[@data-original-title='${categoryName}']/../..//*[@data-original-title='Add']";
    
	/*==========================================================*/

	/*-- Update common functions for Manage ECM Main Functions
	 *-- Manage categories 
	 *-- The category management includes adding, editing and deleting a category tree
	 *-- @author: VuNA
	 *-- @date: 15/10/2012
	 *--*/	

	//Add new category tree: step 1 - Specify the category name, workspace and home path.
	public void addNewCategoryTree_Step1(String categoryTreeName, String workspace, String homePath){
		info("-- Adding a workspace/homepath to: " + categoryTreeName + " --");
		type(ELEMENT_CATEGORIES_TREE_NAME, categoryTreeName, false);
		select(ELEMENT_CATEGORIES_WORKSPACE, workspace);
		if (isElementPresent(ELEMENT_ADD_PATH_LINK_ICON)){
			click(ELEMENT_ADD_PATH_LINK_ICON);
		}else {
			click(ELEMENT_ADD_PATH_LINK);
		}
		waitForAndGetElement(ELEMENT_POPUP_HOME_PATH);
		selectHomePathForCategoryTree(homePath);
		waitForTextNotPresent("Select Home Path");
	}

	//Add new category tree: step 2 - Set permission for the category tree.
	/**
	 * 
	 * @param selectUserOrGroup
	 * @param selectMembership
	 * @param groupID
	 * @param membership
	 * @param user_Per
	 * @param read
	 * @param add
	 * @param set
	 * @param remove
	 */
	public void addNewCategoryTree_Step2(boolean selectUserOrGroup, boolean selectMembership, String groupID, String membership, 
			String user_Per, boolean read, boolean add, boolean remove){
		info("-- Add permissions to the category tree --");
		if (!selectUserOrGroup && !selectMembership){
			if (waitForAndGetElement(ELEMENT_SELECT_EVERYONE_ICON, 3000, 0) != null){
				click(ELEMENT_SELECT_EVERYONE_ICON);
			}else if (waitForAndGetElement(ELEMENT_SELECT_EVERYONE_ICON_1, 3000, 0) != null){
				click(ELEMENT_SELECT_EVERYONE_ICON_1);
			}else {
				click(ELEMENT_SELECT_EVERYONE_ICON_2);
			}
		}
		else{
			if(selectUserOrGroup){
				selectUser(user_Per);
			}
			if(selectMembership){
				if (waitForAndGetElement(ELEMENT_SELECT_MEMBERSHIP_ICON, 3000, 0) != null){
					click(ELEMENT_SELECT_MEMBERSHIP_ICON);
				}else{
					click(By.xpath("//*[@title='Select Membership']"));
				}
				userGroup.selectGroup(groupID);
				if (isElementPresent(By.linkText(membership))){
					click(By.linkText(membership));
				}else if (isElementPresent(By.linkText("Select this Group"))){
					click(By.linkText("Select this Group"));
				}
			}
		}
		Utils.pause(500);
		setPermissionForNode(read,add,remove);
		click(button.ELEMENT_SAVE_BUTTON);
		if (!selectUserOrGroup && !selectMembership){
			//waitForAndGetElement(By.xpath("//*[@data-original-title = 'any']"));
			waitForAndGetElement("//*[text()='User or Group']/../../..//*[contains(text(), 'any')]");
		}
		else if (selectUserOrGroup){
			//waitForAndGetElement(By.xpath("//*[@data-original-title = '"+ user_Per +"']"));
			waitForAndGetElement("//*[text()='User or Group']/../../..//*[contains(text(), '"+ user_Per +"')]");
		}
		else if (selectMembership){
		    if (groupID.contains("Administration")){
		    	//waitForAndGetElement(By.xpath("//*[@data-original-title = '"+ membership +":/platform/administrators']"));
		    	waitForAndGetElement("//*[contains(text(), '"+ membership +":/platform/administrators')]");
		    }else if (groupID.contains("Guests")){
		    	//waitForAndGetElement(By.xpath("//*[@data-original-title = '"+ membership +":/platform/guests']"));
		    	waitForAndGetElement("//*[contains(text(), '"+ membership +":/platform/guests')]");
		    }else if (groupID.contains("Users")){
		    	//waitForAndGetElement(By.xpath("//*[@data-original-title = '"+ membership +":/platform/users']"));
		    	waitForAndGetElement("//*[contains(text(), '"+ membership +":/platform/users')]");
		    }else if (groupID.contains("Content Management")){
		    	//waitForAndGetElement(By.xpath("//*[@data-original-title = '"+ membership +":/platform/web-contributors']"));
		    	waitForAndGetElement("//*[contains(text(), '"+ membership +":/platform/web-contributors')]");
		    }
		    /*else{	
		    	waitForAndGetElement(By.xpath("//*[@data-original-title = '"+ membership +":/"+ groupID +"']"));
		    }*/
		}
		info("Add user permission for category is successfull");
	}

	//Add new category tree: step 3 - Add an action to the category tree to complete the creation or update
	public void addNewCategoryTree_Step3(String actionName, String optionsLifeCycle, String nodeTargetPath){
		Actions actions = new Actions(driver);
		info("-- Add an action to the category tree --");
		  
		type(ELEMENT_ACTION_NAME,actionName,false);		
		String[] optionsLC = optionsLifeCycle.split("/");
		select(ELEMENT_LIFE_CYCLE, optionsLC[0]);
		actions.keyDown(Keys.CONTROL);
		for (int i = 1; i < optionsLC.length; i++) {
			click(ELEMENT_LIFE_CYCLE_LIST_OPTIONS.replace("${option}", optionsLC[i]));
		}
		Utils.pause(500);	
		if (isElementPresent(ELEMENT_SELECT_PATH_ICON)){
			click(ELEMENT_SELECT_PATH_ICON);
		}else if (isElementPresent(ELEMENT_TARGET_PATH_SEARCH_ICON)){
			click(ELEMENT_TARGET_PATH_SEARCH_ICON);
		}
		
		selectHomePathForCategoryTree(nodeTargetPath);

		//click(button.ELEMENT_SAVE_BUTTON);
		if (isElementPresent(ELEMENT_SAVE_BUTTON_IN_STEP_3)){
			click(ELEMENT_SAVE_BUTTON_IN_STEP_3);
		}else {
			click("//*[text()='Add an action to the category tree']/../..//*[text()='Save']");
		}
		
		Utils.pause(500);
	}

	//Add new category tree: step 4 - Edit the taxonomy tree by adding, copying, cutting and selecting permissions.
	public void addNewCategoryTree_Step4(String name, String childname1, String childname2, String user_Per, boolean read, boolean modify, boolean remove ){
		//--Add child category
		addChildCategory(name, childname1);
		//click(ELEMENT_UP_LEVEL);
		clickUpLevel();
		addChildCategory(name, childname2);

		//--Copy and paste
		copyAndPasteCategory(childname1, childname2);
		deleteCategory(childname1);
		//click(ELEMENT_UP_LEVEL);
		clickUpLevel();

		//--Cut and paste
		cutAndPasteCategory(childname1, childname2);

		//--Set permission
		click(By.xpath("//*[@title='" + childname2 + "']/../..//*[@title='Permission Management']"));
		permission.setPermissionForUserOnManageCategory(true, user_Per, false, "", "", read, modify, remove);
		click(button.ELEMENT_CLOSE_BUTTON);
		//deleteCategory(childname2);
		
		click(button.ELEMENT_CLOSE_WINDOW);
		Utils.pause(500);
	}

	//Function to add new category tree
	//boolean[] optionPermission: read / add / set property / remove
	public void addNewCategoryTree(String[] nameWorkspaceHomePath, boolean selectUserOrGroup, boolean selectMembership, String[] groupIDAndMembership, 
			String user_Per, boolean[] optionPermission, String[] actions, Object...params){
		
		Boolean notCloseAddCategoryForm = (Boolean) (params.length > 0 ? params[0]: false);	
		By ELEMENT_CATEGORY_TREE = By.xpath("//div[@title='" + nameWorkspaceHomePath[0] + "']");

		info("Add new category tree with name: " + nameWorkspaceHomePath[0]);
		click(ELEMENT_ADVANCED_CONFIGURATION_TAB);
		click(ecMain.ELEMENT_MANAGE_CATEGORIES_LINK);
		click(ELEMENT_ADD_CATEGORY_TREE_BUTTON);

		//Step 1: Specify the category name, workspace and home path. 
		info("Add new category tree - step 1: Specify the category name, workspace and home path.");
		addNewCategoryTree_Step1(nameWorkspaceHomePath[0], nameWorkspaceHomePath[1], nameWorkspaceHomePath[2]);
		click(button.ELEMENT_NEXT_BUTTON);

		//Step 2: Set permission for the category tree.
		info("Add new category tree - step 2: Set permission for the category tree.");
		addNewCategoryTree_Step2(selectUserOrGroup, selectMembership, groupIDAndMembership[0], groupIDAndMembership[1], 
				user_Per, optionPermission[0], optionPermission[1], optionPermission[2]);	
		//click(button.ELEMENT_NEXT_BUTTON);
		if (isElementPresent(ELEMENT_NEXT_BUTTON_STEP_2)){
			click(ELEMENT_NEXT_BUTTON_STEP_2);
		}else if (isElementPresent(By.xpath("//*[text()='Add permissions to this node']/..//*[text()='Next']"))){
			click("//*[text()='Add permissions to this node']/..//*[text()='Next']");
		}
		
		//step 3: Add an action to the category tree to complete the creation or update
		info("Add new category tree - step 3: Add an action to the category tree to complete the creation or update");
		//actions[0]: actionName, actions[1]: optionsLifeCycle, actions[2]: nodeTargetPath
		addNewCategoryTree_Step3(actions[0], actions[1], actions[2]);

		//step 4: Edit the taxonomy tree by adding, copying, cutting and selecting permissions.
		//info("Add new category tree - step 4: Edit the taxonomy tree by adding, copying, cutting and selecting permissions");
		//addNewCategoryTree_Step4(name, childname1, childname2);
		if (notCloseAddCategoryForm){
			info("-- Waiting Actions: adding, copying, cutting and selecting permissions... --");
		}else {
			waitForTextPresent(MSG_ADD_CATEGORY_STEP3);
			click(button.ELEMENT_CLOSE_BUTTON);
			//check add new category tree successfully
			waitForAndGetElement(ELEMENT_CATEGORY_TREE);
			assert isElementPresent(ELEMENT_CATEGORY_TREE):"Add new category tree is not successful";
			info("Add new category is successful");
		}
	}

	// Function to expand node on category tree
	public void expandNode(String node, boolean ex){
		//By ELEMENT_EXPAND = By.xpath("//div[@title='" + node + "']/../..//*[@class='ExpandIcon exo_taxonomy16x16Icon default16x16Icon']");
		By ELEMENT_COLLAPSE = By.xpath("//*[@title='" + node + "']/../..//*[contains(@class, 'collapseIcon uiIconEcmsexo')]");		  
		//goToNode(By.linkText(node));
		/*if (ex && (getElement(ELEMENT_EXPAND) != null)){
				click(ELEMENT_EXPAND);
			}*/
		if (ex && (waitForAndGetElement(ELEMENT_COLLAPSE, 3000, 0) != null)){
			click(ELEMENT_COLLAPSE);
			info("Expand node " + node + "...successful");
		}
		if (!ex && (waitForAndGetElement(ELEMENT_COLLAPSE, 3000, 0) != null)){
			click(ELEMENT_COLLAPSE);
		}
	}

	//Function to add child category for a category
	public void addChildCategory(String cat_name, String child_name, boolean...grandChild){
		//Click edit category
		boolean isGrandChild = grandChild.length>0 ? grandChild[0] : false;
		if (!isGrandChild){ 
			click(ELEMENT_EDIT_CATEGORY_TREE.replace("${categoryTreeName}", cat_name));
		}
		info("Add child category " + child_name + " for category " + cat_name);
			
		if (waitForAndGetElement(ELEMENT_ADD_CATEGORY_ICON_1.replace("${categoryName}", cat_name), 3000, 0) != null){
			click(By.xpath(ELEMENT_ADD_CATEGORY_ICON_1.replace("${categoryName}", cat_name)));
		}else if (waitForAndGetElement(ELEMENT_ADD_CATEGORY_ICON.replace("${categoryName}", cat_name), 3000, 0) != null) {
			click(By.xpath(ELEMENT_ADD_CATEGORY_ICON.replace("${categoryName}", cat_name)));
		}
		
		type(By.id("taxonomyName"), child_name, false);
		//click(button.ELEMENT_SAVE_BUTTON);
		click("//*[@id='taxonomyName']/../../../..//*[text()='Save']");
		expandNode(cat_name, true);
		waitForAndGetElement(By.linkText(child_name));
		info("Add child category successfully");
	}

	//Function to copy and paste a category
	public void copyAndPasteCategory(String child1, String child2){
		By ELEMENT_CHILD_NEW = By.xpath("//*[@title='"+child2+"']/../..//*[@title='"+child1+"']");
		click(By.xpath(ELEMENT_COPY_CATEGORY_ICON.replace("${categoryName}", child1)));
		click(By.xpath(ELEMENT_PASTE_TO_CATEGORY_ICON.replace("${categoryName}", child2)));
		expandNode(child2,true);
		waitForAndGetElement(ELEMENT_CHILD_NEW);
		info("Copy category " + child1 + "to category " + child2 + "is successful");
	}

	//Function to cut and paste a category
	public void cutAndPasteCategory(String child1, String child2){
		By ELEMENT_CHILD_NEW = By.xpath("//*[@title='" + child2 + "']/../..//*[@title='"+child1+"']");

		click(By.xpath(ELEMENT_CUT_CATEGORY_ICON.replace("${categoryName}", child1)));
		click(By.xpath(ELEMENT_PASTE_TO_CATEGORY_ICON.replace("${categoryName}", child2)));
		expandNode(child2,true);
		waitForAndGetElement(ELEMENT_CHILD_NEW);
		//click(ELEMENT_UP_LEVEL);
		clickUpLevel();
		waitForElementNotPresent(By.linkText("//*[@id='UITaxonomyTreeCreateChild']//..//*[@title='" + child1 + "']"));
		info("Cut category " + child1 + "to category " + child2 + "is successful");
	}

	//Delete a category
	public void deleteCategory(String categoryName){
		By ELEMENT_DELETE = By.xpath("//*[@title = '" + categoryName + "']/../..//*[@class = 'uiIconDelete']");
		By ELEMENT_DELETE_1 = By.xpath("//*[@data-original-title = '" + categoryName + "']/../..//*[@class='uiIconDelete']");
		Utils.pause(500);
		if (isElementPresent(ELEMENT_DELETE)){
			waitForAndGetElement(ELEMENT_DELETE).click(); 
			magAlert.acceptAlert();
			waitForElementNotPresent(ELEMENT_DELETE);
			info("Delete category " + categoryName + " is successful");
		}else if (isElementPresent(ELEMENT_DELETE_1)){
			waitForAndGetElement(ELEMENT_DELETE_1).click(); 
			magAlert.acceptAlert();
			waitForElementNotPresent(ELEMENT_DELETE_1);
			info("Delete category " + categoryName + " is successful");
		}else{
			info("Do not find element to delete");
		}
		Utils.pause(1000);
	}
	/// End of Manage categories
}
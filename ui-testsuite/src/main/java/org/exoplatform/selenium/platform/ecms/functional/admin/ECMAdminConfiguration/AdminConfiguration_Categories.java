package org.exoplatform.selenium.platform.ecms.functional.admin.ECMAdminConfiguration;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.Dialog;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.exoplatform.selenium.platform.ecms.admin.ECMainFunction;
import org.exoplatform.selenium.platform.ecms.admin.ManageCategory;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ActionBar;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContentTemplate;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContextMenu;
import org.exoplatform.selenium.platform.ecms.contentexplorer.SitesExplorer;
import org.openqa.selenium.By;
import org.testng.annotations.*;


/**
 * @author eXo
 *
 */
public class AdminConfiguration_Categories extends PlatformBase{


	//Platform
	NavigationToolbar nav;
	ManageAccount magAcc;
	Dialog dialog;
	Button button;
	//Ecms
	EcmsBase ecms; 
	ECMainFunction ecmMain;
	ActionBar actBar;
	ContextMenu cMenu;
	ManageCategory magCa;
	ContentTemplate cTemplate;
	SitesExplorer siteExp;

	@BeforeMethod
	public void beforeMethods() {
		initSeleniumTest();
		driver.get(baseUrl);
		info("Login ECMS with " + DATA_USER1);
		nav = new NavigationToolbar(driver, this.plfVersion);
		ecmMain = new ECMainFunction(driver, this.plfVersion);
		magAcc = new ManageAccount(driver, this.plfVersion);
		button = new Button(driver, this.plfVersion);
		dialog = new Dialog(driver);
		ecms = new EcmsBase(driver, this.plfVersion);
		actBar = new ActionBar(driver, this.plfVersion);
		magCa = new ManageCategory(driver);
		cMenu = new ContextMenu(driver, this.plfVersion);
		alert = new ManageAlert(driver, this.plfVersion);
		siteExp = new SitesExplorer(driver);
		magAcc.signIn(DATA_USER1, DATA_PASS);
	}

	@AfterMethod
	public void afterMethods() {
		info("Logout ECMS");
		driver.quit();
	}
	/**
	 *<li> Case ID:124610.</li>
	 *<li> Test Case Name: Check configuration of Events catogory tree on acme site.</li>
	 *<li> Pre-Condition: acme extension must have been installed</li>
	 *<li> Post-Condition: </li>
	 *<li> Done with OSs and browsers : </li>
	 *<li> Generated by rosso at 2015/04/21 09:26:53</li>
	 */
	@Test
	public  void test01_CheckConfigurationOfEventsCatogoryTreeOnAcmeSite() {
		info("Test 1: Check configuration of Events catogory tree on acme site");

		String category1 = "events";
		String path1 = " /sites /acme /events";
		String workspace1 = "collaboration";
		String permission1 ="*:/platform/administrators";
		String permission2 ="*:/platform/web-contributors";
		/*Step Number: 1
		 *Step Name: 
		 *Step Description: 
			- Login as administrator
			- Go to Content Administration
			- Click Advanced 
			-
			-> Categories
		 *Input Data: 

		 *Expected Outcome: 
			Categories list is shown*/
		ecmMain.goToCategoriesTabInContentAdmin();	

		/*Step number: 2
		 *Step Name: 
		 *Step Description: 
			- Check names of the category tree
			- Check the workspace of events category tree
			- Check the path of events category tree
		 *Input Data: 

		 *Expected Outcome: 
			- The list must includes the category tree events
			- The workspace of events category tree is collaboration
			- The path of events category tree is:/sites/acme/events*/
		waitForAndGetElement(magCa.ELEMENT_CATEGORY_TREE.replace("${name}", category1));
		waitForAndGetElement(magCa.ELEMENT_CATEGORY_TREE_WORKSPACE.replace("${name}", category1).replace("${workspace}", workspace1));
		waitForAndGetElement(magCa.ELEMENT_CATEGORY_TREE_PATH.replace("${name}", category1).replace("${path}", path1));

		/*Step number: 3
		 *Step Name: 
		 *Step Description: 
			- In Events' row, click Edit button
		 *Input Data: 

		 *Expected Outcome: 
			Popup "Edit Category tree" opened*/
		click(By.xpath(magCa.ELEMENT_EDIT_CATEGORY_TREE.replace("${categoryTreeName}", category1)));

		/*Step number: 4
		 *Step Name: 
		 *Step Description: 
			- Check tree of Events category
		 *Input Data: 

		 *Expected Outcome: 
			The tree must be : 
			- AllFireWaterAirEarth*/
		click(By.xpath(magCa.ELEMENT_IN_CATEGORY_TREE.replace("${item}",category1)));
		click(By.xpath(magCa.ELEMENT_IN_CATEGORY_TREE.replace("${item}","All")));
		waitForAndGetElement(By.xpath(magCa.ELEMENT_IN_CATEGORY_TREE.replace("${item}","Fire")));
		waitForAndGetElement(By.xpath(magCa.ELEMENT_IN_CATEGORY_TREE.replace("${item}","Water")));
		waitForAndGetElement(By.xpath(magCa.ELEMENT_IN_CATEGORY_TREE.replace("${item}","Air")));
		waitForAndGetElement(By.xpath(magCa.ELEMENT_IN_CATEGORY_TREE.replace("${item}","Earth")));

		/*Step number: 5
		 *Step Name: 
		 *Step Description: 
			- Click previous button twice to access to permission configuration
		 *Input Data: 

		 *Expected Outcome: 
			- The permissions of powers category tree are :  *:/platform/administrators : all *:/platform/web
			-contributors : allany : read*/
		click(magCa.ELEMENT_PREVIOUS_BUTTON_3SCREEN);
		click(magCa.ELEMENT_PREVIOUS_BUTTON_2SCREEN);
		waitForAndGetElement(By.xpath(magCa.ELEMENT_CHECK_PERMISSION.replace("${permission}",permission1)));
		waitForAndGetElement(By.xpath(magCa.ELEMENT_CHECK_PERMISSION.replace("${permission}",permission2)));
		button.close();

		/*Step number: 6
		 *Step Name: 
		 *Step Description: 
			- Go to Site Explorer and the drive Collaboration
			- Go to site/acme/events
		 *Input Data: 

		 *Expected Outcome: 
			events category is displayed*/ 
		nav.goToSiteExplorer();
		click(ecms.ELEMENT_CHANGE_DRIVE);
		actBar.goToCollaboration();
		actBar.goToNodeByAddressPath("/sites/acme/events/All");
		waitForAndGetElement(actBar.ELEMENT_SITE_EXPLORER_NODE_PATH1.replace("${name}", "Air"));
		waitForAndGetElement(actBar.ELEMENT_SITE_EXPLORER_NODE_PATH1.replace("${name}", "Water"));
		waitForAndGetElement(actBar.ELEMENT_SITE_EXPLORER_NODE_PATH1.replace("${name}", "Fire"));
		waitForAndGetElement(actBar.ELEMENT_SITE_EXPLORER_NODE_PATH1.replace("${name}", "Earth"));
	}

	/**
	 *<li> Case ID:124611.</li>
	 *<li> Test Case Name: Check configuration of Events catogory tree when acme extension is not installed.</li>
	 *<li> Pre-Condition: acme extension must not be installed</li>
	 *<li> Post-Condition: </li>
	 *<li> Done with OSs and browsers : </li>
	 *<li> Generated by rosso at 2015/04/21 09:26:53</li>
	 */
	@Test(groups="pending")
	public  void test02_CheckConfigurationOfEventsCatogoryTreeWhenAcmeExtensionIsNotInstalled() {
		info("Test 2: Check configuration of Events catogory tree when acme extension is not installed");
		String category1 = "events";

		ecmMain.goToCategoriesTabInContentAdmin();	
		/*Step Number: 1
		 *Step Name: 
		 *Step Description: 
			- Login as administrator
			- Go to Content Administration
			- Click Advanced 
			-
			-> Categories
		 *Input Data: 

		 *Expected Outcome: 
			Categories list is shown*/
		/*Step number: 2
		 *Step Name: 
		 *Step Description: 
			- Check Categories list
		 *Input Data: 

		 *Expected Outcome: 
			- The category tree events does not appear in the list*/ 
		waitForElementNotPresent(magCa.ELEMENT_CATEGORY_TREE.replace("${name}", category1));
	}

	/**
	 *<li> Case ID:124612.</li>
	 *<li> Test Case Name: Check configuration of Intranet category tree.</li>
	 *<li> Pre-Condition: Platform is started without extension</li>
	 */
	@Test
	public  void test03_CheckConfigurationOfIntranetCategoryTree() {
		info("Test 3: Check configuration of Intranet category tree");
		String category1 = "intranet";
		String path1 = " /sites /intranet /categories /intranet";
		String workspace1 = "collaboration";
		String permission1 ="*:/platform/administrators";
		String permission2 ="*:/platform/web-contributors";
		/*Step Number: 1
		 *Step Name: 
		 *Step Description: 
			- Login as administrator
			- Go to Content Administration
			- Click Advanced 
			-
			-> Categories
		 *Input Data: 

		 *Expected Outcome: 
			Categories list is shown*/
		ecmMain.goToCategoriesTabInContentAdmin();	

		/*Step number: 2
		 *Step Name: 
		 *Step Description: 
			- Check names of the category tree
			- Check the workspace of Intranet category tree
			- Check the path of Intranet category tree
		 *Input Data: 

		 *Expected Outcome: 
			- The list must includes the category tree Intranet
			- The workspace of events category tree is collaboration
			- The path of events category tree is: /sites/intranet/categories/intranet*/
		waitForAndGetElement(magCa.ELEMENT_CATEGORY_TREE.replace("${name}", category1));
		waitForAndGetElement(magCa.ELEMENT_CATEGORY_TREE_WORKSPACE.replace("${name}", category1).replace("${workspace}", workspace1));
		waitForAndGetElement(magCa.ELEMENT_CATEGORY_TREE_PATH.replace("${name}", category1).replace("${path}", path1));

		/*Step number: 3
		 *Step Name: 
		 *Step Description: 
			- In powers' row, click Edit button
		 *Input Data: 

		 *Expected Outcome: 
			Popup "Edit Category tree" opened*/
		click(By.xpath(magCa.ELEMENT_EDIT_CATEGORY_TREE.replace("${categoryTreeName}", category1)));

		/*Step number: 4
		 *Step Name: 
		 *Step Description: 
			- Check tree of powers category
		 *Input Data: 

		 *Expected Outcome: 
			The tree must be : 
			- Intranet*/
		waitForAndGetElement(By.xpath(magCa.ELEMENT_IN_CATEGORY_TREE.replace("${item}",category1)));

		/*Step number: 5
		 *Step Name: 
		 *Step Description: 
			- Click previous button twice to access to permission configuration
		 *Input Data: 

		 *Expected Outcome: 
			- The permissions of powers category tree are : *:/platform/administrators : all *:/platform/web
			-contributors : allany : read*/
		click(magCa.ELEMENT_PREVIOUS_BUTTON_3SCREEN);
		click(magCa.ELEMENT_PREVIOUS_BUTTON_2SCREEN);
		waitForAndGetElement(By.xpath(magCa.ELEMENT_CHECK_PERMISSION.replace("${permission}",permission1)));
		waitForAndGetElement(By.xpath(magCa.ELEMENT_CHECK_PERMISSION.replace("${permission}",permission2)));
		button.close();
		/*Step number: 6
		 *Step Name: 
		 *Step Description: 
			- Go to Site Explorer and the drive Collaboration
			- Go to site/intranet/categories/intranet
		 *Input Data: 

		 *Expected Outcome: 
			Intranet category is displayed*/ 
		nav.goToSiteExplorer();
		click(ecms.ELEMENT_CHANGE_DRIVE);
		actBar.goToCollaboration();
		actBar.goToNodeByAddressPath("sites/intranet/categories/");
		waitForAndGetElement(actBar.ELEMENT_SITE_EXPLORER_NODE_PATH1.replace("${name}", "intranet"));
	}

	/**
	 *<li> Case ID:124583.</li>
	 *<li> Test Case Name: Check configuration of Powers catogory tree on acme site.</li>
	 *<li> Pre-Condition: acme extension must have been installed</li>
	 *<li> Post-Condition: </li>
	 *<li> Done with OSs and browsers : </li>
	 *<li> Generated by rosso at 2015/04/21 09:26:53</li>
	 */
	@Test
	public  void test04_CheckConfigurationOfPowersCatogoryTreeOnAcmeSite() {
		info("Test 4: Check configuration of Powers catogory tree on acme site");
		String category1 = "powers";
		String path1 = " /sites /acme /categories /powers";
		String workspace1 = "collaboration";
		String permission1 ="*:/platform/administrators";
		String permission2 ="*:/platform/web-contributors";

		ecmMain.goToCategoriesTabInContentAdmin();	

		/*Step Number: 1
		 *Step Name: 
		 *Step Description: 
			- Login as administrator
			- Go to Content Administration
			- Click Advanced 
			-
			-> Categories
		 *Input Data: 

		 *Expected Outcome: 
			Categories list is shown*/
		/*Step number: 2
		 *Step Name: 
		 *Step Description: 
			- Check names of the category tree
			- Check the workspace of power category tree
			- Check the path of power category tree
		 *Input Data: 

		 *Expected Outcome: 
			- The list must includes the category tree powers
			- The workspace of powers category tree is collaboration
			- The path of powers category tree is 	 /sites/acme/categories/powers*/
		waitForAndGetElement(magCa.ELEMENT_CATEGORY_TREE.replace("${name}", category1));
		waitForAndGetElement(magCa.ELEMENT_CATEGORY_TREE_WORKSPACE.replace("${name}", category1).replace("${workspace}", workspace1));
		waitForAndGetElement(magCa.ELEMENT_CATEGORY_TREE_PATH.replace("${name}", category1).replace("${path}", path1));

		/*Step number: 3
		 *Step Name: 
		 *Step Description: 
			- In powers' row, click Edit button
		 *Input Data: 

		 *Expected Outcome: 
			Popup "Edit Category tree" opened*/
		click(By.xpath(magCa.ELEMENT_EDIT_CATEGORY_TREE.replace("${categoryTreeName}", category1)));

		/*Step number: 4
		 *Step Name: 
		 *Step Description: 
			- Check tree of powers category
		 *Input Data: 

		 *Expected Outcome: 
			The tree must be : 1. Defense 1.1 Vision1.2 Invisibility1.3 Healing1.4 Immunity2. Movement3. Natural Elements*/
		click(By.xpath(magCa.ELEMENT_IN_CATEGORY_TREE.replace("${item}",category1)));
		click(By.xpath(magCa.ELEMENT_IN_CATEGORY_TREE.replace("${item}","Defense")));
		waitForAndGetElement(By.xpath(magCa.ELEMENT_IN_CATEGORY_TREE.replace("${item}","Vision")));
		waitForAndGetElement(By.xpath(magCa.ELEMENT_IN_CATEGORY_TREE.replace("${item}","Invisibility")));
		waitForAndGetElement(By.xpath(magCa.ELEMENT_IN_CATEGORY_TREE.replace("${item}","Healing")));
		waitForAndGetElement(By.xpath(magCa.ELEMENT_IN_CATEGORY_TREE.replace("${item}","Immunity")));
		waitForAndGetElement(By.xpath(magCa.ELEMENT_IN_CATEGORY_TREE.replace("${item}","Movement")));
		waitForAndGetElement(By.xpath(magCa.ELEMENT_IN_CATEGORY_TREE.replace("${item}","Natural Elements")));

		/*Step number: 5
		 *Step Name: 
		 *Step Description: 
			- Click previous button twice to access to permission configuration
		 *Input Data: 

		 *Expected Outcome: 
			- The permissions of powers category tree are : *:/platform/administrators : all *:/platform/web
			-contributors : allany : read*/
		click(magCa.ELEMENT_PREVIOUS_BUTTON_3SCREEN);
		click(magCa.ELEMENT_PREVIOUS_BUTTON_2SCREEN);
		waitForAndGetElement(By.xpath(magCa.ELEMENT_CHECK_PERMISSION.replace("${permission}",permission1)));
		waitForAndGetElement(By.xpath(magCa.ELEMENT_CHECK_PERMISSION.replace("${permission}",permission2)));
		button.close();

		/*Step number: 6
		 *Step Name: 
		 *Step Description: 
			- Go to Site Explorer and the drive Collaboration
			- Go to site/acme/categories
		 *Input Data: 

		 *Expected Outcome: 
			powers category is displayed*/ 
		nav.goToSiteExplorer();
		click(ecms.ELEMENT_CHANGE_DRIVE);
		actBar.goToCollaboration();
		actBar.goToNodeByAddressPath("sites/acme/categories/");
		waitForAndGetElement(actBar.ELEMENT_SITE_EXPLORER_NODE_PATH1.replace("${name}", category1));
	}

	/**
	 *<li> Case ID:124609.</li>
	 *<li> Test Case Name: Check configuration of Powers catogory tree when extension is not installed.</li>
	 *<li> Pre-Condition: acme extension is not installed</li>
	 */
	@Test(groups="pending")
	public  void test05_CheckConfigurationOfPowersCatogoryTreeWhenExtensionIsNotInstalled() {
		info("Test 5: Check configuration of Powers catogory tree when extension is not installed");
		String category1 = "powers";
		ecmMain.goToCategoriesTabInContentAdmin();	

		/*Step Number: 1
		 *Step Name: 
		 *Step Description: 
			- Login as administrator
			- Go to Content Administration
			- Click Advanced 
			-
			-> Categories
		 *Input Data: 

		 *Expected Outcome: 
			Categories list is shown*/

		/*Step number: 2
		 *Step Name: 
		 *Step Description: 
			- Check Categories list
		 *Input Data: 

		 *Expected Outcome: 
			- Category tree powers does not appear in the list*/ 
		waitForElementNotPresent(magCa.ELEMENT_CATEGORY_TREE.replace("${name}", category1));
	}
}
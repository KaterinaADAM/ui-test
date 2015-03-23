
package org.exoplatform.selenium.platform.wiki.functional.permissions.wikipermissions;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.ManageAccount.userType;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformPermission;
import org.exoplatform.selenium.platform.social.ManageMember;
import org.exoplatform.selenium.platform.social.SpaceManagement;
import org.exoplatform.selenium.platform.wiki.BasicAction;
import org.exoplatform.selenium.platform.wiki.Permission;
import org.exoplatform.selenium.platform.wiki.WikiBase;
import org.openqa.selenium.By;
import org.testng.annotations.*;

/**
 * @date 17/09/2014
 *
 */
public class Wiki_Permissions_WikiPermissions_Delete extends WikiBase{
	ManageAccount magAcc;
	Button button;
	PlatformPermission per;
	NavigationToolbar navTool;
	Permission perWiki;
	BasicAction ba;
	SpaceManagement spMag;
	ManageMember magMem;

	@BeforeMethod
	public void setUpBeforeTest(){
		initSeleniumTest();
		driver.get(baseUrl);
		magAcc = new ManageAccount(driver);
		button = new Button(driver);
		per = new PlatformPermission(driver);
		navTool = new NavigationToolbar(driver, this.plfVersion);
		perWiki = new Permission(driver);
		ba = new BasicAction(driver);
		spMag = new SpaceManagement(driver, this.plfVersion);
		magMem = new ManageMember(driver, this.plfVersion);
		magAcc.signIn(DATA_USER1, DATA_PASS);
		goToWiki();
	}

	@AfterMethod
	public void afterTest(){
		driver.manage().deleteAllCookies();
		driver.quit();
	}
	/**
	 * Case ID:118244.
	 * Test Case Name: Delete permissions for a group.
	 * Pre-Condition: - Group A already has permissions: View Pages or Edit Pages or Admin Pages or Admin Wiki, or some of them, or all these permissions
	- Some wiki pages are already created
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by thuntn at 2014/09/17 15:06:55
	 */
	@Test
	public  void test01_DeletePermissionsForAGroup() {
		info("Test 1: Delete permissions for a group");
		
		String[] user = {"Development"};
		String title = "Delete permission118244" + getRandomNumber();
		String space = "space118244" + getRandomNumber();
		
		spMag.goToAllSpaces();
		spMag.addNewSpace(space, space);
		spMag.goToSpaceMenu("Space Settings");
		click(spMag.ELEMENT_MEMBER_TAB);
		magMem.inviteSingleUser(userType.DEVELOPER);
		goToWikiFromSpace(space);
		
		ba.addBlankWikiPage(title, title, 0);
		perWiki.deleteSpacePermission("*:/spaces/" + space);
		perWiki.addSpacePermission(2, user);
		perWiki.editSpacePermission("/developers", true, true, true, true,2);
		
		/*	Step Number: 1
		 *Step Name: Step 1: Open form to add permission for space
		 *Step Description: 
			- Go to Browse 
			-
			-> Wiki Settings
			- Choose Permission tab
		 *Input Data: 

		 *Expected Outcome: 
			Space Permissions form appears
		*/

		/*Step number: 2
		 *Step Name: Step 2: Delete permission
		 *Step Description: 
			- Check permissions of Group A
			- Click Delete icon corresponding to Group A
		 *Input Data: 

		 *Expected Outcome: 
			Group A with their permissions are removed from Permissions table*/
		perWiki.deleteSpacePermission("/developers");

		/*Step number: 3
		 *Step Name: Step 3: Check if the deleted user has any permission
		 *Step Description: 
			- Login as user member of Group A
			- Go to the space 
			-
			-> Wiki
		 *Input Data: 

		 *Expected Outcome: 
			The user cannot view any wiki page: Page Not Found */
		perWiki.deleteSpacePermission("any");
		magAcc.userSignIn(userType.DEVELOPER);
		magMem.acceptInvitation(space);
		
		By element_space = By.linkText(space);

		info("Go to wiki page of space " + space);
		Utils.pause(1000);
		if (isElementNotPresent(ELEMENT_WIKI_LINK_IN_SPACE)){
			magMem.goToMySpacePage();
			click(element_space);
			Utils.pause(2000);
		}
		click(ELEMENT_WIKI_LINK_IN_SPACE);	
		
		waitForAndGetElement(ELEMENT_PAGE_NOT_FOUND);
		magAcc.userSignIn(userType.ADMIN);
		spMag.goToAllSpaces();
		spMag.deleteSpace(space);
	}



	/**
	 * Case ID:118228.
	 * Test Case Name: Delete permissions for a user.
	 * Pre-Condition: - User A already has permissions: View Pages or Edit Pages or Admin Pages or Admin Wiki, or some of them, or all these permissions
	- Some wiki pages are already created
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by thuntn at 2014/09/17 15:06:55
	 */
	@Test
	public  void test02_DeletePermissionsForAUser() {
		info("Test 2: Delete permissions for a user");
		String[] user = {"mary"};
		String title = "Delete permission 118228";
		String space = "space118228";
		
		spMag.goToAllSpaces();
		spMag.addNewSpace(space, space);
		spMag.goToSpaceMenu("Space Settings");
		click(spMag.ELEMENT_MEMBER_TAB);
		magMem.inviteSingleUser(userType.PUBLISHER);
		goToWikiFromSpace(space);
		
		ba.addBlankWikiPage(title, title, 0);
		perWiki.deleteSpacePermission("*:/spaces/" + space);
		perWiki.addSpacePermission(1, user);
		perWiki.editSpacePermission(user[0], true, true, true, true,2);
		
		/*Step Number: 1
		 *Step Name: Step 1: Open form to add permission for space
		 *Step Description: 
			- Go to Browse 
			-
			-> Wiki Settings
			- Choose Permission tab
		 *Input Data: 

		 *Expected Outcome: 
			Space Permissions form appears*/


		/*Step number: 2
		 *Step Name: Step 2: Delete permission
		 *Step Description: 
			- Check permissions of User A
			- Click Delete icon corresponding to User A
		 *Input Data: 

		 *Expected Outcome: 
			User A with his permissions are removed from Permissions table*/
		perWiki.deleteSpacePermission(user[0]);

		/*Step number: 3
		 *Step Name: Step 3: Check if the deleted user has any permission
		 *Step Description: 
			- Login as User A
			- Go to the space 
			-
			-> Wiki
		 *Input Data: 

		 *Expected Outcome: 
			User A cannot view any wiki page: Page Not Found*/ 
		magAcc.userSignIn(userType.PUBLISHER);
		magMem.acceptInvitation(space);
		
		By element_space = By.linkText(space);
		info("Go to wiki page of space " + space);
		Utils.pause(1000);
		if (isElementNotPresent(ELEMENT_WIKI_LINK_IN_SPACE)){
			magMem.goToMySpacePage();
			click(element_space);
			Utils.pause(2000);
		}
		click(ELEMENT_WIKI_LINK_IN_SPACE);	

		waitForAndGetElement(ELEMENT_PAGE_NOT_FOUND);
		magAcc.userSignIn(userType.ADMIN);
		spMag.goToAllSpaces();
		spMag.deleteSpace(space);
	}
	
	/**
	 * Case ID:113635.
	 * Test Case Name: Delete permission for a group by unchecking all permission checkbox
	 * Pre-Condition: - The Group A has already the permission : View Pages in the Wiki Settings
	- Some wiki pages are already created in this space
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by thuntn at 2014/09/17 15:06:55
	 */
	@Test
	public  void test03_DeleteViewPermissionForAGroup() {
		info("Test 3: Delete permission for a group by unchecking all permission checkbox");

		String[] user = {"Platform/Content Management"};
		String title = "Delete permission 3113635";
		String space = "space3113635";

		spMag.goToAllSpaces();
		spMag.addNewSpace(space, space);
		spMag.goToSpaceMenu("Space Settings");
		click(spMag.ELEMENT_MEMBER_TAB);
		magMem.inviteSingleUser(userType.PUBLISHER);
		goToWikiFromSpace(space);

		ba.addBlankWikiPage(title, title, 0);
		perWiki.deleteSpacePermission("*:/spaces/" + space);
		perWiki.addSpacePermission(2, user);
		perWiki.editSpacePermission("/web-contributors", true, true, true, true,2);

		/*Step Number: 1
		 *Step Name: Step 1: Open form to add permission for space
		 *Step Description: 
			- Go to Browse 
			-
			-> Wiki Settings
			- Choose Permission tab
		 *Input Data: 

		 *Expected Outcome: 
			Space Wiki Permissions form appears*/


		/*Step number: 2
		 *Step Name: Step 2: Verify permission for a Group
		 *Step Description: 
			- Check the Group A permission
			- In the permission table, untick the View Pages checkbox of the Group A
			- Click Save 
			-
			-> OK
		 *Input Data: 

		 *Expected Outcome: 
			- The permission View Pages is unselected(if the group had only the View permission, the group is is auto
			-removed from the Wiki Permissions table)*/
		perWiki.editSpacePermission("/web-contributors", false, false, false, false,2);

		/*Step number: 3
		 *Step Name: Step 3: Check if the deselected group can view the page
		 *Step Description: 
			- Log in with a user member of the Group A
			- Go to Wiki of the space.
		 *Input Data: 

		 *Expected Outcome: 
			- The user cannot view the wiki page: Page Not Found*/ 
		magAcc.userSignIn(userType.PUBLISHER);
		magMem.acceptInvitation(space);

		By element_space = By.linkText(space);
		info("Go to wiki page of space " + space);
		Utils.pause(1000);
		if (isElementNotPresent(ELEMENT_WIKI_LINK_IN_SPACE)){
			magMem.goToMySpacePage();
			click(element_space);
			Utils.pause(2000);
		}
		click(ELEMENT_WIKI_LINK_IN_SPACE);	

		waitForAndGetElement(ELEMENT_PAGE_NOT_FOUND);
		magAcc.userSignIn(userType.ADMIN);
		spMag.goToAllSpaces();
		spMag.deleteSpace(space);

	}

	/**
	 * Case ID:113605.
	 * Test Case Name: Delete permission for a user by unchecking all permission checkbox.
	 * Pre-Condition: - The User A has already the permission : View Pages in the Wiki Settings
	- Some wiki pages are already created in this space
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by thuntn at 2014/09/17 15:06:55
	 */
	@Test
	public  void test04_DeleteViewPermissionForAUser() {
		info("Test 4 Delete permission for a user by unchecking all permission checkbox");

		String[] user = {"mary"};
		String title = "Delete permission 4113605";
		String space = "space4113605";

		spMag.goToAllSpaces();
		spMag.addNewSpace(space, space);
		spMag.goToSpaceMenu("Space Settings");
		click(spMag.ELEMENT_MEMBER_TAB);
		magMem.inviteSingleUser(userType.PUBLISHER);
		goToWikiFromSpace(space);

		ba.addBlankWikiPage(title, title, 0);
		perWiki.deleteSpacePermission("*:/spaces/" + space);
		perWiki.addSpacePermission(1, user);
		perWiki.editSpacePermission(user[0], true, true, true, true,2);

		/*Step Number: 1
		 *Step Name: Step 1: Open form to add permission for space
		 *Step Description: 
			- Go to Browse 
			-
			-> Wiki Settings
			- Choose Permission tab
		 *Input Data: 

		 *Expected Outcome: 
			Space Wiki Permissions form appears*/


		/*Step number: 2
		 *Step Name: Step 2: Verify permission for a User
		 *Step Description: 
			- Check the User A permission
			- In the permission table, untick the View Pages checkbox of the User A
			- Click Save 
			-
			-> OK
		 *Input Data: 

		 *Expected Outcome: 
			- The permission View Pages is unselected(if the user had only the View permission, he is is auto
			-removed from the Wiki Permissions table)*/
		perWiki.editSpacePermission(user[0], false, false, false, false,2);

		/*Step number: 3
		 *Step Name: Step 3: Check if the deselected user can view the page
		 *Step Description: 
			- Log in as User A
			- Go to Wiki of the space.
		 *Input Data: 

		 *Expected Outcome: 
			- The user cannot view the wiki page: Page Not Found*/ 
		magAcc.userSignIn(userType.PUBLISHER);
		magMem.acceptInvitation(space);

		By element_space = By.linkText(space);
		info("Go to wiki page of space " + space);
		Utils.pause(1000);
		if (isElementNotPresent(ELEMENT_WIKI_LINK_IN_SPACE)){
			magMem.goToMySpacePage();
			click(element_space);
			Utils.pause(2000);
		}
		click(ELEMENT_WIKI_LINK_IN_SPACE);	

		waitForAndGetElement(ELEMENT_PAGE_NOT_FOUND);
		magAcc.userSignIn(userType.ADMIN);
		spMag.goToAllSpaces();
		spMag.deleteSpace(space);
	}
}
package org.exoplatform.selenium.platform.wiki.functional.pagepermission;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Dialog;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.wiki.Permalink;
import org.openqa.selenium.By;
import org.testng.annotations.*;

public class Wiki_PagePermission_Delete extends Permalink{

	ManageAccount magAc;
	Dialog dialog;

	@BeforeMethod
	public void beforeTest(){
		initSeleniumTest();
		driver.get(baseUrl);
		magAc = new ManageAccount(driver);
		magAc.signIn(DATA_USER1, DATA_PASS);
		dialog = new Dialog(driver);
		goToWiki();
	}

	@AfterMethod
	public void afterTest(){
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	/**
	 *Case ID:78330.
	 *Test Case Name: Delete permissions for a user.
	 *Pre-Condition: - wiki page PageA is already created
	- User A has permissions on PageA: View Pages or Edit Pages or both in Page Permissions
	- User A does not belong to any groups who have permission to View Pages, Edit Pages, in Wiki Settings
	 *Post-Condition: 
	 *Done with OSs and browsers : 
	 *Generated by phuongdt at 2014/09/18 14:17:25
	 */
	@Test
	public  void test01_DeletePermissionsForAUser() {
		info("Test 1: Delete permissions for a user");
		String title = "title78330";
		String content = "content78330";
		String new_content = "newcontent78330";
		By element_page = By.linkText(title);
		String user = DATA_USER3;
		String[] userGroup = {user};

		info("Add a wiki page");
		goToWiki();
		addBlankWikiPage(title, content, 0);

		info("Add user has permission default");
		deletePagePermission("any");
		waitForElementNotPresent(ELEMENT_PAGE_PERMISSION_POPUP);
		addPagePermission(1, userGroup);
		waitForElementNotPresent(ELEMENT_PAGE_PERMISSION_POPUP);

		info("Add edit page permission for " + user);
		checkAndEditPagePermission(user, 2);

		info("Check user can view/edit wiki page");
		magAc.signOut();
		magAc.signIn(user, DATA_PASS);
		checkViewEditPage(element_page, content, new_content);

		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);

		/*Step Number: 1
		 *Step Name: Step 1: Open Page Permissions of PageA
		 *Step Description: 
			- Open the wiki page PageA
			- Select More 
			-
			-> Page Permissions
		 *Input Data: 

		 *Expected Outcome: 
			- Page Permissions form displays*/

		/*Step number: 2
		 *Step Name: Step 2: Delete permission for User A
		 *Step Description: 
			- Check the User A permissions
			- Click Delete icon corresponding to User A
			- Click Save
		 *Input Data: 

		 *Expected Outcome: 
			User A is removed from Page Permissions*/	
		deletePermissionWithUserAdmin(user, element_page);

		/*Step number: 3
		 *Step Name: Step 3: Check if User A have permission
		 *Step Description: 
			- Login as User A
			- Go to the space 
			-
			-> Wiki
		 *Input Data: 

		 *Expected Outcome: 
			User A will not see the wiki page PageA anymore*/
		checkViewPage(ManageAccount.userType.AUTHOR, element_page);

		//Clear data
		info("Clear data");
		String[] wikiPath = {"Wiki Home/" + title};
		resetDataByDeleteWikiPage(ManageAccount.userType.ADMIN, wikiPath);
	}


	/**
	 *Case ID:113647.
	 *Test Case Name: Delete permissions for a group.
	 *Pre-Condition: - wiki page PageA is already created
	- Group A has permissions on PageA: View Pages or Edit Pages or both in Page Permissions
	- Member of Group A does not belong to any groups who have permission: View Pages, Edit Pages, in Page Permissions
	 *Post-Condition: 
	 *Done with OSs and browsers : 
	 *Generated by phuongdt at 2014/09/18 14:17:25
	 */
	@Test
	public  void test02_DeletePermissionsForAGroup() {
		info("Test 2: Delete permissions for a group");
		String title = "title113647";
		String content = "content113647";
		String new_content = "newcontent113647";
		By element_page = By.linkText(title);
		String user = DATA_USER3;
		String path = "*:/organization/employees";
		String[] userGroup = {path};

		info("Add a wiki page");
		goToWiki();
		addBlankWikiPage(title, content, 0);

		info("Add user has permission default");
		deletePagePermission("any");
		waitForElementNotPresent(ELEMENT_PAGE_PERMISSION_POPUP);
		addPagePermission(0, userGroup);
		waitForElementNotPresent(ELEMENT_PAGE_PERMISSION_POPUP);

		info("Add edit page permission for " + user);
		editPagePermission(path, true, true);

		info("Check user can view/edit wiki page");
		magAc.signOut();
		magAc.signIn(user, DATA_PASS);
		checkViewEditPage(element_page, content, new_content);

		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);

		/*Step Number: 1
		 *Step Name: Step 1: Open Page Permissions form
		 *Step Description: 
			- Go to space 
			-
			-> Wiki
			- Open the wiki page PageA
			- Select More 
			-
			-> Page Permissions
		 *Input Data: 

		 *Expected Outcome: 
			- Page Permissions form displays*/

		/*Step number: 2
		 *Step Name: Step 2: Delete permission for Group A
		 *Step Description: 
			- Check the Group A permissions
			- Click Delete icon corresponding to Group A
			- Click Save
		 *Input Data: 

		 *Expected Outcome: 
			Permission of Group A is deleted*/
		deletePermissionWithUserAdmin(path, element_page);

		/*Step number: 3
		 *Step Name: Step 3: Check if Group A have deleted permission
		 *Step Description: 
			- Login as user member of Group A
			- Go to the space 
			-
			-> Wiki
		 *Input Data: 

		 *Expected Outcome: 
			The user will not see PageA in the left panel anymore*/ 
		checkViewPage(ManageAccount.userType.AUTHOR, element_page);

		//Clear data
		info("Clear data");
		String[] wikiPath = {"Wiki Home/" + title};
		resetDataByDeleteWikiPage(ManageAccount.userType.ADMIN, wikiPath);

	}

	/**
	 *Case ID:78339.
	 *Test Case Name: Delete permissions by removing all for a user.
	 *Pre-Condition: - wiki page PageA is already created
	- User A already has permissions: View Pages, Edit Pages in Page Permissions
	- User A does not belong to any groups who have View Pages, Edit Pages in Page Permissions
	 *Post-Condition: 
	 *Done with OSs and browsers : 
	 *Generated by phuongdt at 2014/09/19 12:00:45
	 */
	@Test
	public  void test03_DeletePermissionsByRemovingAllForAUser() {
		info("Test 3: Delete permissions by removing all for a user");
		String title = "title78339";
		String content = "content78339";
		String new_content = "newcontent78339";
		By element_page = By.linkText(title);
		String user = DATA_USER3;
		String[] userGroup = {user};

		info("Add a wiki page");
		goToWiki();
		addBlankWikiPage(title, content, 0);

		info("Add user has permission default");
		deletePagePermission("any");
		waitForElementNotPresent(ELEMENT_PAGE_PERMISSION_POPUP);
		addPagePermission(1, userGroup);
		waitForElementNotPresent(ELEMENT_PAGE_PERMISSION_POPUP);

		info("Add edit page permission for " + user);
		editPagePermission(user, true, true);
		goToPermalink();
		String permalink = getPermalink();
		dialog.closeMessageDialog();

		info("Check user can view/edit wiki page");
		magAc.signOut();
		magAc.signIn(user, DATA_PASS);
		checkViewEditPage(element_page, content, new_content);

		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);

		/*Step Number: 1
		 *Step Name: Step 1: Open Page Permissions form
		 *Step Description: 
			- Open wiki page PageA
			- Go to More 
			-
			-> Page Permissions
		 *Input Data: 

		 *Expected Outcome: 
			- Page Permissions form displays*/

		/*Step number: 2
		 *Step Name: Step 2: Remove all permissions for User A
		 *Step Description: 
			- Check User A permissions
			- Untick View Pages and Edit Pages checkboxes corresponding to User A
			- Click Save
		 *Input Data: 

		 *Expected Outcome: 
			- View Pages and Edit Pages are deselected
			- User A is removed from Page Permissions form*/
		goToWikiPage("Wiki Home/" + title, ManageAccount.userType.ADMIN);
		editPagePermission(user, false, false);
		magAc.signOut();

		/*Step number: 3
		 *Step Name: Step 3: Check if User A has permission
		 *Step Description: 
			- Log in as User A
			- Go to space 
			-
			-> Wiki
		 *Input Data: 

		 *Expected Outcome: 
			User A will not see PageA in the left panel. If User A use the direct link of PageA, "Page Not Found" is shown*/
		magAc.signIn(user, DATA_PASS);
		goToWiki();
		waitForElementNotPresent(By.linkText(title));
		magAc.signOut();
		goToWikiByPermalink(user, permalink, false, content);

		//Clear data
		info("Clear data");
		String[] wikiPath = {"Wiki Home/" + title};
		resetDataByDeleteWikiPage(ManageAccount.userType.ADMIN, wikiPath);
	}

	/**
	 *Case ID:113652.
	 *Test Case Name: Delete permissions by removing all for a group.
	 *Pre-Condition: - wiki page PageA is already created
	- Group A already has permissions: View Pages, Edit Pages in Page Permissions
	- Member of Group A does not belong to any groups who have View Pages, Edit Pages in Page Permissions
	- Member of Group A does not have permissions: View Pages, Edit Pages in Page Permissions
	 *Post-Condition: 
	 *Done with OSs and browsers : 
	 *Generated by phuongdt at 2014/09/19 12:00:45
	 */
	@Test
	public  void test04_DeletePermissionsByRemovingAllForAGroup() {
		info("Test 4: Delete permissions by removing all for a group");
		String title = "title113652";
		String content = "content113652";
		String new_content = "newcontent113652";
		By element_page = By.linkText(title);
		String user = DATA_USER3;
		String path = "*:/organization/employees";
		String[] userGroup = {path};

		info("Add a wiki page");
		goToWiki();
		addBlankWikiPage(title, content, 0);

		info("Add user has permission default");
		goToPermalink();
		String permalink = getPermalink();
		dialog.closeMessageDialog();
		deletePagePermission("any");
		waitForElementNotPresent(ELEMENT_PAGE_PERMISSION_POPUP);
		addPagePermission(0, userGroup);
		waitForElementNotPresent(ELEMENT_PAGE_PERMISSION_POPUP);

		info("Add edit page permission for " + user);
		editPagePermission(path, true, true);

		info("Check user can view/edit wiki page");
		magAc.signOut();
		magAc.signIn(user, DATA_PASS);
		checkViewEditPage(element_page, content, new_content);

		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		/*Step Number: 1
		 *Step Name: Step 1: Open Page Permissions form
		 *Step Description: 
			- Open wiki page PageA
			- Go to More 
			-
			-> Page Permissions
		 *Input Data: 

		 *Expected Outcome: 
			- Page Permissions form displays*/

		/*Step number: 2
		 *Step Name: Step 2: Remove all permissions for Group A
		 *Step Description: 
			- Check Group A permissions
			- Untick View Pages and Edit Pages checkboxes corresponding to Group A
			- Click Save
		 *Input Data: 

		 *Expected Outcome: 
			- View Pages and Edit Pages are deselected
			- Group A is removed from Page Permissions form*/
		goToWikiPage("Wiki Home/" + title, ManageAccount.userType.ADMIN);
		editPagePermission(path, false, false);
		magAc.signOut();

		/*Step number: 3
		 *Step Name: Step 3: Check if Group A has permission
		 *Step Description: 
			- Log in as user member of Group A
			- Go to space 
			-
			-> Wiki
		 *Input Data: 

		 *Expected Outcome: 
			The user will not see PageA in the left panel. If the user uses the direct link of PageA, "Page Not Found" is shown*/
		magAc.signIn(user, DATA_PASS);
		goToWiki();
		waitForElementNotPresent(By.linkText(title));
		magAc.signOut();
		goToWikiByPermalink(user, permalink, false, content);

		//Clear data
		info("Clear data");
		String[] wikiPath = {"Wiki Home/" + title};
		resetDataByDeleteWikiPage(ManageAccount.userType.ADMIN, wikiPath);

	}
}
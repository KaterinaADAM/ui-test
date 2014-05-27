package org.exoplatform.selenium.platform.wiki.functional.basicaction;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.Dialog;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.social.ManageMember;
import org.exoplatform.selenium.platform.social.SpaceManagement;
import org.exoplatform.selenium.platform.social.SpaceSearch;
import org.exoplatform.selenium.platform.wiki.Permalink;
import org.openqa.selenium.By;
import org.testng.annotations.*;


	/**
	* @author lientm
	* @date 26/05/2014
	*/
	public class Wiki_BasicAction_Permanlink extends Permalink {
	ManageAccount magAcc;
	Dialog dialog;
	SpaceManagement spaceMag;
	NavigationToolbar nav;
	ManageMember member;
	Button button;
	SpaceSearch searchS;
		
	@BeforeMethod
	public void beforeMethod(){
		initSeleniumTest();
		driver.get(baseUrl);
		driver.manage().window().maximize();
		magAcc = new ManageAccount(driver);
		dialog = new Dialog(driver);
		spaceMag = new SpaceManagement(driver, this.plfVersion);
		nav = new NavigationToolbar(driver);
		member = new ManageMember(driver, this.plfVersion);
		button = new Button(driver);
		searchS = new SpaceSearch(driver);
		
		magAcc.signIn(DATA_USER1,DATA_PASS);
		goToWiki();
	}

	@AfterMethod
	public void afterMethod(){
		driver.manage().deleteAllCookies();
		driver.quit();
	}
	/**
	*<li> Case ID:78451.</li>
	*<li> Test Case Name: Restrict button for granted user.</li>
	*<li> Pre-Condition: The parent of the page created is with a public status</li>
	*<li> Post-Condition: </li>
	*<li> Done with OSs and browsers : </li>
	*<li> Generated by lientm at 2014/05/26 09:53:10</li>
	*/
	@Test
	public void test01_RestrictButtonForGrantedUser() {
		String title = "Wiki 78451";
		String content = "Content of wiki 78451";
		String user = "demo";
		String userGroup[] = {user};
		
		info("Test 1: Restrict button for granted user");
		/*Step Number: 1
		*Step Name: Step 1; Add public page
		*Step Description: 
			Go to Intranet/WikiCreate a public page
		*Expected Outcome: 
			The page is public*/
		addBlankWikiPage(title, content, 0);
		makePublicPage();
		
		/*Step number: 2
		*Step Name: Step 2: Open Page permission
		*Step Description: 
			From the list [More], choose the link [Page permission]
		*Input Data: 
		*Expected Outcome: 
			The "Page permission" is displayed*/
		/*Step number: 3
		*Step Name: Step 3: Set permission for page
		*Step Description: 
			Cick on the icon [Select a User] Check to the user to grantClick on the button "Add"
		*Input Data: 
		*Expected Outcome: 
			The user is added to the list with "View Page" checked and the "Page edit" box isn't selected*/
		addPagePermission(1, userGroup, 1);

		/*Step number: 4
		*Step Name: Step 4: Edit permission
		*Step Description: 
			Select the icon [Edit page]Click on the button [Save]
		*Input Data: 	
		*Expected Outcome: 
			Edit permission successfully*/
		editPagePermission(user, true, true);
		
		/*Step number: 5
		*Step Name: Step 5: Copy "permalink"
		*Step Description: 
			- Select [More] 
			-> Choose [Permalink]
			- From the page "Permalink", Copy the url of the page to share
		*Input Data: 			
		*Expected Outcome: 
			Copy link successfully*/
		goToPermalink();
		String permalink = getPermalink();
		dialog.closeMessageDialog();
		magAcc.signOut();
		
		/*Step number: 6
		*Step Name: Step 6: View permalink
		*Step Description: 
			- Paste the link in a BrowserClick Enter from the Keyboard and connect with the granted user
		*Input Data: 			
		*Expected Outcome: 
			The page is dispalyed*/
		driver.get(permalink);
		magAcc.signIn(user, DATA_PASS);
		waitForTextPresent(content);
		
		/*Step number: 7
		*Step Name: Step 7: Check "Permalink" link
		*Step Description: 
			From the list [More], choose the link [Permalink]
		*Input Data: 
		*Expected Outcome: 
			The pop up "Permalink" is displayed" and the button "Restrict" is displayed*/ 
		goToPermalink();
		waitForAndGetElement(ELEMENT_MAKE_RESTRICT_BUTTON);
		dialog.closeMessageDialog();
		
		info("Delete data");
		goToWikiPage("Wiki Home/" + title, ManageAccount.userType.ADMIN);
		deleteCurrentWikiPage();
 	}

	/**
	*<li> Case ID:78452.</li>
	*<li> Test Case Name: Restrict button for granted user without "Edit page" permission.</li>
	*<li> Pre-Condition: The parent of the page created is with a public status</li>
	*<li> Post-Condition: </li>
	*<li> Done with OSs and browsers : </li>
	*<li> Generated by lientm at 2014/05/26 09:53:10</li>
	*/
	@Test
	public void test02_RestrictButtonForGrantedUserWithoutEditPagePermission() {
		String title = "Wiki 78452";
		String content = "Content of wiki 78452";
		String user = "demo";
		String userGroup[] = {user};
		
		info("Test 2: Restrict button for granted user without Edit page permission");
		/*Step Number: 1
		*Step Name: Step 1: Add public page
		*Step Description: 
			Go to Intranet/WikiCreate a public page
		*Input Data: 		
		*Expected Outcome: 
			The page is public*/
		addBlankWikiPage(title, content, 0);
		makePublicPage();
		
		/*Step number: 2
		*Step Name: Step 2: Open Page permission
		*Step Description: 
			From the list [More], choose the link [Page permission]
		*Input Data: 
		*Expected Outcome: 
			The "Page permission" is displayed*/
		/*Step number: 3
		*Step Name: Step 3: Set permission for page
		*Step Description: 
			Cick on the icon "Select a User" Check to the user to grantClick on the button "Add"
		*Input Data: 	
		*Expected Outcome: 
			The user is added to the list with "View Page" and the "Page edit" box isn't selected*/
		addPagePermission(1, userGroup, 1);
		
		/*Step number: 4
		*Step Name: Step 4: Copy "permalink"
		*Step Description: 
			- Select [More] 
			-> Choose [Permalink]
			- From the page "Permalink", Copy the url of the page to share
		*Input Data: 
		*Expected Outcome: 
			Copy link successfully*/
		goToPermalink();
		String permalink = getPermalink();
		dialog.closeMessageDialog();
		magAcc.signOut();
		
		/*Step number: 5
		*Step Name: Step 5: View permalink
		*Step Description: 
			- Paste the link in a BrowserClick Enter from the Keyboard and connect with the granted user
		*Input Data: 		
		*Expected Outcome: 
			The page is dispalyed*/
		driver.get(permalink);
		magAcc.signIn(user, DATA_PASS);
		waitForTextPresent(content);
		
		/*Step number: 6
		*Step Name: Step 6: Check "Permalink" link
		*Step Description: 
			From the list [More], choose the link [Permalink]
		*Input Data: 
		*Expected Outcome: 
			The pop up "Permalink" is displayed and the button "Restricted" isn't displayed*/ 
		goToPermalink();
		waitForElementNotPresent(ELEMENT_MAKE_RESTRICT_BUTTON);
		dialog.closeMessageDialog();
		
		info("Delete data");
		goToWikiPage("Wiki Home/" + title, ManageAccount.userType.ADMIN);
		deleteCurrentWikiPage();
 	}

	/**
	*<li> Case ID:78453.</li>
	*<li> Test Case Name: Restrict button for no granted user.</li>
	*<li> Pre-Condition: The parent of the page created is with a public status 
	- The user no granted isn't also the creator the page and the Space manager</li>
	*<li> Post-Condition: </li>
	*<li> Done with OSs and browsers : </li>
	*<li> Generated by lientm at 2014/05/26 09:53:10</li>
	*/
	@Test
	public void test03_RestrictButtonForNoGrantedUser() {
		String title = "Wiki 78453";
		String content = "Content of wiki 78453";
		String user = "demo";
		
		info("Test 3: Restrict button for no granted user");
		/*Step Number: 1
		*Step Name: Step 1: Add public page
		*Step Description: 
			Go to Intranet/WikiCreate a public page
		*Input Data: 		
		*Expected Outcome: 
			The page is public*/
		addBlankWikiPage(title, content, 0);
		makePublicPage();
		
		/*Step number: 2
		*Step Name: Step 2: Copy "permalink"
		*Step Description: 
			- Select More 
			-> Choose Permalink
			- From the page "Permalink", Copy the url of the page to share
		*Input Data: 		
		*Expected Outcome: 
			Copy link successfully*/
		goToPermalink();
		String permalink = getPermalink();
		dialog.closeMessageDialog();
		magAcc.signOut();
		
		/*Step number: 3
		*Step Name: Step 3: View permalink
		*Step Description: 
			- Login with other user
			- Paste the link in a BrowserClick Enter from the Keyboard and connect with a user
		*Input Data: 		
		*Expected Outcome: 
			The page is dispalyed*/
		driver.get(permalink);
		magAcc.signIn(user, DATA_PASS);
		waitForTextPresent(content);
		
		/*Step number: 4
		*Step Name: Step 4: Check "Permalink" link
		*Step Description: 
			From the list "More", choose the link "Permalink"
		*Input Data: 		
		*Expected Outcome: 
			- The pop up " Permalink" is displayed 
			- the button "Restrict" isn't displayed*/ 
		goToPermalink();
		waitForElementNotPresent(ELEMENT_MAKE_RESTRICT_BUTTON);
		dialog.closeMessageDialog();
		
		info("Delete data");
		goToWikiPage("Wiki Home/" + title, ManageAccount.userType.ADMIN);
		deleteCurrentWikiPage();
 	}

	/**
	*<li> Case ID:78472.</li>
	*<li> Test Case Name: Access to page by an user who is not member of the space.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*<li> Done with OSs and browsers : </li>
	*<li> Generated by lientm at 2014/05/26 09:53:10</li>
	*/
	@Test
	public void test04_AccessPage_UserNotMemberOfTheSpace() {
		String space = "space78472";
		String title = "Wiki 78472";
		String content = "Content of wiki 78472";
		String user = "demo";
		
		info("Test 4: Access to page by an user who is not member of the space");
		/*Step Number: 1
		*Step Name: Step 1: Add new page for space when set permission for page
		*Step Description: 
			- Login by admin
			- Add new space
			- Go to space/wiki page
			- Add new page 
			- Set permission for page: remove any permission
			- Select [More] 
			-> choose [Permalink]
			- Copy the link
		*Input Data: 	
		*Expected Outcome: 
			- Add new page have permission successfully
			- Copy link successfully*/
		spaceMag.goToAllSpaces();
		spaceMag.addNewSpace(space, "", "Visible", "Validation", "", "");
		spaceMag.goToSpaceFromMySpaceNavigation(space);
		spaceMag.goToSpaceMenu("Wiki");
		addBlankWikiPage(title, content, 0);
		deletePagePermission("any");
		goToPermalink();
		String permalink = getPermalink();
		dialog.closeMessageDialog();
		magAcc.signOut();
		
		/*Step number: 2
		*Step Name: Step 2: Check permalink when user is not member of space
		*Step Description: 
			- From the list [More], choose the link [Permalink]
			- Copy the link
			- Connect with User B, not a member in the space
			- Paste the permalink
			- Click [Enter] from the keyboard
		*Input Data: 			
		*Expected Outcome: 
			The "Page Not found" is displayed, the user B cannot view the page*/ 
		driver.get(permalink);
		magAcc.signIn(user, DATA_PASS);
		waitForTextPresent("Page Not Found");
		magAcc.signOut();
		
		info("Delete data");
		magAcc.signIn(DATA_USER1,DATA_PASS);
		spaceMag.goToAllSpaces();
		spaceMag.deleteSpace(space, 300000);
 	}

	/**
	*<li> Case ID:78473.</li>
	*<li> Test Case Name: Access to Page permissionby the page creator.</li>
	*<li> Pre-Condition: the user is the space manager and the creator of the page</li>
	*<li> Post-Condition: </li>
	*<li> Done with OSs and browsers : </li>
	*<li> Generated by lientm at 2014/05/26 09:53:10</li>
	*/
	@Test
	public void test05_AccessPagePermission_ThePageCreator() {
		String space = "space78473";
		String title = "Wiki 78473";
		String content = "Content of wiki 78473";
		
		info("Test 5: Access to Page permission by the page creator");
		/*Step Number: 1
		*Step Name: Step 1: Add wiki page for space
		*Step Description: 
			- Login with user A is space manager
			- Go to Space/wiki
			- Create a page
		*Input Data: 	
		*Expected Outcome: 
			a page is created by the manager of the space*/
		spaceMag.goToAllSpaces();
		spaceMag.addNewSpace(space, "", "Visible", "Validation", "", "");
		spaceMag.goToSpaceFromMySpaceNavigation(space);
		spaceMag.goToSpaceMenu("Wiki");
		addBlankWikiPage(title, content, 0);
		
		/*Step number: 2
		*Step Name: Step 2: Check show Page Permission
		*Step Description: 
			From the list [More], choose the link [Permalink]
		*Input Data: 		
		*Expected Outcome: 
			The pop up "Permalink" is displayedThe button "Manage permission" is displayed*/ 
		goToPermalink();
		waitForAndGetElement(ELEMENT_PERMISSION_MANAGEMENT);
		dialog.closeMessageDialog();
		
		spaceMag.goToAllSpaces();
		spaceMag.deleteSpace(space, 300000);
 	}

	/**
	*<li> Case ID:78475.</li>
	*<li> Test Case Name: Access to space setting by a page creator.</li>
	*<li> Pre-Condition: User B is member in the spaceUser B is creator of the pageUser B isn't an admin in the space</li>
	*<li> Post-Condition: </li>
	*<li> Done with OSs and browsers : </li>
	*<li> Generated by lientm at 2014/05/26 09:53:10</li>
	*/
	@Test
	public void test06_AccessToSpaceSettingByAPageCreator() {
		String space = "space78475";
		String title = "Wiki 78475";
		String content = "Content of wiki 78475";
		String user = "mary";
		String fullName = "Mary";
		
		info("Test 6: Access to space setting by a page creator");
		/*Step Number: 1
		*Step Name: Step 1: Add new space
		*Step Description: 
			- Login with user A ( ex: john)
			- Create new space with name is "test" ( user A is space manager)*/
		/*Step number: 2
		*Step Name: Step 2: Add member into space
		*Step Description: 
			- Go to Settings
			- Choose member tab
			- Add user B ( ex: mary) is member of space
		*Input Data: 	
		*Expected Outcome: 
			Add user successfully*/
		spaceMag.goToAllSpaces();
		spaceMag.addNewSpace(space, "", "Visible", "Validation", "", "");
		spaceMag.goToSpaceFromMySpaceNavigation(space);
		spaceMag.goToSpaceMenu("Space Settings");
		spaceMag.goToMembers();
		member.inviteSingleUser(user, fullName);
		magAcc.signOut();
		
		/*Step number: 3
		*Step Name: Step 3: Login by user B to accept is member
		*Step Description: 
			- Login by mary
			- Click join a space
			- Select space "test" and click accept
		*Input Data: 			
		*Expected Outcome: 
			Mary is member of space*/
		magAcc.signIn(user, DATA_PASS);
		member.acceptInvitation(space);
		
		/*Step number: 4
		*Step Name: Step 4: Access to space setting by member of space
		*Step Description: 
			- Go to Space/Wiki
			- User B, member of the space, create a page in the space
		*Input Data: 
		*Expected Outcome: 
			- A page wiki is created in the space
			- User B has not access to the space setting for the wiki application*/ 
		spaceMag.goToSpaceFromMySpaceNavigation(space);
		spaceMag.goToSpaceMenu("Wiki");
		addBlankWikiPage(title, content, 0);
		click(ELEMENT_BROWSE_LINK);
		waitForElementNotPresent(ELEMENT_WIKI_SETTING_LINK);
		magAcc.signOut();
		
		info("Delete data");
		magAcc.signIn(DATA_USER1,DATA_PASS);
		spaceMag.goToAllSpaces();
		spaceMag.deleteSpace(space, 300000);
 	}

	/**
	*<li> Case ID:78476.</li>
	*<li> Test Case Name: Display ancestor Restricted page in left tree panel.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*<li> Done with OSs and browsers : </li>
	*<li> Generated by lientm at 2014/05/26 09:53:10</li>
	*/
	@Test
	public void test07_DisplayAncestorRestrictedPageInLeftTreePanel() {
		String title = "Wiki 78476";
		String content = "Content of wiki 78476";
		String user = "demo";
		String userGroup[] = {user};
		String subPage = "Sub page of wiki 78476";
		String subContent = "content of sub page 78476";
		By ancestor = By.xpath(ELEMENT_ANCESTOR_RESTRICT.replace("${subPage}", subPage));
		
		info("Test 7: Display ancestor Restricted page in left tree panel");
		/*Step Number: 1
		*Step Name: Step 1: Create a page by user A
		*Step Description: 
			- Connect to Intranet/Wiki with UserA
			- Create a page: Page 1
		*Input Data: 			
		*Expected Outcome: 
			The Page 1 is created*/
		addBlankWikiPage(title, content, 0);
		
		/*Step number: 2
		*Step Name: Step 2: Set permission for Page 1
		*Step Description: 
			- From the list [More], choose [Page Permission]
			- Remove 'any' permission
			- Click [Save]
		*Input Data: 		
		*Expected Outcome: 
			Set permission for page successful*/
		deletePagePermission("any");
		
		/*Step number: 3
		*Step Name: Step 2: Add sub-page
		*Step Description: 
			- Add a sub page: Page 1.1
			- Add View permission to User B
		*Input Data: 		
		*Expected Outcome: 
			The Page 1.1 is created*/
		addBlankWikiPage(subPage, subContent, 0);
		addPagePermission(1, userGroup, 1);
		
		/*Step number: 4
		*Step Name: step 3: Copy permalink
		*Step Description: 
			- Open Page 1.1
			- From the list [More], choose the link [Permalink] copy the link to share
		*Input Data:		
		*Expected Outcome: 
			Copy permalink successfully*/
		goToPermalink();
		String permalink = getPermalink();
		dialog.closeMessageDialog();
		magAcc.signOut();
		
		/*Step number: 5
		*Step Name: Step 3: Check permalink for user B for sub
		-page
		*Step Description: 
			- Connect with User B
			-Paste the link in a Browser
			- Click Enter from the Keyboard
		*Input Data: 		
		*Expected Outcome: 
			- In the left tree panel the page 1 is labelled Restricted in italic. 
			- It has a tooltip : this page is restricted, you don't have permissions to view it. 
			- The restricted ancestors are not clickable.*/ 
		driver.get(permalink);
		magAcc.signIn(user, DATA_PASS);
		waitForTextPresent(subContent);
		waitForAndGetElement(ancestor);
		mouseOverAndClick(ancestor);
		waitForAndGetElement(ELEMENT_ANCESTOR_RESTRICT_TOOLTIP);
		waitForTextNotPresent(content);
		
		info("Delete data");
		goToWikiPage("Wiki Home/" + title, ManageAccount.userType.ADMIN);
		deleteCurrentWikiPage();
 	}

	/**
	*<li> Case ID:78477.</li>
	*<li> Test Case Name: Display descendant Restricted page in left tree panel.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*<li> Done with OSs and browsers : </li>
	*<li> Generated by lientm at 2014/05/26 09:53:10</li>
	*/
	@Test
	public void test08_DisplayDescendantRestrictedPageInLeftTreePanel() {
		String title = "Wiki 78477";
		String content = "Content of wiki 78477";
		String user = "demo";
		String subPage = "Sub page of wiki 78477";
		String subContent = "content of sub page 78477";
		
		info("Test 8: Display descendant Restricted page in left tree panel");
		/*Step Number: 1
		*Step Name: Step 1: Add new page is public
		*Step Description: 
			- Connect to Intranet/Wiki with User A
			- Create a page is public: Page 1
		*Input Data: 		
		*Expected Outcome: 
			The page 1 is public*/
		addBlankWikiPage(title, content, 0);
		makePublicPage();
		
		/*Step number: 2
		*Step Name: Step 2: Add sub
		-page is restricted
		*Step Description: 
			- Add a sub page: Page 1.1
			- From the list [More], choose [Permalink]
			- Click on the button "Restricted"
		*Input Data: 			
		*Expected Outcome: 
			The page 1.1 is restricted*/
		addBlankWikiPage(subPage, subContent, 0);
		makeRestrictedPage();
		
		/*Step number: 3
		*Step Name: Step 3: Copy permalink
		*Step Description: 
			- Copy the link to share
		*Input Data: 		
		*Expected Outcome: 
			Copy link successfully*/
		goToPermalink();
		String permalink = getPermalink();
		dialog.closeMessageDialog();
		magAcc.signOut();
		
		/*Step number: 4
		*Step Name: Step 4: View permalink with user does not have permission
		*Step Description: 
			- Connect with the User B
			- Open the page 1.1
		*Input Data: 			
		*Expected Outcome: 
			- The Page not found message is displayed*/
		driver.get(permalink);
		magAcc.signIn(user, DATA_PASS);
		waitForTextPresent("Page Not Found");
		
		/*Step number: 5
		*Step Name: Step 5: Check on wiki home
		*Step Description: 
			Back to Intranet/Wiki
		*Input Data: 			
		*Expected Outcome: 
			- In the left tree panel, the page 1.1 isn't displayed*/ 
		click(By.linkText("Home page"));
		click(By.linkText(title));
		waitForElementNotPresent(subPage);
		
		info("Delete data");
		goToWikiPage("Wiki Home/" + title, ManageAccount.userType.ADMIN);
		deleteCurrentWikiPage();
 	}

	/**
	*<li> Case ID:78547.</li>
	*<li> Test Case Name: Create a wiki page in a Space.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*<li> Done with OSs and browsers : </li>
	*<li> Generated by lientm at 2014/05/26 09:53:10</li>
	*/
	@Test
	public void test09_CreateAWikiPageInASpace() {
		String space = "space78547";
		String title = "Wiki 78547";
		String content = "Content of wiki 78547";
		
		info("Test 9: Create a wiki page in a Space");
		/*Step Number: 1
		*Step Name: Step1: Add new page for wiki of space
		*Step Description: 
			- Select a space
			- Go to Space/wiki
			- Create a page
		*Input Data: 			
		*Expected Outcome: 
			The page is addedIn the buttom of the page is displayed to "Restricted"*/ 
		spaceMag.goToAllSpaces();
		spaceMag.addNewSpace(space, "", "Visible", "Open", "", "");
		spaceMag.goToSpaceFromMySpaceNavigation(space);
		spaceMag.goToSpaceMenu("Wiki");
		addBlankWikiPage(title, content, 0);
		waitForAndGetElement(ELEMENT_STATUS_IN_INFO_PAGE.replace("${title}", title).replace("${status}", "Restricted"));
		
		spaceMag.goToMySpacePage();
		spaceMag.deleteSpace(space, 120000);
 	}

	/**
	*<li> Case ID:78549.</li>
	*<li> Test Case Name: Permission Indicator is refreshed when changing Public/Restrict.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*<li> Done with OSs and browsers : </li>
	*<li> Generated by lientm at 2014/05/26 09:53:10</li>
	*/
	@Test
	public void test10_PermissionIndicatorIsRefreshedWhenChangingPublicRestrict() {
		String title = "Wiki 78549";
		String content = "Content of wiki 78549";
		
		info("Test 10 Permission Indicator is refreshed when changing Public/Restrict");
		/*Step Number: 1
		*Step Name: - Open [Permalink] Dialog
		*Step Description: 
			- Goto Wiki
			- Create a new wiki page
			- Click [More]
			->[Permalink]
		*Input Data: 			
		*Expected Outcome: 
			[Permalink] dialog is displayed*/
		addBlankWikiPage(title, content, 0);
		boolean[] pageOrSpace = {true, false};
		boolean[] type = {true, false};
		verifyPermissions(pageOrSpace, type, "any", 2);
		
		/*Step number: 2
		*Step Name: - Make the page public/restrict
		*Step Description: 
			- Click on button [Make Public] or [Retstrict] on [Permalink] dialog
		*Input Data: 			
		*Expected Outcome: 
			the Permission Indicator is refreshed accordingly.*/ 
		makePublicPage();
		boolean[] pageOrSpace1 = {true, true};
		boolean[] type1 = {true, true};
		verifyPermissions(pageOrSpace1, type1, "any", 2);
		
		makeRestrictedPage();
		goToPagePermission();
		waitForTextNotPresent("any");
		button.save();
		
		deleteCurrentWikiPage();
 	}
}
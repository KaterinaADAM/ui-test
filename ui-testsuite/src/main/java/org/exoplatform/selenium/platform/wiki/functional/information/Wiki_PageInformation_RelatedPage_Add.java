package org.exoplatform.selenium.platform.wiki.functional.information;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.social.ManageMember;
import org.exoplatform.selenium.platform.wiki.BasicAction;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * 
 * Date: Dec 11, 2012
 * Test cases: Wiki\Info\Related_Page
 */
public class Wiki_PageInformation_RelatedPage_Add extends BasicAction{

	ManageAccount magAc;
	Button button;			
	ManageMember mMember;	

	public String admin = "john";
	public String pass = "gtn";

	@BeforeMethod
	public void beforeMethods(){
		initSeleniumTest();
		driver.get(baseUrl);
		magAc = new ManageAccount(driver);
		button = new Button(driver);
		mMember = new ManageMember(driver, this.plfVersion);
		magAc.signIn(admin, pass);
	}

	@AfterMethod
	public void afterMethods(){
		info("-- Finished: test case --");
		driver.quit();
	}

	/**
	 * Qmetry ID: 118175
	 * Case ID 01
	 *  Add related page 
	 *  Step 1: Create new pages 
	 *  Step 2: Open form to see page's information 
	 *  Step 3: Add related page 
	 */
	@Test
	public void test01_AddRelatedPage(){
		String[][] pageInfo = {{"relatedPage01_1", "relatedPage01_2"}, {"content of page1", "content of page2"}};
		String[][] wikiPath = {{"Wiki Home", "Wiki Home"}, {"Wiki Home/relatedPage01_1", "Wiki Home/relatedPage01_2"}};
		addBlankWikiPageAndRelatePage(2, wikiPath[0], pageInfo, 0, wikiPath[1][0], pageInfo[0][1]);
		deleteWikiPage(wikiPath[1]);
		magAc.signOut();
	}

	/**
	 * Qmetry ID: 118176
	 * Case ID 02
	 * Add related page when user does not have permission to edit this page
	 * Step 1: Create new pages
	 * Step 2: Set permission for page
	 * Step 3: Open form to see page's information
	 * Step 4: Add related page
	 */
	@Test
	public void test02_AddRelatedPageWhenUserDoesNotHavePermissionToEditThisPage(){		
		String[][] pageInfo = {{"relatedPage02"}, {"content of page"}};
		String[][] wikiPath = {{"Wiki Home"}, {"Wiki Home/relatedPage02"}};
		boolean[] editInfo = {true, false, false};
		addBlankWikiPageAndEditPagePermissions(1, wikiPath[0], pageInfo, 0, editInfo, "any", 2);
		goToPageInfo(ManageAccount.userType.PUBLISHER, "Wiki Home/relatedPage02");
		Utils.captureScreen("FNC_KS_WIKI_INFO_CASE_02");
		waitForElementNotPresent(ELEMENT_ADD_MORE_RELATION_BUTTON);
		resetDataByDeleteWikiPage(ManageAccount.userType.ADMIN, wikiPath[1]);
	}

	/**
	 * Qmetry ID: 118177
	 * Case ID 03
	 * Add related page when user does not have permission to view selected page
	 * Step 1: Create new pages
	 * Step 2: Set permission for page
	 * Step 3: Open form to see page's information
	 * Step 4: Add related page
	 */
	@Test
	public void test03_AddRelatedPageWhenUserDoesNotHavePermissionToViewSelectedPage(){
		String[][] pageInfo = {{"relatedPage03_1", "relatedPage03_2"}, {"content of page1", "content of page2"}};
		String[][] wikiPath = {{"Wiki Home", "Wiki Home"}, {"Wiki Home/relatedPage03_1", "Wiki Home/relatedPage03_2"}};
		boolean[] editInfo = {false, false, false};
		addBlankWikiPageAndEditPagePermissions(2, wikiPath[0], pageInfo, 0, editInfo, "any", 2);
		goToWikiPage(wikiPath[1][0]);		
		editPagePermission("any", true, true, false, 2);		
		goToPageInfo(ManageAccount.userType.AUTHOR, wikiPath[1][0]);
		click(ELEMENT_ADD_MORE_RELATION_BUTTON);
		waitForElementNotPresent(By.xpath(ELEMENT_SELECTED_PAGE.replace("${relatedPage}", pageInfo[0][1])));
		button.cancel();
		resetDataByDeleteWikiPage(ManageAccount.userType.ADMIN, wikiPath[1]);
	}


	/**
	 * Case ID:118196
	 * Test Case Name: Add 2 relations from 2 different spaces.
	 * Pre-Condition: User is member of Space 1, Space 2, Space 3

		Wiki of "Space 1" has following pages:
		- Page 1
		- Page 2

		Wiki of "Space 2" has following pages:
		- Page A
		- Page B

		Wiki of "Space 3" has following pages:
		- Page a
		- Page b
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/05/13 17:34:46
	 */
	@Test
	public  void test04_Add2RelationsFrom2DifferentSpaces() {
		info("Test 11: Add 2 relations from 2 different spaces");
		String space1 = "Space118196A";
		String space2 = "Space118196B";
		String space3 = "Space118196C";
		String title1 = "Page 1";
		String title2 = "Page A";
		String title3 = "Page a";
		String wikiPath = "Wiki Home";	

		//Pre-Condition: 
		info("Create all space and page");
		mMember.goToAllSpaces();
		mMember.addNewSpace(space2, "");
		mMember.goToSpaceMenu("Wiki");
		addBlankWikiPage(title2, title2, 0);
		Utils.pause(1000);

		mMember.goToAllSpaces();
		mMember.addNewSpace(space3, "");
		mMember.goToSpaceMenu("Wiki");
		addBlankWikiPage(title3, title3, 0);
		Utils.pause(1000);

		mMember.goToAllSpaces();
		mMember.addNewSpace(space1, "");
		mMember.goToSpaceMenu("Wiki");
		addBlankWikiPage(title1, title1, 0);
		Utils.pause(1000);


		/*Step 1: 
		 * - Go to "Space 1" wiki 
		 *Expected Outcome: - Wiki of "Space 1" is displayed		*/
		/*Step 2: 
		 *Input Data: - Open "Page 1"
		 *Expected Outcome: - Page 1 is displayed in the wiki		*/
		/*Step 3: 
		 *Input Data: 
		 - Open "More" Menu
		 - Select "Page Info"
		 *Expected Outcome: - Page infos of Page 1 are diplayed		*/
		/*Step 4: 
		 *Input Data: - On "Related Page Layout" click on button "Add More Relations"
		 *Expected Outcome: - The popup to select a related page is displayed		*/
		/*Step 5: 
		 *Input Data: - Open Space switcher component
		 *Expected Outcome: - The list of space swticher options is displayed	*/ 
		/*Step 6: 
		 *Input Data: - Select "Space 2"
		 *Expected Outcome: - Wiki tree of "Space 2" is displayed on the container below the space switcher	*/ 
		/*Step 7: 
		 *Input Data: 
		- Select "Page A"
		- Click on "Select" button
		 *Expected Outcome: 
		 - Popup is closed
		 - "Page A" is added as a related pages on page info layout	*/ 
		info("Add Relations with pageA");
		addRelatedPage(wikiPath, title2, space2, true);
		Utils.pause(1000);

		/*Step 8: 
		 *Input Data: - On "Related Page Layout" click on button "Add More Relations"
		 *Expected Outcome: - The popup to select a related page is displayed	*/ 
		/*Step 9: 
		 *Input Data: - Open Space switcher component
		 *Expected Outcome: - The list of space swticher options is displayed	*/ 
		/*Step 10: 
		 *Input Data: - Select "Space 3"
		 *Expected Outcome: - Wiki tree of "Space 3" is displayed on the container below the space switcher	*/ 
		/*Step 11: 
		 *Input Data: 
		- Select "Page a"
		- Click on "Select" button
		 *Expected Outcome: 
		 - Popup is closed
		 - "Page a" is added as a related pages on page info layout
         - "Page A" is still displayed as a related pages on page info layout	*/ 
		info("Add Relations with page a");
		click(ELEMENT_ADD_MORE_RELATION_BUTTON);
		click(ELEMENT_SELECT_SPACE);
		click(ELEMENT_SPACE_NAME_SELECTED.replace("${space}", space3.toLowerCase()));
		click(By.xpath(ELEMENT_SELECTED_PAGE.replace("${relatedPage}", title3)));
		Utils.pause(500);
		click(button.ELEMENT_SELECT_BUTTON);
		Utils.pause(1000);
		waitForAndGetElement(ELEMENT_RELATED_PAGE.replace("${relatedPage}", title3));

		//Delete data test
		mMember.goToAllSpaces();
		mMember.deleteSpace(space1,300000);
		Utils.pause(3000);
		mMember.deleteSpace(space2,300000);	
		Utils.pause(3000);
		mMember.deleteSpace(space3,300000);	
	}

	/**
	 * Case ID:118197
	 * Test Case Name: Add related popup must display the currently browsed space by default.
	 * Pre-Condition: User is member of Space 2
		Space 2 wiki has following pages:
		- Page 1
		- Page 2
		--- Sub-Page 1
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/05/13 17:34:46
	 */
	@Test
	public  void test05_AddRelatedPopupMustDisplayTheCurrentlyBrowsedSpaceByDefault() {
		info("Test 12: Add related popup must display the currently browsed space by default");
		String spaceName = "Space118197";
		String title1 = "Page 1";
		String title2 = "Page 2";
		String wikiPath = "Wiki Home";

		//Pre-Condition: 
		info("Create all space and page");
		mMember.goToAllSpaces();
		mMember.addNewSpace(spaceName, "");
		mMember.goToSpaceMenu("Wiki");
		addBlankWikiPage(title1, title1, 0);
		Utils.pause(1000);
		addBlankWikiPage(title2, title2, 0);
		Utils.pause(1000);

		/*Step 1: - Go into "Space 2" wiki
		 *Expected Outcome: - "Space 2 " wiki is displayed		*/
		/*Step 2: 
		 *Input Data: 
		- Open "More" Menu
		- Select "Page Info"
		 *Expected Outcome: - Page Info of "Wiki Home" page are displayed.		*/
		info("Open Page Info");
		goToPageInfo(null, wikiPath);
		Utils.pause(1000);

		/*Step 3: 
		 *Input Data: - On the part dedicated to related page, click on the button "Add More Relations"
		 *Expected Outcome: 
		 - Popup to select a page is displayed
		 - Label "Select the space:" with the space switcher are displayed */
		info("Click on Add More button");
		click(ELEMENT_ADD_MORE_RELATION_BUTTON);
		waitForAndGetElement(ELEMENT_SELECT_SPACE);

		/*Step 4: 
		 *Input Data: - Check space switcher component
		 *Expected Outcome: - Space switcher component is displaying "Space 2"	*/
		info("Check space default");
		waitForAndGetElement(By.xpath(ELEMENT_RELATED_PAGE_SPACE_DEFAUT.replace("${spaceName}", spaceName)));

		//Delete data test
		mMember.goToAllSpaces();
		mMember.deleteSpace(spaceName,300000);
	}

	/**
	 * Case ID:118198
	 * Test Case Name: Add relation to a page from another space.
	 * Pre-Condition: User is member of Space 1 and Space 2

		Wiki of "Space 1" has following pages:
		- Page 1
		- Page 2

		Wiki of "Space 2" has following pages:
		- Page A
		- Page B
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/05/13 17:34:46
	 */
	@Test
	public  void test06_AddRelationToAPageFromAnotherSpace() {
		info("Test 13: Add relation to a page from another space");
		String space1 = "Space118198A";
		String space2 = "Space118198B";
		String title1 = "Page 1";
		String title2 = "Page A";
		String wikiPath = "Wiki Home";	

		//Pre-Condition: 
		info("Create all space and page");
		mMember.goToAllSpaces();
		mMember.addNewSpace(space2, "");
		mMember.goToSpaceMenu("Wiki");
		addBlankWikiPage(title2, title2, 0);
		Utils.pause(1000);

		mMember.goToAllSpaces();
		mMember.addNewSpace(space1, "");
		mMember.goToSpaceMenu("Wiki");
		addBlankWikiPage(title1, title1, 0);
		Utils.pause(1000);

		/*Step 1: 
		 - Go to "Space 1" wiki 
		 *Expected Outcome: - Wiki of "Space 1" is displayed		*/
		/*Step 2:
		 *Input Data: 
		- Open "Page 1"
		 *Expected Outcome: - Page 1 is displayed in the wiki		*/
		/*Step 3: 
		 *Input Data: 
		- Open "More" Menu
		- Select "Page Info"
		 *Expected Outcome: - Page infos of Page 1 are diplayed	*/
		/*Step 4: 
		 *Input Data: - On "Related Page Layout" click on button "Add More Relations"
		 *Expected Outcome: - The popup to select a related page is displayed	*/
		/*Step 5: 
		 *Input Data: 
		- Open Space switcher component
		 *Expected Outcome: - The list of space swticher options is displayed		*/
		/*Step 6: 
		 *Input Data: 
		- Select "Space 2"
		 *Expected Outcome: - Wiki tree of "Space 2" is displayed on the container below the space switcher		*/
		/*Step 7:
		 *Input Data: 
		- Select "Page A"
		- Click on "Select" button
		 *Expected Outcome: 
		- Popup is closed
		- "Page A" is added as a related pages on page info layout	*/ 
		info("Add Relations with pageA");
		addRelatedPage(wikiPath, title2, space2, true);
		Utils.pause(1000);

		//Delete data test
		mMember.goToAllSpaces();
		mMember.deleteSpace(space1,300000);
		Utils.pause(3000);
		mMember.deleteSpace(space2,300000);	
	}

	/**
	 * Case ID:118199
	 * Test Case Name: Add relation to a page from the same space.
	 * Pre-Condition: 
	 * User is member of Space 1
		Wiki of "Space 1" has following pages:
		- Page 1
		- Page 2
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/05/13 17:34:46
	 */
	@Test
	public  void test07_AddRelationToAPageFromTheSameSpace() {
		info("Test 14 Add relation to a page from the same space");
		String spaceName = "Space118199";
		String title1 = "Page 1";
		String title2 = "Page 2";
		String wikiPath = "Wiki Home";

		//Pre-Condition: 
		info("Create all space and page");
		mMember.goToAllSpaces();
		mMember.addNewSpace(spaceName, "");
		mMember.goToSpaceMenu("Wiki");
		addBlankWikiPage(title1, title1, 0);
		Utils.pause(1000);
		click(By.linkText(wikiPath));
		addBlankWikiPage(title2, title2, 0);
		Utils.pause(1000);

		/*
		- Go to "Space 1" wiki 
		 *Expected Outcome: - Wiki of "Space 1" is displayed		*/
		/*
		- Open "Page 1" 
		 *Input Data: 
		 *Expected Outcome: - Page 1 is displayed in the wiki	*/
		/*- Open "More" Menu
		  - Select "Page Info"
		 *Input Data: 
		 *Expected Outcome: - Page infos of Page 1 are diplayed	*/
		/*
		- On "Related Page Layout" click on button "Add More Relations"
		 *Input Data: 
		 *Expected Outcome: - The popup to select a related page is displayed		*/
		/*
		- Select "Page 2"
		- Click on "Select" button
		 *Input Data: 
		 *Expected Outcome: 
		 - Popup is closed
		 - "Page 2" is added as a related pages on page info layout	*/ 
		info("Open Page Info");
		mouseOverAndClick(ELEMENT_MORE_LINK);
		mouseOverAndClick(ELEMENT_PAGE_INFO_LINK);
		Utils.pause(1000);
		info("Add Relations page");
		click(ELEMENT_ADD_MORE_RELATION_BUTTON);
		click(By.xpath(ELEMENT_SELECTED_PAGE.replace("${relatedPage}", title1)));
		Utils.pause(500);
		click(button.ELEMENT_SELECT_BUTTON);
		Utils.pause(1000);
		waitForAndGetElement(ELEMENT_RELATED_PAGE.replace("${relatedPage}", title1));

		//Delete data test
		mMember.goToAllSpaces();
		mMember.deleteSpace(spaceName,300000);
	}

	/**
	 * Case ID:118200
	 * Test Case Name: User should be able to select the currently browsed space.
	 * Pre-Condition: 
	 * User is member of Space 2		
		Wiki of "Space 2" has following pages:
		- Page 1
		- Page 2
		--- Sub-Page 1
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/05/13 17:34:46
	 */
	@Test
	public  void test08_UserShouldBeAbleToSelectTheCurrentlyBrowsedSpace() {
		info("Test 15: User should be able to select the currently browsed spaceView content of current version while view content of other version");
		String spaceName = "Space118200";
		String title1 = "Page 1";
		String title2 = "Page 2";
		String title3 = "Page 3";
		String wikiPath = "Wiki Home";

		//Pre-Condition: 
		info("Create all space and page");
		mMember.goToAllSpaces();
		mMember.addNewSpace(spaceName, "");
		mMember.goToSpaceMenu("Wiki");
		addBlankWikiPage(title1, title1, 0);
		Utils.pause(1000);
		click(By.linkText(wikiPath));
		addBlankWikiPage(title2, title2, 0);
		Utils.pause(1000);
		click(By.linkText(wikiPath));
		addBlankWikiPage(title3, title3, 0);
		Utils.pause(1000);

		/*
		- Go to "Space 2" wiki
		 *Expected Outcome: - Wiki Space 2 is displayed 	*/
		/*
		- Select "Page 2" the wiki tree
		 *Input Data: 
		 *Expected Outcome: - "Page 2" is displayed		*/
		/*- Open "More" Menu
		- Select "Page Info"
		 *Input Data: 
		 *Expected Outcome: - Page info of "Page 2" are displayed	*/
		info("Open Page Info");
		mouseOverAndClick(ELEMENT_MORE_LINK);
		mouseOverAndClick(ELEMENT_PAGE_INFO_LINK);
		Utils.pause(1000);

		/*
		- On "Related pages" part, click on the button "Add More Relations"
		 *Input Data: 
		 *Expected Outcome: - The popup to select a related page is displayed	*/
		info("Click on Add More button");
		click(ELEMENT_ADD_MORE_RELATION_BUTTON);
		waitForAndGetElement(ELEMENT_SELECT_SPACE);

		/*
		- Open Space switcher component
		 *Input Data: 
		 *Expected Outcome: - Space switcher component is displaying its list of options with "Space 2"		*/
		info("Check space default");
		waitForAndGetElement(By.xpath(ELEMENT_RELATED_PAGE_SPACE_DEFAUT.replace("${spaceName}", spaceName)));

		/*
		- Select "Space 2"
		 *Input Data: 
		 *Expected Outcome:- the page selection part is displaying the wiki tree of "Space 2"	*/ 
		info("The page selection part is displayed");
		click(By.xpath(ELEMENT_RELATED_PAGE_SPACE_DEFAUT.replace("${spaceName}", spaceName)));
		Utils.pause(1000);
		waitForAndGetElement(By.xpath(ELEMENT_SELECTED_PAGE.replace("${relatedPage}", title1)));
		waitForAndGetElement(By.xpath(ELEMENT_SELECTED_PAGE.replace("${relatedPage}", title2)));

		//Delete data test
		mMember.goToAllSpaces();
		mMember.deleteSpace(spaceName,300000);
	}

}
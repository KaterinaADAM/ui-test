package org.exoplatform.selenium.platform.wiki.functional.navigationacrossspace;

import static org.exoplatform.selenium.TestLogger.info;
import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.social.ManageMember;
import org.exoplatform.selenium.platform.social.PeopleProfile;
import org.exoplatform.selenium.platform.social.SpaceManagement;
import org.exoplatform.selenium.platform.wiki.BasicAction;
import org.openqa.selenium.By;
import org.testng.annotations.*;


/**
 * @author hantv
 *
 */
public class Wiki_Navigation_Across_Space extends BasicAction {
	ManageAccount magAcc;
	Button button;
	ManageMember magMember;
	NavigationToolbar naviToolbar;
	PeopleProfile peoPro;
	//Space
	SpaceManagement spaceMag;
	@BeforeMethod
	public void setUpBeforeTest(){
		initSeleniumTest();
		driver.get(baseUrl);
		driver.manage().window().maximize();
		magAcc = new ManageAccount(driver);
		button = new Button(driver);
		magAcc.signIn("john", "gtn");
		spaceMag = new SpaceManagement(driver);
		naviToolbar = new NavigationToolbar(driver, this.plfVersion);
		peoPro = new PeopleProfile(driver, this.plfVersion);
	}

	@AfterMethod
	public void afterTest(){
		driver.manage().deleteAllCookies();
		driver.quit();
	}
	
	/**
	 * Case ID:99102.
	 * Test Case Name: Changing from one space wiki to another should change the currently browsed space.
	 * Pre-Condition: User is member of Space 1 and Space 2
	 * Wiki of Space 2 has :
	- Page 1
	- Page 2
	-
	-
	- Sub
	-Page 3
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by hantv at 2014/03/28 14:34:25
	 */
	@Test
	public  void test01_ChangingFromOneSpaceWikiToAnotherShouldChangeTheCurrentlyBrowsedSpace() {
		info("Test 1: Changing from one space wiki to another should change the currently browsed space");

		String space1 = "space991021";
		String space2 = "space991022";
		String PAGE_NAME1 = "page991021";
		String PAGE_NAME2 = "page991022";
		String PAGE_SUB1 = "page991023";

		spaceMag.goToMySpacePage();
		spaceMag.addNewSpace(space1, "");
		waitForAndGetElement(By.linkText(space1));

		spaceMag.goToMySpacePage();
		spaceMag.addNewSpace(space2, "");
		waitForAndGetElement(By.linkText(space2));
		goToWikiFromSpace(space2);
		addBlankWikiPage(PAGE_NAME1, PAGE_NAME1, 0);
		click(By.linkText("Wiki Home"));
		addBlankWikiPage(PAGE_NAME2, PAGE_NAME2, 0);
		click(By.linkText(PAGE_NAME2));
		addBlankWikiPage(PAGE_SUB1, PAGE_SUB1, 0);
		
		goToWikiFromSpace(space2);
		waitForAndGetElement(ELEMENT_SPACE_NAVIGATION_SPACE_ITEM.replace("${spaceName}", space2));
		click(ELEMENT_SPACE_SWITCHER_BREADCRUMB);
		click(ELEMENT_SPACE_SWITCHER_SELECT.replace("${spaceName}",space1));
		waitForElementNotPresent(ELEMENT_SPACE_NAVIGATION_NAME.replace("${spaceName}", space2));
		waitForAndGetElement(ELEMENT_SPACE_NAVIGATION_NAME.replace("${spaceName}", space1));
		waitForAndGetElement(ELEMENT_TITLE_WIKI_HOME);
		
		/*Clear data*/
		info("Clear data");
		spaceMag.goToAllSpaces();
		spaceMag.deleteSpace(space1,300000);
		spaceMag.deleteSpace(space2,300000);

	}

	/**
	 * Case ID:99103.
	 * Test Case Name: Changing wiki should display the root page of the targeted wiki.
	 * Pre-Condition: User is member of Space 2Wiki of Space 2 has :
	- Page 1
	- Page 2
	-
	-
	- Sub
	-Page 3
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by hantv at 2014/03/28 14:34:25
	 */
	@Test
	public  void test02_ChangingWikiShouldDisplayTheRootPageOfTheTargetedWiki() {
		info("Test 2: Changing wiki should display the root page of the targeted wiki");
		String space1 = "space9910312";
		String space2 = "space9910322";
		String PAGE_NAME1 = "page991031";
		String PAGE_NAME2 = "page991032";
		String PAGE_SUB1 = "page991033";

		spaceMag.goToMySpacePage();
		spaceMag.addNewSpace(space1, "");
		waitForAndGetElement(By.linkText(space1));

		spaceMag.goToMySpacePage();
		spaceMag.addNewSpace(space2, "");
		waitForAndGetElement(By.linkText(space2));
		goToWikiFromSpace(space2);
		addBlankWikiPage(PAGE_NAME1, PAGE_NAME1, 0);
		click(By.linkText("Wiki Home"));
		addBlankWikiPage(PAGE_NAME2, PAGE_NAME2, 0);
		click(By.linkText(PAGE_NAME2));
		addBlankWikiPage(PAGE_SUB1, PAGE_SUB1, 0);

		/*Step Number: 1
		 *Step Name: 
		 *Step Description: 
			- Go into Company wiki
		 *Input Data: 

		 *Expected Outcome: 
			- Company wiki is displayed
			- Space switcher is displayed in the breadcrumb*/
		goToWiki();
		waitForAndGetElement(ELEMENT_SPACE_SWITCHER_BREADCRUMB);

		/*Step number: 2
		 *Step Name: 
		 *Step Description: 
			- Open Space switcher component
		 *Input Data: 

		 *Expected Outcome: 
			- Space switcher is displaying its list of options*/

		/*Step number: 3
		 *Step Name: 
		 *Step Description: 
			- Select "Space 2"
		 *Input Data: 

		 *Expected Outcome: 
			- Wiki of Space 2 is opened on "Wiki Home" page*/ 
		click(ELEMENT_SPACE_SWITCHER_BREADCRUMB);
		click(ELEMENT_SPACE_SWITCHER_SELECT.replace("${spaceName}",space2));
		waitForAndGetElement(ELEMENT_SPACE_NAVIGATION_NAME.replace("${spaceName}", space2));
		waitForAndGetElement(ELEMENT_TITLE_WIKI_HOME);

		/*Clear data*/
		info("Clear data");
		spaceMag.goToAllSpaces();
		spaceMag.deleteSpace(space1,300000);
		spaceMag.deleteSpace(space2,300000);

	}

	/**
	 * Case ID:99104.
	 * Test Case Name: Selecting the same wiki should redirect to the wiki's home.
	 * Pre-Condition: User is member of Space 2
	 * Wiki of Space 2 has :
	- Page 1
	- Page 2
	-
	-
	- Sub
	-Page 3
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by hantv at 2014/03/28 14:34:25
	 */
	@Test
	public  void test03_SelectingTheSameWikiShouldRedirectToTheWikisHome() {
		info("Test 3: Selecting the same wiki should redirect to the wiki's home");
		String space2 = "space991043";
		String PAGE_NAME1 = "page991041";
		String PAGE_NAME2 = "page991042";
		String PAGE_SUB1 = "page991043";

		spaceMag.goToMySpacePage();
		spaceMag.addNewSpace(space2, "");
		waitForAndGetElement(By.linkText(space2));
		goToWikiFromSpace(space2);
		addBlankWikiPage(PAGE_NAME1, PAGE_NAME1, 0);
		click(By.linkText("Wiki Home"));
		addBlankWikiPage(PAGE_NAME2, PAGE_NAME2, 0);
		click(By.linkText(PAGE_NAME2));
		addBlankWikiPage(PAGE_SUB1, PAGE_SUB1, 0);

		/*Step Number: 1
		 *Step Name: 
		 *Step Description: 
			- Go into "Space 2" wiki
		 *Input Data: 

		 *Expected Outcome: 
			- "Space 2" wiki is displayed
			- Space switcher is displayed in the breadcrumb*/
		spaceMag.goToMySpacePage();
		goToWikiFromSpace(space2);

		/*Step number: 2
		 *Step Name: 
		 *Step Description: 
			- Open Space switcher component
		 *Input Data: 

		 *Expected Outcome: 
			- Space switcher is displaying its list of options*/

		/*Step number: 3
		 *Step Name: 
		 *Step Description: 
			- Select "Space 2"
		 *Input Data: 

		 *Expected Outcome: 
			- Wiki of Space 2 is opened on "Wiki Home" page*/ 
		waitForAndGetElement(ELEMENT_SPACE_SWITCHER_BREADCRUMB);
		click(ELEMENT_SPACE_SWITCHER_BREADCRUMB);
		waitForAndGetElement(ELEMENT_SPACE_SWITCHER_SELECT.replace("${spaceName}","Intranet"));
		waitForAndGetElement(ELEMENT_SPACE_SWITCHER_SELECT.replace("${spaceName}",space2));
		click(ELEMENT_SPACE_SWITCHER_SELECT.replace("${spaceName}",space2));
		waitForAndGetElement(ELEMENT_SPACE_NAVIGATION_NAME.replace("${spaceName}", space2));
		waitForAndGetElement(ELEMENT_TITLE_WIKI_HOME);

		/*Clear data*/
		info("Clear data");
		spaceMag.goToAllSpaces();
		spaceMag.deleteSpace(space2,300000);
	}

	/**
	 * Case ID:99105.
	 * Test Case Name: Space switcher is displaying "Intranet" when user is browsing the company wiki.
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by hantv at 2014/03/28 14:34:25
	 */
	@Test
	public  void test04_SpaceSwitcherIsDisplayingIntranetWhenUserIsBrowsingTheCompanyWiki() {
		info("Test 4: Space switcher is displaying Intranet when user is browsing the company wiki");
		/*Step Number: 1
		 *Step Name: 
		 *Step Description: 
			- connect to Intranet
		 *Input Data: 

		 *Expected Outcome: 
			- left sidebar navigation is displayed*/

		/*Step number: 2
		 *Step Name: 
		 *Step Description: 
			- using the left sidebar navigation, go into the company wiki
		 *Input Data: 

		 *Expected Outcome: 
			- wiki's breadcrumb is showing the space switcher component with the label "Intranet"*/ 
		goToWiki();
		waitForAndGetElement(ELEMENT_SPACE_SWITCHER_BREADCRUMB);
		click(ELEMENT_SPACE_SWITCHER_BREADCRUMB);
		waitForAndGetElement(ELEMENT_CURRENT_SPACE_SWITCHER_SELECT.replace("${name}","Intranet"));

	}

	/**
	 * Case ID:99106.
	 * Test Case Name: Space switcher is displaying "My Wiki" for user personal wiki.
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by hantv at 2014/03/28 14:34:25
	 */
	@Test
	public  void test05_SpaceSwitcherIsDisplayingMyWikiForUserPersonalWiki() {
		String user = "Mary Williams";
		String name = "My Wiki";
		info("Test 5: Space switcher is displaying My Wiki for user personal wiki");

		/*Step Number: 1
		 *Step Name: 
		 *Step Description: 
			- connect to Intranet
		 *Input Data: 

		 *Expected Outcome: 
			- Intranet is displaying top navigation bar*/

		/*Step number: 2
		 *Step Name: 
		 *Step Description: 
			- using the top navigation bar, go into user's personal wiki
		 *Input Data: 

		 *Expected Outcome: 
			- My wiki is displayed*/
		naviToolbar.goToConnectionPage();
		peoPro.goToUserProfile(user);
		click(ELEMENT_MY_WIKI_TAB);

		/*Step number: 3
		 *Step Name: 
		 *Step Description: 
			- check display of "My Wiki" on Space Switcher
		 *Input Data: 

		 *Expected Outcome: 
			- Space Switcher component is displayed in the breadcrumb with value "My Wiki"*/ 
		waitForAndGetElement(ELEMENT_SPACE_SWITCHER_BREADCRUMB);
		click(ELEMENT_SPACE_SWITCHER_BREADCRUMB);
		click(By.xpath("//*[@title='My Wiki']"));
		waitForAndGetElement(ELEMENT_CURRENT_SPACE_SWITCHER_SELECT.replace("${name}", name));
		waitForAndGetElement(ELEMENT_TITLE_WIKI_HOME);
	}

	/**
	 * Case ID:99107.
	 * Test Case Name: Space switcher is displaying the name of the space if browsing a space's wiki.
	 * Pre-Condition: User is member of a space named: Mobile
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by hantv at 2014/03/28 14:34:25
	 */
	@Test
	public  void test06_SpaceSwitcherIsDisplayingTheNameOfTheSpaceIfBrowsingASpacesWiki() {
		info("Test 6: Space switcher is displaying the name of the space if browsing a space's wiki");
		String space2 = "Mobile4";

		spaceMag.goToMySpacePage();
		spaceMag.addNewSpace(space2, "");

		/*Step Number: 1
		 *Step Name: 
		 *Step Description: 
			- connect to Intranet
		 *Input Data: 

		 *Expected Outcome: 
			- Intranet is displaying left sidebar navigation*/

		/*Step number: 2
		 *Step Name: 
		 *Step Description: 
			- using the left sidebar navigation, go into the space "Mobile"
		 *Input Data: 

		 *Expected Outcome: 
			- Intranet is displaying space navigation*/
		goToWikiFromSpace(space2);

		/*Step number: 3
		 *Step Name: 
		 *Step Description: 
			- using space navigation, go into wiki application
		 *Input Data: 

		 *Expected Outcome: 
			- Space Switcher component is displayed in the breadcrumb with value "Mobile"*/ 
		waitForAndGetElement(ELEMENT_SPACE_SWITCHER_BREADCRUMB);
		waitForAndGetElement(ELEMENT_CURRENT_SPACE_SWITCHER_SELECT.replace("${name}",space2));
		
		/*Clear data*/
		spaceMag.goToAllSpaces();
		spaceMag.deleteSpace(space2,300000);
	}

	/**
	 * Case ID:99108.
	 * Test Case Name: Switching to "Intranet" from a space wiki should not display space navigation anymore.
	 * Pre-Condition: User is member of "Space 1"
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by hantv at 2014/03/28 14:34:25
	 */
	@Test
	public  void test07_SwitchingToIntranetFromASpaceWikiShouldNotDisplaySpaceNavigationAnymore() {
		info("Test 7: Switching to Intranet from a space wiki should not display space navigation anymore");

		String space1 = "space991087";
		String name = "Intranet";

		spaceMag.goToMySpacePage();
		spaceMag.addNewSpace(space1, "");
		waitForAndGetElement(By.linkText(space1));

		/*Step Number: 1
		 *Step Name: 
		 *Step Description: 
			- Go into to "Space 1"
			- Select wiki application
		 *Input Data: 

		 *Expected Outcome: 
			- Space 1 is displayed
			- Wiki is opened*/
		goToWikiFromSpace(space1);

		/*Step number: 2
		 *Step Name: 
		 *Step Description: 
			- Open the space switcher component
		 *Input Data: 

		 *Expected Outcome: 
			- Space switcher is displaying its list with only:* Intranet* My Wiki* input text* Space 1*/

		/*Step number: 3
		 *Step Name: 
		 *Step Description: 
			- Select "Intranet"
		 *Input Data: 

		 *Expected Outcome: 
			- Intranet wiki is displayed
			- Breadcrumb is displaying "Intranet"
			- Space navigation from "Space 1" is not displayed anymore*/ 
		waitForAndGetElement(ELEMENT_SPACE_SWITCHER_BREADCRUMB);
		click(ELEMENT_SPACE_SWITCHER_BREADCRUMB);
		click(By.xpath("//*[@title='"+name+"']"));
		waitForAndGetElement(ELEMENT_CURRENT_SPACE_SWITCHER_SELECT.replace("${name}", name));
		waitForElementNotPresent(ELEMENT_MY_WIKI_TAB);

		/*Clear data*/
		info("Clear data");
		spaceMag.goToAllSpaces();
		spaceMag.deleteSpace(space1,300000);
	}

	/**
	 * Case ID:99109.
	 * Test Case Name: Switching to "My Wiki" from Company Wiki should display User Navigation.
	 * Pre-Condition: User is member of 0 spaces
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by hantv at 2014/03/28 14:34:25
	 */
	@Test
	public  void test08_SwitchingToMyWikiFromCompanyWikiShouldDisplayUserNavigation() {
		info("Test 8: Switching to My Wiki from Company Wiki should display User Navigation");
		/*Step Number: 1
		 *Step Name: 
		 *Step Description: 
			- Go to "Company" wiki
		 *Input Data: 

		 *Expected Outcome: 
			- Company wiki is displayed
			- Breadcrumb is displaying the space switcher component*/
		goToWiki();
		waitForAndGetElement(ELEMENT_SPACE_SWITCHER_BREADCRUMB);

		/*Step number: 2
		 *Step Name: 
		 *Step Description: 
			- Open the space switcher component
		 *Input Data: 

		 *Expected Outcome: 
			- Space switcher is displaying its list with only:* Intranet* My Wiki*/
		click(ELEMENT_SPACE_SWITCHER_BREADCRUMB);
		waitForAndGetElement(By.xpath("//*[@title='Intranet']"));
		waitForAndGetElement(By.xpath("//*[@title='My Wiki']"));

		/*Step number: 3
		 *Step Name: 
		 *Step Description: 
			- Select "My Wiki"
		 *Input Data: 

		 *Expected Outcome: 
			- Personal wiki is displayed
			- Breacrumb is displaying "My Wiki"
			- User navigation is displayed "My wiki" selected*/ 
		click(By.xpath("//*[@title='My Wiki']"));
		waitForAndGetElement(ELEMENT_MY_WIKI_TAB);
	}

	/**
	 * Case ID:99110.
	 * Test Case Name: Switching to a space wiki from Company wiki should display space navigation.
	 * Pre-Condition: User is member of "Space 1"
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by hantv at 2014/03/28 14:34:25
	 */
	@Test
	public  void test09_SwitchingToASpaceWikiFromCompanyWikiShouldDisplaySpaceNavigation() {
		info("Test 9: Switching to a space wiki from Company wiki should display space navigation");
		String space1 = "space991109";

		spaceMag.goToMySpacePage();
		spaceMag.addNewSpace(space1, "");
		waitForAndGetElement(By.linkText(space1));

		/*Step Number: 1
		 *Step Name: 
		 *Step Description: 
			- Go to "Company" wiki
		 *Input Data: 

		 *Expected Outcome: 
			- Company wiki is displayed
			- Breadcrumb is displaying the space switcher component*/
		goToWiki();
		waitForAndGetElement(ELEMENT_SPACE_SWITCHER_BREADCRUMB);

		/*Step number: 2
		 *Step Name: 
		 *Step Description: 
			- Open the space switcher component
		 *Input Data: 

		 *Expected Outcome: 
			- Space switcher is displaying its list with only:* Intranet* My Wiki* input text* Space 1*/
		click(ELEMENT_SPACE_SWITCHER_BREADCRUMB);
		waitForAndGetElement(By.xpath("//*[@title='Intranet']"));
		waitForAndGetElement(By.xpath("//*[@title='My Wiki']"));
		waitForAndGetElement(ELEMENT_SPACE_SWITCHER_SELECT.replace("${spacename}",space1));

		/*Step number: 3
		 *Step Name: 
		 *Step Description: 
			- Select "Space 1"
		 *Input Data: 

		 *Expected Outcome: 
			- Personal wiki is displayed
			- Breacrumb is displaying "Space 1"
			- Space navigation is displayed ("Wiki" application selected)*/ 
		click(ELEMENT_SPACE_SWITCHER_SELECT.replace("${spacename}",space1));
		waitForAndGetElement(ELEMENT_MY_WIKI_TAB);

		/*Clear data*/
		info("Clear data");
		spaceMag.goToAllSpaces();
		spaceMag.deleteSpace(space1,300000);
	}
}
package org.exoplatform.selenium.platform.forum.functional.forum.category;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.UserGroupManagement;
import org.exoplatform.selenium.platform.ManageAccount.userType;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.forum.ForumBase;
import org.exoplatform.selenium.platform.forum.ForumManageCategory;
import org.exoplatform.selenium.platform.forum.ForumManageForum;
import org.exoplatform.selenium.platform.forum.ForumManagePost;
import org.exoplatform.selenium.platform.forum.ForumManageTopic;
import org.exoplatform.selenium.platform.forum.ForumPermission;
import org.openqa.selenium.By;
import org.testng.annotations.*;

/**
 * @author khanhnt
 * Update chinhdtt
 * Update 4.2 : 04/2015
 */
public class Forum_Forum_Category_Add extends ForumBase {

	ManageAccount acc;
	ForumManageCategory fmCat;
	ForumManageForum fmForum;
	ForumPermission frumPer;
	ForumManageTopic topic; 
	ForumManagePost post; 
	NavigationToolbar nav; 
	UserGroupManagement uGroup; 

	@BeforeMethod
	public void setUpBeforeTest() {
		initSeleniumTest();
		driver.get(baseUrl);
		fmCat = new ForumManageCategory(driver);
		acc = new ManageAccount(driver);
		frumPer = new ForumPermission(driver);
		acc.signIn(DATA_USER1, DATA_PASS);
		button = new Button(driver);
		fmForum = new ForumManageForum(driver, this.plfVersion);
		topic = new ForumManageTopic(driver, this.plfVersion);
		post = new ForumManagePost(driver, this.plfVersion);
		nav = new NavigationToolbar(driver, this.plfVersion);
		uGroup = new UserGroupManagement(driver, this.plfVersion);
	}

	@AfterMethod
	public void afterTest() {
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	/**
	 * Case ID: 109042. 
	 * Test Case Name: Check [Add Category] permission.
	 * Created by khanhnt at 2013/11/20 09:34:03
	 */
	@Test
	public void test01_CheckAddANewCategoryPermission() {
		info("Test 01 Check add a new category permission");
		String catName1 = "Category 109042A";
		String catName2 = "Category 109042B";
		String order = "0";
		int chooseRestricted = 1;
		String[] restricted = { "" };
		String description = "Description";
		int setPermission = 0;
		String[] userGroup = {"james"};
		boolean permission = true;

		/*
		- Login as a normal user: [demo] or [mary].
		- Go to Forum application.
		- Try to add a new category into the Forum application.
		 *Expected Outcome: 
		- [Add Category] button is NOT available on the main action bar.
		- [Add Category] function is NOT available for a normal user.		*/		
		info("Test 01 Check with normal user");
		acc.userSignIn(userType.PUBLISHER);
		goToForums();
		waitForElementNotPresent(ELEMENT_ADD_CATEGORY);

		/*
		- Login as administrator user: [john] or [root].
		- Go to Forum application.
		- Try to add a new category into the Forum application: + Click [Add Category] button in main action bar. + Enter the valid data in all required fields. + Click [Save] button.
		 *Input Data: 
		 *Expected Outcome: 
		- [Add Category] button is available on the main action bar.
		- New category is added successfully.		*/
		info("Login as admin to add a category");
		acc.userSignIn(userType.ADMIN);
		goToForums();
		fmCat.addNewCategoryInForum(catName1, order, 5,
				restricted, description, setPermission, null, false);

		/*
		- Login as administrator user: [john] or [root].
		- Go to Forum application.
		- Add a new category following step2 with moderator added for category (Moderator of category is not administrator): Exp [james].
		- Login as moderator user of category: [james].
		- Go to Forum Application.
		- Try to add a new category in Forum Application by moderator.
		 *Input Data: 
		 *Expected Outcome: 
		- Category is added by administrator, and moderator of category is [james].
		- [Add Category] button is NOT available on the main action bar of Forum Application when login by moderator [james].
		- [Add Category] function is NOT available for a moderator but not administrator.		*/ 
		info("Add new category2");
		fmCat.addNewCategoryInForum(catName2, order, chooseRestricted,
				restricted, description, setPermission, userGroup, permission);
		info("Test 01 Check with moderator");		
		acc.userSignIn(userType.AUTHOR);
		goToForums();
		waitForElementNotPresent(ELEMENT_ADD_CATEGORY);
		waitForAndGetElement(By.linkText(catName2));		

		//Delete data test
		acc.userSignIn(userType.ADMIN);
		goToForums();
		click(By.linkText(catName1));
		fmCat.deleteCategoryInForum(catName1, true);
		Utils.pause(1000);
		click(By.linkText(catName2));
		fmCat.deleteCategoryInForum(catName2, true);
	}

	/**
	 * Case ID:109078. 
	 * Test Case Name: Add category with blank entry for Restricted Audience
	 * audience. Created by khanhnt at 2013/11/20 09:34:03
	 */
	@Test
	public void test02_AddANewCategoryWithBlankRestrictedAudience() {
		info("Test 2: Add a new category with blank Restricted audience");
		String catName = "Category 109078";
		String order = "0";
		int chooseRestricted = 5;
		String[] restricted = { "" };
		String description = "Description";
		int setPermission = 0;
		String[] userGroup = null;
		boolean permission = false;

		/*
		- Login as Administrator user.
		- Go to Forum Application.
		- Click on [Add Category] button in main toolbar.
		 *Expected Outcome: 
		- [Category] form is shown properly with below fields:+ [Category] tab: Title, Order, Restricted Audience, Description. + [Permissions] tab: There are the buttons to add permissions for Users, Roles, User Groups.		*/
		/*
		- In [Category] tab, keep [Restricted audience] field blank.
		- Input valid values for other fields.
		- Click [Save] button.
		 *Input Data: 
		 *Expected Outcome: 
		- New Category is added successfully.
		 */
		goToForums();
		fmCat.addNewCategoryInForum(catName, order, chooseRestricted,
				restricted, description, setPermission, userGroup, permission);

		/*
		- Log-out Administrator user, then log-in by normal user [demo]. 
		 *Input Data: 
		 *Expected Outcome: 
		- User [demo] can access the category created by Administrator user in step2.		*/ 
		info("Nomal user login to check");
		fmCat.checkRightOfViewCategory(DATA_USER2, DATA_PASS, catName, description, true);

		//Delete data test
		acc.userSignIn(userType.ADMIN);
		goToForums();
		click(By.linkText(catName));
		fmCat.deleteCategoryInForum(catName);
	}

	/**
	 * Case ID:109082. 
	 * Test Case Name: Add category with invalid data entry for Restricted Audience entry for the Restricted audience field. 
	 * Created by khanhnt at 2013/11/20
	 * 09:34:03
	 */
	@Test
	public void test03_AddANewCategoryInCaseInvalidDataEntryForTheRestrictedAudienceField() {
		info("Test 3: Add a new category in case invalid data entry for the Restricted audience field");
		String catName = "Category 109082";
		String order = "0";
		int chooseRestricted = 1;
		String[] restrictedUser = {"invaliduser"};
		String[] restrictedGroup = {"/invalidGroup"};
		String[] restrictedRole = {"invalidRole:/developers"};
		String description = "Description";
		int setPermission = 0;
		String[] userGroup = null;
		boolean permission = false;

		/*
		- Login as Administrator
		- Go to Forum Application
		- Click on [Add Category] in main toolbar
		 *Expected Outcome: 
		- Add new Category form is shown properly		*/
		/*
		- Enter invalid (unavailable) user(s)/role(s)/group(s) into[Restricted Audience] field.
		 *Input Data: 
		 *Expected Outcome: 
		- User(s)/Role(s)/Group(s) are input into[Restricted Audience] field.		*/
		/*
		- Input valid value for other required fields.
		- Click [Save] button.
		 *Input Data: 
		 *Expected Outcome: 
		- Alert message is shown in order to notify about invalid user/Role/Group for [Restricted Audience] field.		*/ 
		goToForums();
		info("Add invalid user");
		fmCat.goToAddCategory();
		fmCat.inputDataCategoryInForum(catName, order, chooseRestricted,
				restrictedUser, description, setPermission, userGroup, permission);
		button.save();
		waitForMessage(fmCat.MESSAGE_RESTRICTED_AUDIENCE_INVALID+restrictedUser[0]+".");
		click(ELEMENT_OK_INFOR_POPUP);

		info("Add invalid group");
		fmCat.inputDataCategoryInForum(catName, order, chooseRestricted,
				restrictedGroup, description, setPermission, userGroup, permission);
		button.save();
		waitForMessage(fmCat.MESSAGE_RESTRICTED_AUDIENCE_INVALID+restrictedGroup[0]+".");
		click(ELEMENT_OK_INFOR_POPUP);

		info("Add invalid role");
		fmCat.inputDataCategoryInForum(catName, order, chooseRestricted,
				restrictedRole, description, setPermission, userGroup, permission);
		button.save();
		waitForMessage(fmCat.MESSAGE_RESTRICTED_AUDIENCE_INVALID+restrictedRole[0]+".");
		click(ELEMENT_OK_INFOR_POPUP);
	}

	/**
	 * Case ID:109086. 
	 * Test Case Name: Add category with valid user entry for Restricted Audience entry for the Restricted audience field. 
	 * Created by khanhnt at 2013/11/20 09:34:03
	 */
	@Test
	public void test04_AddANewCategoryInCaseValidUserEntryForTheRestrictedAudienceField() {
		info("Test 4: Add a new category in case valid user entry for the Restricted audience field");
		String catName = "Category 109086";
		String order = "0";
		int chooseRestricted = 1;
		String[] restricted = { "mary" };
		String description = "Description";
		int setPermission = 0;
		String[] userGroup = null;
		boolean permission = false;

		/*
		- Login as Administrator user.
		- Go to Forum Application.
		- Click on [Add Category] button in main toolbar.
		 *Expected Outcome: 
		- [Category] form is shown properly.		*/
		/*
		- Enter valid user(s) for [Restricted Audience] field; Or click on [Select User] button to select user(s) in list.
		 *Input Data: 
		 *Expected Outcome: 
		- The user(s) is input into[Restricted Audience] field.		*/
		/*
		- Input valid value for other required fields
		- Click [Save] button.
		 *Input Data: 
		 *Expected Outcome: 
		- Category is added successfully.
		- Only user(s) who is in list assigned at step 2 can see this category.		*/
		goToForums();
		fmCat.addNewCategoryInForum(catName, order, chooseRestricted,
				restricted, description, setPermission, userGroup, permission);
		goToForumHome();
		waitForAndGetElement(By.linkText(catName));

		/*
		- Login by user assign at step 2
		- Select category above
		 *Input Data: 
		 *Expected Outcome: This user can access category created.		*/
		info("User Mary login to check");
		fmCat.checkRightOfViewCategory(DATA_USER2, DATA_PASS, catName, description, true);

		/*
		- Login by user that is NOT assigned in step2.
		- Go to Forum Application.
		 *Input Data: 
		 *Expected Outcome: 
		- User can NOT see this category in list.		*/ 
		info("User Demo login to check");
		fmCat.checkRightOfViewCategory("demo", DATA_PASS, catName, description, false);

		//Delete data test
		acc.userSignIn(userType.ADMIN);
		goToForums();
		click(By.linkText(catName));
		fmCat.deleteCategoryInForum(catName, true);
	}

	/**
	 * Case ID:109089. 
	 * Test Case Name: Add category with valid role entry for Restricted Audience entry for the Restricted audience field. 
	 * Created by khanhnt at 2013/11/20 09:34:03
	 */
	@Test
	public void test05_AddANewCategoryInCaseValidRoleEntryForTheRestrictedAudienceField() {
		info("Test 5: Add a new category in case valid role entry for the Restricted audience field");
		String catName = "Category 109089";
		String order = "0";
		int chooseRestricted = 4;
		String[] restricted = {"Development","*"};
		String description = "Description";
		int setPermission = 0;
		String[] userGroup = null;
		boolean permission = false;

		/*
		- Login as Administrator user.
		- Go to Forum Application.
		- Click on [Add Category] in main toolbar.
		 *Expected Outcome: 
		- [Category] form is shown properly.		*/
		/*
		- Enter valid role(s) into[Restricted Audience] field; Or click on [Select Role] button to select the role(s) in list.
		 *Input Data: 
		 *Expected Outcome: 
		- The role(s) is input into [Restricted Audience] field.		*/
		/*
		- Input valid value for other required fields.
		- Click [Save] button.
		 *Input Data: 
		 *Expected Outcome: 
		- Category is added successfully.	*/
		goToForums();
		fmCat.addNewCategoryInForum(catName, order, chooseRestricted,
				restricted, description, setPermission, userGroup, permission);
		goToForumHome();
		waitForAndGetElement(By.linkText(catName));

		/*
		- Login by user that has the role assigned in step2.
		- Go to Forum Application, access category created above.
		 *Input Data: 
		 *Expected Outcome: 
		- User can see and access this category.		*/
		fmCat.checkRightOfViewCategory("demo", DATA_PASS, catName, description, true);

		/*
		- Login by user that does NOT have the role assigned in step2.
		- Go to Forum Application to view category created above.
		 *Input Data: 
		 *Expected Outcome: 
		- User can NOT see this category in list.		*/ 
		fmCat.checkRightOfViewCategory(DATA_USER2, DATA_PASS, catName, description, false);

		//Delete data test
		acc.userSignIn(userType.ADMIN);
		goToForums();
		click(By.linkText(catName));
		fmCat.deleteCategoryInForum(catName, true);
	}

	/**
	 * Case ID:109092. 
	 * Test Case Name: Add category with valid group entry for Restricted Audience
	 * entry for the Restricted audience field. Created by khanhnt at 2013/11/20
	 * 09:34:03
	 */
	@Test
	public void test06_AddANewCategoryInCaseValidGroupEntryForTheRestrictedAudienceField() {
		info("Test 6: Add a new category in case valid group entry for the Restricted audience field");
		String catName = "Category 109092";
		String order = "0";
		int chooseRestricted = 1;
		String[] restricted = {"/developers"};
		String description = "Description";
		int setPermission = 0;
		String[] userGroup = null;
		boolean permission = false;

		/*
		- Login as Administrator user.
		- Go to Forum Application.
		- Click on [Add Category] button in main toolbar.
		 *Expected Outcome: 
		- [Category] form is shown properly.		*/
		/*
		- Enter valid group(s) into [Restricted Audience] field; Or click on [Select Group] button to select groups in list.
		 *Input Data: 
		 *Expected Outcome: 
		- The group(s) is input into [Restricted Audience] field.		*/
		/*
		- Input valid value for other required fields.
		- Click [Save] button.
		 *Input Data: 
		 *Expected Outcome: 
		- Category is added successfully.
		- Only user(s) who belongs to the group that is listed at step 2 can see this category.		*/
		goToForums();
		fmCat.addNewCategoryInForum(catName, order, chooseRestricted,
				restricted, description, setPermission, userGroup, permission);
		goToForumHome();
		waitForAndGetElement(By.linkText(catName));

		/*
		- Login by user who is in group assigned in step2.
		- Go to Forum Application and access Category created above.
		 *Input Data: 
		 *Expected Outcome: 
		- User can see this category in list and can access it.		*/
		fmCat.checkRightOfViewCategory("demo", DATA_PASS, catName, description, true);

		/*
		- Login by user who is NOT in group assigned in step2.
		- Go to Forum Application.
		 *Input Data: 
		 *Expected Outcome: 
		- User can NOT see this category in list.		*/ 
		fmCat.checkRightOfViewCategory(DATA_USER2, DATA_PASS, catName, description, false);

		//Delete data test
		acc.userSignIn(userType.ADMIN);
		goToForums();
		click(By.linkText(catName));
		fmCat.deleteCategoryInForum(catName, true);
	}

	/**
	 * Case ID:109095. 
	 * Test Case Name: Add category with valid user, role and group entry for Restricted Audience at once. 
	 * Created by khanhnt at 2013/11/20 09:34:03
	 */
	@Test
	public void test07_AddANewCategoryInCaseValidUserRoleAndGroupEntryForRestrictedAudienceFieldAtOnce() {
		info("Test 7: Add a new category in case valid user, role and group entry for Restricted audience field at once");
		String catName = "Category" + getRandomNumber();
		String order = "0";
		int chooseRestricted = 1;
		String[] restricted = {"james, manager:/platform/web-contributors, /developers" };
		String description = "Description";
		int setPermission = 0;
		String[] userGroup = null;
		boolean permission = false;
		String username = getRandomString();
		String password = "gtngtn";
		String firstName = "Test";
		String lastName = "AAAA";
		String email1 = username+"@exoplatform.com";

		/*
		- Login as Administrator user.
		- Go to Forum Application.
		- Click on [Add Category] button in main toolbar.
		 *Expected Outcome: 
		- [Category] form is shown properly.		*/
		/*
		- Enter valid (available) user(s), role(s) and group(s) into[Restricted Audience] field;Or click on [Select User], [Select Role], [Select Group] buttons to select users, roles, groups in lists.
		 *Input Data: 
		 *Expected Outcome: 
		- User(s), role(s) and group(s) are input into [Restricted Audience] field.		*/
		/*
		- Input valid data into all required fields.
		- Click [Save] button.
		 *Input Data: 
		 *Expected Outcome: 
		- Category is added successfully.
		- Only user(s) who is in list assigned at step 2 can see this category.		*/ 
		goToForums();
		fmCat.addNewCategoryInForum(catName, order, chooseRestricted,
				restricted, description, setPermission, userGroup, permission);
		goToForumHome();
		waitForAndGetElement(By.linkText(catName));

		info("User James login to check");
		fmCat.checkRightOfViewCategory("james", DATA_PASS, catName, description, true);

		info("User mary login to check role");		
		fmCat.checkRightOfViewCategory(DATA_USER2, DATA_PASS, catName, description, true);

		info("User demo login to check group");
		fmCat.checkRightOfViewCategory("demo", DATA_PASS, catName, description, true);

		info("Create new account to check");
		acc.userSignIn(userType.ADMIN);
		nav.goToNewStaff();
		acc.addNewUserAccount(username, password, password, firstName, lastName, "", email1, null, null, true);
		fmCat.checkRightOfViewCategory(username, password, catName, description, false);

		//Delete data test	
		acc.userSignIn(userType.ADMIN);
		goToForums();
		click(By.linkText(catName));
		fmCat.deleteCategoryInForum(catName, true);
		info("Delete users test");
		nav.goToUsersAndGroupsManagement();
		uGroup.deleteUser(username);
	}	

	/**
	 * Case ID:109098.
	 * Test Case Name: Add category with valid user entry for Moderator field.
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/06/05 17:37:04
	 */
	@Test
	public  void test08_AddCategoryWithValidUserEntryForModeratorField() {
		info("Test 08 Add category with valid user entry for Moderator field");
		String catName = "Category" + getRandomNumber();
		String order = "1";
		int chooseRestricted = 5;
		String[] restricted = {""}; 
		String description = "Description of Category";
		String[] user = {"james"};
		String[] userRole = {"manager:/platform/web-contributors"}; 
		String[] userGroup = {"/developers"}; 
		String[] addForum = {"Title of forum 001", "1", "Open", "Unlocked", "Description of forum 001"}; 	
		String title1 = "Title topic 001"; 
		String message1 = "Topic 001"; 
		String title2 = "Title topic 002"; 
		String message2 = "Topic 002"; 
		String title3 = "Title topic 003"; 
		String message3 = "Topic 003"; 
		String postTitle1 = "Post 001";
		String postTitle2 = "Post 002";
		String postTitle3 = "Post 003";
		String username = getRandomString();
		String password = "gtngtn";
		String firstName = "Test";
		String lastName = "BBBB";
		String email1 = username+"@exoplatform.com";

		/*
		- Login as Administrator user.
		- Go to Forum Application.
		- Click on [Add Category] button in main toolbar.
		 *Expected Outcome: 
		- [Category] form is shown properly.		*/
		/*
		- Select [Permissions] tab
		- Input/select valid User(s)/Role(s)/Group(s) to add for Moderator(s) field.
		 *Input Data: 
		 *Expected Outcome: 
		- User(s)/Role(s)/Group(s) is added and displayed in 'Moderators' field.
		- You can change value in 'Moderators' field by tick/untick the checkboxes.		*/
		/*
		- Input valid value for other required fields.
		- Click [Save] button.
		 *Input Data: 
		 *Expected Outcome: 
		- New Category is added
		- Selected User(s)/Role(s)/ Group(s) is displayed in Moderator field of added category.
		- Selected User(s)/Role(s)/ Group(s) in Moderator field will be moderator(s) of all forums of this category 		*/ 
		goToForums();
		info("Create New Category");
		fmCat.goToAddCategory(); 
		fmCat.inputTitleOrderDescriptionCategory(catName, order, description);
		fmCat.selectRestricted(chooseRestricted, restricted);
		info("Set permission for category");
		frumPer.configPermission4ForumCategory(2, user, true);
		frumPer.configPermission4ForumCategory(1, userRole, true);
		frumPer.configPermission4ForumCategory(1, userGroup, true);
		button.save();

		info("Create New Forum");
		fmForum.goToAddForum(); 
		fmForum.inputDataInAddForumTab_addForum(catName, addForum); 
		button.save(); 

		// Selected User(s)/Role(s)/ Group(s) in Moderator field will be moderator(s) of all forums of this category 
		info("Verify user User(s)/Role(s)/ Group(s)");
		info("James login to check");
		acc.userSignIn(userType.AUTHOR);
		goToForums();
		click(By.linkText(catName));
		click(By.linkText(addForum[0])); 
		topic.quickStartTopic(title1, message1); 
		Utils.pause(1000);
		click(By.linkText(title1));
		post.postReply(postTitle1, postTitle1, "", "");
		Utils.pause(1000);

		info("Mary login to check role");
		acc.userSignIn(userType.PUBLISHER);
		goToForums();
		click(By.linkText(catName));
		click(By.linkText(addForum[0])); 
		topic.quickStartTopic(title2, message2); 
		Utils.pause(1000);
		click(By.linkText(title2));
		post.postReply(postTitle2, postTitle2, "", "");
		Utils.pause(1000);

		info("Demo login to check Group");
		acc.userSignIn(userType.DEVELOPER);
		goToForums();
		click(By.linkText(catName));
		click(By.linkText(addForum[0])); 
		topic.quickStartTopic(title3, message3); 
		Utils.pause(1000);
		click(By.linkText(title3));
		post.postReply(postTitle3, postTitle3, "", "");
		Utils.pause(1000);

		info("Create new account to check");
		acc.userSignIn(userType.ADMIN);
		nav.goToNewStaff();
		acc.addNewUserAccount(username, password, password, firstName, lastName, "", email1, null, null, true);
		acc.signOut();
		acc.signIn(username, password);
		goToForums();
		click(By.linkText(catName));
		click(By.linkText(addForum[0]));
		waitForElementNotPresent(topic.ELEMENT_START_TOPIC_BUTTON);

		//Delete data test
		acc.userSignIn(userType.ADMIN);
		goToForums();
		click(By.linkText(catName));
		fmCat.deleteCategoryInForum(catName, true);		
		info("Delete users test");
		nav.goToUsersAndGroupsManagement();
		uGroup.deleteUser(username);
	}

	/**
	 * Case ID:73127 | 109099.
	 * Test Case Name: Assign [Permission to start topics] for valid user when adding new category.
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/06/04 18:16:39
	 */
	@Test
	public  void test09_AssignPermissionToStartTopicsForValidUserWhenAddingNewCategory() {
		info("Test 09 Assign [Permission to start topics] for valid user when adding new category");
		String catName = "Category 109099";
		String order = "1";
		int chooseRestricted = 5;
		String[] restricted = {""}; 
		String description = "Description of Category";
		String[] user = {"james"};
		String[] userRole = {"manager:/platform/web-contributors"}; 
		String[] userGroup = {"/developers"}; 
		String[] addForum = {"Title of forum 001", "1", "Open", "Unlocked", "Description of forum 001"}; 	
		String title1 = "Title topic 001"; 
		String message1 = "Topic 001"; 
		String title2 = "Title topic 002"; 
		String message2 = "Topic 002"; 
		String title3 = "Title topic 003"; 
		String message3 = "Topic 003"; 
		String username = getRandomString();
		String password = "gtngtn";
		String firstName = "Test";
		String lastName = "CCCC";
		String email1 = username+"@exoplatform.com";

		/*
		- Login as Administrator user.
		- Go to Forum Application.
		- Click on [Add Category] button in main toolbar.
		 *Expected Outcome: 
		- [Category] form is shown properly.		*/
		/*
		- Select [Permissions] tab
		- Input/select valid User(s)/Role(s)/Group(s)and the click [Add] button.
		- Tick the checkbox [Permission to start topics] = TRUE, corresponding to above [User(s)/Role(s)/Group(s)].
		 *Input Data: 
		 *Expected Outcome: 
		- User(s)/Role(s)/Group(s) is displayed in Permissions table. By default, all checkboxes are marked as TRUE.
		- Checkboxes are enable for changing value manually.
		- [Permission to start topics] checkbox corresponding to selected [User(s)/Role(s)/Group(s)] is displayed with TRUE value.		*/
		/*
		- Input valid value for other required fields.
		- Click [Save] button.
		 *Input Data: 
		 *Expected Outcome: 
		- New Category is added
		-[Permission to start topics] checkbox corresponding to selected [User(s)/Role(s)/Group(s)] is displayed with TRUE value.
		- Selected User(s)/Role(s)/Group(s) will has right to create topic in forums of added category.		*/ 
		goToForums();
		info("Create New Category");
		fmCat.goToAddCategory(); 
		fmCat.inputTitleOrderDescriptionCategory(catName, order, description);
		fmCat.selectRestricted(chooseRestricted, restricted);
		info("Set permission for category");
		frumPer.configPermission4ForumCategory(2, user, false, true, false, false);
		frumPer.configPermission4ForumCategory(1, userRole, false, true, false, false);
		frumPer.configPermission4ForumCategory(1, userGroup, false, true, false, false);
		button.save();

		info("Create New Forum");
		fmForum.goToAddForum(); 
		fmForum.inputDataInAddForumTab_addForum(catName, addForum); 
		button.save(); 

		//- Selected User(s)/Role(s)/Group(s) will has right to create topic in forums of added category.	
		info(" User(s)/Role(s)/Group(s) login to check");
		info("James login to check");
		acc.userSignIn(userType.AUTHOR);
		goToForums();
		click(By.linkText(catName));
		click(By.linkText(addForum[0])); 
		topic.quickStartTopic(title1, message1); 
		Utils.pause(1000);

		info("Mary login to check role");
		acc.userSignIn(userType.PUBLISHER);
		goToForums();
		click(By.linkText(catName));
		click(By.linkText(addForum[0])); 
		topic.quickStartTopic(title2, message2); 
		Utils.pause(1000);

		info("Demo login to check Group");
		acc.userSignIn(userType.DEVELOPER);
		goToForums();
		click(By.linkText(catName));
		click(By.linkText(addForum[0])); 
		topic.quickStartTopic(title3, message3); 
		Utils.pause(1000);

		info("Create new account to check");
		acc.userSignIn(userType.ADMIN);
		nav.goToNewStaff();
		acc.addNewUserAccount(username, password, password, firstName, lastName, "", email1, null, null, true);
		acc.signOut();
		acc.signIn(username, password);
		goToForums();
		click(By.linkText(catName));
		click(By.linkText(addForum[0]));
		waitForElementNotPresent(topic.ELEMENT_START_TOPIC_BUTTON);

		//Delete data test
		acc.userSignIn(userType.ADMIN);
		goToForums();
		click(By.linkText(catName));
		fmCat.deleteCategoryInForum(catName, true);		
		info("Delete users test");
		nav.goToUsersAndGroupsManagement();
		uGroup.deleteUser(username);			
	}

	/**
	 * Case ID:109100.
	 * Test Case Name: Assign [Permission to post replies] for valid user when adding new category.
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/06/04 18:16:39
	 */
	@Test
	public  void test10_AssignPermissionToPostRepliesForValidUserWhenAddingNewCategory() {
		info("Test 10 Assign [Permission to post replies] for valid user when adding new category");
		String catName = "Category 109100";
		String order = "1";
		int chooseRestricted = 5;
		String[] restricted = {""}; 
		String description = "Description of Category";
		String[] user = {"james"};
		String[] userRole = {"manager:/platform/web-contributors"}; 
		String[] userGroup = {"/developers"}; 
		String[] addForum = {"Title of forum 001", "1", "Open", "Unlocked", "Description of forum 001"}; 	
		String title = "Title topic 001"; 
		String message = "Topic 001"; 
		String postTitle1 = "Post 001";
		String postTitle2 = "Post 002";
		String postTitle3 = "Post 003";
		String username = getRandomString();
		String password = "gtngtn";
		String firstName = "Test";
		String lastName = "DDDD";
		String email1 = username+"@exoplatform.com";		

		/*
		- Login as Administrator user.
		- Go to Forum Application.
		- Click on [Add Category] button in main toolbar.
		 *Expected Outcome: 
		- [Category] form is shown properly.		*/
		/*
		- Select [Permissions] tab
		- Input/select valid User(s)/Role(s)/Group(s)and the click [Add] button.
		- Tick the checkbox [Permission to post replies] = TRUE, corresponding to above [User(s)/Role(s)/Group(s)].
		 *Input Data: 
		 *Expected Outcome: 
		- User(s)/Role(s)/Group(s) is displayed in Permissions table. By default, all checkboxes are marked as TRUE.
		- Checkboxes are enable for changing value manually.
		- [Permission to post replies] checkbox corresponding to selected [User(s)/Role(s)/Group(s)] is displayed with TRUE value.		*/
		/*
		- Input valid value for other required fields.
		- Click [Save] button.
		 *Input Data: 
		 *Expected Outcome: 
		- New Category is added
		-[Permission to post replies] checkbox corresponding to selected [User(s)/Role(s)/Group(s)] is displayed with TRUE value.
		- Selected User(s)/Role(s)/Group(s) will has right to post replies in all topics in forums of added category.		*/ 
		goToForums();
		info("Create New Category");
		fmCat.goToAddCategory(); 
		fmCat.inputTitleOrderDescriptionCategory(catName, order, description);
		fmCat.selectRestricted(chooseRestricted, restricted);
		info("Set permission for category");
		frumPer.configPermission4ForumCategory(2, user, false, false, true, false);
		frumPer.configPermission4ForumCategory(1, userRole, false, false, true, false);
		frumPer.configPermission4ForumCategory(1, userGroup, false, false, true, false);
		button.save();

		info("Create New Forum");
		fmForum.goToAddForum(); 
		fmForum.inputDataInAddForumTab_addForum(catName, addForum); 
		button.save(); 

		info("Create topic");
		topic.quickStartTopic(title, message); 
		Utils.pause(1000);

		//-Selected User(s)/Role(s)/Group(s) will has right to post replies in all topics in forums of added category.
		info("User(s)/Role(s)/Group(s) login to verify");
		info("Verify user James");
		acc.userSignIn(userType.AUTHOR);
		goToForums();
		click(By.linkText(catName));
		click(By.linkText(addForum[0])); 
		click(By.linkText(title));
		post.postReply(postTitle1, postTitle1, "", "");
		Utils.pause(1000);

		info("Mary login to check Role");
		acc.userSignIn(userType.PUBLISHER);
		goToForums();
		click(By.linkText(catName));
		click(By.linkText(addForum[0])); 
		click(By.linkText(title));
		post.postReply(postTitle2, postTitle2, "", "");
		Utils.pause(1000);

		info("Verify user Group");
		acc.userSignIn(userType.DEVELOPER);
		goToForums();
		click(By.linkText(catName));
		click(By.linkText(addForum[0])); 
		click(By.linkText(title));
		post.postReply(postTitle3, postTitle3, "", "");
		Utils.pause(1000);

		info("Create new account to check");
		acc.userSignIn(userType.ADMIN);
		nav.goToNewStaff();
		acc.addNewUserAccount(username, password, password, firstName, lastName, "", email1, null, null, true);
		acc.signOut();
		acc.signIn(username, password);
		goToForums();
		click(By.linkText(catName));
		click(By.linkText(addForum[0]));
		click(By.linkText(title));
		Utils.pause(1000);
		waitForElementNotPresent(post.ELEMENT_POST_REPLY_BUTTON);

		//Delete data test
		acc.userSignIn(userType.ADMIN);
		goToForums();
		click(By.linkText(catName));
		fmCat.deleteCategoryInForum(catName, true);		
		info("Delete users test");
		nav.goToUsersAndGroupsManagement();
		uGroup.deleteUser(username);			
	}

	/**
	 * Case ID:109101.
	 * Test Case Name: Assign [Permission to view posts] for valid user when adding new category.
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/06/04 18:16:39
	 */
	@Test
	public  void test11_AssignPermissionToViewPostsForValidUserWhenAddingNewCategory() {
		info("Test 11 Assign [Permission to view posts] for valid user when adding new category");
		String catName = "Category 109101";
		String order = "1";
		int chooseRestricted = 5;
		String[] restricted = {""}; 
		String description = "Description of Category";
		String[] user = {"james"};
		String[] userRole = {"manager:/platform/web-contributors"}; 
		String[] userGroup = {"/developers"}; 
		String[] addForum = {"Title of forum 001", "1", "Open", "Unlocked", "Description of forum 001"}; 	
		String title = "Title topic 001"; 
		String message = "Topic 001"; 
		String postTitle = "Post 001";
		String username = getRandomString();
		String password = "gtngtn";
		String firstName = "Test";
		String lastName = "EEEE";
		String email1 = username+"@exoplatform.com";	

		/*
		- Login as Administrator user.
		- Go to Forum Application.
		- Click on [Add Category] button in main toolbar.
		 *Expected Outcome: 
		- [Category] form is shown properly.		*/
		/*
		- Select [Permissions] tab
		- Input/select valid User(s)/Role(s)/Group(s)and the click [Add] button.
		- Tick the checkbox [Permission to view posts] = TRUE, corresponding to above [User(s)/Role(s)/Group(s)].
		 *Input Data: 
		 *Expected Outcome: 
		- User(s)/Role(s)/Group(s) is displayed in Permissions table. By default, all checkboxes are marked as TRUE.
		- Checkboxes are enable for changing value manually.
		- [Permission to view posts] checkbox corresponding to selected [User(s)/Role(s)/Group(s)] is displayed with TRUE value.		*/
		/*
		- Input valid value for other required fields.
		- Click [Save] button.
		 *Input Data: 
		 *Expected Outcome: 
		- New Category is added
		-[Permission to view posts] checkbox corresponding to selected [User(s)/Role(s)/Group(s)] is displayed with TRUE value.
		- Selected User(s)/Role(s)/Group(s) will has right to view posts in all topics in forums of added category.		*/ 
		goToForums();
		info("Create New Category");
		fmCat.goToAddCategory(); 
		fmCat.inputTitleOrderDescriptionCategory(catName, order, description);
		fmCat.selectRestricted(chooseRestricted, restricted);
		info("Set permission for category");
		frumPer.configPermission4ForumCategory(2, user, false, false, false, true);
		frumPer.configPermission4ForumCategory(1, userRole, false, false, false, true);
		frumPer.configPermission4ForumCategory(1, userGroup, false, false, false, true);
		button.save();

		info("Create New Forum");
		fmForum.goToAddForum(); 
		fmForum.inputDataInAddForumTab_addForum(catName, addForum); 
		button.save(); 

		info("Create topic");
		topic.quickStartTopic(title, message); 
		Utils.pause(1000);
		click(By.linkText(title));
		post.postReply(postTitle, postTitle, "", "");
		Utils.pause(1000);

		info("User(s)/Role(s)/Group(s) login to verify");
		info("Verify user James");
		acc.userSignIn(userType.AUTHOR);
		goToForums();
		click(By.linkText(catName));
		click(By.linkText(addForum[0])); 
		click(By.linkText(title));
		waitForAndGetElement(post.ELEMENT_POST_TITLE_TEXT.replace("${post}", postTitle));
		waitForAndGetElement(post.ELEMENT_POST_CONTENT_TEXT.replace("${post}", postTitle));
		Utils.pause(1000);

		info("Mary login to check role");
		acc.userSignIn(userType.PUBLISHER);
		goToForums();
		click(By.linkText(catName));
		click(By.linkText(addForum[0])); 
		click(By.linkText(title));
		waitForAndGetElement(post.ELEMENT_POST_TITLE_TEXT.replace("${post}", postTitle));
		waitForAndGetElement(post.ELEMENT_POST_CONTENT_TEXT.replace("${post}", postTitle));
		Utils.pause(1000);

		info("Demo login to check group");
		acc.userSignIn(userType.DEVELOPER);
		goToForums();
		click(By.linkText(catName));
		click(By.linkText(addForum[0])); 
		click(By.linkText(title));
		waitForAndGetElement(post.ELEMENT_POST_TITLE_TEXT.replace("${post}", postTitle));
		waitForAndGetElement(post.ELEMENT_POST_CONTENT_TEXT.replace("${post}", postTitle));
		Utils.pause(1000);

		info("Create new account to check");
		acc.userSignIn(userType.ADMIN);
		nav.goToNewStaff();
		acc.addNewUserAccount(username, password, password, firstName, lastName, "", email1, null, null, true);
		acc.signOut();
		acc.signIn(username, password);
		goToForums();
		click(By.linkText(catName));
		click(By.linkText(addForum[0]));
		waitForElementNotPresent(By.linkText(title));

		//Delete data test
		acc.userSignIn(userType.ADMIN);
		goToForums();
		click(By.linkText(catName));
		fmCat.deleteCategoryInForum(catName, true);		
		info("Delete users test");
		nav.goToUsersAndGroupsManagement();
		uGroup.deleteUser(username);		
	}

	/**
	 * Case ID:109102.
	 * Test Case Name: Add category with invalid user entry for Moderator field.
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/06/04 18:16:39
	 */
	@Test
	public  void test12_AddCategoryWithInvalidUserEntryForModeratorField() {
		info("Test 12 Add category with invalid user entry for Moderator field");
		String catName = "Category" +getRandomNumber();
		String order = "1";
		int chooseRestricted = 5;
		String[] restricted = {""}; 
		String description = "Description of Category";
		String[] userInvalid = {"invalidUser"};
		String[] groupInvalid = {"/invalidGroup"};
		String[] roleInvalid = {"invalidRole:/developers"};
		/*
		- Login as Administrator user.
		- Go to Forum Application.
		- Click on [Add Category] button in main toolbar.
		 *Expected Outcome: 
		- [Category] form is shown properly.		*/
		/*
		- Select [Permissions] tab.
		- Input invalid (unavailable) User(s)/Role(s)/Group(s) for Moderator(s) field.
		- Then click [Add] button.
		 *Input Data: 
		 *Expected Outcome: 
		- Invalid User(s)/Role(s)/Group(s) is NOT displayed in 'Moderators' field.
		- A Warning message is shown that: "[Invalid User(s)/Role(s)/Group(s)] not found, please enter a valid value".		*/ 
		goToForums();
		info("Create New Category");
		fmCat.goToAddCategory(); 
		fmCat.inputTitleOrderDescriptionCategory(catName, order, description);
		fmCat.selectRestricted(chooseRestricted, restricted);
		info("Input invalid user");
		frumPer.setPermissionWithOption(1, userInvalid);
		click(frumPer.ELEMENT_FORUM_CATEGORY_PERMISSION_GRID);
		click(button.ELEMENT_ADD_BUTTON);
		waitForMessage('"'+userInvalid[0]+'"' +" "+ fmCat.MESSAGE_CATEGORY_MODERATOR_INVALID);
		click(ELEMENT_OK_INFOR_POPUP);

		info("Input invalid role");		
		frumPer.setPermissionWithOption(1, roleInvalid);
		click(frumPer.ELEMENT_FORUM_CATEGORY_PERMISSION_GRID);
		click(button.ELEMENT_ADD_BUTTON);
		waitForMessage('"'+roleInvalid[0]+'"' +" "+ fmCat.MESSAGE_CATEGORY_MODERATOR_INVALID);
		click(ELEMENT_OK_INFOR_POPUP);

		info("Input invalid group");
		frumPer.setPermissionWithOption(1, groupInvalid);
		click(frumPer.ELEMENT_FORUM_CATEGORY_PERMISSION_GRID);
		click(button.ELEMENT_ADD_BUTTON);
		waitForMessage('"'+groupInvalid[0]+'"' +" "+ fmCat.MESSAGE_CATEGORY_MODERATOR_INVALID);
		click(ELEMENT_OK_INFOR_POPUP);
	}
	
	/**
	 * Case ID:114035.
	 * Test Case Name: Add Category With blank entry for Moderator field.
	 * Steps:
	 * 1. Login as Administrator user.
	 * 2. Go to Forum Application.
	 * 3. Click on [Add Category] button in main toolbar.
	 * 4. Select [Permissions] tab.
	 * 5. Don't input into User(s)/Role(s)/Group(s) for Moderator field.
	 * Expected:
	 * 5. The button [Add] is disable
	 */
	@Test
	public  void test13_AddCategoryWithBlankEntryForModeratorfield() {
		info("Test 13 Add Category With blank entry for Moderator field");
		goToForums();
		info("Create New Category");
		fmCat.goToAddCategory(); 
		click(frumPer.ELEMENT_PERMISSION_TAB);
		
		info("Verify that The button [Add] is disable");
		assert waitForAndGetElement(button.ELEMENT_ADD_BUTTON_DISABLED).isDisplayed();
		
		click(button.ELEMENT_CATEGORY_CANCEL_BUTTON);
	}
	
	/**
	 * Case ID:114036.
	 * Test Case Name: Add category with valid role entry for Moderator field.
	 * Steps:
	 * 1. Login as Administrator user.
	 * 2. Go to Forum Application.
	 * 3. Click on [Add Category] button in main toolbar.
	 * 4. Select [Permissions] tab
	 * 5. Input/select valid Role(s) to add for Moderator(s) field.
	 * 6. Click [Add] button
	 * ==>Role(s) is added and displayed in 'Moderators' field.
	 * You can change value in 'Moderators' field by tick/untick the checkboxes.
	 * 7. Input valid value for other required fields.
	 * 8. Click [Save] button.
	 * ==>New Category is added.
	 * Selected Role(s) is displayed in Moderator field of added category.
	 * Selected Role(s) in Moderator field will be moderator(s) of all forums of this category 

	 */
	@Test
	public  void test14_AddCategoryWithValidRoleEntryForModeratorField() {
		info("Test 14 Add category with Valid Role entry for Moderator field");
		String catName = "Category 114036";
		String order = "1";
		String description = "Description of Category";
		String[] userRole = {"manager:/platform/web-contributors"}; 
		
		goToForums();
		info("Create New Category");
		fmCat.goToAddCategory(); 
		
		info("Input name, order and description for new category");
		fmCat.inputTitleOrderDescriptionCategory(catName, order, description);
		
		info("Set permission role for category");
		frumPer.configPermission4ForumCategory(1, userRole, false, true, false, false);
		frumPer.configPermission4ForumCategory(1, userRole, true, true, true,true);
		button.save();
		
		info("Verify that the category is shown");
		click(ELEMENT_HOME_BUTTON);
		waitForAndGetElement(fmCat.ELEMENT_CATEGORY_LINK.replace("${category}",catName));
		
		info("Delete created category");
		click(By.linkText(catName));
		fmCat.deleteCategoryInForum(catName, true);		
	}
	
	/**
	 * Case ID:114037.
	 * Test Case Name: Add category with valid group entry for Moderator field.
	 * Steps:
	 * 1. Login as Administrator user.
	 * 2. Go to Forum Application.
	 * 3. Click on [Add Category] button in main toolbar.
	 * 4. Select [Permissions] tab
	 * 5. Input/select valid Group(s) to add for Moderator(s) field.
	 * 6. Click [Add] button
	 * ==>Groups is added and displayed in 'Moderators' field.
	 * You can change value in 'Moderators' field by tick/untick the checkboxes.
	 * 7. Input valid value for other required fields.
	 * 8. Click [Save] button.
	 * ==>New Category is added.
	 * Selected Group is displayed in Moderator field of added category.
	 * Selected Group in Moderator field will be moderator(s) of all forums of this category 
	 */
	@Test
	public  void test15_AddCategoryWithValidGroupEntryForModeratorField() {
		info("Test 15 Add category with valid group entry for Moderator field");
		String catName = "Category 114037";
		String order = "1";
		String description = "Description of Category";
		String[] userGroup = {"/platform/web-contributors"}; 
		
		goToForums();
		info("Create New Category");
		fmCat.goToAddCategory(); 
		
		info("Input name, order and description for new category");
		fmCat.inputTitleOrderDescriptionCategory(catName, order, description);
		
		info("Set permission role for category");
		frumPer.configPermission4ForumCategory(1, userGroup, false, false, false, false);
		frumPer.configPermission4ForumCategory(1, userGroup, true, true, true, true);
		button.save();
		
		info("Verify that the category is shown");
		click(ELEMENT_HOME_BUTTON);
		waitForAndGetElement(fmCat.ELEMENT_CATEGORY_LINK.replace("${category}",catName));
		
		info("Delete created category");
		click(By.linkText(catName));
		fmCat.deleteCategoryInForum(catName, true);		
	}
}
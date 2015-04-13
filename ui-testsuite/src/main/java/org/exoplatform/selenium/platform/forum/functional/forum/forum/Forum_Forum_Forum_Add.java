package org.exoplatform.selenium.platform.forum.functional.forum.forum;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.forum.ForumBase;
import org.exoplatform.selenium.platform.forum.ForumManageCategory;
import org.exoplatform.selenium.platform.forum.ForumManageForum;
import org.exoplatform.selenium.platform.forum.ForumManagePost;
import org.exoplatform.selenium.platform.forum.ForumManageTopic;
import org.openqa.selenium.By;
import org.testng.annotations.*;

/**
 * @author khanhnt
 * update chinhdtt
 */
public class Forum_Forum_Forum_Add extends ForumBase {

	ManageAccount acc;
	ForumManageCategory fmCat;
	ForumManageForum fmForum;
	ForumManageTopic fmTopic;
	ForumManagePost fmPost;

	@BeforeMethod
	public void setUpBeforeTest() {
		initSeleniumTest();
		driver.get(baseUrl);
		fmCat = new ForumManageCategory(driver, this.plfVersion);
		fmForum = new ForumManageForum(driver, this.plfVersion);
		fmTopic = new ForumManageTopic(driver, this.plfVersion);
		fmPost = new ForumManagePost(driver, this.plfVersion);
		acc = new ManageAccount(driver);
		acc.signIn(DATA_USER1, DATA_PASS);
		button = new Button(driver);
		fmForum = new ForumManageForum(driver, this.plfVersion);
	}

	@AfterMethod
	public void afterTest() {
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	/**
	 * Case ID:106076.
	 * Test Case Name: Add new Forum when don't select [Auto fill email moderators] option.
	 * Created by khanhnt at 2013/12/09 14:21:54
	 */
	@Test
	public  void test01_AddNewForumWhenDontSelectAutoFillEmailModeratorsOption() {
		info("Test 1: Add new forum when dont select auto fill email moderators option"); 
		String catName = "Category 106076" + getRandomNumber();
		String[] fmName={"Test 1 new forum",null,null,null,null};
		String[] userGroups = {"mary,demo"};

		/*Step 1: Add category
		 *Expected Outcome: 
		- Category is created successfully. 		*/
		goToForums();		
		fmCat.addNewCategoryInForum(catName, "1", 0,null, catName, 0,null);

		/*Step 2: Open Add new Forum form
		 *Input Data: 
		- Click on [Add forum] in main toolbar 
		- Or click on specific Category Title then click on [Manage Category] and select [Add new Forum]
		 *Expected Outcome: 
		- Add new Forum form is shown properly		*/
		/*Step 3: Add new Forum without [Auto fill email moderators] option
		 *Input Data: 
		- Input valid value for other required fields
		- In Moderation options tab: Uncheck on [Auto fill email moderators] options and then select user(s)/membership(s)/group(s) for Moderators field 
		- Click [Save]
		 *Expected Outcome: 
		- The email addresses of selected user(s)/membership(s)/group(s) will not be added automatically into 'Email addresses to notify...' field in Moderation tab. These fields can be manually input.
		-Forum is created successfully.		*/ 		
		//fmForum.addForum(catName, fmName, false,EMAIL_ADDRESS1 ,EMAIL_ADDRESS2, false, 0, null);
		fmForum.goToAddForum();
		info("Create new forum");
		fmForum.inputDataForum(catName, fmName, false,null ,null, false, 1, userGroups);
		click(fmForum.ELEMENT_MODERATOR_TAB);
		waitForTextNotPresent("mary.williams@acme.exoplatform.com, jack.miller@acme.exoplatform.com");
		button.save();
		
		//Delete data test
		fmForum.deleteForum(fmName[0]);
		fmCat.deleteCategoryInForum(catName);
	}

	/**
	 * Case ID:106056.
	 * Test Case Name: Add new Forum When select [Auto fill email moderators] option.
	 * Created by khanhnt at 2013/12/09 14:21:54
	 */
	@Test
	public  void test02_AddNewForumWhenSelectAutoFillEmailModeratorsOption() {
		info("Test 2: Add new forum When select auto fill email moderators option"); 
		String catName = "Category 106056" + getRandomNumber();
		String[] fmName={"Test 2 new forum",null,null,null,null};
		String[] userGroups = {"mary,demo"};

		/*Step 1: Add category
		 *Expected Outcome: 
		- Category is created successfully. 		*/
		goToForums();		
		fmCat.addNewCategoryInForum(catName, "1", 0,null, catName, 0,null);

		/*Step 2: Open Add new Forum form
		 *Input Data: 
		- Click on [Add forum] in main toolbar
		- Or click on specific Category Title then click on [Manage Category] and select [Add new Forum]
		 *Expected Outcome: 
		- Add new Forum form is shown properly		*/

		/*Step 3: Add new Forum with [Auto fill email moderators] option
		 *Input Data: 
		- Input valid value for other required fields
		- In Moderation options tab: Tick on [Auto fill email moderators] options and then select user(s)/membership(s)/group(s) for Moderators field 
		- Click [Save]
		 *Expected Outcome: 
		- The email addresses of selected user(s)/membership(s)/group(s) automatically added into 'Email address to notify...' field in Moderation options tab.
		-Forum is created successfully.		*/ 
		fmForum.goToAddForum();
		info("Create new forum");
		fmForum.inputDataForum(catName, fmName, true,null ,null, false, 1, userGroups);
		fmForum.isEmailAutofillInModerator("mary.williams@acme.exoplatform.com, jack.miller@acme.exoplatform.com");
		button.save();

		//Detele data test
		fmForum.deleteForum(fmName[0]);
		fmCat.deleteCategoryInForum(catName);
	}

	/**
	 * Case ID: 106224.
	 * Test Case Name: Add new Forum with send notify mail  in case invalid email address entry.
	 * Created by khanhnt at 2013/12/09 14:21:54
	 */
	@Test
	public  void test03_AddNewForumWithSendNotifyMailInCaseInvalidEmailAddressEntry() {
		info("Test 3: Add new forum with send notify mail in case invalid email address entry"); 

		String catName = "Category 106224";
		String[] fmName={"Test 3 new forum",null,null,null,null};
		String invalidePostEmail = "inlvadiEmail";
		String invalidTopicEmail = "invalidEmail";
		boolean[] permission= {false,false,false,false,false};

		/*
		- Use administrator account to create new category
		 *Expected Outcome: 
		- Category is created successfully. 		*/
		goToForums();		
		fmCat.addNewCategoryInForum(catName, "1", 0,null, catName, 0,null);

		/*
		- Click on [Add forum] in main toolbar
		- Or click on specific Category Title then click on [Manage Category] and select [Add new Forum]
		 *Input Data: 
		 *Expected Outcome: 
		- Add new Forum form is shown properly		*/

		/*
		- Click Moderation options tab
		- Uncheck on [Auto fill email moderators] option
		- Input invalid email address(s) into 'Email addresses to notify...' fields
		- Click [Save]
		 *Input Data: 
		 *Expected Outcome: 
		- Alert message is shown to notify about the invalid email addresses		*/ 
		fmForum.goToAddForum();
		info("Create new forum");
		fmForum.addForum(catName, fmName, false,invalidePostEmail ,invalidTopicEmail, false, 0,null,permission);
		waitForMessage("Please enter a valid email address.");
		click(ELEMENT_OK_INFOR_POPUP);
		button.cancel();

		//Delete data test
		fmCat.deleteCategoryInForum(catName);
	}

	/**
	 * Case ID:106092.
	 * Test Case Name: Add new Forum with send notify mail for new Post option.
	 * Created by khanhnt at 2013/12/09 14:21:54
	 */
	@Test
	public  void test04_AddNewForumWithSendNotifyMailForNewPostOption() {
		info("Test 4: Add new forum with send notify mail for new Post option"); 

		String catName = "Category 106092" + getRandomNumber();
		String[] fmName={"Test 4 new forum" + getRandomNumber(),null,null,null,null};
		String topic = "Test 4 new topic" + getRandomNumber();
		String post = "Test 4 new post" + getRandomNumber();
		String noti = "ChangeContentNotificaiton";

		/*Step 1: Add category
		 *Expected Outcome: 
		- Category is created successfully. 		*/
		goToForums();		
		fmCat.addNewCategoryInForum(catName, "1", 0,null, catName, 0,null);

		/*Step 2: Open Add new Forum form
		 *Input Data: 
		- Click on [Add forum] in main toolbar
		- Or click on specific Category Title then click on [Manage Category] and select [Add new Forum]
		 *Expected Outcome: 
		- Add new Forum form is shown properly		*/

		/*Step 3: Identify email address that will receive notify mail when there's new post on this forum
		 *Input Data: 
		- Click Moderation options tab
		- Uncheck on [Auto fill email moderator] option
		- Input email address(s) into 'Email addresses to notify when there is a new post' form
		 *Expected Outcome: 
		- Email(s) is input properly and displayed in 'Email addresses to notify when there is a new post'. 		*/


		/*Step 4: Complete Adding new Forum
		 *Input Data: 
		- Input value for other required fields in 'Add new Forum' form
		- Click [Save]
		 *Expected Outcome: 
		- New Forum is added
		- A notify mail will be sent to registered email(s) whenever there's new post on added forum		*/ 		
		fmForum.addForum(catName, fmName, false,EMAIL_ADDRESS1 ,null, false, 0, null);
		fmTopic.startTopic(topic, topic, "", 0, null, true, true);
		click(By.linkText(topic));
		fmPost.postReply(post, post, "", "");

		info("Check if e-mail is sent");
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		checkAndDeleteMail(By.xpath(ELEMENT_GMAIL_EMAIL.replace("${category}",catName).replace("${forum}",
				fmName[0]).replace("${topic}", topic)), noti);		

		//Delete data test
		switchToParentWindow();
		fmPost.deletePost(post);
		goToForumHome();
		click(By.linkText(catName));
		fmCat.deleteCategoryInForum(catName, false);
	}

	/**
	 * Case ID:106104.
	 * Test Case Name: Add new Forum with send notify mail for new Topic option.
	 * Created by khanhnt at 2013/12/09 14:21:54
	 */
	@Test
	public  void test05_AddNewForumWithSendNotifyMailForNewTopicOption() {
		info("Test 5: Add new forum with send notify mail for new topic option"); 

		String catName = "Category 106104";
		String[] fmName={"Test 5 new forum",null,null,null,null};
		String topic = "Test 5 new topic";		

		/*Step 1: Add category
		 *Expected Outcome: 
		- Category is created successfully. 		*/
		goToForums();		
		fmCat.addNewCategoryInForum(catName, "1", 0,null, catName, 0,null);

		/*Step 2: Open Add new Forum form
		 *Input Data: 
		- Click on [Add forum] in main toolbar
		- Or click on specific Category Title then click on [Manage Category] and select [Add new Forum]
		 *Expected Outcome: 
		- Add new Forum form is shown properly		*/

		/*Step 3: Identify email address that will receive notify mail when there's new topic(s) on this forum
		 *Input Data: 
		- Click Moderation options tab
		- Uncheck [Auto fill email moderators] option
		- Input email address(s) into 'Email addresses to notify when there is a new topic' text box.
		 *Expected Outcome: 
		- Email(s) is input properly and displayed in 'Email addresses to notify when there is a new topic'. 		*/

		/*Step 4: Complete Adding new Forum
		 *Input Data: 
		- Input value for other required field in 'Add new Forum' form
		- Click [Save]
		 *Expected Outcome: 
		- New Forum is added
		- A notify mail will be sent to register email(s) whenever there's new topic(s) on added forum		*/ 

		fmForum.addForum(catName, fmName, false,null ,EMAIL_ADDRESS1, false, 0, null);
		fmTopic.startTopic(topic, topic, "", 0, null, true, true);

		info("Check if e-mail is sent");
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		checkAndDeleteMail(By.xpath(ELEMENT_GMAIL_EMAIL.replace("${category}",catName).replace("${forum}",
				fmName[0]).replace("${topic}", topic)), topic);

		//Delete data test
		switchToParentWindow();
		fmForum.deleteForum(fmName[0]);
		fmCat.deleteCategoryInForum(catName);
	}
}
package org.exoplatform.selenium.platform.forum.functional.forum.poll;



import static org.exoplatform.selenium.TestLogger.info;

import java.util.HashMap;
import java.util.Map;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.HomePageActivity;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PageManagement;
import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.exoplatform.selenium.platform.forum.ForumBase;
import org.exoplatform.selenium.platform.forum.ForumManageCategory;
import org.exoplatform.selenium.platform.forum.ForumManageForum;
import org.exoplatform.selenium.platform.forum.ForumManagePoll;
import org.exoplatform.selenium.platform.forum.ForumManageTopic;
import org.exoplatform.selenium.platform.social.ManageMember;
import org.exoplatform.selenium.platform.social.SpaceManagement;
import org.openqa.selenium.By;
import org.testng.annotations.*;

/**
 * @author chinhdtt
 * @date 20 Jan 2014
 * 
 * ISSUE Pending because https://jira.exoplatform.org/browse/FORUM-998
 */
public class Forum_Forum_Poll_Activities extends ForumBase{
	ForumManageCategory cat;
	ForumManageForum forum; 
	ForumManageTopic topic;
	ForumManagePoll poll;
	ManageAccount acc;
	NavigationToolbar nav; 
	HomePageActivity home;
	ManageMember mMember; 
	SpaceManagement mSpace; 
	EcmsBase ecms; 
	PageManagement mPage; 

	//@Parameters({"platform","browser","url","version"})
	@BeforeMethod
	public void setUpBeforeTest(){//String platform,String browser,String url,String version){
		initSeleniumTest();//true,platform,browser,url,version);
		driver.get(baseUrl);
		acc = new ManageAccount(driver, this.plfVersion);
		acc.signIn(DATA_USER1, DATA_PASS);
		cat = new ForumManageCategory(driver, this.plfVersion);
		forum = new ForumManageForum(driver, this.plfVersion); 
		button = new Button(driver, this.plfVersion);
		topic = new ForumManageTopic(driver, this.plfVersion); 		
		nav = new NavigationToolbar(driver, this.plfVersion);
		poll = new ForumManagePoll(driver, this.plfVersion);
		home = new HomePageActivity(driver, this.plfVersion);
		mMember = new ManageMember(driver, this.plfVersion);
		mSpace = new SpaceManagement(driver, this.plfVersion);
		ecms = new EcmsBase(driver, this.plfVersion);
		mPage = new PageManagement(driver, this.plfVersion);
		alert = new ManageAlert(driver);
	}

	@AfterMethod
	public void afterTest(){
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	/**
	 * Case ID:77290, 77294, 77295
	 * Test Case Name: 
	 * ------ Add Poll's activity after add a new poll from Forum application.
	 * ------ Update Poll's activity after edit a poll from Forum application.
	 * ------ Remove Poll's activity after delete a poll from Forum application.
	 * Pre-Condition: - At least a topic named top1 already existed on Forum application
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/01/17 15:27:26
	 * Fail with plf 4.1 related to FORUM-769 issue
	 * This case relates to issue: https://jira.exoplatform.org/browse/FORUM-998
	 */
	@Test(groups = "pending")
	public  void test01_02_03_AddUpdateRemovePollsActivityAfterAddANewPollFromForumApplication() {
		info("Test 1: Add_Update_Remove Poll's activity after add a new poll from Forum application");
		String catName = "Category77290"; 
		String order = "1";
		int chooseRestricted = 1;
		String[] restricted = {""}; 
		String description = "Description of Category 77290";
		int setPermission = 0; 		
		String[] userGroup = {DATA_USER1}; 
		String[] addForum = {"Forum 77290", "1", "Open", "Unlocked", "Description of forum 001"}; 	
		String title = "Topic 77290"; 
		String message = "Topic 77290"; 
		String pollQuestion = "Poll 77290"; 
		String[] options = {"option1", "option2"};
		String pollQuestion_update = "Case 77294";

		/*
		- Connect to Intranet
		- Goto Forum application
		- Open topic top1
		- Click on More Actions > Add Poll
		- Fill valid value for all fields
		- Click Submit
		 *Input Data: 
		 *Expected Outcome: Poll is added and shown in top1		*/
		goToForums();
		cat.addNewCategoryInForum(catName, order, chooseRestricted, restricted, description, setPermission, userGroup);
		forum.goToAddForum();
		forum.inputDataInAddForumTab_addForum(catName, addForum); 
		button.save();
		topic.quickStartTopic(title, message); 
		click(By.linkText(title));
		info("Add Poll");
		poll.addPoll(pollQuestion, options, "", false, false);

		/*
		- Back to the Homepage
		 *Input Data: 
		 *Expected Outcome: 
		- A Poll's activity is added to the activity stream
		- A comment is added into activity of top1: A poll has been added to the topic. 		*/ 
		nav.goToHomePage();
		waitForAndGetElement(home.ELEMENT_ACTIVITY.replace("${activityText}", pollQuestion));

		/*
		- Connect to Intranet
		- Open Forum/Topic or the poll application
		- Click on More Actions of poll1
		- Click on Edit
		- Edit something and Submit Poll
		 *Input Data: 
		 *Expected Outcome: poll1 is saved with new value		*/
		info("Edit Poll");
		goToForums();
		poll.editPoll(pollQuestion_update, options, "", false, false);

		/*
		- Back to the Homepage
		 *Input Data: 
		 *Expected Outcome: 
		- poll1's activity content is updatedin the activity stream
		- A comment is added: Poll has been updated.		*/ 
		nav.goToHomePage();
		waitForAndGetElement(home.ELEMENT_POLL_ACTIVITY.replace("${poll}", pollQuestion_update));
		waitForAndGetElement(home.ELEMENT_ACTIVITY_COMMENT_CONTENT.replace("${title}", pollQuestion_update).replace("${comment}", "Poll has been updated."));

		/*
		- Connect to Intranet
		- Open a topic with a poll
		- Click on More Actions of this poll
		- Click Remove > OK
		 *Input Data: 
		 *Expected Outcome: Poll is deleted		*/
		info("Delete Poll");
		goToForums();
		poll.deletePollInTopic(pollQuestion);

		/*
		- Back to the Homepage
		 *Input Data: 
		 *Expected Outcome: 
		- The Poll's activity is removed from the activity stream		*/ 
		nav.goToHomePage();
		waitForElementNotPresent(home.ELEMENT_POLL_ACTIVITY.replace("${poll}", pollQuestion));

		//delete data
		goToForums();
		click(By.linkText(catName));
		cat.deleteCategoryInForum(catName, true);			
	}

	/**
	 * Case ID:77292.
	 * Test Case Name: Display the content of the Poll's activity.
	 * Pre-Condition: 
	- At least a topic already existed in Forum application
	- At least a space with a topic is already existed
	- A page with Poll portlet is added
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/01/17 15:27:26
	 */
	@Test
	public  void test10_DisplayTheContentOfThePollsActivity() {
		info("Test 2: Display the content of the Poll's activity");
		String catName = "Category 77292"; 
		String order = "1";
		int chooseRestricted = 1;
		String[] restricted = {""}; 
		String description = "Description of Category 77292";
		int setPermission = 0; 		
		String[] userGroup = {DATA_USER1}; 
		String[] addForum = {"Forum 77292", "1", "Open", "Unlocked", "Description of forum 002"}; 	
		String title = "Topic 77292"; 
		String message = "Topic 77292"; 
		String pollQuestion = "Poll 77292";
		String[] options = {"option1", "option2"};

		/*
		- Connect to Intranet
		- Add a poll into a topic o Forum application
		- Or add a poll into forum of space
		- Or add a poll into Poll portlet
		 *Input Data: 
		 *Expected Outcome: Poll is added		*/
		goToForums();
		cat.addNewCategoryInForum(catName, order, chooseRestricted, restricted, description, setPermission, userGroup);
		forum.goToAddForum();
		forum.inputDataInAddForumTab_addForum(catName, addForum); 
		button.save();
		click(By.linkText(catName));
		click(By.linkText(addForum[0]));
		topic.quickStartTopic(title, message); 
		click(By.linkText(title));
		poll.addPoll(pollQuestion, options, "", false, false);

		/*
		- Back to the Homepage
		 *Input Data: 
		 *Expected Outcome: 
		- A Poll's activity is added in the activity stream
		- A content of the activity is displayed with following informations:* Poll's title* All solutions proposed and for each solution : percentage of votes visually indicated + percentage value + number of votes* Total number of votes		*/ 
		nav.goToHomePage();
		info("Content of Poll");
		waitForAndGetElement(home.ELEMENT_ACTIVITY.replace("${activityText}", pollQuestion));
		waitForAndGetElement(home.ELEMENT_POLL_ACTIVITY.replace("${poll}", pollQuestion));
		waitForAndGetElement(home.ELEMENT_VOTE.replace("${poll}", pollQuestion).replace("${vote}", options[0]));
		waitForAndGetElement(home.ELEMENT_VOTE.replace("${poll}", pollQuestion).replace("${vote}", options[1]));
		waitForAndGetElement(home.ELEMENT_VOTE_PROGRESSBAR.replace("${poll}", pollQuestion).replace("${vote}", options[0]));
		waitForAndGetElement(home.ELEMENT_VOTE_PROGRESSBAR.replace("${poll}", pollQuestion).replace("${vote}", options[1]));
		waitForAndGetElement(home.ELEMENT_VOTE_TOTAL.replace("${poll}", pollQuestion));

		//delete data
		goToForums();
		click(By.linkText(catName));
		cat.deleteCategoryInForum(catName, true);	
	}

	/**
	 * Case ID:77298.
	 * Test Case Name: Delete a Poll activity from activity stream by owner.
	 * Pre-Condition: 
	- At least a Poll activity already existed on activity stream
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/01/17 15:27:26
	 */
	@Test
	public  void test11_DeleteAPollActivityFromActivityStreamByOwner() {
		info("Test 3: Delete a Poll activity from activity stream by owner");
		String catName = "Category 77298"; 
		String order = "1";
		int chooseRestricted = 1;
		String[] restricted = {""}; 
		String description = "Description of Category 77298";
		int setPermission = 0; 		
		String[] userGroup = {DATA_USER1}; 
		String[] addForum = {"Forum 77298", "1", "Open", "Unlocked", "Description of forum 005"}; 	
		String title = "Topic 77298"; 
		String message = "Topic 77298"; 
		String pollQuestion = "Poll 77298";
		String[] options = {"option1", "option2"};

		/*
		- Connect to Intranet
		- Move the mouse over the Poll activity
		 *Input Data: 
		 *Expected Outcome: A (X) icon is displayed in the top right panel of the activity		*/
		goToForums();
		cat.addNewCategoryInForum(catName, order, chooseRestricted, restricted, description, setPermission, userGroup);
		forum.goToAddForum();
		forum.inputDataInAddForumTab_addForum(catName, addForum); 
		button.save();
		click(By.linkText(catName));
		click(By.linkText(addForum[0]));
		topic.quickStartTopic(title, message); 
		click(By.linkText(title));
		poll.addPoll(pollQuestion, options, "", false, false);
		nav.goToHomePage();
		waitForAndGetElement(home.ELEMENT_POLL_ACTIVITY.replace("${poll}", pollQuestion));

		/*
		- Click on the (X) icon
		- Click on OK button of confirmation message
		 *Input Data: 
		 *Expected Outcome: The Poll activity is removed from the activity stream		*/ 
		home.deleteActivity(pollQuestion);
		waitForElementNotPresent(home.ELEMENT_POLL_ACTIVITY.replace("${poll}", pollQuestion));

		//delete data
		goToForums();
		click(By.linkText(catName));
		cat.deleteCategoryInForum(catName, true);	
	}

	/**
	 * Case ID:77602, 77606, 77604
	 * Test Case Name: 
	 * --------- Add Poll's activity after add a new poll from space.
	 * --------- Update Poll's activity after edit a poll from a space.
	 * --------- Remove Poll's activity after delete a poll from a space.
	 * Pre-Condition: - At least a space is already existed
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/01/17 15:27:26
	 * Fail with plf 4.1 related to FORUM-769 issue
	 * This case relates to issue: https://jira.exoplatform.org/browse/FORUM-998
	 */
	@Test (groups = "pending")
	public  void test04_05_06_AddUpadeDeletePollsActivityAfterAddANewPollFromSpace() {
		info("Test 6: Add Poll's activity after add a new poll from space");
		String spaceName = "Space 77602";
		String title = "Topic 77602"; 
		String message = "Topic 77602"; 
		String pollQuestion = "Poll 77602";
		String[] options = {"option1", "option2"};
		String pollQuestionUpdate = "Case 77606";

		/*
		- Connect to Intranet
		- Open a space
		- Click on Discussion
		- Create a topic
		- Click on More Actions > Add Poll
		- Fill valid value for all fields
		- Click Submit
		 *Input Data: 
		 *Expected Outcome: Poll is added and shown in topic of space		*/
		mMember.goToMySpacePage();
		mMember.addNewSpace(spaceName, "");
		click(mSpace.ELEMENT_SPACE_TAB_NAME.replace("${index}", "2").replace("${tabName}", "Forums"));
		topic.quickStartTopic(title, message); 
		click(By.linkText(title));
		info("Add Poll in Space");
		poll.addPoll(pollQuestion, options, "", false, false);

		/*
		- Back to the Homepage
		 *Input Data: 
		 *Expected Outcome: 
		- A Poll's activity is added to the activity stream		*/ 
		nav.goToHomePage();
		waitForAndGetElement(home.ELEMENT_POLL_ACTIVITY.replace("${poll}", pollQuestion));

		/*
		- Connect to Intranet
		- Open a space
		- Click on Discussion
		- Open topic with poll1
		- Click on More Actions of poll1
		- Click on Edit
		- Edit something and Submit Poll
		 *Input Data: 
		 *Expected Outcome: poll1 is saved with new value		*/
		info("Edit Poll in Space");
		mMember.goToSpaceFromMySpaceNavigation(spaceName);
		click(mSpace.ELEMENT_SPACE_TAB_NAME.replace("${index}", "2").replace("${tabName}", "Forums"));
		click(By.linkText(title));
		poll.editPoll(pollQuestionUpdate, options, "", false);

		/*
		- Back to the Homepage
		 *Input Data: 
		 *Expected Outcome: 
		- poll1's activity content is updatedin the activity stream
		- A comment is added: Poll has been updated.		*/ 
		nav.goToHomePage();
		waitForAndGetElement(home.ELEMENT_POLL_ACTIVITY.replace("${poll}", pollQuestionUpdate));
		waitForAndGetElement(home.ELEMENT_ACTIVITY_COMMENT_CONTENT.replace("${title}", pollQuestionUpdate).replace("${comment}", "Poll has been updated."));

		/*
		- Connect to Intranet
		- Open space1
		- Click on Discussion
		- Open topic with poll
		- Click on More Actions of this poll
		- Click Remove > OK
		 *Input Data: 
		 *Expected Outcome: Poll is deleted		*/
		info("Delete Poll in Space");
		mMember.goToSpaceFromMySpaceNavigation(spaceName);
		click(mSpace.ELEMENT_SPACE_TAB_NAME.replace("${index}", "2").replace("${tabName}", "Forums"));
		click(By.linkText(title));
		poll.deletePollInTopic(pollQuestion);

		/*
		- Back to the Homepage
		 *Input Data: 
		 *Expected Outcome: 
		- The Poll's activity is removed from the activity stream		*/ 
		nav.goToHomePage();
		waitForElementNotPresent(home.ELEMENT_POLL_ACTIVITY.replace("${poll}", pollQuestion));

		//delete data
		mMember.goToAllSpaces();
		mMember.deleteSpace(spaceName,300000);
	}

	/**
	 * Case ID:77603, 77607, 77605
	 * Test Case Name: 
	 * --------- Add Poll's activity after add a new poll from Poll portlet.
	 * --------- Update Poll's activity after edit a poll from Poll portlet.
	 * --------- Remove Poll's activity after delete a poll from Poll portlet.
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/01/17 15:27:26
	 * Fail with plf 4.1 related to FORUM-769 issue
	 * This case relates to issue: https://jira.exoplatform.org/browse/FORUM-998
	 */
	@Test (groups = "pending")
	public  void test07_08_09_AddUpdateRemovePollsActivityAfterAddANewPollFromPollPortlet() {
		info("Test 7: Add Poll's activity after add a new poll from Poll portlet");
		String nodeName = getRandomString();
		String displayName = "Node77603test5";
		String title = "Collaboration";
		Map<String, String> portletIds= new HashMap<String, String>();
		portletIds.put("Collaboration/PollPortlet", "");
		String pollQuestion = "Poll_77603";
		String[] options = {"option1", "option2"};
		String pollQuestionUpdate = "Case 77607";

		/*
		- Connect to Intranet
		- Create a page by winzard
		- Choose Poll porlet at step 3
		 *Input Data: 
		 *Expected Outcome: Page with Poll portlet is create successfully		*/
		nav.goToPageCreationWizard();
		Utils.pause(5000);
		click(ELEMENT_UP_LEVEL);
		info("Create page");
		mPage.addNewPageEditor(nodeName, displayName,"",title, portletIds, false, true);
		Utils.pause(5000);		

		/*
		- Open above page
		- Click Edit > Layout
		- Edit Poll portlet
		- Click on Add Poll
		- Fill all valid values
		- Click Save > Finish 
		 *Input Data: 
		 *Expected Outcome: Poll is added and shown in Page		*/
		mouseOver(ELEMENT_FRAME_CONTAIN_PORTLET,true);
		click(ELEMENT_EDIT_PORTLET_ICON);
		click(poll.ELEMENT_ADD_POLL_BUTTON);
		info("Add Poll in Portlet");
		poll.inputFormPoll(pollQuestion, options, "", false, false);
		click(poll.ELEMENT_POLL_SUBMIT_BUTTON);
		Utils.pause(500);
		button.close();
		click(ELEMENT_PAGE_FINISH_BUTTON);
		waitForTextNotPresent("Page Editor");
		Utils.pause(1000);
		waitForAndGetElement(poll.ELEMENT_POLL_TITLE.replace("${poll}", pollQuestion));

		/*
		- Back to the Homepage
		 *Input Data: 
		 *Expected Outcome: 
		- A Poll's activity is added to the activity stream		*/ 
		nav.goToHomePage();
		waitForAndGetElement(home.ELEMENT_POLL_ACTIVITY.replace("${poll}", pollQuestion));

		/*
		- Connect to Intranet
		- Open page with poll portlet
		- Edit poll1 and Save
		 *Input Data: 
		 *Expected Outcome: poll1 is saved with new value		*/
		click(home.ELEMENT_POLL_ACTIVITY.replace("${poll}", pollQuestion));
		Utils.pause(1000);
		info("Edit Poll in Portlet");
		poll.editPoll(pollQuestionUpdate, options, "", false);

		/*
		- Back to the Homepage
		 *Input Data: 
		 *Expected Outcome: 
		- poll1's activity content is updatedin the activity stream
		- A comment is added: Poll has been updated.		*/ 
		nav.goToHomePage();
		waitForAndGetElement(home.ELEMENT_POLL_ACTIVITY.replace("${poll}", pollQuestionUpdate));
		waitForAndGetElement(home.ELEMENT_ACTIVITY_COMMENT_CONTENT.replace("${title}", pollQuestionUpdate).replace("${comment}", "Poll has been updated."));

		/*
		- Connect to Intranet
		- Open page with Poll portlet
		- Remove this poll
		 *Input Data: 
		 *Expected Outcome: Poll is deleted		*/
		info("Remove Poll in Portlet");
		click(ELEMENT_LEFT_NAVIGATION_ITEM_INDEX.replace("${index}","7").replace("${menuItem}", displayName));
		click(poll.ELEMENT_POLL_MORE_ACTION);
		click(poll.ELEMENT_POLL_DELETE_LINK);		
		Utils.pause(500);
		alert.acceptAlert();

		/*
		- Back to the Homepage
		 *Input Data: 
		 *Expected Outcome: 
		- The Poll's activity is removed from the activity stream		*/ 	
		nav.goToHomePage();
		waitForElementNotPresent(home.ELEMENT_POLL_ACTIVITY.replace("${poll}", pollQuestionUpdate));

		//delete data
		info("Delete page");
		mPage.deletePageAtManagePageAndPortalNavigation(nodeName, true, "intranet", false, null,displayName);
	}
}
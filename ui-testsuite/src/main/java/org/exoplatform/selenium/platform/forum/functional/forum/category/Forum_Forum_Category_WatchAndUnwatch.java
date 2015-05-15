package org.exoplatform.selenium.platform.forum.functional.forum.category;

import static org.exoplatform.selenium.TestLogger.info;

import java.awt.AWTException;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.Dialog;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.forum.ForumBase;
import org.exoplatform.selenium.platform.forum.ForumManageCategory;
import org.exoplatform.selenium.platform.forum.ForumManageForum;
import org.exoplatform.selenium.platform.forum.ForumManageTopic;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author chinhdtt
 * @date 21 Nov 2013
 * Migrate to plf4.1.x by Lientm (18 Jun 2014)
 */

public class Forum_Forum_Category_WatchAndUnwatch extends ForumBase{
	ManageAccount magAc;
	ForumManageCategory cat;
	ForumManageForum forum;
	ForumManageTopic topic;
	ManageAlert alert; 
	Button button;
	Dialog dialog;

	@BeforeMethod
	public void setUpBeforeTest(){
		initSeleniumTest();
		magAc = new ManageAccount(driver);
		cat = new ForumManageCategory(driver);
		forum = new ForumManageForum(driver);
		topic = new ForumManageTopic(driver);
		button = new Button(driver);
		magAc.signIn(DATA_USER1, DATA_PASS);
		alert = new ManageAlert(driver); 
		dialog = new Dialog(driver);
	}

	@AfterMethod
	public void afterTest(){
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	/** Check send notify when add new topic into a forum that is being watched
	 * Test caseID 109103	
	 * Step 1: Create category, forum
	 * Step 2: Watch forum
	 * Step 3: Add topic into forum/category that is being watched
	 * @throws AWTException 
	 */

	@Test
	public void test01_CheckSendNotify_AddNewTopic_WatchForum() throws AWTException {
		/*Declare variables*/ 
		String catName = "Category 109103"; 
		String order = "1";
		String forumName = "Forum 109103";
		String title = "Title topic 109103"; 
		String message = "Topic 109103"; 
		String[] restricted = {DATA_USER1}; 
		String description = "Description of Category 001";
		int setPermission = 2; 
		String[] userGroup = {DATA_USER1}; 
		/* Step 1: Create category, forum*/
		//- Login by the administrator to create new category, forum
		magAc.updateUserProfile(null,null, null, EMAIL_ADDRESS1);
		goToForums();
		cat.addNewCategoryInForum(catName, order, 1, restricted, description, setPermission, userGroup, true);
		forum.quickAddForum(forumName);

		/*Step 2: Watch forum*/
		//- Select [Watch] icon 
		watchItem(true);

		/* Step 3: Add topic into forum/category that is being watched*/
		// - Add new topic into above forum
		info("Create New Topic");
		topic.quickStartTopic(title, message); 

		//Check email
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		checkAndDeleteMailUsingRobot(By.xpath(ELEMENT_GMAIL_EMAIL.replace("${category}",catName).replace("${forum}", forumName).replace("${topic}", title)), REGISTER_MAIL_CONTENT);

		// Clean data test
		switchToParentWindow();
		click(By.linkText(catName));
		cat.deleteCategoryInForum(catName, true);
		magAc.updateUserProfile(null,null, null, "john.smith@acme.exoplatform.com");
	} 

	/** Check send notify after adding new topic into forum/category which is watching  and it's required for approval
	 * Test caseID 109104
	 * @throws AWTException 
	 */

	@Test
	public void test02_CheckSendNotify_AddTopic_WatchCategory_RequiredApproval() throws AWTException {
		/*Declare variables*/ 
		String catName = "Category" + getRandomNumber(); 
		String order = "1";
		int chooseRestricted = 1;
		String[] restricted = {DATA_USER4}; 
		String description = "Description of Category 002";
		String[] addForum = {"Forum" + getRandomNumber(), "1", "Open", "Unlocked", "Description of forum 002"}; 	
		String title = "Title topic" + getRandomNumber(); 
		String message = "Topic" + getRandomNumber();
		By mail = By.xpath(ELEMENT_GMAIL_EMAIL2.replace("${category}",catName).replace("${forum}", addForum[0]).replace("${topic}", title));

		/* Step 1: Create category, forum */
		//- Login by the administrator to create new category, forum
		//- Create forum with checked Moderate Topics option
		goToForums(); 
		cat.addNewCategoryInForum(catName, order, chooseRestricted, restricted, description, 0, null, true);
		info("Create New Forum checked Moderate Topics option");
		forum.addForum(catName, addForum, true, null, null, true, 0, null);
		magAc.signOut();

		/* Step 2: Watch forum/category */
		// - Login by the normal user
		// - On created category and select [Watch]
		info("Login by the normal user");
		magAc.signIn(DATA_USER4, DATA_PASS);
		magAc.updateUserProfile(null,null, null, EMAIL_ADDRESS1);
		goToForums(); 
		click(By.linkText(catName));
		watchItem(true);

		/*Step 3: Add topic into forum/category that is being watched*/
		// - Add new topic into above forum -> user does not receive any notify
		click(By.linkText(addForum[0]));
		click(topic.ELEMENT_START_TOPIC_BUTTON);
		topic.startTopic(title, message, null, 0, null, true, true);
		waitForAndGetElement(dialog.ELEMENT_POPUP_WARNING.replace("${message}", topic.MSG_ADD_MODERATE_TOPIC));
		button.ok();
		waitForElementNotPresent(By.linkText(title));
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(120000);
		waitForElementNotPresent(mail);

		//admin approve topic
		switchToParentWindow();
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		goToForums();
		click(By.linkText(addForum[0]));
		click(By.linkText(title)); 
		topic.approveTopic();

		//Check email
		switchToNewWindow();
		checkAndDeleteMailUsingRobot(mail, message);

		//Clean data test
		switchToParentWindow();
		click(By.linkText(catName));
		cat.deleteCategoryInForum(catName, true);
		magAc.signOut();
		magAc.signIn(DATA_USER2, DATA_PASS);
		magAc.updateUserProfile(null,null, null, "mary@acme.exoplatform.com");
	} 

	/** Check send notify after adding new topic and it's in pending for censor
	 * Test caseID 109105
	 * @throws AWTException 
	 */	
	@Test
	public void test03_CheckSendNotify_AddNewTopic_PendingForCensor() throws AWTException {
		/*Declare variables*/ 
		String catName = "Category 109105"; 
		String order = "1";  
		String[] restricted = {DATA_USER2}; 
		String description = "Description of Category 109105";
		int setPermission = 0; 
		String[] userGroup = {DATA_USER2}; 
		String forumName = "Forum 109105"; 	
		String message = "Topic 109105"; 
		String key = "Censor Topic 109105"; 
		By mail = By.xpath(ELEMENT_GMAIL_EMAIL2.replace("${category}",catName).replace("${forum}", forumName));
		/* Step 1: Create category, forum */
		//- Login by the administrator to create new category, forum
		//- Define Censored word from Administration form	
		goToForums(); 
		cat.addNewCategoryInForum(catName, order, 1, restricted, description, setPermission, userGroup, true);
		forum.quickAddForum(forumName);
		setCensorKeywords(key);
		magAc.signOut();

		//		 Step 2: Watch forum/category 
		//- Login by the normal user
		//- On created category select [Watch]
		info("Login by the normal user");
		magAc.signIn(DATA_USER2, DATA_PASS);
		magAc.updateUserProfile(null,null, null, EMAIL_ADDRESS1);
		goToForums();
		click(By.linkText(catName));
		watchItem(true);

		//		 Step 3: Add topic into forum/category that is being watched
		// - Add new topic with censor content into above forum -> not having mail
		click(By.linkText(forumName));
		click(topic.ELEMENT_START_TOPIC_BUTTON);
		topic.startTopic(key, message, null, 0, null, true, true);
		waitForAndGetElement(dialog.ELEMENT_POPUP_WARNING.replace("${message}", topic.MSG_ADD_CENSOR_TOPIC));
		button.ok();
		waitForElementNotPresent(By.linkText(key));
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(120000);
		waitForElementNotPresent(mail);

		//admin censor topic
		switchToParentWindow();
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		goToForums();
		click(By.linkText(forumName));
		topic.censorTopic(key);

		//Check email
		switchToNewWindow();
		checkAndDeleteMailUsingRobot(mail, message);

		//Clean data test
		switchToParentWindow();
		click(By.linkText(catName));
		cat.deleteCategoryInForum(catName, true);
		topic.setCensorKeywords("");
		magAc.signOut();
		magAc.signIn(DATA_USER2, DATA_PASS);
		magAc.updateUserProfile(null,null, null, "mary@acme.exoplatform.com");
	}

	/** Check send notify after move topic in forum/category that is being watched
	 * Test caseID 109106
	 * @throws AWTException 
	 */

	@Test
	public void test04_CheckSendNotify_MoveTopic_WatchCategory() throws AWTException {
		/*Declare variables*/ 

		String catName1 = "Category 109106 1"; 
		String catName2 = "Category 109106 2";
		String order = "1";  
		String description = "Description of Category 109106";
		String forum1 = "Forum 109106 1"; 	
		String forum2 = "Forum 109106 2"; 	
		String title = "Title topic 109106"; 
		String message = "Topic 109106"; 
		/* Step 1: Create category, forum*/
		//- Login by the administrator to create new category, forum, topics
		magAc.updateUserProfile(null,null, null, EMAIL_ADDRESS1);
		goToForums(); 
		cat.addNewCategoryInForum(catName1, order, 0, null, description, 0, null, true);		
		forum.quickAddForum(forum1);	
		topic.quickStartTopic(title, message);

		goToForumHome();
		cat.addNewCategoryInForum(catName2, "2", 0, null, description, 0, null, true);
		forum.quickAddForum(forum2);

		/*Step 2: Watch category*/
		//- Select [Watch] icon 
		goToForumHome();
		click(By.linkText(catName1)); 
		watchItem(true);

		/* Step 3: Move topic */
		// - Move topic in category which watching to a destination forum
		click(By.linkText(title));
		topic.moveTopic(title, forum2); 
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		checkAndDeleteMailUsingRobot(By.xpath(ELEMENT_GMAIL_EMAIL.replace("${category}",catName2).replace("${forum}", forum2)), title);

		//Clean data test
		switchToParentWindow();
		click(By.linkText(catName2));
		cat.deleteCategoryInForum(catName2, true);
		click(By.linkText(catName1));
		cat.deleteCategoryInForum(catName1, true);
		magAc.updateUserProfile(null,null, null, "john.smith@acme.exoplatform.com");
	} 
}

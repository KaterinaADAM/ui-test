package org.exoplatform.selenium.platform.forum.functional.forum.administration;

import static org.exoplatform.selenium.TestLogger.info;

import java.awt.AWTException;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.ManageAlert;
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
 * @author chinhdtt
 * @date 19 Nov 2013
 * @update chinhdtt
 * */

public class Forum_Forum_Administration_Notification extends ForumBase{
	ForumManageCategory cat;
	ForumManageForum forum; 
	ForumManageTopic topic;
	ManageAccount acc;

	@BeforeMethod
	public void setUpBeforeTest(){
		initSeleniumTest();
		driver.get(baseUrl);
		acc = new ManageAccount(driver, this.plfVersion);
		acc.signIn(DATA_USER1, DATA_PASS);
		cat = new ForumManageCategory(driver, this.plfVersion);
		forum = new ForumManageForum(driver, this.plfVersion); 
		button = new Button(driver);
		topic = new ForumManageTopic(driver, this.plfVersion); 
		alert = new ManageAlert(driver, this.plfVersion);
	}

	@AfterMethod
	public void afterTest(){
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	/** Change Content of notification
	 * Test caseID 106946
	 * Step 1: Open Notifications form
	 * Step 2: Change content of notification
	 * Step 3: Check notification mail after making changes in Notification table
	 * @throws AWTException 
	 */

	@Test
	public void test01_ChangeContentOfNotification() throws AWTException {
		/*Declare variables*/
		String subject = "Change category Notification 001";
		String content = "Content of Notification 001";
		String catName = "Category 106946"; 
		String order = "1";
		int chooseRestricted = 1;
		String[] restricted = {DATA_USER1}; 
		String description = "Description of Category 001";
		int setPermission = 0; 		
		String[] userGroup = {DATA_USER1}; 
		String[] addForum = {"Forum 001", "1", "Open", "Unlocked", "Description of forum 001"}; 	
		String title = "Title topic 001"; 
		String message = "Topic 001"; 
		
		/*
		- Login by the Administrator
		- Go to Forum porlet
		- Click on [Administration] and select [Notifications] in drop down menu
		 *Expected Outcome: Notification form is shown properly		*/
		acc.updateUserProfile(null, null, null,EMAIL_ADDRESS1);
		info("Open Notifications form");
		goToForums(); 

		/*
		- Make changes about content of notify
		- Click Save button
		 *Input Data: 
		 *Expected Outcome: 
		- Default notify content is shown
		- Changes is saved		*/
		info("Change content of notification");
		changeNotifications(false,subject, content); 

		/*
		- Create new category, forum, topics with topic/post notification option and then add topic/post inside it
		- Or Add watch on specific forum/topic and then add topic/post inside it
		 *Input Data: 
		 *Expected Outcome: 
		- Notification mail is sent to registered e-mail address with the content like the defined content in Notification tab in Administration.		*/ 
		info("Create New Category");
		cat.goToAddCategory(); 
		cat.inputDataCategoryInForum(catName, order, chooseRestricted, restricted,
				description, setPermission, userGroup, true);
		button.save();
		info("Create New Forum");
		forum.goToAddForum(); 
		forum.inputDataInAddForumTab_addForum(catName, addForum); 
		button.save();
		click(By.linkText(catName));		
		click(By.linkText(addForum[0]));
		watchItem(true); 
		alert.acceptAlert();
		topic.quickStartTopic(title, message);			
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS); 
		checkAndDeleteMailUsingRobot(By.xpath(ELEMENT_GMAIL_EMAIL.replace("${category}",catName).replace("${forum}", addForum[0]).replace("${topic}", title)), content);
		// Clean data test
		switchToParentWindow();
		click(By.linkText(catName));
		cat.deleteCategoryInForum(catName, true);	
	}

	/** Set notification when Content moved notification field is blank
	 * Test caseID 106966
	 * Step 1: Open Notifications form
	 * Step 2: Leave Template new topic/post notification blank
	 */

	@Test
	public void test02_SetNotificationWhenContentMovedNotificationFieldIsBlank() {
		/*Declare variables*/ 
		String subject = "Change category Notification 002";
		String content = ""; 

		/*
		- Login by the Administrator
		- Go to Forum porlet
		- Click on [Administration] and select [Notifications] in drop down menu
		 *Expected Outcome: Notification form is shown properly		*/
		info("Open Notifications form");
		goToForums(); 

		/*
		- Remove value default in content moved notification field to blank
		- Click Save button
		 *Input Data: 
		 *Expected Outcome: Show alert message to notify that this field is required		*/ 
		changeNotifications(false, subject, content);

		waitForAndGetElement(MSG_NOTIFY_BLANK);
		click(ELEMENT_OK_INFOR_POPUP);
	} 

	/** Set notification when new post notification field is blank
	 * Test caseID 106961
	 * Step 1: Open Notifications form
	 * Step 2: Leave new post notification field blank
	 */	
	@Test
	public void test03_SetNotificationWhenNewPostNotificationFieldIsBlank() {
		/*Declare variables*/ 
		String subject = ""; 
		String content = "Content of Notification 003";

		/*
		- Login by the Administrator
		- Go to Forum porlet
		- Click on [Administration] and select [Notifications] in drop down menu
		 *Expected Outcome: Notification form is shown properly		*/
		info("Open Notifications form");
		goToForums(); 

		/*
		- Remove value default in new post notification field to blank
		- Click Save button
		 *Input Data: 
		 *Expected Outcome: Show alert message to notify that this field is required.Note:In some version (exp: 4.0.4), the alert message is not shown. If the posted fields are left blank, It will reset to defaut value ([$CATEGORY][$FORUM] $TOPIC) after saving.		*/ 
		changeNotifications(false, subject, content); 
		goToNotifications();
		assert waitForAndGetElement(ELEMENT_NOTIFY_SUBJECT).getAttribute("value").contains("[$CATEGORY][$FORUM] $TOPIC"); 		
	}

	/** Set notification with adding a prefix to notification subject
	 * Test caseID 106994
	 * Step 1: Open Notifications form
	 * Step 2: Change content of notification
	 * Step 3: Check notification mail with adding prefix to subject
	 * @throws AWTException 
	 */
	@Test
	public void test04_SetNotificationWithAddingAPrefixToNotificationSubject() throws AWTException {
		/*Declare variables*/ 
		String subject = "Case106994[$CATEGORY][$FORUM] $TOPIC";
		String content = "Content of notification 004";
		String catName = "Category 106994"; 
		String order = "1";
		int chooseRestricted = 1;
		String[] restricted = {DATA_USER1}; 
		String description = "Description of Category 004";
		int setPermission = 0; 
		String[] userGroup = {DATA_USER1}; 
		String[] addForum = {"Title of forum 004", "1", "Open", "Unlocked", "Description of forum 004"}; 	
		String title = "Title topic 004"; 
		String message = "Topic 004"; 

		/*
		- Login by the Administrator
		- Go to Forum porlet
		- Click on [Administration] and select [Notifications] in drop down menu
		 *Expected Outcome: Notification form is shown properly		*/
		acc.updateUserProfile(null, null, null, EMAIL_ADDRESS1);
		info("Open Notifications form");
		goToForums(); 

		/*
		- Tick on “Add a prefix to notificatios”
		- Input value as prefix into Notification subject template field.Example: Subject: "Test[$CATEGORY][$FORUM] $TOPIC other"
		- Click Save button
		 *Input Data: 
		 *Expected Outcome: 
		- Changes is saved
		--> Test: prefix-> other: content of subject		*/
		changeNotifications(true,subject, content); 

		/*
		- Create new category, forum, topics with topic/post notification option and then add topic/post inside it
		- Or Add watch on specific forum/topic and then add topic/post inside it
		 *Input Data: 
		 *Expected Outcome: 
		- Notification mail is sent to registered e-mail address with the content and a prefix in Subject		*/ 
		info("Create New Category");
		cat.goToAddCategory(); 
		cat.inputDataCategoryInForum(catName, order, chooseRestricted, restricted,
				description, setPermission, userGroup, true);
		button.save(); 
		info("Create New Forum");
		forum.goToAddForum(); 
		forum.inputDataInAddForumTab_addForum(catName, addForum); 
		button.save(); 
		watchItem(true);
		alert.acceptAlert();
		info("Create New Topic");
		topic.quickStartTopic(title, message); 

		// - Or Add watch on specific forum/topic and then add 		
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS); 
		checkAndDeleteMailUsingRobot(By.xpath(ELEMENT_GMAIL_EMAIL.replace("${category}",catName).replace("${forum}", addForum[0]).replace("${topic}", title)), content);
		// Clean data test
		switchToParentWindow();
		click(By.linkText(catName));
		cat.deleteCategoryInForum(catName, true);	
	} 

	/** Set notification without adding a prefix to notification subject
	 * Test case 106995
	 * Step 1: Open Notifications form
	 * Step 2: Change content of notification
	 * Step 3: Check notification mail without adding prefix to subject
	 * @throws AWTException 
	 */
	@Test
	public void test05_SetNotificationWithoutAddingAPrefixToNotificationSubject() throws AWTException {
		/*Declare variables*/ 
		String subject = "Change Notification 106995";
		String content = "Content 106995"; 
		String catName = "Category 106995"; 
		String order = "1";
		int chooseRestricted = 1;
		String[] restricted = {DATA_USER1}; 
		String description = "Description of Category 005";
		int setPermission = 0; 
		String[] userGroup = {DATA_USER1}; 
		String[] addForum = {"Title of forum 005", "1", "Open", "Unlocked", "Description of forum 005"}; 	
		String title = "Title topic 005"; 
		String message = "Topic 005"; 

		/*
		- Login by the Administrator
		- Go to Forum porlet
		- Click on [Administration] and select [Notifications] in drop down menu
		 *Expected Outcome: Notification form is shown properly		*/
		acc.updateUserProfile(null, null, null, EMAIL_ADDRESS1);
		info("Open Notifications form");
		goToForums(); 

		/*
		- Untick on “Add a prefix to notificatios”
		- Click Save button
		 *Input Data: 
		 *Expected Outcome: 
		- Changes is saved		*/
		changeNotifications(false,subject, content); 

		/*
		- Create new category, forum, topics with topic/post notification option and then add topic/post inside it
		- Or Add watch on specific forum/topic and then add topic/post inside it
		 *Input Data: 
		 *Expected Outcome: 
		- Notification mail is sent to registered e-mail address with content and Subject (without a prefix)		*/ 
		info("Create New Category");
		cat.goToAddCategory(); 
		cat.inputDataCategoryInForum(catName, order, chooseRestricted, restricted,
				description, setPermission, userGroup, true);
		button.save(); 
		info("Create New Forum");
		forum.goToAddForum(); 
		forum.inputDataInAddForumTab_addForum(catName, addForum); 
		button.save(); 
		click(By.linkText(catName));
		click(By.linkText(addForum[0]));
		watchItem(true);
		alert.acceptAlert();
		info("Create New Topic");
		topic.quickStartTopic(title, message); 

		//Check email
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS); 
		checkAndDeleteMailUsingRobot(By.xpath(ELEMENT_GMAIL_EMAIL.replace("${category}",catName).replace("${forum}", addForum[0]).replace("${topic}", title)), content);

		// Clean data test
		switchToParentWindow();
		click(By.linkText(catName));
		cat.deleteCategoryInForum(catName, true);	
	} 
}

package org.exoplatform.selenium.platform.social.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import java.awt.AWTException;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.ActivityStream;
import org.exoplatform.selenium.platform.ConnectionsManagement;
import org.exoplatform.selenium.platform.HomePagePlatform;
import org.exoplatform.selenium.platform.ManageLogInOut;
import org.testng.annotations.*;

public class SOC_Notifications_IntranetNotifications_NotificationIcon_List extends SOC_TestConfig_3{
	
	/**
	 * @author tult
	 * date 20/04/2015
	 */
	
	@BeforeMethod
	public void setUpBeforeMethod(){
		magAc.signOut();
		magAc.signIn(DATA_USER1,DATA_PASS);
	}
	
	/**
	 *<li> Case ID:122977.</li>
	 *<li> Test Case Name: Check Notifications icon in the top navigation.</li>
	 */
	@Test
	public void test01_CheckNotificationIconInTheTopNavigation(){
		//set Data test
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String password1 = username1;
		String email1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber() + mailSuffixData.getMailSuffixRandom();
	
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String password2 = username2;
		String email2 = txData.getContentByArrayTypeRandom(1) + getRandomNumber() + mailSuffixData.getMailSuffixRandom();

		String username3 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String password3 = username3;
		String email3 = txData.getContentByArrayTypeRandom(1) + getRandomNumber() + mailSuffixData.getMailSuffixRandom();
		
		String username4 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String password4 = username4;
		String email4 = txData.getContentByArrayTypeRandom(1) + getRandomNumber() + mailSuffixData.getMailSuffixRandom();
		/*Precondition:
		 	- User A has received 3 notifications*/
		info("Check number of notifications in badge before ccreate more notifications");
		int numNotification = Integer.parseInt(waitForAndGetElement(navTool.ELEMENT_BADGE_NUMBER).getText().trim());
		info("Create 3 notifications for add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password1, email1, username1, username1);
		addUserPage.addUser(username2, password2, email2, username2, username2);
		addUserPage.addUser(username3, password3, email3, username3, username3);
		
		/*Step Number: 1
		 *Step Name: Step 1: 
		 *Step Description: 
			- Login with User A
			- Check the top navigation
		 *Input Data: 
		 *Expected Outcome: 
			- The Notifications icon is displayed in the top navigation, next to the profile menu*/
		info("Check the notification icon in the top navigation");
		waitForAndGetElement(navTool.ELEMENT_POSITION_OF_INTRANET_NOTIFICATION);
		
		/*Step Number: 2
		 *Step Name: Step 2: 
		 *Step Description: 
			- Check the badge number
		 *Input Data: 
		 *Expected Outcome: 
			- The number displayed in the badge is 3*/
		info("Check number of notifications after add more notifications");
		int  newNumNotification = Integer.parseInt(waitForAndGetElement(navTool.ELEMENT_BADGE_NUMBER).getText().trim());
		assert (newNumNotification==(numNotification+3)):"Number of notification is not updated";
		
		/*Step Number: 3
		 *Step Name: Step 3: 
		 *Step Description: 
			- Click the notifications icon
		 *Input Data: 
		 *Expected Outcome: 
			- The notifications list is revealed
- 			- The number displayed in the badge is reset and not displayed anymore*/
		info("Check notification list");
		navTool.goToIntranetNotification();
		intraNot.checkNewUserNotification(username1, "1");
		intraNot.checkNewUserNotification(username2, "1");
		intraNot.checkNewUserNotification(username3, "1");
		info("Make sure there is no number of notifications left");
		navTool.checkNUmberOfNotificationsInBadge(false, "3");
		
		/*Step Number: 4
		 *Step Name: Step 4: 
		 *Step Description: 
			- Generate 1 new notification for User A
		 *Input Data: 
		 *Expected Outcome: 
			- The badge is update with the number 1*/
		navTool.goToAddUser();
		addUserPage.addUser(username4, password4, email4, username4, username4);
		navTool.checkNUmberOfNotificationsInBadge(true, "1");
		
		
		info("Delete users");
		navTool.goToUsersAndGroupsManagement();
		userAndGroup.deleteUser(username1);
		userAndGroup.deleteUser(username2);
		userAndGroup.deleteUser(username3);
		userAndGroup.deleteUser(username4);
	}
	
	/**
	 *<li> Case ID:122978.</li>
	 *<li> Test Case Name: Check UI of the notifications list.</li>
	 */
	@Test
	public void test02_CheckUIOfTheNotificationsList(){
		//set Data test
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String password1 = username1;
		String email1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber() + mailSuffixData.getMailSuffixRandom();
	
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String password2 = username2;
		String email2 = txData.getContentByArrayTypeRandom(1) + getRandomNumber() + mailSuffixData.getMailSuffixRandom();
		
		String username3 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String password3 = username3;
		String email3 = txData.getContentByArrayTypeRandom(1) + getRandomNumber() + mailSuffixData.getMailSuffixRandom();
		
		String activity = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String comment = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		
		/*Precondition:
		 	The user has received new notifications (read and unread) : 
			1/ Like notifications (read)
			2/ Comment notification (unread)
			3/ Connection request (read)
			Settings of the user match with with the above notifications*/
		info("Add 3 new users");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password1, email1, username1, username1);
		addUserPage.addUser(username2, password2, email2, username2, username2);
		addUserPage.addUser(username3, password3, email3, username3, username3);
		
		info ("Login with user 1 and enable new user and like notifications");
		magAc.signOut();
		magAc.signIn(username1, password1);
		navTool.goToMyNotifications();
		intraNot.enableOptionNewUserNotification();
		intraNot.enableOptionLikeNotification();
		
		info("Connect to user 2");
		hp.goToConnections();
		connMag.connectToAUser(username2);
		
		info("User 1 add a activity");
		hp.goToHomePage();
		hpAct.addActivity(true, activity, false, "");
		hpAct.checkActivity(activity);
		
		info("user 2 comments in user 1's activity");
		magAc.signOut();
		magAc.signIn(username2, password2);
		hp.goToConnections();
		connMag.acceptAConnection(username1);
		hp.goToHomePage();
		hpAct.addCommentUsingJavascript(activity, comment);
		
		info("user 2 likes user1's activity");
		hpAct.likeOrUnlikeActivity(activity);
		
		info("Login with user 3 and connect to user 1");
		magAc.signOut();
		magAc.signIn(username3, password3);
		hp.goToConnections();
		connMag.connectToAUser(username1);
		
		info("Sign in with user1 and read/unread notification");
		info("Read comment notification");
		magAc.signOut();
		magAc.signIn(username1, password1);
		navTool.goToIntranetNotification();
		intraNot.checkCommentNotification(username2, activity, "1", false, "", 1, "");
		intraNot.gotoActivityViewer(username2, activity, "1", "", false, "", true);
		
		info("Read connection request notification");
		Utils.pause(2000);
		navTool.goToIntranetNotification();
		click(intraNot.ELEMENT_CONNECT_NOTIFICATION.replace("${fullName}", username3+" "+username3));
		
		/*Step Number: 1
		 *Step Name: Step 1: Check notification icon
		 *Step Description: 
			- Login to platform
			- Click the notifications icon
		 *Input Data: 
		 *Expected Outcome: 
			- The notifications list is displayed
		Step Number: 2
		 *Step Name: Step 2: Check UI of notification list
		 *Step Description: 
			- Check the UI of list
		 *Input Data: 
		 *Expected Outcome: 
			- A link [Mark as read] is displayed at the top of the notification popover
			- The notifications are displayed in the good order : from the newest at the top to the latest at the bottom.
			- Unread notifications should look differently to read notifications.
			- A button [View All] is displayed at the bottom of the page*/
		info("Check notification icon");
		navTool.goToIntranetNotification();
		Utils.pause(2000);
		info("A link [Mark as read] is displayed at the top of the notification popover");
		waitForAndGetElement(navTool.ELEMENT_NOTIFICATION_MARK_ALL_AS_READ_WITH_POSITION,3000,1);
		info("The notifications are displayed in the good order : from the newest at the top to the latest at the bottom.");
		intraNot.checkCommentNotification(username2, activity, "1", true, "3", 1, "");
		intraNot.checkLikeNotification(username2, activity, "1", true, "2", 1, "", "");
		waitForAndGetElement(navTool.ELEMENT_CONNECT_NOTIFICATION_POSITION.replace("${position}", "1").replace("${fullName}", username3),3000,1);
		info("Unread notifications should look differently to read notifications.");
		navTool.checkCommentNotificationReadOrUnread(true, false, username2, activity, "1");
		navTool.checkLikeNotificationReadOrUnread(false, false, username2, activity, "1");
		info("A button [View All] is displayed at the bottom of the page");
		waitForAndGetElement(navTool.ELEMENT_VIEW_ALL_BUTTON,3000,1);
		
		/*Step Number: 3
		 *Step Name: Step 3: Clean the notification list
		 *Step Description: 
			- Remove all notifications from the list by clicking the cross icon
			- Check the UI of the list
		 *Input Data: 
		 *Expected Outcome: 
			- The link Mark as read is hidden 
			- The UI of the list must indicate there is no notification to display
			- The button [View All] is displayed at the bottom of the list*/
		info("Clean the notification list");
		navTool.clearCommentNotification(username2, activity, "1");
		Utils.pause(2000);
		navTool.clearLikeNotification(username2, activity, "1");
		Utils.pause(2000);
		navTool.clearConnectionRequestNotification(username3);
		Utils.pause(2000);
		info("Check the UI of the list");
		info("The link Mark as read is hidden");
		waitForElementNotPresent(navTool.ELEMENT_NOTIFICATION_MARK_ALL_AS_READ_WITH_POSITION,3000,1);
		info("The UI of the list must indicate there is no notification to display");
		waitForAndGetElement(navTool.ELEMENT_NO_NOTIFICATIONS,3000,1);
		info("The button [View All] is displayed at the bottom of the list");
		waitForAndGetElement(navTool.ELEMENT_VIEW_ALL_BUTTON,3000,1);
		
		info("Reset Data");
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		navTool.goToUsersAndGroupsManagement();
		userAndGroup.deleteUser(username1);
		userAndGroup.deleteUser(username2);
		userAndGroup.deleteUser(username3);
	}
	
	/**
	 *<li> Case ID:122979.</li>
	 *<li> Test Case Name: Check UI of the notifications list for the first connection.</li>
	 */
	@Test
	public void test03_CheckUIOfTheNotificationsListForTheFirstConnection(){
		magAc.signIn(DATA_USER1, DATA_PASS);
		//set Data test
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String password1 = username1;
		String email1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber() + mailSuffixData.getMailSuffixRandom();
	
		String fullName = userData.getFullNameByIndex(0);
	
		/*Precondition:
		 	The user A has connected to Platform for the first time*/
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password1, email1, username1, username1);
		
		/*Step Number: 1
		 *Step Name: Step 1: Check notification icon
		 *Step Description: 
			- Login with User A
			- Click the notifications icon
		 *Input Data: 
		 *Expected Outcome: 
			- The notifications list is displayed*/
		/*Step Number: 2
		 *Step Name: Step 2: Check UI of notification list
		 *Step Description: 
			- Check the UI of list
		 *Input Data: 
		 *Expected Outcome: 
			- The button [View All] is not displayed
			- The link [Mark as read] is not displayed
			- The UI indicates there is no notification to display */
		info("Login with user and check notification icon");
		magAc.signIn(username1, password1);
		navTool.goToIntranetNotification();
		info("The button [View All] is not displayed");
		waitForElementNotPresent(navTool.ELEMENT_VIEW_ALL_BUTTON);
		info("The link [Mark as read] is not displayed");
		waitForElementNotPresent(navTool.ELEMENT_NOTIFICATION_MARK_ALL_AS_READ_WITH_POSITION);
		info("The UI indicates there is no notification to display");
		waitForAndGetElement(navTool.ELEMENT_NO_NOTIFICATIONS);
		
		/*Step Number: 3
		 *Step Name: Step 3: Receive new notification
		 *Step Description: 
			- Generate intranet notifications for User A (eg: connection request))
			- Check the notifications list
		 *Input Data: 
		 *Expected Outcome: 
			- The button [View All] is displayed
			- The link [Mark as read] is displayed
			- The notification generated (Connection request) is displayed in the list*/
		info("Log in with John and connect with user");
		magAc.signIn(DATA_USER1, DATA_PASS);
		hp.goToConnections();
		connMag.connectToAUser(username1);
		info("Log in with user and check intranet notification when reciving new notification");
		magAc.signIn(username1, password1);
		navTool.goToIntranetNotification();
		info("The button [View All] is displayed");
		waitForAndGetElement(navTool.ELEMENT_VIEW_ALL_BUTTON);
		info("The link [Mark as read] is displayed");
		waitForAndGetElement(navTool.ELEMENT_NOTIFICATION_MARK_ALL_AS_READ_WITH_POSITION);
		info("The notification generated (Connection request) is displayed in the list");
		intraNot.checkConnectionRequestNotification(fullName);
		
		info("Reset Data");
		magAc.signIn(DATA_USER1, DATA_PASS);
		navTool.goToUsersAndGroupsManagement();
		userAndGroup.deleteUser(username1);
	}
	
	/**
	 *<li> Case ID:123007.</li>
	 *<li> Test Case Name: Click Mark as read.</li>
	 */
	@Test
	public void test04_ClickMarkAsRead(){
		magAc.signIn(DATA_USER1, DATA_PASS);
		//set Data test
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String password1 = username1;
		String email1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber() + mailSuffixData.getMailSuffixRandom();
	
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String password2 = username2;
		String email2 = txData.getContentByArrayTypeRandom(1) + getRandomNumber() + mailSuffixData.getMailSuffixRandom();
	
		String activity = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String comment = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		/*Precondition:
		 	- User A has received several notifications
			- The notifications are unread*/
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password1, email1, username1, username1);
		addUserPage.addUser(username2, password2, email2, username2, username2);
		
		info("Log in user 1 and enable new user and like notifications");
		magAc.signIn(username1, password1);
		navTool.goToMyNotifications();
		intraNot.enableOptionNewUserNotification();
		intraNot.enableOptionLikeNotification();
		
		info("Connect to user 2");
		hp.goToConnections();
		connMag.connectToAUser(username2);
		
		info("User 1 add a activity");
		hp.goToHomePage();
		hpAct.addActivity(true, activity, false, "");
		hpAct.checkActivity(activity);
		
		info("user 2 comments in user 1's activity");
		magAc.signIn(username2, password2);
		hp.goToConnections();
		connMag.acceptAConnection(username1);
		hp.goToHomePage();
		hpAct.addCommentUsingJavascript(activity, comment);
		
		info("user 2 likes user1's activity");
		hpAct.likeOrUnlikeActivity(activity);
		
		info("Log in user 1 and check 2 notifications above is unread");
		magAc.signIn(username1, password1);
		Utils.pause(50000);
		driver.navigate().refresh();
		navTool.goToIntranetNotification();
		navTool.checkCommentNotificationReadOrUnread(false, false, username2, activity, "1");
		navTool.checkLikeNotificationReadOrUnread(false, false, username2, activity, "1");
		/*Step Number: 1
		 *Step Name: Step 1: Mark notificitions as read
		 *Step Description: 
			- Login with User A
			- Click the notifications icon in the top navigation
			- Click [Mark as read]
		 *Input Data: 
		 *Expected Outcome: 
			- All unread notifications are changed to read.*/
		info("Click [Mark all as read] button and verify result");
		click(navTool.ELEMENT_NOTIFICATION_MARK_ALL_AS_READ_WITH_POSITION);
		navTool.checkCommentNotificationReadOrUnread(true, true, username2, activity, "1");
		navTool.checkLikeNotificationReadOrUnread(true, true, username2, activity, "1");
		
		info("Reset Data");
		magAc.signIn(DATA_USER1, DATA_PASS);
		navTool.goToUsersAndGroupsManagement();
		userAndGroup.deleteUser(username1);
		userAndGroup.deleteUser(username2);
	}
	
	/**
	 *<li> Case ID:122980.</li>
	 *<li> Test Case Name: Notifications are pushed instantaneously.</li>
	 * @throws AWTException 
	 */
	@Test
	public void test05_NotificationsArePushedInstantaneously() throws AWTException{
		magAc.signIn(DATA_USER1, DATA_PASS);
		//set Data test
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String password1 = username1;
		String email1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber() + mailSuffixData.getMailSuffixRandom();
	
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String password2 = username2;
		String email2 = txData.getContentByArrayTypeRandom(1) + getRandomNumber() + mailSuffixData.getMailSuffixRandom();
	
		String activity = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		/*Precondition:
		 	- The user A doesn't have any new notifications while starting the test
			- The test is performed on latest version of FF, Chrome or IE 10/11*/
		info("Add 2 new users");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password1, email1, username1, username1);
		addUserPage.addUser(username2, password2, email2, username2, username2);
		/*Step Number: 1
		 *Step Name: Step 1: 
		 *Step Description: 
			- Login with user A
			- Go to wiki application 
		 *Input Data: 
		 *Expected Outcome: 
			- User A is doing some actions on wiki*/
		info("log in as user1 and go to wiki");
		magAc.signIn(username1, password1);
		navTool.checkNUmberOfNotificationsInBadge(false, "1");
		driver.navigate().refresh();
		/*Step Number: 2
		 *Step Name: Step 2: 
		 *Step Description: 
			- Login with User B on a different browser
			- Go to Connections or Who's Online and request a connection with User A
		 *Input Data: 
		 *Expected Outcome: 
			- The number of notifications of User A is updated as soon as the connection request notification is received*/
		info("Log in with user2 and connect to user1");
		initNewDriver();
		newDriver.get(baseUrl);
		ManageLogInOut newmagAc = new ManageLogInOut(newDriver);
		HomePagePlatform newhp = new HomePagePlatform(newDriver);
		ConnectionsManagement newconnMag = new ConnectionsManagement(newDriver);
		isDriver = false;
		newmagAc.signIn(username2, password2);
		newhp.goToConnections();
		newconnMag.connectToAUser(username1);
		
		info("Switch to user1 and check intranet notification");
		isDriver = true;
		driver.navigate().refresh();
		navTool.checkNUmberOfNotificationsInBadge(true, "1");
		
		/*Step Number: 3
		 *Step Name: Step 3: 
		 *Step Description: 
			- With User A, click on the notification icon
			- Browse the notifications list
		 *Input Data: 
		 *Expected Outcome: 
			- The connection request notifications is displayed*/
		info("User 1 check notification in notification list");
		navTool.goToIntranetNotification();
		intraNot.checkConnectionRequestNotification(username2);
		/*Step Number: 4
		 *Step Name: Step 4: 
		 *Step Description: 
			- Come back to User B while User A is browsing the list
			- Go to the actiivty stream and mention User A
		 *Input Data: 
		 *Expected Outcome: 
			- While User A is browsing the notification list, the number of notification and the content of the list is updated as soon as a new notifications is received. The new notification is displayed at the top of the list.*/
		info("User2 mentions user1 in an activity");
		info("Check number of notifications in badge before ccreate more notifications");
		int numNotification = 0;
		isDriver = false;
		ActivityStream newhpAct = new ActivityStream(newDriver);
		newhp.goToHomePage();
		newhpAct.addActivity(username1, activity);
		newhpAct.checkActivity(activity);
		newDriver.close();
		
		info("User1 check notification in intranet notification");
		isDriver = true;
		info("Check number of notifications after add more notifications");
		driver.navigate().refresh();
		int newNumNotification = Integer.parseInt(waitForAndGetElement(navTool.ELEMENT_BADGE_NUMBER).getText().trim());
		assert (newNumNotification==(numNotification+1)):"Number of notification is not updated";
		navTool.goToIntranetNotification();
		intraNot.checkMentionNotification(username2, "1", activity.substring(0,2));
		
		info("Reset Data");
		magAc.signIn(DATA_USER1, DATA_PASS);
		navTool.goToUsersAndGroupsManagement();
		userAndGroup.deleteUser(username1);
		userAndGroup.deleteUser(username2);		
	}
}

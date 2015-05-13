package org.exoplatform.selenium.platform.social.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import java.awt.AWTException;

import org.testng.annotations.*;
public class SOC_Notifications_IntranetNotifications_NotificationTypes extends SOC_TestConfig_3{

	/**
	 * @author tult
	 * date 17/04/2015
	 */
	
	@BeforeMethod
	public void setUpBeforeMethod(){
		magAc.signOut();
		magAc.signIn(DATA_USER1,DATA_PASS);
	}
	
	/**
	 *<li> Case ID:122997.</li>
	 *<li> Test Case Name: Comment Intranet Notification.</li>
	 */
	@Test
	public  void test01_CommentIntranetNotification() {
		//Setup data test
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String password1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String email1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber() + mailSuffixData.getMailSuffixRandom();
		
		String activity = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String comment1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
	
		String userName = userData.getUserNameByIndex(0);
		/*Precondition:
		 	- User A and User B are connected
			- User A has posted an activity
			- User B has commented on User A activity
			- The notification "Someone comments on one of my activities" is activated in User A settings*/
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password1, email1, username1, username1);
		
		info ("Connect with user");
		hp.goToConnections();
		connMag.connectToAUser(username1);
		
		info("Add a activity");
		hp.goToHomePage();
		hpAct.addActivity(true, activity, false, "");
		hpAct.checkActivity(activity);
		
		info("user comments in John's activity");
		magAc.signIn(username1, password1);
		hp.goToConnections();
		connMag.acceptAConnection(userName);
		hp.goToHomePage();
		hpAct.addCommentUsingJavascript(activity, comment1);
		
		/*Step Number: 1
		 *Step Name: Step 1: Check comment notification in the notification list
		 *Step Description: 
			- Login with User A
			- Click the notifications icon in the top navigation
			- Check the notification list 
		 *Input Data: 
		 *Expected Outcome: 
			- A comment notification is displayed in the list
			- The activity message is : 
				$AVATAR
				$USER has commented on your activity
				$ACTIVITY
				$DATE

			Where : 
				- $AVATAR is the thumbnail of User B
				- $USER is User B
				- $ACTIVITY is the activity title/message
				- $DATE is the date of the activity*/
		info("Check comment notification in the notification list");
		magAc.signIn(DATA_USER1, DATA_PASS);
		navTool.goToIntranetNotification();
		intraNot.checkCommentNotification(username1, activity, "1", false, "", 1, "");
		
		/*Step Number: 2
		 *Step Name: Step 2: Check comment notification in activity Viewer
		 *Step Description: 
			- Click the notification area
		 *Input Data: 
		 *Expected Outcome: 
			- The activity is displayed in the activity viewer with all comment expanded.
			- The comment that this notification is about is highlighted.*/
		info("Check comment notification in activity Viewer");
		intraNot.gotoActivityViewer(username1, activity, "1", "", false, "", true);
		intraNot.checkCommentInActivityViewer(comment1, "", true);
		
		/*Step Number: 3
		 *Step Name: Step 3: Check comment notification in the View All
		 *Step Description: 
			- Click [View All]
		 *Input Data: 
		 *Expected Outcome: 
			- The comment notification is displayed in the View All page
			- The activity message is : 
				$AVATAR
				$USER has commented on your activity
				$ACTIVITY
				$DATE

			Where : 
				- $AVATAR is the thumbnail of User B
				- $USER is User B
				- $ACTIVITY is the activity title/message
				- $DATE is the date of the activity*/
		info("Check comment notification in the View All");
		navTool.goToIntranetNotification();
		intraNot.goToAllNotification();
		intraNot.checkCommentNotification(username1, activity, "1", false, "", 1, "");
		
		info("Reset Data");
		navTool.goToUsersAndGroupsManagement();
		userAndGroup.deleteUser(username1);
	}
	
	/**
	 *<li> Case ID:122999.</li>
	 *<li> Test Case Name: Comment notification when a new comment is pushed.</li>
	 */
	@Test
	public  void test02_CommentNotificationWhenANewNotificationIsPushed() {
		magAc.signIn(DATA_USER1, DATA_PASS);
		//Setup data test
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String password1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String email1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber() + mailSuffixData.getMailSuffixRandom();
		
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String password2 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String email2 = txData.getContentByArrayTypeRandom(1) + getRandomNumber() + mailSuffixData.getMailSuffixRandom();
		
		String activity = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String comment1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String comment2 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
	
		String userName = userData.getUserNameByIndex(0);
		
		/*Precondition:
		 	- User A, User B and User B are connected
			- User A has posted an activity
			- User B has commented on User A activity
			- The notification "Someone comments on one of my activities" is activated in User A settings*/
		info("Add 2 users");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password1, email1, username1, username1);
		addUserPage.addUser(username2, password2, email2, username2, username2);
		
		info ("Connect with 2 users");
		hp.goToConnections();
		connMag.connectToAUser(username1);
		connMag.connectToAUser(username2);
		
		info("Add a activity");
		hp.goToHomePage();
		hpAct.addActivity(true, activity, false, "");
		hpAct.checkActivity(activity);
		
		info("user1 comments in John's activity");
		magAc.signIn(username1, password1);
		hp.goToConnections();
		connMag.acceptAConnection(userName);
		hp.goToHomePage();
		hpAct.addCommentUsingJavascript(activity, comment1);
			
		/*Step Number: 1
		 *Step Name: Step 1: Check comment notification of user 1
		 *Step Description: 
			- Login with User A
			- Click the notifications icon in the top navigation
			- Check the notification list (keep the notification unread)
		 *Input Data: 
		 *Expected Outcome: 
			- A comment notification is displayed in the list (from User B)*/
		info("Check comment notification in the notification list");
		magAc.signIn(DATA_USER1, DATA_PASS);
		navTool.goToIntranetNotification();
		intraNot.checkCommentNotification(username1, activity, "1", true, "1", 1, "");
		
		/*Step Number: 2
		 *Step Name: Step 2: Check comment notification of user 2
		 *Step Description: 
			- With User C, comment the activity of User A
			- Check the notifications list
		 *Input Data: 
		 *Expected Outcome: 
			- The Comment notification is listed/merged in the same previous notification (step 1)
			- The notification is displayed at the top of the list*/
		info("user2 comments in John's activity");
		magAc.signIn(username2, password2);
		hp.goToConnections();
		connMag.acceptAConnection(userName);
		hp.goToHomePage();
		hpAct.addCommentUsingJavascript(activity, comment2);
		
		info("Check comment notification in the notification list");
		magAc.signIn(DATA_USER1, DATA_PASS);
		navTool.goToIntranetNotification();
		intraNot.checkCommentNotification(username1, activity, "1", true, "1", 2, username2);
		
		/*Step Number: 3
		 *Step Name: Step 3: Check comment notification in View All
		 *Step Description: 
			- Check notification message in the list and in View All
		 *Input Data: 
		 *Expected Outcome: 
			- The notification message is : 
				LAST_AVATAR
				$USER_LIST have commented on your activity
				$ACTIVITY
				$DATE
			Where : 
				- $LAST_AVATAR is the thumbnail of User C
				- $USER_LIST is User B, User C
				- $ACTIVITY is the activity message/title
				- $DATE is the date of the last notification of User C*/
		info("Check comment notification in the View All");
		intraNot.goToAllNotification();
		intraNot.checkCommentNotification(username1, activity, "1", true, "2", 2, username2);
		
		info("Reset Data");
		navTool.goToUsersAndGroupsManagement();
		userAndGroup.deleteUser(username1);
		userAndGroup.deleteUser(username2);
	}
	
	/**
	 *<li> Case ID:122998.</li>
	 *<li> Test Case Name: Comments Intranet Notification merged.</li>
	 */
	@Test
	public  void test03_CommentsIntranetNotificationMerged() {
		magAc.signIn(DATA_USER1, DATA_PASS);
		//Setup data test
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String password1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String email1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber() + mailSuffixData.getMailSuffixRandom();
		
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String password2 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String email2 = txData.getContentByArrayTypeRandom(1) + getRandomNumber() + mailSuffixData.getMailSuffixRandom();
		
		String username3 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String password3 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String email3 = txData.getContentByArrayTypeRandom(1) + getRandomNumber() + mailSuffixData.getMailSuffixRandom();
		
		
		String activity = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String comment1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String comment2 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String comment3 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String comment4 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
	
		String userName = userData.getUserNameByIndex(0);
		
		/*Precondition:
		 	- User A is connected with User B, User C and User D
			- User A has posted an activity
			- User B has commented on User A activity
			- User C has commented on User A activity
			- User D has commented on User A activity
			- User B has commented again on User A activity
			- The notification "Someone comments on one of my activities" is activated in User A settings*/
		info("Add 3 users");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password1, email1, username1, username1);
		addUserPage.addUser(username2, password2, email2, username2, username2);
		addUserPage.addUser(username3, password3, email3, username3, username3);
		
		info ("Connect with 3 users");
		hp.goToConnections();
		connMag.connectToAUser(username1);
		connMag.connectToAUser(username2);
		connMag.connectToAUser(username3);
		
		info("Add a activity");
		hp.goToHomePage();
		hpAct.addActivity(true, activity, false, "");
		hpAct.checkActivity(activity);
		
		info("user1 comments in John's activity");
		magAc.signIn(username1, password1);
		hp.goToConnections();
		connMag.acceptAConnection(userName);
		hp.goToHomePage();
		hpAct.addCommentUsingJavascript(activity, comment1);
		
		info("user2 comments in John's activity");
		magAc.signIn(username2, password2);
		hp.goToConnections();
		connMag.acceptAConnection(userName);
		hp.goToHomePage();
		hpAct.addCommentUsingJavascript(activity, comment2);
		
		info("user3 comments in John's activity");
		magAc.signIn(username3, password3);
		hp.goToConnections();
		connMag.acceptAConnection(userName);
		hp.goToHomePage();
		hpAct.addCommentUsingJavascript(activity, comment3);
		
		info("user1 comments in John's activity");
		magAc.signIn(username1, password1);
		hpAct.addCommentUsingJavascript(activity, comment4);
		
		/*Step Number: 1
		 *Step Name: Step 1: Check comment notification in notification list
		 *Step Description: 
			- Login with User A
			- Click the notifications icon in the top navigation
			- Check the notification list
		 *Input Data: 
		 *Expected Outcome: 
			- A comment notification is displayed in the list
			- The activity message is : 
				$LAST_AVATAR
				$LAST2_USERS and $COUNT more have commented on your activity $ACTIVITY
				$DATE
			Where : 
				- $LAST_AVATAR is the thumbnail of User B
				- $LAST2_USERS  is User D, User B
				- $COUNT is 2
				- $ACTIVITY is the activity title/message
				- $DATE is the date of the activity*/
		info("Check comment notification in the notification list");
		magAc.signIn(DATA_USER1, DATA_PASS);
		navTool.goToIntranetNotification();
		intraNot.checkCommentNotification(username3, activity, "1", true, "1", 3, username1);
		
		/*Step Number: 2
		 *Step Name: Step 2: Check comment notification of in activity viewer
		 *Step Description: 
			- Click the notification area
		 *Input Data: 
		 *Expected Outcome: 
			- The activity is displayed in the activity viewer with all comment expanded.*/
		info("Check comment notification in activity Viewer");
		intraNot.gotoActivityViewer(username3, activity, "1", "", false, username1, false, false, false, false, false, false, true);
		intraNot.checkCommentInActivityViewer(comment1, username1, false);
		intraNot.checkCommentInActivityViewer(comment2, username2, false);
		intraNot.checkCommentInActivityViewer(comment3, username3, false);
		intraNot.checkCommentInActivityViewer(comment4, username1, false);
		

		/*Step Number: 3
		 *Step Name: Step 3: Check comment notification of in View All
		 *Step Description: 
			- Click [View All] from the notification list
		 *Input Data: 
		 *Expected Outcome: 
			- The comment notification is displayed in the View All page
			- The activity message is : 
				$LAST_AVATAR
				$LAST2_USERS and $COUNT more have commented on your activity $ACTIVITY
				$DATE
			Where : 
				- $LAST_AVATAR is the thumbnail of User B
				- $LAST2_USERS  is User D, User B
				- $COUNT is 2
				- $ACTIVITY is the activity title/message
				- $DATE is the date of the activity*/
		info("Check comment notification in the View All");
		navTool.goToIntranetNotification();
		intraNot.goToAllNotification();
		intraNot.checkCommentNotification(username3, activity, "1", true, "2", 3, username1);
		
		info("Reset Data");
		navTool.goToUsersAndGroupsManagement();
		userAndGroup.deleteUser(username1);
		userAndGroup.deleteUser(username2);
		userAndGroup.deleteUser(username3);
	}
	
	/**
	 *<li> Case ID:122982.</li>
	 *<li> Test Case Name: Connection Request Intranet notification (Accept).</li>
	 */
	@Test
	public  void test04_ConnectionRequestIntranetNotification_Accept() {
		magAc.signIn(DATA_USER1, DATA_PASS);
		//Setup data test
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String password1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String email1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber() + mailSuffixData.getMailSuffixRandom();
	
		String userName = userData.getUserNameByIndex(0);
		String fullName = userData.getFullNameByIndex(0);
		
		/*Precondition:
		 	- The notification "Someone sends me a connection request" is activated in User Settings
			- User A sent a connection request to User B*/
		info("Add a user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password1, email1, username1, username1);
	
		info ("Connect with user");
		hp.goToConnections();
		connMag.connectToAUser(username1);
		
		/*Step Number: 1
		 *Step Name: Step 1: Check notification list
		 *Step Description: 
			- Login with User B
			- Click the notifications icon
			- Check the notification list
		 *Input Data: 
		 *Expected Outcome: 
			- The list includes a Connection Request notifications.*/
		/*Step Number: 2
		 *Step Name: Step 2: Check notification message
		 *Step Description: 
			- Check the notification message
		 *Input Data: 
		 *Expected Outcome: 
			- The notification message is :  
				- $AVATAR 
				- $USER wants to connect with you
				- $DATE 
				- 2 actions : [Accept] | [Refuse]
			Where : 
				- $AVATAR is thumbnail of User A
				- $USER is User A
				- $DATE is the date of the notification*/
		info("Check connection request notification in notification list");
		magAc.signIn(username1, password1);
		navTool.goToIntranetNotification();
		intraNot.checkConnectionRequestNotification(fullName);
		
		/*Step Number: 3
		 *Step Name: Step 3: Check notification area
		 *Step Description: 
			- Click the notification area
		 *Input Data: 
		 *Expected Outcome: 
			- User B is redirected to the profile of User A*/
		/*Step Number: 4
		 *Step Name: Step 4: Accept
		 *Step Description: 
			- Click [Accept]
		 *Input Data: 
		 *Expected Outcome: 
			- The connection request is accepted (User A and User B are connected)
			- The notification message is updated to :
				$AVATAR
				You are connected with $USER
				$DATE*/
		intraNot.goToUserProfileFromIntranetNotificationWithConnection(fullName, userName, true, "1");
		
		info("Reset Data");
		magAc.signIn(DATA_USER1, DATA_PASS);
		navTool.goToUsersAndGroupsManagement();
		userAndGroup.deleteUser(username1);
	}
	
	/**
	 *<li> Case ID:122983.</li>
	 *<li> Test Case Name: Connection Request Intranet notification (Refuse).</li>
	 */
	@Test
	public  void test05_ConnectionRequestIntranetNotification_Refuse() {
		magAc.signIn(DATA_USER1, DATA_PASS);
		//Setup data test
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String password1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String email1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber() + mailSuffixData.getMailSuffixRandom();
	
		String userName = userData.getUserNameByIndex(0);
		String fullName = userData.getFullNameByIndex(0);
		/*Precondition:
		 	- The notification "Someone sends me a connection request" is activated in User Settings
			- User A sent a connection request to User B*/
		info("Add user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password1, email1, username1, username1);
	
		magAc.signIn(DATA_USER2, DATA_PASS);
		info ("Connect with user");
		hp.goToConnections();
		connMag.connectToAUser(username1);
		
		magAc.signIn(DATA_USER1, DATA_PASS);
		info ("Connect with user");
		hp.goToConnections();
		connMag.connectToAUser(username1);
		
		/*Step Number: 1
		 *Step Name: Step 1: Check notification list
		 *Step Description: 
			- Login with User B
			- Click the notifications icon
			- Check the notification list
		 *Input Data: 
		 *Expected Outcome: 
			- The list includes a Connection Request notifications.*/
		/*Step Number: 2
		 *Step Name: Step 2: Check notification message
		 *Step Description: 
			- Check the notification message
		 *Input Data: 
		 *Expected Outcome: 
			- The notification message is :  
				- $AVATAR 
				- $USER wants to connect with you
				- $DATE 
				- 2 actions : [Accept] | [Refuse]

			Where : 
				- $AVATAR is thumbnail of User A
				- $USER is User A
				- $DATE is the date of the notification*/
		info("check connection request in intranet notification list");
		magAc.signIn(username1, password1);
		navTool.goToIntranetNotification();
		intraNot.checkConnectionRequestNotification(fullName);
		
		/*Step Number: 3
		 *Step Name: Step 3: Check View All
		 *Step Description: 
			- Click [View All] from the notification list
		 *Input Data: 
		 *Expected Outcome: 
			- The notification is displayed in the page View All*/
		intraNot.goToAllNotification();
		intraNot.checkConnectionRequestNotification(fullName);
		
		/*Step Number: 4
		 *Step Name: Step 4: Refuse
		 *Step Description: 
			- Click [Refuse]
		 *Input Data: 
		 *Expected Outcome: 
			- The connection request is denied (User A and User B are not  connected)
			- The notification message is automatically hidden from the list 
			- The notification message is not available in the View All page..*/
		navTool.goToIntranetNotification();
		intraNot.goToUserProfileFromIntranetNotificationWithConnection(fullName, userName, false, "1");
		
		info("Reset Data");
		magAc.signIn(DATA_USER1, DATA_PASS);
		navTool.goToUsersAndGroupsManagement();
		userAndGroup.deleteUser(username1);
	}
	
	/**
	 *<li> Case ID:123000.</li>
	 *<li> Test Case Name: Like Intranet Notification.</li>
	 */
	@Test 
	public  void test06_LikeIntranetNotification() {
		magAc.signIn(DATA_USER1, DATA_PASS);
		//Setup data test
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String password1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String email1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber() + mailSuffixData.getMailSuffixRandom();
		
		String activity = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		
		String userName = userData.getUserNameByIndex(0);
		/*Precondition:
		 	- User A and User B are connected
			- User A has posted an activity
			- User B has liked User A activity
			- The notification "Someone likes one of my activities" is activated in the user settings*/
		info("Add User");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password1, email1, username1, username1);
		
		info ("Connect with user");
		hp.goToConnections();
		connMag.connectToAUser(username1);
		
		info("Add a activity");
		hp.goToHomePage();
		hpAct.addActivity(true, activity, false, "");
		hpAct.checkActivity(activity);
		
		info("user likes John's activity");
		magAc.signIn(username1, password1);
		hp.goToConnections();
		connMag.acceptAConnection(userName);
		hp.goToHomePage();
		hpAct.likeOrUnlikeActivity(activity);
		
		/*Step Number: 1
		 *Step Name: Step 1: Check notifications list
		 *Step Description: 
			- Login with User A
			- Click the notifications icon in the top navigation
			- Check the notification list
		 *Input Data: 
		 *Expected Outcome: 
			- A Like notification is displayed in the list
			- The activity message is : 
				$AVATAR
				$USER likes your activity.
				$ACTIVITY
					$DATE
			Where : 
				- $AVATAR is the thumbnail of User B
				- $USER is User B
				- $ACTIVITY is the activity title/message
				- $DATE is the date of the activity*/
		info("Check Like notification in intranet notification");
		magAc.signIn(DATA_USER1, DATA_PASS);
		navTool.goToIntranetNotification();
		intraNot.checkLikeNotification(username1, activity, "1", false, "", 1, "", "");
		
		/*Step Number: 2
		 *Step Name: Step 2: Read the notification
		 *Step Description: 
			- Click the notification area
		 *Input Data: 
		 *Expected Outcome: 
			- The activity is displayed in the activity viewer with all comment expanded.*/
		info("Check comment notification in activity Viewer");
		intraNot.gotoActivityViewer(username1, activity, "1", "", false, "", false, false, false, false, true);
		intraNot.checkLikeInActivityViewr("1");
		
		/*Step Number: 3
		 *Step Name: Step 3: Check notification message
		 *Step Description: 
			- Click [View All] from the notification list
		 *Input Data: 
		 *Expected Outcome: 
			- A Like notification is displayed in the list
			- The activity message is : 
				$AVATAR
				$USER likes your activity.
				$ACTIVITY
				$DATE
			Where : 
				- $AVATAR is the thumbnail of User B
				- $USER is User B
				- $ACTIVITY is the activity title/message
				- $DATE is the date of the activity*/
		navTool.goToIntranetNotification();
		intraNot.goToAllNotification();
		intraNot.checkLikeNotification(username1, activity, "1", false, "", 1, "", "");
		
		info("Reset Data");
		navTool.goToUsersAndGroupsManagement();
		userAndGroup.deleteUser(username1);
	}
	
	/**
	 *<li> Case ID:123001.</li>
	 *<li> Test Case Name: Like Intranet Notification merged.</li>
	 */
	@Test 
	public  void test07_LikeIntranetNotificationMerged(){
		magAc.signIn(DATA_USER1, DATA_PASS);
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String password1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String email1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber() + mailSuffixData.getMailSuffixRandom();
		
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String password2 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String email2 = txData.getContentByArrayTypeRandom(1) + getRandomNumber() + mailSuffixData.getMailSuffixRandom();
		
		String username3 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String password3 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String email3 = txData.getContentByArrayTypeRandom(1) + getRandomNumber() + mailSuffixData.getMailSuffixRandom();
		
		String username4 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String password4 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String email4 = txData.getContentByArrayTypeRandom(1) + getRandomNumber() + mailSuffixData.getMailSuffixRandom();
		
		String activity = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		
		String userName = userData.getUserNameByIndex(0);
		/*Precondition:
		 	- User A is connected with User B, User C, User D and User E
			- User A has posted an activity
			- User B has liked User A activity
			- User C has liked User A activity
			- User D has liked User A activity
			- User E has liked User A activity
			- The notification "Someone likes one of my activities" is activated in the user settings*/
		info("Add 4 users");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password1, email1, username1, username1);
		addUserPage.addUser(username2, password2, email2, username2, username2);
		addUserPage.addUser(username3, password3, email3, username3, username3);
		addUserPage.addUser(username4, password4, email4, username4, username4);
		
		info ("Connect with 4 users");
		hp.goToConnections();
		connMag.connectToAUser(username1);
		connMag.connectToAUser(username2);
		connMag.connectToAUser(username3);
		connMag.connectToAUser(username4);
		
		info("Add a activity");
		hp.goToHomePage();
		hpAct.addActivity(true, activity, false, "");
		hpAct.checkActivity(activity);
		
		info("user 1 likes John's activity");
		magAc.signIn(username1, password1);
		hp.goToConnections();
		connMag.acceptAConnection(userName);
		hp.goToHomePage();
		hpAct.likeOrUnlikeActivity(activity);
		
		info("user 2 likes John's activity");
		magAc.signIn(username2, password2);
		hp.goToConnections();
		connMag.acceptAConnection(userName);
		hp.goToHomePage();
		hpAct.likeOrUnlikeActivity(activity);
		
		info("user 3 likes John's activity");
		magAc.signIn(username3, password3);
		hp.goToConnections();
		connMag.acceptAConnection(userName);
		hp.goToHomePage();
		hpAct.likeOrUnlikeActivity(activity);
		
		info("user 4 likes John's activity");
		magAc.signIn(username4, password4);
		hp.goToConnections();
		connMag.acceptAConnection(userName);
		hp.goToHomePage();
		hpAct.likeOrUnlikeActivity(activity);
		
		/*Step Number: 1
		 *Step Name: Step 1: Check notifications list
		 *Step Description: 
			- Login with User A
			- Click the notifications icon in the top navigation
			- Check the notification list
		 *Input Data: 
		 *Expected Outcome: 
			- A Like notification is displayed in the list
			- The activity message is : 
				$LAST_AVATAR
				$LAST2_USERS and $COUNT more like your activity. $ACTIVITY
				$DATE
			Where : 
				- $LAST_AVATAR is the thumbnail of User E
				- $LAST2_USERS  is User E and User D
				- $COUNT is 2
				- $ACTIVITY is the activity title/message
				- $DATE is the date of the activity*/
		info("Check Like notification in intranet notification");
		magAc.signIn(DATA_USER1, DATA_PASS);
		navTool.goToIntranetNotification();
		intraNot.checkLikeNotification(username3, activity, "1", true, "1", 3, username4, "2");
		
		/*Step Number: 2
		 *Step Name: Step 2: Read the notification
		 *Step Description: 
			- Click the notification area
		 *Input Data: 
		 *Expected Outcome: 
			- The activity is displayed in the activity viewer with all comment expanded.*/
		info("Check comment notification in activity Viewer");
		intraNot.gotoActivityViewer(username3, activity, "1", "", false, username4, false, false, false, false, false, true);
		intraNot.checkLikeInActivityViewr("4");
		
		/*Step Number: 3
		 *Step Name: Step 3: Check notification message
		 *Step Description: 
			- Click [View All] from the notifications list
		 *Input Data: 
		 *Expected Outcome: 
			- A Like notification is displayed in View All page
			- The activity message is : 
				$LAST_AVATAR
				$LAST2_USERS and $COUNT more like your activity. $ACTIVITY
				$DATE
			Where : 
				- $LAST_AVATAR is the thumbnail of User E
				- $LAST2_USERS  is User E and User D
				- $COUNT is 2
				- $ACTIVITY is the activity title/message
				- $DATE is the date of the activity.*/
		navTool.goToIntranetNotification();
		intraNot.goToAllNotification();
		intraNot.checkLikeNotification(username3, activity, "1", true, "2", 3, username4, "2");
			
		info("Reset Data");
		navTool.goToUsersAndGroupsManagement();
		userAndGroup.deleteUser(username1);
		userAndGroup.deleteUser(username2);
		userAndGroup.deleteUser(username3);
		userAndGroup.deleteUser(username4);
	}
	
	/**
	 *<li> Case ID:123002.</li>
	 *<li> Test Case Name: Like Notification when a new like is pushed.</li>
	 */
	@Test 
	public  void test08_NotificationWhenANewLikeIsPushed(){
		magAc.signIn(DATA_USER1, DATA_PASS);
		//Setup data test
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String password1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String email1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber() + mailSuffixData.getMailSuffixRandom();
		
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String password2 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String email2 = txData.getContentByArrayTypeRandom(1) + getRandomNumber() + mailSuffixData.getMailSuffixRandom();
		
		String activity = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
	
		String userName = userData.getUserNameByIndex(0);
		/*Precondition:
		 	- User A, User B, User C are connected
			- User A has posted an activity
			- User B has liked User A activity
			- The notification "Someone likes one of my activities" is activated in the user settings*/
		info("Add 2 users");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password1, email1, username1, username1);
		addUserPage.addUser(username2, password2, email2, username2, username2);
		
		info ("Connect with 2 users");
		hp.goToConnections();
		connMag.connectToAUser(username1);
		connMag.connectToAUser(username2);
		
		info("Add a activity");
		hp.goToHomePage();
		hpAct.addActivity(true, activity, false, "");
		hpAct.checkActivity(activity);
	
		info("user 1 likes John's activity");
		magAc.signIn(username1, password1);
		hp.goToConnections();
		connMag.acceptAConnection(userName);
		hp.goToHomePage();
		hpAct.likeOrUnlikeActivity(activity);
		
		/*Step Number: 1
		 *Step Name: Step 1: Check notifications list
		 *Step Description: 
			- Login with User A
			- Click the notifications icon in the top navigation
			- Check the notification list (keep the notification unread)
		 *Input Data: 
		 *Expected Outcome: 
			- A Like notification is displayed in the list.*/
		info("Check Like notification in intranet notification");
		magAc.signIn(DATA_USER1, DATA_PASS);
		navTool.goToIntranetNotification();
		intraNot.checkLikeNotification(username1, activity, "1", true, "1", 1, "", "");
		
		/*Step Number: 2
		 *Step Name: Step 2: Push a new like notification
		 *Step Description: 
			- With User C, like the activity of User A
			- Check the notifications list
		 *Input Data: 
		 *Expected Outcome: 
			- The Like notification is listed/merged in the same previous notification (step 1)
			- The notification is displayed at the top of the list.*/
		info("user 2 likes John's activity");
		magAc.signIn(username2, password2);
		hp.goToConnections();
		connMag.acceptAConnection(userName);
		hp.goToHomePage();
		hpAct.likeOrUnlikeActivity(activity);
		
		info("Check Like notification in intranet notification");
		magAc.signIn(DATA_USER1, DATA_PASS);
		navTool.goToIntranetNotification();
		intraNot.checkLikeNotification(username1, activity, "1", true, "1", 2, username2, "");

		/*Step Number: 3
		 *Step Name: Step 3: check the message of the Like notification
		 *Step Description: 
			- Check notification message in the notifications list and View All page
		 *Input Data: 
		 *Expected Outcome: 
			- The notification message is : 
				LAST_AVATAR
				$USER_LIST like your activity
				$ACTIVITY
				$DATE
			Where : 
				- $LAST_AVATAR is the thumbnail of User C
				- $USER_LIST is User B, User C
				- $ACTIVITY is the activity message/title
				- $DATE is the date of the last notification of User C*/
		intraNot.goToAllNotification();
		intraNot.checkLikeNotification(username1, activity, "1", true, "2", 2, username2, "");
			
		info("Reset Data");
		navTool.goToUsersAndGroupsManagement();
		userAndGroup.deleteUser(username1);
		userAndGroup.deleteUser(username2);
	}
	
	/**
	 *<li> Case ID:122995.</li>
	 *<li> Test Case Name: Mention Intranet notifications (in activity message).</li>
	 * @throws AWTException 
	 */
	@Test 
	public  void test09_MentionIntranetNotification_InactivityMessage() throws AWTException {
		magAc.signIn(DATA_USER1, DATA_PASS);
		//Setup data test
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String password1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String email1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber() + mailSuffixData.getMailSuffixRandom();
		
		String activity = txData.getContentByArrayTypeRandom(1)  + getRandomNumber();
	
		String userName = userData.getUserNameByIndex(0);
		String firstName = userInfoData.getFirstNameByIndex(3);
		String fullName = userData.getFullNameByIndex(0);
		/*Precondition:
		 	- User A has mentioned User B directly in the activity message*/
		info("Add user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password1, email1, username1, username1);
		
		info ("Connect with user");
		hp.goToConnections();
		connMag.connectToAUser(username1);
		
		info("User accepts Request notification and mention John in activity");
		magAc.signIn(username1, password1);
		hp.goToConnections();
		connMag.acceptAConnection(userName);
		hp.goToHomePage();
		hpAct.addActivity(firstName, activity);
		hpAct.checkActivity(activity);
		
		/*Step Number: 1
		 *Step Name: Step 1: 
		 *Step Description: 
			- Login with User B
			- Click the notification icons in the top navigation
			- Check the notification list
		 *Input Data: 
		 *Expected Outcome: 
			- The Mention notification is displayed in the list
			- The notification message is : $AVATAR
				$USER has mentioned you $ACTIVITY
				$DATE
			Where :
				- $AVATAR is the thumbnail of User A
				- $USER is User A
				- $ACTIVITY is the activity title/message
				- $DATE is the date of the notification*/
		info ("Check Mention Intranet Notification in notification list");
		magAc.signIn(DATA_USER1, DATA_PASS);
		navTool.goToIntranetNotification();
		intraNot.checkMentionNotification(username1, "1", activity);
		
		/*Step Number: 2
		 *Step Name: Step 2: 
		 *Step Description: 
			- Click the notification message
		 *Input Data: 
		 *Expected Outcome: 
			- The activity is displayed in the activity viewer with all comments expanded.*/
		info("check notification in activity viewer");
		intraNot.gotoActivityViewer(username1, "", "1", "", false, fullName, false, true);
		intraNot.checkMentionInActivityViewer(activity);
		
		/*Step Number: 3
		 *Step Name: Step 3: 
		 *Step Description: 
			- Click [View All]
		 *Input Data: 
		 *Expected Outcome: 
			- The activity is displayed in View All page
			- The notification message is : $AVATAR
				$USER has mentioned you $ACTIVITY
				$DATE
			Where :
				- $AVATAR is the thumbnail of User A
				- $USER is User A
				- $ACTIVITY is the activity title/message
				- $DATE is the date of the notification.*/
		info("Check notification in view all");
		navTool.goToIntranetNotification();
		intraNot.goToAllNotification();
		intraNot.checkMentionNotification(username1, "1", activity);
		
		info("Reset Data");
		navTool.goToUsersAndGroupsManagement();
		userAndGroup.deleteUser(username1);
	}
	
	/**
	 *<li> Case ID:122996.</li>
	 *<li> Test Case Name: Mention Intranet notifications (in comment).</li>
	 */
	@Test  
	public  void test10_MentionIntranetNotification_InComment() {
		magAc.signIn(DATA_USER1, DATA_PASS);
		//Setup data test
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String password1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String email1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber() + mailSuffixData.getMailSuffixRandom();
		
		String activity = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String comment = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
	
		String userName = userData.getUserNameByIndex(0);
		String firstName = userInfoData.getFirstNameByIndex(3);
		String fullName = userData.getFullNameByIndex(0);
		/*Precondition:
		 	- A wiki activity is generated (create a new page) or a content activity or an event activity
			- User A has mentioned User B directly in a comment*/
		info("Add user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password1, email1, username1, username1);
		
		info ("Connect with user");
		hp.goToConnections();
		connMag.connectToAUser(username1);
		
		info("User accepts Request notification and mention John in comment");
		magAc.signIn(username1, password1);
		hp.goToConnections();
		connMag.acceptAConnection(userName);
		hp.goToHomePage();
		hpAct.addActivity(true, activity, false, "");
		hpAct.checkActivity(activity);
		hpAct.addCommentWithMentionUser(activity, firstName, comment);
		
		/*Step Number: 1
		 *Step Name: Step 1: 
		 *Step Description: 
			- Login with User B
			- Click the notification icons in the top navigation
			- Check the notification list
		 *Input Data: 
		 *Expected Outcome: 
			- The Mention notification is displayed in the list
			- The notification message is : $AVATAR
				$USER has mentioned you $ACTIVITY
				$DATE
			Where :
				- $AVATAR is the thumbnail of User A
				- $USER is User A
				- $ACTIVITY is the name of the wiki page
				- $DATE is the date of the notification.*/
		info ("Check Mention Intranet Notification in notification list");
		magAc.signIn(DATA_USER1, DATA_PASS);
		navTool.goToIntranetNotification();
		intraNot.checkMentionNotification(username1, "1", activity);
		
		/*Step Number: 2
		 *Step Name: Step 2: 
		 *Step Description: 
			- Click the notification
		 *Input Data: 
		 *Expected Outcome: 
			- The activity is displayed in the activity viewer with all comments expanded. As the mention is made in a comment, this comment is highlighted.*/
		intraNot.gotoActivityViewer(username1, "", "1", "", false, fullName, false, true);
		intraNot.checkMentionInActivityViewer(activity);
		
		/*Step Number: 3
		 *Step Name: Step 3: 
		 *Step Description: 
			- Go to [View All]
		 *Input Data: 
		 *Expected Outcome: 
			- The Mention notification is displayed in the page View All
			- The notification message is : $AVATAR
				$USER has mentioned you $ACTIVITY
				$DATE
			Where :
				- $AVATAR is the thumbnail of User A
				- $USER is User A
				- $ACTIVITY is the name of the wiki page
				- $DATE is the date of the notification.*/
		navTool.goToIntranetNotification();
		intraNot.goToAllNotification();
		intraNot.checkMentionNotification(username1, "1", activity);
		
		info("Reset Data");
		navTool.goToUsersAndGroupsManagement();
		userAndGroup.deleteUser(username1);
	}
	
	/**
	 *<li> Case ID:123003.</li>
	 *<li> Test Case Name: Post on my Stream Intranet notification.</li>
	 */
	@Test 
	public  void test11_PostOnMyStreamIntranetNotification() {
		magAc.signIn(DATA_USER1, DATA_PASS);
		//Setup data test
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String password1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String email1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber() + mailSuffixData.getMailSuffixRandom();
		
		String activity = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
	
		String userName = userData.getUserNameByIndex(0);
		String fullName = userData.getFullNameByIndex(0);
		/*Precondition:
		 	- User A has posted an activity on User B activity stream
			- The notification "Someone posts a message on my activity stream" is activated in the user settings*/
		info("add user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password1, email1, username1, username1);
		
		info ("Connect with user");
		hp.goToConnections();
		connMag.connectToAUser(username1);
		
		info("User accepts Request notification and post an activity in John's activity stream");
		magAc.signIn(username1, password1);
		hp.goToConnections();
		connMag.acceptAConnection(userName);
		connMag.goToUser(fullName);
		navTool.goToMyActivities();
		hpAct.addActivity(true, activity, false, "");
		hpAct.checkActivity(activity);
		
		/*Step Number: 1
		 *Step Name: Step 1: Check notification list
		 *Step Description: 
			- Login with User B
			- Click the notifications icon in the top navigation
			- Check the notifications list
		 *Input Data: 
		 *Expected Outcome: 
			- A Post on my Stream notifications is displayed in the list
			- The notification message is :
				$AVATAR
				$USER has posted on your activity stream
				$ACTIVITY
				$DATE
			Where : 
				- $AVATAR is the thumbnail of User A
				- $USER is User A
				- $ACTIVITY is the activity title / message
				- $DATE is the date of the notification.*/
		info ("Check Notification in notification list");
		magAc.signIn(DATA_USER1, DATA_PASS);
		navTool.goToIntranetNotification();
		intraNot.checkPostActivityNotification(username1, activity, "1");
		
		/*Step Number: 2
		 *Step Name: Step 2: Click notification message
		 *Step Description: 
			- Click the notification message
		 *Input Data: 
		 *Expected Outcome: 
			- The activity is displayed in the activity viewer with all comments expanded.*/
		info("Check in activity viewer");
		intraNot.gotoActivityViewer(username1, activity, "1", "", false, "", false, false, true);
		intraNot.checkMentionInActivityViewer(activity);
		
		/*Step Number: 3
		 *Step Name: Step 3: Check View All
		 *Step Description: 
			-  Click [View All] in the notification list
		 *Input Data: 
		 *Expected Outcome: 
			- A Post on my Stream notifications is displayed in View All page
			- The notification message is :
				$AVATAR
				$USER has posted on your activity stream
				$ACTIVITY
				$DATE
			Where : 
				- $AVATAR is the thumbnail of User A
				- $USER is User A
				- $ACTIVITY is the activity title / message
				- $DATE is the date of the notification*/
		info("Check notification in view all");
		navTool.goToIntranetNotification();
		intraNot.goToAllNotification();
		intraNot.checkPostActivityNotification(username1, activity, "1");
		
		info("Reset Data");
		navTool.goToUsersAndGroupsManagement();
		userAndGroup.deleteUser(username1);
	}
	
	/**
	 *<li> Case ID:123004.</li>
	 *<li> Test Case Name: Post on my Space Intranet notification.</li>
	 */
	@Test 
	public  void test12_PostOnMySpaceIntranetNotification() {
		magAc.signIn(DATA_USER1, DATA_PASS);
		//Setup data test
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String password1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String email1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber() + mailSuffixData.getMailSuffixRandom();
		
		String space = txData.getContentByArrayTypeRandom(1) + getRandomNumber();  
		
		String activity = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
	
		/*Precondition:
		 	- User A and User B are members of Space 1
			- User B has posted in Space 1
			- The notification "An activity is posted on one of my spaces" is activated in the user settings*/
		info("Add user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password1, email1, username1, username1);
		
		info("Add new space and ivite user");
		hp.goToAllSpace();
		spaMg.goToCreateSpace();
		spaMg.addNewSpaceSimple(space, space);
		spaHome.goToSettingTab();
		setSpaceMg.inviteUser(username1);
			
		info("User accepts Request notification and mention John in activity");
		magAc.signIn(username1, password1);
		hp.goToAllSpace();
		spaMg.acceptAInvitation(space);
		
		info("user posted an activity inspace");
		hpAct.addActivity(true, activity, false, "");
		hpAct.checkActivity(activity);
		
		/*Step Number: 1
		 *Step Name: Step 1: Check notification list
		 *Step Description: 
			- Login with User B
			- Click the notifications icon in the top navigation
			- Check the notifications list
		 *Input Data: 
		 *Expected Outcome: 
			- A Post on my Space notifications is displayed in the list
			- The notification message is :
				$AVATAR
				$USER has posted an activity in the $SPACE space.
				$ACTIVITY
				$DATE
			Where : 
				- $AVATAR is the thumbnail of User B
				- $USER is User B
				- $SPACE is Space 1
				- $ACTIVITY is the activity title / message
				- $DATE is the date of the notification*/
		info ("Check Notification in notification list");
		magAc.signIn(DATA_USER1, DATA_PASS);
		navTool.goToIntranetNotification();
		intraNot.checkPostActivityInSpaceNotification(username1, space, activity, "1");
		
		/*Step Number: 2
		 *Step Name: Step 2: Check notification message
		 *Step Description: 
			- Check the notification message
		 *Input Data: 
		 *Expected Outcome: 
			- The activity is displayed in the activity viewer with all comments expanded.*/
		info("check notification in activity viewer");
		intraNot.gotoActivityViewer(username1, activity, "1", space, false, "", false, false, false, true);
		intraNot.checkMentionInActivityViewer(activity);
		
		/*Step Number: 3
		 *Step Name: Step 3: Check View All
		 *Step Description: 
			- Click [View All] in the notifications list
		 *Input Data: 
		 *Expected Outcome: 
			- A Post on my Space notifications is displayed in the View All page
			- The notification message is :
				$AVATAR
				$USER has posted an activity in the $SPACE space.
				$ACTIVITY
				$DATE
			Where : 
				- $AVATAR is the thumbnail of User B
				- $USER is User B
				- $SPACE is Space 1
				- $ACTIVITY is the activity title / message
				- $DATE is the date of the notification*/
		info("Check in view all");
		navTool.goToIntranetNotification();
		intraNot.goToAllNotification();
		intraNot.gotoActivityViewer(username1, activity, "1", space, false, "", false, false, false, true);
		
		info("Reset Data");
		navTool.goToUsersAndGroupsManagement();
		userAndGroup.deleteUser(username1);
		hp.goToAllSpace();
		spaMg.searchSpace(space, "");
		spaMg.deleteSpace(space, false);
	}
	
	/**
	 *<li> Case ID:122984.</li>
	 *<li> Test Case Name: Space Invitation Intranet notification (Accept).</li>
	 */
	@Test 
	public  void test13_SpaceInvitationIntranetNotification_Accept() {
		magAc.signIn(DATA_USER1, DATA_PASS);
		//Setup data test
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String password1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String email1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber() + mailSuffixData.getMailSuffixRandom();
		
		String space = txData.getContentByArrayTypeRandom(1) + getRandomNumber();  
	
		/*Precondition:
		 	- User A is manager of the space 1
			- User A invite User B to join the space 1
			- Space Invitation notification is activated in the settings of User B*/
		info("Add user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password1, email1, username1, username1);
		
		info("Add new space and ivite an user");
		hp.goToAllSpace();
		spaMg.goToCreateSpace();
		spaMg.addNewSpaceSimple(space, space);
		spaHome.goToSettingTab();
		setSpaceMg.inviteUser(username1);
		
		/*Step Number: 1
		 *Step Name: Step 1: Check notification list
		 *Step Description: 
			- Login with User B
			- Click Notifications icon
			- Check the notifications list
		 *Input Data: 
		 *Expected Outcome: 
			- A Space Invitation notifications is displayed in the list*/
		/*Step Number: 2
		 *Step Name: Step 2: Check notification message
		 *Step Description: 
			- Check the notification message
		 *Input Data: 
		 *Expected Outcome: 
			The notification message is :  <br />- $AVATAR<br />- You're invited to join $SPACE space<br />- [Accept] | [Refuse]<br />- $DATE<br /><br />Where : <br />- $AVATAR is the thumbnail of the space<br />- $SPACE is space 1<br />- $DATE is the date of the notification*/
		info("Check in notification list");
		magAc.signIn(username1, password1);
		navTool.goToIntranetNotification();
		intraNot.checkSpaceInvitationNotification(space);
		
		/*Step Number: 3
		 *Step Name: Step 3: Check View all
		 *Step Description: 
			- Click [View All] from the bottom of the notification list
			- Check View All page
		 *Input Data: 
		 *Expected Outcome: 
			- The notification is displayed in the View All page*/
		intraNot.goToAllNotification();
		intraNot.checkSpaceInvitationNotification(space);
		
		/*Step Number: 4
		 *Step Name: Step 4: Accept
		 *Step Description: 
			- Click [Accept]
		 *Input Data: 
		 *Expected Outcome: 
			- The invitation is approved (User B is member of the space)
			- The notifications message is updated to :
				$AVATAR
				You joined $SPACE space.
				$DATE*/
		hp.goToAllSpace();
		navTool.goToIntranetNotification();
		intraNot.goToHomepageOfSpaceFromIntranetNotification(space, true, "1");
		
		info("Reset Data");
		magAc.signIn(DATA_USER1, DATA_PASS);
		navTool.goToUsersAndGroupsManagement();
		userAndGroup.deleteUser(username1);
		hp.goToAllSpace();
		spaMg.searchSpace(space, "");
		spaMg.deleteSpace(space, false);
	}
	
	
	/**
	 *<li> Case ID:122985.</li>
	 *<li> Test Case Name: Space Invitation Intranet notification (Refuse).</li>
	 */
	@Test 
	public  void test14_SpaceInvitationIntranetNotification_Refuse() {
		magAc.signIn(DATA_USER1, DATA_PASS);
		//Setup data test
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String password1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String email1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber() + mailSuffixData.getMailSuffixRandom();
		
		String space1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();  
		String space2 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		/*Precondition:
		 	- User A is manager of the space 1
			- User A invite User B to join the space 1
			- Space Invitation notification is activated in the settings of User B*/
		info("Add user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password1, email1, username1, username1);
		
		info("Add a new space and ivite user");
		hp.goToAllSpace();
		spaMg.goToCreateSpace();
		spaMg.addNewSpaceSimple(space1, space1);
		spaHome.goToSettingTab();
		setSpaceMg.inviteUser(username1);
		hp.goToAllSpace();
		spaMg.goToCreateSpace();
		spaMg.addNewSpaceSimple(space2, space2);
		spaHome.goToSettingTab();
		setSpaceMg.inviteUser(username1);
		
		/*Step Number: 1
		 *Step Name: Step 1: Check notification list
		 *Step Description: 
			- Login with User B
			- Click Notifications icon
			- Check the notifications list
		 *Input Data: 
		 *Expected Outcome: 
			- A Space Invitation notifications is displayed in the list*/
		/*Step Number: 2
		 *Step Name: Step 2: Check notification message
		 *Step Description: 
			- Check the notification message
		 *Input Data: 
		 *Expected Outcome: 
			- The notification message is :  
				- $AVATAR
				- You're invited to join $SPACE space
				- [Accept] | [Refuse]
				- $DATE
			Where : 
				- $AVATAR is the thumbnail of the space
				- $SPACE is space 1
				- $DATE is the date of the notification*/
		info("user2 comments in John's activity");
		magAc.signIn(username1, password1);
		navTool.goToIntranetNotification();
		intraNot.checkSpaceInvitationNotification(space1);
		
		/*Step Number: 3
		 *Step Name: Step 3: Click the notification 
		 *Step Description: 
			- Click the notification area
		 *Expected Outcome: 
			- The user is redirected to the Space 1 home*/
		/*Step Number: 4
		 *Step Name: Step 4: Refuse
		 *Step Description: 
			- Click [View All] at the bottom of the list
			- Click [Refuse] in the View all page
		 *Expected Outcome: 
			- The invitation is not approved (User B is not member of the space)
			- The notification message is automatically hidden from the list 
			- The notification message is not available in the View All page.*/
		intraNot.goToHomepageOfSpaceFromIntranetNotification(space1, false, "1");
		
		info("Reset Data");
		magAc.signIn(DATA_USER1, DATA_PASS);
		navTool.goToUsersAndGroupsManagement();
		userAndGroup.deleteUser(username1);
		hp.goToAllSpace();
		spaMg.searchSpace(space1, "");
		spaMg.deleteSpace(space1, false);
		spaMg.searchSpace(space2, "");
		spaMg.deleteSpace(space2, false);
	}
	
	/**
	 *<li> Case ID:122986.</li>
	 *<li> Test Case Name: Space Join Request Intranet notification (Accept).</li>
	 */
	@Test 
	public  void test15_SpaceJoinRequestIntranetNotification_Accept() {
		magAc.signIn(DATA_USER1, DATA_PASS);
		//Setup data test
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String password1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String email1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber() + mailSuffixData.getMailSuffixRandom();
		
		String space = txData.getContentByArrayTypeRandom(1) + getRandomNumber();  
	
		/*Precondition:
		 	- User A requested to join Space 1
			- User B is manager of Space 1
			- Space Join Request notification is activated in User's B settings*/
		info("Add user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password1, email1, username1, username1);
		
		info("Add a new space");
		hp.goToAllSpace();
		spaMg.goToCreateSpace();
		spaMg.addNewSpaceSimple(space, space);
			
		info("user requests to join space");
		magAc.signIn(username1, password1);
		hp.goToAllSpace();
		spaMg.requestToJoinSpace(space);
		
		/*Step Number: 1
		 *Step Name: Step 1: check notification list
		 *Step Description: 
			- Login with User B
			- Click the notification icon
			- Check the notification list
		 *Expected Outcome: 
			- The Space Join Request notification is displayed in the list*/
		/*Step Number: 2
		 *Step Name: Step 2: Check content of notification
		 *Step Description: 
			- Check the notification message
		 *Expected Outcome: 
			- The notification message is :<br />- $AVATAR<br />- $USER has requested access to $SPACE space.<br />- [Accept] | [Refuse]<br />- $DATE<br /><br />Where : <br />- $AVATAR is the thumbnail of User A<br />- $USER is User A<br />- $DATE is the date of the notification*/
		info("check notification in notification list");
		magAc.signIn(DATA_USER1, DATA_PASS);
		navTool.goToIntranetNotification();
		intraNot.checkRequestToJoinSpaceNotification(username1, space, "1");
		
		/*Step Number: 3
		 *Step Name: Step 3: Accept from View all
		 *Step Description: 
			- Click [View All] at the bottom of the notification list
			- Click [Accept]
		 *Expected Outcome: 
			- The request is accepted (User A is member of Space 1)
			- The notification message is updated to :
				$AVATAR
				$USER joined $SPACE space.
				$DATE*/
		intraNot.goToAllNotification();
		intraNot.checkRequestToJoinSpaceNotification(username1, space, "1");
		hp.goToAllSpace();
		navTool.goToIntranetNotification();
		intraNot.goToHomepageOfSpaceFromIntranetNotificationWithRequest(space, true, "1", username1, password1);
		
		info("Reset Data");
		magAc.signIn(DATA_USER1, DATA_PASS);
		navTool.goToUsersAndGroupsManagement();
		userAndGroup.deleteUser(username1);
		hp.goToAllSpace();
		spaMg.searchSpace(space, "");
		spaMg.deleteSpace(space, false);
	}
	
	/**
	 *<li> Case ID:122987.</li>
	 *<li> Test Case Name: Space Join Request Intranet notification (Refuse).</li>
	 */
	@Test 
	public  void test16_SpaceJoinRequestIntranetNotification_Refuse() {
		magAc.signIn(DATA_USER1, DATA_PASS);
		//Setup data test
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String password1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String email1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber() + mailSuffixData.getMailSuffixRandom();
		
		String space = txData.getContentByArrayTypeRandom(1) + getRandomNumber();  
	
		/*Precondition:
		 	- User A requested to join Space 1
			- User B is manager of Space 1
			- Space Join Request notification is activated in User's B settings*/
		info("Go to add user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password1, email1, username1, username1);
		
		info("go to add new space");
		hp.goToAllSpace();
		spaMg.goToCreateSpace();
		spaMg.addNewSpaceSimple(space, space);
			
		info("user requests to join space");
		magAc.signIn(username1, password1);
		hp.goToAllSpace();
		spaMg.requestToJoinSpace(space);
		
		/*Step Number: 1
		 *Step Name: Step 1: check notification list
		 *Step Description: 
			- Login with User B
			- Click the notification icon
			- Check the notification list
		 *Expected Outcome: 
			- The Space Join Request notification is displayed in the list*/
		/*Step Number: 2
		 *Step Name: Step 2: Check content of notification
		 *Step Description: 
			- Check the notification message
		 *Expected Outcome: 
			The notification message is :
				- $AVATAR
				- $USER has requested access to $SPACE space.
				- [Accept] | [Refuse]
				- $DATE
			Where : 
				- $AVATAR is the thumbnail of User A
				- $USER is User A
				- $DATE is the date of the notification*/
		info("check notification");
		magAc.signIn(DATA_USER1, DATA_PASS);
		navTool.goToIntranetNotification();
		intraNot.checkRequestToJoinSpaceNotification(username1, space, "1");
		
		/*Step Number: 3
		 *Step Name: Step 3: Click the notification area
		 *Step Description: 
			- Click the notification area
		 *Expected Outcome: 
			- The user is redirected to Space 1 home*/
		/*Step Number: 4
		 *Step Name: Step 4: Refuse request
		 *Step Description: 
			- Click [Refuse]
		 *Expected Outcome: 
			- The request is denied (User A is not member of Space 1)
			- The notification message is hidden from the notifications list
			- The notification message is hidden from the View All page*/
		intraNot.goToAllNotification();
		intraNot.checkRequestToJoinSpaceNotification(username1, space, "1");
		hp.goToAllSpace();
		navTool.goToIntranetNotification();
		intraNot.goToHomepageOfSpaceFromIntranetNotificationWithRequest(space, false, "1", username1, password1);
		
		info("Reset Data");
		magAc.signIn(DATA_USER1, DATA_PASS);
		navTool.goToUsersAndGroupsManagement();
		userAndGroup.deleteUser(username1);
		hp.goToAllSpace();
		spaMg.searchSpace(space, "");
		spaMg.deleteSpace(space, false);
	}
	
	/**
	 *<li> Case ID:122981.</li>
	 *<li> Test Case Name: New User Intranet notification.</li>
	 */
	@Test
	public void test17_NewUserIntranetNotification(){
		magAc.signIn(DATA_USER1, DATA_PASS);
		//set Data test
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String password1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String email1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber() + mailSuffixData.getMailSuffixRandom();
	

		/*Precondition:
		 	- The settings "Someone joins the social intranet" is activated in User Settings for User A
			- A new user has joined the Intranet*/
		info("Add user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password1, email1, username1, username1);
		
		/*Step Number: 1
		 *Step Name: Step 1: Check notification list
		 *Step Description: 
			- Login to the platform with User A
			- Click the notifications icon
			- Check the list
		 *Input Data: 
		 *Expected Outcome: 
			- A New User notification is displayed in the list.*/
		/*Step Number: 2
		 *Step Name: Step 2: Check notification message
		 *Step Description: 
			- Check the notification message
		 *Input Data: 
		 *Expected Outcome: 
			- The notification message is :
				$AVATAR
				$USER has joined $PORTAL_NAME.
				$DATE
			Where : 
				- $USER is the new user
				- $AVATAR is the thumbnail of the new user
				- $DATE is the date of the notification.*/
		navTool.goToIntranetNotification();
		intraNot.checkNewUserNotification(username1, "1");
		
		/*Step Number: 3
		 *Step Name: Step 3: click notification area
		 *Step Description: 
			- Click the notification area
		 *Input Data: 
		 *Expected Outcome: 
			- The user is redirected to the $USER profile*/
		intraNot.goToUserProfileFromIntranetNotificationWithNewUserJoined(username1, "1");
		
		/*Step Number: 4
		 *Step Name: Step 4: View All
		 *Step Description: 
			- Click [View All]
		 *Input Data: 
		 *Expected Outcome: 
			- The notification is listed the View All page*/
		navTool.goToIntranetNotification();
		intraNot.goToAllNotification();
		intraNot.checkNewUserNotification(username1, "1");
		
		info("Reset Data");
		navTool.goToUsersAndGroupsManagement();
		userAndGroup.deleteUser(username1);
	
	}
}

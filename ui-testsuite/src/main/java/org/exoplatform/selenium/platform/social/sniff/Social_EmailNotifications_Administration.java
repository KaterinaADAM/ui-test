package org.exoplatform.selenium.platform.social.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.UserGroupManagement;
import org.exoplatform.selenium.platform.social.Notification;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
/**
 * 
 * @author thuntn
 * @date 04/06/2014
 *
 */
public class Social_EmailNotifications_Administration extends Notification {
	//Platform
	ManageAccount magAcc;
	NavigationToolbar navToolBar;
	UserGroupManagement userGroup;

	String user = "John Smith";
	String user1="Mary Williams";
	String user2="Jack Miller";
	String user3="James Davis";

	@BeforeMethod
	public void beforeMethods() {
		initSeleniumTest();
		driver.get(baseUrl);
		magAcc = new ManageAccount(driver,this.plfVersion);
		navToolBar = new NavigationToolbar(driver,this.plfVersion);
		magAcc.signIn(DATA_USER1, DATA_PASS);
		userGroup = new UserGroupManagement(driver);
	}

	@AfterMethod
	public void afterMethods() {
		info("Logout portal");
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	/**
	 * == Notification Administration ==
	 * Test case ID: 109848

	 */
	@Test(priority = 0)
	public void test01_NotificationAdministration(){
		
		/*
		- Login as admin
		- From top navigation, click Administration/Portal/Email Notification
		*Expected Outcome: 
		- Email Notifications Administration page appears as attachment with
		1.Notification Sender, allow change name and address
		2. Notification types with 3 columns:  
		+ [Notifications] list all activities which support notification email 
		+ [Title] Title of activities which is shown in [Notification setting] table 
		+ [Enable] let users enable/disable notification option		*/
		navToolBar.goToNotificationAdministration();

		//Verify element on Notification Administration page
		waitForAndGetElement(ELEMENT_NOTIFICATION_SENDER_LABEL);
		waitForAndGetElement(ELEMENT_NOTIFICATION_SENDER_NAME);
		waitForAndGetElement(ELEMENT_NOTIFICATION_SENDER_EMAIL);

		waitForAndGetElement(ELEMENT_NOTIFICATION_TYPES_LABEL);
		waitForAndGetElement(ELEMENT_NOTIFY_COLUMN.replace("${column}", "Notification"));
		waitForAndGetElement(ELEMENT_NOTIFY_COLUMN.replace("${column}", "Title"));
		waitForAndGetElement(ELEMENT_NOTIFY_COLUMN.replace("${column}", "Enable"));
		waitForAndGetElement(ELEMENT_TITLE_ACTIVITY.replace("${activity}", MSG_ACTIVITY_COMMENT).replace("${title}", MSG_TITLE_COMMENT));
		waitForAndGetElement(ELEMENT_TITLE_ACTIVITY.replace("${activity}", MSG_ACTIVITY_MENTION).replace("${title}", MSG_TITLE_MENTION));
		waitForAndGetElement(ELEMENT_TITLE_ACTIVITY.replace("${activity}", MSG_ACTIVITY_JOIN_INTRANET).replace("${title}", MSG_TITLE_NEW_USER));
		waitForAndGetElement(ELEMENT_TITLE_ACTIVITY.replace("${activity}", MSG_ACTIVITY_JOIN_SPACE).replace("${title}", MSG_TITLE_SPACE_JOIN));
		waitForAndGetElement(ELEMENT_TITLE_ACTIVITY.replace("${activity}", MSG_ACTIVITY_SEND_CONNECTION_REQUEST).replace("${title}", MSG_TITLE_CONNECTION_REQUEST));
		waitForAndGetElement(ELEMENT_TITLE_ACTIVITY.replace("${activity}", MSG_ACTIVITY_LIKE_MY_ACTIVITY).replace("${title}", MSG_TITLE_LIKE_ACTIVITY));
		waitForAndGetElement(ELEMENT_TITLE_ACTIVITY.replace("${activity}", MSG_ACTIVITY_POST_MY_SPACE).replace("${title}", MSG_TITLE_POST_MY_SPACE));
		waitForAndGetElement(ELEMENT_TITLE_ACTIVITY.replace("${activity}", MSG_ACTIVITY_POST_MY_STREAM).replace("${title}", MSG_TITLE_POST_STREAM));
		waitForAndGetElement(ELEMENT_TITLE_ACTIVITY.replace("${activity}", MSG_ACTIVITY_RECEIVE_SPACE_INVITATION).replace("${title}", MSG_TITLE_SPACE_INVITATION));
		waitForAndGetElement(ELEMENT_ENABLE.replace("${title}", MSG_TITLE_NEW_USER).replace("${enable}","YES" ));
		waitForAndGetElement(ELEMENT_ENABLE.replace("${title}", MSG_TITLE_CONNECTION_REQUEST).replace("${enable}","YES" ));
		waitForAndGetElement(ELEMENT_ENABLE.replace("${title}", MSG_TITLE_LIKE_ACTIVITY).replace("${enable}","YES" ));
		waitForAndGetElement(ELEMENT_ENABLE.replace("${title}", MSG_TITLE_MENTION).replace("${enable}","YES" ));
		waitForAndGetElement(ELEMENT_ENABLE.replace("${title}", MSG_TITLE_COMMENT).replace("${enable}","YES" ));
		waitForAndGetElement(ELEMENT_ENABLE.replace("${title}", MSG_TITLE_POST_MY_SPACE).replace("${enable}","YES" ));
		waitForAndGetElement(ELEMENT_ENABLE.replace("${title}", MSG_TITLE_POST_STREAM).replace("${enable}","YES" ));
		waitForAndGetElement(ELEMENT_ENABLE.replace("${title}", MSG_TITLE_SPACE_INVITATION).replace("${enable}","YES" ));
		waitForAndGetElement(ELEMENT_ENABLE.replace("${title}", MSG_TITLE_SPACE_JOIN).replace("${enable}","YES" ));

	}

	/**
	 * == Disable a Notification ==
	 * Test case ID: 109865
	 * == Disable a Notification ==
	 * Test case ID: 109865
	 */
	@Test(priority = 1)
	public void test02_DisableAndEnableNotification(){
		String username = getRandomString();
		String password = "gtngtn";
		String fullName = username + " "+username;
		String email = username + "@gmail.com";
		By eEmail = By.xpath(ELEMENT_GMAIL_TITLE.replace("${title}", fullName+" has joined eXo"));
		String username2 = getRandomString();
		String fullName2 = username2 + " "+ username2;
		String email2 = username2 + "@gmail.com";
		By eEmail2 = By.xpath(ELEMENT_GMAIL_TITLE.replace("{$title}", fullName2+" has joined eXo"));
		info("CaseID-109865:Disable a Notification");
		String handlePLF = driver.getWindowHandle();
		//Setup email
		navToolBar.goToMyProfile();
		magAcc.updateUserProfile(null, null, null, EMAIL_ADDRESS1);

		navToolBar.goToNotificationAdministration();

		//Verify all options of activities are YES by default

		waitForAndGetElement(ELEMENT_ENABLE.replace("${title}", MSG_TITLE_NEW_USER).replace("${enable}","YES" ));
		waitForAndGetElement(ELEMENT_ENABLE.replace("${title}", MSG_TITLE_CONNECTION_REQUEST).replace("${enable}","YES" ));
		waitForAndGetElement(ELEMENT_ENABLE.replace("${title}", MSG_TITLE_LIKE_ACTIVITY).replace("${enable}","YES" ));
		waitForAndGetElement(ELEMENT_ENABLE.replace("${title}", MSG_TITLE_MENTION).replace("${enable}","YES" ));
		waitForAndGetElement(ELEMENT_ENABLE.replace("${title}", MSG_TITLE_COMMENT).replace("${enable}","YES" ));
		waitForAndGetElement(ELEMENT_ENABLE.replace("${title}", MSG_TITLE_POST_MY_SPACE).replace("${enable}","YES" ));
		waitForAndGetElement(ELEMENT_ENABLE.replace("${title}", MSG_TITLE_POST_STREAM).replace("${enable}","YES" ));
		waitForAndGetElement(ELEMENT_ENABLE.replace("${title}", MSG_TITLE_SPACE_INVITATION).replace("${enable}","YES" ));
		waitForAndGetElement(ELEMENT_ENABLE.replace("${title}", MSG_TITLE_SPACE_JOIN).replace("${enable}","YES" ));

		//Disable activity New user
		enableNotification(MSG_TITLE_NEW_USER, false);

		//Verify activity "New user" is not present on Notification settings
		navToolBar.goToNotificationSettings();
		waitForAndGetElement(ELEMENT_NOTIFY_COLUMN.replace("${column}", "Send me an email right away"));
		waitForElementNotPresent(ELEMENT_JOIN_INTRANET_SEND_RIGHT);

		//Add new user
		navToolBar.goToNewStaff();
		magAcc.addNewUserAccount(username, password, password, username, username, null, email, "", null,true);

		//Verify no mail is sent
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		String handle = driver.getWindowHandle();
		assert waitForAndGetElement(eEmail, 30000,0) == null : "Still receive email!";

		info("caseID-109866: Enable a Notification");
		driver.switchTo().window(handlePLF);
		navToolBar.goToNotificationAdministration();
		enableNotification(MSG_TITLE_NEW_USER, true);

		//Enable notification for activity 'Join intranet'
		navToolBar.goToNotificationSettings();
		enableSendNotificationRight(MSG_ACTIVITY_JOIN_INTRANET, true);

		navToolBar.goToNewStaff();
		magAcc.addNewUserAccount(username2, password, password, username2, username2, null, email2, "", null,true);

		driver.switchTo().window(handle);
		driver.navigate().refresh();
		checkAndDeleteMail(eEmail2, MSG_CONTENT_EMAIL_NEW_USER.replace("${user}", fullName2));
		driver.switchTo().window(handlePLF);
		
		//Restore data
		navToolBar.goToUsersAndGroupsManagement();
		userGroup.deleteUser(username);
		userGroup.deleteUser(username2);
	}
}

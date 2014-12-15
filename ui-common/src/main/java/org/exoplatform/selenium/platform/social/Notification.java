package org.exoplatform.selenium.platform.social;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author thuntn
 * @date: 04/06/2014
 */

/**
 * This class is for Notification admin page and Notification setting
 */

public class Notification extends SocialBase {

	//=======Element for Notification Administration=======
	//Sender form
	public final By ELEMENT_NOTIFICATION_SENDER_NAME = By.id("senderName");
	public final By ELEMENT_NOTIFICATION_SENDER_EMAIL = By.id("senderEmail");
	public final By ELEMENT_NOTIFICATION_SENDER_LABEL = By.xpath("//*[text()='Notifications sender']");

	//Notification types
	public final By ELEMENT_NOTIFICATION_TYPES_LABEL = By.xpath("//*[text()='Notification types']");
	public final String ELEMENT_TITLE_ACTIVITY = "//label[contains(text(),'${title}')]/../..//td[contains(text(),'${activity}')]";
	public final String ELEMENT_ENABLE = "//label[contains(text(),'${title}')]/../..//span[contains(text(),'${enable}')]";
	public final String ELEMENT_HANDLE = "//label[contains(text(),'${title}')]/../..//div[contains(@class,'iPhoneCheckHandleRight')]";
	public final String MSG_ACTIVITY_JOIN_INTRANET = "Someone joins the social intranet";
	public final String MSG_ACTIVITY_SEND_CONNECTION_REQUEST = "Someone sends me a connection request";
	public final String MSG_ACTIVITY_JOIN_SPACE = "Someone requests to join one of my spaces";
	public final String MSG_ACTIVITY_RECEIVE_SPACE_INVITATION = "I receive a Space invitation";
	public final String MSG_ACTIVITY_POST_MY_SPACE = "An activity is posted on one of my spaces";
	public final String MSG_ACTIVITY_LIKE_MY_ACTIVITY = "Someone likes one of my activities";
	public final String MSG_ACTIVITY_MENTION = "Someone @mentions me";
	public final String MSG_ACTIVITY_POST_MY_STREAM = "Someone posts a message on my activity stream";
	public final String MSG_ACTIVITY_COMMENT = "Someone comments on one of my activities";
	public final String MSG_TITLE_NEW_USER = "New User";
	public final String MSG_TITLE_CONNECTION_REQUEST = "Connection Request";
	public final String MSG_TITLE_SPACE_JOIN = "Space Join Request";
	public final String MSG_TITLE_SPACE_INVITATION = "Space Invitation";
	public final String MSG_TITLE_POST_MY_SPACE = "Post on my Spaces";
	public final String MSG_TITLE_LIKE_ACTIVITY = "Like on Activity";
	public final String MSG_TITLE_MENTION= "Mention";
	public final String MSG_TITLE_POST_STREAM = "Post on my Stream";
	public final String MSG_TITLE_COMMENT = "Comment";


	//================Notification settings page=====================
	public final String ELEMENT_NOTIFICATION_SETTINGS_TITLE = "//form[@id='uiNotificationSetting']//*[text()='Notification Settings']";
	public final String ELEMENT_NOTIFY_COLUMN = "//th[contains(text(),'${column}')]";
	public final String ELEMENT_NOTIFY_ACTIVITY = "//label[contains(text(),'${activity}')]";
	public final String ELEMENT_NOTIFY_RIGHT_INPUT = "//label[contains(text(),'${activity}')]/../..//input";
	public final String ELEMENT_NOTIFY_DIGEST_EMAIL = "//label[contains(text(),'${activity}')]/../..//select/option[contains(text(),'Never')]"
			+ "/../option[contains(text(),'Daily')]/../option[contains(text(),'Weekly')]";
	public final By ELEMENT_CONNECTION_SEND_RIGHT = By.id("RelationshipRecievedRequestPlugin");
	public final By ELEMENT_JOIN_SPACE_SEND_RIGHT = By.id("RequestJoinSpacePlugin");
	public final By ELEMENT_SPACE_INVITATION_SEND_RIGHT = By.id("SpaceInvitationPlugin");
	public final By ELEMENT_POST_ACTIVITY_SPACE_SEND_RIGHT = By.id("PostActivitySpaceStreamPlugin");
	public final By ELEMENT_LIKE_ACTIVITY_SEND_RIGHT = By.id("PostActivitySpaceStreamPlugin");
	public final By ELEMENT_MENTION_SEND_RIGHT = By.id("ActivityMentionPlugin");
	public final By ELEMENT_POST_MY_ACTIVITY_SEND_RIGHT = By.id("PostActivityPlugin");
	public final By ELEMENT_COMMENT_SEND_RIGHT = By.id("ActivityCommentPlugin");
	public final By ELEMENT_JOIN_INTRANET_SEND_RIGHT = By.id("NewUserPlugin");

	public final String MSG_SETTINGS_COLUMN_NOFITY_WHEN = "Notify me when:";
	public final String MSG_SETTINGS_COLUMN_SEND_RIGHT = "Send me an email right away";
	public final String MSG_SETTINGS_COLUMN_INCLUDE_DIGEST = "Include in my digest email";
	public final By ELEMENT_NEVER_NOTIFY_ME = By.id("checkBoxDeactivate");

	//==================Email notification=================

	public final String MSG_CONTENT_EMAIL_CONNECTION_REQUEST = "${user} has sent you a connection request. Accept the connection request and start collaborating with ${user} ?";
	public final String ELEMENT_NOTIFICATION_EMAIL_SUBJECT = "//td[contains(text(),'${subject}')]";
	public final String MSG_CONNECTION_REQUEST_SUBJECT = "New connection request";
	public final String MSG_NEW_USER_SUBJECT = "New user on";
	public final String MSG_REQUEST_JOIN_SPACE_SUBJECT = "New request to join a space";
	public final String MSG_REQUEST_INVITATION_SPACE_SUBJECT = "New space invitation";
	public final String MSG_COMMENT_ACTIVITY_SUBJECT = "New comment on your activity";
	public final String MSG_LIKE_ACTIVITY_SUBJECT = "New like on your activity stream";
	public final String MSG_MENTION_SUBJECT = "New mention of you";
	public final String MSG_POST_SPACE_ACTIVITY_SUBJECT = "New post in ${space}";
	public final String MSG_POST_MY_ACTIVITY_SUBJECT = "New post on your activity stream";
	public final By ELEMENT_NEW_USER_CONNECT_BUTTON = By.linkText("Connect now");
	public final By ELEMENT_CONNECTION_REQUEST_ACCEPT_BUTTON = By.linkText("Accept");
	public final String ELEMENT_NOTIFICATION_MESSAGE = "//a[text()='${action}']/../../p";
	public final String ELEMENT_NOTIFICATION_HI = "//a[text()='${action}']/../../../../../..//p[contains(text(),'Hi ${user}')]";
	public final String ELEMENT_MESSAGE_REFUSE_CONNECTION_REQUEST = "//span[contains(text(),'You refused ${user} connection request')]";
	public final String ELEMENT_MESSAGE_REFUSE_SPACE_REQUEST = "//span[contains(text(),'You refused ${user} to join the space')]";
	public final String ELEMENT_MESSAGE_REFUSE_INVITATION_SPACE = "//span[contains(text(),'You refused to join ${space}')]";
	public final String ELEMENT_MESSAGE_APPROVE_SPACE_REQUEST = "//span[contains(text(),\"You approved ${user}'s request to join the space\")]";
	public final String ELEMENT_MESSAGE_ALREADY_MEMBER_SPACE = "//span[contains(text(),'${user} is already a member of ${space}')]";
	public final String MSG_CONTENT_EMAIL_LIKE_ACTIVITY = "${user} liked your activity :</br>${activity}";
	public final String MSG_CONTENT_EMAIL_COMMENT_ACTIVITY = "${user} has posted a comment on your activity. See below :</br>${activity}</br>${user}:</br>${comment}";
	public final String MSG_CONTENT_EMAIL_POST_MY_ACTIVITY = "${user} has posted on your activity stream. See the post below :</br>${activity}";
	public final String MSG_CONTENT_EMAIL_POST_SPACE_ACTIVITY = "${user} has posted an activity in the ${space} space. See the post below:</br>${activity}";
	public final String MSG_CONTENT_EMAIL_JOIN_SPACE = "${user} has requested access to the ${space} space where you are a manager. Would you like to authorize ${user} to join the space?";
	public final String MSG_CONTENT_EMAIL_INVITATION_SPACE = "You've received an invitation to join the ${space} space. Interested to join the space and access its documents and applications ?";
	public final String MSG_CONTENT_EMAIL_MENTION = "${user} has mentioned you in the following post:</br>${activity}";
	public final String MSG_CONTENT_EMAIL_NEW_USER = "${user} has joined eXo. Interested to connect and start collaborate with ${user}";
	public final String MSG_TITLE_EMAIL_JOIN_SPACE = "${user} has requested access to ${space}";
	public final String MSG_TITLE_EMAIL_INVITATION_SPACE = "You're invited to join ${space} space";
	public final String MSG_TITLE_EMAIL_MENTION = "You have been mentioned by ${user}";
	public final String MSG_TITLE_EMAIL_NEW_USER = "${user} wants to connect with you on eXo";
	public final String MSG_TITLE_EMAIL_LIKE_ACTIVITY = "${user} likes your activity ${activity}";
	public final String MSG_TITLE_EMAIL_COMMENT = "${user} has commented one of your activities";
	public final String MSG_TITLE_EMAIL_POST_MY_ACTIVITY = "${user} has posted on your activity stream: ${activity}";
	public final String MSG_TITLE_EMAIL_POST_SPACE_ACTIVITY = "${user} has posted an activity in the ${space} space: ${activity}";
	public final String MSG_NOTIFICATION_EMAIL_BOTTOM = "If you do not want to receive such notifications, click here to change your notification settings.";

	public Notification(WebDriver dr){
		driver = dr;
		button = new Button(driver);
	}

	public Notification(){
		button = new Button(driver);
	}

	/********** Common functions *************/
	/**
	 * Enable notification of activities
	 * @param title: title of activity (is the same title on Notification Administration)
	 * @param option
	 */
	public void enableNotification(String title,boolean option){
		if(!option){
			info("Disable notification " + title);
			if(waitForAndGetElement(ELEMENT_ENABLE.replace("${title}", title).replace("${enable}", "YES"),5000,0) != null)
				waitForAndGetElement(ELEMENT_ENABLE.replace("${title}", title).replace("${enable}", "YES")).click();
			waitForAndGetElement(ELEMENT_ENABLE.replace("${title}",title).replace("${enable}", "NO"));
		}
		else{
			info("Enable notification " + title);
			if(waitForAndGetElement(ELEMENT_ENABLE.replace("${title}", title).replace("${enable}", "NO"),5000,0) != null)
				waitForAndGetElement(ELEMENT_ENABLE.replace("${title}", title).replace("${enable}", "NO")).click();
			waitForAndGetElement(ELEMENT_ENABLE.replace("${title}",title).replace("${enable}", "YES"));
		}
	}
	/**
	 * Enable or disable to send notification instantly
	 * @param activity
	 * @param option: true if enable
	 *                and vice versa
	 */
	public void enableSendNotificationRight(String activity, boolean option){
		button = new Button(driver);
		if(option){
			info("Enable to send notification instantly");
			check(ELEMENT_NOTIFY_RIGHT_INPUT.replace("${activity}", activity),2);
		}else{
			info("Disable to send notification instantly");
			uncheck(ELEMENT_NOTIFY_RIGHT_INPUT.replace("${activity}", activity),2);
		}
		button.save();
	}

	/**
	 * Check message, action, hi part on email notification
	 * @param message
	 * @param action
	 * @param user
	 */
	public void checkEmailNotification(String message,  String user, String...actions){
		String action = actions.length > 0 ? actions[0] : "";
		String action2 = actions.length > 1 ? actions[1] : "";
		String[] lines = message.split("</br>"); 
		String text ;

		waitForAndGetElement(ELEMENT_NOTIFICATION_HI.replace("${action}", action).replace("${user}", user));
		text = waitForAndGetElement(ELEMENT_GMAIL_CONTENT).getText();
		info("check content " + text);
		info("check message " + message);
		for(String line:lines){
			info(line);
			assert text.contains(line):"line is not correct " + line;
		}
		if(action != "")
			waitForAndGetElement(By.linkText(action));
		if(action2 != "")
			waitForAndGetElement(By.linkText(action2));

		assert text.contains(MSG_NOTIFICATION_EMAIL_BOTTOM);
		waitForAndGetElement(By.linkText("click here"));
	}
}

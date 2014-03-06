package org.exoplatform.selenium.cloud.features;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.cloud.CloudBase;
import org.exoplatform.selenium.platform.HomePageGadget;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.UserGroupManagement;
import org.openqa.selenium.By;
import org.testng.annotations.*;

/**
 * @author phuongdt
 *
 */
public class Cloud_Features_InvitationGadget extends CloudBase{
	ManageAccount magAccount;
	NavigationToolbar navBar;
	UserGroupManagement userGroup;
	HomePageGadget hpGadget;

	@BeforeMethod
	public void beforeMethods() {
		initSeleniumTest();
		driver.get(baseUrl);
		driver.manage().window().maximize();
		magAccount = new ManageAccount(driver);
		navBar = new NavigationToolbar(driver);
		userGroup = new UserGroupManagement(driver);
		hpGadget = new HomePageGadget(driver);
		magAccount.signIn("root", "12345");
	}

	@AfterMethod
	public void afterMethods() {
		driver.quit();
	}

	/**
	 * Case ID:94263.
	 * Test Case Name: Gadget "Invite Coworkers" should be displayed in the Homepage.
	 * Case ID:94264.
	 * Test Case Name: Field Email should be displayed in Gadget "Invite Coworkers".
	 * Case ID:94265.
	 * Test Case Name: Field Email should be displayed in Gadget "Invite Coworkers".
	 * Case ID:94266.
	 * Test Case Name: Label in the Field Email should be removed.
	 * Case ID:94267.
	 * Test Case Name: Label in the Field Email should remain.
	 * Case ID:94269.
	 * Test Case Name: A message should be displayed for an invalid email address.
	 * Case ID:94270.
	 * Test Case Name: A message should be displayed after sending an invitation.
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by phuongdt at 2014/01/07 10:22:35
	 */
	@Test
	public  void test01_02_03_04_05_06_07_GadgetInviteCoworkersShouldBeDisplayedInTheHomepage() {
		/*Declare variable*/
		String invalid_email = "invalidemail";
		String valid_email = "demo@acme.com";
		String invalid_message = "Please enter a valid email address";
		String successful_message = "Invitation sent";


		info("Test 1: Gadget Invite Coworkers should be displayed in the Homepage");
		/*Step Number: 1
		 *Step Name: 
		 *Step Description: 
			- Connect to CW
		 *Input Data: 
		 *Expected Outcome: 
			- In the right panel, a gagdet "Invite Coworkers" is displayed bekow rge Getting Started Gadget*/
		waitForAndGetElement(hpGadget.ELEMENT_GETTING_STARTED_GADGET_INDEX_DEFAULT_CLOUD);
		waitForAndGetElement(hpGadget.ELEMENT_INVITATION_GADGET_INDEX_DEFAULT_CLOUD);
		driver.switchTo().frame(waitForAndGetElement(hpGadget.ELEMENT_FRAME_INVITATION_GADGET_CLOUD));

		info("Test 2: Button Send should be displayed in Gadget Invite Coworkers");
		/*Step Number: 1
		 *Step Name: 
		 *Step Description: 
			- Connect to CW
		 *Input Data: 

		 *Expected Outcome: 
			- In the right panel, a gagdet "Invite Coworkers" is displayed bekow rge Getting Started Gadget
			- A button "Send" is displayed next the field email*/ 
		waitForAndGetElement(hpGadget.ELEMENT_SEND_BUTTON);

		info("Test 3: Field Email should be displayed in Gadget Invite Coworkers");
		/*Step Number: 1
		 *Step Name: 
		 *Step Description: 
			- Connect to CW
		 *Input Data: 

		 *Expected Outcome: 
			- In the right panel, a gagdet "Invite Coworkers" is displayed bekow rge Getting Started Gadget
			- The input field is displayed with a label "name@domain.com"*/ 
		assert getValue(ELEMENT_INPUT_EMAIL_ADDRESS).contains("name@domain.com");

		info("Test 4: Label in the Field Email should be removed");
		/*Step number: 2
		 *Step Name: 
		 *Step Description: 
			- Focus the mouse on the email's field
		 *Input Data: 

		 *Expected Outcome: 
			- The label "name@doamin.com" is removed*/ 
		click(ELEMENT_INPUT_EMAIL_ADDRESS);
		assert !(getValue(ELEMENT_INPUT_EMAIL_ADDRESS).contains("name@domain.com"));

		info("Test 5: Label in the Field Email should remain");
		/*Step number: 3
		 *Step Name: 
		 *Step Description: 
			- Click out of the field
		 *Input Data: 

		 *Expected Outcome: 
			- The label "name@doamin.com" remain*/ 
		switchToParentWindow();
		click(hpGadget.ELEMENT_INVITATION_GADGET_INDEX_DEFAULT_CLOUD);
		driver.switchTo().frame(waitForAndGetElement(hpGadget.ELEMENT_FRAME_INVITATION_GADGET_CLOUD));
		assert getValue(ELEMENT_INPUT_EMAIL_ADDRESS).contains("name@domain.com");

		info("Test 7: A message should be displayed for an invalid email address");
		/*Step number: 2
		 *Step Name: 
		 *Step Description: 
			- Input an invalid email address in the field
			- Click on the button "Send"
		 *Input Data: 

		 *Expected Outcome: 
			- A message is displayed: Please enter a valid email address.*/ 
		type(ELEMENT_INPUT_EMAIL_ADDRESS, invalid_email, true);
		click(hpGadget.ELEMENT_SEND_BUTTON);
		assert getText(hpGadget.ELEMENT_MESSAGE_INVITATION_GADGET).contains(invalid_message):"Message is wrong";

		info("Test 6: A message should be displayed after sending an invitation");
		/*Step number: 2
		 *Step Name: 
		 *Step Description: 
			- Input a valid email address in the field
			- Click on the button "Send"
		 *Input Data: 

		 *Expected Outcome: 
			- A message is displayed: "Invitation sent"*/ 
		type(ELEMENT_INPUT_EMAIL_ADDRESS, valid_email, true);
		click(hpGadget.ELEMENT_SEND_BUTTON);
		waitForTextPresent(successful_message);
		//assert getText(hpGadget.ELEMENT_MESSAGE_INVITATION_GADGET).contains(successful_message):"Message is wrong";
	}

	/**
	 * Case ID:94270.
	 * Test Case Name: A message should be displayed for a blacklisted email address.
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by phuongdt at 2014/01/07 10:22:35
	 *To view black list email, refer issue: https://jira.exoplatform.org/browse/ITOP-1012
	 *To blacklist add content of this file to the end of data/cloud-admin/workspaces/blacklist.properties.
	 */
	@Test
	public  void test08_AMessageShouldBeDisplayedForABlacklistedEmailAddress() {
		/*Declare variable*/
		String black_email = "invalidemail@3000.it";
		String message = "Please use a corporate email address";

		info("Test 8: A message should be displayed for a blacklisted email address");
		/*Step Number: 1
		 *Step Name: 
		 *Step Description: 
			- Connect to CW
		 *Input Data: 

		 *Expected Outcome: 
			- In the right panel, a gagdet "Invite Coworkers" is displayed bekow rge Getting Started Gadget
			- The input field is displayed with a label "name@domain.com"*/

		waitForAndGetElement(hpGadget.ELEMENT_GETTING_STARTED_GADGET_INDEX_DEFAULT_CLOUD);
		waitForAndGetElement(hpGadget.ELEMENT_INVITATION_GADGET_INDEX_DEFAULT_CLOUD);
		driver.switchTo().frame(waitForAndGetElement(hpGadget.ELEMENT_FRAME_INVITATION_GADGET_CLOUD));

		/*Step number: 2
		 *Step Name: 
		 *Step Description: 
			- Input a blacklist email address in the field
			- Click on the button "Send"
		 *Input Data: 

		 *Expected Outcome: 
			- A message is displayed: Please use a corporate email address.*/ 
		type(ELEMENT_INPUT_EMAIL_ADDRESS, black_email, true);
		click(hpGadget.ELEMENT_SEND_BUTTON);
		assert getText(hpGadget.ELEMENT_MESSAGE_INVITATION_GADGET).contains(message):"Message is wrong";
	}

	/**
	 * Case ID:94271.
	 * Test Case Name: A message should be displayed for a technical problem.
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by phuongdt at 2014/01/07 10:22:35
	 *PENDING: Can't simulator the technical problem
	 */
	@Test (groups="pending")
	public  void test09_AMessageShouldBeDisplayedForATechnicalProblem() {
		/*Declare variable*/
		String email = "invalidemail@exoplatform.com";
		String message = "Error sending the invitation. Please try again.";

		info("Test 9: A message should be displayed for a technical problem");
		/*Step Number: 1
		 *Step Name: 
		 *Step Description: 
			- Connect to CW
		 *Input Data: 

		 *Expected Outcome: 
			- In the right panel, a gagdet "Invite Coworkers" is displayed bekow rge Getting Started Gadget
			- The input field is displayed with a label "name@domain.com"*/
		waitForAndGetElement(hpGadget.ELEMENT_GETTING_STARTED_GADGET_INDEX_DEFAULT_CLOUD);
		waitForAndGetElement(hpGadget.ELEMENT_INVITATION_GADGET_INDEX_DEFAULT_CLOUD);
		driver.switchTo().frame(waitForAndGetElement(hpGadget.ELEMENT_FRAME_INVITATION_GADGET_CLOUD));

		/*Step number: 2
		 *Step Name: 
		 *Step Description: 
			- Input a valid email address in the field
			- a technical problem happen
			- Click on the button "Send"
		 *Input Data: 

		 *Expected Outcome: 
			- A message is displayed: Error sending the invitation. Please try again.*/ 
		type(ELEMENT_INPUT_EMAIL_ADDRESS, email, true);
		click(hpGadget.ELEMENT_SEND_BUTTON);
		assert getText(hpGadget.ELEMENT_MESSAGE_INVITATION_GADGET).contains(message):"Message is wrong";
	}

	/**
	 * Case ID:94272.
	 * Test Case Name: User should receive an invitation Email from user of the same domain.
	 * Pre-Condition: User A and User B are from the same domain
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by phuongdt at 2014/01/07 10:22:35
	 */
	@Test
	public  void test10_UserShouldReceiveAnInvitationEmailFromUserOfTheSameDomain() {
		/*Declare variable*/
		String successful_message = "Invitation sent";
		String email = "fqaexovn@gmail.com";
		String invited_email = "exoservice@gmail.com";
		String pass = "exoadmin";
		String userName = "";
		String content = "${user} has invited you to join the ${domain} workspace. Join this social workspace to collaborate with your co-workers in Wikis, Forums, Calendars, Gadgets and more.";
		/*Create data*/
		info("-- Update user profile - email --");
		magAccount.updateUserProfile(null,null, null, email);

		info("Test 10 User should receive an invitation Email from user of the same domain");
		/*Step Number: 1
		 *Step Name: 
		 *Step Description: 
			- Connect to CW with User A
		 *Input Data: 

		 *Expected Outcome: 
			- In the right panel, a gagdet "Invite Coworkers" is displayed bekow rge Getting Started Gadget
			- The input field is displayed with a label "name@domain.com"*/
		navBar.goToHomePage();
		userName=waitForAndGetElement(ELEMENT_ACCOUNT_NAME_LINK).getText().trim();
		waitForAndGetElement(hpGadget.ELEMENT_GETTING_STARTED_GADGET_INDEX_DEFAULT_CLOUD);
		waitForAndGetElement(hpGadget.ELEMENT_INVITATION_GADGET_INDEX_DEFAULT_CLOUD);
		driver.switchTo().frame(waitForAndGetElement(hpGadget.ELEMENT_FRAME_INVITATION_GADGET_CLOUD));


		/*Step number: 2
		 *Step Name: 
		 *Step Description: 
			- Input email address of the User B
			- Click on the button "Send"
		 *Input Data: 

		 *Expected Outcome: 
			- The message is displayed: "Invitation sent."*/
		type(ELEMENT_INPUT_EMAIL_ADDRESS, invited_email, true);
		click(hpGadget.ELEMENT_SEND_BUTTON);
		waitForTextPresent(successful_message);

		/*Step number: 3
		 *Step Name: 
		 *Step Description: 
			- Connect to the email of the User B
		 *Input Data: 

		 *Expected Outcome: 
			- An email is received with the following format:
			- From: $user-name
			- Title: $user-name has invited you to the $workspace Workspace
			- Message: "$user-name has invited you to join the $workspace workspace. Join this social workspace to collaborate with your co-workers in Wikis, Forums, Calendars, Gadgets and more."
			- Variable message: "Join now and invite your colleagues." OR "$number-of-users of your colleagues are already using the $workspace workspace. Join them now."
			- Optional profile pics*/
		goToMail(invited_email, pass);
		checkAndDeleteMail(By.xpath(hpGadget.ELEMENT_GMAIL_EMAIL_SAME_DOMAIN.replace("${user}",userName).replace("${domain}", invited_email.substring(invited_email.indexOf("@")+1,invited_email.indexOf(".")))), content.replace("${user}",userName).replace("${domain}", invited_email.substring(invited_email.indexOf("@")+1,invited_email.indexOf("."))),true,userName);
	}

	/**
	 * Case ID:94273.
	 * Test Case Name: User should receive an invitation Email from user of other domain 1.
	 * Pre-Condition: User A (exoplatform) and User B (gmail) aren't from the same domainExemple : 
	 * gmail has no workspace 
	-> link to exoplatform.net in order to create the tenant
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by phuongdt at 2014/01/07 10:22:35
	 */
	@Test
	public  void test11_UserShouldReceiveAnInvitationEmailFromUserOfOtherDomain1() {
		info("Test 11 User should receive an invitation Email from user of other domain 1");
		/*Declare variable*/
		String successful_message = "Invitation sent";
		String email = "fqa-exo@yopmail.com";
		String invited_email = "exoservice@gmail.com";
		String pass = "exoadmin";
		String userName = "";
		String content = "${user} has invited you to try eXo. Create your own company workspace to collaborate and share documents, calendars, forums, custom dashboards and more.";
		String url = "http://yopmail.wks-acc.exoplatform.org/portal";

		/*Create data*/
		magAccount.signOut();
		driver.get(url);
		magAccount.signIn("root", "12345");
		info("-- Update user profile - email --");
		magAccount.updateUserProfile(null,null, null, email);

		info("Test 10 User should receive an invitation Email from user of the same domain");
		/*Step Number: 1
		 *Step Name: 
		 *Step Description: 
			- Connect to CW with User A
		 *Input Data: 

		 *Expected Outcome: 
			- In the right panel, a gagdet "Invite Coworkers" is displayed bekow rge Getting Started Gadget
			- The input field is displayed with a label "name@domain.com"*/
		navBar.goToHomePage();
		userName=waitForAndGetElement(ELEMENT_ACCOUNT_NAME_LINK).getText().trim();
		waitForAndGetElement(hpGadget.ELEMENT_GETTING_STARTED_GADGET_INDEX_DEFAULT_CLOUD);
		waitForAndGetElement(hpGadget.ELEMENT_INVITATION_GADGET_INDEX_DEFAULT_CLOUD);
		driver.switchTo().frame(waitForAndGetElement(hpGadget.ELEMENT_FRAME_INVITATION_GADGET_CLOUD));

		/*Step number: 2
		 *Step Name: 
		 *Step Description: 
			- Input email address of the User B
			- Click on the button "Send"
		 *Input Data: 

		 *Expected Outcome: 
			- The message is displayed: "Invitation sent."*/
		type(ELEMENT_INPUT_EMAIL_ADDRESS, invited_email, true);
		click(hpGadget.ELEMENT_SEND_BUTTON);
		waitForTextPresent(successful_message);

		/*Step number: 3
		 *Step Name: 
		 *Step Description: 
			- Connect to the email of the User B
		 *Input Data: 

		 *Expected Outcome: 
			- An email is received in the followng format:
			- From: $user-name
			- Title: $user-name has invited you to try eXo
			- Message: "$user-name has invited you to try eXo. Create your private social workspace to collaborate with your co-workers in Wikis, Forums, Calendars, Gadgets and more. Start now by creating the $workspace workspace."*/ 
		goToMail(invited_email, pass);
		checkAndDeleteMail(By.xpath(hpGadget.ELEMENT_GMAIL_EMAIL.replace("${user}",userName)), content.replace("${user}", userName),true,userName);
	}

	/**
	 * Case ID:94274.
	 * Test Case Name: User should receive an invitation Email from user of other domain 2.
	 * Pre-Condition: User A (exoplatform) and User B (gmail) aren't from the same domainEx: 
	 * Gmail has a workspace 
	-> user creation + link to gmail.exoplatform.net
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by phuongdt at 2014/01/07 10:22:35
	 */
	@Test
	public  void test12_UserShouldReceiveAnInvitationEmailFromUserOfOtherDomain2() {
		info("Test 12 User should receive an invitation Email from user of other domain 2");
		/*Declare variable*/
		String successful_message = "Invitation sent";
		String email = "fqa-exo@yopmail.com";
		String invited_email = "exoservice@gmail.com";
		String pass = "exoadmin";
		String userName = "";
		String content = "${user} has invited you to try eXo. Join this social workspace to collaborate with your co-workers in Wikis, Forums, Calendars, Gadgets and more.";
		String url = "http://yopmail.wks-acc.exoplatform.org/portal";
		/*Create data*/
		magAccount.signOut();
		driver.get(url);
		magAccount.signIn("root", "12345");
		info("-- Update user profile - email --");
		magAccount.updateUserProfile(null,null, null, email);
		info("Test 10 User should receive an invitation Email from user of the same domain");
		/*Step Number: 1
		 *Step Name: 
		 *Step Description: 
			- Connect to CW with User A
		 *Input Data: 

		 *Expected Outcome: 
			- In the right panel, a gagdet "Invite Coworkers" is displayed bekow rge Getting Started Gadget
			- The input field is displayed with a label "name@domain.com"*/
		navBar.goToHomePage();
		userName=waitForAndGetElement(ELEMENT_ACCOUNT_NAME_LINK).getText().trim();
		waitForAndGetElement(hpGadget.ELEMENT_GETTING_STARTED_GADGET_INDEX_DEFAULT_CLOUD);
		waitForAndGetElement(hpGadget.ELEMENT_INVITATION_GADGET_INDEX_DEFAULT_CLOUD);
		driver.switchTo().frame(waitForAndGetElement(hpGadget.ELEMENT_FRAME_INVITATION_GADGET_CLOUD));

		/*Step number: 2
		 *Step Name: 
		 *Step Description: 
			- Input email address of the User B
			- Click on the button "Send"
		 *Input Data: 

		 *Expected Outcome: 
			- The message is displayed: "Invitation sent."*/
		type(ELEMENT_INPUT_EMAIL_ADDRESS, invited_email, true);
		click(hpGadget.ELEMENT_SEND_BUTTON);
		waitForTextPresent(successful_message);

		/*Step number: 3
		 *Step Name: 
		 *Step Description: 
			- Connect to the email of the User B
		 *Input Data: 

		 *Expected Outcome: 
			- An email is received in the followng format:
			- From: $user-name
			- Title: $user-name has invited you to try eXo
			- Message: "$user-name has invited you to try eXo."
			- Variable message: "We found that $number-of-users of your co-workers are already using the $workspace social workspace. Join them to collaborate in Wikis, Forums, Calendars, Gadgets and more." 
			OR "Join your co-workers in the $workspace social workspace to collaborate in Wikis, Forums, Calendars, Gadgets and more."
			- Optional profile pics*/

		goToMail(invited_email, pass);
		checkAndDeleteMail(By.xpath(hpGadget.ELEMENT_GMAIL_EMAIL.replace("${user}",userName)), content.replace("${user}",userName),true,userName);

	}
}
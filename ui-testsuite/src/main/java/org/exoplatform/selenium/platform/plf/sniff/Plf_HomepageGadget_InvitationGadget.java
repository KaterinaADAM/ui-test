package org.exoplatform.selenium.platform.plf.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;


	/**
	* @author eXo
	*
	*/
	public class Plf_HomepageGadget_InvitationGadget extends Plf_TestConfig{
		
		String space1;
		String space2;
		@AfterMethod
		public void setAfterMethod(){
			info("Sign out");
			magAc.signOut();
			info("Sign in with mary account");
			magAc.signIn(DATA_USER2, DATA_PASS);
			hp.goToConnections();
			connMg.resetConnection(DATA_USER1);
			
			info("Sign out");
			magAc.signOut();
			info("Sign in with james account");
			magAc.signIn(DATA_USER3, DATA_PASS);
			hp.goToConnections();
			connMg.resetConnection(DATA_USER1);
			
			info("Sign out");
			magAc.signOut();
			info("Sign in with demo account");
			magAc.signIn(DATA_USER4, DATA_PASS);
			hp.goToConnections();
			connMg.resetConnection(DATA_USER1);
			
			info("Sign out");
			magAc.signOut();
			info("Sign in with FQAVN account");
			magAc.signIn("fqa","gtngtn");
			hp.goToConnections();
			connMg.resetConnection(DATA_USER1);
			
			magAc.signOut();
			info("Sign in with john account");
			magAc.signIn(DATA_USER1, DATA_PASS);
		}
		
	/**
	 * Create 3 connections requests to John account
	 * Create 2 space request to John account
	 */
	public void createRequestsConnections(){
		info("--Send request 1 to John");
		info("Sign out");
		magAc.signOut();
		info("Sign in with mary account");
		magAc.signIn(DATA_USER2, DATA_PASS);
		hp.goToConnections();
		connMg.connectToAUser(DATA_USER1);
		
		info("Create 1 spaces and invit John");
		space1=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		space2=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToMySpaces();
		spaceMg.addNewSpaceSimple(space1,space1);
		spaceHome.goToSettingTab();
		setMag.inviteUser("John");
		
		info("--Send request 2 to John");
		info("Sign out");
		magAc.signOut();
		info("Sign in with james account");
		magAc.signIn(DATA_USER3, DATA_PASS);
		hp.goToConnections();
		connMg.connectToAUser(DATA_USER1);
		
		hp.goToMySpaces();
		spaceMg.addNewSpaceSimple(space2,space2);
		spaceHome.goToSettingTab();
		setMag.inviteUser("John");
		
		info("--Send request 3 to John");
		info("Sign out");
		magAc.signOut();
		info("Sign in with demo account");
		magAc.signIn(DATA_USER4, DATA_PASS);
		hp.goToConnections();
		connMg.connectToAUser(DATA_USER1);
		
		info("--Login back to John");
		info("Sign out");
		magAc.signOut();
		info("Sign in with John account");
		magAc.signIn(DATA_USER1, DATA_PASS);
	}
	/**
	 * delete all spaces created before
	 */
	public void deleteDataTest(){
        info("Delete DATA for the last test");
        info("Signin with mary account");
    	magAc.signOut();
		magAc.signIn(DATA_USER2, DATA_PASS);
		hp.goToMySpaces();
		spaceMg.deleteSpace(space1, false);
		
		info("Signin with james account");
		magAc.signOut();
		magAc.signIn(DATA_USER3, DATA_PASS);
		hp.goToMySpaces();
		spaceMg.deleteSpace(space2, false);
		
	}
	/**
	*<li> Case ID:120861.</li>
	*<li> Test Case Name: Not show Invitation gadget.</li>
	*<li> Pre-Condition: - UserA does not receive any invitation from user or space</li>
	*/
	@Test
	public void test01_NotShowInvitationGadget(){
		info("Test 01: Not show Invitation gadget");
		/*Step Number: 1
		*Step Name: - Check if no invitation
		*Step Description: 
			- Login as UserA 
			- Go to intranet home page
		*Input Data: 
			
		*Expected Outcome: 
			This gadget is not shown*/ 
		driver.navigate().refresh();
		waitForElementNotPresent(hp.ELEMENT_INVITATIONS_GADGET);
	}
	
	/**
	*<li> Case ID:120854.</li>
	*<li> Test Case Name: Check display of Invitation Gadget.</li>
	*<li> Pre-Condition: - There are 3 connection request are sent to userA
	- There are 2 invitation form space are sent to userA</li>
	*/
	@Test
	public void test02_CheckDisplayInvitationGadget(){
		/*Step Number: 1
		*Step Name: Check display gadget
		*Step Description: 
			- Login as userA
			- Open intranet home
			- Check Invitation gadget
		*Input Data: 
			
		*Expected Outcome: 
			- A maximum number of displayed requests is 4
			-The oldest request will appear at the bottom while the newest will appear on the top
			- For user request, the gadget displays the profile picture of the user, his name and if available, his title.
			- For Spaces request, the gadget displays the space icon, the name, privacy status which is private or public, and the number of members in the space.
			- The title of the gadget will show the total number of requests received which is 4*/ 
		info("Create datatest");
		createRequestsConnections();
		
		info("Verify that the maximum number of displayed requests is 4");
		info("Verify that for user request, the portlet will displayes the profile picture of the user, his name");
		waitForAndGetElement(hp.ELEMENT_INVITATIONS_PEOPLE_AVATAR .replace("${name}","James"),3000,0);
		waitForAndGetElement(hp.ELEMENT_INVITATIONS_PEOPLE_AVATAR .replace("${name}","Mary"),2000,0);
		waitForAndGetElement(hp.ELEMENT_INVITATIONS_PEOPLE_AVATAR .replace("${name}","Jack"),2000,0);
		waitForAndGetElement(hp.ELEMENT_INVITAITONS_SPACE_ICON.replace("${name}",space1),2000,0);
		waitForAndGetElement(hp.ELEMENT_INVITAITONS_SPACE_ICON.replace("${name}",space2),2000,0);
		waitForElementNotPresent(hp.ELEMENT_INVITAITONS_SPACE_ICON.replace("${name}",space2),2000,0);
	    
	    info("Verify that for space request, the gadget displays the space icon, the name, privacy status which is private or public, and the number of members in the space.");
	    waitForAndGetElement(hp.ELEMENT_INVITAITONS_SPACE_STATUS_MEMBERS.replace("${name}",space1).replace("${statusMember}","Private Space - 1 Members"),2000,0);
	    
	    info("Verify that The title of the gadget will show the total number of requests received which is 5");
	    waitForAndGetElement(By.xpath(hp.ELEMENT_INVITATIONS_NUMBER.replace("${number}", "5")),2000,0);
	
	    deleteDataTest();
	}
	/**
	*<li> Case ID:120855.</li>
	*<li> Test Case Name: Accept a request.</li>
	*<li> Pre-Condition: - Add users (root, mary)
	- Root sends request to connect to John
	- Mary sends connection request to John</li>
	*/
	@Test
	public void test03_AcceptARequest(){
		info("Test03: Accept a Request");
		/*Step Number: 1
		*Step Name: Accept a request
		*Step Description: 
			- Login as John
			- Open intranet homepage
			- On this gadget, mouse over an invitation of mary
			- Click on Accept button
		*Input Data: 
		/*Expected Outcome: 
			- The invitation of root, mary is shown on the invitation gadget
			- The Accept and Refuse button are displayed.
			- John is connected to mary and the invitation fades out and is permanently removed from the list
			- Request of root are moving to the top of the gadget if needed*/ 
		info("--Send request 1 to John");
		magAc.signOut();
		info("Sign in with mary account");
		magAc.signIn(DATA_USER2, DATA_PASS);
		hp.goToConnections();
		connMg.connectToAUser(DATA_USER1);
		
		info("--Send request 2 to John");
		info("Sign out");
		magAc.signOut();
		info("Sign in with james account");
		magAc.signIn(DATA_USER3, DATA_PASS);
		hp.goToConnections();
		connMg.connectToAUser(DATA_USER1);
		
		magAc.signOut();
		info("Sign in with john account");
		magAc.signIn(DATA_USER1, DATA_PASS);
		mouseOver(hp.ELEMENT_INVITATIONS_PEOPLE_AVATAR .replace("${name}","Mary"),true);
		Utils.pause(2000);
		WebElement elementToClick=waitForAndGetElement(hp.ELEMENT_INVITATIONS_PEOPLE_ACCEPT_BTN.replace("${name}","Mary Williams"),5000,1,2);
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", elementToClick);
		waitForElementNotPresent(hp.ELEMENT_INVITATIONS_PEOPLE_AVATAR .replace("${name}","Mary"));
		waitForAndGetElement(hp.ELEMENT_INVITATIONS_PEOPLE_AVATAR .replace("${name}","James"));
		
	}
	
	/**
	*<li> Case ID:120856.</li>
	*<li> Test Case Name: Refuse a request.</li>
	*<li> Pre-Condition: - Root sends connection request to mary
	- John sends connection request to mary</li>
	*/
	@Test
	public void test04_RefuseARequest(){
        info("Test 04: Refuse a request");
        //createRequestsConnections();
		/*Step Number: 1
		*Step Name: Refuse a request
		*Step Description: 
			- Login as mary
			- Open intranet homepage
			- On this gadget, mouse over an invitation of Jack(demo)
			- Click on Refuse icon
		*Input Data: 
			
		*Expected Outcome: 
			- Invitations of root, james are displayed on Invitation gadget
			- The Accept and Refuse button are displayed.
			- The invitation of jack fades out and is permanently removed from the list
			- Requests of James is moved to the top of the gadget*/
        info("--Send request 3 to John");
		info("Sign out");
		magAc.signOut();
		info("Sign in with demo account");
		magAc.signIn(DATA_USER4, DATA_PASS);
		hp.goToConnections();
		connMg.connectToAUser(DATA_USER1);
		
		info("--Send request 2 to John");
		info("Sign out");
		magAc.signOut();
		info("Sign in with james account");
		magAc.signIn(DATA_USER3, DATA_PASS);
		hp.goToConnections();
		connMg.connectToAUser(DATA_USER1);
        
		magAc.signOut();
		info("Sign in with john account");
		magAc.signIn(DATA_USER1, DATA_PASS);
        mouseOver(hp.ELEMENT_INVITATIONS_PEOPLE_AVATAR .replace("${name}","Jack"),true);
		Utils.pause(2000);
        WebElement elementToClick2=waitForAndGetElement(By.xpath(hp.ELEMENT_INVITATIONS_PEOPLE_REFUSE_BTN.replace("${name}","Jack Miller")),5000,1,2);
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", elementToClick2);
		waitForElementNotPresent(hp.ELEMENT_INVITATIONS_PEOPLE_AVATAR .replace("${name}","Jack"));
        waitForAndGetElement(hp.ELEMENT_INVITATIONS_PEOPLE_AVATAR .replace("${name}","James"));
		
		hp.goToConnections();
		waitForAndGetElement(connMg.ELEMENT_CONNECTION_EVERYONE_CONNECT_BTN.replace("${user}","Jack Miller"),2000,0);
	}
}
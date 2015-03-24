package org.exoplatform.selenium.platform.plf.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;


	/**
	* @author eXo
	*
	*/
	public class Plf_HomepageGadget_SuggestionGadget extends Plf_TestConfig{
		
		String space1;
		String space2;
		String space3;
		String space4;
		String space5;

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
		 * Create 3 spaces
		 */
		public void createSpaces(){
			info("--Login with mary account--");
			info("Sign out");
			magAc.signOut();
			magAc.signIn(DATA_USER2, DATA_PASS);
			info("Create 1 space");
			space1=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
			hp.goToMySpaces();
			spaceMg.addNewSpaceSimple(space1,space1);
			
			info("--Log in with james account--");
			info("Sign out");
			magAc.signOut();
			magAc.signIn(DATA_USER3, DATA_PASS);
			info("Create 1 space");
			space2=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
			hp.goToMySpaces();
			spaceMg.addNewSpaceSimple(space2,space2);
			
			info("-- Log in with demo account--");
			info("Sign out");
			magAc.signOut();
			magAc.signIn(DATA_USER4, DATA_PASS);
			info("Create 1 space");
			space3=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
			hp.goToMySpaces();
			spaceMg.addNewSpaceSimple(space3,space3);
			
			info("--Login back to John");
			info("Sign out");
			magAc.signOut();
			info("Sign in with John account");
			magAc.signIn(DATA_USER1, DATA_PASS);
		}
		
		/**
		 * Create 2 spaces and invite to John account
		 */
		public void createTwoSpaces(){
			info("--Login with mary account--");
			info("Sign out");
			magAc.signOut();
			magAc.signIn(DATA_USER2, DATA_PASS);
			info("Create 1 space");
			space4=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
			hp.goToMySpaces();
			spaceMg.addNewSpaceSimple(space4,space4);
			spaceHome.goToSettingTab();
			setMag.inviteUser("John");
			
			info("--Log in with james account--");
			info("Sign out");
			magAc.signOut();
			magAc.signIn(DATA_USER3, DATA_PASS);
			info("Create 1 space");
			space5=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
			hp.goToMySpaces();
			spaceMg.addNewSpaceSimple(space5,space5);
			spaceHome.goToSettingTab();
			setMag.inviteUser("John");
			
			info("--Login back to John");
			info("Sign out");
			magAc.signOut();
			info("Sign in with John account");
			magAc.signIn(DATA_USER1, DATA_PASS);
		}
		/**
		 * Delete all spaces are created before
		 */
		public void deleteDataTest(){
			info("Delete space 1");
			magAc.signOut();
			magAc.signIn(DATA_USER2, DATA_PASS);
			hp.goToMySpaces();
			spaceMg.deleteSpace(space1, false);
			info("Delete space 2");
			magAc.signOut();
			magAc.signIn(DATA_USER3, DATA_PASS);
			hp.goToMySpaces();
			spaceMg.deleteSpace(space2, false);
			info("Delete space 3");
			magAc.signOut();
			magAc.signIn(DATA_USER4, DATA_PASS);
			hp.goToMySpaces();
			spaceMg.deleteSpace(space3, false);
		}
		
		/**
		*<li> Case ID:120858.</li>
		*<li> Test Case Name: Not show suggestions gadget.</li>
		*/
		@Test
		public  void test02_NotShowSuggestionsGadget() {
			info("Test 02: Not show suggestions gadget");
			/*Step number: 4
			*Step Name: Invite user A
			*Step Description: 
				- Login with user B and C
				- Go to [Space setting]
				- Choose [member] tab
				- invite user A is member of space
			*Input Data: 
				
			*Expected Outcome: 
				Invite user successfully*/
			info("Invite John to 2 spaces");
			createTwoSpaces();
			
			/*Step number: 5
			*Step Name: Check show the space suggestion
			*Step Description: 
				- Login by user A
			*Input Data: 
				
			*Expected Outcome: 
				Don't show the space suggestion*/
			info("Verify that Don't show the space suggestion");
			waitForElementNotPresent(hp.ELEMENT_SUGGESTION_SPACE.replace("${space}",space4));
			waitForElementNotPresent(hp.ELEMENT_SUGGESTION_SPACE.replace("${space}",space5));
			
			/*Step number: 6
			*Step Name: Invite user
			*Step Description: 
				- Login by user A
				- Go to [My connetions]
				- Connect with user B and C
			*Input Data: 
				
			*Expected Outcome: 
				Connect user successfully*/
			hp.goToConnections();
			connMg.connectToAUser(DATA_USER4);
			connMg.connectToAUser(DATA_USER2);
			connMg.connectToAUser(DATA_USER3);
			connMg.connectToAUser("fqa");
			
			/*Step number: 7
			*Step Name: Check when this gadget disappears
			*Step Description: 
				Back to homepage
			*Input Data: 
				
			*Expected Outcome: 
				The suggestions gadget is not hided*/ 
			hp.goToHomePage();
			waitForElementNotPresent(hp.ELEMENT_SUGGESTION_BOX);
			
			info("delete data");
			hp.goToConnections();
			connMg.cancelConnection(DATA_USER4);
			connMg.cancelConnection(DATA_USER2);
			connMg.cancelConnection(DATA_USER3);
			connMg.cancelConnection("fqa");
			
			magAc.signOut();
			magAc.signIn(DATA_USER2, DATA_PASS);
			hp.goToMySpaces();
			spaceMg.deleteSpace(space4, false);
			magAc.signOut();
			magAc.signIn(DATA_USER3, DATA_PASS);
			hp.goToMySpaces();
			spaceMg.deleteSpace(space5, false);
	 	}
	/**
	*<li> Case ID:120857.</li>
	*<li> Test Case Name: Check display of Suggestions Gadget.</li>
	*<li> Pre-Condition: There are more than 4 users on system.
	*There are more than 3 spaces on system which userA is not member</li>
	*<li> Post-Condition: </li>
	*<li> Done with OSs and browsers : </li>
	*<li> Generated by rosso at 2015/02/09 14:31:58</li>
	*/
	@Test
	public  void test01_CheckDisplayOfSuggestionsGadget() {
		info("Test 01: Check display of Suggestions Gadget");
		/*Step Number: 1
		*Step Name: - Check display of Suggestions gadget
		*Step Description: 
			- Login as userA
			- Open intranet home
		*Input Data: 
			
		*Expected Outcome: 
			- This gadget is displayed at the right, as attachment SuggestionsGadget.png
			- The suggestion gadget always displays 2 people suggestions and 2 space suggestions.
			- The 2 people suggestions are people with the most common connections with the users and ordered by alphabet
			- The 2 space suggestions are spaces that have the most members who are user's connections and ordered by creation date*/
		info("--Create 3 spaces--");
		createSpaces();
		info("Verify that The suggestion gadget always displays 2 people suggestions");
		waitForAndGetElement(hp.ELEMENT_SUGGESTION_NAME.replace("{$name}","FQA VN"),2000,0);
		waitForAndGetElement(hp.ELEMENT_SUGGESTION_NAME.replace("{$name}","Jack Miller"),2000,0);
		waitForElementNotPresent(hp.ELEMENT_SUGGESTION_NAME.replace("{$name}","Mary Williams"));
		info("Verify that The suggestion gadget always displays 2 last spaces suggestions");
		waitForAndGetElement(hp.ELEMENT_SUGGESTION_SPACE.replace("${space}",space3),2000,0);
		waitForAndGetElement(hp.ELEMENT_SUGGESTION_SPACE.replace("${space}",space2),2000,0);
		deleteDataTest();
 	}

	/**
	*<li> Case ID:120859.</li>
	*<li> Test Case Name: Accept a space suggestion.</li>
	*<li> Pre-Condition: There are some spaces on system created by userA for ex</li>
	*/
	@Test
	public  void test03_AcceptASpaceSuggestion(){
		info("Test 03: Accept a space suggestion");
		/*Step Number: 1
		*Step Name: Show Space suggestion
		*Step Description: 
			- Login
			- Moves the mouse over a "Space suggestion" of a space
		*Input Data: 
			
		*Expected Outcome: 
			- Space suggestion is shown on Suggestion calendar
			- The request and cancel button are displayed*/
		info("Sign out");
		magAc.signOut();
		magAc.signIn(DATA_USER2, DATA_PASS);
		info("Create 1 space");
		space1=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToMySpaces();
		spaceMg.addNewSpaceSimple(space1,space1);
		
		info("--Log in with james account--");
		info("Sign out");
		magAc.signOut();
		magAc.signIn(DATA_USER3, DATA_PASS);
		info("Create 1 space");
		space2=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToMySpaces();
		spaceMg.addNewSpaceSimple(space2,space2);
		
		info("Sign out");
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		
		info("Click on Accept button of Space2");
		mouseOver(hp.ELEMENT_SUGGESTION_SPACE.replace("${space}",space2),true);
		Utils.pause(2000);
		waitForAndGetElement(hp.ELEMENT_SUGGESTION_SPACE_CANCEL_BTN.replace("${space}",space2),5000,1,2);
		waitForAndGetElement(hp.ELEMENT_SUGGESTION_SPACE_REQUEST_BTN.replace("${space}",space2),5000,1,2);
		
		/*Step number: 2
		*Step Name: Accept a space suggestion
		*Step Description: 
			- Click on Request button
		*Input Data: 
			
		*Expected Outcome: 
			- Request is sent to space. The suggestionfades out and is never suggested again.
			- The suggestion of other space is moved up*/ 
		info("Request is sent to space. The suggestionfades out and is never suggested again.");
		WebElement elementToClick=waitForAndGetElement(hp.ELEMENT_SUGGESTION_SPACE_REQUEST_BTN.replace("${space}",space2),5000,1,2);
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", elementToClick);
		waitForElementNotPresent(hp.ELEMENT_SUGGESTION_SPACE.replace("${space}",space2));
		info("The suggestion of other space is moved up");
		waitForAndGetElement(hp.ELEMENT_SUGGESTION_SPACE.replace("${space}",space1));
		
		info("Delete space 1");
		magAc.signOut();
		magAc.signIn(DATA_USER2, DATA_PASS);
		hp.goToMySpaces();
		spaceMg.deleteSpace(space1, false);
		info("Delete space 2");
		magAc.signOut();
		magAc.signIn(DATA_USER3, DATA_PASS);
		hp.goToMySpaces();
		spaceMg.deleteSpace(space2, false);
	}

	/**
	 * <li>Case ID:120863.</li> <li>Test Case Name: Cancel a space connection.</li>
	 * <li>Pre-Condition: There are some spaces created on application</li>
	 */
	@Test
	public void test04_CancelASpaceConnection(){
		info("Test 04: Cancel a space connection");
		/*Step Number: 1
		*Step Name: Show space connection
		*Step Description: 
			- Login
			- Moves the mouse over a "Space suggestion" of a space
		*Input Data: 
			
		*Expected Outcome: 
			- Space suggestion is shown on Suggestion calendar
			- The request and cancel button are displayed*/
		info("Sign out");
		magAc.signOut();
		magAc.signIn(DATA_USER2, DATA_PASS);
		info("Create 1 space");
		space1=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToMySpaces();
		spaceMg.addNewSpaceSimple(space1,space1);
		
		info("--Log in with james account--");
		info("Sign out");
		magAc.signOut();
		magAc.signIn(DATA_USER3, DATA_PASS);
		info("Create 1 space");
		space2=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToMySpaces();
		spaceMg.addNewSpaceSimple(space2,space2);
		
		info("Sign out");
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		
		info("Click on Accept button of Space1");
		mouseOver(hp.ELEMENT_SUGGESTION_SPACE.replace("${space}",space1),true);
		Utils.pause(2000);
		waitForAndGetElement(hp.ELEMENT_SUGGESTION_SPACE_CANCEL_BTN.replace("${space}",space1),5000,1,2);
		waitForAndGetElement(hp.ELEMENT_SUGGESTION_SPACE_REQUEST_BTN.replace("${space}",space1),5000,1,2);
		/*Step number: 2
		*Step Name: Cancel a space suggestion
		*Step Description: 
			- Click on Cancel button
		*Input Data: 
			
		*Expected Outcome: 
			- The suggestion of space fades out and is never suggested again. 
			- The suggestion of other space is moved up*/ 
		info("Cancel is sent to space. The suggestionfades out and is never suggested again.");
		WebElement elementToClick2=waitForAndGetElement(hp.ELEMENT_SUGGESTION_SPACE_CANCEL_BTN.replace("${space}",space1),5000,1,2);
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", elementToClick2);
		waitForElementNotPresent(hp.ELEMENT_SUGGESTION_SPACE.replace("${space}",space1),5000,1,2);
		info("The suggestion of other space is moved up");
		waitForAndGetElement(hp.ELEMENT_SUGGESTION_SPACE_REQUEST_BTN.replace("${space}",space2),5000,1,2);
		
		info("Delete space 1");
		magAc.signOut();
		magAc.signIn(DATA_USER2, DATA_PASS);
		hp.goToMySpaces();
		spaceMg.deleteSpace(space1, false);
		
		info("Delete space 1");
		magAc.signOut();
		magAc.signIn(DATA_USER3, DATA_PASS);
		hp.goToMySpaces();
		spaceMg.deleteSpace(space2, false);
	}

	/**
	*<li> Case ID:120860.</li>
	*<li> Test Case Name: Accept a people suggestion.</li>
	*<li> Pre-Condition: There are some users on system</li>
	*<li> Case ID:120862.</li>
	*<li> Test Case Name: Cancel a people suggestion.</li>
	*<li> Pre-Condition: There are some users on system</li>
	*/
	@Test
	public  void test05_AcceptAPeopleSuggestion() {
		info("Test 05: Accept a people suggestion");
		String userFQA="FQA VN";
		String userJames="James Davis";
		/*Step Number: 1
		*Step Name: Show people suggestion
		*Step Description: 
			- Login as john
			- Open intranet home 
			- Moves the mouse over a "People suggestion" of an user
		*Input Data: 
			
		*Expected Outcome: 
			- Suggestions of 2 users are displayed
			- The Connect and cancel button are displayed.*/
		info("Sign out");
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		info("The Connect and cancel button are displayed");
		mouseOver(hp.ELEMENT_SUGGESTION_NAME.replace("${name}",userFQA),true);
		Utils.pause(2000);
		waitForAndGetElement(hp.ELEMENT_SUGGESTION_PEOPLE_CANCEL_BTN.replace("${name}",userFQA),5000,1,2);
		waitForAndGetElement(hp.ELEMENT_SUGGESTION_PEOPLE_CONNECT_BTN.replace("${name}",userFQA),5000,1,2);
		
		/*Step number: 2
		*Step Name: Accept a people suggestion
		*Step Description: 
			- Click on Connect button
		*Input Data: 
			
		*Expected Outcome: 
			- Connection request is sent to user. The suggestion fades out and is never suggested again.
			- Suggestion of other is moved up.*/ 
		info("Connection request is sent to user. The suggestion fades out and is never suggested again.");
		WebElement elementToClick=waitForAndGetElement(hp.ELEMENT_SUGGESTION_PEOPLE_CONNECT_BTN.replace("${name}",userFQA),5000,1,2);
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", elementToClick);
		waitForElementNotPresent(hp.ELEMENT_SUGGESTION_NAME.replace("${name}",userFQA));
		info("Suggestion of other is moved up.");
		waitForAndGetElement(hp.ELEMENT_SUGGESTION_NAME.replace("${name}",userJames),5000,1,2);
		
 	}
	
	@Test
	public void test06_CancelAPeopleSuggestion(){
        info("Test 06: Cancel a people suggestion");
		String userJack="Jack Miller";
		String userMary="Mary Williams";

        /*Step Number: 1
		*Step Name: Show people suggestion
		*Step Description: 
			- Login as john
			- Open intranet home 
			- Moves the mouse over a "People suggestion" of an user
		*Input Data: 
			
		*Expected Outcome: 
			- Suggestions of 2 users are displayed
			- The Connect and cancel button are displayed.*/
        info("The Connect and cancel button are displayed");
		mouseOver(hp.ELEMENT_SUGGESTION_NAME.replace("${name}",userJack),true);
		Utils.pause(2000);
		waitForAndGetElement(hp.ELEMENT_SUGGESTION_PEOPLE_CANCEL_BTN.replace("${name}",userJack),5000,1,2);
		waitForAndGetElement(hp.ELEMENT_SUGGESTION_PEOPLE_CONNECT_BTN.replace("${name}",userJack),5000,1,2);
		/*Step number: 2
		*Step Name: Cancel a people suggestion
		*Step Description: 
			- Click on Cancel button
		*Input Data: 
			
		*Expected Outcome: 
			- Connection cancel is sent. The suggestion fades out and is never suggested again.
			- Suggestion of other is moved up.*/ 
		info("Connection request is sent to user. The suggestion fades out and is never suggested again.");
		WebElement elementToClick2=waitForAndGetElement(hp.ELEMENT_SUGGESTION_PEOPLE_CANCEL_BTN.replace("${name}",userJack),5000,1,2);
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", elementToClick2);
		waitForElementNotPresent(hp.ELEMENT_SUGGESTION_NAME.replace("${name}",userJack));
		info("Suggestion of other is moved up");
		waitForAndGetElement(hp.ELEMENT_SUGGESTION_NAME.replace("${name}",userMary),5000,1,2);
		
	}
	
	

}
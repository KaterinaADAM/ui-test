package org.exoplatform.selenium.platform.wiki.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.testng.annotations.*;
import org.exoplatform.selenium.Utils;

public class Wiki_Basic_Action_OtherActions extends Wiki_TestConfig {
    
	@AfterMethod
	public void setAfterMedthod(){
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
	}
	/**
	 *<li> Case ID:122810.</li>
	 *<li> Test Case Name: Move Page.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 * issue WIKI-976, wrong address in the mail notification
	 */
	@Test
	public  void test01_MovePage() {
		info("Test 1: Move Page");

		String title1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String title2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();

		/*Step Number: 1
		 *Step Name: Step 1: Move Page
		 *Step Description: 
			- Open an existing page by clicking on page name in navigation tree.
			- Click on More icon on toolbar and select Move Page action in menu
			- In Move form: Select new location and click on Move button
		 *Input Data: 

		 *Expected Outcome: 
			Selected Page is moved and displayed in new location*/ 

		info("Create first new wiki pages");
		hp.goToWiki();
		wHome.goToAddBlankPage();
		wikiMg.addWikiPageSimpleWithSourceEditor(title1,title1);
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",title1),2000,0);
		
		info("Create second new wiki pages");
		hp.goToWiki();
		wHome.goToAddBlankPage();
		wikiMg.addWikiPageSimpleWithSourceEditor(title2,title2);
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",title2),2000,0);
		
		info("Move page 1 to page 2");
		wikiMg.movePage(title1, title2);
		
		hp.goToWiki();
		wHome.deleteWiki(title2);
		
	}

	/**
	 *<li> Case ID:122811.</li>
	 *<li> Test Case Name: Watch a wiki page of intranet.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 * check issue WIKI-976 (wrong link in the mail)
	 */
	@Test
	public  void test17_WatchAWikiPageOfIntranet() {
		info("Test 17: Watch a wiki page of intranet");

		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String update = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
        String mess = wMessage.getWikiMessage(3);
        info("mess:"+mess);
		
		/*Step Number: 1
		 *Step Name: Step 1: Watch Page
		 *Step Description: 
			- Go to Wiki application from Intranet
			- Open an existing page by clicking on page name in navigation tree.
			- Click on More icon on toolbar and select Watch action in menu
		 *Input Data: 

		 *Expected Outcome: 
			Show message :"You started watching this page now." Selected page is watched.*/
		
		hp.goToWiki();
		wHome.goToAddBlankPage();
		wikiMg.addWikiPageSimpleWithSourceEditor(title,title);
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",title),2000,0);
		
		info("Watch the wiki");
		magAc.signOut();
		magAc.signIn(DATA_USER2, DATA_PASS);
		hp.goToWiki();
		wHome.goToAPage(title);
		wikiMg.watchAPage(mess);
        
        
		/*Step number: 2
		 *Step Name: Step 2: Edit page
		 *Step Description: 
			- Login as other user
			- Choose watched wiki page at step 1 and click Edit Page
			- Change page content
			- Check the check box "Publish Activity" and click Save
		 *Input Data: 

		 *Expected Outcome: 
			- Page is saved successfully
			- An email notification will send to email of watcher about changed content*/
        info("Edit the page");
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		hp.goToWiki();
		wHome.goToAPage(title);
		wHome.goToEditPage();
		wikiMg.editWikiPageSimpleWithSourceEditor("", update);
		wikiMg.publishPage();
		wikiMg.saveAddPage();
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",title),2000,0);
		
		/*Step number: 3
		 *Step Name: Step 3: Open the Wiki page via email notification
		 *Step Description: 
			- Open the email notification at step 2
			- Click on the wiki page link in the email
		 *Input Data: 

		 *Expected Outcome: 
			- The wiki page is opened with the link format: http://Server_IP_address:8080/portal/intranet/wiki/[wiki_page_name] (For example: http://localhost:8080/portal/intranet/wiki/Page_1)
			- The browser navigates to the corresponding wiki page in wiki portlet*/ 
		info("Check email notification and link's format of the page");
		goToMail("fqaexovn@gmail.com", "exoadmin");
		wikiMg.checkEmailNotification(title);
        
        info("Delete page");
        hp.goToWiki();
        wHome.deleteWiki(title);
	}

	/**
	 *<li> Case ID:122821.</li>
	 *<li> Test Case Name: Export page as PDF.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test(groups="pending")
	public  void test03_ExportPageAsPDF() {
		info("Test 3: Export page as PDF");
		
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		/*Step Number: 1
		 *Step Name: Step 1: Export a page as PDF
		 *Step Description: 
			- Open an existing page by clicking on page name in navigation tree 
			- Click on More icon on toolbar and selectExport as PDF action in menu
		 *Input Data: 

		 *Expected Outcome: 
			- Convert wiki's page as PDF format
			- The download of page is started
			- The name of file is: "Page_name.pdf"*/
		info("Create a new wiki pages");
		hp.goToWiki();
		wHome.goToAddBlankPage();
		wikiMg.addWikiPageSimpleWithSourceEditor(title,title);
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",title),2000,0);
		
		
		click(wHome.ELEMENT_MORE_LINK);
		click(wHome.ELEMENT_PDF_LINK);
	}

	/**
	 *<li> Case ID:122826.</li>
	 *<li> Test Case Name: Check permalink when user is member of page.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test04_CheckPermalinkWhenUserIsMemberOfPage() {
		info("Test 4: Check permalink when user is member of page");
		
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		/*Step Number: 1
		 *Step Name: Step 1: Check permalink when user is member of page
		 *Step Description: 
			- Go to wiki page
			- Add new page ( with permission default: have view permission)
			- Select More 
			-> choose Permalink
			- Copy the link
			- Connect with User B, can view page
			- Paste the permalink
			- Click Enter from the keyboard
		 *Input Data: 

		 *Expected Outcome: 
			The user B can view the page created by the manager*/ 
		info("Create a new wiki pages");
		hp.goToWiki();
		wHome.goToAddBlankPage();
		wikiMg.addWikiPageSimpleWithSourceEditor(title,title);
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",title),2000,0);
		
		String perLink=wikiMg.permalinkAPage();
		magAc.signOut();
		magAc.signIn(DATA_USER2, DATA_PASS);
		driver.get(perLink);
		waitForAndGetElement(wHome.ELEMENT_WIKI_PAGE_LINK.replace("${pageTitle}", title));
		
		info("Delete page");
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
	    hp.goToWiki();
	    wHome.deleteWiki(title);
	}

	/**
	 *<li> Case ID:122827.</li>
	 *<li> Test Case Name: Check permalink when user is not member of page.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test05_CheckPermalinkWhenUserIsNotMemberOfPage() {
		info("Test 5: Check permalink when user is not member of page");
		
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		/*Step Number: 1
		 *Step Name: Step 1: Add new page when set permission for page
		 *Step Description: 
			- Login by admin
			- Go to wiki page
			- Add new page 
			- Set permission for page: remove any permission
			- Select More 
			-> choose Permalink
			- Copy the link
		 *Input Data: 

		 *Expected Outcome: 
			- Add new page have permission successfully
			- Copy link successfully*/

		info("Create a new wiki pages");
		hp.goToWiki();
		wHome.goToAddBlankPage();
		wikiMg.addWikiPageSimpleWithSourceEditor(title,title);
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",title),2000,0);
		info("Un check view permission of any group");
		wikiMg.unCheckViewAUserOfPage(wHome.ELEMENT_PERMISSION_VIEW_ANY);
		
		String perLink=wikiMg.permalinkAPage();
	
		/*Step number: 2
		 *Step Name: Step 2: Check permalink when user is not member of page
		 *Step Description: 
			- Login by mary not a member in the page
			- Paste the permalink
			- Click Enter from the keyboard
		 *Input Data: 

		 *Expected Outcome: 
			The "Page Not found" is displayed, the user B cannot view the page*/ 
		magAc.signOut();
		magAc.signIn(DATA_USER2, DATA_PASS);
		driver.get(perLink);
		Utils.pause(5000);
		waitForAndGetElement(wHome.ELEMENT_WIKI_HOME_PAGENOTFOUND);
		
		info("Delete page");
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
	    hp.goToWiki();
	    wHome.deleteWiki(title);
	}

	/**
	 *<li> Case ID:122828.</li>
	 *<li> Test Case Name: Check permalink when user is member of space.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test06_CheckPermalinkWhenUserIsMemberOfSpace() {
		info("Test 6: Check permalink when user is member of space");
		
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String wiki = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		/*Step Number: 1
		 *Step Name: Step 1: Add wiki page for space
		 *Step Description: 
			- Login with user A is space manager
			- Go to Space/wiki
			- Create a public page
		 *Input Data: 

		 *Expected Outcome: 
			a public page is created by the manager of the space*/
		info("Create space 1 and wiki page 1");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,space);
		spaHome.goToSettingTab();
		setSpaceMg.inviteUser("mary");
		
		info("Add new wiki page 1 for space 1");
		spaHome.goToWikiTab();
		wHome.goToAddBlankPage();
		wikiMg.addWikiPageSimpleWithSourceEditor(wiki,wiki);
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",wiki),2000,0);
		
		/*Step number: 2
		 *Step Name: Step 2: Check permalink when user is member of space
		 *Step Description: 
			- From the list "More", choose the link "Permalink"
			- Copy the link
			- Connect with User B, a member in the space
			- Paste the permalink
			- Click Enter from the keyboard
		 *Input Data: 

		 *Expected Outcome: 
			The member of space can view the page created by the manager*/ 
		String perLink=wikiMg.permalinkAPage();
		magAc.signOut();
		magAc.signIn(DATA_USER2, DATA_PASS);
		hp.goToMySpaces();
		spaMg.acceptAInvitation(space);
		driver.get(perLink);
		waitForAndGetElement(wHome.ELEMENT_WIKI_PAGE_LEFTBOX.replace("${title}",wiki));
		
		/*info("Delete page");
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		hp.goToMySpaces();
		spaMg.deleteSpace(space,false);*/

	}

	/**
	 *<li> Case ID:122829.</li>
	 *<li> Test Case Name: Check permalink when user is not member of space.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test07_CheckPermalinkWhenUserIsNotMemberOfSpace() {
		info("Test 7: Check permalink when user is not member of space");
		
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String wiki = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		/*Step Number: 1
		 *Step Name: Step 1: Add new page for space when set permission for page
		 *Step Description: 
			- Login by admin<br />
			- Add new space<br />
			- Go to space/wiki page<br />
			- Add new page <br />
			- Set permission for page: remove any permission<br />
			- Select More 
			-> choose Permalink<br />
			- Copy the link<br /><br />
		 *Input Data: 

		 *Expected Outcome: 
			- Add new page have permission successfully
			- Copy link successfully*/
		info("Create space 1 and wiki page 1");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,space);
		info("Add new wiki page 1 for space 1");
		spaHome.goToWikiTab();
		wHome.goToAddBlankPage();
		wikiMg.addWikiPageSimpleWithSourceEditor(wiki,wiki);
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",wiki),2000,0);
		
		String perLink=wikiMg.permalinkAPage();
		
		/*Step number: 2
		 *Step Name: Step 2: Check permalink when user is not member of space
		 *Step Description: 
			- From the list "More", choose the link "Permalink"<br />
			- Copy the link<br />
			- Connect with User B, not a member in the space<br />
			- Paste the permalink<br />
			- Click Enter from the keyboard<br />
		 *Input Data: 

		 *Expected Outcome: 
			The "Page Not found" is displayed, the user B cannot view the page*/
		magAc.signOut();
		magAc.signIn(DATA_USER2, DATA_PASS);
		driver.get(perLink);
		waitForAndGetElement(wHome.ELEMENT_WIKI_HOME_PAGENOTFOUND);
		
		
		/*info("Delete page");
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		hp.goToMySpaces();
		spaMg.deleteSpace(space,false);*/
	}

	/**
	 *<li> Case ID:122831.</li>
	 *<li> Test Case Name: Unwatch Page.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test08_UnwatchPage() {
		info("Test 8: Unwatch Page");
		
		String wiki = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String mess1 = wMessage.getWikiMessage(3);
	    info("mess1:"+mess1);
		String mess = wMessage.getWikiMessage(4);
	    info("mess:"+mess);
	        
		/*Step Number: 1
		 *Step Name: Step 1: Unwatch Page
		 *Step Description: 
			- Open an existing page by clicking on page name in navigation tree.
			- Click on More icon on toolbar and select Unwatch action in menu
		 *Input Data: 

		 *Expected Outcome: 
			Show message : "You stopped watching this page now." Selected page is unwatched. Watch icon is switched to Unwatch icon*/ 
		info("Add new wiki page");
		hp.goToWiki();
		wHome.goToAddBlankPage();
		wikiMg.addWikiPageSimpleWithSourceEditor(wiki,wiki);
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",wiki),2000,0);
		
		info("Watch the wiki");
		magAc.signOut();
		magAc.signIn(DATA_USER2, DATA_PASS);
		hp.goToWiki();
		wHome.goToAPage(wiki);
		wikiMg.watchAPage(mess1);
        wikiMg.unWatchAPage(mess);
        
        info("Delete page");
        magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
        hp.goToWiki();
        wHome.deleteWiki(wiki);
		
	}

	/**
	 *<li> Case ID:122849.</li>
	 *<li> Test Case Name: Check when change link is restricted.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test09_CheckWhenChangeLinkIsRestricted() {
		info("Test 9: Check when change link is restricted");
		
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		/*Step Number: 1
		 *Step Name: Step 1: Add new page is public
		 *Step Description: 
			- Go to wiki page
			- Add new page ( with permission default: have view permission)
		 *Input Data: 

		 *Expected Outcome: 
			The user B can view the page created by the manager*/
		info("Add new wiki page");
		hp.goToWiki();
		wHome.goToAddBlankPage();
		wikiMg.addWikiPageSimpleWithSourceEditor(title,title);
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",title),2000,0);
		
		magAc.signOut();
		magAc.signIn(DATA_USER2, DATA_PASS);
		hp.goToWiki();
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",title),2000,0);

		/*Step number: 2
		 *Step Name: Step 2: Change link is restricted
		 *Step Description: 
			- Choose page above
			- Select More 
			-> choose Permalink
			- Click the Restrict button.
		 *Input Data: 

		 *Expected Outcome: 
			The form will show that the page is changed into the restricted status and just the authorized users can view and edit it.*/
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		hp.goToWiki();
		wHome.goToAPage(title);
		
		wikiMg.permalinkAPage();
		click(wHome.ELEMENT_PERMALINK_MAKEPUBLIC);
		waitForAndGetElement(wHome.ELEMENT_PERMALINK_STATUS.replace("${status}","public"),2000,0);
		click(wHome.ELEMENT_PERMALINK_RESTRICT);
		waitForAndGetElement(wHome.ELEMENT_PERMALINK_STATUS.replace("${status}","restricted"),2000,0);
		click(wHome.ELEMENT_PERMALINK_CLOSE);
		Utils.pause(2000);
		/*Step number: 3
		 *Step Name: Step 3:Check after change to restricted
		 *Step Description: 
			- Copy the link
			- Login by mary not a member in the page
			- Paste the permalink
			- Click Enter from the keyboard
		 *Input Data: 

		 *Expected Outcome: 
			The "Page Not found" is displayed, the user B cannot view the page<br />*/ 
		String perLink=wikiMg.permalinkAPage();
		click(wHome.ELEMENT_PERMALINK_CLOSE);
		Utils.pause(2000);
		magAc.signOut();
		magAc.signIn(DATA_USER2, DATA_PASS);
		driver.get(perLink);
		waitForAndGetElement(wHome.ELEMENT_WIKI_HOME_PAGENOTFOUND);
		
		 info("Delete page");
	     magAc.signOut();
	     magAc.signIn(DATA_USER1, DATA_PASS);
	     hp.goToWiki();
	     wHome.deleteWiki(title);
	}

	/**
	 *<li> Case ID:122850.</li>
	 *<li> Test Case Name: Check when change link is public.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test10_CheckWhenChangeLinkIsPublic() {
		info("Test 10 Check when change link is public");
		
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		/*Step Number: 1
		 *Step Name: Step 1: Add new page is public
		 *Step Description: 
			- Go to wiki page
			- Add new page with restricted permission ( ex: remove any permission of page)
		 *Input Data: 

		 *Expected Outcome: 
			Add new page successfully and only user belong to group can view/edit page*/
		
		info("Add new wiki page");
		hp.goToWiki();
		wHome.goToAddBlankPage();
		wikiMg.addWikiPageSimpleWithSourceEditor(title,title);
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",title),2000,0);
		
		info("Un check view permission of any group");
		wikiMg.unCheckViewAUserOfPage(wHome.ELEMENT_PERMISSION_VIEW_ANY);

		/*Step number: 2
		 *Step Name: Step 2: Change link is public
		 *Step Description: 
			- Choose page above
			- Select More 
			-> choose Permalink
			- Click Make it Public button.
		 *Input Data: 

		 *Expected Outcome: 
			The form will show that the page is changed into the public status, and someone can view and edit it.*/
		wikiMg.permalinkAPage();
		click(wHome.ELEMENT_PERMALINK_MAKEPUBLIC);
		waitForAndGetElement(wHome.ELEMENT_PERMALINK_STATUS.replace("${status}","public"),2000,0);
		click(wHome.ELEMENT_PERMALINK_CLOSE);
		/*Step number: 3
		 *Step Name: Step 3: Check after change link
		 *Step Description: 
			- Copy the link
			- Connect with User B, can view page
			- Paste the permalink
			- Click Enter from the keyboard
		 *Input Data: 

		 *Expected Outcome: 
			The user B can view the page created by the manager*/ 
		String perLink=wikiMg.permalinkAPage();
		click(wHome.ELEMENT_PERMALINK_CLOSE);
		Utils.pause(2000);

		magAc.signOut();
		magAc.signIn(DATA_USER2, DATA_PASS);
		driver.get(perLink);
		waitForAndGetElement(wHome.ELEMENT_WIKI_PAGE_LINK.replace("${pageTitle}", title));
		
		info("Delete page");
	    magAc.signOut();
	    magAc.signIn(DATA_USER1, DATA_PASS);
	    hp.goToWiki();
	    wHome.deleteWiki(title);
	}

	/**
	 *<li> Case ID:122851.</li>
	 *<li> Test Case Name: Change permission for page in Permalink page.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test11_ChangePermissionForPageInPermalinkPage() {
		info("Test 11 Change permission for page in Permalink page");
		
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		/*Step Number: 1
		 *Step Name: Step 1: Change permission for page in Permalink page
		 *Step Description: 
			- Go to wiki page
			- Choose a page
			- Select More 
			-> choose Permalink
			- Click Manage Permission 
			- Choose permission ( user/group/membership) which want to add
			- Click Add and Save button
		 *Input Data: 

		 *Expected Outcome: 
			- Permission is updated and The user can view or not the page edited*/ 
		info("Add new wiki page");
		hp.goToWiki();
		wHome.goToAddBlankPage();
		wikiMg.addWikiPageSimpleWithSourceEditor(title,title);
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",title),2000,0);
		
		String perLink = wikiMg.permalinkAPage();
		click(wHome.ELEMENT_PERMALINK_MANAGEPERM);
		info("Un check view permission of any group");
		wikiMg.unCheckViewAUserOfPage(wHome.ELEMENT_PERMISSION_VIEW_ANY);
		
		
		magAc.signOut();
		magAc.signIn(DATA_USER2, DATA_PASS);
		driver.get(perLink);
		waitForAndGetElement(wHome.ELEMENT_WIKI_HOME_PAGENOTFOUND);
		
		info("Delete page");
	    magAc.signOut();
	    magAc.signIn(DATA_USER1, DATA_PASS);
	    hp.goToWiki();
	    wHome.deleteWiki(title);
	}

	/**
	 *<li> Case ID:122858.</li>
	 *<li> Test Case Name: Move page in the cases 2 different spaces.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test12_MovePageInTheCases2DifferentSpaces() {
		info("Test 12 Move page in the cases 2 different spaces");
		String space1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String space2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		String wiki1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String wiki2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		
		/*Step Number: 1
		 *Step Name: Step 1: Add new space
		 *Step Description: 
			- Login portal
			- Click join a space
			- Add new space for "Space 1" and "Space 2"
		 *Input Data: 

		 *Expected Outcome: 
			New spaceis added successfully*/
		
		/*Step number: 2
		 *Step Name: Step 2: Add new page for wiki on space 1 and space 2
		 *Step Description: 
			- Go to Space 1/ wiki and Space2/wiki
			- Add new page for wiki with name "Page1" and "Page2"
		 *Input Data: 

		 *Expected Outcome: 
			- Add new page successfully*/
		
		info("Create space 1 and wiki page 1");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space1,space1);
		
		info("Add new wiki page 1 for space 1");
		spaHome.goToWikiTab();
		wHome.goToAddBlankPage();
		wikiMg.addWikiPageSimpleWithSourceEditor(wiki1,wiki1);
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",wiki1),2000,0);
		
		info("Create space 2 and wiki page 2");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space2,space2);
		
		info("Add new wiki page 2 for space 2");
		spaHome.goToWikiTab();
		wHome.goToAddBlankPage();
		wikiMg.addWikiPageSimpleWithRichText(wiki2,wiki2);
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",wiki2),2000,0);
		
		/*Step number: 3
		 *Step Name: Step 3: Open Page1 of space 1
		 *Step Description: 
			- Open "Page 1"
		 *Input Data: 

		 *Expected Outcome: 
			- Page 1 is displayed in the wiki*/
		info("Open wiki page 1");
		spaHome.goToSpace(space1);
		spaHome.goToWikiTab();
		
		
		/*Step number: 4
		 *Step Name: Step 4: Go to Move Page
		 *Step Description: 
			- Open "More" Menu 
			-> "Move Page"
		 *Input Data: 

		 *Expected Outcome: 
			- Move Page form appear*/
		
		

		/*click(wHome.ELEMENT_MORE_LINK);
		click(wHome.ELEMENT_MOVE_LINK);*/
		
		/*Step number: 5
		 *Step Name: Step 5: Check when add relations from 2 different spaces
		 *Step Description: 
			- Open Destination component
			- Select "Space 2"
			- Select "Page 2"
			- Click on "Move" button
		 *Input Data: 

		 *Expected Outcome: 
			- The list of space switcher options is displayed
			- Wiki tree of "Space 2" is displayed on the container below the space switcher
			- Popup is closed
			- "Page 1" is moved to "Page 2"*/
		info("Move page 1 to page 2");
		wikiMg.movePageDiffDestination(wiki1,wiki2,space2);
		
		/*click(wHome.ELEMENT_MOVE_SPACESWITCHER);
		click((wHome.ELEMENT_MOVE_SPACESWITCHER_OTHERSPACE).replace("${name}", name2));
		click(wHome.ELEMENT_MOVE_BTNMOVE);*/
		
		/*Step number: 6
		 *Step Name: Step 6: Check after move page
		 *Step Description: 
			- Go to wiki/Page 2 of space 2
			- Go to wiki/Page 1 of space 1
		 *Input Data: 

		 *Expected Outcome: 
			- In wiki page of space 2 have two pages: Page 1 and Page 2
			- In wiki page of space 1 don't have Page 1*/ 
		
		info("Open wiki page 1");
		spaHome.goToSpace(space1);
		spaHome.goToWikiTab();
		waitForElementNotPresent((wHome.ELEMENT_WIKI_PAGE_LEFTBOX).replace("${title}",wiki1));
		
		info("Open wiki page 2");
		spaHome.goToSpace(space2);
		spaHome.goToWikiTab();
		wHome.goToAPage(wiki2);
		waitForAndGetElement((wHome.ELEMENT_WIKI_PAGE_LEFTBOX).replace("${title}", wiki1));
		
        
		/*info("Delete data test");
		hp.goToMySpaces();
		spaMg.deleteSpace(space1,false);
		spaMg.deleteSpace(space2,false);*/
	}

	/**
	 *<li> Case ID:122859.</li>
	 *<li> Test Case Name: Move wiki's page of space to portal.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test13_MoveWikisPageOfSpaceToPortal() {
		info("Test 13 Move wiki's page of space to portal");
        String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		String wiki1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String wiki2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		/*Step Number: 1
		 *Step Name: Step 1: Add new space
		 *Step Description: 
			- Login portal
			- Click join a space
			- Add new space for "Space 1" 
		 *Input Data: 

		 *Expected Outcome: 
			New spaceis added successfully*/
		/*Step number: 2
		 *Step Name: Step 2: Add new page for wiki on space 1 
		 *Step Description: 
			- Go to Space 1/ wiki 
			- Add new page for wiki with name "Page1" 
		 *Input Data: 

		 *Expected Outcome: 
			- Add new page successfully*/

		info("Create space 1 and wiki page 1");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,space);
		
		info("Add new wiki page 1 for space 1");
		spaHome.goToWikiTab();
		wHome.goToAddBlankPage();
		wikiMg.addWikiPageSimpleWithSourceEditor(wiki1,wiki1);
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",wiki1),2000,0);
		
		hp.goToWiki();
		wHome.goToAddBlankPage();
		wikiMg.addWikiPageSimpleWithSourceEditor(wiki2,wiki2);
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",wiki2),2000,0);
		
		/*Step number: 3
		 *Step Name: Step 3: Open Page1
		 *Step Description: 
			- Open "Page 1"
		 *Input Data: 

		 *Expected Outcome: 
			- Page 1 is displayed in the wiki*/

		info("Open wiki page 1");
		spaHome.goToSpace(space);
		spaHome.goToWikiTab();
		
		/*Step number: 4
		 *Step Name: Step 4: Go to Move page
		 *Step Description: 
			- Open "More" Menu 
			-> "Move Page"
		 *Input Data: 

		 *Expected Outcome: 
			- Move Page form appear*/

		/*Step number: 5
		 *Step Name: Step 5: Check when move wiki's page of space to wiki's page of portal
		 *Step Description: 
			- Open Space switcher component
			- Select "intranet"
			- Click on "Move Page" button
		 *Input Data: 

		 *Expected Outcome: 
			- The list of space switcher options is displayed
			- Wiki's page of space is moved to wiki's page of portal*/ 

		info("Move page 1 to page 2");
		wikiMg.movePageDiffDestination(wiki1,wiki2,"Intranet");
		
		info("Open wiki page 1");
		spaHome.goToSpace(space);
		spaHome.goToWikiTab();
		waitForElementNotPresent((wHome.ELEMENT_WIKI_PAGE_LEFTBOX).replace("${title}",wiki1));
		
		info("Open wiki page 2");
		hp.goToWiki();
		wHome.goToAPage(wiki2);
		waitForAndGetElement((wHome.ELEMENT_WIKI_PAGE_LEFTBOX).replace("${title}", wiki1));
		
        
		/*info("Delete data test");
		hp.goToMySpaces();
		spaMg.deleteSpace(space,false);*/
		
	}

	/**
	 *<li> Case ID:122860.</li>
	 *<li> Test Case Name: Move page in same space.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test14_MovePageInSameSpace() {
		info("Test 14 Move page in same space");
		
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
			
		String wiki1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String wiki2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		/*Step Number: 1
		 *Step Name: Step 1: Add new space
		 *Step Description: 
			- Login portal
			- Click join a space
			- Add new space for "Space 1"
		 *Input Data: 

		 *Expected Outcome: 
			New spaceis added successfully*/
		/*Step number: 2
		 *Step Name: Step 2: Add new page for wiki on space 1
		 *Step Description: 
			- Go to Space 1/ wiki
			- Add new page for wiki with name "Page1" and "Page2"
		 *Input Data: 

		 *Expected Outcome: 
			- Add new page successfully*/

		info("Create space 1 and wiki page ");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,space);
		
		info("Add new wiki page 1 for space 1");
		spaHome.goToWikiTab();
		wHome.goToAddBlankPage();
		wikiMg.addWikiPageSimpleWithSourceEditor(wiki1,wiki1);
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",wiki1),2000,0);
		
		info("Add new wiki page 2 for space ");
		wHome.goToHomeWikiPage();
		wHome.goToAddBlankPage();
		wikiMg.addWikiPageSimpleWithSourceEditor(wiki2,wiki2);
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",wiki2),2000,0);
		
		/*Step number: 3
		 *Step Name: Step 3: Open Page1
		 *Step Description: 
			- Open "Page 1"
		 *Input Data: 

		 *Expected Outcome: 
			- Page 1 is displayed in the wiki*/
		
		info("Open wiki page 1");
		spaHome.goToSpace(space);
		spaHome.goToWikiTab();
		
		/*Step number: 4
		 *Step Name: Step 4: Go to Move Page
		 *Step Description: 
			- Open "More" Menu 
			-> "Move Page"
		 *Input Data: 

		 *Expected Outcome: 
			- Move Page form appear*/

				
		/*Step number: 5
		 *Step Name: Step 5: Move Page
		 *Step Description: 
			- Open Space switcher component
			- Select "Space 1"
			- Select "Page 2"
			- Click on "Move Page" button
		 *Input Data: 

		 *Expected Outcome: 
			- The popup to select a related page is displayed
			- The list of space switcher options is displayed
			- Wiki tree of "Space 2" is displayed on the container below the space switcher
			- Popup is closed
			- "Page 1" is moved to "Page 2" in Space 1*/ 

		wikiMg.movePage(wiki1, wiki2);
		/*info("Delete data test");
		hp.goToMySpaces();
		spaMg.deleteSpace(space,false);*/
	}

	/**
	 *<li> Case ID:122861.</li>
	 *<li> Test Case Name: Move wiki's page of Portal to Space.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test15_MoveWikisPageOfPortalToSpace() {
		info("Test 15 Move wiki's page of Portal to Space");
		
        String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		String wiki1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String wiki2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		/*Step Number: 1
		 *Step Name: Step 1: Add new space
		 *Step Description: 
			- Login portal
			- Click join a space
			- Add new space for "Space 1"
		 *Input Data: 

		 *Expected Outcome: 
			New spaceis added successfully*/
		/*Step number: 2
		 *Step Name: Step 2: Add new page for wiki on space 1 and space 2
		 *Step Description: 
			- Go to Space 1/ wiki 
			- Add new page for wiki with name "Page1"
		 *Input Data: 

		 *Expected Outcome: 
			- Add new page successfully*/

		info("Create space 1 and wiki page 1");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,space);
		
		info("Add new wiki page 1 for space 1");
		spaHome.goToWikiTab();
		wHome.goToAddBlankPage();
		wikiMg.addWikiPageSimpleWithSourceEditor(wiki1,wiki1);
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",wiki1),2000,0);
		
		hp.goToWiki();
		wHome.goToAddBlankPage();
		wikiMg.addWikiPageSimpleWithSourceEditor(wiki2,wiki2);
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",wiki2),2000,0);
		
		/*Step number: 3
		 *Step Name: Step 3: Open Wiki page of portal
		 *Step Description: 
			- Click Wiki link on CompanyNavigations form
		 *Input Data: 

		 *Expected Outcome: 
			- Wiki home appear*/		
		/*Step number: 4
		 *Step Name: Step 4: Add new page for wiki page of portal
		 *Step Description: 
			- Add new page for wiki of portal with name is Page2
		 *Input Data: 

		 *Expected Outcome: 
			- Add new page successfully*/
		
		/*Step number: 5
		 *Step Name: Step 5: Go to Move Page
		 *Step Description: 
			- Open "More" Menu 
			-> "Move Page"
		 *Input Data: 

		 *Expected Outcome: 
			- Move Page form appear*/

		info("Open Wiki page of portal");
		hp.goToWiki();
		wikiMg.movePageDiffDestination(wiki2,wiki1,space);
		
		/*Step number: 6
		 *Step Name: Step 6: Check when move wiki's page of Portal to Space
		 *Step Description: 
			- Open Space switcher component
			- Select "Space 1" and choose "Page 1"
			- Click on "Move Page" button
		 *Input Data: 

		 *Expected Outcome: 
			- The list of space switcher options is displayed
			- Wiki's page of portal is moved to wiki's page of space*/ 

		info("Open wiki page 1");
		spaHome.goToSpace(space);
		spaHome.goToWikiTab();
		wHome.goToAPage(wiki1);
		waitForAndGetElement((wHome.ELEMENT_WIKI_PAGE_LEFTBOX).replace("${title}",wiki2));
		
		hp.goToWiki();
		waitForElementNotPresent((wHome.ELEMENT_WIKI_PAGE_LEFTBOX).replace("${title}",wiki1));
		
		/*info("Delete data test");
		hp.goToMySpaces();
		spaMg.deleteSpace(space,false);*/
	}

	/**
	 *<li> Case ID:122862.</li>
	 *<li> Test Case Name: Move page has the same name with page in target space.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test16_MovePageHasTheSameNameWithPageInTargetSpace() {
		info("Test 16 Move page has the same name with page in target space");
		
        String space1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
        String space2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		String wiki1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String wiki2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String newTitle = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		String mess = wMessage.getWikiMessage(5);
		info("mess:"+mess);
		/*Step Number: 1
		 *Step Name: Step 1: Add new space
		 *Step Description: 
			- Login portal
			- Click join a space
			- Add new space for "Space 1" and "Space 2"
		 *Input Data: 

		 *Expected Outcome: 
			New spaceis added successfully*/
		/*Step number: 2
		 *Step Name: Step 2: Add new page for wiki on space 1 and space 2
		 *Step Description: 
			- Go to Space 1/ wiki and Space2/wiki
			- Add new page for wiki of 2 space have same name is "Page 1"
		 *Input Data: 

		 *Expected Outcome: 
			- Add new page successfully*/

		info("Create space 1 and wiki page 1");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space1,space1);
		
		info("Add new wiki page 1 for space 1");
		spaHome.goToWikiTab();
		wHome.goToAddBlankPage();
		wikiMg.addWikiPageSimpleWithSourceEditor(wiki1,wiki1);
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",wiki1),2000,0);
		
		info("Create space 2 and wiki page 2");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space2,space2);
		
		info("Add new wiki page 2 for space 2");
		spaHome.goToWikiTab();
		wHome.goToAddBlankPage();
		wikiMg.addWikiPageSimpleWithSourceEditor(wiki2,wiki2);
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",wiki2),2000,0);
		
		info("Add new wiki page 1 for space 2");
		wHome.goToHomeWikiPage();
		wHome.goToAddBlankPage();
		wikiMg.addWikiPageSimpleWithSourceEditor(wiki1,wiki1);
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",wiki1),2000,0);
		
		
		/*Step number: 3
		 *Step Name: Step 3: Open Page1 of space 1
		 *Step Description: 
			- Open "Page 1" of "Space 1"
		 *Input Data: 

		 *Expected Outcome: 
			- Page 1 is displayed in the wiki*/

		info("Open wiki page 1");
		spaHome.goToSpace(space1);
		spaHome.goToWikiTab();
		
		/*Step number: 4
		 *Step Name: Step 4: Go to Move Page
		 *Step Description: 
			- Open "More" Menu 
			-> "Move Page"
		 *Input Data: 

		 *Expected Outcome: 
			- The list of space switcher options is displayed
			- A tooltip is displayed alert "Another page with the same title already exists in the selected space. "*/

		info("Move page 1 to page 2");
		wikiMg.movePageDiffDestination(wiki1,wiki2,space2);
		waitForAndGetElement(wHome.ELEMENT_MOVE_PAGE_POPUP_ALERT_MESSAGE_SAME_NAME.replace("${message}",mess),2000,0);
		
		
		/*Step number: 5
		 *Step Name: Step 5: Rename page
		 *Step Description: 
			Click on [Rename] link, the user is redirected to the page to move or the sub
			-page of the parent to move in the edition mode, he is able to change the name and save.
		 *Input Data: 

		 *Expected Outcome: 
			Page is changed namesuccessfully*/ 
		wikiMg.renameAfterPageHasSameName(newTitle,"");
		waitForAndGetElement((wHome.ELEMENT_WIKI_PAGE_LEFTBOX).replace("${title}",newTitle));
		
		/*info("Delete data test");
		hp.goToMySpaces();
		spaMg.deleteSpace(space1,false);
		spaMg.deleteSpace(space2,false);*/
	}

	/**
	 *<li> Case ID:122863.</li>
	 *<li> Test Case Name: Move page when user has no edit permission at destination page.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 *check issue WIKI-725 (bug -> the user mary can move in the page without edit permission)
	 */
	@Test(groups="pending")
	public  void test02_MovePageWhenUserHasNoEditPermissionAtDestinationPage() {
		info("Test 02: Move page when user has no edit permission at destination page");
		
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		String wiki1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String wiki2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String mess = wMessage.getWikiMessage(6);
		info("mess:"+mess);
		/*Step Number: 1
		 *Step Name: Step 1: Add new space
		 *Step Description: 
			- Login by admin
			- Click join a space
			- Add new space for "Space 1"
		 *Input Data: 

		 *Expected Outcome: 
			New spaceis added successfully*/		
		/*Step number: 2
		 *Step Name: Step 2: Add new page for wiki on space
		 *Step Description: 
			- Go to Space 1 wiki 
			- Add new page for wiki with name "Page1" , Page2
		 *Input Data: 

		 *Expected Outcome: 
			- Add new page successfully*/
		
		/*Step number: 3
		 *Step Name: Step 3: Open Page1 of space 1
		 *Step Description: 
			- Open "Page 1", set permission user2 can't edit
		 *Input Data: 

		 *Expected Outcome: 
			- Page 1 is displayed in the wiki 
			- User2 cannot edit the Page1*/

		info("Create space 1 and wiki page 1");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,space);
		spaHome.goToSettingTab();
		setSpaceMg.inviteUser("mary");
		
		
		info("Add new wiki page 1 for space 1");
		spaHome.goToWikiTab();
		wHome.goToAddBlankPage();
		wikiMg.addWikiPageSimpleWithSourceEditor(wiki1,wiki1);
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",wiki1),2000,0);
		wikiMg.addAUserToPermission("mary","");
		
		info("Add new wiki page 2 for space 2");
		wHome.goToHomeWikiPage();
		wHome.goToAddBlankPage();
		wikiMg.addWikiPageSimpleWithSourceEditor(wiki2,wiki2);
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",wiki2),2000,0);
		
		wikiMg.addAUserToPermission("mary",wHome.ELEMENT_PERMISSION_EDIT_USER.replace("${user}", "mary"));
		
		/*Step number: 4
		 *Step Name: Step 4: Go to Move Page
		 *Step Description: 
			-Login by user2
			- Open Wiki of Space 1
			- Select Page 2
			- Open "More" Menu 
			-> "Move Page"
		 *Input Data: 

		 *Expected Outcome: 
			- Move Page form appear*/

		magAc.signOut();
		magAc.signIn(DATA_USER2, DATA_PASS);
		hp.goToMySpaces();
		spaMg.acceptAInvitation(space);
		spaHome.goToWikiTab();
		wikiMg.movePage(wiki1, wiki2);
		
		
		/*Step number: 5
		 *Step Name: Step 5: Check when add relations from 2 different spaces
		 *Step Description: 
			- Open Destination component
			- Select "Page1"
			- Click on "Move" button
		 *Input Data: 

		 *Expected Outcome: 
			- The list of space switcher options is displayed
			- Wiki tree of "intranet" is displayed on the container below the space switcher
			- Popup is closed
			- Show message alert "You have no edit permission at destination page"*/ 
		
	}

	/**
	 *<li> Case ID:122874.</li>
	 *<li> Test Case Name: Watch a wiki page of space.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 * check issue WIKI-976 (wrong link in the mail)
	 */
	@Test
	public  void test18_WatchAWikiPageOfSpace() {
		info("Test 18 Watch a wiki page of space");
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String wiki1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String wiki2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
        String mess = wMessage.getWikiMessage(3);
        info("mess:"+mess);

		
		/*Step Number: 1
		 *Step Name: Step 1: Watch Page
		 *Step Description: 
			- Go to Wiki application from a space
			- Open an existing page by clicking on page name in navigation tree.
			- Click on More icon on toolbar and select Watch action in menu
		 *Input Data: 

		 *Expected Outcome: 
			Show message :"You started watching this page now." Selected page is watched.*/

        info("Create space 1 and wiki page 1");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,space);
		spaHome.goToSettingTab();
		setSpaceMg.inviteUser("mary");
		
		info("Add new wiki page 1 for space 1");
		spaHome.goToWikiTab();
		wHome.goToAddBlankPage();
		wikiMg.addWikiPageSimpleWithSourceEditor(wiki1,wiki1);
		
		magAc.signOut();
		magAc.signIn(DATA_USER2, DATA_PASS);
		hp.goToMySpaces();
		spaMg.acceptAInvitation(space);
		spaHome.goToWikiTab();
		wHome.goToAPage(wiki1);
		wikiMg.watchAPage(mess);
		
		/*Step number: 2
		 *Step Name: Step 2: Edit page
		 *Step Description: 
			- Login as other user
			- Choose the watched wiki page at step 1 and click Edit Page
			- Change page content
			- Check the check box "Publish Activity" and click Save
		 *Input Data: 

		 *Expected Outcome: 
			- Page is saved successfully
			- An email notification will send to email of watcher about changed content*/

		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		spaHome.goToSpace(space);
		spaHome.goToWikiTab();
		wHome.goToAPage(wiki1);
		wikiMg.editWikiPageSimpleWithSourceEditor("", wiki2);
		wikiMg.publishPage();
		wikiMg.saveAddPage();
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",wiki1),2000,0);
		
		info("Check email notification and link's format of the page");
		goToMail("fqaexovn@gmail.com", "exoadmin");
		wikiMg.checkEmailNotification(wiki1);
		
		/*Step number: 3
		 *Step Name: Step 3: Open the Wiki page via email notification
		 *Step Description: 
			- Open the email notification at step 2
			- Click on the wiki page link in the email
		 *Input Data: 

		 *Expected Outcome: 
			- The wiki page is opened with the link format: http://IP_Server_Address:8080/portal/g/:spaces:[group_site]/[space_Name]/wiki/[wiki_page_name](For example: http://localhost:8080/portal/g/:spaces:space_1/space_1/wiki/Page_1)
			- The browser navigates to the corresponding wiki page in space wiki application*/ 

		
        
        /*info("Delete data test");
		hp.goToMySpaces();
		spaMg.deleteSpace(space,false);*/
	}
	
}
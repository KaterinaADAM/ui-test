package org.exoplatform.selenium.platform.plf.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.calendar.CalendarHomePage.selectDayOption;
import org.exoplatform.selenium.platform.calendar.CalendarHomePage.selectViewOption;
import org.openqa.selenium.By;
import org.testng.annotations.*;


	/**
	* @author eXo
	*
	*/
	public class Plf_Navigation_TopNavigation extends Plf_TestConfig{
		
		@AfterMethod
		public void setAfterMethod(){
			magAc.signOut();
			magAc.signIn(DATA_USER1, DATA_PASS);
		}

	/**
	*<li> Case ID:120889.</li>
	*<li> Test Case Name: Create a new Wiki page via the top navigation.</li>
	*/
	@Test
	public  void test01_CreateANewWikiPageViaTheTopNavigation() {
		info("Test 1: Create a new Wiki page via the top navigation");
		/*Step Number: 1
		*Step Name: Connect to intranet
		*Step Description: 
			- Login as normal user
			- Connect to Intranet
		*Input Data: 
			
		*Expected Outcome: 
			- The Top Navigation bar is displayed*/

		/*Step number: 2
		*Step Name: Open wiki application
		*Step Description: 
			- Click on the button "Create" (+)
			- Select "Wiki Page"
			- Choose a location to add a new wiki page from the space switcher
			- Click on the button "Next"
		*Input Data: 
			
		*Expected Outcome: 
			- The menu is updated to "Create a new Wiki Page:"
			- The wiki application is opened with the New Page editor opened*/ 
		info("Go to Create Wiki page from Navigation toolbar");
		navToolBar.goToCreateWikiPage("");
		info("Verify that wiki home page is shown");
		waitForAndGetElement(wikiHome.ELEMENT_WIKI_HOME_PAGE_TEXT);	

 	}

	/**
	*<li> Case ID:120890.</li>
	*<li> Test Case Name: Create a new Poll via the top navigation.</li>
	*<li> Pre-Condition: A forum exists in Intranet</li>
	*/
	@Test
	public  void test02_CreateANewPollViaTheTopNavigation() {
		info("Test 2: Create a new Poll via the top navigation");
		String num = getRandomNumber();
		String category = txData.getContentByArrayTypeRandom(1)+num ;
		String forum = txData.getContentByArrayTypeRandom(1)+num ;
		
		hp.goToForum();
		info("Verify that the forum home page is shown full");
		waitForAndGetElement(forumHP.ELEMENT_FORUM_WHAT_GOING_ON,3000,0);
		
		info("Add a category");
		forumCatMag.addCategorySimple(category,"",category);
		
		info("Add a forum in the category1");
		forumMg.addForumSimple(forum,"", forum);
		

		/*Step Number: 1
		*Step Name: Connect to intranet
		*Step Description: 
			- Login as normal user
			- Connect to Intranet
		*Input Data: 
			- The Top Navigation bar is displayed*/
		magAc.signOut();
		magAc.signIn(DATA_USER3, DATA_PASS);
		navToolBar.goToAddPoll("",forum);
		info("Verify that the poll popup is shown");
		waitForAndGetElement(foTopic.ELEMENT_POLL_SUBMIT,3000,0);
		info("Delete data");
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		hp.goToForum();
		forumCatMag.deleteCategory(category);
		/*Step number: 2
		*Step Name: Open poll application
		*Step Description: 
			- Click on the button "Create" (+)
			- Select "Poll"
			- Click on the button "Next"
		*Input Data: 
			
		*Expected Outcome: 
			- Form to create new Poll is displayed
			- A new Post editor is opened ready to create a new poll*/ 

 	}

	/**
	*<li> Case ID:120891.</li>
	*<li> Test Case Name: Create a new Topic via the top navigation.</li>
	*<li> Pre-Condition: 1 forum already exist on Intranet</li>
	*/
	@Test
	public  void test03_CreateANewTopicViaTheTopNavigation() {
		info("Test 3: Create a new Topic via the top navigation");
		String num = getRandomNumber();
		String category = txData.getContentByArrayTypeRandom(1)+num ;
		String forum = txData.getContentByArrayTypeRandom(1)+num ;
		
		hp.goToForum();
		info("Verify that the forum home page is shown full");
		waitForAndGetElement(forumHP.ELEMENT_FORUM_WHAT_GOING_ON,3000,0);
		
		info("Add a category");
		forumCatMag.addCategorySimple(category,"",category);
		
		info("Add a forum in the category1");
		forumMg.addForumSimple(forum,"", forum);
		/*Step Number: 1
		*Step Name: - Connect to intranet
		*Step Description: 
			- Login as a normal user
			- Connect to Intranet
		*Input Data: 
			
		*Expected Outcome: 
			- The Top Navigation bar is displayed*/
		magAc.signOut();
		magAc.signIn(DATA_USER3, DATA_PASS);
		
		/*Step number: 2
		*Step Name: - Open Topic application
		*Step Description: 
			- Click on the button "Create" (+)
			- Select "Topic"
			- Click on the button "Next"
		*Input Data: 
			
		*Expected Outcome: 
			- Form to create new Topic is displayed
			- A new Post editor is opened*/ 
		navToolBar.goToAddTopic("",forum);
		info("Verify that the topic is shown");
		waitForAndGetElement(forumMg.ELEMENT_START_TOPIC_POPUP_TITLE,3000,0);
		info("Delete data");
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		hp.goToForum();
		forumCatMag.deleteCategory(category);
		
 	}

	/**
	*<li> Case ID:120892.</li>
	*<li> Test Case Name: Create a new Event via the top navigation.</li>
	*/
	@Test
	public  void test04_CreateANewEventViaTheTopNavigation() {
		info("Test 4: Create a new Event via the top navigation");
		String name = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: - Connect to intranet
		*Step Description: 
			- Login as a normal user
			- Connect to Intranet
		*Input Data: 
			
		*Expected Outcome: 
			- The Top Navigation bar is displayed*/
		navToolBar.goToAddEventTask("event", name, "", "", "");
		
		/*Step number: 2
		*Step Name: - Open form "Add new" to input data
		*Step Description: 
			- Click on the button "Create" (+)
			- Select the item "Event/Task"
		*Input Data: 
			
		*Expected Outcome: 
			- Form to create Event/Task is displayed*/

		/*Step number: 3
		*Step Name: - Create an event
		*Step Description: 
			- Select "event" from "Add new"
			- Input available data of date and times
			- Select the calendar
			- Click on the button "Next"
			- Wait 2 second
		*Input Data: 
			
		*Expected Outcome: 
			- A message is displayed: "The event was added to $calendar_name"
			- The message disappears with a fadeout effect*/ 
		hp.goToCalendarPage();
		calHome.verifyIsPresentEventTask(name,selectViewOption.WEEK,selectDayOption.ONEDAY);

 	}

	/**
	*<li> Case ID:120893.</li>
	*<li> Test Case Name: Create a new Task via the top navigation.</li>
	*/
	@Test
	public  void test05_CreateANewTaskViaTheTopNavigation() {
		info("Test 5: Create a new Task via the top navigation");
		String name = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: - Connect to intranet
		*Step Description: 
			- Login as a normal user
			- Connect to Intranet
		*Input Data: 
			
		*Expected Outcome: 
			- The Top Navigation bar is displayed*/
		navToolBar.goToAddEventTask("task", name, "", "", "");
		
		/*Step number: 2
		*Step Name: Open form "Add new Task"
		*Step Description: 
			- Click on the button "Create" (+)
			- Select the item "Event/Task"
		*Input Data: 
			
		*Expected Outcome: 
			- Form to create new Event/Task is displayed*/

		/*Step number: 3
		*Step Name: Create a task
		*Step Description: 
			- Select "Task" from "Add new"
			- Input available data of date and times
			- Select the calendar
			- Click on the button "Next"
		*Input Data: 
			
		*Expected Outcome: 
			- A message is displayed: "The task was added to $calendar_name"
			- The message disappears with a fadeout effect*/ 
		hp.goToCalendarPage();
		calHome.verifyIsPresentEventTask(name,selectViewOption.WEEK,selectDayOption.ONEDAY);

 	}

	/**
	*<li> Case ID:120894.</li>
	*<li> Test Case Name: Upload a new file via the top navigation.</li>
	*/
	@Test
	public  void test06_UploadANewFileViaTheTopNavigation() {
		info("Test 6: Upload a new file via the top navigation");
		// -------------------------------------------------- voir SITE EXPLORER HOME LINE 854 ---------------------------------------------------------
		/*Step Number: 1
		*Step Name: Connect to intranet
		*Step Description: 
			- Login as normal user
			- Connect to Intranet
		*Input Data: 
			
		*Expected Outcome: 
			The Top navigation bar is displayed*/

		/*Step number: 2
		*Step Name: Open Document selector popup
		*Step Description: 
			- Click Create "+", choose the option "Upload a file"
			- Browse to file to upload
		*Input Data: 
			
		*Expected Outcome: 
			- The "Select File" pop up is displayed
			- File is uploaded successfully*/ 
		info("upload the file");
		String filePath = fData.getAttachFileByArrayTypeRandom(11);
		navToolBar.goToUploadFile("","","TestData/"+filePath);
		waitForAndGetElement(By.xpath(navToolBar.ELEMENT_CHECK_NAME_UPLOADED_FILE.replace("{$name}",filePath)));
		
 	}

	/**
	*<li> Case ID:120895.</li>
	*<li> Test Case Name: Open user guide.</li>
	*/
	@Test
	public  void test07_OpenUserGuide() {
		info("Test 7: Open user guide");
		/*Step Number: 1
		*Step Name: Connect to intranet
		*Step Description: 
			- Login as normal user
			- Connect to Intranet
		*Input Data: 
			
		*Expected Outcome: 
			- The Homepage is displayed 
			- The Help button is displayed in the right of the bar*/

		/*Step number: 2
		*Step Name: Open user guide
		*Step Description: 
			- Click on the button "?"
		*Input Data: 
			
		*Expected Outcome: 
			- A new tab in the internet browser is opened
			- The user guide is opened and the chapter displayed matched with the current navigation of the user.*/ 
		hp.goToHomePage();
		click(navToolBar.ELEMENT_HELP_TOOLBAR);
		Utils.pause(1000);
		switchToNewWindow();
		String actualUrl=driver.getCurrentUrl();
		String url="PLFUserGuide.GettingStarted.SocialIntranetHomepage.html";
		assert actualUrl.contains(url);
		switchToParentWindow();
 	}
}
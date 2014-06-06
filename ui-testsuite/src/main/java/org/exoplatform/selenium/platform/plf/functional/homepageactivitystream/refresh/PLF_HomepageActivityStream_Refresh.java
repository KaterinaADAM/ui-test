package org.exoplatform.selenium.platform.plf.functional.homepageactivitystream.refresh;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.HomePageActivity;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.ManageApplications;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.ManageAccount.userType;
import org.exoplatform.selenium.platform.forum.ForumManageCategory;
import org.exoplatform.selenium.platform.forum.ForumManageTopic;
import org.exoplatform.selenium.platform.social.Activity;
import org.exoplatform.selenium.platform.social.PeopleConnection;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;


/**
 * @author phuongdt
 *
 */
public class PLF_HomepageActivityStream_Refresh extends Activity{
	ManageAccount acc;
	HomePageActivity hpActivity; 
	NavigationToolbar navToolBar; 
	PeopleConnection peoConn;
	ManageApplications app;
	ForumManageTopic mngTopic;
	ForumManageCategory mngCat;

	@BeforeMethod
	public void setUpBeforeTest(){
		initSeleniumTest();
		driver.get(baseUrl);
		acc = new ManageAccount(driver);
		hpActivity = new HomePageActivity(driver);
		navToolBar = new NavigationToolbar(driver);
		peoConn = new PeopleConnection(driver);
		app = new ManageApplications(driver);
		mngTopic = new ForumManageTopic(driver);
		mngCat = new ForumManageCategory(driver);
		acc.signIn("john", DATA_PASS);
	}

	@AfterMethod
	public void afterTest(){
		driver.manage().deleteAllCookies();		
		driver.quit();
	}

	public void addCommentNewDriver(String activityText, String contentOfComment){
		//Add a new comment following an activity 
		HomePageActivity newhpActivity = new HomePageActivity(newDriver);
		waitForAndGetElement(newhpActivity.ELEMENT_ICON_COMMENT.replace("${title}", activityText),DEFAULT_TIMEOUT,1,0,newDriver).click();

		WebElement commentText = waitForAndGetElement(newhpActivity.ELEMENT_COMMENTBOX.replace("${title}", activityText),DEFAULT_TIMEOUT,1,2,newDriver);
		WebElement commentButton = waitForAndGetElement(ELEMENT_COMMENT_BUTTON.replace("${activityText}", activityText),DEFAULT_TIMEOUT,1,2,newDriver);
		WebElement workingLabel = waitForAndGetElement(ELEMENT_ACTIVITY_ADD_YOUR_COMMENTLABEL.replace("${activityText}", activityText),DEFAULT_TIMEOUT,1,2,newDriver);

		((JavascriptExecutor)newDriver).executeScript("arguments[0].textContent = '';", workingLabel);
		((JavascriptExecutor)newDriver).executeScript("arguments[0].textContent = '"+contentOfComment+"';", commentText);
		((JavascriptExecutor)newDriver).executeScript("arguments[0].disabled = false;", commentButton);
		((JavascriptExecutor)newDriver).executeScript("arguments[0].className = 'btn pull-right btn-primary';", commentButton);
		waitForAndGetElement(ELEMENT_COMMENT_BUTTON.replace("${activityText}", activityText),DEFAULT_TIMEOUT,1,2,newDriver).click();
		waitForAndGetElement(ELEMENT_DELETE_COMMENT_BUTTON.replace("${activityText}", activityText).replace("${commentText}", contentOfComment), DEFAULT_TIMEOUT,1,2,newDriver);

	}
	
	/**
	 * Case ID:108208.
	 * Test Case Name: Check refresh function when user comments on his activity.
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by phuongdt at 2014/02/17 08:57:51
	 */
	@Test
	public  void test01_CheckRefreshFunctionWhenUserCommentsOnHisActivity() {
		String act1 = "Activity 1082081";
		String act2 = "Activity 1082082";
		String act3 = "Activity 1082083";
		String act4 = "Activity 1082084";
		String comment01 = "Comment 1082081";

		info("Test 1: Check refresh function when user comments on his activity");
		/*Step Number: 1
		 *Step Name: Step 1: Connect to Intranet
		 *Step Description: 
			- Connect to Intranet
			- Open the stream "All activities"
		 *Input Data: 

		 *Expected Outcome: 
			Activity of the stream "All activities" are displayed*/

		/*Step number: 2
		 *Step Name: Step 2: Add some activities
		 *Step Description: 
			- Add some activities
			- Click [Share]
		 *Input Data: 

		 *Expected Outcome: 
			- Some activities are added on the stream*/
		addActivity(true, act1, false, "");
		addActivity(true, act2, false, "");
		addActivity(true, act3, false, "");
		addActivity(true, act4, false, "");
		waitForAndGetElement(hpActivity.ELEMENT_CONTENT_ACTIVITY_INDEX.replace("${index}", "1").replace("${content}", act4));
		waitForAndGetElement(hpActivity.ELEMENT_CONTENT_ACTIVITY_INDEX.replace("${index}", "2").replace("${content}", act3));
		waitForAndGetElement(hpActivity.ELEMENT_CONTENT_ACTIVITY_INDEX.replace("${index}", "3").replace("${content}", act2));
		waitForAndGetElement(hpActivity.ELEMENT_CONTENT_ACTIVITY_INDEX.replace("${index}", "4").replace("${content}", act1));

		/*Step number: 3
		 *Step Name: Step 3: Add comment for activity
		 *Step Description: 
			- Select one of new added activities above (step 2) and add comment for it
		 *Input Data: 

		 *Expected Outcome: 
			Add comment for an activity successfully*/
		addComment(act1, comment01);

		/*Step number: 4
		 *Step Name: Step 4: Check refresh function
		 *Step Description: 
			- Click [Refresh] on activity Stream Status
		 *Input Data: 

		 *Expected Outcome: 
			- The activity which added comment is shown the top of the stream*/ 
		click(app.ELEMENT_GADGET_REFRESH_ICON);
		waitForAndGetElement(hpActivity.ELEMENT_CONTENT_ACTIVITY_INDEX.replace("${index}", "1").replace("${content}", act1));
		waitForAndGetElement(hpActivity.ELEMENT_CONTENT_ACTIVITY_INDEX.replace("${index}", "2").replace("${content}", act4));
		waitForAndGetElement(hpActivity.ELEMENT_CONTENT_ACTIVITY_INDEX.replace("${index}", "3").replace("${content}", act3));
		waitForAndGetElement(hpActivity.ELEMENT_CONTENT_ACTIVITY_INDEX.replace("${index}", "4").replace("${content}", act2));


		//Clear data
		info("clear data");
		hpActivity.deleteActivity(act1);
		hpActivity.deleteActivity(act2);
		hpActivity.deleteActivity(act3);
		hpActivity.deleteActivity(act4);
	}

	/**
	 * Case ID:108209.
	 * Test Case Name: Check refresh function on AS with 2 users have connection.
	 * Pre-Condition: User A connect with user B
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by phuongdt at 2014/02/17 08:57:51
	 */
	@Test
	public  void test02_CheckRefreshFunctionOnASWith2UsersHaveConnection() {
		String act1 = "Activity 1082091";
		String comment01 = "Comment 1082091";
		String user1="Mary Williams";
		String user = "John Smith";

		/*Create data to test*/
		info("Create data to test");
		//Click on Connections on the left panel
		navToolBar.goToConnectionPage();

		//Display list of people
		//Click on Connect button to invite about 2 users
		peoConn.connectPeople(user1);

		//Login by invited users, go to My Connections/Requests Received
		acc.userSignIn(userType.PUBLISHER);
		//An user click on Confirm button
		peoConn.acceptInvitation(user);
		acc.userSignIn(userType.ADMIN);

		info("Test 2: Check refresh function on AS with 2 users have connection");
		/*Step Number: 1
		 *Step Name: Step 1: Connect to Intranet by user A
		 *Step Description: 
			- Connect to Intranet as user A in 1 browser
			- Open the stream "All activities"
		 *Input Data: 

		 *Expected Outcome: 
			Activity of the stream "All activities" are displayed*/

		/*Step number: 2
		 *Step Name: Step 2: Add activity
		 *Step Description: 
			- Add an activity ( ex: add new status, attach file or share link on AS)
			- Click [Share]
		 *Input Data: 

		 *Expected Outcome: 
			- An activity is added on the stream*/
		addActivity(true, act1, false, "");

		/*Step number: 3
		 *Step Name: Step 3: Add comment for activity by user B
		 *Step Description: 
			- Login by user B in another browser
			- Select an activity at step 2 and add comment for it
		 *Input Data: 

		 *Expected Outcome: 
			Add comment for an activity successfully*/
		loginWithAnotherAccOnThesameBrowser("mary", DATA_PASS);
		addCommentNewDriver(act1, comment01);
		
		/*Step number: 4
		 *Step Name: Step 4: Check refresh function
		 *Step Description: 
			Back to browser 1 As user A
			- Click [Refresh] on activity Stream Status
		 *Input Data: 

		 *Expected Outcome: 
			- The activity which added comment is shown the top of the stream*/ 
		click(ELEMENT_REFRESH);
		waitForAndGetElement(hpActivity.ELEMENT_CONTENT_ACTIVITY_INDEX.replace("${index}", "1").replace("${content}", act1));
		
		/*Clear data*/
		info("clear data");
		navToolBar.goToConnectionPage();
		click(peoConn.ELEMENT_MY_CONNECTIONS_TAB);
		peoConn.removeConnection(user1);
		info("--Close the 2nd browser window--");
		Utils.pause(500);
		newDriver.manage().deleteAllCookies();
		newDriver.quit();
	}

	/**
	 * Case ID:108286.
	 * Test Case Name: Check refresh function when do some action on portal with 2 users have connection.
	 * Pre-Condition: User A connect with user B
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by phuongdt at 2014/02/17 08:57:51
	 */
	@Test
	public  void test03_CheckRefreshFunctionWhenDoSomeActionOnPortalWith2UsersHaveConnection() {
		String user1="Mary Williams";
		String user = "John Smith";
		String titleCat = "Category 108286";
		String titleForum = "Forum 108286";
		String titleTop = "Topic 108286";
		String descTop = "line1";
		String comment01 = "Comment 108286";
		
		info("Test 3: Check refresh function when do some action on portal with 2 users have connection");
		/*Create data to test*/
		info("Create data to test");
		//Click on Connections on the left panel
		navToolBar.goToConnectionPage();

		//Display list of people
		//Click on Connect button to invite about 2 users
		peoConn.connectPeople(user1);

		//Login by invited users, go to My Connections/Requests Received
		acc.userSignIn(userType.PUBLISHER);
		//An user click on Confirm button
		peoConn.acceptInvitation(user);
		
		/*Step Number: 1
		 *Step Name: Step 1: Connect to Intranet by user A
		 *Step Description: 
			- Connect to Intranet as user A in 1 browser
			- Open the stream "All activities"
		 *Input Data: 

		 *Expected Outcome: 
			Activity of the stream "All activities" are displayed*/
		acc.userSignIn(userType.ADMIN);
		
		/*Step number: 2
		 *Step Name: Step 2: Add activity
		 *Step Description: 
			- Go to wiki page ( calendar)
			- Add new page for wiki ( or add event/task)
		 *Input Data: 

		 *Expected Outcome: 
			- New page ( event/task) is added successfully*/
		mngTopic.goToForums();;
		mngTopic.addCategoryForumTopic(titleCat, titleForum, titleTop,descTop);
		
		/*Step number: 3
		 *Step Name: Step 3: Check activity of user
		 *Step Description: 
			- Go to homepage
		 *Input Data: 

		 *Expected Outcome: 
			- Activity is displayed*/
		navToolBar.goToHomePage();
		waitForAndGetElement(By.linkText(titleTop));
		
		/*Step number: 4
		 *Step Name: Step 4: Add comment for activity by user B
		 *Step Description: 
			- Login by user B in another browser
			- Select an activity at step 2 ( page of wiki or event/task,forum) on homepage and add comment for it
		 *Input Data: 

		 *Expected Outcome: 
			Add comment for an activity successfully*/
		loginWithAnotherAccOnThesameBrowser("mary", DATA_PASS);
		addCommentNewDriver(titleTop, comment01);
		
		/*Step number: 5
		 *Step Name: Step 5: Check refresh function
		 *Step Description: 
			- Back to browser 1 as user A
			- Click [Refresh] on activity Stream Status
		 *Input Data: 

		 *Expected Outcome: 
			- The activity which added comment is shown the top of the stream*/ 
		click(ELEMENT_REFRESH);
		waitForAndGetElement(hpActivity.ELEMENT_CONTENT_ACTIVITY_INDEX.replace("${index}", "1").replace("${content}", titleTop));
		
		/*Clear data*/
		info("clear data");
		navToolBar.goToConnectionPage();
		click(peoConn.ELEMENT_MY_CONNECTIONS_TAB);
		peoConn.removeConnection(user1);
		mngTopic.goToForums();
		click(By.linkText(titleCat));
		mngCat.deleteCategoryInForum(titleCat, true);	
		info("--Close the 2nd browser window--");
		Utils.pause(500);
		newDriver.manage().deleteAllCookies();
		newDriver.quit();

	}
}
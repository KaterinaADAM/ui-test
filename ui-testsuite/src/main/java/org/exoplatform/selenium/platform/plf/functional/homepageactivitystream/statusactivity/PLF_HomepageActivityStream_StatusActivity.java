package org.exoplatform.selenium.platform.plf.functional.homepageactivitystream.statusactivity;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.HomePageActivity;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ActionBar;
import org.exoplatform.selenium.platform.social.Activity;
import org.exoplatform.selenium.platform.social.ManageMember;
import org.openqa.selenium.By;
import org.testng.annotations.*;

/**
 * @author chinhdtt
 *
 */
public class PLF_HomepageActivityStream_StatusActivity extends Activity{

	ManageAccount acc; 
	HomePageActivity home; 
	NavigationToolbar nav; 
	ActionBar actBar;	
	EcmsBase ecms; 
	ManageMember mMember; 

	@BeforeMethod
	public void setUpBeforeTest(){
		initSeleniumTest();
		driver.get(baseUrl);
		acc = new ManageAccount(driver, this.plfVersion);
		home = new HomePageActivity(driver, this.plfVersion); 
		acc.signIn(DATA_USER1, DATA_PASS);
		nav = new NavigationToolbar(driver, this.plfVersion);	
		actBar = new ActionBar(driver, this.plfVersion);
		ecms = new EcmsBase(driver, this.plfVersion);
		mMember = new ManageMember(driver, this.plfVersion);
	}

	@AfterMethod
	public void afterTest(){
		driver.manage().deleteAllCookies();		
		driver.quit();
	}

	/**
	 * Case ID:77526.
	 * Test Case Name: Add Status activity.
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/01/10 15:55:22
	 */
	@Test
	public  void test01_AddStatusActivity() {
		info("Test 1: Add Status activity");
		String text = "Activity 77526";
		/*
		- Connect to Intranet
		- Input a message in the activity text box
		- Click on Share button
		 *Input Data: 
		 *Expected Outcome: 
		- A Status activity is added to the activity stream		*/ 
		addActivity(true, text, false,"");

		//delete data
		home.deleteActivity(text);		
	}

	/**
	 * Case ID:77529.
	 * Test Case Name: Delete a Status activity from activity stream by owner.
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/01/10 15:55:22
	 */
	@Test
	public  void test02_DeleteAStatusActivityFromActivityStreamByOwner() {
		info("Test 2: Delete a Status activity from activity stream by owner");
		String text = "Activity 77529";
		/*
		- Connect to Intranet
		- Add a Status activity 
		 *Input Data: 
		 *Expected Outcome: 
		- The Status activity is displayed in the activity stream		*/
		addActivity(true, text, false,"");

		/*
		- Move the mouse over the Status activity
		 *Input Data: 
		 *Expected Outcome: A (X) icon is displayed in the top right panel of the activity		*/
		mouseOver(By.xpath(home.ELEMENT_ACTIVITY.replace("${activityText}", text)), true);
		waitForAndGetElement(By.xpath(home.ELEMENT_ACTIVITY_DELETE.replace("${activityText}", text)), DEFAULT_TIMEOUT,1,2);
		/*
		- Click on the (X) icon
		 *Input Data: 
		 *Expected Outcome: The Status activity is removed from the activity stream		*/ 
		home.deleteActivity(text);
	}

	/**
	 * Case ID:77530.
	 * Test Case Name: Dislike Status activity.
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/01/10 15:55:22
	 */
	@Test
	public  void test03_DislikeStatusActivity() {
		info("Test 3: Dislike Status activity");
		String text = "Activity 77530";
		/*
		- Connect to Intranet
		- Add a Status activity
		 *Input Data: 
		 *Expected Outcome: 
		- The Status activity is displayed in the activity stream */
		addActivity(true, text, false,"");

		/*
		- Click on like icon
		 *Input Data: 
		 *Expected Outcome: 
		- The Status activity is liked by the user, the number of like is updated to +1		*/
		home.likeOrUnlikeActivity(text);

		/*
		- Click on like icon again
		 *Input Data: 
		 *Expected Outcome: 
		- The Status activity is disliked by the user, the number of like is updated to -1	*/ 
		Utils.pause(100);
		home.likeOrUnlikeActivity(text);

		//delete data
		home.deleteActivity(text);
	}
}
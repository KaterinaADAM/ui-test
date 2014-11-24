package org.exoplatform.selenium.platform.plf.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.exoplatform.selenium.platform.HomePageGadget;
import org.exoplatform.selenium.platform.social.Activity;
import org.exoplatform.selenium.platform.social.PeopleConnection;

/**
 * 
 * @author havtt
 * @date 01-Nov-2013
 */
public class PLF_HomepageGadget_WhoIsOnlineGadget extends Activity {

	ManageAccount acc;
	HomePageGadget hg;
	NavigationToolbar navToolBar;
	PeopleConnection peopleC;

	String User1 = DATA_USER1; //"john";
	String User2 = DATA_USER2; //"mary";
	//String Pass1 = "gtn";
	String fullNameUser2 = "Mary Williams";
	String fullNameUser1 = "John Smith";

	@BeforeMethod
	public void setUpBeforeTest(){
		//		getDriverAutoSave();
		initSeleniumTest();
		acc = new ManageAccount(driver);
		hg = new HomePageGadget(driver);
		peopleC = new PeopleConnection(driver);
		navToolBar = new NavigationToolbar(driver);
		acc.signIn(User1, DATA_PASS);
	}

	@AfterMethod
	public void afterTest(){
		driver.manage().deleteAllCookies();
		driver.quit();
	}


	/**
	 * Check display of WhoisOnline gadget
	 * CaseID 70763
	 */
	@Test
	public void test01_checkDisplayOfWhoisOnlineGadget(){
		info("Go to Homepage Intranet by user acc 1");
		//		navToolBar.goToHomePage();

		info("Switch to other browser to login by user acc 2");
		loginWithAnotherAccOnThesameBrowser(User2, DATA_PASS);

		info("Confirm if WhoisOnline gadget dislays or not with user1");
		hg=new HomePageGadget(newDriver);
		newDriver.findElement(hg.ELEMENT_WHOISONLINE_GADGET);
		Utils.pause(500);
		newDriver.manage().deleteAllCookies();
		newDriver.quit();
	}

	/**
	 * Show information of online users
	 * CaseID 70764
	 */
	@Test
	public void test02_showInfoOfOnlineUser(){
		info("Go to Homepage Intranet by user acc 1");
		//		navToolBar.goToHomePage();

		info("Switch to other browser to login by user acc 2");
		loginWithAnotherAccOnThesameBrowser(User2, DATA_PASS);

		info("Confirm if WhoisOnline gadget dislays or not with user1");
		hg=new HomePageGadget(newDriver);
		hg.checkUserInfoOnWhoisOnlineGadget(User1, false, "", false, false);
		newDriver.manage().deleteAllCookies();
		newDriver.quit();
	}

	/**
	 * Connect with users from Who's online gadget
	 * CaseID 70764
	 */
	@Test
	public void test03_ConnectUserfromWhoisOnlineGadget(){
		info("Go to Homepage Intranet by user acc 1");
		//		navToolBar.goToHomePage();
		goToMyConnections();
		click(peopleC.ELEMENT_EVERYONE_TAB);
		waitForAndGetElement(By.linkText(fullNameUser2),DEFAULT_TIMEOUT,1,2);
		if(isElementNotPresent(peopleC.ELEMENT_CONNECTION_BUTTON.replace("${peopleName}", fullNameUser2)));
		    peopleC.resetConnection(fullNameUser2);

		info("Switch to other browser to login by user acc 2");
		loginWithAnotherAccOnThesameBrowser(User2, DATA_PASS);
		HomePageGadget hp2 = new HomePageGadget(newDriver);
		hp2.checkUserInfoOnWhoisOnlineGadget(User1, false , "", false, false);
		
		info("User 1 connect with user 2 from Who's Online gadget");
		navToolBar.goToHomePage();
		hg.connectPeoplefromWhoisOnlineGadget(User2);
		acc.signOut();

		info("Check if user 2 received connect invitation from user 1 or not");
		acc.signIn(User2, DATA_PASS);
		waitForAndGetElement(hg.ELEMENT_SHOW_CONNECTIONS_REQUEST_USER.replace("${nameinvitation}",fullNameUser1), DEFAULT_TIMEOUT, 1, 2);

		info("-- Clear data --");
		navToolBar.goToConnectionPage();
		peopleC.ignoreInvitation(fullNameUser1);
		newDriver.manage().deleteAllCookies();
		newDriver.quit();
	}

	/**
	 * Access activity stream of other user from Who's online gadget
	 * CaseID 70764
	 */
	@Test
	public void test04_accessASfromWhoisOnlineGadget(){
		info("Go to Homepage Intranet by user acc 1");
		navToolBar.goToHomePage();

		info("Switch to other browser to login by user acc 2");
		loginWithAnotherAccOnThesameBrowser(User2, DATA_PASS);

		info("User 1 connect with user 2 from Who's Online gadget");
		hg.accessASfromWhoisOnlineGadget(User2);
		Utils.pause(500);

		info("Check if user 2 profile page is displayed or not");
		waitForAndGetElement(hg.ELEMENT_PROFILE_TAB_USER_INFO.replace("${acc}",User2));
		waitForAndGetElement(hg.ELEMENT_PROFILE_TAB_USER_INFO.replace("${acc}",User2));
		Utils.pause(500);
		waitForAndGetElement(hg.ELEMENT_MY_AS_TAB.replace("${acc}",User2));
		Utils.pause(500);
		newDriver.manage().deleteAllCookies();
		newDriver.quit();
	}
}

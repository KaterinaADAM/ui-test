package org.exoplatform.selenium.platform.plf.functional.navigation.topnavigation.create;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformBase;
import org.testng.annotations.*;

/**
 * @author chinhdtt
 * @date 04 Jun 2014
 */
public class PLF_Navigation_TopNavigation_Create_UploadFile extends PlatformBase{
	ManageAccount acc;
	NavigationToolbar nav;

	@BeforeMethod
	public void beforeMethods(){	
		initSeleniumTest();
		driver.get(baseUrl);
		acc = new ManageAccount(driver, this.plfVersion);
		nav = new NavigationToolbar(driver, this.plfVersion);				
		acc.signIn(DATA_USER1, DATA_PASS);		
	}

	@AfterMethod
	public void afterMethods() {
		info("Logout portal");
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	/**
	 * Case ID:76724.
	 * Test Case Name: Open "Select file" from Create menu.
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/06/04 14:04:53
	 */
	@Test
	public  void test01_OpenSelectFileFromCreateMenu() {
		info("Test 1: Open Select file from Create menu");
		/*
		- Connect to Intranet
		 *Expected Outcome: The Top navigation bar is displayed		*/
		waitForAndGetElement(ELEMENT_NAVIGATION_TOOLBAR_HOMEPAGE);

		/*
		- From the menu Create "+", choose the option "Upload a file"
		 *Input Data: 
		 *Expected Outcome: 
		- The "Select File" pop up is displayed
		- Button "Cancel" are displayed
		- A Selector list "Select Drive" is displayed		*/ 
		info("Upload a file");
		nav.goToUploadFile();
		waitForAndGetElement(ELEMENT_UPLOAD_FILE_LABEL);
		waitForAndGetElement(ELEMENT_UPLOAD_FILE_DRIVER);
		waitForAndGetElement(button.ELEMENT_CLOSE_BUTTON);
	}
}
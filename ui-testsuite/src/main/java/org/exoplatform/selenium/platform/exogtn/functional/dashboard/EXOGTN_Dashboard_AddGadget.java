package org.exoplatform.selenium.platform.exogtn.functional.dashboard;

import org.exoplatform.selenium.platform.PlatformBase;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.exoplatform.selenium.platform.ManageAccount.*;
import static org.exoplatform.selenium.platform.NavigationToolbar.*;
import static org.exoplatform.selenium.platform.DashBoard.*;

/**
 *@author NhungVT
 *@date: 28/09/2012
 */

public class EXOGTN_Dashboard_AddGadget extends PlatformBase
{
	//Define data
	public String GADGET_NAME = "Hangman";
	public By MAXIMIZE_ICON = By.xpath("//span[text()='"+ GADGET_NAME+"']/preceding::span[@title='Maximize']");
	public String GADGET_URI = "http://www.labpixies.com/campaigns/hangman/hangman.xml";
	
	@BeforeMethod()
	public void beforeTest()
	{
		initSeleniumTest();
		actions = new Actions(driver);
		driver.get(baseUrl);
		driver.manage().window().maximize();
		signIn("john", "gtn");
	}
	
	
	//Add new gadget into dashboard with valid value
	@Test()
	public void test01_AddValidGadgetIntoDashboard()
	{
		//Goto DashBoard
		goToDashboard();
		waitForTextPresent(MESSAGE_DRAG_GADGETS_HERE);
		
		//Click on Add Gadgets link
		waitForElementPresent(ELEMENT_ADD_GADGETS_LINK);
		click(ELEMENT_ADD_GADGETS_LINK);
		
		//Add "http://www.labpixies.com/campaigns/hangman/hangman.xml" into Gadget list
		type(ELEMENT_GADGET_URI_INPUT, GADGET_URI, true);
		click(ELEMENT_ADD_GADGET_BUTTON);
		
		waitForElementPresent(ELEMENT_CLOSE_WINDOW);
		click(ELEMENT_CLOSE_WINDOW);
		
		//Verify Gadget is added into Dashboard
		waitForTextPresent(GADGET_NAME);
		waitForElementPresent(MAXIMIZE_ICON);
		
		//Open Hangman Gadget
		click(MAXIMIZE_ICON);
		
		/*--- After adding a gadget on Dashboard, not display icon edit, minimize, delete gadget
		 *--- Must refresh browser or open this Gadget --> These icon are displayed */ 
		
		//Delete Gadget
		deleteGadgetOnDashboard(GADGET_NAME);
	}
	
	@AfterMethod()
	public void afterTest()
	{
		driver.quit();
	}
}

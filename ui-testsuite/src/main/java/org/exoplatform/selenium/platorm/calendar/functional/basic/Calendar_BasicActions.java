package org.exoplatform.selenium.platorm.calendar.functional.basic;

import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.calendar.CalendarBase;
import org.exoplatform.selenium.platform.calendar.Event;
import org.exoplatform.selenium.platform.calendar.Task;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Calendar_BasicActions extends CalendarBase{
	
	ManageAccount acc;
	Event evt;
	Task tsk;
	@BeforeMethod
	public void setUpBeforeTest(){
		initSeleniumTest();
		acc = new ManageAccount(driver);
		evt = new Event(driver, this.plfVersion);
		tsk = new Task(driver);
		acc.signIn(DATA_USER1, DATA_PASS);
		goToCalendarPage();

	}

	@AfterMethod
	public void afterTest(){
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	/**
	 * Check export format
	 * Id : 116324
	 */
	@Test
	public void test01_CheckExportFormat(){
		String calName="caltest01";
		
		evt.addCalendar(calName,"" , "blue", null);
		evt.addQuickEvent("event1", "", "", "", true, calName);
		evt.exportCalendar(calName, "");
		deleteFile("TestOutput/" + calName+".ics");
		deleteCalendar(calName);	
	}

}

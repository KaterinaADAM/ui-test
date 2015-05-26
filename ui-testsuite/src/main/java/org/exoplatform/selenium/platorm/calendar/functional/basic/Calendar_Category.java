package org.exoplatform.selenium.platorm.calendar.functional.basic;

import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.calendar.CalendarBase;
import org.exoplatform.selenium.platform.calendar.Event;
import org.exoplatform.selenium.platform.calendar.Task;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Calendar_Category extends CalendarBase {
	
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
		setTimezoneForCalendar("(GMT +07:00) Asia/Ho_Chi_Minh");
		}

	@AfterMethod
	public void afterTest(){
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	/**
	 * Add new category with valid value
	 * ID : 116476
	 */
	@Test
	public void test01_AddTestWithValidValue(){
		String categoryName="maCtag";
		evt.gotoAddEventCategory();
		evt.addEventCategory(categoryName);
		evt.gotoAddEventCategory();
		evt.deleteEventCategory(categoryName);
		
	}
	
	/**
	 * Delete event category
	 * ID : 116476
	 */
	@Test
	public void test02_DeleteEventCategory(){
		String categoryName="maCtagtwo";
		String calName="John Smith";
		evt.gotoAddEventCategory();
		evt.addEventCategory(categoryName);
		evt.addQuickEvent("event1", "", "", "", true,calName,categoryName);
		evt.gotoAddEventCategory();
		evt.deleteEventCategory(categoryName);

		waitForElementNotPresent(ELEMENT_EVENT_TASK_ONE_DAY.replace("${taskName}", "event1"),5000);
	}
	
	/**
	 * Edit category with valid value
	 * ID : 116478
	 */
	@Test
	public void test03_EditCategoryWithValidValue(){
		String categoryName="maCtagthree";
		String editedCategoryName="newCat";
		evt.gotoAddEventCategory();
		evt.addEventCategory(categoryName);
		
		evt.gotoAddEventCategory();
		evt.editEventCategory(categoryName, editedCategoryName);
		
		evt.gotoAddEventCategory();
		evt.deleteEventCategory(editedCategoryName);
	}
}

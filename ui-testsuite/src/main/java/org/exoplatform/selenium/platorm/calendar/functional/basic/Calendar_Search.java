package org.exoplatform.selenium.platorm.calendar.functional.basic;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.calendar.CalendarBase;
import org.exoplatform.selenium.platform.calendar.Event;
import org.exoplatform.selenium.platform.calendar.Task;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Calendar_Search extends CalendarBase{
	ManageAccount acc;
	Event evt;
	Task tsk;
	NavigationToolbar navTool;
	@BeforeMethod
	public void setUpBeforeTest(){
		initSeleniumTest();
		acc = new ManageAccount(driver);
		evt = new Event(driver, this.plfVersion);
		tsk = new Task(driver);
		navTool = new NavigationToolbar(driver);
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
	 * Search by quick search when search keyword matches existing events or tasks
	 */
	@Test
	public void test01_SearchByQuickSearchWhenSearchKeywordMatchesExistingEventsOrTasks(){
		String eventName="monEvent";
		String description="I like apple and chocolate";
		
		evt.addQuickEvent(eventName, description, "", "", true);
		
		quickSearchCalendar("apple");
		Utils.pause(3000);
		
		waitForAndGetElement(By.xpath(ELEMENT_TITLE_RESULT.replace("${title}",eventName)));
		waitForAndGetElement(By.xpath(ELEMENT_COLUMN_NAME_AND_RESULT_NAME.replace("${title}", "Start").replace("${name}", getDate(0,"MM/dd/yyyy"))));
		waitForAndGetElement(By.xpath(ELEMENT_COLUMN_NAME_AND_RESULT_NAME.replace("${title}", "End").replace("${name}", getDate(0,"MM/dd/yyyy"))));
		waitForAndGetElement(By.xpath(ELEMENT_COLUMN_NAME_AND_RESULT_NAME.replace("${title}", "Description").replace("${name}",description )));

		info("Restore data");
		click(ELEMENT_BUTTON_CLOSE_QUICK_SEARCH_RESULT);		
		// delete data
		evt.deleteEventTask(eventName, selectDayOption.ALLDAY);
	}
	
}

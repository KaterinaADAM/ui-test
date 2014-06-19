package org.exoplatform.selenium.platform.calendar.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.ManageAccount.userType;
import org.openqa.selenium.By;
import org.exoplatform.selenium.Utils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.exoplatform.selenium.platform.calendar.CalendarBase;
import org.exoplatform.selenium.platform.calendar.Event;
import org.exoplatform.selenium.platform.calendar.Task;

/**
 * @author havtt
 * @date 25 Oct 2013
 */
public class Calendar_Event extends CalendarBase {

	ManageAccount acc;
	Event evt;
	Task tsk;

	@BeforeMethod
	public void setUpBeforeTest(){
		getDriverAutoSave();
		acc = new ManageAccount(driver);
		evt = new Event(driver);
		tsk = new Task(driver);
		acc.signIn(DATA_USER1, DATA_PASS);
		goToCalendarPage();
		goToCalendarSettings();
		settingCalendar("Week", "mm/dd/yyyy", null, null, "Monday", null, null);
	}

	@AfterMethod
	public void afterTest(){
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	/**
	 * Add new event in Personal Calendar
	 * CaseID 68651
	 */
	@Test
	public void test01_addNewEventinPersonalCal() {
		String EVENT_01 = "EVENT68651";

		info("Go to Intranet Calendar");

		info("Add a new event in Personal calendar");
		Utils.pause(5000);
		evt.addQuickEvent(EVENT_01, EVENT_01,getDate(0,"MM/dd/yyyy"), getDate(0,"MM/dd/yyyy"), false);

		info("Confirm added event displays in the calendar");
		waitForAndGetElement(By.xpath(ELEMENT_EVENT_TASK_ONE_DAY.replace("${taskName}", EVENT_01)));

		info("restore data");
		deleteEventTask(EVENT_01, selectDayOption.ONEDAY);
	}

	/**
	 * Add new event in Shared Calendar
	 * CaseID 68656
	 */
	@Test
	public void test02_addNewEventinSharedCal() {
		String EVENT_02 = "EVENT68656";
		String CAL_02 = "CAL_02";
		String EVENT_CATEGORY = "All";
		String EVENT_SHARED_CALENDAR = CAL_02;
		String[] USER_SHARED = {"root"};
		String USER_SHARED_PASS = "12345";
		boolean[] EDITABLE = {true};

		info("==Create and share a calendar with User root==");
		Utils.pause(5000);
		addCalendar(CAL_02, CAL_02, "pink");
		Utils.pause(3000);
		shareCalendar(CAL_02, USER_SHARED, EDITABLE);

		info("==User fqa sign out==");
		acc.signOut();

		info("==Login by shared user - root==");
		acc.signIn(USER_SHARED[0], USER_SHARED_PASS);

		info("==Add event on shared calendar==");
		goToCalendarPage();
		Utils.pause(5000);
		evt.addQuickEvent(EVENT_02,EVENT_02,getDate(0,"MM/dd/yyyy"), getDate(0,"MM/dd/yyyy"), false, EVENT_SHARED_CALENDAR, EVENT_CATEGORY);
		waitForAndGetElement(By.xpath(ELEMENT_EVENT_TASK_ONE_DAY.replace("${taskName}", EVENT_02)));

		info("==Restore data==");
		deleteEventTask(EVENT_02,selectDayOption.ONEDAY);
		deleteCalendar(CAL_02,true);
	}

	/**
	 * Add new event in Group Calendar
	 * CaseID 68657
	 */
	@Test
	public void test03_addNewEventinGroupCal() {
		String EVENT_03 = "EVENT68657";
		String CAL_03 = "CAL_03"; 
		String EVENT_CATEGORY = "All";
		String USER_GROUP = "root";
		String USER_GROUP_PASS = "12345";
		String CAL_GROUP = "/platform/administrators";

		info("==Create a group calendar==");
		Utils.pause(5000);
		addCalendar(CAL_03, CAL_03, "red", CAL_GROUP);
		Utils.pause(3000);
		changeEditPermissionForCalShowInGroup(CAL_03, USER_GROUP, CAL_GROUP);

		info("==User fqa sign out==");
		acc.signOut();

		info("==Login by shared user - root==");
		acc.signIn(USER_GROUP, USER_GROUP_PASS);
		Utils.pause(5000);

		info("==Add event on group calendar==");
		goToCalendarPage();
		evt.addQuickEvent(EVENT_03,EVENT_03,getDate(0,"MM/dd/yyyy"), getDate(0,"MM/dd/yyyy"), false, CAL_03, EVENT_CATEGORY);
		waitForAndGetElement(By.xpath(ELEMENT_EVENT_TASK_ONE_DAY.replace("${taskName}", EVENT_03)));

		info("==Restore data==");
		deleteEventTask(EVENT_03, selectDayOption.ONEDAY);
		deleteCalendar(CAL_03,true);
	}

	/**
	 * Edit event in Personal Calendar
	 * CaseID 69264
	 */
	@Test
	public void test04_editEventinPersonalCal() {
		String EVENT04 = "EVENT69264";
		String TITLE = "EVENT69264edited";
		String DESCRIPTION = "EVENT_04_description_edited";

		info("Add a new task");
		Utils.pause(3000);
		evt.addQuickEvent(EVENT04,EVENT04,getDate(0,"MM/dd/yyyy"),getDate(0,"MM/dd/yyyy"),false);

		info("Edit a task");
		Utils.pause(3000);
		evt.editEvent(EVENT04,TITLE,DESCRIPTION, null,null,null,false);

		info("Restore data");
		waitForAndGetElement(By.xpath(ELEMENT_EVENT_TASK_ONE_DAY.replace("${taskName}", EVENT04)));
		deleteEventTask(EVENT04, selectDayOption.ONEDAY);
	}

	/**
	 * Delete event in Personal Calendar
	 * CaseID 69265
	 */
	@Test
	public void test05_deleteEventinPersonalCal() {
		String EVENT05 = "EVENT69265";

		info("Add a new event");
		Utils.pause(3000);
		evt.addQuickEvent(EVENT05,EVENT05,getDate(0,"MM/dd/yyyy"),getDate(0,"MM/dd/yyyy"),false);
		//waitForAndGetElement(ELEMENT_EVENT_TASK_ONE_DAY.replace("${taskName}", EVENT05));
		if (isElementNotPresent(ELEMENT_EVENT_TASK_ONE_DAY.replace("${taskName}", EVENT05))){
			waitForAndGetElement(ELEMENT_EVENT_TASK_ONE_DAY_PLF41.replace("${taskName}", EVENT05));
		}
		
		info("Delete an event");
		Utils.pause(5000);
		deleteEventTask(EVENT05, selectDayOption.ONEDAY);
	}


	/**
	 * Drag & Drop event
	 * CaseID 69287
	 */
	@Test
	public void test06_DragDropEvent() {
		String EVENT06 = "EVENT69287";

		info("Add a new task");
		Utils.pause(5000);
		evt.addQuickEvent(EVENT06,EVENT06,getDate(0,"MM/dd/yyyy"),getDate(0,"MM/dd/yyyy"),false);

		info("Drag & drop a task");
		if (isElementNotPresent(ELEMENT_EVENT_TASK_ONE_DAY.replace("${taskName}", EVENT06))){
			dragAndDropToObject(By.xpath(ELEMENT_EVENT_TASK_ONE_DAY_PLF41.replace("${taskName}", EVENT06)), ELEMENT_TARGET_DATE);
		}else{
			//waitForAndGetElement(By.xpath(ELEMENT_EVENT_TASK_ONE_DAY.replace("${taskName}", EVENT06)), 50000);
			dragAndDropToObject(By.xpath(ELEMENT_EVENT_TASK_ONE_DAY.replace("${taskName}", EVENT06)), ELEMENT_TARGET_DATE);
		}
		info("Restore data");
		Utils.pause(5000);
		deleteEventTask(EVENT06, selectDayOption.ONEDAY);
	}

	/**
	 * Edit event in Group Calendar
	 * CaseID 69288
	 */
	@Test
	public void test07_editEventinGroupCal() {
		String EVENT07 = "EVENT69288";
		String CAL_07 = "CAL_07";
		String CAL_GROUP = "/platform/web-contributors";
		String TITLE = "EVENT_07_edited";
		String DESCRIPTION = "EVENT_07_description_edited";
		String EVENT_CATEGORY = "All";
		String USER_GROUP = "root";

		info("==Create a group calendar==");
		driver.navigate().refresh();
		addCalendar(CAL_07, CAL_07, "green", CAL_GROUP);
		Utils.pause(3000);
		changeEditPermissionForCalShowInGroup(CAL_07, USER_GROUP, CAL_GROUP);

		info("==Login by shared user==");
		acc.userSignIn(userType.ROOT);

		info("==Add a new event on group calendar==");
		goToCalendarPage();
		Utils.pause(5000);
		evt.addQuickEvent(EVENT07,EVENT07,getDate(0,"MM/dd/yyyy"),getDate(0,"MM/dd/yyyy"),false, CAL_07, EVENT_CATEGORY);

		info("==Edit an event==");
		Utils.pause(5000);
		evt.editEvent(EVENT07,TITLE,DESCRIPTION, null,null,null,false);

		info("==Restore data==");
		waitForAndGetElement(By.xpath(ELEMENT_EVENT_TASK_ONE_DAY.replace("${taskName}", EVENT07)));
		deleteEventTask(EVENT07, selectDayOption.ONEDAY);
		deleteCalendar(CAL_07,true);
	}

	/**
	 * Delete event in group Calendar
	 * CaseID 69289
	 */
	@Test
	public void test08_deleteEventinGroupCal() {
		String EVENT08 = "EVENT69289";
		String CAL_08 = "CAL_08";
		String CAL_GROUP = "/platform/web-contributors";
		String EVENT_CATEGORY = "All";
		String USER_GROUP = "root";
		String USER_GROUP_PASS = "12345";

		info("==Create a group calendar==");
		driver.navigate().refresh();
		addCalendar(CAL_08, CAL_08, "green", CAL_GROUP);
		Utils.pause(3000);
		changeEditPermissionForCalShowInGroup(CAL_08, USER_GROUP, CAL_GROUP);

		info("==User fqa log out==");
		acc.signOut();

		info("==Login by shared user==");
		acc.signIn(USER_GROUP, USER_GROUP_PASS);
		Utils.pause(5000);

		info("==Add a new event on group calendar==");
		goToCalendarPage();
		Utils.pause(5000);
		evt.addQuickEvent(EVENT08,EVENT08,getDate(0,"MM/dd/yyyy"),getDate(0,"MM/dd/yyyy"),false, CAL_08, EVENT_CATEGORY);

		info("==Delete an event on group calendar==");
		waitForAndGetElement(By.xpath(ELEMENT_EVENT_TASK_ONE_DAY.replace("${taskName}", EVENT08)));
		deleteEventTask(EVENT08, selectDayOption.ONEDAY);
		deleteCalendar(CAL_08,true);
	}

	/**
	 * Edit event in shared Calendar
	 * CaseID 69292
	 */
	@Test
	public void test09_editEventinSharedCal() {
		String EVENT09 = "EVENT69292";
		String CAL_09 = "CAL_09";
		String TITLE = "EVENT_09_edited";
		String DESCRIPTION = "EVENT_09_description_edited";
		String EVENT_CATEGORY = "All";
		String[] USER_SHARED = {"root"};
		String USER_SHARED_PASS = "12345";
		boolean[] EDITABLE = {true};

		info("==Create and share a calendar with User root==");
		driver.navigate().refresh();
		addCalendar(CAL_09, CAL_09, "pink");
		Utils.pause(3000);
		shareCalendar(CAL_09, USER_SHARED, EDITABLE);

		info("==User fqa sign out==");
		acc.signOut();

		info("==Login by shared user - root==");
		acc.signIn(USER_SHARED[0], USER_SHARED_PASS);

		info("==Add event on shared calendar==");
		goToCalendarPage();
		Utils.pause(5000);
		evt.addQuickEvent(EVENT09,EVENT09,getDate(0,"MM/dd/yyyy"), getDate(0,"MM/dd/yyyy"), false, CAL_09, EVENT_CATEGORY);
		waitForAndGetElement(By.xpath(ELEMENT_EVENT_TASK_ONE_DAY.replace("${taskName}", EVENT09)));

		info("==Edit an event==");
		evt.editEvent(EVENT09,TITLE,DESCRIPTION, null,null,null,false);

		info("==Restore data==");
		waitForAndGetElement(By.xpath(ELEMENT_EVENT_TASK_ONE_DAY.replace("${taskName}", EVENT09)));
		deleteEventTask(EVENT09, selectDayOption.ONEDAY);
		deleteCalendar(CAL_09,true);
	}

	/**
	 * Delete event in shared Calendar
	 * CaseID 69291
	 */
	@Test
	public void test10_deleteEventinSharedCal() {
		String EVENT10 = "EVENT69291";
		String CAL_10 = "CAL_10";
		String EVENT_CATEGORY = "All";
		String[] USER_SHARED = {"root"};
		String USER_SHARED_PASS = "12345";
		boolean[] EDITABLE = {true};

		info("==Create a shared calendar==");
		driver.navigate().refresh();
		addCalendar(CAL_10, CAL_10, "blue");
		Utils.pause(3000);
		shareCalendar(CAL_10, USER_SHARED, EDITABLE);

		info("==User fqa sign out==");
		acc.signOut();

		info("==Login by shared user - root==");
		acc.signIn(USER_SHARED[0], USER_SHARED_PASS);

		info("==Add a new event on shared calendar==");
		goToCalendarPage();
		Utils.pause(5000);
		evt.addQuickEvent(EVENT10,EVENT10,getDate(0,"MM/dd/yyyy"),getDate(0,"MM/dd/yyyy"),false, CAL_10, EVENT_CATEGORY);

		info("==Delete an event on shared calendar==");
		waitForAndGetElement(By.xpath(ELEMENT_EVENT_TASK_ONE_DAY.replace("${taskName}", EVENT10)));
		deleteEventTask(EVENT10, selectDayOption.ONEDAY);

		info("==Restore data==");
		deleteCalendar(CAL_10);
	}

	/** 
	 * Check pop-up reminder of an event
	 * CaseID: 75245
	 */
	//related issue: FQA-1351
	@Test(groups={"pending"})
	public void test11_CheckPopupReminderOfEvent() {

	}

	/** 
	 * Check E-mail reminder of an event
	 * CaseID: 75246
	 */
	//Related issue: FQA-1351
	@Test(groups={"pending"})
	public void test12_CheckEmailReminderOfEvent() {

	}

	/** 
	 * Resize an event
	 * CaseID: 68660
	 */
	//Related issue: FQA-1352
	@Test(groups={"pending"})
	public void test13_ResizeEvent(){

	}
}

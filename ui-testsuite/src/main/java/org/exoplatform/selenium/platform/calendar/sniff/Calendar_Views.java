package org.exoplatform.selenium.platform.calendar.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.calendar.CalendarBase;
import org.exoplatform.selenium.platform.calendar.Event;
import org.exoplatform.selenium.platform.calendar.Task;
import org.testng.annotations.*;

/**
 * @author chinhdtt
 * @date 09 Jul 2014
 */
public class Calendar_Views extends CalendarBase{
	ManageAccount acc; 
	Event event; 
	Task task; 

	@BeforeMethod
	public void setUpBeforeTest(){
		initSeleniumTest();
		driver.get(baseUrl);
		acc = new ManageAccount(driver, this.plfVersion);
		event = new Event(driver, this.plfVersion);
		task = new Task(driver, this.plfVersion);
		acc.signIn(DATA_USER1, DATA_PASS);
	}

	@AfterMethod
	public void afterTest(){
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	/**
	 * Case ID:99809.
	 * Test Case Name: Check category filter in Week view.
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/07/09 16:59:49
	 */
	@Test
	public void test01_CheckCategoryFilterInWeekView() {
		info("Test 1: Check category filter in Week view");
		String event1 = "Event199809";
		String event2 = "Event299809";
		String event3 = "Event399809";
		String event4 = "Event499809";
		String event5 = "Event599809";
		String task1 = "Task199809";

		/*
		- goto Calendar and click [Week] to show week view
		- Create some event/tasks belong to some different category like anniversary, holiday, meeting, call
		- in Categories drop-down box, choose in turn these categories
		 *Expected Outcome: 
		- Events/Tasks belong to the category are shown in main pane
		- When selecting [All], all the Events are shown		*/ 
		goToCalendarPage();
		click(ELEMENT_BUTTON_WEEK_VIEW);
		waitForAndGetElement(ELEMENT_BUTTON_VIEW_ACTIVE.replace("${view}", "Week"));

		info("add some events and tasks in Meeting");
		event.addQuickEvent(event1, event1,getDate(0,"MM/dd/yyyy"),getDate(0,"MM/dd/yyyy"),true,"John Smith","Meeting");
		task.addQuickTask(task1, task1,getDate(0,"MM/dd/yyyy"),getDate(0,"MM/dd/yyyy"),true,"John Smith","Meeting");

		info("Add event in Calls");
		event.addQuickEvent(event2, event2,getDate(0,"MM/dd/yyyy"),getDate(0,"MM/dd/yyyy"),true,"John Smith","Calls");

		info("Add event in Client");
		event.addQuickEvent(event3, event3,getDate(0,"MM/dd/yyyy"),getDate(0,"MM/dd/yyyy"),true,"John Smith","Clients");

		info("Add event in Holiday");
		event.addQuickEvent(event4, event4,getDate(0,"MM/dd/yyyy"),getDate(0,"MM/dd/yyyy"),true,"John Smith","Holiday");

		info("Add event in Anniversary");
		event.addQuickEvent(event5, event5,getDate(0,"MM/dd/yyyy"),getDate(0,"MM/dd/yyyy"),true,"John Smith","Anniversary");

		info("Check event displayed in Meeting category");
		chooseEventCategoryOpt("Meeting");
		Utils.pause(2000);
		waitForAndGetElement(ELEMENT_EVENT_TASK_ALL_DAY.replace("${event}",event1));
		waitForElementNotPresent(ELEMENT_EVENT_TASK_ALL_DAY.replace("${event}",event2));
		waitForElementNotPresent(ELEMENT_EVENT_TASK_ALL_DAY.replace("${event}",event3));
		waitForElementNotPresent(ELEMENT_EVENT_TASK_ALL_DAY.replace("${event}",event4));
		waitForElementNotPresent(ELEMENT_EVENT_TASK_ALL_DAY.replace("${event}",event5));
		waitForAndGetElement(ELEMENT_EVENT_TASK_ALL_DAY.replace("${event}",task1));

		info("Check event displayed in Anniversary category");
		chooseEventCategoryOpt("Anniversary");
		Utils.pause(2000);
		waitForElementNotPresent(ELEMENT_EVENT_TASK_ALL_DAY.replace("${event}",event1));
		waitForElementNotPresent(ELEMENT_EVENT_TASK_ALL_DAY.replace("${event}",event2));
		waitForElementNotPresent(ELEMENT_EVENT_TASK_ALL_DAY.replace("${event}",event3));
		waitForElementNotPresent(ELEMENT_EVENT_TASK_ALL_DAY.replace("${event}",event4));
		waitForAndGetElement(ELEMENT_EVENT_TASK_ALL_DAY.replace("${event}",event5));
		waitForElementNotPresent(ELEMENT_EVENT_TASK_ALL_DAY.replace("${event}",task1));

		info("Check event displayed in Holiday category");
		chooseEventCategoryOpt("Holiday");
		Utils.pause(2000);
		waitForElementNotPresent(ELEMENT_EVENT_TASK_ALL_DAY.replace("${event}",event1));
		waitForElementNotPresent(ELEMENT_EVENT_TASK_ALL_DAY.replace("${event}",event2));
		waitForElementNotPresent(ELEMENT_EVENT_TASK_ALL_DAY.replace("${event}",event3));
		waitForAndGetElement(ELEMENT_EVENT_TASK_ALL_DAY.replace("${event}",event4));
		waitForElementNotPresent(ELEMENT_EVENT_TASK_ALL_DAY.replace("${event}",event5));
		waitForElementNotPresent(ELEMENT_EVENT_TASK_ALL_DAY.replace("${event}",task1));

		info("Check event displayed in Client category");
		chooseEventCategoryOpt("Clients");
		Utils.pause(2000);
		waitForElementNotPresent(ELEMENT_EVENT_TASK_ALL_DAY.replace("${event}",event1));
		waitForElementNotPresent(ELEMENT_EVENT_TASK_ALL_DAY.replace("${event}",event2));
		waitForAndGetElement(ELEMENT_EVENT_TASK_ALL_DAY.replace("${event}",event3));
		waitForElementNotPresent(ELEMENT_EVENT_TASK_ALL_DAY.replace("${event}",event4));
		waitForElementNotPresent(ELEMENT_EVENT_TASK_ALL_DAY.replace("${event}",event5));
		waitForElementNotPresent(ELEMENT_EVENT_TASK_ALL_DAY.replace("${event}",task1));

		info("Check event displayed in calls category");
		chooseEventCategoryOpt("Calls");
		Utils.pause(2000);
		waitForElementNotPresent(ELEMENT_EVENT_TASK_ALL_DAY.replace("${event}",event1));
		waitForAndGetElement(ELEMENT_EVENT_TASK_ALL_DAY.replace("${event}",event2));
		waitForElementNotPresent(ELEMENT_EVENT_TASK_ALL_DAY.replace("${event}",event3));
		waitForElementNotPresent(ELEMENT_EVENT_TASK_ALL_DAY.replace("${event}",event4));
		waitForElementNotPresent(ELEMENT_EVENT_TASK_ALL_DAY.replace("${event}",event5));
		waitForElementNotPresent(ELEMENT_EVENT_TASK_ALL_DAY.replace("${event}",task1));

		info("Check event displayed in all category");
		chooseEventCategoryOpt("All");
		Utils.pause(2000);
		waitForAndGetElement(ELEMENT_EVENT_TASK_ALL_DAY.replace("${event}",event1));
		waitForAndGetElement(ELEMENT_EVENT_TASK_ALL_DAY.replace("${event}",event2));
		waitForAndGetElement(ELEMENT_EVENT_TASK_ALL_DAY.replace("${event}",event3));
		waitForAndGetElement(ELEMENT_EVENT_TASK_ALL_DAY.replace("${event}",event4));
		waitForAndGetElement(ELEMENT_EVENT_TASK_ALL_DAY.replace("${event}",event5));
		waitForAndGetElement(ELEMENT_EVENT_TASK_ALL_DAY.replace("${event}",task1));

		info("Clear data");
		deleteEventTask(event1);
		deleteEventTask(event2);
		deleteEventTask(event3);
		deleteEventTask(event4);
		deleteEventTask(event5);
		deleteEventTask(task1);
	}

	/**
	 * Case ID:99810.
	 * Test Case Name: Check category filter in List view.
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/07/09 16:59:49
	 */
	@Test
	public  void test02_CheckCategoryFilterInListView() {
		info("Test 2: Check category filter in List view");
		String event1 = "Event199810";
		String event2 = "Event299810";
		String event3 = "Event399810";
		String event4 = "Event499810";
		String event5 = "Event599810";
		String task1 = "Task199810";

		/*
		- goto Calendar and click [List] to show List view
		- Create some event/tasks belong to some different category like anniversary, holiday, meeting, call
		- in Categories drop-down box, choose in turn these categories
		 *Expected Outcome: 
		- Events/Tasks belong to the category are shown in main pane
		- When selecting [All], all the Events are shown		*/ 
		goToCalendarPage();
		click(ELEMENT_BUTTON_LIST_VIEW);
		waitForAndGetElement(ELEMENT_BUTTON_VIEW_ACTIVE.replace("${view}", "List"));
		event.addQuickEvent(event1, event1,getDate(0,"MM/dd/yyyy"),getDate(0,"MM/dd/yyyy"),true,"John Smith","Meeting");
		task.addQuickTask(task1, task1,getDate(0,"MM/dd/yyyy"),getDate(0,"MM/dd/yyyy"),true,"John Smith","Meeting");

		info("Add event in Calls");
		event.addQuickEvent(event2, event2,getDate(0,"MM/dd/yyyy"),getDate(0,"MM/dd/yyyy"),true,"John Smith","Calls");

		info("Add event in Client");
		event.addQuickEvent(event3, event3,getDate(0,"MM/dd/yyyy"),getDate(0,"MM/dd/yyyy"),true,"John Smith","Clients");

		info("Add event in Holiday");
		event.addQuickEvent(event4, event4,getDate(0,"MM/dd/yyyy"),getDate(0,"MM/dd/yyyy"),true,"John Smith","Holiday");

		info("Add event in Anniversary");
		event.addQuickEvent(event5, event5,getDate(0,"MM/dd/yyyy"),getDate(0,"MM/dd/yyyy"),true,"John Smith","Anniversary");


		info("Check event displayed in Meeting category");
		chooseEventCategoryOpt("Meeting");
		Utils.pause(2000);
		waitForAndGetElement(EVENT_LIST_VIEW.replace("${eventTitle}",event1));
		waitForElementNotPresent(EVENT_LIST_VIEW.replace("${eventTitle}",event2));
		waitForElementNotPresent(EVENT_LIST_VIEW.replace("${eventTitle}",event3));
		waitForElementNotPresent(EVENT_LIST_VIEW.replace("${eventTitle}",event4));
		waitForElementNotPresent(EVENT_LIST_VIEW.replace("${eventTitle}",event5));
		waitForAndGetElement(EVENT_LIST_VIEW.replace("${eventTitle}",task1));

		info("Check event displayed in Anniversary category");
		chooseEventCategoryOpt("Anniversary");
		Utils.pause(2000);
		waitForElementNotPresent(EVENT_LIST_VIEW.replace("${eventTitle}",event1));
		waitForElementNotPresent(EVENT_LIST_VIEW.replace("${eventTitle}",event2));
		waitForElementNotPresent(EVENT_LIST_VIEW.replace("${eventTitle}",event3));
		waitForElementNotPresent(EVENT_LIST_VIEW.replace("${eventTitle}",event4));
		waitForAndGetElement(EVENT_LIST_VIEW.replace("${eventTitle}",event5));
		waitForElementNotPresent(EVENT_LIST_VIEW.replace("${eventTitle}",task1));

		info("Check event displayed in Holiday category");
		chooseEventCategoryOpt("Holiday");
		Utils.pause(2000);
		waitForElementNotPresent(EVENT_LIST_VIEW.replace("${eventTitle}",event1));
		waitForElementNotPresent(EVENT_LIST_VIEW.replace("${eventTitle}",event2));
		waitForElementNotPresent(EVENT_LIST_VIEW.replace("${eventTitle}",event3));
		waitForAndGetElement(EVENT_LIST_VIEW.replace("${eventTitle}",event4));
		waitForElementNotPresent(EVENT_LIST_VIEW.replace("${eventTitle}",event5));
		waitForElementNotPresent(EVENT_LIST_VIEW.replace("${eventTitle}",task1));

		info("Check event displayed in Client category");
		chooseEventCategoryOpt("Clients");
		Utils.pause(2000);
		waitForElementNotPresent(EVENT_LIST_VIEW.replace("${eventTitle}",event1));
		waitForElementNotPresent(EVENT_LIST_VIEW.replace("${eventTitle}",event2));
		waitForAndGetElement(EVENT_LIST_VIEW.replace("${eventTitle}",event3));
		waitForElementNotPresent(EVENT_LIST_VIEW.replace("${eventTitle}",event4));
		waitForElementNotPresent(EVENT_LIST_VIEW.replace("${eventTitle}",event5));
		waitForElementNotPresent(EVENT_LIST_VIEW.replace("${eventTitle}",task1));

		info("Check event displayed in calls category");
		chooseEventCategoryOpt("Calls");		
		Utils.pause(2000);
		waitForElementNotPresent(EVENT_LIST_VIEW.replace("${eventTitle}",event1));
		waitForAndGetElement(EVENT_LIST_VIEW.replace("${eventTitle}",event2));
		waitForElementNotPresent(EVENT_LIST_VIEW.replace("${eventTitle}",event3));
		waitForElementNotPresent(EVENT_LIST_VIEW.replace("${eventTitle}",event4));
		waitForElementNotPresent(EVENT_LIST_VIEW.replace("${eventTitle}",event5));
		waitForElementNotPresent(EVENT_LIST_VIEW.replace("${eventTitle}",task1));

		info("Check event displayed in all category");
		chooseEventCategoryOpt("All");
		Utils.pause(2000);
		waitForAndGetElement(EVENT_LIST_VIEW.replace("${eventTitle}",event1));
		waitForAndGetElement(EVENT_LIST_VIEW.replace("${eventTitle}",event2));
		waitForAndGetElement(EVENT_LIST_VIEW.replace("${eventTitle}",event3));
		waitForAndGetElement(EVENT_LIST_VIEW.replace("${eventTitle}",event4));
		waitForAndGetElement(EVENT_LIST_VIEW.replace("${eventTitle}",event5));
		waitForAndGetElement(EVENT_LIST_VIEW.replace("${eventTitle}",task1));

		info("Clear data");
		click(ELEMENT_BUTTON_WEEK_VIEW);
		Utils.pause(2000);
		waitForAndGetElement(ELEMENT_BUTTON_VIEW_ACTIVE.replace("${view}", "Week"));
		deleteEventTask(event1);
		deleteEventTask(event2);
		deleteEventTask(event3);
		deleteEventTask(event4);
		deleteEventTask(event5);
		deleteEventTask(task1);	
	}

	/**
	 * Case ID:111894.
	 * Test Case Name: Check displaying added task/event in day view.
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/07/09 16:59:49
	 */
	@Test
	public  void test03_CheckDisplayingAddedTaskeventInDayView() {
		info("Test 3: Check displaying added task/event in day view");
		String event1 = "Event1118941";
		String event2 = "Event1118942";
		String task1 = "Task1118941";

		/*
		- Go to calendar
		- Add new task/event
		 *Expected Outcome: Task/ event are created		*/
		/*
		- Click on Day on the main bar
		 *Input Data: 
		 *Expected Outcome: Event/ task is displayed in Day view		*/		
		goToCalendarPage();
		click(ELEMENT_BUTTON_DAY_VIEW);
		waitForAndGetElement(ELEMENT_BUTTON_VIEW_ACTIVE.replace("${view}", "Day"));

		info("add some events and tasks in Meeting");
		event.addQuickEvent(event1, event1,getDate(-1,"MM/dd/yyyy"),getDate(-1,"MM/dd/yyyy"),true,"John Smith","Meeting");

		task.addQuickTask(task1, task1,getDate(0,"MM/dd/yyyy"),getDate(0,"MM/dd/yyyy"),true,"John Smith","Meeting");

		info("Add event in Calls");
		event.addQuickEvent(event2, event2,getDate(1,"MM/dd/yyyy"),getDate(1,"MM/dd/yyyy"),true,"John Smith","Calls");

		/*
		- Click next day/ previous day icon
		 *Input Data: 
		 *Expected Outcome: 
		- Next day/previous day is displayed correctly
		- Mini calendar is updated also		*/ 

		mouseOverAndClick(event.ELEMENT_PREVIOUS_DAY);
		Utils.pause(2000);
		info("Select previous day");
		waitForAndGetElement(ELEMENT_CALENDAR_PANEL);
		waitForAndGetElement(ELEMENT_EVENT_TASK_ALL_DAY_PLF41_DAY_VIEW.replace("${event}", event1));

		Utils.pause(500);
		click(event.ELEMENT_NEXT_DAY);
		Utils.pause(2000);
		waitForAndGetElement(ELEMENT_CALENDAR_PANEL);
		waitForAndGetElement(ELEMENT_EVENT_TASK_ALL_DAY_PLF41_DAY_VIEW.replace("${event}", task1));

		Utils.pause(500);
		click(event.ELEMENT_NEXT_DAY);
		Utils.pause(2000);
		waitForAndGetElement(ELEMENT_CALENDAR_PANEL);
		waitForAndGetElement(ELEMENT_EVENT_TASK_ALL_DAY_PLF41_DAY_VIEW.replace("${event}", event2));

		info("Clear data");
		click(ELEMENT_BUTTON_WEEK_VIEW);
		Utils.pause(2000);
		waitForAndGetElement(ELEMENT_BUTTON_VIEW_ACTIVE.replace("${view}", "Week"));
		Utils.pause(500);
		deleteEventTask(event1);
		deleteEventTask(event2);
		deleteEventTask(task1);
	}

	/**
	 * Case ID:111895.
	 * Test Case Name: Check displaying added task/event in week view.
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/07/09 16:59:49
	 */
	@Test
	public  void test04_CheckDisplayingAddedTaskeventInWeekView() {
		info("Test 4: Check displaying added task/event in week view");
		String event1 = "Event111895";
		String task1 = "Task111895";

		/*
		- Go to calendar
		- Add new task/event
		 *Expected Outcome: Task/ event are created		*/
		goToCalendarPage();
		click(ELEMENT_BUTTON_WEEK_VIEW);
		Utils.pause(2000);
		waitForAndGetElement(ELEMENT_BUTTON_VIEW_ACTIVE.replace("${view}", "Week"));

		info("add some events and tasks in Meeting");
		event.addQuickEvent(event1, event1,getDate(0,"MM/dd/yyyy"),getDate(0,"MM/dd/yyyy"),true,"John Smith","Meeting");
		waitForAndGetElement(ELEMENT_EVENT_TASK_ALL_DAY.replace("${event}",event1));
		task.addQuickTask(task1, task1,getDate(7,"MM/dd/yyyy"),getDate(7,"MM/dd/yyyy"),true,"John Smith","Meeting");

		/*
		- Click on Week on the main bar
		 *Input Data: 
		 *Expected Outcome: Event/ task is displayed in Week view		*/
		/*
		- Click next week/previous week icon
		 *Input Data: 
		 *Expected Outcome: 
		- Next week/previous week is displayed correctly
		- Mini calendar is updated also		*/ 
		Utils.pause(500);
		click(event.ELEMENT_NEXT_WEEK);
		Utils.pause(2000);
		waitForAndGetElement(ELEMENT_CALENDAR_PANEL);
		waitForAndGetElement(ELEMENT_EVENT_TASK_ALL_DAY.replace("${event}",task1));
		deleteEventTask(task1);

		click(event.ELEMENT_PREVIOUS_WEEK);
		Utils.pause(2000);
		info("Select previous week");
		waitForAndGetElement(ELEMENT_CALENDAR_PANEL);
		waitForAndGetElement(ELEMENT_EVENT_TASK_ALL_DAY.replace("${event}",event1));
		deleteEventTask(event1);
	}

	/**
	 * Case ID:111896.
	 * Test Case Name: Check displaying added task/event in month view.
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/07/09 16:59:49
	 */
	@Test
	public  void test05_CheckDisplayingAddedTaskeventInMonthView() {
		info("Test 5: Check displaying added task/event in month view");
		String event1 = "Event1118961";
		String task1 = "Task1118961";
		String event2 = "Event1118962";
		String task2 = "Task1118962";
		/*
		- Go to calendar
		- Add new task/event
		 *Expected Outcome: Task/ event are created		*/
		goToCalendarPage();
		click(ELEMENT_BUTTON_WEEK_VIEW);
		waitForAndGetElement(ELEMENT_BUTTON_VIEW_ACTIVE.replace("${view}", "Week"));

		info("add some events and tasks in Meeting");
		event.addQuickEvent(event1, event1,getDate(0,"MM/dd/yyyy"),getDate(0,"MM/dd/yyyy"),true,"John Smith","Meeting");
		task.addQuickTask(task1, task1,getDate(0,"MM/dd/yyyy"),getDate(0,"MM/dd/yyyy"),true,"John Smith","Meeting");
		task.addQuickTask(task2, task2,getDate(31,"MM/dd/yyyy"),getDate(31,"MM/dd/yyyy"),true,"John Smith","Meeting");
		event.addQuickEvent(event2, event2,getDate(31,"MM/dd/yyyy"),getDate(31,"MM/dd/yyyy"),true,"John Smith","Meeting");

		/*
		- Click on Month on the main bar
		 *Input Data: 
		 *Expected Outcome: Event/ task is displayed in Month view		*/
		Utils.pause(1000);
		click(ELEMENT_BUTTON_MONTH_VIEW);
		Utils.pause(2000);
		waitForAndGetElement(ELEMENT_BUTTON_VIEW_ACTIVE.replace("${view}", "Month"));
		waitForAndGetElement(EVENT_MONTH_VIEW.replace("${eventTitle}", event1));
		waitForAndGetElement(EVENT_MONTH_VIEW.replace("${eventTitle}", task1));

		/*
		- Click on next month/previous month icon
		 *Input Data: 
		 *Expected Outcome: 
		- Next month/previous month is displayed correctly
		- Mini calendar is updated also		*/ 
		Utils.pause(500);
		click(event.ELEMENT_NEXT_MONTH);
		Utils.pause(2000);
		waitForAndGetElement(ELEMENT_CALENDAR_PANEL);
		waitForAndGetElement(EVENT_MONTH_VIEW.replace("${eventTitle}",task2));
		waitForAndGetElement(EVENT_MONTH_VIEW.replace("${eventTitle}",event2));
		click(ELEMENT_BUTTON_WEEK_VIEW);
		Utils.pause(2000);
		waitForAndGetElement(ELEMENT_BUTTON_VIEW_ACTIVE.replace("${view}", "Week"));
		deleteEventTask(task2);
		deleteEventTask(event2);

		click(ELEMENT_BUTTON_MONTH_VIEW);
		Utils.pause(2000);
		waitForAndGetElement(ELEMENT_BUTTON_VIEW_ACTIVE.replace("${view}", "Month"));
		click(event.ELEMENT_PREVIOUS_MONTH);
		Utils.pause(2000);
		info("Select previous day");
		waitForAndGetElement(ELEMENT_CALENDAR_PANEL);
		waitForAndGetElement(EVENT_MONTH_VIEW.replace("${eventTitle}",task1));
		waitForAndGetElement(EVENT_MONTH_VIEW.replace("${eventTitle}",event1));
		click(ELEMENT_BUTTON_WEEK_VIEW);
		Utils.pause(2000);
		waitForAndGetElement(ELEMENT_BUTTON_VIEW_ACTIVE.replace("${view}", "Week"));
		deleteEventTask(task1);
		deleteEventTask(event1);
	}

	/**
	 * Case ID:111897.
	 * Test Case Name: Check displaying added task/event in list view.
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/07/09 16:59:49
	 */
	@Test
	public  void test06_CheckDisplayingAddedTaskeventInListView() {
		info("Test 6: Check displaying added task/event in list view");
		String event1 = "Event1118971";
		String task1 = "Task1118971";
		String event2 = "Event1118972";
		String task2 = "Task1118972";
		/*
		- Go to calendar
		- Add new task/event
		 *Expected Outcome: Task/ event are created		*/
		goToCalendarPage();
		click(ELEMENT_BUTTON_WEEK_VIEW);
		waitForAndGetElement(ELEMENT_BUTTON_VIEW_ACTIVE.replace("${view}", "Week"));

		info("add some events and tasks");
		event.addQuickEvent(event1, event1,getDate(0,"MM/dd/yyyy"),getDate(0,"MM/dd/yyyy"),true,"John Smith","Meeting");
		task.addQuickTask(task1, task1,getDate(0,"MM/dd/yyyy"),getDate(0,"MM/dd/yyyy"),true,"John Smith","Meeting");
		task.addQuickTask(task2, task2,getDate(1,"MM/dd/yyyy"),getDate(1,"MM/dd/yyyy"),true,"John Smith","Meeting");
		event.addQuickEvent(event2, event2,getDate(1,"MM/dd/yyyy"),getDate(1,"MM/dd/yyyy"),true,"John Smith","Meeting");

		/*
		- Click on List on the main bar
		 *Input Data: 
		 *Expected Outcome: Event/ task is displayed in List view		*/
		Utils.pause(1000);
		click(ELEMENT_BUTTON_LIST_VIEW);
		Utils.pause(2000);
		waitForAndGetElement(ELEMENT_BUTTON_VIEW_ACTIVE.replace("${view}", "List"));
		waitForAndGetElement(EVENT_LIST_VIEW.replace("${eventTitle}", event1));
		waitForAndGetElement(EVENT_LIST_VIEW.replace("${eventTitle}", task1));

		/*
		- Click on next week/previous day icon
		 *Input Data: 
		 *Expected Outcome: 
		- Next week/previous day is displayed correctly
		- Mini calendar is updated also		*/ 
		Utils.pause(500);
		click(event.ELEMENT_NEXT_LIST);
		Utils.pause(2000);
		waitForAndGetElement(ELEMENT_CALENDAR_PANEL);
		waitForAndGetElement(EVENT_LIST_VIEW.replace("${eventTitle}", event2));
		waitForAndGetElement(EVENT_LIST_VIEW.replace("${eventTitle}", task2));

		click(event.ELEMENT_PREVIOUS_LIST);
		Utils.pause(2000);
		info("Select previous day");
		waitForAndGetElement(ELEMENT_CALENDAR_PANEL);
		waitForAndGetElement(EVENT_LIST_VIEW.replace("${eventTitle}", event1));
		waitForAndGetElement(EVENT_LIST_VIEW.replace("${eventTitle}", task1));

		info("Clear data");
		click(ELEMENT_BUTTON_WEEK_VIEW);
		Utils.pause(2000);
		waitForAndGetElement(ELEMENT_BUTTON_VIEW_ACTIVE.replace("${view}", "Week"));
		deleteEventTask(event1);
		deleteEventTask(task1);
		deleteEventTask(event2);
		deleteEventTask(task2);
	}

	/**
	 * Case ID:111898.
	 * Test Case Name: Check displaying added task/event in work week view.
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/07/09 16:59:49
	 */
	@Test
	public  void test07_CheckDisplayingAddedTaskeventInWorkWeekView() {
		info("Test 7: Check displaying added task/event in work week view");
		String event1 = "Event1118981";
		String task1 = "Task1118981";
		String event2 = "Event1118982";
		String task2 = "Task1118982";
		String eventSun = "EventSun";
		String taskSun = "taskSun";
		String eventSat = "EventSat";
		String taskSat = "taskSat";

		/*
		- Go to calendar
		- Add new task/event
		 *Expected Outcome: Task/ event are created		*/
		goToCalendarPage();
		click(ELEMENT_BUTTON_WEEK_VIEW);
		waitForAndGetElement(ELEMENT_BUTTON_VIEW_ACTIVE.replace("${view}", "Week"));

		info("add some events and tasks for satuday and sunday");
		switch(getDayOfWeek(0)){
		case 2:
			event.addQuickEvent(eventSat, eventSat,getDate(5,"MM/dd/yyyy"),getDate(5,"MM/dd/yyyy"),true,"John Smith","All");
			task.addQuickTask(taskSat, taskSat,getDate(5,"MM/dd/yyyy"),getDate(5,"MM/dd/yyyy"),true,"John Smith","All");
			event.addQuickEvent(eventSun, eventSun,getDate(6,"MM/dd/yyyy"),getDate(6,"MM/dd/yyyy"),true,"John Smith","All");
			task.addQuickTask(taskSun, taskSun,getDate(6,"MM/dd/yyyy"),getDate(6,"MM/dd/yyyy"),true,"John Smith","All");
			break;
		case 3:
			event.addQuickEvent(eventSat, eventSat,getDate(4,"MM/dd/yyyy"),getDate(4,"MM/dd/yyyy"),true,"John Smith","All");
			task.addQuickTask(taskSat, taskSat,getDate(4,"MM/dd/yyyy"),getDate(4,"MM/dd/yyyy"),true,"John Smith","All");
			event.addQuickEvent(eventSun, eventSun,getDate(5,"MM/dd/yyyy"),getDate(5,"MM/dd/yyyy"),true,"John Smith","All");
			task.addQuickTask(taskSun, taskSun,getDate(5,"MM/dd/yyyy"),getDate(5,"MM/dd/yyyy"),true,"John Smith","All");
			break;
		case 4:
			event.addQuickEvent(eventSat, eventSat,getDate(3,"MM/dd/yyyy"),getDate(3,"MM/dd/yyyy"),true,"John Smith","All");
			task.addQuickTask(taskSat, taskSat,getDate(3,"MM/dd/yyyy"),getDate(3,"MM/dd/yyyy"),true,"John Smith","All");
			event.addQuickEvent(eventSun, eventSun,getDate(4,"MM/dd/yyyy"),getDate(4,"MM/dd/yyyy"),true,"John Smith","All");
			task.addQuickTask(taskSun, taskSun,getDate(4,"MM/dd/yyyy"),getDate(4,"MM/dd/yyyy"),true,"John Smith","All");
			break;
		case 5:
			event.addQuickEvent(eventSat, eventSat,getDate(2,"MM/dd/yyyy"),getDate(2,"MM/dd/yyyy"),true,"John Smith","All");
			task.addQuickTask(taskSat, taskSat,getDate(2,"MM/dd/yyyy"),getDate(2,"MM/dd/yyyy"),true,"John Smith","All");
			event.addQuickEvent(eventSun, eventSun,getDate(3,"MM/dd/yyyy"),getDate(3,"MM/dd/yyyy"),true,"John Smith","All");
			task.addQuickTask(taskSun, taskSun,getDate(3,"MM/dd/yyyy"),getDate(3,"MM/dd/yyyy"),true,"John Smith","All");
			break;
		case 6:
			event.addQuickEvent(eventSat, eventSat,getDate(1,"MM/dd/yyyy"),getDate(1,"MM/dd/yyyy"),true,"John Smith","All");
			task.addQuickTask(taskSat, taskSat,getDate(1,"MM/dd/yyyy"),getDate(1,"MM/dd/yyyy"),true,"John Smith","All");
			event.addQuickEvent(eventSun, eventSun,getDate(2,"MM/dd/yyyy"),getDate(2,"MM/dd/yyyy"),true,"John Smith","All");
			task.addQuickTask(taskSun, taskSun,getDate(2,"MM/dd/yyyy"),getDate(2,"MM/dd/yyyy"),true,"John Smith","All");
			break;
		case 7:
			event.addQuickEvent(eventSat, eventSat,getDate(0,"MM/dd/yyyy"),getDate(0,"MM/dd/yyyy"),true,"John Smith","All");
			task.addQuickTask(taskSat, taskSat,getDate(0,"MM/dd/yyyy"),getDate(0,"MM/dd/yyyy"),true,"John Smith","All");
			event.addQuickEvent(eventSun, eventSun,getDate(1,"MM/dd/yyyy"),getDate(1,"MM/dd/yyyy"),true,"John Smith","All");
			task.addQuickTask(taskSun, taskSun,getDate(1,"MM/dd/yyyy"),getDate(1,"MM/dd/yyyy"),true,"John Smith","All");
			break;
		case 1:
			event.addQuickEvent(eventSat, eventSat,getDate(-1,"MM/dd/yyyy"),getDate(-1,"MM/dd/yyyy"),true,"John Smith","All");
			task.addQuickTask(taskSat, taskSat,getDate(-1,"MM/dd/yyyy"),getDate(-1,"MM/dd/yyyy"),true,"John Smith","All");
			event.addQuickEvent(eventSun, eventSun,getDate(0,"MM/dd/yyyy"),getDate(0,"MM/dd/yyyy"),true,"John Smith","All");
			task.addQuickTask(taskSun, taskSun,getDate(0,"MM/dd/yyyy"),getDate(0,"MM/dd/yyyy"),true,"John Smith","All");
			break;
		}

		if(getDayOfWeek(0)!=1 && getDayOfWeek(0)!=7){
			info("add some events and tasks");
			event.addQuickEvent(event1, event1,getDate(0,"MM/dd/yyyy"),getDate(0,"MM/dd/yyyy"),true,"John Smith","All");
			task.addQuickTask(task1, task1,getDate(0,"MM/dd/yyyy"),getDate(0,"MM/dd/yyyy"),true,"John Smith","All");
			event.addQuickEvent(event2, event2,getDate(7,"MM/dd/yyyy"),getDate(7,"MM/dd/yyyy"),true,"John Smith","All");
			task.addQuickTask(task2, task2,getDate(7,"MM/dd/yyyy"),getDate(7,"MM/dd/yyyy"),true,"John Smith","All");
			/*
			- Click on Work Week on the main bar
			 *Input Data: 
			 *Expected Outcome: Event/ task is displayed in Work Week view		*/
			Utils.pause(3000);
			click(ELEMENT_BUTTON_WORK_WEEK_VIEW);
			Utils.pause(2000);
			waitForAndGetElement(ELEMENT_BUTTON_VIEW_ACTIVE.replace("${view}", "Work Week"));
			waitForAndGetElement(EVENT_WORK_WEEK_VIEW.replace("${eventTitle}", event1));
			waitForAndGetElement(EVENT_WORK_WEEK_VIEW.replace("${eventTitle}", task1));
			waitForElementNotPresent(EVENT_WORK_WEEK_VIEW.replace("${eventTitle}", eventSat));
			waitForElementNotPresent(EVENT_WORK_WEEK_VIEW.replace("${eventTitle}", taskSat));
			waitForElementNotPresent(EVENT_WORK_WEEK_VIEW.replace("${eventTitle}", eventSun));
			waitForElementNotPresent(EVENT_WORK_WEEK_VIEW.replace("${eventTitle}", taskSun));
			/*
			- Click next week/previous week icon
			 *Input Data: 
			 *Expected Outcome: 
			- Next week/previous week is displayed correctly
			- Mini calendar is updated also		*/
			Utils.pause(500);
			click(event.ELEMENT_NEXT_WORK_WEEK);
			Utils.pause(2000);
			waitForAndGetElement(ELEMENT_CALENDAR_PANEL);
			waitForAndGetElement(EVENT_WORK_WEEK_VIEW.replace("${eventTitle}", event2));
			waitForAndGetElement(EVENT_WORK_WEEK_VIEW.replace("${eventTitle}", task2));
			waitForElementNotPresent(EVENT_WORK_WEEK_VIEW.replace("${eventTitle}", eventSat));
			waitForElementNotPresent(EVENT_WORK_WEEK_VIEW.replace("${eventTitle}", taskSat));
			waitForElementNotPresent(EVENT_WORK_WEEK_VIEW.replace("${eventTitle}", eventSun));
			waitForElementNotPresent(EVENT_WORK_WEEK_VIEW.replace("${eventTitle}", taskSun));
			deleteEventTask(event2);
			deleteEventTask(task2);

			click(event.ELEMENT_PREVIOUS_WORK_WEEK);
			Utils.pause(2000);
			info("Select previous day");
			waitForAndGetElement(ELEMENT_CALENDAR_PANEL);
			waitForAndGetElement(EVENT_WORK_WEEK_VIEW.replace("${eventTitle}", event1));
			waitForAndGetElement(EVENT_WORK_WEEK_VIEW.replace("${eventTitle}", task1));
			waitForElementNotPresent(EVENT_WORK_WEEK_VIEW.replace("${eventTitle}", eventSat));
			waitForElementNotPresent(EVENT_WORK_WEEK_VIEW.replace("${eventTitle}", taskSat));
			waitForElementNotPresent(EVENT_WORK_WEEK_VIEW.replace("${eventTitle}", eventSun));
			waitForElementNotPresent(EVENT_WORK_WEEK_VIEW.replace("${eventTitle}", taskSun));
			click(ELEMENT_BUTTON_WEEK_VIEW);
			Utils.pause(2000);
			waitForAndGetElement(ELEMENT_BUTTON_VIEW_ACTIVE.replace("${view}", "Week"));
			deleteEventTask(event1);
			deleteEventTask(task1);
			deleteEventTask(eventSat);
			deleteEventTask(taskSat);
			deleteEventTask(eventSun);
			deleteEventTask(taskSun);
		}
		else{
			info("add some events and tasks");
			event.addQuickEvent(event1, event1,getDate(2,"MM/dd/yyyy"),getDate(3,"MM/dd/yyyy"),true,"John Smith","All");
			task.addQuickTask(task1, task1,getDate(2,"MM/dd/yyyy"),getDate(3,"MM/dd/yyyy"),true,"John Smith","All");
			event.addQuickEvent(event2, event2,getDate(10,"MM/dd/yyyy"),getDate(10,"MM/dd/yyyy"),true,"John Smith","All");
			task.addQuickTask(task2, task2,getDate(10,"MM/dd/yyyy"),getDate(10,"MM/dd/yyyy"),true,"John Smith","All");

			/*
			- Click on Work Week on the main bar
			 *Input Data: 
			 *Expected Outcome: Event/ task is displayed in Work Week view		*/
			Utils.pause(3000);
			click(ELEMENT_BUTTON_WORK_WEEK_VIEW);
			Utils.pause(2000);
			waitForAndGetElement(ELEMENT_BUTTON_VIEW_ACTIVE.replace("${view}", "Work Week"));
			click(event.ELEMENT_NEXT_WORK_WEEK);
			Utils.pause(2000);
			waitForAndGetElement(ELEMENT_CALENDAR_PANEL);
			waitForAndGetElement(EVENT_WORK_WEEK_VIEW.replace("${eventTitle}", event1));
			waitForAndGetElement(EVENT_WORK_WEEK_VIEW.replace("${eventTitle}", task1));
			waitForElementNotPresent(EVENT_WORK_WEEK_VIEW.replace("${eventTitle}", eventSat));
			waitForElementNotPresent(EVENT_WORK_WEEK_VIEW.replace("${eventTitle}", taskSat));
			waitForElementNotPresent(EVENT_WORK_WEEK_VIEW.replace("${eventTitle}", eventSun));
			waitForElementNotPresent(EVENT_WORK_WEEK_VIEW.replace("${eventTitle}", taskSun));

			/*
			- Click next week/previous week icon
			 *Input Data: 
			 *Expected Outcome: 
			- Next week/previous week is displayed correctly
			- Mini calendar is updated also		*/
			Utils.pause(500);
			click(event.ELEMENT_NEXT_WORK_WEEK);
			Utils.pause(2000);
			waitForAndGetElement(ELEMENT_CALENDAR_PANEL);
			waitForAndGetElement(EVENT_WORK_WEEK_VIEW.replace("${eventTitle}", event2));
			waitForAndGetElement(EVENT_WORK_WEEK_VIEW.replace("${eventTitle}", task2));
			waitForElementNotPresent(EVENT_WORK_WEEK_VIEW.replace("${eventTitle}", eventSat));
			waitForElementNotPresent(EVENT_WORK_WEEK_VIEW.replace("${eventTitle}", taskSat));
			waitForElementNotPresent(EVENT_WORK_WEEK_VIEW.replace("${eventTitle}", eventSun));
			waitForElementNotPresent(EVENT_WORK_WEEK_VIEW.replace("${eventTitle}", taskSun));
			deleteEventTask(event2);
			deleteEventTask(task2);

			click(event.ELEMENT_PREVIOUS_WORK_WEEK);
			Utils.pause(2000);
			info("Select previous day");
			waitForAndGetElement(ELEMENT_CALENDAR_PANEL);
			waitForAndGetElement(EVENT_WORK_WEEK_VIEW.replace("${eventTitle}", event1));
			waitForAndGetElement(EVENT_WORK_WEEK_VIEW.replace("${eventTitle}", task1));
			waitForElementNotPresent(EVENT_WORK_WEEK_VIEW.replace("${eventTitle}", eventSat));
			waitForElementNotPresent(EVENT_WORK_WEEK_VIEW.replace("${eventTitle}", taskSat));
			waitForElementNotPresent(EVENT_WORK_WEEK_VIEW.replace("${eventTitle}", eventSun));
			waitForElementNotPresent(EVENT_WORK_WEEK_VIEW.replace("${eventTitle}", taskSun));
			waitForAndGetElement(ELEMENT_BUTTON_VIEW_ACTIVE.replace("${view}", "Week"));
			deleteEventTask(event1);
			deleteEventTask(task1);
			deleteEventTask(eventSat);
			deleteEventTask(taskSat);
			deleteEventTask(eventSun);
			deleteEventTask(taskSun);
		}
	}

	/**
	 * Case ID:111899.
	 * Test Case Name: Check Today view.
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/07/09 16:59:49
	 */
	@Test
	public  void test08_CheckTodayView() {
		info("Test 8: Check Today view");
		String event1 = "Event1118991";
		String task1 = "Task1118991";
		/*
		- Go to calendar
		- Add new task/event
		 *Expected Outcome: Task/ event are created		*/
		goToCalendarPage();
		Utils.pause(2000);
		click(ELEMENT_BUTTON_WEEK_VIEW);
		Utils.pause(2000);
		waitForAndGetElement(ELEMENT_BUTTON_VIEW_ACTIVE.replace("${view}", "Week"));

		info("add some events and tasks");
		event.addQuickEvent(event1, event1,getDate(0,"MM/dd/yyyy"),getDate(0,"MM/dd/yyyy"),true,"John Smith","All");
		task.addQuickTask(task1, task1,getDate(0,"MM/dd/yyyy"),getDate(0,"MM/dd/yyyy"),true,"John Smith","All");

		/*
		- Select view (day/week/month/list/work week) which has not the current day
		 *Input Data: 
		 *Expected Outcome: 
		- Calendar is displayed with the selected view(day/week/month/list/work week)		*/
		info("view day view");
		click(ELEMENT_BUTTON_DAY_VIEW);
		Utils.pause(2000);
		waitForAndGetElement(ELEMENT_BUTTON_VIEW_ACTIVE.replace("${view}", "Day"));
		waitForAndGetElement(ELEMENT_EVENT_TASK_ALL_DAY_PLF41_DAY_VIEW.replace("${event}", event1));
		waitForAndGetElement(ELEMENT_EVENT_TASK_ALL_DAY_PLF41_DAY_VIEW.replace("${event}", task1));
		click(event.ELEMENT_NEXT_DAY);
		Utils.pause(2000);
		waitForElementNotPresent(ELEMENT_EVENT_TASK_ALL_DAY_PLF41_DAY_VIEW.replace("${event}", event1));
		waitForElementNotPresent(ELEMENT_EVENT_TASK_ALL_DAY_PLF41_DAY_VIEW.replace("${event}", task1));

		/*
		- Click on Today on the main bar
		 *Input Data: 
		 *Expected Outcome: 
		- Go to the current view which has the current day (today view). Mini calendar is updated to the week has current day		*/ 
		click(ELEMENT_LINK_TODAY_VIEW);
		Utils.pause(2000);
		waitForAndGetElement(ELEMENT_EVENT_TASK_ALL_DAY_PLF41_DAY_VIEW.replace("${event}", event1));
		waitForAndGetElement(ELEMENT_EVENT_TASK_ALL_DAY_PLF41_DAY_VIEW.replace("${event}", task1));

		/*
		- Select view (day/week/month/list/work week) which has not the current day
		 *Input Data: 
		 *Expected Outcome: 
		- Calendar is displayed with the selected view(day/week/month/list/work week)		*/
		info("view week view");
		click(ELEMENT_BUTTON_WEEK_VIEW);
		Utils.pause(2000);
		waitForAndGetElement(ELEMENT_BUTTON_VIEW_ACTIVE.replace("${view}", "Week"));
		waitForAndGetElement(ELEMENT_EVENT_TASK_ALL_DAY.replace("${event}",event1));
		waitForAndGetElement(ELEMENT_EVENT_TASK_ALL_DAY.replace("${event}",task1));
		click(event.ELEMENT_NEXT_WEEK);
		Utils.pause(2000);
		waitForElementNotPresent(ELEMENT_EVENT_TASK_ALL_DAY.replace("${event}",event1));
		waitForElementNotPresent(ELEMENT_EVENT_TASK_ALL_DAY.replace("${event}",task1));

		/*
		- Click on Today on the main bar
		 *Input Data: 
		 *Expected Outcome: 
		- Go to the current view which has the current day (today view). Mini calendar is updated to the week has current day		*/ 
		click(ELEMENT_LINK_TODAY_VIEW);
		Utils.pause(2000);
		waitForAndGetElement(ELEMENT_EVENT_TASK_ALL_DAY.replace("${event}",event1));
		waitForAndGetElement(ELEMENT_EVENT_TASK_ALL_DAY.replace("${event}",task1));

		/*
		- Select view (day/week/month/list/work week) which has not the current day
		 *Input Data: 
		 *Expected Outcome: 
		- Calendar is displayed with the selected view(day/week/month/list/work week)		*/
		info("view month");
		click(ELEMENT_BUTTON_MONTH_VIEW);
		Utils.pause(2000);
		waitForAndGetElement(ELEMENT_BUTTON_VIEW_ACTIVE.replace("${view}", "Month"));
		waitForAndGetElement(EVENT_MONTH_VIEW.replace("${eventTitle}", event1));
		waitForAndGetElement(EVENT_MONTH_VIEW.replace("${eventTitle}", task1));	
		click(event.ELEMENT_NEXT_MONTH);
		Utils.pause(2000);
		waitForElementNotPresent(EVENT_MONTH_VIEW.replace("${eventTitle}",event1));
		waitForElementNotPresent(EVENT_MONTH_VIEW.replace("${eventTitle}",task1));

		/*
		- Click on Today on the main bar
		 *Input Data: 
		 *Expected Outcome: 
		- Go to the current view which has the current day (today view). Mini calendar is updated to the week has current day		*/ 
		click(ELEMENT_LINK_TODAY_VIEW);
		Utils.pause(2000);
		waitForAndGetElement(EVENT_MONTH_VIEW.replace("${eventTitle}", event1));
		waitForAndGetElement(EVENT_MONTH_VIEW.replace("${eventTitle}", task1));	

		/*
		- Select view (day/week/month/list/work week) which has not the current day
		 *Input Data: 
		 *Expected Outcome: 
		- Calendar is displayed with the selected view(day/week/month/list/work week)		*/
		info("view list");
		click(ELEMENT_BUTTON_LIST_VIEW);
		Utils.pause(2000);
		waitForAndGetElement(ELEMENT_BUTTON_VIEW_ACTIVE.replace("${view}", "List"));
		waitForAndGetElement(EVENT_LIST_VIEW.replace("${eventTitle}", event1));
		waitForAndGetElement(EVENT_LIST_VIEW.replace("${eventTitle}", task1));
		click(event.ELEMENT_NEXT_LIST);
		Utils.pause(2000);
		waitForElementNotPresent(EVENT_LIST_VIEW.replace("${eventTitle}",event1));
		waitForElementNotPresent(EVENT_LIST_VIEW.replace("${eventTitle}",task1));

		/*
		- Click on Today on the main bar
		 *Input Data: 
		 *Expected Outcome: 
		- Go to the current view which has the current day (today view). Mini calendar is updated to the week has current day		*/ 
		click(ELEMENT_LINK_TODAY_VIEW);
		Utils.pause(2000);
		waitForAndGetElement(EVENT_LIST_VIEW.replace("${eventTitle}", event1));
		waitForAndGetElement(EVENT_LIST_VIEW.replace("${eventTitle}", task1));	

		/*
		- Select view (day/week/month/list/work week) which has not the current day
		 *Input Data: 
		 *Expected Outcome: 
		- Calendar is displayed with the selected view(day/week/month/list/work week)		*/
		info("view work week");
		click(ELEMENT_BUTTON_WORK_WEEK_VIEW);
		Utils.pause(2000);
		waitForAndGetElement(ELEMENT_BUTTON_VIEW_ACTIVE.replace("${view}", "Work Week"));
		waitForAndGetElement(EVENT_WORK_WEEK_VIEW.replace("${eventTitle}", event1));
		waitForAndGetElement(EVENT_WORK_WEEK_VIEW.replace("${eventTitle}", task1));
		click(event.ELEMENT_NEXT_WORK_WEEK);
		Utils.pause(2000);
		waitForElementNotPresent(EVENT_WORK_WEEK_VIEW.replace("${eventTitle}",event1));
		waitForElementNotPresent(EVENT_WORK_WEEK_VIEW.replace("${eventTitle}",task1));

		/*
		- Click on Today on the main bar
		 *Input Data: 
		 *Expected Outcome: 
		- Go to the current view which has the current day (today view). Mini calendar is updated to the week has current day		*/ 
		click(ELEMENT_LINK_TODAY_VIEW);
		Utils.pause(2000);
		waitForAndGetElement(EVENT_WORK_WEEK_VIEW.replace("${eventTitle}", event1));
		waitForAndGetElement(EVENT_WORK_WEEK_VIEW.replace("${eventTitle}", task1));	

		info("Clear data");
		Utils.pause(500);
		deleteEventTask(event1);
		deleteEventTask(task1);
	}
}
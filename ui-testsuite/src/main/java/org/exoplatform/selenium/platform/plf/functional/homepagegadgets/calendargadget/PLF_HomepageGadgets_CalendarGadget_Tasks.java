package org.exoplatform.selenium.platform.plf.functional.homepagegadgets.calendargadget;

import static org.exoplatform.selenium.TestLogger.info;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.calendar.CalendarBase;
import org.exoplatform.selenium.platform.calendar.Task;
import org.exoplatform.selenium.platform.calendar.Event;
import org.exoplatform.selenium.Utils;
import org.testng.annotations.*;

/**
 * @author chinhdtt
 * @date 21 Feb 2014
 */
public class PLF_HomepageGadgets_CalendarGadget_Tasks extends CalendarBase{
	ManageAccount acc; 
	NavigationToolbar nav; 
	Event event; 
	Task task;

	@BeforeMethod
	public void beforeMethods(){	
		initSeleniumTest();
		driver.get(baseUrl);
		acc = new ManageAccount(driver, this.plfVersion);
		nav = new NavigationToolbar(driver, this.plfVersion);	
		task = new Task(driver, this.plfVersion);
		event = new Event(driver, this.plfVersion);
		acc.signIn(DATA_USER1, DATA_PASS);		
		goToCalendarPage();
		setTimezoneForCalendar("(GMT +07:00) Asia/Ho_Chi_Minh");
		
	}

	@AfterMethod
	public void afterMethods() {
		info("Logout portal");
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	/**
	 * Case ID:69100.
	 * Test Case Name: Display Task part in Gadget Calendar.
	 * Pre-Condition: tasks are added to calendar
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/02/24 18:03:16
	 */
	@Test
	public  void test01_DisplayTaskPartInGadgetCalendar() {
		info("Test 1: Display Task part in Gadget Calendar");
		String calendar = "Calendar 69100";
		String task1 = "Task 69100";

		/*
		- Connect to Intranet
		- Add a task to Calendar with the current day
		- Back to the Homepage
		 *Input Data: 
		 *Expected Outcome: 
		- The Gadget calendar is displayed
		- Task is displayed in thelower part of the calendar gadget		*/ 
		addCalendar(calendar, calendar,null);
		task.addQuickTask(task1, task1, getDate(0,"MM/dd/yyyy"),getDate(0,"MM/dd/yyyy"),false, calendar);

		nav.goToHomePage();
		waitForAndGetElement(ELEMENT_CALENDAR_GADGET_TASK_LABEL);
		waitForAndGetElement(ELEMENT_CALENDAR_GADGET_TASK_NORMAL.replace("${task}",task1));

		//Delete data test
		info("Delete data test");
		goToCalendarPage();
		deleteCalendar(calendar,true);
	}

	/**
	 * Case ID:69175.
	 * Test Case Name: Display many tasks in Calendar Gadget.
	 * Pre-Condition: Add many tasks in calendars application
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/02/24 18:03:16
	 */
	@Test
	public  void test02_DisplayManyTasksInCalendarGadget() {
		info("Test 2: Display many tasks in Calendar Gadget");
		String calendar = "Calendar 69175";
		String task1 = "Task 69175";
		String task2 = "Task 69175";
		String task3 = "Task 69175";
		/*
		- Connect to Intranet
		- In Calendar application, add many tasks in Calendars
		 *Input Data: 
		 *Expected Outcome: Tasks are added		*/
		
		addCalendar(calendar, calendar,null);
		task.addQuickTask(task1, task1, getDate(0,"MM/dd/yyyy"),getDate(0,"MM/dd/yyyy"),true, calendar);
		Utils.pause(500);
		task.addQuickTask(task2, task2, getDate(0,"MM/dd/yyyy"),getDate(0,"MM/dd/yyyy"),false, calendar);
		Utils.pause(500);
		task.addQuickTask(task3, task3, getDate(0,"MM/dd/yyyy"),getDate(0,"MM/dd/yyyy"),false, calendar);

		/*
		- Go to the Homepage
		 *Input Data: 
		 *Expected Outcome: The tasks are displayed without a scroll bar		*/ 
		nav.goToHomePage();
		waitForAndGetElement(ELEMENT_CALENDAR_GADGET_TASK_LABEL);
		waitForAndGetElement(ELEMENT_CALENDAR_GADGET_TASK_NORMAL.replace("${task}",task1));
		waitForAndGetElement(ELEMENT_CALENDAR_GADGET_TASK_NORMAL.replace("${task}",task2));
		waitForAndGetElement(ELEMENT_CALENDAR_GADGET_TASK_NORMAL.replace("${task}",task3));

		//Delete data test
		info("Delete data test");
		goToCalendarPage();
		deleteCalendar(calendar,true);
	}

	/**
	 * Case ID:69187.
	 * Test Case Name: Display a task in "Not completed" status.
	 * Pre-Condition: task added is with status "Need Action"
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/02/24 18:03:16
	 */
	@Test
	public  void test03_DisplayATaskInNotCompletedStatus() {
		info("Test 3: Display a task in Not completed status");
		String calendar = "Calendar 69187";
		String task1 = "Task 69187";

		/*
		- Connect to Intranet
		- In calendar application, add a task in current day
		 *Input Data: 
		 *Expected Outcome: Task is added with status "Need action"		*/
		addCalendar(calendar, calendar,null);
		task.addQuickTask(task1, task1, getDate(0,"MM/dd/yyyy"),getDate(0,"MM/dd/yyyy"),false, calendar);

		/*Back to the Home page
		 *Input Data: 
		 *Expected Outcome: Task is displayed in Normal style		*/ 
		nav.goToHomePage();
		waitForAndGetElement(ELEMENT_CALENDAR_GADGET_TASK_LABEL);
		waitForAndGetElement(ELEMENT_CALENDAR_GADGET_TASK_NORMAL.replace("${task}",task1));

		//Delete data test
		info("Delete data test");
		goToCalendarPage();
		deleteCalendar(calendar,true);
	}

	/**
	 * Case ID:69188.
	 * Test Case Name: Display a task in "Completed" status.
	 * Pre-Condition: task added is with status "Completed"
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/02/24 18:03:16
	 * Bug: Not show completed task in calendar gadget (CAL-880)
	 */
	@Test(groups="error")
	public  void test04_DisplayATaskInCompletedStatus() {
		info("Test 4: Display a task in Completed status");
		String calendar = "Calendar 69188";
		String task1 = "Task 69188";

		/*
		- Connect to Intranet
		- In calendar application, add a task in current day
		- Change the status of the task to "Completed"
		 *Input Data: 
		 *Expected Outcome: Task is added with status "Completed"		*/
		addCalendar(calendar, calendar,null);
		task.goToAddTaskFromActionBar();
		click(task.ELEMENT_BUTTON_TASK_MORE_DETAILS);
		task.inputDataTabDetailTask(task1, task1,getDate(0,"MM/dd/yyyy"),getDate(0,"MM/dd/yyyy"), false, null, calendar, null, "Completed");
		click(task.ELEMENT_BUTTON_TASK_SAVE_DETAILS);

		/*Back to the Home page
		 *Input Data: 
		 *Expected Outcome: Task is displayed in the gadget calendar and crossed		*/ 
		nav.goToHomePage();
		waitForAndGetElement(ELEMENT_CALENDAR_GADGET_TASK_LABEL);
		waitForAndGetElement(ELEMENT_CALENDAR_GADGET_TASK_COMPLETED.replace("${task}",task1));

		//Delete data test
		info("Delete data test");
		goToCalendarPage();
		deleteCalendar(calendar,true);
	}

	/**
	 * Case ID:69189.
	 * Test Case Name: Display a task in "Late and not Completed" status.
	 * Pre-Condition: task added is with status "Need action" in a past day
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/02/24 18:03:16
	 */
	@Test
	public  void test05_DisplayATaskInLateAndNotCompletedStatus() {
		info("Test 5: Display a task in Late and not Completed status");
		String calendar = "Calendar 69189";
		String task1 = "Task 69189";

		/*
		- Connect to Intranet
		- In calendar application, add a task in previous day
		 *Input Data: 
		 *Expected Outcome: Task is added in the previous day with status "Need action"		*/
		addCalendar(calendar, calendar,null);
		task.addQuickTask(task1, task1, getDate(-1,"MM/dd/yyyy"),getDate(-1,"MM/dd/yyyy"),false, calendar);

		/*Back to the Home page
		 *Input Data: 
		 *Expected Outcome: Task is displayed in the gadget in Italic		*/
		nav.goToHomePage();
		waitForAndGetElement(ELEMENT_CALENDAR_GADGET_TODAY_LABEL);
		waitForElementNotPresent(ELEMENT_CALENDAR_GADGET_TASK_NORMAL.replace("${task}",task1));

		/*From the calendar gadget, click on the left arrow in the header to display "YESTERDAY" day
		 *Input Data: 
		 *Expected Outcome: The task is displayed in its planned day in italic		*/ 
		click(ELEMENT_CALENDAR_GADGET_PREVIOUSDAY_ARROW);
		waitForAndGetElement(ELEMENT_CALENDAR_GADGET_YESTERDAY_LABEL); 
		waitForAndGetElement(ELEMENT_CALENDAR_GADGET_TASK_LABEL);
		waitForAndGetElement(ELEMENT_CALENDAR_GADGET_TASK_NORMAL.replace("${task}",task1));

		//Delete data test
		info("Delete data test");
		goToCalendarPage();
		deleteCalendar(calendar,true);
	}

	/**
	 * Case ID:69190.
	 * Test Case Name: Display a task in "Late and Completed" status.
	 * Pre-Condition: task added is with status "Completed" in a past day
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/02/24 18:03:16
	 * Bug: Not show completed task in calendar gadget (CAL-880)
	 */
	@Test(groups="error")
	public  void test06_DisplayATaskInLateAndCompletedStatus() {
		info("Test 6: Display a task in Late and Completed status");
		String calendar = "Calendar 69190";
		String task1 = "Task 69190";
		/*
		- Connect to Intranet
		- In calendar application, add a task in previous day
		- Change the status of the task to "Completed"
		 *Input Data: 
		 *Expected Outcome: Task is added in the previous day with status "Completed"		*/
		addCalendar(calendar, calendar,null);
		task.goToAddTaskFromActionBar();
		click(task.ELEMENT_BUTTON_TASK_MORE_DETAILS);
		task.inputDataTabDetailTask(task1, task1, getDate(-1,"MM/dd/yyyy"),getDate(-1,"MM/dd/yyyy"), false, null, calendar,null, "Completed");
		click(task.ELEMENT_BUTTON_TASK_SAVE_DETAILS);

		/*Back to the Home page
		 *Input Data: 
		 *Expected Outcome: Task is NOT displayed in TODAY day		*/
		nav.goToHomePage();
		waitForAndGetElement(ELEMENT_CALENDAR_GADGET_TODAY_LABEL);
		waitForElementNotPresent(ELEMENT_CALENDAR_GADGET_TASK_COMPLETED.replace("${task}",task1));

		/*From the calendar gadget, click on the left arrow in the header to display "YESTERDAY" day
		 *Input Data: 
		 *Expected Outcome: The task is displayed in its planned day and strikethrough		*/ 
		click(ELEMENT_CALENDAR_GADGET_PREVIOUSDAY_ARROW);
		waitForAndGetElement(ELEMENT_CALENDAR_GADGET_YESTERDAY_LABEL); 
		waitForAndGetElement(ELEMENT_CALENDAR_GADGET_TASK_COMPLETED.replace("${task}",task1));

		//Delete data test
		info("Delete data test");
		goToCalendarPage();
		deleteCalendar(calendar,true);
	}

	/**
	 * Case ID:69191.
	 * Test Case Name: Verify the order of displayed tasks.
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/02/24 18:03:16
	 */
	@Test
	public  void test07_VerifyTheOrderOfDisplayedTasks() {
		info("Test 7: Verify the order of displayed tasks");
		String calendar = "Calendar 69191";
		String task1 = "Task 69191";
		String task2 = "Task 69191";
		/*
		- Connect to Intranet
		- In calendar application, add:* Tasks in the current day in different time
		 *Input Data: 
		 *Expected Outcome: Tasks are added in calendar		*/
		addCalendar(calendar, calendar,null);
		task.addQuickTask(task1, task1, getDate(0,"MM/dd/yyyy"),getDate(0,"MM/dd/yyyy"),true, calendar);
		Utils.pause(1000);
		task.addQuickTask(task2, task2, getDate(0,"MM/dd/yyyy"),getDate(0,"MM/dd/yyyy"),false, calendar);
		/*
		- Go to Homepage
		 *Input Data: 
		 *Expected Outcome: The oldest tasks are displayed at the bottom		*/ 
		nav.goToHomePage();
		waitForAndGetElement(ELEMENT_CALENDAR_GADGET_TASK_LABEL);
		waitForAndGetElement(ELEMENT_CALENDAR_GADGET_TODAY_LABEL);
		waitForAndGetElement(ELEMENT_CALENDAR_GADGET_TASK_INDEX.replace("${index}","1").replace("${task}",task1));
		waitForAndGetElement(ELEMENT_CALENDAR_GADGET_TASK_INDEX.replace("${index}","2").replace("${task}",task2));

		//Delete data test
		info("Delete data test");
		goToCalendarPage();
		deleteCalendar(calendar,true);
	}

	/**
	 * Case ID:69192.
	 * Test Case Name: Display a task with a long name in Calendar gadget.
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/02/24 18:03:16
	 * 
	 * Pending this case because can't auto check " The title is displayed in two lines maximum And the name of the task istruncated"
	 */
	@Test (groups = "pending")
	public  void test08_DisplayATaskWithALongNameInCalendarGadget() {
		info("Test 8: Display a task with a long name in Calendar gadget");
		String calendar = "Calendar 69192";
		String task1 = "Task 69192 test a long string of task name";
		/*
		- Connect to Intranet
		- In Calendar application, add a task with a long name
		 *Input Data: 
		 *Expected Outcome: The task is added		*/
		addCalendar(calendar, calendar,null);
		task.addQuickTask(task1, task1, getDate(0,"MM/dd/yyyy"),getDate(0,"MM/dd/yyyy"),true, calendar);

		/*
		- Go to the Homepage
		 *Input Data: 
		 *Expected Outcome: 
		- The title is displayed in two lines maximum
		- The name of the task istruncated		*/ 
		nav.goToHomePage();
		waitForAndGetElement(ELEMENT_CALENDAR_GADGET_TASK_LABEL);
		waitForAndGetElement(ELEMENT_CALENDAR_GADGET_TASK_NORMAL.replace("${task}",task1));

		//Delete data test
		info("Delete data test");
		goToCalendarPage();
		deleteCalendar(calendar,true);
	}

	/**
	 * Case ID:69193.
	 * Test Case Name: Open the Calendar application on the task's details from Calendar Gadget.
	 * Pre-Condition: a task is displayed in the calendar gadget
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/02/24 18:03:16
	 */
	@Test
	public  void test09_OpenTheCalendarApplicationOnTheTasksDetailsFromCalendarGadget() {
		info("Test 9: Open the Calendar application on the task's details from Calendar Gadget");
		String calendar = "Calendar 69191";
		String task1 = "Task 69191";

		//Pre-Condition
		addCalendar(calendar, calendar,null);
		task.addQuickTask(task1, task1, getDate(0,"MM/dd/yyyy"),getDate(0,"MM/dd/yyyy"),false, calendar);

		/*
		- Connect to Intranet
		- From the Calendar gadget, click on an task name label
		 *Input Data: 
		 *Expected Outcome: The calendar application is opened in the task's details screen		*/ 
		nav.goToHomePage();
		waitForAndGetElement(ELEMENT_CALENDAR_GADGET_TASK_LABEL);
		waitForAndGetElement(ELEMENT_CALENDAR_GADGET_TASK_NORMAL.replace("${task}",task1));
		click(ELEMENT_CALENDAR_GADGET_TASK_NORMAL.replace("${task}",task1));
		waitForAndGetElement(task.ELEMENT_PREVIEW_TASK_POPUP);
		waitForAndGetElement(task.ELEMENT_TITLE_TASK_POPUP.replace("${task}",task1));

		//Delete data test
		info("Delete data test");
		goToCalendarPage();
		deleteCalendar(calendar,true);
	}

	/**
	 * Case ID:69283.
	 * Test Case Name: Display the Calendar Gadget only with Tasks panel.
	 * Pre-Condition: No events are performed in the calendar
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/02/24 18:03:16
	 */
	@Test
	public  void test10_DisplayTheCalendarGadgetOnlyWithTasksPanel() {
		info("Test 10 Display the Calendar Gadget only with Tasks panel");
		String calendar = "Calendar 69283";
		String task1 = "Task 69283";
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();

		/*
		- Connect to Intranet
		 *Input Data: 
		 *Expected Outcome: 
		- The Gadget Calendar is displayed
		- In the Header "TODAY: DD/MM/YY"
		- A small sentence in the gadget is displayed: "Nothing planned" in the events panel
		- the panel "TASKS" is displayed		*/ 
		addCalendar(calendar, calendar,null);
		task.addQuickTask(task1, task1, getDate(0,"MM/dd/yyyy"),getDate(0,"MM/dd/yyyy"),true, calendar);
		info("Expected Outcome");
		nav.goToHomePage();
		waitForAndGetElement(ELEMENT_CALENDAR_GADGET_TASK_LABEL);
		waitForAndGetElement(ELEMENT_CALENDAR_GADGET_TODAY_LABEL).getText().equalsIgnoreCase(dateFormat.format(date));
		waitForAndGetElement(ELEMENT_CALENDAR_GADGET_EMPTY);
		waitForAndGetElement(ELEMENT_CALENDAR_GADGET_TASK_NORMAL.replace("${task}",task1));

		//Delete data test
		info("Delete data test");
		goToCalendarPage();
		deleteCalendar(calendar,true);
	}

	/**
	 * Case ID:71620.
	 * Test Case Name: Display "Late and Not completed" task in the top of the list.
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/02/24 18:03:16
	 */
	@Test
	public  void test11_DisplayLateAndNotCompletedTaskInTheTopOfTheList() {
		info("Test 11 Display Late and Not completed task in the top of the list");
		String calendar = "Calendar 71620";
		String task1 = "Task 71620";
		/*
		- Connect to Intranet
		- From calendar application, add task in the previous day
		 *Input Data: 
		 *Expected Outcome: 
		- The task is added with the task "Need action"		*/
		addCalendar(calendar, calendar,null);
		task.addQuickTask(task1, task1, getDate(-1,"MM/dd/yyyy"),getDate(-1,"MM/dd/yyyy"),true, calendar);

		/*
		- Back to the Homepage
		 *Input Data: 
		 *Expected Outcome: 
		- The Task is added in the top of the list		*/ 
		nav.goToHomePage();
		waitForAndGetElement(ELEMENT_CALENDAR_GADGET_TASK_LABEL);
		click(ELEMENT_CALENDAR_GADGET_PREVIOUSDAY_ARROW);
		waitForAndGetElement(ELEMENT_CALENDAR_GADGET_YESTERDAY_LABEL); 
		waitForAndGetElement(ELEMENT_CALENDAR_GADGET_TASK_INDEX.replace("${index}","1").replace("${task}",task1));

		//Delete data test
		info("Delete data test");
		goToCalendarPage();
		deleteCalendar(calendar,true);
	}
}
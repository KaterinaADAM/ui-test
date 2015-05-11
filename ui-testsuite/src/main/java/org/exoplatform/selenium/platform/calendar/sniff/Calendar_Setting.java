package org.exoplatform.selenium.platform.calendar.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.platform.HomePagePlatform;
import org.exoplatform.selenium.platform.ManageLogInOut;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.calendar.EventManagement;
import org.exoplatform.selenium.platform.calendar.TaskManagement;
import org.exoplatform.selenium.platform.calendar.CalendarHomePage;
import org.exoplatform.selenium.platform.calendar.CalendarManagement;
import org.exoplatform.selenium.platform.calendar.CalendarHomePage.selectDayOption;
import org.exoplatform.selenium.platform.calendar.CalendarHomePage.selectViewOption;
import org.exoplatform.selenium.platform.calendar.CalendarManagement.menuOfMainCalendar;
import org.exoplatform.selenium.platform.objectdatabase.common.TextBoxDatabase;
import org.exoplatform.selenium.platform.objectdatabase.user.UserDatabase;
import org.testng.annotations.*;

public class Calendar_Setting extends PlatformBase {
	HomePagePlatform hp;
	ManageLogInOut magAc;
	TextBoxDatabase txData;
	CalendarHomePage cHome;
	EventManagement event;
	CalendarManagement cMang;
	UserDatabase userData;
	TaskManagement task;
	String fullName;
	ManageAlert alert;

	@BeforeClass
	public void setUpBeforeTest() throws Exception{
		initSeleniumTest();
		getDefaultUserPass(userDataFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		driver.get(baseUrl);
		alert = new ManageAlert(driver);
		magAc = new ManageLogInOut(driver);
		hp = new HomePagePlatform(driver);
		cHome= new CalendarHomePage(driver);
		event= new EventManagement(driver);
		task= new TaskManagement(driver);
		cMang = new CalendarManagement(driver);
		txData = new TextBoxDatabase();
		userData = new UserDatabase();
		magAc.signIn(DATA_USER1, DATA_PASS);
		userData.setUserData(userDataFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		txData.setContentData(texboxFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlContent);
		fullName = userData.fullName.get(0);
	}

	@AfterClass
	public void afterTest(){
		magAc.signOut();
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	/**
	 * Case ID:115601.
	 * Test Case Name: Cancel settings form.
	 * Pre-Condition: 
	 * Post-Condition: 
	 */
	@Test
	public  void test01_CancelSettingsForm() {
		info("Test 1: Cancel settings form");
		/*Step Number: 1
		 *Step Name: Step 1: Show calendar setting form
		 *Step Description: 
			Click on Calendar Settings icon
		 *Input Data: 

		 *Expected Outcome: 
			Calendar Settings pop up appears.*/
		hp.goToCalendarPage();
		waitForAndGetElement(cHome.ELEMENT_CALENDAR_ACTIVE_VIEW.replace("$view", "Week"));
		cMang.goToMenuFromMainCalendar(menuOfMainCalendar.CALSETTING);

		/*Step number: 2
		 *Step Name: Step 2: Choose default view type
		 *Step Description: 
			- Change something in settings form
			- Click Cancel
		 *Input Data: 

		 *Expected Outcome: 
			- The changes are not saved*/ 
		cMang.changeSettingCalendar("Month",null,null,null,null,null,null);
		cMang.cancelSetting();
		hp.goToCalendarPage();
		waitForAndGetElement(cHome.ELEMENT_CALENDAR_ACTIVE_VIEW.replace("$view", "Week"));
	}

	/**
	 * Case ID:115602.
	 * Test Case Name: Setup a default View type to show calendar.
	 * Pre-Condition: 
	 * Post-Condition: 
	 */
	@Test
	public  void test02_SetupADefaultViewTypeToShowCalendar() {
		info("Test 2: Setup a default View type to show calendar");
		/*Step Number: 1
		 *Step Name: Step 1: Show calendar setting form
		 *Step Description: 
			Click on Calendar Settings icon
		 *Input Data: 

		 *Expected Outcome: 
			Calendar Settings pop up appears.*/
		hp.goToCalendarPage();
		waitForAndGetElement(cHome.ELEMENT_CALENDAR_ACTIVE_VIEW.replace("$view", "Week"));
		cMang.goToMenuFromMainCalendar(menuOfMainCalendar.CALSETTING);

		/*Step number: 2
		 *Step Name: Step 2: Choose default view type
		 *Step Description: 
			- Select one value in View type
			- Click Save
		 *Input Data: 

		 *Expected Outcome: 
			- New setting for calendar is saved
			- Whenever this user goes to calendar application, new selected view type is shown as default*/
		cMang.changeSettingCalendar("Month",null,null,null,null,null,null);
		cMang.saveSetting();
		hp.goToCalendarPage();
		waitForAndGetElement(cHome.ELEMENT_CALENDAR_ACTIVE_VIEW.replace("$view", "Month"));
	}

	/**
	 * Case ID:115603.
	 * Test Case Name: Setup date format to show calendar.
	 * Pre-Condition: 
	 * Post-Condition: 
	 */
	@Test
	public  void test03_SetupDateFormatToShowCalendar() {
		String titleEvent = txData.getContentByArrayTypeRandom(1)+"e115603";
		String titleTask = txData.getContentByArrayTypeRandom(1)+"t115603";
		String content = txData.getContentByArrayTypeRandom(1)+"115603";
		String formatDate = "dd-MM-yyyy";
		String defaultFormatDate = "mm/dd/yyyy";
		String dateFrom;
		String dateTo;

		info("Test 3: Setup date format to show calendar");
		/*Step Number: 1
		 *Step Name: Step 1: Show calendar setting form
		 *Step Description: 
			Click on Settings from Tool bar
		 *Input Data: 

		 *Expected Outcome: 
			Calendar Settings pop up appears.*/
		hp.goToCalendarPage();
		cMang.goToMenuFromMainCalendar(menuOfMainCalendar.CALSETTING);

		/*Step number: 2
		 *Step Name: Step 2: Set date format
		 *Step Description: 
			- Choose a format from list for Date format
			- Click Save
		 *Input Data: 

		 *Expected Outcome: 
			New setting is saved*/
		cMang.changeSettingCalendar("Week",null,formatDate.toLowerCase(),null,null,null,null);
		cMang.saveSetting();
		hp.goToCalendarPage();

		/*Step number: 3
		 *Step Name: -Step 3: Check after re
		-setup date format
		 *Step Description: 
			- Click Add event/task
			- Check calendar in List View or search result
		 *Input Data: 

		 *Expected Outcome: 
			All fields relate to date are displayed as new selected format*/
		info("verify dateformat of event");
		hp.goToCalendarPage();
		event.goToAddEventFromActionBar();
		dateFrom = waitForAndGetElement(event.ELEMENT_QUICK_INPUT_EVENT_FROM_DATE).getAttribute("format"); 
		dateTo = waitForAndGetElement(event.ELEMENT_QUICK_INPUT_EVENT_TO_DATE).getAttribute("format");
		assert dateFrom.equals(formatDate);
		assert dateTo.equals(formatDate);
		event.inputDataEventInQuickForm(titleEvent, content, getDate(0,formatDate), getDate(0,formatDate),true);
		event.saveQuickAddEvent();

		info("verify dateformat of task");
		hp.goToCalendarPage();
		task.goToAddTaskFromActionBar();
		dateFrom = waitForAndGetElement(task.ELEMENT_QUICK_INPUT_TASK_FROM_DATE).getAttribute("format"); 
		dateTo = waitForAndGetElement(task.ELEMENT_QUICK_INPUT_TASK_TO_DATE).getAttribute("format");
		assert dateFrom.equals(formatDate);
		assert dateTo.equals(formatDate);
		task.inputDataTaskInQuickForm(titleTask, content, getDate(0,formatDate), getDate(0,formatDate),true);
		task.saveQuickAddTask();

		info("verify dateformat of list view");
		cHome.goToView(selectViewOption.LIST);
		cHome.verifyIsPresentEventTaskWithDateTime(titleEvent,getDate(0,formatDate), selectViewOption.LIST, selectDayOption.ALLDAY);
		cHome.verifyIsPresentEventTaskWithDateTime(titleTask, getDate(0,formatDate), selectViewOption.LIST, selectDayOption.ALLDAY);

		info("Delete data");
		cHome.deleteEventTask(titleEvent, selectViewOption.WEEK, selectDayOption.ALLDAY,null);
		cHome.deleteEventTask(titleTask, selectViewOption.WEEK, selectDayOption.ALLDAY,null);
		cMang.goToMenuFromMainCalendar(menuOfMainCalendar.CALSETTING);
		cMang.changeSettingCalendar("Week",null,defaultFormatDate,null,null,null,null);
	}

	/**
	 * Case ID:115604.
	 * Test Case Name: Setup time format to show calendar.
	 * Pre-Condition: 
	 * Post-Condition: 
	 */
	@Test
	public  void test04_SetupTimeFormatToShowCalendar() {
		String titleEvent = txData.getContentByArrayTypeRandom(1)+"e115604";
		String titleTask = txData.getContentByArrayTypeRandom(1)+"t115604";
		String content = txData.getContentByArrayTypeRandom(1)+"115604";
		String formatTime = "AM/PM";
		String defaultFormatTime = "24 Hours";
		String defaultFormatDate = "MM/dd/yyyy";
		String timeFrom;
		String timeTo;
		String timeEvent;
		String timeTask;
		info("Test 4: Setup time format to show calendar");
		/*Step Number: 1
		 *Step Name: Step 1: Show calendar setting form
		 *Step Description: 
			Click on Settings from Tool bar
		 *Input Data: 

		 *Expected Outcome: 
			Calendar Settings pop up appears.*/
		hp.goToCalendarPage();
		cMang.goToMenuFromMainCalendar(menuOfMainCalendar.CALSETTING);

		/*Step number: 2
		 *Step Name: Step 2: Set time format
		 *Step Description: 
					- Choose a format from list for Time format
					- Click Save
		 *Input Data: 

		 *Expected Outcome: 
					New setting is saved*/
		cMang.changeSettingCalendar("Week",null,defaultFormatDate.toLowerCase(),formatTime,null,null,null);
		cMang.saveSetting();
		hp.goToCalendarPage();

		/*Step number: 3
		 *Step Name: Step 3: Check after re
				-setup time format
		 *Step Description: 
					- Click Add event/task
					- Check calendar in List View or search result
		 *Input Data: 

		 *Expected Outcome: 
					All fields relate to time are displayed as new selected format*/ 
		info("verify dateformat of event");
		hp.goToCalendarPage();
		event.goToAddEventFromActionBar();
		timeFrom = waitForAndGetElement(event.ELEMENT_QUICK_INPUT_EVENT_FROM_TIME, DEFAULT_TIMEOUT, 1, 2).getAttribute("value");
		timeTo = waitForAndGetElement(event.ELEMENT_QUICK_INPUT_EVENT_TO_TIME, DEFAULT_TIMEOUT, 1, 2).getAttribute("value");
		assert (timeFrom.contains("AM")||timeFrom.contains("PM"));
		assert (timeTo.contains("AM")||timeTo.contains("PM"));
		event.inputDataEventInQuickForm(titleEvent, content, getDate(0,defaultFormatDate), getDate(0,defaultFormatDate),false);
		event.saveQuickAddEvent();

		info("verify dateformat of task");
		hp.goToCalendarPage();
		task.goToAddTaskFromActionBar();
		timeFrom = getValue(task.ELEMENT_QUICK_INPUT_TASK_FROM_TIME_INPUT);
		timeTo = getValue(task.ELEMENT_QUICK_INPUT_TASK_TO_TIME_INPUT);
		assert (timeFrom.contains("AM")||timeFrom.contains("PM"));
		assert (timeTo.contains("AM")||timeTo.contains("PM"));
		task.inputDataTaskInQuickForm(titleTask, content, getDate(0,defaultFormatDate), getDate(0,defaultFormatDate),false);
		task.saveQuickAddTask();

		info("verify dateformat of list view - event");
		cHome.goToView(selectViewOption.LIST);
		if(waitForAndGetElement(cHome.ELEMENT_TOTAL_PAGE,5000,0)!=null){
			click(cHome.ELEMENT_ANY_PAGE.replace("$page", "1"));
			while((waitForAndGetElement(cHome.ELEMENT_EVENT_TASK_LIST_VIEW.replace("$name", titleEvent),5000,0)==null)
					&& !(waitForAndGetElement(cHome.ELEMENT_TOTAL_PAGE).getText().equals(waitForAndGetElement(cHome.ELEMENT_CURRENT_PAGE).getText())))
				click(cHome.ELEMENT_NEXT_PAGE);
			waitForAndGetElement(cHome.ELEMENT_EVENT_TASK_LIST_VIEW.replace("$name", titleEvent));
			timeEvent=waitForAndGetElement(cHome.ELEMENT_EVENT_TASK_START_DETAIL_DATE_LIST_VIEW.replace("$name", titleEvent).replace("$date", getDate(0,defaultFormatDate))).getText();
			click(cHome.ELEMENT_ANY_PAGE.replace("$page", "1"));
		}
		else{
			waitForAndGetElement(cHome.ELEMENT_EVENT_TASK_LIST_VIEW.replace("$name", titleEvent));
			timeEvent=waitForAndGetElement(cHome.ELEMENT_EVENT_TASK_START_DETAIL_DATE_LIST_VIEW.replace("$name", titleEvent).replace("$date", getDate(0,defaultFormatDate))).getText();
		}
		assert (timeEvent.contains("AM")||timeFrom.contains("PM"));

		info("verify dateformat of list view - task");
		cHome.goToView(selectViewOption.LIST);
		if(waitForAndGetElement(cHome.ELEMENT_TOTAL_PAGE,5000,0)!=null){
			click(cHome.ELEMENT_ANY_PAGE.replace("$page", "1"));
			while((waitForAndGetElement(cHome.ELEMENT_EVENT_TASK_LIST_VIEW.replace("$name", titleTask),5000,0)==null)
					&& !(waitForAndGetElement(cHome.ELEMENT_TOTAL_PAGE).getText().equals(waitForAndGetElement(cHome.ELEMENT_CURRENT_PAGE).getText())))
				click(cHome.ELEMENT_NEXT_PAGE);
			waitForAndGetElement(cHome.ELEMENT_EVENT_TASK_LIST_VIEW.replace("$name", titleTask));
			timeTask=waitForAndGetElement(cHome.ELEMENT_EVENT_TASK_START_DETAIL_DATE_LIST_VIEW.replace("$name", titleTask).replace("$date", getDate(0,defaultFormatDate))).getText();
			click(cHome.ELEMENT_ANY_PAGE.replace("$page", "1"));
		}
		else{
			waitForAndGetElement(cHome.ELEMENT_EVENT_TASK_LIST_VIEW.replace("$name", titleTask));
			timeTask=waitForAndGetElement(cHome.ELEMENT_EVENT_TASK_START_DETAIL_DATE_LIST_VIEW.replace("$name", titleTask).replace("$date", getDate(0,defaultFormatDate))).getText();
		}
		assert (timeTask.contains("AM")||timeTo.contains("PM"));

		info("Delete data");
		cHome.deleteEventTask(titleEvent, selectViewOption.LIST, selectDayOption.ONEDAY,null);
		cHome.deleteEventTask(titleTask, selectViewOption.LIST, selectDayOption.ONEDAY,null);
		cMang.goToMenuFromMainCalendar(menuOfMainCalendar.CALSETTING);
		cMang.changeSettingCalendar("Week",null,defaultFormatDate.toLowerCase(),defaultFormatTime,null,null,null);
		cMang.saveSetting();
	}

	/**
	 * Case ID:115605.
	 * Test Case Name: Setup Time zone to show calendar.
	 * Pre-Condition: 
	 * Post-Condition: 
	 */
	@Test
	public  void test05_SetupTimeZoneToShowCalendar() {
		String defaultFormatTime = "24 Hours";
		String defaultFormatDate = "MM/dd/yyyy";
		String defaultTimeZone = "(GMT +07:00) Asia/Ho_Chi_Minh";
		String titleEvent = txData.getContentByArrayTypeRandom(1)+"e115605";
		String titleTask = txData.getContentByArrayTypeRandom(1)+"t115605";
		String content = txData.getContentByArrayTypeRandom(1)+"115605";

		info("Test 5: Setup Time zone to show calendar");
		/*Step Number: 1
		 *Step Name: Step 1: Show calendar setting form
		 *Step Description: 
			Click on Settings from Tool bar
		 *Input Data: 

		 *Expected Outcome: 
			Calendar Settings pop up appears.*/
		hp.goToCalendarPage();
		cMang.goToMenuFromMainCalendar(menuOfMainCalendar.CALSETTING);

		/*Step number: 2
		 *Step Name: Step 2: Change calendar settings
		 *Step Description: 
			- Choose value from list for Time Zone
			- Click Save
		 *Input Data: 

		 *Expected Outcome: 
			New setting is saved*/
		cMang.changeSettingCalendar("Week",defaultTimeZone,defaultFormatDate.toLowerCase(),defaultFormatTime,null,null,null);
		cMang.saveSetting();

		/*Step number: 3
		 *Step Name: Step 3: Check after change calendar setting
		 *Step Description: 
			- Click Add event/task
		 *Input Data: 

		 *Expected Outcome: 
			Time Zone are displayed as selected*/
		info("verify dateformat of event");
		hp.goToCalendarPage();
		event.goToAddEventFromActionBar();
		event.inputDataEventInQuickForm(titleEvent, content, getDate(0,defaultFormatDate), getDate(0,defaultFormatDate),false);
		event.saveQuickAddEvent();

		info("verify dateformat of task");
		hp.goToCalendarPage();
		task.goToAddTaskFromActionBar();
		task.inputDataTaskInQuickForm(titleTask, content, getDate(0,defaultFormatDate), getDate(0,defaultFormatDate),false);
		task.saveQuickAddTask();

		info("Delete data");
		cHome.deleteEventTask(titleEvent, selectViewOption.LIST, selectDayOption.ONEDAY,null);
		cHome.deleteEventTask(titleTask, selectViewOption.LIST, selectDayOption.ONEDAY,null);

	}

	/**
	 * Case ID:115606.
	 * Test Case Name: Setup start day for week.
	 * Pre-Condition: 
	 * Post-Condition: 
	 */
	@Test
	public  void test06_SetupStartDayForWeek() {
		String defaultFormatTime = "24 Hours";
		String defaultFormatDate = "MM/dd/yyyy";
		String defaultTimeZone = "(GMT +07:00) Asia/Ho_Chi_Minh";
		String day = "Tuesday";
		String defaultDay="Monday";
		String dayBar="Tue";

		info("Test 6: Setup start day for week");
		/*Step Number: 1
		 *Step Name: Step 1: Show calendar setting form
		 *Step Description: 
			Click on Settings from Tool bar
		 *Input Data: 

		 *Expected Outcome: 
			Calendar Settings pop up appears.*/
		hp.goToCalendarPage();
		cMang.goToMenuFromMainCalendar(menuOfMainCalendar.CALSETTING);

		/*Step number: 2
		 *Step Name: Step 2: Change calendar settings
		 *Step Description: 
			- Select a day from list form Week start on
			- Click Save
		 *Input Data: 

		 *Expected Outcome: 
			New setting is saved*/
		cMang.changeSettingCalendar("Week",defaultTimeZone,defaultFormatDate.toLowerCase(),defaultFormatTime,day,null,null);
		cMang.saveSetting();

		/*Step number: 3
		 *Step Name: Step 3: Check after change calendar setting
		 *Step Description: 
			Select Week View
		 *Input Data: 

		 *Expected Outcome: 
			The start date of week on both mini calendar and main calendar is new selected date*/
		cHome.goToView(selectViewOption.WEEK);
		waitForAndGetElement(cHome.ELEMENT_WEEK_VIEW_BAR_TIME.replace("$index","2")).getText().contains(dayBar);

		info("Reset data");
		hp.goToCalendarPage();
		cMang.goToMenuFromMainCalendar(menuOfMainCalendar.CALSETTING);
		cMang.changeSettingCalendar("Week",defaultTimeZone,defaultFormatDate.toLowerCase(),defaultFormatTime,defaultDay,null,null);
		cMang.saveSetting();
	}

	/**
	 * Case ID:115607.
	 * Test Case Name: Setup working time for calendar.
	 * Pre-Condition: 
	 * Post-Condition: 
	 */
	@Test
	public  void test07_SetupWorkingTimeForCalendar() {
		String defaultFormatTime = "24 Hours";
		String defaultFormatDate = "MM/dd/yyyy";
		String defaultTimeZone = "(GMT +07:00) Asia/Ho_Chi_Minh";
		String defaultDay="Monday";
		String beginTime="01:00";
		String endTime="23:59";
		String color="rgba(240, 240, 240, 1)";

		info("Test 7: Setup working time for calendar");
		/*Step Number: 1
		 *Step Name: Step 1: Open Settings form
		 *Step Description: 
			Click on Settings from Tool bar
		 *Input Data: 

		 *Expected Outcome: 
			Calendar Settings pop up appears.*/
		hp.goToCalendarPage();
		cMang.goToMenuFromMainCalendar(menuOfMainCalendar.CALSETTING);

		/*Step number: 2
		 *Step Name: Step 2: Show form to setup working time
		 *Step Description: 
			- Check Show working times option
		 *Input Data: 

		 *Expected Outcome: 
			Start and End time fields are shown to choose*/

		/*Step number: 3
		 *Step Name: Step 3: Setup working time
		 *Step Description: 
			- Select time for Start and End
			- Click Save
		 *Input Data: 

		 *Expected Outcome: 
			Change is saved*/
		cMang.changeSettingCalendar("Week",defaultTimeZone,defaultFormatDate.toLowerCase(),defaultFormatTime,defaultDay,true,null);
		select(cMang.ELEMENT_CALENDAR_SETTING_SHOW_WORKING_BEGIN_TIME,beginTime,2);
		select(cMang.ELEMENT_CALENDAR_SETTING_SHOW_WORKING_END_TIME,endTime,2);
		cMang.saveSetting();

		/*Step number: 4
		 *Step Name: Step 4: Check after changing calendar setting
		 *Step Description: 
			Check working pane
		 *Input Data: 

		 *Expected Outcome: 
			Working pane is shown as normal from selected Start to End time, other time in day will be in Gray*/
		cHome.goToView(selectViewOption.WEEK);
		String cell = cHome.ELEMENT_CELL_TO_WORKING_PANEL.replace("$date", getDate(0,"MMM dd yyyy")).replace("$time", "00:00");
		driver.navigate().refresh();
		String backgroundColor = waitForAndGetElement(cell).getCssValue("background-color");
		info(backgroundColor);
		assert(color.equals(backgroundColor));

		info("Reset data");
		hp.goToCalendarPage();
		cMang.goToMenuFromMainCalendar(menuOfMainCalendar.CALSETTING);
		cMang.changeSettingCalendar("Week",defaultTimeZone,defaultFormatDate.toLowerCase(),defaultFormatTime,defaultDay,false,null);
		cMang.saveSetting();
	}

	/**
	 * Case ID:115608.
	 * Test Case Name: Set invitation option.
	 * Pre-Condition: 
	 * Post-Condition: 
	 */
	@Test
	public  void test08_SetInvitationOption() {
		String defaultFormatTime = "24 Hours";
		String defaultFormatDate = "MM/dd/yyyy";
		String defaultTimeZone = "(GMT +07:00) Asia/Ho_Chi_Minh";
		String defaultDay="Monday";

		info("Test 8: Set invitation option");
		/*Step Number: 1
		 *Step Name: Step 1: Show calendar setting form
		 *Step Description: 
			Click on Settings from Tool bar
		 *Input Data: 

		 *Expected Outcome: 
			Calendar Settings pop up appears.*/
		hp.goToCalendarPage();
		cMang.goToMenuFromMainCalendar(menuOfMainCalendar.CALSETTING);

		/*Step number: 2
		 *Step Name: Step 2: Finish setting
		 *Step Description: 
			- Click Always/Never/Ask radio 
			- Click Save
		 *Input Data: 

		 *Expected Outcome: 
			Setting calendar was set*/
		cMang.changeSettingCalendar("Week",defaultTimeZone,defaultFormatDate.toLowerCase(),defaultFormatTime,defaultDay,false,selectInvitationOption.ALWAYS);
		cMang.saveSetting();

		/*Step number: 3
		 *Step Name: Step 3: Check invitation option while adding event
		 *Step Description: 
			- Click [Event]
			- Go to Participant tab
			- Check Send invitation option
		 *Input Data: 

		 *Expected Outcome: 
			- Send invitation option is correct as step 2*/ 
		hp.goToCalendarPage();
		event.goToAddEventFromActionBar();
		event.moreDetailsEvent();
		click(event.ELEMENT_EVENT_PARTICIPANTS_TAB);
		assert waitForAndGetElement(event.ELEMENT_SEND_INVITATION_ALWAYS_CHECKBOX, 5000,1,2).isSelected();
		event.cancelAddEditDetailEvent();

		cMang.goToMenuFromMainCalendar(menuOfMainCalendar.CALSETTING);
		cMang.changeSettingCalendar("Week",defaultTimeZone,defaultFormatDate.toLowerCase(),defaultFormatTime,defaultDay,false,selectInvitationOption.NEVER);
		cMang.saveSetting();

	}

	/**
	 * Case ID:115656.
	 * Test Case Name: Displayed calendar.
	 * Pre-Condition: 
	 * Post-Condition: 
	 */
	@Test
	public  void test09_DisplayedCalendar() {
		String titleEvent = txData.getContentByArrayTypeRandom(1)+"e115656";
		String titleTask = txData.getContentByArrayTypeRandom(1)+"t115656";
		String content = txData.getContentByArrayTypeRandom(1)+"115656";
		String group="Development";
		String defaultFormatDate="MM/dd/yyyy";

		info("create data test");
		hp.goToCalendarPage();
		event.goToAddEventFromActionBar();
		event.inputDataEventInQuickForm(titleEvent, content, getDate(0,defaultFormatDate), getDate(0,defaultFormatDate),false);
		event.saveQuickAddEvent();
		cHome.verifyIsPresentEventTask(titleEvent, selectViewOption.LIST, selectDayOption.ONEDAY);

		task.goToAddTaskFromActionBar();
		task.inputDataTaskInQuickForm(titleTask, content, getDate(0,defaultFormatDate), getDate(0,defaultFormatDate),false);
		task.saveQuickAddTask();
		cHome.verifyIsPresentEventTask(titleTask, selectViewOption.LIST, selectDayOption.ONEDAY);

		info("Test 9: Displayed calendar");
		/*Step Number: 1
		 *Step Name: Step 1: Show calendar setting form
		 *Step Description: 
			- Click Calendar Action icon
			- Click Settings option on menuOr
			- Click Wheel icon beside Search box on the top right
		 *Input Data: 

		 *Expected Outcome: 
			Calendar Settings pop up appears.There are 3 tabs in this pop up: Settings, Displayed calendar and Feed*/
		hp.goToCalendarPage();
		cMang.goToMenuFromMainCalendar(menuOfMainCalendar.CALSETTING);

		/*Step number: 2
		 *Step Name: Step 2: Check Displayed Calendar tab
		 *Step Description: 
			In Calendar settings form:
			- Select Display Calendars tab
		 *Input Data: 

		 *Expected Outcome: 
			- List all existing calendar using the exact same list as on the Calendar view including Personal calendar, Group calendar, Shared calendar
			- Calendars that showing are checked*/
		click(cMang.ELEMENT_CALENDAR_SETTING_TAB_ITEM.replace("$tab", "Displayed Calendars"));
		waitForAndGetElement(cMang.ELEMENT_DISPLAY_FORM_PERSONAL_CALENDAR_ITEM_CHECKBOX.replace("$name", fullName));
		waitForAndGetElement(cMang.ELEMENT_DISPLAY_FORM_GROUP_CALENDAR_ITEM_CHECKBOX.replace("$name", group));

		/*Step number: 3
		 *Step Name: -
		 *Step Description: 
			Step 3: Setup to show/hide specific calendar
		 *Input Data: 
			- Unchecked the calendar(s) you don't want to show
			- Click Save
		 *Expected Outcome: 
			- Change is saved 
			- Unchecked calendar(s) is not shown in left pane and its event/task are also not shown in working pane or list*/ 
		cMang.showHidePersonalCalendar(fullName, false);
		cMang.showHideGroupCalendar(group, false);
		cMang.saveSetting();
		waitForElementNotPresent(cMang.ELEMENT_CALENDAR_LIST_ITEM.replace("$calendar", fullName));
		waitForElementNotPresent(cMang.ELEMENT_CALENDAR_LIST_ITEM.replace("$calendar", group));
		cHome.verifyIsNotPresentEventTask(titleEvent, selectViewOption.LIST, selectDayOption.ONEDAY);
		cHome.verifyIsNotPresentEventTask(titleTask, selectViewOption.LIST, selectDayOption.ONEDAY);

		info("Reset data");
		hp.goToCalendarPage();
		cMang.goToMenuFromMainCalendar(menuOfMainCalendar.CALSETTING);
		click(cMang.ELEMENT_CALENDAR_SETTING_TAB_ITEM.replace("$tab", "Displayed Calendars"));
		cMang.showHidePersonalCalendar(fullName, true);
		cMang.showHideGroupCalendar(group, true);
		cMang.saveSetting();
		cHome.deleteEventTask(titleEvent, selectViewOption.LIST, selectDayOption.ONEDAY,null);
		cHome.deleteEventTask(titleTask, selectViewOption.LIST, selectDayOption.ONEDAY,null);
		waitForAndGetElement(cMang.ELEMENT_CALENDAR_LIST_ITEM.replace("$calendar", fullName));
		waitForAndGetElement(cMang.ELEMENT_CALENDAR_LIST_ITEM.replace("$calendar", group));
	}

	/**
	 * Case ID:115657.
	 * Test Case Name: Add new feed.
	 * Pre-Condition: Calendar to add feed must have task/ event
	 * Post-Condition: 
	 */
	@Test
	public  void test10_11_12_AddEditDeleteNewFeed() {
		String name = txData.getContentByArrayTypeRandom(1)+"old115657";
		String newName = txData.getContentByArrayTypeRandom(1)+"new115657";

		String calendar=fullName;
		
		String titleEvent = txData.getContentByArrayTypeRandom(1)+"e115656";
		String titleTask = txData.getContentByArrayTypeRandom(1)+"t115656";
		String content = txData.getContentByArrayTypeRandom(1)+"115656";
		String defaultFormatDate="MM/dd/yyyy";

		info("create data test");
		hp.goToCalendarPage();
		event.goToAddEventFromActionBar();
		event.inputDataEventInQuickForm(titleEvent, content, getDate(0,defaultFormatDate), getDate(0,defaultFormatDate),false);
		event.saveQuickAddEvent();
		cHome.verifyIsPresentEventTask(titleEvent, selectViewOption.LIST, selectDayOption.ONEDAY);

		task.goToAddTaskFromActionBar();
		task.inputDataTaskInQuickForm(titleTask, content, getDate(0,defaultFormatDate), getDate(0,defaultFormatDate),false);
		task.saveQuickAddTask();
		cHome.verifyIsPresentEventTask(titleTask, selectViewOption.LIST, selectDayOption.ONEDAY);

		info("Test 10 Add new feed");
		/*Step Number: 1
		 *Step Name: Step 1: Show calendar setting form
		 *Step Description: 
			- Click Calendar Action icon
			- Click Settings option on menuOr
			- Click Wheel icon beside Search box on the top right
		 *Input Data: 

		 *Expected Outcome: 
			Calendar Settings pop up appears.There are 3 tabs in this pop up: Settings, Displayed calendar and Feed*/
		hp.goToCalendarPage();
		cMang.goToMenuFromMainCalendar(menuOfMainCalendar.CALSETTING);

		/*Step number: 2
		 *Step Name: Step 2: Select Feed tab
		 *Step Description: 
			In Calendar settings form:
			- Select Feed tab
			- Click Add
		 *Input Data: 

		 *Expected Outcome: 
			- Feed tab is shown
			- Form to add new feed is shown*/
		click(cMang.ELEMENT_CALENDAR_SETTING_TAB_ITEM.replace("$tab", "Feeds"));
		click(cMang.ELEMENT_FEED_TAB_SAVE_BUTTON);
		waitForAndGetElement(cMang.ELEMENT_FEED_EDIT_FEED_FORM);

		/*Step number: 3
		 *Step Name: Step 3: Add new feed
		 *Step Description: 
			- Put value in Name field
			- Select calendar by + Click Add more box+ Then choose a calendar in drop down menu+ Click Add calendar (+ icon) 
			- Click Save button
		 *Input Data: 

		 *Expected Outcome: 
			New feed is created successfully*/
		cMang.addEditFeed(name, "", calendar);
		click(cMang.ELEMENT_FEED_EDIT_FEED_SAVE_FORM);
		alert.verifyAlertMessage(cMang.ELEMENT_FEED_CONFIRM_ADD_FEED.replace("$name", name));
		waitForAndGetElement(cMang.ELEMENT_FEED_LIST_ITEM_RSS_BUTTON.replace("$name", name));

		info("Test 11 Edit a feed");		
		/*Step number: 4
		 *Step Name: 
		 *Step Description: 
			Step 4: Edit Feed
		 *Input Data: 
			- Click Edit icon corresponding to Feed above
			- Change some values
			- Save
		 *Expected Outcome: 
			Feed is edited successfully with new values*/ 
		click(cMang.ELEMENT_FEED_LIST_ITEM_EDIT_BUTTON.replace("$name", name));
		cMang.addEditFeed(newName, "", "");
		click(cMang.ELEMENT_FEED_EDIT_FEED_SAVE_FORM);
		alert.verifyAlertMessage(cMang.ELEMENT_FEED_CONFIRM_ADD_FEED.replace("$name", newName));
		waitForAndGetElement(cMang.ELEMENT_FEED_LIST_ITEM_RSS_BUTTON.replace("$name", newName));

		info("Test 12 Delete a feed");
		/*Step number: 2
		 *Step Name: 
		 *Step Description: 
			Step 2: Delete Feed
		 *Input Data: 
			- Click Delete icon corresponding to Feed above
			- Click OK to confirmation message
		 *Expected Outcome: 
			Feed is deleted successfully*/ 
		cMang.deleteFeed(newName, true);
		waitForElementNotPresent(cMang.ELEMENT_FEED_LIST_ITEM_RSS_BUTTON.replace("$name", newName));
		cMang.saveSetting();
		
		info("Reset data");
		hp.goToCalendarPage();
		cHome.deleteEventTask(titleEvent, selectViewOption.LIST, selectDayOption.ONEDAY,null);
		cHome.deleteEventTask(titleTask, selectViewOption.LIST, selectDayOption.ONEDAY,null);
	}

}
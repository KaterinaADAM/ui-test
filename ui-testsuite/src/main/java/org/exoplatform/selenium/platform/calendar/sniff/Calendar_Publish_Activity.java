package org.exoplatform.selenium.platform.calendar.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.platform.HomePagePlatform;
import org.exoplatform.selenium.platform.ActivityStream;
import org.exoplatform.selenium.platform.ManageLogInOut;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.calendar.EventManagement;
import org.exoplatform.selenium.platform.calendar.TaskManagement;
import org.exoplatform.selenium.platform.calendar.TaskManagement.statusTask;
import org.exoplatform.selenium.platform.calendar.CalendarHomePage;
import org.exoplatform.selenium.platform.calendar.CalendarManagement;
import org.exoplatform.selenium.platform.calendar.EventManagement.recurringType;
import org.exoplatform.selenium.platform.calendar.EventManagement.repeatEndType;
import org.exoplatform.selenium.platform.calendar.EventManagement.repeatType;
import org.exoplatform.selenium.platform.calendar.CalendarHomePage.selectDayOption;
import org.exoplatform.selenium.platform.calendar.CalendarHomePage.selectViewOption;
import org.exoplatform.selenium.platform.calendar.CalendarManagement.menuOfCalendarOption;
import org.exoplatform.selenium.platform.calendar.CalendarManagement.menuOfMainCalendar;
import org.exoplatform.selenium.platform.objectdatabase.common.AttachmentFileDatabase;
import org.exoplatform.selenium.platform.objectdatabase.common.TextBoxDatabase;
import org.exoplatform.selenium.platform.objectdatabase.user.UserDatabase;
import org.exoplatform.selenium.platform.social.SpaceManagement;
import org.openqa.selenium.By;
import org.testng.annotations.*;


public class Calendar_Publish_Activity  extends PlatformBase {
	HomePagePlatform hp;
	ManageLogInOut magAc;
	TextBoxDatabase txData;
	CalendarHomePage cHome;
	TaskManagement task;
	EventManagement event;
	AttachmentFileDatabase fData;
	CalendarManagement cMang;
	UserDatabase userData;
	SpaceManagement sMang;
	ActivityStream hpAct;
	String fullName;
	String spaceName;
	String spaceDes;
	
	@BeforeClass
	public void setUpBeforeTest() throws Exception{
		initSeleniumTest();
		getDefaultUserPass(userDataFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		driver.get(baseUrl);
		magAc = new ManageLogInOut(driver);
		magAc.signIn(DATA_USER1, DATA_PASS);
		hp = new HomePagePlatform(driver);
		cHome= new CalendarHomePage(driver);
		event= new EventManagement(driver);
		task= new TaskManagement(driver);
		cMang = new CalendarManagement(driver);
		sMang = new SpaceManagement(driver);
		hpAct = new ActivityStream(driver);
		txData = new TextBoxDatabase();
		fData = new AttachmentFileDatabase();
		userData = new UserDatabase();
		userData.setUserData(userDataFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		txData.setContentData(texboxFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlContent);
		fData.setAttachFileData(attachmentFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		fullName = userData.fullName.get(0);
		spaceName = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		spaceDes = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		createDataTest();
	}

	@AfterClass
	public void afterTest(){
		//deleteDataTest();
		magAc.signOut();
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	/**
	 * Create data test
	 */
	public void createDataTest(){
		hp.goToCalendarPage();
		cMang.goToMenuFromMainCalendar(menuOfMainCalendar.CALSETTING);
		cMang.changeSettingCalendar(null,"(GMT +07:00) Asia/Ho_Chi_Minh",null,null,null,null,null);
		cMang.saveSetting();
		hp.goToMySpaces();
		sMang.goToCreateSpace();
		sMang.addNewSpaceSimple(spaceName, spaceDes);
	}
	
	/**
	 * Delete datatest
	 */
	public void deleteDataTest(){
		hp.goToMySpaces();
		sMang.deleteSpace(spaceName, false);
	}
	
	/**
	 * Case ID:115635.
	 * Test Case Name: Activities should be updated after deleting of an edited recurring event.
	 * Pre-Condition: - Event should be in group calendars for spaces
	- An edited recurring event is displayed, edited by drag & drop or by only this event option
	- An activity of edited recurring event is displayed in the activity stream
	 * Post-Condition: 
	 */
	@Test (priority=1)
	public  void test08_ActivitiesShouldBeUpdatedAfterDeletingOfAnEditedRecurringEvent() {
		String titleEvent = txData.getContentByArrayTypeRandom(1)+"r115635";
		String content = txData.getContentByArrayTypeRandom(1)+"r115635";
		info("Test 8: Activities should be updated after deleting of an edited recurring event");
		/*Step Number: 1
		 *Step Name: <p>Step 1: Open calendar application<br data
		-mce
		-bogus="1"></p>
		 *Step Description: 
			<p>
			- Connect to Intranet<br>
			- Open "Calendar" application</p>
		 *Input Data: 

		 *Expected Outcome: 
			<p>
			- The calendar application is displayed</p><p>
			- An edited recurring event is displayed&nbsp;</p><p><br></p>*/
		info("Add a recurring event");
		hp.goToCalendarPage();
		cMang.executeActionCalendar(spaceName, menuOfCalendarOption.ADDEVENT);
		event.inputDataEventInQuickForm(titleEvent, content, getDate(0,"MM/dd/yyyy HH")+":00", getDate(0,"MM/dd/yyyy HH")+":30",false);
		event.moreDetailsEvent();
		check(event.ELEMENT_ADD_EDIT_EVENT_REPEAT_CHECKBOX,2);
		event.inputRecurringInfoEvent(repeatType.Daily,"1",null,repeatEndType.After,"5");
		click(event.ELEMENT_SAVE_EVENT_OCCURRING);
		event.saveAddEventDetails();

		/*Step number: 2
		 *Step Name: <p>Step 2: Delete the edited recurring event<br data
		-mce
		-bogus="1"></p>
		 *Step Description: 
			<p>
			- Right click on the edited recurring event</p><p>
			- Click Delete</p>
		 *Input Data: 

		 *Expected Outcome: 
			<p>
			- The edited recurring event is deleted</p>*/
		event.deleteRecurringEvent(titleEvent, selectViewOption.MONTH, selectDayOption.ONEDAY, recurringType.ONLY_EVENT, getDate(1,"MMM dd yyyy"),true);

		/*Step number: 3
		 *Step Name: <p>Step 3: Check activity<br data
		-mce
		-bogus="1"></p>
		 *Step Description: 
			- Go to the Homepage
		 *Input Data: 

		 *Expected Outcome: 
			<p>
			- In the activity stream: </p><p>Activity of the edited recurring event is</p><p>A comment is added to the main activity (of series):&nbsp;<em>Event cancelled for $CANCEL_DATE</em></p><p><em>where&nbsp;<span>$CANCEL_DATE : the date of the event removed</span></em></p>*/
		hp.goToHomePage();
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_TASK_EVENT_COMMENT_RECURRING_CANCEL.replace("$name", titleEvent).replace("$date", getDate(1,"EEEE, MMMM dd, yyyy")));
		
		info("Clear data");
		hp.goToCalendarPage();
		event.deleteRecurringEvent(titleEvent, selectViewOption.MONTH, selectDayOption.ONEDAY, recurringType.ALL_EVENT, getDate(0,"MMM dd yyyy"),true);
	}

	/**
	 * Case ID:115636.
	 * Test Case Name: An activity should displayed after drag and drop a recurring event.
	 * Pre-Condition: - Event should be in group calendars for spaces
	- A recurring event is displayed in Calendar
	 * Post-Condition: 
	 */
	@Test (priority=2)
	public  void test09_AnActivityShouldDisplayedAfterDragAndDropARecurringEvent() {
		String titleEvent = txData.getContentByArrayTypeRandom(1)+"115636";
		String content = txData.getContentByArrayTypeRandom(1)+"115636";

		info("Test 9: An activity should displayed after drag and drop a recurring event");
		/*Step Number: 1
		 *Step Name: <p>Step 1: Open calendar application<br data
		-mce
		-bogus="1"></p>
		 *Step Description: 
			- Connect to Intranet
			- Open "Calendar" application
		 *Input Data: 

		 *Expected Outcome: 
			<p>
			- A recurring event is displayed</p>*/
		info("Add a event");
		hp.goToCalendarPage();
		cMang.executeActionCalendar(spaceName, menuOfCalendarOption.ADDEVENT);
		event.inputDataEventInQuickForm(titleEvent, content, getDate(0,"MM/dd/yyyy HH")+":00", getDate(0,"MM/dd/yyyy HH")+":30",false);
		event.moreDetailsEvent();
		check(event.ELEMENT_ADD_EDIT_EVENT_REPEAT_CHECKBOX,2);
		event.inputRecurringInfoEvent(repeatType.Daily,"1",null,repeatEndType.After,"5");
		click(event.ELEMENT_SAVE_EVENT_OCCURRING);
		event.saveAddEventDetails();

		/*Step number: 2
		 *Step Name: <p>Step 2: Drag & drop event<br data
		-mce
		-bogus="1"></p>
		 *Step Description: 
			<p>
			- Drag and drop an event from the series</p>
		 *Input Data: 

		 *Expected Outcome: 
			<p>
			- Starts and End time are edited</p>*/
		cHome.goToView(selectViewOption.MONTH);
		dragAndDropToObject(By.xpath(cHome.ELEMENT_EVENT_TASK_DETAIL_DATE_MONTH_VIEW.replace("$name", titleEvent).replace("$date", getDate(0, "MMM dd yyyy"))),By.xpath(cHome.ELEMENT_ANY_TARGET_DATE.replace("${targetDate}", getDate(-1, "MMM dd yyyy"))));

		/*Step number: 3
		 *Step Name: <p>Step 3: Check activity after drap & drop event<br data
		-mce
		-bogus="1"></p>
		 *Step Description: 
			- Go to the Homepage
		 *Input Data: 

		 *Expected Outcome: 
			<p>
			- A new activity is created for the recurring event</p>*/ 
		hp.goToHomePage();
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_TASK_EVENT_DATE.replace("$name", titleEvent).replace("$date", getDate(-1,"EEEE, MMMM dd, yyyy")));
		
		info("Clear data");
		hp.goToCalendarPage();
		cHome.deleteEventTask(titleEvent, selectViewOption.MONTH, selectDayOption.ONEDAY,getDate(-1,"MMM dd yyyy"),false);
		event.deleteRecurringEvent(titleEvent, selectViewOption.MONTH, selectDayOption.ONEDAY, recurringType.ALL_EVENT, getDate(1,"MMM dd yyyy"),false);
	}

	/**
	 * Case ID:115637.
	 * Test Case Name: Activity of recurring event should be deleted after deleting all events.
	 * Pre-Condition: - Event should be in group calendars for spaces
	- A recurring event is displayed in the Calendar
	- An activity recurring event is displayed in the activity stream
	 * Post-Condition: 
	 */
	@Test (priority=3)
	public  void test10_ActivityOfRecurringEventShouldBeDeletedAfterDeletingAllEvents() {
		String titleEvent = txData.getContentByArrayTypeRandom(1)+"115637";
		String content = txData.getContentByArrayTypeRandom(1)+"115637";

		info("Test 10: Activity of recurring event should be deleted after deleting all events");
		/*Step Number: 1
		 *Step Name: <p>Step 1: Open calendar application<br data
		-mce
		-bogus="1"></p>
		 *Step Description: 
			<p>
			- Connect to Intranet<br>
			- Open "Calendar" application</p>
		 *Input Data: 

		 *Expected Outcome: 
			<p>
			- The calendar application is displayed</p><p>
			- A recurring event is displayed&nbsp;</p><p><br></p>*/
		info("Add a event");
		hp.goToCalendarPage();
		cMang.executeActionCalendar(spaceName, menuOfCalendarOption.ADDEVENT);
		event.inputDataEventInQuickForm(titleEvent, content, getDate(0,"MM/dd/yyyy HH")+":00", getDate(0,"MM/dd/yyyy HH")+":30",false);
		event.moreDetailsEvent();
		check(event.ELEMENT_ADD_EDIT_EVENT_REPEAT_CHECKBOX,2);
		event.inputRecurringInfoEvent(repeatType.Daily,"1",null,repeatEndType.After,"5");
		click(event.ELEMENT_SAVE_EVENT_OCCURRING);
		event.saveAddEventDetails();

		/*Step number: 2
		 *Step Name: <p>Step 2: Choose an event<br data
		-mce
		-bogus="1"></p>
		 *Step Description: 
			<p>
			- Right click on an event from the series</p><p>
			- Click Delete</p>
		 *Input Data: 

		 *Expected Outcome: 
			<p>
			- The Delete Recurring Event will be disappeared with 3 options: Only this instance, Following this instance, All events in the series.<br></p><p><br class="uiRadio"></p>*/

		/*Step number: 3
		 *Step Name: <p>Step 3: Delete event<br data
		-mce
		-bogus="1"></p>
		 *Step Description: 
			<p>
			- Choose "All events in the series" option</p><p>
			- Click on Delete</p>
		 *Input Data: 

		 *Expected Outcome: 
			<p>
			- All events of the series are deleted</p>*/

		/*Step number: 4
		 *Step Name: <p>Step 4: Check activity after delete event<br data
		-mce
		-bogus="1"></p>
		 *Step Description: 
			- Go to the Homepage
		 *Input Data: 

		 *Expected Outcome: 
			<p>
			- In the activity stream, the activity of recurring event is deleted</p>*/
		hp.goToHomePage();
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_TASK_EVENT_TITLE.replace("$name", titleEvent));
		
		hp.goToCalendarPage();
		event.deleteRecurringEvent(titleEvent, selectViewOption.MONTH, selectDayOption.ONEDAY, recurringType.ALL_EVENT, getDate(1,"MMM dd yyyy"),true);
		hp.goToHomePage();
		waitForElementNotPresent(hpAct.ELEMENT_ACTIVITY_TASK_EVENT_TITLE.replace("$name", titleEvent));

	}

	/**
	 * Case ID:115638.
	 * Test Case Name: A comment to event activity should be added after adding a repeat event.
	 * Pre-Condition: * Event should be in group calendars for spaces* An event is displayed in Calendar* An activity event is displayed in the activity stream
	 * Post-Condition: 
	 */
	@Test (priority=4)
	public  void test11_ACommentToEventActivityShouldBeAddedAfterAddingARepeatEvent() {
		String titleEvent = txData.getContentByArrayTypeRandom(1)+"115638";
		String content = txData.getContentByArrayTypeRandom(1)+"115638";
		String comment = hpAct.ELEMENT_ACTIVITY_EVENT_COMMENT_REPEAT_DAY.replace("$number", "5");
		
		info("Test 11: A comment to event activity should be added after adding a repeat event");
		/*Step Number: 1
		 *Step Name: <p>Step 1: Show application calendar<br data
		-mce
		-bogus="1"></p>
		 *Step Description: 
			- Connect to Intranet
			- Open "Calendar" application
		 *Input Data: 

		 *Expected Outcome: 
			- An event is displayed*/
		info("Add a event");
		hp.goToCalendarPage();
		cMang.executeActionCalendar(spaceName, menuOfCalendarOption.ADDEVENT);
		event.inputDataEventInQuickForm(titleEvent, content, getDate(0,"MM/dd/yyyy HH")+":00", getDate(0,"MM/dd/yyyy HH")+":30",false);
		event.saveQuickAddEvent();
		cHome.verifyIsPresentEventTask(titleEvent, selectViewOption.MONTH, selectDayOption.ONEDAY);

		/*Step number: 2
		 *Step Name: <p>Step 2: Chang event to recurring event<br data
		-mce
		-bogus="1"></p>
		 *Step Description: 
			<p>
			- Edit the event<br>
			- Check "Repeat" option<br>
			- Click Save</p>
		 *Input Data: 

		 *Expected Outcome: 
			<p>
			- A recurring event is added</p>*/
		cHome.goToEditEventTaskFormByRightClick(titleEvent, selectViewOption.MONTH, selectDayOption.ONEDAY,getDate(0,"MMM dd yyyy"));
		check(event.ELEMENT_ADD_EDIT_EVENT_REPEAT_CHECKBOX,2);
		event.inputRecurringInfoEvent(repeatType.Daily,"1",null,repeatEndType.After,"5");
		click(event.ELEMENT_SAVE_EVENT_OCCURRING);
		event.saveAddEventDetails();

		/*Step number: 3
		 *Step Name: <p>Step 3: Check acitity after add recurring event<br data
		-mce
		-bogus="1"></p>
		 *Step Description: 
			- Go to the Homepage
		 *Input Data: 

		 *Expected Outcome: 
			<p>
			- In the activity stream, a comment is added to the main activity event: "Event will be repeated $REPETITION"<br>where $REPETITION : strong format of the recurring settings (e.g : every week on Wednesday)</p>*/
		hp.goToHomePage();
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_TASK_EVENT_COMMENT.replace("$name", titleEvent).replace("$comment", comment));
		
		info("Clear data");
		hp.goToCalendarPage();
		event.deleteRecurringEvent(titleEvent, selectViewOption.MONTH, selectDayOption.ONEDAY, recurringType.ALL_EVENT, getDate(1,"MMM dd yyyy"),true);
	}

	/**
	 * Case ID:115661.
	 * Test Case Name: Update activity for event of Space Calendar- event is updated as all day eventa space.
	 * Pre-Condition: a space is created
	 * Post-Condition: 
	 */
	@Test (priority=5)
	public  void test12_UpdateActivityForEventOfSpaceCalendarEventIsUpdatedAsAllDayEventaSpace() {
		String titleEvent = txData.getContentByArrayTypeRandom(1)+"115661";
		String content = txData.getContentByArrayTypeRandom(1)+"115661";
		String comment = hpAct.ELEMENT_ACTIVITY_EVENT_COMMENT_CHECK_ALL_DAY;
		
		info("Test 12: Update activity for event of Space Calendar- event is updated as all day eventa space");
		/*Step Number: 1
		 *Step Name: - Create an event
		 *Step Description: 
			- Connect to Intranet
			- Go to Calendar
			- Create an event for space calendar
		 *Input Data: 

		 *Expected Outcome: 
			- Event is created and activity is published in HomePage*/
		hp.goToCalendarPage();
		info("create event for space calendar");
		cMang.executeActionCalendar(spaceName, menuOfCalendarOption.ADDEVENT);
		event.inputDataEventInQuickForm(titleEvent, content, getDate(0,"MM/dd/yyyy HH")+":00", getDate(0,"MM/dd/yyyy HH")+":30",false);
		event.saveQuickAddEvent();
		cHome.verifyIsPresentEventTask(titleEvent, selectViewOption.MONTH, selectDayOption.ONEDAY);

		/*Step number: 2
		 *Step Name: - Update Event
		 *Step Description: 
			- Edit event and check "all day"
		 *Input Data: 

		 *Expected Outcome: 
			- The event in the activity stream is updated
			- A comment is added to the activity with the following message Event is now an all day event.*/
		cHome.goToEditEventTaskFormByRightClick(titleEvent, selectViewOption.MONTH, selectDayOption.ONEDAY,getDate(0,"MMM dd yyyy"));
		check(event.ELEMENT_ADD_EDIT_EVENT_ALLDAY,2);
		event.saveAddEventDetails();
		
		hp.goToHomePage();
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_TASK_EVENT_COMMENT.replace("$name", titleEvent).replace("$comment", comment));
		
		info("Clear data");
		hp.goToCalendarPage();
		cHome.deleteEventTask(titleEvent, selectViewOption.MONTH, selectDayOption.ALLDAY,getDate(0,"MMM dd yyyy"));
	}

	/**
	 * Case ID:115662.
	 * Test Case Name: Update activity for event of Space Calendar - event title.
	 * Pre-Condition: a space is created
	 * Post-Condition: 
	 */
	@Test (priority=6)
	public  void test13_UpdateActivityForEventOfSpaceCalendarEventTitle() {
		String titleEvent = txData.getContentByArrayTypeRandom(1)+"115662";
		String newTitleEvent = txData.getContentByArrayTypeRandom(1)+"n115662";
		String content = txData.getContentByArrayTypeRandom(1)+"115662";
		String comment = hpAct.ELEMENT_ACTIVITY_EVENT_COMMENT_UPDATE_TITLE.replace("$title", newTitleEvent);
		
		info("Test 13 Update activity for event of Space Calendar - event title");
		/*Step Number: 1
		 *Step Name: - Create an event
		 *Step Description: 
			- Connect to Intranet
			- Go to Calendar
			- Create an event for space calendar
		 *Input Data: 

		 *Expected Outcome: 
			- Event is created and activity is published*/
		hp.goToCalendarPage();
		info("create event for space calendar");
		cMang.executeActionCalendar(spaceName, menuOfCalendarOption.ADDEVENT);
		event.inputDataEventInQuickForm(titleEvent, content, getDate(0,"MM/dd/yyyy HH")+":00", getDate(0,"MM/dd/yyyy HH")+":30",false);
		event.saveQuickAddEvent();
		cHome.verifyIsPresentEventTask(titleEvent, selectViewOption.MONTH, selectDayOption.ONEDAY);
		
		/*Step number: 2
		 *Step Name: - Update Event
		 *Step Description: 
			- Update Title of the event
		 *Input Data: 

		 *Expected Outcome: 
			- The title of event in the activity stream is updated 
			- A comment is added with the following message: "Title has been updated to: $value."*/ 
		cHome.goToEditEventTaskFormByRightClick(titleEvent, selectViewOption.MONTH, selectDayOption.ONEDAY,getDate(0,"MMM dd yyyy"));
		event.inputBasicDetailEvent(newTitleEvent, null);
		event.saveAddEventDetails();
		
		hp.goToHomePage();
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_TASK_EVENT_COMMENT.replace("$name", newTitleEvent).replace("$comment", comment));
		
		info("Clear data");
		hp.goToCalendarPage();
		cHome.deleteEventTask(newTitleEvent, selectViewOption.MONTH, selectDayOption.ONEDAY,getDate(0,"MMM dd yyyy"));
	}

	/**
	 * Case ID:115663.
	 * Test Case Name: Update activity for event of Space Calendar - event description.
	 * Pre-Condition: a space is created
	 * Post-Condition: 
	 */
	@Test (priority=7)
	public  void test14_UpdateActivityForEventOfSpaceCalendarEventDescription() {
		String titleEvent = txData.getContentByArrayTypeRandom(1)+"115662";
		String newContent = txData.getContentByArrayTypeRandom(1)+"n115662";
		String content = txData.getContentByArrayTypeRandom(1)+"115662";
		String comment = hpAct.ELEMENT_ACTIVITY_EVENT_COMMENT_UPDATE_DES.replace("$description", newContent);
		
		info("Test 14 Update activity for event of Space Calendar - event description");
		/*Step Number: 1
		 *Step Name: - Create an event
		 *Step Description: 
			- Connect to Intranet
			- Go to Calendar
			- Create an event for space calendar
		 *Input Data: 

		 *Expected Outcome: 
			- Event is created and activity is published in activity stream*/
		hp.goToCalendarPage();
		info("create event for space calendar");
		cMang.executeActionCalendar(spaceName, menuOfCalendarOption.ADDEVENT);
		event.inputDataEventInQuickForm(titleEvent, content, getDate(0,"MM/dd/yyyy HH")+":00", getDate(0,"MM/dd/yyyy HH")+":30",false);
		event.saveQuickAddEvent();
		cHome.verifyIsPresentEventTask(titleEvent, selectViewOption.MONTH, selectDayOption.ONEDAY);

		/*Step number: 2
		 *Step Name: - Update an event
		 *Step Description: 
			- Update an event description and check activity stream
		 *Input Data: 

		 *Expected Outcome: 
			- The description of event in the activity stream is updated
			- A comment is added to the activity with the following message: Description has been updated to: $value.*/ 
		cHome.goToEditEventTaskFormByRightClick(titleEvent, selectViewOption.MONTH, selectDayOption.ONEDAY,getDate(0,"MMM dd yyyy"));
		event.inputBasicDetailEvent(null, newContent);
		event.saveAddEventDetails();
		
		hp.goToHomePage();
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_TASK_EVENT_COMMENT.replace("$name", titleEvent).replace("$comment", comment));
		
		info("Clear data");
		hp.goToCalendarPage();
		cHome.deleteEventTask(titleEvent, selectViewOption.MONTH, selectDayOption.ONEDAY,getDate(0,"MMM dd yyyy"));
	}

	/**
	 * Case ID:115664.
	 * Test Case Name: Update activity for event of Space Calendar - event location.
	 * Pre-Condition: a space is created
	 * Post-Condition: 
	 */
	@Test (priority=8)
	public  void test15_UpdateActivityForEventOfSpaceCalendarEventLocation() {
		String titleEvent = txData.getContentByArrayTypeRandom(1)+"115662";
		String location = txData.getContentByArrayTypeRandom(1)+"n115662";
		String content = txData.getContentByArrayTypeRandom(1)+"115662";
		String comment = hpAct.ELEMENT_ACTIVITY_EVENT_COMMENT_UPDATE_LOC.replace("$location", location);
		
		info("Test 15 Update activity for event of Space Calendar - event location");
		/*Step Number: 1
		 *Step Name: - Create an event
		 *Step Description: 
			- Connect to Intranet
			- Go to calendar
			- Create an event for Space Calendar
		 *Input Data: 

		 *Expected Outcome: 
			- Event is created and activity is published in activity stream*/
		hp.goToCalendarPage();
		info("create event for space calendar");
		cMang.executeActionCalendar(spaceName, menuOfCalendarOption.ADDEVENT);
		event.inputDataEventInQuickForm(titleEvent, content, getDate(0,"MM/dd/yyyy HH")+":00", getDate(0,"MM/dd/yyyy HH")+":30",false);
		event.saveQuickAddEvent();
		cHome.verifyIsPresentEventTask(titleEvent, selectViewOption.MONTH, selectDayOption.ONEDAY);
		
		/*Step number: 2
		 *Step Name: - Update Event
		 *Step Description: 
			- Update an event location
		 *Input Data: 

		 *Expected Outcome: 
			- The location of event in the activity stream is updated
			- A comment is added to the activity with the following message: Location has been updated to: $value.*/ 
		cHome.goToEditEventTaskFormByRightClick(titleEvent, selectViewOption.MONTH, selectDayOption.ONEDAY,getDate(0,"MMM dd yyyy"));
		event.inputBasicDetailEvent(null,null,null,null,location);
		event.saveAddEventDetails();
		
		hp.goToHomePage();
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_TASK_EVENT_COMMENT.replace("$name", titleEvent).replace("$comment", comment));
		
		info("Clear data");
		hp.goToCalendarPage();
		cHome.deleteEventTask(titleEvent, selectViewOption.MONTH, selectDayOption.ONEDAY,getDate(0,"MMM dd yyyy"));
	}

	/**
	 * Case ID:115667.
	 * Test Case Name: Update Task for a Space Calendar - add attachment.
	 * Pre-Condition: a space is created
	 * Post-Condition: 
	 */
	@Test (priority=9)
	public  void test16_UpdateTaskForASpaceCalendarAddAttachment() {
		String titleTask = txData.getContentByArrayTypeRandom(1)+"115667";
		String content = txData.getContentByArrayTypeRandom(1)+"115667";
		String link = fData.getAttachFileByArrayTypeRandom(1);
		String comment = hpAct.ELEMENT_ACTIVITY_TASK_COMMENT_ATTACHMENT;
		info("Test 16 Update Task for a Space Calendar - add attachment");
		/*Step Number: 1
		 *Step Name: - create a task
		 *Step Description: 
			- Connect to intranet
			- Go to Calendar
			- Add a task for space group calendar
			- check activity stream
		 *Input Data: 

		 *Expected Outcome: 
			- task is created and an activity of task is added into activity stream*/
		info("Add a task");
		hp.goToCalendarPage();
		cMang.executeActionCalendar(spaceName, menuOfCalendarOption.ADDTASK);
		task.inputDataTaskInQuickForm(titleTask, content, getDate(0,"MM/dd/yyyy HH")+":00", getDate(0,"MM/dd/yyyy HH")+":30",false);
		task.saveQuickAddTask();
		cHome.verifyIsPresentEventTask(titleTask, selectViewOption.MONTH, selectDayOption.ONEDAY);

		/*Step number: 2
		 *Step Name: - Edit the task
		 *Step Description: 
			- Edit the task to add an attachment
			- Check activity stream
		 *Input Data: 

		 *Expected Outcome: 
			- A comment is added with the following message: Attachment(s) has been added to the task.
			- The content of activity isn't updated*/ 
		cHome.goToEditEventTaskFormByRightClick(titleTask, selectViewOption.MONTH, selectDayOption.ONEDAY,getDate(0,"MMM dd yyyy"));
		task.attachFileToTask("TestData/" + link);
		task.saveAddTaskDetails();
		
		hp.goToHomePage();
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_TASK_EVENT_COMMENT.replace("$name", titleTask).replace("$comment", comment));
		
		info("Clear data");
		hp.goToCalendarPage();
		cHome.deleteEventTask(titleTask, selectViewOption.MONTH, selectDayOption.ONEDAY,getDate(0,"MMM dd yyyy"));
	}

	/**
	 * Case ID:115668.
	 * Test Case Name: Update Task for a Space Calendar - edit note.
	 * Pre-Condition: Add a space
	 * Post-Condition: 
	 */
	@Test (priority=10)
	public  void test17_UpdateTaskForASpaceCalendarEditNote() {
		String titleTask = txData.getContentByArrayTypeRandom(1)+"115668";
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String newContent = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String comment = hpAct.ELEMENT_ACTIVITY_TASK_COMMENT_UPDATE_NOTE.replace("$note", newContent);
		
		info("Test 17 Update Task for a Space Calendar - edit note");
		/*Step Number: 1
		 *Step Name: - create a task
		 *Step Description: 
			- Connect to intranet
			- Go to Calendar
			- add a task for space group calendar
			- check activity stream
		 *Input Data: 

		 *Expected Outcome: 
			- task is created and an activity of task is added into activity stream*/
		info("Add a task");
		hp.goToCalendarPage();
		cMang.executeActionCalendar(spaceName, menuOfCalendarOption.ADDTASK);
		task.inputDataTaskInQuickForm(titleTask, content, getDate(0,"MM/dd/yyyy HH")+":00", getDate(0,"MM/dd/yyyy HH")+":30",false);
		task.saveQuickAddTask();
		cHome.verifyIsPresentEventTask(titleTask, selectViewOption.MONTH, selectDayOption.ONEDAY);

		/*Step number: 2
		 *Step Name: - Edit task
		 *Step Description: 
			- Edit task's note
		 *Input Data: 

		 *Expected Outcome: 
			- The content of task's activity is updated
			- A comment is added: 'Note has been updated to: $value.*/ 
		cHome.goToEditEventTaskFormByRightClick(titleTask, selectViewOption.MONTH, selectDayOption.ONEDAY,getDate(0,"MMM dd yyyy"));
		task.inputBasicDetailTask(null, newContent);
		task.saveAddTaskDetails();
		cHome.verifyIsPresentEventTask(titleTask, selectViewOption.DAY, selectDayOption.ONEDAY);

		hp.goToHomePage();
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_TASK_EVENT_COMMENT.replace("$name", titleTask).replace("$comment", comment));
		
		info("Clear data");
		hp.goToCalendarPage();
		cHome.deleteEventTask(titleTask, selectViewOption.MONTH, selectDayOption.ONEDAY,getDate(0,"MMM dd yyyy"));
	}

	/**
	 * Case ID:115669.
	 * Test Case Name: Update Task for a space Calendar - task status.
	 * Pre-Condition: Add a space
	 * Post-Condition: 
	 */
	@Test (priority=11)
	public  void test18_UpdateTaskForASpaceCalendarTaskStatus() {
		String titleTask = txData.getContentByArrayTypeRandom(1)+"115669";
		String content = txData.getContentByArrayTypeRandom(1)+"115669";
		String comment = hpAct.ELEMENT_ACTIVITY_TASK_COMMENT_UPDATE_STATUS_CANCEL;
		
		info("Test 18 Update Task for a space Calendar - task status");
		/*Step Number: 1
		 *Step Name: - Create a task
		 *Step Description: 
			- Connect to intranet
			- Go to Calendar
			- Add a task for space group calendar
			- Check activity stream
		 *Input Data: 

		 *Expected Outcome: 
			- Task is created and an activity of task is added into activity stream*/
		info("Add a task");
		hp.goToCalendarPage();
		cMang.executeActionCalendar(spaceName, menuOfCalendarOption.ADDTASK);
		task.inputDataTaskInQuickForm(titleTask, content, getDate(0,"MM/dd/yyyy HH")+":00", getDate(0,"MM/dd/yyyy HH")+":30",false);
		task.saveQuickAddTask();
		cHome.verifyIsPresentEventTask(titleTask, selectViewOption.MONTH, selectDayOption.ONEDAY);
		
		/*Step number: 2
		 *Step Name: - Edit task
		 *Step Description: 
			- Edit the status of task to "Completed"
			- Check activity stream
		 *Input Data: 

		 *Expected Outcome: 
			- The content of activity of the task is updated with the Finished icon.
			- A comment is added with the following message: Task has been completed.*/ 
		cHome.goToEditEventTaskFormByRightClick(titleTask, selectViewOption.MONTH, selectDayOption.ONEDAY,getDate(0,"MMM dd yyyy"));
		task.selectStatus(statusTask.CANCELLED);
		task.saveAddTaskDetails();
		cHome.verifyIsPresentEventTask(titleTask, selectViewOption.DAY, selectDayOption.ONEDAY);
		
		hp.goToHomePage();
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_TASK_EVENT_COMMENT.replace("$name", titleTask).replace("$comment", comment));
		
		info("Clear data");
		hp.goToCalendarPage();
		cHome.deleteEventTask(titleTask, selectViewOption.MONTH, selectDayOption.ONEDAY,getDate(0,"MMM dd yyyy"));
	}
	
	/**
	 * Case ID:115660.
	 * Test Case Name: Publish activity for event of Group Calendar of a Space.
	 * Case ID:115666.
	 * Test Case Name: Publish activity for Task of Group Calendar of Space.
	 * Case ID:115630.
	 * Test Case Name: Activity isn't publish for a event of Personal, Shared and other group calendars than Space.
	 * Case ID:115651.
	 * Test Case Name: Open event from activity stream.
	 * Case ID:115652.
	 * Test Case Name: Open Task's details from activity stream.
	 * Case ID:115665.
	 * Test Case Name: Delete an event of space calendar.
	 * Case ID:115670.
	 * Test Case Name: Delete task for Space Calendar.
	 * Pre-Condition: Please note : Only group calendars for spaces are publishing activities. Personal, Shared and other group calendars are not concerned.
	 * Post-Condition: 
	 */
	@Test (priority=12)
	public  void test01_02_03_04_05_06_07_VerifyActivityEventTaskOfPersonalSharedAndOtherGroupCalendarsSpace() {
		String titleEventSpace = txData.getContentByArrayTypeRandom(1)+"es115630";
		String titleEventPersonal = txData.getContentByArrayTypeRandom(1)+"ep115630";
		String titleEventGroup = txData.getContentByArrayTypeRandom(1)+"eg115630";
		String titleEventShare = txData.getContentByArrayTypeRandom(1)+"sh115630";

		String titleTaskSpace = txData.getContentByArrayTypeRandom(1)+"ts115630";
		String titleTaskPersonal = txData.getContentByArrayTypeRandom(1)+"tp115630";
		String titleTaskGroup = txData.getContentByArrayTypeRandom(1)+"tg115630";
		String titleTaskShare = txData.getContentByArrayTypeRandom(1)+"tsh115630";

		String groupCalendar="Content Management";
		String personalCalendar=fullName;
		String shareCalendar= txData.getContentByArrayTypeRandom(1)+"c115630";
		String calendarColor = "purple";
		String[] groupShare = {DATA_USER2};
		boolean[] edit = {true};

		info("Create datatest");
		hp.goToCalendarPage();
		cMang.goToMenuFromMainCalendar(menuOfMainCalendar.ADDCAL);
		cMang.inputDataInDetailTabCalendarForm(shareCalendar, shareCalendar, calendarColor);
		cMang.saveAddCalendar();
		waitForAndGetElement(cMang.ELEMENT_CALENDAR_LIST_ITEM.replace("$calendar", shareCalendar));
		cMang.shareCalendar(shareCalendar, groupShare, edit , 1);

		/*Step Number: 1
		 *Step Name: - Create a Group calendar
		 *Step Description: 
			- Login and Click [Join a Space]
			- Create a new space
		 *Input Data: 

		 *Expected Outcome: 
			- Space and Group calendar of space is created succesfully*/
		//See function setUpBeforeTest()

		/*Step number: 2
		 *Step Name: - Create new event
		 *Step Description: 
			- Go to Calendar
			- Click Add Event button
			- Input the info of event and click save
		 *Input Data: 

		 *Expected Outcome: 
			- Event is created successfully*/
		hp.goToCalendarPage();
		info("create event for space calendar");
		cMang.executeActionCalendar(spaceName, menuOfCalendarOption.ADDEVENT);
		event.inputDataEventInQuickForm(titleEventSpace, titleEventSpace, getDate(1,"MM/dd/yyyy HH")+":00", getDate(1,"MM/dd/yyyy HH")+":30",false);
		event.saveQuickAddEvent();

		info("create task for space calendar");
		cMang.executeActionCalendar(spaceName, menuOfCalendarOption.ADDTASK);
		task.inputDataTaskInQuickForm(titleTaskSpace, titleTaskSpace, getDate(0,"MM/dd/yyyy HH")+":00", getDate(0,"MM/dd/yyyy HH")+":30",false);
		task.saveQuickAddTask();

		hp.goToCalendarPage();
		info("create event for personal calendar");
		cMang.executeActionCalendar(personalCalendar, menuOfCalendarOption.ADDEVENT);
		event.inputDataEventInQuickForm(titleEventPersonal, titleEventPersonal, getDate(1,"MM/dd/yyyy HH")+":00", getDate(1,"MM/dd/yyyy HH")+":30",false);
		event.saveQuickAddEvent();

		info("create task for personal calendar");
		cMang.executeActionCalendar(personalCalendar, menuOfCalendarOption.ADDTASK);
		task.inputDataTaskInQuickForm(titleTaskPersonal, titleTaskPersonal, getDate(0,"MM/dd/yyyy HH")+":00", getDate(0,"MM/dd/yyyy HH")+":30",false);
		task.saveQuickAddTask();

		hp.goToCalendarPage();
		info("create event for group calendar");
		cMang.executeActionCalendar(groupCalendar, menuOfCalendarOption.ADDEVENT);
		event.inputDataEventInQuickForm(titleEventGroup, titleEventGroup, getDate(1,"MM/dd/yyyy HH")+":00", getDate(1,"MM/dd/yyyy HH")+":30",false);
		event.saveQuickAddEvent();

		info("create task for group calendar");
		cMang.executeActionCalendar(groupCalendar, menuOfCalendarOption.ADDTASK);
		task.inputDataTaskInQuickForm(titleTaskGroup, titleTaskGroup, getDate(0,"MM/dd/yyyy HH")+":00", getDate(0,"MM/dd/yyyy HH")+":30",false);
		task.saveQuickAddTask();

		hp.goToCalendarPage();
		info("create event for share calendar");
		cMang.executeActionCalendar(shareCalendar, menuOfCalendarOption.ADDEVENT);
		event.inputDataEventInQuickForm(titleEventShare, titleEventShare, getDate(1,"MM/dd/yyyy HH")+":00", getDate(1,"MM/dd/yyyy HH")+":30",false);
		event.saveQuickAddEvent();

		info("create task for share calendar");
		cMang.executeActionCalendar(shareCalendar, menuOfCalendarOption.ADDTASK);
		task.inputDataTaskInQuickForm(titleTaskShare, titleTaskShare, getDate(0,"MM/dd/yyyy HH")+":00", getDate(0,"MM/dd/yyyy HH")+":30",false);
		task.saveQuickAddTask();

		/*Step number: 3
		 *Step Name: - Check Activity stream
		 *Step Description: 
			- Goto Homepage and check activity stream
		 *Input Data: 

		 *Expected Outcome: 
			- The activity of creating Event is added and displayed in the activity stream*/
		hp.goToHomePage();
		info("Test 1: Publish activity for event of Group Calendar of a Space");
		info("Event of space calendar are displayed on homepage activity");
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_TASK_EVENT_TITLE.replace("$name", titleEventSpace));
		
		info("Test 2 Publish activity for Task of Group Calendar of Space");
		info("Task of space calendar are displayed on homepage activity");
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_TASK_EVENT_TITLE.replace("$name", titleTaskSpace));

		info("Test 3: Activity isn't publish for a event of Personal, Shared and other group calendars than Space");
		info("Event/task of personal/group/share calendar are NOT displayed on homepage activity");
		waitForElementNotPresent(hpAct.ELEMENT_ACTIVITY_TASK_EVENT_TITLE.replace("$name", titleEventPersonal));
		waitForElementNotPresent(hpAct.ELEMENT_ACTIVITY_TASK_EVENT_TITLE.replace("$name", titleEventGroup));
		waitForElementNotPresent(hpAct.ELEMENT_ACTIVITY_TASK_EVENT_TITLE.replace("$name", titleEventShare));

		waitForElementNotPresent(hpAct.ELEMENT_ACTIVITY_TASK_EVENT_TITLE.replace("$name", titleTaskPersonal));
		waitForElementNotPresent(hpAct.ELEMENT_ACTIVITY_TASK_EVENT_TITLE.replace("$name", titleTaskGroup));
		waitForElementNotPresent(hpAct.ELEMENT_ACTIVITY_TASK_EVENT_TITLE.replace("$name", titleTaskShare));

		info("Test 4: Open event from activity stream");
		/*Step number: 2
		 *Step Name: 
		 *Step Description: 
			- Click on Title of the event
		 *Input Data: 

		 *Expected Outcome: 
			Space calendar opens with event details opened.*/ 
		click(hpAct.ELEMENT_ACTIVITY_TASK_EVENT_TITLE.replace("$name", titleEventSpace));
		waitForAndGetElement(cHome.ELEMENT_PREVIEW_TASK_EVENT_NAME.replace("$name", titleEventSpace));
		click(cHome.ELEMENT_CLOSE_PREVIEW_TASK_EVENT_FORM);

		info("Test 5: Open Task's details from activity stream");
		/*Step Number: 1
		 *Step Name: 
		 *Step Description: 
			- Connect to Intranet
			- From activity stream, click on "Task's name" from task's activity
		 *Input Data: 

		 *Expected Outcome: 
			- Return Space calendar with task details is opened*/ 
		hp.goToHomePage();
		click(hpAct.ELEMENT_ACTIVITY_TASK_EVENT_TITLE.replace("$name", titleTaskSpace));
		waitForAndGetElement(cHome.ELEMENT_PREVIEW_TASK_EVENT_NAME.replace("$name", titleTaskSpace));
		click(cHome.ELEMENT_CLOSE_PREVIEW_TASK_EVENT_FORM);
		
		info("Test 6 Delete an event of space calendar");
		/*Step number: 2
		 *Step Name: - Delete event
		 *Step Description: 
			- Right click on event and select delete
			- Click ok to confirm
			- Check activity stream
		 *Input Data: 

		 *Expected Outcome: 
			- The event's activity is removed from the activity stream*/
		hp.goToHomePage();
		click(hpAct.ELEMENT_ACTIVITY_TASK_EVENT_TITLE.replace("$name", titleEventSpace));
		waitForAndGetElement(cHome.ELEMENT_PREVIEW_TASK_EVENT_NAME.replace("$name", titleEventSpace));
		click(cHome.ELEMENT_CLOSE_PREVIEW_TASK_EVENT_FORM);
		waitForElementNotPresent(cHome.ELEMENT_PREVIEW_TASK_EVENT_NAME.replace("$name", titleEventSpace));
		cHome.deleteEventTask(titleEventSpace, selectViewOption.MONTH, selectDayOption.ONEDAY,getDate(1,"MMM dd yyyy"),false,false);

		hp.goToHomePage();
		info("Event activit of space calendar is removed from homepage activity");
		waitForElementNotPresent(hpAct.ELEMENT_ACTIVITY_TASK_EVENT_TITLE.replace("$name", titleEventSpace));

		info("Test 7 Delete task for Space Calendar");
		/*Step number: 2
		 *Step Name: - Delete task
		 *Step Description: 
			- Delete the task
			- Check activity stream
		 *Input Data: 

		 *Expected Outcome: 
			The task's activity is removed from activity stream*/ 
		hp.goToHomePage();
		click(hpAct.ELEMENT_ACTIVITY_TASK_EVENT_TITLE.replace("$name", titleTaskSpace));
		waitForAndGetElement(cHome.ELEMENT_PREVIEW_TASK_EVENT_NAME.replace("$name", titleTaskSpace));
		click(cHome.ELEMENT_CLOSE_PREVIEW_TASK_EVENT_FORM);
		waitForElementNotPresent(cHome.ELEMENT_PREVIEW_TASK_EVENT_NAME.replace("$name", titleTaskSpace));
		cHome.deleteEventTask(titleTaskSpace, selectViewOption.MONTH, selectDayOption.ONEDAY,getDate(0,"MMM dd yyyy"),false,false);

		hp.goToHomePage();
		info("Event activit of space calendar is removed from homepage activity");
		waitForElementNotPresent(hpAct.ELEMENT_ACTIVITY_TASK_EVENT_TITLE.replace("$name", titleTaskSpace));

		info("Clear data");
		hp.goToCalendarPage();
		cMang.deleteCalendar(shareCalendar,false);
		
		hp.goToCalendarPage();
		cHome.deleteEventTask(titleEventPersonal, selectViewOption.MONTH, selectDayOption.ONEDAY,getDate(1,"MMM dd yyyy"),true,true);
		cHome.deleteEventTask(titleEventGroup, selectViewOption.MONTH, selectDayOption.ONEDAY,getDate(1,"MMM dd yyyy"),true,true);

		cHome.deleteEventTask(titleTaskPersonal, selectViewOption.MONTH, selectDayOption.ONEDAY,getDate(0,"MMM dd yyyy"),false,false);
		cHome.deleteEventTask(titleTaskGroup, selectViewOption.MONTH, selectDayOption.ONEDAY,getDate(0,"MMM dd yyyy"),false,false);
	}

}
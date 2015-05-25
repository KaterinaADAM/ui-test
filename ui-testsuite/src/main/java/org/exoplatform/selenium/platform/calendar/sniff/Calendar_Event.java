package org.exoplatform.selenium.platform.calendar.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.platform.HomePagePlatform;
import org.exoplatform.selenium.platform.ManageLogInOut;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.calendar.EventManagement;
import org.exoplatform.selenium.platform.calendar.EventManagement.selectAvailableOption;
import org.exoplatform.selenium.platform.calendar.CalendarHomePage;
import org.exoplatform.selenium.platform.calendar.CalendarManagement;
import org.exoplatform.selenium.platform.calendar.CalendarHomePage.selectDayOption;
import org.exoplatform.selenium.platform.calendar.CalendarHomePage.selectViewOption;
import org.exoplatform.selenium.platform.calendar.CalendarManagement.menuOfCalendarOption;
import org.exoplatform.selenium.platform.calendar.CalendarManagement.menuOfMainCalendar;
import org.exoplatform.selenium.platform.objectdatabase.common.AttachmentFileDatabase;
import org.exoplatform.selenium.platform.objectdatabase.common.TextBoxDatabase;
import org.exoplatform.selenium.platform.objectdatabase.user.UserDatabase;
import org.openqa.selenium.By;
import org.testng.annotations.*;


public class Calendar_Event extends PlatformBase {
	HomePagePlatform hp;
	ManageLogInOut magAc;
	TextBoxDatabase txData;
	CalendarHomePage cHome;
	EventManagement event;
	AttachmentFileDatabase fData;
	CalendarManagement cMang;
	UserDatabase userData;
	String fullName;

	@BeforeMethod
	public void setUpBeforeMethod() throws Exception{
		magAc.signIn(DATA_USER1, DATA_PASS);
	}

	@BeforeClass
	public void setUpBeforeTest() throws Exception{
		initSeleniumTest();
		getDefaultUserPass(userDataFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		driver.get(baseUrl);
		magAc = new ManageLogInOut(driver);
		hp = new HomePagePlatform(driver);
		cHome= new CalendarHomePage(driver);
		event= new EventManagement(driver);
		cMang = new CalendarManagement(driver);
		txData = new TextBoxDatabase();
		fData = new AttachmentFileDatabase();
		userData = new UserDatabase();
		userData.setUserData(userDataFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		txData.setContentData(texboxFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlContent);
		fData.setAttachFileData(attachmentFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		fullName = userData.fullName.get(0);
	}

	@AfterMethod
	public void afterMethod(){
		magAc.signOut();
	}

	@AfterClass
	public void afterTest(){
		driver.manage().deleteAllCookies();
		driver.quit();
	}
	/**
	 * Case ID:115621.
	 * Test Case Name: Add attachment to event.
	 * Case ID:115620.
	 * Test Case Name: Remove attachment of event.
	 * Pre-Condition: Event with attachment is exist
	 * Post-Condition: 
	 */
	@Test(priority=4)
	public  void test01_02_AddRemoveAttachmentOfEvent() {
		String titleEvent = txData.getContentByArrayTypeRandom(1)+"115621";
		String content = txData.getContentByArrayTypeRandom(1)+"115621";
		String link = fData.getAttachFileByArrayTypeRandom(2);
		if ("iexplorer".equals(browser) || "chrome".equals(browser)){
			info("Test 1: Add attachment to event on IE and chrome manually");
		}else{
			info("Test 1: Add attachment to event");

			/*Step Number: 1
			 *Step Name: Step 1: Open Add event form
			 *Step Description: 
			- Select a calendar, Click Setting icon of this calendar and choose [Add Event] or Click Event button on action bar
			- Input start and end time
			- Click [More Details
			 *Input Data: 

			 *Expected Outcome: 
			Add/Edit Event pop
			-up has 4 tabs 
			- Details
			- Reminders
			- Participants
			- Schedule*/
			info("Add a event");
			hp.goToCalendarPage();
			cMang.goToMenuFromMainCalendar(menuOfMainCalendar.CALSETTING);
			cMang.changeSettingCalendar(null,"(GMT +07:00) Asia/Ho_Chi_Minh",null,null,null,null,null);
			cMang.saveSetting();
			event.goToAddEventFromActionBar();
			event.moreDetailsEvent();

			/*Step number: 2
			 *Step Name: Step 2: Add attachment
			 *Step Description: 
			- Click [Add Attachment]
			- Browse to file and click save
			 *Input Data: 

			 *Expected Outcome: 
			- Attachment is added to event*/ 
			info("Add attachment");
			event.inputDataEventInDetailForm(titleEvent, content, getDate(0,"MM/dd/yyyy"), getDate(0,"MM/dd/yyyy"), false);
			event.attachFileToEvent(link);
			event.saveAddEventDetails();
			cHome.verifyIsPresentEventTask(titleEvent, selectViewOption.DAY, selectDayOption.ONEDAY);

			info("Test 2: Remove attachment of event");
			/*Step Number: 1
			 *Step Name: Step 1: Open edit event form
			 *Step Description: 
			- Go to calendar
			- Select event which has attachment and edit
			 *Input Data: 

			 *Expected Outcome: 
			- Edit form appears*/

			/*Step number: 2
			 *Step Name: Step 2: Remove attachment
			 *Step Description: 
			- Click [Delete] icon
			- Click Save
			 *Input Data: 

			 *Expected Outcome: 
			- Attachment is deleted*/ 
			info("Remove the attachment");
			cHome.goToEditEventTaskFormByRightClick(titleEvent, selectViewOption.DAY, selectDayOption.ONEDAY,null);
			event.removeAttachment(link);
			event.saveAddEventDetails();

			info("Delete data");
			cHome.deleteEventTask(titleEvent, selectViewOption.DAY, selectDayOption.ONEDAY,null);
		}
	}

	/**
	 * Case ID:115622.
	 * Test Case Name: Check busy time on schedule tab.
	 * Pre-Condition: 
	 * Post-Condition: 
	 */
	@Test(priority=1)
	public  void test03_CheckBusyTimeOnScheduleTab() {
		String titleEvent = txData.getContentByArrayTypeRandom(1)+"115622";
		String content = txData.getContentByArrayTypeRandom(1)+"115622";

		info("Test 3: Check busy time on schedule tab");
		/*Step Number: 1
		 *Step Name: Step 1: Add new event
		 *Step Description: 
			- Login as John for ex
			- Go to Calendar
			- Click [Event] or right click 
			-
			-> [New Event]
			- Input start and end time: from 9h00 to 10h00 for example
			- Click Save
		 *Input Data: 

		 *Expected Outcome: 
			- New event is added successfully*/
		info("Add a event");
		hp.goToCalendarPage();		
		cMang.goToMenuFromMainCalendar(menuOfMainCalendar.CALSETTING);
		cMang.changeSettingCalendar(null,"(GMT +07:00) Asia/Ho_Chi_Minh",null,null,null,null,null);
		cMang.saveSetting();
		event.goToAddEventFromActionBar();
		event.inputDataEventInQuickForm(titleEvent, content, getDate(0,"MM/dd/yyyy HH")+":00", getDate(0,"MM/dd/yyyy HH")+":30",false);
		event.saveQuickAddEvent();
		cHome.verifyIsPresentEventTask(titleEvent, selectViewOption.LIST, selectDayOption.ONEDAY);

		/*Step number: 2
		 *Step Name: Step 2: Add participant to event
		 *Step Description: 
			- Login as other user (Jack for ex)
			- Go to Calendar
			- Click [Event] or right click, select [Event]
			- Click [More details], open Schedule tab
			- Click Add participant and select John to add
		 *Input Data: 

		 *Expected Outcome: 
			- John is displayed in list with busy time is same as event created at step 1*/ 
		magAc.signOut();
		magAc.signIn(DATA_USER2, DATA_PASS);
		hp.goToCalendarPage();
		event.goToAddEventFromActionBar();
		event.inputDataEventInQuickForm(titleEvent, content, getDate(0,"MM/dd/yyyy"), getDate(0,"MM/dd/yyyy"),true);
		event.moreDetailsEvent();
		click(event.ELEMENT_EVENT_SCHEDULE_TAB);
		event.addParticipants(DATA_USER1, 1);
		event.checkBusyTimeOfUser(DATA_USER1, getDate(0,"HH")+":00", getDate(0,"HH")+":30");

		info("clear data");
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		hp.goToCalendarPage();
		cHome.deleteEventTask(titleEvent, selectViewOption.LIST, selectDayOption.ONEDAY,getDate(0,"MM/dd/yyyy"));
	}

	/**
	 * Case ID:115623.
	 * Test Case Name: Edit schedule of event.
	 * Pre-Condition: 
	 * Post-Condition: 
	 */
	@Test(priority=3)
	public  void test04_EditScheduleOfEvent() {
		String titleEvent = txData.getContentByArrayTypeRandom(1)+"115623";
		String content = txData.getContentByArrayTypeRandom(1)+"115623";
		info("Test 4: Edit schedule of event");
		/*Step Number: 1
		 *Step Name: Step 1: Open add/edit event pop up
		 *Step Description: 
			- Select a calendar, Click Setting icon of this calendar and choose [Add Event] or Click Event button on action bar
			- Input start and end time
			- Click [More Details]
		 *Input Data: 

		 *Expected Outcome: 
			Add/Edit Event pop
			-up has 4 tabs 
			- Details
			- Reminders
			- Participants
			- Schedule*/
		info("Add a event");
		hp.goToCalendarPage();
		event.goToAddEventFromActionBar();
		event.inputDataEventInQuickForm(titleEvent, content, getDate(0,"MM/dd/yyyy"), getDate(0,"MM/dd/yyyy"),false);
		info("Check default time ");
		event.checkSuggestionEventTimeInQuickForm(null,null, 60);
		event.moreDetailsEvent();

		/*Step number: 2
		 *Step Name: Step 2: Add aprticipant
		 *Step Description: 
			- Click [Schedule] tab
			- Change time of event on schedule tab
		 *Input Data: 

		 *Expected Outcome: 
			- Schedule is updated*/ 
		click(event.ELEMENT_EVENT_SCHEDULE_TAB);
		event.changeTimeEventFromScheduleTab(selectArrowOption.NEXT, getDate(1,"HH")+":00", getDate(1,"HH")+":30");
		click(event.ELEMENT_EVENT_DETAILS_TAB);
		event.checkSuggestionEventTimeInDetailForm(getDate(1,"MM/dd/yyyy HH")+":00",getDate(1,"MM/dd/yyyy HH")+":30", 30);
	}

	/**
	 * Case ID:115624.
	 * Test Case Name: Check start/end time of event on schedule.
	 * Pre-Condition: 
	 * Post-Condition: 
	 */
	@Test(priority=2)
	public  void test05_CheckStartEndTimeOfEventOnSchedule() {
		String titleEvent = txData.getContentByArrayTypeRandom(1)+"115624";
		String contentEvent = txData.getContentByArrayTypeRandom(1)+"115624";
		info("Test 5: Check start/end time of event on schedule");
		/*Step Number: 1
		 *Step Name: Step 1: Open add/edit event pop up
		 *Step Description: 
			- Select a calendar, Click Setting icon of this calendar and choose [Add Event] or Click Event button on action bar
			- Input start and end time
			- Click [More Details]
		 *Input Data: 

		 *Expected Outcome: 
			Add/Edit Event pop
			-up has 4 tabs 
			- Details
			- Reminders
			- Participants
			- Schedule*/
		info("Add a event");
		hp.goToCalendarPage();
		event.goToAddEventFromActionBar();
		event.inputDataEventInQuickForm(titleEvent, contentEvent, getDate(0,"MM/dd/yyyy HH")+":00", getDate(0,"MM/dd/yyyy HH")+":30",false);
		event.moreDetailsEvent();

		/*Step number: 2
		 *Step Name: Step 2: Add aprticipant
		 *Step Description: 
			- Click [Schedule] tab
			- Check time on schedule tab
		 *Input Data: 

		 *Expected Outcome: 
			- Time on schedule tab is correct (input at step 1)*/ 
		click(event.ELEMENT_EVENT_SCHEDULE_TAB);
		event.checkScheduleTimeOfUser(getDate(0,"MM/dd/yyyy HH")+":00", getDate(0,"MM/dd/yyyy HH")+":30");
		event.cancelAddEditDetailEvent();
	}

	/**
	 * Case ID:115625.
	 * Test Case Name: Check privacy.
	 * Pre-Condition: 
	 * Post-Condition: 
	 */
	@Test(priority=5)
	public  void test06_CheckPrivacy() {
		String titleEvent = txData.getContentByArrayTypeRandom(1)+"115625";
		String content = txData.getContentByArrayTypeRandom(1)+"115625";

		info("Test 6: Check privacy");
		/*Step Number: 1
		 *Step Name: Step 1: Open add/edit event pop up
		 *Step Description: 
			- Select a calendar, Click Setting icon of this calendar and choose [Add Event] or Click Event button on action bar
			- Click [More Details]
		 *Input Data: 

		 *Expected Outcome: 
			Add/Edit Event pop
			-up has 4 tabs 
			- Details
			- Reminders
			- Participants
			- Schedule*/
		info("Add a event");
		hp.goToCalendarPage();
		event.goToAddEventFromActionBar();
		event.inputDataEventInQuickForm(titleEvent, content, getDate(0,"MM/dd/yyyy"), getDate(0,"MM/dd/yyyy"),false);
		event.moreDetailsEvent();

		/*Step number: 2
		 *Step Name: Step 2: Add aprticipant
		 *Step Description: 
			- Click [Participants] tab
			- Select Privacy option 
			- Click Save
		 *Input Data: 

		 *Expected Outcome: 
			- Event is saved with new privacy option*/ 
		click(event.ELEMENT_EVENT_PARTICIPANTS_TAB);
		event.selectPrivacyParticipant(true);
		event.saveAddEventDetails();
		cHome.verifyIsPresentEventTask(titleEvent, selectViewOption.LIST, selectDayOption.ONEDAY);

		info("Clear data");
		hp.goToCalendarPage();
		cHome.deleteEventTask(titleEvent, selectViewOption.LIST, selectDayOption.ONEDAY,getDate(0,"MM/dd/yyyy"));
	}

	/**
	 * Case ID:115626.
	 * Test Case Name: Check date suggestion.
	 * Pre-Condition: Time suggestion is set on configuration.properties#auto suggest the end of event time about 1 hourexo.calendar.default.event.suggest=2
	 * Post-Condition: 
	 */
	@Test(priority=6)
	public  void test07_CheckDateSuggestion() {
		String from = getDate(0,"MM/dd/yyyy HH")+":00";
		info("Test 7: Check date suggestion");
		/*Step Number: 1
		 *Step Name: Step1: Check date suggestion when add event by action bar
		 *Step Description: 
			- Go to Calendar
			- Click [Event] on action bar
			- Select a from time
		 *Input Data: 

		 *Expected Outcome: 
			- Open quick add event form with To time = From time + 1 hour
			- When set a new From time, To time = From time + 1 hour
			- From date = To date = Current date*/
		hp.goToCalendarPage();
		event.goToAddEventFromActionBar();
		info("Check default time ");
		event.checkSuggestionEventTimeInQuickForm(null,null, 60);
		event.cancelQuickAddEditEvent();

		/*Step number: 2
		 *Step Name: Step2: Check date suggestion when add event on a calendar
		 *Step Description: 
			- Add new calendar
			- On calendar, click setting icon then choose [Add Event]
			- Select From time
		 *Input Data: 

		 *Expected Outcome: 
			- Open quick add event form with To time = From time + 1 hour
			- When set a new From time, To time = From time + 1 hour
			- From date = To date = current date*/
		hp.goToCalendarPage();
		cMang.executeActionCalendar(fullName, menuOfCalendarOption.ADDEVENT);

		info("Check default date");
		event.checkSuggestionEventTimeInQuickForm(null,null, 60);
		event.cancelQuickAddEditEvent();

		/*Step number: 3
		 *Step Name: Setp3: Check date suggestion when add event by left click on main panel
		 *Step Description: 
			- Left click on a cell on main panel on Week view/Daily view
			- Select From time
		 *Input Data: 

		 *Expected Outcome: 
			- Open quick add event form with To time = From time + 30 minus (by a block time)
			- When set new From time, To time = From time + 1 hour
			- From date = To date = Date of click*/
		cHome.goToView(selectViewOption.WEEK);
		event.goToAddEventByLeftClickFromMainPanel("","");
		info("Check default time ");
		event.checkSuggestionEventTimeInQuickForm(null,null, 30);
		event.inputFromToQuickEvent(from, "", false);
		event.checkSuggestionEventTimeInQuickForm(null,null, 60);
		event.cancelQuickAddEditEvent();

		/*Step number: 4
		 *Step Name: Step4: Check date suggestion when add event by right click on main panel
		 *Step Description: 
			- Right click on cell on main panel then click [Add Event]
		 *Input Data: 

		 *Expected Outcome: 
			- Open quick add event form with to time = from time + 1 hour
			- When set new from time, to time alway = to time + 1 hour
			- From date = To date = current date*/ 
		event.goToAddEventByRightClickFromMainPanel("","");
		info("Check default time ");
		event.checkSuggestionEventTimeInQuickForm(null,null,60);
		event.cancelQuickAddEditEvent();
	}

	/**
	 * Case ID:115627.
	 * Test Case Name: Send invitation.
	 * Pre-Condition: Mail is configuredEmail of participant is valid to receive mail
	 * Post-Condition: 
	 * PENDING: Should verify link by checking manual
	 */
	@Test(groups="pending")
	public  void test08_SendInvitation() {
		String titleEvent = txData.getContentByArrayTypeRandom(1)+"115627";
		String contentEvent = txData.getContentByArrayTypeRandom(1)+"115627";
		String content = txData.getContentByArrayTypeRandom(1)+"115627";
		info("Test 8: Send invitation");
		/*Step Number: 1
		 *Step Name: Step 1: Open add/edit event pop up
		 *Step Description: 
			- Select a calendar, Click Setting icon of this calendar and choose [Add Event] or Click Event button on action bar
			- Click [More Details]
		 *Input Data: 

		 *Expected Outcome: 
			Add/Edit Event pop
			-up has 4 tabs 
			- Details
			- Reminders
			- Participants
			- Schedule*/
		info("Add a event");
		hp.goToCalendarPage();
		event.goToAddEventFromActionBar();
		event.inputDataEventInQuickForm(titleEvent, contentEvent, getDate(0,"MM/dd/yyyy"), getDate(0,"MM/dd/yyyy"),false);
		event.moreDetailsEvent();

		/*Step number: 2
		 *Step Name: Step 2: Add aprticipant
		 *Step Description: 
			- Click [Participants] tab
			- Select Privacy, Available 
			- Add aparticipant
			- Select 1 option to Send Invitations, eg Always
			- Save
		 *Input Data: 

		 *Expected Outcome: 
			Automatically send the invitation mail to the participants.Their statuses will be updated in the Status column after they have answered the invitations via emails.If the participants agree to participate (by clicking Yes in their received invitation emails), their statuses will be yes.If the participants do not agree to participate (by clicking No), their statuses will be no.If the participants have not decided to take part in the event (by clicking Not sure), their statuses will be pending.*/ 
		click(event.ELEMENT_EVENT_PARTICIPANTS_TAB);
		event.selectPrivacyParticipant(false);
		event.selectAvailable(selectAvailableOption.AVAILABLE);
		event.selectUserParticipants(DATA_USER2, content,0);
		click(event.ELEMETN_INVITATION_SAVE_BUTTON);
		event.selectSendInvitation(selectInvitationOption.ALWAYS);
		event.saveAddEventDetails();
		cHome.verifyIsPresentEventTask(titleEvent, selectViewOption.WEEK, selectDayOption.ONEDAY);

		info("Clear data");
		cHome.deleteEventTask(titleEvent, selectViewOption.WEEK, selectDayOption.ONEDAY,getDate(0,"MMM dd yyyy"));
	}

	/**
	 * Case ID:115628.
	 * Test Case Name: Add a participant.
	 * Pre-Condition: 
	 * Post-Condition: 
	 */
	@Test(priority=7)
	public  void test09_AddAParticipant() {
		String titleEvent = txData.getContentByArrayTypeRandom(1)+"115628";
		String contentEvent = txData.getContentByArrayTypeRandom(1)+"115628";
		String content = txData.getContentByArrayTypeRandom(1)+"115628";
		String users = DATA_USER2+"/"+DATA_USER3;

		info("Test 9: Add a participant");
		/*Step Number: 1
		 *Step Name: Step 1: Open add/edit event pop up
		 *Step Description: 
			- Select a calendar, Click Setting icon of this calendar and choose [Add Event] or Click Event button on action bar
			- Click [More Details]
		 *Input Data: 

		 *Expected Outcome: 
			Add/Edit Event pop
			-up has 4 tabs 
			- Details
			- Reminders
			- Participants
			- Schedule*/
		info("Add a event");
		hp.goToCalendarPage();
		event.goToAddEventFromActionBar();
		event.inputDataEventInQuickForm(titleEvent, contentEvent, getDate(0,"MM/dd/yyyy"), getDate(0,"MM/dd/yyyy"),false);
		event.moreDetailsEvent();

		/*Step number: 2
		 *Step Name: Step 2: Add aprticipant
		 *Step Description: 
			- Click [Participants] tab
			- Select Privacy, Available 
			- Click [Add Participant] icon (+)
			- Pick some users
			- Fill Invitation message
			- Click Save
			- Select 1 option for Send Invitations
			- Save
		 *Input Data: 

		 *Expected Outcome: 
			- Selected users are listed on the table with 3 column + Name: participant fullname + Information: participant email + Status: participant status yes/no/not sure + Cction with remove icon
			- Event is created with added participants*/ 
		click(event.ELEMENT_EVENT_PARTICIPANTS_TAB);
		event.selectPrivacyParticipant(false);
		event.selectAvailable(selectAvailableOption.AVAILABLE);
		event.click(event.ELEMENT_INVITATION_PARTICITPANT_USER);
		event.selectUserParticipants(users, content,0);
		click(event.ELEMETN_INVITATION_SAVE_BUTTON);
		event.selectSendInvitation(selectInvitationOption.NEVER);
		event.saveAddEventDetails();
		cHome.verifyIsPresentEventTask(titleEvent, selectViewOption.LIST, selectDayOption.ONEDAY);

		info("Clear data");
		cHome.deleteEventTask(titleEvent, selectViewOption.LIST, selectDayOption.ONEDAY,getDate(0,"MM/dd/yyyy"));
	}

	/**
	 * Case ID:115629.
	 * Test Case Name: [More Details] add/edit event popup.
	 * Pre-Condition: 
	 * Post-Condition: 
	 */
	@Test(priority=8)
	public  void test10_MoreDetailsAddeditEventPopup() {
		info("Test 10 [More Details] add/edit event popup");
		/*Step Number: 1
		 *Step Name: Step 1: Check quick add event form
		 *Step Description: 
			- Select a calendar, Click Setting icon of this calendar and choose [Add Event] or Click Event button on action bar
		 *Input Data: 

		 *Expected Outcome: 
			- The pop up "Quick Add Event" is displayed with fields + Title + Description + From + To + All day checkbox + Calendar + Event Category*/
		info("Add a event");
		hp.goToCalendarPage();
		event.goToAddEventFromActionBar();

		/*Step number: 2
		 *Step Name: Step 2: View more detail
		 *Step Description: 
			- Click [More Details]
		 *Input Data: 

		 *Expected Outcome: 
			Add/Edit Event pop
			-up has 4 tabs 
			- Details
			- Reminders
			- Participants
			- Schedule*/
		event.moreDetailsEvent();

		/*Step number: 3
		 *Step Name: Step 3: View details tab
		 *Step Description: 
			- View Details tab
		 *Input Data: 

		 *Expected Outcome: 
			Detail tab with fields
			- Title:
			- Description:
			- Location:
			- From:
			- To:
			- All Day checkbox
			- Priority:
			- Repeat:
			- Calendar:
			- Event Category:
			- Files: Attachment*/
		waitForAndGetElement(event.ELEMENT_ADD_EDIT_EVENT_NAME);
		waitForAndGetElement(event.ELEMENT_ADD_EDIT_EVENT_NOTE);
		waitForAndGetElement(event.ELEMENT_ADD_EDIT_EVENT_LOCATION);
		waitForAndGetElement(event.ELEMENT_ADD_EDIT_EVENT_CALENDAR);
		waitForAndGetElement(event.ELEMENT_ADD_EDIT_EVENT_CATEGORY);
		waitForAndGetElement(event.ELEMENT_ADD_EDIT_EVENT_PRIORITY);
		waitForAndGetElement(event.ELEMENT_ADD_EDIT_EVENT_ALLDAY,5000,1,2);
		waitForAndGetElement(event.ELEMENT_ADD_EDIT_EVENT_FROM_DATE);
		waitForAndGetElement(event.ELEMENT_ADD_EDIT_EVENT_TO_DATE);
		waitForAndGetElement(event.ELEMENT_ADD_EDIT_INPUT_EVENT_FROM_TIME,5000,1,2);
		waitForAndGetElement(event.ELEMENT_ADD_EDIT_INPUT_EVENT_TO_TIME,5000,1,2);
		waitForAndGetElement(event.ELEMENT_ADD_EDIT_EVENT_REPEAT_CHECKBOX,5000,1,2);
		waitForAndGetElement(event.ELEMENT_EVENT_ADD_ATTACHMENT);

		/*Step number: 4
		 *Step Name: Step 4: View Reminder tab
		 *Step Description: 
			View Reminder tab
		 *Input Data: 

		 *Expected Outcome: 
			Reminder tab includes:
			- Remind by Email
			- Display a notification pop
			-up*/
		click(event.ELEMENT_EVENT_REMINDER_TAB);
		waitForAndGetElement(event.ELEMENT_REMINDER_BY_POPUP,5000,1,2);
		waitForAndGetElement(event.ELEMENT_REMINDER_BY_MAIL,5000,1,2);

		/*Step number: 5
		 *Step Name: Step 5: View Participants tab
		 *Step Description: 
			View Participants tab
		 *Input Data: 

		 *Expected Outcome: 
			Participants tab includes:
			- Privacy
			- Available
			- Add participant
			- Send Invitations*/
		click(event.ELEMENT_EVENT_PARTICIPANTS_TAB);
		waitForAndGetElement(event.ELEMENT_PRIVACY_PUBLIC_CHECKBOX,5000,1,2);
		waitForAndGetElement(event.ELEMENT_PRIVACY_PRIVATE_CHECKBOX,5000,1,2);
		waitForAndGetElement(event.ELEMENT_AVAILABLE_CHECKBOX,5000,1,2);
		waitForAndGetElement(event.ELEMENT_BUSY_CHECKBOX,5000,1,2);
		waitForAndGetElement(event.ELEMENT_OUTSIDE_CHECKBOX,5000,1,2);
		waitForAndGetElement(event.ELEMENT_SEND_INVITATION_NEVER_CHECKBOX,5000,1,2);
		waitForAndGetElement(event.ELEMENT_SEND_INVITATION_ALWAYS_CHECKBOX,5000,1,2);
		waitForAndGetElement(event.ELEMENT_SEND_INVITATION_ASK_CHECKBOX,5000,1,2);
		waitForAndGetElement(event.ELEMENT_ADD_PARTICIPANTS_BUTTON_IN_PARTICIPANT_TAB);

		/*Step number: 6
		 *Step Name: Step 5: View Schedule tab
		 *Step Description: 
			Step 5: View Schedule tab
		 *Input Data: 

		 *Expected Outcome: 
			Schedule tab includes a schedule table listing available/busy time of participants*/
		click(event.ELEMENT_EVENT_SCHEDULE_TAB);
		waitForAndGetElement(event.ELEMENT_ADD_PARTICIPANTS_BUTTON_IN_SCHEDULE_TAB);
		event.cancelAddEditDetailEvent();

	}

	/**
	 * Case ID:115645.
	 * Test Case Name: Check pop-up reminder.
	 * Pre-Condition: - Setting calendar time zone is your time zone (for Vietnam: GMT+7, Asia/Ho_Chi_Minh)
	 * Post-Condition: 
	 * PENDING: Refer to : https://jira.exoplatform.org/browse/FQA-1352
	 */
	@Test(groups="pending")
	public  void test11_CheckPopupReminder() {
		info("Test 11 Check pop-up reminder");
		/*Step Number: 1
		 *Step Name: Step 1: Check pop up reminder of an event
		 *Step Description: 
			- Add event
			- Input Title of the event
			- Click More Details
			- In Reminders tab, check [Show a notification popup]
			- set [When the event starts in next] value to 5 minutes
			- Click Save
		 *Input Data: 

		 *Expected Outcome: 
			- Event with pop up reminder is created successfully
			- Reminder popup is shown at the time 5 minutes before the event happens*/ 

	}

	/**
	 * Case ID:115646.
	 * Test Case Name: Check email reminder.
	 * Pre-Condition: - Setting calendar time zone is your time zone (for Vietnam: GMT+7, Asia/Ho_Chi_Minh)
	 * Post-Condition: 
	 * PENDING: Refer to : https://jira.exoplatform.org/browse/FQA-1352
	 */
	@Test(groups="pending")
	public  void test12_CheckEmailReminder() {
		info("Test 12 Check email reminder");
		/*Step Number: 1
		 *Step Name: Step 1: Check mail reminder of an event
		 *Step Description: 
			In Add new event form:
			- Input Event Summary 
			- Click More Details
			- In Reminders tab, check [Remind by email]
			- Set [Send an email before the event starts in] to 5 minutes
			- Add some valid emails of some user who will receive reminder
			- Click Save
		 *Input Data: 

		 *Expected Outcome: 
			- Event with mail reminder is created successfully
			- Check the mailbox of user who receive reminder that the mail is sent at the right time*/ 

	}

	/**
	 * Case ID:115684.
	 * Test Case Name: Add an event in personal calendar.
	 * Case ID:115677.
	 * Test Case Name: Edit an Event in personal calendar.
	 * Case ID:115678.
	 * Test Case Name: Delete an Event in personal calendar.
	 * Pre-Condition: 
	 * Post-Condition: 
	 */
	@Test(priority=9)
	public  void test13_14_15_AddEditDeleteAnEventInPersonalCalendar() {
		String titleEvent = txData.getContentByArrayTypeRandom(1)+"1115684";
		String contentEvent = txData.getContentByArrayTypeRandom(1)+"1115684";
		String titleEvent2= txData.getContentByArrayTypeRandom(1)+"2115684";
		String contentEvent2 = txData.getContentByArrayTypeRandom(1)+"2115684";
		info("Test 13 Add an event in personal calendar");
		/*Step Number: 1
		 *Step Name: -
		 *Step Description: 
			Step 1: Add an event
		 *Input Data: 
			- Select a personal calendar
			- Click Setting icon of this personal calendar and choose [Add Event]
		 *Expected Outcome: 
			- The pop up "Quick Add Event" is displayed
			- The Default Start date "From" is set to Today (System date)
			-The default duration for event is 1 hour*/

		/*Step number: 2
		 *Step Name: 
		 *Step Description: 
			Step 2: save
		 *Input Data: 
			- Input other values
			- Save
		 *Expected Outcome: 
			Event is created in personal calendar*/ 
		hp.goToCalendarPage();
		cMang.executeActionCalendar(fullName, menuOfCalendarOption.ADDEVENT);
		info("Check default date");
		event.checkSuggestionEventTimeInQuickForm(null,null, 60);
		event.inputDataEventInQuickForm(titleEvent, contentEvent, getDate(0,"MM/dd/yyyy"), getDate(0,"MM/dd/yyyy"),false);
		event.saveQuickAddEvent();
		cHome.verifyIsPresentEventTask(titleEvent, selectViewOption.LIST, selectDayOption.ONEDAY);

		info("Test 14 Edit an Event in personal calendar");
		/*Step Number: 1
		 *Step Name: -
		 *Step Description: 
			Step 1: Edit an event
		 *Input Data: 
			- Add an event 
			- Edit an event by right click on a existing event and select Edit
			- Update some values
			- Change From time
		 *Expected Outcome: 
			- To time is automatically set = From Time + 1hour*/

		/*Step number: 2
		 *Step Name: 
		 *Step Description: 
			Step 2: Save
		 *Input Data: 
			- Save
		 *Expected Outcome: 
			Event is saved successfully in personal calendar*/ 
		cHome.goToEditEventTaskFormByRightClick(titleEvent, selectViewOption.WEEK, selectDayOption.ONEDAY,getDate(0,"MMM dd yyyy"));
		event.checkSuggestionEventTimeInDetailForm(null,null, 60);
		event.inputDataEventInDetailForm(titleEvent2, contentEvent2, getDate(0,"MM/dd/yyyy"), getDate(0,"MM/dd/yyyy"), false);
		event.saveAddEventDetails();
		cHome.verifyIsPresentEventTask(titleEvent2, selectViewOption.LIST, selectDayOption.ONEDAY);

		info("Test 15 Delete an Event in personal calendar");
		/*Step Number: 1
		 *Step Name: -
		 *Step Description: 
			Step 1: Delete an event
		 *Input Data: 
			- Add an event 
			- Delete an event right click on an existing event and select Delete
			- Click OK at confirmation message
		 *Expected Outcome: 
			- The event is removed normally.*/ 
		cHome.deleteEventTask(titleEvent2, selectViewOption.LIST, selectDayOption.ONEDAY,getDate(0,"MM/dd/yyyy"));
	}

	/**
	 * Case ID:115679.
	 * Test Case Name: Drag-drop event.
	 * Pre-Condition: The event is created
	 * Post-Condition: 
	 */
	@Test(priority=10)
	public  void test16_DragDropEvent() {
		String titleEvent = txData.getContentByArrayTypeRandom(1)+"1115649";
		String contentEvent = txData.getContentByArrayTypeRandom(1)+"1115649";
		String dateTime=getDate(1,"MM/dd/yyyy");

		info("Create data test");
		info("Add a event");
		hp.goToCalendarPage();
		event.goToAddEventFromActionBar();
		event.inputDataEventInQuickForm(titleEvent, contentEvent, getDate(0,"MM/dd/yyyy"), getDate(0,"MM/dd/yyyy"),false);
		String fromTime = waitForAndGetElement(event.ELEMENT_QUICK_INPUT_EVENT_FROM_TIME_INPUT, DEFAULT_TIMEOUT, 1, 2).getAttribute("value");
		String toTime = waitForAndGetElement(event.ELEMENT_QUICK_INPUT_EVENT_TO_TIME_INPUT, DEFAULT_TIMEOUT, 1, 2).getAttribute("value");
		event.saveQuickAddEvent();
		cHome.verifyIsPresentEventTask(titleEvent, selectViewOption.LIST, selectDayOption.ONEDAY);

		info("Test 16 Drag-drop event");
		/*Step Number: 1
		 *Step Name: Step 1: Drag and drop an event
		 *Step Description: 
			- Choose View type from main bar (except List View)
			- Drag & drop added the eventin working pane
		 *Input Data: 

		 *Expected Outcome: 
			- Event is moved to new place in working pane
			- time of event is updated according to new place in main pane*/ 
		cHome.goToView(selectViewOption.MONTH);
		dragAndDropToObject(By.xpath(cHome.ELEMENT_EVENT_TASK_DETAIL_DATE_MONTH_VIEW.replace("$name", titleEvent).replace("$date", getDate(0, "MMM dd yyyy"))),By.xpath(cHome.ELEMENT_ANY_TARGET_DATE.replace("${targetDate}", getDate(1, "MMM dd yyyy"))));
		cHome.goToEditEventTaskFormByRightClick(titleEvent, selectViewOption.WEEK, selectDayOption.ONEDAY,getDate(1,"MMM dd yyyy"));
		event.checkSuggestionEventTimeInDetailForm(dateTime+" "+fromTime, dateTime+" "+toTime, 60);

		info("Delete data");
		cHome.deleteEventTask(titleEvent, selectViewOption.MONTH, selectDayOption.ONEDAY,getDate(1,"MMM dd yyyy"));	
	}


	/**
	 * Case ID:115690.
	 * Test Case Name: Add an event in group calendar.
	 * Case ID:115680.
	 * Test Case Name: Edit an event in group calendar.
	 * Case ID:115681.
	 * Test Case Name: Delete an event in group calendar.
	 * Pre-Condition: 
	 * Post-Condition: 
	 */
	@Test(priority=11)
	public  void test17_18_19_AddEditDeleteAnEventInGroupCalendar() {
		String titleEvent = txData.getContentByArrayTypeRandom(1)+"1115690";
		String contentEvent = txData.getContentByArrayTypeRandom(1)+"1115690";
		String titleEvent2= txData.getContentByArrayTypeRandom(1)+"2115690";
		String contentEvent2= txData.getContentByArrayTypeRandom(1)+"2115690";
		String groupCalendar = "Content Management";

		info("Test 17 Add an event in group calendar");
		/*Step Number: 1
		 *Step Name: 
		 *Step Description: 
			Step 1: Open Add Event form
		 *Input Data: 
			- Login by user who has edit right on a group calendar
			- Click Setting icon of this group calendar and choose [Add Event]
		 *Expected Outcome: 
			- The pop up "Quick Add Event" is displayed
			- The Default Start date "From" is set to Today (System date)
			-The default duration for Event is 1 hour*/

		/*Step number: 2
		 *Step Name: -
		 *Step Description: 
			Step 2: Add an event to a group calendar
		 *Input Data: 
			- Fill values
			- Save
		 *Expected Outcome: 
			- New event for that group calendar is added successfully
			- The other users in shared group can view the event in the group calendar*/ 
		magAc.signOut();
		magAc.signIn(DATA_USER2, DATA_PASS);
		hp.goToCalendarPage();
		cMang.executeActionCalendar(groupCalendar, menuOfCalendarOption.ADDEVENT);
		info("Check default date");
		event.checkSuggestionEventTimeInQuickForm(null,null, 60);
		event.inputDataEventInQuickForm(titleEvent, contentEvent, getDate(0,"MM/dd/yyyy"), getDate(0,"MM/dd/yyyy"),false);
		event.saveQuickAddEvent();
		cHome.verifyIsPresentEventTask(titleEvent, selectViewOption.WEEK, selectDayOption.ONEDAY);

		magAc.signOut();
		magAc.signIn(DATA_USER3, DATA_PASS);
		hp.goToCalendarPage();
		cMang.goToMenuFromMainCalendar(menuOfMainCalendar.CALSETTING);
		cMang.changeSettingCalendar(null,"(GMT +07:00) Asia/Ho_Chi_Minh",null,null,null,null,null);
		cMang.saveSetting();
		cHome.verifyIsPresentEventTask(titleEvent, selectViewOption.WEEK, selectDayOption.ONEDAY);

		magAc.signOut();
		magAc.signIn(DATA_USER4, DATA_PASS);
		hp.goToCalendarPage();
		cMang.goToMenuFromMainCalendar(menuOfMainCalendar.CALSETTING);
		cMang.changeSettingCalendar(null,"(GMT +07:00) Asia/Ho_Chi_Minh",null,null,null,null,null);
		cMang.saveSetting();
		cHome.verifyIsNotPresentEventTask(titleEvent, selectViewOption.WEEK, selectDayOption.ONEDAY);

		info("Test 18 Edit an event in group calendar");
		/*Step Number: 1
		 *Step Name: Step 1: Edit an event to a group calendar
		 *Step Description: 
			- Login by user who has edit right on a group calendar
			- Add new event into the group calendar
			- Right click on event then choose Edit
			- Update some values
			- Change From time
		 *Input Data: 

		 *Expected Outcome: 
			- To time is automatically set = From Time + 1 hour*/

		/*Step number: 2
		 *Step Name: Step 2: Save
		 *Step Description: 
			- Click Save to finish.
		 *Input Data: 

		 *Expected Outcome: 
			- Event is saved successfully
			- The other users in group can view updated event in the group calendar*/ 
		magAc.signOut();
		magAc.signIn(DATA_USER2, DATA_PASS);
		hp.goToCalendarPage();
		cHome.goToEditEventTaskFormByRightClick(titleEvent, selectViewOption.DAY, selectDayOption.ONEDAY,getDate(0,"MMM dd yyyy"));
		event.checkSuggestionEventTimeInDetailForm(null,null, 60);
		event.inputDataEventInDetailForm(titleEvent2, contentEvent2, getDate(0,"MM/dd/yyyy"), getDate(0,"MM/dd/yyyy"), false);
		event.saveAddEventDetails();
		cHome.verifyIsPresentEventTask(titleEvent2, selectViewOption.WEEK, selectDayOption.ONEDAY);

		magAc.signOut();
		magAc.signIn(DATA_USER3, DATA_PASS);
		hp.goToCalendarPage();
		cHome.verifyIsPresentEventTask(titleEvent2, selectViewOption.WEEK, selectDayOption.ONEDAY);

		magAc.signOut();
		magAc.signIn(DATA_USER4, DATA_PASS);
		hp.goToCalendarPage();
		cHome.verifyIsNotPresentEventTask(titleEvent2, selectViewOption.WEEK, selectDayOption.ONEDAY);

		info("Test 19 Delete an event in group calendar");
		/*Step Number: 1
		 *Step Name: -
		 *Step Description: 
			Step 1: Delete an event to a group calendar
		 *Input Data: 
			- Login by user who has edit right on a group calendar
			- Add new event into the group calendar
			- Right click on event then choose Delete
			- Click OK at confirmation message
		 *Expected Outcome: 
			- Event is deleted
			- The other users in shared group cannot view the event in the group calendar any more.*/ 
		magAc.signOut();
		magAc.signIn(DATA_USER2, DATA_PASS);
		hp.goToCalendarPage();
		cHome.deleteEventTask(titleEvent2, selectViewOption.WEEK, selectDayOption.ONEDAY,getDate(0,"MMM dd yyyy"));

		magAc.signOut();
		magAc.signIn(DATA_USER3, DATA_PASS);
		hp.goToCalendarPage();
		cHome.verifyIsNotPresentEventTask(titleEvent2, selectViewOption.WEEK, selectDayOption.ONEDAY);

		magAc.signOut();
		magAc.signIn(DATA_USER4, DATA_PASS);
		hp.goToCalendarPage();
		cHome.verifyIsNotPresentEventTask(titleEvent2, selectViewOption.WEEK, selectDayOption.ONEDAY);
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
	}

	/**
	 * Case ID:115689.
	 * Test Case Name: Add an event for shared calendar.
	 * Case ID:115683.
	 * Test Case Name: Edit an event in shared calendar.
	 * Case ID:115682.
	 * Test Case Name: Delete an event in shared calendar.
	 * Pre-Condition: 
	 * Post-Condition: 
	 */
	@Test(priority=12)
	public  void test20_21_22_AddEditDeleteAnEventInSharedCalendar() {
		String titleEvent = txData.getContentByArrayTypeRandom(1)+"1115689";
		String contentEvent = txData.getContentByArrayTypeRandom(1)+"1115689";
		String titleEvent2 = txData.getContentByArrayTypeRandom(1)+"2115689";
		String contentEvent2 = txData.getContentByArrayTypeRandom(1)+"2115689";
		String calendarName= txData.getContentByArrayTypeRandom(1)+"115689";
		String calendarColor = "purple";
		String[] groupShare = {DATA_USER2};
		boolean[] edit = {true};

		info("Test 20 Add an event for shared calendar");
		/*Step Number: 1
		 *Step Name: -
		 *Step Description: 
			Step 1: Create & share a calendar
		 *Input Data: 
			- Create personal calendar
			- Share added calendar with edit right
		 *Expected Outcome: 
			Calendar is created and shared*/
		hp.goToCalendarPage();
		cMang.goToMenuFromMainCalendar(menuOfMainCalendar.ADDCAL);
		cMang.inputDataInDetailTabCalendarForm(calendarName, calendarName, calendarColor);
		cMang.saveAddCalendar();
		waitForAndGetElement(cMang.ELEMENT_CALENDAR_LIST_ITEM.replace("$calendar", calendarName));
		cMang.shareCalendar(calendarName, groupShare, edit , 1);

		/*Step number: 2
		 *Step Name: 
		 *Step Description: 
			Step 2: Add an event to a shared calendar
		 *Input Data: 
			- Login by shared user
			- Click wheel icon of shared calendar and select Add event
		 *Expected Outcome: 
			- The pop up "Quick Add Event" is displayed
			- The Default Start date "From" is set to Today (System date)
			-The default duration for event is 1 hour*/

		/*Step number: 3
		 *Step Name: 
		 *Step Description: 
			Step 3: Save
		 *Input Data: 
			- Fill values
			- Save
		 *Expected Outcome: 
			Event is added successfully in shared calendar*/ 
		magAc.signOut();
		magAc.signIn(DATA_USER2, DATA_PASS);
		hp.goToCalendarPage();
		cMang.goToMenuFromMainCalendar(menuOfMainCalendar.CALSETTING);
		cMang.changeSettingCalendar(null,"(GMT +07:00) Asia/Ho_Chi_Minh",null,null,null,null,null);
		cMang.saveSetting();
		cMang.executeActionCalendar(calendarName, menuOfCalendarOption.ADDEVENT);
		info("Check default date");
		event.checkSuggestionEventTimeInQuickForm(null,null, 60);
		event.inputDataEventInQuickForm(titleEvent, contentEvent, getDate(0,"MM/dd/yyyy"), getDate(0,"MM/dd/yyyy"),false);
		event.saveQuickAddEvent();
		cHome.verifyIsPresentEventTask(titleEvent, selectViewOption.WEEK, selectDayOption.ONEDAY);

		info("Test 21 Edit an event in shared calendar");
		/*Step number: 3
		 *Step Name: Step 3: Edit an event to a shared calendar
		 *Step Description: 
			- Right click on event then choose Edit
			- Update some values
			- Change From time
		 *Input Data: 

		 *Expected Outcome: 
			- To time is automatically set = From Time + 1 hour*/

		/*Step number: 4
		 *Step Name: Step 4: Save
		 *Step Description: 
			- Click Save to finish
		 *Input Data: 

		 *Expected Outcome: 
			- Event in shared calendar is edited
			- Sharing user can see updated event*/ 
		cHome.goToEditEventTaskFormByRightClick(titleEvent, selectViewOption.WEEK, selectDayOption.ONEDAY,getDate(0,"MMM dd yyyy"));
		event.checkSuggestionEventTimeInDetailForm(null, null,60);
		event.inputDataEventInDetailForm(titleEvent2, contentEvent2, getDate(0,"MM/dd/yyyy"), getDate(0,"MM/dd/yyyy"), false);
		event.saveAddEventDetails();
		cHome.verifyIsPresentEventTask(titleEvent2, selectViewOption.WEEK, selectDayOption.ONEDAY);

		info("Test 22 Delete an event in shared calendar");
		/*Step Number: 1
		 *Step Name: -
		 *Step Description: 
			Step 1: Create & share a calendar
		 *Input Data: 
			- Create personal calendar
			- Share added calendar with edit right
		 *Expected Outcome: 
			Calendar is created and shared*/

		/*Step number: 2
		 *Step Name: 
		 *Step Description: 
			Step 2: Add an event to a shared calendar
		 *Input Data: 
			- Log in as shared user
			- Click wheel icon of shared calendar then choose Add event
			- Input Event summary and click Save
		 *Expected Outcome: 
			- The shared user can see the shared calendar and add event into it.*/

		/*Step number: 3
		 *Step Name: -
		 *Step Description: 
			Step 3: Delete an event to a shared calendar
		 *Input Data: 
			- Right click on event then choose Delete
			- Click OK at confirmation
			- Save
		 *Expected Outcome: 
			- Event is deleted
			- The sharing user cannot see this event*/ 
		cHome.deleteEventTask(titleEvent2, selectViewOption.WEEK, selectDayOption.ONEDAY,getDate(0,"MMM dd yyyy"),true,true);

		info("Delete data");
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		hp.goToCalendarPage();
		cMang.deleteCalendar(calendarName,true);

	}

	/**
	 * Case ID:115691.
	 * Test Case Name: Re-size event.
	 * Pre-Condition: The event is already created
	 * Post-Condition: 
	 * PENDING: Refer to : https://jira.exoplatform.org/browse/FQA-1351
	 */
	@Test(groups="pending")
	public  void test23_ReSizeEvent() {
		info("Test 23 Re-size event");
		/*Step Number: 1
		 *Step Name: Step 1: Resize an existing event/ task
		 *Step Description: 
			- Choose View type from list (except List View)
			- Resize time of the event by increasing its size on the calendar view.
		 *Input Data: 

		 *Expected Outcome: 
			Time of the event is changed depending on the changed size.*/ 

	}
}
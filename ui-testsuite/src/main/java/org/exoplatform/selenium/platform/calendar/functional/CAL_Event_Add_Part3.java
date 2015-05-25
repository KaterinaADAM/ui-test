package org.exoplatform.selenium.platform.calendar.functional;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.calendar.CalendarHomePage.contextMenuAddEditEvenTaskOption;
import org.exoplatform.selenium.platform.calendar.CalendarHomePage.selectDayOption;
import org.exoplatform.selenium.platform.calendar.CalendarHomePage.selectViewOption;
import org.exoplatform.selenium.platform.calendar.CalendarManagement.menuOfCalendarOption;
import org.exoplatform.selenium.platform.calendar.CalendarManagement.menuOfMainCalendar;
import org.testng.annotations.*;


	public class CAL_Event_Add_Part3 extends CAL_TestConfig_2{
		String password;
		public void createUser() {
			int index = userInfoData.getRandomIndexByType(3);
			username = userInfoData.newUserName.get(index) + getRandomNumber();
			firstname = userInfoData.newFirstName.get(index);
			String lastname = userInfoData.newLastName.get(index);
			password = userInfoData.newPassword.get(index);
			String email = EMAIL_ADDRESS1;

			navTool.goToAddUser();
			addUserPage.addUser(username, password, email, firstname, lastname);
		}
	/**
	*<li> Case ID:116408.</li>
	*<li> Test Case Name: Add new event into an imported calendar.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test40_AddNewEventIntoAnImportedCalendar() {
		info("Test 40 Add new event into an imported calendar");
		/*Step Number: 1
		*Step Name: -
		*Step Description: 
			Step 1: Import calendar
		*Input Data: 
			- Create new calendar, event/task,[ Details ]
			- export then import itExport: [ Details ]Import: [ Details ]
		*Expected Outcome: 
			Imported calendar is displayed in selected group*/
		info("Create a new calendar");
		String calendar = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String exportedCalendar=getRandomNumber()+".ics";
        hp.goToCalendarPage();
		cHome.goToView(selectViewOption.WEEK);
        cMang.goToMenuFromMainCalendar(menuOfMainCalendar.ADDCAL);
        cMang.inputDataInDetailTabCalendarForm(calendar, calendar,null);
        cMang.saveAddCalendar();
        
        info("Create an event in the calendar");
        String titleEvent = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToCalendarPage();
		evMg.goToAddEventFromActionBar();
		evMg.inputBasicQuickEvent(titleEvent,content,calendar);
		evMg.saveQuickAddEvent();
		info("Add successfully");
		driver.navigate().refresh();
		cHome.verifyIsPresentEventTask(titleEvent, selectViewOption.LIST, selectDayOption.ONEDAY);
		
		info("Export the calendar");
		cMang.exportCalendar(calendar,exportedCalendar);
		cMang.deleteCalendar(calendar,true);
		cHome.goToView(selectViewOption.LIST);
		waitForElementNotPresent(cMang.ELEMENT_EVENT_TASK_TITLE.replace("${name}",titleEvent));
		
		info("Import the calendar");
		cMang.importCalendar("TestData/TestOutput/" +exportedCalendar,calendar,null,null);
		info("Check the event is present ");
		driver.navigate().refresh();
		cHome.verifyIsPresentEventTask(titleEvent, selectViewOption.LIST, selectDayOption.ONEDAY);
		/*Step number: 2
		*Step Name: -
		*Step Description: 
			Step 2: Add new event
		*Input Data: 
			Add new event on that imported calendar
			- Export a calendar :[ Details ]
			- Add new event: [ Details ]
		*Expected Outcome: 
			Event is added successfully*/ 
		
		 info("Create an event in the calendar");
	     String titleEvent1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		 String content1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		 hp.goToCalendarPage();
		 evMg.goToAddEventFromActionBar();
		 evMg.inputBasicQuickEvent(titleEvent1,content1,calendar);
		 evMg.saveQuickAddEvent();
		 info("Add successfully");
		 cHome.verifyIsPresentEventTask(titleEvent1, selectViewOption.LIST, selectDayOption.ONEDAY);

		info("Delete file and task");
		deleteFile("TestOutput/" + exportedCalendar);
		cHome.goToView(selectViewOption.LIST);
		cMang.deleteTaskEvent(titleEvent);
		cMang.deleteTaskEvent(titleEvent1);
 	}

	/**
	*<li> Case ID:116409.</li>
	*<li> Test Case Name: Add new event when its calendar is not selected to view.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test41_AddNewEventWhenItsCalendarIsNotSelectedToView() {
		info("Test 41 Add new event when its calendar is not selected to view");
		/*Step Number: 1
		*Step Name: -
		*Step Description: 
			Step 1: Add calendar
		*Input Data: 
			Add new calendar and events [ Details ]
		*Expected Outcome: 
			Added calendar and its events are shown in working pane or list*/
		info("Create a new calendar");
		String calendar = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
        hp.goToCalendarPage();
		cHome.goToView(selectViewOption.WEEK);
        cMang.goToMenuFromMainCalendar(menuOfMainCalendar.ADDCAL);
        cMang.inputDataInDetailTabCalendarForm(calendar, calendar,null);
        cMang.saveAddCalendar();
        
        info("Create an event in the calendar");
        String titleEvent = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		evMg.goToAddEventFromActionBar();
		evMg.inputBasicQuickEvent(titleEvent,content,calendar);
		evMg.saveQuickAddEvent();
		info("Add successfully");
		driver.navigate().refresh();
		cHome.verifyIsPresentEventTask(titleEvent, selectViewOption.WEEK, selectDayOption.ONEDAY);
		
		/*Step number: 2
		*Step Name: -
		*Step Description: 
			Step 2: Hide calendar's events
		*Input Data: 
			Un
			-check above calendar
		*Expected Outcome: 
			All events of this calendar are hidden from working pane*/
		info("Hide the calendar's event");
		cHome.goToView(selectViewOption.LIST);
		cMang.showHideEventTask(calendar, false);
		info("All events of this calendar are hidden from working pane");
		waitForElementNotPresent(cMang.ELEMENT_EVENT_TASK_TITLE.replace("${name}",titleEvent));
		
		/*Step number: 3
		*Step Name: -
		*Step Description: 
			Step 3: Add event
		*Input Data: 
			Add event into unchecked calendar above
		*Expected Outcome: 
			Add new event successfully but it's not displayed in working pane or list*/ 
		
		 info("Create an event in the calendar");
         String titleEvent1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		 String content1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		 evMg.goToAddEventFromActionBar();
		 evMg.inputBasicQuickEvent(titleEvent1,content1,calendar);
		 evMg.saveQuickAddEvent();
		 info("Add new event successfully but it's not displayed in working pane or list");
		 waitForElementNotPresent(cMang.ELEMENT_EVENT_TASK_TITLE.replace("${name}",titleEvent1));

		 hp.goToCalendarPage();
		 cMang.deleteCalendar(calendar,true);
 	}

	/**
	*<li> Case ID:116410.</li>
	*<li> Test Case Name: Add new event into a category when it is not selected to view.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test42_AddNewEventIntoACategoryWhenItIsNotSelectedToView() {
		info("Test 42 Add new event into a category when it is not selected to view");
		/*Step Number: 1
		*Step Name: -
		*Step Description: 
			Step 1: Add category, calendar
		*Input Data: 
			- Add category[ Details ]
			- Add new calendar and events[ Details ]
			- Select that category to view on Action bar
		*Expected Outcome: 
			Added calendar and its events are shown in working pane or list*/
		info("Create a new calendar");
		String calendar = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
        hp.goToCalendarPage();
		cHome.goToView(selectViewOption.WEEK);
        cMang.goToMenuFromMainCalendar(menuOfMainCalendar.ADDCAL);
        cMang.inputDataInDetailTabCalendarForm(calendar, calendar,null);
        cMang.saveAddCalendar();
        
        info("Create an event in the calendar");
        String titleEvent = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		evMg.goToAddEventFromActionBar();
		evMg.inputBasicQuickEvent(titleEvent,content,calendar);
		evMg.saveQuickAddEvent();
		info("Add successfully");
		driver.navigate().refresh();
		cHome.verifyIsPresentEventTask(titleEvent, selectViewOption.WEEK, selectDayOption.ONEDAY);
		
		/*Step number: 2
		*Step Name: -
		*Step Description: 
			Step 2: Hide category's events
		*Input Data: 
			Select another category on Action bar to view
		*Expected Outcome: 
			All events of this category are hidden from working pane or list*/
		info("Hide the calendar's event");
		cHome.goToView(selectViewOption.LIST);
		cMang.showHideEventTask(calendar, false);
		info("All events of this calendar are hidden from working pane");
		waitForElementNotPresent(cMang.ELEMENT_EVENT_TASK_TITLE.replace("${name}",titleEvent));
		/*Step number: 3
		*Step Name: -
		*Step Description: 
			Step 3: Add event
		*Input Data: 
			Add event into unselected category
		*Expected Outcome: 
			Add new event successfully but it's not displayed in working pane or list*/ 
		 info("Create an event in the calendar");
         String titleEvent1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		 String content1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		 evMg.goToAddEventFromActionBar();
		 evMg.inputBasicQuickEvent(titleEvent1,content1,calendar);
		 evMg.saveQuickAddEvent();
		 info("Add new event successfully but it's not displayed in working pane or list");
		 waitForElementNotPresent(cMang.ELEMENT_EVENT_TASK_TITLE.replace("${name}",titleEvent1));

		 hp.goToCalendarPage();
		 cMang.deleteCalendar(calendar,true);
 	}

	/**
	*<li> Case ID:116411.</li>
	*<li> Test Case Name: Add new event in not working time.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test43_AddNewEventInNotWorkingTime() {
		info("Test 43 Add new event in not working time");
		/*Step Number: 1
		*Step Name: -
		*Step Description: 
			Step 1: Setting calendar
		*Input Data: 
			Set working time for calendar not start from 00:00 to 24:00
		*Expected Outcome: 
			The time that is not in working time is displayed in gray in working pane (Day and Week view)*/
		info("Change setting");
		String startTime="23:30";
		String endTime="23:59";
		hp.goToCalendarPage();		
		cMang.goToMenuFromMainCalendar(menuOfMainCalendar.CALSETTING);
		cMang.changeSettingCalendar(null,null,null,null,null,true,null);
		cMang.changeWorkingTime(startTime,endTime);
		cMang.saveSetting();
		/*Step number: 2
		*Step Name: -
		*Step Description: 
			Step 2: Add event
		*Input Data: 
			Add event with event duration is not in working time
		*Expected Outcome: 
			Add event successfully and event is displayed as normal*/ 
		 info("Create an event in the calendar");
         String titleEvent = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		 String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		 evMg.goToAddEventFromActionBar();
		 evMg.inputBasicQuickEvent(titleEvent,content);
		 evMg.saveQuickAddEvent();
		 info("Add new event successfully but it's not displayed in working pane or list");
		 waitForElementNotPresent(cMang.ELEMENT_EVENT_TASK_TITLE.replace("${name}",titleEvent));

		 info("Restore Calendar setting");
		 hp.goToCalendarPage();		
		 cMang.goToMenuFromMainCalendar(menuOfMainCalendar.CALSETTING);
		 cMang.changeSettingCalendar(null,null,null,null,null,null,null);
		 cMang.saveSetting();
		 info("Delete event");
		 cHome.goToView(selectViewOption.LIST);
		 cMang.deleteTaskEvent(titleEvent);

 	}

	/**
	*<li> Case ID:116412.</li>
	*<li> Test Case Name: Add new event in same day with 3 other events or tasks in Month View.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test44_AddNewEventInSameDayWith3OtherEventsOrTasksInMonthView() {
		info("Test 44 Add new event in same day with 3 other events or tasks in Month View");
		/*Step Number: 1
		*Step Name: - Step 1: Create event
		*Step Description: 
			- Create new 3 events/tasks in same day [ Details ]
			- Select Month View
		*Input Data: 
			
		*Expected Outcome: 
			3 events/task are displayed in selected day*/
		info("Create event1");
        String titleEvent = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToCalendarPage();
		evMg.goToAddEventFromActionBar();
		evMg.inputBasicQuickEvent(titleEvent,content);
		evMg.saveQuickAddEvent();
		info("Add successfully");
		cHome.verifyIsPresentEventTaskWithDateTime(titleEvent,getDate(0,"MM/dd/yyyy"),selectViewOption.MONTH, selectDayOption.ONEDAY);
		
		
		info("Create event2");
        String titleEvent1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToCalendarPage();
		evMg.goToAddEventFromActionBar();
		evMg.inputBasicQuickEvent(titleEvent1,content1);
		evMg.saveQuickAddEvent();
		info("Add successfully");
		cHome.verifyIsPresentEventTaskWithDateTime(titleEvent1,getDate(0,"MM/dd/yyyy"),selectViewOption.MONTH, selectDayOption.ONEDAY);
		
		info("Create event3");
        String titleEvent2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToCalendarPage();
		evMg.goToAddEventFromActionBar();
		evMg.inputBasicQuickEvent(titleEvent2,content2);
		evMg.saveQuickAddEvent();
		info("Add successfully");
		cHome.verifyIsPresentEventTaskWithDateTime(titleEvent2,getDate(0,"MMM dd yyyy"),selectViewOption.MONTH, selectDayOption.ONEDAY);
		
		/*Step number: 2
		*Step Name: - Step 2: Add event
		*Step Description: 
			Add new event in the same day with 3 events/tasks above
		*Input Data: 
			
		*Expected Outcome: 
			Add new event successfully but only 3 events/tasks is show, the 1 more is hidden, there's a link to show hidden event . 
			When hidden event is shown, it is possible to hide it by clicking close icon on menu*/ 
		info("Create event4");
        String titleEvent3 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content3 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToCalendarPage();
		evMg.goToAddEventFromActionBar();
		evMg.inputBasicQuickEvent(titleEvent3,content3);
		evMg.saveQuickAddEvent();
		info("Add successfully");
		cHome.verifyIsNotPresentEventTaskWithDateTime(titleEvent,getDate(0,"MMM dd yyyy"),selectViewOption.MONTH, selectDayOption.ONEDAY);
		waitForAndGetElement(cHome.ELEMENT_EVENT_TASK_DETAIL_DATE_MONTH_VIEW_MORE_LABEL.replace("$date",getDate(0,"MMM dd yyyy")));
		click(cHome.ELEMENT_EVENT_TASK_DETAIL_DATE_MONTH_VIEW_MORE_LABEL.replace("$date",getDate(0,"MMM dd yyyy")));
		waitForAndGetElement(cMang.ELEMENT_EVENT_TASK_TITLE.replace("${name}",titleEvent));
		
		 info("Delete event");
		 cHome.goToView(selectViewOption.LIST);
		 cMang.deleteTaskEvent(titleEvent);
		 cMang.deleteTaskEvent(titleEvent1);
		 cMang.deleteTaskEvent(titleEvent2);
		 cMang.deleteTaskEvent(titleEvent3);
		
 	}

	/**
	*<li> Case ID:116413.</li>
	*<li> Test Case Name: Check Send invitation mail when Choose Ask send invitation mail while creating event.</li>
	*<li> Pre-Condition: Config email of user1</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test45_CheckSendInvitationMailWhenChooseAskSendInvitationMailWhileCreatingEvent() {
		info("Test 45 Check Send invitation mail when Choose Ask send invitation mail while creating event");
		/*Step Number: 1
		*Step Name: Step 1: Open Add/edit event form
		*Step Description: 
			- Login the system (user1)
			- Go to calendar application
			- Click [Event] on theaction bar
		*Input Data: 
			
		*Expected Outcome: 
			The form is shown properly*/

		/*Step number: 2
		*Step Name: Step 2: Add participant(s) or email(s) to send invitation mail
		*Step Description: 
			- Click on [More details] button
			- Go to [Participants] tab 
			- Click [Add participant] icon
			- Input username of users or email addresses, 
			- Click [Save]
		*Input Data: 
			
		*Expected Outcome: 
			The Participant(s) ,email(s) are selected*/

		/*Step number: 3
		*Step Name: Step 3:Confirm sending invitation mail
		*Step Description: 
			- Click[Ask] radio
			- Go to Detail tab, input name for the event 
			- Click [Save]
		*Input Data: 
			
		*Expected Outcome: 
			- An alert message is shown to ask yes/no sending invitation mail: "Would you like to send updates to all guests?"*/
		createUser();
		String titleEvent = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		info("Add a event");
		hp.goToCalendarPage();		
		evMg.goToAddEventFromActionBar();
		evMg.moreDetailsEvent();
		evMg.inputBasicDetailEvent(titleEvent,content);
		evMg.goToParticipantsTab();
		evMg.goToInvitationParticipantForm();
		evMg.selectUserParticipants(username, content,0);
		evMg.saveInvitationParticipantForm();
		evMg.selectSendInvitation(selectInvitationOption.ASK);
		evMg.saveAddEventDetails();
		waitForAndGetElement(evMg.ELEMENT_CONFIRM_SEND_INVITATION_MESSAGE);
		waitForAndGetElement(evMg.ELMEMENT_CONFIRM_SEND_INVITATION_YES_BTN);
		waitForAndGetElement(evMg. ELMEMENT_CONFIRM_SEND_INVITATION_NO_BTN);
		
		/*Step number: 4
		*Step Name: Step 4: Agree send invitation mail
		*Step Description: 
			-Click [Yes]
		*Input Data: 
			
		*Expected Outcome: 
			- New event is created 
			- And an Invitation mail will be sent to email address of selected participant(s) and selected email(s).
			- An .ics file will be attached with the invitation mail*/ 
		click(evMg. ELMEMENT_CONFIRM_SEND_INVITATION_YES_BTN);
		cHome.goToView(selectViewOption.LIST);
		waitForAndGetElement(cMang.ELEMENT_EVENT_TASK_TITLE.replace("${name}",titleEvent));
		
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(20000);
		cMang.checkEmailNotificationCalendar(titleEvent,"icalendar.ics","","",true,true);
        switchToParentWindow();
        
		info("restore data");
		hp.goToCalendarPage();
		cMang.deleteTaskEvent(titleEvent);
		navTool.goToUsersAndGroupsManagement();
		userAndGroup.deleteUser(username);
 	}

	/**
	*<li> Case ID:116414.</li>
	*<li> Test Case Name: Check Send invitation mail in case Choose Ask send invitation mail but confirm "No".</li>
	*<li> Pre-Condition: Config email of some users that are used in this case</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test46_CheckSendInvitationMailInCaseChooseAskSendInvitationMailButConfirmNo() {
		info("Test 46 Check Send invitation mail in case Choose Ask send invitation mail but confirm No");
		/*Step Number: 1
		*Step Name: Step 1: Open Add/edit event form
		*Step Description: 
			- Login the system (user1)
			- Go to calendar application
			- Click [Event] on theaction bar
		*Input Data: 
			
		*Expected Outcome: 
			The form is shown properly*/

		/*Step number: 2
		*Step Name: Step 2: Add participant(s) or email(s) to send invitation mail
		*Step Description: 
			- Click on [More details] button
			- Go to [Participants] tab 
			- Click [Add participant] icon
			- Inputusername of users or email addresses, 
			- Click [Save]
		*Input Data: 
			
		*Expected Outcome: 
			The Participant(s) ,email(s) are added to the participants list*/
		
		/*Step number: 3
		*Step Name: Step 3:Confirm sending invitation mail
		*Step Description: 
			- Click[Ask] radio
			- Go to [Details] tab, input name for the event 
			- Click [Save]
		*Input Data: 
			
		*Expected Outcome: 
			- An alert message is shown to ask yes/no sending invitation mail: "Would you like to send updates to all guests?"*/
		createUser();
		String titleEvent = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		info("Add a event");
		hp.goToCalendarPage();		
		evMg.goToAddEventFromActionBar();
		evMg.moreDetailsEvent();
		evMg.inputBasicDetailEvent(titleEvent,content);
		evMg.goToParticipantsTab();
		evMg.goToInvitationParticipantForm();
		evMg.selectUserParticipants(username, content,0);
		evMg.saveInvitationParticipantForm();
		evMg.selectSendInvitation(selectInvitationOption.ASK);
		evMg.saveAddEventDetails();
		waitForAndGetElement(evMg.ELEMENT_CONFIRM_SEND_INVITATION_MESSAGE);
		waitForAndGetElement(evMg.ELMEMENT_CONFIRM_SEND_INVITATION_YES_BTN);
		waitForAndGetElement(evMg. ELMEMENT_CONFIRM_SEND_INVITATION_NO_BTN);
		
		/*Step number: 4
		*Step Name: Step 4: Don't Agree send invitation mail
		*Step Description: 
			-Click [No]
		*Input Data: 
			
		*Expected Outcome: 
			- New event is created 
			- And NoInvitation mailis sent to email address of selected participant(s) and selected email(s).*/ 
		click(evMg. ELMEMENT_CONFIRM_SEND_INVITATION_NO_BTN);
		cHome.goToView(selectViewOption.LIST);
		waitForAndGetElement(cMang.ELEMENT_EVENT_TASK_TITLE.replace("${name}",titleEvent));
		
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(20000);
		cMang.checkEmailNotificationCalendar(titleEvent,"","","",false);
        switchToParentWindow();
        
		info("restore data");
		hp.goToCalendarPage();
		cMang.deleteTaskEvent(titleEvent);
		navTool.goToUsersAndGroupsManagement();
		userAndGroup.deleteUser(username);
 	}

	/**
	*<li> Case ID:116415.</li>
	*<li> Test Case Name: Check create event with No  invitation mail.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test47_CheckCreateEventWithNoInvitationMail() {
		info("Test 47 Check create event with No  invitation mail");
		/*Step Number: 1
		*Step Name: - Step 1: Create an event
		*Step Description: 
			- Create an event which don't send invitation mail to participantsby click [More Details] 
			and choose [Participants] tab and check [Never] in Send Invitations
		*Input Data: 
			
		*Expected Outcome: 
			The event is created*/ 
		String titleEvent = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		info("Add a event");
		hp.goToCalendarPage();		
		evMg.goToAddEventFromActionBar();
		evMg.moreDetailsEvent();
		evMg.inputBasicDetailEvent(titleEvent,content);
		evMg.goToParticipantsTab();
		evMg.selectSendInvitation(selectInvitationOption.NEVER);
		evMg.saveAddEventDetails();
		
		cHome.goToView(selectViewOption.LIST);
		waitForAndGetElement(cMang.ELEMENT_EVENT_TASK_TITLE.replace("${name}",titleEvent));
		info("restore data");
		hp.goToCalendarPage();
		cMang.deleteTaskEvent(titleEvent);
 	}

	/**
	*<li> Case ID:116416.</li>
	*<li> Test Case Name: Check status of participant after create event with no invitation mail.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test48_CheckStatusOfParticipantAfterCreateEventWithNoInvitationMail() {
		info("Test 48 Check status of participant after create event with no invitation mail");
		/*Step Number: 1
		*Step Name: Step 2: View the event
		*Step Description: 
			- Right click on the event and choose Edit
		*Input Data: 
			
		*Expected Outcome: 
			The Add/Edit Events form is shown properly*/

		/*Step number: 2
		*Step Name: Step 1: Add new event with no invitation mail
		*Step Description: 
			- Go to calendar
			- Click Event
			- Input all require fields
			- Don't select invitation mail
			- Click Save
		*Input Data: 
			
		*Expected Outcome: 
			- Event is created without invitation mail*/
		String titleEvent = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		info("Add a event");
		hp.goToCalendarPage();		
		evMg.goToAddEventFromActionBar();
		evMg.inputBasicQuickEvent(titleEvent,content);
		evMg.saveQuickAddEvent();
		
		cHome.goToView(selectViewOption.LIST);
		waitForAndGetElement(cMang.ELEMENT_EVENT_TASK_TITLE.replace("${name}",titleEvent));
		
		/*Step number: 3
		*Step Name: Step 3: View status of Participant
		*Step Description: 
			- Go to Participants tab
		*Input Data: 
			
		*Expected Outcome: 
			- Participant(s) don't have status.*/ 
		cMang.openEditEventTaskPopup(titleEvent);
		evMg.goToParticipantsTab();
		waitForElementNotPresent(evMg.ELEMENT_INVITATION_PARTICIPANTS_REFUSED.replace("$fullName",DATA_NAME_USER1));
		waitForElementNotPresent(evMg.ELEMENT_INVITATION_PARTICIPANTS_MAYBE.replace("$fullName",DATA_NAME_USER1));
		waitForElementNotPresent(evMg.ELEMENT_INVITATION_PARTICIPANTS_YES.replace("$fullName",DATA_NAME_USER1));
		evMg.saveAddEventDetails();

		info("restore data");
		hp.goToCalendarPage();
		cMang.deleteTaskEvent(titleEvent);
 	}

	/**
	*<li> Case ID:116417.</li>
	*<li> Test Case Name: Check status of participant after create event with invitation mail.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test49_CheckStatusOfParticipantAfterCreateEventWithInvitationMail() {
		info("Test 49 Check status of participant after create event with invitation mail");
		/*Step Number: 1
		*Step Name: -
		*Step Description: 
			Step 1: Create an event
		*Input Data: 
			- Create an event which will send invitation mail to participants[ Details ]
		*Expected Outcome: 
			The event is created*/

		/*Step number: 2
		*Step Name: -
		*Step Description: 
			Step 2: View the event
		*Input Data: 
			- Right click on the event and choose View
		*Expected Outcome: 
			The Add/Edit Events form is shown properly*/
		 createUser();
         String titleEvent = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		 String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		 info("Add a event");
		 hp.goToCalendarPage();		
		 evMg.goToAddEventFromActionBar();
		 evMg.moreDetailsEvent();
		 evMg.inputBasicDetailEvent(titleEvent, content);
		 evMg.goToParticipantsTab();
		 evMg.goToInvitationParticipantForm();
		 evMg.selectUserParticipants(username, content,0);
		 evMg.saveInvitationParticipantForm();
		 evMg.selectSendInvitation(selectInvitationOption.ALWAYS);
		 evMg.saveAddEventDetails();
		 Utils.pause(2000);
		 
		 goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		 Utils.pause(20000);
		 cMang.checkEmailNotificationCalendar(titleEvent,"icalendar.ics","Maybe",DATA_USER1,true,true);
		 switchToParentWindow();
		 
		/*Step number: 3
		*Step Name: -
		*Step Description: 
			Step 3: View status of Participant
		*Input Data: 
			- Go to Participants tab
		*Expected Outcome: 
			- Participant(s)have status :Pending*/ 
		 hp.goToCalendarPage();
		 cMang.openEditEventTaskPopup(titleEvent);
 		 evMg.goToParticipantsTab();
 		 waitForAndGetElement(evMg.ELEMENT_INVITATION_PARTICIPANTS_MAYBE.replace("$fullName",firstname));
 		 evMg.saveAddEventDetails();
 		
 		 info("Delete Event");
 		 hp.goToCalendarPage();
 		 cHome.goToView(selectViewOption.LIST);
 		 cMang.deleteTaskEvent(titleEvent);
		 navTool.goToUsersAndGroupsManagement();
		 userAndGroup.deleteUser(username);
 	}

	/**
	*<li> Case ID:116418.</li>
	*<li> Test Case Name: Check status of participant after create event with invitation mail and the participant agree attend the event.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test50_CheckStatusOfParticipantAfterCreateEventWithInvitationMailAndTheParticipantAgreeAttendTheEvent() {
		info("Test 50 Check status of participant after create event with invitation mail and the participant agree attend the event");
		/*Step Number: 1
		*Step Name: - Step 1: Create an event with an invitation mail to participant
		*Step Description: 
			- Login by one user ( Root) 
			- Perform to Create an event with an invitation to participant ( Demo: e.g email address of Demo is demo@gmail.com)[ Details ]
		*Input Data: 
			
		*Expected Outcome: 
			The event is created successfully and has an invitation mail is sent to demo@gmail.com*/

		/*Step number: 2
		*Step Name: - Step 2: Show invitation email
		*Step Description: 
			-Login by Demo
			- Create an account mail with address mail : demo@gmail.com
			- Perform to check mail for the account
			- Click invitation mail to view
		*Input Data: 
			
		*Expected Outcome: 
			- Will be gotten an invitation mail from Root
			- This mail is viewed*/

		/*Step number: 3
		*Step Name: - Step 3: Agree attend the event
		*Step Description: 
			- Click [ Yes] link on the mail
		*Input Data: 
			
		*Expected Outcome: 
			Open new tab with "You have accepted invitation from Root"*/
		 createUser();
         String titleEvent = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		 String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		 info("Add a event");
		 hp.goToCalendarPage();		
		 evMg.goToAddEventFromActionBar();
		 evMg.moreDetailsEvent();
		 evMg.inputBasicDetailEvent(titleEvent, content);
		 evMg.goToParticipantsTab();
		 evMg.goToInvitationParticipantForm();
		 evMg.selectUserParticipants(username, content,0);
		 evMg.saveInvitationParticipantForm();
		 evMg.selectSendInvitation(selectInvitationOption.ALWAYS);
		 evMg.saveAddEventDetails();
		 Utils.pause(2000);
		 
		 goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		 Utils.pause(20000);
		 cMang.checkEmailNotificationCalendar(titleEvent,"icalendar.ics","Yes",DATA_USER1,true,true);
		 switchToParentWindow();
		 
		/*Step number: 4
		*Step Name: - Step 4: Check status of Demo when open above event again
		*Step Description: 
			- Come back Root
			- Open Add/edit event form of above event 
			- Go to Participant tab
		*Input Data: 
			
		*Expected Outcome: 
			- Demo has new status : Yes*/ 
		 hp.goToCalendarPage();
		 cMang.openEditEventTaskPopup(titleEvent);
 		 evMg.goToParticipantsTab();
 		 waitForAndGetElement(evMg.ELEMENT_INVITATION_PARTICIPANTS_YES.replace("$fullName",firstname));
 		 evMg.saveAddEventDetails();
 		
 		 info("Delete Event");
 		 hp.goToCalendarPage();
 		 cHome.goToView(selectViewOption.LIST);
 		 cMang.deleteTaskEvent(titleEvent);
		 navTool.goToUsersAndGroupsManagement();
		 userAndGroup.deleteUser(username);

 	}

	/**
	*<li> Case ID:116419.</li>
	*<li> Test Case Name: Check status of participant after create event with invitation mail and the participant want to add event to his calendar.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test51_CheckStatusOfParticipantAfterCreateEventWithInvitationMailAndTheParticipantWantToAddEventToHisCalendar() {
		info("Test 51 Check status of participant after create event with invitation mail and the participant want to add event to his calendar");
		/*Step Number: 1
		*Step Name: -
		*Step Description: 
			Step 1: Create an event with an invitation mail to participant
		*Input Data: 
			- Login by one user ( Root) 
			- Perform to Create an event with an invitation to participant ( Demo: e.g email address of Demo is demo@gmail.com)[ Details ]
		*Expected Outcome: 
			The event is created successfully and has an invitation mail is sent to demo@gmail.com*/

		/*Step number: 2
		*Step Name: -
		*Step Description: 
			Step 2: Show invitation email
		*Input Data: 
			-Login by Demo
			- Create an account mail with address mail : demo@gmail.com
			- Perform to check mail for the account
			- Click invitation mail to view
		*Expected Outcome: 
			- Will be gotten an invitation mail from Root
			- This mail is viewed*/

		/*Step number: 3
		*Step Name: -
		*Step Description: 
			Step 3: Import event into his calendar
		*Input Data: 
			- Click [ Yes and Import] link on the mail
			- Click Save
		*Expected Outcome: 
			The Add/Edit Events form is shown properly with correctly information , 
			Andthe event is added to his calendar*/

		 createUser();
         String titleEvent = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		 String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		 info("Add a event");
		 hp.goToCalendarPage();		
		 evMg.goToAddEventFromActionBar();
		 evMg.moreDetailsEvent();
		 evMg.inputBasicDetailEvent(titleEvent, content);
		 evMg.goToParticipantsTab();
		 evMg.goToInvitationParticipantForm();
		 evMg.selectUserParticipants(username, content,0);
		 evMg.saveInvitationParticipantForm();
		 evMg.selectSendInvitation(selectInvitationOption.ALWAYS);
		 evMg.saveAddEventDetails();
		 Utils.pause(2000);
		 
		 magAc.signOut();
		 magAc.signIn(username, password);
		 
		 goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		 Utils.pause(20000);
		 cMang.checkEmailNotificationCalendar(titleEvent,"icalendar.ics","Import to your eXo Calendar","",true,true);
		 String titleEvent1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		 String content1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		 evMg.inputBasicDetailEvent(titleEvent1, content1);
		 evMg.saveAddEventDetails();
		 Utils.pause(2000);
		 switchToParentWindow();
		 
		 magAc.signOut();
		 magAc.signIn(DATA_USER1,DATA_PASS);
		 
		/*Step number: 4
		*Step Name: -
		*Step Description: 
			Step 4: Check status of Demo when open above event again
		*Input Data: 
			- Come back Root
			- Open Add/edit event form of above event 
			- Go to Participant tab
		*Expected Outcome: 
			- Demo has new status : Yes*/ 
		 hp.goToCalendarPage();
		 cHome.goToView(selectViewOption.LIST);
		 cMang.openEditEventTaskPopup(titleEvent);
		 evMg.goToParticipantsTab();
 		 waitForAndGetElement(evMg.ELEMENT_INVITATION_PARTICIPANTS_YES.replace("$fullName",firstname));
 		 evMg.saveAddEventDetails();
 		
 		 info("Delete Event");
 		 hp.goToCalendarPage();
 		 cHome.goToView(selectViewOption.LIST);
 		 cMang.deleteTaskEvent(titleEvent);
		 navTool.goToUsersAndGroupsManagement();
		 userAndGroup.deleteUser(username);
		 

 	}

	/**
	*<li> Case ID:116420.</li>
	*<li> Test Case Name: Check input email(s) automatically to send invitation email.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test52_CheckInputEmailAutomaticallyToSendInvitationEmail() {
		info("Test 52 Check input email(s) automatically to send invitation email");
		/*Step Number: 1
		*Step Name: - Step 1: Open Add/Edit Event form
		*Step Description: 
			- Click at Add eventbutton on action bar
		*Input Data: 
			
		*Expected Outcome: 
			The Add/Edit Events form is shown properly*/

		/*Step number: 2
		*Step Name: - Step 2: Open Select User form
		*Step Description: 
			- Go to Participants tab
			- Click ' AddUsert' icon
		*Input Data: 
			
		*Expected Outcome: 
			Invitation form is shown properly*/

		/*Step number: 3
		*Step Name: - Step 3: Open select Address form
		*Step Description: 
			- Click Contact Picker
		*Input Data: 
			
		*Expected Outcome: 
			The form is shown properly*/

		/*Step number: 4
		*Step Name: - Step 4: Search contact
		*Step Description: 
			- input keyword into Search text box
			- Choose address at [ Address Book] combox box to search
			- Click Search icon
		*Input Data: 
			
		*Expected Outcome: 
			- List all contacts which match keyword*/

		/*Step number: 5
		*Step Name: - Step 5: select contact
		*Step Description: 
			- Click corresponding check box of contacts in list
			- Click Add
			- Save
		*Input Data: 
			
		*Expected Outcome: 
			The selected contacts are filled into the first textarea 
			at Invitation form, and each email address is on different line*/ 
	 	 String titleEvent = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		 String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		 info("Add a event");
		 hp.goToCalendarPage();		
		 evMg.goToAddEventFromActionBar();
		 evMg.moreDetailsEvent();
		 evMg.inputBasicDetailEvent(titleEvent, content);
		 evMg.goToParticipantsTab();
		 evMg.goToInvitationParticipantForm();
		 evMg.selectUserParticipants(DATA_USER2, content,1);
		 evMg.saveInvitationParticipantForm();
		 waitForAndGetElement(evMg.ELEMENT_INVITATION_PARTICIPANTS_USER.replace("$fullName",DATA_USER2));
 	}

	/**
	*<li> Case ID:116421.</li>
	*<li> Test Case Name: Check input participant who is not existed by manual to send invitation mail.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test53_CheckInputParticipantWhoIsNotExistedByManualToSendInvitationMail() {
		info("Test 53 Check input participant who is not existed by manual to send invitation mail");
		/*Step Number: 1
		*Step Name: Step 1: Open Add/Edit Event form
		*Step Description: 
			- Click Add eventbutton on action bar
			- Click "More details" button on "Quick Add Event" form
		*Input Data: 
			
		*Expected Outcome: 
			- "Quick Add Event" form is shown
			- The "Add/Edit Event" form is shown*/

		/*Step number: 2
		*Step Name: Step 2: Open Select User form
		*Step Description: 
			- Go to Participants tab
			- Click "Add Participant" icon
		*Input Data: 
			
		*Expected Outcome: 
			Invitation form is shown properly*/

		/*Step number: 3
		*Step Name: Step 3: input participant
		*Step Description: 
			-Input participant who is not existed into the first textarea
			-Click Save
		*Input Data: 
			
		*Expected Outcome: 
			A warning message is shown to report that : invalid participant*/ 
		 String titleEvent = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		 String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		 String user=getRandomString();
		 info("Add a event");
		 hp.goToCalendarPage();		
		 evMg.goToAddEventFromActionBar();
		 evMg.moreDetailsEvent();
		 evMg.inputBasicDetailEvent(titleEvent, content);
		 evMg.goToParticipantsTab();
		 evMg.goToInvitationParticipantForm();
		 evMg.selectUserParticipants(user, content,0);
		 evMg.saveInvitationParticipantForm();
		 waitForAndGetElement(evMg.ELEMENT_INVITATION_PARTICIPANTS_INVALID_USER_MESSAGE.replace("$user",user));
 	}

	/**
	*<li> Case ID:116422.</li>
	*<li> Test Case Name: Check input invalid email address by manual to send invitation mail.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test54_CheckInputInvalidEmailAddressByManualToSendInvitationMail() {
		info("Test 54 Check input invalid email address by manual to send invitation mail");
		/*Step Number: 1
		*Step Name: Step 1: Input participant(s)
		*Step Description: 
			- Open "Quick Add Event" from by clicking "Event" icon on action bar
			- Open "Add/Edit Event" form by clicking "More details" button on "Quick Add Event" form
			- Go to "Participants" tab to add participant
		*Input Data: 
			
		*Expected Outcome: 
			The "Participant" tab of "Add/Edit Event" form is shown.*/

		/*Step number: 2
		*Step Name: Step 2: Open Select User form
		*Step Description: 
			Click "Add Participant" icon
		*Input Data: 
			
		*Expected Outcome: 
			Invitation form is shown properly*/

		/*Step number: 3
		*Step Name: Step 3: Input invalid email address as abc@vn
		*Step Description: 
			- Input invalid email address as abc@vn
			- Click Save
		*Input Data: 
			
		*Expected Outcome: 
			A warning message is shown to report that : invalid email*/ 
		 String titleEvent = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		 String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		 String email=getRandomString()+"@vn";
		 info("Add a event");
		 hp.goToCalendarPage();		
		 evMg.goToAddEventFromActionBar();
		 evMg.moreDetailsEvent();
		 evMg.inputBasicDetailEvent(titleEvent, content);
		 evMg.goToParticipantsTab();
		 evMg.goToInvitationParticipantForm();
		 evMg.selectUserParticipants(user, content,0);
		 evMg.saveInvitationParticipantForm();
		 waitForAndGetElement(evMg.ELEMENT_INVITATION_PARTICIPANTS_INVALID_EMAIL_MESSAGE.replace("$email",email));
 	}

	/**
	*<li> Case ID:116423.</li>
	*<li> Test Case Name: Remove participant(s) or email (s) which was selected to send invitation mail.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test55_RemoveParticipantOrEmailWhichWasSelectedToSendInvitationMail() {
		info("Test 55 Remove participant(s) or email (s) which was selected to send invitation mail");
		/*Step Number: 1
		*Step Name: Step 1: Open Add/Edit Event form
		*Step Description: 
			- Click "Event" button on action bar.
			- Click "More details" button on "Quick Add Event" form
		*Input Data: 
			
		*Expected Outcome: 
			- The "Quick Add Event" form is shown
			- The "Add/Edit Event" form is shown*/

		/*Step number: 2
		*Step Name: Step 2: Add Participant(s) or email(s)
		*Step Description: 
			- Open Participant tab on "Add/Edit Event" form
			- Add Participant(s) or Email(s) by clicking "Add Participant" icon on "Add/Edit Event" form
		*Input Data: 
			
		*Expected Outcome: 
			The Participant(s) Or email(s) is added into a table on Participants tab*/

		/*Step number: 3
		*Step Name: Step 3: Remove Participant(s) or Email(s)
		*Step Description: 
			Click Delete icon of corresponding participant(s) or email(s)
		*Input Data: 
			
		*Expected Outcome: 
			- The selected Participant(s) or email(s) is removed*/ 
		 String titleEvent = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		 String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		 info("Add a event");
		 hp.goToCalendarPage();		
		 evMg.goToAddEventFromActionBar();
		 evMg.moreDetailsEvent();
		 evMg.inputBasicDetailEvent(titleEvent, content);
		 evMg.goToParticipantsTab();
		 evMg.goToInvitationParticipantForm();
		 evMg.selectUserParticipants(DATA_USER2, content,0);
		 evMg.saveInvitationParticipantForm();
		 evMg.removeUser(DATA_NAME_USER2);
 	}

	/**
	*<li> Case ID:116472.</li>
	*<li> Test Case Name: Add quick event from action bar.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test56_AddQuickEventFromActionBar() {
		info("Test 56 Add quick event from action bar");
		/*Step Number: 1
		*Step Name: -
		*Step Description: 
			Step 1: Show quick add event form
		*Input Data: 
			Click Event in action bar
		*Expected Outcome: 
			Quick add event form appears properly with default calendar, 
			default category and current default date time.*/

		/*Step number: 2
		*Step Name: -
		*Step Description: 
			Step 2: Add quick event
		*Input Data: 
			- Input required fields
			- Click Save/Save
		*Expected Outcome: 
			- The new event is added and shown in the selected calendar
			- Working pane is kept during creating event*/ 
		 String titleEvent = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		 String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		 info("Add a event");
		 hp.goToCalendarPage();		
		 evMg.goToAddEventFromActionBar();
		 evMg.inputBasicQuickEvent(titleEvent, content);
		 evMg.saveQuickAddEvent();
		 cHome.verifyIsPresentEventTaskWithDateTime(titleEvent,getDate(0,"MMM dd yyyy"),selectViewOption.LIST, selectDayOption.ONEDAY);
		 
		 info("Delete Event");
 		 hp.goToCalendarPage();
 		 cHome.goToView(selectViewOption.LIST);
 		 cMang.deleteTaskEvent(titleEvent);
	}

	/**
	*<li> Case ID:116473.</li>
	*<li> Test Case Name: Add quick event from left pane.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test57_AddQuickEventFromLeftPane() {
		info("Test 57 Add quick event from left pane");
		/*Step Number: 1
		*Step Name: -
		*Step Description: 
			Step 1: Show quick add event form
		*Input Data: 
			- Click on a specific calendar and select Add event
		*Expected Outcome: 
			Quick add event form appears properly with default calendar, default category and current default date time.*/

		/*Step number: 2
		*Step Name: -
		*Step Description: 
			Step 2: Add quick event
		*Input Data: 
			- Input required fields
			- Click Save/Save
		*Expected Outcome: 
			The new event is added and shown in this specific calendar*/ 
		info("Create a new calendar");
		String calendar = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
        hp.goToCalendarPage();
		cHome.goToView(selectViewOption.WEEK);
        cMang.goToMenuFromMainCalendar(menuOfMainCalendar.ADDCAL);
        cMang.inputDataInDetailTabCalendarForm(calendar, calendar,null);
        cMang.saveAddCalendar();
        
        
        info("Create an event in the calendar");
        String titleEvent = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToCalendarPage();
		cMang.executeActionCalendar(calendar, menuOfCalendarOption.ADDEVENT);
		evMg.inputBasicQuickEvent(titleEvent,content);
		evMg.saveQuickAddEvent();
		info("Add successfully");
		cHome.verifyIsPresentEventTaskWithDateTime(titleEvent,getDate(0,"MMM dd yyyy"),selectViewOption.LIST, selectDayOption.ONEDAY);
		 
		
		cHome.goToView(selectViewOption.LIST);
		cMang.showHideEventTask(calendar, false);
		waitForElementNotPresent(cMang.ELEMENT_EVENT_TASK_TITLE.replace("${name}",titleEvent));
		
		cMang.showHideEventTask(calendar,true);
		waitElementAndTryGetElement(cMang.ELEMENT_EVENT_TASK_TITLE.replace("${name}",titleEvent));
		cMang.deleteTaskEvent(titleEvent);
		
 	}

	/**
	*<li> Case ID:116474.</li>
	*<li> Test Case Name: Add quick event from working pane  by left click.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test58_AddQuickEventFromWorkingPaneByLeftClick() {
		info("Test 58 Add quick event from working pane  by left click");
		/*Step Number: 1
		*Step Name: -
		*Step Description: 
			Step 1: Show quick add event form
		*Input Data: 
			- In working pane, left click on a specific date and time
		*Expected Outcome: 
			Quick add event form appears properly with default calendar, 
			default category and currentdefault date time.*/

		/*Step number: 2
		*Step Name: -
		*Step Description: 
			Step 2: Add quick event
		*Input Data: 
			- Input required fields
			- Click Save/Save
		*Expected Outcome: 
			The new event is added and shown in this specific calendar*/
		info("Create a new calendar");
		String calendar = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
        hp.goToCalendarPage();
		cHome.goToView(selectViewOption.WEEK);
        cMang.goToMenuFromMainCalendar(menuOfMainCalendar.ADDCAL);
        cMang.inputDataInDetailTabCalendarForm(calendar, calendar,null);
        cMang.saveAddCalendar();
        
		String titleEvent = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
        hp.goToCalendarPage();
        cHome.openAddEditEventTaskByLeftClick(getDate(0,"MMM dd yyyy HH")+":00",selectViewOption.WEEK,selectDayOption.ONEDAY);
    	evMg.inputBasicQuickEvent(titleEvent,content,calendar);
		evMg.saveQuickAddEvent();
		info("Add successfully");
		cHome.verifyIsPresentEventTaskWithDateTime(titleEvent,getDate(0,"MMM dd yyyy HH")+":00",selectViewOption.LIST, selectDayOption.ONEDAY);
		 
		cHome.goToView(selectViewOption.LIST);
		cMang.showHideEventTask(calendar, false);
		waitForElementNotPresent(cMang.ELEMENT_EVENT_TASK_TITLE.replace("${name}",titleEvent));
		
		cMang.showHideEventTask(calendar,true);
		waitElementAndTryGetElement(cMang.ELEMENT_EVENT_TASK_TITLE.replace("${name}",titleEvent));
		cMang.deleteTaskEvent(titleEvent);
	}

	/**
	*<li> Case ID:116475.</li>
	*<li> Test Case Name: Add quick event from calendar view by right click.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test59_AddQuickEventFromCalendarViewByRightClick() {
		info("Test 59 Add quick event from calendar view by right click");
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			Step 1: Show quick add event form
		*Input Data: 
			- In view calendar, right click on a specific date and time and select Add new event
		*Expected Outcome: 
			Quick add event form appears properly with default calendar, default category and current default date time.*/

		/*Step number: 2
		*Step Name: -
		*Step Description: 
			Step 2: Add quick event
		*Input Data: 
			- Input required fields
			- Click Save/Save
		*Expected Outcome: 
			The new event is added and shown in this specific calendar*/ 
		info("Create a new calendar");
		String calendar = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
        hp.goToCalendarPage();
		cHome.goToView(selectViewOption.WEEK);
        cMang.goToMenuFromMainCalendar(menuOfMainCalendar.ADDCAL);
        cMang.inputDataInDetailTabCalendarForm(calendar, calendar,null);
        cMang.saveAddCalendar();
        
        String titleEvent = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
        hp.goToCalendarPage();
		cHome.openAddEditEventTaskByRightClick(getDate(0, "MMM dd yyyy HH")
				+ ":00", selectViewOption.WEEK, selectDayOption.ONEDAY,
				contextMenuAddEditEvenTaskOption.ADD_EVENT);
		evMg.inputBasicQuickEvent(titleEvent, content, calendar);
		evMg.saveQuickAddEvent();
		info("Add successfully");
		waitForElementNotPresent(cMang.ELEMENT_EVENT_TASK_TITLE.replace("${name}",titleEvent));

		cHome.goToView(selectViewOption.LIST);
		cMang.showHideEventTask(calendar, false);
		waitForElementNotPresent(cMang.ELEMENT_EVENT_TASK_TITLE.replace("${name}",titleEvent));
		
		cMang.showHideEventTask(calendar,true);
		waitElementAndTryGetElement(cMang.ELEMENT_EVENT_TASK_TITLE.replace("${name}",titleEvent));
		cMang.deleteTaskEvent(titleEvent);
        
        
 	}}
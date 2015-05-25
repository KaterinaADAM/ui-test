package org.exoplatform.selenium.platform.calendar.functional;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.calendar.CalendarHomePage.selectDayOption;
import org.exoplatform.selenium.platform.calendar.CalendarHomePage.selectViewOption;
import org.exoplatform.selenium.platform.calendar.CalendarManagement.menuOfCalendarOption;
import org.exoplatform.selenium.platform.calendar.CalendarManagement.menuOfMainCalendar;
import org.exoplatform.selenium.platform.calendar.EventManagement.priorityType;
import org.exoplatform.selenium.platform.calendar.EventManagement.recurringType;
import org.exoplatform.selenium.platform.calendar.EventManagement.repeatEndType;
import org.exoplatform.selenium.platform.calendar.EventManagement.repeatType;
import org.openqa.selenium.By;
import org.testng.annotations.*;


	public class CAL_Event_Add_Part2 extends CAL_TestConfig_2{
	
	public void createUser() {
		int index = userInfoData.getRandomIndexByType(3);
		username = userInfoData.newUserName.get(index) + getRandomNumber();
		firstname = userInfoData.newFirstName.get(index);
		String lastname = userInfoData.newLastName.get(index);
		String password = userInfoData.newPassword.get(index);
		String email = EMAIL_ADDRESS1;

		navTool.goToAddUser();
		addUserPage.addUser(username, password, email, firstname, lastname);
	}
	/**
	*<li> Case ID:116372.</li>
	*<li> Test Case Name: Add event with priority.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*CANNOT AUTOMATE: CANNOT VERIFY STYLE OF PRIORITY'S FLAG
	*/
	@Test(groups="pending")
	public  void test20_AddEventWithPriority() {
		info("Test 20 Add event with priority");
		/*Step Number: 1
		*Step Name: - Step 1: Show add new event form
		*Step Description: 
			- Select Month View
			- Select days to create event in working pane
		*Input Data: 
			
		*Expected Outcome: 
			Add new event form appears, 
			From and To date is the selected From and To date in working pane*/

		/*Step number: 2
		*Step Name: - Step 2: Complete adding new event
		*Step Description: 
			- Select one value for Priority field
			- Input value for other fields 
			- Click Save/Save
		*Input Data: 
			
		*Expected Outcome: 
			Event is added with corresponding icon for selected priority if it's not None
			- Priority is Low, Event is shown on working pane with a flag
			- Priority is Normal, Event is shown on working panel with larger flag than low event
			- Priority is High, Event is shown on working pane with larger flag than Normal event*/ 
		String titleEvent= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String contentEvent= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
        hp.goToCalendarPage(); 
		cHome.goToView(selectViewOption.MONTH);
		evMg.goToAddEventByRightClickFromMainPanel(null,null);
		evMg.moreDetailsEvent();
		evMg.inputBasicDetailEvent(titleEvent,contentEvent,null,null,null,String.valueOf(priorityType.Low));
		evMg.saveAddEventDetails();
		
 	}

	/**
	*<li> Case ID:116373.</li>
	*<li> Test Case Name: Add new event when selected value is No repeat.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test21_AddNewEventWhenSelectedValueIsNoRepeat() {
		info("Test 21 Add new event when selected value is No repeat");
		createUser();
		/*Step Number: 1
		*Step Name: -
		*Step Description: 
			Step 1: Show add new event form
		*Input Data: 
			- Click Add event on theaction bar
			- Click on More detail
		*Expected Outcome: 
			- Add detail event form appears properly
			- Display 4 tabs: Detail, Reminders, Participants and Schedule.*/
		String titleEvent= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String contentEvent= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String numberRepeat="5";
        hp.goToCalendarPage(); 
		cHome.goToView(selectViewOption.WEEK);
		evMg.goToAddEventFromActionBar();
		evMg.moreDetailsEvent();
		evMg.inputDataEventInDetailForm(titleEvent, contentEvent,getFirstDayOfWeek("MM/dd/yyyy")+" 02:00",getFirstDayOfWeek("MM/dd/yyyy")+" 03:00",false);
		info("Display 4 tabs: Detail, Reminders, Participants and Schedule.");
		waitForAndGetElement(evMg.ELEMENT_EVENT_REMINDER_TAB);
		waitForAndGetElement(evMg.ELEMENT_EVENT_PARTICIPANTS_TAB);
		waitForAndGetElement(evMg.ELEMENT_EVENT_SCHEDULE_TAB);
		waitForAndGetElement(evMg.ELEMENT_EVENT_DETAILS_TAB);
		/*Step number: 2
		*Step Name: -
		*Step Description: 
			Step 2: Complete adding new event
		*Input Data: 
			- Input data into all required field
			- Choose â€œNo repeatâ€ item of Repeat field of Detail tab 
			- Click Save
		*Expected Outcome: 
			Event is created at specific duration without repeating at the other time.*/ 
		evMg.openRecurringForm();
		evMg.inputRecurringInfoEvent(repeatType.Daily,"1",null,repeatEndType.After,numberRepeat);
		evMg.saveRecurringForm();
		evMg.saveAddEventDetails();
		
		info("Event is created at specific duration without repeating at the other time");
		waitForAndGetElement(cHome.ELEMENT_EVENT_TASK_DETAIL_TIME_WEEK.replace("$date","Mon").replace("$name",titleEvent).replace("$time",getCurrentDate("02:00")));
		waitForAndGetElement(cHome.ELEMENT_EVENT_TASK_DETAIL_TIME_WEEK.replace("$date","Tue").replace("$name",titleEvent).replace("$time",getCurrentDate("02:00")));
		waitForAndGetElement(cHome.ELEMENT_EVENT_TASK_DETAIL_TIME_WEEK.replace("$date","Wed").replace("$name",titleEvent).replace("$time",getCurrentDate("02:00")));
		waitForAndGetElement(cHome.ELEMENT_EVENT_TASK_DETAIL_TIME_WEEK.replace("$date","Thu").replace("$name",titleEvent).replace("$time",getCurrentDate("02:00")));
		waitForAndGetElement(cHome.ELEMENT_EVENT_TASK_DETAIL_TIME_WEEK.replace("$date","Fri").replace("$name",titleEvent).replace("$time",getCurrentDate("02:00")));
 	
		info("Delete data");
		hp.goToCalendarPage();
		evMg.deleteRecurringEvent(titleEvent, selectViewOption.WEEK,selectDayOption.ONEDAY,recurringType.ALL_EVENT,"Mon");
	}

	/**
	*<li> Case ID:116374.</li>
	*<li> Test Case Name: Add new event when selected value is Repeat.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test22_AddNewEventWhenSelectedValueIsRepeat() {
		info("Test 22 Add new event when selected value is Repeat");
		/*Step Number: 1
		*Step Name: -
		*Step Description: 
			Step 1: Show add new event form
		*Input Data: 
			- Click Add event on theaction bar
			- Click on More detail
		*Expected Outcome: 
			- Add detail event form appears properly
			- Display 4 tabs: Detail, Reminders, Participants and Schedule.*/
		String titleEvent= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String contentEvent= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String numberRepeat="5";
        hp.goToCalendarPage(); 
		cHome.goToView(selectViewOption.WEEK);
		evMg.goToAddEventFromActionBar();
		evMg.moreDetailsEvent();
		evMg.inputDataEventInDetailForm(titleEvent, contentEvent,getFirstDayOfWeek("MM/dd/yyyy")+" 02:00",getFirstDayOfWeek("MM/dd/yyyy")+" 03:00",false);
		
		info("Display 4 tabs: Detail, Reminders, Participants and Schedule.");
		waitForAndGetElement(evMg.ELEMENT_EVENT_REMINDER_TAB);
		waitForAndGetElement(evMg.ELEMENT_EVENT_PARTICIPANTS_TAB);
		waitForAndGetElement(evMg.ELEMENT_EVENT_SCHEDULE_TAB);
		waitForAndGetElement(evMg.ELEMENT_EVENT_DETAILS_TAB);
		/*Step number: 2
		*Step Name: -
		*Step Description: 
			Step 2: Complete adding new event
		*Input Data: 
			- Input data into all required field
			- Choose a item of Repeat field of Detail tab without ' No Repeat' 
			- Click Save
		*Expected Outcome: 
			Event is created at specific duration with repeating feature depending its selected option in Repeat field.*/ 
		evMg.openRecurringForm();
		evMg.inputRecurringInfoEvent(repeatType.Daily,"1",null,repeatEndType.After,numberRepeat);
		evMg.saveRecurringForm();
		evMg.saveAddEventDetails();
		
		info("Event is created at specific duration without repeating at the other time");
		waitForAndGetElement(cHome.ELEMENT_EVENT_TASK_DETAIL_TIME_WEEK.replace("$date","Mon").replace("$name",titleEvent).replace("$time",getCurrentDate("02:00")));
		waitForAndGetElement(cHome.ELEMENT_EVENT_TASK_DETAIL_TIME_WEEK.replace("$date","Tue").replace("$name",titleEvent).replace("$time",getCurrentDate("02:00")));
		waitForAndGetElement(cHome.ELEMENT_EVENT_TASK_DETAIL_TIME_WEEK.replace("$date","Wed").replace("$name",titleEvent).replace("$time",getCurrentDate("02:00")));
		waitForAndGetElement(cHome.ELEMENT_EVENT_TASK_DETAIL_TIME_WEEK.replace("$date","Thu").replace("$name",titleEvent).replace("$time",getCurrentDate("02:00")));
		waitForAndGetElement(cHome.ELEMENT_EVENT_TASK_DETAIL_TIME_WEEK.replace("$date","Fri").replace("$name",titleEvent).replace("$time",getCurrentDate("02:00")));
 	
		info("Delete data");
		hp.goToCalendarPage();
		evMg.deleteRecurringEvent(titleEvent, selectViewOption.WEEK,selectDayOption.ONEDAY,recurringType.ALL_EVENT,"Mon");
 	}

	/**
	*<li> Case ID:116375.</li>
	*<li> Test Case Name: Add new event with attachments in case total file size is more than 10MB.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test23_AddNewEventWithAttachmentsInCaseTotalFileSizeIsMoreThan10MB() {
		info("Test 23 Add new event with attachments in case total file size is more than 10MB");
		/*Step Number: 1
		*Step Name: -
		*Step Description: 
			Step 1: Show add new event form
		*Input Data: 
			- Click Add event on theaction bar
			- Click on More detail
		*Expected Outcome: 
			- Add detail event form appears properly
			- Display 4 tabs: Detail, Reminders, Participants and Schedule.*/
		String titleEvent= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String contentEvent= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
        hp.goToCalendarPage(); 
		cHome.goToView(selectViewOption.WEEK);
		evMg.goToAddEventFromActionBar();
		evMg.moreDetailsEvent();
		evMg.inputBasicDetailEvent(titleEvent,contentEvent);
		info("Display 4 tabs: Detail, Reminders, Participants and Schedule.");
		waitForAndGetElement(evMg.ELEMENT_EVENT_REMINDER_TAB);
		waitForAndGetElement(evMg.ELEMENT_EVENT_PARTICIPANTS_TAB);
		waitForAndGetElement(evMg.ELEMENT_EVENT_SCHEDULE_TAB);
		waitForAndGetElement(evMg.ELEMENT_EVENT_DETAILS_TAB);
		
		/*Step number: 2
		*Step Name: -
		*Step Description: 
			Step 2: Add new event with invalid attachment size
		*Input Data: 
			- Click Attach file and browse file(s) to attach and total file size is more than 10MB
			- Input values for other fields
			- Click Save
		*Expected Outcome: 
			A warning message is shown to report that : total files size must be less than 10MB*/ 
		String link = fData.getAttachFileByArrayTypeRandom(70);
		info("link:"+link);
		info("Add attachment");
		evMg.attachFileToEvent(link,true);
		String warningMess=alert.getTextFromAlert();
		info("warningMess:"+warningMess);
		String expectedText="The file must be less than 10 MB.";
		alert.acceptAlert();
		if(warningMess.equalsIgnoreCase(expectedText)==false)
			assert false:"incorrect warning message";
		
 	}

	/**
	*<li> Case ID:116376.</li>
	*<li> Test Case Name: Remove attachment when adding new event.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test24_RemoveAttachmentWhenAddingNewEvent() {
		info("Test 24 Remove attachment when adding new event");
		/*Step Number: 1
		*Step Name: -
		*Step Description: 
			Step 1: Attach files when add new event
		*Input Data: 
			- Click Add Event on action bar
			- In Detail add new event form: Click Attach file and browse some files to attach
		*Expected Outcome: 
			The uploaded files are displayed in attachments list in Add new event form, allow removing each uploaded file*/
		String titleEvent = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String link = fData.getAttachFileByArrayTypeRandom(2);
		info("Add a event");
		hp.goToCalendarPage();		
		evMg.goToAddEventFromActionBar();
		evMg.moreDetailsEvent();
		info("Add attachment");
		evMg.attachFileToEvent(link);
		
		/*Step number: 2
		*Step Name: -
		*Step Description: 
			Step 2: Remove uploaded file
		*Input Data: 
			Click Remove link of corresponding uploaded file
		*Expected Outcome: 
			The uploaded file is removed from attachments list*/
		evMg.removeAttachment(link );
		
		/*Step number: 3
		*Step Name: -
		*Step Description: 
			Step 3: Complete adding new event
		*Input Data: 
			- Input other fields in Add new event form
			- Click Save
		*Expected Outcome: 
			New event is added with attachments but without removed attached file*/ 
		evMg.inputBasicDetailEvent(titleEvent, content);
        evMg.saveAddEventDetails();
        cHome.goToView(selectViewOption.LIST);
        waitForElementNotPresent(evMg.ELEMENT_ATTACH_FILE_NAME.replace("$fileName",link));
		
        info("Delete Data");
		cMang.deleteTaskEvent(titleEvent);
 	}

	/**
	*<li> Case ID:116377.</li>
	*<li> Test Case Name: Show Add/Edit Event form.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test25_ShowAddEditEventForm() {
		info("Test 25 Show Add/Edit Event form");
		/*Step Number: 1
		*Step Name: - Step 1: Open Add/Edit Event form
		*Step Description: 
			- Click at Add eventbutton on action bar
		*Input Data: 
			
		*Expected Outcome: 
			The Add/Edit Events form is shown properly*/ 
		info("Add a event");
		hp.goToCalendarPage();		
		evMg.goToAddEventFromActionBar();
 	}

	/**
	*<li> Case ID:116378.</li>
	*<li> Test Case Name: Check input participant(s) automatically to send invitation email.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test26_CheckInputParticipantAutomaticallyToSendInvitationEmail() {
		info("Test 26 Check input participant(s) automatically to send invitation email");
		/*Step Number: 1
		*Step Name: -
		*Step Description: 
			Step 2: Open Select User form
		*Input Data: 
			- Go to Participants tab
			- Click ' AddUsert' icon
		*Expected Outcome: 
			Invitation form is shown properly*/

		/*Step number: 2
		*Step Name: -
		*Step Description: 
			Step 3: Open Select User form
		*Input Data: 
			- Click User Picker icon
		*Expected Outcome: 
			Select Users form is shown properly*/

		/*Step number: 3
		*Step Name: -
		*Step Description: 
			Step 4: Search user
		*Input Data: 
			- Choose group type
			- Choose search way: Search with Last name or first name or email or user name
			- Input keyword into text box 
			- Click Quick search icon
		*Expected Outcome: 
			Result is shown correctly*/

		/*Step number: 4
		*Step Name: -
		*Step Description: 
			Step 5: Select User
		*Input Data: 
			- Tick at corresponding checkbox of each user
			-Click Add
			- Click Close
		*Expected Outcome: 
			- Username of selected user(s) is filled into [participants to invite] field. Each of them is on one line*/ 
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String titleEvent=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content1=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String users=DATA_USER2+"/"+DATA_USER4;
		info("Add a event");
		hp.goToCalendarPage();		
		evMg.goToAddEventFromActionBar();
		evMg.moreDetailsEvent();
		evMg.inputBasicDetailEvent(titleEvent, content1);
		evMg.goToParticipantsTab();
		evMg.goToInvitationParticipantForm();
		evMg.selectUserParticipants(users, content,1);
		evMg.saveInvitationParticipantForm();
		waitForAndGetElement(evMg.ELEMENT_INVITATION_PARTICIPANTS_USER.replace("$fullName",DATA_NAME_USER2));
		waitForAndGetElement(evMg.ELEMENT_INVITATION_PARTICIPANTS_USER.replace("$fullName",DATA_NAME_USER4));
		evMg.saveAddEventDetails();
		
		info("Delete Data");
		cMang.deleteTaskEvent(titleEvent);
 	}

	/**
	*<li> Case ID:116379.</li>
	*<li> Test Case Name: Add event in several days from working pane of Month View.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test27_AddEventInSeveralDaysFromWorkingPaneOfMonthView() {
		info("Test 27 Add event in several days from working pane of Month View");
		/*Step Number: 1
		*Step Name: -
		*Step Description: 
			Step 1: Show add new event form
		*Input Data: 
			- Select Month View
			- Select days to create event in working pane
		*Expected Outcome: 
			Add new event form appears, From and To date is the selected From and To date in working pane*/

		String titleEvent= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String contentEvent= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
        hp.goToCalendarPage(); 
		cHome.goToView(selectViewOption.MONTH);
		evMg.goToAddEventByRightClickForMothnView(null);
		
		/*Step number: 2
		*Step Name: -
		*Step Description: 
			Step 2: Complete adding new event
		*Input Data: 
			- Input value for fields
			- Click Save/Save
		*Expected Outcome: 
			Added event is displayed in working pane*/ 
		evMg.inputDataEventInQuickForm(titleEvent, contentEvent,getFirstDayOfWeek("MM/dd/yyyy"),getLastDayOfWeek("MM/dd/yyyy"),false);
		evMg.saveQuickAddEvent();
        waitForAndGetElement(cHome.ELEMENT_EVENT_TASK_MONTH_VIEW.replace("$name",titleEvent));
        
        info("Delete Data");
		cMang.deleteTaskEvent(titleEvent);
 	}

	/**
	*<li> Case ID:116380.</li>
	*<li> Test Case Name: Delete event's "all" category.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test28_DeleteEventsAllCategory() {
		info("Test 28 Delete event's all category");
		/*Step Number: 1
		*Step Name: - Step 1: Perform to Delete "all"category
		*Step Description: 
			- Click Calendar Actions
			- Click "Add Event Category"
			- Click delete icon for "All" category
			- Confirm deleting
		*Input Data: 
			
		*Expected Outcome: 
			A message is displayed "Cannot delete the default event category."*/ 
        hp.goToCalendarPage();
        cMang.goToMenuFromMainCalendar(menuOfMainCalendar.ADDCATEGORY);
        click(cMang.ELEMENT_LIST_DELETE_EVENT_BUTTON.replace("${categoryName}","All"));
        waitForAndGetElement(cMang.ELEMENT_DELETE_ALL_CATEGORY);
        click(cMang.ELEMENT_DELETE_ALL_CATEGORY_OK_BTN);
        
 	}

	/**
	*<li> Case ID:116381.</li>
	*<li> Test Case Name: Add quick event from existing event or task.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*CANNOT AUTOMATE: THE CASE IS TOO OLD. WORK FLOW IS INCORRECT
	*/
	@Test(groups="pending")
	public  void test29_AddQuickEventFromExistingEventOrTask() {
		info("Test 29 Add quick event from existing event or task");
		/*Step Number: 1
		*Step Name: - Step 1: Show quick add event form
		*Step Description: 
			- In view calendar, right click on a existing event/task and select Add new event
		*Input Data: 
			
		*Expected Outcome: 
			Quick add event form appears properly with default calendar, default category and current default date time.*/

		/*Step number: 2
		*Step Name: - Step 2: Add quick event
		*Step Description: 
			- Input required fields
			- Click Save
		*Input Data: 
			
		*Expected Outcome: 
			The new event is added and shown in this specific calendar*/ 

 	}

	/**
	*<li> Case ID:116388.</li>
	*<li> Test Case Name: Check input many participants,emails to send invitation mail.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test30_CheckInputManyParticipantsemailsToSendInvitationMail() {
		info("Test 30 Check input many participants,emails to send invitation mail");
		/*Step Number: 1
		*Step Name: - Step 1: Open Add/Edit Event form
		*Step Description: 
			- Click "Event" button on action bar
			- Click "More details" button on "Quick Add Event" form
		*Input Data: 
			
		*Expected Outcome: 
			- The "Quick Add Event" form is shown
			- The "Add/Edit Event" form is shown*/

		/*Step number: 2
		*Step Name: - Step 2: Open Select User form
		*Step Description: 
			- Open Participant tab on "Add/Edit Event" form
			- Click "Add Participant" icon
		*Input Data: 
			
		*Expected Outcome: 
			Invitation form is shown properly*/

		/*Step number: 3
		*Step Name: - Step 3: Input participant or email
		*Step Description: 
			- Input many valid participants, each one in a separate line
			- Click Save
		*Input Data: 
			
		*Expected Outcome: 
			These participants Or emails are filled into a table onParticipant Tab, and each of them is on a line*/ 
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String titleEvent=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content1=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String users=DATA_USER2+"/"+DATA_USER4;
		info("Add a event");
		hp.goToCalendarPage();		
		evMg.goToAddEventFromActionBar();
		evMg.moreDetailsEvent();
		evMg.inputBasicDetailEvent(titleEvent, content1);
		evMg.goToParticipantsTab();
		evMg.goToInvitationParticipantForm();
		evMg.selectUserParticipants(users, content,1);
		evMg.saveInvitationParticipantForm();
		waitForAndGetElement(evMg.ELEMENT_INVITATION_PARTICIPANTS_USER.replace("$fullName",DATA_NAME_USER2));
		waitForAndGetElement(evMg.ELEMENT_INVITATION_PARTICIPANTS_USER.replace("$fullName",DATA_NAME_USER4));
 	}

	/**
	*<li> Case ID:116389.</li>
	*<li> Test Case Name: Check Send invitation mail when use calendar setting to be Never send invitation.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test31_CheckSendInvitationMailWhenUseCalendarSettingToBeNeverSendInvitation() {
		info("Test 31 Check Send invitation mail when use calendar setting to be Never send invitation");
		/*Step Number: 1
		*Step Name: Step 1: Setting calendar
		*Step Description: 
			- Go to Intranet: open Calendar application
			- Choose "Setting" icon on action bar
			- On "Setting" tab, choose Never send invitation mail
		*Input Data: 
			
		*Expected Outcome: 
			- Set calendar successfully*/
		createUser();
		info("Change setting");
		hp.goToCalendarPage();		
		cMang.goToMenuFromMainCalendar(menuOfMainCalendar.CALSETTING);
		cMang.changeSettingCalendar(null,null,null,null,null,null,null);
		cMang.saveSetting();
		
		/*Step number: 2
		*Step Name: Step 2: Open Add/edit event form
		*Step Description: 
			- Open "Quick Add Event" from by clicking "Event" icon on action bar
			- Open "Add/Edit Event" form by clicking "More details" button on "Quick Add Event" form
		*Input Data: 
			
		*Expected Outcome: 
			The form is shown properly*/
		
		/*Step number: 3
		*Step Name: Step 3: Add participant(s) or email(s) to send invitation mail
		*Step Description: 
			- Go to Participants taband choose participant(s) or email(s)[ Details ]
			- [ Never ] option is selected automatically 
			- Go to Detail tab, input name for the event
			- Click Save
		*Input Data: 
			
		*Expected Outcome: 
			The new event is created but No invitation mail is sent to selected participant(s) or selected email*/ 
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
		evMg.saveAddEventDetails();
		cHome.goToView(selectViewOption.WEEK);
		waitForAndGetElement(cMang.ELEMENT_EVENT_TASK_TITLE.replace("${name}",titleEvent));
		
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(20000);
		cMang.checkEmailNotificationCalendar(titleEvent,"","","",false);
        switchToParentWindow();
        
		info("Delete Data");
		cMang.deleteTaskEvent(titleEvent);
		navTool.goToUsersAndGroupsManagement();
		userAndGroup.deleteUser(username);
 	}

	/**
	*<li> Case ID:116390.</li>
	*<li> Test Case Name: Check Send invitation mail when use calendar setting to be Always send invitation.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test32_CheckSendInvitationMailWhenUseCalendarSettingToBeAlwaysSendInvitation() {
		info("Test 32 Check Send invitation mail when use calendar setting to be Always send invitation");
		/*Step Number: 1
		*Step Name: Step 1: Setting calendar
		*Step Description: 
			- Go to Intranet
			- Open the Calendar application
			- Choose "Setting" icon on action bar
			- On "Setting" tab, choose Always send invitation mail
		*Input Data: 
			
		*Expected Outcome: 
			- Set calendar successfully*/
		createUser();
		info("Change setting");
		hp.goToCalendarPage();		
		cMang.goToMenuFromMainCalendar(menuOfMainCalendar.CALSETTING);
		cMang.changeSettingCalendar(null,null,null,null,null,null,selectInvitationOption.ALWAYS);
		cMang.saveSetting();
		/*Step number: 2
		*Step Name: Step 2: Create an account mail in Webmail portlet
		*Step Description: 
			Create an account mail in Webmail portlet:e.g : abc@gmail.com
		*Input Data: 
			
		*Expected Outcome: 
			The account is created successfully*/

		/*Step number: 3
		*Step Name: Step 3: Open Add/edit event form
		*Step Description: 
			- Open "Quick Add Event" from by clicking "Event" icon on action bar
			- Open "Add/Edit Event" form by clicking "More details" button on "Quick Add Event" form
		*Input Data: 
			
		*Expected Outcome: 
			The form is shown properly*/

		/*Step number: 4
		*Step Name: Step 4:Finish adding event
		*Step Description: 
			- Go to Participants taband choose participant(s) or email(s)[ Details ]
			- [ Always] option is selected automatically 
			- Go to Detail tab, input name for the event
			- Click Save
		*Input Data: 
			
		*Expected Outcome: 
			- The new event is created successfully
			- An Invitation from abc@gmail.com will be sent to email address of selected participant(s) and selected email.
			- An .ics file will be attached with the invitation mail*/ 
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
		evMg.saveAddEventDetails();
		Utils.pause(2000);
		cHome.goToView(selectViewOption.LIST);
		waitForAndGetElement(cMang.ELEMENT_EVENT_TASK_TITLE.replace("${name}",titleEvent));
		
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(20000);
		cMang.checkEmailNotificationCalendar(titleEvent,"icalendar.ics","","",true,true);
        switchToParentWindow();
        
		info("Delete Data");
		hp.goToCalendarPage();
		cMang.deleteTaskEvent(titleEvent);
		navTool.goToUsersAndGroupsManagement();
		userAndGroup.deleteUser(username);
 	}

	/**
	*<li> Case ID:116391.</li>
	*<li> Test Case Name: Check Send invitation mail when use calendar setting to be Ask when  send invitation.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test33_CheckSendInvitationMailWhenUseCalendarSettingToBeAskWhenSendInvitation() {
		info("Test 33 Check Send invitation mail when use calendar setting to be Ask when  send invitation");
		/*Step Number: 1
		*Step Name: Step 1: Setting calendar
		*Step Description: 
			- Login and go to Calendar page
			- Click on icon [Settings]
			- Choose [Ask] radio
			- Click [Save]
		*Input Data: 
			
		*Expected Outcome: 
			- Calendar settings is updated successfully
			- Option [Send event invitation] is set as "Ask"
			- Option "Ask" is selected by default on tab "Participants" when add an event*/
		createUser();
		info("Change setting");
		hp.goToCalendarPage();		
		cMang.goToMenuFromMainCalendar(menuOfMainCalendar.CALSETTING);
		cMang.changeSettingCalendar(null,null,null,null,null,null,selectInvitationOption.ASK);
		cMang.saveSetting();
		/*Step number: 2
		*Step Name: Step 2: Open Add/edit event form
		*Step Description: 
			Click [Event] on theaction bar
		*Input Data: 
			
		*Expected Outcome: 
			The form is shown properly*/

		/*Step number: 3
		*Step Name: Step 3: Add participant(s) or email(s) to send invitation mail
		*Step Description: 
			- Click on [More details] button
			- Go to [Participants] tab 
			- Click [Add participant] icon
			- Inputusername of users or email addresses, 
			- Click [Save]
		*Input Data: 
			
		*Expected Outcome: 
			The Participant(s) ,email(s) are added to the participants list*/

		/*Step number: 4
		*Step Name: Step 4:Show confirmation sending invitation mail
		*Step Description: 
			- Don't change option [Send invitation]
			- Go to [Details] tab, input name for the event 
			- Click [Save]
		*Input Data: 
			
		*Expected Outcome: 
			- Option "Send invitation" is "Ask" by default
			- An alert message is shown to ask yes/no sending invitation mail: "Would you like to send updates to all guests?"*/

		/*Step number: 5
		*Step Name: Step 5: Agree send invitation mail
		*Step Description: 
			-Click [Yes]
		*Input Data: 
			
		*Expected Outcome: 
			- New event is created 
			- And an Invitation from abc@gmail.com will be sent to email address of selected participant(s) and selected email.
			- An .ics file will be attached with the invitation mail*/ 
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
		evMg.saveAddEventDetails();
		waitForAndGetElement(evMg.ELEMENT_CONFIRM_SEND_INVITATION_MESSAGE);
		waitForAndGetElement(evMg.ELMEMENT_CONFIRM_SEND_INVITATION_YES_BTN);
		waitForAndGetElement(evMg. ELMEMENT_CONFIRM_SEND_INVITATION_NO_BTN);
		
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
	*<li> Case ID:116402.</li>
	*<li> Test Case Name: Check status of participant after create event with invitation mail but the participant do not want to attend at the event.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test34_CheckStatusOfParticipantAfterCreateEventWithInvitationMailButTheParticipantDoNotWantToAttendAtTheEvent() {
		info("Test 34 Check status of participant after create event with invitation mail but the participant do not want to attend at the event");
		/*Step Number: 1
		*Step Name: - Step 1: Create an event with an invitation mail to participant
		*Step Description: 
			- Login by one user ( Root) 
			- Perform to Create an event with an invitation to participant ( Demo: e.g email address of Demo is demo@gmail.com)[ Details ]
		*Input Data: 
			
		*Expected Outcome: 
			The event is created successfully and has an invitation mail is sent to demo@gmail.com*/
         magAc.signOut();
         magAc.signIn(USER_ROOT,DATA_PASS);
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
		*Step Name: - Step 3: Refuse to attend
		*Step Description: 
			- Click [ No] link on the mail
		*Input Data: 
			
		*Expected Outcome: 
			A new tab is opened "You have refused invitation from Root"*/
 		 goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		 Utils.pause(20000);
		 cMang.checkEmailNotificationCalendar(titleEvent,"icalendar.ics","No",USER_ROOT,true,true);
		 switchToParentWindow();
		/*Step number: 4
		*Step Name: - Step 4: Check status of Demo when open above event again
		*Step Description: 
			- Come back Root
			- Open Add/edit event form of above event 
			- Go to Participant tab
		*Input Data: 
			
		*Expected Outcome: 
			- Demo has new status : No*/ 
		hp.goToCalendarPage();
		cMang.openEditEventTaskPopup(titleEvent);
 		evMg.goToParticipantsTab();
 		waitForAndGetElement(evMg.ELEMENT_INVITATION_PARTICIPANTS_REFUSED.replace("$fullName",firstname));
 		evMg.saveAddEventDetails();
 		
 		info("Delete Event");
 		hp.goToCalendarPage();
 		cMang.deleteTaskEvent(titleEvent);
		navTool.goToUsersAndGroupsManagement();
		userAndGroup.deleteUser(username);
 	}

	/**
	*<li> Case ID:116403.</li>
	*<li> Test Case Name: Check Status of participant when he switch between yes/no  and not sure attend the event.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test35_CheckStatusOfParticipantWhenHeSwitchBetweenYesnoAndNotSureAttendTheEvent() {
		info("Test 35 Check Status of participant when he switch between yes/no  and not sure attend the event");
		/*Step Number: 1
		*Step Name: - Step 1: Invite participant(s)
		*Step Description: 
			- Root create an event and invite Demo with invitation mail
			- Go to webmail of Demo and open The invitation mail to view
			- Demo accept/don't accept the invitation [ Details ]
		*Input Data: 
			
		*Expected Outcome: 
			- Status of Demo is changed depend on accept /don't accept the invitation*/

		/*Step number: 2
		*Step Name: - Step 2: Change status of Demo
		*Step Description: 
			- Go to Webmail of Demo
			- Open the invitation mail to view
			- Click the Link [ Maybe]
		*Input Data: 
			
		*Expected Outcome: 
			- A new tab is opened "You have answered invitation from Root : Maybe"*/
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
		*Step Name: -Step 3: Check status of Demo when open above event again
		*Step Description: 
			- Come back Root
			- Open Add/edit event form of above event 
			- Go to Participant tab
		*Input Data: 
			
		*Expected Outcome: 
			- Demo has new status : Maybe*/ 
		 
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
	*<li> Case ID:116404.</li>
	*<li> Test Case Name: Add new event into a shared calendar when shared user has edit right.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test36_AddNewEventIntoASharedCalendarWhenSharedUserHasEditRight() {
		info("Test 36 Add new event into a shared calendar when shared user has edit right");
		/*Step Number: 1
		*Step Name: - Step 1: Add and share calendar
		*Step Description: 
			- Add new calendar 
			- Share added calendar for specific user with edit right
		*Input Data: 
			
		*Expected Outcome: 
			- This calendar appears in share list of shared user*/
		info("Create a new calendar");
		String[] userGroup={DATA_USER2};
		boolean[] canEdit={true};
		String calendar = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
        hp.goToCalendarPage();
		cHome.goToView(selectViewOption.WEEK);
        cMang.goToMenuFromMainCalendar(menuOfMainCalendar.ADDCAL);
        cMang.inputDataInDetailTabCalendarForm(calendar, calendar,null);
        cMang.saveAddCalendar();
		
        info("Share the calendar");
		cMang.shareCalendar(calendar, userGroup,canEdit);
		/*Step number: 2
		*Step Name: - Step 2: Add event into shared calendar
		*Step Description: 
			- Login by shared user
			- Perform to add new event on shared calendar above
		*Input Data: 
			
		*Expected Outcome: 
			New event is added successfully in shared calendar*/ 
        magAc.signOut();
        magAc.signIn(DATA_USER2, DATA_PASS);
        
        info("Create an event in shared calendar");
        String titleEvent = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToCalendarPage();
		cMang.executeActionCalendar(calendar,menuOfCalendarOption.ADDEVENT);
		evMg.inputBasicQuickEvent(titleEvent,content);
		evMg.saveQuickAddEvent();
		info("Add successfully");
		cMang.scrollElementIntoView(this.driver.findElement(By.xpath(cMang.ELEMENT_EVENT_TASK_TITLE.replace("${name}",titleEvent))));
		waitForAndGetElement(cMang.ELEMENT_EVENT_TASK_TITLE.replace("${name}",titleEvent));
		
		info("Delete Event");
 		hp.goToCalendarPage();
 		cHome.goToView(selectViewOption.LIST);
 		cMang.deleteTaskEvent(titleEvent);
 		
 	}

	/**
	*<li> Case ID:116405.</li>
	*<li> Test Case Name: Add new event into a shared calendar when shared user does not have edit right.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test37_AddNewEventIntoASharedCalendarWhenSharedUserDoesNotHaveEditRight() {
		info("Test 37 Add new event into a shared calendar when shared user does not have edit right");
		/*Step Number: 1
		*Step Name: Step 1: Add and share calendar
		*Step Description: 
			- Add new calendar
			- Share added calendar for specific user without edit right
		*Input Data: 
			
		*Expected Outcome: 
			- This calendar appears in share list of shared user*/
		info("Create a new calendar");
		String[] userGroup={DATA_USER2};
		boolean[] canEdit={false};
		String calendar = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
        hp.goToCalendarPage();
		cHome.goToView(selectViewOption.WEEK);
        cMang.goToMenuFromMainCalendar(menuOfMainCalendar.ADDCAL);
        cMang.inputDataInDetailTabCalendarForm(calendar, calendar,null);
        cMang.saveAddCalendar();
		
        info("Share the calendar");
		cMang.shareCalendar(calendar, userGroup,canEdit);
		/*Step number: 2
		*Step Name: Step 2: Add event into shared calendar by shared user from right click menu
		*Step Description: 
			- Login by shared user
			- Click "settings" on shared calendar,
		*Input Data: 
			
		*Expected Outcome: 
			Only "remove" and "refresh" are allowed, "add event" is not allowed*/ 
		magAc.signOut();
        magAc.signIn(DATA_USER2, DATA_PASS);
        
        info("Only remove  and refresh are allowed, add event is not allowed");
		hp.goToCalendarPage();
		mouseOver(cMang.ELEMENT_CALENDAR_LIST_ITEM.replace("$calendar", calendar),true);
		click(cMang.ELEMENT_CALENDAR_SETTING_ICON.replace("$calendar", calendar),2);
		waitForAndGetElement(cMang.ELEMENT_CALENDAR_RIGHT_MENU);
		waitForAndGetElement(cMang.ELEMENT_CALENDAR_REMOVE_SHARE_CALENDAR);
		waitForAndGetElement(cMang.ELEMENT_CALENDAR_REFRESH_MENU);
		waitForAndGetElement(cMang.ELEMENT_CALENDAR_ADD_EVENT_MENU_NO_DISPLAY,2000,2);
		
 	}

	/**
	*<li> Case ID:116406.</li>
	*<li> Test Case Name: Add new event into a group calendar when user has edit right.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test38_AddNewEventIntoAGroupCalendarWhenUserHasEditRight() {
		info("Test 38 Add new event into a group calendar when user has edit right");
		/*Step Number: 1
		*Step Name: -
		*Step Description: 
			Step 1: Add calendar
		*Input Data: 
			Create new group calendar, assign some user in group has edit[ Details ]
		*Expected Outcome: 
			Added calendar appears in selected group's calendars list*/
		info("Create a new calendar");
		String userGroup="/platform/web-contributors";
		String calendar = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String[] user={DATA_USER2};
        hp.goToCalendarPage();
		cHome.goToView(selectViewOption.WEEK);
        cMang.goToMenuFromMainCalendar(menuOfMainCalendar.ADDCAL);
        cMang.inputDataInDetailTabCalendarForm(calendar, calendar,null);
        cMang.selectGroupInGroupTabCalendarForm(userGroup,true);
        cMang.selectUserEditPermissionInGroup(user,0);
        cMang.saveAddCalendar();
		
		/*Step number: 2
		*Step Name: -
		*Step Description: 
			Step 2: Add event
		*Input Data: 
			- Login by user who has edit right on added group calendar
			- Create new event on this calendar
		*Expected Outcome: 
			Add new event successfully with input values*/ 
		magAc.signOut();
        magAc.signIn(DATA_USER2, DATA_PASS);
        info("Create an event in shared calendar");
        String titleEvent = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToCalendarPage();
		evMg.goToAddEventFromActionBar();
		evMg.inputBasicQuickEvent(titleEvent,content,calendar);
		evMg.saveQuickAddEvent();
		info("Add successfully");
		cMang.scrollElementIntoView(this.driver.findElement(By.xpath(cMang.ELEMENT_EVENT_TASK_TITLE.replace("${name}",titleEvent))));
		waitForAndGetElement(cMang.ELEMENT_EVENT_TASK_TITLE.replace("${name}",titleEvent));
		
		info("Delete Event");
 		hp.goToCalendarPage();
 		cHome.goToView(selectViewOption.LIST);
 		cMang.deleteTaskEvent(titleEvent);
 	}

	/**
	*<li> Case ID:116407.</li>
	*<li> Test Case Name: Add new event into a group calendar when user does not have edit right.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*CANNOT AUTOMATE: AT STEP 2: INCORRECT WORK FOLOW. WHEN USER CLICK ON THE CALENDAR, ADD EVENT LINK IS NOT SHOWN.
	*USER CANNOUT CLICK ON THIS LINK TO VIEW ADD EVENT FORM
	*/
	@Test(groups="pendings")
	public  void test39_AddNewEventIntoAGroupCalendarWhenUserDoesNotHaveEditRight() {
		info("Test 39 Add new event into a group calendar when user does not have edit right");
		/*Step Number: 1
		*Step Name: -
		*Step Description: 
			Step 1: Add calendar
		*Input Data: 
			Create new group calendar, assign some user in group has edit[ Details ]
		*Expected Outcome: 
			Added calendar appears in selected group's calendars list*/
		info("Create a new calendar");
		String userGroup="/platform/web-contributors";
		String calendar = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
        hp.goToCalendarPage();
		cHome.goToView(selectViewOption.WEEK);
        cMang.goToMenuFromMainCalendar(menuOfMainCalendar.ADDCAL);
        cMang.inputDataInDetailTabCalendarForm(calendar, calendar,null);
        cMang.selectGroupInGroupTabCalendarForm(userGroup,true);
        cMang.saveAddCalendar();
        
		/*Step number: 2
		*Step Name: -
		*Step Description: 
			Step 2: Add event
		*Input Data: 
			- Login by user of above group but does not have edit right on added group calendar
			- Right click on that calendar and select Add Event
		*Expected Outcome: 
			Add event form is shown, but this group calendar is not displayed at calendar field . 
			And can't not add event for this calendar*/ 

 	}}
package org.exoplatform.selenium.platform.calendar.functional;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.calendar.CalendarHomePage.selectDayOption;
import org.exoplatform.selenium.platform.calendar.CalendarHomePage.selectViewOption;
import org.exoplatform.selenium.platform.calendar.CalendarManagement.menuOfMainCalendar;
import org.exoplatform.selenium.platform.calendar.EventManagement.selectAvailableOption;
import org.testng.annotations.*;


	public class CAL_Event_Add_Part1 extends CAL_TestConfig_2{
		String password;
		public void createNewUser(){
			info("Add new a user");
			int index = userInfoData.getRandomIndexByType(3);
			username = userInfoData.newUserName.get(index)+getRandomNumber();
			firstname = userInfoData.newFirstName.get(index);
			String lastname = userInfoData.newLastName.get(index);
			password = userInfoData.newPassword.get(index);
			String email = EMAIL_ADDRESS1;
			
			navTool.goToAddUser();
			addUserPage.addUser(username, password, email,firstname,lastname);
		}
	/**
	*<li> Case ID:116306.</li>
	*<li> Test Case Name: Check tooltip of schedule tab.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_CheckTooltipOfScheduleTab() {
		info("Test 1: Check tooltip of schedule tab");
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Log in to Calendar
			- Add a new event with name SCHEDULE_01 
			- Click More Details
			- Click Schedule tab
		*Input Data: 
			
		*Expected Outcome: 
			A small tooltip is displayed when the mouse is over the first table's row: 
			Participants: "Drag here to change your event's start and end time"*/ 
		info("Add a event");
		String eventText=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToCalendarPage();
		evMg.goToAddEventFromActionBar();
		evMg.inputBasicQuickEvent(eventText,eventText);
		evMg.moreDetailsEvent();
		evMg.goToScheduleTab();
		mouseOver(evMg.ELEMENT_SCHEDULE_TIME.replace("${index}","2"),true);
		waitForAndGetElement(evMg.ELEMENT_SCHEDULE_TOOLTIP_PARTICIPANTS.replace("${index}","2"));
		evMg.saveAddEventDetails();
		
	    info("Delete Data");
		hp.goToCalendarPage();
		cMang.deleteTaskEvent(eventText);
 	}

	/**
	*<li> Case ID:116307.</li>
	*<li> Test Case Name: Check busy period.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*CANNOT AUTOMATE: BECAUSE CANNOT CHECK RED COLOR OF THE TABLE
	*/
	@Test(groups="pending")
	public  void test02_CheckBusyPeriod() {
		info("Test 2: Check busy period");
		/*Step Number: 1
		*Step Name: Add an event
		*Step Description: 
			- Log in to Calendar as root
			- Add a new event with name SCHEDULE_021 and from 8:00 to 10:00
		*Input Data: 
			
		*Expected Outcome: 
			New event SCHEDULE_021 is created from 8:00 to 10:00*/
		String titleEvent = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		info("Add a event");
		hp.goToCalendarPage();		
		cMang.goToMenuFromMainCalendar(menuOfMainCalendar.CALSETTING);
		cMang.changeSettingCalendar(null,"(GMT +07:00) Asia/Ho_Chi_Minh",null,null,null,null,null);
		cMang.saveSetting();
		evMg.goToAddEventFromActionBar();
		evMg.inputDataEventInQuickForm(titleEvent, content, getDate(0,"MM/dd/yyyy HH")+":00", getDate(0,"MM/dd/yyyy HH")+":30",false);
		evMg.saveQuickAddEvent();
		cHome.verifyIsPresentEventTask(titleEvent, selectViewOption.LIST, selectDayOption.ONEDAY);
		/*Step number: 2
		*Step Name: Check busy time in schedule tab
		*Step Description: 
			- Add a new event with name SCHEDULE_022 on the same day
			- Click More Details
			- Click Schedule tab
		*Input Data: 
			
		*Expected Outcome: 
			- In first table's row Participant, 8:00 to 10:00 is displayed in red*/ 

 	}

	/**
	*<li> Case ID:116308.</li>
	*<li> Test Case Name: Check schedule new event by drag and drop.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*CANNOT AUTOMATE: BECAUSE CANNOT CHECK GREEN COLOR OF THE TABLE
	*/
	@Test(groups="pendings")
	public  void test03_CheckScheduleNewEventByDragAndDrop() {
		info("Test 3: Check schedule new event by drag and drop");
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Add a new event with name SCHEDULE_03
			- Click More Details
			- Click Schedule tab
			- Using mouse drag and drop from 9:00 to 12:00
		*Input Data: 
			
		*Expected Outcome: 
			Time from 9:00 to 12:00 of selected day on Participant row is displayed in green.*/ 

 	}

	/**
	*<li> Case ID:116309.</li>
	*<li> Test Case Name: Check click on the first table's row will reset previous schedule.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test04_CheckClickOnTheFirstTablesRowWillResetPreviousSchedule() {
		info("Test 4: Check click on the first table's row will reset previous schedule");
		/*Step Number: 1
		*Step Name: Create new event
		*Step Description: 
			- Add a new event with name SCHEDULE_04
			- Click More Details
			- Click Schedule tab
			- Choose time form 2:00 to 3:00
			- Save
		*Input Data: 
			
		*Expected Outcome: 
			New event is created*/
		String titleEvent = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		info("Add a event");
		hp.goToCalendarPage();		
		evMg.goToAddEventFromActionBar();
		evMg.inputDataEventInQuickForm(titleEvent, content, getDate(0,"MM/dd/yyyy")+" 02:00", getDate(0,"MM/dd/yyyy")+" 03:30",false);
		evMg.saveQuickAddEvent();
		cHome.verifyIsPresentEventTask(titleEvent, selectViewOption.LIST, selectDayOption.ONEDAY);
		
		/*Step number: 2
		*Step Name: Check click on participants row
		*Step Description: 
			- Edit event
			- Click Schedule tab
			- Click at location 9:00 on the participants row
		*Input Data: 
			
		*Expected Outcome: 
			- The Previous time is not reset, Back to Details tab, the Start time, 
			end time is updated following the tab Schedule*/
		info("Edit an recurring event");
		cMang.openEditEventTaskPopup(titleEvent);
		evMg.goToScheduleTab();
		evMg.changeTimeEventFromScheduleTab(selectArrowOption.NOW,"09:00","09:00");
		evMg.goToDetailsTab();
		waitForAndGetElement(evMg.ELEMENT_EVENT_INPUT_EVENT_TIME_COMBOBOX.replace("${time}","09:00"));
		waitForAndGetElement(evMg.ELEMENT_EVENT_INPUT_EVENT_TIME_COMBOBOX.replace("${time}","09:15"));
		evMg.saveAddEventDetails();

		info("Delete Data");
		cMang.deleteTaskEvent(titleEvent);
 	}

	/**
	*<li> Case ID:116310.</li>
	*<li> Test Case Name: Check update schedule of an event by drag&drop.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*CANNOT AUTOMATE: BECAUSE CANNOT CHECK GREEN COLOR OF THE TABLE
	*/
	@Test(groups="pending")
	public  void test05_CheckUpdateScheduleOfAnEventByDragDrop() {
		info("Test 5: Check update schedule of an event by drag&drop");
		/*Step Number: 1
		*Step Name: Add an event
		*Step Description: 
			- Log in calendar
			- Add a new event with name SCHEDULE_05 from 3:00 to 5:00
		*Input Data: 
			
		*Expected Outcome: 
			New event is created & displayed*/

		/*Step number: 2
		*Step Name: Update schedule
		*Step Description: 
			- Edit above event
			- Click Schedule tab
			- Drag and drop on the 1st row eg, from 2:00 to 3:00
		*Input Data: 
			
		*Expected Outcome: 
			Updated start/end time information 2:00 
			- 3:00 is displayed on the first row with green color*/ 

 	}

	/**
	*<li> Case ID:116311.</li>
	*<li> Test Case Name: Check schedule an event by selecting start/end time from input boxes at the top of the popup.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*CANNOT AUTOMATE: BECAUSE CANNOT CHECK GREEN COLOR OF THE TABLE
	*/
	@Test(groups="pending")
	public  void test06_CheckScheduleAnEventBySelectingStartendTimeFromInputBoxesAtTheTopOfThePopup() {
		info("Test 6: Check schedule an event by selecting start/end time from input boxes at the top of the popup");
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Log in calendar
			- Add a new event named SCHEDULE_06
			- Click Schedule tab
			- Select start/end time from input boxes at the top of the popup, eg: from 9:30, to 11:30
		*Input Data: 
			
		*Expected Outcome: 
			On the 1st row, period from 9:30 to 11:30 will be in green*/ 

 	}

	/**
	*<li> Case ID:116312.</li>
	*<li> Test Case Name: Check not displaying user availability status corresponding to the current event..</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test07_CheckNotDisplayingUserAvailabilityStatusCorrespondingToTheCurrentEvent() {
		info("Test 7: Check not displaying user availability status corresponding to the current event.");
		/*Step Number: 1
		*Step Name: Create an event
		*Step Description: 
			- Log in calendar as root
			- Add a new eventwith name SCHEDULE_071 at current time, eg current time is 10:00, so select time is 10:00 
			- 12:00
		*Input Data: 
			
		*Expected Outcome: 
			Event is created from 10:00 to 12:00*/
		magAc.signOut();
		magAc.signIn(USER_ROOT, PASS_ROOT);
		String titleEvent = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		info("Add a event");
		hp.goToCalendarPage();		
		evMg.goToAddEventFromActionBar();
		evMg.inputDataEventInQuickForm(titleEvent, content, getDate(0,"MM/dd/yyyy")+" 10:00", getDate(0,"MM/dd/yyyy")+" 12:00",false);
		evMg.saveQuickAddEvent();
		cHome.verifyIsPresentEventTask(titleEvent, selectViewOption.LIST, selectDayOption.ONEDAY);
		
		/*Step number: 2
		*Step Name: Check user availability of current event
		*Step Description: 
			- Log in as john
			- Create a new event with SCHEDULE_072
			- Click Schedule tab
			- Choose time 10:00 to 12:00
			- Click Add participants
			- Choose root
		*Input Data: 
			
		*Expected Outcome: 
			- The first line "participants" shows the current event
			- In the lines of root, we don't display the status (available/busy) of the current event*/ 

		magAc.signOut();
		magAc.signIn(DATA_USER1,DATA_PASS);
		String titleEvent1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		info("Add a event");
		hp.goToCalendarPage();		
		evMg.goToAddEventFromActionBar();
		evMg.inputDataEventInQuickForm(titleEvent1, content1,"","",false);
		evMg.moreDetailsEvent();
		evMg.goToScheduleTab();
		evMg.changeTimeEventFromScheduleTab(selectArrowOption.NOW,"10:00","12:00");
		evMg.addParticipants(USER_ROOT,1);
		evMg.checkBusyTimeOfUser(USER_ROOT, "10:00","12:00");
		evMg.saveAddEventDetails();
		
		info("Delete Data");
		hp.goToCalendarPage();	
		cMang.deleteTaskEvent(titleEvent1);
 	}

	/**
	*<li> Case ID:116351.</li>
	*<li> Test Case Name: Check attachment's explorer.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test08_CheckAttachmentsExplorer() {
		info("Test 8: Check attachment's explorer");
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Log in to Calendar
			- Add a new event with name ATTACH_FILE_01_5
			- Click More Details
			- Click Add Attachment
			- Click Select File
		*Input Data: 
			
		*Expected Outcome: 
			A native explorer is opened for user to select a file to upload*/ 
		String titleEvent = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String link = fData.getAttachFileByArrayTypeRandom(2);
		info("Add a event");
		hp.goToCalendarPage();		
		evMg.goToAddEventFromActionBar();
		evMg.inputDataEventInQuickForm(titleEvent, content,"","",false);
		evMg.moreDetailsEvent();
		info("Add attachment");
		evMg.attachFileToEvent(link);
		evMg.saveAddEventDetails();
		
		info("Delete Data");
		hp.goToCalendarPage();	
		cMang.deleteTaskEvent(titleEvent);

 	}

	/**
	*<li> Case ID:116352.</li>
	*<li> Test Case Name: Check progress bar during attachment time.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*CANNOT AUTOMATE: BECAUSE THE PROGRESS BAR IS DISAPPEARED TOO FAST. CANNOT VERIFY % UPLOADING
	*/
	@Test(groups="pending")
	public  void test09_CheckProgressBarDuringAttachmentTime() {
		info("Test 9: Check progress bar during attachment time");
		/*Step Number: 1
		*Step Name: Attache small file to event
		*Step Description: 
			- Log in to Calendar
			- Add a new event with name ATTACH_FILE_02
			- Click More Details
			- Click Add Attachment
			- Choose a file to upload with some kb
		*Input Data: 
			
		*Expected Outcome: 
			- File is selected
			- During the upload, a progress bar is displayed, it shows the percentage of the file already uploaded
			- File size is small (Kb), progress bar will be displayed & disappear very fast*/

		/*Step number: 2
		*Step Name: Attach larger file to event
		*Step Description: 
			- Click attach more file
			- Choose a larger file from 1
			-10MB
		*Input Data: 
			
		*Expected Outcome: 
			Progress bar will be displayed longer to show eg 50% and 2M of 4M*/ 

 	}

	/**
	*<li> Case ID:116353.</li>
	*<li> Test Case Name: Check deletion of an attachment after uploading.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test10_CheckDeletionOfAnAttachmentAfterUploading() {
		info("Test 10 Check deletion of an attachment after uploading");
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Log in to Calendar
			- Add a new event with name ATTACH_FILE_03
			- Click More Details
			- Click Add Attachment
			- Choose a file to upload
			- After uploading, click Delete icon of attachment
		*Input Data: 
			
		*Expected Outcome: 
			- File is uploaded successfully
			- Attachment is removed*/ 
		String titleEvent = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String link = fData.getAttachFileByArrayTypeRandom(2);
		info("Add a event");
		hp.goToCalendarPage();		
		evMg.goToAddEventFromActionBar();
		evMg.inputDataEventInQuickForm(titleEvent, content,"","",false);
		evMg.moreDetailsEvent();
		info("Add attachment");
		evMg.attachFileToEvent(link);
		evMg.removeAttachment(link );
		evMg.saveAddEventDetails();
		
		info("Delete Data");
		cMang.deleteTaskEvent(titleEvent);
 	}

	/**
	*<li> Case ID:116354.</li>
	*<li> Test Case Name: Check uploading more files.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test11_CheckUploadingMoreFiles() {
		info("Test 11 Check uploading more files");
		/*Step Number: 1
		*Step Name: Attach 1 file
		*Step Description: 
			- Log in to Calendar
			- Add a new event with name ATTACH_FILE_04
			- Click More Details
			- Click Add Attachment
			- Choose a file to upload
		*Input Data: 
			
		*Expected Outcome: 
			- One attachment is listed in the popup*/
		
		/*Step number: 2
		*Step Name: Attache more files
		*Step Description: 
			- Click Select more files
			- Select another file
		*Input Data: 
			
		*Expected Outcome: 
			- Another native explorer is open to choose file to upload
			- One more attachment is listed in the popup*/ 
		String titleEvent = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String link = fData.getAttachFileByArrayTypeRandom(1);
		String link1 = fData.getAttachFileByArrayTypeRandom(2);
		String link2 = fData.getAttachFileByArrayTypeRandom(3);
		info("Add a event");
		hp.goToCalendarPage();		
		evMg.goToAddEventFromActionBar();
		evMg.inputDataEventInQuickForm(titleEvent, content,"","",false);
		evMg.moreDetailsEvent();
		info("Add attachment");
		evMg.attachFileToEvent(link);
		evMg.attachFileToEvent(link1);
		evMg.attachFileToEvent(link2);
		
 	}

	/**
	*<li> Case ID:116355.</li>
	*<li> Test Case Name: Check limit of 10 attachments.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test12_CheckLimitOf10Attachments() {
		info("Test 12 Check limit of 10 attachments");
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Log in to Calendar
			- Add a new event with name ATTACH_FILE_05
			- Click More Details
			- Click Add Attachment
			- Upload 1 file successfully
			- Click Attach more file 
			- Upload files until there are 10 attachments
		*Input Data: 
			
		*Expected Outcome: 
			A limit of 10 files can be attached to an event.*/ 
		String titleEvent = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String link = fData.getAttachFileByArrayTypeRandom(1);
		String link1 = fData.getAttachFileByArrayTypeRandom(2);
		String link2 = fData.getAttachFileByArrayTypeRandom(3);
		String link3 = fData.getAttachFileByArrayTypeRandom(4);
		String link4 = fData.getAttachFileByArrayTypeRandom(5);
		String link5 = fData.getAttachFileByArrayTypeRandom(6);
		String link6 = fData.getAttachFileByArrayTypeRandom(7);
		String link7 = fData.getAttachFileByArrayTypeRandom(8);
		String link8 = fData.getAttachFileByArrayTypeRandom(9);
		String link9 = fData.getAttachFileByArrayTypeRandom(10);
		info("Add a event");
		hp.goToCalendarPage();		
		evMg.goToAddEventFromActionBar();
		evMg.inputDataEventInQuickForm(titleEvent, content,"","",false);
		evMg.moreDetailsEvent();
		info("Add attachment");
		evMg.attachFileToEvent(link);
		evMg.attachFileToEvent(link1);
		evMg.attachFileToEvent(link2);
		evMg.attachFileToEvent(link3);
		evMg.attachFileToEvent(link4);
		evMg.attachFileToEvent(link5);
		evMg.attachFileToEvent(link6);
		evMg.attachFileToEvent(link7);
		evMg.attachFileToEvent(link8);
		evMg.attachFileToEvent(link9);
 	}

	/**
	*<li> Case ID:116356.</li>
	*<li> Test Case Name: Check attachment behavior of an event after uploading 10 attachments.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test13_CheckAttachmentBehaviorOfAnEventAfterUploading10Attachments() {
		info("Test 13 Check attachment behavior of an event after uploading 10 attachments");
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Log in to Calendar
			- Add a new event with name ATTACH_FILE_06
			- Click More Details
			- Click Add Attachment
			- Upload 10 files
		*Input Data: 
			
		*Expected Outcome: 
			- 10 files are uploaded successfully
			- No message is displayed but the select component is not displayed anymore.*/ 
		String titleEvent = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String link = fData.getAttachFileByArrayTypeRandom(1);
		String link1 = fData.getAttachFileByArrayTypeRandom(2);
		String link2 = fData.getAttachFileByArrayTypeRandom(3);
		String link3 = fData.getAttachFileByArrayTypeRandom(4);
		String link4 = fData.getAttachFileByArrayTypeRandom(5);
		String link5 = fData.getAttachFileByArrayTypeRandom(6);
		String link6 = fData.getAttachFileByArrayTypeRandom(7);
		String link7 = fData.getAttachFileByArrayTypeRandom(8);
		String link8 = fData.getAttachFileByArrayTypeRandom(9);
		String link9 = fData.getAttachFileByArrayTypeRandom(10);
		info("Add a event");
		hp.goToCalendarPage();		
		evMg.goToAddEventFromActionBar();
		evMg.inputDataEventInQuickForm(titleEvent, content,"","",false);
		evMg.moreDetailsEvent();
		info("Add attachment");
		evMg.attachFileToEvent(link);
		evMg.attachFileToEvent(link1);
		evMg.attachFileToEvent(link2);
		evMg.attachFileToEvent(link3);
		evMg.attachFileToEvent(link4);
		evMg.attachFileToEvent(link5);
		evMg.attachFileToEvent(link6);
		evMg.attachFileToEvent(link7);
		evMg.attachFileToEvent(link8);
		evMg.attachFileToEvent(link9);
		
        evMg.saveAddEventDetails();
        cHome.goToView(selectViewOption.LIST);
        waitForAndGetElement(evMg.ELEMENT_ATTACH_FILE_NAME.replace("$fileName",link));
        waitForAndGetElement(evMg.ELEMENT_ATTACH_FILE_NAME.replace("$fileName",link1));
        waitForAndGetElement(evMg.ELEMENT_ATTACH_FILE_NAME.replace("$fileName",link2));
        waitForAndGetElement(evMg.ELEMENT_ATTACH_FILE_NAME.replace("$fileName",link3));
        waitForAndGetElement(evMg.ELEMENT_ATTACH_FILE_NAME.replace("$fileName",link4));
        waitForAndGetElement(evMg.ELEMENT_ATTACH_FILE_NAME.replace("$fileName",link5));
        waitForAndGetElement(evMg.ELEMENT_ATTACH_FILE_NAME.replace("$fileName",link6));
        waitForAndGetElement(evMg.ELEMENT_ATTACH_FILE_NAME.replace("$fileName",link7));
        waitForAndGetElement(evMg.ELEMENT_ATTACH_FILE_NAME.replace("$fileName",link8));
        waitForAndGetElement(evMg.ELEMENT_ATTACH_FILE_NAME.replace("$fileName",link9));
		
		info("Delete Data");
		cHome.goToView(selectViewOption.WEEK);
		cMang.deleteTaskEvent(titleEvent);
		
 	}

	/**
	*<li> Case ID:116357.</li>
	*<li> Test Case Name: Check displaying of Add attachment button.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test14_CheckDisplayingOfAddAttachmentButton() {
		info("Test 14 Check displaying of Add attachment button");
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Log in to Calendar
			- Add a new event with name ATTACH_FILE_01_1
			- Click More Details
			-See the last row of Details tab
		*Input Data: 
			
		*Expected Outcome: 
			The last row of "Details" tab is the label "Files:" and the button "Add Attachment"*/ 
		info("Add a event");
		hp.goToCalendarPage();		
		evMg.goToAddEventFromActionBar();
		evMg.moreDetailsEvent();
        waitForAndGetElement(evMg.ELEMENT_EVENT_ADD_ATTACHMENT);
        waitForAndGetElement(evMg.ELEMENT_ATTACH_LABEL_FIELD);
 	}

	/**
	*<li> Case ID:116358.</li>
	*<li> Test Case Name: Check title of Add attachment popup.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test15_CheckTitleOfAddAttachmentPopup() {
		info("Test 15 Check title of Add attachment popup");
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Log in to Calendar
			- Add a new event with name ATTACH_FILE_01_3
			- Click More Details
			- Click "Add Attachment"
		*Input Data: 
			
		*Expected Outcome: 
			A popup is opened with title "Attach Files"*/ 
		info("Add a event");
		hp.goToCalendarPage();		
		evMg.goToAddEventFromActionBar();
		evMg.moreDetailsEvent();
		click(evMg.ELEMENT_EVENT_ADD_ATTACHMENT);
		Utils.pause(2000);
        waitForAndGetElement(evMg.ELEMENT_ATTACH_FORM);
 	}

	/**
	*<li> Case ID:116359.</li>
	*<li> Test Case Name: Check Add attachment popup without any attachment.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test16_CheckAddAttachmentPopupWithoutAnyAttachment() {
		info("Test 16 Check Add attachment popup without any attachment");
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Log in to Calendar
			- Add a new event with name ATTACH_FILE_01_4
			- Click More Details
			- Click "Add Attachment"
			- Do not attach any file
			- See popup
		*Input Data: 
			
		*Expected Outcome: 
			"Attach Files" popup is displayed as
			- a "Select File" button
			- the label "No file selected"*/ 
		info("Add a event");
		hp.goToCalendarPage();		
		evMg.goToAddEventFromActionBar();
		evMg.moreDetailsEvent();
		click(evMg.ELEMENT_EVENT_ADD_ATTACHMENT);
		Utils.pause(2000);
        waitForAndGetElement(evMg.ELEMENT_ATTACH_FORM);
        waitForAndGetElement(evMg.ELEMENT_ATTACHMENT_FORM_SELECT_FILE);
        waitForAndGetElement(evMg.ELEMENT_ATTACHMENT_FORM_NO_FILE);

 	}

	/**
	*<li> Case ID:116369.</li>
	*<li> Test Case Name: Check Send invitation mail when use calendar setting to be Ask  sending invitation but  confirm "No".</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test17_CheckSendInvitationMailWhenUseCalendarSettingToBeAskSendingInvitationButConfirmNo() {
		info("Test 17 Check Send invitation mail when use calendar setting to be Ask  sending invitation but  confirm No");
		
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
		createNewUser();
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

		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String users = username;
		String titleEvent=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content1=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		info("Add a event");
		hp.goToCalendarPage();		
		evMg.goToAddEventFromActionBar();
		evMg.moreDetailsEvent();
		evMg.inputBasicDetailEvent(titleEvent, content1);
		evMg.goToParticipantsTab();
		evMg.selectPrivacyParticipant(false);
		evMg.selectAvailable(selectAvailableOption.AVAILABLE);
		evMg.goToInvitationParticipantForm();
		evMg.selectUserParticipants(users, content,0);
		evMg.saveInvitationParticipantForm();
		waitForAndGetElement(evMg.ELEMENT_PARTICIPANT_SEND_INVITATION_OPTION_CHECKED.replace("$opion","ask"),2000,2);
		evMg.saveAddEventDetails();
		waitForAndGetElement(evMg.ELEMENT_CONFIRM_SEND_INVITATION_MESSAGE);
		waitForAndGetElement(evMg.ELMEMENT_CONFIRM_SEND_INVITATION_YES_BTN);
		waitForAndGetElement(evMg. ELMEMENT_CONFIRM_SEND_INVITATION_NO_BTN);
		
		/*Step number: 5
		*Step Name: Step 5: Don't Agree send invitation mail
		*Step Description: 
			-Click [No]
		*Input Data: 
			
		*Expected Outcome: 
			- New event is created 
			- And NoInvitation mailis sent to email address of selected participant(s) and selected email(s).*/ 
		click(evMg. ELMEMENT_CONFIRM_SEND_INVITATION_NO_BTN);
		cHome.goToView(selectViewOption.WEEK);
		waitForAndGetElement(cMang.ELEMENT_EVENT_TASK_TITLE.replace("${name}",titleEvent));
		
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(20000);
		cMang.checkEmailNotificationCalendar(titleEvent,"","","",false,false);
        switchToParentWindow();
        
		info("restore data");
		hp.goToCalendarPage();
		cMang.deleteTaskEvent(titleEvent);
		navTool.goToUsersAndGroupsManagement();
		userAndGroup.deleteUser(username);
 	}

	/**
	*<li> Case ID:116370.</li>
	*<li> Test Case Name: Check Send invitation mail when Choose Never send invitation mail while creating event.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test18_CheckSendInvitationMailWhenChooseNeverSendInvitationMailWhileCreatingEvent() {
		info("Test 18 Check Send invitation mail when Choose Never send invitation mail while creating event");
		/*Step Number: 1
		*Step Name: Step 1: Open Add/Edit Event form
		*Step Description: 
			- Click [Event_button] on the action bar.
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
			The Participant(s) ,email(s) are selected*/

		/*Step number: 3
		*Step Name: Step 3:Confirm Not send invitation mail
		*Step Description: 
			- Ensure the Never radio is selected.
			- Go to Detail tab, input name for the event 
			- Click [Save]
		*Input Data: 
			
		*Expected Outcome: 
			- No message for sending invitation email is shown.
			- The event is created successfully.
			- The participants do not receive the invitation emails.*/ 
		createNewUser();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String titleEvent=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content1=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		info("Add a event");
		hp.goToCalendarPage();		
		evMg.goToAddEventFromActionBar();
		evMg.moreDetailsEvent();
		evMg.inputBasicDetailEvent(titleEvent, content1);
		evMg.goToParticipantsTab();
		evMg.goToInvitationParticipantForm();
		evMg.selectUserParticipants(DATA_USER2, content,0);
		evMg.saveInvitationParticipantForm();
		evMg.selectSendInvitation(selectInvitationOption.NEVER);
		evMg.saveAddEventDetails();
		waitForElementNotPresent(evMg.ELEMENT_CONFIRM_SEND_INVITATION_MESSAGE);
		cHome.goToView(selectViewOption.WEEK);
		waitForAndGetElement(cMang.ELEMENT_EVENT_TASK_TITLE.replace("${name}",titleEvent));
		
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(20000);
		cMang.checkEmailNotificationCalendar(titleEvent,"","","",false,false);
        switchToParentWindow();
        
		info("restore data");
		hp.goToCalendarPage();
		cMang.deleteTaskEvent(titleEvent);
		navTool.goToUsersAndGroupsManagement();
		userAndGroup.deleteUser(username);
		
 	}

	/**
	*<li> Case ID:116371.</li>
	*<li> Test Case Name: Check Send invitation mail when Choose Always send invitation mail while creating event.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test19_CheckSendInvitationMailWhenChooseAlwaysSendInvitationMailWhileCreatingEvent() {
		info("Test 19 Check Send invitation mail when Choose Always send invitation mail while creating event");
	
		/*Step Number: 1
		*Step Name: -
		*Step Description: 
			Step 1: Create an account mail in Webmail portlet
		*Input Data: 
			Create an account mail in Webmail portlet:e.g : abc@gmail.com
		*Expected Outcome: 
			The account is created successfully*/

		/*Step number: 2
		*Step Name: -
		*Step Description: 
			Step 2: Open Add/edit event form
		*Input Data: 
			Click Add event on theaction bar
		*Expected Outcome: 
			The form is shown properly*/

		/*Step number: 3
		*Step Name: -
		*Step Description: 
			Step 2: Add participant(s) or email(s) to send invitation mail
		*Input Data: 
			- Go to Participants taband choose participant(s) or email(s)[ Details ]
			- ClickNever radio
			- Go to Detail tab, input name for the event
			- Click Save
		*Expected Outcome: 
			The new event is created but No invitation mail is sent to selected participant(s) or selected email*/

		/*Step number: 4
		*Step Name: -
		*Step Description: 
			Step 3: Add participant(s) or email(s) to send invitation mail
		*Input Data: 
			- Go to Participants taband choose participant(s) or email(s)[ Details ]
		*Expected Outcome: 
			The Participant(s) ,email(s) are selected*/

		/*Step number: 5
		*Step Name: -
		*Step Description: 
			Step 4: Finish adding event
		*Input Data: 
			- Click Always radio
			- Go to Detail tab, input name for the event 
			- Click Save
		*Expected Outcome: 
			- New event is created 
			- And an Invitation mail from abc@gmail.com will be sent to email address of selected participant(s) and selected email(s).
			- An .ics file will be attached with the invitation mail*/ 
		createNewUser();
		String titleEvent=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content1=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		info("Add a event");
		hp.goToCalendarPage();		
		evMg.goToAddEventFromActionBar();
		evMg.moreDetailsEvent();
		evMg.inputBasicDetailEvent(titleEvent, content1);
		evMg.goToParticipantsTab();
		evMg.goToInvitationParticipantForm();
		evMg.selectUserParticipants(username, content1,0);
		evMg.saveInvitationParticipantForm();
		evMg.selectSendInvitation(selectInvitationOption.ALWAYS);
		evMg.saveAddEventDetails();
		waitForElementNotPresent(evMg.ELEMENT_CONFIRM_SEND_INVITATION_MESSAGE);
		cHome.goToView(selectViewOption.WEEK);
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

 	}}
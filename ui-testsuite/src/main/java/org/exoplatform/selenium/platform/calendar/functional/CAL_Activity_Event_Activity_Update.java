package org.exoplatform.selenium.platform.calendar.functional;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.calendar.CalendarHomePage.selectDayOption;
import org.exoplatform.selenium.platform.calendar.CalendarHomePage.selectViewOption;
import org.exoplatform.selenium.platform.calendar.EventManagement.priorityType;
import org.exoplatform.selenium.platform.calendar.EventManagement.recurringType;
import org.exoplatform.selenium.platform.calendar.EventManagement.repeatEndType;
import org.exoplatform.selenium.platform.calendar.EventManagement.repeatType;
import org.exoplatform.selenium.platform.calendar.EventManagement.selectAvailableOption;
import org.openqa.selenium.By;
import org.testng.annotations.*;


	public class CAL_Activity_Event_Activity_Update extends CAL_TestConfig{

	/**
	*<li> Case ID:116240.</li>
	*<li> Test Case Name: Update event's activity after delete an instance of a repeated event.</li>
	*<li> Pre-Condition: - Create a space with calendar app</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_UpdateEventsActivityAfterDeleteAnInstanceOfARepeatedEvent() {
		info("Test 1: Update event's activity after delete an instance of a repeated event");
		/*Step Number: 1
		*Step Name: Create a repeated event
		*Step Description: 
			- Connect to Intranet
			- Open calendar application
			- Click [Event] button in action bar
			- Input into fields
			- Click [More Details]
			- Click on [Repeat], input into fields
			- Select a space's calendar for [Calendar] field
			- Click [Save]
		*Input Data: 
			
		*Expected Outcome: 
			- A repeated event is created*/
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String contentSpace=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String firstDay=getFirstDayOfWeek("MM/dd/yyyy");
		String numberRepeat="5";
		info("create new space");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,contentSpace,60000);

		info("Create a recurring event");
		String newEvent= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToSpecificSpace(space);
		spaMg.goToAgendaTab();
		evMg.goToAddEventFromActionBar();
		evMg.moreDetailsEvent();
		evMg.inputBasicDetailEvent(newEvent,newEvent);
		evMg.inputFromToDetailEvent(firstDay, firstDay,false);
		evMg.openRecurringForm();
		evMg.inputRecurringInfoEvent(repeatType.Daily,"1",null,repeatEndType.After,numberRepeat);
		evMg.saveRecurringForm();
		evMg.saveAddEventDetails();
		
		info("A repeat event is created successfully");
		cHome.verifyIsPresentEventTaskWithDateTime(newEvent,"Mon", selectViewOption.WEEK, selectDayOption.ONEDAY);
		cHome.verifyIsPresentEventTaskWithDateTime(newEvent,"Tue", selectViewOption.WEEK, selectDayOption.ONEDAY);
		cHome.verifyIsPresentEventTaskWithDateTime(newEvent,"Wed", selectViewOption.WEEK, selectDayOption.ONEDAY);
		cHome.verifyIsPresentEventTaskWithDateTime(newEvent,"Thu", selectViewOption.WEEK, selectDayOption.ONEDAY);
		cHome.verifyIsPresentEventTaskWithDateTime(newEvent,"Fri", selectViewOption.WEEK, selectDayOption.ONEDAY);
		
		/*Step number: 2
		*Step Name: Delete an instance of a repeated event
		*Step Description: 
			- Right click on an instance of a repeated event of a space calendar, select [Delete]
			- Click on [Only this instance]
		*Input Data: 
			
		*Expected Outcome: 
			- A comment is added to the event's activity:"Event cancelled for $value"$value, is the date of the event removed*/ 

		hp.goToSpecificSpace(space);
		spaMg.goToAgendaTab();
		evMg.deleteRecurringEvent(newEvent, selectViewOption.WEEK, selectDayOption.ONEDAY,recurringType.ONLY_EVENT,"Wed");
		cHome.verifyIsNotPresentEventTaskWithDateTime(newEvent,"Wed", selectViewOption.WEEK, selectDayOption.ONEDAY);
 
		String comment = cCommentData.getContentByIndex(8);
		String dateText="Wednesday";
		hp.goToSpecificSpace(space);
		spaMg.goToActivityStreamTab();
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_TASK_EVENT_COMMENT
				.replace("$name", newEvent).replace("$comment", comment)
				.replace("${date}",dateText));
		
		hp.goToHomePage();
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_TASK_EVENT_COMMENT
				.replace("$name", newEvent).replace("$comment", comment)
				.replace("${date}", dateText));
		info("Delete Data");
		hp.goToSpecificSpace(space);
		spaMg.goToAgendaTab();
		evMg.deleteRecurringEvent(newEvent, selectViewOption.WEEK,selectDayOption.ONEDAY,recurringType.ALL_EVENT,"Mon");
	
	}

	/**
	*<li> Case ID:116241.</li>
	*<li> Test Case Name: Update event's activity after edit all instances of a repeated event with exception events created.</li>
	*<li> Pre-Condition: - Create a space with calendar app</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_UpdateEventsActivityAfterEditAllInstancesOfARepeatedEventWithExceptionEventsCreated() {
		info("Test 2: Update event's activity after edit all instances of a repeated event with exception events created");
		/*Step Number: 1
		*Step Name: Create a repeat event
		*Step Description: 
			- Connect to Intranet
			- Open calendar application
			- Click [Event] button in action bar
			- Input into fields
			- Click [More Details]
			- Click on [Repeat], input into fields
			- Select a space's calendar for [Calendar] field
			- Click [Save]
		*Input Data: 
			
		*Expected Outcome: 
			Arepeat event is created*/
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String contentSpace=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String firstDay=getFirstDayOfWeek("MM/dd/yyyy");
		String numberRepeat="5";
		info("create new space");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,contentSpace,60000);

		info("Create a recurring event");
		String newEvent= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToSpecificSpace(space);
		spaMg.goToAgendaTab();
		evMg.goToAddEventFromActionBar();
		evMg.moreDetailsEvent();
		evMg.inputBasicDetailEvent(newEvent,newEvent);
		evMg.inputFromToDetailEvent(firstDay, firstDay,false);
		evMg.openRecurringForm();
		evMg.inputRecurringInfoEvent(repeatType.Daily,"1",null,repeatEndType.After,numberRepeat);
		evMg.saveRecurringForm();
		evMg.saveAddEventDetails();
		
		info("A repeat event is created successfully");
		cHome.verifyIsPresentEventTaskWithDateTime(newEvent,"Mon", selectViewOption.WEEK, selectDayOption.ONEDAY);
		cHome.verifyIsPresentEventTaskWithDateTime(newEvent,"Tue", selectViewOption.WEEK, selectDayOption.ONEDAY);
		cHome.verifyIsPresentEventTaskWithDateTime(newEvent,"Wed", selectViewOption.WEEK, selectDayOption.ONEDAY);
		cHome.verifyIsPresentEventTaskWithDateTime(newEvent,"Thu", selectViewOption.WEEK, selectDayOption.ONEDAY);
		cHome.verifyIsPresentEventTaskWithDateTime(newEvent,"Fri", selectViewOption.WEEK, selectDayOption.ONEDAY);
		
		/*Step number: 2
		*Step Name: Create an exception event
		*Step Description: 
			- Double click on an instance of a repeated event of space calendar
			- Input value into fields
			- Click [Save], then select [Only this instance]
		*Input Data: 
			
		*Expected Outcome: 
			- That instance become an exception event
			- An activity for this exception event is created*/
		info("Edit an recurring event");
		String newEvent2= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToSpecificSpace(space);
		spaMg.goToAgendaTab();
		cMang.openEditEventTaskPopup(newEvent,"3");//edit 3th recurring event
		evMg.inputBasicDetailEvent(newEvent2,null);
		evMg.saveAddEventDetails();
		evMg.editRecurringEvent(recurringType.ONLY_EVENT);
		
		info("A repeat event is created successfully");
		cHome.verifyIsPresentEventTaskWithDateTime(newEvent,"Mon", selectViewOption.WEEK, selectDayOption.ONEDAY);
		cHome.verifyIsPresentEventTaskWithDateTime(newEvent,"Tue", selectViewOption.WEEK, selectDayOption.ONEDAY);
		cHome.verifyIsPresentEventTaskWithDateTime(newEvent2,"Wed", selectViewOption.WEEK, selectDayOption.ONEDAY);
		cHome.verifyIsPresentEventTaskWithDateTime(newEvent,"Thu", selectViewOption.WEEK, selectDayOption.ONEDAY);
		cHome.verifyIsPresentEventTaskWithDateTime(newEvent,"Fri", selectViewOption.WEEK, selectDayOption.ONEDAY);
		
		cHome.verifyIsNotPresentEventTaskWithDateTime(newEvent2,"Mon", selectViewOption.WEEK, selectDayOption.ONEDAY);
		cHome.verifyIsNotPresentEventTaskWithDateTime(newEvent2,"Tue", selectViewOption.WEEK, selectDayOption.ONEDAY);
		cHome.verifyIsNotPresentEventTaskWithDateTime(newEvent,"Wed", selectViewOption.WEEK, selectDayOption.ONEDAY);
		cHome.verifyIsNotPresentEventTaskWithDateTime(newEvent2,"Thu", selectViewOption.WEEK, selectDayOption.ONEDAY);
		cHome.verifyIsNotPresentEventTaskWithDateTime(newEvent2,"Fri", selectViewOption.WEEK, selectDayOption.ONEDAY);
		
	    spaMg.goToActivityStreamTab();
        waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_TASK_EVENT_TITLE.replace("$name", newEvent2));
		
		hp.goToHomePage();
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_TASK_EVENT_TITLE.replace("$name", newEvent2));
		
		/*Step number: 3
		*Step Name: Edit all instance of a repeated event
		*Step Description: 
			- Double click on another instance of that repeated event
			- Input value into fields
			- Click [Save], then select [All Events in the series]
		*Input Data: 
			
		*Expected Outcome: 
			- Comments are added to the initial activity.
			- There is no impact for activity created for exception event*/ 
		info("Edit an recurring event");
		String newEvent3= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToSpecificSpace(space);
		spaMg.goToAgendaTab();
		cMang.openEditEventTaskPopup(newEvent,"1");//edit 3th recurring event
		evMg.inputBasicDetailEvent(newEvent3,null);
		evMg.saveAddEventDetails();
		evMg.editRecurringEvent(recurringType.ALL_EVENT);
		
		info("A repeat event is created successfully");
		cHome.verifyIsPresentEventTaskWithDateTime(newEvent3,"Mon", selectViewOption.WEEK, selectDayOption.ONEDAY);
		cHome.verifyIsPresentEventTaskWithDateTime(newEvent3,"Tue", selectViewOption.WEEK, selectDayOption.ONEDAY);
		cHome.verifyIsPresentEventTaskWithDateTime(newEvent2,"Wed", selectViewOption.WEEK, selectDayOption.ONEDAY);
		cHome.verifyIsPresentEventTaskWithDateTime(newEvent3,"Thu", selectViewOption.WEEK, selectDayOption.ONEDAY);
		cHome.verifyIsPresentEventTaskWithDateTime(newEvent3,"Fri", selectViewOption.WEEK, selectDayOption.ONEDAY);
		
		cHome.verifyIsNotPresentEventTaskWithDateTime(newEvent,"Mon", selectViewOption.WEEK, selectDayOption.ONEDAY);
		cHome.verifyIsNotPresentEventTaskWithDateTime(newEvent,"Tue", selectViewOption.WEEK, selectDayOption.ONEDAY);
		cHome.verifyIsNotPresentEventTaskWithDateTime(newEvent,"Thu", selectViewOption.WEEK, selectDayOption.ONEDAY);
		cHome.verifyIsNotPresentEventTaskWithDateTime(newEvent,"Fri", selectViewOption.WEEK, selectDayOption.ONEDAY);
		
 
		String comment = cCommentData.getContentByIndex(6);
		hp.goToSpecificSpace(space);
		spaMg.goToActivityStreamTab();
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_TASK_EVENT_COMMENT
				.replace("$name", newEvent3).replace("$comment", comment).replace("${editText}",newEvent3));
		
		hp.goToHomePage();
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_TASK_EVENT_COMMENT
				.replace("$name", newEvent3).replace("$comment", comment).replace("${editText}",newEvent3));
		
		info("Delete Data");
		hp.goToSpecificSpace(space);
		spaMg.goToAgendaTab();
		evMg.deleteRecurringEvent(newEvent3, selectViewOption.WEEK,selectDayOption.ONEDAY,recurringType.ALL_EVENT,"Mon");
	    cMang.deleteTaskEvent(newEvent2);
 	}

	/**
	*<li> Case ID:116242.</li>
	*<li> Test Case Name: Update event's activity after edit all instances of a repeated event without exception events created.</li>
	*<li> Pre-Condition: - Create a space with calendar app
	- Create a repeated event in a space calendar</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test03_UpdateEventsActivityAfterEditAllInstancesOfARepeatedEventWithoutExceptionEventsCreated() {
		info("Test 3: Update event's activity after edit all instances of a repeated event without exception events created");
		/*Step Number: 1
		*Step Name: Create a repeat event
		*Step Description: 
			- Connect to Intranet
			- Open calendar application
			- Click [Event] button in action bar
			- Input into fields
			- Click [More Details]
			- Click on [Repeat], input into fields
			- Select a space's calendar for [Calendar] field
			- Click [Save]
		*Input Data: 
			
		*Expected Outcome: 
			Arepeat event is created*/
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String contentSpace=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String firstDay=getFirstDayOfWeek("MM/dd/yyyy");
		String numberRepeat="5";
		info("create new space");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,contentSpace,60000);

		info("Create a recurring event");
		String newEvent= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToSpecificSpace(space);
		spaMg.goToAgendaTab();
		evMg.goToAddEventFromActionBar();
		evMg.moreDetailsEvent();
		evMg.inputBasicDetailEvent(newEvent,newEvent);
		evMg.inputFromToDetailEvent(firstDay, firstDay,false);
		evMg.openRecurringForm();
		evMg.inputRecurringInfoEvent(repeatType.Daily,"1",null,repeatEndType.After,numberRepeat);
		evMg.saveRecurringForm();
		evMg.saveAddEventDetails();
		
		info("A repeat event is created successfully");
		cHome.verifyIsPresentEventTaskWithDateTime(newEvent,"Mon", selectViewOption.WEEK, selectDayOption.ONEDAY);
		cHome.verifyIsPresentEventTaskWithDateTime(newEvent,"Tue", selectViewOption.WEEK, selectDayOption.ONEDAY);
		cHome.verifyIsPresentEventTaskWithDateTime(newEvent,"Wed", selectViewOption.WEEK, selectDayOption.ONEDAY);
		cHome.verifyIsPresentEventTaskWithDateTime(newEvent,"Thu", selectViewOption.WEEK, selectDayOption.ONEDAY);
		cHome.verifyIsPresentEventTaskWithDateTime(newEvent,"Fri", selectViewOption.WEEK, selectDayOption.ONEDAY);
		
		/*Step number: 2
		*Step Name: Edit all instance of a repeated event
		*Step Description: 
			- Double click on an instance of a repeated event of space calendar 
			- Input into fields
			- Click [Save], then Select [All events in the series]
		*Input Data: 
			
		*Expected Outcome: 
			- Comments are added to the initial activity.*/ 
		info("Edit an recurring event");
		String newEvent2= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToSpecificSpace(space);
		spaMg.goToAgendaTab();
		cMang.openEditEventTaskPopup(newEvent,"1");//edit 3th recurring event
		evMg.inputBasicDetailEvent(newEvent2,null);
		evMg.saveAddEventDetails();
		evMg.editRecurringEvent(recurringType.ALL_EVENT);
		
		info("A repeat event is created successfully");
		cHome.verifyIsPresentEventTaskWithDateTime(newEvent2,"Mon", selectViewOption.WEEK, selectDayOption.ONEDAY);
		cHome.verifyIsPresentEventTaskWithDateTime(newEvent2,"Tue", selectViewOption.WEEK, selectDayOption.ONEDAY);
		cHome.verifyIsPresentEventTaskWithDateTime(newEvent2,"Wed", selectViewOption.WEEK, selectDayOption.ONEDAY);
		cHome.verifyIsPresentEventTaskWithDateTime(newEvent2,"Thu", selectViewOption.WEEK, selectDayOption.ONEDAY);
		cHome.verifyIsPresentEventTaskWithDateTime(newEvent2,"Fri", selectViewOption.WEEK, selectDayOption.ONEDAY);
		
		cHome.verifyIsNotPresentEventTaskWithDateTime(newEvent,"Mon", selectViewOption.WEEK, selectDayOption.ONEDAY);
		cHome.verifyIsNotPresentEventTaskWithDateTime(newEvent,"Tue", selectViewOption.WEEK, selectDayOption.ONEDAY);
		cHome.verifyIsNotPresentEventTaskWithDateTime(newEvent,"Wed", selectViewOption.WEEK, selectDayOption.ONEDAY);
		cHome.verifyIsNotPresentEventTaskWithDateTime(newEvent,"Thu", selectViewOption.WEEK, selectDayOption.ONEDAY);
		cHome.verifyIsNotPresentEventTaskWithDateTime(newEvent,"Fri", selectViewOption.WEEK, selectDayOption.ONEDAY);
		
 
		String comment = cCommentData.getContentByIndex(6);
		hp.goToSpecificSpace(space);
		spaMg.goToActivityStreamTab();
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_TASK_EVENT_COMMENT
				.replace("$name", newEvent2).replace("$comment", comment).replace("${editText}",newEvent2));
		
		hp.goToHomePage();
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_TASK_EVENT_COMMENT
				.replace("$name", newEvent2).replace("$comment", comment).replace("${editText}",newEvent2));
		
		info("Delete Data");
		hp.goToSpecificSpace(space);
		spaMg.goToAgendaTab();
		evMg.deleteRecurringEvent(newEvent2, selectViewOption.WEEK,selectDayOption.ONEDAY,recurringType.ALL_EVENT,"Mon");
 	}

	/**
	*<li> Case ID:116257.</li>
	*<li> Test Case Name: Update event after adding attachments.</li>
	*<li> Pre-Condition: - Create a space with calendar app
	- Create an event in a space calendar</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test04_UpdateEventAfterAddingAttachments() {
		info("Test 4: Update event after adding attachments");
		/*Step Number: 1
		*Step Name: Open Form "Add/Edit events"
		*Step Description: 
			- Connect to Intranet
			- Open calendar application
			- Double click an event of space calendar,
		*Input Data: 
			
		*Expected Outcome: 
			Form "Add/Edit events" is opened*/
		String newEvent = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String des = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String contentSpace=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToMySpaces();
		info("create new space");
		spaMg.addNewSpaceSimple(space,contentSpace,60000);
		
		hp.goToSpecificSpace(space);
		spaMg.goToAgendaTab();
		evMg.goToAddEventFromActionBar();
		evMg.inputBasicQuickEvent(newEvent,des);
		evMg.saveQuickAddEvent();

		info("Edit an recurring event");
		String link = fData.getAttachFileByArrayTypeRandom(2);
		hp.goToSpecificSpace(space);
		spaMg.goToAgendaTab();
		cMang.openEditEventTaskPopup(newEvent);//edit 3th recurring event
		
		/*Step number: 2
		*Step Name: Add attachment
		*Step Description: 
			- Click on [More Details], then click [Add Attachment]
			- Click [Select File], select one file
			- Click [Save]
			- Click [Save]
		*Input Data: 
			
		*Expected Outcome: 
			- The Content of the activity isn't updated
			- A comment is added to the activity in the stream with the following message Attachment(s) has been added to the event.*/ 
		info("Add attachment");
		evMg.attachFileToEvent(link);
		evMg.saveAddEventDetails();
		
		String comment = cCommentData.getContentByIndex(9);
		hp.goToSpecificSpace(space);
		spaMg.goToActivityStreamTab();
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_TASK_EVENT_COMMENT
				.replace("$name", newEvent).replace("$comment", comment));
		
		hp.goToHomePage();
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_TASK_EVENT_COMMENT
				.replace("$name", newEvent).replace("$comment", comment));
		
		info("Delete Data");
		hp.goToSpecificSpace(space);
		spaMg.goToAgendaTab();
		cMang.deleteTaskEvent(newEvent);
 	}

	/**
	*<li> Case ID:116258.</li>
	*<li> Test Case Name: Update event as all day event.</li>
	*<li> Pre-Condition: - Create a space with calendar app
	- Create an event in a space calendar</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test05_UpdateEventAsAllDayEvent() {
		info("Test 5: Update event as all day event");
		/*Step Number: 1
		*Step Name: Change event to all day event
		*Step Description: 
			- Connect to Intranet
			- Open calendar application
			- Double click an event of space calendar
			- Check [All day] checkbox
			- Click [Save]
		*Input Data: 
			
		*Expected Outcome: 
			- The event in the activity stream is updated
			- A comment is added to the activity with the following message Event is now an all day event.*/ 
		String newEvent = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String des = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String contentSpace=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToMySpaces();
		info("create new space");
		spaMg.addNewSpaceSimple(space,contentSpace,60000);
		
		hp.goToSpecificSpace(space);
		spaMg.goToAgendaTab();
		evMg.goToAddEventFromActionBar();
		evMg.inputBasicQuickEvent(newEvent,des);
		evMg.saveQuickAddEvent();

		info("Edit an recurring event");
		hp.goToSpecificSpace(space);
		spaMg.goToAgendaTab();
		cMang.openEditEventTaskPopup(newEvent);
		check(evMg.ELEMENT_ADD_EDIT_EVENT_ALLDAY,2);
		evMg.saveAddEventDetails();
		
		String comment = cCommentData.getContentByIndex(10);
		hp.goToSpecificSpace(space);
		spaMg.goToActivityStreamTab();
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_TASK_EVENT_COMMENT
				.replace("$name", newEvent).replace("$comment", comment));
		
		hp.goToHomePage();
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_TASK_EVENT_COMMENT
				.replace("$name", newEvent).replace("$comment", comment));
		
		info("Delete Data");
		hp.goToSpecificSpace(space);
		spaMg.goToAgendaTab();
		cMang.deleteTaskEvent(newEvent);
 	}

	/**
	*<li> Case ID:116259.</li>
	*<li> Test Case Name: Update event as an repeatable event.</li>
	*<li> Pre-Condition: - Create a space with calendar app
	- Create an event in a space calendar</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test06_UpdateEventAsAnRepeatableEvent() {
		info("Test 6: Update event as an repeatable event");
		/*Step Number: 1
		*Step Name: Change Event to Repeat event
		*Step Description: 
			- Connect to Intranet
			- Open calendar application
			- Double click an event of space calendar
			- Click [More Details]
			- Check "Repeat" checkbox
			- Click [Save], click [Save]
		*Input Data: 
			
		*Expected Outcome: 
			- A comment is added to the activity in the stream with the following message: Event will be repeated each: $value.
			- The content of the activity isn't updated*/ 

		String newEvent = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String des = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String contentSpace=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String firstDay=getFirstDayOfWeek("MM/dd/yyyy");
		String numberRepeat="5";
		hp.goToMySpaces();
		info("create new space");
		spaMg.addNewSpaceSimple(space,contentSpace,60000);
		
		hp.goToSpecificSpace(space);
		spaMg.goToAgendaTab();
		evMg.goToAddEventFromActionBar();
		evMg.inputBasicQuickEvent(newEvent,des);
		evMg.inputFromToQuickEvent(firstDay, firstDay,false);
		evMg.saveQuickAddEvent();
		info("Add successfully");
		cMang.scrollElementIntoView(this.driver.findElement(By.xpath(cMang.ELEMENT_EVENT_TASK_TITLE.replace("${name}",newEvent))));
		cHome.verifyIsPresentEventTaskWithDateTime(newEvent,"Mon", selectViewOption.WEEK, selectDayOption.ONEDAY);
		
		info("Edit an recurring event");
		hp.goToSpecificSpace(space);
		spaMg.goToAgendaTab();
		cMang.openEditEventTaskPopup(newEvent);
		evMg.openRecurringForm();
		evMg.inputRecurringInfoEvent(repeatType.Daily,"1",null,repeatEndType.After,numberRepeat);
		evMg.saveRecurringForm();
		evMg.saveAddEventDetails();
		
		info("A repeat event is created successfully");
		cHome.verifyIsPresentEventTaskWithDateTime(newEvent,"Mon", selectViewOption.WEEK, selectDayOption.ONEDAY);
		cHome.verifyIsPresentEventTaskWithDateTime(newEvent,"Tue", selectViewOption.WEEK, selectDayOption.ONEDAY);
		cHome.verifyIsPresentEventTaskWithDateTime(newEvent,"Wed", selectViewOption.WEEK, selectDayOption.ONEDAY);
		cHome.verifyIsPresentEventTaskWithDateTime(newEvent,"Thu", selectViewOption.WEEK, selectDayOption.ONEDAY);
		cHome.verifyIsPresentEventTaskWithDateTime(newEvent,"Fri", selectViewOption.WEEK, selectDayOption.ONEDAY);
				
		String comment = cCommentData.getContentByIndex(0);
		hp.goToSpecificSpace(space);
		spaMg.goToActivityStreamTab();
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_TASK_EVENT_COMMENT
				.replace("$name", newEvent).replace("$comment", comment).replace("${number}",numberRepeat));
		
		hp.goToHomePage();
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_TASK_EVENT_COMMENT
				.replace("$name", newEvent).replace("$comment", comment).replace("${number}",numberRepeat));
		
		info("Delete Data");
		hp.goToSpecificSpace(space);
		spaMg.goToAgendaTab();
		evMg.deleteRecurringEvent(newEvent, selectViewOption.WEEK,selectDayOption.ONEDAY,recurringType.ALL_EVENT,"Mon");
		
 	}

	/**
	*<li> Case ID:116260.</li>
	*<li> Test Case Name: Update event by adding new participants.</li>
	*<li> Pre-Condition: - Create a space with calendar app
	- Create an event in a space calendar</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test07_UpdateEventByAddingNewParticipants() {
		info("Test 7: Update event by adding new participants");
		/*Step Number: 1
		*Step Name: Open form "Add/Edit event"
		*Step Description: 
			- Connect to Intranet
			- Open calendar application
			- Double click on an event of space calendar
		*Input Data: 
			
		*Expected Outcome: 
			Form "Add/Edit event" is opened*/
		String newEvent = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String des = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String contentSpace=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToMySpaces();
		info("create new space");
		spaMg.addNewSpaceSimple(space,contentSpace,60000);
		
		hp.goToSpecificSpace(space);
		spaMg.goToAgendaTab();
		evMg.goToAddEventFromActionBar();
		evMg.inputBasicQuickEvent(newEvent,des);
		evMg.saveQuickAddEvent();

		info("Edit an recurring event");
		hp.goToSpecificSpace(space);
		spaMg.goToAgendaTab();
		cMang.openEditEventTaskPopup(newEvent);
		
		/*Step number: 2
		*Step Name: Add participants to events
		*Step Description: 
			- Click on [Participants] tab
			- Click on [Add Participant] icon
			- Input valid username into [Participants] field
			- Click [Save], 
			- Click [Save]
		*Input Data: 
			
		*Expected Outcome: 
			- The event's activity isn't updated in the activity stream*/ 
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String users = DATA_USER2+"/"+DATA_USER3;
		click(evMg.ELEMENT_EVENT_PARTICIPANTS_TAB);
		evMg.selectPrivacyParticipant(false);
		evMg.selectAvailable(selectAvailableOption.AVAILABLE);
		evMg.click(evMg.ELEMENT_INVITATION_PARTICITPANT_USER);
		evMg.selectUserParticipants(users, content,1);
		click(evMg.ELEMETN_INVITATION_SAVE_BUTTON);
		evMg.selectSendInvitation(selectInvitationOption.NEVER);
		evMg.saveAddEventDetails();

		hp.goToSpecificSpace(space);
		spaMg.goToActivityStreamTab();
		waitForElementNotPresent(hpAct.ELEMENT_ACTIVITY_TASK_EVENT_COMMENT_ITEM.replace("$name", newEvent));
		
		hp.goToHomePage();
		waitForElementNotPresent(hpAct.ELEMENT_ACTIVITY_TASK_EVENT_COMMENT_ITEM.replace("$name", newEvent));
		
		info("Delete Data");
		hp.goToSpecificSpace(space);
		spaMg.goToAgendaTab();
		cMang.deleteTaskEvent(newEvent);
 	}

	/**
	*<li> Case ID:116261.</li>
	*<li> Test Case Name: Update event description.</li>
	*<li> Pre-Condition: - Create a space with calendar app
	- Create an event in a space calendar</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test08_UpdateEventDescription() {
		info("Test 8: Update event description");
		/*Step Number: 1
		*Step Name: Update description of event
		*Step Description: 
			- Connect to Intranet
			- Open calendar application
			- Double click an event of a space calendar
			- Update the [Description] field
			- Click [Save]
		*Input Data: 
			
		*Expected Outcome: 
			- The description of event in the activity stream is updated
			- A comment is added to the activity with the following message: Description has been updated to: $value.*/ 
		String newEvent = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String des = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String newdes = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String contentSpace=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToMySpaces();
		info("create new space");
		spaMg.addNewSpaceSimple(space,contentSpace,60000);
		
		hp.goToSpecificSpace(space);
		spaMg.goToAgendaTab();
		evMg.goToAddEventFromActionBar();
		evMg.inputBasicQuickEvent(newEvent,des);
		evMg.saveQuickAddEvent();
		
		info("Edit an recurring event");
		hp.goToSpecificSpace(space);
		spaMg.goToAgendaTab();
		cMang.openEditEventTaskPopup(newEvent);
		evMg.inputBasicDetailEvent(null,newdes);
		evMg.saveAddEventDetails();
		
		info("Verify the comment is added to the activity");
		String comment = cCommentData.getContentByIndex(15);
		hp.goToSpecificSpace(space);
		spaMg.goToActivityStreamTab();
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_TASK_EVENT_COMMENT
				.replace("$name", newEvent).replace("$comment", comment).replace("${value}",newdes));
		
		hp.goToHomePage();
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_TASK_EVENT_COMMENT
				.replace("$name", newEvent).replace("$comment", comment).replace("${value}",newdes));
		
		info("Delete Data");
		hp.goToSpecificSpace(space);
		spaMg.goToAgendaTab();
		cMang.deleteTaskEvent(newEvent);
 	}

	/**
	*<li> Case ID:116262.</li>
	*<li> Test Case Name: Update event End Date.</li>
	*<li> Pre-Condition: - Create a space with calendar app
	- Create an event in a space calendar</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test09_UpdateEventEndDate() {
		info("Test 9: Update event End Date");
		/*Step Number: 1
		*Step Name: Update an End date of an event
		*Step Description: 
			- Connect to Intranet
			- Open calendar application
			- Double click an event of space calendar 
			- Update [End date] field
			- Click [Save]
		*Input Data: 
			
		*Expected Outcome: 
			- A comment is added to the activity with the following message: End date has been updated to: $value
			- The content of the activity isn't updated*/ 
		String newEvent = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String des = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String contentSpace=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String firstDay=getFirstDayOfWeek("MM/dd/yyyy");
		String lastDay=getLastDayOfWeek("MM/dd/yyyy");
		String nameDay= getLastDayOfWeek("EEEE");
		info("nameDay:"+nameDay);
		hp.goToMySpaces();
		info("create new space");
		spaMg.addNewSpaceSimple(space,contentSpace,60000);
		
		hp.goToSpecificSpace(space);
		spaMg.goToAgendaTab();
		evMg.goToAddEventFromActionBar();
		evMg.inputBasicQuickEvent(newEvent,des);
		evMg.inputFromToQuickEvent(firstDay, firstDay,false);
		evMg.saveQuickAddEvent();
		info("Add successfully");
		cMang.scrollElementIntoView(this.driver.findElement(By.xpath(cMang.ELEMENT_EVENT_TASK_TITLE.replace("${name}",newEvent))));
		cHome.verifyIsPresentEventTaskWithDateTime(newEvent,"Mon", selectViewOption.WEEK, selectDayOption.ONEDAY);
		
		info("Edit an recurring event");
		hp.goToSpecificSpace(space);
		spaMg.goToAgendaTab();
		cMang.openEditEventTaskPopup(newEvent);
		evMg.inputFromToDetailEvent(null,lastDay,false);
		evMg.saveAddEventDetails();
		
		
		String comment = cCommentData.getContentByIndex(11);
		hp.goToSpecificSpace(space);
		spaMg.goToActivityStreamTab();
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_TASK_EVENT_COMMENT
				.replace("$name", newEvent).replace("$comment", comment).replace("${date}",nameDay));
		
		hp.goToHomePage();
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_TASK_EVENT_COMMENT
				.replace("$name", newEvent).replace("$comment", comment).replace("${date}",nameDay));
		
		info("Delete Data");
		hp.goToSpecificSpace(space);
		spaMg.goToAgendaTab();
		cMang.deleteTaskEvent(newEvent);
		
 	}

	/**
	*<li> Case ID:116263.</li>
	*<li> Test Case Name: Update event location.</li>
	*<li> Pre-Condition: - Create a space with calendar app
	- Create an event in a space calendar</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test10_UpdateEventLocation() {
		info("Test 10 Update event location");
		/*Step Number: 1
		*Step Name: Update an event location
		*Step Description: 
			- Connect to Intranet
			- Open calendar application
			- Double click an event of a space calendar
			- Update[Location] field
			- Click [Save]
		*Input Data: 
			
		*Expected Outcome: 
			- The location of event in the activity stream is updated
			- A comment is added to the activity with the following message: Location has been updated to: $value.*/ 
		String newEvent = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String des = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String contentSpace=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToMySpaces();
		info("create new space");
		spaMg.addNewSpaceSimple(space,contentSpace,60000);
		
		hp.goToSpecificSpace(space);
		spaMg.goToAgendaTab();
		evMg.goToAddEventFromActionBar();
		evMg.inputBasicQuickEvent(newEvent,des);
		evMg.saveQuickAddEvent();
		
		info("Edit an recurring event");
		hp.goToSpecificSpace(space);
		spaMg.goToAgendaTab();
		cMang.openEditEventTaskPopup(newEvent);
		evMg.inputBasicDetailEvent(null,null,null,null,"Hanoi");
		evMg.saveAddEventDetails();
		
		String comment = cCommentData.getContentByIndex(12);
		hp.goToSpecificSpace(space);
		spaMg.goToActivityStreamTab();
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_TASK_EVENT_COMMENT
				.replace("$name", newEvent).replace("$comment", comment).replace("${locator}","Hanoi"));
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_TASK_EVENT_LOCATION.replace("$name", newEvent),2000,2);
		
		hp.goToHomePage();
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_TASK_EVENT_COMMENT
				.replace("$name", newEvent).replace("$comment", comment).replace("${locator}","Hanoi"));
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_TASK_EVENT_LOCATION.replace("$name", newEvent),2000,2);
		
		info("Delete Data");
		hp.goToSpecificSpace(space);
		spaMg.goToAgendaTab();
		cMang.deleteTaskEvent(newEvent);
		
 	}

	/**
	*<li> Case ID:116264.</li>
	*<li> Test Case Name: Update event priority.</li>
	*<li> Pre-Condition: - Create a space with calendar app
	- Create an event in a space calendar</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test11_UpdateEventPriority() {
		info("Test 11 Update event priority");
		/*Step Number: 1
		*Step Name: Update an event priority
		*Step Description: 
			- Connect to Intranet
			- Open calendar application
			- Double Click an event of space calendar
			- Change [Priority] field
			- Click [Save]
		*Input Data: 
			
		*Expected Outcome: 
			- A comment is added to the activity with the following message: Priority is now: $value.
			- The content of the event's activity isn't updated*/ 
		String newEvent = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String des = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String contentSpace=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToMySpaces();
		info("create new space");
		spaMg.addNewSpaceSimple(space,contentSpace,60000);
		
		hp.goToSpecificSpace(space);
		spaMg.goToAgendaTab();
		evMg.goToAddEventFromActionBar();
		evMg.inputBasicQuickEvent(newEvent,des);
		evMg.saveQuickAddEvent();

		info("Edit an recurring event");
		hp.goToSpecificSpace(space);
		spaMg.goToAgendaTab();
		cMang.openEditEventTaskPopup(newEvent);
		evMg.inputBasicDetailEvent(null,null,null,null,null,String.valueOf(priorityType.Low));
		evMg.saveAddEventDetails();

		String comment = cCommentData.getContentByIndex(13);
		hp.goToSpecificSpace(space);
		spaMg.goToActivityStreamTab();
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_TASK_EVENT_COMMENT
				.replace("$name", newEvent)
				.replace("$comment", comment)
				.replace("${priority}",
						String.valueOf(priorityType.Low).toLowerCase()));
		hp.goToHomePage();
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_TASK_EVENT_COMMENT
				.replace("$name", newEvent)
				.replace("$comment", comment)
				.replace("${priority}",
						String.valueOf(priorityType.Low).toLowerCase()));

		info("Delete Data");
		hp.goToSpecificSpace(space);
		spaMg.goToAgendaTab();
		cMang.deleteTaskEvent(newEvent);

 	}

	/**
	*<li> Case ID:116265.</li>
	*<li> Test Case Name: Update event privacy.</li>
	*<li> Pre-Condition: - Create a space with calendar app
	- Create an event in a space calendar</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test12_UpdateEventPrivacy() {
		info("Test 12 Update event privacy");
		/*Step Number: 1
		*Step Name: Open form "Add/Edit events"
		*Step Description: 
			- Connect to Intranet
			- Open calendar application
			- Double Click an event of a space calendar
		*Input Data: 
			
		*Expected Outcome: 
			Form "Add/Edit events" is opened*/
		String newEvent = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String des = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String contentSpace=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToMySpaces();
		info("create new space");
		spaMg.addNewSpaceSimple(space,contentSpace,60000);
		
		hp.goToSpecificSpace(space);
		spaMg.goToAgendaTab();
		evMg.goToAddEventFromActionBar();
		evMg.inputBasicQuickEvent(newEvent,des);
		evMg.saveQuickAddEvent();

		info("Edit an recurring event");
		hp.goToSpecificSpace(space);
		spaMg.goToAgendaTab();
		cMang.openEditEventTaskPopup(newEvent);
		/*Step number: 2
		*Step Name: Change option "Privacy"
		*Step Description: 
			- Click [Participants] tab
			- Change option [Privacy]
			- Click [Save]
		*Input Data: 
			
		*Expected Outcome: 
			- The event's activity isn't updated in the activity stream*/ 
		click(evMg.ELEMENT_EVENT_PARTICIPANTS_TAB);
		evMg.selectPrivacyParticipant(true);
		evMg.saveAddEventDetails();
		
		hp.goToSpecificSpace(space);
		spaMg.goToActivityStreamTab();
		waitForElementNotPresent(hpAct.ELEMENT_ACTIVITY_TASK_EVENT_COMMENT_ITEM.replace("$name", newEvent));
		
		hp.goToHomePage();
		waitForElementNotPresent(hpAct.ELEMENT_ACTIVITY_TASK_EVENT_COMMENT_ITEM.replace("$name", newEvent));
		
		info("Delete Data");
		hp.goToSpecificSpace(space);
		spaMg.goToAgendaTab();
		cMang.deleteTaskEvent(newEvent);
 	}

	/**
	*<li> Case ID:116266.</li>
	*<li> Test Case Name: Update event reminders.</li>
	*<li> Pre-Condition: - Create a space with calendar app
	- Create an event in a space calendar</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test13_UpdateEventReminders() {
		info("Test 13 Update event reminders");
		/*Step Number: 1
		*Step Name: Open form "Add/Edit events"
		*Step Description: 
			- Connect to Intranet
			- Open calendar application
			- Double click an event of space calendar
		*Input Data: 
			
		*Expected Outcome: 
			Form "Add/Edit events" is opened*/
		String newEvent = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String des = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String contentSpace=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToMySpaces();
		info("create new space");
		spaMg.addNewSpaceSimple(space,contentSpace,60000);
		
		hp.goToSpecificSpace(space);
		spaMg.goToAgendaTab();
		evMg.goToAddEventFromActionBar();
		evMg.inputBasicQuickEvent(newEvent,des);
		evMg.saveQuickAddEvent();

		info("Edit an recurring event");
		hp.goToSpecificSpace(space);
		spaMg.goToAgendaTab();
		cMang.openEditEventTaskPopup(newEvent);
		/*Step number: 2
		*Step Name: Update reminders
		*Step Description: 
			- Click [Reminder] tab
			- Input into Checkboxes
			- Click [Save]
		*Input Data: 
			
		*Expected Outcome: 
			- The event's activity isn't updated in the activity stream*/
		click(evMg.ELEMENT_REMINDER_TAB);
		evMg.selectReminderEmailBox("10 minutes");
		evMg.saveAddEventDetails();
		
		hp.goToSpecificSpace(space);
		spaMg.goToActivityStreamTab();
		waitForElementNotPresent(hpAct.ELEMENT_ACTIVITY_TASK_EVENT_COMMENT_ITEM.replace("$name", newEvent));
		
		hp.goToHomePage();
		waitForElementNotPresent(hpAct.ELEMENT_ACTIVITY_TASK_EVENT_COMMENT_ITEM.replace("$name", newEvent));
		
		info("Delete Data");
		hp.goToSpecificSpace(space);
		spaMg.goToAgendaTab();
		cMang.deleteTaskEvent(newEvent);

 	}

	/**
	*<li> Case ID:116267.</li>
	*<li> Test Case Name: Update event start date.</li>
	*<li> Pre-Condition: - Create a space with calendar app
	- Create an event in a space calendar</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test14_UpdateEventStartDate() {
		info("Test 14 Update event start date");
		/*Step Number: 1
		*Step Name: Update an event star date
		*Step Description: 
			- Connect to Intranet
			- Open calendar application
			- Double click an eventof a space calendar
			- Update From date field
			- Click [Save]
		*Input Data: 
			
		*Expected Outcome: 
			- The start date in the activity stream is updated following the selected date
			- A comment is added to the activity with the following message: Start date has been updated to: $value*/ 
		String newEvent = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String des = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String contentSpace=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String firstDay=getFirstDayOfWeek("MM/dd/yyyy");
		String firstDayName=getFirstDayOfWeek("EEEE");
		hp.goToMySpaces();
		info("create new space");
		spaMg.addNewSpaceSimple(space,contentSpace,60000);
		
		hp.goToSpecificSpace(space);
		spaMg.goToAgendaTab();
		evMg.goToAddEventFromActionBar();
		evMg.inputBasicQuickEvent(newEvent,des);
		evMg.saveQuickAddEvent();

		info("Edit an recurring event");
		hp.goToSpecificSpace(space);
		spaMg.goToAgendaTab();
		cMang.openEditEventTaskPopup(newEvent);
		evMg.inputFromToDetailEvent(firstDay, firstDay,false);
		evMg.saveAddEventDetails();

		String comment = cCommentData.getContentByIndex(14);
		hp.goToSpecificSpace(space);
		spaMg.goToActivityStreamTab();
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_TASK_EVENT_COMMENT
				.replace("$name", newEvent).replace("$comment", comment).replace("${date}",firstDayName));
		
		hp.goToHomePage();
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_TASK_EVENT_COMMENT
				.replace("$name", newEvent).replace("$comment", comment).replace("${date}",firstDayName));
		
		info("Delete Data");
		hp.goToSpecificSpace(space);
		spaMg.goToAgendaTab();
		cMang.deleteTaskEvent(newEvent);
	}

	/**
	*<li> Case ID:116268.</li>
	*<li> Test Case Name: Update event summary.</li>
	*<li> Pre-Condition: - Create a space with calendar app
	- Create an event in a space calendar</li>
	*<li> Post-Condition: </li>
	*/
	@Test(groups="pendings")
	public  void test15_UpdateEventSummary() {
		info("Test 15 Update event summary");
		/*Step Number: 1
		*Step Name: Update an event summary
		*Step Description: 
			- Connect to Intranet 
			- Open calendar application
			- Double click on an event
			- Update the [Summary Event] field, then click [Save]
		*Input Data: 
			
		*Expected Outcome: 
			- The summary of event in the activity stream is updated 
			- A comment is added with the following message: "Summary has been updated to: $value."*/ 

 	}

	/**
	*<li> Case ID:116269.</li>
	*<li> Test Case Name: Update event with user's availabilty.</li>
	*<li> Pre-Condition: - Create a space with calendar app
	- Create an event in a space calendar</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test16_UpdateEventWithUsersAvailabilty() {
		info("Test 16 Update event with user's availabilty");
		/*Step Number: 1
		*Step Name: Open Add/Edit event form
		*Step Description: 
			- Connect to Intranet
			- Open calendar application
			- Double click on an event of a space calendar,
		*Input Data: 
			
		*Expected Outcome: 
			Add/Edit event form is opened*/
		String newEvent = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String des = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String contentSpace=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToMySpaces();
		info("create new space");
		spaMg.addNewSpaceSimple(space,contentSpace,60000);
		
		hp.goToSpecificSpace(space);
		spaMg.goToAgendaTab();
		evMg.goToAddEventFromActionBar();
		evMg.inputBasicQuickEvent(newEvent,des);
		evMg.saveQuickAddEvent();

		info("Edit an recurring event");
		hp.goToSpecificSpace(space);
		spaMg.goToAgendaTab();
		Utils.pause(2000);
		cMang.openEditEventTaskPopup(newEvent);
		/*Step number: 2
		*Step Name: Update availabity during event
		*Step Description: 
			- Click on [Participants] tab
			- Change [Available] option, then Click [Save]
		*Input Data: 
			
		*Expected Outcome: 
			- The event's activity isn't updated in the activity stream*/ 
		click(evMg.ELEMENT_EVENT_PARTICIPANTS_TAB);
		evMg.selectAvailable(selectAvailableOption.AVAILABLE);
		evMg.saveAddEventDetails();
		
		hp.goToSpecificSpace(space);
		spaMg.goToActivityStreamTab();
		waitForElementNotPresent(hpAct.ELEMENT_ACTIVITY_TASK_EVENT_COMMENT_ITEM.replace("$name", newEvent));
		
		hp.goToHomePage();
		waitForElementNotPresent(hpAct.ELEMENT_ACTIVITY_TASK_EVENT_COMMENT_ITEM.replace("$name", newEvent));
		
		info("Delete Data");
		hp.goToSpecificSpace(space);
		spaMg.goToAgendaTab();
		cMang.deleteTaskEvent(newEvent);
 	}}
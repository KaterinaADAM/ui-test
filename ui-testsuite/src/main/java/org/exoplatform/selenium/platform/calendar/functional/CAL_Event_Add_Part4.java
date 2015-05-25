package org.exoplatform.selenium.platform.calendar.functional;

import static org.exoplatform.selenium.TestLogger.info;

import org.testng.annotations.*;


	public class CAL_Event_Add_Part4 extends CAL_TestConfig{

	/**
	*<li> Case ID:116501.</li>
	*<li> Test Case Name: Add quick event from calendar view by drop and drag date.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test60_AddQuickEventFromCalendarViewByDropAndDragDate() {
		info("Test 60 Add quick event from calendar view by drop and drag date");
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			Step 1: Show quick add event form
		*Input Data: 
			- In day, week,work week and moth view calendar, drop and drag on the calendar panel
		*Expected Outcome: 
			Quick add event form appears properly with default calendar, default category and current default date time.*/

		/*Step number: 2
		*Step Name: -
		*Step Description: 
			Step 2: Add quick event
		*Input Data: 
			Input required fields and Save/Save
		*Expected Outcome: 
			The new event is added and shown in this specific calendar*/ 

 	}

	/**
	*<li> Case ID:116502.</li>
	*<li> Test Case Name: Add detail event from action bar.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test61_AddDetailEventFromActionBar() {
		info("Test 61 Add detail event from action bar");
		/*Step Number: 1
		*Step Name: -
		*Step Description: 
			Step 1: Show detail add event form
		*Input Data: 
			- Click Add event on theaction bar
			- Click on More detail
		*Expected Outcome: 
			- Add detail event form appears properly
			- Display 4 tabs: Detail, Reminders, Participants and Schedule.*/

		/*Step number: 2
		*Step Name: -
		*Step Description: 
			Step 2: Add detail event
		*Input Data: 
			- Input required fields
			- Click Save/Save
		*Expected Outcome: 
			The new event is added and shown in this specific calendar*/ 

 	}

	/**
	*<li> Case ID:116503.</li>
	*<li> Test Case Name: Add new event with duplicated name.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test62_AddNewEventWithDuplicatedName() {
		info("Test 62 Add new event with duplicated name");
		/*Step Number: 1
		*Step Name: -
		*Step Description: 
			Step 1: Show detail add event form
		*Input Data: 
			- Click Add event on theaction bar
			- Click on More detail
		*Expected Outcome: 
			- Add detail event form appears properly
			- Display 4 tabs: Detail, Reminders, Participants and Schedule.*/

		/*Step number: 2
		*Step Name: -
		*Step Description: 
			Step 2: Add event with duplicated name
		*Input Data: 
			- Input a duplicated name 
			- Input value for other fields
			- Click Save/Save
		*Expected Outcome: 
			- The event with duplicated name is added properly.*/ 

 	}

	/**
	*<li> Case ID:116504.</li>
	*<li> Test Case Name: Add new event with special characters.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test63_AddNewEventWithSpecialCharacters() {
		info("Test 63 Add new event with special characters");
		/*Step Number: 1
		*Step Name: -
		*Step Description: 
			Step 1: Show detail add event form
		*Input Data: 
			- Click Add event on theaction bar
			- Click on More detail
		*Expected Outcome: 
			- Add detail event form appears properly
			- Display 4 tabs: Detail, Reminders, Participants and Schedule.*/

		/*Step number: 2
		*Step Name: -
		*Step Description: 
			Step 2: Add event with special characters
		*Input Data: 
			- Input value some specialcharacters< > for Event summary field 
			- Input value for other fields
			- Click Save/Save
		*Expected Outcome: 
			- Show alert message report that :Event summary does not contain >, <.*/ 

 	}

	/**
	*<li> Case ID:116505.</li>
	*<li> Test Case Name: Display properly tabs when clicking more details.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test64_DisplayProperlyTabsWhenClickingMoreDetails() {
		info("Test 64 Display properly tabs when clicking more details");
		/*Step Number: 1
		*Step Name: - Step 1: Show detail add event form
		*Step Description: 
			- Click Add event on theaction bar
			- Click on More detail
		*Input Data: 
			
		*Expected Outcome: 
			- Add detail event form appears properly
			- Display 4 tabs: Detail, Reminders, Participants and Schedule.*/ 

 	}

	/**
	*<li> Case ID:116506.</li>
	*<li> Test Case Name: Add new event in case name of event begin with space characters.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test65_AddNewEventInCaseNameOfEventBeginWithSpaceCharacters() {
		info("Test 65 Add new event in case name of event begin with space characters");
		/*Step Number: 1
		*Step Name: Step 1: Add event with special name
		*Step Description: 
			- Input value for Summary field and begin with space characters
			- Input value for other fields
			- Click Save
		*Input Data: 
			
		*Expected Outcome: 
			Add new event successfully with input values. Those space characters are deleted after creating*/ 

 	}

	/**
	*<li> Case ID:116507.</li>
	*<li> Test Case Name: Add new event in a period of time in one day.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test66_AddNewEventInAPeriodOfTimeInOneDay() {
		info("Test 66 Add new event in a period of time in one day");
		/*Step Number: 1
		*Step Name: -
		*Step Description: 
			Step 1: Show add new event form
		*Input Data: 
			- Create new event category,[ Details ] personal/group/share calendar
			- Click Add Event in Action bar orright click on calendar for which user has edit right
		*Expected Outcome: 
			Add new event form is shown*/

		/*Step number: 2
		*Step Name: -
		*Step Description: 
			Step 2: Complete adding new event
		*Input Data: 
			- Select the same value for date
			- Select Start/End time
			- Input value for other fields
			- Click Save/Save
		*Expected Outcome: 
			This event is created and displayed in working area in selected date and time as a part of day*/ 

 	}

	/**
	*<li> Case ID:116508.</li>
	*<li> Test Case Name: Add new event in a period of time in one day by selecting from working pane in Day View.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test67_AddNewEventInAPeriodOfTimeInOneDayBySelectingFromWorkingPaneInDayView() {
		info("Test 67 Add new event in a period of time in one day by selecting from working pane in Day View");
		/*Step Number: 1
		*Step Name: -
		*Step Description: 
			Step 1: Display calendar in Day View
		*Input Data: 
			Select to show calendar in Day View
		*Expected Outcome: 
			Events happen in current day are displayed in working pane*/

		/*Step number: 2
		*Step Name: -
		*Step Description: 
			Step 2: Show add new event form
		*Input Data: 
			Select duration of new event from working pane
		*Expected Outcome: 
			Add new event form is shown:
			- Current date is selected as default for both From and To date
			- The From and To time is selected as you selected from working pane*/

		/*Step number: 3
		*Step Name: -
		*Step Description: 
			Step 3: Complete adding new event
		*Input Data: 
			- Input other fields
			- Click Save/Save
		*Expected Outcome: 
			New event is displayed in Day View working pane between selected From and To time*/ 

 	}

	/**
	*<li> Case ID:116509.</li>
	*<li> Test Case Name: Add new event in a period of time in one day by selecting from working pane in Week View.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test68_AddNewEventInAPeriodOfTimeInOneDayBySelectingFromWorkingPaneInWeekView() {
		info("Test 68 Add new event in a period of time in one day by selecting from working pane in Week View");
		/*Step Number: 1
		*Step Name: -
		*Step Description: 
			Step 1: Display calendar in Week View
		*Input Data: 
			Select to show calendar in Week View
		*Expected Outcome: 
			Events happen in current week are displayed in working pane*/

		/*Step number: 2
		*Step Name: -
		*Step Description: 
			Step 2: Show add new event form
		*Input Data: 
			Select duration of new event in one day from working pane
		*Expected Outcome: 
			Add new event form is shown:
			- The date where you selected the time duration is selected as default for both From and To date
			- The From and To time is selected as you selected from working pane*/

		/*Step number: 3
		*Step Name: -
		*Step Description: 
			Step 3: Complete adding new event
		*Input Data: 
			- Input other fields
			- Click Save/Save
		*Expected Outcome: 
			New event is displayed in selected day in Week working pane between selected From and To time*/ 

 	}

	/**
	*<li> Case ID:116510.</li>
	*<li> Test Case Name: Add new event in a specific day from working pane in Month View.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test69_AddNewEventInASpecificDayFromWorkingPaneInMonthView() {
		info("Test 69 Add new event in a specific day from working pane in Month View");
		/*Step Number: 1
		*Step Name: -
		*Step Description: 
			Step 1: Display calendar in Month View
		*Input Data: 
			Select to show calendar in Month View
		*Expected Outcome: 
			Events happen in selected month are displayed in working pane*/

		/*Step number: 2
		*Step Name: -
		*Step Description: 
			Step 2: Show add new event form
		*Input Data: 
			Click on one date in working pane
		*Expected Outcome: 
			Add new event form is shown:
			- The date you selected is displayed in From and To fields as default*/

		/*Step number: 3
		*Step Name: -
		*Step Description: 
			Step 3: Complete adding new event
		*Input Data: 
			- Input value for fields
			- Click Save/Save
		*Expected Outcome: 
			New added event is displayed in selected day in Month working pane*/ 

 	}

	/**
	*<li> Case ID:116511.</li>
	*<li> Test Case Name: Add new event in a period of time in one day by selecting from working pane in Work Week View.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test70_AddNewEventInAPeriodOfTimeInOneDayBySelectingFromWorkingPaneInWorkWeekView() {
		info("Test 70 Add new event in a period of time in one day by selecting from working pane in Work Week View");
		/*Step Number: 1
		*Step Name: -
		*Step Description: 
			Step 1: Display calendar in Work Week View
		*Input Data: 
			Select to show calendar in Work Week View
		*Expected Outcome: 
			Events happen in working day in week are displayed in working pane*/

		/*Step number: 2
		*Step Name: -
		*Step Description: 
			Step 2: Show add new event form
		*Input Data: 
			Select duration of new event in one day from working pane
		*Expected Outcome: 
			Add new event form is shown:
			- The date where you selected the time duration is selected as default for both From and To date
			- The From and To time is selected as you selected from working pane*/

		/*Step number: 3
		*Step Name: -
		*Step Description: 
			Step 3: Complete adding new event
		*Input Data: 
			- Input other fields
			- Click Save/Save
		*Expected Outcome: 
			New added event is displayed in selected day in working pane between selected From and To time*/ 

 	}

	/**
	*<li> Case ID:116512.</li>
	*<li> Test Case Name: Add new event in a period of time in case From and To time is the start and end time of day.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test71_AddNewEventInAPeriodOfTimeInCaseFromAndToTimeIsTheStartAndEndTimeOfDay() {
		info("Test 71 Add new event in a period of time in case From and To time is the start and end time of day");
		/*Step Number: 1
		*Step Name: -
		*Step Description: 
			Step 1:Show add new event form
		*Input Data: 
			- Click Add event on theaction bar
			- Click on More detail
		*Expected Outcome: 
			- Add detail event form appears properly
			- Display 4 tabs: Detail, Reminders, Participants and Schedule.*/

		/*Step number: 2
		*Step Name: -
		*Step Description: 
			Step 2: Complete adding new event
		*Input Data: 
			- Select the same date for From and To
			- Select/input a time from 00:00 to 00:14 for From
			- Select/input a time from 23:46 to 23:59
			- Input value for other fields
			- Click Save
		*Expected Outcome: 
			This event is created and displayed in working area as all day event*/ 

 	}

	/**
	*<li> Case ID:116513.</li>
	*<li> Test Case Name: Add new event when choose a specific  category to use.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test72_AddNewEventWhenChooseASpecificCategoryToUse() {
		info("Test 72 Add new event when choose a specific  category to use");
		/*Step Number: 1
		*Step Name: -
		*Step Description: 
			Step 1: Choose a specific category
		*Input Data: 
			- Choose a specific category from combo box of categories
		*Expected Outcome: 
			The category is shown*/

		/*Step number: 2
		*Step Name: -
		*Step Description: 
			Step 2: Open Add/edit event form
		*Input Data: 
			Perform to open add/edit event form
		*Expected Outcome: 
			- The form is shown properly
			- Default event category of Eventcategory field will be above category*/ 

 	}

	/**
	*<li> Case ID:116514.</li>
	*<li> Test Case Name: Add new event in a period of time in case From time is greater than To time.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test73_AddNewEventInAPeriodOfTimeInCaseFromTimeIsGreaterThanToTime() {
		info("Test 73 Add new event in a period of time in case From time is greater than To time");
		/*Step Number: 1
		*Step Name: -
		*Step Description: 
			Step 1:Show add new event form
		*Input Data: 
			- Click Add event on theaction bar
			- Click on More detail
		*Expected Outcome: 
			- Add detail event form appears properly
			- Display 4 tabs: Detail, Reminders, Participants and Schedule.*/

		/*Step number: 2
		*Step Name: -
		*Step Description: 
			Step 2: Complete adding new event
		*Input Data: 
			-Select From time is greater than To time
			- Input value for other fields
			- Click Save/Save
		*Expected Outcome: 
			Show message to inform about the invalid period time.*/ 

 	}

	/**
	*<li> Case ID:116515.</li>
	*<li> Test Case Name: Add all day event.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test74_AddAllDayEvent() {
		info("Test 74 Add all day event");
		/*Step Number: 1
		*Step Name: -
		*Step Description: 
			Step 1:Show add new event form
		*Input Data: 
			- Click Add event on theaction bar
			- Click on More detail
		*Expected Outcome: 
			- Add detail event form appears properly
			- Display 4 tabs: Detail, Reminders, Participants and Schedule.*/

		/*Step number: 2
		*Step Name: -
		*Step Description: 
			Step 2: Complete adding new event
		*Input Data: 
			- Check the All day check box
			- Input required fields
			- Click Save/Save
		*Expected Outcome: 
			New event is added and displayed at the top of working pane of Day and week , week workview, it's displayed as other events in other view*/ 

 	}

	/**
	*<li> Case ID:116516.</li>
	*<li> Test Case Name: Add event in full several days.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test75_AddEventInFullSeveralDays() {
		info("Test 75 Add event in full several days");
		/*Step Number: 1
		*Step Name: -
		*Step Description: 
			Step 1:Show add new event form
		*Input Data: 
			- Click Add event on theaction bar
			- Click on More detail
		*Expected Outcome: 
			- Add detail event form appears properly
			- Display 4 tabs: Detail, Reminders, Participants and Schedule.*/

		/*Step number: 2
		*Step Name: -
		*Step Description: 
			Step 2: Complete adding new event
		*Input Data: 
			- Select From date < To date
			- Check the All day check box or select start time of day for From and end time of day for To
			- Input valid value for other fields
			- Click Save/Save
		*Expected Outcome: 
			Added event is displayed in full selected days*/ 

 	}

	/**
	*<li> Case ID:116517.</li>
	*<li> Test Case Name: Add event in several days but not full day.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test76_AddEventInSeveralDaysButNotFullDay() {
		info("Test 76 Add event in several days but not full day");
		/*Step Number: 1
		*Step Name: -
		*Step Description: 
			Step 1:Show add new event form
		*Input Data: 
			- Click Add event on theaction bar
			- Click on More detail
		*Expected Outcome: 
			- Add detail event form appears properly
			- Display 4 tabs: Detail, Reminders, Participants and Schedule.*/

		/*Step number: 2
		*Step Name: -
		*Step Description: 
			Step 2: Complete adding new event
		*Input Data: 
			- Select From date < To date
			- Select From and To time (not the start and end time of day)
			- Input valid value for other fields
			- Click Save/Save
		*Expected Outcome: 
			Added event is displayed as all day event but in selected From and To time*/ 

 	}}
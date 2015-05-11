package org.exoplatform.selenium.platform.calendar.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import java.util.List;

import org.exoplatform.selenium.platform.HomePagePlatform;
import org.exoplatform.selenium.platform.ManageLogInOut;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.calendar.TaskManagement;
import org.exoplatform.selenium.platform.calendar.CalendarHomePage;
import org.exoplatform.selenium.platform.calendar.CalendarManagement;
import org.exoplatform.selenium.platform.calendar.CalendarHomePage.selectDayOption;
import org.exoplatform.selenium.platform.calendar.CalendarHomePage.selectViewOption;
import org.exoplatform.selenium.platform.calendar.CalendarManagement.menuOfCalendarOption;
import org.exoplatform.selenium.platform.calendar.CalendarManagement.menuOfMainCalendar;
import org.exoplatform.selenium.platform.objectdatabase.common.TextBoxDatabase;
import org.exoplatform.selenium.platform.objectdatabase.user.UserDatabase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;


public class Calendar_Calendar extends PlatformBase {
	HomePagePlatform hp;
	ManageLogInOut magAc;
	TextBoxDatabase txData;
	CalendarHomePage cHome;
	TaskManagement task;
	CalendarManagement cMang;
	UserDatabase userData;

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
		task= new TaskManagement(driver);
		cMang = new CalendarManagement(driver);
		txData = new TextBoxDatabase();
		userData = new UserDatabase();
		userData.setUserData(userDataFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		txData.setContentData(texboxFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlContent);
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
	 * Case ID:115594.
	 * Test Case Name: Check collapse/expand on left panel.
	 * Pre-Condition: Calendar is displayed with default is expanded left panel
	 * Post-Condition: 
	 */
	@Test
	public  void test01_CheckCollapseexpandOnLeftPanel() {
		info("Test 1: Check collapse/expand on left panel");
		/*Step Number: 1
		 *Step Name: Step 1: Collapse panel
		 *Step Description: 
			- Go to Calendar
			- Click arrow icon on the left panel
		 *Input Data: 

		 *Expected Outcome: 
			- Left panel is collapsed, mini calendar and calendar list are not shown*/
		hp.goToCalendarPage();
		cMang.goToMenuFromMainCalendar(menuOfMainCalendar.CALSETTING);
		cMang.changeSettingCalendar(null,"(GMT +07:00) Asia/Ho_Chi_Minh",null,null,null,null,null);
		cMang.saveSetting();
		click(cHome.ELEMENT_SHOW_HIDE_LEFT_PANEL);
		waitForElementNotPresent(cHome.ELEMENT_CALENDAR_PANEL);
		waitForElementNotPresent(cHome.ELEMENT_TOOLBAR_MINI_CALENDAR);

		/*Step number: 2
		 *Step Name: Step 2: Expand panel
		 *Step Description: 
			- When panel is collapsed, click arrow icon
		 *Input Data: 

		 *Expected Outcome: 
			- Left panel is expanded, mini calendar and calendar list are shown*/ 
		click(cHome.ELEMENT_SHOW_HIDE_LEFT_PANEL);
		waitForAndGetElement(cHome.ELEMENT_CALENDAR_PANEL);
		waitForAndGetElement(cHome.ELEMENT_TOOLBAR_MINI_CALENDAR);
	}

	/**
	 * Case ID:115671.
	 * Test Case Name: Add Personal Calendar.
	 * Case ID:115672.
	 * Test Case Name: Edit Personal Calendar.
	 * Case ID:115696.
	 * Test Case Name: Delete Personal Calendar.
	 * Pre-Condition: 
	 * Post-Condition: 
	 */
	@Test
	public  void test02_03_04_AddEditDeletePersonalCalendar() {
		String calendarName= txData.getContentByArrayTypeRandom(1)+"115671";
		String newCalendar= txData.getContentByArrayTypeRandom(1)+"215671";
		String calendarColor = "light_purple";

		info("Test 2: Add Personal Calendar");
		/*Step Number: 1
		 *Step Name: -
		 *Step Description: 
			Step 1: Add a personal calendar
		 *Input Data: 
			- Click + iconon the top left pane and selectAdd Calendar
			- Fill required fields in Calendar Details tab
			- Save
		 *Expected Outcome: 
			- The calendar is added into Personal Calendar*/ 
		hp.goToCalendarPage();
		cMang.goToMenuFromMainCalendar(menuOfMainCalendar.ADDCAL);
		cMang.inputDataInDetailTabCalendarForm(calendarName, calendarName, calendarColor);
		cMang.saveAddCalendar();
		waitForAndGetElement(cMang.ELEMENT_PERSONAL_CALENDAR_LIST_ITEM.replace("$calendar", calendarName));

		info("Test 3: Edit Personal Calendar");
		/*Step Number: 1
		 *Step Name: -
		 *Step Description: 
			Step 1: Edit Personal Calendar
		 *Input Data: 
			- Add a personal calendar or choose an existing one.
			- Mouse over calendar then click on wheelicon of an existing calendar and select Edit
			- Edit some value
			- Save
		 *Expected Outcome: 
			- The calendar is updated with the changed value*/ 
		cMang.editCalendar(calendarName, newCalendar, newCalendar,calendarColor,null);
		cMang.saveAddCalendar();
		waitForAndGetElement(cMang.ELEMENT_PERSONAL_CALENDAR_LIST_ITEM.replace("$calendar", newCalendar));

		info("Test 4 Delete Personal Calendar");
		/*Step Number: 1
		 *Step Name: -
		 *Step Description: 
			Step 1: Delete Personal Calendar
		 *Input Data: 
			- Create a personal calendar or choose an existing one
			- Mouse over calendar then click on wheelicon of an existing calendar and select Remove
			- Click OK at confirmation
		 *Expected Outcome: 
			- The calendar is removed from the personal calendar group*/ 
		hp.goToCalendarPage();
		cMang.deleteCalendar(newCalendar,true);
	}

	/**
	 * Case ID:115673.
	 * Test Case Name: Add Group Calendar.
	 * Case ID:115674.
	 * Test Case Name: Edit Group Calendar.
	 * Case ID:115697.
	 * Test Case Name: Delete Group Calendar.
	 * Pre-Condition: 
	 * Post-Condition: 
	 */
	@Test
	public  void test05_06_07_AddEditDeleteGroupCalendar() {
		String groupCalendar = "/platform/web-contributors";
		String calendarName= txData.getContentByArrayTypeRandom(1)+"1115673";
		String newCalendar= txData.getContentByArrayTypeRandom(1)+"2115673";
		String calendarColor = "light_purple";

		info("Test 5: Add Group Calendar");
		/*Step Number: 1
		 *Step Name: -
		 *Step Description: 
			Step 1: Add Group Calendar
		 *Input Data: 
			+ Open Add Calendar form+ Input Display Name+ In Show in Groups tab, choose group(s) and select User name/ membership+ Click Save
		 *Expected Outcome: 
			- The group calendar is added normally and displayed at the group calendar*/ 
		hp.goToCalendarPage();
		cMang.goToMenuFromMainCalendar(menuOfMainCalendar.ADDCAL);
		cMang.inputDataInDetailTabCalendarForm(calendarName, calendarName, calendarColor);
		cMang.selectGroupInGroupTabCalendarForm(groupCalendar,true);
		cMang.saveAddCalendar();
		waitForAndGetElement(cMang.ELEMENT_GROUP_CALENDAR_LIST_ITEM.replace("$calendar", calendarName));

		info("Test 6: Edit Group Calendar");
		/*Step Number: 1
		 *Step Name: -
		 *Step Description: 
			Step 1: Edit Group Calendar
		 *Input Data: 
			- Add a Group calendar
			- Edit a group calendar by mouse over then click wheel icon of existing group calendar and select Edit
		 *Expected Outcome: 
			- The group calendars are updated normally.*/ 
		cMang.editCalendar(calendarName, newCalendar, newCalendar,calendarColor,null);
		cMang.saveAddCalendar();
		waitForAndGetElement(cMang.ELEMENT_GROUP_CALENDAR_LIST_ITEM.replace("$calendar", newCalendar));

		info("Test 7 Delete Group Calendar");
		/*Step Number: 1
		 *Step Name: -
		 *Step Description: 
			Step 1: Delete Group Calendar
		 *Input Data: 
			- Add a group calendar
			- Delete a public calendar by mouse over then click wheel icon of existing group calendar and select Remove
			- Click OK at confirmation message
		 *Expected Outcome: 
			- The group calendar is removed from the group calendar*/ 

		hp.goToCalendarPage();
		cMang.deleteCalendar(newCalendar,true);
	}

	/**
	 * Case ID:115698.
	 * Test Case Name: Share a calendar.
	 * Case ID:115676.
	 * Test Case Name: Edit a shared calendar.
	 * Pre-Condition: - A calendar exists in Personal calendar
	 * Post-Condition: 
	 */
	@Test
	public  void test08_09_EditShareACalendar() {
		String calendarName= txData.getContentByArrayTypeRandom(1)+"1115698";
		String newCalendar= txData.getContentByArrayTypeRandom(1)+"2115698";
		String calendarColor = "light_purple";
		String[] groupShare = {DATA_USER2};
		boolean[] edit = {false};
		boolean[] newEdit = {true};
		info("Test 08 Share a calendar");
		/*Step Number: 1
		 *Step Name: - Share a calendar
		 *Step Description: 
			- Login and goto calendar
			- Click on wheel icon of existing Personal calendar and select Share
			- Select user or group to share the calendar
			- Click Save
			- Log in by user who is shared and goto calendar to check
			- Click on the wheel icon to show action menu
		 *Input Data: 

		 *Expected Outcome: 
			- can see and do following actions only:RefreshRemove*/ 
		hp.goToCalendarPage();
		cMang.goToMenuFromMainCalendar(menuOfMainCalendar.ADDCAL);
		cMang.inputDataInDetailTabCalendarForm(calendarName, calendarName, calendarColor);
		cMang.saveAddCalendar();
		waitForAndGetElement(cMang.ELEMENT_CALENDAR_LIST_ITEM.replace("$calendar", calendarName));
		cMang.shareCalendar(calendarName, groupShare, edit , 1);
		magAc.signOut();
		magAc.signIn(DATA_USER2, DATA_PASS);
		hp.goToCalendarPage();
		waitForAndGetElement(cMang.ELEMENT_SHARED_CALENDAR_LIST_ITEM.replace("$calendar", calendarName));
		cMang.openMenuOfCalendar(calendarName);
		waitForElementNotPresent(cMang.ELEMENT_CALENDAR_ADD_TASK_MENU);
		waitForElementNotPresent(cMang.ELEMENT_CALENDAR_ADD_EVENT_MENU);
		waitForElementNotPresent(cMang.ELEMENT_CALENDAR_EDIT_MENU);
		waitForElementNotPresent(cMang.ELEMENT_CALENDAR_REMOVE_MENU);
		waitForElementNotPresent(cMang.ELEMENT_CALENDAR_SHARE_MENU);
		waitForElementNotPresent(cMang.ELEMENT_CALENDAR_IMPORT_MENU);
		waitForElementNotPresent(cMang.ELEMENT_CALENDAR_EXPORT_MENU);
		waitForAndGetElement(cMang.ELEMENT_CALENDAR_REFRESH_MENU);

		info("Test 09: Edit a shared calendar");
		/*Step Number: 1
		 *Step Name: - Edit a shared calendar
		 *Step Description: 
			- Share a calendar to a user/ group
			- Click wheel icon of existing shared calendar and select Share 
			- Select user/ group and grant[Edit Permission] right
			- Save
		 *Input Data: 

		 *Expected Outcome: 
			- Permission of shared user/ group is edited successfully*/

		/*Step number: 2
		 *Step Name: - Check shared calendar
		 *Step Description: 
			- Login with the user who is shared
			- Click on the wheel icon in shared calendar to show menu actions
		 *Input Data: 

		 *Expected Outcome: 
			- In the action menu, there are following items:Add eventAdd taskImportExportRefreshRemove*/
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		hp.goToCalendarPage();
		cMang.executeActionCalendar(calendarName, menuOfCalendarOption.EDIT);
		cMang.inputDataInDetailTabCalendarForm(newCalendar, newCalendar, calendarColor);
		cMang.saveAddCalendar();
		waitForAndGetElement(cMang.ELEMENT_CALENDAR_LIST_ITEM.replace("$calendar", newCalendar));
		cMang.shareCalendar(newCalendar, groupShare, newEdit , 1);
		waitForAndGetElement(cMang.ELEMENT_CALENDAR_LIST_ITEM.replace("$calendar", newCalendar));
		magAc.signOut();
		magAc.signIn(DATA_USER2, DATA_PASS);
		hp.goToCalendarPage();
		cMang.openMenuOfCalendar(newCalendar);
		waitForAndGetElement(cMang.ELEMENT_CALENDAR_REMOVE_SHARE_CALENDAR);
		waitForAndGetElement(cMang.ELEMENT_CALENDAR_IMPORT_MENU);
		waitForAndGetElement(cMang.ELEMENT_CALENDAR_EXPORT_MENU);
		waitForAndGetElement(cMang.ELEMENT_CALENDAR_REFRESH_MENU);
		waitForAndGetElement(cMang.ELEMENT_CALENDAR_ADD_TASK_MENU);
		waitForAndGetElement(cMang.ELEMENT_CALENDAR_ADD_EVENT_MENU);

		info("Clear data");
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		hp.goToCalendarPage();
		cMang.deleteCalendar(calendarName,true);
	}


	/**
	 * Case ID:115675.
	 * Test Case Name: Remove user/group from shared list.
	 * Pre-Condition: - A calendar is sharing to some users
	 * Post-Condition: 
	 */
	@Test
	public  void test10_RemoveUsergroupFromSharedList() {
		String calendarName= txData.getContentByArrayTypeRandom(1)+"115675";
		String calendarColor = "light_purple";
		String[] groupShare = {DATA_USER2};
		boolean[] edit = {true};

		info("Test 10: Remove user/group from shared list");
		/*Step Number: 1
		 *Step Name: Step 1: Remove User from Shared list
		 *Step Description: 
			- Click on wheel icon of existing Shared calendar and select Share 
			- Selectuser/ group you want to remove and click Delete icon
			- Click Save
			- Login with user who is removed from shared list and goto Calendar
		 *Input Data: 

		 *Expected Outcome: 
			- He/ she cannot view shared calendar in shared list*/ 
		hp.goToCalendarPage();
		cMang.goToMenuFromMainCalendar(menuOfMainCalendar.ADDCAL);
		cMang.inputDataInDetailTabCalendarForm(calendarName, calendarName, calendarColor);
		cMang.saveAddCalendar();
		waitForAndGetElement(cMang.ELEMENT_CALENDAR_LIST_ITEM.replace("$calendar", calendarName));
		cMang.shareCalendar(calendarName, groupShare, edit , 1);

		magAc.signOut();
		magAc.signIn(DATA_USER2, DATA_PASS);
		hp.goToCalendarPage();
		waitForAndGetElement(cMang.ELEMENT_CALENDAR_LIST_ITEM.replace("$calendar", calendarName));

		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		hp.goToCalendarPage();
		cMang.removeUserGrooupFromShareCalendar(calendarName, groupShare);

		magAc.signOut();
		magAc.signIn(DATA_USER2, DATA_PASS);
		hp.goToCalendarPage();
		waitForElementNotPresent(cMang.ELEMENT_CALENDAR_LIST_ITEM.replace("$calendar", calendarName));

		info("Clear data");
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		hp.goToCalendarPage();
		cMang.deleteCalendar(calendarName,true);
	}

	/**
	 * Case ID:115686.
	 * Test Case Name: Check mini calendar.
	 * Pre-Condition: 
	 * Post-Condition: 
	 */
	@Test
	public  void test11_CheckMiniCalendar() {
		String titleTask = txData.getContentByArrayTypeRandom(1)+"115686";
		String contentTask = txData.getContentByArrayTypeRandom(1)+"115686";

		info("Test 11: Check mini calendar");
		/*Step Number: 1
		 *Step Name: Step 1:Check highlight in the mini calendar
		 *Step Description: 
			- Add event/task in
			- Take a look at mini calendar
		 *Input Data: 

		 *Expected Outcome: 
			- The date having event/task is highlighted with a bold effect
			- The current week is highlighted with a gray color
			- The current day is highlighted with a square*/
		hp.goToCalendarPage();
		task.goToAddTaskFromActionBar();
		task.inputDataTaskInQuickForm(titleTask, contentTask, getDate(1,"MM/dd/yyyy"), getDate(1,"MM/dd/yyyy"),false);
		task.saveQuickAddTask();
		cHome.verifyIsPresentEventTask(titleTask, selectViewOption.MONTH, selectDayOption.ONEDAY);

		/*Step number: 2
		 *Step Name: Step 2:Check highlight in the mini calendar
		 *Step Description: 
			- Click on any day
		 *Input Data: 

		 *Expected Outcome: 
			- The day is selected with a blue effect on the mini
			-calendar
			- All events and tasks of that day are shown in Calendar View pane (it keep the view of the users)*/
		List <WebElement> highLight = driver.findElements(By.xpath("//td[@class='highLight']"));
		for(WebElement we:highLight){
			boolean verify = we.getText().equals(getDate(1,"dd")) || we.getText().equals(getDate(2,"dd"));
			assert verify;
		}

		info("clear data");
		cHome.deleteEventTask(titleTask, selectViewOption.MONTH, selectDayOption.ONEDAY,null);
	}

	/**
	 * Case ID:115693.
	 * Test Case Name: Export calendar.
	 * Case ID:115692.
	 * Test Case Name: Import calendar.
	 * Pre-Condition: 
	 * Post-Condition: 
	 */
	@Test
	public  void test12_13_ExportImportCalendar() {
		String taskName= txData.getContentByArrayTypeRandom(1)+"115693";
		String attachment=getRandomNumber()+".ics";
		String calendarName= txData.getContentByArrayTypeRandom(1)+"115693";
		String calendarColor = "purple";
		info("Create data test");
		hp.goToCalendarPage();
		cMang.goToMenuFromMainCalendar(menuOfMainCalendar.ADDCAL);
		cMang.inputDataInDetailTabCalendarForm(calendarName, calendarName, calendarColor);
		cMang.saveAddCalendar();
		waitForAndGetElement(cMang.ELEMENT_CALENDAR_LIST_ITEM.replace("$calendar", calendarName));
		cMang.executeActionCalendar(calendarName, menuOfCalendarOption.ADDTASK);
		task.inputDataTaskInQuickForm(taskName, taskName, getDate(0,"MM/dd/yyyy"), getDate(0,"MM/dd/yyyy"),false);
		task.saveQuickAddTask();
		cHome.verifyIsPresentEventTask(taskName, selectViewOption.LIST, selectDayOption.ONEDAY);

		info("Test 12 Export calendar");
		/*Step Number: 1
		 *Step Name: -
		 *Step Description: 
			Step 1: Export Calendar
		 *Input Data: 
			- Mouse over then click on wheel icon of an existing calendar and select Export
			- Click Save
		 *Expected Outcome: 
			Export successfully with .ics file*/ 
		cMang.exportCalendar(calendarName,attachment);
		cMang.deleteCalendar(calendarName,true);

		info("Check the task is not present");
		cHome.verifyIsNotPresentEventTask(taskName, selectViewOption.LIST, selectDayOption.ONEDAY);

		info("Test 13: Import calendar");
		/*Step Number: 1
		 *Step Name: -
		 *Step Description: 
			Step 1: Import Calendar
		 *Input Data: 
			- Click on Calendar actions and select Import
			- Select format 
			- Upload file
			- Choose calendar
			- Click Save
		 *Expected Outcome: 
			Import successfully with:
			- Valid name
			- All events/task in the imported file*/ 
		cMang.importCalendar("TestData/TestOutput/" + attachment,calendarName,null,null);

		info("Check the task is present ");
		driver.navigate().refresh();
		cHome.verifyIsPresentEventTask(taskName, selectViewOption.LIST, selectDayOption.ONEDAY);

		info("Delete file and task");
		deleteFile("TestOutput/" + attachment);
		cHome.deleteEventTask(taskName, selectViewOption.LIST, selectDayOption.ONEDAY,null);
	}
}
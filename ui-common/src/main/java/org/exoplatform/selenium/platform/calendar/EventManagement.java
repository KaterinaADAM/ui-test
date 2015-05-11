package org.exoplatform.selenium.platform.calendar;

import static org.exoplatform.selenium.TestLogger.info;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.PlatformPermission;
import org.exoplatform.selenium.platform.calendar.CalendarHomePage.selectDayOption;
import org.exoplatform.selenium.platform.calendar.CalendarHomePage.selectViewOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EventManagement extends PlatformBase {

	//------------------------------------Add Quick EVENT Form--------------------------------------\\
	public By ELEMENT_QUICK_ADD_EVENT_POPUP = By.id("UIQuickAddEventPopupWindow");
	public By ELEMENT_QUICK_INPUT_EVENT_NAME = By.xpath("//*[@id='UIQuickAddEvent']//*[@id='eventName']");
	public By ELEMENT_QUICK_INPUT_EVENT_NOTE = By.xpath("//*[@id='UIQuickAddEvent']//*[@id='description']");
	public By ELEMENT_QUICK_INPUT_EVENT_CALENDAR = By.xpath("//*[@id='UIQuickAddEvent']//*[@name='calendar']");
	public By ELEMENT_QUICK_INPUT_EVENT_CATEGORY = By.xpath("//*[@id='UIQuickAddEvent']//*[@name='category']");
	public By ELEMENT_QUICK_CHECKBOX_EVENT_ALLDAY = By.xpath("//*[@id='UIQuickAddEvent']//*[@name='allDay']");
	public By ELEMENT_QUICK_INPUT_EVENT_FROM_DATE = By.xpath("//*[@id='UIQuickAddEvent']//*[@name='from']");
	public By ELEMENT_QUICK_INPUT_EVENT_TO_DATE = By.xpath("//*[@id='UIQuickAddEvent']//*[@name='to']");
	public By ELEMENT_QUICK_INPUT_EVENT_FROM_TIME = By.xpath("//*[@id='UIQuickAddEvent']//*[@name='fromTime']");
	public By ELEMENT_QUICK_INPUT_EVENT_TO_TIME = By.xpath("//*[@id='UIQuickAddEvent']//*[@name='toTime']");
	public By ELEMENT_QUICK_INPUT_EVENT_FROM_TIME_INPUT = By.xpath("//*[@id='UIQuickAddEvent']//*[@id='fromTime']/..//*[@class='UIComboboxInput']");
	public By ELEMENT_QUICK_INPUT_EVENT_TO_TIME_INPUT = By.xpath("//*[@id='UIQuickAddEvent']//*[@id='toTime']/..//*[@class='UIComboboxInput']");
	public String ELEMENT_QUICK_EVENT_SELECT_TO_TIME = "//*[@id='UIQuickAddEvent']//*[@id='toTime']/..//*[@class='UIComboboxLabel' and text()='${time}']";
	public String ELEMENT_QUICK_EVENT_SELECT_FROM_TIME = "//*[@id='UIQuickAddEvent']//*[@id='fromTime']/..//*[@class='UIComboboxLabel' and text()='${time}']";
	public By ELEMENT_BUTTON_EVENT_SAVE = By.xpath("//*[@id='UIQuickAddEventPopupWindow']//*[text()='Save']");
	public String ELEMENT_ITEM_QUICK_EVENT_CATEGORY_OPTION="//*[@id='UIQuickAddEventPopupWindow']//*[@name='category']/*[text()='$category']";

	//----------------------------------Add EVENT Form (more details )------------------------------------\\
	public By ELEMENT_ADD_EDIT_EVENT_NAME = By.xpath("//*[@id='UIEventForm']//*[@name='eventName']");
	public By ELEMENT_ADD_EDIT_EVENT_NOTE = By.xpath("//*[@id='UIEventForm']//*[@id='description']");
	public By ELEMENT_ADD_EDIT_EVENT_LOCATION=By.xpath("//*[@id='UIEventForm']//*[@id='place']");
	public By ELEMENT_ADD_EDIT_EVENT_CALENDAR = By.xpath("//*[@id='UIEventForm']//*[@name='calendar']");
	public By ELEMENT_ADD_EDIT_EVENT_CATEGORY = By.xpath("//*[@id='UIEventForm']//*[@name='category']");
	public By ELEMENT_ADD_EDIT_EVENT_PRIORITY = By.xpath("//*[@id='UIEventForm']//*[@name='priority']");
	public By ELEMENT_ADD_EDIT_EVENT_ALLDAY = By.xpath("//*[@id='UIEventForm']//*[@name='allDay']");
	public By ELEMENT_ADD_EDIT_EVENT_FROM_DATE = By.xpath("//*[@id='UIEventForm']//*[@name='from']");
	public By ELEMENT_ADD_EDIT_EVENT_TO_DATE = By.xpath("//*[@id='UIEventForm']//*[@name='to']");
	public By ELEMENT_ADD_EDIT_INPUT_EVENT_FROM_TIME = By.xpath("//*[@id='UIEventForm']//*[@name='fromTime']");
	public By ELEMENT_ADD_EDIT_INPUT_EVENT_TO_TIME = By.xpath("//*[@id='UIEventForm']//*[@name='toTime']");
	public By ELEMENT_ADD_EDIT_EVENT_FROM_TIME_INPUT = By.xpath("//*[@id='UIEventForm']//*[@id='fromTime']/..//*[@class='UIComboboxInput']");
	public By ELEMENT_ADD_EDIT_EVENT_TO_TIME_INPUT = By.xpath("//*[@id='UIEventForm']//*[@id='toTime']/..//*[@class='UIComboboxInput']");
	public String ELEMENT_ADD_EDIT_EVENT_SELECT_FROM_TIME = "//*[@id='UIEventForm']//*[@id='fromTime']/..//*[@class='UIComboboxLabel' and text()='${time}']";
	public String ELEMENT_ADD_EDIT_EVENT_SELECT_TO_TIME = "//*[@id='UIEventForm']//*[@id='toTime']/..//*[@class='UIComboboxLabel' and text()='${time}']";
	public By ELEMENT_ADD_EDIT_EVENT_REPEAT_CHECKBOX=By.id("isRepeat");
	public By ELEMENT_BUTTON_EVENT_MORE_DETAILS = By.xpath("//*[@id='UIQuickAddEventPopupWindow']//*[text()='More Details']");
	public By ELEMENT_BUTTON_EVENT_QUICK_CANCEL = By.xpath("//*[@id='UIQuickAddEventPopupWindow']//*[text()='Cancel']");
	public By ELEMENT_BUTTON_EVENT_CANCEL_DETAILS = By.xpath("//*[ @id='UIEventForm']//*[text()='Cancel']");
	public By ELEMENT_BUTTON_EVENT_SAVE_DETAILS = By.xpath("//*[@id='UIEventForm']//*[text()='Save']");
	public String ELEMENT_ATTACH_FILE_NAME = "//*[@data-original-title='$fileName']";
	public By ELEMENT_EVENT_FILE_INPUT = By.xpath("//*[@id='upload']//*[@name='file']");
	public By ELEMENT_SELECT_FILE_BUTTON=By.xpath("//*[@class='uploadButton']/*[@class='btn']");

	public By ELEMENT_EVENT_REMINDER_TAB = By.xpath("//*[text()='Reminders']");
	public By ELEMENT_EVENT_PARTICIPANTS_TAB = By.xpath("//*[text()='Participants']");
	public By ELEMENT_EVENT_SCHEDULE_TAB = By.xpath("//*[text()='Schedule']");
	public By ELEMENT_EVENT_DETAILS_TAB = By.xpath("//*[text()='Details']");

	//Attach file form
	public By ELEMENT_ATTACH_SAVE_BUTTON = By.xpath("//form[@id='UIAttachFileForm']//*[text()='Save']");
	public By ELEMENT_EVENT_ADD_ATTACHMENT = By.xpath("//button[contains(@onclick,'AddAttachment')]");
	public String ELEMENT_EVENT_ATTACHMENT = "//*[@id='UIEventForm']/..//a[@data-original-title='${file}']";
	public By ELEMENT_ATTACHMENT_SAVE_BUTTON = By.xpath("//*[@id='UIAttachFileForm']//*[text()='Save']");
	public String ELEMENT_ATTACHMENT_FORM_FILE_NAME = "//*[@class='fileNameLabel' and text()='$fileName']";

	//Schedule tab
	public final By ELEMENT_ADD_PARTICIPANTS_BUTTON_IN_SCHEDULE_TAB = By.xpath("//*[@id='UIEventForm']//*[@class='uiIconCalInviteUser uiIconLightGray']");
	public final String ELEMENT_USER_CHECKBOX_FULLNAME = "//*[contains(text(),'${user}')]/../..//*[@type='checkbox']";
	public final String ELEMENT_USER_CHECKBOX_USERNAME = "//*[@name='${user}']";
	public final String ELEMENT_SCHEDULE_BUSY_TIME = "//*[@id='${user}']/../../../../..//td[${index}]";
	public final String ELEMENT_SCHEDULE_TIME = "//*[@id='RowContainerDay']/../../../..//tr[2]//td[${index}]";
	public final By ELEMENT_SCHEDULE_NEXT_DAY=By.xpath("//*[@title='Next Day' or @data-original-title='Next Day']");
	public final By ELEMENT_SCHEDULE_PREVIOUS_DAY=By.xpath("//*[@title='Previous Day' or @data-original-title='Previous Day']");
	public final String ELEMENT_SCHEDULE_DRAG = "//td[${index}]//span[@data-original-title=\"Drag here to change your event's start and end times\" or @title=\"Drag here to change your event's start and end times\"]";
	public final String ELEMENT_SCHEDULE_SELECTED_DATE="//*[@id='RowContainerDay' and @datevalue='$date']";

	//Participant tab
	public final By ELEMENT_PRIVACY_PUBLIC_CHECKBOX=By.xpath("//*[@value='public']");
	public final By ELEMENT_PRIVACY_PRIVATE_CHECKBOX=By.xpath("//*[@value='private']");
	public final By ELEMENT_AVAILABLE_CHECKBOX=By.xpath("//*[@value='available']");
	public final By ELEMENT_BUSY_CHECKBOX=By.xpath("//*[@value='busy']");
	public final By ELEMENT_OUTSIDE_CHECKBOX=By.xpath("//*[@value='outside']");
	public final By ELEMENT_SEND_INVITATION_NEVER_CHECKBOX=By.xpath("//*[@value='never']");
	public final By ELEMENT_SEND_INVITATION_ALWAYS_CHECKBOX=By.xpath("//*[@value='always']");
	public final By ELEMENT_SEND_INVITATION_ASK_CHECKBOX=By.xpath("//*[@value='ask']");
	public final By ELEMENT_ADD_PARTICIPANTS_BUTTON_IN_PARTICIPANT_TAB = By.xpath("//*[@class='uiFormGrid']//*[@class='uiIconPlus uiIconLightGray']");
	public final By ELEMENT_INVITATION_PARTICITPANT_USER=By.xpath("//*[@id='eventShare-tab']//*[@data-original-title='Add Participant' or @title='Add Participant']");
	public final By ELEMENT_INVITATION_PARTICIPANT_TEXTBOX=By.id("participant");
	public final By ELEMENT_INVITATION_PARTICITPANT_MSG=By.id("invitation-msg");
	public final By ELEMENT_INVITATION_SELECT_USER_BUTTON=By.id("uiInvitationUser");
	public final By ELEMETN_INVITATION_SAVE_BUTTON=By.xpath("//*[@id='UIInvitationContainer']//*[text()='Save']");
	public final By ELEMETN_INVITATION_CANCEL_BUTTON=By.xpath("//*[@id='UIInvitationContainer']//*[text()='Cancel']");

	//Reminder tab
	public final By ELEMENT_REMINDER_BY_POPUP=By.id("popupReminder");
	public final By ELEMENT_REMINDER_BY_MAIL=By.id("mailReminder");

	/*Recurring event form*/
	public By ELEMENT_RECURRING_TYPE_SELECT_BOX = By.xpath("//*[@name='repeatType']");
	public By ELEMENT_INTERVAL_SELECT_BOX = By.xpath("//*[@name='interval']");
	public By ELEMENT_END_AFTER_NUMBER = By.id("endAfterNumber");
	public By ELEMENT_NEVER_END_RECURRING_EVENT = By.id("endNever");
	public By ELEMENT_AFTER_END_RECURRING_EVENT = By.id("endAfter");
	public By ELEMENT_BY_THIS_DATE_END_RECURRING_EVENT = By.id("endByDate");
	public By ELEMENT_DATE_TIME_PICKER = By.xpath("//*[contains(@id, 'DateTimePicker')]");
	public By ELEMENT_IS_REPEAT_CHECKBOX = By.id("isRepeat");
	public By ELEMENT_SAVE_EVENT_OCCURRING = By.xpath("//*[@id='UIRepeatEventForm']//*[contains(text(),'Save')]");

	/*Delete recurring event form*/
	public By ELEMENT_DELETE_RECURRING_EVENT_FORM = By.id("UICalendarPopupWindow");
	public By ELEMENT_EDIT_DELETE_ONE_EVENT = By.xpath("//*[@value='save_one']");
	public By ELEMENT_EDIT_DELETE_FOLLOWING_EVENT = By.xpath("//*[@value='save_follow']");
	public By ELEMENT_EDIT_DELETE_ALL_EVENT = By.xpath("//*[@value='save_all']");
	public By ELEMENT_CONFIRM_DELETE_BUTTON = By.xpath("//*[@id='UIConfirmFormDelete']//*[text()='Delete']");
	public By ELEMENT_CONFIRM_CANCEL_BUTTON = By.xpath("//*[@id='UIConfirmFormDelete']//*[text()='Cancel']");
	public String ELEMENT_CONFIRM_DELETE_MESSAGE = "Would you like to delete only this event, all events in the series, or this and all following events in the series?";
	public By ELEMENT_CONFIRM_EDIT_DELETE_RECURRING_EVENT = By.xpath("//*[@class='media-body']");

	/*Delete recurring event*/
	public By ELEMENT_CONFIRM_EDIT_BUTTON = By.xpath("//*[@id='UIConfirmFormUpdate']//*[text()='Save']");
	public By ELEMENT_CONFIRM_EDIT_RECURRING_FORM = By.id("UICalendarChildPopupWindow");
	public String ELEMENT_CONFIRM_EDIT_MESSAGE = "Would you like to change only this event, all events in the series, or this and all following events in the series?";

	/*Content recurring*/
	public By ELEMENT_TITLE_RECURRING_EVENT = By.xpath("//*[@class='popover-content']/*[@class='title clearfix']/*[@class='text']");
	public By ELEMENT_DATE_TIME_RECURRING_EVENT = By.xpath("//*[@class='popover-content']/*[@class='time clearfix']//*[@class='uiIconCalClockMini']/../../*[@class='text']");
	public By ELEMENT_RECURRING_TEXT_RECURRING_EVENT = By.xpath("//*[@class='popover-content']/*[@class='time clearfix']//*[@class='uiIconCalRecurring']/../../*[@class='text']");
	public By ELEMENT_EDITED_RECURRING_TEXT_RECURRING_EVENT = By.xpath("//*[@class='popover-content']/*[@class='time clearfix']//*[@class='uiIconCalEditRecurring']/../../*[@class='text']");
	public By ELEMENT_DESCRIPTION_EVENT = By.xpath("//*[@class='popover-content']/*[@class='description']");

	PlatformPermission pPer;
	CalendarHomePage cHome;
	ManageAlert alert;
	public EventManagement(WebDriver dr){
		driver = dr;
		pPer = new PlatformPermission(driver);
		cHome = new CalendarHomePage(driver);
		alert = new ManageAlert(driver);
	}


	/**
	 * 
	 * @param date
	 * 				date to create event
	 * 				format: MM/dd/yyyy (Ex: 12/09/2014)
	 * @param time
	 * 				time to create event
	 * 				format: hh:mm (Ex: 12:30)
	 */
	public void goToAddEventByLeftClickFromMainPanel(String date, String time){
		info("Go to add task by right clicking from main panel");
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("MMM dd yyyy");
		String tempDate2 = getCurrentDate("MMM dd yyyy");
		Date tempDate1 = null;
		String tempTime = getCurrentDate("HH")+":00";

		info("Get date");
		if(date!=null && date!=""){
			try {
				tempDate1 = format1.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			tempDate2 = format2.format(tempDate1);
			info("Selected date is " + tempDate2);
		}
		else{
			tempDate2 = getCurrentDate("MMM dd yyyy");
			info("Selected date is current date" + tempDate2);
		}

		info("Get time");
		if(time!=null && time!=""){
			tempTime = time;
			info("Selected date is " + tempTime);
		}
		else{
			tempTime = getCurrentDate("HH")+":00";
			info("Selected date is current date" + tempTime);
		}
		String cell = cHome.ELEMENT_CELL_TO_WORKING_PANEL.replace("$date", tempDate2).replace("$time", tempTime);
		info(cell);
		waitForAndGetElement(cell).click();
		waitForAndGetElement(ELEMENT_QUICK_ADD_EVENT_POPUP);
	}

	/**
	 * 
	 * @param date
	 * 				date to create event
	 * 				format: MM/dd/yyyy (Ex: 12/09/2014)
	 * @param time
	 * 				time to create event
	 * 				format: hh:mm (Ex: 12:30)
	 */
	public void goToAddEventByRightClickFromMainPanel(String date, String time){
		info("Go to add task by right clicking from main panel");
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("MMM dd yyyy");
		String tempDate2 = getCurrentDate("MMM dd yyyy");
		Date tempDate1 = null;
		String tempTime = getCurrentDate("HH")+":00";

		info("Get date");
		if(date!=null && date!=""){
			try {
				tempDate1 = format1.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			tempDate2 = format2.format(tempDate1);
			info("Selected date is " + tempDate2);
		}
		else{
			tempDate2 = getCurrentDate("MMM dd yyyy");
			info("Selected date is current date" + tempDate2);
		}

		info("Get time");
		if(time!=null && time!=""){
			tempTime = time;
			info("Selected date is " + tempTime);
		}
		else{
			tempTime = getCurrentDate("HH")+":00";
			info("Selected date is current date" + tempTime);
		}

		String cell = cHome.ELEMENT_CELL_TO_WORKING_PANEL.replace("$date", tempDate2).replace("$time", tempTime);
		rightClickOnElement(cell);
		click(cHome.ELEMENT_RIGHT_CLICK_ADD_EVENT);
		waitForAndGetElement(ELEMENT_QUICK_ADD_EVENT_POPUP);
	}

	/**
	 * Open "Add new event" form from action bar
	 * 
	 */
	public void goToAddEventFromActionBar(){
		info("Go to Add Task page from action bar"); 
		click(cHome.ELEMENT_BUTTON_EVENT);
		waitForAndGetElement(ELEMENT_QUICK_ADD_EVENT_POPUP);
	}

	/**
	 * Input into basic fields of Quick EVENT form
	 * 
	 * @param name
	 * 			name of a EVENT
	 * @param note
	 * 			note of a EVENT 
	 * @param opt
	 * 			optional parameter
	 * 			opt[0]: calendar
	 * 			opt[1]: category
	 */
	public void inputBasicQuickEvent(String name, String note, String...opt){
		info("Input into basic fields of Quick EVENT form");
		if (name != null){
			type(ELEMENT_QUICK_INPUT_EVENT_NAME, name, true);
		}
		if (note != null){
			type(ELEMENT_QUICK_INPUT_EVENT_NOTE, note, true);
		}
		if (opt.length > 0 && opt[0] != null){
			select(ELEMENT_QUICK_INPUT_EVENT_CALENDAR, opt[0]);
		}
		if (opt.length > 1 && opt[1] != null){
			select(ELEMENT_QUICK_INPUT_EVENT_CATEGORY, opt[1]);
		}
		Utils.pause(2000);
	}

	/**
	 * Input into basic fields of EVENT detail form
	 * 
	 * @param name
	 * 			name of a EVENT
	 * @param note
	 * 			note of a EVENT 
	 * @param opt
	 * 			optional parameter
	 * 			opt[0]: calendar
	 * 			opt[1]: category
	 * 			opt[2]: location
	 */
	public void inputBasicDetailEvent(String name, String note, String...opt){
		info("Input into basic fields of Quick EVENT form");
		if (name != null){
			type(ELEMENT_ADD_EDIT_EVENT_NAME, name, true);
		}
		if (note != null){
			type(ELEMENT_ADD_EDIT_EVENT_NOTE, note, true);
		}
		if (opt.length > 0 && opt[0] != null){
			select(ELEMENT_ADD_EDIT_EVENT_CALENDAR, opt[0]);
		}
		if (opt.length > 1 && opt[1] != null){
			select(ELEMENT_ADD_EDIT_EVENT_CATEGORY, opt[1]);
		}
		if (opt.length > 2 && opt[2] != null){
			type(ELEMENT_ADD_EDIT_EVENT_LOCATION, opt[2], true);
		}
	}

	/**
	 * Input into "From, To" and check/uncheck allday checkbox fields of a EVENT in quick form
	 * 
	 * @param from
	 * 			From date, time of a EVENT i.e.: 11/06/2013 14:00
	 * @param to
	 * 			To date, time of a EVENT, i.e.: 11/06/2013 14:00
	 * @param allDay
	 * 			Option "all day" of a EVENT
	 */
	public void inputFromToQuickEvent(String from, String to, boolean allDay){
		info("Input into From, To and check/uncheck allday checkbox fields of a EVENT");
		if(allDay){
			info("Check all day, then select date");
			check(ELEMENT_QUICK_CHECKBOX_EVENT_ALLDAY,2);
			if ((from != null) & (from != ""))
				type(ELEMENT_QUICK_INPUT_EVENT_FROM_DATE, from, true);
			if ((to != null) & (to != ""))
				type(ELEMENT_QUICK_INPUT_EVENT_TO_DATE, to, true);

		}else {
			info("Uncheck all day, then select date time");
			uncheck(ELEMENT_QUICK_CHECKBOX_EVENT_ALLDAY,2);
			if ((from != null) & (from != "")){
				String[] dateTimeFrom = from.split(" ");
				if(dateTimeFrom.length > 0)
					type(ELEMENT_QUICK_INPUT_EVENT_FROM_DATE, dateTimeFrom[0], true);
				if(dateTimeFrom.length > 1){
					click(ELEMENT_QUICK_INPUT_EVENT_FROM_TIME_INPUT, 2);
					click(ELEMENT_QUICK_EVENT_SELECT_FROM_TIME.replace("${time}", dateTimeFrom[1]));
				}
			}
			if ((to != null) & (to != "")){
				String[] dateTimeTo = to.split(" ");
				if(dateTimeTo.length > 0)
					type(ELEMENT_QUICK_INPUT_EVENT_TO_DATE, dateTimeTo[0], true);
				if(dateTimeTo.length > 1){
					click(ELEMENT_QUICK_INPUT_EVENT_TO_TIME_INPUT, 2);
					click(ELEMENT_QUICK_EVENT_SELECT_TO_TIME.replace("${time}", dateTimeTo[1]));
				}
			}
		}
	}

	/**
	 * Input into "From, To" and check/uncheck allday checkbox fields of a EVENT in detail form
	 * 
	 * @param from
	 * 			From date, time of a EVENT i.e.: 11/06/2013 14:00
	 * @param to
	 * 			To date, time of a EVENT, i.e.: 11/06/2013 14:00
	 * @param allDay
	 * 			Option "all day" of a EVENT
	 */
	public void inputFromToDetailEvent(String from, String to, boolean allDay){
		info("Input into From, To and check/uncheck allday checkbox fields of a EVENT");
		if(allDay){
			info("Check all day, then select date");
			check(ELEMENT_QUICK_CHECKBOX_EVENT_ALLDAY,2);
			if ((from != null) & (from != ""))
				type(ELEMENT_QUICK_INPUT_EVENT_FROM_DATE, from, true);
			if ((to != null) & (to != ""))
				type(ELEMENT_QUICK_INPUT_EVENT_TO_DATE, to, true);

		}else {
			info("Uncheck all day, then select date time");
			uncheck(ELEMENT_ADD_EDIT_EVENT_ALLDAY,2);
			if ((from != null) & (from != "")){
				String[] dateTimeFrom = from.split(" ");
				if(dateTimeFrom.length > 0)
					type(ELEMENT_ADD_EDIT_EVENT_FROM_DATE, dateTimeFrom[0], true);
				if(dateTimeFrom.length > 1){
					click(ELEMENT_ADD_EDIT_EVENT_FROM_TIME_INPUT, 2);
					click(ELEMENT_ADD_EDIT_EVENT_SELECT_FROM_TIME.replace("${time}", dateTimeFrom[1]));
				}
			}
			if ((to != null) & (to != "")){
				String[] dateTimeTo = to.split(" ");
				if(dateTimeTo.length > 0)
					type(ELEMENT_ADD_EDIT_EVENT_TO_DATE, dateTimeTo[0], true);
				if(dateTimeTo.length > 1){
					click(ELEMENT_ADD_EDIT_EVENT_TO_TIME_INPUT, 2);
					click(ELEMENT_ADD_EDIT_EVENT_SELECT_TO_TIME.replace("${time}", dateTimeTo[1]));
				}
			}
		}
	}

	/**
	 * Input into basic fields of Quick EVENT form
	 * @param name
	 * @param from
	 * 			From date, time of a EVENT i.e.: 11/06/2013 14:00
	 * @param to
	 * 			To date, time of a EVENT, i.e.: 11/06/2013 14:00
	 * @param allDay
	 * @param opt
	 */
	public void inputDataEventInQuickForm(String name, String note, String from, String to, boolean allDay, String...opt){
		inputBasicQuickEvent(name, note, opt);
		inputFromToQuickEvent(from, to, allDay);
	}

	/**
	 * Input into basic fields of detail EVENT form
	 * @param name
	 * @param note
	 * @param from
	 * 			From date, time of a EVENT i.e.: 11/06/2013 14:00
	 * @param to
	 * 			To date, time of a EVENT, i.e.: 11/06/2013 14:00
	 * @param allDay
	 * @param opt
	 */
	public void inputDataEventInDetailForm(String name, String note, String from, String to, boolean allDay, String...opt){
		inputBasicDetailEvent(name, note, opt);
		inputFromToDetailEvent(from, to, allDay);
	}

	/**
	 * Attach file in "Add new EVENT" form
	 * 
	 * @param path
	 * 				path of attachment of a EVENT
	 */
	public void attachFileToEvent(String path){
		String fullPath="";
		if ("win".equals(server)){
			fullPath="TestData\\" + path;
		}
		else{
			fullPath="TestData/" + path;
		}
		click(ELEMENT_EVENT_ADD_ATTACHMENT);
		click(ELEMENT_SELECT_FILE_BUTTON);
		uploadFileUsingRobot(fullPath);
		waitForAndGetElement(ELEMENT_ATTACHMENT_FORM_FILE_NAME.replace("$fileName", path));
		click(ELEMENT_ATTACHMENT_SAVE_BUTTON,0,true);
		waitForAndGetElement(ELEMENT_ATTACH_FILE_NAME.replace("$fileName", path));
	}	

	/**
	 * Check default suggestion EVENT time in detail add form
	 * @param fromDateTime (Format: MM/dd/yyyy HH:mm)
	 * @param toDateTime (Format: MM/dd/yyyy HH:mm)
	 * @param duration	 
	 */
	public void checkSuggestionEventTimeInQuickForm(String fromDateTime, String toDateTime, int duration){
		info("Check date is current date");
		DateFormat formatterTimeTemp = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		DateFormat formatterTime = new SimpleDateFormat("HH:mm");
		DateFormat formatterDate = new SimpleDateFormat("MM/dd/yyyy");
		String dateFrom = getValue(ELEMENT_QUICK_INPUT_EVENT_FROM_DATE);
		String dateTo = getValue(ELEMENT_QUICK_INPUT_EVENT_TO_DATE);
		String fromTime = waitForAndGetElement(ELEMENT_QUICK_INPUT_EVENT_FROM_TIME, DEFAULT_TIMEOUT, 1, 2).getAttribute("value");
		String toTime = waitForAndGetElement(ELEMENT_QUICK_INPUT_EVENT_TO_TIME, DEFAULT_TIMEOUT, 1, 2).getAttribute("value");

		info("Check default suggestion EVENT time");
		if (fromDateTime == null || fromDateTime == ""){
			info("Check time suggestion default");
			assert dateFrom.equals(getCurrentDate("MM/dd/yyyy"));
		}
		else{
			info("Check suggesion when select from time");
			try {
				Date fr = formatterDate.parse(fromDateTime);
				Date frTime = formatterTimeTemp.parse(fromDateTime);
				assert dateFrom.equals(formatterDate.format(fr));
				assert fromTime.equals(formatterTime.format(frTime));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (toDateTime == null || toDateTime == ""){
			info("Check time suggestion default");
			assert dateTo.equals(getCurrentDate("MM/dd/yyyy"));
		}
		else {
			info("Check suggesion when select to time");
			try {
				Date to = formatterDate.parse(toDateTime);
				Date tTime = formatterTimeTemp.parse(toDateTime);
				assert dateTo.equals(formatterDate.format(to));
				assert toTime.equals(formatterTime.format(tTime));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		info("Check duration");
		try {
			Date fr = formatterTime.parse(fromTime);
			Date to = formatterTime.parse(toTime);
			long diff = (to.getTime() - fr.getTime())/60000;
			info("Duration is " + diff + " minus");
			assert duration == (int) diff;
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Check default suggestion EVENT time in detail add form
	 * @param fromDateTime (Format: MM/dd/yyyy HH:mm)
	 * @param toDateTime (Format: MM/dd/yyyy HH:mm)
	 * @param duration	 
	 */
	public void checkSuggestionEventTimeInDetailForm(String fromDateTime, String toDateTime, int duration){
		info("Check date is current date");
		DateFormat formatterTimeTemp = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		DateFormat formatterTime = new SimpleDateFormat("HH:mm");
		DateFormat formatterDate = new SimpleDateFormat("MM/dd/yyyy");
		String dateFrom = getValue(ELEMENT_ADD_EDIT_EVENT_FROM_DATE);
		String dateTo = getValue(ELEMENT_ADD_EDIT_EVENT_TO_DATE);
		String fromTime = waitForAndGetElement(ELEMENT_ADD_EDIT_INPUT_EVENT_FROM_TIME, DEFAULT_TIMEOUT, 1, 2).getAttribute("value");
		String toTime = waitForAndGetElement(ELEMENT_ADD_EDIT_INPUT_EVENT_TO_TIME, DEFAULT_TIMEOUT, 1, 2).getAttribute("value");

		info("Check default suggestion EVENT time");
		if (fromDateTime == null || fromDateTime == ""){
			info("Check time suggestion default");
			assert dateFrom.equals(getCurrentDate("MM/dd/yyyy"));
		}
		else{
			info("Check suggesion when select from time");
			try {
				Date fr = formatterDate.parse(fromDateTime);
				Date frTime = formatterTimeTemp.parse(fromDateTime);
				assert dateFrom.equals(formatterDate.format(fr));
				assert fromTime.equals(formatterTime.format(frTime));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (toDateTime == null || toDateTime == ""){
			info("Check time suggestion default");
			assert dateTo.equals(getCurrentDate("MM/dd/yyyy"));
		}
		else {
			info("Check suggesion when select to time");
			try {
				Date to = formatterDate.parse(toDateTime);
				Date tTime = formatterTimeTemp.parse(toDateTime);
				assert dateTo.equals(formatterDate.format(to));
				assert toTime.equals(formatterTime.format(tTime));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		info("Check duration");
		try {
			Date fr = formatterTime.parse(fromTime);
			Date to = formatterTime.parse(toTime);
			long diff = (to.getTime() - fr.getTime())/60000;
			info("Duration is " + diff + " minus");
			assert duration == (int) diff;
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * add participants into schedule
	 * @param user
	 * @param type
	 */
	public void addParticipants(String user, int type){
		click(ELEMENT_ADD_PARTICIPANTS_BUTTON_IN_SCHEDULE_TAB);
		pPer.selectUserPermission(user, type);
		if(type!=2 && type!=3 && type!=4)
			waitForAndGetElement(ELEMENT_USER_CHECKBOX_USERNAME.replace("${user}", user),5000,1,2);
		else
			waitForAndGetElement(ELEMENT_USER_CHECKBOX_FULLNAME.replace("${user}", user),5000,1,2);
	}

	/**
	 * convertFromTimeToIndex
	 * @param time (ex: HH:mm)
	 */
	@SuppressWarnings("deprecation")
	public int convertFromTimeToIndex(String time){
		int index = 0;
		SimpleDateFormat hour = new SimpleDateFormat("HH:mm");
		try {
			Date tempHour = hour.parse(time);
			if(tempHour.getMinutes()==0)
				index=(Integer.valueOf(tempHour.getHours())*4)+2;
			else
				index=(Integer.valueOf(tempHour.getHours())*4)+3;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		info("Index is "+ String.valueOf(index));
		return index;
	}

	/**
	 * Check busy time of user
	 * @param user
	 * @param from (ex: HH:mm)
	 * @param to (ex: HH:mm)
	 */
	public void checkBusyTimeOfUser(String user, String from, String to){
		info("Check busy time of an user");
		int fromIndex = convertFromTimeToIndex(from);
		int toIndex = convertFromTimeToIndex(to);
		info("From index is " + String.valueOf(fromIndex));
		info("To index is " + String.valueOf(toIndex));
		for(int i = fromIndex; i<= toIndex; i++){
			assert waitForAndGetElement(ELEMENT_SCHEDULE_BUSY_TIME.replace("${user}", user).replace("${index}", String.valueOf(i))).getAttribute("class")
			.contains("busyDotTime"):"Wrong busy time";
		}
	}

	/**
	 * Check schedule time of user
	 * @param from (ex: MM/dd/yyyy HH:mm)
	 * @param to (ex: MM/dd/yyyy HH:mm)
	 */
	public void checkScheduleTimeOfUser(String from, String to){
		info("Check schedule time of an user");
		DateFormat formatterDateTime = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		DateFormat formatterDate = new SimpleDateFormat("MM/dd/yyyy");
		try {
			Date dateFrom = formatterDateTime.parse(from);
			Date dateTo = formatterDateTime.parse(to);
			Date date = formatterDate.parse(from);

			String dateS = formatterDate.format(date);
			@SuppressWarnings("deprecation")
			String timeFrom = String.valueOf(dateFrom.getHours())+":"+String.valueOf(dateFrom.getMinutes());
			@SuppressWarnings("deprecation")
			String timeTo = String.valueOf(dateTo.getHours())+":"+String.valueOf(dateTo.getMinutes());

			int fromIndex = convertFromTimeToIndex(timeFrom);
			int toIndex = convertFromTimeToIndex(timeTo);
			for(int i = fromIndex; i<= toIndex; i++){
				assert waitForAndGetElement(ELEMENT_SCHEDULE_TIME.replace("${index}", String.valueOf(i))).getAttribute("class")
				.contains("userSelection"):"Wrong schedule time";
			}
			waitForAndGetElement(ELEMENT_SCHEDULE_SELECTED_DATE.replace("$date", dateS));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * change time event from schedule tab
	 * @param option
	 * @param fromTime
	 * @param toTime
	 */
	public void changeTimeEventFromScheduleTab(selectArrowOption option, String fromTime, String toTime){
		info("Change time event in schedule tab");
		int fromIndex = convertFromTimeToIndex(fromTime);
		int toIndex = convertFromTimeToIndex(toTime);
		info("From is " + fromIndex);
		info("To is " + toIndex);
		switch(option){
		case NEXT:
			click(ELEMENT_SCHEDULE_NEXT_DAY);
			break;
		case PREVIOUS:
			click(ELEMENT_SCHEDULE_PREVIOUS_DAY);
			break;
		case NOW:
			break;
		default:
			break;
		}
		dragAndDropToObject(ELEMENT_SCHEDULE_DRAG.replace("${index}", String.valueOf(fromIndex)), ELEMENT_SCHEDULE_DRAG.replace("${index}", String.valueOf(toIndex)));
	}

	/**
	 * Select privacy
	 * @param isPublic
	 * 					true: check public checkbox
	 * 					false: check private checkbox
	 */
	public void selectPrivacyParticipant(boolean isPublic){
		if(isPublic){
			info("Select public privacy");
			check(ELEMENT_PRIVACY_PUBLIC_CHECKBOX,2);
		}
		else{
			info("Select private privacy");
			check(ELEMENT_PRIVACY_PRIVATE_CHECKBOX,2);
		}
	}
	/**
	 * Available option
	 */
	public enum selectAvailableOption{
		AVAILABLE, BUSY, OUTSIDE
	}

	/**
	 * selectAvailable on participant tab
	 * @param option
	 */
	public void selectAvailable(selectAvailableOption option){
		switch(option){
		case AVAILABLE:
			info("Select AVAILABLE");
			check(ELEMENT_AVAILABLE_CHECKBOX,2);
			break;
		case BUSY:
			info("Select BUSY");
			check(ELEMENT_BUSY_CHECKBOX,2);
			break;
		case OUTSIDE:
			info("Select OUTSIDE");
			check(ELEMENT_OUTSIDE_CHECKBOX,2);
			break;
		default:
			info("Select AVAILABLE");
			check(ELEMENT_AVAILABLE_CHECKBOX,2);
			break;
		}
	}

	/**
	 * selectSendInvitation
	 * @param option
	 */
	public void selectSendInvitation(selectInvitationOption option){
		switch(option){
		case ALWAYS:
			info("Select ALWAYS");
			check(ELEMENT_SEND_INVITATION_ALWAYS_CHECKBOX,2);
			break;
		case NEVER:
			info("Select NEVER");
			check(ELEMENT_SEND_INVITATION_NEVER_CHECKBOX,2);
			break;
		case ASK:
			info("Select ASK");
			check(ELEMENT_SEND_INVITATION_ASK_CHECKBOX,2);
			break;
		default:
			info("Select NEVER");
			check(ELEMENT_SEND_INVITATION_NEVER_CHECKBOX,2);
			break;
		}

	}

	/**
	 * select user participant
	 * @param users
	 * @param content
	 * @param type
	 */
	public void selectUserParticipants(String users, String content, int type){
		info("Select User Participant");
		if(type==1){
			String[] temp = users.split("/");
			for (int i = 0; i < temp.length; i ++){
				type(ELEMENT_INVITATION_PARTICIPANT_TEXTBOX,","+temp[i],false);
			}
		}
		else{
			pPer.selectUserPermission(user, 1);
		}
		if(content!=null && content!=""){
			type(ELEMENT_INVITATION_PARTICITPANT_MSG,content,true);
		}
	}
	/**
	 * Save add EVENT
	 */
	public void saveQuickAddEvent(){
		click(ELEMENT_BUTTON_EVENT_SAVE);
		waitForElementNotPresent(ELEMENT_BUTTON_EVENT_SAVE);
		Utils.pause(2000);
	}

	/**
	 * Save a EVENT with more details
	 */
	public void saveAddEventDetails(){
		click(ELEMENT_BUTTON_EVENT_SAVE_DETAILS);
		waitForElementNotPresent(ELEMENT_BUTTON_EVENT_SAVE_DETAILS);
		Utils.pause(500);
	}

	/**
	 * Click on more details
	 */
	public void moreDetailsEvent(){
		click(ELEMENT_BUTTON_EVENT_MORE_DETAILS);
		waitForElementNotPresent(ELEMENT_BUTTON_EVENT_MORE_DETAILS);
	}

	/**
	 * Click on cancel button
	 */
	public void cancelQuickAddEditEvent(){
		click(ELEMENT_BUTTON_EVENT_QUICK_CANCEL);
		waitForElementNotPresent(ELEMENT_BUTTON_EVENT_QUICK_CANCEL);
	}

	public void cancelAddEditDetailEvent(){
		click(ELEMENT_BUTTON_EVENT_CANCEL_DETAILS);
		waitForElementNotPresent(ELEMENT_BUTTON_EVENT_CANCEL_DETAILS);
	}

	/**
	 * Remove attachment
	 * @param file
	 * 				name of file
	 */
	public void removeAttachment(String file){
		click(ELEMENT_EVENT_ATTACHMENT.replace("${file}", "Remove"));
		waitForElementNotPresent(ELEMENT_EVENT_ATTACHMENT.replace("${file}", file));
	}
	/**
	 * Define a type of repeat 
	 * Daily
	 * Weekly
	 * Monthly
	 * Yearly
	 */
	public enum repeatType {
		Daily, Weekly, Monthly, Yearly;
	}

	/**
	 * Define a type of repeat on
	 * MO
	 * TU
	 * WE
	 * TH
	 * FR
	 * SA
	 * SU
	 */
	public enum repeatOn {
		MO, TU, WE, TH, FR, SA, SU;
	}

	/**
	 * Define a type of repeat 
	 * Never
	 * After
	 * ByThisDate
	 */
	public enum repeatEndType {
		Never, After, ByThisDate;
	}

	/**
	 * Define a type of delete recurring 
	 * Only this event
	 * Following events
	 * All events
	 */
	public enum recurringType {
		ONLY_EVENT, FOLLOW_EVENT, ALL_EVENT;
	}

	/**
	 * input recurring info event
	 * @param repeatType
	 * 					repeat type: Daily, Weekly, Monthly, Yearly;
	 * @param repeatInterval
	 * @param repeatOn
	 * @param endRepeat
	 * @param option
	 * 					occurrennumber if endRepeat.equals(repeatEndType.After)
	 * 					day format if endRepeat.equals(repeatEndType.ByThisDate) -- format: mm/dd/yyyy
	 */
	public void inputRecurringInfoEvent(repeatType repeatType, String repeatInterval, repeatOn[] repeatOn, repeatEndType endRepeat, String...option){
		info("Add recurring information");
		String occurence = option.length > 0 ? option[0]: "";
		if(repeatType!=null){
			select(ELEMENT_RECURRING_TYPE_SELECT_BOX,String.valueOf(repeatType));
		}
		if(repeatInterval!=null && repeatInterval!=""){
			select(ELEMENT_INTERVAL_SELECT_BOX,String.valueOf(repeatInterval));
		}
		if(repeatOn!=null){
			for(int i = 0; i<repeatOn.length; i++){
				check(By.id(String.valueOf(repeatOn[i])));
			}
		}
		if(endRepeat!=null){
			switch(endRepeat){
			case After:
				info("Check After option");
				check(ELEMENT_AFTER_END_RECURRING_EVENT,2);
				if(occurence!="")
					type(ELEMENT_END_AFTER_NUMBER,option[0],true);
				break;
			case ByThisDate:
				info("Check By this date option");
				check(ELEMENT_BY_THIS_DATE_END_RECURRING_EVENT,2);
				if(occurence!="")
					type(ELEMENT_DATE_TIME_PICKER,option[0],true);
				break;
			case Never:
				info("Check never option");
				check(ELEMENT_NEVER_END_RECURRING_EVENT,2);
				break;
			}
		}
	}

	/**
	 * Delete an event
	 * 
	 * @param name
	 * 				name of event or task
	 * @param view	
	 * 				view of calendar: day, week, list...
	 * @param optionDay
	 * 				ONEDAY or ALLDAY
	 * @param optDeleteType
	 * 				ONLY_EVENT, FOLLOW_EVENT or ALL_EVENT
	 * @param date
	 * 				date of event: format (MMM dd yyyy)
	 * @param opParams
	 * 				isVerify, if not be set, the event/task will be automatically set as verify delete confirm message
	 * 				= true: verify delete confirm message
	 * 				= false: not verify delete confirm message
	 */
	public void deleteRecurringEvent(String name, selectViewOption view, selectDayOption optionDay, recurringType optDeleteType, String date, Object... opParams){
		boolean isVerify = (Boolean) (opParams.length > 0 ? opParams[0]: false);
		info("Delete event/tak: " + name);
		cHome.goToRightMenuTaskEventFromAnyView(name,view,optionDay,date);
		click(cHome.ELEMENT_EVENT_TASK_DELETE_MENU);
		waitForAndGetElement(ELEMENT_DELETE_RECURRING_EVENT_FORM);
		if(isVerify){
			waitForAndGetElement(ELEMENT_CONFIRM_EDIT_DELETE_RECURRING_EVENT);
			waitForAndGetElement(ELEMENT_EDIT_DELETE_ONE_EVENT,DEFAULT_TIMEOUT,1,2);
			info(waitForAndGetElement(ELEMENT_CONFIRM_EDIT_DELETE_RECURRING_EVENT).getText());
			assert waitForAndGetElement(ELEMENT_CONFIRM_EDIT_DELETE_RECURRING_EVENT).getText().contains(ELEMENT_CONFIRM_DELETE_MESSAGE);
			assert waitForAndGetElement(ELEMENT_EDIT_DELETE_ONE_EVENT,DEFAULT_TIMEOUT,1,2).isSelected();
		}
		switch (optDeleteType) {
		case ONLY_EVENT:
			info("Delete only event recurring");
			check(ELEMENT_EDIT_DELETE_ONE_EVENT,2);
			break;
		case FOLLOW_EVENT:
			info("Delete following event recurring");
			check(ELEMENT_EDIT_DELETE_FOLLOWING_EVENT,2);
			break;
		case ALL_EVENT:
			info("Delete all event recurring");
			check(ELEMENT_EDIT_DELETE_ALL_EVENT,2);
			break;
		default: 
			info("Delete only event recurring");
			check(ELEMENT_EDIT_DELETE_ONE_EVENT,2);
			break;
		}
		click(ELEMENT_CONFIRM_DELETE_BUTTON);
		waitForElementNotPresent(ELEMENT_DELETE_RECURRING_EVENT_FORM);
	}


	/**
	 * edit recurring event
	 * @param optEditType
	 * 				type of edit recurring
	 * @param opParams
	 * 				isVerify, if not be set, the event/task will be automatically set as verify edit confirm message
	 * 				= true: verify edit confirm message
	 * 				= false: not verify edit confirm message	 */
	public void editRecurringEvent(recurringType optEditType, Object... opParams){
		boolean isVerify = (Boolean) (opParams.length > 0 ? opParams[0]: false);
		info("Edit recurring event");
		waitForAndGetElement(ELEMENT_CONFIRM_EDIT_RECURRING_FORM);
		if(isVerify){
			waitForAndGetElement(ELEMENT_CONFIRM_EDIT_DELETE_RECURRING_EVENT);
			waitForAndGetElement(ELEMENT_EDIT_DELETE_ONE_EVENT,DEFAULT_TIMEOUT,1,2);
			info(waitForAndGetElement(ELEMENT_CONFIRM_EDIT_DELETE_RECURRING_EVENT).getText());
			assert waitForAndGetElement(ELEMENT_CONFIRM_EDIT_DELETE_RECURRING_EVENT).getText().contains(ELEMENT_CONFIRM_EDIT_MESSAGE);
			assert waitForAndGetElement(ELEMENT_EDIT_DELETE_ONE_EVENT,DEFAULT_TIMEOUT,1,2).isSelected();
		}
		switch (optEditType) {
		case ONLY_EVENT:
			info("Edit only event recurring");
			check(ELEMENT_EDIT_DELETE_ONE_EVENT,2);
			break;
		case FOLLOW_EVENT:
			info("Edit following event recurring");
			check(ELEMENT_EDIT_DELETE_FOLLOWING_EVENT,2);
			break;
		case ALL_EVENT:
			info("Edit all event recurring");
			check(ELEMENT_EDIT_DELETE_ALL_EVENT,2);
			break;
		}
		click(ELEMENT_CONFIRM_EDIT_BUTTON);
		waitForElementNotPresent(ELEMENT_CONFIRM_EDIT_RECURRING_FORM);
	}
}


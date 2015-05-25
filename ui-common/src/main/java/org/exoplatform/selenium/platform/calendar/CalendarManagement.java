package org.exoplatform.selenium.platform.calendar;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.PlatformPermission;
import org.exoplatform.selenium.platform.calendar.EventManagement.recurringType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
public class CalendarManagement extends PlatformBase{


	public String ELEMENT_EVENT_TASK_TITLE="//*[contains(text(),'${name}')]";
	public By ELEMENT_ADD_EDIT_EVENT_POPUP = By.xpath(".//*[@id='UICalendarPopupWindow']");
	public String ELEMENT_EVENT_TASK_NUMBER_RECURRING="(.//*[@id='UIWeekViewGrid']//*[contains(text(),'${name}')])[${number}]";
	
	//Grid-->List tab
	public String ELEMENT_CALENDAR_TAB=".//*[@id='UIActionBar']//*[contains(text(),'${name}')]";
	public By ELEMENT_CALENDAR_LIST_TAB_SELECT_ALL_CHECKBOX=By.xpath(".//*[@id='UIListUsers']//*[contains(@data-original-title,'Select All')]//input");
	public By ELEMENT_CALENDAR_LIST_TAB_DELETE_BUTTON =By.xpath(".//*[contains(@data-original-title,'Delete')]//*[contains(@class,'uiIconDelete')]");
	public By ELEMENT_CALENDAR_ROW_TAB_LIST=By.xpath(".//*[@id='UIListUsers']//td[1]");
	public By ELMENT_CALENDAR_TAB_LIST_EMPTY=By.xpath(".//*[@id='UIListUsers']//*[contains(@class,'empty')]");

	//Common calendar action menu (icon +)
	public By ELEMENT_CALENDAR_MENU = By.id("tmpMenuElement");
	public By ELEMENT_CALENDAR_MENU_ACTIONS_ICON = By.xpath("//*[@class='uiIconCalSimplePlus uiIconLightGray']");
	public By ELEMENT_CALENDAR_MENU_ACTIONS_ADD = By.xpath("//*[@id='tmpMenuElement']//a[contains(@href,'AddCalendar')]");
	public By ELEMENT_CALENDAR_MENU_ACTIONS_REMOTE = By.xpath("//*[@id='tmpMenuElement']//a[contains(@href,'RemoteCalendar')]");
	public By ELEMENT_CALENDAR_MENU_ACTIONS_ADD_EVENT_CATEGORY = By.xpath("//*[@id='tmpMenuElement']//a[contains(@href,'AddEventCategory')]");
	public By ELEMENT_CALENDAR_MENU_ACTIONS_IMPORT = By.xpath("//*[@id='tmpMenuElement']//a[contains(@href,'ImportCalendar')]");
	public By ELEMENT_CALENDAR_MENU_ACTIONS_CALENDAR_SETTING = By.xpath("//*[@id='tmpMenuElement']//a[contains(@href,'CalendarSetting')]");

	//Calendar setting form
	public By ELEMENT_CALENDAR_SETTING_FORM=By.id("UICalendarSettingForm");
	public By ELEMENT_CALENDAR_SETTING_DISPLAY_TAB= By.xpath("//*[@data-target='#defaultCalendarTab-tab']");
	public By ELEMENT_CALENDAR_SETTING_FEED_TAB= By.xpath("//*[@data-target='#feedTab-tab']");
	public By ELEMENT_CALENDAR_SETTING_VIEW_TYPE=By.name("viewType");
	public By ELEMENT_CALENDAR_SETTING_TIME_ZONE=By.name("timeZone");
	public By ELEMENT_CALENDAR_SETTING_DATE_FORMAT=By.name("dateFormat");
	public By ELEMENT_CALENDAR_SETTING_TIME_FORMAT=By.name("timeFormat");
	public By ELEMENT_CALENDAR_SETTING_WEEK_START_ON=By.name("weekStartOn");
	public By ELEMENT_CALENDAR_SETTING_SHOW_WORKING_TIME_CHECKBOX = By.name("showWorkingTime");
	public By ELEMENT_CALENDAR_SETTING_SHOW_WORKING_BEGIN_TIME = By.name("beginTime");
	public By ELEMENT_CALENDAR_SETTING_SHOW_WORKING_END_TIME = By.name("endTime");
	public By ELEMENT_CALENDAR_SETTING_NEVER_SEND_INVITE_CHECKBOX = By.xpath("//*[@value='never']");
	public By ELEMENT_CALENDAR_SETTING_ALWAYS_SEND_INVITE_CHECKBOX = By.xpath("//*[@value='always']");
	public By ELEMENT_CALENDAR_SETTING_ASK_SEND_INVITE_CHECKBOX = By.xpath("//*[@value='ask']");
	public String ELEMENT_CALENDAR_SETTING_TIMEZONE_VALUE = "//*[@id='setting']//select[@name='timeZone']/option[@value='$time']";
	public String ELEMENT_CALENDAR_SETTING_VIEWTYPE_VALUE = "//*[@id='setting']//select[@name='viewType']/option[@value='$view']";
	public String ELEMENT_CALENDAR_SETTING_DATEFORMAT_VALUE = "//*[@id='setting']//select[@name='dateFormat']/option[@value='$date']";
	public String ELEMENT_CALENDAR_SETTING_TIMEFORMATE_VALUE = "//*[@id='setting']//select[@name='timeFormat']/option[@value='$time']";
	public String ELEMENT_CALENDAR_SETTING_STARTON_VALUE = "//*[@id='setting']//select[@name='weekStartOn']/option[@value='$time']";
	public By ELEMENT_SETTING_FORM_SAVE_BUTTON = By.xpath(".//*[@id='UICalendarSettingForm']//button[1]");
	public By ELEMENT_SETTING_FORM_CANCEL_BUTTON = By.xpath(".//*[@id='UICalendarSettingForm']//button[2]");

	//Calendar setting - Displayed calendar form
	public String ELEMENT_DISPLAY_FORM_PERSONAL_CALENDAR_ITEM="//*[@id='UIPopupCalendarSettingContainer']//*[text()='Personal Calendars']/../..//*[@class='calendarName' and text()='$name']";
	public String ELEMENT_DISPLAY_CALENDAR_FORM_CHECKED=".//*[@id='UICalendarPopupWindow']//*[contains(text(),'$calendar')]/..//*[contains(@class,'iconCheckBox')]";
	public String ELEMENT_DISPLAY_CALENDAR_FORM_UNCHECKED=".//*[@id='UICalendarPopupWindow']//*[contains(text(),'$calendar')]/..//*[contains(@class,'iconUnCheckBox')]";
	public String ELEMENT_DISPLAY_FORM_GROUP_CALENDAR_ITEM="//*[@id='UIPopupCalendarSettingContainer']//*[text()='Group Calendars']/../..//*[@class='calendarName' and contains(text(),'$name')]";
	public String ELEMENT_DISPLAY_FORM_GROUP_CALENDAR_ITEM_CHECKBOX="//*[@id='UIPopupCalendarSettingContainer']//*[text()='Group Calendars']/../..//*[@class='calendarName' and contains(text(),'$name')]/../..//*[@class='iconCheckBox checkbox' or @class='checkbox iconCheckBox']";
	public String ELEMENT_DISPLAY_FORM_GROUP_CALENDAR_ITEM_UNCHECKBOX="//*[@id='UIPopupCalendarSettingContainer']//*[text()='Group Calendars']/../..//*[@class='calendarName' and contains(text(),'$name')]/../..//*[@class='iconUnCheckBox checkbox' or @class='checkbox iconUnCheckBox']";

	//Calendar setting - Feed tab
	public By ELEMENT_FEED_TAB_FORM = By.id("feedTab");
	public By ELEMENT_FEED_TAB_SAVE_BUTTON = By.xpath("//*[@id='feedTab']//*[text()='Add']");
	public By ELEMENT_FEED_EDIT_FEED_FORM=By.xpath("//*[@id='UIEditFeed' and @class='UIEditFeed']");
	public By ELEMENT_FEED_EDIT_FEED_SAVE_FORM=By.xpath("//*[@id='UIEditFeed' and @class='UIEditFeed']//*[contains(@onclick,'Save')]");
	public By ELEMENT_FEED_EDIT_FEED_CANCEL_FORM=By.xpath("//*[@id='UIEditFeed' and @class='UIEditFeed']//*[contains(@onclick,'Cancel')]");
	public By ELEMENT_FEED_NAME_INPUT=By.id("name");
	public By ELEMENT_FEED_URL_INPUT=By.id("url");
	public By ELEMENT_FEED_CALENDAR_OPTION=By.name("addMore");
	public By ELEMENT_FEED_EDIT_FEED_RESET_URL=By.xpath("//*[@id='UIEditFeed' and @class='UIEditFeed']//*[@data-original-title='Reset URL']");
	public By ELEMENT_FEED_EDIT_FEED_GENERATE_URL=By.xpath("//*[@id='UIEditFeed' and @class='UIEditFeed']//*[@data-original-title='Generate URL']");
	public By ELEMENT_FEED_EDIT_FEED_ADD_CALENDAR=By.xpath("//*[@id='UIEditFeed' and @class='UIEditFeed']//*[@data-original-title='Add Calendar']");
	public String ELEMENT_FEED_EDIT_FEED_DELETE_CALENDAR="//*[@id='UIEditFeed' and @class='UIEditFeed']//*[text()='$name']/../..//*[@class='uiIconDelete uiIconLightGray']";
	public String ELEMENT_FEED_LIST_ITEM_RSS="//*[@id='UIFeedList']//*[text()='$name']/../..//*[@data-original-title='Rss']";
	public String ELEMENT_FEED_LIST_ITEM_RSS_BUTTON="//*[@id='UIFeedList']//*[text()='$name']/../..//*[@data-original-title='Rss']";
	public String ELEMENT_FEED_LIST_ITEM_EDIT_BUTTON="//*[@id='UIFeedList']//*[text()='$name']/../..//*[@data-original-title='Edit']";
	public String ELEMENT_FEED_LIST_ITEM_DELETE_BUTTON="//*[@id='UIFeedList']//*[text()='$name']/../..//*[@data-original-title='Delete']";
	public String ELEMENT_FEED_CONFIRM_DELETE="Are you sure you want to delete this feed from the list?";
	public String ELEMENT_FEED_CONFIRM_ADD_FEED="The feed $name has been generated successfully";

	//Remote calendar form
	public By ELEMENT_REMOTE_CALENDAR_FORM=By.id("UICalendarPopupWindow");

	//Add event category form
	public By ELEMENT_ADD_EVENT_CATEGORY_FORM = By.id("UICalendarPopupAction");
	public By ELEMENT_ADD_EVENT_CATEGORY_INPUT = By.id("eventCategoryName");
	public By ELEMENT_ADD_EVENT_CATEGORY_BUTTON_ADD = By.id("btnEventCategoryFormContainer");
	public String ELEMENT_ADD_EVENT_CATEGORY_BUTTON_CLOSE = "//*[@id='UIEventCategoryForm']//button[contains(text(),'Close')]";
	public String ELEMENT_LIST_EVENT_CATEGORY = "//*[@id='UIEventCategoryList']//span[contains(text(),'${categoryName}')]";
	public String ELEMENT_LIST_DELETE_EVENT_BUTTON = "//*[@id='UIEventCategoryList']//span[contains(text(),'${categoryName}')]/parent::td/parent::tr//a[@data-original-title='Delete']/i[@class='uiIconDelete uiIconLightGray']";
	public String ELEMENT_LIST_EDIT_EVENT_BUTTON = ".//*[@id='UIEventCategoryList']//span[contains(text(),'${categoryName}')]/parent::td/parent::tr//a[@data-original-title='Edit']/i[@class='uiIconEdit uiIconLightGray']";
	public By ELEMENT_EDIT_EVENT_CATEGORY_BUTTON_UPDATE = By.id("btnEventCategoryFormContainer");
	public final By ELEMENT_TOOLBAR_MINI_CALENDAR = By.xpath("//*[@class='weekDays']");
	public final By ELEMENT_DELETE_ALL_CATEGORY=By.xpath(".//*[contains(text(),'Cannot delete the default event category.')]");
	public final By ELEMENT_DELETE_ALL_CATEGORY_OK_BTN=By.xpath(".//*[contains(@class,'UIPopupWindow')]//*[contains(text(),'OK')]");

	//Add calendar form
	public By ELEMENT_CALENDAR_ADD_FORM = By.id("UICalendarPopupWindow");
	public By ELEMENT_CALENDAR_DISPLAY_NAME_INPUT = By.id("displayName");
	public By ELEMENT_CALENDAR_DESC_INPUT = By.xpath("//*[@id='UICalendarForm']//*[@id='description']");
	public By ELEMENT_CALENDAR_COLOR = By.xpath("//*[contains(@class,'displayValue')]");
	public By ELEMENT_CALENDAR_GROUP_TAB = By.xpath(".//*[@id='uiPopupAddCalendarContainer']//*[contains(@data-target,'#public-tab')]");
	public By ELEMENT_CALENDAR_DETAIL_TAB = By.xpath("//*[text()='Details']");
	public By ELEMENT_CALENDAR_GROUP_INPUT = By.id("AddGroupInput");
	public By ELEMENT_CALENDAR_GROUP_INPUT_USER=By.xpath(".//*[contains(@class,'inputLarge')]//input");
	public By ELEMENT_CALENDAR_GROUP_SELECT_USER_BTN=By.xpath(".//*[@id='public']//*[contains(@class,'uiIconUser')]");
	public By ELEMENT_CALENDAR_GROUP_SELECT_ROLE_BTN=By.xpath(".//*[@id='public']//*[contains(@class,'uiIconMembership')]");
	public String ELEMENT_CALENDAR_GROUP_ITEM = "//*[@id='UICalendarChildPopupWindow']//*[@data-original-title='$group']";
	public By ELEMENT_CALENDAR_ADD_GROUP_BUTTON = By.xpath("//*[@class='addGroup']//*[text()='Add']");
	public By ELEMENT_CALENDAR_ADD_SAVE_BUTTON = By.xpath("//*[@id='UICalendarForm']//*[text()='Save']");
	public String ELEMENT_CALENDAR_COLOR_SELECT = "//*[@id='UICalendarForm']//a[contains(@class,'${color}')]";

	//Calendar list
	public String ELEMENT_SHARED_CALENDAR_LIST_ITEM="//*[@id='UICalendars']//*[text()='Shared Calendars']/..//*[text()='$calendar']";
	public String ELEMENT_GROUP_CALENDAR_LIST_ITEM="//*[@id='UICalendars']//*[text()='Group Calendars']/..//*[text()='$calendar']";
	public String ELEMENT_PERSONAL_CALENDAR_LIST_ITEM="//*[@id='UICalendars']//*[text()='Personal Calendars']/..//*[text()='$calendar']";
	public String ELEMENT_CALENDAR_LIST_ITEM="//*[@id='UICalendars']//*[text()='$calendar']";
	public String ELEMENT_CALENDAR_LIST_UNCHECKED=".//*[contains(@data-original-title,'$calendar')]/..//*[contains(@class,'iconUnCheckBox')]";
	public String ELEMENT_CALENDAR_LIST_CHECKED=".//*[contains(@data-original-title,'$calendar')]/..//*[contains(@class,'iconCheckBox')]";
	
	public By ELEMENT_CALENDAR_RIGHT_MENU=By.id("tmpMenuElement");
	public By ELEMENT_CALENDAR_ADD_TASK_MENU = By.xpath("//*[@id='tmpMenuElement']//*[@class='uiIconCalCreateTask uiIconLightGray']");
	public By ELEMENT_CALENDAR_ADD_EVENT_MENU =By.xpath("//*[@id='tmpMenuElement']//*[@class='uiIconCalCreateEvent uiIconLightGray']");
	public By ELEMENT_CALENDAR_ADD_EVENT_MENU_NO_DISPLAY=By.xpath(".//*[@id='tmpMenuElement']//*[contains(@style,'display: none;')]//*[contains(@class,'uiIconCalCreateEvent')]");
	public By ELEMENT_CALENDAR_EDIT_MENU = By.xpath("//*[@id='tmpMenuElement']//*[@class='uiIconEdit uiIconLightGray']");
	public By ELEMENT_CALENDAR_SHARE_MENU = By.xpath("//*[@id='tmpMenuElement']//*[@class='uiIconCalShare uiIconLightGray']");
	public By ELEMENT_CALENDAR_REMOVE_MENU = By.xpath("//*[@id='tmpMenuElement']//*[contains(@href,'RemoveCalendar')]");
	public By ELEMENT_CALENDAR_REMOVE_SHARE_CALENDAR=By.xpath("//*[@id='tmpMenuElement']//*[contains(@href,'RemoveSharedCalendar')]");
	public By ELEMENT_CALENDAR_IMPORT_MENU = By.xpath("//*[@id='tmpMenuElement']//*[@class='uiIconCalImportCalendar uiIconLightGray']");
	public By ELEMENT_CALENDAR_EXPORT_MENU = By.xpath("//*[@id='tmpMenuElement']//*[@class='uiIconCalExportCalendar uiIconLightGray']");
	public By ELEMENT_CALENDAR_REFRESH_MENU = By.xpath("//*[@id='tmpMenuElement']//*[@class='uiIconRefresh uiIconLightGray']");

	//Forms after click common calendar action menu (icon *)
	public String ELEMENT_CALENDAR_SETTING_ICON="//*[text()='$calendar']/../..//*[contains(@class,'uiIconCalSettingMini')]";
	public By ELEMENT_CALENDAR_QUICK_ADD_EVENT_FORM = By.id("UIQuickAddEventPopupWindow");
	public By ELEMENT_CALENDAR_QUICK_ADD_TASK_FORM = By.id("UIQuickAddTask");
	public By ELEMENT_CALENDAR_IMPORT_FORM = By.xpath("//*[@class='uiImportForm']");
	public By ELEMENT_CALENDAR_EXPORT_FORM = By.id("UICalendarPopupWindow");
	public By ELEMENT_CALENDAR_SHARE_FORM = By.id("UISharedForm");
	public By ELEMENT_CALENDAR_REMOVE_FORM = By.xpath("//*[@class='confirmationIcon']");
	
	//Content menu
	public final By ELEMENT_CALENDAR_CONTEXT_MENU=By.xpath(".//*[@id='tmpMenuElement']");

	//Export form
	public By ELEMENT_CALENDAR_EXPORT_POPUP_FORM=By.id("UICalendarPopupWindow");
	public By ELEMENT_CALENDAR_EXPORT_FILE_NAME=By.id("name");
	public By ELEMENT_CALENDAR_EXPORT_SAVE_BUTTON=By.xpath("//*[@id='UIExportForm']//*[text()='Save']");

	//Import form
	public By ELEMENT_CALENDAR_IMPORT_POPUP_FORM=By.id("UIImportForm");
	public By ELEMENT_CALENDAR_IMPORT_DESC_INPUT = By.xpath("//form[@id='UIImportForm']//*[@id='description']");
	public By ELEMENT_CALENDAR_IMPORT_SAVE_BUTTON = By.xpath("//form[@id='UIImportForm']//*[text()='Save']");
	public By ELEMENT_CALENDAR_IMPORT_SELECT_FILE = By.name("file");
	public By ELEMENT_CALENDAR_IMPORT_DELETE_ICON = By.xpath("//a[@data-original-title='Delete']/i[@class='uiIconDelete uiIconLightGray']");
	public By ELEMENT_CALENDAR_IMPORT_NAME_INPUT = By.id("displayName");
	public By ELEMENT_CALENDAR_IMPORT_COLOR = By.xpath("//*[contains(@class,'displayValue')]");

	//Share calendar form
	public By ELEMENT_CALENDAR_SHARE_INPUT = By.id("PermissionOwnerInput");
	public By ELEMENT_CALENDAR_SELECT_USER_ICON = By.xpath("//*[@class='uiIconUser uiIconLightGray']");
	public By ELEMENT_CALENDAR_SELECT_MEMBERSHIP_ICON = By.xpath("//*[@class='uiIconMembership uiIconLightGray']");
	public By ELEMENT_CALENDAR_SELECT_GROUP_ICON = By.xpath("//*[@class='uiIconGroup uiIconLightGray']");
	public String ELEMENT_CALENDAR_SHARE_EDIT_PERMISSION = "//div[@title='${user}']/../..//input[@class='checkbox']";
	public By ELEMENT_CALENDAR_SHARE_ADD_BUTTON = By.xpath("//form[@id='UISharedForm']//*[text()='Add']");
	public By ELEMENT_CALENDAR_SHARE_SAVE_BUTTON = By.xpath("//form[@id='UISharedForm']//*[text()='Save']");
	public String ELEMENT_DELETE_SHARE_USER = "//*[@id='UISharedForm']//*[contains(text(),'{$user}')]/../..//*[@class='uiIconDelete uiIconLightGray']";

	//Remove calendar
	public final By ELEMENT_YES_BUTTON = By.xpath("//*[contains(@class, 'popup')]//*[contains(text(),'Yes')]");
	public final String ELEMENT_CONFIRM_REMOVE_CALENDAR_MSG="Are you sure you want to delete this calendar and all its events?";

	//Preview popup
	public final String ELEMENT_CALENDAR_PREVIEW_TASK_EVENT=".//*[@id='UIPreviewPopup']//strong[contains(text(),'${name}')]";
	
	//Context Menu when right click on an event
	public By ELEMENT_CONTEXT_MENU_VIEW=By.xpath(".//*[@id='tmpMenuElement']//*[contains(@class,'iIconPreview')]");
	public By ELEMENT_CONTEXT_MENU_EDIT=By.xpath(".//*[@id='tmpMenuElement']//*[contains(@class,'uiIconEdit')]");
	public By ELEMENT_CONTEXT_MENU_DELETE=By.xpath(".//*[@id='tmpMenuElement']//*[contains(@class,'uiIconDelete')]");
	public By ELEMENT_CONTEXT_MENU_EXPORT=By.xpath(".//*[@id='tmpMenuElement']//*[contains(@class,'uiIconCalExportCalendar')]");

	
	//Confirm popup
	public By ELEMENT_CONFIRM_POPUP_OK=By.xpath(".//*[@id='UIConfirmation']//*[contains(text(),'Yes')]");
	public By ELEMENT_CONFIRM_POPUP_DELETE=By.xpath(".//*[contains(@class,'uiConfirmForm')]//button[1]");
	
	//Invitation email
	public final String ELEMENT_GMAIL_CONTENT_INVITATION_EMAIL = ".//b[contains(text(),'[Invitation] $eventTask')]";
	public final String ELEMENT_GMAIL_EMAIL_DETAILS=".//h3//*[contains(@name,'$email')]";
	public final String ELEMENT_GMAIL_EMAIL_ICS_FILE=".//span[contains(text(),'$file')]";
	public final String ELEMENT_GMAIL_EMAIL_DETAIL_LINK="//*[contains(@target,'_blank')][contains(text(),'$link')]";
	public final String ELEMENT_REFUSE_INVITATION_MESSAGE ="//*[contains(text(),'You have refused invitation from $user')]";
	public final String ELEMENT_MAYBE_INVITATION_MESSAGE ="//*[contains(text(),'You have answered invitation from $user: Maybe')]";
	public final String ELEMENT_ACCEPT_INVITATION_MESSAGE ="//*[contains(text(),'You have accepted invitation from $user')]";
	
	
	PlatformPermission pPer;
	EventManagement evMg;
	ManageAlert alert;
    Button button;
	/**
	 * constructor
	 * @param dr
	 */
	public CalendarManagement(WebDriver dr){
		this.driver=dr;
		pPer = new PlatformPermission(dr);
		alert = new ManageAlert(dr);
		button = new Button(dr);
		evMg = new EventManagement(dr);
	};

	/**
	 * View list in calendar (Click icon +)
	 */
	public enum menuOfMainCalendar{
		IMPORT, ADDCAL, REMOTECAL, ADDCATEGORY, CALSETTING
	}

	/**
	 * View list in calendar (click on an calendar - Click icon *)
	 */
	public enum menuOfCalendarOption{
		ADDTASK, ADDEVENT, EDIT, REMOVE, SHARE, IMPORT, EXPORT, REFRESH
	}

	/**
	 * Execute action of calendar: Edit, Delete, Share, export....
	 * @param action
	 * 				action that needs to be done, e.g.: "ShareCalendar"
	 */
	public void goToMenuFromMainCalendar(menuOfMainCalendar action){
		info("Select action from menu");
		click(ELEMENT_CALENDAR_MENU_ACTIONS_ICON,0,true);
		waitForAndGetElement(ELEMENT_CALENDAR_MENU);
		switch(action){
		case ADDCAL:
			info("Go to add calendar");
			click(ELEMENT_CALENDAR_MENU_ACTIONS_ADD,0,true);
			waitForAndGetElement(ELEMENT_CALENDAR_ADD_FORM);
			break;
		case REMOTECAL:
			info("Go to remote calendar");
			click(ELEMENT_CALENDAR_MENU_ACTIONS_REMOTE,0,true);
			waitForAndGetElement(ELEMENT_REMOTE_CALENDAR_FORM);
			break;
		case ADDCATEGORY:
			info("Go to add category calendar");
			click(ELEMENT_CALENDAR_MENU_ACTIONS_ADD_EVENT_CATEGORY,0,true);
			waitForAndGetElement(ELEMENT_ADD_EVENT_CATEGORY_FORM);
			break;
		case CALSETTING:
			info("Go to calendar setting");
			click(ELEMENT_CALENDAR_MENU_ACTIONS_CALENDAR_SETTING,0,true);
			waitForAndGetElement(ELEMENT_CALENDAR_SETTING_FORM);
			break;
		case IMPORT:
			info("Import calendar");
			click(ELEMENT_CALENDAR_MENU_ACTIONS_IMPORT,0,true);
			waitForAndGetElement(ELEMENT_CALENDAR_IMPORT_POPUP_FORM);
			break;
		default:
			info("Go to add calendar");
			click(ELEMENT_CALENDAR_MENU_ACTIONS_ADD,0,true);
			waitForAndGetElement(ELEMENT_CALENDAR_QUICK_ADD_TASK_FORM);
			break;
		}
	}

	/** 
	 * Delete a calendar
	 * 
	 * @param name
	 * 				name of calendar
	 * @param verify
	 * 				optional parameter. If not be set, by default, it is considered that calendar is deleted 
	 * 				= true: verify that calendar is deleted, 
	 * 				= false: not verify that calendar is deleted, 
	 */
	public void deleteCalendar(String name, boolean...verify){
		boolean isVerify = (verify.length > 0 ? verify[0]: false);
		info("Remove calendar");
		driver.navigate().refresh();
		executeActionCalendar(name, menuOfCalendarOption.REMOVE);
		if(isVerify)
			alert.verifyAlertMessage(ELEMENT_CONFIRM_REMOVE_CALENDAR_MSG);
		else
			click(ELEMENT_YES_BUTTON);
		waitForElementNotPresent(ELEMENT_CALENDAR_LIST_ITEM.replace("$calendar", name));
	}

	/** 
	 * Edit a calendar
	 * 
	 * @param oldName
	 * 				old name of calendar
	 * @param name
	 * 				new name of calendar
	 * @param description
	 * 				new description of calendar
	 * @param color
	 * 				new color of calendar
	 * @param group
	 * 				group: example (/developers, /platform/administrators, /platform/users, /platform/web-contributors, 
	 * 				/organization/management/executive-board, /organization/employees
	 */
	public void editCalendar(String oldName,String name, String description, String color, String group){
		executeActionCalendar(oldName, menuOfCalendarOption.EDIT);
		inputDataInDetailTabCalendarForm(name, description, color);
		if(group!=null && group!="")
			selectGroupInGroupTabCalendarForm(group,true);
	}

	/** 
	 * Input into tab Detail of Add calendar form
	 * 
	 * @param name
	 * 			name of calendar
	 * @param description
	 * 			description of calendar
	 * @param color
	 * 			color of calendar
	 */
	public void inputDataInDetailTabCalendarForm(String name, String description, String color){
		info("Input into tab Detail of Add calendar form");
		click(ELEMENT_CALENDAR_DETAIL_TAB);
		if(name != null && name!="")
			type(ELEMENT_CALENDAR_DISPLAY_NAME_INPUT,name,true);
		if(description != null && description!="")
			type(ELEMENT_CALENDAR_DESC_INPUT, description,true);
		if(color != null && color !=""){
			click(ELEMENT_CALENDAR_COLOR);
			click(ELEMENT_CALENDAR_COLOR_SELECT.replace("${color}", color));
		}
	}

	/**
	 * selectGroupInGroupTabCalendarForm
	 * @param group
	 * 				group: example (/developers, /platform/administrators, /platform/users, /platform/web-contributors, 
	 * 				/organization/management/executive-board, /organization/employees
	 * @param isType
	 * 				true: select a group by typing
	 * 				false: select a group by select group icon
	 */
	public void selectGroupInGroupTabCalendarForm(String group,boolean isType){
		info("Input into tab Show in Group of Add calendar form");
		click(ELEMENT_CALENDAR_GROUP_TAB);
		if(group!=null&&group!=""){
			if(isType){
				type(ELEMENT_CALENDAR_GROUP_INPUT,group,true);
			}
			else{
				click(ELEMENT_CALENDAR_SELECT_GROUP_ICON);
				click(group);
			}
		}
		click(ELEMENT_CALENDAR_ADD_GROUP_BUTTON);
	}
	/**
	 * Select a user/role who has edit permission in a group
	 * @param user
	 * @param mode
	 * 				way to input users, groups.
	 *            =0: type directly
	 *            =1: select user
	 *            =2: select role
	 */
	public void selectUserEditPermissionInGroup(String[] user,int...mode){
		for(int i = 0; i < user.length; i++){
			int modeUser = mode.length > i ? mode[i] : 0;
			switch (modeUser){
			case 0: 
				info("user"+user[i]);
				type(ELEMENT_CALENDAR_GROUP_INPUT_USER,user[i],true);
				break;
			case 1:
				click(ELEMENT_CALENDAR_GROUP_SELECT_USER_BTN); 
				pPer.selectUserPermission(user[i],1); break;
			case 2: 
				String[] groupMem = user[i].split(":");
				String[] membership = groupMem[1].split(".");
				click(ELEMENT_CALENDAR_GROUP_SELECT_ROLE_BTN);
				pPer.selectGroupMembership(groupMem[0],membership[1]); break;
			}
		}
	}

	/**
	 * Save add calendar
	 */
	public void saveAddCalendar(){
		info("Save add calendar");
		click(ELEMENT_CALENDAR_ADD_SAVE_BUTTON);
		waitForElementNotPresent(ELEMENT_CALENDAR_ADD_FORM);
	}

	/** 
	 * Go to Calendar Actions> Add Event Category
	 * 
	 * @param categoryName
	 * 				category name of Calendar
	 */
	public void addEventCategory(String categoryName){
		info("----Add new event category----");
		type(ELEMENT_ADD_EVENT_CATEGORY_INPUT,categoryName,true);
		click(ELEMENT_ADD_EVENT_CATEGORY_BUTTON_ADD);
		info("----Verify if event category is added in Category List or not----");
		waitForAndGetElement(ELEMENT_LIST_EVENT_CATEGORY.replace("${categoryName}", categoryName));
		click(ELEMENT_ADD_EVENT_CATEGORY_BUTTON_CLOSE);	 
	}

	/** 
	 * Delete Event Category
	 * 
	 * @param categoryName
	 * 				category name of calendar
	 */
	public void deleteEventCategory(String categoryName){
		ManageAlert alert = new ManageAlert(driver);
		Button button = new Button(driver);
		waitForAndGetElement(ELEMENT_LIST_DELETE_EVENT_BUTTON.replace("${categoryName}",categoryName));
		click(ELEMENT_LIST_DELETE_EVENT_BUTTON.replace("${categoryName}",categoryName));
		alert.acceptAlert();
		button.yes();
		waitForElementNotPresent(ELEMENT_LIST_DELETE_EVENT_BUTTON.replace("${categoryName}",categoryName));
		click(ELEMENT_ADD_EVENT_CATEGORY_BUTTON_CLOSE);	
	}

	/** 
	 * Edit Event Category
	 * 
	 * @param oldCategory
	 * 						old category name
	 * @param newCategory
	 * 						new category name
	 */

	public void editEventCategory(String oldCategory, String newCategory){
		waitForAndGetElement(ELEMENT_LIST_EDIT_EVENT_BUTTON.replace("${categoryName}",oldCategory));
		click(ELEMENT_LIST_EDIT_EVENT_BUTTON.replace("${categoryName}",oldCategory));
		type(ELEMENT_ADD_EVENT_CATEGORY_INPUT,newCategory,true);
		click(ELEMENT_EDIT_EVENT_CATEGORY_BUTTON_UPDATE);
		waitForAndGetElement(ELEMENT_LIST_EDIT_EVENT_BUTTON.replace("${categoryName}",newCategory));
		click(ELEMENT_ADD_EVENT_CATEGORY_BUTTON_CLOSE);
	}

	/**
	 * Open menu an a calendar
	 * @param calendar
	 * 					name of calendar
	 */
	public void openMenuOfCalendar(String calendar){
		info("Open menu of a calendar");
		mouseHoverByJavaScript(ELEMENT_CALENDAR_LIST_ITEM.replace("$calendar", calendar),2);
		clickByJavascript(ELEMENT_CALENDAR_SETTING_ICON.replace("$calendar", calendar),2);
		waitForAndGetElement(ELEMENT_CALENDAR_RIGHT_MENU);
	}

	/**
	 * Execute action of calendar: Edit, Delete, Share, export....
	 * 
	 * @param calendar
	 * 				name of calendar
	 * @param action
	 * 				action that needs to be done, e.g.: "ShareCalendar"
	 */
	public void executeActionCalendar(String calendar, menuOfCalendarOption action){
		info("Select action from menu");
		openMenuOfCalendar(calendar);
		switch(action){
		case ADDTASK:
			clickByJavascript(ELEMENT_CALENDAR_ADD_TASK_MENU,2);
			waitForAndGetElement(ELEMENT_CALENDAR_QUICK_ADD_TASK_FORM);
			break;
		case ADDEVENT:
			clickByJavascript(ELEMENT_CALENDAR_ADD_EVENT_MENU,2);
			waitForAndGetElement(ELEMENT_CALENDAR_QUICK_ADD_EVENT_FORM);
			break;
		case EDIT:
			clickByJavascript(ELEMENT_CALENDAR_EDIT_MENU,2);
			waitForAndGetElement(ELEMENT_CALENDAR_ADD_FORM);
			break;
		case REMOVE:
			clickByJavascript(ELEMENT_CALENDAR_REMOVE_MENU,2);
			waitForAndGetElement(ELEMENT_CALENDAR_REMOVE_FORM);
			break;
		case SHARE:
			clickByJavascript(ELEMENT_CALENDAR_SHARE_MENU,2);
			waitForAndGetElement(ELEMENT_CALENDAR_SHARE_FORM);
			break;
		case IMPORT:
			clickByJavascript(ELEMENT_CALENDAR_IMPORT_MENU,2);
			waitForAndGetElement(ELEMENT_CALENDAR_IMPORT_FORM);
			break;
		case EXPORT:
			clickByJavascript(ELEMENT_CALENDAR_EXPORT_MENU,2);
			waitForAndGetElement(ELEMENT_CALENDAR_EXPORT_FORM);
			break;
		case REFRESH:
			clickByJavascript(ELEMENT_CALENDAR_REFRESH_MENU,2);
			break;
		default:
			click(ELEMENT_CALENDAR_ADD_TASK_MENU,2);
			waitForAndGetElement(ELEMENT_CALENDAR_QUICK_ADD_TASK_FORM);
			break;
		}
	}

	/** 
	 * Share a calendar
	 * 
	 * @param calendar
	 * 				name of calendar
	 * @param userGroup
	 * 				array of users or groups that are shared with
	 * @param canEdit
	 * 				array of "canEdit" permissions for users, groups respectively inputed by userGroup variable 
	 * @param mode
	 * 				way to input users, groups.
	 *            =0: type directly
	 *            =1: select user
	 *            =2: select group
	 *            =3: select membership
	 */
	public void shareCalendar(String calendar, String[] userGroup, boolean[] canEdit, int...mode){
		info("Share calendar");
		executeActionCalendar(calendar, menuOfCalendarOption.SHARE);
		for(int i = 0; i < userGroup.length; i++){
			int modeUser = mode.length > i ? mode[i] : 0;
			switch (modeUser){
			case 0: 
				info("userGroup[i]"+userGroup[i]);
				type(ELEMENT_CALENDAR_SHARE_INPUT,userGroup[i],true);
				break;
			case 1:
				click(ELEMENT_CALENDAR_SELECT_USER_ICON); 
				pPer.selectUserPermission(userGroup[i],1); break;
			case 2: 
				String[] group = userGroup[i].split(":");
				click(ELEMENT_CALENDAR_SELECT_GROUP_ICON);
				pPer.selectGroupPermission(group[0]); break;
			case 3: 
				String[] groupMem = userGroup[i].split(":");
				String[] membership = groupMem[1].split(".");
				click(ELEMENT_CALENDAR_SELECT_MEMBERSHIP_ICON);
				pPer.selectGroupMembership(groupMem[0],membership[1]); break;
			}
		}
		click(ELEMENT_CALENDAR_SHARE_ADD_BUTTON);
		for(int j=0; j < canEdit.length; j++){
			if(canEdit[j]==true){
				check(ELEMENT_CALENDAR_SHARE_EDIT_PERMISSION.replace("${user}", userGroup[j]),2);
			}
			else{
				uncheck(ELEMENT_CALENDAR_SHARE_EDIT_PERMISSION.replace("${user}", userGroup[j]),2);
			}
		}
		click(ELEMENT_CALENDAR_SHARE_SAVE_BUTTON);
		waitForElementNotPresent(ELEMENT_CALENDAR_SHARE_SAVE_BUTTON);
	}

	/** 
	 * Share a calendar
	 * 
	 * @param calendar
	 * 				name of calendar
	 * @param userGroup
	 * 				array of users or groups that are shared with
	 */
	public void removeUserGrooupFromShareCalendar(String calendar, String[] userGroup){
		info("Share calendar");
		executeActionCalendar(calendar, menuOfCalendarOption.SHARE);
		for(int i = 0; i < userGroup.length; i++){
			click(By.xpath(ELEMENT_DELETE_SHARE_USER.replace("{$user}",userGroup[i])));
		}
		click(ELEMENT_CALENDAR_SHARE_SAVE_BUTTON);
		waitForElementNotPresent(ELEMENT_CALENDAR_SHARE_SAVE_BUTTON);
	}

	/** 
	 * Upload calendar
	 * 
	 * @param path
	 * 			path of a file which will be uploaded.
	 */
	public void uploadCalendar(String path){
		info("--Upload Calendar--");
		WebElement element = waitForAndGetElement(ELEMENT_CALENDAR_IMPORT_SELECT_FILE, DEFAULT_TIMEOUT, 1, 2);
		((JavascriptExecutor)driver).executeScript("arguments[0].style.display = 'block';", element);
		element.sendKeys(getAbsoluteFilePath(path));
		String[] links = path.split("/");
		waitForAndGetElement("//*[text()='"+links[links.length-1]+"']");
		info("import " + getAbsoluteFilePath(path));
		switchToParentWindow();
		waitForAndGetElement(ELEMENT_CALENDAR_IMPORT_DELETE_ICON);
	}

	/** 
	 * Import calendar
	 * 
	 * @param path
	 * 				path of a file which is for upload
	 * @param name
	 * 				name of calendar
	 * @param description
	 * 				description of calendar
	 * @param color
	 * 				color of calendar
	 */
	public void importCalendar(String path, String name, String description, String color){
		click(ELEMENT_CALENDAR_MENU_ACTIONS_ICON);
		Utils.pause(3000);
		click(ELEMENT_CALENDAR_MENU_ACTIONS_IMPORT);
		waitForAndGetElement(ELEMENT_CALENDAR_IMPORT_POPUP_FORM);
		uploadCalendar(path);
		if(name != null)
			type(ELEMENT_CALENDAR_IMPORT_NAME_INPUT,name,true);
		if(description != null)
			type(ELEMENT_CALENDAR_IMPORT_DESC_INPUT,description,true);

		if (color != null){
			click(ELEMENT_CALENDAR_IMPORT_COLOR);
			click(ELEMENT_CALENDAR_COLOR_SELECT.replace("${color}", color));
		}
		click(ELEMENT_CALENDAR_IMPORT_SAVE_BUTTON);
		waitForElementNotPresent(ELEMENT_CALENDAR_IMPORT_POPUP_FORM);
		driver.navigate().refresh();
		waitForAndGetElement(By.linkText(name));
		button.ok();
	}


	/**
	 * Export calendar
	 * 
	 * @param calendar
	 * 				name of calendar
	 * @param name
	 * 				filename of exported calendar
	 */
	public void exportCalendar(String calendar, String name){
		info("Export calendar");
		executeActionCalendar(calendar, menuOfCalendarOption.EXPORT);
		waitForAndGetElement(ELEMENT_CALENDAR_EXPORT_POPUP_FORM);
		type(ELEMENT_CALENDAR_EXPORT_FILE_NAME,name,true);
		click(ELEMENT_CALENDAR_EXPORT_SAVE_BUTTON);
		waitForElementNotPresent(ELEMENT_CALENDAR_EXPORT_POPUP_FORM);
	}

	/**
	 * change values in setting tab of calendar setting form
	 * @param viewtype
	 * 					default view fof calendar: Week, Day, Month, List, Work Week
	 * @param timezone
	 * 					time zone of calendar, e.g.: (GMT -11:00) Pacific/Samoa	 * 				
	 * @param dateformat
	 * 					date format of calendar:dd/mm/yyyy, dd-mm-yyyy, mm/dd/yyyy, mm-dd-yyyy
	 * @param timeformat
	 * 					time format of calendar: AM/PM, 24 Hours
	 * @param day
	 * 			 start day of week: monday, tuesday, wednesday, thursday, saturday, sunday
	 * @param isShow
	 * 			true: show calendar
	 * 			false: don't show calendar
	 * @param option
	 * 			option to send mail inviatation: NEVER, ASK, ALWAYS
	 */
	public void changeSettingCalendar(String viewtype, String timezone, String dateformat, String timeformat,
			String day, Boolean isShow, selectInvitationOption option){
		goToDisplayCalendarTab();

		if(viewtype!=null && viewtype != ""){
			info("-- Select filter option of view type --");
			select(ELEMENT_CALENDAR_SETTING_VIEW_TYPE,viewtype);
		}
		if(timezone!=null && timezone != ""){
			info("-- Select filter option of Timezone --");
			select(ELEMENT_CALENDAR_SETTING_TIME_ZONE,timezone);	
		}
		if(dateformat!=null && dateformat != ""){
			info("-- Select filter option of date format --");
			select(ELEMENT_CALENDAR_SETTING_DATE_FORMAT,dateformat);
		}
		if(timeformat!=null && timeformat != ""){
			info("-- Select filter option of time format --");
			select(ELEMENT_CALENDAR_SETTING_TIME_FORMAT,timeformat);
		}
		if(day!=null && day != ""){
			info("-- Select filter option of week start on --");
			select(ELEMENT_CALENDAR_SETTING_WEEK_START_ON,day);
		}
		if(isShow!=null){
			info("-- Select show calendar or not --");
			if(isShow)
				check(ELEMENT_CALENDAR_SETTING_SHOW_WORKING_TIME_CHECKBOX,2);
			else
				uncheck(ELEMENT_CALENDAR_SETTING_SHOW_WORKING_TIME_CHECKBOX,2);
		}

		if(option!=null){
			info("-- Select send invitaion option --");
			switch(option){
			case NEVER:
				check(ELEMENT_CALENDAR_SETTING_NEVER_SEND_INVITE_CHECKBOX,2);
				break;
			case ALWAYS:
				check(ELEMENT_CALENDAR_SETTING_ALWAYS_SEND_INVITE_CHECKBOX,2);
				break;
			case ASK:
				check(ELEMENT_CALENDAR_SETTING_ASK_SEND_INVITE_CHECKBOX,2);
				break;
			default:
				check(ELEMENT_CALENDAR_SETTING_NEVER_SEND_INVITE_CHECKBOX,2);
				break;
			}
		}
	}

	/**
	 * change start and end time for working time in calendar setting form
	 * @param startTime
	 * @param endTime
	 */
	public void changeWorkingTime(String startTime,String endTime){
		if(!startTime.isEmpty())
			select(ELEMENT_CALENDAR_SETTING_SHOW_WORKING_BEGIN_TIME,startTime);
		if(!endTime.isEmpty())
			select(ELEMENT_CALENDAR_SETTING_SHOW_WORKING_END_TIME,endTime);
	}
	/**
	 * save setting form
	 */
	public void saveSetting(){
		click(ELEMENT_SETTING_FORM_SAVE_BUTTON);
		waitForElementNotPresent(ELEMENT_CALENDAR_SETTING_FORM);
	}

	/**
	 * cancel setting form
	 */
	public void cancelSetting(){
		click(ELEMENT_SETTING_FORM_CANCEL_BUTTON);
		waitForElementNotPresent(ELEMENT_CALENDAR_SETTING_FORM);

	}

	/**
	 * show/hide personal calendar in tab displayed calendar of calendar setting form
	 * @param calendarName
	 * 						name of calendar
	 * @param isShow
	 * 						true: check show calendar
	 * 						false: hide calendar
	 */
	public void showHideCalendar(String calendarName, Boolean isShow){
		info("Show/Hide personal calenar");
		goToDisplayCalendarTab();
		if(isShow!=null){
			if(isShow){
				if(waitForAndGetElement(ELEMENT_DISPLAY_CALENDAR_FORM_UNCHECKED.replace("$calendar", calendarName),5000,0)!=null)
					click(ELEMENT_DISPLAY_CALENDAR_FORM_UNCHECKED.replace("$calendar", calendarName));
				waitForAndGetElement(ELEMENT_DISPLAY_CALENDAR_FORM_CHECKED.replace("$calendar", calendarName));
			}
			else{
				if(waitForAndGetElement(ELEMENT_DISPLAY_CALENDAR_FORM_CHECKED.replace("$calendar", calendarName),5000,0)!=null)
					click(ELEMENT_DISPLAY_CALENDAR_FORM_CHECKED.replace("$calendar", calendarName));
				waitForAndGetElement(ELEMENT_DISPLAY_CALENDAR_FORM_UNCHECKED.replace("$calendar", calendarName));
			}
		}
		else{
			if(waitForAndGetElement(ELEMENT_DISPLAY_CALENDAR_FORM_UNCHECKED.replace("$calendar", calendarName),5000,0)!=null)
				click(ELEMENT_DISPLAY_CALENDAR_FORM_UNCHECKED.replace("$calendar", calendarName));
			waitForAndGetElement(ELEMENT_DISPLAY_CALENDAR_FORM_CHECKED.replace("$calendar", calendarName));
		}
	}
	/**
	 * Show/Hide event/task from Calendar list
	 * @param calendar
	 * @param isShow
	 *                      true: check show calendar
	 * 						false: uncheck calendar
	 */
	public void showHideEventTask(String calendar,Boolean isShow){
		info("Show/Hide an calendar");
		if(isShow){
			if(waitForAndGetElement(ELEMENT_CALENDAR_LIST_UNCHECKED.replace("$calendar",calendar),3000,0)!=null){
				click(ELEMENT_CALENDAR_LIST_UNCHECKED.replace("$calendar",calendar));
				waitForElementNotPresent(ELEMENT_CALENDAR_LIST_UNCHECKED.replace("$calendar",calendar));
			}
		}else {
			if(waitForAndGetElement(ELEMENT_CALENDAR_LIST_CHECKED.replace("$calendar",calendar),3000,0)!=null){
				click(ELEMENT_CALENDAR_LIST_CHECKED.replace("$calendar",calendar));
				waitForElementNotPresent(ELEMENT_CALENDAR_LIST_CHECKED.replace("$calendar",calendar));
			}
		}
	}

	/**
	 * show/hide group calendar in tab displayed calendar of calendar setting form
	 * @param calendarName
	 * 						name of calendar
	 * @param isShow
	 * 						true: check show calendar
	 * 						false: hide calendar
	 *//*
	public void showHideGroupCalendar(String calendarName, Boolean isShow){
		info("Show/Hide group calenar");
		if(isShow!=null){
			if(isShow){
				if(waitForAndGetElement(ELEMENT_DISPLAY_FORM_GROUP_CALENDAR_ITEM_UNCHECKBOX.replace("$name", calendarName),5000,0)!=null)
					click(ELEMENT_DISPLAY_FORM_GROUP_CALENDAR_ITEM_UNCHECKBOX.replace("$name", calendarName));
				waitForAndGetElement(ELEMENT_DISPLAY_FORM_GROUP_CALENDAR_ITEM_CHECKBOX.replace("$name", calendarName));
			}
			else{
				if(waitForAndGetElement(ELEMENT_DISPLAY_FORM_GROUP_CALENDAR_ITEM_CHECKBOX.replace("$name", calendarName),5000,0)!=null)
					click(ELEMENT_DISPLAY_FORM_GROUP_CALENDAR_ITEM_CHECKBOX.replace("$name", calendarName));
				waitForAndGetElement(ELEMENT_DISPLAY_FORM_GROUP_CALENDAR_ITEM_UNCHECKBOX.replace("$name", calendarName));
			}
		}
		else{
			if(waitForAndGetElement(ELEMENT_DISPLAY_FORM_GROUP_CALENDAR_ITEM_UNCHECKBOX.replace("$name", calendarName),5000,0)!=null)
				click(ELEMENT_DISPLAY_FORM_GROUP_CALENDAR_ITEM_UNCHECKBOX.replace("$name", calendarName));
			waitForAndGetElement(ELEMENT_DISPLAY_FORM_GROUP_CALENDAR_ITEM_CHECKBOX.replace("$name", calendarName));
		}
	}*/

	/**
	 * Add/Edit new feed
	 * @param name
	 * 				name of feed
	 * @param url
	 * 				url of feed
	 * @param calendars
	 * 				calendar  list (split by "/": ex: John Smith/Development)
	 */
	public void addEditFeed(String name, String url, String calendars){
		info("Add new feed");
		if(name!=null && name!=""){
			type(ELEMENT_FEED_NAME_INPUT,name,true);
		}
		if(url!=null && url!=""){
			type(ELEMENT_FEED_URL_INPUT,name,true);
		}
		if(calendars!=null && calendars!=""){
			String[] calendar = calendars.split("/");
			for(int i =0; i<calendar.length; i++){
				select(ELEMENT_FEED_CALENDAR_OPTION,calendar[i]);
				click(ELEMENT_FEED_EDIT_FEED_ADD_CALENDAR);
			}
		}
	}

	/**
	 * Generate URL
	 */
	public void generateUrl(){
		info("Generate URL");
		click(ELEMENT_FEED_EDIT_FEED_GENERATE_URL);
	}

	/**
	 * Reset URL
	 */
	public void resetUrl(){
		info("Reset URL");
		click(ELEMENT_FEED_EDIT_FEED_RESET_URL);
	}

	/**
	 * delete calendar from feed
	 * @param calendar
	 * 					name of calendar
	 */
	public void deleteCalendar(String calendar){
		info("delete calendar from feed");
		if(calendar!=null && calendar!=""){
			click(ELEMENT_FEED_EDIT_FEED_DELETE_CALENDAR.replace("$name", calendar));
			waitForElementNotPresent(ELEMENT_FEED_EDIT_FEED_DELETE_CALENDAR.replace("$name", calendar));
		}
	}
	
	/**
	 * Delete Feed
	 * @param feed
	 * 				name of feed
	 * @param isVerify
	 * 				true: verify confirm message
	 * 				false: not verify confirm message
	 */
	public void deleteFeed(String feed, Boolean isVerify){
		info("Delete Feed");
		ManageAlert alert = new ManageAlert(driver);
		Button button = new Button(driver);
		click(ELEMENT_FEED_LIST_ITEM_DELETE_BUTTON.replace("$name", feed));
		if(isVerify!=null){
			if(isVerify)
				alert.verifyAlertMessage(ELEMENT_FEED_CONFIRM_DELETE);
			else
				click(button.ELEMENT_YES_BUTTON);
		}
		else
			click(button.ELEMENT_YES_BUTTON);
	}
	
	
	
	/**
	 * 
	 * right click on an even to show context menu
	 */
	public enum contextMenuEditEvenOption{
		VIEW,EDIT,DELETE,DELETE_RECURRING,EXPORT;
	}
	/**
	 * Select an option in context menu
	 * @param option
	 */
	public void selectOptionByRightclickOnEvent(contextMenuEditEvenOption option){
		switch(option){
		case VIEW:
			info("Select View option");
			click(ELEMENT_CONTEXT_MENU_VIEW);
			break;
		case EDIT:
			info("Select Edit option");
			click(ELEMENT_CONTEXT_MENU_EDIT);
			break;
		case DELETE:
			info("Select Delete option");
			click(ELEMENT_CONTEXT_MENU_DELETE);
			click(ELEMENT_CONFIRM_POPUP_OK);
			break;
		case DELETE_RECURRING:
			info("Select Delete option");
			click(ELEMENT_CONTEXT_MENU_DELETE);
			break;
		case EXPORT:
			info("Select Export option");
			click(ELEMENT_CONTEXT_MENU_EXPORT);
			break;
		default:
			info("No option to select");
			break;
		}
	}
	/**
	 * Open edit event form by right click on the event
	 * @param name
	 */
	public void openEditPopupEventByRightClick(String name){
		info("Right click on an Event/Task");
		rightClickOnElement(ELEMENT_EVENT_TASK_TITLE.replace("${name}",name));
		selectOptionByRightclickOnEvent(contextMenuEditEvenOption.EDIT);
		waitForAndGetElement(ELEMENT_ADD_EDIT_EVENT_POPUP,2000,1);
		info("Edit/Task form is shown");
	}
	
	/**
	 * Open add/edit event popup by double clicking
	 * @param name
	 * @param opt is an instance of a repeated event as 1,2,3,4....
	 */
	public void openEditEventTaskPopup(String name, String... opt) {
		info("Edit an event");
		Actions action = new Actions(this.driver);
		if (opt.length > 0 && opt[0] != null) {
			info("Double click on the event");
			scrollElementIntoView(this.driver.findElement(By
					.xpath(ELEMENT_EVENT_TASK_NUMBER_RECURRING.replace("${name}",name).replace("${number}",opt[0]))));
			action.moveToElement(
					waitForAndGetElement(ELEMENT_EVENT_TASK_NUMBER_RECURRING.replace("${name}",name).replace("${number}",opt[0]))).doubleClick().perform();
		}else{
			info("Double click on the event");
			scrollElementIntoView(this.driver.findElement(By
					.xpath(ELEMENT_EVENT_TASK_TITLE.replace("${name}", name))));
			action.moveToElement(
					waitForAndGetElement(ELEMENT_EVENT_TASK_TITLE.replace(
							"${name}", name))).doubleClick().perform();
		}
		waitForAndGetElement(ELEMENT_ADD_EDIT_EVENT_POPUP,4000,0);
		info("The edit form is shown");
	}
	/**
	 * Remove an event or a task on Week tab
	 * @param name
	 */
	public void deleteTaskEvent(String name){
			info("Right click on an Event/Task");
			scrollElementIntoView(this.driver.findElement(By.xpath(ELEMENT_EVENT_TASK_TITLE.replace("${name}",name))));
			rightClickOnElement(ELEMENT_EVENT_TASK_TITLE.replace("${name}",name));
			selectOptionByRightclickOnEvent(contextMenuEditEvenOption.DELETE);
			waitForElementNotPresent(ELEMENT_EVENT_TASK_TITLE.replace("${name}",name));
	}
	
	
	/**
	 * Remove an event or a task on List tab
	 * @param name
	 */
	public void deleteAllTaskEvent(String listTab) {
		goToTab(listTab);
		if (waitForAndGetElement(ELMENT_CALENDAR_TAB_LIST_EMPTY, 3000, 0) == null) {
			info("Select all task/events in the list tab");
			check(ELEMENT_CALENDAR_LIST_TAB_SELECT_ALL_CHECKBOX, 2);
			click(ELEMENT_CALENDAR_LIST_TAB_DELETE_BUTTON);
			if (waitForAndGetElement(ELEMENT_CONFIRM_POPUP_OK, 2000, 0) != null)
				click(ELEMENT_CONFIRM_POPUP_OK);
			if (waitForAndGetElement(evMg.ELEMENT_EDIT_DELETE_ONE_EVENT,2000, 0) != null){
				evMg.deleteRecurringConfirm(recurringType.ALL_EVENT);
			}
			if (waitForAndGetElement(ELEMENT_CONFIRM_POPUP_DELETE, 2000, 0) != null)
				click(ELEMENT_CONFIRM_POPUP_DELETE);
		}
		Utils.pause(2000);

	}
	/**
	 * Go to a tab in action bar
	 * @param tab
	 */
	public void goToTab(String tab){
		info("Select a tab");
		click(ELEMENT_CALENDAR_TAB.replace("${name}",tab));
		Utils.pause(2000);
	}
	/**
	 * Scroll to element to view
	 * @param element
	 */
	public void scrollElementIntoView(WebElement element) {
		info("Scroll to the element to view");
	    ((JavascriptExecutor) this.driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	/**
	 * function: check content of mail then delete mail in email server
	 * @param titleEventTask
	 * @param fileAttch
	 * @param link
	 * @param user
	 * @param opParams
	 */
	public void checkEmailNotificationCalendar(String titleEventTask,String fileAttch,String link,String user,Object... opParams){
		info("Check and delete mail");
		Boolean checkOrNo = (Boolean)(opParams.length > 0 ? opParams[0]: true);
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		  for(String windowHandle  : driver.getWindowHandles()){
			     driver.switchTo().window(windowHandle);
			     info("driver.title:"+driver.getTitle());
		}
		if (opParams.length > 0) {
			if (checkOrNo == true)
				waitForAndGetElement(ELEMENT_GMAIL_CONTENT_INVITATION_EMAIL.replace("$eventTask",titleEventTask),30000,1);
            else 
            	waitForElementNotPresent(ELEMENT_GMAIL_CONTENT_INVITATION_EMAIL.replace("$eventTask",titleEventTask),30000,1);
		}
		if(opParams.length>1){
			info("Click on the email");
			click(ELEMENT_GMAIL_CONTENT_INVITATION_EMAIL.replace("$eventTask",titleEventTask));
			Utils.pause(2000);
			for(String windowHandle  : driver.getWindowHandles()){
			     driver.switchTo().window(windowHandle);
			     info("driver.title:"+driver.getTitle());
		    }
			if(!fileAttch.isEmpty())
				waitForAndGetElement(ELEMENT_GMAIL_EMAIL_ICS_FILE.replace("$file",fileAttch));
			
			if(!link.isEmpty()){
				info("Click on the link");
				click(ELEMENT_GMAIL_EMAIL_DETAIL_LINK.replace("$link",link));
				for(String windowHandle  : driver.getWindowHandles()){
				     driver.switchTo().window(windowHandle);
				     info("driver.title:"+driver.getTitle());
			    }
				if (!user.isEmpty()) {
					if (waitForAndGetElement(ELEMENT_REFUSE_INVITATION_MESSAGE.replace("$user",user), 2000, 0) != null)
						assert true;
					else if (waitForAndGetElement(ELEMENT_MAYBE_INVITATION_MESSAGE.replace("$user",user), 2000, 0) != null)
						assert true;
					else if (waitForAndGetElement(ELEMENT_ACCEPT_INVITATION_MESSAGE.replace("$user",user), 2000, 0) != null)
						assert true;
					else
						assert false : "Wrong message or Cannot access to the link";
				}
			}
		}
	}
	/**
	 * Open display calendar tab in Calendar setting form
	 */
	public void goToDisplayCalendarTab(){
		info("Click on the Display calendar tab");
		click(ELEMENT_CALENDAR_SETTING_DISPLAY_TAB);
		Utils.pause(2000);
	}
	/**
	 * Open Feed tab in Calendar setting form
	 */
	public void goToFeedTab(){
		info("Click on the Display calendar tab");
		click(ELEMENT_CALENDAR_SETTING_FEED_TAB);
		Utils.pause(2000);
	}
}

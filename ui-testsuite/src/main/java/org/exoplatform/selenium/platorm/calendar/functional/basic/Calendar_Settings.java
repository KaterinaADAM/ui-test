package org.exoplatform.selenium.platorm.calendar.functional.basic;

import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.calendar.CalendarBase;
import org.exoplatform.selenium.platform.calendar.Event;
import org.exoplatform.selenium.platform.calendar.Task;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Calendar_Settings extends CalendarBase {
	
	ManageAccount acc;
	Event evt;
	Task tsk;
	@BeforeMethod
	public void setUpBeforeTest(){
		initSeleniumTest();
		acc = new ManageAccount(driver);
		evt = new Event(driver, this.plfVersion);
		tsk = new Task(driver);
		acc.signIn(DATA_USER1, DATA_PASS);
		goToCalendarPage();

	}

	@AfterMethod
	public void afterTest(){
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	/**
	 * Check displaying of Displayed Calendars tab
	 */
	@Test
	public void test01_CheckDisplayingOfDisplayedCalendarsTab(){
		String title[]={"John","Content Management","Development","Executive Board","Employees","Users","Administration"};

		goToCalendarSettings();
		click(ELEMENT_DISPLAYED_CALENDAR);
		for ( int i =0; i<7;i++)
			waitForAndGetElement(ELEMENT_DISPLAYED_CALENDAR_CHECK.replace("${title}",title[i] ));
		
		for ( int i =0; i<7;i++)
			waitForAndGetElement(ELEMENT_LEFT_CALENDAR.replace("${calendar}", title[i]));
		
	}
	
	/**
	 * Check displaying of scroll in Displayed Calendars tab
	 */
	@Test
	public void test02_CheckDisplayingOfScrollInDisplayedCalendarsTab(){
		String titleCal="calTest";
		
		for ( int i =1; i<10;i++){
			goToAddCalendar();
			evt.addCalendar(titleCal+i,"" , "blue");
			}
		
		goToCalendarSettings();
		click(ELEMENT_DISPLAYED_CALENDAR);
		assert checkExitScrollBar(ELEMENT_DISPLAYED_CALENDAR_ZONE);
		
		for ( int i =1; i<10;i++){
			deleteCalendar(titleCal+i);
		}	
	}
	
	/**
	 * Check displaying of Settings popup's height
	 * Pending : impossible to check pixel value
	 */
	@Test(groups="pending")
	public void test03_CheckDisplayongOfSettingsPopusHeight(){
		
	}
	
	/**
	 * Set always send invitation
	 * ID : 116454
	 */
	@Test
	public void test04_SetAlwaysSendInvitation(){
		String choice="Always";
		goToCalendarSettings();
		check(By.xpath(ELEMENT_SEND_INVITATION_CHOOSE.replace("${choose}",choice)),2);
		click(ELEMENT_SETTINGS_FORM_SAVE_BUTTON);
		goToCalendarSettings();
		waitForAndGetElement(By.xpath(ELEMENT_SEND_INVITATION_CHOOSE_CHECKED.replace("${choose}", choice)));
	}
	
	/**
	 * Set Ask send invitation
	 * ID : 116455
	 */
	@Test
	public void test05_SetAskSendInvitation(){
		String choice="Ask";
		goToCalendarSettings();
		check(By.xpath(ELEMENT_SEND_INVITATION_CHOOSE.replace("${choose}",choice)),2);
		click(ELEMENT_SETTINGS_FORM_SAVE_BUTTON);
		goToCalendarSettings();
		waitForAndGetElement(By.xpath(ELEMENT_SEND_INVITATION_CHOOSE_CHECKED.replace("${choose}", choice)), DEFAULT_TIMEOUT,1,2);
	}
	
	/**
	 * Set never send invitation
	 * ID : 116533
	 */
	@Test
	public void test06_SetNeverSendInvitation(){
		String choice="Never";
		goToCalendarSettings();
		check(By.xpath(ELEMENT_SEND_INVITATION_CHOOSE.replace("${choose}",choice)),2);
		click(ELEMENT_SETTINGS_FORM_SAVE_BUTTON);
		goToCalendarSettings();
		waitForAndGetElement(By.xpath(ELEMENT_SEND_INVITATION_CHOOSE_CHECKED.replace("${choose}", choice)));
	}
}

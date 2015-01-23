package org.exoplatform.selenium.platform;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.TestBase;
import org.exoplatform.selenium.platform.answer.AnswerHomePage;
import org.exoplatform.selenium.platform.answer.FaqHomePage;
import org.exoplatform.selenium.platform.calendar.CalendarHomePage;
import org.exoplatform.selenium.platform.forum.ForumHomePage;
import org.exoplatform.selenium.platform.social.SpaceManagement;

import org.exoplatform.selenium.platform.wiki.WikiHomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePagePlatform extends TestBase{
	WikiHomePage wHome;
	CalendarHomePage cHome;
	SpaceManagement sMang;
	AnswerHomePage aHome;
	ForumHomePage fHome;
	FaqHomePage fqHome;

	//Left panel
	public final By ELEMENT_FORUM_LINK_PLF=By.xpath("//*[@data-original-title='Forums']");
	public final By ELEMENT_ANSWER_LINK_PLF=By.xpath("//*[@data-original-title='Answer']");
	public final By ELEMENT_WIKI_LINK_PLF=By.xpath("//*[@data-original-title='Wiki']");
	public final By ELEMENT_HOME_LINK_PLF=By.xpath("//*[@data-original-title='Home']");
	public final By ELEMENT_CALENDAR_LINK_PLF=By.xpath("//*[@data-original-title='Calendar']");


	//Wiki activity
	public final String ELEMENT_WIKI_COMMENT_EDIT_TITLE = "//*[text()='${title}']/../../../..//*[@class='contentComment' and contains(text(), 'title has been updated to: ${title}')]";
	public final String ELEMENT_WIKI_COMMENT_EDIT_CONTENT = "//*[text()='${title}']/../../../..//*[@class='contentComment' and contains(text(), 'content has been edited')]";
	public final String ELEMENT_ACTIVITY_WIKI_TITLE = "//*[@class='linkTitle' and text()='${title}']";
	public final String ELEMENT_ACTIVITY_WIKI_CONTENT = "//*[@class='linkTitle' and text()='${title}']/../../..//*[@class='contentWiki theContent']/*[@class='text']";
	public final String ELEMENT_ACTIVITY_WIKI_VERSION = "//*[@class='linkTitle' and text()='${title}']/../..//*[@class = 'pull-right versionLabel' and contains(text(), 'Version: ${version}')]";
	public final String ELEMENT_ACTIVITY_MOVE_WIKI_PAGE = "//*[text()='${title}']/../../../..//*[@class='contentComment' and contains(text(), 'Page has been moved to: ${path}')]";
	public final By ELEMENT_MY_SPACE_LINK_PLF=By.xpath("//*[@id='UISpaceNavigationPortlet']//*[contains(text(),'My Spaces')]");
	public final String ELEMENT_SPECIFIC_SPACE_LINK_PLF ="//*[@id='UISpaceNavigationPortlet']//*[contains(text(),'{$space}')]";

	//Middle homepage panel
	public final By ELEMENT_HOMPAGE_MIDDLE_PANEL = By.id("OfficeMiddle");
	
	public final By ELEMENT_SITE_TOP_LIST = By.xpath(".//*[@id='UIAdminToolbarContainer']/ul/li[3]/a");
	public final By ELEMENT_ADD_PAGE_DROP_LIST = By.xpath(".//*[@id='UIAdminToolbarContainer']/ul/li[3]/ul[@class='dropdown-menu']/li[4]/a");
	public final By ELEMENT_EDITSITE_SAVEBTN = By.xpath("//*[@class='btn' and text()='Save']");
	
	//Site Explorer activities
	public final By ELEMENT_SITEMAPS_ACTIVITY = By.xpath(".//*[@data-original-title='sitemaps']/../../../..//div[@class='commentItem commentItemLast']//p[text()='File has been updated.']");
	
	//SEO Management
	public final By ELEMENT_SEO_LANGUAGE_SHOW = By.xpath("//*[@onClick='eXo.ecm.WCMUtils.showSEOLanguage(true)']");
	public final By ELEMENT_SEO_LANGUAGE_SELECTBOX = By.xpath("//*[@name='language']");
	public final By ELEMENT_SEO_TITLEBOX = By.xpath("//*[@id='title']");
	public final By ELEMENT_SEO_DELETE = By.xpath("//*[@title='Delete']");
	public final By ELEMENT_SEO_HELPDESC = By.xpath("//*[text()='Description: ']/..//*[@id='DescriptionHelp']");
	public final By ELEMENT_SEO_HELPKEYWORD = By.xpath("//*[text()='Keywords: ']/..//*[@id='DescriptionHelp']");
	public final By ELEMENT_SEO_HELPPRIORITY = By.xpath("//*[@id='PriorityHelp']");

	/**
	 * constructor
	 * @param dr
	 */
	public HomePagePlatform(WebDriver dr){
		this.driver=dr;
		wHome = new WikiHomePage(dr);
		cHome = new CalendarHomePage(dr);
	
		sMang = new SpaceManagement(dr);
		aHome = new AnswerHomePage(dr);
		fHome = new ForumHomePage(dr);
		fqHome = new FaqHomePage(dr);
	}

	/**
	 * Go to Wiki portlet
	 */
	public void goToWiki(){
		info("--Go to Wiki--");
		click(ELEMENT_WIKI_LINK_PLF);
		waitForAndGetElement(wHome.ELEMENT_WIKI_HOME_PAGE_LINK);	
	}

	/**
	 * Go to Home page
	 */
	public void goToHomePage(){
		info("--Go to Home page--");
		info("Click on Home link of intranet page");
		click(ELEMENT_HOME_LINK_PLF);
		info("Verify that the home page of intranet is shown");
		waitForAndGetElement(ELEMENT_HOMPAGE_MIDDLE_PANEL);
		info("the intranet is shown sucessfully"); 
	}

	/**
	 * Go to Home Calendar Page
	 */
	public void goToCalendarPage(){
		info("-- Go to calendar home page --");
		info("click on Calendar link");
		click(ELEMENT_CALENDAR_LINK_PLF);
		info("Verify that Calendar page is shown");
		waitForAndGetElement(cHome.ELEMENT_CALENDAR_WORKING_PANEL);
		info("The calendar page is shown successfully");
	}

	/**
	 * Go to my spaces
	 */
	public void goToMySpaces(){
		info("-- Go to my spaces --");
		click(ELEMENT_MY_SPACE_LINK_PLF);
	}

	/**
	 * Go to answer page
	 */
	public void goToAnswer(){
		info("-- Go to answer page --");
		click(ELEMENT_ANSWER_LINK_PLF);
		waitForAndGetElement(aHome.ELEMENT_ANSWER_PORTLET);
	}

	/**
	 * Go to forum page
	 */
	public void goToForum(){
		info("-- Go to forum page --");
		info("Click on Forum link");
		click(ELEMENT_FORUM_LINK_PLF);
		info("Verify that the forum portlet is shown");
		waitForAndGetElement(fHome.ELEMENT_FORUM_PORTLET);
		info("The forum portlet is shown successfully");
	}

	/**
	 * Go to faq page
	 */
	public void goToFaq(){
		if (baseUrl==null) baseUrl = DEFAULT_BASEURL;
		info("Base url is " + baseUrl);
		String url = baseUrl + "/intranet/home/FAQ";
		info("-- Go to FAQ page --");
		driver.get(url);
		waitForAndGetElement(fqHome.ELEMENT_FAQ_QUESTION_LIST);
	}

	/**
	 * Go to content administration
	 *//*
	public void goToPageAdministration(){
		info("Go to content administration");
		click(ELEMENT_TOPBAR_ADMINISTRATION_BUTTON);
		mouseOver(ELEMENT_TOPBAR_CONTENT, true);
		click(ELEMENT_CONTENT_TOPBAR_ADMINISTRATION);
	}*/
	
}


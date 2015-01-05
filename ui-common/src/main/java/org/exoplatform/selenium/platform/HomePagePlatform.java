package org.exoplatform.selenium.platform;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.TestBase;
import org.exoplatform.selenium.platform.answer.AnswerHomePage;
import org.exoplatform.selenium.platform.answer.FaqHomePage;
import org.exoplatform.selenium.platform.calendar.CalendarHomePage;
import org.exoplatform.selenium.platform.ecms.SiteExplorerHome;
import org.exoplatform.selenium.platform.forum.ForumHomePage;
import org.exoplatform.selenium.platform.social.SpaceManagement;

import org.exoplatform.selenium.platform.wiki.WikiHomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePagePlatform extends TestBase{
	WikiHomePage wHome;
	CalendarHomePage cHome;
	SiteExplorerHome SEHome;
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
	//Tool bar
	public final By ELEMENT_TOOLBAR_ADMINISTRATION = By.xpath("//*[@class='uiIconPLF24x24Setup']");

	//Administration
	public final By ELEMENT_ADMINISTRATION_CONTENT = By.xpath("//*[text()='Content']");
	public final By ELEMENT_ADMINISTRATION_SITEEXPLORER = By.xpath("//*[text()='Sites Explorer']");

		// administration panel
	public final By ELEMENT_TOPBAR_ADMINISTRATION_BUTTON =By.xpath(".//*[@id='UISetupPlatformToolBarPortlet']/a");
	public final By ELEMENT_TOPBAR_CONTENT = By.xpath("//*[@id='UISetupPlatformToolBarPortlet']//a[contains(text(),'Content')]");
	public final By ELEMENT_CONTENT_TOPBAR_ADMINISTRATION = By.xpath("//*[@id='UISetupPlatformToolBarPortlet']//a[contains(text(),'Content Administration')]");
	
		// Edit panel
	public final By ELEMENT_EDIT_BUTTON = By.xpath(".//*[@id='UIAdminToolbarContainer']//*[@class='uiIconPLF24x24Edit']");
	public final By ELEMENT_SITE_TOP_LIST = By.xpath(".//*[@id='UIAdminToolbarContainer']/ul/li[3]/a");
	public final By ELEMENT_ADD_PAGE_DROP_LIST = By.xpath(".//*[@id='UIAdminToolbarContainer']/ul/li[3]/ul[@class='dropdown-menu']/li[4]/a"); 


	/**
	 * constructor
	 * @param dr
	 */
	public HomePagePlatform(WebDriver dr){
		this.driver=dr;
		wHome = new WikiHomePage(dr);
		cHome = new CalendarHomePage(dr);
		SEHome = new SiteExplorerHome(dr);
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
		click(ELEMENT_HOME_LINK_PLF);
		waitForAndGetElement(ELEMENT_HOMPAGE_MIDDLE_PANEL);
	}

	/**
	 * Go to Home Calendar Page
	 */
	public void goToCalendarPage(){
		info("-- Go to calendar home page --");
		click(ELEMENT_CALENDAR_LINK_PLF);
		waitForAndGetElement(cHome.ELEMENT_CALENDAR_WORKING_PANEL);
	}


	public void goToSiteExplorer() {
		info("-- Go to site explorer home page --");
		click(ELEMENT_TOOLBAR_ADMINISTRATION);
		mouseOver(ELEMENT_ADMINISTRATION_CONTENT, true);
		click(ELEMENT_ADMINISTRATION_SITEEXPLORER);
		waitForAndGetElement(SEHome.ELEMENT_SITEEXPLORER_WORKING_PANEL);
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
		click(ELEMENT_FORUM_LINK_PLF);
		waitForAndGetElement(fHome.ELEMENT_FORUM_PORTLET);
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
	 */
	public void goToPageAdministration(){
		info("Go to content administration");
		click(ELEMENT_TOPBAR_ADMINISTRATION_BUTTON);
		mouseOver(ELEMENT_TOPBAR_CONTENT, true);
		click(ELEMENT_CONTENT_TOPBAR_ADMINISTRATION);

	}
}


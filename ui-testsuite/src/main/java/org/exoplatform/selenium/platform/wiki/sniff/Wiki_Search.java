package org.exoplatform.selenium.platform.wiki.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.platform.HomePageActivity;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.wiki.Template;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/** 
 * @author lientm
 * @date: 5-July-2013
 */

public class Wiki_Search extends Template {
	ManageAccount magAc;
	NavigationToolbar naTool;
	HomePageActivity activity;

	@BeforeMethod
	public void setUpBeforeTest(){
		initSeleniumTest();
		driver.get(baseUrl);
		magAc = new ManageAccount(driver);
		naTool = new NavigationToolbar(driver);
		activity = new HomePageActivity(driver);
		magAc.signIn("john", "gtn");
		goToWiki();
	}
	
	@AfterMethod
	public void afterTest(){
		driver.manage().deleteAllCookies();
		driver.quit();
	}
	
	/**CaseId: 68844
	 * Quick search
	 */
	@Test
	public void test01_QuickSearch(){
		String title = "Wiki_search_title_01";
		String content = "line1/line2/line3/line4/line5";
		String result = ELEMENT_PAGE_RESULT.replace("${title}", title);
		
		info("Add new wiki page");		
		addWikiPageWithContentMultiLine(title, content);
		
		quickSearch("line5");
		assert getText(ELEMENT_SEARCH_RESULT) != "0";
		waitForAndGetElement(result);
		
		click(result);
		deleteCurrentWikiPage();
	}
	
	/**CaseId: 68845
	 * Advanced search -> wait to merge SOC common functions (need create page in space)
	 * 
	 */
	@Test (groups = "pending")
	public void test02_AdvancedSearch(){
		String title = "Wiki_search_title_02";
		String content = "line1/line2/line3/line4/line5";
		String space = "";
		
		//create space
		info("Add new wiki page");		
		addBlankWikiPage(title, content, 0);
		
		goToWiki();
		advancedSearch("line5", "/spaces/" + space);
		assert getText(ELEMENT_SEARCH_RESULT) != "0";
		waitForAndGetElement(ELEMENT_PAGE_RESULT.replace("${title}", title));
		
		goToWikiHome();
		click(By.linkText(title));
		deleteCurrentWikiPage();
	}
	
	/**CaseId: 70263
	 * Search template
	 */
	@Test
	public void test03_SearchTemplate(){
		String title = "Wiki template title 03";
		String description = "Global";
		String content = "Wiki template content 03";

		info("Search template when the key is matched");
		goToTemplateManagement();
		addTemplate(title, description, content);

		searchTemplate("template");
		waitForAndGetElement(ELEMENT_NEW_TEMPLATE_LINK.replace("${TEMPLATE_TITLE}", title));

		deleteTemplate(title);
	}
}

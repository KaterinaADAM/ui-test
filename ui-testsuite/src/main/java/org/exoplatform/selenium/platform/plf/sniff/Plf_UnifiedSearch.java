package org.exoplatform.selenium.platform.plf.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.exoplatform.selenium.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.*;


public class Plf_UnifiedSearch extends Plf_TestConfig{


	/**
	 *<li> Case ID:120868.</li>
	 *<li> Test Case Name: Quick Search.</li>
	 *<li> Pre-Condition: Some contents such as wiki, events, tasks, people...are existed, and contain text "cloud"</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test01_QuickSearch() {
		info("Test 1: Quick Search");
		/*Step Number: 1
		 *Step Name: Quick search
		 *Step Description: 
			- Log in Intranet
			- Type the text "cloud" at Quick search box
		 *Input Data: 

		 *Expected Outcome: 
			- By default, quick search returns results for items 
			with All types located in the current site only, as attachment SearchResult.png*/ 
		navToolBar.goToQuickSearch();
		quickSearch.search("cloud");
		waitForAndGetElement(quickSearch.ELEMENT_SEARCHRESULT_ALLTYPECHECK,5000,0);

	}

	/**
	 *<li> Case ID:120869.</li>
	 *<li> Test Case Name: Configure quick search.</li>
	 *<li> Pre-Condition: There is a page containing a Quick search portlet</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test02_ConfigureQuickSearch() {
		info("Test 2: Configure quick search");
		/*Step Number: 1
		 *Step Name: Configure Quick search
		 *Step Description: 
			- Login as admin, go to intranet home page
			- Open Quick search page
			- Click Edit this page
			- Click Edit portlet "Quick search"
			- Set value to fields
			- Click Save settings,
		 *Input Data: 

		 *Expected Outcome: 
			- Quick search settings screen is shown.
			- value is save*/ 
		navToolBar.goToQuickSearch();
		quickSearch.search("log");
		navToolBar.goToEditLayout();
		pagEditor.goToEditPortlet(pagEditor.ELEMENT_FRAME_CONTAIN_PORTLET);
		select(pagEditor.ELEMENT_EDIT_PORTLET_FORM_RESULTPERPAGE, "5");
		click(pagEditor.ELEMENT_EDIT_PORTLET_FORM_SAVESETTINGS_BUTTON);
		alert.acceptAlert();
		click(pagEditor.ELEMENT_EDIT_PORTLET_FORM_CLOSE_BUTTON);
		click(pagEditor.ELEMENT_EDIT_PORTLET_FINISH);
		waitForAndGetElement(quickSearch.ELEMENT_QUICKSEARCHRESULT_NUMBEROFRESULT,5000,0);
	}

	/**
	 *<li> Case ID:120870.</li>
	 *<li> Test Case Name: Sort search result.</li>
	 *<li> Pre-Condition: Some contents (such as wiki pages, events, tasks...) having the word "dinner" are existed.</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test03_SortSearchResult() {
		info("Test 3: Sort search result");
		/*Step Number: 1
		 *Step Name: Quick search
		 *Step Description: 
			- Login to Intranet
			- Type the word "dinner" into search box on top navigation bar
			- Click on Search icon or press Enter key
		 *Input Data: 

		 *Expected Outcome: 
			- By default, quick search returns results for items located in the current site only, as attachment SearchResult.png*/
		navToolBar.goToQuickSearch();
		type(quickSearch.ELEMENT_TOOLBAR_QUICKSEARCH_TEXTBOX,"dinner",true);
		click(quickSearch.ELEMENT_QUICKSEARCHRESULT_SEE_ALL_SEARCH);
		/*action.sendKeys(Keys.ENTER);
		action.perform();
		action.release();*/
		/*Step number: 2
		 *Step Name: Sort search results
		 *Step Description: 
			- Click on Sort By combo, select one of sets "Relevance", "Date", "Title"
		 *Input Data: 

		 *Expected Outcome: 
			Search result will sorted by Relevance, or Date, or Title respectively*/ 
		click(quickSearch.ELEMENT_QUICKSEARCHRESULT_SORTBY);
		Utils.pause(2000);
		click(quickSearch.ELEMENT_QUICKSEARCHRESULT_SORTBY_DATE);
		waitForAndGetElement((quickSearch.ELEMENT_SEARCHRESULT_CONTENT_FILE_TITLE).replace("${name}", "Immunity"),5000,0);
	}

	/**
	 *<li> Case ID:120871.</li>
	 *<li> Test Case Name: Filter search.</li>
	 *<li> Pre-Condition: Some contents such as wiki, events, tasks, people, pages...are existed, and contain text "cloud"</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test04_FilterSearch() {
		info("Test 4: Filter search");
		/*Step Number: 1
		 *Step Name: Quick search
		 *Step Description: 
			- Login and Open intranet home or ACME homepage
			- Type the text "cloud" in search box in top navigation
			- Press Enter key, or click Search icon
		 *Input Data: 

		 *Expected Outcome: 
			- By default, quick search returns results for items located in the current site only, as attachment SearchResult.png*/
		navToolBar.goToQuickSearch();
		quickSearch.search("cloud");
		/*Step number: 2
		 *Step Name: Filter search
		 *Step Description: 
			On filter area, click on fields that you want to search
		 *Input Data: 

		 *Expected Outcome: 
			The page will search only selected fields for results*/ 
		uncheck(quickSearch.ELEMENT_SEARCHRESULT_ALLTYPECHECK, 2);
		check(quickSearch.ELEMENT_SEARCHRESULT_FILESTYPECHECK, 2);
		waitForAndGetElement((quickSearch.ELEMENT_SEARCHRESULT_CONTENT_FILE_TITLE).replace("${name}", "metro.pdf"),5000,0);
	}

	/**
	 *<li> Case ID:120875.</li>
	 *<li> Test Case Name: Configure Search page.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test05_ConfigureSearchPage() {
		info("Test 5: Configure Search page");
		/*Step Number: 1
		 *Step Name: Go to search page
		 *Step Description: 
			- Log in to intranet
			- Enter a keyword into the Search box on the administration bar, then press Enter
		 *Input Data: 

		 *Expected Outcome: 
			Seach page is shown*/
		navToolBar.goToQuickSearch();
		quickSearch.search("log");
		/*Step number: 2
		 *Step Name: Configure search page
		 *Step Description: 
			- Click Edit > Page > Edit Layout on the administration bar
			- Click Edit portlet icon
			- Set value to fields
			- Click Save Settings
			- Click Close
			- Click Finish icon on the Page Editor
		 *Input Data: 

		 *Expected Outcome: 
			- Edit Mode settings screen is shown.
			- value is save
			- Quit the Page editor*/ 
		navToolBar.goToEditLayout();
		pagEditor.goToEditPortlet(pagEditor.ELEMENT_FRAME_CONTAIN_PORTLET);
		select(pagEditor.ELEMENT_EDIT_PORTLET_FORM_RESULTPERPAGE, "5");
		click(pagEditor.ELEMENT_EDIT_PORTLET_FORM_SAVESETTINGS_BUTTON);
		alert.acceptAlert();
		click(pagEditor.ELEMENT_EDIT_PORTLET_FORM_CLOSE_BUTTON);
		click(pagEditor.ELEMENT_EDIT_PORTLET_FINISH);
		waitForAndGetElement(quickSearch.ELEMENT_QUICKSEARCHRESULT_NUMBEROFRESULT,5000,0);
	}

	/**
	 *<li> Case ID:120876.</li>
	 *<li> Test Case Name: Administrate the unified search engine.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test06_AdministrateTheUnifiedSearchEngine() {
		info("Test 6: Administrate the unified search engine");
		/*Step Number: 1
		 *Step Name: Open Search Administration
		 *Step Description: 
			- Login and go to intranet home page
			- Click Administration > Content > Search menu of the navigation bar
		 *Input Data: 

		 *Expected Outcome: 
			The search admin has a table with 3 columns : Content Type, Description, and Actions, as attachment searchAdmin.png*/
		navToolBar.goToAdminSearch();
		info("Content type is shown");
		waitForAndGetElement(seaAdmin.ELEMENT_SEARCH_ADMINISTRATION_COLUMMN_TYPE_TITLE,2000,0);
		info("Description is shown");
		waitForAndGetElement(seaAdmin.ELEMENT_SEARCH_ADMINISTRATION_COLUMMN_TYPE_DESCRIPTION,2000,0);
		info("Action is shown");
		waitForAndGetElement(seaAdmin.ELEMENT_SEARCH_ADMINISTRATION_COLUMMN_TYPE_ACTION,2000,0);
		/*Step number: 2
		 *Step Name: Enable a content type
		 *Step Description: 
			Click on Enable button matching a content type
		 *Input Data: 

		 *Expected Outcome:
		    - Disable button label is changed into Enable 
			- This content type will no longer appear in the search results, nor in the search apps settings 
			*/
		info("Click on Disable button");
		click(seaAdmin.ELEMENT_SEARCHADMIN_ACTION_DISABLE_BUTTON.replace("${type}","File"));
		info("Open Search page");
		navToolBar.goToQuickSearch();
		quickSearch.search("cloud");
		info("Verify that File checkbox is not shown");
		waitForElementNotPresent(quickSearch.ELEMENT_SEARCHRESULT_FILESTYPECHECK);
		/*Step number: 3
		 *Step Name: Disable a content type
		 *Step Description: 
			Click on Disable button matching a content type
		 *Input Data: 

		 *Expected Outcome: 
			- The Enable button label is changed into Disable 
			- The content type will reappear in the search results, and in the search apps settings.*/ 
		navToolBar.goToAdminSearch();
		info("Click on Enable button");
		click(seaAdmin.ELEMENT_SEARCHADMIN_ACTION_ENABLE_BUTTON.replace("${type}","File"));
		info("Open Search page");
		navToolBar.goToQuickSearch();
		quickSearch.search("cloud");
		waitForAndGetElement(quickSearch.ELEMENT_SEARCHRESULT_FILESTYPECHECK,2000,0);
	}

	/**
	 *<li> Case ID:120877.</li>
	 *<li> Test Case Name: Search files.</li>
	 *<li> Pre-Condition: Some files containing the word "cloud" are existed</li>
	 *<li> Post-Condition: </li>
	 *PENDING:CANNOT AUTOMATE, BECAUSE WHEN CLICK ON THE FILE, THE FILE WILL AUTO DOWNLOAD AND NOT OPEN AS REALLY BROWSER
	 */
	@Test(groups="pending")
	public  void test07_SearchFiles() {
		info("Test 7: Search files");
		/*Step Number: 1
		 *Step Name: Search files
		 *Step Description: 
			- Login Intranet
			- Type the text "cloud" into search box on the top navigation bar
			- Click on Search icon, or press Enter key
		 *Input Data: 

		 *Expected Outcome: 
			- Search results of files should display: file title, 
			the file location and the mimetype icon used in content explorer 
			- Item in search result is clickable and open it when user click*/ 
		navToolBar.goToQuickSearch();
		type(quickSearch.ELEMENT_TOOLBAR_QUICKSEARCH_TEXTBOX,"cloud",true);
		click(quickSearch.ELEMENT_QUICKSEARCHRESULT_SEE_ALL_SEARCH);
		/*action.sendKeys(Keys.ENTER);
		action.perform();*/
	    
		uncheck(quickSearch.ELEMENT_SEARCHRESULT_ALLTYPECHECK, 2);
		check(quickSearch.ELEMENT_SEARCHRESULT_FILESTYPECHECK, 2);
		
		info("Verify that the title is shown");
		waitForAndGetElement(quickSearch.ELEMENT_SEARCHRESULT_CONTENT_FILE_TITLE.replace("${name}", "metro.pdf"));
		info("Verify that location is shown");
		waitForAndGetElement(quickSearch.ELEMENT_SEARCHRESULT_CONTENT_FILE_LOCATION.replace("${name}","metro.pdf").replace("${location}","Managed Sites"));
		info("Verify that icon is shown");
		waitForAndGetElement(quickSearch.ELEMENT_SEARCHRESULT_CONTENT_FILE_ICON.replace("${name}","metro.pdf"));
		info("Verify that Item in search result is clickable and open it when user click ");
	
	}

	/**
	 *<li> Case ID:120878.</li>
	 *<li> Test Case Name: Search documents.</li>
	 *<li> Pre-Condition: Some documents containing the text "cloud" are existed on Site explorer</li>
	 *<li> Post-Condition: </li>
	 *PENDING: THIS CASE IS ERROR BECAUSE A MEMBER CAN VIEW A DOCUMENT WHEN IT IS NOT PUBLISHED
	 */
	@Test(groups="pending")
	public  void test08_SearchDocuments() {
		info("Test 8: Search documents");
		/*Step Number: 1
		 *Step Name: Search documents
		 *Step Description: 
			- Login Intranet
			- Type the text "cloud" into search box on the top navigation
			- Click on Search icon on the top navigation or press Enter key
		 *Input Data: 

		 *Expected Outcome: 
			- Search results should display: document title,and document type icon such as displayed in the content explorer
			- In published mode : For members of /platform/web
			- contributors, return search result with all status of document. Else,just return search result with published document.
			- In edit mode: return search result with all status of document
			- Item in search result is clickable and open it when user click*/ 
		String name ="Speed";
		navToolBar.goToQuickSearch();
		type(quickSearch.ELEMENT_TOOLBAR_QUICKSEARCH_TEXTBOX,"cloud",true);
		click(quickSearch.ELEMENT_QUICKSEARCHRESULT_SEE_ALL_SEARCH);
		/*action.sendKeys(Keys.ENTER);
		action.perform();*/
		uncheck(quickSearch.ELEMENT_SEARCHRESULT_ALLTYPECHECK, 2);
		check(quickSearch.ELEMENT_SEARCHRESULT_DOCTYPECHECK, 2);
		
		info("Verify that icon is shown");
		waitForAndGetElement(quickSearch.ELEMENT_SEARCHRESULT_CONTENT_DOC_ICON.replace("${name}", name));
		info("Verify that title is shown");
		waitForAndGetElement(quickSearch.ELEMENT_SEARCHRESULT_CONTENT_DOC_TITLE.replace("${name}",name));
		info("Verify that Item in search result is clickable and open it when user click ");
		click(quickSearch.ELEMENT_SEARCHRESULT_CONTENT_DOC_TITLE.replace("${name}",name));
		waitForAndGetElement(SEHome.ELEMENT_SITE_EXPLORER_RIGHT_COLUMN_CONTENT.replace("${title}",name));
	}

	/**
	 *<li> Case ID:120879.</li>
	 *<li> Test Case Name: Search Discussions.</li>
	 *<li> Pre-Condition: Forums, topics, posts containing the text "cloud" are already existed on Forum application.</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test09_SearchDiscussions() {
		info("Test 9: Search Discussions");
		String topic = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String nameCat = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String nameForum = txData.getContentByArrayTypeRandom(1)+getRandomNumber();

		hp.goToForum();
		forumCatMag.addCategorySimple(nameCat, "", content);
		forumMg.addForumSimple(nameForum, "", content);
		forumMg.goToStartTopic();
		foTopic.startTopic(topic, topic, "", "");

		hp.goToHomePage();
		/*Step Number: 1
		 *Step Name: Search Discussions
		 *Step Description: 
			- Login Intranet
			- Type the word "cloud" into search box on the top navigation
			- Click on Search icon or press Enter key
		 *Input Data: 

		 *Expected Outcome: 
			- Search results should display in the Search page:the topic icon such as displayed in the forum application, the post title, an excerpt, 
			forum name, the post date, the rating, the number of replies in the topic
			- Items in search result is clickable and open it when user click*/ 
		DateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM dd, yyyy");
		Calendar cal = Calendar.getInstance();
		String date = dateFormat.format(cal.getTime());
		info(date);
		
		navToolBar.goToQuickSearch();
		type(quickSearch.ELEMENT_TOOLBAR_QUICKSEARCH_TEXTBOX,topic,true);
		Actions action = new Actions(this.driver);
		action.sendKeys(Keys.ENTER);
		action.perform();
		uncheck(quickSearch.ELEMENT_SEARCHRESULT_ALLTYPECHECK, 2);
		check(quickSearch.ELEMENT_SEARCHRESULT_DISCTYPECHECK, 2);
		
		
		info("Verify that discussion icon is shown");
		waitForAndGetElement(quickSearch.ELEMENT_SEARCHRESULT_CONTENT_DISCUSSIONS_ICON.replace("${name}",topic),2000,0);
		info("Verify that post's title is shown");
		waitForAndGetElement(quickSearch.ELEMENT_SEARCHRESULT_CONTENT_DISCUSSIONS_POST_TITLE.replace("${name}",topic),2000,0);
		info("Verify that excerpt is shown");
		waitForAndGetElement(quickSearch.ELEMENT_SEARCHRESULT_CONTENT_DISCUSSIONS_EXCERPT.replace("${name}",topic),2000,0);
		info("Verify that forum name is shown");
		waitForAndGetElement(quickSearch.ELEMENT_SEARCHRESULT_CONTENT_DISCUSSIONS_FORUM_NAME.replace("${topic}",topic).replace("${forum}",nameForum),2000,0);
		info("Verify that post date is shown");
		waitForAndGetElement(quickSearch.ELEMENT_SEARCHRESULT_CONTENT_DISCUSSIONS_POST_DATE.replace("${topic}",topic).replace("${date}",date),2000,0);
		info("Verify that rating is shown");
		waitForAndGetElement(quickSearch.ELEMENT_SEARCHRESULT_CONTENT_DISCUSSIONS_RATING.replace("${topic}",topic),2000,0);
		info("Verify that number reply is shown");
		waitForAndGetElement(quickSearch.ELEMENT_SEARCHRESULT_CONTENT_DISCUSSIONS_NUMBER_REPLY.replace("${topic}",topic).replace("${number}","0 replies"),2000,0);
		info("Verify that Item in search result is clickable and open it when user click ");
		click(quickSearch.ELEMENT_SEARCHRESULT_CONTENT_DISCUSSIONS_POST_TITLE.replace("${name}",topic),0,true);
		waitForAndGetElement(foTopic.ELEMENT_POST_TITLE.replace("${title}", topic),5000,0);
	}

	/**
	 *<li> Case ID:120880.</li>
	 *<li> Test Case Name: Search tasks.</li>
	 *<li> Pre-Condition: Tasks which have a word "dinner" in their task name or note are existed on calendar with status: Need actions, In Progress, Completed and Cancelled</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test10_SearchTasks() {
		info("Test 10 Search tasks");

		String name = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String note = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToCalendarPage();
		taskMag.goToAddTaskFromActionBar();
		taskMag.inputBasicQuickTask(name,note);
		taskMag.saveQuickAddTask();

		hp.goToHomePage();

		/*Step Number: 1
		 *Step Name: Search tasks
		 *Step Description: 
			- Login and go to intranet home page
			- Type the word "dinner" into search box on Top navigation bar
			- Click on Search icon or press Enter key
		 *Input Data: 

		 *Expected Outcome: 
			- Search results should display:task icon, task name, task note, due date. 
			Only tasks that are in progress and need actions are listed in the Search result.
			- Item in search result is clickable and open it when user click*/ 
		navToolBar.goToQuickSearch();
		type(quickSearch.ELEMENT_TOOLBAR_QUICKSEARCH_TEXTBOX,name,true);
		Actions action = new Actions(this.driver);
		action.sendKeys(Keys.ENTER);
		action.perform();
		uncheck(quickSearch.ELEMENT_SEARCHRESULT_ALLTYPECHECK, 2);
		check(quickSearch.ELEMENT_SEARCHRESULT_TASKTYPECHECK, 2);
		
		info("Verify that task icon is shown");
		waitForAndGetElement(quickSearch.ELEMENT_SEARCHRESULT_CONTENT_TASK_ICON.replace("${name}",name),2000,0);
		info("Verify that task name is shown");
		waitForAndGetElement(quickSearch.ELEMENT_SEARCHRESULT_CONTENT_TASK_NAME_NOTE.replace("${name}",name),2000,0);
		info("Verify that task note is shown");
		waitForAndGetElement(quickSearch.ELEMENT_SEARCHRESULT_CONTENT_TASK_NAME_NOTE.replace("${name}",note),2000,0);
		info("Verify that due date is shown");
		waitForAndGetElement(quickSearch.ELEMENT_SEARCHRESULT_CONTENT_TASK_DUEDATE.replace("${name}",name),2000,0);
		info("Verify that Item in search result is clickable and open it when user click ");
		click(quickSearch.ELEMENT_SEARCHRESULT_CONTENT_TASK_NAME_NOTE.replace("${name}",name));
		waitForAndGetElement(calMg.ELEMENT_CALENDAR_PREVIEW_TASK_EVENT.replace("${name}",name),5000,0);
	}

	/**
	 *<li> Case ID:120881.</li>
	 *<li> Test Case Name: Search events.</li>
	 *<li> Pre-Condition: Some events containing the text "could" in Name, 
	 *description or Location are existed on Calendar. 
	 *Some events were created in the past dates, current date and future dates.</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test11_SearchEvents() {
		info("Test 11 Search events");

		String name = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String note = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		DateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM dd, yyyy");
		Calendar cal = Calendar.getInstance();
		String date = dateFormat.format(cal.getTime());
		info(date);

		hp.goToCalendarPage();
		eventMag.goToAddEventFromActionBar();
		eventMag.inputBasicQuickEvent(name, note);
		eventMag.saveQuickAddEvent();

		hp.goToHomePage();
		/*Step Number: 1
		 *Step Name: Search events
		 *Step Description: 
			- Log in Intranet
			- Type the text "cloud" into search box on Top navigation
			- Click on Search icon on the top navigation bar or press Enter key
		 *Input Data: 

		 *Expected Outcome: 
			- Search results should display:display event icon, event title, event description, start date and location. Only events of current date and future dates are listed in the Search results.
			- Item in search result is clickable and open it when user click*/ 
		navToolBar.goToQuickSearch();
		type(quickSearch.ELEMENT_TOOLBAR_QUICKSEARCH_TEXTBOX,name,true);
		Actions action = new Actions(this.driver);
		action.sendKeys(Keys.ENTER);
		action.perform();
		uncheck(quickSearch.ELEMENT_SEARCHRESULT_ALLTYPECHECK, 2);
		check(quickSearch.ELEMENT_SEARCHRESULT_EVENTSTYPECHECK, 2);
		
		info("Verify that the icon is shown");
		waitForAndGetElement(quickSearch.ELEMENT_SEARCHRESULT_CONTENT_EVENT_ICON.replace("${name}",name),2000,0);
		info("Verify that the name is shown");
		waitForAndGetElement(quickSearch.ELEMENT_SEARCHRESULT_CONTENT_EVENT_NAME_NOTE.replace("${name}",name),2000,0);
		info("Verify that the note is shown");
		waitForAndGetElement(quickSearch.ELEMENT_SEARCHRESULT_CONTENT_EVENT_NAME_NOTE.replace("${name}",note),2000,0);
		info("Verify that due date is shown");
		waitForAndGetElement(quickSearch.ELEMENT_SEARCHRESULT_CONTENT_EVENT_DUEDATE.replace("${name}",name).replace("${date}",date),2000,0);
		info("Verify that Item in search result is clickable and open it when user click ");
		click(quickSearch.ELEMENT_SEARCHRESULT_CONTENT_EVENT_NAME_NOTE.replace("${name}",name));
		waitForAndGetElement(calMg.ELEMENT_CALENDAR_PREVIEW_TASK_EVENT.replace("${name}",name),5000,0);

	}

	/**
	 *<li> Case ID:120882.</li>
	 *<li> Test Case Name: Search pages.</li>
	 *<li> Pre-Condition: Some pages are existed and have the text "cloud" in name or page content</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test12_SearchPages() {
		info("Test 12 Search pages");

		String name = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
        hp.goToHomePage();
		navToolBar.goToAddPage();
		pagCW.addAPageSimple(name,name);

		/*Step Number: 1
		 *Step Name: Search pages
		 *Step Description: 
			- Login and go to intranet home page
			- Type text "Cloud" into search box on Top navigation bar
			- Click on Search
		 *Input Data: 

		 *Expected Outcome: 
			- Search results should display: page icon, page title, the site that the page belongs to, and the url 
			-Item in search result is clickable and open it when user click*/ 
		navToolBar.goToQuickSearch();
		type(quickSearch.ELEMENT_TOOLBAR_QUICKSEARCH_TEXTBOX,name,true);
		Actions action = new Actions(this.driver);
		action.sendKeys(Keys.ENTER);
		action.perform();
		uncheck(quickSearch.ELEMENT_SEARCHRESULT_ALLTYPECHECK, 2);
		check(quickSearch.ELEMENT_SEARCHRESULT_PAGETYPECHECK, 2);
		
		info("Verify that page icon is shown");
		waitForAndGetElement(quickSearch.ELEMENT_SEARCHRESULT_CONTENT_PAGE_ICON.replace("${page}",name),2000,0);
		info("Verify that page title is shown");
		waitForAndGetElement(quickSearch.ELEMENT_SEARCHRESULT_CONTENT_PAGE_TITLE.replace("${page}",name),2000,0);
		info("Verify that the site that tha page belongs to");
		waitForAndGetElement(quickSearch.ELEMENT_SEARCHRESULT_CONTENT_PAGE_SITE_BELONGS_TO.replace("${page}",name).replace("${site}","Collaboration"),2000,0);
		info("Verify url of the page");
		String url = waitForAndGetElement(quickSearch.ELEMENT_SEARCHRESULT_CONTENT_PAGE_SITE_HREF.replace("${page}",name)).getAttribute("href").toString();
		waitForAndGetElement(quickSearch.ELEMENT_SEARCHRESULT_CONTENT_PAGE_SITE_URL.replace("${page}",name).replace("${url}",url));
		info("Verify that Item in search result is clickable and open it when user click ");
		click(quickSearch.ELEMENT_SEARCHRESULT_CONTENT_PAGE_TITLE.replace("${page}",name));
		if(driver.getCurrentUrl().equals(url)==false) assert false:"Not redirects to the page when click on the title of the page";
	
	}

	/**
	 *<li> Case ID:120883.</li>
	 *<li> Test Case Name: Search wikis.</li>
	 *<li> Pre-Condition: Wiki pages with the word "cluster" in their content or title are existed.</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test13_SearchWikis() {
		info("Test 13 Search wikis");

		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();;

		hp.goToWiki();
		wikiHome.goToAddBlankPage();
		wikiMag.goToSourceEditor();
		wikiMag.inputDataToPageSourceEditor(title,content,true,true);
		wikiMag.saveAddPage();
		
		DateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM dd, yyyy");
		Calendar cal = Calendar.getInstance();
		String date = dateFormat.format(cal.getTime());
		info(date);

		/*Step Number: 1
		 *Step Name: Search wikis
		 *Step Description: 
			- Login and go to intranet home page
			- Type the word "cluster"into search box on Top navigation bar
			- Click on Search, or press Enter key
		 *Input Data: 

		 *Expected Outcome: 
			- Search results should display: the generic wiki icon, the wiki article title,the last modification date and the location (wiki name)
			- Item in search result is clickable and open it when user click*/ 
		navToolBar.goToQuickSearch();
		type(quickSearch.ELEMENT_TOOLBAR_QUICKSEARCH_TEXTBOX,title,true);
		Actions action = new Actions(this.driver);
		action.sendKeys(Keys.ENTER);
		action.perform();
		uncheck(quickSearch.ELEMENT_SEARCHRESULT_ALLTYPECHECK, 2);
		check(quickSearch.ELEMENT_SEARCHRESULT_WIKITYPECHECK, 2);
		
		info("Verify that wiki icon is shown");
		waitForAndGetElement(quickSearch.ELEMENT_SEARCHRESULT_CONTENT_WIKI_ICON.replace("${name}",title),2000,0);
		info("Verify that wiki title is shown");
		waitForAndGetElement(quickSearch.ELEMENT_SEARCHRESULT_CONTENT_WIKI_TITLE.replace("${name}",title),2000,0);
		info("Verify that last modification is shown");
		waitForAndGetElement(quickSearch.ELEMENT_SEARCHRESULT_CONTENT_WIKI_LAST_MODIFICATION_DATE.replace("${name}",title).replace("${date}", date),2000,0);
		info("Verify that location is shown");
		waitForAndGetElement(quickSearch.ELEMENT_SEARCHRESULT_CONTENT_WIKI_LOCATION.replace("${name}",title).replace("${location}","intranet"),2000,0);
		
		info("Verify that Item in search result is clickable and open it when user click ");
        click(quickSearch.ELEMENT_SEARCHRESULT_CONTENT_WIKI_TITLE.replace("${name}",title));
        waitForAndGetElement(wikiHome.ELEMENT_PAGE_TITLE.replace("${title}",title),5000,0);
	}

	/**
	 *<li> Case ID:120884.</li>
	 *<li> Test Case Name: Search spaces.</li>
	 *<li> Pre-Condition: Some spaces which have the word "dinner" in their name/description are existed.</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test14_SearchSpaces() {
		info("Test 14 Search spaces");
		
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		hp.goToMySpaces();
		spaceMg.addNewSpaceSimple(title, content);
		
		
		/*Step Number: 1
		 *Step Name: Search spaces
		 *Step Description: 
			- Log in to Intranet 
			- Type the word "dinner" into search box on Top navigation bar
			- Click on Search icon or press Enter key
		 *Input Data: 

		 *Expected Outcome: 
			- Search results should display: the space avatar, the space name, the space description, the members count, the validation status 
			- Item in search result is clickable and open it when user click*/ 
		navToolBar.goToQuickSearch();
		type(quickSearch.ELEMENT_TOOLBAR_QUICKSEARCH_TEXTBOX,title,true);
		Actions action = new Actions(this.driver);
		action.sendKeys(Keys.ENTER);
		action.perform();
		uncheck(quickSearch.ELEMENT_SEARCHRESULT_ALLTYPECHECK, 2);
		check(quickSearch.ELEMENT_SEARCHRESULT_SPACETYPECHECK, 2);
		
		info("Verify that the avatar is shown");
		waitForAndGetElement(quickSearch.ELEMENT_SEARCHRESULT_CONTENT_SPACE_AVATAR.replace("${name}",title),2000,0);
		info("Verify that title is shown");
		waitForAndGetElement(quickSearch.ELEMENT_SEARCHRESULT_CONTENT_SPACE_TITLE.replace("${name}",title),2000,0);
		info("Verify that desctiption is shown");
		waitForAndGetElement(quickSearch.ELEMENT_SEARCHRESULT_CONTENT_SPACE_DESCRIPTION.replace("${des}",title),2000,0);
		info("Verify that member count is shown");
		waitForAndGetElement(quickSearch.ELEMENT_SEARCHRESULT_CONTENT_SPACE_MEMBER_COUNT.replace("${name}",title).replace("${number}","1 Member(s)"),2000,0);
		info("Verify that the status is shown");
		waitForAndGetElement(quickSearch.ELEMENT_SEARCHRESULT_CONTENT_SPACE_STATUS.replace("${name}",title).replace("${status}","Register"),2000,0);
		info("Verify that Item in search result is clickable and open it when user click ");
        click(quickSearch.ELEMENT_SEARCHRESULT_CONTENT_SPACE_TITLE.replace("${name}",title));
        waitForAndGetElement(spaceHome.ELEMENT_SPACE_NAME.replace("${name}", title),5000,0);

	}

	/**
	 *<li> Case ID:120885.</li>
	 *<li> Test Case Name: Search people.</li>
	 *<li> Pre-Condition: Some users are existed. The word "test" exists in their profile (position, name, skill, organization...)</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test15_SearchPeople() {
		info("Test 15 Search people");
		/*Step Number: 1
		 *Step Name: Search people
		 *Step Description: 
			- Login Intranet
			- Type the word "test" into search box on Top navigation bar
			- Click on Search icon, or press Enter key
		 *Input Data: 

		 *Expected Outcome: 
			- Search results should display: the user profile avatar, the user full name, the user profile title, the user profile email, the user profile phone, gender
			- Item in search result is clickable and open it when user click*/ 
		
		String phone= getRandomNumber();
		
		navToolBar.goToMyProfile();
		myProfile.updateContact("",phone,"","");
		
		navToolBar.goToQuickSearch();
		type(quickSearch.ELEMENT_TOOLBAR_QUICKSEARCH_TEXTBOX,"John Smith",true);
		click(quickSearch.ELEMENT_QUICKSEARCHRESULT_SEE_ALL_SEARCH);
		/*Actions action = new Actions(this.driver);
		action.sendKeys(Keys.ENTER);
		action.perform();*/
		uncheck(quickSearch.ELEMENT_SEARCHRESULT_ALLTYPECHECK, 2);
		check(quickSearch.ELEMENT_SEARCHRESULT_PEOPLETYPECHECK, 2);
		
		info("Verify that the avatar is shown");
		waitForAndGetElement(quickSearch.ELEMENT_SEARCHRESULT_CONTENT_USER_AVATAR.replace("${fullname}","John Smith"),2000,0);
		info("Verify that full name is shown");
		waitForAndGetElement(quickSearch.ELEMENT_SEARCHRESULT_CONTENT_USER_FULL_NAME.replace("${fullname}","John Smith"),2000,0);
		info("Verify that title is shown");
		waitForAndGetElement(quickSearch.ELEMENT_SEARCHRESULT_CONTENT_USER_TITLE.replace("${fullname}","John Smith"),2000,0);
		info("Verify that email is shown");
		waitForAndGetElement(quickSearch.ELEMENT_SEARCHRESULT_CONTENT_USER_EMAIL.replace("${fullname}","John Smith").replace("${email}","john.smith@acme.exoplatform.com"),2000,0);
		info("Verify that the phone is shown");
		waitForAndGetElement(quickSearch.ELEMENT_SEARCHRESULT_CONTENT_USER_PHONE.replace("${fullname}","John Smith").replace("${phone}",phone),2000,0);
		info("Verify that the gender is shown");
		waitForAndGetElement(quickSearch.ELEMENT_SEARCHRESULT_CONTENT_USER_GENDER.replace("${fullname}","John Smith").replace("${gender}","male"),2000,0);
		info("Verify that Item in search result is clickable and open it when user click ");
        click(quickSearch.ELEMENT_SEARCHRESULT_CONTENT_USER_FULL_NAME.replace("${fullname}","John Smith"));

	}

	/**
	 *<li> Case ID:120886.</li>
	 *<li> Test Case Name: Search answers.</li>
	 *<li> Pre-Condition: Answers page is existed. And Some Questions and Answers containing the word "cloud" in title/content are existed.</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test16_SearchAnswers() {
		info("Test 16 Search answers");

		String question = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber()+"des";

		hp.goToAnswer();
		qMang.goToSubmitQuestion();
		qMang.inputDataToQuestionForm(question, content, null, null);
		click(qMang.ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON);
		click(but.ELEMENT_OK_BUTTON_LINK);
		waitForAndGetElement(By.xpath(aHome.ELEMENT_QUESTION_LIST_ITEM.replace("$question", question)));
		/*Step Number: 1
		 *Step Name: Search Answers
		 *Step Description: 
			- Login Intranet
			- Type the text "cloud" into search box on the top navigationClick on Search icon or press Enter
		 *Input Data: 

		 *Expected Outcome: 
			- Search results should display in the Search page: the answers icon, the question title, an excerpt of the matching content, the number of replies, the number of comments, the average rating of the question*/ 
		navToolBar.goToQuickSearch();
		type(quickSearch.ELEMENT_TOOLBAR_QUICKSEARCH_TEXTBOX,question,true);
		Actions action = new Actions(this.driver);
		action.sendKeys(Keys.ENTER);
		action.perform();
		uncheck(quickSearch.ELEMENT_SEARCHRESULT_ALLTYPECHECK, 2);
		check(quickSearch.ELEMENT_SEARCHRESULT_ANSWERTYPECHECK, 2);
		
		info("Verify that icon is shown");
		waitForAndGetElement(quickSearch.ELEMENT_SEARCHRESULT_CONTENT_ANSWER_ICON.replace("${name}",question),2000,0);
		info("Verify that title is shown");
		waitForAndGetElement(quickSearch.ELEMENT_SEARCHRESULT_CONTENT_ANSWER_TITLE.replace("${name}",question),2000,0);
		info("Verify that excerpt is shown");
		waitForAndGetElement(quickSearch.ELEMENT_SEARCHRESULT_CONTENT_ANSWER_EXCERPT.replace("${name}",question),2000,0);
		info("Verify that number reply is shown");
		waitForAndGetElement(quickSearch.ELEMENT_SEARCHRESULT_CONTENT_ANSWER_NUMBER_REPLY.replace("${name}",question).replace("${number}","0 answers"),2000,0);
		info("Verify that number comments is shown");
		waitForAndGetElement(quickSearch.ELEMENT_SEARCHRESULT_CONTENT_ANSWER_NUMBER_COMMENTS.replace("${name}",question).replace("${comment}","0 comments"),2000,0);
		info("Verify that Rating is shown");
		waitForAndGetElement(quickSearch.ELEMENT_SEARCHRESULT_CONTENT_ANSWER_RATING.replace("${name}",question),2000,0);

	}
	
}
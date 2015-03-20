package org.exoplatform.selenium.platform.ecms.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.ecms.CreateNewDocument.selectDocumentType;
import org.openqa.selenium.By;
import org.testng.annotations.*;

/**
 * @date 15-January-2015
 * @author exo
 * update 26/01/2015
 */


public class Ecms_WCM extends ECMS_TestConfig_Part1 {
	/**
	 *<li> Case ID:116568.</li>
	 *<li> Test Case Name: Create new Content List viewer page with mode "By Folder".</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test01_CreateNewContentListViewerPageWithModeByFolder() {
		info("Test 1: Create new Content List viewer page with mode By Folder");
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		 *Step Name: Step 1: Create new Content List viewer page with mode "By Folder"
		 *Step Description: 
			- Login acme by admin/web contributor
			- Go to Administration 
			-
			-> Content 
			-
			-> Sites Explorer. 
			- Add some web contents in Sites Management/acme/web contents (or documents folder)
		 */
		navTool.goToSiteExplorer();
		SEHome.goToPath("acme", "Sites Management");
        //Create node
		SEHome.goToAddNewContent();
		CreNewDoc.createNewDoc(selectDocumentType.WEBCONTENT);
		CreNewDoc.addNewWebContent(content, content);
		CreNewDoc.saveAndClose();

		/*	- Go to acme homepage, select Edit/ Page/Add Page to create new page.+ 
		 * Fill node name+ Click Next -> Next. 
		 * + Drag and drop Content/Content list portlet from page editor+ 
			Click Edit portlet icon of this Content list portlet+ Choose By Folder option+ 
			Click [Select folder path] icon to some folder that you want to view+ 
			Click [Save] button in Multiple content selector panel+ 
			Click [Save] button in Content list viewer configuration form+ 
			Click [Close] button inContent list viewer configuration form
			-Click Finish icon in Page editor
			- Make created web content public.
		 *Input Data: 

		 *Expected Outcome: 
			- All web contents/documents in selected folder are listed in Content List Viewer portlet in this page*/ 
		this.driver.get(baseUrl+"/acme");
		navTool.goToAddPage();
		pagCW.inputPageInfoStep1(content, true, "English", content, true,false);
		click(pagCW.ELEMENT_ADDNEWPAGE_BTNNEXT);
		click(pagCW.ELEMENT_ADDNEWPAGE_BTNNEXT);
		pagCW.addContentlistByFolder("General Drives/Sites Management","acme");
		
		navTool.goToEditContent();
		waitForAndGetElement(contList.ELEMENT_CONTENT_LIST_CONTENT_TITLE.replace("${title}", content));
		
		info("Delete Data test");
		info("Delete created file");
		navTool.goToSiteExplorer();
		SEHome.deleteData(content);
		info("Delete created page");
		navTool.goToPotalPages();
		paMang.deletePage(content,"");
		info("Delete a node on navigation menu");
		navTool.goToPotalSites();
		maSite.goToEditNavigation("acme");
		navMag.deleteNode(content);
		
	}

	/**
	 *<li> Case ID:116570.</li>
	 *<li> Test Case Name: Create new Content List Viewer page with mode "By Contents".</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test02_CreateNewContentListViewerPageWithModeByContents() {
		info("Test 2: Create new Content List Viewer page with mode By Contents");
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		 *Step Name: Step 1: Create new Content List Viewer page with mode "By Contents"
		 *Step Description: 
			- Login acme by admin/web contributor
			- Go to content explorer, add some web contents Sites Management/acme/web contents (or documents folder)
		 */

		navTool.goToSiteExplorer();
		SEHome.goToPath("acme", "Sites Management");
        //Create node
		SEHome.goToAddNewContent();
		CreNewDoc.createNewDoc(selectDocumentType.WEBCONTENT);
		CreNewDoc.addNewWebContent(content, content);
		CreNewDoc.saveAndClose();

		/*	- Go to acme home page, select Edit/ Page/Add Page to create new page. + 
		 * Fill node name+ Next+ Next + 
		 * Drag and drop Content/Content list portlet from page editor+ 
		 * Click edit portlet icon of this Content list portlet+ 
		 * Choose By Contents option+ Click [Select folder path] icon to some web contents/documents that you want to view+
		 *  Click [Save] button in Multiple content selector panel+
		 *  Click [Save] button in Content list viewer configuration form+ 
		 *  Click [Close] button inContent list viewer configuration form
			-Click Finish icon in Page editor
		 *Input Data: 

		 *Expected Outcome: 
			All selected web content/documents are displayed as list in List Content Viewer page*/ 
		this.driver.get(baseUrl+"/acme");
		
		navTool.goToAddPage();
		pagCW.inputPageInfoStep1(content, true, "English", content, true,false);
		click(pagCW.ELEMENT_ADDNEWPAGE_BTNNEXT);
		click(pagCW.ELEMENT_ADDNEWPAGE_BTNNEXT);
		pagCW.addContentListByContent("General Drives/Sites Management/acme",content);
		
		navTool.goToEditContent();
        waitForAndGetElement(contList.ELEMENT_CONTENT_LIST_CONTENT_TITLE.replace("${title}",content));
		
        info("Delete Data test");
		info("Delete created file");
		navTool.goToSiteExplorer();
		SEHome.deleteData(content);
		info("Delete created page");
		navTool.goToPotalPages();
		paMang.deletePage(content,"");
		info("Delete a node on navigation menu");
		navTool.goToPotalSites();
		maSite.goToEditNavigation("acme");
		navMag.deleteNode(content);
	}

	/**
	 *<li> Case ID:116571.</li>
	 *<li> Test Case Name: Create Single Content Viewer page.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test03_CreateSingleContentViewerPage() {
		info("Test 3: Create Single Content Viewer page");
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();

		navTool.goToSiteExplorer();
		SEHome.goToPath("acme", "Sites Management");
        //Create node
		SEHome.goToAddNewContent();
		CreNewDoc.createNewDoc(selectDocumentType.WEBCONTENT);
		CreNewDoc.addNewWebContent(content, content);
		CreNewDoc.saveAndClose();
		/*Step Number: 1
		 *Step Name: Step 1: Create Single Content Viewer page
		 *Step Description: 
			- Login acme by Admin/Web contributor
			- Choose Edit/Page/Add Page+ Fill name+ Next+ Next+ 
			Drag and drop Content/ Content Detail portlet to this Page+ 
			Click Edit icon to edit this porlet+ Select [Content Path] where stores these web contents/documents+
		 	Click icon in Action column in the right to select one of them+ 
		 	Click Save+ 
		 	Click Close+ 
		 	Click Finish icon in page editor
		 *Input Data: 

		 *Expected Outcome: 
			- The selected web content/document is displayed*/ 
		this.driver.get(baseUrl+"/acme");
		navTool.goToAddPage();

		pagCW.inputPageInfoStep1(content, true, "English", content, true,false);
		click(pagCW.ELEMENT_ADDNEWPAGE_BTNNEXT);
		click(pagCW.ELEMENT_ADDNEWPAGE_BTNNEXT);
		pagCW.addContentDetail("General Drives/Sites Management/acme",content);
		
		navTool.goToEditContent();
        waitForAndGetElement(contDetail.ELEMENT_CONTENT_DETAIL_VIEW_CONTENT.replace("${title}",content));
		
        info("Delete Data test");
		info("Delete created file");
		navTool.goToSiteExplorer();
		SEHome.deleteData(content);
		info("Delete created page");
		navTool.goToPotalPages();
		paMang.deletePage(content,"");
		info("Delete a node on navigation menu");
		navTool.goToPotalSites();
		maSite.goToEditNavigation("acme");
		navMag.deleteNode(content);
	}

	/**
	 *<li> Case ID:116577.</li>
	 *<li> Test Case Name: Edit Preference of Content List Viewer (Documents, Last news ...).</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test04_EditPreferenceOfContentListViewer() {
		info("Test 4: Edit Preference of Content List Viewer (Documents, Last news ...)");
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String newContent = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		navTool.goToSiteExplorer();
		SEHome.goToPath("acme", "Sites Management");
        //Create node
		SEHome.goToAddNewContent();
		CreNewDoc.createNewDoc(selectDocumentType.WEBCONTENT);
		CreNewDoc.addNewWebContent(content, content);
		CreNewDoc.saveAndClose();
		/*Step Number: 1
		 *Step Name: Step 1: Edit Content List Viewer (Documents, Last news...)
		 *Step Description: 
			- Login acme by administrator successfully
			- Open Overview page
			- Open edit mode: Mouse over Edit, Click Content
		 */

		this.driver.get(baseUrl+"/acme");
		navTool.goToAddPage();

		pagCW.inputPageInfoStep1(content, true, "English", content, true,false);
		click(pagCW.ELEMENT_ADDNEWPAGE_BTNNEXT);
		click(pagCW.ELEMENT_ADDNEWPAGE_BTNNEXT);
		pagCW.addContentListByContent("General Drives/Sites Management/acme",content);
		
		navTool.goToEditContent();

		/* - Click Preference icon of any CLV (Documents, Last news ...)
			- Change properties in CLV configuration form
			- Click [Save] button
		 *Input Data: 

		 *Expected Outcome: 
			Content list viewer is updated and displayed with new properties*/ 
		acmeHP.editPreferenceContentList(newContent);
		waitForAndGetElement(By.xpath((contList. ELEMENT_CONTENT_LIST_CONTENT_BOX_CONTENT_TITLE).replace("${title}",newContent)));
		
		info("Delete Data test");
		info("Delete created file");
		navTool.goToSiteExplorer();
		SEHome.deleteData(content);
		info("Delete created page");
		navTool.goToPotalPages();
		paMang.deletePage(content,"");
		info("Delete a node on navigation menu");
		navTool.goToPotalSites();
		maSite.goToEditNavigation("acme");
		navMag.deleteNode(content);
	}

	/**
	 *<li> Case ID:116603.</li>
	 *<li> Test Case Name: Search content of document/page.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test05_SearchContentOfDocumentpage() {
		info("Test 5: Search content of document/page");
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		/*Step Number: 1
		 *Step Name: Step 1: Search content of document/page
		 *Step Description: 
			- Go to acme site (both publish and private mode)
			- Input search keyword into text box in Quick search
			- Press Enter
		 *Input Data: 

		 *Expected Outcome: 
			-Return matched web content/document or return blank if there's no matched value.
			-In published mode, only list the published contents (not list Draft content)
			- In Edit mode, show all contents matching keyword
			- Can click link on search result to see content*/ 
		this.driver.get(baseUrl+"/acme");
		navTool.goToSiteExplorer();
		SEHome.goToPath("acme", "Sites Management");
        //Create node
		SEHome.goToAddNewContent();
		CreNewDoc.createNewDoc(selectDocumentType.WEBCONTENT);
		CreNewDoc.addNewWebContent(content , content);
		CreNewDoc.saveAndClose();

		//go to acme
		this.driver.get(baseUrl+"/acme");
		
		//search
		acmeHP.searchQuickSearchBox(content );
		navTool.goToEditContent();
		waitForAndGetElement((acmeHP.ELEMENT_SEARCHRESULT_TITLE).replace("${title}",content ));
		navTool.goToUnEditContent();
		navTool.goToSiteExplorer();
		SEHome.goToPublication();
		SEHome.changeStatusPulication("Published");
		this.driver.get(baseUrl+"/acme");
		Utils.pause(5000);
		acmeHP.searchQuickSearchBox(content);
		waitForAndGetElement((acmeHP.ELEMENT_SEARCHRESULT_TITLE).replace("${title}",content));
		
		info("Delete created file");
		navTool.goToSiteExplorer();
		SEHome.deleteData(content);

	}

	/**
	 *<li> Case ID:116605.</li>
	 *<li> Test Case Name: Show categories and documents.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test06_ShowCategoriesAndDocuments() {
		info("Test 6: Show categories and documents");
		 String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		/*Step Number: 1
		 *Step Name: Step 1: Show categories and documents
		 *Step Description: 
			- Login acme by admin or web contributor
			- Go to Content Explorer
			- Select drive [power]
			- Add new some web contents/documents in some categories. 
			- Publish them
			- Go to acme home page then News page
			- Select a category at left pane
		 *Input Data: 

		 *Expected Outcome: 
			- Documents/web contents in the selected category are displayed in Parameterized content list viewer portlet*/
		 this.driver.get(baseUrl+"/acme");
		navTool.goToSiteExplorer();
		SEHome.goToPath("acme/categories/powers/Defense", "Sites Management");

		//Create node
		SEHome.goToAddNewContent();
		CreNewDoc.createNewDoc(selectDocumentType.WEBCONTENT);
		CreNewDoc.addNewWebContent(content, content);
		CreNewDoc.saveAndClose();
		SEHome.selectNode(content);
		SEHome.goToPublication();
		SEHome.changeStatusPulication("Published");
		this.driver.get(baseUrl+"/acme");
		acmeHP.goToNews();
		click(acmeHP.ELEMENT_TOPIC_DEFENSE);
		waitForAndGetElement(acmeHP.ELEMENT_NEWS_DEFENSE_CONTENT.replace("${title}",content));
		
		info("Delete created file");
		navTool.goToSiteExplorer();
		SEHome.goToPath("acme/categories/powers/Defense", "Sites Management");
		SEHome.deleteData(content);
		
	}
	
	/**
	 *<li> Case ID:116606.</li>
	 *<li> Test Case Name: Show draft/public content from page.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test07_ShowDraftpublicContentFromPage() {
		info("Test 7: Show draft/public content from page");
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		this.driver.get(baseUrl+"/acme");
		navTool.goToSiteExplorer();
		SEHome.goToPath("acme", "Sites Management");
		//Create node
		SEHome.goToAddNewContent();
		CreNewDoc.createNewDoc(selectDocumentType.WEBCONTENT);
		CreNewDoc.addNewWebContent(content , content);
		CreNewDoc.saveAndClose();
		
		/*Step Number: 1
		 *Step Name: Step 1: Show draft /public content from page
		 *Step Description: 
			- Login acme by Admin/web contributor
			- Go to Content Explorer/Sites Management/acme
			- Add new document/web content 
			- Go to acme home page, add a SCV or CLV page with these contents
			- Open page contains document/web content above
			- Change to Edit mode [1]
			- Return to Content Explorer/Sites Management/acme
			- Select the document above
			- Click Publications in Action bar
			- Choose Publish status.
			- Go to page contains document/web content above
			- Change to Public mode [2]
		 *Input Data: 

		 *Expected Outcome: 
			- [1] Selected document/web content is displayed into this page with draft 
			- [2] Selected document/web content is published into this page with published status*/ 
		this.driver.get(baseUrl+"/acme");
		navTool.goToAddPage();

		pagCW.inputPageInfoStep1(content , true, "English", content, true,false);
		click(pagCW.ELEMENT_ADDNEWPAGE_BTNNEXT);
		click(pagCW.ELEMENT_ADDNEWPAGE_BTNNEXT);
		pagCW.addContentListByContent("General Drives/Sites Management/acme",content);
	
		navTool.goToEditContent();
		//Verify that Selected document/web content is displayed into this page with draft 
		waitForAndGetElement(contList.ELEMENT_CONTENT_LIST_CONTENT_TITLE.replace("${title}", content));
		navTool.goToSiteExplorer();
		SEHome.selectNode(content);
		
		SEHome.goToPublication();
		SEHome.changeStatusPulication("Published");
		this.driver.get(baseUrl+"/acme/overview/"+content+"");
		navTool.goToUnEditContent();
		//Verify that Selected document/web content is published into this page with published status
		waitForAndGetElement(acmeHP.ELEMENT_OVERVIEWS_CONTENT.replace("${title}",content));
		
		info("Delete Data test");
		info("Delete created file");
		navTool.goToSiteExplorer();
		SEHome.deleteData(content);
		info("Delete created page");
		navTool.goToPotalPages();
		paMang.deletePage(content,"");
		info("Delete a node on navigation menu");
		navTool.goToPotalSites();
		maSite.goToEditNavigation("acme");
		navMag.deleteNode(content);
	}

	/**
	 *<li> Case ID:116612.</li>
	 *<li> Test Case Name: Edit Content List Viewer page with mode "By Contents".</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test08_EditContentListViewerPageWithModeByContents() {
		info("Test 8: Edit Content List Viewer page with mode By Contents");
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		 *Step Name: -
		 *Step Description: 
			Step 1: Create new Content List Viewer page with mode "By Contents"
		 *Input Data: 
			- Login acme by admin/web contributor
			- Go to content explorer, add some web contents Sites Management/acme/web contents (or documents folder)
			- Go to acme home page, click Edit/ Page/Add Page to create new page. +
			 Fill node name+ Next+ Next + Drag and drop Content/Content list portlet from page editor+ 
			 Click edit portlet icon of this Content list portlet+ 
			 Choose By Contents option+ Click [Select folder path] icon to some web contents/documents that you want to view+ 
			 Click [Save] button in Multiple content selector panel+ 
			 Click [Save] button in Content list viewer configuration form+ 
			 Click [Close] button inContent list viewer configuration form
			-Click Finish icon in Page editor
		 *Expected Outcome: 
			All selected web content/documents are displayed as list in List Content Viewer page*/
		this.driver.get(baseUrl+"/acme");
		navTool.goToSiteExplorer();
		SEHome.goToPath("acme", "Sites Management");

		info("Create webcontent1");
		SEHome.goToAddNewContent();
		CreNewDoc.createNewDoc(selectDocumentType.WEBCONTENT);
		CreNewDoc.addNewWebContent(content1 ,content1);
		CreNewDoc.saveAndClose();
		
		info("Select acme folder");
		SEHome.selectNode("acme");
		
		info("Create webcontent2");
		SEHome.goToAddNewContent();
		CreNewDoc.createNewDoc(selectDocumentType.WEBCONTENT);
		CreNewDoc.addNewWebContent(content2,content2);
		CreNewDoc.saveAndClose();
			
		this.driver.get(baseUrl+"/acme");
		
		navTool.goToAddPage();
		pagCW.inputPageInfoStep1(title, true, "English",title, true,false);
		click(pagCW.ELEMENT_ADDNEWPAGE_BTNNEXT);
		click(pagCW.ELEMENT_ADDNEWPAGE_BTNNEXT);
		pagCW.addContentlistByFolder("General Drives/Sites Management","acme");
		
		navTool.goToEditContent();
		//Verify that all webcontents are shown in Content list View page
		waitForAndGetElement(contList.ELEMENT_CONTENT_LIST_CONTENT_TITLE.replace("${title}",content1));
		waitForAndGetElement(contList.ELEMENT_CONTENT_LIST_CONTENT_TITLE.replace("${title}",content2));
		
		/*Step number: 2
		 *Step Name: 
		 *Step Description: 
			Step 2: Edit Content List Viewer page with mode "By Contents"
		 *Input Data: 
			- Open CLV above
			- Click Edit/ Page/Page Layout to edit page + 
			Click edit portlet icon of this Content list portlet+ 
			Choose By Contents option+ Click [Select folder path] icon to some web contents/documents that you want to view+ 
			Click [Save] button in Multiple content selector panel+ 
			Click [Save] button in Content list viewer configuration form+ 
			Click [Close] button inContent list viewer configuration form
			-Click Finish icon in Page editor
		 *Expected Outcome: 
			All selected web content/documents you've chosen are displayed as list in List Content Viewer page*/ 
	
		mouseOver(contList.ELEMENT_EDIT_CLV, true);
		click(contList.ELEMENT_EDIT_PREFERENCE);
		check(contList.ELEMENT_CONTENT_LIST_BY_CONTENT_MODE, 2);
		contList.selectFolderContent("General Drives/Sites Management/acme",content1);
		Utils.pause(2000);
		click(contList.ELEMENT_MULTIPLE_CONTENT_POPUP_SAVE_BTN);
		click(contList.ELEMENT_CONTENT_LIST_PREFERENCE_SAVE_BTN);
		//Verify that all webcontents are shown in Content list View page
		waitForAndGetElement(contList.ELEMENT_CONTENT_LIST_CONTENT_TITLE.replace("${title}",content1));
		waitForElementNotPresent(contList.ELEMENT_CONTENT_LIST_CONTENT_TITLE.replace("${title}",content2));
		
		info("Delete create files");
		this.driver.get(baseUrl+"/intranet");
		navTool.goToSiteExplorer();
		SEHome.deleteData(content1);
		SEHome.deleteData(content2);
	    info("Delete created page");
		navTool.goToPotalPages();
		paMang.deletePage(title,"");
		info("Delete a node on navigation menu");
		navTool.goToPotalSites();
		maSite.goToEditNavigation("acme");
		navMag.deleteNode(title);
	}

	/**
	 *<li> Case ID:116613.</li>
	 *<li> Test Case Name: Edit Content List viewer page with mode "By Folder".</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test09_EditContentListViewerPageWithModeByFolder() {
		info("Test 9: Edit Content List viewer page with mode By Folder");
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		this.driver.get(baseUrl+"/acme");
		/*Step Number: 1
		 *Step Name: -
		 *Step Description: 
			Step 1: Create new Content List viewer page with mode "By Content"
		 *Input Data: 
			- Login acme by admin/web contributor
			- Go to content explorer, add some web contents Sites Management/acme/web contents (or documents folder)
			- Go to acme home page, select Edit/ Page/Add Page to create new page. + Fill node name+ Next+ Next + Drag and drop Content/Content list portlet from page editor+ Click edit portlet icon of this Content list portlet+ Choose By Folder option+ Click [Select folder path] icon to some folder that you want to view+ Click [Save] button in Multiple content selector panel+ Click [Save] button in Content list viewer configuration form+ Click [Close] button inContent list viewer configuration form
			-Click Finish icon in Page editor
		 *Expected Outcome: 
			- All web contents/documents in selected folder are listed in Content List Viewer portlet in this page*/
		navTool.goToSiteExplorer();
		SEHome.goToPath("acme", "Sites Management");

		info("Create webcontent1");
		SEHome.goToAddNewContent();
		CreNewDoc.createNewDoc(selectDocumentType.WEBCONTENT);
		CreNewDoc.addNewWebContent(content1 ,content1);
		CreNewDoc.saveAndClose();
		
		info("Select acme folder");
		SEHome.selectNode("acme");
		
		info("Create webcontent2");
		SEHome.goToAddNewContent();
		CreNewDoc.createNewDoc(selectDocumentType.WEBCONTENT);
		CreNewDoc.addNewWebContent(content2 ,content2);
		CreNewDoc.saveAndClose();
			
		this.driver.get(baseUrl+"/acme");
		
		navTool.goToAddPage();
		pagCW.inputPageInfoStep1(title, true, "English",title, true,false);
		click(pagCW.ELEMENT_ADDNEWPAGE_BTNNEXT);
		click(pagCW.ELEMENT_ADDNEWPAGE_BTNNEXT);
		pagCW.addContentListByContent("General Drives/Sites Management/acme",content1);
		
		navTool.goToEditContent();
		waitForAndGetElement(contList.ELEMENT_CONTENT_LIST_CONTENT_TITLE.replace("${title}",content1));
		waitForElementNotPresent(contList.ELEMENT_CONTENT_LIST_CONTENT_TITLE.replace("${title}",content2));
		/*Step number: 2
		 *Step Name: 
		 *Step Description: 
			Step 2: Edit Content List viewer page with mode "By Folder"
		 *Input Data: 
			- Open CLV above
			- Click Edit/ Page/Page Layout to edit page+ Click edit portlet icon of this Content list portlet+ Choose By Folder option+ Click [Select folder path] icon to some folder that you want to view+ Click [Save] button in Multiple content selector panel+ Click [Save] button in Content list viewer configuration form+ Click [Close] button inContent list viewer configuration form
			-Click Finish icon in Page editor
		 *Expected Outcome: 
			- All web contents/documents in selected folder are listed in Content List Viewer portlet in this page*/ 
		mouseOver(contList.ELEMENT_EDIT_CLV, true);
		click(contList.ELEMENT_EDIT_PREFERENCE);
		check(contList.ELEMENT_CONTENT_LIST_BY_FOLDER_MODE, 2);
		contList.selectFolderContent("General Drives/Sites Management","acme");
		Utils.pause(2000);
		click(contList.ELEMENT_CONTENT_LIST_PREFERENCE_SAVE_BTN);
		waitForAndGetElement(contList.ELEMENT_CONTENT_LIST_CONTENT_TITLE.replace("${title}",content1));
		waitForAndGetElement(contList.ELEMENT_CONTENT_LIST_CONTENT_TITLE.replace("${title}",content2));
		
		info("Delete created files");
		this.driver.get(baseUrl+"/intranet");
		navTool.goToSiteExplorer();
		SEHome.deleteData(content1);
		SEHome.deleteData(content2);
		info("Delete created page");
		navTool.goToPotalPages();
		paMang.deletePage(title,"");
		info("Delete created node on navigation menu");
		navTool.goToPotalSites();
		maSite.goToEditNavigation("acme");
		navMag.deleteNode(title);
	}

	/**
	 *<li> Case ID:116614.</li>
	 *<li> Test Case Name: Edit Single Content Viewer page.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test10_EditSingleContentViewerPage() {
		info("Test 10 Edit Single Content Viewer page");
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		 *Step Name: Step 1: Create Single Content Viewer page
		 *Step Description: 
			- Login acme by Admin/Web contributor
			- Choose Edit/Page/Add Page+ Fill name+ Next+ Next+ Drag and drop Content/ Content Detail portlet to this Page+ Click Edit icon to edit this porlet+ Select [Content Path] where stores these web contents/documents+ Click icon in Action column in the right to select one of them+ Click Save+ Click Close+ Click Finish icon in page editor
		 *Input Data: 

		 *Expected Outcome: 
			- The selected web content/document is displayed*/
		this.driver.get(baseUrl+"/acme");
		navTool.goToSiteExplorer();
		SEHome.goToPath("acme", "Sites Management");

		info("Create webcontent 1");
		SEHome.goToAddNewContent();
		CreNewDoc.createNewDoc(selectDocumentType.WEBCONTENT);
		CreNewDoc.addNewWebContent(content1 ,content1);
		CreNewDoc.saveAndClose();
		
		info("Select acme folder");
		SEHome.selectNode("acme");
		
		info("Create webcontent2");
		SEHome.goToAddNewContent();
		CreNewDoc.createNewDoc(selectDocumentType.WEBCONTENT);
		CreNewDoc.addNewWebContent(content2,content2);
		CreNewDoc.saveAndClose();
		
		this.driver.get(baseUrl+"/acme");
		navTool.goToAddPage();
		pagCW.inputPageInfoStep1(title, true, "English",title, true,false);
		click(pagCW.ELEMENT_ADDNEWPAGE_BTNNEXT);
		click(pagCW.ELEMENT_ADDNEWPAGE_BTNNEXT);
		pagCW.addContentDetail("General Drives/Sites Management/acme",content1);
		
		navTool.goToEditContent();
		waitForAndGetElement(contDetail.ELEMENT_CONTENT_DETAIL_VIEW_CONTENT.replace("${title}",content1));
		
		/*Step number: 2
		 *Step Name: Step 2: Edit Single Content Viewer page
		 *Step Description: 
			- Login acme by Admin/Web contributor
			- Open page above
			- Click Edit/ Page/ Page Layout+ Click Edit icon to edit this porlet+ Select [Content Path] where stores these web contents/documents+ Click icon in Action column in the right to select one of them+ Click Save+ Click Close+ Click Finish icon in page editor
		 *Input Data: 

		 *Expected Outcome: 
			- The new selected web content/document is displayed*/ 
		mouseOver(contDetail.ELEMENT_CONTENT_DETAIL_VIEW_CONTENT.replace("${title}",content1),true);
		click(contDetail.ELEMENT_CONTENT_DETAIL_CONTENT_BOX_PREFERENCES_BTN.replace("${title}",content1));
		click(contDetail.ELEMENT_CONTENT_DETAIL_ADDPATH_BTN);
		contList.selectFolderContent("General Drives/Sites Management/acme",content2);
		click(contDetail.ELEMENT_CONTENT_DETAIL_SAVE_BTN);
		waitForAndGetElement(contDetail.ELEMENT_CONTENT_DETAIL_VIEW_CONTENT.replace("${title}",content2));
		
		info("Delete created files");
		this.driver.get(baseUrl+"/intranet");
		navTool.goToSiteExplorer();
		SEHome.deleteData(content1);
		SEHome.deleteData(content2);
		info("Delete create page");
		navTool.goToPotalPages();
		paMang.deletePage(title,"");
		info("Delete created node on navigation");
		navTool.goToPotalSites();
		maSite.goToEditNavigation("acme");
		navMag.deleteNode(title);
	}

	/**
	 *<li> Case ID:116661.</li>
	 *<li> Test Case Name: Manage the title.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test11_ManageTheTitle() {
		info("Test 15 Manage the title");
		String random = getRandomNumber();
		String title = txData.getContentByArrayTypeRandom(1)+random;
		
		/*Step Number: 1
		 *Step Name: Step 1: Manage the title
		 *Step Description: 
			- Log in acme home page as admin
			- Go to Edit/ Page/ SEO
			- Enter the title that you want.
			- Click Save
		 *Input Data: 

		 *Expected Outcome: 
			You can see the new title will be displayed on TITLE element of your web page.*/ 
		this.driver.get(baseUrl+"/acme");
		Utils.pause(2000);
		navTool.goToSEO();
		type(seoMang.ELEMENT_SEO_TITLEBOX, title, true);
		click(seoMang.ELEMENT_SEO_SAVE);
		click(seoMang.ELEMENT_SEO_CLOSE);
		this.driver.navigate().refresh();
		info("title:"+driver.getTitle().toString());
		//Verify that the title of the page is changed
	    if(this.driver.getTitle().toString().contains(title))
	    	assert true;
	    	else assert false:"The title of the page is not changed";
	}

	/**
	 *<li> Case ID:116662.</li>
	 *<li> Test Case Name: Check SEO tool tips.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test12_CheckSEOToolTips() {
		info("Test 11 Check SEO tool tips");
		/*Step Number: 1
		 *Step Name: Step 1: Check SEO tool tips
		 *Step Description: 
			- Log in acme home page as admin
			- Go to Edit/ Page/ SEO
			- Mouse over ? icon beside Description, Keywords, Priority
		 *Input Data: 

		 *Expected Outcome: 
			Tool tips is shown for user to understand the SEO*/ 
		this.driver.get(baseUrl+"/acme");
		navTool.goToSEO();
		mouseOver(seoMang.ELEMENT_SEO_HELPDESC, true);
		waitForAndGetElement(seoMang.ELEMENT_SEO_HELP_POPOVER);
		mouseOver(seoMang.ELEMENT_SEO_HELPKEYWORD, true);
		waitForAndGetElement(seoMang.ELEMENT_SEO_HELP_POPOVER);
		mouseOver(seoMang.ELEMENT_SEO_HELPPRIORITY, true);
		waitForAndGetElement(seoMang.ELEMENT_SEO_HELP_POPOVER);
	}
	
	/**
	 *<li> Case ID:116637.</li>
	 *<li> Test Case Name: Add SEO metadas with localization.</li>
	 *<li> Pre-Condition: <title> ... </title><meta name="description" content=" ... " /><meta name="keywords" content=" ... " /><meta name="robots" content=" ... " /></li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test13_AddSEOMetadasWithLocalization() {
		info("Test 12 Add SEO metadas with localization");
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		int index1 = changLangData.getRandomIndexByType(1);
		info("index1:"+index1);
		String language1=changLangData.language.get(index1);
		info("language1 is:"+language1);
		String apply1 = changLangData.applyBtn.get(index1);
		
		int index2 = changLangData.getRandomIndexByType(2)+1;
		info("index2:"+index2);
		String language2=changLangData.language.get(index2);
		info("language2 is:"+language2);
	    String apply2 = changLangData.applyBtn.get(index2);

		
		/*Step Number: 1
		 *Step Name: Step 1: Add SEO metadas with localization
		 *Step Description: 
			- Log in acme home page as admin
			- Go to Edit/ Page/ SEO
			- Select a language to add SEO metadata for it from select box on the top of panel.
			- Fill data for all the fields
			- Click Save
			- Refresh page
			- Change to the language selected for adding SEO
			- Right click to view page source
		 *Input Data: 

		 *Expected Outcome: 
			- SEO information is saved successfully
			- Selected language is listed on the left of SEO 
			- Page source will be shown likein precondition
			- Go to Sites management/acme/ SEO folder there will be sitemap.xml file*/
		this.driver.get(baseUrl+"/acme");
		Utils.pause(2000);
		navTool.goToSEO();
		
		click(seoMang.ELEMENT_SEO_LANGUAGE_SHOW);
		select(seoMang.ELEMENT_SEO_LANGUAGE_SELECTBOX, language1);
		type(seoMang.ELEMENT_SEO_TITLEBOX, title, true);
		click(seoMang.ELEMENT_SEO_SAVE);
		click(seoMang.ELEMENT_SEO_CLOSE);
		this.driver.navigate().refresh();
		navTool.goToChangeLanguage();
		changLang.changeLanguage(language1,apply1);
		//Verify that French language is changed
		waitForAndGetElement(acmeHP.ELEMENT_NAVIGATION_MENU_OVERVIEW_FRENCH);
		
		navTool.goToChangeLanguage();
		changLang.changeLanguage(language2,apply2);
		
		this.driver.get(baseUrl+"/intranet");
		navTool.goToSiteExplorer();
		SEHome.goToPath("acme/SEO", "Sites Management");
		//Verify that sitemaps is created in SEO folder
		waitForAndGetElement(SEHome.ELEMENT_SE_NODE.replace("${node}", "sitemaps"));
		
		
		info("Delete SEO folder");
		navTool.goToSiteExplorer();
		SEHome.goToPath("acme/SEO", "Sites Management");
		SEHome.deleteData("SEO");
		info("Detete added language on SEO management");
		this.driver.get(baseUrl+"/acme");
		navTool.goToSEO();
		seoMang.deleteLanguage(language1);
	}

	/**
	 *<li> Case ID:116638.</li>
	 *<li> Test Case Name: Update SEO metadatas with localization.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 *
	 *<li> Case ID:116660.</li>
	 *<li> Test Case Name: Delete localize SEO metadatas.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */

	@Test
	public  void test14_15_UpdateDeleteSEOMetadatasWithLocalization() {
		info("Test 13_14 Update SEO metadatas with localization");
		String random = getRandomNumber();
		String title = txData.getContentByArrayTypeRandom(1)+random;
		
		int index1 = changLangData.getRandomIndexByType(1);
		info("index1:"+index1);
		String language1=changLangData.language.get(index1);
		info("language1 is:"+language1);
		String apply1 = changLangData.applyBtn.get(index1);
		
		int index2 = changLangData.getRandomIndexByType(2)+1;
		info("index2:"+index2);
		String language2=changLangData.language.get(index2);
		info("language2 is:"+language2);
	    String apply2 = changLangData.applyBtn.get(index2);
		
		/*Step Number: 1
		 *Step Name: Step 1: Edit SEO metadata with localization
		 *Step Description: 
			- Log in acme home page as admin
			- Go to Edit/ Page/ SEO
			- Select a language that you want to update SEO metadata form languages list
			- Update fields that you want to modify
			- Save
		 *Input Data: 

		 *Expected Outcome: 
			- SEO information is saved
			- Language is updated
			- View page source, updated SEO information is shown
			- Sitemap.xml is updated*/ 
		this.driver.get(baseUrl+"/acme");
		Utils.pause(2000);
		navTool.goToSEO();
		
		click(seoMang.ELEMENT_SEO_LANGUAGE_SHOW);
		select(seoMang.ELEMENT_SEO_LANGUAGE_SELECTBOX,language1);
		type(seoMang.ELEMENT_SEO_TITLEBOX, title, true);
		click(seoMang.ELEMENT_SEO_SAVE);
		click(seoMang.ELEMENT_SEO_CLOSE);
		this.driver.navigate().refresh();
		navTool.goToChangeLanguage();
		changLang.changeLanguage(language1,apply1);
		//Verify that French language is changed
		waitForAndGetElement(acmeHP.ELEMENT_NAVIGATION_MENU_OVERVIEW_FRENCH);
		
		navTool.goToChangeLanguage();
		info("Changed language is:"+language2);
		changLang.changeLanguage(language2,apply2);
		
		//Verify that the language is changed to English
		waitForAndGetElement(acmeHP.ELEMENT_NAVIGATION_MENU_OVERVIEW_ENGLISH);
		
		this.driver.get(baseUrl+"/intranet");
		//Verify that sitemaps file is updated
		waitForAndGetElement(hp.ELEMENT_SITEMAPS_ACTIVITY);
	
		
		info("Delete SEO folder");
		navTool.goToSiteExplorer();
		SEHome.goToPath("acme/SEO", "Sites Management");
		SEHome.deleteData("SEO");
		info("Detete added language on SEO management");
		this.driver.get(baseUrl+"/acme");
		navTool.goToSEO();
		seoMang.deleteLanguage(language1);
	}
}
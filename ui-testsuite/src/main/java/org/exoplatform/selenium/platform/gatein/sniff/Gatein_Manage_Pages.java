package org.exoplatform.selenium.platform.gatein.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.testng.annotations.*;


	/**
	* @author eXo
	*
	*/
	public class Gatein_Manage_Pages extends GateIn_TestConfig{

	/**
	*<li> Case ID:123032.</li>
	*<li> Test Case Name: Add page of portal using Page Management.</li>
	*/
	@Test
	public  void test02_AddPageOfPortalUsingPageManagement() {
		info("Test 02:Add page of portal using Page Management");
		String num=getRandomNumber();
		String pageName =txData.getContentByArrayTypeRandom(1)+num;
		String title = txData.getContentByArrayTypeRandom(1)+num;
		/*Step Number: 1
		*Step Name: Step 1: Add page of portal	
		*Step Description: 
			- Go to Administration/Portal/Pages
			- Click Add new page
			- Choose Owner type is portal
			- Add some fields required
			- Click Save
		*Input Data: 
			
		*Expected Outcome: 
			- Add page successfully*/
		navToolBar.goToPotalPages();
		portMg.addPage(pageName, title,"");
		info("Verify that the page is added successfully");
		portMg.searchPage(title, "","",true);
 	}

	/**
	*<li> Case ID:123031.</li>
	*<li> Test Case Name:Show and Search page.</li>
	*/
	@Test
	public void test01_ShowAndSearchPage() {
		info("Test 01:  Show and Search page");
		int index = pagMgListData.getRandomIndexByType(1);
		String titlePage = pagMgListData.newTitle.get(index);
		String siteName = pagMgListData.newSiteName.get(index);
		String type = pagMgListData.newModel.get(index);
		/*Step Number: 1
		*Step Name: Step 1: Show and Search page
		*Step Description: 
			- Go to Group/Administration/Pages management
			- Search page by:
			+ Owner title
			+ Owner id
			+ Title
		*Input Data: 
			
		*Expected Outcome: 
			- Pages Management appears, includes:
			+ Search option
			+ All existing pages list
			- The results are displayed matching with the search keyword*/
		navToolBar.goToPotalPages();
		portMg.searchPage(titlePage,siteName,type,true);

 	}

	/**
	*<li> Case ID:123036.</li>
	*<li> Test Case Name: Edit page for user.</li>
	*/
	@Test
	public  void test05_EditPageOfUser() {
		info("Test 05: Edit page for user");
		int index = appLayData.getRandomIndexByType(1);
		String idName = appLayData.newId.get(index);
		String name = appLayData.newTitle.get(index);
		/*Step Number: 1
		*Step Name: Step 1: Edit page for user
		*Step Description: 
			- Select a page/Edit page
			+ Edit portlet, 
			+ View page properties
			+ Drag & drop container and application
			+ Switch view mode
	
		*Input Data: 
			
		*Expected Outcome: 
			- Edit page is updated with new changes
			*/
		navToolBar.goToMyDashboard();
		navToolBar.goToEditLayout();
		pagCW.addContainer("oneRow");
		navToolBar.goToEditLayout();
		pagCW.addApp("",name,pagCW.ELEMENT_APPLICATION_APPLICATION.replace("${name}",idName),pagCW.ELEMENT_DROP_SOURCE_HAS_LAYOUT_BY_NAME.replace("${name}","Container"));
		info("Delete container");
		navToolBar.goToEditLayout();
		pagCW.deleteContainer("Container");

 	}

	/**
	*<li> Case ID:123035.</li>
	*<li> Test Case Name: Add new page for user.</li>
	*/
	@Test
	public  void test04_AddNewPageOfUser() {
		info("Test 04: Add new page for user");
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		int index = appLayData.getRandomIndexByType(1);
		String idName = appLayData.newId.get(index);
		String name = appLayData.newTitle.get(index);
		/*Step Number: 1
		*Step Name:Step 1: Add new page for user
		*Step Description: 
			- Select Dashboard  /Add new page
			- Input value for 3 main steps
			- Save
		*Input Data: 
			
		*Expected Outcome: 
			- New page is created successfully*/
		navToolBar.goToMyDashboard();
		navToolBar.goToAddPage();
		pagCW.inputPageInfoStep1(title, true, "English",title, null,null);
		click(pagCW.ELEMENT_ADDNEWPAGE_BTNNEXT);
		click(pagCW.ELEMENT_ADDNEWPAGE_BTNNEXT);
		pagCW.addApp("",name,pagCW.ELEMENT_APPLICATION_APPLICATION.replace("${name}",idName),pagCW.ELEMENT_PAGEEDITOR_VIEWPAGE);
		info("Verify that the page is added successfully");
		myDash.deleteTab(title);
	}

	/**
	*<li> Case ID:123040.</li>
	*<li> Test Case Name: Edit page for portal.</li>
	*PENDING CASE:THE CASE IS INCORRECT AND DUPPLICATE WITH CASE ID:123101
	*/
	@Test(groups="pendings")
	public  void test06_EditPageForPortal() {
		info("Test 06: Edit page for portal");
		
		/*Step Number: 1
		*Step Name: Step 1: Edit page for portal
		*Step Description: 
			- Go to Site editor /Edit page
			+ Edit portlet, 
			+ View page properties
			+ Drag & drop container and application
			+ Switch view mode
		*Input Data: 
			
		*Expected Outcome: 
			- Edit page is updated with new changes*/
	     
	}

	/**
	*<li> Case ID:123065.</li>
	*<li> Test Case Name: Add new page using winzard for portal.</li>
	*/
	@Test
	public  void test07_AddNewPageUsingWinzard() {
		info("Test 07: Add new page using winzard for portal");
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Add new page for portal
		*Step Description: 
			- Select a site, for example intranet
			- Choose Edit -> Page -> Add Page
			- Input value for 3 main steps
			- Save
		*Input Data: 
			
		*Expected Outcome: 
			- New page is created successfully*/
		hp.goToHomePage();
		navToolBar.goToAddPage();
		info("Create a new page");
		pagCW.inputPageInfoStep1(title, true, "English",title, true,false);
		click(pagCW.ELEMENT_ADDNEWPAGE_BTNNEXT);
		click(pagCW.ELEMENT_ADDNEWPAGE_BTNNEXT);
		pagCW.saveChangesPageEditor();
		
		info("Verify that the page is added successfully");
		navToolBar.goToPotalPages();
		portMg.searchPage(title, "","",true);
		portMg.deletePage(title,"");
	}

	/**
	*<li> Case ID:123046.</li>
	*<li> Test Case Name: Edit page for group.</li>
	*PENDING CASE: THE CASE IS INCORRECT AND DUPPLICATE WITH CASE ID: 123099
	*/
	@Test(groups="pendings")
	public  void test08_EditPageForGroup() {
		info("Test 08: Edit page for group");
		
		/*Step Number: 1
		*Step Name: Step 1: Edit page for group 
		*Step Description: 
			- Select a page
			- Choose Edit -> Page -> Layout
			+ Edit portlet, 
			+ View page properties
			+ Drag & drop container and application
			+ Switch view mode
		*Input Data: 
			
		*Expected Outcome: 
			- Edit page is updated with new changes
			*/
	}
	
	/**
	*<li> Case ID:123100.</li>
	*<li> Test Case Name: Add page of group using Page Management.</li>
	*/
	@Test
	public  void test09_11_Add_Delete_PageOfGroupUsingPageManagement() {
		info("Test 09: Add page of group using Page Management");
		String num=getRandomNumber();
		String pageName =txData.getContentByArrayTypeRandom(1)+num;
		String title = txData.getContentByArrayTypeRandom(1)+num;
		int index = pagMgListData.getRandomIndexByType(2);
		String type = pagMgListData.newModel.get(index);
		/*Step Number: 1
		*Step Name: Step 1: Add page of group
		*Step Description: 
			- Go to Administration/Portal/Pages
			- Click Add new page
			- Choose Owner type is group
			- Add some fields require
			- Click Save
		*Input Data: 
			
		*Expected Outcome: 
			- Add page successfully
			*/
		navToolBar.goToPotalPages();
		info("Add a new page with group type");
		portMg.addPage(pageName, title,type);
		info("Verify that the page is added successfully");
		portMg.searchPage(title, "",type,true);
		info("Test 09: Add page of group using Page Management");
		/*Step Number: 1
		*Step Name: Step 1: Delete page of portal
		*Step Description: 
			-Go to Group/Administration/Page Management
			- Select a page of group and click [Delete page]
		*Input Data: 
			
		*Expected Outcome: 
			- The page is removed from the list
			*/ 
		portMg.deletePage(title,"");
	}

	/**
	*<li> Case ID:123101.</li>
	*<li> Test Case Name: Edit page of portal.</li>
	*/
	@Test
	public  void test10_EditPageOfPortal() {
		info("Test 10: Edit page of portal");
		String num=getRandomNumber();
		String pageName =txData.getContentByArrayTypeRandom(1)+num;
		String title = txData.getContentByArrayTypeRandom(1)+num;
		int index = appLayData.getRandomIndexByType(1);
		String idName = appLayData.newId.get(index);
		String name = appLayData.newTitle.get(index);
		/*Step Number: 1
		*Step Name: Step 1: Edit page of portal
		*Step Description: 
			- Go to Administration/Portal/Pages 
			- Select a page of portal and click [Edit page] icon
			- Change something
			+ Edit portlet, 
			+ View page properties
			+ Drag & drop container and application
			+ Switch view mode
			- Click Save


		*Input Data: 
			
		*Expected Outcome: 
			- The page is updated with the change value
			*/
		navToolBar.goToPotalPages();
		portMg.addPage(pageName, title,"");
		portMg.editPage(title,"");
		pagCW.addApp("",name,pagCW.ELEMENT_APPLICATION_APPLICATION.replace("${name}",idName),pagCW.ELEMENT_PAGEEDITOR_VIEWPAGE);
		info("Verify that all changes of page is saved");
		portMg.editPage(title,"");
		waitForAndGetElement(pagCW.ELEMENT_APPLICATION_IN_LAYOUT_PAGE.replace("${name}",name),3000,0);
		pagCW.saveChangesPageEditor();
		portMg.deletePage(title,"");
	}

	/**
	*<li> Case ID:123102.</li>
	*<li> Test Case Name: Delete page of portal.</li>
	*/
	@Test
	public  void test03_DeletePageOfPortal() {
		info("Test 03: Delete page of portal");
		String num=getRandomNumber();
		String pageName =txData.getContentByArrayTypeRandom(1)+num;
		String title = txData.getContentByArrayTypeRandom(1)+num;
		/*Step Number: 1
		*Step Name: Step 1: Delete page of portal
		*Step Description: 
			- Go to Group/Administration/Page Management
			- Select a page of portal and click [Delete page]
	
		*Input Data: 
			
		*Expected Outcome: 
			- The page is removed from the list
			*/
		navToolBar.goToPotalPages();
		portMg.addPage(pageName, title,"");
		portMg.deletePage(title,"");
	
	}

	/**
	*<li> Case ID:123045.</li>
	*<li> Test Case Name: Add new page using winzard for group.</li>
	*PENDING: THE TEST CASE IS INCORRECT
	*/
	@Test(groups="pendings")
	public  void test12_AddNewPageUsingWinzardForGroup() {
		info("Test 07: Add new page using winzard for group");
		
		/*Step Number: 1
		*Step Name: Step 1: Add new page for group
		*Step Description: 
			- Select a site, for example intranet
			- Choose Edit -> Page -> Add Page
			- Input value for 3 main steps
			- Save
		*Input Data: 
			
		*Expected Outcome: 
			- New page is created successfully*/
		
	}

	/**
	*<li> Case ID:123099.</li>
	*<li> Test Case Name: Edit page for group.</li>
	*/
	@Test
	public  void test13_EditPageForGroup() {
		info("Test 13: Edit page for group");
		info("Get information about tiltle, pageName for new page");
		String num=getRandomNumber();
		String pageName =txData.getContentByArrayTypeRandom(1)+num;
		String title = txData.getContentByArrayTypeRandom(1)+num;
		info("Get value of group type");
		int index = pagMgListData.getRandomIndexByType(2);
		String type = pagMgListData.newModel.get(index);
		
		info("Get information of an application");
		int index2 = appLayData.getRandomIndexByType(1);
		String idName = appLayData.newId.get(index2);
		String name = appLayData.newTitle.get(index2);
		/*Step Number: 1
		*Step Name: Step 1: Edit page for group 
		*Step Description: 
			- Go to Group/Administration/Page Management
			- Select a page of group in list and click [Edit page] icon
			- Change something:
			+ Edit portlet, 
			+ View page properties
			+ Drag & drop container and application
			+ Switch view mode 
			- Click Save

		*Input Data: 
			
		*Expected Outcome: 
			- The page is updated with the change value
			*/
		navToolBar.goToPotalPages();
		info("Add a new page with group type");
		portMg.addPage(pageName, title,type);
		info("Edit a page");
		portMg.editPage(title,type);
		pagCW.addApp("",name,pagCW.ELEMENT_APPLICATION_APPLICATION.replace("${name}",idName),pagCW.ELEMENT_PAGEEDITOR_VIEWPAGE);
		
		info("Verify that all changes of page is saved");
		portMg.editPage(title,type);
		waitForAndGetElement(pagCW.ELEMENT_APPLICATION_IN_LAYOUT_PAGE.replace("${name}",name),3000,0);
		pagCW.saveChangesPageEditor();
		
		portMg.deletePage(title,type);
	}
}
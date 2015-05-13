package org.exoplatform.selenium.platform.gatein.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.testng.annotations.*;


	/**
	* @author eXo
	*
	*/
	public class Gatein_Portal_Navigation_Edit_LayOut extends GateIn_TestConfig{

		
	/**
	*<li> Case ID:99433.</li>
	*<li> Test Case Name: Change site's config of portal.</li>
	*/
	@Test
	public  void test02_ChangeSiteConfigOfPortal() {
		info("Test 02:Change site's config of portal");
		String num = getRandomNumber();
		String portalName = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		addNewPortal(portalName);
		String label = txData.getContentByArrayTypeRandom(1)+num;
		String des   = txData.getContentByArrayTypeRandom(1)+num;
		/*Step Number: 1
		*Step Name: Step 1: Change site's config of portal
		*Step Description: 
			- Go to Administration/Portal/Sites
			- Choose a portal from list and click Edit layout
			- Click Site's config from edit inline composer
			- Make change something and save
		*Input Data: 
			
		*Expected Outcome: 
			Portal Setting, Properties, Permission setting tab are updated successfully with new changes*/
		navToolBar.goToPotalSites();
		portSite.changeConfig(portalName);
		portSite.editSimplePortal("",label,des,"","");
		pagCW.saveChangesPageEditor();
		info("Verify that the changes are updated");
		waitForAndGetElement(portSite.ELEMENT_MANAGESITES_PORTAL_LABEL.replace("${portal}",portalName).replace("${label}",label),3000,0);
		waitForAndGetElement(portSite.ELEMENT_MANAGESITES_PORTAL_DESC.replace("${portal}",portalName).replace("${desc}",des),3000,0);
	}

	/**
	*<li> Case ID:99421.</li>
	*<li> Test Case Name:Add application into container when edit layout for group's page.</li>
	*/
	@Test
	public void test01_AddApplicationIntoContainerWhenEditLayoutForGroupPage() {
		info("Test 01:  Add application into container when edit layout for group's page");
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
		
		String container = contaiData.id.get(0);
		
		/*Step Number: 1
		*Step Name: Step 1: Add application into container when edit layout for group's page
		*Step Description: 
			- Select a page and click Edit layout
			- Add container by drag & drop
			- Drag & drop application into the container added above
			- Switch view mode
		*Input Data: 
			
		*Expected Outcome: 
			- Add application into container successfully
			- The layout of page is displayed in the view mode with all changes*/
		
		navToolBar.goToPotalPages();
		info("Add a new page with group type");
		portMg.addPage(pageName, title,type);
		
		info("Add a container");
		portMg.editPage(title,type);
		pagCW.addContainer(container);
		
		info("Add an application to the container");
		portMg.editPage(title,type);
		pagCW.addApp("",name,pagCW.ELEMENT_APPLICATION_APPLICATION.replace("${name}",idName),pagCW.ELEMENT_DROP_SOURCE_HAS_LAYOUT_BY_NAME.replace("${name}","Container"));
 
		info("Verify that the application is added successfully in the container");
		portMg.editPage(title,type);
		waitForAndGetElement(pagCW.ELEMENT_APPLICATION_IN_LAYOUT_PAGE.replace("${name}",name),3000,0);
		pagCW.switchViewMode(true);
		pagCW.saveChangesPageEditor();
		
		portMg.deletePage(title,type);
	}

	/**
	*<li> Case ID:99599.</li>
	*<li> Test Case Name: Move application when edit layout for portal's page.</li>
	*/
	@Test
	public  void test05_MoveApplicationWhenEditLayoutForPortalPage() {
		info("Test 05: Move application when edit layout for portal's page");
		
		info("Get information of an application");
		int index2 = appLayData.getRandomIndexByType(1);
		String idName = appLayData.newId.get(index2);
		String name = appLayData.newTitle.get(index2);
		/*Step Number: 1
		*Step Name: Step 1: Move application when edit layout for portal's page
		*Step Description: 
			- Select a page and click Edit layout
			- Select application, drag & drop it to new place on page
			- Switch view mode
	
		*Input Data: 
			
		*Expected Outcome: 
			- The application  is move to new place
			- The Layout of page is displayed in the view mode with all changes
			*/
		
		String portalName = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		addNewPortal(portalName);
		
		info("Add an application to the layout");
		navToolBar.goToEditLayout();
		pagCW.addApp("",name,pagCW.ELEMENT_APPLICATION_APPLICATION.replace("${name}",idName),pagCW.ELEMENT_PAGEEDITOR_VIEWPAGE);
 
		info("Move an application to new place");
		navToolBar.goToEditLayout();
		pagCW.moveApplication(name,"Announcement",87);
		navToolBar.goToEditLayout();
		pagCW.checkPositions(pagCW.ELEMENT_APPLICATION_PRECEDING_PORTLET.replace("${app1}","Announcement").replace("${app2}",name),pagCW.ELEMENT_APPLICATION_FOLLOWING_PORTLET.replace("${app1}","Announcement").replace("${app2}",name));

		info("Delete an application");
		navToolBar.goToEditLayout();
		pagCW.deleteApplication(name);
 	}

	/**
	*<li> Case ID:99435.</li>
	*<li> Test Case Name: Edit portal's config.</li>
	*/
	@Test
	public  void test04_EditPortalConfig() {
		info("Test 04: Edit portal's config");
		String num = getRandomNumber();
		String portalName = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		addNewPortal(portalName);
		String label = txData.getContentByArrayTypeRandom(1)+num;
		String des   = txData.getContentByArrayTypeRandom(1)+num;
		/*Step Number: 1
		*Step Name:Step 1: Edit portal's config
		*Step Description: 
			- Go to Administration/Portal/Sites
			- Choose a portal from list and click Edit portal config
			- Make change something and save
		*Input Data: 
			
		*Expected Outcome: 
			Portlet Setting, Properties, Permission setting tab is updated successfully with new changes*/
		navToolBar.goToPotalSites();
		portSite.goToEditSiteConfig(portalName);
		portSite.editSimplePortal("",label,des,"","");
		info("Verify that the changes are updated");
		waitForAndGetElement(portSite.ELEMENT_MANAGESITES_PORTAL_LABEL.replace("${portal}",portalName).replace("${label}",label),3000,0);
		waitForAndGetElement(portSite.ELEMENT_MANAGESITES_PORTAL_DESC.replace("${portal}",portalName).replace("${desc}",des),3000,0);
	}

	/**
	*<li> Case ID:99600.</li>
	*<li> Test Case Name: Add application when edit layout for portal's page.</li>
	*/
	@Test
	public  void test06_AddApplicationWhenEditLayoutForFortalPage() {
		info("Test 06: Add application when edit layout for portal's page");
		
		info("Get information of an application");
		int index2 = appLayData.getRandomIndexByType(1);
		String idName = appLayData.newId.get(index2);
		String name = appLayData.newTitle.get(index2);
		/*Step Number: 1
		*Step Name: Step 1: Add application when edit layout for portal's page
		*Step Description: 
			- Select a page and click Edit layout
			- Select Application tab from edit inline composer
			- Add application by drag & drop
			- Switch view mode
		*Input Data: 
			
		*Expected Outcome: 
			- Add application successfully
			- The Layout of page is displayed in the view mode with all changes*/
		String portalName = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		addNewPortal(portalName);
		
		info("Add an application to the layout");
		navToolBar.goToEditLayout();
		pagCW.addApp("",name,pagCW.ELEMENT_APPLICATION_APPLICATION.replace("${name}",idName),pagCW.ELEMENT_PAGEEDITOR_VIEWPAGE);
 
		info("Verify that the application is added successfully in the container");
		navToolBar.goToEditLayout();
		waitForAndGetElement(pagCW.ELEMENT_APPLICATION_IN_LAYOUT_PAGE.replace("${name}",name),3000,0);
		pagCW.switchViewMode(true);
		pagCW.saveChangesPageEditor();
		
		info("Delete an application");
		navToolBar.goToEditLayout();
		pagCW.deleteApplication(name);
	
	}

	/**
	*<li> Case ID:99601.</li>
	*<li> Test Case Name: Edit application when edit layout for portal's page.</li>
	**<li> Case ID:99602.</li>
	*<li> Test Case Name: Remove application when edit layout for portal's page.</li>
	*/
	@Test
	public  void test07_08_Edit_Remove_ApplicationWhenEditLayoutForPortalPage() {
		info("Test 07: Edit application when edit layout for portal's page");
		info("Get information of an application");
		int index2 = appLayData.getRandomIndexByType(1);
		String idName = appLayData.newId.get(index2);
		String name = appLayData.newTitle.get(index2);
		String newTitle = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String portalName = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		addNewPortal(portalName);
		/*Step Number: 1
		*Step Name: Step 1: Change application when edit layout for portal's page
		*Step Description: 
			- Select a page and click Edit layout
			- Select application on page and click Edit portlet icon
			- Change something and click [Save and Close]
			- Click Finish button
			- Switch view mode
		*Input Data: 
			
		*Expected Outcome: 
			- The application is updated with the change value
			- The Layout of page is displayed in the view mode with all changes*/

		info("Add an application to the layout");
		navToolBar.goToEditLayout();
		pagCW.addApp("",name,pagCW.ELEMENT_APPLICATION_APPLICATION.replace("${name}",idName),pagCW.ELEMENT_PAGEEDITOR_VIEWPAGE);
		
		info("Edit an application");
		navToolBar.goToEditLayout();
		pagCW.editApplication(name,newTitle,"","");
		pagCW.saveChangesApplication();
		pagCW.saveChangesPageEditor();
		
		info("Verify that the application is added successfully in the container");
		navToolBar.goToEditLayout();
		waitForAndGetElement(pagCW.ELEMENT_APPLICATION_IN_LAYOUT_PAGE.replace("${name}",newTitle),3000,0);
		pagCW.switchViewMode(true);
		pagCW.saveChangesPageEditor();
		
		info("Test 08:Remove application when edit layout for portal's page");

		/*Step Number: 1
		*Step Name: Step 1: Remove application when edit layout for portal's page
		*Step Description: 
			- Select a page and click Edit layout
			- Select application on page and click [Delete portlet] icon
			- Switch view mode
		*Input Data: 
			
		*Expected Outcome: 
			- The application  is removed successfully
			- The Layout of page is displayed in the view mode with all changes
			*/
		navToolBar.goToEditLayout();
		pagCW.deleteApplication(newTitle);
	}

	/**
	*<li> Case ID:99605.</li>
	*<li> Test Case Name: Add container when edit layout for portal's page.</li>
	*<li> Case ID:99606.</li>
	*<li> Test Case Name: Edit container when edit layout for portal's page.</li>
	*<li> Case ID:99604.</li>
	*<li> Test Case Name: Move container when edit layout for portal's page.</li>
	*<li> Case ID:99607.</li>
	*<li> Test Case Name: Delete container when edit layout for portal's page.</li>
	*/
	@Test
	public  void test09_10_11_12_Add_Edit_Move_Delete_ContainerWhenEditLayoutForPortalPage() {
		String num=getRandomNumber();
		String title = txData.getContentByArrayTypeRandom(1)+num;
		info("Get information of an application");
		int index2 = appLayData.getRandomIndexByType(1);
		String idName = appLayData.newId.get(index2);
		String name = appLayData.newTitle.get(index2);
		
		hp.goToHomePage();
		navToolBar.goToAddPage();
		info("Create a new page");
		pagCW.inputPageInfoStep1(title, true, "English",title, true,false);
		click(pagCW.ELEMENT_ADDNEWPAGE_BTNNEXT);
		click(pagCW.ELEMENT_ADDNEWPAGE_BTNNEXT);
		pagCW.saveChangesPageEditor();
		
		info("Add an application to the layout");
		navToolBar.goToEditLayout();
		pagCW.addApp("",name,pagCW.ELEMENT_APPLICATION_APPLICATION.replace("${name}",idName),pagCW.ELEMENT_PAGEEDITOR_VIEWPAGE);
		
		info("Test 09: Add container when edit layout for portal's page");
		/*Step Number: 1
		*Step Name: Step 1: Add container when edit layout for portal's page
		*Step Description: 
			- Select a page and click Edit layout
			- Select Container tab from edit inline composer
			- Add container by drag & drop
			- Switch view mode

		*Input Data: 
			
		*Expected Outcome: 
			- Add container successfully
			- The Layout of page is displayed in the view mode with all changes
			*/
		navToolBar.goToEditLayout();
		pagCW.addContainer("oneRow");
	
		info("Test 10: Edit container when edit layout for portal's page");
		/*Step Number: 1
		*Step Name: Step 1: Edit container when edit layout for portal's page
		*Step Description: 
			- Select a page and click Edit layout
			- Select a container in page and click Edit container
			- Change something 
			- Click Save and Finish button
			- Switch view mode
		*Input Data: 
			
		*Expected Outcome: 
			- The container is updated with the change value
			- The Layout of page is displayed in the view mode with all changes*/
        info("Edit a container");
		navToolBar.goToEditLayout();
		pagCW.editContainer("Container",title, "","");
		
		info("Test 11: Move container when edit layout for portal's page");
		/*Step Number: 1
		*Step Name: Step 1: Move container when edit layout for portal's page
		*Step Description: 
			- Select a page and click Edit layout
			- Select a container in page and drag & drop it to new page 
			- Switch view mode
		*Input Data: 
			
		*Expected Outcome: 
			- The container is move to new place
			- The Layout of page is displayed in the view mode with all changes
			*/
		info("Move a container to new place");
		navToolBar.goToEditLayout();
		pagCW.moveContainer(title,pagCW.ELEMENT_CONTAINER_HOLDER_MOVE,pagCW.ELEMENT_PORTLET,87);
		
		info("Verify that the container is changed the position");
		navToolBar.goToEditLayout();
		pagCW.checkPositions(pagCW.ELEMENT_CONTAINER_PRECEDING_PORTLET,pagCW.ELEMENT_CONTAINER_FOLLOWING_PORTLET);
		
		info("Test 12: Delete container when edit layout for portal's page");
		/*Step Number: 1
		*Step Name: Step 1: Delete container when edit layout for portal's page
		*Step Description: 
			- Select a page and click Edit layout
			- Select a container on page and click Delete container
			- Switch view mode

		*Input Data: 
			
		*Expected Outcome: 
			- The container is removed successfully
			- The Layout of page is displayed in the view mode with all changes
			*/
		info("Delete a container");
		navToolBar.goToEditLayout();
		pagCW.deleteContainer(title);
		
	}
	/**
	*<li> Case ID:99434.</li>
	*<li> Test Case Name: Add application into container in layout of portal.</li>
	*/
	@Test
	public  void test03_AddApplicationIntoContainerInLayoutOfPortal() {
		info("Test 03: Add application into container in layout of portal");
		String num=getRandomNumber();
		String title = txData.getContentByArrayTypeRandom(1)+num;
		info("Get value of group type");
		int index = pagMgListData.getRandomIndexByType(1);
		String type = pagMgListData.newModel.get(index);
		
		info("Get information of an application");
		int index2 = appLayData.getRandomIndexByType(1);
		String idName = appLayData.newId.get(index2);
		String name = appLayData.newTitle.get(index2);
		/*Step Number: 1
		*Step Name: Step 1: Add application into container in layout of portal
		*Step Description: 
	        - Go to Site
			- Choose a portal from list and click Edit layout
			- Add container by drag & drop
			- Drag & drop application into the container added above
			- Switch view mode
		*Input Data: 
			
		*Expected Outcome: 
			- Add application into container successfully
			- The portal is displayed in the view mode with all changes
			*/
		
		hp.goToHomePage();
		navToolBar.goToAddPage();
		info("Create a new page");
		pagCW.inputPageInfoStep1(title, true, "English",title, true,false);
		click(pagCW.ELEMENT_ADDNEWPAGE_BTNNEXT);
		click(pagCW.ELEMENT_ADDNEWPAGE_BTNNEXT);
		pagCW.saveChangesPageEditor();
		
		navToolBar.goToEditLayout();
		pagCW.addContainer("oneRow");
		
		info("Add an application to the container");
		navToolBar.goToEditLayout();
		pagCW.addApp("",name,pagCW.ELEMENT_APPLICATION_APPLICATION.replace("${name}",idName),pagCW.ELEMENT_DROP_SOURCE_HAS_LAYOUT_BY_NAME.replace("${name}","Container"));
 
		info("Verify that the application is added successfully in the container");
		navToolBar.goToEditLayout();
		waitForAndGetElement(pagCW.ELEMENT_APPLICATION_IN_LAYOUT_PAGE.replace("${name}",name),3000,0);
		pagCW.switchViewMode(true);
		pagCW.saveChangesPageEditor();
		
		navToolBar.goToPotalPages();
		portMg.deletePage(title,type);
	
	}
}
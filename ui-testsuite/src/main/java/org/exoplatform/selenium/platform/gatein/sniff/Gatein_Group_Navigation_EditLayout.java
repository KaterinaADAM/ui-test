package org.exoplatform.selenium.platform.gatein.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.testng.annotations.Test;

/**
 * @date 17 March 2015
 * @author tult
 */

public class Gatein_Group_Navigation_EditLayout extends GateIn_TestConfig {
	
	/**
	*<li> Case ID:123105.</li>
	*<li> Test Case Name: Add application when edit layout for group's page.</li>
	*<li> Case ID:123106.</li>
	*<li> Test Case Name: Edit application when edit layout for group's page.</li>
	*<li> Case ID:123107.</li>
	*<li> Test Case Name: Remove application when edit layout for group's page.</li>
	*/
	@Test
	public void test01_02_03_AddEditRemoveAppWhenEditLayout_GroupPage(){
		info("test01_02_03_AddEditRemoveAppWhenEditLayout_GroupPage()");
		String pageName = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
        
        info("Get information of an application");
		int index = appLayData.getRandomIndexByType(1);
		String portletId = appLayData.newId.get(index);
		String portletName = appLayData.newTitle.get(index);
		String newPortletName = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		
		/*Step Number: 1
		*Step Name: Step 1: Add application when edit layout of page
		*Step Description: 
			- Select a page and click Edit layout
			- Select Application tab from edit inline composer
			- Add application by drag & drop
			- Switch view mode
		*Input Data: 
		*Expected Outcome: 
			- Add application successfully
			- The Layout of page is displayed in the view mode with all changes*/
        
		info("Add new page of group");
		navToolBar.goToSiteExplorer();
		navToolBar.goToAddPage();
		pagCW.addAPageSimple(pageName,pageName);
		navToolBar.goToEditLayout();
		pagCW.addApp("",portletName,pagCW.ELEMENT_APPLICATION_APPLICATION.replace("${name}",portletId),pagCW.ELEMENT_PAGEEDITOR_VIEWPAGE);
		
		info("Verify that the application is added successfully");
		navToolBar.goToEditLayout();
		waitForAndGetElement(pagCW.ELEMENT_APPLICATION_IN_LAYOUT_PAGE.replace("${name}",portletName),3000,0);
		pagCW.saveChangesPageEditor();
		
		/*Step Number: 2
		*Step Name: Step 2: Edit application when edit layout of page
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
		
		info("Edit an application");
		navToolBar.goToEditLayout();
		pagCW.editApplication(portletName,newPortletName,"","");
		pagCW.saveChangesApplication();
		pagCW.saveChangesPageEditor();
		
		info("Verify that the application is added successfully");
		navToolBar.goToEditLayout();
		waitForAndGetElement(pagCW.ELEMENT_APPLICATION_IN_LAYOUT_PAGE.replace("${name}",newPortletName),3000,0);
		pagCW.saveChangesPageEditor();
		
		/*Step Number: 3
		*Step Name: Step 3: Remove application when edit layout for group's page
		*Step Description: 
			- Select a page and click Edit layout
			- Select application on page and click [Delete portlet] icon
			- Switch view mode
		*Input Data: 
		*Expected Outcome: 
			- The application  is removed successfully
			- The Layout of page is displayed in the view mode with all changes*/
		
		navToolBar.goToEditLayout();
		pagCW.deleteApplication(newPortletName);
		
	}
	
	
	/**
	*<li> Case ID:123049.</li>
	*<li> Test Case Name: Move application when edit layout for group's page.</li>
	*/
	@Test
	public void test04_MoveAppWhenEditLayout_GroupPage(){
		info("test04_MoveAppWhenEditLayout_GroupPage()");
		
		 info("Get information of an application");
	     int index = appLayData.getRandomIndexByType(1);
		 String portletId = appLayData.newId.get(index);
		 String portletName = appLayData.newTitle.get(index);
		
		/*Step Number: 1
		*Step Name: Step 1: Move application when edit layout for group's page
		*Step Description: 
			- Select a page and click Edit layout
			- Select application, drag & drop it to new place on page
			- Switch view mode
		*Input Data: 
		*Expected Outcome: 
			- The application  is move to new place
			- The Layout of page is displayed in the view mode with all changes*/
		
		info("Add new page of group");
		navToolBar.goToSiteExplorer();
		
		navToolBar.goToEditLayout();
		pagCW.addApp("",portletName,pagCW.ELEMENT_APPLICATION_APPLICATION.replace("${name}",portletId),pagCW.ELEMENT_PAGEEDITOR_VIEWPAGE);
		
		info("Move an application to new place");
		navToolBar.goToEditLayout();
		pagCW.moveApplication(portletName,"Authoring Dashboard",105);
		navToolBar.goToEditLayout();
		pagCW.checkPositions(pagCW.ELEMENT_APPLICATION_PRECEDING_PORTLET.replace("${app1}","Authoring Dashboard").replace("${app2}",portletName),
				pagCW.ELEMENT_APPLICATION_FOLLOWING_PORTLET.replace("${app1}","Authoring Dashboard").replace("${app2}",portletName));
	
		info("Delete an application");
		navToolBar.goToEditLayout();
		pagCW.deleteApplication(portletName);
	}

	
	
	/**
	*<li> Case ID:123108.</li>
	*<li> Test Case Name: Add container when editing layout for group's page.</li>
	*<li> Case ID:123109.</li>
	*<li> Test Case Name: Edit container when editing layout for group's page.</li>
	*<li> Case ID:123048.</li>
	*<li> Test Case Name: Move container when editing layout for group's page.</li>
	*<li> Case ID:123110.</li>
	*<li> Test Case Name: Delete container when editing layout for group's page.</li>
	*/
	@Test
	public void test05_06_07_08_AddEditMoveDeleteContainerWhenEditLayout_GroupPage(){
		info("test05: Add a container");
		
		String num=getRandomNumber();
		String title = txData.getContentByArrayTypeRandom(1)+num;
		String container = contaiData.id.get(0);
		
		 info("Get information of an application");
	     int index = appLayData.getRandomIndexByType(1);
		 String portletId = appLayData.newId.get(index);
		 String portletName = appLayData.newTitle.get(index);
		
		/*Step Number: 1
		*Step Name: Step 1: Add container when edit layout for group's page
		*Step Description: 
			- Select a page and click Edit layout
			- Select Container tab from edit inline composer
			- Add container by drag & drop
			- Switch view mode
		*Input Data: 
		*Expected Outcome: 
			- Add container successfully
			- The Layout of page is displayed in the view mode with all changes*/
		
		info("Create a New page with Empty layout");
		navToolBar.goToSiteExplorer();
		navToolBar.goToAddPage();
		pagCW.addAPageSimple(title,title);
		
		navToolBar.goToEditLayout();
		pagCW.addApp("",portletName,pagCW.ELEMENT_APPLICATION_APPLICATION.replace("${name}",portletId),pagCW.ELEMENT_PAGEEDITOR_VIEWPAGE);
		
		navToolBar.goToEditLayout();
		pagCW.addContainer(container);
		
		info("Test Case 06: Edit a container");
		/*Step Number: 2
		*Step Name: Step 2: Edit container when edit layout for group's page
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
		navToolBar.goToEditLayout();
		pagCW.editContainer("Container",title, "","");
		
		info("Test Case 07: Move a container");
		/*Step Number: 3
		*Step Name: Step 3: Move container when edit layout for group's page
		*Step Description: 
			- Select a page and click Edit layout
			- Select a container in page and drag & drop it to new page 
			- Switch view mode
		*Input Data: 
		*Expected Outcome: 
			- The container is move to new place
			- The Layout of page is displayed in the view mode with all changes*/
		info("Move a container to new place");
		navToolBar.goToEditLayout();
		pagCW.moveContainer(title,pagCW.ELEMENT_CONTAINER_HOLDER_MOVE,pagCW.ELEMENT_PORTLET,87);
		
		info("Verify that the container is changed the position");
		navToolBar.goToEditLayout();
		pagCW.checkPositions(pagCW.ELEMENT_CONTAINER_PRECEDING_PORTLET,pagCW.ELEMENT_CONTAINER_FOLLOWING_PORTLET);
		
		info("Test Case 08: Delete a container");
		/*Step Number: 4
		*Step Name: Step 4: Delete container when edit layout for group's page
		*Step Description: 
			- Select a page and click Edit layout
			- Select a container on page and click Delete container
			- Switch view mode
		*Input Data: 
		*Expected Outcome: 
			- The container is removed successfully
			- The Layout of page is displayed in the view mode with all changes*/
		
		navToolBar.goToEditLayout();
		pagCW.deleteContainer(title);
	}
	
	/**
	*<li> Case ID:123047.</li>
	*<li> Test Case Name: View page properties when editing layout for group's page.</li>
	*/
	@Test
	public void test09_ViewPropertiesWhenEditLayout_GroupPage(){
		info("test09_ViewPropertiesWhenEditLayout_GroupPage()");
		String title = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String newtitle = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String groupPath = portGroupPermisData.getContentByIndex(0);
		String memberships = portMemPermisData.getContentByIndex(0);
		
		/*Step Number: 1
		*Step Name: Step 1: View page properties when edit layout for group's page
		*Step Description: 
			- Select a page and click Edit layout
			- Choose View Page properties on Page Editor
			- Make change something and Save and Finish button 
		*Input Data: 
		*Expected Outcome: 
			- Portal Setting, Permission setting tab is updated successfully with new changes*/
		
		navToolBar.goToSiteExplorer();
		navToolBar.goToAddPage();
		pagCW.addAPageSimple(title, title);
		
		navToolBar.goToEditLayout();
		pagCW.viewProperties(true);
		pagCW.changeProperties(newtitle,groupPath,memberships,true,false);
		
		info("Verify that the changs are updated");
		pagCW.viewProperties();
		info("Page settings is updated");
		String titleActual = pagCW.getOldTitle();
		info("titleActual:"+titleActual);
		info("newtitle:"+newtitle);
		info("Permission setting tab is updated");
		click(pagCW.ELEMENT_VIEW_PROPERTIES_PERMISSION_TAB);
		waitForAndGetElement(pagCW.ELEMENT_VIEW_PROPERTIES_ACCESS_PERMISSTION_VALUE.replace("${group}",groupPath.toLowerCase()),2000,0);
		pagCW.saveChangeProperties();
		pagCW.saveChangesPageEditor();
		if(!titleActual.equals(newtitle)) assert false:"The title:"+newtitle+" is not updated";
	}
	
}

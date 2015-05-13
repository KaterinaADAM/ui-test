package org.exoplatform.selenium.platform.gatein.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.testng.annotations.*;


	/**
	* @author eXo
	*
	*/
	public class Gatein_Portal_Navigation_Edit_Navigation extends GateIn_TestConfig{

		
	/**
	 * *<li> Case ID:123122.</li>
	*<li> Test Case Name:Delete a node.</li>
	*<li> Case ID:123123.</li>
	*<li> Test Case Name:Add node for portal.</li>
	*<li> Case ID:123041.</li>
	*<li> Test Case Name:Edit node's page properties.</li>
	*/
	@Test
	public void test01_Add_Edit_Delete_NodePage() {
		info("Test 01: Add new node");
		String portalName = portDeftData.getContentByIndex(1);
		String nodeName = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String namePage= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String titlePage= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		String newTitlePage= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String groupPath = portGroupPermisData.getContentByIndex(0);
		String memberships = portMemPermisData.getContentByIndex(0);
		/*Step Number: 1
		*Step Name: Step 1: Add node for portal
		*Step Description: 
			- Go to Administration/Portal/ Sites/Edit navigation
			- Add new node by click add new node button or right click
			- Input value for Page Node Setting and Page Selector form
			- Click Save
		*Input Data: 
			
		*Expected Outcome: 
			Add node successfully
			*/
		navToolBar.goToPotalSites();
		portSite.goToEditNavigation(portalName);
		info("Add a new node");
		navMag.addNode(nodeName,"");
		navMag.inputInfoPageSelector(namePage, titlePage, true,false,false);
		navMag.saveNode();
		
		info("Test 02: Edit node Page");
		/*Step Number: 1
		*Step Name: Step 1: Edit node's page properties
		*Step Description: 
			- Go to Administration/Portal/Sites/Edit navigation
			- Select a node
			- Right click and choose Edit node's page
			- Choose View Page properties on Page Editor
			- Edit some changes
			- Click Save button
		*Input Data: 
			
		*Expected Outcome: 
			Page Setting, Permission setting tab are updated successfully with new changes*/
		portSite.goToEditNavigation(portalName);
		navMag.editNodePage(nodeName);
		pagCW.viewProperties();
		pagCW.changeProperties(newTitlePage,groupPath,memberships,true,false);
		
		info("Verify that the changs are updated");
		pagCW.viewProperties();
		info("Page settings is updated");
		String titleActual = pagCW.getOldTitle();
		info("titleActual:"+titleActual);
		info("newtitle:"+newTitlePage);
		info("Permission setting tab is updated");
		click(pagCW.ELEMENT_VIEW_PROPERTIES_PERMISSION_TAB);
		waitForAndGetElement(pagCW.ELEMENT_VIEW_PROPERTIES_ACCESS_PERMISSTION_VALUE.replace("${group}",groupPath.toLowerCase()),2000,0);
		pagCW.saveChangeProperties();
		pagCW.saveChangesPageEditor();
		if(!titleActual.equals(newTitlePage)) assert false:"The title:"+newTitlePage+" is not updated";
		
		info("Test 03: Delete node");
		/*Step Number: 1
		*Step Name: Step 1: Delete Node
		*Step Description: 
			- Go to Administration/Portal/ Sites/Edit navigation
			- Select a node and choose Delete node by right click 
			- Click OK in the confirmation message to accept your deletion. 
			- Click Save
		*Input Data: 
			
		*Expected Outcome: 
			- The node is removed from the list*/
		navMag.deleteNode(nodeName);
	}

	/**
	*<li> Case ID:123124.</li>
	*<li> Test Case Name:Edit node.</li>
	*/
	@Test
	public void test04_EditNode() {
		info("Test 04: Edit Node");
		String portalName = portDeftData.getContentByIndex(1);
		String nodeName = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String newNodeName= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		/*Step Number: 1
		*Step Name: Step 1: Add node for portal
		*Step Description: 
			- Go to Administration/Portal/ Sites/Edit navigation
			- Select a node and choose Edit node by right click 
			- Change values in fields of the current node, except the Node Name. 
			- Click Save
		*Input Data: 
			
		*Expected Outcome: 
			The node is updated with the change value
			*/
		navToolBar.goToPotalSites();
		portSite.goToEditNavigation(portalName);
		info("Add a new node");
		navMag.addNode(nodeName,"");
		navMag.saveNode();
		
		info("Edit a node");
		portSite.goToEditNavigation(portalName);
		navMag.editThisNode(nodeName);
		navMag.inputInfoNodeSetting(true, "",newNodeName,true,false);
		navMag.saveNode();
		
		info("Verify that the node is changed with new name");
		portSite.goToEditNavigation(portalName);
		waitForAndGetElement(navMag.ELEMENT_NAVIGATION_MANAGEMENT_NODE_NAME.replace(
				"${name}",newNodeName));
		navMag.editThisNode(newNodeName);
		info("Verify that label mode is not checked");
		waitForElementNotPresent(navMag.ELEMENT_NODE_SETTING_SWITCH_MODE_CHECKED);
		info("Verify that language box is not shown");
		waitForElementNotPresent(navMag.ELEMENT_NODE_SETTING_LANGUAGE_BOX);
		info("Verify that visible is not checked");
		waitForElementNotPresent(navMag.ELEMENT_NODE_SETTING_VISIBLE_CHECKED);
		info("Verify that publish date and time is not shown");
		waitForElementNotPresent(navMag.ELEMENT_NODE_SETTING_PUBLISH_DATE_TIME);
		navMag.saveNode();
	}

	/**
	*<li> Case ID:123042.</li>
	*<li> Test Case Name:Add application into container when edit page properties of node.</li>
	*/
	@Test
	public void test05_AddApplicationIntoContainerWhenEditPagePropertiesOfNode() {
		info("Test 05: Add application into container when edit page properties of node");
		String portalName = portDeftData.getContentByIndex(1);
		String nodeName = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String namePage= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String titlePage= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		info("Get information of an application");
		int index2 = appLayData.getRandomIndexByType(1);
		String idName = appLayData.newId.get(index2);
		String name = appLayData.newTitle.get(index2);
		
		/*Step Number: 1
		*Step Name: Step 1: Add application into container when edit page properties of node
		*Step Description:
		    - Go to Site/Edit navigation
			- Select a node
			- Right click and choose Edit node's page
			- Add container by drag & drop
			- Drag & drop application into the container added above
			- Switch view mode 
			
		*Input Data: 
			
		*Expected Outcome: 
			- Add application into container successfully
			- The portal is displayed in the view mode with all changes
			*/
		navToolBar.goToPotalSites();
		portSite.goToEditNavigation(portalName);
		info("Add a new node");
		navMag.addNode(nodeName,"");
		navMag.inputInfoPageSelector(namePage, titlePage, true,false,false);
		navMag.saveNode();
		
		info("Edit a node");
		portSite.goToEditNavigation(portalName);
		navMag.editNodePage(nodeName);
		pagCW.addContainer("oneRow");
		
		navMag.editNodePage(nodeName);
		pagCW.addApp("",name,pagCW.ELEMENT_APPLICATION_APPLICATION.replace("${name}",idName),pagCW.ELEMENT_DROP_SOURCE_HAS_LAYOUT_BY_NAME.replace("${name}","Container"));
		info("Click on Save button");
		click(navMag.ELEMENT_NAVIGATION_MANAGEMENT_SAVE);
		
		info("Verify that the application is added successfully in the container");
		portSite.goToEditNavigation(portalName);
		navMag.editNodePage(nodeName);
		waitForAndGetElement(pagCW.ELEMENT_APPLICATION_IN_LAYOUT_PAGE.replace("${name}",name),3000,0);
		pagCW.switchViewMode(true);
		pagCW.saveChangesPageEditor();
		click(navMag.ELEMENT_NAVIGATION_MANAGEMENT_SAVE);
	}

	/**
	*<li> Case ID:123137.</li>
	*<li> Test Case Name:Move application when edit page properties of node.</li>
	*/
	@Test
	public void test09_MoveApplicationWhenEditPagePropertiesOfNode() {
		info("Test 09: Move application when edit page properties of node");
		String portalName = portDeftData.getContentByIndex(1);
		String nodeName = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		pagMgListData.getRandomIndexByType(1);
		String titlePage = pagMgListData.newTitle.get(0);
		
		info("Get information of an application");
		int index1 = appLayData.getRandomIndexByType(1);
		String idportlet1 = appLayData.newId.get(index1);
		String portlet1 = appLayData.newTitle.get(index1);
		
		
		/*Step Number: 1
		*Step Name: Step 1: Move application when edit page properties of node
		*Step Description:
		   Go to Administration/Portal/Sites/Edit navigation
		   - Select a node
		   - Right click and choose Edit node's page
		   - Select Application tab from edit inline composer
		   - Move application on page by drag & drop into new place
		   - Switch view mode
			
		*Input Data: 
			
		*Expected Outcome: 
			- The application is move to new place
			- The page is displayed in the view mode with all changes
			*/
		navToolBar.goToPotalSites();
		portSite.goToEditNavigation(portalName);
		info("Add a new node");
		navMag.addNode(nodeName,"");
		navMag.inputInfoPageSelector("","",false,true,false);
		navMag.selectPage(titlePage);
		navMag.saveNode();
		
		info("Edit a node");
		portSite.goToEditNavigation(portalName);
		navMag.editNodePage(nodeName);
		info("Add an application to the layout");
		pagCW.addApp("",portlet1,pagCW.ELEMENT_APPLICATION_APPLICATION.replace("${name}",idportlet1),pagCW.ELEMENT_PAGEEDITOR_VIEWPAGE);
		info("Click on Save button");
		navMag.closeNavigationManagementPopup();
		
		info("Move an application to new place");
		portSite.goToEditNavigation(portalName);
		navMag.editNodePage(nodeName);
		pagCW.moveApplication(portlet1,"Announcement",87);
		info("Click on Save button");
		navMag.closeNavigationManagementPopup();
		
		
		info("Verify that the application is moved successfully");
		portSite.goToEditNavigation(portalName);
		navMag.editNodePage(nodeName);
		pagCW.checkPositions(pagCW.ELEMENT_APPLICATION_PRECEDING_PORTLET.replace("${app1}","Announcement").replace("${app2}",portlet1),
				pagCW.ELEMENT_APPLICATION_FOLLOWING_PORTLET.replace("${app1}","Announcement").replace("${app2}",portlet1));
		navMag.closeNavigationManagementPopup();
		
		portSite.goToEditNavigation(portalName);
		navMag.editNodePage(nodeName);
		pagCW.deleteApplication(portlet1);
		navMag.closeNavigationManagementPopup();
		
	}

	/**
	*<li> Case ID:123138.</li>
	*<li> Test Case Name:Add application when edit page properties of node.</li>
	*<li> Case ID:123139.</li>
	*<li> Test Case Name:Edit application when edit page properties of node.</li>
	*<li> Case ID:123140.</li>
	*<li> Test Case Name:Remove application when edit page properties of node.</li>
	*/
	@Test
	public void test06_07_08_Add_Edit_Remove_ApplicationWhenEditPagePropertiesOfNode() {
		info("Test 06: Add application when edit page properties of node");
		String portalName = portDeftData.getContentByIndex(1);
		String nodeName = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String namePage= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String titlePage= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		info("Get information of an application");
		int index2 = appLayData.getRandomIndexByType(1);
		String idName = appLayData.newId.get(index2);
		String name = appLayData.newTitle.get(index2);
		/*Step Number: 1
		*Step Name: Step 1: Add application when edit page properties of node
		*Step Description:
		   Go to Administration/Portal/Sites/Edit navigation
		   - Select a node
		   - Right click and choose Edit node's page
		   - Select Application tab from edit inline composer
		   - Add application by drag & drop
		   - Switch view mode
			
		*Input Data: 
			
		*Expected Outcome: 
			- Add application successfully
			- The page is displayed in the view mode with all changes
			*/
		
		navToolBar.goToPotalSites();
		portSite.goToEditNavigation(portalName);
		info("Add a new node");
		navMag.addNode(nodeName,"");
		navMag.inputInfoPageSelector(namePage, titlePage, true,false,false);
		navMag.saveNode();
		
		info("Add an application to the page");
		portSite.goToEditNavigation(portalName);
		navMag.editNodePage(nodeName);
		pagCW.addApp("",name,pagCW.ELEMENT_APPLICATION_APPLICATION.replace("${name}",idName),pagCW.ELEMENT_PAGEEDITOR_VIEWPAGE);
		info("Click on Save button");
		navMag.closeNavigationManagementPopup();
		
		info("Verify that the application is added successfully");
		portSite.goToEditNavigation(portalName);
		navMag.editNodePage(nodeName);
		pagCW.switchViewMode(true);
		pagCW.saveChangesPageEditor();
		navMag.closeNavigationManagementPopup();
		
		info("Test 07: Edit application when edit page properties of node");
		String newTitle= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Edit application when edit page properties of node
		*Step Description:
		 - Go to Administration/Portal/Sites/Edit navigation
		 - Select a node
		 - Right click and choose Edit node's page
		 - Select Application tab from edit inline composer
		 - Choose application on page and click [Edit portlet]
		 - Make change something
		 - Click Save and Close
		 - Switch view mode
			
		*Input Data: 
			
		*Expected Outcome: 
		- The application is updated with the change value
		- The page is displayed in the view mode with all changes
			*/
		portSite.goToEditNavigation(portalName);
		navMag.editNodePage(nodeName);
		pagCW.editApplication(name,newTitle,"","");
		pagCW.saveChangesApplication();
		pagCW.saveChangesPageEditor();
		navMag.closeNavigationManagementPopup();
		
		info("Verify that the application is edited successfully");
		portSite.goToEditNavigation(portalName);
		navMag.editNodePage(nodeName);
		waitForAndGetElement(pagCW.ELEMENT_APPLICATION_IN_LAYOUT_PAGE.replace("${name}",newTitle),3000,0);
		pagCW.switchViewMode(true);
		pagCW.saveChangesPageEditor();
		navMag.closeNavigationManagementPopup();
		
		info("Test 08: Remove application when edit page properties of node");
		/*Step Number: 1
		*Step Name: Step 1: Remove application when edit page properties of node
		*Step Description:
		 - Go to Administration/Portal/Sites/Edit navigation
		 - Select a node
		 - Right click and choose Edit node's page
		 - Select Application tab from edit inline composer
		 - Choose application on page and click Delete portlet
		 - Switch view mode
			
		*Input Data: 
			
		*Expected Outcome: 
		- The application is removed successfully
		- The page is displayed in the view mode with all changes
			*/
		portSite.goToEditNavigation(portalName);
		navMag.editNodePage(nodeName);
		pagCW.deleteApplication(newTitle);
		navMag.closeNavigationManagementPopup();
	}

	/**
	*<li> Case ID:123138.</li>
	*<li> Test Case Name:Add container when edit page properties of node.</li>
	*<li> Case ID:123139.</li>
	*<li> Test Case Name:Edit container when edit page properties of node.</li>
	*<li> Case ID:123140.</li>
	*<li> Test Case Name:Remove container when edit page properties of node.</li>
	*<li> Case ID:123141.</li>
	*<li> Test Case Name:Move container when edit page properties of node.</li>
	*/
	@Test
	public void test10_11_12_13_Add_Edit_Move_Remove_ContainerWhenEditPagePropertiesOfNode() {
		info("Test 10: Add container when edit page properties of node");
		String portalName = portDeftData.getContentByIndex(1);
		String nodeName = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String namePage= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String titlePage= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		
		info("Get information of an application");
		int index2 = appLayData.getRandomIndexByType(1);
		String idName = appLayData.newId.get(index2);
		String name = appLayData.newTitle.get(index2);
		/*Step Number: 1
		*Step Name: Step 1: Add container when edit page properties of node
		*Step Description:
		  - Go to Administration/Portal/Sites/Edit navigation
		  - Select a node
		  - Right click and choose Edit node's page
		  - Select Container tab from Page Editor
		  - Add container by drag & drop
		  - Switch view mode
			
		*Input Data: 
			
		*Expected Outcome: 
			- Add container successfully
			- The page is displayed in the view mode with all changes
			*/
		
		navToolBar.goToPotalSites();
		portSite.goToEditNavigation(portalName);
		info("Add a new node");
		navMag.addNode(nodeName,"");
		navMag.inputInfoPageSelector(namePage, titlePage, true,false,false);
		navMag.saveNode();
		
		info("Add an application to the page");
		portSite.goToEditNavigation(portalName);
		navMag.editNodePage(nodeName);
		pagCW.addApp("",name,pagCW.ELEMENT_APPLICATION_APPLICATION.replace("${name}",idName),pagCW.ELEMENT_PAGEEDITOR_VIEWPAGE);
		info("Click on Save button");
		navMag.closeNavigationManagementPopup();
		
		
		info("Add a container to the layout");
		portSite.goToEditNavigation(portalName);
		navMag.editNodePage(nodeName);
		pagCW.addContainer("oneRow");
		info("Click on Save button");
		navMag.closeNavigationManagementPopup();
		
		info("Test 11: Edit container when edit page properties of node");
		String newTitle= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Edit container when edit page properties of node
		*Step Description:
		- Go to Administration/Portal/Sites/Edit navigation
		- Select a node
		- Right click and choose Edit node's page
		- Select Container tab from Page Editor
		- Select Container in page and click Edit container
		- Make change something
		- Click Save
		- Switch view mode
			
		*Input Data: 
			
		*Expected Outcome: 
		- The container is updated with the change value
		- The page is displayed in the view mode with all changes
			*/
		 info("Edit a container");
		 portSite.goToEditNavigation(portalName);
		 navMag.editNodePage(nodeName);
		 pagCW.editContainer("Container",newTitle, "","");
		 navMag.closeNavigationManagementPopup();
		 
		 info("Test 12: Move container when edit page properties of node");
		 /*Step Number: 1
			*Step Name: Step 1: Move container when edit page properties of node
			*Step Description:
			- Go to Administration/Portal/Sites/Edit navigation
			- Select a node
			- Right click and choose Edit node's page
			- Select Container tab from Page Editor
			- Select container in page and drag& drop it into new place
			- Switch view mode
				
			*Input Data: 
				
			*Expected Outcome: 
			- The container is move to new place
			- The page is displayed in the view mode with all changes
				*/
		 info("Move a container to new place");
		 portSite.goToEditNavigation(portalName);
		 navMag.editNodePage(nodeName);
		 pagCW.moveContainer(newTitle,pagCW.ELEMENT_CONTAINER_HOLDER_MOVE,pagCW.ELEMENT_PORTLET,87);
		 navMag.closeNavigationManagementPopup();
		 
		 info("Verify that the container is changed the position");
		 portSite.goToEditNavigation(portalName);
		 navMag.editNodePage(nodeName);
		 pagCW.checkPositions(pagCW.ELEMENT_CONTAINER_PRECEDING_PORTLET,pagCW.ELEMENT_CONTAINER_FOLLOWING_PORTLET);
		 navMag.closeNavigationManagementPopup();
		 
		 info("Test 13: Delete container when edit page properties of node");
		/*Step Number: 1
		*Step Name: Step 1: Delete container when edit page properties of node
		*Step Description:
		- Go to Administration/Portal/Sites/Edit navigation
		- Select a node
		- Right click and choose Edit node's page
		- Select Container tab from Page Editor
		- Select container in page and click Delete container
		- Switch view mode
			
		*Input Data: 
			
		*Expected Outcome: 
		- The container is removed successfully
		- The page is displayed in the view mode with all changes
			*/
		info("Delete a container");
		portSite.goToEditNavigation(portalName);
		navMag.editNodePage(nodeName);
		pagCW.deleteContainer(newTitle);
		navMag.closeNavigationManagementPopup();
	}

	/**
	*<li> Case ID:123134.</li>
	*<li> Test Case Name:Copy and Paste node.</li>
	*/
	@Test
	public void test14_CopyAndPasteNode() {
		info("Test 14: Copy and Paste node");
		String portalName = portDeftData.getContentByIndex(1);
		String nodeName1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String nodeName2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Copy and Paste node
		*Step Description: 
			- Go to Administration/Portal/Sites/Edit navigation
			- Select a node
			- Right click on node and choose Copy from the drop-down menu. 
			- Right-click the position you want to paste this node and select Paste Node. 
		*Input Data: 
			
		*Expected Outcome: 
			- Node is copied to new place
			*/
		info("Add a new node 1");
		navToolBar.goToPotalSites();
		portSite.goToEditNavigation(portalName);
		navMag.addNode(nodeName1,"");
		navMag.saveNode();
		
		info("Add a new node 2");
		portSite.goToEditNavigation(portalName);
		navMag.addNode(nodeName2,"");
		navMag.saveNode();
		
		info("Copy and paste a node");
		portSite.goToEditNavigation(portalName);
		navMag.copyNode(nodeName1);
		navMag.pasteNode(nodeName2);
		info("Verify that node 2 has only one children is node1");
		waitForAndGetElement(navMag.ELEMENT_NAVIGATION_PARENT_CHILD_NODE.replace("${parent}",nodeName2).replace("${child}",nodeName1));
		waitForAndGetElement(navMag.ELEMENT_NAVIGATION_NUMBER_CHILD_NODES.replace("${parent}",nodeName2).replace("${numberChild}","1"));
		navMag.closeNavigationManagementPopup();
	}

	/**
	*<li> Case ID:123135.</li>
	*<li> Test Case Name:Cut and Paste node.</li>
	*/
	@Test
	public void test15_CutAndPasteNode() {
		info("Test 15: Cut and Paste node");
		String portalName = portDeftData.getContentByIndex(1);
		String nodeName1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String nodeName2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Cut and Paste node
		*Step Description: 
			- Go to Administration/Portal/Sites/Edit navigation
			- Select a node
			- Right click on node and choose Cut from the drop-down menu. 
			- Right-click the position you want to paste this node and select Paste Node. 
		*Input Data: 
			
		*Expected Outcome: 
			- Node is cut to new place
			*/
		info("Add a new node 1");
		navToolBar.goToPotalSites();
		portSite.goToEditNavigation(portalName);
		navMag.addNode(nodeName1,"");
		navMag.saveNode();
		
		info("Add a new node 2");
		portSite.goToEditNavigation(portalName);
		navMag.addNode(nodeName2,"");
		navMag.saveNode();
		
		info("Cut and paste a node");
		portSite.goToEditNavigation(portalName);
		navMag.cutNode(nodeName1);
		navMag.pasteNode(nodeName2);
		
		info("Verify that node 2 has only one children is node1");
		waitForAndGetElement(navMag.ELEMENT_NAVIGATION_PARENT_CHILD_NODE.replace("${parent}",nodeName2).replace("${child}",nodeName1));
		waitForAndGetElement(navMag.ELEMENT_NAVIGATION_NUMBER_CHILD_NODES.replace("${parent}",nodeName2).replace("${numberChild}","1"));
		navMag.closeNavigationManagementPopup();
		
		info("Verify that node 1 is only one avaiable");
		portSite.goToEditNavigation(portalName);
		waitForElementNotPresent(navMag.ELEMENT_NAVIGATION_PARENT_NODE.replace("${parent}",nodeName1));
		navMag.closeNavigationManagementPopup();
	}

	/**
	*<li> Case ID:123136.</li>
	*<li> Test Case Name:Clone and Paste Node.</li>
	*/
	@Test
	public void test16_CloneAndPasteNode() {
		info("Test 16: Clone and Paste Node");
		String portalName = portDeftData.getContentByIndex(1);
		String nodeName1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String nodeName2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Clone and Paste node
		*Step Description: 
			- Go to Administration/Portal/Sites/Edit navigation
			- Select a node
			- Right click on node and choose Clone Node from the drop-down menu. 
			- Right-click the position that you want to paste this node and select Paste Node
		*Input Data: 
			
		*Expected Outcome: 
			Node is clone successfully and have properties is the same with node is copied
			*/
		info("Add a new node 1");
		navToolBar.goToPotalSites();
		portSite.goToEditNavigation(portalName);
		navMag.addNode(nodeName1,"");
		navMag.saveNode();
		
		info("Add a new node 2");
		portSite.goToEditNavigation(portalName);
		navMag.addNode(nodeName2,"");
		navMag.saveNode();
		
		info("Clone and paste a node");
		portSite.goToEditNavigation(portalName);
		navMag.cloneNode(nodeName1);
		navMag.pasteNode(nodeName2);
		
		info("Verify that node 2 has only one children is node1");
		waitForAndGetElement(navMag.ELEMENT_NAVIGATION_PARENT_CHILD_NODE.replace("${parent}",nodeName2).replace("${child}",nodeName1));
		waitForAndGetElement(navMag.ELEMENT_NAVIGATION_NUMBER_CHILD_NODES.replace("${parent}",nodeName2).replace("${numberChild}","1"));
		navMag.closeNavigationManagementPopup();
		
		info("Verify that node 1 is more one avaiable");
		portSite.goToEditNavigation(portalName);
		waitForAndGetElement(navMag.ELEMENT_NAVIGATION_PARENT_NODE.replace("${parent}",nodeName1));
		navMag.closeNavigationManagementPopup();
	}

	/**
	*<li> Case ID:123136.</li>
	*<li> Test Case Name:Change node order.</li>
	*/
	@Test
	public void test17_ChangeNodeOrder() {
		info("Test 17: Change node order");
		String portalName = portDeftData.getContentByIndex(1);
		String nodeName1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String nodeName2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Change node order
		*Step Description: 
			- Go to Administration/Portal/Sites/Edit navigation
			- Select a node
			- Select Move Up or Move Down from the drop-down menu
			- Click Save to accept your changes. 
		*Input Data: 
			
		*Expected Outcome: 
			- Position of node is changed successfully
			*/
		info("Add a new node 1");
		navToolBar.goToPotalSites();
		portSite.goToEditNavigation(portalName);
		navMag.addNode(nodeName1,"");
		navMag.saveNode();
		
		info("Add a new node 2");
		portSite.goToEditNavigation(portalName);
		navMag.addNode(nodeName2,"");
		navMag.saveNode();
		
		info("move up node2 to node 1");
		portSite.goToEditNavigation(portalName);
		navMag.moveUpNode(nodeName2);
		
		info("Verify that node 2 is moved up node1");
		waitForElementNotPresent(navMag.ELEMENT_NAVIGATION_PREVIOUS_NODE.replace("${currentNode}",nodeName2).replace("${previousNode}",nodeName1));
		waitForAndGetElement(navMag.ELEMENT_NAVIGATION_NEXT_NODE.replace("${currentNode}",nodeName2).replace("${nextNode}",nodeName1));
		
		info("move down node 2 to node 1");
		navMag.moveDownNode(nodeName2);
		info("Verify that node 2 is moved up node1");
		waitForAndGetElement(navMag.ELEMENT_NAVIGATION_PREVIOUS_NODE.replace("${currentNode}",nodeName2).replace("${previousNode}",nodeName1));
		waitForElementNotPresent(navMag.ELEMENT_NAVIGATION_NEXT_NODE.replace("${currentNode}",nodeName2).replace("${nextNode}",nodeName1));
		navMag.closeNavigationManagementPopup();
		
	}
}
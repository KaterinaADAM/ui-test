package org.exoplatform.selenium.platform.ecms.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.platform.ManageLogInOut;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.ecms.CreateNewDocument.selectDocumentType;
import org.exoplatform.selenium.platform.ecms.SiteExplorerHome;
import org.openqa.selenium.By;
import org.testng.annotations.*;


/**
 * @author eXo
 *
 */
public class Ecms_SE_BasicAction extends ECMS_TestConfig_Part2{
	/**
	 *<li> Case ID:116565.</li>
	 *<li> Test Case Name: Add symlink for a node.</li>
	 */
	@Test
	public  void test01_AddSymlinkForANode() {
		info("Test 1: Add symlink for a node");
		/*Step Number: 1
		 *Step Name: -
		 *Step Description: 
			Step 1: Add symlink for a node
		 *Input Data: 
			- Select a node (folder)
			- Right click and select Add Symlink icon 
			- Select a node to add symlink
			- Click Save
		 *Expected Outcome: 
			Symlink for a node is added successfully. Show symlink node as a child node*/

		/*Step number: 2
		 *Step Name: 
		 *Step Description: 
			Step 2 : Move Symlink
		 *Input Data: 
			- Go back to destination symlink node
		 *Expected Outcome: 
			- Destination symlink node is opened*/

		/*Step number: 3
		 *Step Name: 
		 *Step Description: 
			Step 3 : Add file in the node
		 *Input Data: 
			- Add a web content file
		 *Expected Outcome: 
			The webcontent file created must appears in the Symlink*/ 
		
		String random= getRandomNumber();
		String folderTitle = txData.getContentByArrayTypeRandom(1)+random;
		String node =folderTitle .toLowerCase();
		
		navTool.goToSiteExplorer();
		SEHome.goToAddNewFolder();
		SEHome.createFolder(folderTitle, "Content Folder");
		SEHome.addSymlink(folderTitle);
		waitForAndGetElement(By.xpath((SEHome.ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME).replace("${title}", node+".lnk")));
		
		click(SEHome.ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}", folderTitle));
		SEHome.goToAddNewContent();
		CreNewDoc.createNewDoc(selectDocumentType.WEBCONTENT);
		CreNewDoc.addNewWebContent(node,node);
		CreNewDoc.saveAndClose();
		
		click(SEHome.ELEMENT_SIDEBAR_SITES_MANAGEMENT);
		click(SEHome.ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}", node+".lnk"));
		waitForAndGetElement(By.xpath((SEHome.ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME).replace("${title}", node)));
		SEHome.deleteData(node+".lnk");
		SEHome.deleteData(folderTitle);
	}

	/**
	 *<li> Case ID:116567.</li>
	 *<li> Test Case Name: Copy/paste a node.</li>
	 *<li> Case ID:116574.</li>
	 *<li> Test Case Name: Cut/paste a node.</li>
	 *<li> Case ID:116576.</li>
	 *<li> Test Case Name: Drag and drop a node.</li>
	 */
	@Test
	public  void test02_03_04_CopypasteANode_CutpasteANode_DragAndDropANode() {

		String destination="intranet";
		String secondDestination="acme";
		
		String titleCommonNode= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		navTool.goToSiteExplorer();
		click(SEHome.ELEMENT_SIDEBAR_SITES_MANAGEMENT);
		SEHome.goToAddNewContent();
		CreNewDoc.createNewDoc(selectDocumentType.WEBCONTENT);
		CreNewDoc.addNewWebContent(titleCommonNode, titleCommonNode);
		CreNewDoc.saveAndClose();
		/*Step Number: 1
		 *Step Name: -
		 *Step Description: 
			Step 1: Copy/paste a node
		 *Input Data: 
			- Right click on a node and select Copy
			- Right click on destination node and select Paste
		 *Expected Outcome: 
			Node is pasted into destination node*/ 
		info("Test 4: Drag and drop a node");
		dragAndDropToObject(SEHome.ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}", titleCommonNode),SEHome.ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}", destination));
		alert.acceptAlert();

		click(SEHome.ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}", destination));

		info("Test 3: Cut/paste a node");
		SEHome.cutPasteNode(titleCommonNode, secondDestination);

		click(SEHome.ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}", secondDestination));

		info("Test 2: Copy/paste a node");
		SEHome.copyPasteNode(titleCommonNode, destination);

		info("delete data");
		click(SEHome.ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}", destination));
		SEHome.deleteData(titleCommonNode);
		click(SEHome.ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}", secondDestination));
		SEHome.deleteData(titleCommonNode);
	}

	/**
	 *<li> Case ID:116575.</li>
	 *<li> Test Case Name: Download and allow edition.</li>
	 */
	@Test(groups="pending")
	public  void test05_DownloadAndAllowEdition() {
		info("Test 5: Download and allow edition");
		/*Step Number: 1
		 *Step Name: -Step 1: Download and Allow Edition
		 *Step Description: 
			- Go to a drive
			- Right click on a node and choose Open in MS Office
		 *Input Data: 

		 *Expected Outcome: 
			The structure or the content of node appears*/ 
//		rightClickOnElement(By.xpath((SEHome.ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME).replace("${title}",titleCommonNode )));
//		click(SEHome.ELEMENT_SITEEXPLORER_ACTION_OPEN_IN_MS_OFFICE);
//		waitForAndGetElement(SEHome.ELEMENT_CHECK_OPEN_WEBCONTENT_IN_MSOFFICE);
//		driver.close();
	}

	/**
	 *<li> Case ID:116580.</li>
	 *<li> Test Case Name: Lock a node.</li>
	 *<li> Case ID:116658.</li>
	 *<li> Test Case Name: Unlock a node.</li>
	 */
	@Test
	public  void test06_07_LockANode_UnlockANode() {
		info("Test 6: Lock a node");
		/*Step Number: 1
		 *Step Name: Step 1: Lock a node
		 *Step Description: 
			- Right click on a node and select Lock
		 *Input Data: 

		 *Expected Outcome: 
			- Node is locked and the node has Lock icon on it. Other users can view and copy content of the locked node but cannot remove or make changes.*/ 

		String titleCommonNode= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		navTool.goToSiteExplorer();
		click(SEHome.ELEMENT_SIDEBAR_SITES_MANAGEMENT);
		SEHome.goToAddNewContent();
		CreNewDoc.createNewDoc(selectDocumentType.WEBCONTENT);
		CreNewDoc.addNewWebContent(titleCommonNode,titleCommonNode);
		CreNewDoc.saveAndClose();
		SEHome.lockNode(titleCommonNode);

		initNewDriver();
		newDriver.get(baseUrl);
		ManageLogInOut  acc = new ManageLogInOut(newDriver);
		NavigationToolbar navTool2 = new NavigationToolbar(newDriver);
		SiteExplorerHome SEHome2 = new SiteExplorerHome(newDriver);
		acc.signIn(DATA_USER2, DATA_PASS);
		navTool2.goToSiteExplorer();
		acc.rightClickOnElement(SEHome2.ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}",titleCommonNode ));
		acc.waitForElementNotPresent(SEHome2.ELEMENT_SITEEXPLORER_LIST_LOCK_NODE);
		acc.signOut();

		info("Test 10 Unlock a node");

		SEHome.unlockNode(titleCommonNode);

		acc.signIn(DATA_USER2, DATA_PASS);
		navTool2.goToSiteExplorer();
		acc.rightClickOnElement(SEHome2.ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}",titleCommonNode ));
		acc.waitForElementNotPresent(SEHome2.ELEMENT_SITEEXPLORER_LIST_UNLOCK_NODE);
		acc.signOut();
		newDriver.quit();
		
		SEHome.deleteData(titleCommonNode);
	}

	/**
	 *<li> Case ID:116582.</li>
	 *<li> Test Case Name: Manage Actions on WebDAV.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 *<li> Done with OSs and browsers : </li>
	 *<li> Generated by rosso at 2015/01/20 14:18:37</li>
	 */
	@Test(groups="pending")
	public  void test08_ManageActionsOnWebDAV() {
		info("Test 8: Manage Actions on WebDAV");
		/*Step Number: 1
		 *Step Name: -
		 *Step Description: 
			Step 1: Manage Actions on WebDAV
		 *Input Data: 
			- Create a new Network Place with network address is the path of a folder (/rest/private/jcr/repository/collaboration/sites/intranet)
		 *Expected Outcome: 
			- You can add new document at WebDAV. This mean : a new corresponding document is created at exo folder
			- Can copy/Delete document on WebDAV*/ 

	}

	/**
	 *<li> Case ID:116585.</li>
	 *<li> Test Case Name: Delete Clipboard.</li>
	 *<li> Case ID:116659.</li>
	 *<li> Test Case Name: Paste Clipboard.</li>
	 */
	@Test
	public  void test09_010_DeleteClipboard_PasteClipboard() {
		/*Step Number: 1
		 *Step Name: -
		 *Step Description: 
			Step 1: Delete Clipboard
		 *Input Data: 
			- Perform cut/copy a node
			- Go to other node
			- Go to Clipboard : Click Clipboard icon on side bar
			- Click corresponding Delete icon of a action
		 *Expected Outcome: 
			Clipboard pane is shown . When delete a action (copy/cut) the action will be not shown any more at Clipboard*/ 
		
		String titleCommonNode= (txData.getContentByArrayTypeRandom(1)+getRandomNumber()).toLowerCase();
		
		navTool.goToSiteExplorer();
		click(SEHome.ELEMENT_SIDEBAR_SITES_MANAGEMENT);
		SEHome.goToAddNewContent();
		CreNewDoc.createNewDoc(selectDocumentType.WEBCONTENT);
		CreNewDoc.addNewWebContent(titleCommonNode,titleCommonNode);
		CreNewDoc.saveAndClose();
		
		// clean the clipboard
		rightClickOnElement(SEHome.ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}", titleCommonNode));
		click(SEHome.ELEMENT_SITEEXPLORER_ACTION_COPY);
		click(SEHome.ELEMENT_SITEEXPLORER_CLIPBOARD);
		click(SEHome.ELEMENT_CLIPBOARD_CLEAR_ALL);
		click(SEHome.ELEMENT_SIDE_BAR_FILE_EXPLORER_ICON);
		click(SEHome.ELEMENT_SIDEBAR_SITES_MANAGEMENT);
		
		info("Test 10 Paste Clipboard");
		
		rightClickOnElement(SEHome.ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}", titleCommonNode));
		click(SEHome.ELEMENT_SITEEXPLORER_ACTION_COPY);
		click(SEHome.ELEMENT_SITEEXPLORER_CLIPBOARD);
		click(SEHome.ELEMENT_CLIPBOARD_PASTE_NODE.replace("{$node}",titleCommonNode));
		click(SEHome.ELEMENT_SIDE_BAR_FILE_EXPLORER_ICON);
	
		// delete the past node
		rightClickOnElement(SEHome.ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}", titleCommonNode));
		click(SEHome.ELEMENT_SITEEXPLORER_ACTION_DELETE);
		click(SEHome.ELEMENT_SITEEXPLORER_CONFIRMBOX_DELETE);
		waitForAndGetElement(SEHome.ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}", titleCommonNode));
		
		info("Test 9: Delete Clipboard");
		rightClickOnElement(SEHome.ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}", titleCommonNode));
		click(SEHome.ELEMENT_SITEEXPLORER_ACTION_COPY);
		click(SEHome.ELEMENT_SITEEXPLORER_CLIPBOARD);
		waitForAndGetElement(SEHome.ELEMENT_CLIPBOARD_DELETE_NODE.replace("{$node}",titleCommonNode));
		click(SEHome.ELEMENT_CLIPBOARD_DELETE_NODE.replace("{$node}",titleCommonNode),0,true);
		
		waitForElementNotPresent(SEHome.ELEMENT_CLIPBOARD_DELETE_NODE.replace("{$node}",titleCommonNode));
		SEHome.deleteData(titleCommonNode);
		
	}

	/**
	 *<li> Case ID:116602.</li>
	 *<li> Test Case Name: Rename a node.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 *<li> Done with OSs and browsers : </li>
	 *<li> Generated by rosso at 2015/01/20 14:18:37</li>
	 */
	@Test
	public  void test11_RenameANode() {
		info("Test 11: Rename a node");
		
		/*Step Number: 1
		 *Step Name: -
		 *Step Description: 
			Step 1: Rename Title for a node
		 *Input Data: 
			- Right click on a node and select Rename
			- Put new name for node
			- Click Save
		 *Expected Outcome: 
			Node is renamed*/
		String titleCommonNode= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String newName= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		navTool.goToSiteExplorer();
		click(SEHome.ELEMENT_SIDEBAR_SITES_MANAGEMENT);
		SEHome.goToAddNewContent();
		CreNewDoc.createNewDoc(selectDocumentType.WEBCONTENT);
		CreNewDoc.addNewWebContent(titleCommonNode,titleCommonNode);
		CreNewDoc.saveAndClose();
		
		SEHome.renameNode(titleCommonNode, newName);
		waitForElementNotPresent(SEHome.ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}", titleCommonNode));
		waitForAndGetElement(SEHome.ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}", newName));
		SEHome.deleteData(newName);
	}

}
package org.exoplatform.selenium.platform.ecms.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.ecms.CreateNewDocument.folderType;
import org.exoplatform.selenium.platform.ecms.CreateNewDocument.selectDocumentType;
import org.testng.annotations.*;


public class Ecms_SE_Admin extends ECMS_TestConfig_Part2{
	/**
	 * Add manage publishtation to action bar of Web view type
	 */
	public void addActions(){
		info("Go to Explorer tab");
		navTool.goToContentAdministration();
		caPage.addAllActions();
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
	}
	/**
	 * By QuynhPT
	 *<li> Case ID:116656</li>
	 *<li> Test Case Name: Add Relation</li>
	 */

	@Test
	public  void test01_AddRelation() {
		info("Test 1: Add Relation");
		/*
		 * Precondition: If Relations is not available on action bar, 
		 * go to Content Administration/ Manage View and edit your current view in use with Relation option ticked
		 * Step Number: 1
		 *Step Description: 
			Step 1: Add Relation
		 *Input Data: 
			- Add Relation
            - Select a node
            - Click on Relations icon in the action bar
            - Go to Select Relation tab
            - Select document to add relation for document/uploaded file
		 *Expected Outcome: 
			- A relation is added for a node.
			*/ 
		
		info("Create data test");
		String node1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String node2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		info("Finished data test");
		
		//Declare a string array
		String[] nameContent={node1};
		
		info("Create content 1");
		navTool.goToSiteExplorer();
		SEHome.goToPath("acme/documents", "Sites Management");
		SEHome.goToAddNewContent();
		CreNewDoc.createNewDoc(selectDocumentType.FILE);
		CreNewDoc.addNewFile(node1, node1);
		CreNewDoc.saveAndClose();
		Utils.pause(5000);
		
		SEHome.selectNode("documents");
		
		info("Create content 2");
		SEHome.goToAddNewContent();
		CreNewDoc.createNewDoc(selectDocumentType.FILE);
		CreNewDoc.addNewFile(node2, node2);
		CreNewDoc.saveAndClose();
		Utils.pause(5000);
		
		
		SEHome.selectNode(node2);		
		info("Click on More link ");
		click(SEHome.ELEMENT_ACTIONBAR_MORE);
		info("Add relation");
		SEHome.goToManageRelation();
		SEHome.addRelation(nameContent,"sites/acme/documents");
		SEHome.closeAddRelationPopup();
		Utils.pause(5000);
		
		info("Select relation tab of SE");
		SEHome.goToRelationSideBar();
		waitForAndGetElement(SEHome.ELEMENT_SIDE_BAR_RELATION_TAB_FILE_TITLE.replace("${nameContent}",node1));
		click(SEHome.ELEMENT_SIDE_BAR_RELATION_TAB_FILE_TITLE.replace("${nameContent}",node1));
		Utils.pause(2000);
		
		info("Verify the file in reference section");
		waitForAndGetElement(SEHome.ELEMENT_SIDE_BAR_RELATION_TAB_FILE_TITLE.replace("${nameContent}",node2));
		
		info("Delete all data test");
		navTool.goToSiteExplorer();
		SEHome.goToPath("acme/documents", "Sites Management");
		SEHome.deleteData(node1);
		SEHome.deleteData(node2);
	}

	/**
	 * By QuynhPT
	 * <li>Case ID:116657.</li> 
	 * <li>Test Case Name: Delete Relation</li>
	 */

	@Test
	public  void test02_DeleteRelation() {
		info("Test 02: Delete Relation");
		
		 /* Precondition: If Relations is not available on action bar, 
		 * go to Content Administration/ Manage View and edit your current view in use with Relation option ticked
		 * Step Number: 1
		 *Step Description: 
			Step 1: Delete Relation
		 *Input Data: 
            - Select a node
            - Click on Relations icon in the action bar
            - Go to Select Relation tab
            - Select document to add relation for document/uploaded file 
            - Go to Manage relation list tab
            - Click on corresponding Delete icon of Relation 
		 *Expected Outcome: 
			- Relation is deleted for a node.*/
			 

		info("Create data test");
		String node1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String node2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String[] nameContent={node1};
		info("Finished data test");
		
		
		info("Create content 1");
		navTool.goToSiteExplorer();
		SEHome.goToPath("acme/documents", "Sites Management");
		SEHome.goToAddNewContent();
		CreNewDoc.createNewDoc(selectDocumentType.FILE);
		CreNewDoc.addNewFile(node1, node1);
		CreNewDoc.saveAndClose();
		Utils.pause(5000);
		
        SEHome.selectNode("documents");
		
		info("Create content 2");
		SEHome.goToAddNewContent();
		CreNewDoc.createNewDoc(selectDocumentType.FILE);
		CreNewDoc.addNewFile(node2, node2);
		CreNewDoc.saveAndClose();
		Utils.pause(5000);
		
		
		SEHome.selectNode(node2);
		info("click on More link");
		click(SEHome.ELEMENT_ACTIONBAR_MORE);
		info("Select Relation");
		SEHome.goToManageRelation();
		SEHome.addRelation(nameContent,"sites/acme/documents");
		SEHome.deleteRelation(node1.toLowerCase());
		SEHome.closeAddRelationPopup();
		Utils.pause(2000);
		
		
		//Check Relation on left of sideBar
		SEHome.goToRelationSideBar();
		waitForElementNotPresent(SEHome.ELEMENT_SIDE_BAR_RELATION_TAB_FILE_TITLE.replace("${nameContent}",node1));
		
		info("Delete all data test");
		navTool.goToSiteExplorer();
		SEHome.goToPath("acme/documents", "Sites Management");
		SEHome.deleteData(node1);
		SEHome.deleteData(node2);
		
	 }
	/**
	 *<li> Case ID:116596.</li>
	 *<li> Test Case Name: Show/ Hide Relation</li>
	 */
	@Test
	public  void test03_ShowHideRelation() {
		info("Test 3: Show/Hide Relation");
		info("Create data test");
		String node1 = txData.getContentByArrayTypeRandom(1)+ getRandomNumber();
		String node2 = txData.getContentByArrayTypeRandom(1)+ getRandomNumber();
		String[] nameContent = { node1 };
		info("Finished data test");

		info("Create content 1");
		navTool.goToSiteExplorer();
		SEHome.goToPath("acme/documents", "Sites Management");
		SEHome.goToAddNewContent();
		CreNewDoc.createNewDoc(selectDocumentType.FILE);
		CreNewDoc.addNewFile(node1, node1);
		CreNewDoc.saveAndClose();
		Utils.pause(5000);
		
        SEHome.selectNode("documents");
		
		info("Create content 2");
		SEHome.goToAddNewContent();
		CreNewDoc.createNewDoc(selectDocumentType.FILE);
		CreNewDoc.addNewFile(node2, node2);
		CreNewDoc.saveAndClose();
		Utils.pause(5000);
		
		
		SEHome.selectNode(node2);
		info("click on More link");
		click(SEHome.ELEMENT_ACTIONBAR_MORE);
		info("Select relation");
		SEHome.goToManageRelation();
		SEHome.addRelation(nameContent,"sites/acme/documents");
		SEHome.closeAddRelationPopup();
		Utils.pause(5000);
		
		//Show relation
		SEHome.goToRelationSideBar();
		waitForAndGetElement(SEHome.ELEMENT_SIDE_BAR_RELATION_TAB_FILE_TITLE.replace("${nameContent}",node1));
		
		//Hide relation
		click(SEHome.ELEMENT_SIDE_BAR_FILE_EXPLORER_ICON);
		waitForAndGetElement(SEHome.ELEMENT_SIDEBAR_SITES_MANAGEMENT);
		
		info("Delete all data test");
		navTool.goToSiteExplorer();
		SEHome.goToPath("intranet/documents", "Sites Management");
		SEHome.deleteData(node1);
		SEHome.deleteData(node2);
	}

	/**
	 *<li> Case ID:116578.</li>
	 *<li> Test Case Name: Export a node.</li>
	 *<li> Case ID:116655.</li>
	 *<li> Test Case Name: Import a node</li>
	 */
	@Test
	public  void test04_05_ExportANode() {
		info("Test 4: Export a Node");

		/*Step Number: 1
		 *Step Name: Export a node
		 *Step description:
			- Select one node
			- On action bar, click Export button
			- Choose System View in Format
			- Click on Export button
			- Click Save in the bottom message to export.
		 *Expected Outcome: 
			- Node is exported successfully.
		*/ 

		info("Create data test");
		String node1 = txData.getContentByArrayTypeRandom(1)+ getRandomNumber();
		info("Finished data test");
		
		info("Add New folder");
		navTool.goToSiteExplorer();
		SEHome.goToPath("intranet/documents","Sites Management");
		SEHome.goToAddNewFolder();
		
		info("Create Folder node");
		CreNewDoc.createNewFolder(node1, folderType.Content);
		info("Select folder");
		SEHome.selectNode(node1);
						
		info("Export a node");
		click(SEHome.ELEMENT_ACTIONBAR_MORE);
		SEHome.goToExportNode();
		SEHome.exportNode(true, false);
		
		info("Delete all data test");
		navTool.goToSiteExplorer();
		SEHome.goToPath("intranet/documents", "Sites Management");
		SEHome.deleteData(node1);
		
		info("Test 5: Import a Node");
		info("Create data test");
		String node2 = txData.getContentByArrayTypeRandom(1)+ getRandomNumber();
		String filePath = "sysview.xml";
		info("Finished data test");
		
		info("Add New folder");
		navTool.goToSiteExplorer();
		SEHome.goToPath("intranet/documents", "Sites Management");
		SEHome.goToAddNewFolder();
		
		info("Create Folder node");
		CreNewDoc.createNewFolder(node2, folderType.Content);
		info("Select folder");
		SEHome.selectNode(node2);
		
		info("Import a node");
		click(SEHome.ELEMENT_ACTIONBAR_MORE);
		SEHome.goToImportNode();
		SEHome.importNode("TestData/"+filePath,"Create New",false, "");
		
		info("Delete all data test");
		navTool.goToSiteExplorer();
		SEHome.goToPath("intranet/documents", "Sites Management");
		SEHome.deleteData(node2);
		deleteFile("sysview.xml");
	}

	/**
	 *<li> Case ID:116584.</li>
	 *<li> Test Case Name: Add Category.</li>

	 */
	@Test
	public  void test06_Add_Delete_Category() {
		info("Test 6: Add a category");

		/*Step Number: 1
		 *Step Name:Add Category
		 *Step Description: 
			- Add category for node
			- Select a document/Uploaded file
			- Click on Categories icon in the action bar
			- Select category to add for document/Uploaded file
		 *Expected Outcome: 
			- Category added for Document/uploaded file
		*/
		info("Create data test");
		String node1 = txData.getContentByArrayTypeRandom(1)+ getRandomNumber();
		String[] arrayCatePath={"Defense"};
		String categoryTree = "powers";
		String nameSelectedCategory = "Healing";
		info("Finished data test");
		
		info("Create a content");
		navTool.goToSiteExplorer();
		SEHome.goToPath("acme/documents", "Sites Management");
		SEHome.goToAddNewContent();
		CreNewDoc.createNewDoc(selectDocumentType.FILE);
		CreNewDoc.addNewFile(node1, node1);
		CreNewDoc.saveAndClose();
		Utils.pause(5000);
			
		info("Add Category");
		click(SEHome.ELEMENT_ACTIONBAR_MORE);
		SEHome.goToAddCategory();
		SEHome.addCategory(categoryTree,arrayCatePath, nameSelectedCategory);
		waitForAndGetElement(SEHome.ELEMENT_ADD_CATEGORY_POPUP_DELETE_CATEGORY.replace("${nameCategory}",nameSelectedCategory));
		SEHome.closeAddCategoryPopup();
		
		info("Delete all data test");
		navTool.goToSiteExplorer();
		SEHome.goToPath("acme/documents", "Sites Management");
		SEHome.deleteData(node1);
	}
	
	/**<li> Case ID:116654.</li>
	 *<li> Test Case Name: Delete a category.</li>
	 *<li> Precondition: If Category is not available on action bar, 
	 *go to Content Administration/ Manage View and edit your current view 
	 *in use with Category option ticked</li>
	 */

    @Test
	public void test07_Delete_Category() {
		info("Test 7: Delete a category");
		/*Step Number: 1
		 *Step Name:Delete a category
		 *Step Description: 
			- Select a document/Uploaded file
			- Click on Categories icon in the action bar
			- Select category to add for document/Uploaded file
			- Delete category
			- Click on corresponding Delete icon of category
		 *Expected Outcome: 
			- Document/uploaded file doesn't have any category.
		*/
		info("Create data test");
		String node1 = txData.getContentByArrayTypeRandom(1)+ getRandomNumber();
		String[] arrayCatePath={"Defense"};
		String categoryTree = "powers";
		String nameSelectedCategory = "Healing";
		info("Finished data test");
		
		info("Create a content");
		navTool.goToSiteExplorer();
		SEHome.goToPath("acme/documents", "Sites Management");
		SEHome.goToAddNewContent();
		CreNewDoc.createNewDoc(selectDocumentType.FILE);
		CreNewDoc.addNewFile(node1, node1);
		CreNewDoc.saveAndClose();
		Utils.pause(5000);
		
		info("Add Category");
		click(SEHome.ELEMENT_ACTIONBAR_MORE);
		SEHome.goToAddCategory();
		SEHome.addCategory(categoryTree,arrayCatePath, nameSelectedCategory);
		info("Delete Category");
		SEHome.deleteCategory(nameSelectedCategory);
		SEHome.closeAddCategoryPopup();
		
		info("Delete all data test");
		navTool.goToSiteExplorer();
		SEHome.goToPath("acme/documents", "Sites Management");
		SEHome.deleteData(node1);
		
	}
	/**
	 *<li> Case ID:116610.</li>
	 *<li> Test Case Name: View Node Properties</li>
	 */
	@Test
	public  void test08_ViewNodeProperties() {
		info("Test 08: View Node Properties");
		
		/*Step Number: 1
		 *Step Name: -
		 *Step Description: 
		   - View node properties
		   - Create a node
		   - Select this node
		   - Click on Properties on action bar
		   - Add properties
		   - Go to Add new properties Tab
		   - Perform to add new a properties for this node
		 *Expected Outcome: 
		   - Manage properties form is shown, and all properties of this node is shown here.
		   - New properties is added
		   */
		info("Create data test");
		String node1 = txData.getContentByArrayTypeRandom(1)+ getRandomNumber();
		String property = "exo:summary";
		info("Finished data test");
		
		info("Create a content");
		navTool.goToSiteExplorer();
		SEHome.goToPath("acme/documents", "Sites Management");
		SEHome.goToAddNewContent();
		CreNewDoc.createNewDoc(selectDocumentType.FILE);
		CreNewDoc.addNewFile(node1, node1);
		CreNewDoc.saveAndClose();
		Utils.pause(5000);
		
		info("View Node Properties");
		SEHome.selectNode(node1);
		click(SEHome.ELEMENT_ACTIONBAR_MORE);
		SEHome.goToProperties();
		SEHome.addProperty(property, property);
		
		info("Delete all data test");
		navTool.goToSiteExplorer();
		SEHome.goToPath("acme/documents", "Sites Management");
		SEHome.deleteData(node1);
		
	}

	/**
	 *<li> Case ID:116593.</li>
	 *<li> Test Case Name: Manage Publication.</li>
	 */
	@Test
	public  void test09_ManagePublication() {
		info("Test 09: Manage Publication");
		/*Step Number: 1
		 *Step Name: -
		 *Step Description: 
			- S	elect a document/Uploaded file
			- Click on Publications icon in the action bar
			- Click on Stage
			- Setting time in From and To field
			- Click Save
		 *Expected Outcome: 
			- Document/uploaded file is published during the time in From and To field
		*/
		info("Create data test");
		String node1 = txData.getContentByArrayTypeRandom(1)+ getRandomNumber();
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
		Date date = new Date();
		info("Finished data test");
		
		info("Create a content");
		navTool.goToSiteExplorer();
		SEHome.goToPath("acme/documents", "Sites Management");
		SEHome.goToAddNewContent();
		CreNewDoc.createNewDoc(selectDocumentType.FILE);
		CreNewDoc.addNewFile(node1, node1);
		CreNewDoc.saveAndClose();
		Utils.pause(5000);
		
		info("Manage Publication");
		click(SEHome.ELEMENT_ACTIONBAR_MORE);
		SEHome.goToManagePublishtation();
		SEHome.managePublication("Staged", dateFormat.format(date.getTime()),dateFormat.format(date.getTime()));
		
		info("Delete all data test");
		navTool.goToSiteExplorer();
		SEHome.goToPath("acme/documents", "Sites Management");
		SEHome.deleteData(node1);
		
	}
}
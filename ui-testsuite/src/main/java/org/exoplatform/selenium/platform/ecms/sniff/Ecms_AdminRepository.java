package org.exoplatform.selenium.platform.ecms.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.platform.administration.ContentAdministration.mainEcmFunctions;
import org.exoplatform.selenium.platform.administration.ContentAdministration.specificEcmFunctions;
import org.exoplatform.selenium.platform.ecms.CreateNewDocument.selectDocumentType;
import org.testng.annotations.*;


/**
 * @author eXo
 *
 */
public class Ecms_AdminRepository extends ECMS_TestConfig_Part1{

	/**
	 *<li> Case ID:116588.</li>
	 *<li> Test Case Name: Unlock a Node.</li>
	 */
	@Test
	public  void test01_UnlockANode() {
		info("Test 1: Unlock a Node");
		/*Step Number: 1
		 *Step Name: -
		 *Step Description: 
			Step 1: Unlock a node
		 *Input Data: 
			- Go to Site explorer : perform to lock a node
			- Go to Administration/Repository/ Locks 
			- Go to Locked node tab
			- Click corresponding Unlock icon of locked node
		 *Expected Outcome: 
			- Node is unlocked*/ 
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();

		navTool.goToSiteExplorer();
		SEHome.goToAddNewContent();
		info("Create new file document");
		CreNewDoc.createNewDoc(selectDocumentType.WEBCONTENT);
		CreNewDoc.addNewWebContent(title, content);
		CreNewDoc.saveAndClose();
		SEHome.lockNode(title);
		navTool.goToContentAdministration();
		caPage.goToSpecificMainFunctions(mainEcmFunctions.REPOSITORY);
		caPage.goToSpecificFunctions(specificEcmFunctions.LOCKS);
		click(caPage.ELEMENT_ECM_REPOSITORY_UNLOCK_NODE_LIST.replace("{$name}",title));
		navTool.goToSiteExplorer();
		SEHome.deleteData(title);
	}

	/**
	 *<li> Case ID:116590.</li>
	 *<li> Test Case Name: Add Node types.</li>
	 *<li> Case ID:116628.</li>
	 *<li> Test Case Name: View Node types.</li>
	 */
	@Test
	public void test02_03_AddNodeTypes_ViewNode() {
		info("Test 2: Add Node and show it");

		String name = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String superTypes ="exo:calendar";
		driver.get(baseUrl);
		navTool.goToContentAdministration();
		caPage.goToSpecificMainFunctions(mainEcmFunctions.REPOSITORY);
		caPage.goToSpecificFunctions(specificEcmFunctions.NODESTYPES);
		caPage.addNodeType(name, superTypes);
		caPage.searchNodeAndCheckIt(name, superTypes+", nt:base");

		/*Step Number: 1
		 *Step Name: -
		 *Step Description: 
			Step 1: Add Node types
		 *Input Data: 
			Add new node type
			- Go to Content Administration/Repository/Node Types
			- Click on Add button
			- Input Node Type Name
			- Select super type
			- Click Save button
		 *Expected Outcome: 
			New node type is created*/ 

	}

	/**
	 *<li> Case ID:116601.</li>
	 *<li> Test Case Name: Namespace registry.</li>
	 */
	@Test
	public  void test04_NamespaceRegistry() {
		info("Test 3: Namespace registry");
		String prefix = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String url = "www.exo-fqa-test"+getRandomNumber()+".com";
		/*Step Number: 1
		 *Step Name: -
		 *Step Description: 
			Step 1: Namespace registry
		 *Input Data: 
			- Go to Content Administration/Repository/Namespace
			- Click on Register button 
			- Put value in required fields
			- Click Save button
		 *Expected Outcome: 
			New namespace is registered successfully.*/ 
		driver.get(baseUrl);
		navTool.goToContentAdministration();
		caPage.goToSpecificMainFunctions(mainEcmFunctions.REPOSITORY);
		caPage.goToSpecificFunctions(specificEcmFunctions.NAMESPACES);
		caPage.registerNamespace(prefix, url);
		waitForAndGetElement(caPage.ELEMENT_ECM_REPOSITORY_NAMESPACES_CHECK_LIST_URL_AND_PREFIX.replace("{$url}",url).replace("{$prefix}",prefix));
	}


	/**
	 *<li> Case ID:116629.</li>
	 *<li> Test Case Name: Manage lock.</li>
	 */
	@Test
	public  void test05_ManageLock() {
		info("Test 5: Manage lock");
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String group = "*:/developers";
		/*Step Number: 1
		 *Step Name: -
		 *Step Description: 
			Step 1: Manage lock
		 *Input Data: 
			- Go to Administration/Repository/ Locks 
			- Go to Manage lock tab
			- Perform to add some group/ users which have permission to unlock a locked node
			- Can Delete permission of group/ user by click Delete icon
		 *Expected Outcome: 
			- Group is added permission. All users In the group will be able unlock a locked node
			- Group is removed permission*/ 
		driver.get(baseUrl);
		navTool.goToSiteExplorer();
		SEHome.goToAddNewContent();
		info("Create new file document");
		CreNewDoc.createNewDoc(selectDocumentType.WEBCONTENT);
		CreNewDoc.addNewWebContent(title, content);
		CreNewDoc.saveAndClose();
		SEHome.lockNode(title);
		navTool.goToContentAdministration();
		caPage.goToSpecificMainFunctions(mainEcmFunctions.REPOSITORY);
		caPage.goToSpecificFunctions(specificEcmFunctions.LOCKS);
		click(caPage.ELEMENT_ECM_REPOSITORY_MANAGE_LOCK);
		click(caPage.ELEMENT_ECM_REPOSITORY_LOCKS_DEVELOPMENT_GROUP);
		click(caPage.ELEMENT_ECM_REPOSITORY_LOCKS_ALL_GROUP);
		waitForAndGetElement(caPage.ELEMENT_ECM_REPOSITORY_CHECK_LOCK_PERMISSION.replace("{$group}",group));
		click(caPage.ELEMENT_ECM_REPOSITORY_DELETE_LOCK_PERMISSION.replace("{$group}",group));
		waitForElementNotPresent(caPage.ELEMENT_ECM_REPOSITORY_CHECK_LOCK_PERMISSION.replace("{$group}",group));
		navTool.goToSiteExplorer();
		SEHome.deleteData(title);	
	}
}
package org.exoplatform.selenium.platform.ecms.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.ecms.CreateNewDocument.selectDocumentType;
import org.openqa.selenium.By;
import org.testng.annotations.*;

/**
 * @date 15-January-2015
 * @author exo
 */

	public class Ecms_SE_Collaboration extends ECMS_TestConfig_Part2 {
		
		
	/**
	*<li> Case ID:116564.</li>
	*<li> Test Case Name: Add translation for document/uploaded file.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_AddTranslationForDocumentuploadedFile() {
		info("Test 1: Add translation for document/uploaded file");
		String number1= getRandomNumber();
		String number2 = getRandomNumber();
		String title = txData.getContentByArrayTypeRandom(1)+number1+"_EN";
		String content = txData.getContentByArrayTypeRandom(1)+number1+"_EN";
		String title2 = txData.getContentByArrayTypeRandom(1)+number2+"_FR";
		String content2 = txData.getContentByArrayTypeRandom(1)+number2+"_FR";
		
		/*Step Number: 1
		*Step Name: -
		*Step Description: 
			Step 1: Add translation for document/uploaded file
		*Input Data: 
			- Add some documents in different languages, eg English, French...
			- Open document in English
			- Click on Add Translation icon on the action bar 
			- Navigate to French document
			- Save
			- Click Relation icon on side bar
		*Expected Outcome: 
			- Documents are created
			- French document is list in Languages list of English document.When you add this content in a CLV or SCV, change language, you will see effect*/ 
		
		info("Create content 1");
		navTool.goToSiteExplorer();
		SEHome.goToPath("acme/documents", "Sites Management");
		SEHome.goToAddNewContent();
		CreNewDoc.createNewDoc(selectDocumentType.FILE);
		CreNewDoc.addNewFile(title, content);
		CreNewDoc.saveAndClose();
		
		SEHome.selectNode("documents");
		info("Create content 2");
		SEHome.goToAddNewContent();
		CreNewDoc.createNewDoc(selectDocumentType.FILE);
		CreNewDoc.addNewFile(title2, content2);
		select(CreNewDoc.ELEMENT_FILEFORM_LANGUAGE, "fr", 2);
		Utils.pause(2000);
		CreNewDoc.saveAndClose();
		
		click(SEHome.ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}", title));
		SEHome.addDocumentTranslation("General Drives/Sites Management/acme/documents", title2);
		waitForAndGetElement(SEHome.ELEMENT_SITEEXPLORER_LEFTBOX_RELATION);
		click(SEHome.ELEMENT_SITEEXPLORER_LEFTBOX_RELATION);
		waitForAndGetElement(SEHome.ELEMENT_SITEEXPLORER_LEFTBOX_TITLE_TRANSLATION.replace("${title}", title2));
		
		info("Delete all data test");
		navTool.goToSiteExplorer();
		SEHome.goToPath("acme/documents", "Sites Management");
		SEHome.deleteData(title);
		SEHome.deleteData(title2);
 	}

	/**
	*<li> Case ID:116586.</li>
	*<li> Test Case Name: Add Comment.</li>
	*<li> Pre-Condition: If Comment is not available on action bar, go to Content Administration/ Explorer/ Views, Edit view being in use with Comment option is ticked</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_AddComment() {
		info("Test 2: Add Comment");
		info("Get data test");
		String number1= getRandomNumber();
		String number2 = getRandomNumber();
		String title = txData.getContentByArrayTypeRandom(1)+number1;
		String content = txData.getContentByArrayTypeRandom(1)+number1;
		String content2 = txData.getContentByArrayTypeRandom(1)+number2;
		info("Fnishing Getting data test");
		
		/*Step Number: 1
		*Step Name: -
		*Step Description: 
			Step 1: Add Comment
		*Input Data: 
			Add a comment
			- Select a document or uploaded file
			- Click on Comment icon on the action bar 
			- Fill comment's content
			- Save
		*Expected Outcome: 
			You can see the comment at the bottom of document/uploaded file.*/ 
		info("Create content 1");
		navTool.goToSiteExplorer();
		SEHome.goToPath("acme/documents", "Sites Management");
		SEHome.goToAddNewContent();
		CreNewDoc.createNewDoc(selectDocumentType.FILE);
		CreNewDoc.addNewFile(title, content);
		CreNewDoc.saveAndClose();

		info("Add a comment");
		SEHome.addEditComment(content2,true);
		
		waitForAndGetElement(SEHome.ELEMENT_SITEEXPLORER_COMMENT.replace("${number}", "1"));
		SEHome.selectNode("documents");
		SEHome.deleteData(title);
 	}

	/**
	*<li> Case ID:116592.</li>
	*<li> Test Case Name: Add a tag.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*
	**<li> Case ID:116652.</li>
	*<li> Test Case Name: Edit a tag.</li>
	*<li> Pre-Condition: A tag is created successfully</li>
	*<li> Post-Condition: </li>
	*
	**<li> Case ID:116653.</li>
	*<li> Test Case Name: Detele a tag.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: A tag is already created.</li>
	*/
	@Test
	public  void test05_06_07_AddEditDeleteATag() {
		info("Test 05: Add a tag");
		String number1= getRandomNumber();
		String number2 = getRandomNumber();
		String title = txData.getContentByArrayTypeRandom(1)+number1;
		String content = txData.getContentByArrayTypeRandom(1)+number1;
		String content2 = txData.getContentByArrayTypeRandom(1)+number2;
		String content3 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		/*Step Number: 1
		*Step Name: Step 1: Add a tag
		*Step Description: 
			- Select a document or uploaded file
			- Click on Tag button in the action bar (if not, select Tag from More drop
			-down menu)
			- Put name for a tag
			- Click Add button, the Close button
		*Input Data: 
			
		*Expected Outcome: 
			Node is added tag. You can find document using tag in Tag cloud of FE*/ 
		info("Create content 1");
		navTool.goToSiteExplorer();
		SEHome.goToPath("acme/documents", "Sites Management");
		SEHome.goToAddNewContent();
		CreNewDoc.createNewDoc(selectDocumentType.FILE);
		CreNewDoc.addNewFile(title, content);
		CreNewDoc.saveAndClose();

		info("Add a tag to the Content");
		SEHome.selectNode(title);
		SEHome.addTag(content2);
		info("Verify that the tag is shown in Tag tab of SE");
		click(SEHome.ELEMENT_SITEEXPLORER_TAG_CLOUD_TAB);
		waitForAndGetElement(SEHome.ELEMENT_SIDEBAR_TAGCLOUD_NAME.replace("${name}",content2));
		
		info("Test 06: Edit a tag");
		SEHome.editTag(content2,content3);
		
		info("Test 07: Delete a tag");
		SEHome.deleteTag(content3);
		SEHome.deleteData(title);
 	}

	/**
	*<li> Case ID:116611.</li>
	*<li> Test Case Name: Vote for document/uploaded file.</li>
	*<li> Pre-Condition: If Vote is not available on action bar, go to Content Administration/Manage View, and edit your current view in use with Vote option ticked</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test08_VoteForDocumentuploadedFile() {
		info("Test 08: Vote for document/uploaded file");
		
		String fileName = fData.getAttachFileByArrayTypeRandom(1);
		
		/*Step Number: 1
		*Step Name: -
		*Step Description: 
			Step 1: Vote for document/uploaded file
		*Input Data: 
			- Create a document or upload a file
			- Select this node
			- Click on Vote icon on action bar, perform to vote
		*Expected Outcome: 
			The node is voted*/ 
		info("Upload file");
		navTool.goToSiteExplorer();
		SEHome.goToPath("acme/documents", "Sites Management");
		SEHome.uploadFile("TestData/"+fileName);
		
		click(SEHome.ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}", fileName));
		SEHome.voteDocument();
		click(SEHome.ELEMENT_SITEEXPLORER_VOTE_AVERAGE);
		waitForAndGetElement(SEHome.ELEMENT_SITEEXPLORER_VOTEONDOCUMENT);	
		SEHome.deleteData(fileName);
 	}

	/**
	*<li> Case ID:116650.</li>
	*<li> Test Case Name: Edit Comment.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test03_EditComment() {
		info("Test 3: Edit Comment");
		info("Get data test");
		String number1= getRandomNumber();
		String number2 = getRandomNumber();
		String title = txData.getContentByArrayTypeRandom(1)+number1;
		String content = txData.getContentByArrayTypeRandom(1)+number1;
		String content2 = txData.getContentByArrayTypeRandom(1)+number2;
		String content3 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		info("Finished getting data test");
		
		/*Step Number: 1
		*Step Name: -
		*Step Description: 
			Step 1: Edit Comment
		*Input Data: 
			Add a comment
			- Select a document or uploaded file
			- Click on Comment icon in the action bar 
			- Put content for comment
			- Click Save buttonEdit a comment
			- Show comment, click on Edit icon, perform to edit, click Save
		*Expected Outcome: 
			- Comment is edited*/ 
		navTool.goToSiteExplorer();
		SEHome.goToPath("acme/documents", "Sites Management");
		info("Add a new content");
		SEHome.goToAddNewContent();
		info("Select a document type");
		CreNewDoc.createNewDoc(selectDocumentType.FILE);
		info("Create a new file");
		CreNewDoc.addNewFile(title, content);
		info("Save and close the file");
		CreNewDoc.saveAndClose();
		
		SEHome.addEditComment(content2,true);
		info("Veriy that the comment is added");
		waitForAndGetElement(By.xpath((SEHome.ELEMENT_SITEEXPLORER_COMMENT).replace("${number}", "1")));
		
		info("Click on Show comment button on action bar");
		click(SEHome.ELEMENT_SITEEXPLORER_COMMENT_SHOW);
		SEHome.addEditComment(content3,false);
		
		info("Click on Show comment button on action bar");
		click(SEHome.ELEMENT_SITEEXPLORER_COMMENT_SHOW);
		info("Verify that the comment is edited");
		waitForAndGetElement(By.xpath((SEHome.ELEMENT_SITEEXPLORER_COMMENT_CONTENT).replace("${content}", content3)));
		info("The coment is edited successfully");
		SEHome.deleteData(title);
 	}

	/**
	*<li> Case ID:116651.</li>
	*<li> Test Case Name: Delete Comment.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test04_DeleteComment() {
		info("Test 4: Delete Comment");
		
		String number1= getRandomNumber();
		String number2 = getRandomNumber();
		String title = txData.getContentByArrayTypeRandom(1)+number1;
		String content = txData.getContentByArrayTypeRandom(1)+number1;
		String content2 = txData.getContentByArrayTypeRandom(1)+number2;
		
		/*Step Number: 1
		*Step Name: -
		*Step Description: 
			Step 1: Delete Comment
		*Input Data: 
			Add a comment
			- Select a document or uploaded file
			- Click on Comment icon in the action bar 
			- Put content for comment
			- Click Save buttonDelete a comment
			- Show the comment, click on Delete icon ,click OK
		*Expected Outcome: 
			Comment is deleted*/ 
		navTool.goToSiteExplorer();
		SEHome.goToPath("acme/documents", "Sites Management");
		SEHome.goToAddNewContent();
		CreNewDoc.createNewDoc(selectDocumentType.FILE);
		CreNewDoc.addNewFile(title, content);
		CreNewDoc.saveAndClose();

		SEHome.addEditComment(content2,true);
		waitForAndGetElement(By.xpath((SEHome.ELEMENT_SITEEXPLORER_COMMENT).replace("${number}", "1")));
		
		click(SEHome.ELEMENT_SITEEXPLORER_COMMENT_SHOW);
		click(SEHome.ELEMENT_SITEEXPLORER_COMMENT_DELETE);
		alert.acceptAlert();
		waitForElementNotPresent(By.xpath((SEHome.ELEMENT_SITEEXPLORER_COMMENT).replace("${number}", "1")));
		SEHome.deleteData(title);
 	}

}
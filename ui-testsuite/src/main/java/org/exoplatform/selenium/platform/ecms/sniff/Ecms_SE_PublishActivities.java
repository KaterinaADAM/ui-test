package org.exoplatform.selenium.platform.ecms.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.ecms.CreateNewDocument.selectDocumentType;
import org.openqa.selenium.By;
import org.testng.annotations.*;


/**
 * @author eXo
 *
 */
public class Ecms_SE_PublishActivities extends ECMS_TestConfig_Part2 {
	/**
	 *<li> Case ID:116665.</li>
	 *<li> Test Case Name: Check intranet homepage after adding a File content.</li>
	 *<li> Case ID:116683.</li>
	 *<li> Test Case Name: Edit a file from the File activity.</li>
	 */
	@Test
	public  void test01_02_CheckIntranetHomepageAfterAddingAFileContent_EditFileFromTheFileActivity() {
		info("Test 1: Check intranet homepage after adding a File content");
		info("Get data test");
		String random=getRandomNumber();
		String title =  txData.getContentByArrayTypeRandom(1)+random;
		String content =  txData.getContentByArrayTypeRandom(1)+random;
		info("Fnishing Getting data test");
		/*Step Number: 1
		 *Step Name: - Add a File Content
		 *Step Description: 
			- Connect to Intranet
			- Navigate Administration 
			-> Content 
			-> Sites Explorer 
			- Click [New content]: and choose File
			- Fill the info and click [Save & Close]
			- Back to the Homepage
		 *Input Data: 

		 *Expected Outcome: 
			- A new Content activity is added in the activity stream
			- Informations displayed in the featured content are :1. File icon 2.File's name3.File description if exist 4.Version (if exist) and file size*/ 
		navTool.goToSiteExplorer();
		SEHome.goToAddNewContent();
		CreNewDoc.createNewDoc(selectDocumentType.FILE);
		CreNewDoc.addNewFile(title, content);
		CreNewDoc.saveAndClose();
		hp.goToHomePage();
		aHome.checkActivityAddFile(title);

		info("Test 2 Edit a file from the File activity");
		/*Step Number: 1
		 *Step Name: 
		 *Step Description: 
			- Connect to Intranet
			- From the file activity, click on the link "Edit"
		 *Input Data: 

		 *Expected Outcome: 
			- The Content explorer is opened in the file to edit*/ 
		click(By.xpath(aHome.ELEMENT_ACTIVITY_FILE_EDIT_FILE_FROM_ACTIVITY.replace("{$title}", title)));
		CreNewDoc.saveAndClose();

		// delete data
		navTool.goToSiteExplorer();
		SEHome.deleteData(title);
	}

	/**
	 *<li> Case ID:116666.</li>
	 *<li> Test Case Name: Check intranet homepage after adding Web Content.</li>	
	 *<li> Case ID:116671.</li>
	 *<li> Test Case Name: Check intranet homepage after publishing a content.</li>
	 */
	@Test
	public  void test03__04_CheckIntranetHomepageAfterAddingWebContent_CheckIntranetHomepageAfterPublishWebContent() {
		info("Test 3: Check intranet homepage after adding Web Content");
		String random=getRandomNumber();
		String title =  txData.getContentByArrayTypeRandom(1)+random;
		String content =  txData.getContentByArrayTypeRandom(1)+random;
		/*Step Number: 1
		 *Step Name: - Add a FreeLayout Web Content
		 *Step Description: 
			- Connect to Intranet
			- Navigate Administration 
			-> Content 
			-> Sites Explorer 
			- Click [New content]: and choose Web Content
			- Fill the info and click [Save & Close]
			- Back to the Homepage
		 *Input Data: 

		 *Expected Outcome: 
			- A new Content activity is added in the activity stream
			- Informations displayed in the featured content are :1. Icon corresponding to the content type2. Name of the content3. First 4 lines of content's summary4. Type of the content5.Version6. Current status*/ 
		navTool.goToSiteExplorer();
		SEHome.goToAddNewContent();
		CreNewDoc.createNewDoc(selectDocumentType.WEBCONTENT);
		CreNewDoc.addNewWebContent(title, content);
		CreNewDoc.saveAndClose();
		hp.goToHomePage();
		aHome.checkActivityAddWebContent(title, null, null);

		info("Test 4: Check intranet homepage after publishing a content");
		String comment="Document has been published.";
		/*Step number: 2
		 *Step Name: Publish file content
		 *Step Description: 
			- Navigate Admin 
			-> Content 
			-> Content Explorer 
			- Choose the content and click [Publication] in Action ba
			- Change the status of the shared content to Published
			- Back to the homepage
		 *Input Data: 

		 *Expected Outcome: 
			- The content activity is updated in the activity stream with the new summary
			- A comment is added: Document has been published.*/ 
		navTool.goToSiteExplorer();
		SEHome.selectNode(title);
		SEHome.goToPublication();
		SEHome.changeStatusPulication("Published");
		hp.goToHomePage();
		driver.navigate().refresh();
		aHome.checkActivityAddWebContent(title, "1", "Published");
		waitForAndGetElement(By.xpath(aHome.ELEMENT_ACTIVITY_COMMOM_CHECK_COMMENT_OF_ACTIVITY.replace("${title}",title).replace("${comment}",comment)));

		// delete data
		navTool.goToSiteExplorer();
		SEHome.deleteData(title);
	}

	/**
	 *<li> Case ID:116667.</li>
	 *<li> Test Case Name: Check intranet homepage after adding Product content.</li>
	 */
	@Test
	public void test05_CheckIntranetHomepageAfterAddingProductContent() {
		info("Test 5: Check intranet homepage after adding Product content");
		String random=getRandomNumber();
		String title =  txData.getContentByArrayTypeRandom(1)+random;
		String summary =  txData.getContentByArrayTypeRandom(1)+random;

		/*Step Number: 1
		 *Step Name: - Add a Product Content
		 *Step Description: 
			- Connect to Intranet
			- Navigate Administration 
			-> Content 
			-> Sites Explorer 
			- Click [New content]: and choose Product
			- Fill the info and click [Save & Close]
			- Back to the Homepage
		 *Input Data: 

		 *Expected Outcome: 
			- A new Content activity is added in the activity stream
			- Information displayed in the featured content are:1. Icon corresponding to the content type2. Name of the content3. First 4 lines of content's summary4. Type of the content5.Version6. Current status*/ 
		navTool.goToSiteExplorer();
		SEHome.goToAddNewContent();
		CreNewDoc.createNewDoc(selectDocumentType.PRODUCT);
		CreNewDoc.addNewProduct(title, summary);
		CreNewDoc.saveAndClose();
		hp.goToHomePage();
		aHome.checkActivityAddProduct(title, null, null);

		// delete data
		navTool.goToSiteExplorer();
		SEHome.deleteData(title);	
	}

	/**
	 *<li> Case ID:116668.</li>
	 *<li> Test Case Name: Check intranet homepage after Editing title of content.</li>
	 */
	@Test
	public  void test06_CheckIntranetHomepageAfterEditingTitleOfContent() {
		info("Test 6: Check intranet homepage after Editing title of content");
		String random=getRandomNumber();
		String title =  txData.getContentByArrayTypeRandom(1)+random;
		String content =  txData.getContentByArrayTypeRandom(1)+random;
		String random1=getRandomNumber();
		String newContent=txData.getContentByArrayTypeRandom(1)+random1;
		String comment ="File has been updated.";
		/*Step Number: 1
		 *Step Name: - Add Content
		 *Step Description: 
			- Connect to Intranet
			- Navigate Admin 
			-> Content 
			-> Content Explorer 
			- Click [New content]: and choose a template, for example: File
			- Fill the info and click [Save & Close]
			- Back to the Homepage
		 *Input Data: 

		 *Expected Outcome: 
			- A new Content activity is added in the activity stream
			- Informations displayed in the featured content are :1. File icon 2.File's name3.File description if exist 4.Version (if exist) and file size*/
		navTool.goToSiteExplorer();
		SEHome.goToAddNewContent();
		CreNewDoc.createNewDoc(selectDocumentType.FILE);
		CreNewDoc.addNewFile(title, content);
		CreNewDoc.saveAndClose();
		hp.goToHomePage();
		aHome.checkActivityAddFile(title);

		/*Step number: 2
		 *Step Name: Edit title of content
		 *Step Description: 
			- Navigate Admin 
			-> Content 
			-> Content Explorer 
			- Navigate to the content and click Edit in Action Bar
			- Edit the title of the shared content
			- Back to the homepage
		 *Input Data: 

		 *Expected Outcome: 
			- The content activity is updated in the activity stream with the new title
			- A comment is added:File has been updated.*/ 
		navTool.goToSiteExplorer();
		SEHome.selectNode(title);
		SEHome.editDocument("",newContent);
		CreNewDoc.saveAndClose();
		hp.goToHomePage();
		waitForAndGetElement(By.xpath(aHome.ELEMENT_ACTIVITY_COMMOM_CHECK_COMMENT_OF_ACTIVITY.replace("${title}",title).replace("${comment}",comment)));

		// delete data
		navTool.goToSiteExplorer();
		SEHome.deleteData(title);
	}

	/**
	 *<li> Case ID:116669.</li>
	 *<li> Test Case Name: Check intranet homepage after adding tag to a content.</li>
	 */
	@Test
	public  void test07_CheckIntranetHomepageAfterAddingTagToAContent() {
		info("Test 7: Check intranet homepage after adding tag to a content");
		info("Get data test");
		String random=getRandomNumber();
		String title =  txData.getContentByArrayTypeRandom(1)+random;
		String content =  txData.getContentByArrayTypeRandom(1)+random;
		String tag =txData.getContentByArrayTypeRandom(22);
		String secondTags=txData.getContentByArrayTypeRandom(23);
		info("Fnishing Getting data test");
		/*Step Number: 1
		 *Step Name: - Add a Content
		 *Step Description: 
			- Connect to Intranet
			- Navigate Admin 
			-> Content 
			-> Content Explorer 
			- Click [New content]: and choose a template
			- Fill the info and click [Save & Close]
			- Back to the Homepage
		 *Input Data: 

		 *Expected Outcome: 
			- A new Content activity is added in the activity stream*/
		navTool.goToSiteExplorer();
		SEHome.goToAddNewContent();
		CreNewDoc.createNewDoc(selectDocumentType.WEBCONTENT);
		CreNewDoc.addNewFile(title, content);
		CreNewDoc.saveAndClose();
		hp.goToHomePage();
		driver.navigate().refresh();
		aHome.checkActivityAddWebContent(title,null,null);
		/*Step number: 2
		 *Step Name: - Add a tag to content
		 *Step Description: 
			- Connect to Intranet
			- Navigate Admin 
			-> Content 
			-> Content Explorer 
			- Navigate to the content
			- Click [Collbration] on Action Bar and click [Add tag]
			- Back to the homepage
		 *Input Data: 

		 *Expected Outcome: 
			- The content of the content activity isn't updated in the activity stream 
			- A comment is added: Tag: $value has been added.*/
		navTool.goToSiteExplorer();
		SEHome.selectNode(title);
		SEHome.addTag(tag);
		hp.goToHomePage();
		waitForAndGetElement(By.xpath(aHome.ELEMENT_ACTIVITY_COMMOM_CHECK_COMMENT_OF_ACTIVITY.replace("${title}",title).replace("${comment}","Tag: "+tag+" has been added.")));


		/*Step number: 3
		 *Step Name: - Add more 2 tags
		 *Step Description: 
			-Add two other tags by using common, then click Add
			- Back to the homepage
		 *Input Data: 

		 *Expected Outcome: 
			- One comment isadded the activity: Tag: $value, $value have been added.*/ 
		navTool.goToSiteExplorer();
		SEHome.selectNode(title);
		SEHome.addTag(secondTags);
		hp.goToHomePage();
		waitForAndGetElement(By.xpath(aHome.ELEMENT_ACTIVITY_COMMOM_CHECK_COMMENT_OF_ACTIVITY.replace("${title}",title).replace("${comment}","Tags: "+secondTags+" have been added.")));

		// delete data
		navTool.goToSiteExplorer();
		SEHome.deleteData(title);
	}

	/**
	 *<li> Case ID:116670.</li>
	 *<li> Test Case Name: Check intranet homepage after deleting a content.</li>
	 */
	@Test
	public  void test08_CheckIntranetHomepageAfterDeletingAContent() {
		info("Test 8: Check intranet homepage after deleting a content");
		info("Get data test");
		String random=getRandomNumber();
		String title =  txData.getContentByArrayTypeRandom(1)+random;
		String content =  txData.getContentByArrayTypeRandom(1)+random;
		info("Fnishing Getting data test");
		/*Step Number: 1
		 *Step Name: - Add a Content
		 *Step Description: 
			- Connect to Intranet
			- Navigate Admin 
			-> Content 
			-> Content Explorer 
			- Click [New content]: and choose a template
			- Fill the info and click [Save & Close]
			- Back to the Homepage
		 *Input Data: 

		 *Expected Outcome: 
			- A new Content activity is added in the activity stream*/
		navTool.goToSiteExplorer();
		SEHome.goToAddNewContent();
		CreNewDoc.createNewDoc(selectDocumentType.WEBCONTENT);
		CreNewDoc.addNewFile(title, content);
		CreNewDoc.saveAndClose();
		hp.goToHomePage();
		driver.navigate().refresh();
		aHome.checkActivityAddWebContent(title,null,null);
		/*Step number: 2
		 *Step Name: Delete content
		 *Step Description: 
			- Navigate Admin 
			-> Content 
			-> Content Explorer 
			- Navigate to the Content to be deleted, 
			- Right click and select [Delete]
			- Click OK to confirm
			- Back to the Homepage
		 *Input Data: 

		 *Expected Outcome: 
			- The Content activity related to the content is removed from the activity stream*/ 
		navTool.goToSiteExplorer();
		SEHome.deleteData(title);
		hp.goToHomePage();
		waitForElementNotPresent(By.xpath(aHome.ELEMENT_ACTIVITY_WEBCONTENT_TITLE.replace("{$title}", title)));
	}


	@Test
	public  void test09_CheckIntranetHomepageAfterUploadingAFile(){
		info("Test 9: Check intranet homepage after Uploading a file");
		/*Step Number: 1
		 *Step Name: 
		 *Step Description: 
			- Connect to Intranet
			- Navigate Admin 
			-> Content 
			-> Content Explorer 
			- Select a folder and click [Upload] in Action Bar
			- Browse to select a file
			- Back to the Home page
		 *Input Data: 

		 *Expected Outcome: 
			- A File activity is added to the activity stream*/ 
		info("Create data test");
		String file = fData.getAttachFileByArrayTypeRandom(1);
		info("Finish creating data test");

		info("Upload a file");
		navTool.goToSiteExplorer();
		SEHome.goToPath("intranet/documents","Sites Management");
		SEHome.uploadFile("TestData/"+file);

		info("Go to the activity and verify that the file's activity is shown");
		hp.goToHomePage();
		waitForAndGetElement(By.xpath(aHome.ELEMENT_ACTIVITY_FILE_UPLOAD_TITLE.replace("{$title}",file)));

		info("Delete the file");
		navTool.goToSiteExplorer();
		SEHome.goToPath("intranet/documents","Sites Management");
		SEHome.deleteData(file);

	}

	/**
	 *<li> Case ID:116674.</li>
	 *<li> Test Case Name: Check intranet homepage after editing Title of an uploaded file.</li>
	 */
	@Test
	public  void test10_CheckIntranetHomepageAfterEditingAFile(){
		info("Test 10:Check intranet homepage after editing Title of an uploaded file");
		info("Create data test");
		String file = fData.getAttachFileByArrayTypeRandom(1);
		String newTitle =  txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		info("Finish creating data test");

		info("Upload a file");
		navTool.goToSiteExplorer();
		SEHome.goToPath("intranet/documents","Sites Management");
		SEHome.uploadFile("TestData/"+file);

		info("Edit the file");
		SEHome.selectNode(file);
		SEHome.editDocument(newTitle,"");
		CreNewDoc.saveAndClose();

		info("Delete the file");
		navTool.goToSiteExplorer();
		SEHome.goToPath("intranet/documents","Sites Management");
		SEHome.deleteData(file);
	}
	/**
	 *<li> Case ID:116673.</li>
	 *<li> Test Case Name: Check intranet homepage after adding a category to an uploaded file.</li>
	 */
	@Test
	public void test11_CheckIntranetHomepageAfterAddingACategoryforUploadFile(){
		info("Test 11: Check intranet homepage after adding a category to an uploaded file");
		/*Step number: 2
		 *Step Name: - Add a Category to the uploaded file
		 *Step Description: 
			- Connect to Intranet
			- Navigate Admin 
			-> Content 
			-> Content Explorer 
			- Select the uploaded file and click [Categories] in Action Bar
			- Open [Select Catogory] Tab and Add a category to the file
			- Click [Close] Button to finish adding category
			- Back to the Homepage
		 *Input Data: 

		 *Expected Outcome: 
			- The content of theFile activity isn't updated in the activity stream
			- A comment is added: Category: $value has been added.*/
		info("Create data test");
		String file = fData.getAttachFileByArrayTypeRandom(1);
		info("Finish creating data test");

		info("Upload a file");
		navTool.goToSiteExplorer();
		SEHome.goToPath("intranet/documents","Sites Management");
		SEHome.uploadFile("TestData/"+file);

		info("Select the file");
		SEHome.selectNode(file);

		info("Add a category to the file");
		SEHome.addCategoryForNode(file,"intranet");

		hp.goToHomePage();
		waitForAndGetElement(By.xpath(aHome.ELEMENT_ACTIVITY_COMMOM_CHECK_COMMENT_OF_ACTIVITY.replace("${title}",file).replace("${comment}","Category: intranet has been added.")));

		info("Delete the file");
		navTool.goToSiteExplorer();
		SEHome.goToPath("intranet/documents","Sites Management");
		SEHome.deleteData(file);

	}

	/**
	 *<li> Case ID:116675.</li>
	 *<li> Test Case Name: Check intranet homepage after deleting an uploaded file.</li>
	 */
	public void test12_CheckIntranetHomepageAfterDeletingAFile(){
		info("Test 12:Check intranet homepage after deleting an uploaded file");
		info("Create data test");
		String file = fData.getAttachFileByArrayTypeRandom(1);
		info("Finish creating data test");

		info("Upload a file");
		navTool.goToSiteExplorer();
		SEHome.goToPath("intranet/documents","Sites Management");
		SEHome.uploadFile("TestData/"+file);

		info("Delete the file");
		navTool.goToSiteExplorer();
		SEHome.goToPath("intranet/documents","Sites Management");
		SEHome.deleteData(file);

		info("Check the activity");
		hp.goToHomePage();
		waitForElementNotPresent(By.xpath(aHome.ELEMENT_ACTIVITY_FILE_UPLOAD_TITLE.replace("{$title}",file)));
	}

	/**
	 *<li> Case ID:116680.</li>
	 *<li> Test Case Name: Edit a content from the Content activity.</li>
	 */
	@Test
	public  void test13_EditAContentFromTheContentActivity(){
		info("Test 13: Edit a content from the Content activity");
		/*Step Number: 1
		 *Step Name: 
		 *Step Description: ;
			- Connect to Intranet
			- From the Content activity, click on the link "Edit"
		 *Input Data: 

		 *Expected Outcome: 
			- The content explorer is opened to edit the content*/ 
		info("Create data test");
		String random=getRandomNumber();
		String title =  txData.getContentByArrayTypeRandom(1)+random;
		String content =  txData.getContentByArrayTypeRandom(1)+random;
		String newContent =  txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		info("Finish creating data test");

		info("Create a new Content");
		navTool.goToSiteExplorer();
		SEHome.goToPath("intranet/documents","Sites Management");
		SEHome.goToAddNewContent();
		CreNewDoc.createNewDoc(selectDocumentType.WEBCONTENT);
		CreNewDoc.addNewFile(title, content);
		CreNewDoc.saveAndClose();

		hp.goToHomePage();
		driver.navigate().refresh();
		aHome.checkActivityAddWebContent(title,null,null);
		click(aHome.ELEMENT_ACTIVITY_EDIT_A_NODE.replace("{$title}", title));
		inputFrame(CreNewDoc.ELEMENT_FILEFORM_BLANK_CONTENT2,newContent);
		CreNewDoc.saveAndClose();

		info("Delete the file");
		navTool.goToSiteExplorer();
		SEHome.goToPath("intranet/documents","Sites Management");
		SEHome.deleteData(title);

	}
	/**
	 *<li> Case ID:116681.</li>
	 *<li> Test Case Name: View a content from the Content activity.</li>
	 */
	public  void test14_ViewAContentFromTheContentActivity(){
		info("Test 14: View a content from the Content activity");
		info("Create data test");
		String random=getRandomNumber();
		String title =  txData.getContentByArrayTypeRandom(1)+random;
		String content =  txData.getContentByArrayTypeRandom(1)+random;
		info("Finish creating data test");

		info("Create a new Content");
		navTool.goToSiteExplorer();
		SEHome.goToPath("intranet/documents","Sites Management");
		SEHome.goToAddNewContent();
		CreNewDoc.createNewDoc(selectDocumentType.WEBCONTENT);
		CreNewDoc.addNewFile(title, content);
		CreNewDoc.saveAndClose();

		info("View the content from the activity");
		hp.goToHomePage();
		click(aHome.ELEMENT_ACTIVITY_VIEW_A_NODE.replace("{$title}", title));
		waitForAndGetElement(SEHome.ELEMENT_DOCUMENT_VIEW.replace("{$content}", content));

		info("Delete the file");
		navTool.goToSiteExplorer();
		SEHome.goToPath("intranet/documents","Sites Management");
		SEHome.deleteData(title);
	}

	/**
	 *<li> Case ID:116682.</li>
	 *<li> Test Case Name: View a file from the File activity.</li>
	 *<li> Test Case Name: Edit a file from the File activity.</li>
	 */
	@Test
	public  void test15_ViewAndEditAFileFromTheFileActivity() {
		info("Test 15: View a file from the File activity");
		info("Create data test");
		String file = fData.getAttachFileByArrayTypeRandom(1);
		info("Finish creating data test");

		info("Upload a file");
		navTool.goToSiteExplorer();
		SEHome.goToPath("intranet/documents","Sites Management");
		SEHome.uploadFile("TestData/"+file);

		info("View the content from the activity");
		hp.goToHomePage();
		waitForAndGetElement(aHome.ELEMENT_ACTIVITY_FILE_UPLOAD_TITLE.replace("{$title}",file));
		click(aHome.ELEMENT_ACTIVITY_VIEW_A_NODE.replace("{$title}",file));
		waitForAndGetElement(aHome.ELEMENT_ACTIVITY_PREVIEW_FILE_WINDOW_NAME_OF_FILE.replace("{$title}",file));

		info("Edit the content from the activity");
		hp.goToHomePage();
		click(aHome.ELEMENT_ACTIVITY_EDIT_A_NODE.replace("{$title}",file));
		waitForAndGetElement(aHome.ELEMENT_ACTIVITY_EDIT_FROM_HOMEPAGE.replace("{$title}",file));

		info("Delete the file");
		navTool.goToSiteExplorer();
		SEHome.goToPath("intranet/documents","Sites Management");
		SEHome.deleteData(file);
	}


	/**
	 *<li> Case ID:116684.</li>
	 *<li> Test Case Name: Update the File activity after moving a file.</li>
	 */
	@Test
	public  void test16_UpdateTheFileActivityAfterMovingAFile() {
		info("Test 16 Update the File activity after moving a file");
		/*Step Number: 1
		 *Step Name: 
		 *Step Description: 
			- Connect to Intranet
			- Open Sites explorer
			- Move the file
			- back to the Homepage
		 *Input Data: 

		 *Expected Outcome: 
			- The content of the file content activity isn't updated in the activity stream
			- A comment is added: File has been moved to: $valuewhere $value = path of the file.d*/ 

		info("Create data test");
		String file = fData.getAttachFileByArrayTypeRandom(1);
		String fileRecept="intranet";
		info("Finish creating data test");

		info("Upload a file");
		navTool.goToSiteExplorer();
		SEHome.goToPath("intranet/documents","Sites Management");
		SEHome.uploadFile("TestData/"+file);

		info("Move the file");
		dragAndDropToObject(SEHome.ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}", file),SEHome.ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}", fileRecept));
		alert.acceptAlert();
		Utils.pause(2000);

		info("Check the comment on the activity");
		hp.goToHomePage();
		waitForAndGetElement(aHome.ELEMENT_ACTIVITY_COMMOM_CHECK_COMMENT_OF_ACTIVITY.replace("${title}",file).replace("${comment}","File has been moved to: /sites/intranet/"+file.toLowerCase()));

		info("Delete the file");
		navTool.goToSiteExplorer();
		SEHome.goToPath(fileRecept,"Sites Management");
		SEHome.deleteData(file);
	}

	/**
	 *<li> Case ID:116685.</li>
	 *<li> Test Case Name: Update Content activity after moving a content.</li>
	 */
	@Test
	public  void test17_UpdateContentActivityAfterMovingAContent() {
		info("Test 17 Update Content activity after moving a content");
		/*Step Number: 1
		 *Step Name: 
		 *Step Description: 
			- Connect to Intranet
			- Open Site explorer
			- Move the content
			- Back to the homepage
		 *Input Data: 

		 *Expected Outcome: 
			- The content of the content activity isn't updated in the activity stream
			- A comment is added: Publication has been moved to: $value$value = path of the content.*/ 
		info("Create data test");
		String random=getRandomNumber();
		String title =  txData.getContentByArrayTypeRandom(1)+random;
		String content =  txData.getContentByArrayTypeRandom(1)+random;
		String fileRecept="intranet";
		info("Finish creating data test");

		info("Create a new Content");
		navTool.goToSiteExplorer();
		SEHome.goToPath("intranet/documents","Sites Management");
		SEHome.goToAddNewContent();
		CreNewDoc.createNewDoc(selectDocumentType.WEBCONTENT);
		CreNewDoc.addNewFile(title, content);
		CreNewDoc.saveAndClose();

		info("Move the content");
		navTool.goToSiteExplorer();
		SEHome.goToPath("intranet/documents","Sites Management");
		dragAndDropToObject(SEHome.ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}", title),SEHome.ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}", fileRecept));
		alert.acceptAlert();
		Utils.pause(2000);
		
		info("Check the comment on the activity");
		hp.goToHomePage();
		waitForAndGetElement(aHome.ELEMENT_ACTIVITY_COMMOM_CHECK_COMMENT_OF_ACTIVITY.replace("${title}",title).replace("${comment}","Publication has been moved to: /sites/intranet/"+title.toLowerCase()));

		info("Delete the file");
		navTool.goToSiteExplorer();
		SEHome.goToPath(fileRecept,"Sites Management");
		SEHome.deleteData(title);

	}
}
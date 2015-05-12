package org.exoplatform.selenium.platform.ecms.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.testng.annotations.*;



	public class Ecms_Document_Preview extends ECMS_TestConfig_Part1{
		String file;
		String folderPath;
		String nameDrive;
		
		@BeforeMethod
		public void setBeforeMethod(){
			file=folderPath=nameDrive="";
		}
		
		@AfterMethod
		public void afterMethod(){
			folderPath=siteExPath.getSiteExpPathByIndex(8);//sites/intranet/documents
		    nameDrive=siteExDrive.getSiteExpDriveByIndex(0);//Collaboration
		    if(waitForAndGetElement(docPrev.ELEMENT_PREVIEW_MODE, 5000,0)!=null)
				docPrev.closeByClickCrossIcon();
		    
			deleteAllDataTest(nameDrive,folderPath);
			magAc.signOut();
			magAc.signIn(DATA_USER1, DATA_PASS);
		}
		
		/**
		 * Delete all data test
		 */
		private void deleteAllDataTest(String nameDrive,String path){
			info("Delete data test");
			this.driver.get(baseUrl);
			Utils.pause(2000);
			navTool.goToSiteExplorer();
			info("Go to the drive:"+nameDrive);
			SEHome.openDrives();
			SEHome.selectADrive(nameDrive);

			info("Go to the folder:"+folderPath);
			SEHome.goToAFolder(folderPath);
			driver.navigate().refresh();
			
			info("Delete all files");
			SEHome.deleteAllFiles();
		}
		

	/**
	*<li> Case ID:112333.</li>
	*<li> Test Case Name: Check the reader on the activity stream</li>
	*/
	@Test
	public  void test01_CheckReaderOnActivityStream() {
		info("Test 1: Check the reader on the activity stream");
		file = attachFile.getAttachFileByArrayTypeRandom(1);
	    folderPath=siteExPath.getSiteExpPathByIndex(7);//Collaboration/sites/intranet/documents
		nameDrive=siteExDrive.getSiteExpDriveByIndex(2);//General Drives
		String description = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		/*Step Number: 1
		*Step Name: Step 1. Share a PDF file activity.
		*Step Description: 
			- Connect to Intranet Homepage
			- Attach a PDF file
			- Click on the button Share
		*Input Data: 
		*Expected Outcome: 
			- An activity of the PDF file is displayed on the stream*/ 
        info("Share a pdf file activity");
		acStream.addActivity(nameDrive,folderPath,folderDataTestPath, file,true,description);

		info("Verify that the activity is shown on AS");
		waitForAndGetElement(acStream.ELEMENT_ACTIVITY_DOCUMENT_MEDIA_TITLE.replace("${title}",file),3000,1);
		
		/*Step Number: 2
		*Step Name: Step 2. View the attached file.
		*Step Description: 
			- Click on the link "View"
		*Input Data: 
			
		*Expected Outcome: 
			- The reader is displayed with a file content
			- Actions are displayed on the top bar of the reader
			- On the right panel, the title of the file, 
			the username and the comment box are displayed*/ 
		
		info("Open Preview mode by clicking on View link");
		acStream.openPreviewMode(file,1,"");

		info("Verify that the preview mode is shown");
		waitForAndGetElement(docPrev.ELEMENT_PREVIEW_MODE, 3000,1);
		info("The preview is shown successfully");
		
		info("The reader is displayed with a file content");
		waitForAndGetElement(docPrev.ELEMENT_READER_FILE_CONTENT_PAGE_1,3000,1);
		
		info("Actions are displayed on the top bar of the reader");
		waitForAndGetElement(docPrev.ELEMENT_ACTIONS_SIDERBAR_TOGGLE_BTN,1000,1);
		waitForAndGetElement(docPrev.ELEMENT_ACTIONS_SEARCH_BTN,1000,1);
		waitForAndGetElement(docPrev.ELEMENT_ACTIONS_ARROW_UP_BTN,1000,1);
		waitForAndGetElement(docPrev.ELEMENT_ACTIONS_ARROW_DOWN_BTN,1000,1);
		waitForAndGetElement(docPrev.ELEMENT_ACTIONS_PAGE_INPUT_NUMBER_BOX,1000,1);
		waitForAndGetElement(docPrev.ELEMENT_ACTIONS_ZOOM_OUT_BTN,1000,1);
		waitForAndGetElement(docPrev.ELEMENT_ACTIONS_ZOOM_IN_BTN,1000,1);
		waitForAndGetElement(docPrev.ELEMENT_ACTIONS_SCALE_SELECT_BOX,1000,1);
		waitForAndGetElement(docPrev.ELEMENT_ACTIONS_DOWNLOAD_BTN,1000,1);
		waitForAndGetElement(docPrev.ELEMENT_ACTIONS_FULLSCREEN_BTN,1000,1);
		waitForAndGetElement(docPrev.ELEMENT_ACTIONS_TOOLS_BTN,1000,1);
		
		info("On the right panel, the title of the file,the username and the comment box are displayed");
		waitForAndGetElement(docPrev.ELEMENT_RIGHT_PANEL_HEADER,3000,1);
		waitForAndGetElement(docPrev.ELEMENT_RIGHT_PANEL_PROFILE_AVATAR.replace("${fullName}",DATA_NAME_USER1),1000,1);
		waitForAndGetElement(docPrev.ELEMENT_RIGHT_PANEL_PROFILE_NAME_LINK.replace("${firstName}",DATA_USER1),1000,1);
		waitForAndGetElement(docPrev.ELEMENT_RIGHT_PANEL_PROFILE_DATE_TIME,1000,1);
		waitForAndGetElement(docPrev.ELEMENT_RIGHT_PANEL_COMMENT_INPUT_BOX,1000,1);
		waitForAndGetElement(docPrev.ELEMENT_RIGHT_PANEL_COMMENT_AREA_BOX_WITH_NO_COMMENT,1000,1);
		docPrev.closeByClickCrossIcon();
 	}
	
	/**
	*<li> Case ID:112334.</li>
	*<li> Test Case Name: Check the reader on the Sites Explorer</li>
	*/
	@Test
	public  void test02_CheckReaderOnSiteExplorer() {
		info("Test 02: Check the reader on the Sites Explorer");
		file = attachFile.getAttachFileByArrayTypeRandom(1);
		String officefile = attachFile.getAttachFileByArrayTypeRandom(2);
	    folderPath=siteExPath.getSiteExpPathByIndex(8);//sites/intranet/documents
		nameDrive=siteExDrive.getSiteExpDriveByIndex(0);//Collaboration
		/*Step Number: 1
		*Step Name: Step 1. Upload a PDF file.
		*Step Description: 
			- Connect to Intranet
			- Go to Administration/Content/Sites Explorer
			- Choose a path
			- Upload a PDF file
		*Input Data: 

		*Expected Outcome: 
			- The PDF file is uploaded*/ 
		info("Go to Sites Explorer");
		navTool.goToSiteExplorer();
		info("Go to the drive:"+nameDrive);
		SEHome.openDrives();
		SEHome.selectADrive(nameDrive);

		info("Go to the folder:"+folderPath);
		SEHome.goToAFolder(folderPath);
		driver.navigate().refresh();

		info("Upload a file from:" + folderDataTestPath);
		SEHome.uploadFileWithDymanicPath(folderDataTestPath+ file);

		/*Step Number: 2
		*Step Name: Step 2. Open the uploaded PDF file.
		*Step Description: 
			- Click on the file's name
		*Input Data: 
			
		*Expected Outcome: 
			- The reader is displayed with a file content
			- Actions are displayed on the top bar of the reader
			*/ 
		info("Go to Activity stream of Intranet Home page");
		hp.goToHomePage();
		acStream.openPreviewMode(file, "",1);
		
		info("Verify that the preview mode is shown");
		waitForAndGetElement(docPrev.ELEMENT_PREVIEW_MODE, 3000,1);
		info("The preview is shown successfully");
		
		info("The reader is displayed with a file content");
		waitForAndGetElement(docPrev.ELEMENT_READER_FILE_CONTENT_PAGE_1,3000,1);
		
		info("Actions are displayed on the top bar of the reader");
		waitForAndGetElement(docPrev.ELEMENT_ACTIONS_SIDERBAR_TOGGLE_BTN,1000,1);
		waitForAndGetElement(docPrev.ELEMENT_ACTIONS_SEARCH_BTN,1000,1);
		waitForAndGetElement(docPrev.ELEMENT_ACTIONS_ARROW_UP_BTN,1000,1);
		waitForAndGetElement(docPrev.ELEMENT_ACTIONS_ARROW_DOWN_BTN,1000,1);
		waitForAndGetElement(docPrev.ELEMENT_ACTIONS_PAGE_INPUT_NUMBER_BOX,1000,1);
		waitForAndGetElement(docPrev.ELEMENT_ACTIONS_ZOOM_OUT_BTN,1000,1);
		waitForAndGetElement(docPrev.ELEMENT_ACTIONS_ZOOM_IN_BTN,1000,1);
		waitForAndGetElement(docPrev.ELEMENT_ACTIONS_SCALE_SELECT_BOX,1000,1);
		waitForAndGetElement(docPrev.ELEMENT_ACTIONS_DOWNLOAD_BTN,1000,1);
		waitForAndGetElement(docPrev.ELEMENT_ACTIONS_FULLSCREEN_BTN,1000,1);
		waitForAndGetElement(docPrev.ELEMENT_ACTIONS_TOOLS_BTN,1000,1);
		docPrev.closeByClickCrossIcon();
		/*Step Number: 3
		*Step Name: Step 3. Upload an office file.
		*Step Description: 
			- Upload an Office files: doc, docx, ppt, pptx, 
			xls, xlsx, xlt, odt, odp, ods, ots, odg, odt, rtf
		*Input Data: 
		*
		*Expected Outcome: 
			- The Office file is uploaded
			*/ 
		
		info("Go to Sites Explorer");
		navTool.goToSiteExplorer();
		info("Go to the drive:"+nameDrive);
		SEHome.openDrives();
		SEHome.selectADrive(nameDrive);

		info("Go to the folder:"+folderPath);
		SEHome.goToAFolder(folderPath);
		driver.navigate().refresh();

		info("Upload a file from:" + folderDataTestPath);
		SEHome.uploadFileWithDymanicPath(folderDataTestPath+ officefile);
		
		/*Step Number: 4
		*Step Name: Step 4. Open the uploaded Office file.
		*Step Description: 
			- Click on the file's name
		*Input Data: 
			
		*Expected Outcome: 
			- The reader is displayed with a file content
			- Actions are displayed on the top bar of the reader
			*/ 
		
		info("Go to Activity stream of Intranet Home page");
		hp.goToHomePage();
		acStream.openPreviewMode(officefile, "",1);
		
		info("Verify that the preview mode is shown");
		waitForAndGetElement(docPrev.ELEMENT_PREVIEW_MODE, 3000,1);
		info("The preview is shown successfully");
		
		info("The reader is displayed with a file content");
		waitForAndGetElement(docPrev.ELEMENT_READER_FILE_CONTENT_PAGE_1,3000,1);
		
		info("Actions are displayed on the top bar of the reader");
		waitForAndGetElement(docPrev.ELEMENT_ACTIONS_SIDERBAR_TOGGLE_BTN,1000,1);
		waitForAndGetElement(docPrev.ELEMENT_ACTIONS_SEARCH_BTN,1000,1);
		waitForAndGetElement(docPrev.ELEMENT_ACTIONS_ARROW_UP_BTN,1000,1);
		waitForAndGetElement(docPrev.ELEMENT_ACTIONS_ARROW_DOWN_BTN,1000,1);
		waitForAndGetElement(docPrev.ELEMENT_ACTIONS_PAGE_INPUT_NUMBER_BOX,1000,1);
		waitForAndGetElement(docPrev.ELEMENT_ACTIONS_ZOOM_OUT_BTN,1000,1);
		waitForAndGetElement(docPrev.ELEMENT_ACTIONS_ZOOM_IN_BTN,1000,1);
		waitForAndGetElement(docPrev.ELEMENT_ACTIONS_SCALE_SELECT_BOX,1000,1);
		waitForAndGetElement(docPrev.ELEMENT_ACTIONS_DOWNLOAD_BTN,1000,1);
		waitForAndGetElement(docPrev.ELEMENT_ACTIONS_FULLSCREEN_BTN,1000,1);
		waitForAndGetElement(docPrev.ELEMENT_ACTIONS_TOOLS_BTN,1000,1);
		docPrev.closeByClickCrossIcon();
		
 	}
	
	/**
	*<li> Case ID:112336.</li>
	*<li> Test Case Name: Check the display of the reader when the files format aren't supported on Sites Explorer</li>
	 * @throws Exception 
	*/
	@Test
	public  void test04_CheckDisplayOfReaderWhenTheFilesFormatNotSupportedOnSE() throws Exception {
		info("Test 04: Check the display of the reader when the files format aren't supported on Sites Explorer");
		file = attachFile.getAttachFileByArrayTypeRandom(25);
	    folderPath=siteExPath.getSiteExpPathByIndex(8);//sites/intranet/documents
		nameDrive=siteExDrive.getSiteExpDriveByIndex(0);//Collaboration
		/*Step Number: 1
		*Step Name: Step 1. Upload a not-supported file type on Sites Explorer.
		*Step Description: 
			- Connect to Intranet
			- Go to Administration/Content/Sites Explorer
			- Choose a path
			- Upload a not supported file type (NOT pdf format and office files: 
			doc, docx, ppt, pptx, xls, xlsx, xlt, odt, odp, ods, ots, odg, odt, rtf)

		*Input Data: 

		*Expected Outcome: 
			- The file with not supported type is shared on the activity stream*/ 
		
		info("Go to Sites Explorer");
		navTool.goToSiteExplorer();
		info("Go to the drive:"+nameDrive);
		SEHome.openDrives();
		SEHome.selectADrive(nameDrive);

		info("Go to the folder:"+folderPath);
		SEHome.goToAFolder(folderPath);
		driver.navigate().refresh();

		info("Upload a file from:" + folderDataTestPath);
		SEHome.uploadFileWithDymanicPath(folderDataTestPath+ file);
		
		/*Step Number: 2
		*Step Name: Step 2. View the attached file.
		*Step Description: 
			- Click on the file's name
		*Input Data: 
			
		*Expected Outcome: 
			- The reader form is not displayed
			- Download button is displayed
			- View link is not displayed
			- The file redirects to SE.
			*/ 
		
		info("Go to Activity stream of Intranet Home page");
		hp.goToHomePage();
		info("View link is not displayed");
		waitForElementNotPresent(acStream.ELEMENT_ACTIVITY_DOCUMENT_MEDIA_VIEW_LINK.replace("${nameFile}",file),3000,1);
		info("Download button is displayed");
		waitForAndGetElement(acStream.ELEMENT_ACTIVITY_DOCUMENT_MEDIA_DOWNLOAD_BTN.replace("${nameFile}",file),3000,1);
		
		acStream.openPreviewMode(file,"",1);
		waitForAndGetElement(SEHome.ELEMENT_CONTENT_NAME.replace("${nameFile}",file),3000,1);
		
		/*Step Number: 3
		*Step Name: Step 3. Download the attached file.
		*Step Description: 
			- Click on the button "Download"
		*Input Data: 
		*
		*Expected Outcome: 
			- The download popup is displayed.
			*/ 
		
		info("Go to Activity stream of Intranet Home page");
		hp.goToHomePage();
		info("Get dowload link");
		String url = waitForAndGetElement(acStream.ELEMENT_ACTIVITY_DOCUMENT_MEDIA_DOWNLOAD_BTN.replace("${nameFile}",file)).getAttribute("href").toString();
		info("Check download link is working well");
		downloadHandler.downloadFile(url);
		
		
 	}
	
	/**
	*<li> Case ID:112335.</li>
	*<li> Test Case Name: Check the display of the reader when the files format is not supported on the activity stream</li>
	 * @throws Exception 
	*/
	@Test
	public  void test03_CheckDisplayOfReaderWhenTheFilesFormatNotSupportedOnAS() throws Exception {
		info("Test 03: Check the display of the reader when the files format is not supported on the activity stream");
		file = attachFile.getAttachFileByArrayTypeRandom(25);
	    folderPath=siteExPath.getSiteExpPathByIndex(7);//Collaboration/sites/intranet/documents
		nameDrive=siteExDrive.getSiteExpDriveByIndex(2);//General Drives
		String description = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		/*Step Number: 1
		*Step Name: Step 1. Share an activity of file that is not a supported file type.
		*Step Description: 
			- Connect to Intranet Homepage
			- Attach a not supported file type (NOT pdf format and office files: 
			doc, docx, ppt, pptx, xls, xlsx, xlt, odt, odp, ods, ots, odg, odt, rtf)
			- Click Share

		*Input Data: 

		*Expected Outcome: 
			- The file with not supported type is uploaded*/ 
		
		 info("Share a pdf file activity");
		 acStream.addActivity(nameDrive,folderPath,folderDataTestPath, file,true,description);

		 info("Verify that the activity is shown on AS");
		 waitForAndGetElement(acStream.ELEMENT_ACTIVITY_DOCUMENT_MEDIA_TITLE.replace("${title}",file),3000,1);
		
		/*Step Number: 2
		*Step Name: Step 2. Open the uploaded file.
		*Step Description: 
			- Click on the file's name
		*Input Data: 
			
		*Expected Outcome: 
			- The reader form is not displayed
			- Download button is displayed
			*/ 
		
		info("View link is not displayed");
		waitForElementNotPresent(acStream.ELEMENT_ACTIVITY_DOCUMENT_MEDIA_VIEW_LINK.replace("${nameFile}",file),3000,1);
		info("Download button is displayed");
		waitForAndGetElement(acStream.ELEMENT_ACTIVITY_DOCUMENT_MEDIA_DOWNLOAD_BTN.replace("${nameFile}",file),3000,1);
			
		acStream.openPreviewMode(file,"",1);
		waitForAndGetElement(SEHome.ELEMENT_CONTENT_NAME.replace("${nameFile}",file),3000,1);
			
		/*Step Number: 3
		*Step Name: Step 3. Download the file.
		*Step Description: 
			- Click on the button "Download"
		*Input Data: 
		*
		*Expected Outcome: 
			- The download popup is displayed.
			*/ 
		info("Go to Activity stream of Intranet Home page");
		hp.goToHomePage();
		info("Get dowload link");
		String url = waitForAndGetElement(acStream.ELEMENT_ACTIVITY_DOCUMENT_MEDIA_DOWNLOAD_BTN.replace("${nameFile}",file)).getAttribute("href").toString();
		info("Check download link is working well");
		downloadHandler.downloadFile(url);
 	}
	
	/**
	*<li> Case ID:112475.</li>
	*<li> Test Case Name: Check No preview mode in Activity Stream</li>
	*<li> Precondition: - The shared file is not of the supported list of Preview.</li>
	*/
	@Test
	public  void test05_CheckNoPreviewModeInActivityStream() {
		info("Test 05: Check No preview mode in Activity Stream");
		file = attachFile.getAttachFileByArrayTypeRandom(24);
	    folderPath=siteExPath.getSiteExpPathByIndex(7);//Collaboration/sites/intranet/documents
		nameDrive=siteExDrive.getSiteExpDriveByIndex(2);//General Drives
		String description = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1.Share a PDF file.
		*Step Description: 
			- Connect to Intranet Homepage
			- Share a PDF file (ex: big size more than 99 pages)

		*Input Data: 

		*Expected Outcome: 
			- An activity is added to the stream with the PDF file
			- The link View is displayed*/ 
		 info("Share a pdf file activity");
		 acStream.addActivity(nameDrive,folderPath,folderDataTestPath, file,true,description);

		 info("Verify that the activity is shown on AS");
		 waitForAndGetElement(acStream.ELEMENT_ACTIVITY_DOCUMENT_MEDIA_TITLE.replace("${title}",file),3000,1);
		 info("View link is displayed");
		 waitForAndGetElement(acStream.ELEMENT_ACTIVITY_DOCUMENT_MEDIA_VIEW_LINK.replace("${nameFile}",file),3000,1);
			
		/*Step Number: 2
		*Step Name: Step 2. Check the Display area.
		*Step Description: 
			- Click on the link "View"
		*Input Data: 
			
		*Expected Outcome: 
			- The Dislay area shows:
			* The thumbnail of the content
			* The name of the content
			* A message: "The preview of this content is not available."
			* A button "Download"
			* if applicable, a "Open..." button
			*/ 
		 acStream.openPreviewMode(file,1,"");
		 waitForAndGetElement(docPrev.ELEMENT_PEVIEW_MODE_NOT_AVAIABLE_ICON,3000,1);
		 waitForAndGetElement(docPrev.ElEMENT_PREVIEW_MODE_NOT_AVAIABLE_MESSAGE_MANY_PAGES,3000,1);
		 waitForAndGetElement(docPrev.ELEMENT_PREVIEW_MODE_NOT_AVAIABLE_DOWNLOAD_BUTTON,3000,1);
		 waitForAndGetElement(docPrev.ELEMENT_PREVIEW_MODE_NOT_AVAIABLE_OPEN_IN_DESKTOP,3000,1);
		 docPrev.closeByClickCrossIcon();
 	}
	
	/**
	*<li> Case ID:112476.</li>
	*<li> Test Case Name: Check No preview mode in Sites Explorer</li>
	*<li> Precondition: - The shared file is not of the supported list of Preview.</li>
	*/
	@Test
	public  void test06_CheckNoPreviewModeInSE() {
		info("Test 06: Check No preview mode in Sites Explorer");
		file = attachFile.getAttachFileByArrayTypeRandom(24);
	    folderPath=siteExPath.getSiteExpPathByIndex(8);//sites/intranet/documents
		nameDrive=siteExDrive.getSiteExpDriveByIndex(0);//Collaboration
		/*Step Number: 1
		*Step Name: Step 1. Upload a PDF file from Sites Explorer.
		*Step Description: 
			- Connect to Intranet
			- Go to Administration/Content/Sites Explorer
			- Choose a Folder Path
			- Upload a PDF file (ex: big size more than 99 pages)

		*Input Data: 

		*Expected Outcome: 
			- The file PDF is uploaded and available under the folder path*/ 
		
		info("Go to Sites Explorer");
		navTool.goToSiteExplorer();
		info("Go to the drive:"+nameDrive);
		SEHome.openDrives();
		SEHome.selectADrive(nameDrive);

		info("Go to the folder:"+folderPath);
		SEHome.goToAFolder(folderPath);
		driver.navigate().refresh();

		info("Upload a file from:" + folderDataTestPath);
		SEHome.uploadFileWithDymanicPath(folderDataTestPath+ file);
		
		/*Step Number: 2
		*Step Name: Step 2. Check the display area.
		*Step Description: 
			- Click on the file's name
		*Input Data: 
			
		*Expected Outcome: 
			- The Dislay area shows:
			* The thumbnail of the content
			* The name of the content
			* A message: "The preview of this content is not available."
			* A button "Download"
			* if applicable, a "Open..." button
			*/ 
		info("Open the document in Site Explorer");
		SEHome.clickWebView();
		SEHome.selectFileExplorer();
		SEHome.selectAFile(file);
		
		info("Vefiry that no preview avaible for the content");
		waitForAndGetElement(SEHome.ELEMENT_CONTENT_THUMBNAIL,3000,1);
		waitForAndGetElement(SEHome.ELEMENT_CONTENT_NAME.replace("${nameFile}",file),3000,1);
		waitForAndGetElement(SEHome.ELEMENT_CONTENT_MESSAGE_TOO_MANY_PAGES,3000,1);
		waitForAndGetElement(SEHome.ELEMENT_CONTENT_DOWNLOAD_BUTTON,3000,1);
		waitForAndGetElement(SEHome.ELEMENT_CONTENT_OPEN_DESKTOP,3000,1);
 	}
	
	/**
	*<li> Case ID:112476.</li>
	*<li> Test Case Name: Check Preview mode in the Activity Stream</li>
	*<li> Precondition: - The shared file must be in the list of supported file types for Preview.</li>
	*/
	@Test
	public  void test07_CheckPreviewModeInAS() {
		info("Test 07: Check Preview mode in the Activity Stream");
		file = attachFile.getAttachFileByArrayTypeRandom(1);
	    folderPath=siteExPath.getSiteExpPathByIndex(7);//Collaboration/sites/intranet/documents
		nameDrive=siteExDrive.getSiteExpDriveByIndex(2);//General Drives
		String description = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1. Share an activity on the Intranet activity stream.
		*Step Description: 
			- Connect to Intranet Homepage
			- Share an activity (Attach file, video..)

		*Input Data: 

		*Expected Outcome: 
			- The activity is displayed on the stream
			- The link View is displayed*/ 
		
		info("Share a pdf file activity");
		acStream.addActivity(nameDrive,folderPath,folderDataTestPath, file,true,description);

		info("Verify that the activity is shown on AS");
		waitForAndGetElement(acStream.ELEMENT_ACTIVITY_DOCUMENT_MEDIA_TITLE.replace("${title}",file),3000,1);
	    info("View link is displayed");
	    waitForAndGetElement(acStream.ELEMENT_ACTIVITY_DOCUMENT_MEDIA_VIEW_LINK.replace("${nameFile}",file),3000,1);
		
		/*Step Number: 2
		*Step Name: Step 2. Check the Preview mode.
		*Step Description: 
			- Click on the link "View".
		*Input Data: 
			
		*Expected Outcome: 
			- The Preview screen is displayed. 
			- The Preview applies a shadow mask with high opacity over the browser window to display the document reader.
			- The background of the display area is black.
			*/
	    acStream.openPreviewMode(file,1,"");
	    info("Verify that the preview mode is shown");
		waitForAndGetElement(docPrev.ELEMENT_PREVIEW_MODE, 3000,1);
		info("The preview is shown successfully");
		
		Dimension dimen = waitForAndGetElement(docPrev.ELEMENT_PREVIEW_MODE,3000,0).getSize();
        int wd_prev =dimen.width;
        int wd_br=this.driver.manage().window().getSize().width;
 	    info("wd_pre:"+wd_prev);
 	    info("wd_br:"+wd_br);
		info("Check and Verify shadow mask display");
		docPrev.shadowMask(wd_prev,wd_br);
	    
		/*Step Number: 3
		*Step Name:Step 3. Close the Preview.
		*Step Description: 
			- Exit the Preview mode by hitting Esc key 
			or clicking the X cross available at the top right 
			or clicking the shadow mask (black background).
		*Input Data: 
			
		*Expected Outcome: 
			- The Preview mode is closed.
			*/
		info("Close Preview mode by pressing Esc key");
		docPrev.closeByPressECS();
		info("Close Preview mode by clicking on X cross");
		acStream.openPreviewMode(file,1,"");
		docPrev.closeByClickCrossIcon();
		info("Close Preview mode by clicking on background");
		acStream.openPreviewMode(file,1,"");
		docPrev.closeByClickBackground();
 	}
	
	/**
	*<li> Case ID:123337.</li>
	*<li> Test Case Name: Check reader display of a Web content file</li>
	*/
	@Test
	public  void test08_CheckReaderDisplayOfAWebContentFile() {
		info("Test 08: Check reader display of a Web content file");
		file = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		folderPath=siteExPath.getSiteExpPathByIndex(8);//sites/intranet/documents
		nameDrive=siteExDrive.getSiteExpDriveByIndex(0);//Collaboration
		String contentType = siteExContentType.getContentByIndex(0);
		/*Step Number: 1
		*Step Name: Step 1. Create a Web content file.
		*Step Description: 
			- Connect to Intranet
			- Go to Administration/Content/Sites Explorer
			- Select a Folder path
			- Create a WebContent File

		*Input Data: 

		*Expected Outcome: 
			- The Web Content file is created*/ 
		
		info("Go to Sites Explorer");
		navTool.goToSiteExplorer();
		info("Go to the drive:"+nameDrive);
		SEHome.openDrives();
		SEHome.selectADrive(nameDrive);

		info("Go to the folder:"+folderPath);
		SEHome.goToAFolder(folderPath);
		driver.navigate().refresh();
		
		info("Create a new document");
		SEHome.goToAddNewContent();
		SEHome.selectAContentType(contentType);
		CreNewDoc.addNewWebContent(file, file);
		CreNewDoc.saveAndClose();
		
		/*Step Number: 2
		*Step Name:Step 2. Check the activity stream.
		*Step Description: 
			- Go to the Intranet Homepage
		*Input Data: 
			
		*Expected Outcome: 
			- An activity with the Web content file is added to the stream.
			*/
		
		info("Go to Activity stream of Intranet Home page");
		hp.goToHomePage();
		info("Verify that the activity is shown on AS");
		waitForAndGetElement(acStream.ELEMENT_ACTIVITY_WEBCONTENT_TITLE.replace("${title}",file),3000,1);
		/*Step Number: 3
		*Step Name: Step 3. Open the file from the activity stream.
		*Step Description: 
			- Click on the file's name
		*Input Data: 
			
		*Expected Outcome: 
			- The reader of the file is displayed
			- Actions are displayed on the top bar
			- On the left area, a comment box is displayed
			*/
		
		acStream.openPreviewMode(file, "",2);
		
		info("Verify that the preview mode is shown");
		waitForAndGetElement(docPrev.ELEMENT_PREVIEW_MODE, 3000,1);
		info("The preview is shown successfully");
		
		info("The reader is displayed with a file content");
		waitForAndGetElement(docPrev.ELEMENT_READER_FILE_CONTENT_PAGE_1,3000,1);
		
		info("Actions are displayed on the top bar of the reader");
		waitForAndGetElement(docPrev.ELEMENT_ACTIONS_SIDERBAR_TOGGLE_BTN,1000,1);
		waitForAndGetElement(docPrev.ELEMENT_ACTIONS_SEARCH_BTN,1000,1);
		waitForAndGetElement(docPrev.ELEMENT_ACTIONS_ARROW_UP_BTN,1000,1);
		waitForAndGetElement(docPrev.ELEMENT_ACTIONS_ARROW_DOWN_BTN,1000,1);
		waitForAndGetElement(docPrev.ELEMENT_ACTIONS_PAGE_INPUT_NUMBER_BOX,1000,1);
		waitForAndGetElement(docPrev.ELEMENT_ACTIONS_ZOOM_OUT_BTN,1000,1);
		waitForAndGetElement(docPrev.ELEMENT_ACTIONS_ZOOM_IN_BTN,1000,1);
		waitForAndGetElement(docPrev.ELEMENT_ACTIONS_SCALE_SELECT_BOX,1000,1);
		waitForAndGetElement(docPrev.ELEMENT_ACTIONS_DOWNLOAD_BTN,1000,1);
		waitForAndGetElement(docPrev.ELEMENT_ACTIONS_FULLSCREEN_BTN,1000,1);
		waitForAndGetElement(docPrev.ELEMENT_ACTIONS_TOOLS_BTN,1000,1);
		
		info("Verify that the preview mode is shown");
		waitForAndGetElement(docPrev.ELEMENT_PREVIEW_MODE, 3000,1);
		info("The preview is shown successfully");
		
		info("The reader is displayed with a file content");
		waitForAndGetElement(docPrev.ELEMENT_READER_FILE_CONTENT_PAGE_1,3000,1);
		
		info("Actions are displayed on the top bar of the reader");
		waitForAndGetElement(docPrev.ELEMENT_ACTIONS_SIDERBAR_TOGGLE_BTN,1000,1);
		waitForAndGetElement(docPrev.ELEMENT_ACTIONS_SEARCH_BTN,1000,1);
		waitForAndGetElement(docPrev.ELEMENT_ACTIONS_ARROW_UP_BTN,1000,1);
		waitForAndGetElement(docPrev.ELEMENT_ACTIONS_ARROW_DOWN_BTN,1000,1);
		waitForAndGetElement(docPrev.ELEMENT_ACTIONS_PAGE_INPUT_NUMBER_BOX,1000,1);
		waitForAndGetElement(docPrev.ELEMENT_ACTIONS_ZOOM_OUT_BTN,1000,1);
		waitForAndGetElement(docPrev.ELEMENT_ACTIONS_ZOOM_IN_BTN,1000,1);
		waitForAndGetElement(docPrev.ELEMENT_ACTIONS_SCALE_SELECT_BOX,1000,1);
		waitForAndGetElement(docPrev.ELEMENT_ACTIONS_DOWNLOAD_BTN,1000,1);
		waitForAndGetElement(docPrev.ELEMENT_ACTIONS_FULLSCREEN_BTN,1000,1);
		waitForAndGetElement(docPrev.ELEMENT_ACTIONS_TOOLS_BTN,1000,1);
		
		info("On the right panel, the comment box are displayed");
		waitForAndGetElement(docPrev.ELEMENT_RIGHT_PANEL_COMMENT_INPUT_BOX,1000,1);
		waitForAndGetElement(docPrev.ELEMENT_RIGHT_PANEL_COMMENT_AREA_BOX_WITH_NO_COMMENT,1000,1);
		docPrev.closeByClickCrossIcon();
		
 	}
	
	/**
	*<li> Case ID:123220.</li>
	*<li> Test Case Name: Check uploaded video from Intranet Activity Stream</li>
	*<li> Precondition:A Video file is available under Document Folder reporsitory</li>
	*PENDING: CANNOT AUTOMATE FOR LAUNCHING VIDEO
	*/
	@Test(groups="pending")
	public  void test09_CheckUploadVideoFromAS() {
		info("Test 09: Check uploaded video from Intranet Activity Stream");
		file = attachFile.getAttachFileByArrayTypeRandom(55);
	    folderPath=siteExPath.getSiteExpPathByIndex(9);//Personal Documents/Documents
		String description = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1. Share a video file on the Intranet activity stream.
		*Step Description: 
			- Connect to Intranet Homepage
			- Select file icon
			- Go to Personal Documents/Documents and select a video file 
			- Click on the button Share

		*Input Data: 

		*Expected Outcome: 
			- Activity is shared on the stream
			- A reader display for the Video is displayed*/ 
		
		info("Share a pdf file activity");
		acStream.addActivity("",folderPath,folderDataTestPath, file,true,description);

		info("Verify that the activity is shown on AS");
		waitForAndGetElement(acStream.ELEMENT_ACTIVITY_DOCUMENT_MEDIA_TITLE.replace("${title}",file),3000,1);
	    info("View link is displayed");
	    waitForAndGetElement(acStream.ELEMENT_ACTIVITY_DOCUMENT_MEDIA_VIEW_LINK.replace("${nameFile}",file),3000,1);
		
		/*Step Number: 2
		*Step Name: Step 2. Play the shared video.
		*Step Description: 
			- Click on Play
		*Input Data: 
			
		*Expected Outcome: 
			- The video is launched
			*/
		
 	}
	
	/**
	*<li> Case ID:123221.</li>
	*<li> Test Case Name: Check uploaded video from Sites Explorer</li>
	*<li> Precondition:A Video file is available under Document Folder reporsitory</li>
	*PENDING: CANNOT AUTOMATE FOR LAUNCHING VIDEO
	*/
	@Test(groups="pending")
	public  void test10_CheckUploadedVideoFromSE() {
		info("Test 10: Check uploaded video from Sites Explorer");
		file = attachFile.getAttachFileByArrayTypeRandom(55);
		folderPath=siteExPath.getSiteExpPathByIndex(8);//sites/intranet/documents
		nameDrive=siteExDrive.getSiteExpDriveByIndex(0);//Collaboration
		/*Step Number: 1
		*Step Name: Step 1. Upload a Video file from Sites Explorer..
		*Step Description: 
			- Connect to Intranet
			- Go to Administration/Content/Sites Explorer
			- Select a Folder path
			- Upload a Video file 


		*Input Data: 

		*Expected Outcome: 
			- Activity is shared on the stream
			- A reader display for the Video is displayed*/ 
		info("Go to Sites Explorer");
		navTool.goToSiteExplorer();
		info("Go to the drive:"+nameDrive);
		SEHome.openDrives();
		SEHome.selectADrive(nameDrive);

		info("Go to the folder:"+folderPath);
		SEHome.goToAFolder(folderPath);
		driver.navigate().refresh();

		info("Upload a file from:" + folderDataTestPath);
		SEHome.uploadFileWithDymanicPath(folderDataTestPath+ file);
		
		hp.goToHomePage();
		info("Verify that the activity is shown on AS");
		waitForAndGetElement(acStream.ELEMENT_ACTIVITY_DOCUMENT_MEDIA_TITLE.replace("${title}",file),3000,1);
	    info("View link is displayed");
	    waitForAndGetElement(acStream.ELEMENT_ACTIVITY_DOCUMENT_MEDIA_VIEW_LINK.replace("${nameFile}",file),3000,1);
		/*Step Number: 2
		*Step Name: Step 2. Play the video.
		*Step Description: 
			- Click on Play
		*Input Data: 
			
		*Expected Outcome: 
			- The video is launched
			*/
		
 	}
	
	/**
	*<li> Case ID:123222.</li>
	*<li> Test Case Name: Check uploaded image from Sites Explorer</li>
	*/
	@Test
	public  void test11_CheckUploadedImageFromSE() {
		info("Test 11: Check uploaded image from Sites Explorer");
		file = attachFile.getAttachFileByArrayTypeRandom(26);
		folderPath=siteExPath.getSiteExpPathByIndex(8);//sites/intranet/documents
		nameDrive=siteExDrive.getSiteExpDriveByIndex(0);//Collaboration
		/*Step Number: 1
		*Step Name: Step 1. Upload an image from Sites Explorer.
		*Step Description: 
			- Connect to Intranet
			- Go to Administration/Content/Sites Explorer
			- Select a Folder path
			- Upload an Image 


		*Input Data: 

		*Expected Outcome: 
			- The image file is stored under the folder*/ 
		
		info("Go to Sites Explorer");
		navTool.goToSiteExplorer();
		info("Go to the drive:"+nameDrive);
		SEHome.openDrives();
		SEHome.selectADrive(nameDrive);

		info("Go to the folder:"+folderPath);
		SEHome.goToAFolder(folderPath);
		driver.navigate().refresh();

		info("Upload a file from:" + folderDataTestPath);
		SEHome.uploadFileWithDymanicPath(folderDataTestPath+ file);
		
		
		/*Step Number: 2
		*Step Name: Step 2. Check display of the image.
		*Step Description: 
			- Click on the link "View" or click on the file's name
		*Input Data: 
			
		*Expected Outcome: 
			- The display area of the image is displayed
			*/
		hp.goToHomePage();
		info("Verify that the activity is shown on AS");
		waitForAndGetElement(acStream.ELEMENT_ACTIVITY_DOCUMENT_MEDIA_TITLE.replace("${title}",file),3000,1);
	    info("View link is displayed");
	    waitForAndGetElement(acStream.ELEMENT_ACTIVITY_DOCUMENT_MEDIA_VIEW_LINK.replace("${nameFile}",file),3000,1);
	    acStream.openPreviewMode(file, 1,"");
	    info("Verify that the preview mode is shown");
		waitForAndGetElement(docPrev.ELEMENT_PREVIEW_MODE, 3000,1);
		info("The preview is shown successfully");
		docPrev.closeByPressECS();
		
 	}
	
	/**
	*<li> Case ID:123223.</li>
	*<li> Test Case Name: Check the embedded media via the display area on activity stream</li>
	*<li> Preconditions: 
	*- One new space has been already created. 
	*- Share the external embedded medias content:
	*YouTube, Vimeo, Dailymotion, Slideshare, Flickr</li>
	*PENDING: CANNOT AUTOMATE FOR CHECKING LAUCHNING OF THE VIDEO
	*/
	@Test(groups="pending")
	public  void test12_CheckEmbeddedMediaViaDisplayAreaOnAS() {
		info("Test 12: Check the embedded media via the display area on activity stream");
		String videoLink = videoLinksFile.getVideoLinksByArrayTypeRandom(1);
		folderPath=siteExPath.getSiteExpPathByIndex(8);//sites/intranet/documents
		nameDrive=siteExDrive.getSiteExpDriveByIndex(0);//Collaboration
		String description = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1. Connect to activity stream of Intranet or the space.
		*Step Description: 
			- Log in with an account
			- Go to the space if testing on the space activity stream

		*Input Data: 

		*Expected Outcome: 
			- The video  is displayed on the activity stream
			- The link "View" is displayed*/ 
		
		info("Share a pdf file activity");
		acStream.addActivity(true,description, true, videoLink);

		info("Verify that the activity is shown on AS");
		waitForAndGetElement(acStream.ELEMENT_ACTIVITY_AUDIO_VIDEO_TITLE.replace("${link}",videoLink),3000,1);
	    info("View link is displayed");
	    waitForAndGetElement(acStream.ELEMENT_ACTIVITY_EMBBED_MEDIA_VIEW_LINK.replace("${linkFile}",videoLink),3000,1);
		
		
		/*Step Number: 2
		*Step Name: Step 2. Open the Preview mode.
		*Step Description: 
			- Click on the file name or View link from the activity
		*Input Data: 
			
		*Expected Outcome: 
			-The video is displayed on the display area
			*/
		acStream.openPreviewMode(videoLink, 3,"");
		info("Verify that the preview mode is shown");
		waitForAndGetElement(docPrev.ELEMENT_PREVIEW_MODE, 3000,1);
		info("The preview is shown successfully");
		
		/*Step Number: 3
		*Step Name: Step 3. Play the video.
		*Step Description: 
			- Click on Play
		*Input Data: 
			
		*Expected Outcome: 
			- The video is launched
			*/
		
 	}
	
	/**
	*<li> Case ID:123224.</li>
	*<li> Test Case Name: Check uploaded image from the Intranet Activity Stream</li>
	*<li> Preconditions: 
	*- An image file is available under the Personal Documents/Documents</li>
	*/
	@Test
	public  void test13_CheckUploadedImageFromAS() {
		info("Test 13: Check uploaded image from the Intranet Activity Stream");
		file = attachFile.getAttachFileByArrayTypeRandom(26);
		folderPath=siteExPath.getSiteExpPathByIndex(7);//Collaboration/sites/intranet/documents
		nameDrive=siteExDrive.getSiteExpDriveByIndex(2);//General Drives
		String description = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1. Upload an image from Activity Stream.
		*Step Description: 
			- Connect to Intranet
			- From Share area, click on the button "File"
			- Select an Image
 
		*Input Data: 

		*Expected Outcome: 
			- An activity with the image file is displayed on the stream
			*/ 
		info("Share a pdf file activity");
		acStream.addActivity(nameDrive,folderPath,folderDataTestPath, file,true,description);

		info("Verify that the activity is shown on AS");
		waitForAndGetElement(acStream.ELEMENT_ACTIVITY_DOCUMENT_MEDIA_TITLE.replace("${title}",file),3000,1);
	    info("View link is displayed");
	    waitForAndGetElement(acStream.ELEMENT_ACTIVITY_DOCUMENT_MEDIA_VIEW_LINK.replace("${nameFile}",file),3000,1);
		
	    /*Step Number: 2
		*Step Name: Step 2. Open the Preview mode.
		*Step Description: 
			- Click on the link "View" or click on the file's name
		*Input Data: 
			
		*Expected Outcome: 
			- The display area of the image is displayed
			*/
	    acStream.openPreviewMode(file,1,"");
		info("Verify that the preview mode is shown");
		waitForAndGetElement(docPrev.ELEMENT_PREVIEW_MODE, 3000,1);
		info("The preview is shown successfully");
		docPrev.closeByPressECS();
 	}
	
	/**
	*<li> Case ID:123225.</li>
	*<li> Test Case Name: Check preview mode for not-supported media file on activity stream</li>
	*<li> Preconditions: 
	*- One new space has been already created. 
	*- Share a not-supported embedded media on activity stream of Intranet or the space.</li>
	*/
	@Test
	public  void test14_CheckPreviewModeForNotSupportedMediaFileFromAS() {
		info("Test 14: Check preview mode for not-supported media file on activity stream");
		file = attachFile.getAttachFileByArrayTypeRandom(65);
		folderPath=siteExPath.getSiteExpPathByIndex(7);//Collaboration/sites/intranet/documents
		nameDrive=siteExDrive.getSiteExpDriveByIndex(2);//General Drives
		String description = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1. Connect to activity stream of  Intranet or the space.
		*Step Description: 
			- Log in with a user
			- Go to the activity stream of Intranet or the space
 
		*Input Data: 

		*Expected Outcome: 
			- The not-supported file media  is displayed on the activity stream
			- The "View" link is not displayed on the activity 

			*/ 
		
		info("Share a pdf file activity");
		acStream.addActivity(nameDrive,folderPath,folderDataTestPath, file,true,description);

		info("Verify that the activity is shown on AS");
		waitForAndGetElement(acStream.ELEMENT_ACTIVITY_DOCUMENT_MEDIA_TITLE.replace("${title}",file),3000,1);
	    info("View link is not displayed");
	    waitForElementNotPresent(acStream.ELEMENT_ACTIVITY_DOCUMENT_MEDIA_VIEW_LINK.replace("${nameFile}",file),3000,1);
		
	    /*Step Number: 2
		*Step Name: Step 2. Check the Preview mode.
		*Step Description: 
			- Click on the file name of the media
		*Input Data: 
			
		*Expected Outcome: 
			- The preview is not shown
			- user is redirected to SE
			*/
		acStream.openPreviewMode(file, "",1);
		info("Verify that the preview mode is shown");
		waitForElementNotPresent(docPrev.ELEMENT_PREVIEW_MODE, 3000,1);
		info("user is redirected to SE");
		waitForAndGetElement(SEHome.ELEMENT_CONTENT_NAME.replace("${nameFile}",file),3000,1);
 	}
	
	/**
	*<li> Case ID:123226.</li>
	*<li> Test Case Name: Check Download link of embedded medias on the activity stream</li>
	*<li> Preconditions: 
	*-One space has been already created. 
	*- Share an embedded media on the activity stream of Intranet or the space.</li>
	*PENDING: EMBEDDED MEDIA HAS NOT DOWNLOAD LINK. THE TEST CASE IS NOT CORRECT.
	*/
	@Test(groups="pending")
	public  void test15_CheckDownloadlinkOfEmbeddedMediasOnAS() {
		info("Test 15: Check Download link of embedded medias on the activity stream");
		/*Step Number: 1
		*Step Name: Step 1. Connect to Intranet/Space activity.
		*Step Description: 
			- Login with an account
			- Go to the space if testing on space activity stream
 
		*Input Data: 

		*Expected Outcome: 
			- The embedded medias is displayed on the activity stream

			*/ 
		
		/*Step Number: 2
		*Step Name: Step 2. Check the Download link.
		*Step Description: 
			- Click on the "View" link.
		*Input Data: 
			
		*Expected Outcome: 
			- The display area is displayed.
			- A Download link is displayed while previewing the media from the Activity Stream.

			*/
		
 	}
	
	/**
	*<li> Case ID:123478.</li>
	*<li> Test Case Name: Check the display of the Comment area in Display area in Activity Stream</li>
	*/
	@Test
	public  void test16_CheckDisplayOfCommentAreaInDisplayAreaInAS() {
		info("Test 16: Check the display of the Comment area in Display area in Activity Stream");
		file = attachFile.getAttachFileByArrayTypeRandom(1);
		folderPath=siteExPath.getSiteExpPathByIndex(7);//Collaboration/sites/intranet/documents
		nameDrive=siteExDrive.getSiteExpDriveByIndex(2);//General Drives
		String description = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1. Share a file activity.
		*Step Description: 
			- Connect to Intranet Homepage
			- Share an activity and attach (file, video..)

 
		*Input Data: 

		*Expected Outcome: 
			- The activity is displayed on the stream

			*/ 
		info("Share a pdf file activity");
		acStream.addActivity(nameDrive,folderPath,folderDataTestPath, file,true,description);

		info("Verify that the activity is shown on AS");
		waitForAndGetElement(acStream.ELEMENT_ACTIVITY_DOCUMENT_MEDIA_TITLE.replace("${title}",file),3000,1);
		
		/*Step Number: 2
		*Step Name: Step 2. Open the Preview mode.
		*Step Description: 
			- Click on the name of the attachment
		*Input Data: 
			
		*Expected Outcome: 
			- The Display area is shown
			- The  Comments area is displayed beside the display area

			*/
		acStream.openPreviewMode(file,"",1);
		info("Verify that the preview mode is shown");
		waitForAndGetElement(docPrev.ELEMENT_PREVIEW_MODE, 3000,1);
		info("Verify that the comment area is shown beside the display area");
		waitForAndGetElement(docPrev.ELEMENT_RIGHT_PANEL_COMMENT_AREA_BOX_WITH_NO_COMMENT,3000,1);
		docPrev.closeByPressECS();
		
 	}
	
	/**
	*<li> Case ID:123479.</li>
	*<li> Test Case Name: Check the collapse/expand comments area</li>
	*/
	@Test
	public  void test17_CheckCollapseExpandCommentArea() {
		info("Test 17: Check the collapse/expand comments area");
		file = attachFile.getAttachFileByArrayTypeRandom(1);
		folderPath=siteExPath.getSiteExpPathByIndex(7);//Collaboration/sites/intranet/documents
		nameDrive=siteExDrive.getSiteExpDriveByIndex(2);//General Drives
		String description = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1. Share a file activity.
		*Step Description: 
			- Connect to Intranet Homepage
			- Share an activity and attach (file, video..)
 
		*Input Data: 

		*Expected Outcome: 
			- The activity is displayed on the stream
			*/ 
		
		info("Share a pdf file activity");
		acStream.addActivity(nameDrive,folderPath,folderDataTestPath, file,true,description);

		info("Verify that the activity is shown on AS");
		waitForAndGetElement(acStream.ELEMENT_ACTIVITY_DOCUMENT_MEDIA_TITLE.replace("${title}",file),3000,1);
		
		/*Step Number: 2
		*Step Name: Step 2. Show the Preview mode.
		*Step Description: 
			- Click on the name of the attachment
		*Input Data: 
			
		*Expected Outcome: 
			- The Display area is shown
			- The  Comments area is displayed beside the display area.
			- An arrow is displayed to collapse/expand the comment area
			- The comment area is expanded
			*/
		acStream.openPreviewMode(file,"",1);
		info("Verify that the preview mode is shown");
		waitForAndGetElement(docPrev.ELEMENT_PREVIEW_MODE, 3000,1);
		
		/*Step Number: 3
		*Step Name: Step 3. Hide the Comments area
		*Step Description: 
			- Click on the arrow
		*Input Data: 
			
		*Expected Outcome: 
			The comments area is collapsed.
			*/
		
		/*Step Number: 4
		*Step Name: Step 4. Open the Comments area
		*Step Description: 
			- Click again on the arrow
		*Input Data: 
			
		*Expected Outcome: 
			The comments area is expanded.
			*/
		int wd_comment_old = waitForAndGetElement(docPrev.ELEMENT_COMMENT_AREA,3000,0).getSize().width;
		info("Hide comment area");
		docPrev.collapseCommentArea();
		info("Verify that the comment area is hided");
		waitForElementNotPresent(docPrev.ELEMENT_COMMENT_AREA,2000,1);
		info("Show comment area");
		docPrev.expandCommentArea();
		info("Verify that the comment area is shown again");
		waitForAndGetElement(docPrev.ELEMENT_COMMENT_AREA,3000,1);
		int wd_comment_new = waitForAndGetElement(docPrev.ELEMENT_COMMENT_AREA,3000,0).getSize().width;
		info("Close The preview mode");
		docPrev.closeByPressECS();
		
		if(wd_comment_new==wd_comment_old){
			assert true;
			info("The comment area isnot resized.");
		}else assert false:"The comment area is resized, it is old_size/new_size:"+wd_comment_old+"/"+wd_comment_new;
		
 	}
	
	/**
	*<li> Case ID:123480.</li>
	*<li> Test Case Name: Add a new comment on the Comment area in Display area</li>
	*/
	@Test
	public  void test18_AddNewCommentOnCommentAreaInAS() {
		info("Test 18: Add a new comment on the Comment area in Display area");
		file = attachFile.getAttachFileByArrayTypeRandom(1);
		folderPath=siteExPath.getSiteExpPathByIndex(7);//Collaboration/sites/intranet/documents
		nameDrive=siteExDrive.getSiteExpDriveByIndex(2);//General Drives
		String description = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String comment = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1. Share a file activity.
		*Step Description: 
			- Connect to Intranet Homepage
			- Share an activity and attach (file, video..)
 
		*Input Data: 

		*Expected Outcome: 
			- The activity is displayed on the stream
			*/ 
		info("Share a pdf file activity");
		acStream.addActivity(nameDrive,folderPath,folderDataTestPath, file,true,description);

		info("Verify that the activity is shown on AS");
		waitForAndGetElement(acStream.ELEMENT_ACTIVITY_DOCUMENT_MEDIA_TITLE.replace("${title}",file),3000,1);
		
		/*Step Number: 2
		*Step Name: Step 2. Show the Preview mode.
		*Step Description: 
			- Click on the name of the attachment
		*Input Data: 
			
		*Expected Outcome: 
			- The Display area is shown
			- The  Comments area is displayed beside the display area
			*/
		acStream.openPreviewMode(file,"",1);
		info("Verify that the preview mode is shown");
		waitForAndGetElement(docPrev.ELEMENT_PREVIEW_MODE, 3000,1);
		
		info("Verify that the comment area is shown beside the display area");
		waitForAndGetElement(docPrev.ELEMENT_RIGHT_PANEL_COMMENT_AREA_BOX_WITH_NO_COMMENT,3000,1);
		
		/*Step Number: 3
		*Step Name: Step 3. Add a comment.
		*Step Description: 
			- Add a comment on the writting box
			- Click "Enter" from the Keyboard
		*Input Data: 
			
		*Expected Outcome: 
			- The comment is added in the stream of comments
			*/
		
		info("Add a comment to Comment Area");
		docPrev.addComment(comment,1);
		waitForAndGetElement(docPrev.ELEMENT_COMMENT_CONTENT.replace("${text}",comment),3000,1);
		
		info("Verify that added comment is shown on Comment Area is only one");
		int count = driver.findElements(By.xpath(docPrev.ELEMENT_COMMENT_CONTENT.replace("${text}",comment))).size();
		info("Close The preview mode");
		docPrev.closeByPressECS();
			
		if(count==1){
			assert true;
			info("Added comment is shown on Comment Area is only one");
		}else assert false:"Added comment is shown on Comment Area isnot only one, it is:"+count;
		
		/*Step Number: 4
		*Step Name: Step 4. Close the Display area.
		*Step Description: 
			- Close the Display area
		*Input Data: 
			
		*Expected Outcome: 
			- Activity is displayed on the stream
			- Comment is displayed in the activity
			*/
		acStream.checkCommentOfActivity(file, comment);
 	}
	
	/**
	*<li> Case ID:123481.</li>
	*<li> Test Case Name: Check the Like option</li>
	*/
	@Test
	public  void test19_CheckLikeOption() {
		info("Test 19: Check the Like option");
		file = attachFile.getAttachFileByArrayTypeRandom(1);
		folderPath=siteExPath.getSiteExpPathByIndex(7);//Collaboration/sites/intranet/documents
		nameDrive=siteExDrive.getSiteExpDriveByIndex(2);//General Drives
		String description = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1. Share a file activity.
		*Step Description: 
			- Connect to Intranet Homepage
			- Share an activity and attach (file, video..)

 
		*Input Data: 

		*Expected Outcome: 
			- The activity is displayed on the stream
			*/ 
		info("Share a pdf file activity");
		acStream.addActivity(nameDrive,folderPath,folderDataTestPath, file,true,description);

		info("Verify that the activity is shown on AS");
		waitForAndGetElement(acStream.ELEMENT_ACTIVITY_DOCUMENT_MEDIA_TITLE.replace("${title}",file),3000,1);
		/*Step Number: 2
		*Step Name: Step 2. Show the Preview mode.
		*Step Description: 
			- Click on the name of the attachment
		*Input Data: 
			
		*Expected Outcome: 
			- The display area and the Comments area are displayed 
			- The icon Like is displayed
			*/
		
		acStream.openPreviewMode(file,"",1);
		info("Verify that the preview mode is shown");
		waitForAndGetElement(docPrev.ELEMENT_PREVIEW_MODE, 3000,1);
		
		info("Verify that The icon Like is displayed");
		waitForAndGetElement(docPrev.ELEMENT_COMMENT_AREA_TOP_LIKE_ICON,3000,1);
		
		/*Step Number: 3
		*Step Name: Step 3. Like in the Preview mode.
		*Step Description: 
			- Click on the icon Like
		*Input Data: 
			
		*Expected Outcome: 
			- A like is added to the activity (+1 on the number of Like)
			*/
		
		info("click on Like icon");
		click(docPrev.ELEMENT_COMMENT_AREA_TOP_LIKE_ICON);
		Utils.pause(2000);
		info("Verify that the number of like is +1");
		waitForAndGetElement(docPrev.ELEMENT_COMMENT_AREA_TOP_LIKE_NUMBER.replace("${number}","1"),3000,1);
		
		
		/*Step Number: 4
		*Step Name: Step 4. Check the activity.
		*Step Description: 
			- Close the Display area
		*Input Data: 
			
		*Expected Outcome: 
			- The activity is displayed on the stream
			- A like is added to the activity
			*/
		info("Close The preview mode");
		docPrev.closeByPressECS();
		Utils.pause(2000);
		info("Verify that the number of like is +1");
		String num= waitForAndGetElement(acStream.ELEMENT_LIKE_NUMBER.replace("${title}",file),3000,1).getText();
		info("num:"+num);
		if(num.equals(" 1")) assert true;
		else assert false:"the number of like is incorrect";
		/*Step Number: 5
		*Step Name: Step 5. Dislike in the Preview mode.
		*Step Description: 
			- Click again on the name of the attachment.
			- Click again on the Like icon
		*Input Data: 
			
		*Expected Outcome: 
			- The number of like is decreased (-1 on the number of Like)
			*/
		acStream.openPreviewMode(file,"",1);
		info("Verify that the preview mode is shown");
		waitForAndGetElement(docPrev.ELEMENT_PREVIEW_MODE, 3000,1);
		info("click on Like icon");
		click(docPrev.ELEMENT_COMMENT_AREA_TOP_LIKE_ICON);
		Utils.pause(2000);
		info("Verify that the number of like is -1");
		waitForAndGetElement(docPrev.ELEMENT_COMMENT_AREA_TOP_LIKE_NUMBER.replace("${number}","0"),3000,1);
		
		info("Close The preview mode");
		docPrev.closeByPressECS();
		info("Verify that the number of like is -1");
		String num1=waitForAndGetElement(acStream.ELEMENT_LIKE_NUMBER.replace("${title}",file),3000,1).getText();
		if(num1.equals(" 0")) assert true;
		else assert false:"the number of like is incorrect";
		info("num1:"+num1);
 	}
	
	
}
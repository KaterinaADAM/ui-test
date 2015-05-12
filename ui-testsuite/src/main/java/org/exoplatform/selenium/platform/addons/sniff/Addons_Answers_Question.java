package org.exoplatform.selenium.platform.addons.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.platform.HomePagePlatform;
import org.exoplatform.selenium.platform.ManageLogInOut;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.answer.AnswerHomePage;
import org.exoplatform.selenium.platform.answer.AnswerCategoryManagement;
import org.exoplatform.selenium.platform.answer.QuestionManagement;
import org.exoplatform.selenium.platform.answer.AnswerCategoryManagement.actionCategoryOption;
import org.exoplatform.selenium.platform.answer.QuestionManagement.actionQuestionOption;
import org.exoplatform.selenium.platform.objectdatabase.common.AttachmentFileDatabase;
import org.exoplatform.selenium.platform.objectdatabase.common.TextBoxDatabase;
import org.exoplatform.selenium.platform.objectdatabase.user.UserDatabase;
import org.openqa.selenium.By;
import org.testng.annotations.*;


public class Addons_Answers_Question extends PlatformBase {
	HomePagePlatform hp;
	ManageLogInOut magAc;
	TextBoxDatabase txData;
	QuestionManagement qMang;
	AnswerCategoryManagement cMang;
	AnswerHomePage aHome;
	AttachmentFileDatabase fData;
	UserDatabase userData;
	ManageAlert alert;
	Button button;

	@BeforeClass
	public void setUpBeforeTest() throws Exception{
		initSeleniumTest();
		getDefaultUserPass(userDataFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		driver.get(baseUrl);
		magAc = new ManageLogInOut(driver);
		hp = new HomePagePlatform(driver);
		qMang = new QuestionManagement(driver);
		cMang = new AnswerCategoryManagement(driver);
		button = new Button(driver);
		aHome=new AnswerHomePage(driver);
		alert = new ManageAlert(driver);
		txData = new TextBoxDatabase();
		fData = new AttachmentFileDatabase();
		userData = new UserDatabase();
		magAc.signIn(DATA_USER1, DATA_PASS);
		userData.setUserData(userDataFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		txData.setContentData(texboxFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlContent);
		fData.setAttachFileData(attachmentFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
	}

	@AfterClass
	public void afterTest(){
		driver.manage().deleteAllCookies();
		driver.quit();
	}
	
	@AfterMethod
	public void afterMethod(){
		switchToParentWindow();
		magAc.signOut();
	}
	@BeforeMethod
	public void setUpBeforeMethod() throws Exception{
		magAc.signIn(DATA_USER1, DATA_PASS);
	}
	/**
	 * Case ID:116812.
	 * Test Case Name: Vote question.
	 * Pre-Condition: 
	 * Post-Condition: 
	 */
	@Test(priority=1)
	public  void test01_VoteQuestion() {
		String title = txData.getContentByArrayTypeRandom(1)+"116812";
		String content = txData.getContentByArrayTypeRandom(1)+"116812";
		info("Create question");
		hp.goToAnswer();
		qMang.goToSubmitQuestion();
		qMang.inputDataToQuestionForm(title, content, null, null);
		click(qMang.ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON);
		click(button.ELEMENT_OK_BUTTON_LINK);
		waitForAndGetElement(By.xpath(aHome.ELEMENT_QUESTION_LIST_ITEM.replace("$question", title)));

		info("Test 1: Vote question");
		/*Step Number: 1
		 *Step Name: -
		 *Step Description: 
			Step 1: Vote Question
		 *Input Data: 
			- Open a question
			- Vote Question by select number of star
		 *Expected Outcome: 
			- Question is voted successful.The number of stars are selected set yellow.*/
		click(By.xpath(aHome.ELEMENT_QUESTION_LIST_ITEM.replace("$question", title)));
		qMang.rateQuestion(4);
		
		info("clear data");
		qMang.goToActionOfQuestionFromMoreAction(actionQuestionOption.DELETE);
		waitForAndGetElement(qMang.ELEMENT_QUESTION_CONFIRM_DELETE);
		click(qMang.ELEMENT_QUESTION_DELETE_FORM_OK_BUTTON);
		waitForElementNotPresent(By.xpath(aHome.ELEMENT_QUESTION_LIST_ITEM.replace("$question", title)));
	}

	/**
	 * Case ID:116828.
	 * Test Case Name: Add a question.
	 * Case ID:116829.
	 * Test Case Name: Edit a question.
	 * Case ID:116830.
	 * Test Case Name: Delete a question.
	 * Pre-Condition: 
	 * Post-Condition: 
	 */
	@Test(priority=2)
	public  void test02_03_04_AddEditDeleteAQuestion() {
		String title = txData.getContentByArrayTypeRandom(1)+"116828";
		String content = txData.getContentByArrayTypeRandom(1)+"116828";
		String newtitle = txData.getContentByArrayTypeRandom(1)+"n116828";
		String newcontent = txData.getContentByArrayTypeRandom(1)+"n116828";
		String link = fData.getAttachFileByArrayTypeRandom(1);

		info("Test 3: Add a question");
		/*Step Number: 1
		 *Step Name: Add a question
		 *Step Description: 
			- Select 1 category and click on Submit question
			- Put values
			- Save
		 *Input Data: 

		 *Expected Outcome: 
			- Question is added new and shown in selected category*/ 
		hp.goToAnswer();
		qMang.goToSubmitQuestion();
		qMang.inputDataToQuestionForm(title, content, null, "TestData/" + link);
		click(qMang.ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON);
		click(button.ELEMENT_OK_BUTTON_LINK);
		waitForAndGetElement(By.xpath(aHome.ELEMENT_QUESTION_LIST_ITEM.replace("$question", title)));

		info("Test 4: Edit a question");
		/*Step number: 2
		 *Step Name: Edit a question
		 *Step Description: 
			- Right click on this question, select Edit
			- Put values into fields
			- Save
		 *Input Data: 

		 *Expected Outcome: 
			- Edit Question form is shown
			- This question is updated successfully.*/ 
		qMang.goToActionOfQuestionByRightClick(title, actionQuestionOption.EDIT);
		qMang.inputDataToQuestionForm(newtitle, newcontent, null, "TestData/" + link);
		click(qMang.ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON);
		waitForElementNotPresent(By.xpath(aHome.ELEMENT_QUESTION_LIST_ITEM.replace("$question", title)));
		waitForAndGetElement(By.xpath(aHome.ELEMENT_QUESTION_LIST_ITEM.replace("$question", newtitle)));

		info("Test 5: Delete a question");
		/*Step number: 2
		 *Step Name: Delete a question
		 *Step Description: 
			- Right click on a question and select Delete
		 *Input Data: 

		 *Expected Outcome: 
			- Question is deleted and disappear in Answers page*/ 
		qMang.deleteQuestion(newtitle);
	}

	/**
	 * Case ID:116831.
	 * Test Case Name: Move a question.
	 * Pre-Condition: 
	 * Post-Condition: 
	 */
	@Test(priority=3)
	public  void test06_MoveAQuestion() {
		String title = txData.getContentByArrayTypeRandom(1)+"116831";
		String content = txData.getContentByArrayTypeRandom(1)+"116831";
		String paCat1 = txData.getContentByArrayTypeRandom(1)+"p1116831";
		String paDes1 = txData.getContentByArrayTypeRandom(1)+"p1116831";

		String paCat2 = txData.getContentByArrayTypeRandom(1)+"p2116831";
		String paDes2 = txData.getContentByArrayTypeRandom(1)+"p2116831";

		info("Create parent categories");
		hp.goToAnswer();
		cMang.goToActionOfCategoryFromActionBar(actionCategoryOption.ADD);
		cMang.inputDataToSettingTab(paCat1, null, paDes1, null, null, null);
		click(cMang.ELEMENT_CATEGORY_ADD_FORM_SAVE_BUTTON);
		waitForAndGetElement(cMang.ELEMENT_CATEGORY_LIST_ITEM.replace("$category", paCat1));

		cMang.goToActionOfCategoryFromActionBar(actionCategoryOption.ADD);
		cMang.inputDataToSettingTab(paCat2, null, paDes2, null, null, null);
		click(cMang.ELEMENT_CATEGORY_ADD_FORM_SAVE_BUTTON);
		waitForAndGetElement(cMang.ELEMENT_CATEGORY_LIST_ITEM.replace("$category", paCat2));

		info("Test 6: Move a question");
		/*Step Number: 1
		 *Step Name: Add a question
		 *Step Description: 
			- Select 1 category and click on Submit question
			- Put values
			- Save
		 *Input Data: 

		 *Expected Outcome: 
			- Question is added new and shown in selected category*/
		hp.goToAnswer();
		cMang.goToActionOfCategoryFromRightClick(paCat1, actionCategoryOption.SUBMITQUESTION);
		qMang.inputDataToQuestionForm(title, content, null, null);
		click(qMang.ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON);
		click(button.ELEMENT_OK_BUTTON_LINK);
		
		/*Step number: 2
		 *Step Name: Move a question
		 *Step Description: 
			- Right click on question and select Move to 
			- Select destination category
		 *Input Data: 

		 *Expected Outcome: 
			- Question is moved to destination category*/ 
		click(cMang.ELEMENT_CATEGORY_LIST_ITEM.replace("$category", paCat1));
		qMang.goToActionOfQuestionByRightClick(title, actionQuestionOption.MOVE);
		cMang.moveCategory(paCat2);
		aHome.goToHomeCategory();
		click(cMang.ELEMENT_CATEGORY_LIST_ITEM.replace("$category", paCat2));
		waitForAndGetElement(By.xpath(aHome.ELEMENT_QUESTION_LIST_ITEM.replace("$question", title)));
		aHome.goToHomeCategory();
		click(cMang.ELEMENT_CATEGORY_LIST_ITEM.replace("$category", paCat1));
		waitForElementNotPresent(By.xpath(aHome.ELEMENT_QUESTION_LIST_ITEM.replace("$question", title)));
		
		info("Clear data");
		aHome.goToHomeCategory();
		click(cMang.ELEMENT_CATEGORY_LIST_ITEM.replace("$category", paCat1));
		cMang.deleteCategory(paCat1);
		click(cMang.ELEMENT_CATEGORY_LIST_ITEM.replace("$category", paCat2));
		cMang.deleteCategory(paCat2);
	}

	/**
	 * Case ID:116833.
	 * Test Case Name: Edit a question in Manage Answer form.
	 * Case ID:116834.
	 * Test Case Name: Delete a question in Manage Question form.
	 * Pre-Condition: 
	 * Post-Condition: 
	 */
	@Test(priority=4)
	public  void test05_06_EditDeleteAQuestionInManageAnswerForm() {
		String title = txData.getContentByArrayTypeRandom(1)+"116833";
		String content = txData.getContentByArrayTypeRandom(1)+"116833";
		String newtitle = txData.getContentByArrayTypeRandom(1)+"n116833";
		String newcontent = txData.getContentByArrayTypeRandom(1)+"n116833";
		String link = fData.getAttachFileByArrayTypeRandom(1);

		info("Add a question");
		hp.goToAnswer();
		qMang.goToSubmitQuestion();
		qMang.inputDataToQuestionForm(title, content, null, "TestData/" + link);
		click(qMang.ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON);
		click(button.ELEMENT_OK_BUTTON_LINK);
		waitForAndGetElement(By.xpath(aHome.ELEMENT_QUESTION_LIST_ITEM.replace("$question", title)));

		info("Test 7: Edit a question in Manage Answer form");
		/*Step Number: 1
		 *Step Name: Add a question
		 *Step Description: 
			- Select 1 category and click on Submit question
			- Put values
			- Save
		 *Input Data: 

		 *Expected Outcome: 
			- Question is added new and shown in selected category*/

		/*Step number: 2
		 *Step Name: -Answer a question
		 *Step Description: 
			- Click Manage Question in the main menu
			- Select All Question tab
			- Click Edit on question want to edit
			- Put values
			- Save
		 *Input Data: 

		 *Expected Outcome: 
			- This question is updated successfully*/ 
		qMang.goToManageQuestionForm();
		qMang.goToEditQuestionFromManageQuestionForm(title);
		qMang.inputDataToQuestionForm(newtitle, newcontent, null, "TestData/" + link);
		click(qMang.ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON);
		waitForElementNotPresent(By.xpath(qMang.ELEMENT_MANAGE_QUESTION_EDIT_QUESTION.replace("$question", title)));
		waitForAndGetElement(By.xpath(qMang.ELEMENT_MANAGE_QUESTION_EDIT_QUESTION.replace("$question", newtitle)));

		info("Test 8: Delete a question in Manage Question form");
		/*Step number: 2
		 *Step Name: Delete a question
		 *Step Description: 
			- Select All Question tab
			- Click Delete on question want to delete
		 *Input Data: 

		 *Expected Outcome: 
			Question is deleted successfully*/ 
		qMang.goToDeleteQuestionFromManageQuestionForm(newtitle);
		waitForAndGetElement(qMang.ELEMENT_QUESTION_CONFIRM_DELETE);
		click(qMang.ELEMENT_QUESTION_DELETE_FORM_OK_BUTTON);
		waitForElementNotPresent(By.xpath(qMang.ELEMENT_MANAGE_QUESTION_DELETE_QUESTION.replace("$question", newtitle)));
		button.close();
	}


	/**
	 * Case ID:116835.
	 * Test Case Name: Activate / Deactivate a question.
	 * Pre-Condition: 
	 * Post-Condition: 
	 */
	@Test(priority=5)
	public  void test08_ActivateDeactivateAQuestion() {
		String title = txData.getContentByArrayTypeRandom(1)+"116835";
		String content = txData.getContentByArrayTypeRandom(1)+"116835";
		info("Test 9: Activate / Deactivate a question");
		/*Step Number: 1
		 *Step Name: Add a question
		 *Step Description: 
			- Select 1 category and click on Submit question
			- Put values
			- Save
		 *Input Data: 

		 *Expected Outcome: 
			- Question is added new and shown in selected category*/
		hp.goToAnswer();
		qMang.goToSubmitQuestion();
		qMang.inputDataToQuestionForm(title, content, null, null);
		click(qMang.ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON);
		click(button.ELEMENT_OK_BUTTON_LINK);
		waitForAndGetElement(By.xpath(aHome.ELEMENT_QUESTION_LIST_ITEM.replace("$question", title)));

		/*Step number: 2
		 *Step Name: Deactivate a question
		 *Step Description: 
			- Click Manage Question in the main menu
			- Select All Question tab
			- Click Inactivate icon on activated question
		 *Input Data: 

		 *Expected Outcome: 
			Question is Inactive successfully. Inactive question isdisappear with normal user*/
		qMang.goToManageQuestionForm();
		qMang.activeQuestionFromManageQuestionForm(title, false);
		waitForAndGetElement(qMang.ELEMENT_MANAGE_QUESTION_CLOSE_BUTTON).click();
		magAc.signOut();
		magAc.signIn(DATA_USER2, DATA_PASS);
		hp.goToAnswer();
		waitForElementNotPresent(By.xpath(aHome.ELEMENT_QUESTION_LIST_ITEM.replace("$question", title)));

		/*Step number: 3
		 *Step Name: Activate a question
		 *Step Description: 
			- Select All Question tab
			- Click Activate icon on inactivate question
		 *Input Data: 

		 *Expected Outcome: 
			Question is active successfully.*/ 
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		hp.goToAnswer();
		qMang.goToManageQuestionForm();
		qMang.activeQuestionFromManageQuestionForm(title, true);
		waitForAndGetElement(qMang.ELEMENT_MANAGE_QUESTION_CLOSE_BUTTON).click();
		magAc.signOut();
		magAc.signIn(DATA_USER2, DATA_PASS);
		hp.goToAnswer();
		waitForAndGetElement(By.xpath(aHome.ELEMENT_QUESTION_LIST_ITEM.replace("$question", title)));
		
		info("Clear data");
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		hp.goToAnswer();
		qMang.deleteQuestion(title);
	}

	/**
	 * Case ID:116836.
	 * Test Case Name: Approve / Disapprove a question.
	 * Pre-Condition: 
	 * Post-Condition: 
	 */
	@Test(priority=6)
	public  void test09_ApproveDisapproveAQuestion() {
		String title = txData.getContentByArrayTypeRandom(1)+"116836";
		String content = txData.getContentByArrayTypeRandom(1)+"116836";
		
		info("Test 10 Approve / Disapprove a question");
		/*Step Number: 1
		 *Step Name: Add a question
		 *Step Description: 
			- Select 1 category and click on Submit question
			- Put values
			- Save
		 *Input Data: 

		 *Expected Outcome: 
			- Question is added new and shown in selected category*/
		hp.goToAnswer();
		qMang.goToSubmitQuestion();
		qMang.inputDataToQuestionForm(title, content, null, null);
		click(qMang.ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON);
		click(button.ELEMENT_OK_BUTTON_LINK);
		waitForAndGetElement(By.xpath(aHome.ELEMENT_QUESTION_LIST_ITEM.replace("$question", title)));

		/*Step number: 2
		 *Step Name: Disapprove a question
		 *Step Description: 
			- Click Manage Question in the main menu
			- Select All Question tab
			- Click Disapprove icon on approved question
		 *Input Data: 

		 *Expected Outcome: 
			- Questions is disapproved successful.Disapprove question is invisible with normal users.*/
		qMang.goToManageQuestionForm();
		qMang.approveQuestionFromManageQuestionForm(title, false);
		waitForAndGetElement(qMang.ELEMENT_MANAGE_QUESTION_CLOSE_BUTTON).click();
		magAc.signOut();
		magAc.signIn(DATA_USER2, DATA_PASS);
		hp.goToAnswer();
		waitForElementNotPresent(By.xpath(aHome.ELEMENT_QUESTION_LIST_ITEM.replace("$question", title)));
		
		/*Step number: 3
		 *Step Name: Approve a question
		 *Step Description: 
			- Click Approve icon on disapproved question
		 *Input Data: 

		 *Expected Outcome: 
			Questions is approved successfully*/ 
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		hp.goToAnswer();
		qMang.goToManageQuestionForm();
		qMang.approveQuestionFromManageQuestionForm(title, true);
		waitForAndGetElement(qMang.ELEMENT_MANAGE_QUESTION_CLOSE_BUTTON).click();
		magAc.signOut();
		magAc.signIn(DATA_USER2, DATA_PASS);
		hp.goToAnswer();
		waitForAndGetElement(By.xpath(aHome.ELEMENT_QUESTION_LIST_ITEM.replace("$question", title)));
		
		info("Clear data");
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		hp.goToAnswer();
		qMang.deleteQuestion(title);
	}
	
	/**
	 * Case ID:116813.
	 * Test Case Name: Send to friend.
	 * Pre-Condition: 
	 * Post-Condition: 
	 */
	@Test(priority=7)
	public  void test10_SendToFriend() {
		String title = txData.getContentByArrayTypeRandom(1)+"116813";
		String content = txData.getContentByArrayTypeRandom(1)+"116813";
		String contentMail = "Hi,/You may be interested in this question:/Question "+title+"/Details/Content of "+title+"/Click here for more details.";
		By mail = By.xpath("//b[text()= '"+title+"']");
		
		info("Create question");
		hp.goToAnswer();
		qMang.goToSubmitQuestion();
		qMang.inputDataToQuestionForm(title, content, null, null);
		click(qMang.ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON);
		click(button.ELEMENT_OK_BUTTON_LINK);
		waitForAndGetElement(By.xpath(aHome.ELEMENT_QUESTION_LIST_ITEM.replace("$question", title)));

		info("Test 2: Send to friend");
		/*Step Number: 1
		 *Step Name: Send Question
		 *Step Description: 
			- Right click on question and send question
			- Check mail Inbox to see question content, question link
		 *Input Data: 

		 *Expected Outcome: 
			- Mail is sent successful to email address
			- Content of mail includes Question content & Question link*/ 
		qMang.goToActionOfQuestionByRightClick(title, actionQuestionOption.SEND);
		type(qMang.ELEMENT_QUESTION_SEND_TO_INPUT,EMAIL_ADDRESS1,true);
		click(qMang.ELEMENT_QUESTION_SEND_SEND_BUTTON);
		click(qMang.ELEMENT_QUESTION_OK_BUTTON);
		
		//Check email
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		checkAndDeleteMail(mail, contentMail);
		switchToParentWindow();
		
		info("clear data");
		qMang.goToActionOfQuestionByRightClick(title,actionQuestionOption.DELETE);
		waitForAndGetElement(qMang.ELEMENT_QUESTION_CONFIRM_DELETE);
		click(qMang.ELEMENT_QUESTION_DELETE_FORM_OK_BUTTON);
		waitForElementNotPresent(By.xpath(aHome.ELEMENT_QUESTION_LIST_ITEM.replace("$question", title)));
	}
}
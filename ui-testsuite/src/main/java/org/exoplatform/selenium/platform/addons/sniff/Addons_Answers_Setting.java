package org.exoplatform.selenium.platform.addons.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.HomePagePlatform;
import org.exoplatform.selenium.platform.ManageLogInOut;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.answer.AnswerHomePage;
import org.exoplatform.selenium.platform.answer.AnswerManagement;
import org.exoplatform.selenium.platform.answer.AnswerCategoryManagement;
import org.exoplatform.selenium.platform.answer.CommentManagement;
import org.exoplatform.selenium.platform.answer.QuestionManagement;
import org.exoplatform.selenium.platform.answer.AnswerCategoryManagement.actionCategoryOption;
import org.exoplatform.selenium.platform.forum.ForumCategoryManagement;
import org.exoplatform.selenium.platform.forum.ForumForumManagement;
import org.exoplatform.selenium.platform.gatein.AnswerPage;
import org.exoplatform.selenium.platform.gatein.PageEditor;
import org.exoplatform.selenium.platform.gatein.PageCreationWizard;
import org.exoplatform.selenium.platform.objectdatabase.common.AttachmentFileDatabase;
import org.exoplatform.selenium.platform.objectdatabase.common.TextBoxDatabase;
import org.exoplatform.selenium.platform.objectdatabase.user.UserDatabase;
import org.testng.annotations.*;


public class Addons_Answers_Setting  extends PlatformBase {
	HomePagePlatform hp;
	ManageLogInOut magAc;
	TextBoxDatabase txData;
	QuestionManagement qMang;
	AnswerCategoryManagement cMang;
	ForumCategoryManagement fcMang;
	ForumForumManagement fMang;
	AnswerManagement aMang;
	AnswerHomePage aHome;
	CommentManagement comMang;
	AttachmentFileDatabase fData;
	UserDatabase userData;
	ManageAlert alert;
	NavigationToolbar navTool;
	PageCreationWizard pagMang;
	PageEditor pagEditor;
	AnswerPage aPage;
	Button button;
	String question1;
	String content1;
	String paCat1;
	String paDes1;
	String fullName;
	String paCat2;
	String paDes2;
	String question2;
	String content2;
	String answer1;
	String answer2;
	String comment1;
	String comment2;
	String cat1;
	String for1;

	@BeforeClass
	public void setUpBeforeTest() throws Exception{
		initSeleniumTest();
		getDefaultUserPass(userDataFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		driver.get(baseUrl);
		magAc = new ManageLogInOut(driver);
		hp = new HomePagePlatform(driver);
		qMang = new QuestionManagement(driver);
		cMang = new AnswerCategoryManagement(driver);
		fcMang = new ForumCategoryManagement(driver);
		comMang = new CommentManagement(driver);
		aMang = new AnswerManagement(driver);
		button = new Button(driver);
		aHome=new AnswerHomePage(driver);
		alert = new ManageAlert(driver);
		navTool = new NavigationToolbar(driver);
		pagMang = new PageCreationWizard(driver);
		pagEditor = new PageEditor(driver);
		aPage = new AnswerPage(driver);
		fMang=new ForumForumManagement(driver);
		txData = new TextBoxDatabase();
		fData = new AttachmentFileDatabase();
		userData = new UserDatabase();
		magAc.signIn(DATA_USER1, DATA_PASS);
		userData.setUserData(userDataFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		txData.setContentData(texboxFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlContent);
		fData.setAttachFileData(attachmentFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		question1 = txData.getContentByArrayTypeRandom(1)+"q1116808";
		content1 = txData.getContentByArrayTypeRandom(1)+"qc1116808";
		paCat1 = txData.getContentByArrayTypeRandom(1)+"p1116808";
		paDes1 = txData.getContentByArrayTypeRandom(1)+"p1116808";
		question2 = txData.getContentByArrayTypeRandom(1)+"q2116808";
		content2 = txData.getContentByArrayTypeRandom(1)+"qc2116808";
		paCat2 = txData.getContentByArrayTypeRandom(1)+"p2116808";
		paDes2 = txData.getContentByArrayTypeRandom(1)+"p2116808";
		answer1 = txData.getContentByArrayTypeRandom(1)+"a1116808";
		answer2 = txData.getContentByArrayTypeRandom(1)+"a2116808";
		comment1 = txData.getContentByArrayTypeRandom(1)+"c1116808";
		comment2 = txData.getContentByArrayTypeRandom(1)+"c2116808";
		cat1 = txData.getContentByArrayTypeRandom(1)+"cat1116808";
		for1 = txData.getContentByArrayTypeRandom(1)+"fo1116808";
		fullName = userData.fullName.get(0);
		createDataTest();
	}

	@AfterClass
	public void afterTest(){
		deleteDataTest();
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	public void createDataTest(){
		info("create data test");
		info("Create category 1");
		hp.goToAnswer();
		cMang.goToActionOfCategoryFromActionBar(actionCategoryOption.ADD);
		cMang.inputDataToSettingTab(paCat1, null, paDes1, null, null, null);
		click(cMang.ELEMENT_CATEGORY_ADD_FORM_SAVE_BUTTON);
		waitForAndGetElement(cMang.ELEMENT_CATEGORY_LIST_ITEM.replace("$category", paCat1));

		info("Create question 1");
		cMang.goToActionOfCategoryFromRightClick(paCat1, actionCategoryOption.SUBMITQUESTION);
		qMang.inputDataToQuestionForm(question1, content1, null, null);
		click(qMang.ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON);
		click(button.ELEMENT_OK_BUTTON_LINK);

		info("Create answer 1");
		aHome.goToHomeCategory();
		waitForAndGetElement(cMang.ELEMENT_CATEGORY_LIST_ITEM.replace("$category", paCat1));
		click(cMang.ELEMENT_CATEGORY_LIST_ITEM.replace("$category", paCat1));
		aMang.goToAnswerQuestion(question1);
		aMang.inputDataToAnswer(answer1, null, null, null);
		click(aMang.ELEMENT_ANSWER_FORM_SAVE_BUTTON);
		waitForAndGetElement(aMang.ELEMENT_ANSWER_AUTHOR.replace("$answer", answer1).replace("$fullname", fullName));

		info("Create comment 1");
		aHome.goToHomeCategory();
		waitForAndGetElement(cMang.ELEMENT_CATEGORY_LIST_ITEM.replace("$category", paCat1));
		click(cMang.ELEMENT_CATEGORY_LIST_ITEM.replace("$category", paCat1));
		comMang.goToCommentQuestion(question1);
		comMang.inputDataToComment(comment1);
		click(comMang.ELEMENT_COMMENT_FORM_SAVE_BUTTON);
		waitForAndGetElement(comMang.ELEMENT_COMMENT_AUTHOR.replace("$comment", comment1).replace("$fullname", fullName));

		info("Create category 2");
		aHome.goToHomeCategory();
		cMang.goToActionOfCategoryFromActionBar(actionCategoryOption.ADD);
		cMang.inputDataToSettingTab(paCat2, null, paDes2, null, null, null);
		click(cMang.ELEMENT_CATEGORY_ADD_FORM_SAVE_BUTTON);
		waitForAndGetElement(cMang.ELEMENT_CATEGORY_LIST_ITEM.replace("$category", paCat2));

		info("Create question 2");
		cMang.goToActionOfCategoryFromRightClick(paCat2, actionCategoryOption.SUBMITQUESTION);
		qMang.inputDataToQuestionForm(question2, content2, null, null);
		click(qMang.ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON);
		click(button.ELEMENT_OK_BUTTON_LINK);

		info("Create answer 2");
		aHome.goToHomeCategory();
		waitForAndGetElement(cMang.ELEMENT_CATEGORY_LIST_ITEM.replace("$category", paCat2));
		click(cMang.ELEMENT_CATEGORY_LIST_ITEM.replace("$category", paCat2));
		aMang.goToAnswerQuestion(question2);
		aMang.inputDataToAnswer(answer2, null, null, null);
		click(aMang.ELEMENT_ANSWER_FORM_SAVE_BUTTON);
		waitForAndGetElement(aMang.ELEMENT_ANSWER_AUTHOR.replace("$answer", answer2).replace("$fullname", fullName));

		info("Create comment 2");
		aHome.goToHomeCategory();
		waitForAndGetElement(cMang.ELEMENT_CATEGORY_LIST_ITEM.replace("$category", paCat2));
		click(cMang.ELEMENT_CATEGORY_LIST_ITEM.replace("$category", paCat2));
		comMang.goToCommentQuestion(question2);
		comMang.inputDataToComment(comment2);
		click(comMang.ELEMENT_COMMENT_FORM_SAVE_BUTTON);
		waitForAndGetElement(comMang.ELEMENT_COMMENT_AUTHOR.replace("$comment", comment2).replace("$fullname", fullName));
		
		info("Create category");
		hp.goToForum();
		fcMang.addCategorySimple(cat1,"","");
		/*fcMang.goToAddCategory();
		fcMang.inputBasicDataToCategoryTab(cat1, null, null);
		click(fcMang.ELEMENT_CATEGORY_ADD_FORM_SAVE_BUTTON);*/
		
		info("Create forum");
		//fMang.goToAddForum();
		fMang.addForumSimple(for1,"","");
		/*fMang.inputDataInAddForumTab_addForum(for1,null,null,null,null,null);
		click(fMang.ELEMENT_FORUM_ADD_FORM_SAVE_BUTTON);*/
	}

	public void deleteDataTest(){
		info("Delete data test");
		info("Create category");
		hp.goToAnswer();
		aHome.goToHomeCategory();
		click(cMang.ELEMENT_CATEGORY_LIST_ITEM.replace("$category", paCat1));
		cMang.deleteCategory(paCat1);
		click(cMang.ELEMENT_CATEGORY_LIST_ITEM.replace("$category", paCat2));
		cMang.deleteCategory(paCat2);
	}
	/**
	 * Case ID:116808.
	 * Test Case Name: Setting Answer porlet.
	 * Case ID:116837.
	 * Test Case Name: Set to discuss on forum.
	 * Pre-Condition: 
	 * Post-Condition: 
	 */
	@Test
	public  void test01_02_SettingAnswerPorlet() {
		info("Test 1: Setting Answer porlet");
		/*Step Number: 1
		 *Step Name: Setting porlet from edit mode
		 *Step Description: 
			- Go to answer page and goto edit mode
			- Click on Edit portlet icon of Answer portlet
			- On tab Edit mode/ Category scoping, check Show/hide categories
			- On tab Discussion, Click on Discuss in Forum
			- Then, select a forum
			- On tab Display mode, Enable comment/vote/RSS..
			- On tab "Email notification", Edit template notification
		 *Input Data: 

		 *Expected Outcome: 
			Setting porlet is successful with properties*/
		aPage.goToEditAnswerPortlet();
		aPage.setDisplayCategoryScoping(paCat1, false);
		aPage.settingEmailTemplate(1, content1);
		click(pagEditor.ELEMENT_PAGE_EDITOR_CLOSE_BUTTON);
		pagEditor.goToEditPortlet(pagEditor.ELEMENT_FRAME_CONTAIN_PORTLET);
		Utils.pause(2000);
		aPage.settingDisplayMode(true, true, true, true, true, true, true, true);
		
		info("Test 2: Set to discuss on forum");
		/*Step Number: 1
		 *Step Name: Configure to discuss in Forum
		 *Step Description: 
			- Go to answer page and goto edit mode
			- Click on Edit portlet icon of Answer portlet
			- On tab Edit mode, click On tab Discussion, Click on Discuss in Forum
			- Then, select a forum
			- Save and close, Finish
		 *Input Data: 

		 *Expected Outcome: 
			- Setting is saved,*/ 
		aPage.settingDiscussion(true,cat1+"/"+for1);
		click(pagEditor.ELEMENT_PAGE_EDITOR_CLOSE_BUTTON);
		pagEditor.finishEditLayout();
		aPage.goToEditAnswerPortlet();
		aPage.setDisplayCategoryScoping(paCat1, true);
		click(pagEditor.ELEMENT_PAGE_EDITOR_CLOSE_BUTTON);
		pagEditor.finishEditLayout();
	}
}
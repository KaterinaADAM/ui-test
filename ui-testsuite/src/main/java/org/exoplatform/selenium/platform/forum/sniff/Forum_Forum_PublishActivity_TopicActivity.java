package org.exoplatform.selenium.platform.forum.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.platform.HomePageActivity;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.forum.ForumBase;
import org.exoplatform.selenium.platform.forum.ForumManageCategory;
import org.exoplatform.selenium.platform.forum.ForumManageForum;
import org.exoplatform.selenium.platform.forum.ForumManagePost;
import org.exoplatform.selenium.platform.forum.ForumManageTopic;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * 
 * @author Thuntn
 * @date 05 Sep 2013
 */
public class Forum_Forum_PublishActivity_TopicActivity extends ForumBase{

	ManageAccount magAc;
	ForumManageCategory mngCat;
	ForumManageForum mngFru;
	ForumManageTopic mngTopic;
	ForumManagePost mngPost;
	HomePageActivity hpgAct;

	@BeforeMethod
	public void setUpBeforeTest(){
		initSeleniumTest();
		driver.get(baseUrl);
		magAc = new ManageAccount(driver);
		mngFru = new ForumManageForum(driver);
		mngCat = new ForumManageCategory(driver);
		mngPost = new ForumManagePost(driver);
		mngTopic = new ForumManageTopic(driver);
		hpgAct = new HomePageActivity(driver);
		navTool = new NavigationToolbar(driver);

		magAc.signIn(DATA_USER_JOHN, DATA_PASS);
		goToForums();
	}

	@AfterMethod
	public void afterTest(){
		driver.manage().deleteAllCookies();
		driver.quit();
	}
	/**
	 * CaseID: 75275
	 * Check topic AS after Move a topic
	 */
	@Test
	public void test01_CheckTopicASafterMoveTopic(){
		String titleCat = "Category 75275";
		String titleForum = "Forum 75275";
		String titleTop = "Topic 75275";
		String forum2 = "Forum 7527502";
		String[] addForum2 = {forum2, "1",null,null,forum2};
		String[] permission = {};

		info("Move a topic");
		//create category, forum, topic
		mngTopic.addCategoryForumTopic(titleCat, titleForum, titleTop,titleTop);
		click(By.linkText(titleCat));
		waitForElementNotPresent(ELEMENT_BREAD_FORUM.replace("${forum}", titleForum));

		//Create a forum - destination
		mngFru.addForum(titleCat, addForum2, true, "", "", false,0, permission);

		//Move topic
		click(By.linkText(titleCat));
		click(By.linkText(titleForum));
		click(mngFru.ELEMENT_TOPIC_LINK.replace("${topic}", titleTop));
		waitForAndGetElement(mngPost.ELEMENT_POST_REPLY_BUTTON);
		mngTopic.moveTopic(titleTop, forum2);

		//Check activity
		navTool.goToHomePage();
		if(waitForAndGetElement(By.linkText(titleTop),DEFAULT_TIMEOUT,0) == null){
			driver.navigate().refresh();
			waitForAndGetElement(By.linkText(titleTop),DEFAULT_TIMEOUT2);
		}
		hpgAct.checkCommentAfterMoveTopic(titleTop, titleCat + ">" + forum2);

		//Delete data
		goToForums();
		click(By.linkText(titleCat));
		mngCat.deleteCategoryInForum(titleCat, true);	
	}

	/**
	 * CaseID: 75276
	 * Check topic activity after create topic
	 */
	@Test
	public void test02_CheckTopicASafterCreateTopic(){
		String titleCat = "Category 75276";
		String titleForum = "Forum 75276";
		String titleTop = "Topic 75276";
		String descTop = "line1<br>line2<br>line3<br>line4<br>line5";
		String reply = "Reply on this topic";

		info("Create new Topic");
		//		create category, forum, topic
		mngTopic.addCategoryForumTopic(titleCat, titleForum, titleTop,descTop);
		click(mngFru.ELEMENT_TOPIC_LINK.replace("${topic}", titleTop));
		mngPost.postReply(reply, reply, "", "", "");

		mngTopic.voteTopic(1);

		navTool.goToHomePage();
		if(waitForAndGetElement(By.linkText(titleTop),DEFAULT_TIMEOUT,0) == null){
			driver.navigate().refresh();
			waitForAndGetElement(By.linkText(titleTop),DEFAULT_TIMEOUT2);
		}
		hpgAct.checkNumberOfLineOfContent(getText(hpgAct.ELEMENT_FORUM_ACT_CONTENT.replace("${title}", titleTop)), descTop);
		hpgAct.checkRateTopic(titleTop, 1.0);
		hpgAct.checkReplyForum(titleTop, reply);

		//Delete data
		goToForums();
		click(By.linkText(titleCat));
		mngCat.deleteCategoryInForum(titleCat, true);		
	}

	/**
	 * CaseID: 75277
	 * Check update topic activity after Update topic title
	 */
	@Test
	public void test03_CheckTopicASafterUpdateTopicTitle(){
		String titleCat = "Category 75277";
		String titleForum = "Forum 75277";
		String titleTop = "Topic 75277";
		String descTop = "line1<br>line2<br>line3<br>line4<br>line5";
		String newTopic = "New Topic 75277";
		String[] userGroup ={};

		info("Update topic title");
		//create category, forum, topic
		mngTopic.addCategoryForumTopic(titleCat, titleForum, titleTop,descTop);
		click(mngFru.ELEMENT_TOPIC_LINK.replace("${topic}", titleTop));
		mngTopic.editTopic(newTopic, descTop, "",  0, userGroup,false,false,false);

		navTool.goToHomePage();
		if(waitForAndGetElement(By.linkText(newTopic),DEFAULT_TIMEOUT,0) == null){
			driver.navigate().refresh();
			waitForAndGetElement(By.linkText(newTopic),DEFAULT_TIMEOUT2);
		}
		
		hpgAct.checkTitleAfterEditing(titleTop,newTopic);

		//Delete data
		goToForums();
		click(By.linkText(titleCat));
		mngCat.deleteCategoryInForum(titleCat, true);		
	}

	/**
	 * CaseID: 75278
	 * Check update Topic activity after Update topic content
	 */
	@Test
	public void test04_CheckTopicASafterUpdateTopicContent(){
		String titleCat = "Category75278";
		String titleForum = "Forum75278";
		String titleTop = "Topic75278";
		String descTop = "line1<br>line2<br>line3<br>line4<br>line5";
		String newDesc = "New line1<br>line2<br>line3<br>line4<br>line5<br>line6";
		String[] userGroup ={};

		info("Update topic title");
		//create category, forum, topic
		mngTopic.addCategoryForumTopic(titleCat, titleForum, titleTop,descTop);
		click(mngFru.ELEMENT_TOPIC_LINK.replace("${topic}", titleTop));
		mngTopic.editTopic(titleTop, newDesc, "",  0, userGroup,false,false,false);

		navTool.goToHomePage();
		if(waitForAndGetElement(By.linkText(titleTop),DEFAULT_TIMEOUT,0) == null){
			driver.navigate().refresh();
			waitForAndGetElement(By.linkText(titleTop),DEFAULT_TIMEOUT2);
		}
		hpgAct.checkUpdateTopic(titleTop, newDesc);

		//Delete data
		goToForums();
		click(By.linkText(titleCat));
		mngCat.deleteCategoryInForum(titleCat, true);	
	}

	/**
	 * CaseID: 75279
	 * Check topic activity after Lock/Unlock a topic
	 */
	@Test
	public void test05_CheckTopicASafterLockUnlockTopic(){
		String titleCat = "Category75279";
		String titleForum = "Forum75279";
		String titleTop = "Topic75279";
		String descTop = "line1<br>line2<br>line3<br>line4<br>line5";

		info("Lock/Unlock a topic");
		//create category, forum, topic
		mngTopic.addCategoryForumTopic(titleCat, titleForum, titleTop,descTop);
		click(mngFru.ELEMENT_TOPIC_LINK.replace("${topic}", titleTop));
		//Lock a topic
		mngTopic.actionOnTopic(1);

		//Check if topic AS is updated or not after lock topic
		navTool.goToHomePage();
		if(waitForAndGetElement(By.linkText(titleTop),DEFAULT_TIMEOUT,0) == null){
			driver.navigate().refresh();
			waitForAndGetElement(By.linkText(titleTop),DEFAULT_TIMEOUT2);
		}
		hpgAct.checkLockTopic(titleTop);

		//Unlock a topic
		goToForums();
		mngTopic.actionOnTopic(2);

		//Check if topic AS is updated or not after unlock topic
		navTool.goToHomePage();
		if(waitForAndGetElement(By.linkText(titleTop),DEFAULT_TIMEOUT,0) == null){
			driver.navigate().refresh();
			waitForAndGetElement(By.linkText(titleTop),DEFAULT_TIMEOUT2);
		}
		hpgAct.checkUnlockTopic(titleTop);

		//Delete data
		goToForums();
		click(By.linkText(titleCat));
		mngCat.deleteCategoryInForum(titleCat, true);	
	}

	/**
	 * CaseID: 75280
	 * Check topic activity after Delete topic
	 */
	@Test
	public void test06_CheckTopicASafterDeleteTopic(){
		String titleCat = "Category75280";
		String titleForum = "Forum75280";
		String titleTop = "Topic75280";
		String descTop = "line1<br>line2<br>line3<br>line4<br>line5";

		info("Delete topic");
		//create category, forum, topic
		mngTopic.addCategoryForumTopic(titleCat, titleForum, titleTop,descTop);
		click(mngFru.ELEMENT_TOPIC_LINK.replace("${topic}", titleTop));

		navTool.goToHomePage();
		waitForAndGetElement(By.linkText(titleTop));

		goToForums();
		mngTopic.deleteTopic(titleTop);

		navTool.goToHomePage();
		driver.navigate().refresh();
		assert waitForAndGetElement(By.linkText(titleTop),5000,0) == null;

		//Delete data
		goToForums();
		click(By.linkText(titleCat));
		mngCat.deleteCategoryInForum(titleCat, true);	
	}

	/**
	 * CaseID: 75286
	 * Jump into Reply form by clicking on "Reply" action
	 */
	@Test
	public void test07_OpenReplyFormByClickReplyOnActivity(){
		String titleCat = "Category75286";
		String titleForum = "Forum75286";
		String titleTop = "Topic75286";
		String descTop = "line1<br>line2<br>line3<br>line4<br>line5";

		info("Jump into Reply form by clicking on Reply action");

		//create category, forum, topic
		mngTopic.addCategoryForumTopic(titleCat, titleForum, titleTop,descTop);

		//Check activity
		navTool.goToHomePage();
		if(waitForAndGetElement(By.linkText(titleTop),DEFAULT_TIMEOUT,0) == null){
			driver.navigate().refresh();
			waitForAndGetElement(By.linkText(titleTop),DEFAULT_TIMEOUT2);
		}

		hpgAct.openReplyFormFromActivity(titleTop);

		//Delete data
		click(By.linkText(titleCat));
		mngCat.deleteCategoryInForum(titleCat, true);		
	}

	/**
	 * CaseID: 75287
	 * Jump into last Reply of Topic
	 */
	@Test
	public void test08_OpenLastReplyOfTopic(){
		String titleCat = "Category75287";
		String titleForum = "Forum75287";
		String titleTop = "Topic75287";
		String descTop = "line1<br>line2<br>line3<br>line4<br>line5";
		String reply1 = "Reply to this topic 1";
		String reply2 = "Reply to this topic 2";

		info("Jump into last Reply of Topic");

		//create category, forum, topic
		mngTopic.addCategoryForumTopic(titleCat, titleForum, titleTop,descTop);
		click(mngFru.ELEMENT_TOPIC_LINK.replace("${topic}", titleTop));
		mngPost.postReply(reply1, reply1, "", "", "");
		mngPost.postReply(reply2, reply2, "", "", "");

		//Check activity
		navTool.goToHomePage();
		if(waitForAndGetElement(By.linkText(titleTop),DEFAULT_TIMEOUT,0) == null){
			driver.navigate().refresh();
			waitForAndGetElement(By.linkText(titleTop),DEFAULT_TIMEOUT2);
		}

		hpgAct.openLastReplyFromActivity(titleTop,reply2);

		//Delete data
		click(By.linkText(titleCat));
		mngCat.deleteCategoryInForum(titleCat, true);	
	}

	/**
	 * CaseID: 75288
	 * Jump to related reply
	 */
	@Test
	public void test09_OpenRelatedReply(){
		String titleCat = "Category75288";
		String titleForum = "Forum75288";
		String titleTop = "Topic75288";
		String descTop = "line1<br>line2<br>line3<br>line4<br>line5";
		String reply = "Reply to this topic 1";

		info("Jump to related reply");

		//create category, forum, topic
		mngTopic.addCategoryForumTopic(titleCat, titleForum, titleTop,descTop);
		click(mngFru.ELEMENT_TOPIC_LINK.replace("${topic}", titleTop));
		mngPost.postReply(reply, reply, "", "", "");

		//Check activity
		navTool.goToHomePage();

		if(waitForAndGetElement(By.linkText(titleTop),DEFAULT_TIMEOUT,0) == null)
			driver.navigate().refresh();

		hpgAct.viewReplyFromActivity(titleTop, reply);

		//Delete data
		click(By.linkText(titleCat));
		mngCat.deleteCategoryInForum(titleCat, true);		
	}
}

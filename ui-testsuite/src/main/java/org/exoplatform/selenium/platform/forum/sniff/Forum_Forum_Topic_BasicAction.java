package org.exoplatform.selenium.platform.forum.sniff;

import static org.exoplatform.selenium.TestLogger.info; 

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.ManageAccount;
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
 * @author thuntn
 * @date 11 Sep 2013
 */

public class Forum_Forum_Topic_BasicAction extends ForumBase {
	ManageAccount magAc;
	ForumManageCategory mngCat;
	ForumManageForum mngFru;
	ForumManageTopic mngTopic;
	ForumManagePost mngPost;

	@BeforeMethod
	public void setUpBeforeTest(){
		initSeleniumTest();
		magAc = new ManageAccount(driver);
		mngCat = new ForumManageCategory(driver);
		mngFru = new ForumManageForum(driver);
		mngTopic = new ForumManageTopic(driver);
		mngPost = new ForumManagePost(driver);

		magAc.signIn(DATA_USER1, DATA_PASS);
		goToForums();
	}

	@AfterMethod
	public void afterTest(){
		driver.manage().deleteAllCookies();
		driver.quit();
	}
	/**Create new Topic
	 * CaseID: 74749
	 * Create new category
	 * Create new Forum
	 * Create new Topic 
	 */
	@Test
	public void test01_CreateTopic() {
		String titleCat = "Category 74749";
		String titleForum = "Forum 74749";
		String titleTop = "Topic 74749";

		info("Create a topic");
		//create category, forum, topic
		mngTopic.addCategoryForumTopic(titleCat, titleForum, titleTop,titleTop); 

		//Check if topic is created in forum
		waitForAndGetElement(mngFru.ELEMENT_TOPIC_LINK.replace("${topic}", titleTop));
		Utils.pause(3000);
		//Delete data
		click(By.linkText(titleCat));
		Utils.pause(3000);
		mngCat.deleteCategoryInForum(titleCat, true);

	}
	/** Delete topic
	 * caseID: 74753
	 */
	@Test
	public void test02_DeleteTopic() {
		String titleCat = "Category 74753";
		String titleForum = "Forum 74753";
		String titleTop = "Topic 74753";

		info("Delete topic");
		//create category, forum, topic
		mngTopic.addCategoryForumTopic(titleCat, titleForum, titleTop,titleTop); 
		click(mngFru.ELEMENT_TOPIC_LINK.replace("${topic}", titleTop));
		waitForAndGetElement(mngPost.ELEMENT_POST_QUICK_BUTTON);
		mngTopic.deleteTopic(titleTop);

		//Delete data
		click(By.linkText(titleCat));
		mngCat.deleteCategoryInForum(titleCat, true);
	}

	//FQA-1342: remove LockUnlockTopic

	/** Move a topic
	 * caseID: 71197
	 */
	@Test
	public void test04_MoveTopic() {
		String titleCat = "Category 71197";
		String titleForum = "Forum 71197";
		String titleTop = "Topic 71197";
		String forum2 = "Forum 71197 2";
		String[] addForum2 = {forum2, "1",null,null,forum2};
		String[] permission = {};

		info("Move a topic");
		//create category, forum, topic
		mngTopic.addCategoryForumTopic(titleCat, titleForum, titleTop,titleTop); 
		click(By.linkText(titleCat));

		mngFru.addForum(titleCat, addForum2, true, "", "", false,0, permission);

		//Move topic
		click(By.linkText(titleCat));
		click(By.linkText(titleForum));
		click(mngFru.ELEMENT_TOPIC_LINK.replace("${topic}", titleTop));
		mngTopic.moveTopic(titleTop, forum2); 

		//Delete data
		click(By.linkText(titleCat));
		mngCat.deleteCategoryInForum(titleCat, true);
	}
	
	/** Update topic title, 
	 * caseID: 74750
	 */
	@Test
	public void test05_UpdateTopicTitle() {
		String titleCat = "Category 74750";
		String titleForum = "Forum 74750";
		String titleTop = "Topic 74750";
		String newTopic = "New topic 74750";
		String[] userGroup = {};

		info("Update topic title");
		//create category, forum, topic
		mngTopic.addCategoryForumTopic(titleCat, titleForum, titleTop,titleTop); 
		click(mngFru.ELEMENT_TOPIC_LINK.replace("${topic}", titleTop));
		mngTopic.editTopic(newTopic, titleTop, "",  0, userGroup,false,false,false); 
		waitForAndGetElement(mngTopic.ELEMENT_BREADCRUMB_TOPIC.replace("${forum}", titleForum).replace("${topic}", newTopic));

		//Delete data
		click(By.linkText(titleCat));
		mngCat.deleteCategoryInForum(titleCat, true);
	}
	
	/** Update topic content, 
	 * caseID: 74751
	 */
	@Test
	public void test06_UpdateTopicContent() {
		String titleCat = "Category 74751";
		String titleForum = "Forum 74751";
		String titleTop = "Topic 74751";
		String newDesc = "New topic 74751";
		String[] userGroup = {};

		info("Update topic title");
		//create category, forum, topic
		mngTopic.addCategoryForumTopic(titleCat, titleForum, titleTop,titleTop); 
		click(mngFru.ELEMENT_TOPIC_LINK.replace("${topic}", titleTop));
		mngTopic.editTopic(titleTop, newDesc, "",  0, userGroup,false,false,false); 
		waitForTextPresent(newDesc);

		//Delete data
		click(By.linkText(titleCat));
		mngCat.deleteCategoryInForum(titleCat, true);
	}

}

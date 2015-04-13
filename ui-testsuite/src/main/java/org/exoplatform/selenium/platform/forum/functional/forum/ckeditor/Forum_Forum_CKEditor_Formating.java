package org.exoplatform.selenium.platform.forum.functional.forum.ckeditor;

import static org.exoplatform.selenium.TestLogger.info;

import java.awt.event.KeyEvent;

import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.forum.ForumBase;
import org.exoplatform.selenium.platform.forum.ForumManageCategory;
import org.exoplatform.selenium.platform.forum.ForumManageForum;
import org.exoplatform.selenium.platform.forum.ForumManagePost;
import org.exoplatform.selenium.platform.forum.ForumManageTopic;
import org.exoplatform.selenium.platform.CKeditor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * 
 * by quynhpt
 * date 14/10/2014
 */
public class Forum_Forum_CKEditor_Formating extends ForumBase {

	ForumManageForum mngFru;
	ForumManageTopic mngTopic;
	ForumManagePost mngPost;
	ManageAccount magAcc;
	ForumManageCategory mngCat;
	NavigationToolbar navToolBar;
	CKeditor cke;

	String category = "";
	String forum = "";
	String topic = "";
	String post = "";
	String newPost = "";
	String editTopic = "";
	String quotePost = "";
	String tex_message = "";
	String tex_mess_forw = "";
	String text = "";

	@BeforeTest
	public void setBeforeTest() {
		initSeleniumTest();
		driver.get(baseUrl);
		info("Login with " + DATA_USER1);
		mngFru = new ForumManageForum(driver, this.plfVersion);
		mngTopic = new ForumManageTopic(driver, this.plfVersion);
		mngPost = new ForumManagePost(driver, this.plfVersion);
		magAcc = new ManageAccount(driver, this.plfVersion);
		mngCat = new ForumManageCategory(driver, this.plfVersion);
		navToolBar = new NavigationToolbar(driver);
		cke = new CKeditor(driver, this.plfVersion);
		magAcc.signIn(DATA_USER1, DATA_PASS);

	}

	@BeforeMethod
	public void setBeforeMethod() {
		// Create a new name for category, forum, post
		setUpNameForCatForTopicPost();
	}

	@AfterTest
	public void setAfterTest() {
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	/**
	 * Set the name for all parameters
	 */
	private void setUpNameForCatForTopicPost() {
		// Setup names for category, forum, topic, post and newpost
		String number_Random = getRandomNumber();
		category = "Category_" + number_Random;
		forum = "Forum_" + number_Random;
		topic = "Topic_" + number_Random;
		post = "Post_" + number_Random+"\n";
		newPost = "New_post_" + number_Random+"\n";
		editTopic = "EditTopic_" + number_Random+"\n";
		quotePost = "QuotePost_" + number_Random;
		tex_message = "PrivateMessage_" + number_Random+"\n";
		tex_mess_forw = "ForwardPrivMessage_" + number_Random;
		text = post + newPost + editTopic + tex_message;
	}

	/************************** Formating *******************************/
	/**
	 * TC_102566: Post reply with blockquote
	 * Steps:
	 * 1.Login by the administrator
	 * 2.Choose category/forum has a topic
	 * 3.Select this topic and choose [Post reply] button
	 * 4.Fill content in Details, use some features of editor to decorate this content ( ex: Block quote)
	 * 5.Click Save 
	 * Expected:
	 * 5.New reply is created successfully
	 * 5.Detail of reply is displayed as decorated.
	 */
	@Test(enabled = true)
	public void test01_PostReplyWithBlockQuote() {
		info("Go to the forum portlet");
		mngFru.goToForums();

		info("Create a new category, forum, topic");
		mngTopic.addCategoryForumTopic(category, forum, topic, topic);

		// open the topic
		click(By.linkText(topic));

		// click on Post Reply button
		mngPost.openReplyPostPopUp();
		// click on block quote button
		cke.cke_BlockQuote();
		// input a text into the content
		inputFrame(mngTopic.ELEMENT_TOPIC_MESSAGE_FRAME_CKEDITOR,newPost);

		// save the post
		mngPost.savePostContent();

		info("-- Verify the content of the post--");
		waitForAndGetElement(
				ELEMENT_TOPIC_POST_DESCRIPTION_DECORATED_BLOCKQUOTE.replace(
						"${nameDes}", newPost.trim()));
		info("-- The test is successfull--");
		// delete the data test
		goToForumHome();
		click(By.linkText(category));
		mngCat.deleteCategoryInForum(category, true);
	}

	/**
	 * TC_102569: Show help BB code form
	 * Steps:
	 * 1.Login by the administrator
	 * 2.Choose category/forum
	 * 3.Click on Start topic
	 * 4.Click (?) icon in CKEditor 
	 * Expected:
	 * 4. Show BB Code form and  a list of the BB code tags you can use to format
	 */
	@Test(enabled = true)
	public void test02_ShowHelpBBCodeForm() {
		info("Go to the forum portlet");
		mngFru.goToForums();

		info("add a new category and a forum");
		mngTopic.addCategoryForum(category, forum);
		info(" open new topic popup");
		click(mngTopic.ELEMENT_START_TOPIC_BUTTON);

		// click on BB code button
		cke.cke_BBCodeHelp();
		// switch to BB code window
		switchToNewWindow();

		assert (driver.getTitle().contains("Help BB Code")):"wrong title";

		driver.close();
		info("-- The test is successfull--");
		switchToParentWindow();
		cke.cancelPopup();

		// delete the data test
		goToForumHome();
		click(By.linkText(category));
		mngCat.deleteCategoryInForum(category, true);
	}

	/**
	 * TC_102576: Paste as plain text (pending)
	 * Preconditions:
	 * Templates for this case are (Accessible breadcrum, acc navigation, 
	 * acc search box,web content, illustrated web content, product)
	 * Steps:
	 * 1.Log in
	 * 2.Go to Forum
	 * 3.Click Start topic/Post/Private Post/Private message/Notification
	 * 4.Open a word file in your computer with good format (colorful text, bold, highlight, Bullet & number )
	 * 5.Copy content of this file
	 * 6.Back to ckeditor at step 1
	 * 7.Click icon [Paste as plain text]
	 * 8.Paste the content by Control V or right click paste
	 * 9.Click OK
	 * Expected:
	 * 9.Content pasted in CKEditor is shown as plain text, no text decoration
	 */

	/**
	 * TC_102577: Paste option only
	 * Steps:
	 * 1.Log in
	 * 2.Go to Forum
	 * 3.Click Start topic/Post/Private Post/Private message/Notification
	 * 4.Open a web page, eg acme homepage
	 * 5.Control A then Copy all content and format of this webpage
	 * 6.Back to ckeditor at step 1
	 * 7.Click icon [Paste]
	 * 8.Paste the content by control or right click then Paste
	 * 9.Click OK
	 * Expected:
	 * 9.Content pasted in CKEditor with no text decoration 
	 */
	@Test(enabled = true)
	public void test03_PasteOptionOnly() {
		// back to intranet
		driver.get(baseUrl);

		// go to acme page
		info("Go to acme");
		String url = driver.getCurrentUrl().toString();
		String url_acme = url.replace("intranet", "acme");
		driver.get(url_acme);

		info("Copy a text on the home page of acme");
		pressGroupKeysUsingRobot(KeyEvent.VK_CONTROL, KeyEvent.VK_HOME);
		pressGroupKeysUsingRobot(KeyEvent.VK_CONTROL, KeyEvent.VK_A);
		pressGroupKeysUsingRobot(KeyEvent.VK_CONTROL, KeyEvent.VK_C);

		info("Go to the forum portlet");
		driver.get(baseUrl);
		mngFru.goToForums();

		info("Create a new category, forum, topic");
		mngTopic.addCategoryForumTopic(category, forum, topic, topic);

		// open the topic
		click(By.linkText(topic));

		// click on Post Reply button
		mngPost.openReplyPostPopUp();

		// past the text copied into the ckeditor
		cke.cke_Paste();

		// save the post
		mngPost.savePostContent();

		info("-- Verify the content of the post--");
		info("-- The test is successfull--");
		// delete the data test
		goToForumHome();
		click(By.linkText(category));
		mngCat.deleteCategoryInForum(category, true);
	}

	/**
	 * TC_102578_1: Bullet List
	 * Steps:
	 * 1. Login with adiministrator
	 * 2. Create a new category, forum, topic
	 * 3. Click on Post Reply button
	 * 4. Insert some texts into the content
	 * 5. Select it
	 * 6. Click [Insert/remove bulleted List] icon on formatting bar
	 * 7. Click again 
	 * Expected:
	 * 7.Text is inserted bullet
	 * Text is removed bullet
	 */
	@Test 
	public void test04_1_BulletList() {
		info("Go to the forum portlet");
		mngFru.goToForums();

		info("Create a new category, forum, topic");
		mngTopic.addCategoryForumTopic(category, forum, topic, topic);

		// open the topic
		click(By.linkText(topic));

		// click on Post Reply button
		mngPost.openReplyPostPopUp();

		// input 4 texts into the content

		//String text = post+newPost+editTopic+tex_message;
		inputFrame(mngTopic.ELEMENT_TOPIC_MESSAGE_FRAME_CKEDITOR,text);
		pressGroupKeysUsingRobot(KeyEvent.VK_CONTROL, KeyEvent.VK_A);
		cke.cke_InsertRemoveBulletList();
		mngPost.savePostContent();

		info("-- Verify the content of the post--");
		waitForAndGetElement(
				ELEMENT_TOPIC_POST_DESCRIPTITON_DECORATED_BULLET_LIST.replace(
						"${nameItem}", post).replace("\n", ""));

		waitForAndGetElement(
				ELEMENT_TOPIC_POST_DESCRIPTITON_DECORATED_BULLET_LIST.replace(
						"${nameItem}", newPost).replace("\n", ""));

		waitForAndGetElement(
				ELEMENT_TOPIC_POST_DESCRIPTITON_DECORATED_BULLET_LIST.replace(
						"${nameItem}", editTopic).replace("\n", ""));

		waitForAndGetElement(
				ELEMENT_TOPIC_POST_DESCRIPTITON_DECORATED_BULLET_LIST.replace(
						"${nameItem}", tex_message).replace("\n", ""));

		info("-- The test is successfull--");
		// delete the data test
		goToForumHome();
		click(By.linkText(category));
		mngCat.deleteCategoryInForum(category, true);
	}

	/**
	 * TC_102578_2: Numbering list
	 * Steps:
	 * 1. Login with adiministrator
	 * 2. Create a new category, forum, topic
	 * 3. Click on Post Reply button
	 * 4. Insert some texts into the content
	 * 5. Select it
	 * 6. Click [Insert/remove numbered List] icon on formatting bar
	 * 7. Click again 
	 * Expected:
	 * 7.Text is inserted number list
	 * Text is removed number list
	 */
	@Test
	public void test04_2_NumList() {
		info("Go to the forum portlet");
		mngFru.goToForums();

		info("Create a new category, forum, topic");
		mngTopic.addCategoryForumTopic(category, forum, topic, topic);

		// open the topic
		click(By.linkText(topic));

		// click on Post Reply button
		mngPost.openReplyPostPopUp();
		// input 4 texts into the content
		//cke.inputDataInContent(post);
		inputFrame(mngTopic.ELEMENT_TOPIC_MESSAGE_FRAME_CKEDITOR,text);
		/*inputDataToFrame(
				mngTopic.ELEMENT_TOPIC_MESSAGE_FRAME_CKEDITOR, post,
				false, false);
		switchToParentWindow();
		cke.cke_ReturnLine();
		//cke.inputDataInContent(newPost);
		inputDataToFrame(
				mngTopic.ELEMENT_TOPIC_MESSAGE_FRAME_CKEDITOR, post,
				false, false);
		switchToParentWindow();
		cke.cke_ReturnLine();
		//cke.inputDataInContent(editTopic);
		inputDataToFrame(
				mngTopic.ELEMENT_TOPIC_MESSAGE_FRAME_CKEDITOR, post,
				false, false);
		switchToParentWindow();
		cke.cke_ReturnLine();
		//cke.inputDataInContent(tex_message);
		inputDataToFrame(
				mngTopic.ELEMENT_TOPIC_MESSAGE_FRAME_CKEDITOR, post,
				false, false);
		switchToParentWindow();*/

		// save the post
		pressGroupKeysUsingRobot(KeyEvent.VK_CONTROL, KeyEvent.VK_A);
		cke.cke_InsertRemoveNumList();
		mngPost.savePostContent();

		info("-- Verify the content of the post--");
		waitForAndGetElement(
				ELEMENT_TOPIC_POST_DESCRIPTITON_DECORATED_NUM_LIST.replace(
						"${nameItem}", post).replace("\n", ""));
		waitForAndGetElement(
				ELEMENT_TOPIC_POST_DESCRIPTITON_DECORATED_NUM_LIST.replace(
						"${nameItem}", newPost).replace("\n", ""));

		waitForAndGetElement(
				ELEMENT_TOPIC_POST_DESCRIPTITON_DECORATED_NUM_LIST.replace(
						"${nameItem}", editTopic).replace("\n", ""));

		waitForAndGetElement(
				ELEMENT_TOPIC_POST_DESCRIPTITON_DECORATED_NUM_LIST.replace(
						"${nameItem}", tex_message).replace("\n", ""));

		info("-- The test is successfull--");
		// delete the data test
		goToForumHome();
		click(By.linkText(category));
		mngCat.deleteCategoryInForum(category, true);
	}

	/**
	 * TC_102579: Check align effect
	 * Steps:
	 * 1. Log in with administrator
	 * 2. Create a new category, forum and topic
	 * 3. Open the topic and click on Post Reply button
	 * 4. Fill text into main content field
	 * 5. Select it
	 * 6. Click [Align Left] icon on formatting bar
	 * ==> Text is left aligned
	 * 4. Fill text into main content field
	 * 5. Select it
	 * 6. Click [Align Right] icon on formatting bar
	 * ==> Text is right aligned
	 * 4. Fill text into main content field
	 * 5. Select it
	 * 6. Click [Center] icon on formatting bar
	 * ==> Text is center aligned
	 * 4. Fill text into main content field
	 * 5. Select it
	 * 6.Click [Justify] icon on formatting bar
	 * ==> Text is justify aligned
	 */
	@Test(enabled = true)
	public void test05_AlignEffects() {
		info("Go to the forum portlet");
		mngFru.goToForums();

		info("Create a new category, forum, topic");
		mngTopic.addCategoryForumTopic(category, forum, topic, topic);

		// open the topic
		click(By.linkText(topic));

		// Create a post with align right decoreated
		mngPost.openReplyPostPopUp();
		cke.cke_AlignRight();
		inputFrame(mngTopic.ELEMENT_TOPIC_MESSAGE_FRAME_CKEDITOR,newPost);
		mngPost.savePostContent();

		// Create a post with align left decoreated
		mngPost.openReplyPostPopUp();
		cke.cke_AlignLeft();
		inputFrame(mngTopic.ELEMENT_TOPIC_MESSAGE_FRAME_CKEDITOR,post);
		mngPost.savePostContent();

		// Create a post with center decoreated
		mngPost.openReplyPostPopUp();
		cke.cke_Center();
		inputFrame(mngTopic.ELEMENT_TOPIC_MESSAGE_FRAME_CKEDITOR,editTopic);
		mngPost.savePostContent();

		String just_tex = "Ice powers enable instant freeze capabilities. "
				+ "In addition, you can create ice formations without a water supply, "
				+ "such as ice cubes, skating rinks or even decorative sculptures";

		// Create a post with justify decoreated
		mngPost.openReplyPostPopUp();
		inputFrame(mngTopic.ELEMENT_TOPIC_MESSAGE_FRAME_CKEDITOR,just_tex);
		cke.cke_Justify();
		mngPost.savePostContent();

		info("-- Verify the content of the post--");
		waitForAndGetElement(
				ELEMENT_TOPIC_POST_DESCRIPTION_DECORATED_LEFT.replace(
						"${descripTopic}", post.trim()));

		info("-- Verify the content of the post--");
		waitForAndGetElement(
				ELEMENT_TOPIC_POST_DESCRIPTION_DECORATED_RIGHT.replace(
						"${descripTopic}", newPost.trim()));

		info("-- Verify the content of the post--");
		waitForAndGetElement(
				ELEMENT_TOPIC_POST_DESCRIPTION_DECORATED_CENTER.replace(
						"${descripTopic}", editTopic.trim()));

		info("-- Verify the content of the post--");
		waitForAndGetElement(
				ELEMENT_TOPIC_POST_DESCRIPTION_DECORATED_JUSTIFY.replace(
						"${descripTopic}", just_tex.trim()));

		info("-- The test is successfull--");
		// delete the data test
		goToForumHome();
		click(By.linkText(category));
		mngCat.deleteCategoryInForum(category, true);
	}

	/**
	 * TC_102580: Check Text effect bold, italic, underline
	 * 1.Log in as admin
	 * 2.Go to Forum page
	 * 3.Create a new category, forum and topic
	 * 4.Open the topic and click on Post Reply button
	 * 5.Fill text into main content field
	 * 6.Select it
	 * 7.Press control + B or, Click B icon on formatting bar
	 * ==>Text is in bold effect
	 * 8.Fill text into main content field
	 * 9.Select it 
	 *10.Press control + I or, Click I icon on formatting bar
	 *==>Text is in italic effect
	 *11.Fill text into main content field
	 *12.Select it
	 *13.Press control + U or, Click U icon on formatting bar
	 *==> Text is in Underline effect
	 */
	@Test(enabled = true)
	public void test06_BoldItalicUnderlineEffects() {
		info("Go to the forum portlet");
		mngFru.goToForums();

		info("Create a new category, forum, topic");
		mngTopic.addCategoryForumTopic(category, forum, topic, topic);

		// open the topic
		click(By.linkText(topic));

		// Create a post with align right decoreated
		mngPost.openReplyPostPopUp();
		inputFrame(mngTopic.ELEMENT_TOPIC_MESSAGE_FRAME_CKEDITOR,newPost);
		pressGroupKeysUsingRobot(KeyEvent.VK_CONTROL, KeyEvent.VK_A);
		cke.cke_Bold();
		mngPost.savePostContent();

		// Create a post with align left decoreated
		mngPost.openReplyPostPopUp();
		inputFrame(mngTopic.ELEMENT_TOPIC_MESSAGE_FRAME_CKEDITOR,post);
		pressGroupKeysUsingRobot(KeyEvent.VK_CONTROL, KeyEvent.VK_A);
		cke.cke_Italic();
		mngPost.savePostContent();

		// Create a post with center decoreated
		mngPost.openReplyPostPopUp();
		inputFrame(mngTopic.ELEMENT_TOPIC_MESSAGE_FRAME_CKEDITOR,editTopic);	
		pressGroupKeysUsingRobot(KeyEvent.VK_CONTROL, KeyEvent.VK_A);
		cke.cke_Underline();
		mngPost.savePostContent();

		info("-- Verify the content of the post--");
		waitForAndGetElement(
				ELEMENT_TOPIC_POST_DESCRIPTION_DECORATED_BOLD.replace(
						"${descripTopic}", newPost.trim()));

		info("-- Verify the content of the post--");
		waitForAndGetElement(
				ELEMENT_TOPIC_POST_DESCRIPTION_DECORATED_ITALIC.replace(
						"${descripTopic}", post.trim()));

		info("-- Verify the content of the post--");
		waitForAndGetElement(
				ELEMENT_TOPIC_POST_DESCRIPTION_DECORATED_UNDERLINE.replace(
						"${descripTopic}", editTopic.trim()));

		info("-- The test is successfull--");
		// delete the data test
		goToForumHome();
		click(By.linkText(category));
		mngCat.deleteCategoryInForum(category, true);
	}

	/**
	 * TC_102581: Decrease indent/InCrease indent
	 * 1.Log in as admin
	 * 2.Go to Forum page
	 * 3.Create a new category, forum and topic
	 * 4.Open the topic and click on Post Reply button
	 * 5.Fill text into main content field
	 * 6.Select it
	 * 7.Click [Increase Indent] icon on formatting bar
	 * ==>Indent is Increase
	 * 8. Fill text into main content field
	 * 9.Select it
	 * 10.Click [Decrease Indent] icon on formatting bar
	 * ==>Indent is decrease
	 */
	@Test
	public void test07_InDesCreaseIndents() {
		info("Go to the forum portlet");
		mngFru.goToForums();

		info("Create a new category, forum, topic");
		mngTopic.addCategoryForumTopic(category, forum, topic, topic);

		// open the topic
		click(By.linkText(topic));

		// click on Post Reply button
		mngPost.openReplyPostPopUp();
		// input 4 texts into the content
		inputFrame(mngTopic.ELEMENT_TOPIC_MESSAGE_FRAME_CKEDITOR,text);
		/*WebElement e = waitForAndGetElement(mngTopic.ELEMENT_TOPIC_MESSAGE_FRAME_CKEDITOR,DEFAULT_TIMEOUT,1,2);
		driver.switchTo().frame(e);
		pressGroupKeysUsingRobot(KeyEvent.VK_CONTROL, KeyEvent.VK_HOME);*/
		pressGroupKeysUsingRobot(KeyEvent.VK_CONTROL, KeyEvent.VK_A);
		//switchToParentWindow();
		cke.cke_InscrIndent();

		// save the post
		mngPost.savePostContent();

		info("-- Verify the content of the post--");
		waitForAndGetElement(
				ELEMENT_TOPIC_POST_DESCRIPTION_DECORATED_INCREASE.replace(
						"${nameItem}", post).replace("\n", ""));
		waitForAndGetElement(
				ELEMENT_TOPIC_POST_DESCRIPTION_DECORATED_INCREASE.replace(
						"${nameItem}", newPost).replace("\n", ""));

		waitForAndGetElement(
				ELEMENT_TOPIC_POST_DESCRIPTION_DECORATED_INCREASE.replace(
						"${nameItem}", editTopic).replace("\n", ""));

		waitForAndGetElement(
				ELEMENT_TOPIC_POST_DESCRIPTION_DECORATED_INCREASE.replace(
						"${nameItem}", tex_message).replace("\n", ""));

		// Edit the post
		By EDIT_POST = By.xpath(mngPost.ELEMENT_POST_EDIT_BUTTON.replace(
				"${postContent}", post).replace("\n", ""));
		click(EDIT_POST);
		waitForAndGetElement(mngPost.ELEMENT_POST_POPUP_EDIT);
		pressGroupKeysUsingRobot(KeyEvent.VK_CONTROL, KeyEvent.VK_A);
		cke.cke_DescrIndent();
		mngPost.savePostContent();

		info("-- Verify the content of the post--");
		waitForAndGetElement(
				ELEMENT_TOPIC_POST_DESCRIPTION_NORMAL.replace("${nameItem}",
						post).replace("\n", ""));

		waitForAndGetElement(
				ELEMENT_TOPIC_POST_DESCRIPTION_NORMAL.replace("${nameItem}",
						newPost).replace("\n", ""));

		waitForAndGetElement(
				ELEMENT_TOPIC_POST_DESCRIPTION_NORMAL.replace("${nameItem}",
						editTopic).replace("\n", ""));

		waitForAndGetElement(
				ELEMENT_TOPIC_POST_DESCRIPTION_NORMAL.replace("${nameItem}",
						tex_message).replace("\n", ""));

		info("-- The test is successfull--");
		// delete the data test
		goToForumHome();
		click(By.linkText(category));
		mngCat.deleteCategoryInForum(category, true);
	}
}

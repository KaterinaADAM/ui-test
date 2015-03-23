package org.exoplatform.selenium.platform.forum;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.PlatformPermission;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.exoplatform.selenium.TestLogger.info;

public class ForumTopicManagement extends PlatformBase {
	
	public By ELEMENT_POST_REPLY =By.xpath("//*[@id='UITopicDetail']//*[@class='pull-left actionContainer']/a[contains(text(),'Post Reply')]");
	public final String ELEMENT_TOPIC_POST_TITLE = "//*[@class='postViewTitle']/..//*[contains(text(),'${name}')]";
	public final String ELEMENT_TOPIC_POST_CONTENT = "//*[@class='postContentContainer']/..//*[contains(text(),'${name}')]";
	//Action bar
	public final By ELEMENT_ACTIONBAR_TOPIC_TAG = By.xpath("//*[@class='uiIconTag uiIconLightGray']");
	public final By ELEMENT_ACTIONBAR_TOPIC_TAGNAME = By.xpath("//*[@id='AddTag']");
	public final By ELEMENT_ACTIONBAR_TOPIC_RATE = By.xpath("//*[@class='uiIconForumStar uiIconForumLightGray']");
	public final String ELEMENT_ACTIONBAR_TOPIC_TAGPRESENT = ".//*[@id='UITopicDetail']//a[@data-original-title='${tag}']";
	
	//Tag of topic
    public final By ELEMENT_FORUM_TOPIC_ADD_TAG = By.xpath(".//*[@id='UITopicDetail']//a[contains(text(),'Add Tag')]");
	
    //Rate
    public final String ELEMENT_FORUM_VOTE_MARK = "//*[@data-original-title='${star}']";
	public String ELEMENT_POST_TITLE =".//*[@class='postViewTitle'][contains(text(),'${title}')]";
	
	//More Action menu
	public final By ELEMENT_MORE_ACTION = By.xpath("//*[@data-toggle='dropdown']/*[@class='uiIconSettings uiIconLightGray']");
	public final By ELEMENT_EDIT_TOPIC = By.xpath(".//*[@id='UITopicDetail']//a[contains(text(),'Edit')]");
	public final By ELEMENT_DELETE_TOPIC = By.xpath(".//*[@id='UITopicDetail']//a[contains(text(),'Delete')]");
	public final By ELEMENT_MOVE_TOPIC = By.xpath(".//*[@id='UITopicDetail']//a[contains(text(),'Move')]");
	public final By ELEMENT_CLOSE_TOPIC = By.xpath(".//*[@id='UITopicDetail']//a[contains(text(),'Close')]");
	public final By ELEMENT_OPEN_TOPIC = By.xpath(".//*[@id='UITopicDetail']//a[contains(text(),'Open')]");
	public final By ELEMENT_LOCK_TOPIC = By.xpath(".//*[@id='UITopicDetail']//a[contains(text(),'Lock')]");
	public final By ELEMENT_UNLOCK_TOPIC = By.xpath(".//*[@id='UITopicDetail']//a[contains(text(),'Unlock')]");
	public final By ELEMENT_ADD_POLL = By.xpath(".//*[@id='UITopicDetail']//a[contains(text(),'Add Poll')]");
	//public final By ELEMENT_FORUM_ICON_CLOSE = By.xpath("//*[@class='uiIconMinus']");
	
	// Post on a topic
	public final By ELEMENT_TOPIC_POST_A_REPLY_TITLE = By.id("PostTitle");
	public final By ELEMENT_TOPIC_CANCEL_A_POST = By.xpath("//*[@id='UIPostForm']//*[contains(text(),'Cancel')]");
	public final By ELEMENT_FORUM_ADDPOST = By.xpath("//*[@class='pull-left actionContainer']//*[@class='uiPostReplyIcon btn btn-primary']");
	public final By ELEMENT_FORUM_POST_TITLE = By.xpath("//*[@id='PostTitle']");
	public final By ELEMENT_FORUM_MESSAGE = By.xpath("//iframe[@class='cke_wysiwyg_frame cke_reset']");
	public final By ELEMENT_FORUM_SETTINGS_SUBMIT = By.xpath("//*[text()='Submit']");
	public final By ELEMENT_FORUM_ADDTOPIC = By.xpath("//*[@id='UITopicContainer']/div[2]//*[@class='uiIconForumCreateTopic uiIconForumWhite']");
	public final By ELEMENT_FORUM_TOPIC_TITLE = By.xpath("//*[@id='ThreadTitle']");
	
	//Start Topic popup
	public final By ELEMENT_START_TOPIC_POPUP_TITLE = By.xpath(".//*[@id='UIForumPopupWindow']//span[@class='PopupTitle popupTitle']");
	public final By ELEMENT_START_TOPIC_POPUP_TITLE_FILED = By.id("ThreadTitle");
   
	// reply post form 
    public final By ELEMENT_TITLE_POST = By.id("PostTitle");
    public final By ELEMENT_POST_CONTENT = By.xpath("//iframe[@class='cke_wysiwyg_frame cke_reset']");
    public final By ELEMENT_POST_FORM_SUBMIT = By.xpath("//*[@id='UIPostForm']//*[contains(text(),'Submit')]");
    public final String ELEMENT_POST_IN_TOPIC = "//*[@class='postViewTitle' and contains(text(),'{$title}')]/../..//*[@class='postContent']//*[contains(text(),'{$content}')]";
    public final String ELEMENT_POST_IN_TOPIC_QUOTE = "//*[@class='postViewTitle' and contains(text(),'{$title}')]/../..//*[@class='contentQuote']//*[contains(text(),'{$content}')]";
    public final String ELEMENT_POST_IN_TOPIC_PRIVATE = "//*[@class='postViewTitle' and contains(text(),'{$title}')]//*[contains(text(),'Post Private')]/../../..//*[contains(text(),'{$content}')]";
    public final By ELEMENT_TOPIC_POST_REPLY_BOTTOM= By.xpath(".//*[@id='UITopicDetail']/div[5]//a[text()='Post Reply']");
    public final By ELEMENT_TOPIC_POST_REPLY_BUTTON_DISABLE=By.xpath(".//*[@id='UITopicDetail']//*[@data-original-title='You cannot reply to this topic.']");
    //New post popup
    public final By ELEMENT_TOPIC_NEW_POST_TITLE= By.xpath(".//*[@id='UIForumPopupWindow']//span[text()='New Post']");
    public final By ELEMENT_TOPIC_NEW_POST_TITLE_FIELD=By.id("PostTitle");
    public final By ELEMENT_START_TOPIC_MESSAGE_FRAME_CKEDITOR = By.xpath("//iframe[@class='cke_wysiwyg_frame cke_reset']");
    public final By ELEMENT_START_TOPIC_ATTACH_FILE =By.xpath("//*[@id='ThreadContent']//*[@class='uiIconAttach uiIconLightGray']");
    public final By ELEMENT_UPLOAD_POPUP_FILE = By.xpath("//span[@class='PopupTitle popupTitle' and text()='Attach File']");
    public final String ELEMENT_TOPIC_REPPLY_CONTENT=".//*[contains(text(),'${content}')]";
    
    // foot page of post
    public final String ELEMENT_EDIT_POST = "//*[@class='postViewTitle' and contains(text(),'{$title}')]/../..//*[@data-original-title='Edit This Post']";
	public final String ELEMENT_QUOTE_POST = "//*[@class='postViewTitle' and contains(text(),'{$title}')]/../..//*[@data-original-title='Reply with Quote']";
    public final String ELEMENT_DELETE_POST = "//*[@class='postViewTitle' and contains(text(),'{$title}')]/../..//*[@data-original-title='Delete This Post']";
	public final String ELEMENT_PRIVATE_POST = "//*[@class='postViewTitle' and contains(text(),'{$title}')]/../..//*[@data-original-title='Private Reply']";

    public final By ELEMENT_DELETE_BOX_CONFIRMATION = By.xpath("//*[@id='UIForumPopupConfirmation']//*[contains(text(),'OK')]");
    
	//  Poll
    public final By ELEMENT_POLL_QUESTION = By.id("Question");
    public final By ELEMENT_POLL_OPTIONS0 = By.id("Option0");
    public final By ELEMENT_POLL_OPTIONS1 = By.id("Option1");
    public final By ELEMENT_POLL_SUBMIT = By.xpath("//*[@id='UIPollForm']//*[contains(text(),'Submit Poll')]");
    public final By ELEMENT_MORE_ACTIONS_POLL = By.xpath("//*[@class='uiIconPoll uiIconLightGray']/../..//*[@class='uiIconSettings uiIconLightGray']");
    public final By ELEMENT_EDIT_POLL = By.xpath("//*[@id='UITopicPoll']//*[contains(text(),'Edit')]");
    public final By ELEMENT_REMOVE_POLL = By.xpath("//*[@id='UITopicPoll']//*[contains(text(),'Remove')]");
    public final By ELEMENT_CLOSE_POLL = By.xpath(".//*[@id='UITopicPoll']//a[contains(text(),'Close')]");
    public final By ELEMENT_OPEN_POLL = By.xpath(".//*[@id='UITopicPoll']//a[contains(text(),'Reopen')]");
    public final By ELEMENT_POLL_POPUP_TITLE= By.xpath(".//*[@id='UIForumPopupWindow']//span[text()='Poll']");
    public final String ELEMENT_FORUM_POLL_DISPLAYOPT = "//*[contains(text(),'${opt}')]";
    public final By ELEMENT_OK_DELETE = By.xpath("//*[@id='UIForumPopupConfirmation']//*[contains(text(),'OK')]");
	public final By ELEMENT_SUBMIT_BUTTON = By.xpath("//*[text()='Submit']");
	public final By ELEMENT_FORUM_POLL_GRID = By.xpath("//*[@class='uiGrid table no-border-cell rounded-corners table-striped tableVoting']");
	public final By ELEMENT_FORUM_POLL_GRIDCLOSE = By.xpath("//*[@class='uiGrid table rounded-corners table-striped tableVoted']");
	
	//move a topic
	public String ELEMENT_UI_POPUP_MOVE_TOPIC ="//*[@id='MoveTopicForm']//*[@class='node']//*[contains(text(),'{$forum}')]";
	public final String ELEMENT_MOVE_POPUP_COLLASPE_NODE =".//*[@class='uiIconNode collapseIcon'][contains(.,'${category}')]";
	
	
	PlatformPermission per;
	ManageAlert alert;
	Button button;
	/**
	 * constructor
	 * @param dr
	 */
	public ForumTopicManagement(WebDriver dr){
		this.driver=dr;
		alert = new ManageAlert(driver);
		button = new Button(driver);
	}
	/**
	 * Move a topic to a forum
	 * @param category
	 * @param forum
	 */
	public void moveTopicToForum(String category,String forum){
		info("if not found forum");
		if(waitForAndGetElement(ELEMENT_UI_POPUP_MOVE_TOPIC.replace("{$forum}",forum), 3000,0)==null){
			info("Click on Category to expand tree");
			click(ELEMENT_MOVE_POPUP_COLLASPE_NODE.replace("${category}",category));
			info("Select the forum");
		    click(ELEMENT_UI_POPUP_MOVE_TOPIC.replace("{$forum}",forum));
		}else{
			info("Select the forum");
			click(ELEMENT_UI_POPUP_MOVE_TOPIC.replace("{$forum}",forum));
		}
		info("Finish moving");
	}
	
	/**
	 * Open More Action menu of topic
	 * By QuynhPT
	 */
	public void openMoreActionMenu(){
		info("Wait More link is shown");
		waitForAndGetElement(ELEMENT_MORE_ACTION);
		info("Click on More link");
		click(ELEMENT_MORE_ACTION);
	}
	/**
	 * list sublinks in More Action menu of Topic
	 * @author quynhpt
	 *
	 */
	public enum specifMoreActionMenuTopic{
		EDIT,ADD_POLL,LOCK,UNLOCK,CLOSE,OPEN,STICK,SPLIT,MOVE,DELETE,WATCHES;
	}
	/**
	 * select a item in More Action menu
	 * By QuynhPT
	 * @param item
	 */
    public void selectItemMoreActionMenuTopic(specifMoreActionMenuTopic item){
    	openMoreActionMenu();
    	info("Select a link in More menu of the topic");
    	switch(item) {
    	case ADD_POLL:
    		info("Click on Add poll button");
    		click(ELEMENT_ADD_POLL);
    		break;
		case EDIT:
			info("Click on Edit topic button");
			click(ELEMENT_EDIT_TOPIC);
			break;
		case DELETE:
			info("Click on Delete topic button");
			click(ELEMENT_DELETE_TOPIC);
			Utils.pause(1000);
			info("Verify that confirm popup is shown");
			alert.waitForMessage("Are you sure you want to delete this topic ?");
			info("Click on OK on Confirm popup");
			click(ELEMENT_OK_DELETE);
			break;
		case WATCHES:
			break;
		case LOCK:
			info("Click on lock topic button");
			click(ELEMENT_LOCK_TOPIC);
			break;
		case UNLOCK:
			info("Click on unlock topic button");
			click(ELEMENT_UNLOCK_TOPIC);
			break;
		case MOVE:
			info("Wait Move topic link is shown");
			waitForAndGetElement(ELEMENT_MOVE_TOPIC);
			info("Click on move topic link");
			click(ELEMENT_MOVE_TOPIC);
			break;
		case CLOSE:
			info("Click on Close");
			click(ELEMENT_CLOSE_TOPIC);
			break;
		case OPEN:
			info("Click on Close");
			click(ELEMENT_OPEN_TOPIC);
			break;
		case STICK:
			break;
		case SPLIT:
			break;
		default:
			break;

		}
	}
    /**
     * Open More Action menu of poll portlet
     */
    public void openMoreActionMenuPoll(){
    	info("Wait More link is shown");
		waitForAndGetElement(ELEMENT_MORE_ACTIONS_POLL);
		info("Click on More link");
		click(ELEMENT_MORE_ACTIONS_POLL);
    }
	/**
	 * list sublinks of MoreAction of Poll portlet
	 * @author quynhpt
	 *
	 */
    public enum specifMoreActionMenuPoll{
		EDIT,CLOSE,OPEN,REMOVE;
    }
    /**
     * Select a action in More Action menu of Poll portlet
     * @param item
     */
    public void selectItemMoreActionMenuPoll(specifMoreActionMenuPoll item){
    	openMoreActionMenuPoll();
    	info("Select a link in More menu of the poll");
    	switch(item) {
		case EDIT:
			info("Click on Edit link");
			click(ELEMENT_EDIT_POLL);
			break;
		case CLOSE:
			info("Click on Close link");
			click(ELEMENT_CLOSE_POLL);
			break;
		case OPEN:
			info("Click on Open link");
			click(ELEMENT_OPEN_POLL);
			break;
		case REMOVE:
			info("Click on Remove link");
			click(ELEMENT_REMOVE_POLL);
			break;
		default:
			break;

		}
	}
    /**
     * Close or open a poll 
     * @param isClose = true if the poll is closed
     *                = false if the poll is opened
     */
    public void closeOpenPoll(boolean isClose){
    	if(isClose){
    		info("Close the poll");
    		selectItemMoreActionMenuPoll(specifMoreActionMenuPoll.CLOSE);
    		waitForAndGetElement(ELEMENT_FORUM_POLL_GRIDCLOSE);
    	}else {
    		info("Open the poll");
    		selectItemMoreActionMenuPoll(specifMoreActionMenuPoll.OPEN);
    		waitForAndGetElement(ELEMENT_FORUM_POLL_GRID);
    	}
    	
    }
    
    /**
     * Post a reply
     * @param title
     * @param content
     */
    public void postReply(String title, String content){
    	click(ELEMENT_POST_REPLY);
		if (!title.isEmpty())
			type(ELEMENT_TITLE_POST,title,true);
		inputFrame(ELEMENT_POST_CONTENT, content);
		click(ELEMENT_POST_FORM_SUBMIT);
    }
    
    /**
     * Edit a post
     * @param title
     * @param newTitle
     * @param newContent
     */
    public void editPost(String title, String newTitle,String newContent){
    	click(ELEMENT_EDIT_POST.replace("{$title}", title));
    	if (!newTitle.isEmpty())
			type(ELEMENT_TITLE_POST,newTitle,true);
    	if(!newContent.isEmpty())
    		inputFrame(ELEMENT_POST_CONTENT, newContent);
    	click(ELEMENT_POST_FORM_SUBMIT);
    }
    
    /**
     * Quote a post 
     * @param title
     * @param newContent
     */
    public void quotePost(String title,String newContent){
    	click(By.xpath(ELEMENT_QUOTE_POST.replace("{$title}",title)));
    	
    	if(newContent !="")
    		inputFrame(ELEMENT_POST_CONTENT, newContent);
    	
    	click(ELEMENT_POST_FORM_SUBMIT);
    }
    
    /**
     * Create a private post
     * @param titlePost
     * @param newTitle
     * @param content
     */
    public void privatePost(String titlePost, String newTitle, String content){
    	click(By.xpath(ELEMENT_PRIVATE_POST.replace("{$title}",titlePost)));
    	
    	if(newTitle !="")
			type(ELEMENT_TITLE_POST,newTitle,true);
    	
    	if(content !="")
    		inputFrame(ELEMENT_POST_CONTENT, content);
    	
    	click(ELEMENT_POST_FORM_SUBMIT);
    }
    
	/**
	 * Add poll
	 * @param question
	 * @param option1
	 * @param option2
	 */
    public void addPoll(String question, String option1, String option2){
    	selectItemMoreActionMenuTopic(specifMoreActionMenuTopic.ADD_POLL);
    	info("Input a question to poll");
    	type(ELEMENT_POLL_QUESTION,question,true);
    	info("Input an option 1 to poll");
    	type(ELEMENT_POLL_OPTIONS0,option1,true);
    	info("Input an option 2 to poll");
    	type(ELEMENT_POLL_OPTIONS1,option2,true);
    	info("Click on Submit button");
    	click(ELEMENT_POLL_SUBMIT);
    	info("Finished adding poll");
    }
    /**
	 * Lock or Unlock a topic
	 * By QuynhPT
	 * @param islock =true if a topic is locked
	 *               =false if a topic is unlocked
	 */
	public void lockUnlockTopic(boolean islock){
		if (islock) {
			selectItemMoreActionMenuTopic(specifMoreActionMenuTopic.LOCK);
			waitForAndGetElement(ELEMENT_TOPIC_POST_REPLY_BUTTON_DISABLE);
		}else {
			selectItemMoreActionMenuTopic(specifMoreActionMenuTopic.UNLOCK);
			waitForAndGetElement(ELEMENT_TOPIC_POST_REPLY_BOTTOM);
		}
	}
	/**
	 * Close or Open a topic
	 * @param isClose  =true if a topic is closed
	 *                 = false if a topic is opened
	 */
	public void closeOpenTopic(boolean isClose){
		if(isClose){
			selectItemMoreActionMenuTopic(specifMoreActionMenuTopic.CLOSE);
			waitForAndGetElement(ELEMENT_TOPIC_POST_REPLY_BUTTON_DISABLE);
		}else{
			selectItemMoreActionMenuTopic(specifMoreActionMenuTopic.OPEN);
			waitForAndGetElement(ELEMENT_TOPIC_POST_REPLY_BOTTOM);
		}
		
	}
    /**
     * Edit Poll
     * @param question
     * @param option1
     * @param option2
     */
    public void editPoll(String question,String option1, String option2){
    	selectItemMoreActionMenuPoll(specifMoreActionMenuPoll.EDIT);
    	info("Refresh the page");
    	driver.navigate().refresh();
    	waitForAndGetElement(ELEMENT_POLL_POPUP_TITLE);
    	info("Input a question to poll");
    	waitForAndGetElement(ELEMENT_POLL_QUESTION).clear();
    	type(ELEMENT_POLL_QUESTION,question,true);
    	info("Input an option 1 to poll");
    	waitForAndGetElement(ELEMENT_POLL_OPTIONS0).clear();
    	type(ELEMENT_POLL_OPTIONS0,option1,true);
    	info("Input an option 2 to poll");
    	waitForAndGetElement(ELEMENT_POLL_OPTIONS1).clear();
    	type(ELEMENT_POLL_OPTIONS1, option2,true);
    	info("Click on Submit button");
    	click(ELEMENT_POLL_SUBMIT);
    	info("Finished adding poll");
    }
	
    /**
     * Delete the poll of the topic
     */
    public void deletePoll(){
    	selectItemMoreActionMenuPoll(specifMoreActionMenuPoll.REMOVE);
    	info("Click on Ok button");
    	click(ELEMENT_OK_DELETE);
    	info("Finish deleting poll");
    }
    /**
     * Delete a topic
     */
    public void deleteTopic(){
    	info("Delete the topic");
    	selectItemMoreActionMenuTopic(specifMoreActionMenuTopic.DELETE);
    }
    
    /**
     * Add a tag gor topic
     * @param name
     */
	public void addATag(String name) {
		click(ELEMENT_ACTIONBAR_TOPIC_TAG);
		type(ELEMENT_ACTIONBAR_TOPIC_TAGNAME, name, true);
		click(ELEMENT_FORUM_TOPIC_ADD_TAG);
		waitForAndGetElement(ELEMENT_ACTIONBAR_TOPIC_TAGPRESENT.replace("${tag}", name));
	}

	/**
	 * addPostSimple
	 * @param name
	 * @param message
	 * 
	 */
	public void addPostSimple(String name, String message) {
		click(ELEMENT_FORUM_ADDPOST);
		type(ELEMENT_FORUM_POST_TITLE, name, true);
		inputFrame(ELEMENT_FORUM_MESSAGE , message);
		switchToParentWindow();
		click(ELEMENT_FORUM_SETTINGS_SUBMIT);
	}
	
	/**
	 * addTopicSimple
	 * @param name
	 * @param message
	 * 
	 */
	public void addTopicSimple(String name, String message) {
		click(ELEMENT_FORUM_ADDTOPIC);
		type(ELEMENT_FORUM_TOPIC_TITLE, name, true);
		inputFrame(ELEMENT_FORUM_MESSAGE , message);
		switchToParentWindow();
		click(ELEMENT_FORUM_SETTINGS_SUBMIT);
	}
	
	/**
	 * Reply the topic
	 * @param newTitle
	 * @param newMessg
	 * @param pathFile
	 * @param fileName
	 */
	public void replyTopic(String newTitle,String newMessg,String pathFile,String fileName){
		info("Click on Post Reply button"); 
		click(ELEMENT_TOPIC_POST_REPLY_BOTTOM);
		info("Verify that the pop up is shown");
		waitForAndGetElement(ELEMENT_TOPIC_NEW_POST_TITLE);
		info("Refresh the page");
		this.driver.navigate().refresh();
		if(!newTitle.isEmpty()){
			info("Input the title:"+newTitle);
			waitForAndGetElement(ELEMENT_TOPIC_NEW_POST_TITLE_FIELD).clear();
			type(ELEMENT_TOPIC_NEW_POST_TITLE_FIELD,newTitle, true);
		}
		
		if (!newMessg.isEmpty()){
			info("Input the message:"+newMessg);
			inputFrame(ELEMENT_START_TOPIC_MESSAGE_FRAME_CKEDITOR,newMessg);
		}
		
		if (!pathFile.isEmpty()|| !fileName.isEmpty()) {
			info("click on Attached file button");
			click(ELEMENT_START_TOPIC_ATTACH_FILE);
			info("Verify that upload button is shown");
			waitForAndGetElement(ELEMENT_UPLOAD_POPUP_FILE);
			info("Attached file");
			attachFile(pathFile,fileName);
			info("Verify that upload popup is closed");
			waitForElementNotPresent(ELEMENT_UPLOAD_POPUP_FILE);
		}
		info("click on Submit button");
		click(ELEMENT_SUBMIT_BUTTON);
		info("Verify that the replying is created");
		waitForAndGetElement(ELEMENT_TOPIC_REPPLY_CONTENT.replace("${content}", newMessg));
		info("Reply topic successfully");
	}
	
	/**
	 * Start a Topic
	 * By QuynhPT
	 * @param title
	 * @param message
	 */
	public void startTopic(String title, String message,String pathFile,String fileName) {
		info("Verify that the pop up is shown");
		waitForAndGetElement(ELEMENT_START_TOPIC_POPUP_TITLE_FILED);
		info("Refresh the page");
		this.driver.navigate().refresh();
		if(!title.isEmpty()){
			info("Input the title:"+title);
			type(ELEMENT_START_TOPIC_POPUP_TITLE_FILED, title, true);
		}
		
		if (!message.isEmpty()){
			info("Input the message:"+message);
			inputFrame(ELEMENT_START_TOPIC_MESSAGE_FRAME_CKEDITOR,message);
		}
		
		if (!pathFile.isEmpty()|| !fileName.isEmpty()) {
			info("click on Attached file button");
			click(ELEMENT_START_TOPIC_ATTACH_FILE);
			info("Verify that upload button is shown");
			waitForAndGetElement(ELEMENT_UPLOAD_POPUP_FILE);
			info("Attached file");
			attachFile(pathFile,fileName);
			info("Verify that upload popup is closed");
			waitForElementNotPresent(ELEMENT_UPLOAD_POPUP_FILE);
		}
		info("click on Submit button");
		click(ELEMENT_SUBMIT_BUTTON,0,true);
		info("Verify that the topic is created");
		waitForAndGetElement(By.linkText(title));
		info("Start topic successfully");
	}
	/**
	 * Rate a topic
	 * @param name
	 */
	public void rateTopic(String name,String starType){
		click(ELEMENT_ACTIONBAR_TOPIC_RATE);
		click(ELEMENT_FORUM_VOTE_MARK.replace("${star}",starType));
		Utils.pause(1000);
	}
	/**
	 * Edit a topic 
	 * @param newTitle
	 * @param newContent
	 */
	public void editTopic(String newTitle,String newContent){
		selectItemMoreActionMenuTopic(specifMoreActionMenuTopic.EDIT);
		if(!newTitle.isEmpty())
		  type(ELEMENT_START_TOPIC_POPUP_TITLE_FILED,newTitle, true);
		if(!newContent.isEmpty())
		  inputFrame(ELEMENT_START_TOPIC_MESSAGE_FRAME_CKEDITOR,newContent);
		info("Click on Submit button");
		click(ELEMENT_SUBMIT_BUTTON,0,true);
		info("All changes are saved");
	}
}

package org.exoplatform.selenium.platform.forum;

import org.exoplatform.selenium.Button;
import static org.exoplatform.selenium.TestLogger.info;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.HomePageActivity;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author lientm
 * @date 27 Aug 2013
 */
public class AnswerManageComment extends AnswerBase {

	HomePageActivity hpAct;
	AnswerManageAnwser mAns;
	
	public AnswerManageComment(WebDriver dr,String...plfVersion){
		this.plfVersion = plfVersion.length>0?plfVersion[0]:"4.0";
		driver = dr;
		button = new Button(driver);
		alert = new ManageAlert(driver);
		hpAct = new HomePageActivity(driver);
		mAns = new AnswerManageAnwser(driver);
	}

	AnswerManageQuestion magQuest = new AnswerManageQuestion(driver);
	AnswerManageAnwser magAns = new AnswerManageAnwser(driver);

	//Add comment
	public final By ELEMENT_COMMENT_LINK_IN_QUESTION = By.xpath("//*[@class='questionAction']//*[contains(text(),'Comment')]");
	public final By ELEMENT_COMMENT_CONTENT_FRAME_1 = By.id("CommentContent___Frame");
	public final By ELEMENT_COMMENT_CONTENT_FRAME_2 = By.xpath("//*[@id ='xEditingArea']/iframe");
	public final By ELEMENT_COMMENT_CONTENT_FRAME_41 = By.xpath("//*[@id='cke_CommentContent']//iframe");
	public final String ELEMENT_COMMENT_IN_QUESTION = "//*[contains(@id, 'Comment')]//*[text()='${comment}']";
	public final By ELEMENT_GET_COMMENT = By.xpath("//*[contains(@id, 'Comment')]//p");

	//More action
	public final String ELEMENT_COMMENT_MORE_ACTION_LINK = "//*[text()='${comment}']/../../../../..//*[contains(text(), 'More Actions')]";
	public final By ELEMENT_EDIT_COMMENT_MENU = By.xpath("//a[contains(.,'Edit Comment')]"); 
	public final String ELEMENT_DELETE_COMMENT_MENU = "//*[contains(text(),'${comment}')]/ancestor::div[contains(@id,'Comment')]//a[contains(.,'Delete Comment')]";
	public final String ELEMENT_PROMOTE_COMMENT_MENU = "//*[contains(text(),'${comment}')]/ancestor::div[contains(@id,'Comment')]//a[contains(.,'Promote as Answer')]";

	//message
	public final String MSG_DELETE_COMMENT = "Are you sure you want to delete this comment ?";

	/*----------------------------common functions---------------------------------*/

	/**
	 * function add comment for a question
	 * @param question
	 * @param comment
	 */
	public void addComment4Question(String question, String comment){
		if (waitForAndGetElement(ELEMENT_COMMENT_LINK_IN_QUESTION, 5000, 0) != null){
			click(ELEMENT_COMMENT_LINK_IN_QUESTION);
		}else {
			rightClickOnElement(By.linkText(question));
			click(magQuest.ELEMENT_COMMENT_LINK_IN_CONTEXT_MENU);
		}
		if (comment != null){
			if(this.plfVersion.equalsIgnoreCase("4.1"))
				inputDataToFrame(ELEMENT_COMMENT_CONTENT_FRAME_41, comment, false);
			else//(this.plfVersion.equalsIgnoreCase("4.0"))
				inputDataToFrameInFrame(ELEMENT_COMMENT_CONTENT_FRAME_1, ELEMENT_COMMENT_CONTENT_FRAME_2, comment, true);
			switchToParentWindow();	
		}
		button.save();
		
		if(!comment.contains("<br/>"))
			waitForAndGetElement(ELEMENT_COMMENT_IN_QUESTION.replace("${comment}", comment));
		else{
			info("text of comment " + getText(ELEMENT_GET_COMMENT));
			hpAct.checkNumberOfLineOfContent(getText(ELEMENT_GET_COMMENT), comment,false);
		}
	}

	/**
	 * function edit comment in question
	 * @param comment
	 * @param newComment
	 */
	public void editComment4Question(String comment, String newComment){
		click(ELEMENT_COMMENT_MORE_ACTION_LINK.replace("${comment}", comment));
		Utils.pause(2000);
		click(ELEMENT_EDIT_COMMENT_MENU);
		if(this.plfVersion.equalsIgnoreCase("4.0"))
			inputDataToFrameInFrame(ELEMENT_COMMENT_CONTENT_FRAME_1, ELEMENT_COMMENT_CONTENT_FRAME_2, newComment, true);
		else
			inputDataToFrame(ELEMENT_COMMENT_CONTENT_FRAME_41,newComment,true);
		switchToParentWindow();
		button.save();
		waitForAndGetElement(ELEMENT_COMMENT_IN_QUESTION.replace("${comment}", newComment));
	}

	/**
	 * function delete comment in question
	 * @param comment
	 */
	public void deleteCommentInQuestion(String comment){
		click(ELEMENT_COMMENT_MORE_ACTION_LINK.replace("${comment}", comment));
		Utils.pause(2000);
		click(ELEMENT_DELETE_COMMENT_MENU.replace("${comment}", comment));
		waitForMessage(MSG_DELETE_COMMENT);
		click(ELEMENT_CONFIMATION_OK_POPUP);
		waitForElementNotPresent(ELEMENT_COMMENT_MORE_ACTION_LINK.replace("${comment}", comment));
	}

	/**
	 * function promote a comment as answer in question
	 * @param comment
	 */
	public void promoteAsAnswer(String comment){
		click(ELEMENT_COMMENT_MORE_ACTION_LINK.replace("${comment}", comment));
		Utils.pause(2000);
		click(ELEMENT_PROMOTE_COMMENT_MENU.replace("${comment}", comment));
		waitForElementNotPresent(ELEMENT_COMMENT_IN_QUESTION.replace("${comment}", comment));
		if(!comment.contains("<br/>"))
			waitForAndGetElement(magAns.ELEMENT_ANSWER_IN_QUESTION.replace("${answer}", comment));
		else
			hpAct.checkNumberOfLineOfContent(getText(mAns.ELEMENT_GET_ANSWER_IN_QUESTION), comment,false);
	}
}

package org.exoplatform.selenium.platform;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.social.MyProfilePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.exoplatform.selenium.TestLogger.info;

public class ConnectionsManagement extends PlatformBase {

	public final By ELEMENT_CONNECTION_EVERYONE_TITLE=By.xpath(".//*[@id='UIAllPeople']//*[contains(text(),'Contacts Directory')]");
	public final String ELEMENT_CONNECTION_CONNECT_BTN = "//a[contains(@href,'${user}')]/../..//*[text()='Connect']";
	public final String ELEMENT_CONNECTION_CANCEL_BTN = "//a[contains(@href,'${user}')]/../..//*[text()='Cancel Request']";
	public final String ELEMENT_CONNECTION_REVOVE_BTN = "//a[contains(@href,'${user}')]/../..//*[text()='Remove Connection']";

	 public final String ELEMENT_CONNECTION_IGNORE_BTN =" //a[contains(@href,'${user}')]/../..//*[text()='Ignore']";
    public final String ELEMENT_CONNECTION_CONFIRM_BTN =" //a[contains(@href,'${user}')]/../..//*[text()='Confirm']";
    public final String ELEMENT_CONNECTION_USER_AVARTAR="//a[contains(@href,'${user}')]/img";
    public final String ELEMENT_CONNECTION_USER_NAME="//a[contains(@href,'${user}') and @data-key='title']";
    
    public final By ELEMENT_ALL_CONNECTIONS_TAB=By.xpath("//*[@id='UIConnectionsPortlet' or @id='UIAllPeoplePortlet']//*[contains(@href,'all-people')]");
    public final By ELEMENT_MY_CONNECTIONS_TAB = By.xpath("//*[@id='UIConnectionsPortlet' or @id='UIAllPeoplePortlet']//*[contains(@href,'network')]");
    public final By ELEMENT_REQUEST_RECEIVE_CONNECTIONS_TAB = By.xpath("//*[@id='UIConnectionsPortlet' or @id='UIAllPeoplePortlet']//*[contains(@href,'receivedInvitations')]");
    public final By ELEMENT_REQUEST_PENDING_CONNECTIONS_TAB = By.xpath("//*[@id='UIConnectionsPortlet' or @id='UIAllPeoplePortlet']//*[contains(@href,'pendingRequests')]");
    
    public final By ELEMENT_ALL_RESULTS = By.id("searchAll");
    public final By ELEMENT_NAME_OF_PEOPLE = By.id("name");
    public final By ELEMENT_POSITIONS_OF_PEOPLE = By.id("position");
    public final By ELEMENT_SKILL_OF_PEOPLE = By.id("skills");
    public final By ELEMENT_SEARCH_BUTTON = By.id("SearchButton");
    public final String ELEMENT_USER_LINK = "//a[contains(text(),'${userName}')]";
    
    MyProfilePage myProf;
    
    public ConnectionsManagement(WebDriver dr){
		this.driver = dr;
		myProf = new MyProfilePage(dr);
    }
		/**
	 * Tab list
	 */
	public enum selectTabOption{
		ALL, MYCONNECTION, RECEIVE, PENDING;
	}

	/**
	 * 
	 * @param option
	 */
	public void goToConnectionTab(selectTabOption option){
		info("Go to tab");
		switch(option){
		case ALL:
			info("Go to all tab");
			click(ELEMENT_ALL_CONNECTIONS_TAB);
			break;
		case MYCONNECTION:
			info("Go to my connection tab");
			click(ELEMENT_MY_CONNECTIONS_TAB);
			break;
		case RECEIVE:
			info("Go to receive tab");
			click(ELEMENT_REQUEST_RECEIVE_CONNECTIONS_TAB);
			break;
		case PENDING:
			info("Go to pending tab");
			click(ELEMENT_REQUEST_PENDING_CONNECTIONS_TAB);
			break;
		default:
			info("Go to all tab");
			click(ELEMENT_ALL_CONNECTIONS_TAB);
			break;
		}
		Utils.pause(1000);
   
	}

	/**
	 * Connect to a user
	 * @param username
	 */
	public void connectToAUser(String username){
		info("--Connect to a user--");
		info("Click on connect button");
		searchPeople(username,null,null,null);
		click(ELEMENT_CONNECTION_CONNECT_BTN.replace("${user}",username));
		waitForAndGetElement(ELEMENT_CONNECTION_CANCEL_BTN.replace("${user}",username),2000,1);
		info("Connected to the user");
	}

	/**
	 * Remove a connection of user
	 * @param username
	 */
	public void removeConnection(String username){
		info("--Remove a connection of a user--");
		info("Click on remove button");
		searchPeople(username,null,null,null);
		click(ELEMENT_CONNECTION_REVOVE_BTN.replace("${user}",username));
		waitForElementNotPresent(ELEMENT_CONNECTION_REVOVE_BTN.replace("${user}",username),2000,1);
		info("Removed to the user");
	}
	/**
	 * Cancel a connection to a user
	 * @param username
	 */
	public void cancelConnection(String username){
		info("--Cancel a connection of a user--");
		info("Click on Cancel button");
		searchPeople(username,null,null,null);
		click(ELEMENT_CONNECTION_CANCEL_BTN.replace("${user}",username));
		waitForElementNotPresent(ELEMENT_CONNECTION_CANCEL_BTN.replace("${user}",username),2000,1);
		info("Canceled to the user");
	}
	/**
	 * Ignore a connection that is sent from a user
	 * @param username
	 */
	public void ignoreConnection(String username){
		info("--Ignore a connection of a user--");
		info("Click on Ignore button");
		searchPeople(username,null,null,null);
		click(ELEMENT_CONNECTION_IGNORE_BTN.replace("${user}",username));
		waitForElementNotPresent(ELEMENT_CONNECTION_IGNORE_BTN.replace("${user}",username),2000,1);
		info("Connected to the user");
	}
	/**
	 * Reset all connections to default status
	 * @param username
	 */
	public void resetConnection(String username){
		searchPeople(username,null,null,null);
		if(waitForAndGetElement(ELEMENT_CONNECTION_REVOVE_BTN.replace("${user}",username),3000,0)!=null)
			removeConnection(username);
		if(waitForAndGetElement(ELEMENT_CONNECTION_CANCEL_BTN.replace("${user}",username),3000,0)!=null)
			cancelConnection(username);
		if(waitForAndGetElement(ELEMENT_CONNECTION_IGNORE_BTN.replace("${user}",username),3000,0)!=null)
			ignoreConnection(username);
	}
	/**
	 * Accept a connection from a user in Connection page
	 * @param username
	 */
	public void acceptAConnection(String username){
		info("--Accept a connection of a user--");
		info("Click on Confirm button");
		searchPeople(username,null,null,null);
		click(ELEMENT_CONNECTION_CONFIRM_BTN.replace("${user}",username));
		waitForAndGetElement(ELEMENT_CONNECTION_REVOVE_BTN.replace("${user}",username));
		info("Accepted to the user");
	}

	/**
	 * Function Verify connection
	 * @param username
	 * @param accept (true: if user accept invitation)
	 */
	public void verifyConnection(String username, Boolean accept){
		click(ELEMENT_MY_CONNECTIONS_TAB);
		//With user confirmed the invitation, user  becomes friend and user's name is displayed on user's network list
		searchPeople(username,null,null,null);
		if (accept)
			waitForAndGetElement(ELEMENT_CONNECTION_REVOVE_BTN.replace("${user}", username));
		else
			waitForElementNotPresent(ELEMENT_CONNECTION_REVOVE_BTN.replace("${user}", username));
	}

	/**
	 * Clear search textbox
	 */
	public void clearSearchTextbox(){
		info("Clear search textbox");
		click(ELEMENT_ALL_RESULTS);
		type(ELEMENT_NAME_OF_PEOPLE,"",true);
		type(ELEMENT_POSITIONS_OF_PEOPLE,"",true);
		type(ELEMENT_SKILL_OF_PEOPLE,"",true);
	}
	/**
	 * Function search people
	 * @param peopleName
	 * @param position
	 * @param skills
	 * @param directory
	 */
	public void searchPeople(String peopleName, String position, String skills, String directory){
		info("-- Searching people ... --");
		if(peopleName!="" && peopleName!=null){
			type(ELEMENT_NAME_OF_PEOPLE, peopleName, true);
		}
		else{
			type(ELEMENT_NAME_OF_PEOPLE, "", true);
		}
		if(position!="" && position!=null){
			type(ELEMENT_POSITIONS_OF_PEOPLE, position, true);
		}
		else{
			type(ELEMENT_POSITIONS_OF_PEOPLE, "", true);
		}
		if(skills!="" && skills!=null){
			type(ELEMENT_SKILL_OF_PEOPLE, skills, true);
		}
		else{
			type(ELEMENT_SKILL_OF_PEOPLE, "", true);
		}
		click(ELEMENT_SEARCH_BUTTON);
		if (directory!="" && directory!=null)
			click(By.linkText(directory));
		Utils.pause(1000);
	}
	/**
	 * Go to User
	 * @param userName
	 */
	public void goToUser(String userName){
		info("Go to User profile page");
		searchPeople(userName, "", "", "");
		click(ELEMENT_USER_LINK.replace("${userName}", userName));
		waitForAndGetElement(myProf.ELEMENT_NAME_OF_PROFILE_TOP_LEFT.replace("${name}", userName));
	}
}
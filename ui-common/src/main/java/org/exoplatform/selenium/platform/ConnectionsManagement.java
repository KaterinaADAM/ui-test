package org.exoplatform.selenium.platform;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.exoplatform.selenium.TestLogger.info;

public class ConnectionsManagement extends PlatformBase {

	public final By ELEMENT_CONNECTION_EVERYONE_TITLE=By.xpath(".//*[@id='UIAllPeople']//h4[contains(text(),'Contacts Directory')]");
    public final String ELEMENT_CONNECTION_EVERYONE_CONNECT_BTN = ".//*[contains(@href,'${user}')]/parent::*//*[text()='Connect']";
	public final String ELEMENT_CONNECTION_EVERYONE_CANCEL_BTN = ".//*[contains(@href,'${user}')]/parent::*//*[text()='Cancel Request']";
	public final String ELEMENT_CONNECTION_EVERYONE_REVOVE_BTN = ".//*[contains(@href,'${user}')]/parent::*//*[text()='Remove Connection']";
    public final String ELEMENT_CONNECTION_EVERYONE_IGNORE_BTN =".//*[contains(@href,'${user}')]/parent::*//*[text()='Ignore']";
    public final String ELEMENT_CONNECTION_EVERYONE_CONFIRM_BTN =".//*[contains(@href,'${user}')]/parent::*//*[text()='Confirm']";
    
    public ConnectionsManagement(WebDriver dr){
		driver = dr;
	}

    /**
     * Connect to a user
     * @param username
     */
    public void connectToAUser(String username){
    	info("--Connect to a user--");
    	info("Click on connect button");
    	click(ELEMENT_CONNECTION_EVERYONE_CONNECT_BTN.replace("${user}",username),0,true);
    	waitForAndGetElement(ELEMENT_CONNECTION_EVERYONE_CANCEL_BTN.replace("${user}",username),2000,0);
    	info("Connected to the user");
    }
    
    /**
     * Remove a connection of user
     * @param username
     */
    public void removeConnection(String username){
    	info("--Remove a connection of a user--");
    	info("Click on remove button");
    	click(ELEMENT_CONNECTION_EVERYONE_REVOVE_BTN.replace("${user}",username));
    	waitForAndGetElement(ELEMENT_CONNECTION_EVERYONE_CONNECT_BTN.replace("${user}",username),2000,0);
    	info("Removed to the user");
    }
    /**
     * Cancel a connection to a user
     * @param username
     */
    public void cancelConnection(String username){
    	info("--Cancel a connection of a user--");
    	info("Click on Cancel button");
    	click(ELEMENT_CONNECTION_EVERYONE_CANCEL_BTN.replace("${user}",username));
    	waitForAndGetElement(ELEMENT_CONNECTION_EVERYONE_CONNECT_BTN.replace("${user}",username),2000,0);
    	info("Canceled to the user");
    }
    /**
     * Ignore a connection that is sent from a user
     * @param username
     */
    public void ignoreConnection(String username){
    	info("--Ignore a connection of a user--");
    	info("Click on Ignore button");
    	click(ELEMENT_CONNECTION_EVERYONE_IGNORE_BTN.replace("${user}",username));
    	waitForAndGetElement(ELEMENT_CONNECTION_EVERYONE_CONNECT_BTN.replace("${user}",username),2000,0);
    	info("Canceled to the user");
    }
    /**
     * Reset all connections to default status
     * @param username
     */
    public void resetConnection(String username){
    	if(waitForAndGetElement(ELEMENT_CONNECTION_EVERYONE_REVOVE_BTN.replace("${user}",username),3000,0)!=null)
    		removeConnection(username);
       if(waitForAndGetElement(ELEMENT_CONNECTION_EVERYONE_CANCEL_BTN.replace("${user}",username),3000,0)!=null)
    	   cancelConnection(username);
       if(waitForAndGetElement(ELEMENT_CONNECTION_EVERYONE_IGNORE_BTN.replace("${user}",username),3000,0)!=null)
    	   ignoreConnection(username);
	}
    /**
     * Accept a connection from a user in Connection page
     * @param username
     */
    public void acceptAConnection(String username){
    	info("--Accept a connection of a user--");
    	info("Click on Confirm button");
    	click(ELEMENT_CONNECTION_EVERYONE_CONFIRM_BTN.replace("${user}",username));
    	waitForAndGetElement(ELEMENT_CONNECTION_EVERYONE_REVOVE_BTN.replace("${user}",username),2000,0);
    	info("Canceled to the user");
    }
}
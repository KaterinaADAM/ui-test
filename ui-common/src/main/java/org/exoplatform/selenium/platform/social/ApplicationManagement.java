package org.exoplatform.selenium.platform.social;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.exoplatform.selenium.TestLogger.*;
/**
 * 
 * @author thaopth
 * Date: 08/11/2012
 */

public class ApplicationManagement extends SocialBase {
	
	//Go to My Spaces > Select a space > Settings
	//Applications Tab 
	public final By ELEMENT_SPACE_NAVIGATION = By.xpath("//div[text()='Applications']");
	public final By ELEMENT_ADD_APPLICATION_BUTTON = By.xpath("//button[text()='Add Application']");
	public final By ELEMENT_ADD_APP_POPUP = By.xpath("//span[text()='Space Application Installer']");
	public final By ELEMENT_CLOSE_BUTTON = By.xpath("//div[@id='UIAddApplication']/div/a[@title='Close Window']");
	public final String ELEMENT_SELECT_APPLICATION = "//strong[text()='${applicationTitle}']/../../a[text()='Add']";
	public final String ELEMENT_DELETE_APPLICATION = "//div[@class='communityContainer']/*[text()='${applicationTitle}']/../../a[contains(@class,'uiIconClose')]";

	public ApplicationManagement(WebDriver dr) {
		driver = dr;
	}

	//Add application for space
	public void addApplication(String categoryName, String applicationTitle) {
		goToApplications();
		info("----Click add application button----");
		waitForAndGetElement(ELEMENT_ADD_APPLICATION_BUTTON);
		click(ELEMENT_ADD_APPLICATION_BUTTON);
		info("---Select category----");
		waitForAndGetElement(ELEMENT_ADD_APP_POPUP);
		click(By.id(categoryName));
		info("-------Add application-------");
		waitForAndGetElement(ELEMENT_SELECT_APPLICATION.replace("${applicationTitle}", applicationTitle));
		click(By.xpath((ELEMENT_SELECT_APPLICATION.replace("${applicationTitle}", applicationTitle))));
		info("---Verify new application is added");
		info("----Close add application pop up-----");
		click(ELEMENT_CLOSE_BUTTON);
		waitForElementNotPresent(ELEMENT_CLOSE_BUTTON);
		waitForAndGetElement(ELEMENT_DELETE_APPLICATION.replace("${applicationTitle}", applicationTitle));
	}
	
	//Delete application
	public void removeApplication(String applicationTitle){
		info("----Go to Space application ---");
		goToApplications();
		info("-----Click delete button-----");
		click(By.xpath((ELEMENT_DELETE_APPLICATION.replace("${applicationTitle}", applicationTitle))));
		info("----Verify application is deleted----");
		waitForElementNotPresent((ELEMENT_DELETE_APPLICATION.replace("${applicationTitle}", applicationTitle)),60000);
	}
}

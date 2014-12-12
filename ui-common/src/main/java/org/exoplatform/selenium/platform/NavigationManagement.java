package org.exoplatform.selenium.platform;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.Utils;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NavigationManagement extends  PlatformBase{

	public NavigationManagement(WebDriver dr, String...plfVersion){
		driver = dr;
		this.plfVersion = plfVersion.length>0?plfVersion[0]:"4.0";
		button = new Button(driver, this.plfVersion);
	}

	ManageAlert alt = new ManageAlert(driver);

	//public String ELEMENT_PAGE_MANAGEMENT_SEARCH_BUTTON = "//form[@id='UIPageSearchForm']/div[2]/a[@class='SearchIcon']";
	public  final By ELEMENT_INPUT_POPUP_SEARCH_TITLE = By.xpath("//div[contains(@class, 'uiPageSearch')]//input[@id='pageTitle']"); 
	//public  final By ELEMENT_SELECT_PAGE = By.xpath("//div[@id='UIRepeater']//table//tbody/tr/td[5]/div[@class='ActionContainer']/img");

	// Add a node for portal at portal navigation
	public void addNodeForPortal(String currentNavigation, String currentNodeLabel, boolean useAddNodeLink, String nodeName, boolean extendedLabelMode,
			Map<String, String> languages, String nodeLabel, String pageName, String pageTitle, boolean verifyPage, boolean verifyNode){
		button = new Button(driver);
		String currentNode="";
		if(currentNodeLabel!="") {
			currentNode = ELEMENT_NODE_LINK.replace("${nodeLabel}", currentNodeLabel); 
		}
		else {
			currentNode = "//*[@class='uiIconUpLevel uiIconLightGray']"; 
		}
		//		if (!verifyFirstLevel) {
		//		//String node = ELEMENT_NODE_LINK.replace("${nodeLabel}", nodeLabel);
		//		currentNode = ELEMENT_NODE_LINK.replace("${nodeLabel}", currentNodeLabel); } else {
		//			click(ELEMENT_SITE_NAVIGATION_FIRST_LEVEL);
		//			currentNode = ELEMENT_SITE_NAVIGATION_FIRST_LEVEL_AREA;
		//		}
		if (waitForAndGetElement(ELEMENT_TITLE_NAVIGATION_MANAGEMENT, 5000, 0) == null){
			editNavigation(currentNavigation);
		}
		info("--Add new node at navigation--");	
		if (useAddNodeLink){
			click(currentNode);
			click(ELEMENT_ADD_NODE_LINK);
		}else{

			click(currentNode);
			Utils.pause(500);
			if(currentNodeLabel!="")
				rightClickOnElement(currentNode);
			else
				rightClickOnElement("//*[@class='homeNode clearfix']");
			if (currentNode.equals(ELEMENT_NAVIGATION_HOME_NODE)) {
				click(ELEMENT_NODE_ADD_NEW_TOP_NODE);
			} else {
				if(currentNodeLabel!="")
					click(ELEMENT_NODE_ADD_NEW);
				else
					click(ELEMENT_NODE_ADD_NEW_1);
			}	
		}
		//waitForTextPresent("Page Node Settings");
		waitForAndGetElement(ELEMENT_INPUT_NAME);
		type(ELEMENT_INPUT_NAME, nodeName, true);

		if (extendedLabelMode) {
			for (String language : languages.keySet()) {
				select(ELEMENT_SELECT_LANGUAGE, language);
				Utils.pause(500);
			}
		} else {
			uncheck(ELEMENT_CHECKBOX_EXTENDED_LABEL_MODE);
			type(ELEMENT_INPUT_LABEL, nodeLabel, true);
		}

		click(ELEMENT_PAGE_SELECTOR_TAB);

		if (pageName != "") {
			info("-- Create new page --");
			type(ELEMENT_INPUT_PAGE_NAME, pageName, true);
			type(ELEMENT_INPUT_PAGE_TITLE, pageTitle, true);
			click(ELEMENT_CREATE_PAGE_LINK);
			if (verifyPage) {
				waitForElementNotPresent(ELEMENT_CREATE_PAGE_LINK);
			} else {
				return;
			}
		} else {
			info("-- Select Page --");
			Utils.pause(500);
			click(ELEMENT_SEARCH_SELECTOR_PAGE_LINK);
			type(ELEMENT_INPUT_POPUP_SEARCH_TITLE, pageTitle, true);
			click(ELEMENT_PAGE_MANAGEMENT_SEARCH_BUTTON);
			Utils.pause(1000);
			click(ELEMENT_SELECT_SEARCHED_PAGE);
		}
		info("-- Save add node for portal --");
		Utils.pause(500);
		button.save();
		if (verifyNode) {
			//waitForTextNotPresent("Page Node Settings");
			//waitForTextPresent(nodeName);
			waitForAndGetElement( ELEMENT_NODE_LINK.replace("${nodeLabel}", nodeName));
			//button.save();
			//waitForTextNotPresent("Navigation Management");
		}
		button.save();
		waitForElementNotPresent(ELEMENT_TITLE_NAVIGATION_MANAGEMENT);
	}

	// Edit a node 
	public void editNodeInPortalNavigation(String currentNavigation, String nodeNameHome, String nodeName, boolean extendedLabelMode, Map<String, String> languages, 
			String nodeLabel, String pageName, String pageTitle, boolean firstLevel){
		String currentNodeHome = ELEMENT_NODE_LINK.replace("${nodeLabel}", nodeNameHome);
		String currentNodeName = ELEMENT_NODE_LINK.replace("${nodeLabel}", nodeName);

		//Go to [Edit Navigation]
		if (waitForAndGetElement(ELEMENT_TITLE_NAVIGATION_MANAGEMENT, 5000, 0) == null){
			editNavigation(currentNavigation);
		}	
		if (firstLevel){
			click(currentNodeName);
			rightClickOnElement(currentNodeName);
			click(ELEMENT_EDIT_SELECTED_NODE);
		}else {
			click(currentNodeHome);
			click(currentNodeName);
			rightClickOnElement(currentNodeName);
			click(ELEMENT_EDIT_SELECTED_NODE);
		}
		//waitForTextPresent("Page Node Settings");
		if (extendedLabelMode) {
			WebElement element = waitForAndGetElement(ELEMENT_CHECKBOX_EXTENDED_LABEL_MODE, DEFAULT_TIMEOUT, 1, 2);
			if(!element.isSelected()){
				click(element, 2);
			}
			for (String language : languages.keySet()) {
				select(ELEMENT_SELECT_LANGUAGE, language);
				Utils.pause(500);
			}
		} else {
			uncheck(ELEMENT_CHECKBOX_EXTENDED_LABEL_MODE);
			type(ELEMENT_INPUT_LABEL, nodeLabel, true);
		}
		//Page Selector
		click(ELEMENT_PAGE_SELECTOR_TAB);
		click(ELEMENT_CLEAR_SELECTOR_PAGE);
		if (!pageName.isEmpty()){
			type(ELEMENT_INPUT_PAGE_NAME, pageName, true);
			type(ELEMENT_INPUT_PAGE_TITLE, pageTitle, true);
			click(ELEMENT_CREATE_PAGE_LINK);
			button.save();
		}else {
			click(ELEMENT_SEARCH_SELECTOR_PAGE_LINK);
			type(ELEMENT_INPUT_POPUP_SEARCH_TITLE, pageTitle, true);
			click(ELEMENT_PAGE_MANAGEMENT_SEARCH_BUTTON);
			click(ELEMENT_SELECT_SEARCHED_PAGE);	
			button.save();
		}
		Utils.pause(500);
		//button.save();
		//waitForTextNotPresent("Navigation Management");
	}

	//Delete a node from Portal navigation
	public void deleteNode(String currentNavigation, String nodeNameHome, String nodeName, boolean firstLevel, boolean...verify){
		boolean check = verify.length > 0 ? verify[0]:true;
		info("--Delete a node from navigation--");
		alt = new ManageAlert(driver);
		button = new Button(driver);
		String currentNodeHome = ELEMENT_NODE_LINK.replace("${nodeLabel}", nodeNameHome);
		String currentNodeName = ELEMENT_NODE_LINK.replace("${nodeLabel}", nodeName);
		String currentChildNodeName = ELEMENT_CHILD_NODE_LINK.replace("${nodeLabel}", nodeNameHome).replace("${childNode}", nodeName);
		editNavigation(currentNavigation);
		//currentNodeHome.equals(ELEMENT_NAVIGATION_NODE_AREA)
		if (firstLevel){
			click(currentNodeName);
			rightClickOnElement(currentNodeName);
			waitForAndGetElement(ELEMENT_NAVIGATION_DELETE_NODE).click();
			alt.waitForConfirmation("Are you sure you want to delete this node?");
			if (check){
				waitForElementNotPresent(currentNodeName);
			}
			button.save();		
		}else {
			if (isElementNotPresent(currentChildNodeName) && isElementNotPresent(currentNodeName)){
				click(currentNodeHome);
			}
			click(currentNodeName);
			rightClickOnElement(currentNodeName);
			waitForAndGetElement(ELEMENT_NAVIGATION_DELETE_NODE).click();
			alt.waitForConfirmation("Are you sure you want to delete this node?");
			if (check){
				waitForElementNotPresent(currentNodeName);
			}
			button.save();		
		}
		//waitForTextNotPresent("Navigation Management");
		waitForElementNotPresent(button.ELEMENT_SAVE_BUTTON);
	}
}
package org.exoplatform.selenium.platform.ecms.admin;

import static org.exoplatform.selenium.TestLogger.info;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * 
 * @author vuna2
 *
 */
public class ManageTag extends EcmsBase{

	public ManageTag(WebDriver dr) {
		super(dr);
		// TODO Auto-generated constructor stub
	}

	ManageAlert alt = new ManageAlert(driver);
	ECMainFunction ecMain = new ECMainFunction(driver);

	//Elements
	//Add Style Tab
	public final By ELEMENT_ADD_STYLE_BUTTON = By.xpath("//*[text()='Add Style']");
	public final By ELEMENT_STYLE_NAME = By.name("styleName"); 
	public final By	ELEMENT_DOCUMENT_RANGE = By.name("documentRange");
	public final By ELEMENT_INPUT_HTML_STYLE = By.xpath("//textarea[@name='styleHTML']");
	public final By ELEMENT_UPDATE_BUTTON = By.xpath("//*[text()='Update']");
	
	//Tag Manager
	public final String ELEMENT_VALUE_DOCUMENT_RANGE = "//*[text()='${styleName}']/../../td[2]/*[@class='Text']";
	public final String ELEMENT_EDIT_TAG_ICON = "//*[text()='${styleName}']/../..//*[contains(@class, 'uiIconEdit')]";
	public final String ELEMENT_REMOVE_TAG_ICON = "//*[text()='${styleName}']/../..//*[contains(@class, 'uiIconRemove')]";
			
	/*=====================================================*/

	/**
	 * Add a new TAG 
	 * @param styleName
	 * @param range: document range (0..2; 2..5; etc...)
	 * @param htmlStyle
	 */
	public void addNewTag(String styleName, String range, String htmlStyle){
		info("-- Adding a new tag --" + styleName);
		click(ELEMENT_ADD_STYLE_BUTTON);
		type(ELEMENT_STYLE_NAME, styleName, true);
		type(ELEMENT_DOCUMENT_RANGE, range, true);
		type(ELEMENT_INPUT_HTML_STYLE, htmlStyle, true);
		click(ELEMENT_UPDATE_BUTTON);
		waitForTextPresent(styleName);
	}

	/**
	 * Edit a TAG
	 * @param styleName
	 * @param range
	 * @param htmlStyle
	 */
	public void editTag(String styleName, String range, String htmlStyle, Object...params){
		Boolean update = (Boolean) (params.length > 0 ? params[0]: false);
		
		info("-- Editing Tag... " + styleName);
		click(ELEMENT_EDIT_TAG_ICON.replace("${styleName}", styleName));
		if (!range.isEmpty()){ 
			type(ELEMENT_DOCUMENT_RANGE, range, true);
		}
		if (!htmlStyle.isEmpty()){
			type(ELEMENT_INPUT_HTML_STYLE, htmlStyle, true);
		}
		if (update){
			click(ELEMENT_UPDATE_BUTTON);
			Utils.pause(500);
			driver.navigate().refresh();
			if (!range.isEmpty()){
				WebElement element = waitForAndGetElement(ELEMENT_VALUE_DOCUMENT_RANGE.replace("${styleName}", styleName), 3000, 1, 2);
				String rg = element.getAttribute("data-original-title");	
				assert rg.equals(range): "[Document range] is not updated";
			}
		}
	}

	/**
	 * Delete a TAG
	 * @param styleName
	 */
	public void deleteTag(String styleName){
		info("-- Removing a tag... " + styleName);
		waitForAndGetElement(ELEMENT_REMOVE_TAG_ICON.replace("${styleName}", styleName)).click();
		alt.acceptAlert();
		waitForTextNotPresent(styleName);
	}
}
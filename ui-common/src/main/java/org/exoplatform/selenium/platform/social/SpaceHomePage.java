package org.exoplatform.selenium.platform.social;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.PlatformBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SpaceHomePage extends PlatformBase{

	
	public final By ELEMENT_SPACE_PANEL=By.xpath(".//*[@id='UIMySpacesPortlet']");
	
	//select menu (actvity stream, forum, agenda etc ..)
	public final By ELEMENT_SPACE_MENU_ACTIVITY_STREAM = By.xpath(".//*[@class='tabName' and contains(text(),' Activity Stream')]");
	public final By ELEMENT_SPACE_MENU_AGENDA = By.xpath(".//*[@id='calendar' and contains(text(),'Agenda')]");
	public final By ELEMENT_SPACE_MENU_FORUMS = By.xpath(".//*[@class='tabName' and contains(text(),'Forums')]");
	public final By ELEMENT_SPACE_MENU_WIKI = By.xpath(".//*[@class='tabName' and contains(text(),'Wiki')]");
	public final By ELEMENT_SPACE_MENU_DOCUMENTS = By.xpath(".//*[@class='tabName' and contains(text(),'Documents')]");
	public final By ELEMENT_SPACE_MENU_SETTINGS = By.xpath(".//*[@class='tabName' and contains(text(),'Space Settings')]");
	public final By ELEMENT_SPACE_MENU_ANSWER = By.xpath(".//*[@class='tabName' and contains(text(),'Answer')]");
	public final String ELEMENT_SPACE_NAME = ".//*[@id='UIBreadCrumbsNavigationPortlet']//*[@class='name'][contains(text(),'${name}')]";
	
	//Navigation menu
	public By ELEMENT_SPACE_SPACE_SETTINGS=By.xpath("//*[@class='uiIconAppSpaceSettingPortlet uiIconDefaultApp']/..");
	public By ELEMENT_SPACE_WIKI_TAB=By.xpath(".//*[@id='spaceMenuTab']//*[contains(text(),'Wiki')]");
	
	//left menu
	public String ELEMENT_SPACE_LEFT_MENU_SPACE_NAME = ".//*[@id='UISpaceNavigationPortlet']//*[contains(text(),'${name}')]";
	
	
	SpaceSettingManagement setSpaceMg;
	/**
	 * constructor
	 * @param dr
	 */
	public SpaceHomePage(WebDriver dr){
		this.driver=dr;
		
	}
	
	/**
	 * Open Space setting page
	 */
	public void goToSettingTab(){
		info("--Open Setting tab of the space");
		info("Click on the tab");
		click(ELEMENT_SPACE_SPACE_SETTINGS);
		Utils.pause(3000);
		info("Space setting page is shown");
	}
	/**
	 * Open Wiki portlet of space
	 */
	public void goToWikiTab(){
		info("--Open Wiki tab of the space");
		info("Click on the tab");
		waitForAndGetElement(ELEMENT_SPACE_WIKI_TAB,3000,0).click();
		info("wiki page is shown");
	}
	/**
	 * Open a space from left menu
	 * @param name
	 */
	public void goToSpace(String name){
		info("Go to the Space:"+name);
		click(ELEMENT_SPACE_LEFT_MENU_SPACE_NAME.replace("${name}",name),0,true);
		waitForAndGetElement(ELEMENT_SPACE_NAME.replace("${name}",name),2000,0);
		info("The space is shown");
	}
}
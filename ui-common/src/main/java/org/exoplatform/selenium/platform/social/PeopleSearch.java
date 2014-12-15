package org.exoplatform.selenium.platform.social;

import java.awt.event.KeyEvent;

import org.exoplatform.selenium.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.exoplatform.selenium.TestLogger.info;

/*--@Author: VuNA  
 *--@date: 08/11/2012   
 *--Common functions for SOCIAL 
 *--Part: Search people
 *--*/
public class PeopleSearch extends SocialBase{

	//Go to username link -> Find Connections

	//Search people by name
	public final By ELEMENT_NAME_OF_PEOPLE = By.id("Search");

	//Search people by position
	public final By ELEMENT_ROLE_OF_PEOPLE = By.id("position");
	//By ELEMENT_ROLE_OF_PEOPLE = By.xpath("//*[@id='position']");

	//Search people by skill
	public final By ELEMENT_SKILL_OF_PEOPLE = By.id("skills");
	//By ELEMENT_SKILL_OF_PEOPLE = By.xpath("//*[@id='skills']");   	

	//Search people by directory
	public final String ELEMENT_DIRECTORY_OF_PEOPLE = "//*[@id='DirectorySpaces']/li/a[text()='${directoryPeople}']";

	//public final By ELEMENT_SEARCH_BUTTON = By.id("SearchButton");
	//public final By ELEMENT_SEARCH_BUTTON = By.id("SearchButton");
	public PeopleSearch(WebDriver dr){
		driver = dr;
	}
	/**
	 * Function search people
	 * @author phuongdt
	 * @date 24/09/2013
	 * @param useEnterKey
	 * @param option: peopleName, position, skills, directory
	 */
	public void searchPeople(boolean useEnterKey, Object...params){
		info("-- Searching people ... --");
		String peopleName = (String) (params.length > 0 ? params[0]: "");
		String position = (String) (params.length > 1 ? params[1]: "");
		String skills = (String) (params.length > 2 ? params[2]: "");
		String directory = (String) (params.length > 3 ? params[3]: "");
		//Clear textboxes
		click(By.linkText("All"));
		/*type(ELEMENT_NAME_OF_PEOPLE, "", true);
		type(ELEMENT_ROLE_OF_PEOPLE, "", true);
		type(ELEMENT_SKILL_OF_PEOPLE, "", true);*/
		if(peopleName!=""){
			type(ELEMENT_NAME_OF_PEOPLE, peopleName, true);
		}
		if(position!=""){
			type(ELEMENT_ROLE_OF_PEOPLE, position, true);
		}
		if(skills!=""){
			type(ELEMENT_SKILL_OF_PEOPLE, skills, true);
		}
		if(useEnterKey)
			Utils.javaSimulateKeyPress(KeyEvent.VK_ENTER);
		else
			clickByJavascript(ELEMENT_SEARCH_BUTTON);
		if (directory!=""){
			click(By.linkText(directory));
		}
		if(!peopleName.equalsIgnoreCase("Root Root") && !peopleName.equals(""))
			waitForElementNotPresent(By.linkText("Root Root"));
		Utils.pause(1000);
	}
//	/**
//	 * 
//	 * @param type: Search by Name/Role/Skill or Directory
//	 * @param inputSearch:
//	 * by name: inputSearch -> name of People;
//	 * by role/position: inputSearch -> role of People;
//	 * by skill: inputSearch -> skill of People;
//	 * by directory: inputSearch -> input an alphabet: A,B,C ...
//	 * 
//	 */
//	public void searchPeople(searchType type, String inputSearch){
//		info("-- Searching people ... --");
//		switch (type) {
//		case NAME:
//			type(ELEMENT_NAME_OF_PEOPLE, inputSearch, true);
//			click(ELEMENT_SEARCH_BUTTON);
//			break;
//		case ROLE:
//			type(ELEMENT_ROLE_OF_PEOPLE, inputSearch, true);
//			click(ELEMENT_SEARCH_BUTTON);
//			break;	
//		case SKILL:
//			type(ELEMENT_SKILL_OF_PEOPLE, inputSearch, true);
//			click(ELEMENT_SEARCH_BUTTON);
//			break;
//		case DIRECTORY:
//			click(ELEMENT_DIRECTORY_OF_PEOPLE.replace("${directoryPeople}", inputSearch));
//			break;
//		default:
//			break;
//		}
//	}

//	public void searchPeopleAdvance(boolean searchName, String inputName,
//			                               boolean searchRole, String inputRole,
//			                               boolean searchSkill,String inputSkill){
//		info("-- Searching people ... --");
//		if (searchName && searchRole){
//			type(ELEMENT_NAME_OF_PEOPLE, inputName, true);
//			type(ELEMENT_ROLE_OF_PEOPLE, inputRole, true);
//		}else if (searchName && searchSkill){
//			type(ELEMENT_NAME_OF_PEOPLE, inputName, true);
//			type(ELEMENT_SKILL_OF_PEOPLE, inputSkill, true);
//		}else if (searchRole && searchSkill){
//			type(ELEMENT_ROLE_OF_PEOPLE, inputRole, true);
//			type(ELEMENT_SKILL_OF_PEOPLE, inputSkill, true);
//		}
//		/*else {
//			type(ELEMENT_NAME_OF_PEOPLE, inputName, true);
//			type(ELEMENT_ROLE_OF_PEOPLE, inputRole, true);
//			type(ELEMENT_SKILL_OF_PEOPLE, inputSkill, true);
//		}*/
//		click(ELEMENT_SEARCH_BUTTON);
//	}
	
	//Define a type of search 
	public enum searchType {
		NAME, ROLE, SKILL, DIRECTORY;
	}

}

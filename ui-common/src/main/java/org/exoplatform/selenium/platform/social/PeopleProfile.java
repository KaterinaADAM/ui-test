package org.exoplatform.selenium.platform.social;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.PlatformBase;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


/**
 *Initiated by HangNTT
 * 
 *@author VuNA
 *@date: 08/11/2012
 *Update/Add new method
 * -- Edit a basic information
 * -- Edit a contact
 * -- Edit an Experience 
 */

public class PeopleProfile extends PlatformBase {
	PeopleSearch peoSearch;
	Button button;

	// Go to Account Name link > My Profile	
	// Edit user in My Profile
	// Basic information
	public final String ELEMENT_HEADER_NAME = "//div[@id='UIBreadCrumbsNavigationPortlet']//div[contains(text(),'${name}')]";
	public final By ELEMENT_EDIT_HEADER_BUTTON = By.xpath("//*[@id='UIHeaderSection']//../*[@class='uiIconEdit']");
	public final By ELEMENT_EDIT_INFORMATION_BUTTON = By.xpath("//*[@id='UIBasicInfoSection']//../*[@class='uiIconEdit']");

	// Contact
	public final By ELEMENT_EDIT_CONTACT_BUTTON = By.xpath("//*[@id='UIContactSection']//../*[@class='uiIconEdit']");
	//Contact > Gender
	public final By ELEMENT_GENDER_BOX = By.name("gender");

	//Contact > Phone
	public final By ELEMENT_ADD_PHONE_BUTTON = By.xpath("//*[@class='uiContactSection']//../*[text()='Phone:']//../..//*[@class='uiIconPlus uiIconLightGray']");
	public final By ELEMENT_REMOVE_PHONE_BUTTON = By.xpath("//*[@class='uiContactSection']//../*[text()='Phone:']//../..//*[@class='uiIconClose uiIconLightGray']");
	public final By ELEMENT_TYPE_OF_ADD_PHONE = By.name("1phone000");
	public final By ELEMENT_INPUT_FIELD_ADD_PHONE = By.id("1phone001");

	//Contact > IM
	public final By ELEMENT_ADD_IMS_BUTTON = By.xpath("//*[@class='uiContactSection']//../*[text()='IMs:']//../..//*[@class='uiIconPlus uiIconLightGray']");
	public final By ELEMENT_IMS_BOX = By.name("2im000");
	public final By ELEMENT_INPUT_FIELD_IMS = By.id("2im001");
	public final By ELEMENT_REMOVE_IMS_BUTTON = By.xpath("//*[@class='uiContactSection']//../*[text()='IMs:']//../..//*[@class='uiIconClose uiIconLightGray']");

	//Contact > URL
	public final By ELEMENT_ADD_URLS_BUTTON = By.xpath("//*[@class='uiContactSection']//../*[text()='URLs:']//../..//*[@class='uiIconPlus uiIconLightGray']");
	public final By ELEMENT_INPUT_FIELD_URLS	= By.id("3url001");
	public final By ELEMENT_REMOVE_URLS_BUTTON = By.xpath("//*[@class='uiContactSection']//../*[text()='URLs:']//../..//*[@class='uiIconClose uiIconLightGray']");

	// Experience
	public final By ELEMENT_EDIT_EXPERIENCE_BUTTON = By.xpath("//*[@id='UIExperienceSection']//../*[@class='uiIconEdit']");
	public final By ELEMENT_ADD_EXPERIENCE_BUTTON = By.xpath("//*[@id='UIExperienceSection']//../*[@class='uiIconPlus uiIconLightGray']");
	public final By ELEMENT_INPUT_FIELD_ORGANIZATION = By.id("company1");
	public final By ELEMENT_INPUT_FIELD_POSITION = By.id("position1");
	public final By ELEMENT_INPUT_FIELD_SKILL = By.id("skills1");
	public final By ELEMENT_REMOVE_EXPERIENCE_BUTTON = By.xpath("//*[@id='UIExperienceSection']//../*[@class='uiIconClose uiIconLightGray']");

	//Avatar
	public final By	ELEMENT_CHANGE_AVATAR_LINK = By.className("changeAvatar");
	public final By ELEMENT_CHOOSE_AVATAR_IMAGE = By.className("fileNameLabel");
	public final By ELEMENT_UPLOAD_NAME = By.name("file");
	public final By ELEMENT_SELECT_FILE_BUTTON = By.xpath("//label[text()='Select File']");
	public final String ELEMENT_IMAGE_FINISHED = "//div[@class='fileNameLabel' and contains(text(),'${image}')]";

	//Confirm
	public final By ELEMENT_CONFIRM = By.xpath("//*[text()='Confirm']");
	public final By ELEMENT_CANCEL = By.xpath("//*[text()='Cancel']");
	public final By ELEMENT_SAVE_UPDATE_INFO = By.xpath("//*[@id='UIProfile']//../*[contains(text(), 'Save')]");
	public final By ELEMENT_CANCEL_UPDATE_INFO = By.xpath("//*[@id='UIProfile']//../*[contains(text(), 'Cancel')]");

	public PeopleProfile(WebDriver dr, String...plfVersion) {
		driver = dr;
		this.plfVersion = plfVersion.length>0?plfVersion[0]:"4.0";
		button = new Button(driver, this.plfVersion);
		peoSearch = new PeopleSearch(driver);
	}

	/**
	 * Edit Basic Information
	 * @param firstName: edit the user's first name
	 * @param lastName: edit the user's last name
	 * @param email: edit the user's email
	 */
	public void editUserBasicInformation(Object...params){
		info("-- Edit the user's basic information --");
		String firstName = (String) (params.length > 0 ? params[0]: "");
		String lastName = (String) (params.length > 1 ? params[1]: "");
		String email = (String) (params.length > 2 ? params[2]: "");
		getElementByJavascript("uiIconEdit",1).click();
		if(firstName!="")
			type(ELEMENT_INPUT_FIRSTNAME, firstName, true);
		if(lastName!="")
			type(ELEMENT_INPUT_LASTNAME, lastName, true);
		if(email!="")
			type(ELEMENT_INPUT_EMAIL, email, true);
		getElementByJavascript("btn btn-primary").click();
		waitForElementNotPresent(ELEMENT_SAVE_UPDATE_INFO);
		Utils.pause(1000);
	}

	/**
	 * Edit a contact
	 * @param typeOfGender: male or female
	 * @param addPhone: boolean 
	 * @param typeOfAddPhone: Work/Home/Other 
	 * @param numberOfPhone
	 * @param addIMS: boolean
	 * @param typeOfIMS: Gtalk/Yahoo/Skype, etc...
	 * @param nameOfIMS: name of IM
	 * @param addURLS: boolean
	 * @param nameOfURLS: name of URL
	 */
	public void editUserContact(String typeOfGender, boolean addPhone, String typeOfAddPhone, String numberOfPhone,
			boolean addIMS, String typeOfIMS, String nameOfIMS,
			boolean addURLS, String nameOfURLS){
		info("-- Edit the user's contact --");
		WebElement e = getElementByJavascript("uiIconEdit", 2);
		e.click();
		info("-- Type of gender --");
		select(ELEMENT_GENDER_BOX, typeOfGender);
		if(addPhone){
			info("-- Adding a phone number --");
			if (isTextPresent("No phone number entered")){
				getElementByJavascript("uiIconPlus uiIconLightGray").click();
			}
			select(ELEMENT_TYPE_OF_ADD_PHONE, typeOfAddPhone);
			type(ELEMENT_INPUT_FIELD_ADD_PHONE, numberOfPhone, true);
		}
		if(addIMS){
			info("-- Adding an IM --");
			if (isTextPresent("No IM handle entered")){
				getElementByJavascript("uiIconPlus uiIconLightGray",1).click();
			}
			select(ELEMENT_IMS_BOX, typeOfIMS);
			type(ELEMENT_INPUT_FIELD_IMS, nameOfIMS, true);
		}
		if(addURLS){
			info("-- Adding an URL --");
			if (isTextPresent("No contact link entered")){
//				info("test" + waitForAndGetElement(ELEMENT_ADD_URLS_BUTTON) + " and " + getElementByJavascript("uiIconPlus uiIconLightGray",1) + " and " + getElementByJavascript("uiIconPlus uiIconLightGray"));
				
				((JavascriptExecutor) driver).executeScript("return document.getElementsByClassName('uiIconPlus uiIconLightGray')[2].click();");
//				click("//*[@class='uiContactSection']//../*[text()='URLs:']//../..//*[@class='uiIconPlus uiIconLightGray']");
//				getElementByJavascript("uiIconPlus uiIconLightGray",3).click();
			}
			type(ELEMENT_INPUT_FIELD_URLS, nameOfURLS, true);
		}
		getElementByJavascript("btn btn-primary").click();
		waitForElementNotPresent(ELEMENT_SAVE_UPDATE_INFO);
		Utils.pause(2000);
	}

	/**
	 * Edit the user's experience
	 * @param nameOfOrganization
	 * @param nameOfPosition
	 * @param nameOfSkill
	 */
	public void editUserExperience(String nameOfOrganization, String nameOfPosition, Object...params){
		info("-- Edit the user's experience --");
		String nameOfSkill = (String) (params.length > 0 ? params[0]: "");
		getElementByJavascript("uiIconEdit",3).click();
		if(isTextPresent("You have not entered any experience yet.")){
			((JavascriptExecutor) driver).executeScript("return document.getElementsByClassName('uiIconPlus uiIconLightGray')[0].click();");
//			getElementByJavascript("uiIconPlus uiIconLightGray").click();
		}
		type(ELEMENT_INPUT_FIELD_ORGANIZATION, nameOfOrganization, true);
		type(ELEMENT_INPUT_FIELD_POSITION, nameOfPosition, true);
		if(nameOfSkill!="")
			type(ELEMENT_INPUT_FIELD_SKILL, nameOfSkill, true);
		getElementByJavascript("btn btn-primary").click();
		waitForElementNotPresent(ELEMENT_SAVE_UPDATE_INFO);
	}

	/**
	 * Remove experience
	 * @author phuongdt
	 */
	public void removeUserExperience(){
		info("-- Remove the user's experience --");
		getElementByJavascript("uiIconEdit",3).click();
		waitForAndGetElement(ELEMENT_INPUT_FIELD_ORGANIZATION);
		
		getElementByJavascript("uiIconClose uiIconLightGray").click();
		button.ok();
		Utils.pause(500);
		waitForElementNotPresent(ELEMENT_REMOVE_EXPERIENCE_BUTTON);
		getElementByJavascript("btn btn-primary").click();
		waitForElementNotPresent(ELEMENT_SAVE_UPDATE_INFO);
	}

	/**
	 * Remove contact information
	 * @author phuongdt
	 */
	public void removeUserContact(boolean phone, boolean im, boolean url){
		info("-- Remove the user's contact --");
		WebElement e = getElementByJavascript("uiIconEdit", 2);
		e.click();
		waitForAndGetElement(ELEMENT_GENDER_BOX);
		if(phone){
			getElementByJavascript("uiIconClose uiIconLightGray").click();
			waitForMessage("No phone number entered");
		}
		if(im){
			if(isTextPresent("No phone number entered"))
				getElementByJavascript("uiIconClose uiIconLightGray").click();
			else
				getElementByJavascript("uiIconClose uiIconLightGray",1).click();
			waitForMessage("No IM handle entered");
		}
		if(url){
			if(isTextPresent("No phone number entered") && isTextPresent("No IM handle entered"))
				getElementByJavascript("uiIconClose uiIconLightGray").click();
			else
				if (isTextPresent("No phone number entered") || isTextPresent("No IM handle entered"))
					getElementByJavascript("uiIconClose uiIconLightGray",1).click();
				else
					getElementByJavascript("uiIconClose uiIconLightGray",2).click();
			waitForMessage("No contact link entered");
		}
		getElementByJavascript("btn btn-primary").click();
		waitForElementNotPresent(ELEMENT_SAVE_UPDATE_INFO);
	}

	/**
	 * Change avatar
	 * @author phuongdt
	 * @param linkfile : File path of new avatar
	 */
	public void changeAvatar(String linkfile){
		info("-- changeAvatar --");
		String[] files = linkfile.split("/");

		clickByJavascript(ELEMENT_CHANGE_AVATAR_LINK);

		Utils.pause(2000);
		if(System.getProperty("browser").equalsIgnoreCase("firefox")){
			WebElement upload = waitForAndGetElement(ELEMENT_UPLOAD_NAME, DEFAULT_TIMEOUT, 1, 2);
			((JavascriptExecutor)driver).executeScript("arguments[0].style.visibility = 'visible'; arguments[0].style.height = '1px'; " +
			"arguments[0].style.width = '1px'; arguments[0].style.opacity = 1", upload);
			((JavascriptExecutor)driver).executeScript("arguments[0].style.display = 'block'; arguments[0].style.visibility = 'visible'", upload);
			upload.sendKeys(Utils.getAbsoluteFilePath(linkfile));
		}else if(System.getProperty("browser").equalsIgnoreCase("iexplorer")){
			waitForAndGetElement(ELEMENT_SELECT_FILE_BUTTON).click();
			uploadFile(linkfile);
		}
		
		info("Upload file " + Utils.getAbsoluteFilePath(linkfile));
		switchToParentWindow();
		waitForAndGetElement(ELEMENT_IMAGE_FINISHED.replace("${image}",files[files.length-1]));
		click(ELEMENT_CONFIRM);

		waitForElementNotPresent(ELEMENT_CONFIRM);
		button.save();
		Utils.pause(1000);
	}

	/**
	 * Go to user's profile in connection
	 * @author phuongdt
	 * @date 24/09/2013
	 * @param userName
	 */
	public void goToUserProfile(String userName){
		info("--Go to User's Profile--");
		peoSearch.searchPeople(false,userName);
		click(By.linkText(userName));
		waitForAndGetElement(ELEMENT_HEADER_NAME.replace("${name}", userName));
		waitForAndGetElement(ELEMENT_MY_PROFILE_TAB);
	}
}

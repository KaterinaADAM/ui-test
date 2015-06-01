package org.exoplatform.selenium.platform.social;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
public class PeopleProfile extends PlatformBase {

	public final By ELEMENT_EDIT_MY_PROFILE_LINK = By.xpath(".//*[@id='UIBreadCrumbsNavigationPortlet']//*[@class='uiIconEdit uiIconLightGray']");
	public final By ELEMENT_EDIT_MY_PROFILE_BUTTON = By.xpath("//*[@id='UIExperienceProfilePortlet']//*[@class='uiIconEdit uiIconLightGray']");
	public final By ELEMENT_EDIT_PROFILE_FORM = By.id("UIEditUserProfileForm");
	public final String ELEMENT_NAME_OF_PROFILE_TOP_LEFT = "//*[@id='UIBreadCrumbsNavigationPortlet']//*[contains(text(),'${name}')]";
	public final String ELEMENT_NAME_OF_USER_TOP_LEFT = "//*[@id='UIBreadCrumbsNavigationPortlet']//*[contains(text(),'{$name}')]";
	public final String ELEMENT_NAME_OF_USER_TOP_RIGHT = ".//*[@id='UIUserPlatformToolBarPortlet']//*[contains(normalize-space(),'${firstName} ${lastName}')]";
	public final By ELEMENT_HORIZONTAL_TOOLBAR = By.xpath("//*[@id='UIUserNavigationPortlet']/ul");

	//Navigation menu
	public final By ELEMENT_HORIZONTAL_TOOLBAR_FIRST_APP_PROFILE = By.xpath("//*[@id='UIUserNavigationPortlet']/ul/li[1]//*[@class='uiIconAppprofile uiIconDefaultApp']");
	public final By ELEMENT_HORIZONTAL_TOOLBAR_SECOND_APP_ACTIVITIES = By.xpath("//*[@id='UIUserNavigationPortlet']/ul/li[2]//*[@class='uiIconAppactivities uiIconDefaultApp']");
	public final By ELEMENT_HORIZONTAL_TOOLBAR_THIRD_APP_CONNECTIONS = By.xpath("//*[@id='UIUserNavigationPortlet']/ul/li[3]//*[@class='uiIconAppconnections uiIconDefaultApp']");
	public final By ELEMENT_HORIZONTAL_TOOLBAR_FORTH_APP_WIKI = By.xpath("//*[@id='UIUserNavigationPortlet']/ul/li[4]//*[@class='uiIconAppwiki uiIconDefaultApp']");
	public final By ELEMENT_HORIZONTAL_TOOLBAR_FIFTH_APP_DASHBOARD = By.xpath("//*[@id='UIUserNavigationPortlet']/ul/li[5]//*[@class='uiIconAppdashboard uiIconDefaultApp']");

	//Left contact information
	public final By ELEMENT_UIBASICPROFILEPORTLET = By.xpath(".//*[@id='UIBasicProfilePortlet']/h4[contains(text(),'Contact Information')]");
	public final String ELEMENT_FULLNAME_INFO=".//*[@id='UIStatusProfilePortlet']//span[text()='${fullname}']";
	public final String ELEMENT_EMAIL_INFO=".//*[@class='uiEmail ellipsis' and @data-original-title='${email}']";
	public final String ELEMENT_JOB_TITLE_INFO=".//*[@class='uiPosition ellipsis' and @data-original-title='${job}']";
	public final String ELEMENT_GENDER_INFO=".//*[@class='uiGender ellipsis' and @data-original-title='${gender}']";
	public final String ELEMENT_USER_AVATAR="//*[@class='userAvt pull-left']/img";
	public final String ELEMENT_PHONE_INFO="//div[contains(text(),'${type}:')]/../*[@data-original-title='${phone}']";
	public final String ELEMENT_IM_INFO="//div[contains(text(),'${type}:')]/../*[@data-original-title='${im}']";
	public final String ELEMENT_URL_INFO="//*[@class='uiUrls']/*[@data-original-title='${url}']";
	
	//Middle experience information
	public final String ELEMENT_COMPANY_INFO="//*[@class='company clearfix']//*[@data-original-title='${company}']";
	public final String ELEMENT_POSITION_INFO="//*[@class='position clearfix']//*[@data-original-title='${position}']";
	public final String ELEMENT_JOB_DETAIL_INFO="//*[@class='description clearfix']//*[@data-original-title='${description}']";
	public final String ELEMENT_SKILL_INFO="//*[@class='skills clearfix']//*[@data-original-title='${skill}']";
	public final String ELEMENT_STARTDATE_INFO="//*[@class='startDate clearfix']//*[@data-original-title='${date}']";
	public final String ELEMENT_ENDDATE_INFO="//*[@class='endDate clearfix']//*[@data-original-title='${date}']";
	
	//Navigation tabs
	public final By ELEMENT_MY_PROFILE_TAB = By.xpath("//*[@class='nav nav-tabs userNavigation']//*[@class='uiIconAppprofile uiIconDefaultApp']");

	//Current position
	public final By ELEMENT_EDIT_POSITION = By.xpath("//*[@id='UIHeaderSection']//*[@class='uiIconEdit']");
	public final By ELEMENT_POSITION_TEXTBOX_EDIT = By.id("position");
	public final By ELEMENT_EDIT_POSITION_SAVE_BUTTON = By.id("savePosition");

	//About me
	public final By ELEMENT_ABOUTME_TEXTAREA_EDIT = By.id("aboutMe");
	public final String ELEMENT_UIEXPERIENCE_PROFILE_PORTLET = "//*[@id='UIExperienceProfilePortlet']//*[contains(text(),'${content}')]";
	
	//Basic information
	public final By ELEMENT_EDIT_BASIC_INFORMATION = By.xpath("//*[@id='UIBasicInfoSection']//*[@class='uiIconEdit']");
	public final By ELEMENT_FIRST_NAME_TEXTBOX_EDIT = By.id("firstName");
	public final By ELEMENT_LAST_NAME_TEXTBOX_EDIT = By.id("lastName");
	public final By ELEMENT_EMAIL_TEXTBOX_EDIT = By.id("email");
	public final By ELEMENT_EDIT_BASIC_INFO_SAVE_BUTTON = By.xpath(".//*[@id='UIEditUserProfileForm']//*[contains(@class,'btn-save')]");

	//Contact
	public final By ELEMENT_CONTACT_EDIT_ICON = By.xpath(".//*[@id='UIContactSection']//*[contains(text(),'Contact')]/..//*[@class='uiIconEdit']");
	public final By ELEMENT_CONTACT_GENDER_SELECTION=By.name("gender");
	public final By ELEMENT_CONTACT_JOB_TITLE=By.name("position");
	public final String ELEMENT_CONTACT_IMS_OPTION="//*[@id='ims']/div[${index}]//*[contains(@name,'selectKey_ims')]";
	public final String ELEMENT_CONTACT_IMS_INPUT="//*[@id='ims']/div[${index}]//*[contains(@name,'inputKey_ims')]";
	public final String ELEMENT_CONTACT_IMS_INPUT_LIST="//*[@id='ims']/div";
	public final By ELEMENT_CONTACT_IMS_ADD_ICON = By.xpath("//*[@id='ims']//*[@data-original-title='Add Item']");
	public final String ELEMENT_CONTACT_IMS_REMOVE_ICON = "/*[@id='ims']/div[${index}]//*[@data-original-title='Remove Item']";
	public final String ELEMENT_CONTACT_PHONE_OPTION="//*[@id='phones']/div[${index}]//*[contains(@name,'selectKey_phones')]";
	public final String ELEMENT_CONTACT_PHONE_INPUT="//*[@id='phones']/div[${index}]//*[contains(@name,'inputKey_phones')]";
	public final String ELEMENT_CONTACT_PHONE_INPUT_LIST="//*[@id='phones']/div";
	public final By ELEMENT_CONTACT_PHONE_ADD_ICON = By.xpath("//*[@id='phones']//*[@data-original-title='Add Item']");
	public final String ELEMENT_CONTACT_PHONE_REMOVE_ICON = "/*[@id='phones']/div[${index}]//*[@data-original-title='Remove Item']";
	public final String ELEMENT_CONTACT_URL_INPUT="//*[@class='multiValueContainer']/li[${index}]//*[contains(@name,'urls')]";
	public final String ELEMENT_CONTACT_URL_INPUT_LIST="//*[@class='multiValueContainer']/li";
	public final By ELEMENT_CONTACT_URL_ADD_ICON = By.xpath("//*[@class='multiValueContainer']//*[@data-original-title='Add Item']");
	public final String ELEMENT_CONTACT_URL_REMOVE_ICON = "/*[@class='multiValueContainer']/div[${index}]//*[@data-original-title='Remove Item']";

	//Avatar
	public final By	ELEMENT_CHANGE_AVATAR_LINK = By.xpath(".//*[@id='Avatar']//*[@class='btn btn-mini changeAvatar']");
	public final By ELEMENT_CHOOSE_AVATAR_IMAGE = By.className("fileNameLabel");
	public final By ELEMENT_SELECT_AVATAR = By.xpath(".//*[@id='Uploader']//*[text()='Select File']");
	public final By ELEMENT_UPLOAD_NAME = By.name("file");
	public final By ELEMENT_CONFIRM = By.xpath(".//*[@id='UIAvatarUploader']//*[text()='Confirm']");
	public final By ELEMENT_CANCEL = By.xpath(".//*[@id='UIAvatarUploader']//*[text()='Cancel']");
	public final By ELEMENT_SAVE_AVATAR = By.xpath(".//*[@id='UIAvatarUploadContent']//*[text()='Save']");
	public final By ELEMENT_CANCEL_AVATAR = By.xpath(".//*[@id='UIAvatarUploadContent']//*[text()='Cancel']");

	//Experience
	public final By ELEMENT_NO_EXPERIENCE=By.id("infoExperien");
	public final By ELEMENT_ADD_MORE_EXP_ICON = By.xpath("//*[@data-original-title='Add more experience' or @title='Add more experience']");
	public final String ELEMENT_EXPERIENCE_LIST=".//*[starts-with(@id,'ExperienceSection')]";
	public final String ELEMENT_EXPERIENCE_COMPANY_INPUT="//*[@id='companyExperienceSection${index}']";
	public final String ELEMENT_EXPERIENCE_POSITION_INPUT = "//*[@id='positionExperienceSection${index}']";
	public final String ELEMENT_EXPERIENCE_DESCRIPTION_INPUT = "//*[@id='descriptionExperienceSection${index}']";
	public final String ELEMENT_EXPERIENCE_SKILL_INPUT = "//*[@id='skillsExperienceSection${index}']";
	public final String ELEMENT_EXPERIENCE_START_DATE_INPUT = "//*[@name='startDateExperienceSection${index}']";
	public final String ELEMENT_EXPERIENCE_END_DATE_INPUT = "//*[@name='endDateExperienceSection${index}']";
	public final String ELEMENT_EXPERIENCE_CURRENT_CHECKBOX = "//*[@id='isCurrentExperienceSection${index}']";
	public final String ELEMENT_EXPERIENCE_CLOSE = "//*[@id='ExperienceSection${index}']//../*[@title='Remove this experience']";

	//Save - Cancel button
	public final By ELEMENT_CONTACT_SAVE_BUTTON = By.xpath(".//*[@id='UIEditUserProfileForm']//button[text()='Save']");
	public final By ELEMENT_CONTACT_CANCEL_BUTTON = By.xpath(".//*[@id='UIEditUserProfileForm']//button[text()='Cancel']");
	public final By ELEMENT_SAVE_UPDATE_INFO = By.xpath("//*[@id='UIProfile']//../*[contains(text(), 'Save')]");
	public final By ELEMENT_CANCEL_UPDATE_INFO = By.xpath("//*[@id='UIProfile']//../*[contains(text(), 'Cancel')]");
	
	//Recent activity
	public final String ELEMENT_RECENT_ACTIVITY_CONTENT="//*[@id='UIRecentActivitiesPortlet']//*[@class='activityCont']/div[${index}]//*[@class='status' and contains(text(),'${content}')]";
	public final String ELEMENT_RECENT_ACTIVITY_NO_CONTENT = "//*[@id='UIRecentActivitiesPortlet']//*[contains(text(),'${content}')]";
	public final String ELEMENT_RECENT_ACTIVITY_ALL_CONTENT = "//*[@id='UIRecentActivitiesPortlet']//*[@class='activityCont']//*[@class='content']/*[contains(normalize-space(),'${content}')]";
	public final By ELEMENT_RECENT_ACTIVITY_VIEWALL_BTN = By.xpath(".//*[@id='UIRecentActivitiesPortlet']//button[contains(text(),'View All')]");
	
	//Connection part
	public final String ELEMENT_UIMINICONNECTIONS_PORTLET_TEXT = "//*[@id='UIMiniConnectionsPortlet']/*[contains(text(),'${content}')]";
	public final By ELEMENT_UIMINICONNECTIONS_PORTLET_FIND = By.xpath("//*[@id='UIMiniConnectionsPortlet']/..//*[contains(text(),'Find connections')]");
	public final String ELEMENT_UIMINICONNECTIONS_PORTLET_VIEWALL = "//*[@id='UIMiniConnectionsPortlet']/..//*[contains(text(),'View all') and contains(text(),'${num}')]";
	public final String ELEMENT_UIMINICONNECTIONS_PORLET_NUMBER_CONNECTION=".//*[@id='UIMiniConnectionsPortlet']//*[@class='borderContainer']/*[@class='avatarXSmall']";
	public final String ELEMENT_UIMINICONNECTIONS_PORLET_AVATAR=".//*[@id='UIMiniConnectionsPortlet']//*[@class='borderContainer']/*[@class='avatarXSmall' and contains(@href,'${username}')]";
	public final String ELEMENT_UIMINICONNECTIONS_PORLET_HOVER_POPUP_AVATAR="//*[@id='tipName']//*[contains(@href,'${username}')]/img";
	public final String ELEMENT_UIMINICONNECTIONS_PORLET_HOVER_POPUP_USERNAME="//*[@id='tipName']//*[contains(@href,'${username}') and contains(text(),'${fullname}')]";
	
	//Connection status
	public final By ELEMENT_UIMINICONNECTIONS_PORLET_CONNECT_STATUS=By.xpath(".//*[@id='UIRelationshipAction']//*[@class='uiIconStatusConnect']/..");
	public final By ELEMENT_UIMINICONNECTIONS_PORLET_CANCEL_STATUS=By.xpath(".//*[@id='UIRelationshipAction']//*[text()='Cancel Request']");
	public final By ELEMENT_UIMINICONNECTIONS_PORLET_ACCEPT_STATUS=By.xpath(".//*[@id='UIRelationshipAction']//*[@class='uiIconStatusAccept']/..");
	public final By ELEMENT_UIMINICONNECTIONS_PORLET_CONNECTED_STATUS=By.xpath(".//*[@id='UIActionProfilePortlet']//*[@class='btn show-default']/*[@class='uiIconStatusConnected']");
	public final By ELEMENT_UIMINICONNECTIONS_PORLET_DISCONNECTED_STATUS=By.xpath(".//*[@id='UIActionProfilePortlet']//*[@class='btn hide-default']/*[@class='uiIconStatusDisconnect']");
	public final By ELEMENT_UIMINICONNECTIONS_PORTLET_DENY_STATUS=By.xpath(".//*[@id='UIRelationshipAction']//*[@class='uiIconStatusDeny']/..");
	public final By ELEMENT_UIMINICONNECTIONS_PORTLET_TITLE = By.xpath(".//*[@id='UIMiniConnectionsPortlet']/h4[contains(text(),'Connections')]");
	
	public final By ELEMENT_EDIT_MY_PROFILE = By.xpath(".//*[@id='UIExperienceProfilePortlet']/div/button");
	
	/**
	 * constructor
	 * @param dr
	 */
	public PeopleProfile(WebDriver dr, String...plfVersion){
		this.driver=dr;
		this.plfVersion = plfVersion.length>0?plfVersion[0]:"4.1";
	}

	/**
	 * Update Current position
	 * By QuynhPT
	 * @param pos
	 */
	public void updateCurrentPosition(String pos){
		info("Update Current Position");
		if(pos !="" && pos !=null){
			waitForAndGetElement(ELEMENT_EDIT_POSITION);
			click(ELEMENT_EDIT_POSITION);
			Utils.pause(2000);
			click(ELEMENT_EDIT_POSITION_SAVE_BUTTON);
			Utils.pause(2000);
		}
	}

	/**
	 * function: Go to Edit profile
	 */
	public void goToEditProfile(){
		info("Go to edit profile");
		click(ELEMENT_EDIT_MY_PROFILE_BUTTON);
		waitForAndGetElement(ELEMENT_EDIT_PROFILE_FORM);
	}

	/**
	 * Update About me section
	 * @param pos
	 */
	public void updateAboutMe(String pos){
		info("Update About me");
		if(pos !=""){
			type(ELEMENT_ABOUTME_TEXTAREA_EDIT, pos, true);
		}

	}

	/**
	 * Update Basic information
	 * By QuynhPT
	 * @param firstName
	 * @param lastName
	 * @param email
	 */
	public void updateBasicInformation(String firstName,String lastName,String email){
		info("Update basic information");
		if(waitForAndGetElement(ELEMENT_EDIT_BASIC_INFORMATION,5000,0)!=null){
			click(ELEMENT_EDIT_BASIC_INFORMATION);
		}
		if (firstName !="" && firstName!=null){
			info("update firstname");
			type(ELEMENT_FIRST_NAME_TEXTBOX_EDIT, firstName, true);
		}
		if (lastName !="" && lastName!=null){
			info("update lastName");
			type(ELEMENT_LAST_NAME_TEXTBOX_EDIT, lastName, true);
		}
		if (email !="" && email!=null){
			info("update email");
			type(ELEMENT_EMAIL_TEXTBOX_EDIT, email, true);
		}
	} 

	/**
	 * Change avatar
	 * @param linkfile : File path of new avatar
	 */
	public void changeAvatar(String linkfile){
		info("-- changeAvatar --");
		click(ELEMENT_EDIT_MY_PROFILE);
		click(ELEMENT_CHANGE_AVATAR_LINK);
		click(ELEMENT_SELECT_AVATAR);
		uploadFileUsingRobot(linkfile);
		click(ELEMENT_CONFIRM);
		waitForElementNotPresent(ELEMENT_CONFIRM);
		click(ELEMENT_SAVE_AVATAR);
		Utils.pause(1000);
	}

	/**
	 * Update information of contact of a user
	 * @param gender
	 * @param job
	 */
	public void updateGenderJob(String gender, String job) {
		if(waitForAndGetElement(ELEMENT_CONTACT_EDIT_ICON,5000,0)!=null)
			click(ELEMENT_CONTACT_EDIT_ICON);
		if(gender !="" && gender!=null){
			info("update gender");
			select(ELEMENT_CONTACT_GENDER_SELECTION, gender);
		}
		if(job !="" && job!=null){
			info("update job");
			type(ELEMENT_CONTACT_JOB_TITLE,job,true);
		}
		
	}

	/**
	 * Update ims
	 * @param type
	 * @param ims
	 * @param opParams
	 */
	public void updateIms(String type, String ims, Object... opParams){
		info("Update ims");
		String index = (String) (opParams.length > 0 ? opParams[0]: "0");
		Integer xpathCount= getElements(ELEMENT_CONTACT_IMS_INPUT_LIST).size();
		if(Integer.valueOf(index)>=xpathCount){
			click(ELEMENT_CONTACT_IMS_ADD_ICON);
		}
		if(type!=null && !type.isEmpty()){
			info("select type of ims");
			select(ELEMENT_CONTACT_IMS_OPTION.replace("${index}", index),type);
		}
		if(ims!=null && !ims.isEmpty()){
			info("update ims "+ims);
			type(ELEMENT_CONTACT_IMS_INPUT.replace("${index}", index),ims,true);
		}
	}

	/**
	 * 
	 * @param url
	 * @param opParams
	 */
	public void updateUrl(String url, Object... opParams){
		String index = (String) (opParams.length > 0 ? opParams[0]: "0");
		Integer xpathCount= getElements(ELEMENT_CONTACT_URL_INPUT_LIST).size();
		if(Integer.valueOf(index)>=xpathCount){
			click(ELEMENT_CONTACT_URL_ADD_ICON);
		}
		if(url!=null && !url.isEmpty()){
			info("update url");
			type(ELEMENT_CONTACT_URL_INPUT.replace("${index}", index),url,true);
		}
	}

	/**
	 * Update phone
	 * @param type
	 * @param phone
	 * @param opParams
	 */
	public void updatePhone(String type,String phone, Object... opParams){
		info("Update phone");
		String index = (String) (opParams.length > 0 ? opParams[0]: "0");
		Integer xpathCount= getElements(ELEMENT_CONTACT_PHONE_INPUT_LIST).size();
		if(Integer.valueOf(index)>=xpathCount){
			click(ELEMENT_CONTACT_PHONE_ADD_ICON);
		}
		if(type!=null && !type.isEmpty()){
			info("select type of phone");
			select(ELEMENT_CONTACT_PHONE_OPTION.replace("${index}", index),type);
		}
		if(phone!=null && !phone.isEmpty()){
			info("update phone");
			type(ELEMENT_CONTACT_PHONE_INPUT.replace("${index}", index),phone,true);
		}
	}

	/**
	 * update experience 
	 * @param organization
	 * @param jobTitle
	 * @param jobDetail
	 * @param skill
	 * @param startDate
	 * @param endDate
	 * @param curPos
	 * @param opParams
	 */
	public void updateExperience(String organization,String jobTitle,String jobDetail, 
			String skill, String startDate, String endDate, Boolean curPos, Object... opParams){
		String index = (String) (opParams.length > 0 ? opParams[0]: "0");	
		Integer xpathCount= getElements(ELEMENT_EXPERIENCE_LIST).size();
		if(Integer.valueOf(index)>=xpathCount){
			click(ELEMENT_ADD_MORE_EXP_ICON,0,true);
		}
		info("-- update experience --");
		if(organization!=null && organization != ""){
			type(ELEMENT_EXPERIENCE_COMPANY_INPUT.replace("${index}", index),organization, true);
		}
		if(jobTitle!=null && jobTitle != ""){
			type(ELEMENT_EXPERIENCE_POSITION_INPUT.replace("${index}", index),jobTitle, true);
		}
		if(jobDetail!=null && jobDetail != ""){
			type(ELEMENT_EXPERIENCE_DESCRIPTION_INPUT.replace("${index}", index),jobDetail, true);
		}
		if(skill!=null && skill != ""){
			type(ELEMENT_EXPERIENCE_SKILL_INPUT.replace("${index}", index),skill, true);
		}
		if(startDate!=null && startDate != ""){
			type(ELEMENT_EXPERIENCE_START_DATE_INPUT.replace("${index}", index),startDate, true);
		}
		if(endDate!=null && endDate != ""){
			type(ELEMENT_EXPERIENCE_END_DATE_INPUT.replace("${index}", index),endDate, true);
		}
		if(curPos!=null && curPos){
			check(ELEMENT_EXPERIENCE_CURRENT_CHECKBOX.replace("${index}", index),2);
		}
		else{
			uncheck(ELEMENT_EXPERIENCE_CURRENT_CHECKBOX.replace("${index}", index),2);
		}
	} 

	/**
	 * Save or cancle update info
	 * @param isSave 
	 * 				null or true: save updating
	 * 				false: cancel
	 */
	public void saveCancelUpdateInfo(Boolean isSave){
		if(isSave==null || isSave){
			info("Save updating information");
			click(ELEMENT_CONTACT_SAVE_BUTTON);
			Utils.pause(2000);	
		}
		else{
			info("Cancel updating information");
			click(ELEMENT_CONTACT_CANCEL_BUTTON);
			Utils.pause(2000);	
		}	
	}
}
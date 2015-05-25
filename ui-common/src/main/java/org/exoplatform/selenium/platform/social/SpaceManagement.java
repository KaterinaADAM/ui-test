package org.exoplatform.selenium.platform.social;

import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.exoplatform.selenium.TestLogger.info;
/**
 * update : quynhpt
 * date: 0601/2014
 *
 */
public class SpaceManagement extends SpaceHomePage {

	// Add form space
	public final By ELEMENT_ADDNEWSPACE_BUTTON = By.xpath("//button[contains(.,'Add New Space')]");
	public final By ELEMENT_ADDNEWSPACE_FORM = By.xpath("//span[@class='PopupTitle popupTitle' and text()='Add New Space']");
	public final By ELEMENT_ADDNEWSPACE_ICON = By.xpath("//*[contains(@class, 'uiIconSocSimplePlus')]");
	
	//Search panel
	public final By ELEMENT_MY_SPACE_SEARCH_TEXT_BOX = By.xpath(".//*[@id='SpaceSearch']");
	public final By ELEMENT_MY_SPACE_SEARCH_BTN = By.xpath(".//*[@id='UISpaceSearch']//i[@class='uiIconSearch uiIconLightGray']");
	public final String ELEMENT_MY_SPACE_SEARCH_RESULT = ".//*[@id='UIManageMySpaces']//*[contains(text(),'${name}')]";
	public final String ELEMENT_MY_SPACE_SEARCH_RESULT_NUMBER = ".//*[@id='UIManageMySpaces']//*[@class='number'][text()='${number}']";

	//Letter list 
	public final String ELEMENT_MY_SPACE_LETTER_LIST= ".//*[@class='letterList']//*[text()='${alpha}']";

	//Space portlets
	public By ELEMENT_SPACE_MY_SPACE_PORTLET = By.id("UIMySpacesPortlet");
	public By ELEMENT_SPACE_ALL_SPACE_PORTLET = By.id("UIAllSpacesPortlet");
	public By ELEMENT_SPACE_INVITATION_SPACE_PORTLET = By.id("UIInvitationSpacesPortlet");
	public By ELEMENT_SPACE_PENDING_SPACE_PORTLET = By.id("UIPendingSpacesPortlet");

	//Add new space buttons
	public By ELEMENT_ADD_NEW_SPACE_BUTTON = By.xpath("//*[@class='uiIconSocSimplePlus uiIconSocWhite']");
	public By ELEMENT_ADD_SPACE_FORM = By.id("UIPopupAddSpace");

	//Add new space popup and Setting tab
	public final By ELEMENT_SPACE_NAME_INPUT = By.xpath("//input[contains(@name,'displayName')]");
	public final By ELEMENT_SPACE_DESCRIPTION_INPUT = By.xpath("//textarea[contains(@name,'description')]");
	public final By ELEMENT_SPACE_CHANGE_AVATAR_BTN = By.xpath(".//*[@id='UISpaceInfo']//button[text()='Change Picture']");
	public final By ELEMENT_UPLOAD_POPUP_SELECT_FILE_BTN=By.xpath(".//*[@id='Uploader']//label[text()='Select File']");
	public final By ELEMENT_SPACE_SAVE_BTN = By.xpath(".//*[@id='UISpaceInfo']//button[text()='Save']");
	public final By ELEMENT_SPACE_UPLOAD_CONFIRM_BTN=By.xpath(".//*[@id='UIAvatarUploader']//button[text()='Confirm']");
	public final By ELEMENT_SPACE_UPLOAD_SAVE_BTN=By.xpath(".//*[@id='UIAvatarUploadContent']//button[text()='Save']");

	//Access and Edit tab form
	public By ELEMENT_SPACE_ACCESS_EDIT_TAB=By.xpath("//*[@data-target='#UISpacePermission-tab']");
	public By ELEMENT_SPACE_VISIBILITY_VISIBLE_CHECKBOX=By.xpath("//*[@value='private']");
	public By ELEMENT_SPACE_VISIBILITY_HIDDEN_CHECKBOX=By.xpath("//*[@value='hidden']");
	public By ELEMENT_SPACE_REGISTRATION_OPEN_CHECKBOX=By.xpath("//*[@value='open']");
	public By ELEMENT_SPACE_REGISTRATION_CLOSED_CHECKBOX=By.xpath("//*[@value='close']");
	public By ELEMENT_SPACE_REGISTRATION_VALIDATION_CHECKBOX=By.xpath("//*[@value='validation']");


	//Access and Edit tab form
	public By ELEMENT_SPACE_INVITE_GROUP_USER_TAB=By.xpath("//*[@data-target='#UISpaceGroupBound-tab']");
	public By ELEMENT_SPACE_SELECT_EXIST_GROUP_CHECKBOX=By.id("UseExistingGroupCheckBox");

	//Button create
	public By ELEMENET_SPACE_CREATE_BUTTON=By.xpath("//*[@class='uiAction']/*[text()='Create']");

	//My space
	public final By ELEMENT_SPACE_MY_SPACE_TAB=By.xpath(".//*[@id='UIManageAllSpaces']//*[contains(text(),'My Spaces')]");
	public String ELEMENT_SPACE_TITLE="//*[@class='spaceTitle']//*[text()='${space}']";
	public final String ELEMENT_SPACE_DESCRIPTION=".//*[@id='UIManageMySpaces']//*[@class='content limitText'][text()='${des}']";
	public final By ELEMENT_SPACE_AVATAR_DEFAULT=By.xpath(".//*[@id='UISpaceInfo']//*[contains(@src,'SpaceAvtDefault.png')]");
	public final String ELEMENT_SPACE_DELETE_BUTTON="//*[@class='spaceTitle']//*[text()='${space}']/../../..//*[text()='Delete']";
	public final String ELEMENT_SPACE_LEAVE_BTN = "//*[@class='spaceTitle']//*[text()='${space}']/../../..//*[text()='Leave']";
	public final String ELEMENT_SPACE_EDIT_BTN = "	//*[@class='spaceTitle']//*[text()='${space}']/../../..//*[text()='Edit']";
    public final By ELEMENT_SPACE_EDIT_SETTING_TAB =By.xpath(".//*[contains(@data-target,'#UISpaceInfo-tab')]");
	public final String ELEMENT_SPACE_MEMBER_INFOR="//*[@class='spaceTitle']//*[text()='${space}']/../..//*[contains(@class,'membersCount')]";
	public final String ELEMENT_SPACE_DESC_INFOR="//*[@class='spaceTitle']//*[text()='${space}']/../..//*[contains(@class,'content')]";
	public final String ELEMENT_SPACE_MANAGER_STATUS="//*[@class='spaceTitle']//*[text()='${space}']/../../..//*[contains(@class,'statusLabel') and text()='Manager']";
	public String ELEMENT_SPACE_CONFIRM_DELETE="Are you sure you want to delete this space? This cannot be undone. All page navigations and this group will also be deleted";
	public By ELEMENT_SPACE_DELETE_SPACE_OK_BUTTON=By.xpath("//*[text()='OK']");

	public String ELEMENT_SPACE_NAME_BREADCUMB ="//*[@id='UIBreadCrumbsNavigationPortlet']//*[@class='name' and contains(text(),'{$name}')]";

	//Invitations received tab
	public final By ELEMENT_MY_SPACE_INVITATION_RECEIVED = By.xpath(".//a[text()='Invitations Received']");
	public final String ELEMENT_MY_SPACE_INVITATION_RECEIVED_ACCEPT_BTN = ".//*[contains(text(),'${space}')]/../../..//button[text()='Accept']";
	public final String ELEMENT_MY_SPACE_INVITATION_RECEIVED_CANCEL_BTN = ".//*[contains(text(),'${space}')]/../../..//button[text()='Ignore']";

	//All Spaces tab
	public final By ELEMENT_MY_SPACE_ALL_SPACES_TAB = By.xpath(".//*[@id='UIPage']//*[contains(@href,'all-spaces')]");
	public final String ELEMENT_MY_SPACE_ALL_SPACES_REQUEST_TO_JOIN_BTN = ".//*[contains(text(),'${space}')]/../../..//button[text()='Request to Join']";
	public final String ELEMENT_MY_SPACE_ALL_SPACES_JOIN_BTN = ".//*[contains(text(),'${space}')]/../../..//button[text()='Join']";
	public final String ELEMENT_MY_SPACE_ALL_SPACES_REQUEST_PENDING = ".//*[contains(text(),'${space}')]/../../..//*[contains(text(),'Request Pending')]";
    public final By ELEMENT_ALL_SPACE_ACTIVE_TAB=By.xpath(".//*[@id='UIManageAllSpaces']//*[contains(@class,'active')]//*[contains(@href,'all-spaces')]");
	
    //Request pending tab
	public final By ELEMENT_MY_SPACE_REQUEST_PENDING_TAB = By.xpath("//*[contains(@href,'pendingSpace')]");
	public final String ELEMENT_SPACE_CANCEL_BUTTON="//*[@class='spaceTitle']//*[text()='${space}']/../../..//*[text()='Cancel']";
	
	//Members
	public final By ELEMENT_SPACE_GOWIKI = By.xpath("//*[@class='uiIconAppWikiPortlet uiIconDefaultApp']/..//*[@id='wiki']");
	public final By ELEMENT_SPACE_MEMBERS = By.xpath("//*[@data-toggle='tab' and text()='Members']");
	public final By ELEMENT_SPACE_GOSETTINGS = By.xpath("//*[@id='settings']");
	public final By ELEMENT_SPACE_TEXTBOX_USER = By.xpath("//*[@id='user']");
	public final By ELEMENT_SPACE_TEXTBOX_USER_SUGGEST = By.xpath("//*[@class='text' and text()='Mary Williams']");
	public final By ELEMENT_SPACE_BTN_INVITE = By.xpath("//*[text()='Invite']");
	public final String ELEMENT_SPACE_BTN_MANAGER = "//*[text()='${name}']/..//*[@class='switchBtnLabelOff']";
	public final String ELEMENT_SPACE_MEMBER_USER_MANAGER=".//*[@id='existingUsersTable']//*[contains(text(),'${fullName}')]/..//*[@class='switchBtnHandle' and contains(@style,'left: 41px;')]";
	public final String ELEMENT_SPACE_MEMBER_USER_MEMBER=".//*[@id='existingUsersTable']//*[contains(text(),'${fullName}')]/..//*[@class='switchBtnHandle' and not(contains(@style,'left: 41px;'))]";
	
	public final By ELEMENT_SPACE_BTN_ACCEPT_INVITE = By.xpath("//*[text()='Accept']");
	public final By ELEMENT_SPACE_ALLSPACES = By.xpath("//*[text()='All Spaces']");

	//Request to join a space
	public final String ELEMENT_REQUEST_TO_JOIN_SPACE_BTN = "//*[contains(text(),'${space}')]/../../..//button[text()='Request to Join']";
	public final String ELEMENT_REQUEST_PENDING = "//*[contains(text(),'${space}')]/../../..//*[text()='Request Pending']";
	
	//Forum tab
	public final By ELEMENT_FORUM_START_BUTTON_UP = By.xpath("(.//*[@id='UITopicContainer']//*[contains(@class,'uiIconForumCreateTopic ')])[1]");

	//Wiki tab
	public final By ELEMENT_WIKI_HOME_TITLE =By.xpath(".//*[@id='titleInfo']");
	//Document tab
	public final By ELEMENT_DOCUMENT_FOLDER_ADD_BTN = By.xpath(".//*[contains(@class,'uiIconEcmsAddFolder ')]");	
	
	//Agenda tab
    public final By ELEMENT_AGENDA_EVENT_ADD_BTN =By.xpath(".//*[@id='UIActionBarQuickAddEvent']");
	
    //Member tab
	public final By ELEMENT_MEMBER_USER_INFOR = By.xpath(".//*[@id='spaceManagerListBox']");
	public final By ELEMENT_MEMBER_USER_SEARCH= By.xpath(".//*[@id='UIProfileUserSearch']");
	public final By ELEMENT_MEMBER_USER_CONTACT_LIST=By.xpath(".//*[@id='spaceMemberListBox']");
	public final String ELEMENT_MEMBER_USER_NAME = ".//*[@id='spaceMemberListBox']//*[contains(@data-text,'${fullName}')]";
	

	ManageAlert alert;

	/** 
	 * constructor
	 * @param dr
	 */
	public SpaceManagement(WebDriver dr){
		super(dr);
		this.driver=dr;
		alert = new ManageAlert(driver);
	}

	/**
	 * Open create space form
	 */
	public void goToCreateSpace(){
		info("Open create space form");
		click(ELEMENT_ADD_NEW_SPACE_BUTTON);
		waitForAndGetElement(ELEMENT_ADD_SPACE_FORM);
	}

	/**
	 * delete Space
	 * @param spaceName
	 * 					name of space
	 * @param isVerify
	 * 					true: verify content of confirm msg
	 * 					false: not verify content of confirm msg
	 */
	public void deleteSpace(String spaceName, Boolean isVerify){
		info("Do delete space");
		click(ELEMENT_SPACE_DELETE_BUTTON.replace("${space}", spaceName));
		if(isVerify)
			alert.verifyAlertMessage(ELEMENT_SPACE_CONFIRM_DELETE);
		click(ELEMENT_SPACE_DELETE_SPACE_OK_BUTTON);
		waitForElementNotPresent(ELEMENT_SPACE_DELETE_BUTTON.replace("${space}", spaceName),60000);

	}
	/**
	 * Leave a space
	 * @param space
	 */
	public void leaveSpace(String space){
		info("Do leave space");
		click(ELEMENT_SPACE_LEAVE_BTN.replace("${space}", space));
		waitForElementNotPresent(ELEMENT_SPACE_LEAVE_BTN.replace("${space}", space));
	}


	/**
	 * Create quickly a new space
	 * 
	 * @param name : Space name
	 * @param desc : Space description
	 * 
	 */
	public void addNewSpaceSimple(String name, String desc, int... params) {
		int iTimeout = params.length > 0 ? params[0] : DEFAULT_TIMEOUT; 
		if (waitForAndGetElement(ELEMENT_ADDNEWSPACE_BUTTON, 3000, 0, 2) != null){
			click(ELEMENT_ADDNEWSPACE_BUTTON);
		}else {
			click(By.xpath("//*[contains(@class, 'uiIconSocSimplePlus')]"));
		}
		waitForAndGetElement(ELEMENT_ADDNEWSPACE_FORM,3000,0);
		type(ELEMENT_SPACE_NAME_INPUT, name, true);
		type(ELEMENT_SPACE_DESCRIPTION_INPUT, desc, true);
		info("Save all changes");
		click(ELEMENET_SPACE_CREATE_BUTTON);
		waitForAndGetElement(By.linkText(name), iTimeout);
	}
	
	/**
	 * Create quickly a new space
	 * 
	 * @param name : Space name
	 * @param desc : Space description
	 * 
	 */
	public void addNewSpace(String name, String desc, int... params) {
		int iTimeout = params.length > 0 ? params[0] : DEFAULT_TIMEOUT; 
		if (waitForAndGetElement(ELEMENT_ADDNEWSPACE_BUTTON, 3000, 0, 2) != null){
			click(ELEMENT_ADDNEWSPACE_BUTTON);
		}else {
			click(By.xpath("//*[contains(@class, 'uiIconSocSimplePlus')]"));
		}
		waitForAndGetElement(ELEMENT_ADDNEWSPACE_FORM,3000,0);
		type(ELEMENT_SPACE_NAME_INPUT, name, true);
		type(ELEMENT_SPACE_DESCRIPTION_INPUT, desc, true);
		info("Save all changes");
		click(ELEMENET_SPACE_CREATE_BUTTON);
		waitForAndGetElement(By.linkText(name), iTimeout);
	}
	
	/**
	 * Edit a Space
	 * @param space
	 * @param newName
	 * @param newDes
	 * @param isChangeAvatar
	 * @param filepath
	 */
	public void editSpaceSimple(String space,String newName,String newDes,boolean isChangeAvatar,String filepath){
		info("Click on Edit button of the space");
		if (waitForAndGetElement(
				ELEMENT_SPACE_EDIT_BTN.replace("${space}", space), 3000, 0) != null)
			click(ELEMENT_SPACE_EDIT_BTN.replace("${space}", space));
		if (waitForAndGetElement(ELEMENT_SPACE_NAME_INPUT, 3000, 0) == null)
			click(ELEMENT_SPACE_EDIT_SETTING_TAB);
		if (!newName.isEmpty()) {
			info("Input new name");
			type(ELEMENT_SPACE_NAME_INPUT, newName, true);
		}
		if (!newDes.isEmpty()) {
			info("Input new description");
			type(ELEMENT_SPACE_DESCRIPTION_INPUT, newDes, true);
		}
		if (isChangeAvatar == true) {
			info("click on change picture button");
			click(ELEMENT_SPACE_CHANGE_AVATAR_BTN);
			info("click on upload button");
			click(ELEMENT_UPLOAD_POPUP_SELECT_FILE_BTN);
			Utils.pause(2000);
			info("filepath:" + filepath);
			uploadFileUsingRobot(filepath);
			Utils.pause(2000);
			click(ELEMENT_SPACE_UPLOAD_CONFIRM_BTN);
			click(ELEMENT_SPACE_UPLOAD_SAVE_BTN);
			Utils.pause(2000);
		}

	}
	
	/**
	 * Save change all when edit a space
	 */
	public void saveChangesSpace(){
		info("click on Save button");
		click(ELEMENT_SPACE_SAVE_BTN);
		info("Save all changes");
		Utils.pause(2000);
	}
	/**
	 * Search a space by name or description
	 * @param name
	 * @param number
	 */
	public void searchSpace(String name, String number){
		info("Waiting my space is shown");
		waitForAndGetElement(ELEMENT_MY_SPACE_SEARCH_TEXT_BOX,3000,0);
		info("Input the space into search text box");
		type(ELEMENT_MY_SPACE_SEARCH_TEXT_BOX,name,true);
		info("Click on Search button");
		click(ELEMENT_MY_SPACE_SEARCH_BTN);
		if(!name.isEmpty()){
			info("Verify that the space is shown in the search result");
			waitForAndGetElement(ELEMENT_MY_SPACE_SEARCH_RESULT.replace("${name}", name),3000,0);
		}
		if(!number.isEmpty()){
			info("Verify that the number of search result is shown correctly");
			waitForAndGetElement(ELEMENT_MY_SPACE_SEARCH_RESULT_NUMBER.replace("${number}", number),3000,0);
		}
	}
	/**
	 * Click on an alpha in the list
	 * @param alpha
	 * @param name
	 */
	public void searchByLetterList(String alpha,String name){
		info("Waiting my space is shown");
		waitForAndGetElement(ELEMENT_MY_SPACE_LETTER_LIST.replace("${alpha}", alpha),3000,0);
		info("click on the alpha");
		click(ELEMENT_MY_SPACE_LETTER_LIST.replace("${alpha}", alpha));
		info("Verify that the space is shown in the search result");
		waitForAndGetElement(ELEMENT_MY_SPACE_SEARCH_RESULT.replace("${name}", name),3000,0);
	}
	/**
	 * Open Invitations received tab
	 */
	public void goToInvitationsReceivedTab(){
		info("Open Invitation Received tab");
		waitForAndGetElement(ELEMENT_MY_SPACE_INVITATION_RECEIVED,3000,0);
		click(ELEMENT_MY_SPACE_INVITATION_RECEIVED);
		Utils.pause(2000);

	}

	/**
	 * Open All Spaces tab
	 */
	public void goToAllSpacesTab(){
		info("Open All spaces tab");
		waitForAndGetElement(ELEMENT_MY_SPACE_ALL_SPACES_TAB,3000,0);
		click(ELEMENT_MY_SPACE_ALL_SPACES_TAB);
		Utils.pause(2000);
	}

	/**
	 * Send a request to a space
	 * @param space
	 */
	public void sendARequestToASpace(String space,boolean... isVerify){
		info("Send a request to a space");
		waitForAndGetElement(ELEMENT_MY_SPACE_ALL_SPACES_REQUEST_TO_JOIN_BTN.replace("${space}", space),2000,0).click();
		if (isVerify.length > 0) {
			info("Verify that request to join button is hidden and request pending status is shown");
			waitForAndGetElement(ELEMENT_MY_SPACE_ALL_SPACES_REQUEST_PENDING.replace("${space}", space), 3000, 1);
		}
	}
	
	/**
	 * Join to a space
	 * @param space
	 */
	public void joinToASpace(String space){
		info("Join to a space");
		waitForAndGetElement(ELEMENT_MY_SPACE_ALL_SPACES_JOIN_BTN.replace("${space}", space),2000,0).click();
		info("Verify that join button is hidden and leave button is shown");
		waitForAndGetElement(ELEMENT_SPACE_LEAVE_BTN.replace("${space}", space),3000,1);
	}
	
	/**
	 * Open request pending tab
	 */
	public void goToRequestPendingTab(){
		info("Open Request pending tab");
		click(ELEMENT_MY_SPACE_REQUEST_PENDING_TAB);
		Utils.pause(2000);
	}
	/**
	 * Accept an invitation of the space
	 * @param space
	 */
	public void acceptAInvitation(String space){
		info("Open invitation received tab");
		goToInvitationsReceivedTab();
		info("Click on Accept button of the space");
		click(ELEMENT_MY_SPACE_INVITATION_RECEIVED_ACCEPT_BTN.replace("${space}",space));
		info("Verify that the user joijed to the space");
		waitForAndGetElement( ELEMENT_SPACE_NAME.replace("${name}",space),3000,1);
	}
	/**
	 * Ignore an invitation of the space
	 * @param space
	 */
	public void ignoreAInvitation(String space){
		info("Open invitation received tab");
		goToInvitationsReceivedTab();
		info("Click on Ignore button of the space");
		click(ELEMENT_MY_SPACE_INVITATION_RECEIVED_CANCEL_BTN.replace("${space}",space));
		info("Verify that the user didn't join to the space");
		waitForElementNotPresent(ELEMENT_MY_SPACE_INVITATION_RECEIVED_CANCEL_BTN.replace("${space}",space));
	}
    /**
     * Request to join a Space
     * @param space
     */
	public void requestToJoinSpace(String space,boolean... isVerify){
		info("Request to join a space");
		searchSpace(space, "");
		click(ELEMENT_REQUEST_TO_JOIN_SPACE_BTN.replace("${space}", space));
		if (isVerify.length > 0) {
			waitForAndGetElement(
					ELEMENT_REQUEST_PENDING.replace("${space}", space), 2000, 1);
		}
		info("Request successfully");
	}
	/**
	 * Join to space
	 * @param space
	 * @param isVerify
	 */
	public void joinToSpace(String space,boolean... isVerify){
		info("Request to join a space");
		searchSpace(space, "");
		click(ELEMENT_MY_SPACE_ALL_SPACES_JOIN_BTN.replace("${space}", space));
		if (isVerify.length > 0) {
			waitForAndGetElement(
					ELEMENT_SPACE_LEAVE_BTN.replace("${space}", space), 2000, 1);
		}
		info("Request successfully");
	}
	/**
	 * Cancel a pending request
	 * @param space
	 */
	public void cancelPendingRequest(String space){
		info("Click on Cancel button");
		click(ELEMENT_SPACE_CANCEL_BUTTON.replace("${space}",space));
		waitForElementNotPresent(ELEMENT_SPACE_TITLE.replace("${space}",space),2000,1);
		info("Cancelling pending request is success");
	}
	
	/**
	 * Open Activity Stream portlet
	 */
	public void goToActivityStreamTab(){
		info("Open Activity STream Tab");
		click(ELEMENT_ACTIVITY_STREAM_TAB);
		Utils.pause(2000);
		info("Activity STream portlet is shown");
	}
	
	/**
	 * Open Forum portlet
	 */
	public void goToForumTab(){
		info("Open forum Tab");
		click(ELEMENT_FORUM_TAB);
		waitForAndGetElement(ELEMENT_FORUM_START_BUTTON_UP,2000,0);
		info("Forum portlet is shown");
	}
	/**
	 * Open Wiki tab
	 */
	public void goToWikiTab(){
		info("Open Wiki Tab");
		click(ELEMENT_WIKI_TAB);
		waitForAndGetElement(ELEMENT_WIKI_HOME_TITLE,2000,0);
		info("Wiki portlet is shown");
	}
	
	/**
	 * Open Document tab
	 */
	public void goToDocumentTab(){
		info("Open Document Tab");
		click(ELEMENT_DOCUMENT_TAB);
		waitForAndGetElement(ELEMENT_DOCUMENT_FOLDER_ADD_BTN,2000,0);
		info("Document portlet is shown");
	}

	/**
	 * Open Agenda tab
	 */
	public void goToAgendaTab(){
		info("Open Agenda Tab");
		click(ELEMENT_AGENDA_TAB);
		waitForAndGetElement(ELEMENT_AGENDA_EVENT_ADD_BTN,2000,0);
		Utils.pause(2000);
		info("Agenda portlet is shown");
	}
	
	/**
	 * Open Member tab
	 */
	public void goToMemberTab(){
		info("Open Member Tab");
		click(ELEMENT_MEMBER_TAB);
		waitForAndGetElement(ELEMENT_MEMBER_USER_INFOR,2000,0);
		info("Member portlet is shown");
	}
	/**
	 * Open user profile portlet of a user
	 * @param fullName
	 */
	public void goToUserProfilePage(String fullName){
		info("Open User profile page");
		click(ELEMENT_MEMBER_USER_NAME.replace("${fullName}",fullName));
		Utils.pause(2000);
	}
	
}

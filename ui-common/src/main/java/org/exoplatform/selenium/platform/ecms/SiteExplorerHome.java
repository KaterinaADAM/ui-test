package org.exoplatform.selenium.platform.ecms;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.Dialog;
import org.exoplatform.selenium.ManageAlert;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.PlatformBase;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SiteExplorerHome extends PlatformBase{
	

	public final By ELEMENT_SITEEXPLORER_WORKING_PANEL = By.xpath("//*[@class='navItemSelected' and text()='Content Explorer']");
	public final By ELEMENT_DOCUMENT_LIST_ROW_CONTENT = By.xpath(".//*[@id='UIDocumentNodeList']//*[contains(@class,'rowView')]");
	
	//Address Bar
	public final By ELEMENT_ADDRESS_BAR_ICON_VIEW = By.xpath(".//*[@id='UIAddressBar']//*[@class='uiIconEcmsViewDefault uiIconEcmsViewIcons uiIconEcmsLightGray']");
	public final By ELEMENT_SITE_PATH= By.cssSelector("#address");
	
	//Action Bar
	public final By ELEMENT_ACTIONBAR_ADDDOCUMENT = By.xpath("//*[@class='uiIconEcmsAddDocument uiIconEcmsLightGray']");
	public final By ELEMENT_ACTIONBAR_ADDFOLDER = By.xpath("//*[@class='uiIconEcmsAddFolder uiIconEcmsLightGray']");
	public final By ELEMENT_ACTIONBAR_SETTINGS = By.xpath("//*[@class='setupPreferencesButton actionIcon pull-right']");
	public final By ELEMENT_ACTIONBAR_PERMISSION = By.xpath("//*[@class='uiIconEcmsViewPermissions uiIconEcmsLightGray']");
	public final By ELEMENT_ACTIONBAR_SEARCHBAR = By.xpath("//*[@id='simpleSearch']");
	public final By ELEMENT_ACTIONBAR_MORE = By.xpath("//*[@class='dropdown pull-right listHiddenActionsContainer']/..//*[text()='More ']");
	public final By ELEMENT_ACTIONBAR_METADATA = By.xpath(".//*[@class='uiIconEcmsViewMetadatas uiIconEcmsLightGray']");

	public final By ELEMENT_ACTIONBAR_ADDTRANSLATION = By.xpath("//*[@class='uiIconEcmsAddLocalizationLink uiIconEcmsLightGray']");
	public final By ELEMENT_ACTIONBAR_ADDCOMMENT = By.xpath("//*[@class='uiIconEcmsComment uiIconEcmsLightGray']");
	public final By ELEMENT_ACTIONBAR_PUBLICATION = By.xpath("//*[@class='uiIconEcmsManagePublications uiIconEcmsLightGray']");
	public final By ELEMENT_ACTIONBAR_VOTE = By.xpath("//*[@class='uiIconEcmsVote uiIconEcmsLightGray']");
	public final By ELEMENT_ACTIONBAR_RELATION = By.xpath(".//i[@class='uiIconEcmsManageRelations uiIconEcmsLightGray']");
	public final By ELEMENT_ACTIONBAR_IMPORT_BUTTON = By.xpath(".//i[@class='uiIconEcmsImportNode uiIconEcmsLightGray']");
	public final By ELEMENT_ACTIONBAR_EXPORT_BUTTON= By.xpath(".//*[@class='uiIconEcmsExportNode uiIconEcmsLightGray']");
	public final By ELEMENT_ACTIONBAR_PROPERTIES = By.xpath(".//i[@class='uiIconEcmsViewProperties uiIconEcmsLightGray']");
	public final By ELEMENT_ACTIONBAR_MANAGER_PUBLISHTATION =By.xpath(".//i[@class='uiIconEcmsManagePublications uiIconEcmsLightGray']");
	public final By ELEMENT_ACTIONBAR_CATEGORY = By.xpath("//*[@class='uiIconEcmsManageCategories uiIconEcmsLightGray']");
	public final By ELEMENT_ACTIONBAR_TAG = By.xpath("//*[@class='uiIconEcmsTaggingDocument uiIconEcmsLightGray']");
	public final By ELEMENT_ACTIONBAR_SHOWDRIVES = By.id("driveAction");
	public final By ELEMENT_ACTIONBAR_DELETE=By.xpath(".//*[@id='ECMContextMenu']//*[@class='uiIconEcmsDelete']");
	public final By ELEMENT_SITE_EXPLORER_ALL_CHECKBOX= By.xpath("//input[@type='checkbox' and @name= 'UIFileViewCheckBox']");
	public final By ELEMENT_DELETE_ALL_BUTTON = By.xpath(".//*[@id='JCRContextMenu']//i[@class='uiIconEcmsDelete']");
	
	//Add Category popup
	public final By ELEMENT_ADD_CATEGORY_POPUP_SELECT_CATEGORY_TAB = By.xpath(".//*[@id='UICategoryManager']//a[text()='Select Category']");
	public final By ELEMENT_ADD_CATEGORY_POPUP_MENU = By.name("taxonomyTree");
	public final String ELEMENT_ADD_CATEGORY_POPUP_CATEGORY_NAME_LEFT_SIDE=".//*[@id='UIOneTaxonomySelector']//i[@title='${nameTitle}']";
	public final String ELEMENT_ADD_CATEGORY_POPUP_SELECT_CATEGORY_RIGHT_SIDE =".//*[@id='UISelectTaxonomyPanel']//div[contains(.,'${nameCategory}')]//../..//i[@class='uiIconValidate uiIconLightGray']";
	public final By ELEMENT_ADD_CATEGORY_POPUP_CLOSED_BUTTON= By.xpath(".//*[@id='UICategoryManager']//button[text()='Close']");
	public final String ELEMENT_ADD_CATEGORY_POPUP_DELETE_CATEGORY= ".//*[@id='UICategoriesAddedList']//td[contains(.,'${nameCategory}')]/../..//i[@class='uiIconDelete uiIconLightGray']";

	//Import Node popup
	public final By ELEMENT_IMPORT_NODE_POPUP_TITLE= By.xpath(".//*[@id='UIPopupWindow']//span[text()='Import']");
	public final By ELEMENT_IMPORT_NODE_POPUP_UPLOAD_BUTTON = By.name("file");
	public final By ELEMENT_IMPORT_NODE_POPUP_BEHAVIOR = By.name("behavior");
	public final By ELEMENT_IMPORT_NODE_POPUP_VERSION_HISTORY_BUTTON = By.xpath("//div[@id='versionHistory']//input[@name='file']");
	public final By ELEMENT_IMPORT_NODE_POPUP_BEHAVIOR_CREATE_NEW = By.xpath(".//*[@id='UIImportNode']//option[text()='Create New']");
	public final By ELEMENT_IMPORT_NODE_POPUP_BEHAVIOR_REMOVE_EXISTING = By.xpath(".//*[@id='UIImportNode']//option[text()='Remove Existing']");
	public final By ELEMENT_IMPORT_NODE_POPUP_BEHAVIOR_REPLACE_EXISTING = By.xpath(".//*[@id='UIImportNode']//option[text()='Replace Existing']");
	public final By ELEMENT_IMPORT_NODE_POPUP_BEHAVIOR_THROW_EXCEPTION = By.xpath(".//*[@id='UIImportNode']//option[text()='Throw Exception']");
	public final String ELEMENT_IMPORT_NODE_POPUP_UPLOAD_FILE_LABEL = "//div[@class='fileNameLabel' and contains(text(),'${fileName}')]";
	public final By ELEMENT_IMPORT_MODE_POPUP_IMPORT_BUTTON = By.xpath(".//*[@id='UIImportNode']//button[text()='Import']");

	//Export Node popup
	public final By ELEMENT_EXPORT_NODE_POPUP_TITLE= By.xpath(".//*[@id='UIPopupWindow']//span[text()='Export']");
	public final By ELEMENT_EXPORT_NODE_POPUP_DOC_VIEW = By.xpath(".//*[@id='UIExportNode']//input[@value='docview']");
	public final By ELEMENT_EXPORT_NODE_POPUP_SYS_VIEW = By.xpath(".//*[@id='UIExportNode']//input[@value='sysview']");
	public final By ELEMENT_EXPORT_NODE_POPUP_ZIP = By.name("zip");
	public final By ELEMENT_EXPORT_NODE_POPUP_EXPORT_BUTTON = By.xpath("//button[text()='Export']");

	//Relation popup
	public final By ELEMENT_RELATION_POPUP_SELECT_RELATION_TAB=By.xpath(".//*[@id='UIRelationManager']//a[text()='Select Relation']");
	public final String ELEMENT_RELATION_POPUP_SELECT_RELATION_TAB_NODE_LEFT_TREE=".//*[@id='UIOneNodePathSelector']//i[@title='${nameNode}']";
	public final String ELEMENT_RELATION_POPUP_SELECT_RELATION_TAB_SELECT_CONTENT_RIGHT_TREE =".//*[@id='UISelectPathPanel']//div[contains(.,'${nameContent}')]/../..//*[@class='uiIconValidate uiIconLightGray']";
	public final By ELEMENT_RELATION_POPUP_SELECT_RELATION_TAB_CLOSE_BUTTON=By.xpath(".//*[@id='UIRelationManager']//button[text()='Close']");
	public final String ELEMENT_RELATION_POPUP_RELATION_LIST_DELETE_BUTTON=".//*[@id='RelateAddedList']//span[contains(.,'${nameContent}')]/../..//i[@class='uiIconDelete uiIconLightGray']";
	public final String MESSAGE_DELETE_RELATION = "Are you sure you want to delete this relation?";

	//Metadata popup
	public final By ELEMENT_METADATA_POPUP_CANCEL= By.xpath(".//*[@id='UIViewMetadataContainer']//button[text()='Cancel']");
	public final By ELEMENT_METADATA_POPUP= By.xpath("//*[@id='UIViewMetadataManager']");

	// go to Show drives
     public final By ELEMENT_SHOW_DRIVES = By.cssSelector("#driveAction");
     public final String ELEMENT_SELECTED_DRIVE= ".//*[@data-original-title='${nameDrive}']";
		
	//Drive area
	public final String ELEMENT_ACTIONBAR_SELECTED_DRIVE= ".//*[@id='UIDrivesArea']//*[@data-original-title='${drive}']";

	// View Properties form
	public final By ELEMENT_VIEWPROPERTIES_PROPERTIES_TAB = By.linkText("Properties");
	public final By ELEMENT_VIEWPROPERTIES_ADD_PROPERTY_TAB = By.linkText("Add New Property");
	public final By ELEMENT_VIEWPROPERTIES_VALUE_INPUT = By.xpath("//input[contains(@id,'value')]");
	public final By ELEMENT_VIEWPROPERTIES_ADD_PROPERTY_INPUT = By.name("property_select");
	public final String ELEMENT_VIEWPROPERTIES_PROPERTY = "//td[text()='{$property}']/..//div[contains(text(),'{$value}')]";

	//Manage Publishtation popup
	public final String ELEMENT_MANAGEPUBLICATION_STATE = "//p[contains(text(),'{$state}')]/../a[@class='node']";	
	public final String ELEMENT_MANAGEPUBLICATION_CURRENT_SPECIFIC_STATUS = "//*[@class='currentStatus']/p[contains(text(),'${status}')]";
	public final By ELEMENT_MANAGEPUBLICATION_SCHEDULE_TAB = By.xpath("//a[text()='Scheduled']");	
	public final By ELEMENT_MANAGEPUBLICATION_PUB_FROM_INPUT = By.name("UIPublicationPanelStartDateInput");
	public final By ELEMENT_MANAGEPUBLICATION_PUB_TO_INPUT = By.name("UIPublicationPanelEndDateInput");
	public final String MSG_INVALID_DATE_TIME = "The date format is invalid. Please check again.";
	public final By ELEMENT_MANAGEPUBLICATION_REVISION_TAB = By.linkText("Revision");
	public final By ELEMENT_MANAGEPUBLICATION_HISTORY_TAB = By.linkText("History");
	public final String ELEMENT_MANAGEPUBLICATION_HISTORY_ITEM = "//div[text()='${state}']";

	//add translation popup
	public final By ELEMENT_ACTIONBAR_ADDTAG = By.xpath("//*[@class='uiIconEcmsTaggingDocument uiIconEcmsLightGray']");
	public final By ELEMENT_ADDTRANSLATION_SELECTDOC = By.xpath("//*[@title='Select Document']");
	public final String ELEMENT_FOLDERSELECTOR_PATH = "//*[@class='nodeName' and text()=' ${path}' ]";
	public final String ELEMENT_FOLDERSELECTOR_CONTENTDETAIL_FINALPATH = "//*[@class='OddItem']//*[text()='${name}']";
	public final By ELEMENT_SAVE_BTN = By.xpath("//*[text()='Save']");
	public final By ELEMENT_ADD_BTN = By.xpath("//*[text()='Add']");
	public final By ELEMENT_CLOSE_BTN = By.xpath("//*[text()='Close']");
	public final By ELEMENT_OK_BTN = By.xpath("//*[text()='OK']");

	//Select document popup
	final public String ELEMENT_SELECT_DOCUMENT_NODE_FOLDER= ".//*[@id='LeftWorkspace']//span[contains(.,'${node}')]";
	final public String ELEMENT_SELECT_DOCUMENT_NODE_FILE= ".//*[@id='ListRecords']//*[contains(text(),'${content}')]";

	//settings driver display
	public final By ELEMENT_DRIVERSETTINGS_SORTBY = By.xpath("//*[@class='selectbox' and @name='sortBy']");
	public final By ELEMENT_DRIVERSETTINGS_ORDER = By.xpath("//*[@class='selectbox' and @name='order']");
	public final By ELEMENT_DRIVERSETTINGS_SAVE = By.xpath("//*[@class='btn btn-primary' and text()='Save']");

	// upload
	public final By ELEMENT_ACTIONBAR_UPLOAD = By.xpath("//*[@class='uiIconEcmsUpload uiIconEcmsLightGray']");
	public final By ELEMENT_MORE_LINK_WITHOUT_BLOCK = By.xpath("//*[@id='uiActionsBarContainer']//*[contains(text(), 'More')]");
//	public final By ELEMENT_UPLOAD_LINK = By.id("MultiUploadInputFiles");
	public final By ELEMENT_UPLOAD_LINK =By.xpath(".//*[@id='UploadButtonDiv']//*[contains(@class,'uiIconEcmsUpload')]");
	public final By ELEMENT_ACTIONBAR_EDIT = By.xpath("//*[@class='uiIconEcmsEditDocument uiIconEcmsLightGray']");
	public final By ELEMENT_FILE_FORM_TITLE = By.xpath("//*[@id='title0']");
	//Drive selection
	public final String ELEMENT_SELECTDRIVE_SPECIFICDRIVE = "//*[@class='driveLabel' and @data-original-title='${spaceName}']";

	//Add document
	public final By ELEMENT_ADDDOCUMENT_CHOICETYPE = By.xpath("//*[@class='templateTitle']");
	
	//Add folder
	public final By ELEMENT_ADDFOLDERBOX = By.xpath("//*[@class='PopupTitle popupTitle']");
	public final By ELEMENT_ADDFOLDER_NAME = By.xpath("//*[@id='titleTextBox']");
	public final By ELEMENT_ADDFOLDER_FOLDERTYPE = By.xpath("//*[@class='selectbox']");
	public final By ELEMENT_ADDFOLDER_CREATEFOLDERBUTTON = By.xpath("//*[@class='btn addFolderButton']");

	public final By ELEMENT_SITEEXPLORER_ACTION_COPY = By.xpath("//*[@class='uiIconEcmsCopy']");
	public final By ELEMENT_SITEEXPLORER_ACTION_CUT = By.xpath("//*[@class='uiIconEcmsCut']");
	public final By ELEMENT_SITEEXPLORER_ACTION_PASTE = By.xpath("//*[@class='uiIconEcmsPaste']");
	public final By ELEMENT_SITEEXPLORER_ACTION_RENAME = By.xpath("//*[@class='uiIconEcmsRename']");
	public final By ELEMENT_SITEEXPLORER_ACTION_OPEN_IN_MS_OFFICE = By.xpath("//*[@class='uiIconDownload uiIconLightGray']");
	public final By ELEMENT_SITEEXPLORER_ACTION_ADDSYMLINK = By.xpath("//*[@class='uiIconEcmsAddSymLink']");

	//Left explorer box
	public final By ELEMENT_SITEEXPLORER_LEFTBOX_ROOTNODE = By.xpath("//*[@class='uiIconEcmsHome uiIconEcmsLightGray']");
	public final By ELEMENT_SITEEXPLORER_LEFTBOX_RELATION = By.xpath("//*[@class='uiIconEcmsRelationMini uiIconEcmsLightGray']");
	public final By ELEMENT_SITEEXPLORER_LEFTBOX_EXPLORER = By.xpath("//*[@class='uiIconEcmsExplorerMini uiIconEcmsLightGray']");

	public final String ELEMENT_SITEEXPLORER_LEFTBOX_TITLE_TRANSLATION="//*[text()='fr (${title})']";
	public final By ELEMENT_SITEEXPLORER_LIST_LOCK_NODE = By.xpath("//*[@id='ECMContextMenu']//*[@class='uiIconEcmsLock']");
	public final By ELEMENT_SITEEXPLORER_LIST_UNLOCK_NODE = By.xpath("//*[@id='ECMContextMenu']//*[@class='uiIconEcmsUnlock']");

	//Left panel of SE
	public final By ELEMENT_FILE_EXPLORER = By.xpath("//*[@data-original-title = 'File Explorer']");
	public final By ELEMENT_FILE_EXPLORER_ICON = By.xpath(".//i[@class='uiIconEcmsExplorerMini uiIconEcmsLightGray']");
	public String ELEMENT_FILE_TITLE_RIGHT_PANEL=".//*[@class='nodeGroup']//span[text()='${fileName}']";
	
	 //View detail a content in SE
    public By ELEMENT_CONTENT_THUMBNAIL = By.xpath(".//*[@class='iconContainer']/i");
    public String ELEMENT_WEBCONTENT_NAME = ".//*[@id='UIDocumentContainer']//h6[text()='${nameFile}']";
    public By ELEMENT_CONTENT_MESSAGE_NOT_AVAILABLE = By.xpath(".//h4[text()='The preview of this document is not available.']");
    public By ELEMENT_CONTENT_MESSAGE_TOO_MANY_PAGES = By.xpath(".//h4[text()='The preview is not available for content with over 99 pages.']");
    public By ELEMENT_CONTENT_MESSAGE_OVER_SIZE= By.xpath(".//h4[text()='The preview is not available for content larger than 5 MB.']");
    public By ELEMENT_CONTENT_DOWNLOAD_BUTTON= By.xpath(".//*[@class='btn btn-primary']");
    public By ELEMENT_CONTENT_OPEN_DESKTOP=By.xpath(".//*[@class='btn'][text()='Open on Desktop']");
	
	//advanced search 
	public final By ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_NAME = By.xpath("//*[@id='keyword']");
	public final By ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_SEARCHBTN = By.xpath("//*[@id='tab-UIContentNameSearch']//*[@class='btn' and text()='Search']");
	public final By ELEMENT_SITEXPLORER_ADVANCEDSEARCH_CREATEQUERYTAB = By.xpath("//*[text()='New Query']");
	public final By ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_NAMEQUERY = By.xpath("//*[@id='name']");
	public final By ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_SAVEQUERYBTN = By.xpath("//*[@id='UIJCRAdvancedSearch']//*[@class='btn' and text()='Save']");
	public final String ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_DELETEQUERYBTN = "//*[text()='${name}']/../..//*[@class='uiIconDelete uiIconLightGray']";
	public final By ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_EXECUTEQUERYBTN = By.xpath("//*[@class='uiIconEcmsExecute']");
	public final String ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_EDITQUERYBTN = "//*[text()='${name}']/../..//*[@class='uiIconEdit uiIconLightGray']";
	public final By ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_QUERYTYPE = By.xpath("//*[@class='uiForm EditQueryForm']/..//*[@class='selectbox']");
	public final By ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_SAVEEDITQUERYBTN = By.xpath("//*[@id='EditQueryForm']//*[@class='btn' and text()='Save']");
	public final String ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_RESULT ="//*[text()='${name}']";
	public final String ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_RESULT1 = "//*[@id='UISavedQuery']//*[text()='${name}']/../..//*[text()='xpath']";

	//Confirm delete box
	public final By ELEMENT_SITEEXPLORER_CONFIRMBOX_DELETE= By.xpath("//*[@class='uiAction uiActionBorder']//*[text()='Delete']");

	//upload file form
	public String ELEMENT_ATTACHMENT_FORM_FILE_NAME = "//*[text()='$fileName']";
	public By ELEMENT_EVENT_FILE_INPUT = By.xpath("//*[@id='upload']//*[@name='file']");
	public By ELEMENT_ATTACHMENT_SAVE_BUTTON = By.xpath("//*[@id='UIAttachFileForm']//*[text()='Save']");
	public String ELEMENT_ATTACH_FILE_NAME = "//*[@data-original-title='$fileName']";
	public final By ELEMENT_UPLOAD_PROGRESS_BAR = By.xpath(".//*[contains(@class,'progress progress-striped pull-right')]");
	public final By ELEMENT_UPLOAD_BUTTON = By.xpath("//a[@class='actionIcon' and contains(text(),'Upload')]");
	
	//Permission
	public final By ELEMENT_PERMISSION_USER = By.xpath("//*[@class='uiIconSelectUser uiIconLightGray']");
	public final By ELEMENT_PERMISSION_GROUP = By.xpath("//*[@class='uiIconSelectMember uiIconLightGray']");
	public final By ELEMENT_PERMISSION_ANY = By.xpath("//*[@class='uiIconAddAny uiIconLightGray']");
	public final By ELEMENT_SEARCH_USER_INPUT = By.id("Quick Search");
	public final By ELEMENT_SELECT_SEARCH = By.name("filter");
	public final String ELEMENT_USER_CHECKBOX = "//*[text()='${user}']/../..//*[@type='checkbox']"; 
	public final By ELEMENT_QUICK_SEARCH_BUTTON = By.xpath("//a[@data-original-title='Quick Search']");
	public final By ELEMENT_ADD_USERS_BUTTON = By.xpath("//*[@id='UIUserSelector']//*[text()='Add']");

	//comment
	public final String ELEMENT_SITEEXPLORER_COMMENT = "//*[text()=' ${number} Comment(s)']";
	public final By ELEMENT_SITEEXPLORER_COMMENT_SHOW = By.xpath("//*[text()='Show comments']");
	public final By ELEMENT_SITEEXPLORER_COMMENT_EDIT = By.xpath("//*[@class='uiIconEdit uiIconLightGray']");
	public final By ELEMENT_SITEEXPLORER_COMMENT_DELETE = By.xpath("//*[@class='uiIconTrash uiIconLightGray']");
	public final String ELEMENT_SITEEXPLORER_COMMENT_CONTENT = "//*[text()='${content}']";
	public final By ELEMENT_SITEEXPLORER_COMMENT_SAVE = By.xpath(".//*[@id='UICommentForm']//button[text()='Save']");


	// clipboard
	public final By ELEMENT_SITEEXPLORER_CLIPBOARD = By.xpath("//*[@id='UISideBar']//*[@class='uiIconEcmsClipboardMini uiIconEcmsLightGray']");
	public final String ELEMENT_CLIPBOARD_PASTE_NODE = "//*[@id='UISideBar']//*[contains(text(),'{$node}')]/../..//*[@class='uiIconEcmsPaste uiIconEcmsLightGray']";
	public final String ELEMENT_CLIPBOARD_DELETE_NODE = "//*[@id='UISideBar']//*[contains(text(),'{$node}')]/../..//*[@class='uiIconEcmsDelete uiIconEcmsLightGray']";
	public final By ELEMENT_CLIPBOARD_CLEAR_ALL = By.xpath("//*[@id='UIClipboard']//*[contains(text(),'Clear All')]");

	//vote
	public final By ELEMENT_SITEEXPLORER_VOTE_AVERAGE = By.xpath("//*[@data-original-title='Average']");
	public final By ELEMENT_SITEEXPLORER_VOTEONDOCUMENT = By.xpath("//*[@class='uiVote clearfix']");

	//Tag
	public final By ELEMENT_SITEEXPLORER_TAG_DELETE = By.xpath("//*[@class='uiIconClose']");
	public final By ELEMENT_SITEEXPLORER_TAG_NAME = By.xpath("//*[@id='names']");
	public final String ELEMENT_SITEEXPLORER_TAG_EXISTING = "//*[@class='actionField']//*[contains(text(),'${name}')]";
	//public final By ELEMENT_SITEEXPLORER_TAG_INPUT= By.xpath("//*[@id='tagName']");
	public final By ELEMENT_TAG_FORM = By.xpath("//*[@id='names']");
	public final By ELEMENT_ADD_TAG_FORM = By.xpath("//*[@id='UITaggingForm']//*[contains(text(),'Add')]");
	public final By ELEMENT_TAG_POPUP_CLOSE = By.xpath("//*[@id='UITaggingForm']//*[contains(text(),'Close')]");
	public final String ELEMENT_TAG_POPUP_LINK_TAGS=".//*[@id='UITaggingForm']//*[contains(text(),'${name}')]";

	//Add category
	public final By ELEMENT_CATEGORY_CHANGE_FORM_SELECT_CATEGORY =By.xpath("//*[@id='UICategoryManager']//*[contains(text(),'Select Category')]");
	public final By ELEMENT_CATEGORY_SELECT_CATEGORY_TREE = By.xpath("//*[@name='taxonomyTree']");
	public final By ELEMENT_CATEGORY_ADD_ROOT_NODE = By.xpath("//*[@class='uiIconAddRootNode uiIconLightGray']");

	public String ELEMENT_DOCUMENT_VIEW = "//*[@id='UITabContent']//*[contains(text(),'{$content}')]";

	public final By ELEMENT_SITEEXPLORER_RENAME_FIELD = By.xpath("//*[@id='renameField']");
	public final By ELEMENT_SITEEXPLORER_RENAME_SAVE = By.xpath("//*[@id='renameLink']");

	public final By ELEMENT_CHECK_OPEN_WEBCONTENT_IN_MSOFFICE = By.xpath("//*[@id='main']//*[contains(text(),'css')]");
	// SideBar
	public final String ELEMENT_SE_NODE = "//*[@title='${node}']";
	public final By ELEMENT_SIDE_BAR_MAINTAB = By.xpath(".//*[@id='UISideBar']//h6[@class='title']");
	public final By ELEMENT_SIDEBAR_SITES_MANAGEMENT = By.xpath("//*[@data-original-title = 'Sites Management' or @title = 'Sites Management']");
	public final By ELEMENT_SIDE_BAR_RELATION_ICON = By.xpath(".//*[@id='UISideBar']//i[@class='uiIconEcmsRelationMini uiIconEcmsLightGray']");
	public final String ELEMENT_SIDE_BAR_RELATION_TAB_FILE_TITLE = ".//*[@id='UISideBar']//a[text()='${nameContent}']";
	public final By ELEMENT_SIDE_BAR_FILE_EXPLORER_ICON = By.xpath(".//*[@id='UISideBar']//i[@class='uiIconEcmsExplorerMini uiIconEcmsLightGray']");
	public final By ELEMENT_SITEEXPLORER_LEFTBOX_INTRANET = By.xpath("//*[@class='uiIcon16x16FolderDefault uiIcon16x16exo_portalFolder' and @title='intranet']");
	public final By ELEMENT_SITEEXPLORER_LEFTBOX_DOCUMENT = By.xpath("//*[@class='uiIcon16x16FolderDefault uiIcon16x16exo_documentFolder' and @title='documents']");
	public final By ELEMENT_SITEEXPLORER_LEFTBOX_SPACE = By.xpath("//*[@class='uiIcon16x16FolderDefault uiIcon16x16exo_portalFolder' and @title='intranet']");
	public final String ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME = "//*[@class='nodeName' and text()='${title}']";
	public final By ELEMENT_SITEEXPLORER_ACTION_DELETE = By.xpath("//*[@class='uiIconEcmsDelete']");
	public final By ELEMENT_SITEEXPLORER_TAG_CLOUD_TAB = By.xpath(".//*[@class='uiIconEcmsTagExplorerMini uiIconEcmsLightGray']");
	public final By ELEMENT_SITEEXPLORER_LEFTBOX_SAVEDSEARCH = By.xpath("//*[@class='uiIconEcmsSavedSearchesMini uiIconEcmsLightGray']");
	public final By ELEMENT_SITEEXPLORER_LEFTBOX_ADVANCEDSEARCH = By.xpath("//*[@class='actionIcon advancedSearchIcon pull-right']//*[@class='uiIconSearch uiIconLightGray']");

	//Side bar-->Tag cloud
	public final String ELEMENT_SIDEBAR_TAGCLOUD_NAME=".//*[@id='UITagExplorer']//a[text()='${name}']";
	public final By ELEMENT_SIDEBAR_TAGCLOUD_EDIT = By.xpath(".//*[@class='uiIconEdit uiIconLightGray']");
	public final By ELEMENT_SIDEBAR_TAGCLOUD_POPUP_TITLE= By.xpath(".//*[@id='UIPopupWindow']//span[text()='Edit Tag']");
	public final String ELEMENT_SIDEBAR_TAGCLOUD_POPUP_EDIT=".//*[@id='UIEditingTagList']//span[text()='${name}']/../..//*[@class='uiIconEditTag uiIconLightGray']";
	public final String ELEMENT_SIDEBAR_TAGCLOUD_POPUP_DELETE=".//*[@id='UIEditingTagList']//span[text()='${name}']/../..//*[@class='uiIconRemoveTag uiIconLightGray']";
	//Tag Cloud-->Edit-->Tag popup
	public final By ELEMENT_TAG_POPUP_TITLE=By.xpath(".//*[@id='TagPopup']//span[text()='Tag']");
	public final By ELEMENT_TAG_POPUP_NAME_FIELD=By.id("tagName");
	public final By ELEMENT_TAG_POPUP_SAVE=By.xpath(".//*[@id='UITagForm']//*[text()='Save']");
	public final By ELEMENT_TAGE_POPUP_CLOSE=By.xpath(".//span[text()='Edit Tag']/..//*[@title='Close Window']");
	//SEO folder
	public final By ELEMENT_SEO_FOLDER_FILE = By.xpath("//*[@class='text']//*[@data-original-title='sitemaps']");
	//Personal document
	public final String ELEMENT_PERSONAL_DOCUMENT_FILE = ".//*[@id='UIDocumentNodeList']//span[text()='${file}']";

	//Grid list
	public final String ELEMENT_GRID_LIST_CONTENT = ".//*[@class='uiListGrid']//*[text()='${file}']";


	public final String ELEMENT_PERSONAL_DOCUMENT_FILE_CHECKBOX=".//*[@id='UIDocumentNodeList']//span[text()='${file}']/../../..//span/input";

	//Space drive
	public final String ELEMENT_SPACE_DRIVE_FILE = ".//*[@id='UIDocumentNodeList']//span[text()='${file}']";
	public final String ELEMENT_SPACE_DRIVE_CHECKBOX=".//*[@id='UIDocumentNodeList']//span[text()='${file}']/../../..//*[@type='checkbox']";
	public final String ELEMENT_SPACE_DRIVE_NODE_TREE_FILE =".//*[@class='nodeGroup']//*[contains(text(),'${file}')]";
	//Publication box
	public final String ELEMENT_PUBLICATION_STATUS = "//*[text()='${status}']/..//*[@class='node']";

	//Right column content
	public final String ELEMENT_SITE_EXPLORER_RIGHT_COLUMN_CONTENT=".//*[@id='UITabContent']//a[contains(text(),'${title}')]";

	//View detail a content
	 public String ELEMENT_CONTENT_NAME = ".//*[@id='UIDocumentContainer']//span[text()='${nameFile}']";
	 
	// View icons
	public final By ELEMENT_LIST_VIEW_ICON = By.xpath("//*[@data-original-title = 'List']");
	public final By ELEMENT_ADMIN_VIEW_ICON = By.xpath("//*[@data-original-title = 'Admin']");
	public final By ELEMENT_ICONS_VIEW = By.xpath("//*[@data-original-title = 'Icons']");
	public final By ELEMENT_WEB_VIEW = By.xpath("//*[@data-original-title = 'Web']");
	public final By ELEMENT_CATEGORIES_VIEW = By.xpath("//*[@data-original-title = 'Categories']");
	
	//Add new content
    public String ELEMENT_SITE_EXPLORER_CONTENT_NAME= ".//*[@id='UISelectDocumentForm']//i[@data-original-title='${nameContent}']";
	
	ManageAlert alert;
	Button button;
	CreateNewDocument CreNewDoc;
	Dialog dialog;

	public SiteExplorerHome(WebDriver dr){
		this.driver=dr;
		alert = new ManageAlert(dr);
		CreNewDoc = new CreateNewDocument(dr);
		button = new Button(dr);
		dialog= new Dialog(driver);
	}
	/**
	 * Go to a folder by a path in SE
	 * Example: go to Site management drive-->a folder
	 * @param path
	 * @param drive
	 */
	public void goToPath(String path, String drive){
		info("Go to selected Drive");
		waitForAndGetElement(ELEMENT_ACTIONBAR_SHOWDRIVES);
		click(ELEMENT_ACTIONBAR_SHOWDRIVES);
		waitForAndGetElement(ELEMENT_ACTIONBAR_SELECTED_DRIVE.replace("${drive}",drive));
		click(ELEMENT_ACTIONBAR_SELECTED_DRIVE.replace("${drive}",drive));
		waitForAndGetElement(ELEMENT_SIDE_BAR_MAINTAB);
		click(ELEMENT_SIDE_BAR_FILE_EXPLORER_ICON);
		info("Go to folder");
		String[] arrayPath = path.split("/");
		for (String arrayElement : arrayPath){
			selectNode(arrayElement);
		}
	}

	/**
	 * Open Add a new folder popup
	 */
	public void goToAddNewFolder() {
		info("Add a new folder");
		info("Click on New folder on Action Bar");
		click(ELEMENT_ACTIONBAR_ADDFOLDER);
		info("Verify that Add folder popup is shown");
		waitForAndGetElement(ELEMENT_ADDFOLDERBOX);
		info("The folder is shown successfully");
	}

	/**
	 * Create a new folder. Input the title and folder type
	 * @param title
	 * @param folderType
	 */
	public void createFolder(String title, String folderType) {
		info("Type a title:"+title+" for the folder");
		type(ELEMENT_ADDFOLDER_NAME, title, true);
		info("Select folder type:"+folderType);
		select(ELEMENT_ADDFOLDER_FOLDERTYPE, folderType );
		info("Click on Create folder button");
		click(ELEMENT_ADDFOLDER_CREATEFOLDERBUTTON);
		info("Verify that the folder is created");
		waitForAndGetElement(By.xpath((ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME).replace("${title}", title)));
		info("The folder is created successfully");
	}


	/**
	 * Go to New content page
	 */
	public void goToAddNewContent() {
		waitForAndGetElement(ELEMENT_ACTIONBAR_ADDDOCUMENT);
		info("Click on New Document on Action Bar");
		click(ELEMENT_ACTIONBAR_ADDDOCUMENT);
		info("Verify that New content page is shown");
		waitForAndGetElement(ELEMENT_ADDDOCUMENT_CHOICETYPE);
		info("New content page is shown successfully");
	}
	/**
	 * Add Symlink for a node
	 * @param node
	 */
	public void addSymlink(String node){
		rightClickOnElement(By.xpath((ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME).replace("${title}", node)));
		click(ELEMENT_SITEEXPLORER_ACTION_ADDSYMLINK);
		Utils.pause(2000);
	}
	/**
	 * Delete data by title
	 * @param title
	 */
	public void deleteData(String title) {
		info("Click on File Explorer icon");
		click(ELEMENT_SIDE_BAR_FILE_EXPLORER_ICON);
		Utils.pause(2000);
		info("Right click on nodename");
		rightClickOnElement(By.xpath((ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME).replace("${title}", title)));
		info("Click on Delete link");
		click(ELEMENT_SITEEXPLORER_ACTION_DELETE);
		info("Click on Delete button on Confirm popup");
		click(ELEMENT_SITEEXPLORER_CONFIRMBOX_DELETE);
		info("Verify that the node is deleted");
		waitForElementNotPresent(By.xpath((ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME).replace("${title}", title)));
		info("the node is deleted successfully");
	}
	/**
	 * 
	 * @param title
	 * @param destination
	 */
	public void copyPasteNode(String title,String destination){
		rightClickOnElement(By.xpath((ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME).replace("${title}", title)));
		click(ELEMENT_SITEEXPLORER_ACTION_COPY);
		rightClickOnElement(By.xpath((ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME).replace("${title}", destination)));
		click(ELEMENT_SITEEXPLORER_ACTION_PASTE);
		driver.navigate().refresh();
		click(ELEMENT_SIDEBAR_SITES_MANAGEMENT);
		Utils.pause(2000);
	}

	/**
	 * Cut and paste node
	 * @param title
	 * @param destination
	 */
	public void cutPasteNode(String title,String destination){
		rightClickOnElement(By.xpath((ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME).replace("${title}", title)));
		click(ELEMENT_SITEEXPLORER_ACTION_CUT);
		rightClickOnElement(By.xpath((ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME).replace("${title}", destination)));
		click(ELEMENT_SITEEXPLORER_ACTION_PASTE);
		driver.navigate().refresh();
		click(ELEMENT_SIDEBAR_SITES_MANAGEMENT);
		Utils.pause(2000);
	}
	/**
	 * Rename a node
	 * @param node
	 * @param newName
	 */
	public void renameNode(String node, String newName){
		rightClickOnElement(By.xpath((ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME).replace("${title}", node)));
		click(ELEMENT_SITEEXPLORER_ACTION_RENAME);
		type(ELEMENT_SITEEXPLORER_RENAME_FIELD,newName,true);
		click(ELEMENT_SITEEXPLORER_RENAME_SAVE);
		Utils.pause(2000);
		click(ELEMENT_SIDEBAR_SITES_MANAGEMENT);

	}
	
	/**
	 * Upload a file
	 * @param link
	 * @param params
	 */
	public void uploadFileWithDymanicPath(String link, Object... params) {
		Boolean verify = (Boolean) (params.length > 0 ? params[0] : true);
		if (waitForAndGetElement(ELEMENT_UPLOAD_BUTTON, DEFAULT_TIMEOUT, 0) == null) {
			click(ELEMENT_MORE_LINK_WITHOUT_BLOCK);
		}
		click(ELEMENT_UPLOAD_LINK);
		uploadFileUsingRobot(link);
		info("Upload file " + getAbsoluteFilePath(link));
		waitForElementNotPresent(ELEMENT_UPLOAD_PROGRESS_BAR,120000,0);
				  
		info("verify:"+verify);
		if (verify) {
			String links[] = link.split("/");
			int length = links.length;
			Utils.pause(2000);
			waitForAndGetElement(By.xpath("//*[contains(text(),'"
					+ links[length - 1] + "')]"));
		}

		info("Upload file successfully");
		Utils.pause(2000);
	}

	/**
	 * Upload a file
	 * @param link
	 * @param params
	 */
	public void uploadFile(String link, Object... params) {
		Boolean verify = (Boolean) (params.length > 0 ? params[0] : true);
		if (waitForAndGetElement(ELEMENT_UPLOAD_BUTTON, DEFAULT_TIMEOUT, 0) == null) {
			click(ELEMENT_MORE_LINK_WITHOUT_BLOCK);
		}
		/*((JavascriptExecutor) driver)
				.executeScript(
						"arguments[0].style.visibility = 'block'; arguments[0].style.height = '1px'; "
								+ "arguments[0].style.width = '1px'; arguments[0].style.opacity = 1",
						waitForAndGetElement(ELEMENT_UPLOAD_LINK,
								DEFAULT_TIMEOUT, 1, 2));
		
		Utils.pause(10000);
		driver.findElement(ELEMENT_UPLOAD_LINK).sendKeys(Utils.getAbsoluteFilePathFromFile(link));
		info("Upload file " + Utils.getAbsoluteFilePathFromFile(link));*/
		Utils.pause(2000);
		click(ELEMENT_UPLOAD_LINK);
		uploadFileUsingRobot(link);
		//waitForElementNotPresent(ELEMENT_UPLOAD_PROGRESS_BAR);
				  
		info("verify:"+verify);
		if (verify) {
			String links[] = link.split("/");
			int length = links.length;
			Utils.pause(2000);
			waitForAndGetElement(By.xpath("//*[contains(text(),'"+ links[length - 1] + "')]"));
		}

		info("Upload file successfully");
		Utils.pause(2000);
	}

	/**
	 * Go to Edit page of a document
	 */

	public void goToEditDocument() {
		click(ELEMENT_ACTIONBAR_EDIT);
		Utils.pause(3000);
	}
	/**
	 * Add tag to a Content
	 * @param tag
	 */
	public void addTag(String tag){
		waitForAndGetElement(ELEMENT_ACTIONBAR_MORE);
		info("Click on More menu");
		click(ELEMENT_ACTIONBAR_MORE);
		info("Click on Tag link");
		click(ELEMENT_ACTIONBAR_TAG);
		info("Input name of tag");
		type(ELEMENT_TAG_FORM,tag,true);
		info("Click on Add button");
		click(ELEMENT_ADD_TAG_FORM);
		info("The tag is created successfully");
		info("Close the popup");
		click(ELEMENT_TAG_POPUP_CLOSE);
	}
	/**
	 * Edit a Tag
	 * @param oldName
	 * @param newName
	 */
	public void editTag(String oldName,String newName){
		info("Click on Tag Cloud tab of SE");
		click(ELEMENT_SITEEXPLORER_TAG_CLOUD_TAB);
		waitForAndGetElement(ELEMENT_SIDEBAR_TAGCLOUD_NAME.replace("${name}",oldName));
		info("Click on Edit button of Tag Cloud");
		click(ELEMENT_SIDEBAR_TAGCLOUD_EDIT);
		waitForAndGetElement(ELEMENT_SIDEBAR_TAGCLOUD_POPUP_TITLE);
		info("Click on Edit button of the old tag");
		click(ELEMENT_SIDEBAR_TAGCLOUD_POPUP_EDIT.replace("${name}",oldName));
		waitForAndGetElement(ELEMENT_TAG_POPUP_NAME_FIELD);
		info("Input new name of tag");
		((JavascriptExecutor)driver).executeScript("arguments[0].setAttribute('value', '" + newName +"')", waitForAndGetElement(ELEMENT_TAG_POPUP_NAME_FIELD));
		info("Save all changes");
		clickByJavascript(ELEMENT_TAG_POPUP_SAVE);
		info("Verify that the new name of tag is changed");
		waitForAndGetElement(ELEMENT_SIDEBAR_TAGCLOUD_NAME.replace("${name}",newName));
		info("the new name of tag is changed successfully");
		click(ELEMENT_TAGE_POPUP_CLOSE);
		info("The edit tag popup is closed");
	}
	/**
	 * Delete a tag
	 * @param tag
	 */
	public void deleteTag(String tag){
		info("Click on Tag Cloud tab of SE");
		click(ELEMENT_SITEEXPLORER_TAG_CLOUD_TAB);
		waitForAndGetElement(ELEMENT_SIDEBAR_TAGCLOUD_NAME.replace("${name}",tag));
		info("Click on Edit button of Tag Cloud");
		click(ELEMENT_SIDEBAR_TAGCLOUD_EDIT);
		waitForAndGetElement(ELEMENT_SIDEBAR_TAGCLOUD_POPUP_TITLE);
		info("Click on Delete button of the old tag");
		click(ELEMENT_SIDEBAR_TAGCLOUD_POPUP_DELETE.replace("${name}",tag));
		alert.acceptAlert();
		info("Verify that tag is delete");
		waitForElementNotPresent(ELEMENT_SIDEBAR_TAGCLOUD_NAME.replace("${name}",tag));
		info("The tag is deleted successfully");
		info("Close the popup");
		click(ELEMENT_TAGE_POPUP_CLOSE);

	}
	/**
	 * Edit a Document
	 * @param content
	 */
	public void editDocument(String newTitle,String content) {
		if(waitForAndGetElement(ELEMENT_ACTIONBAR_EDIT,5000,0)==null){
			info("Click on More menu");
			click(ELEMENT_ACTIONBAR_MORE);
		}
		info("Click on Edit link");
		click(ELEMENT_ACTIONBAR_EDIT);
		driver.navigate().refresh();
		if(!newTitle.isEmpty())
			type(ELEMENT_FILE_FORM_TITLE,newTitle, true );
		if(!content.isEmpty()){	
			inputFrame(CreNewDoc.ELEMENT_FILEFORM_BLANK_CONTENT, content);
			switchToParentWindow();
		}
	}
	/**
	 * Go to Intranet page
	 */

	public void goToIntranet() {
		click(ELEMENT_SITEEXPLORER_LEFTBOX_INTRANET);
	}

	/**
	 * Go to document
	 */
	public void goToDocument() {
		click(ELEMENT_SITEEXPLORER_LEFTBOX_DOCUMENT);
	}

	/**
	 * Go to space
	 */
	public void goToSpace(String spaceName) {
		click(ELEMENT_ACTIONBAR_SHOWDRIVES);
		click(By.xpath((ELEMENT_SELECTDRIVE_SPECIFICDRIVE).replace("${spaceName}",spaceName)));
	}

	/**
	 * Select Drive by option
	 *
	 */
	public enum selectDriverOption{
		ALPHABETICAL, TYPE, CREATEDDATE, MODIFIEDDATE
	}

	/**
	 * Select Drive by order
	 *
	 */

	public enum selectDriverOrder{
		ASCENDING, DESCENDING
	}

	/**
	 * Open Setting drive page
	 * @param type
	 * @param order
	 */
	public void openSettingsDriver(selectDriverOption type, selectDriverOrder order) {
		click(ELEMENT_ACTIONBAR_SETTINGS);
		info("Go to type "+ type);
		switch(type){
		case ALPHABETICAL:
			select(ELEMENT_DRIVERSETTINGS_SORTBY, "Alphabetical");
			break;

		case TYPE:
			select(ELEMENT_DRIVERSETTINGS_SORTBY, "Type");
			break;

		case CREATEDDATE:
			select(ELEMENT_DRIVERSETTINGS_SORTBY, "Created Date");
			break;

		case MODIFIEDDATE:
			select(ELEMENT_DRIVERSETTINGS_SORTBY, "Modified Date");
			break;
		}

		info("Go to type "+ order);
		switch(order){
		case ASCENDING:
			select(ELEMENT_DRIVERSETTINGS_ORDER, "Ascending");
			break;

		case DESCENDING:
			select(ELEMENT_DRIVERSETTINGS_ORDER, "Descending");
			break;
		}

		click(ELEMENT_DRIVERSETTINGS_SAVE);
	}

	/**
	 * Go to Permission popup
	 */
	public void goToPermission() {
		info("Click on More link on Action bar");
		click(ELEMENT_ACTIONBAR_MORE);
		info("Click on Permission on Action bar");
		click(ELEMENT_ACTIONBAR_PERMISSION);
		info("Finished opening permission popup");
	}
	/**
	 * Select a node by name
	 * @param nodeName
	 */
	public void selectNode(String nodeName) {
		info("Verify that nodeName:"+nodeName+" is shown");
		waitForAndGetElement(ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}", nodeName));
		info("Click on the nodeName:"+nodeName);
		click(ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}", nodeName));
		Utils.pause(2000);
		info("Finished selecting nodeName:"+nodeName);
	}
	/**
	 * Go to Advanced Search page
	 */
	public void goToAdvancedSearch() {
		click(ELEMENT_SITEEXPLORER_LEFTBOX_SAVEDSEARCH);
		Utils.pause(2000);
		click(ELEMENT_SITEEXPLORER_LEFTBOX_ADVANCEDSEARCH);
	}
	/**
	 * Lock a Node
	 * @param name
	 */
	public void lockNode(String name){
		rightClickOnElement(By.xpath(ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}",name)));
		click(ELEMENT_SITEEXPLORER_LIST_LOCK_NODE);
	}

	/**
	 * Unlock a Node
	 * @param name
	 */
	public void unlockNode(String name){
		rightClickOnElement(By.xpath(ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}",name)));
		click(ELEMENT_SITEEXPLORER_LIST_UNLOCK_NODE);
	}


	/**
	 * By QuynhPT
	 * Open Add Relation popup
	 */
	public void goToManageRelation(){
		click(ELEMENT_ACTIONBAR_RELATION);
		Utils.pause(2000);
	}

	/**
	 * Add a Relation for many files
	 * @param nameContent
	 * @param path
	 */
	public void addRelation(String[] nameContent,String path) {
		for (String arrayElement:nameContent){
			goToPathHasFiles(path);
			click(ELEMENT_RELATION_POPUP_SELECT_RELATION_TAB_SELECT_CONTENT_RIGHT_TREE
					.replace("${nameContent}",arrayElement));
			Utils.pause(2000);
		}
	}
	/**
	 * Open Add Category popup
	 * By QuynhPT
	 * date 16/01/2015
	 */
	public void goToAddCategory(){
		click(ELEMENT_ACTIONBAR_CATEGORY);
		Utils.pause(2000);
	}
	/**
	 * Add category for a file in SE
	 * @param categoryTreeName
	 * @param arrayCatePath
	 * @param nameSelectedCategory
	 */
	public void addCategory(String categoryTreeName,String[] arrayCatePath,String nameSelectedCategory){
		info("select category");
		click(ELEMENT_ADD_CATEGORY_POPUP_SELECT_CATEGORY_TAB);
		Utils.pause(2000);
		select(ELEMENT_ADD_CATEGORY_POPUP_MENU, categoryTreeName);
		Utils.pause(2000);
		for (String cateName : arrayCatePath) {
			click(ELEMENT_ADD_CATEGORY_POPUP_CATEGORY_NAME_LEFT_SIDE.replace("${nameTitle}", cateName));
			Utils.pause(2000);
		}

		click(ELEMENT_ADD_CATEGORY_POPUP_SELECT_CATEGORY_RIGHT_SIDE.replace("${nameCategory}",nameSelectedCategory));
		Utils.pause(3000);
	}

	/**
	 * Close Add Category popup
	 * By QuynhPT
	 * date 16/01/2015
	 */
	public void closeAddCategoryPopup(){
		click(ELEMENT_ADD_CATEGORY_POPUP_CLOSED_BUTTON);
		Utils.pause(2000);
	}
	/**
	 * Go to the path that include content files to create relation
	 * @param path
	 */
	public void goToPathHasFiles(String path) {
		// Open "Select Relation" tab
		click(ELEMENT_RELATION_POPUP_SELECT_RELATION_TAB);
		Utils.pause(500);
		String[] arrayPath = path.split("/");
		for (String arrayElement:  arrayPath) {
			click(ELEMENT_RELATION_POPUP_SELECT_RELATION_TAB_NODE_LEFT_TREE
					.replace("${nameNode}", arrayElement));
		}
	}
	/**
	 * By QuynhPT
	 * Close "Add Relation" popup
	 */
	public void closeAddRelationPopup(){
		click(ELEMENT_RELATION_POPUP_SELECT_RELATION_TAB_CLOSE_BUTTON);
		Utils.pause(2000);
	}

	/**
	 * By QuynhPT
	 * Open Relation tab on SideBar
	 */
	public void goToRelationSideBar(){
		click(ELEMENT_SIDE_BAR_RELATION_ICON);
		Utils.pause(2000);
	}

	/**
	 * Delete Relation
	 * By QuynhPT
	 * @param nameContent
	 */
	public void deleteRelation(String nameContent){
		click(By.xpath(ELEMENT_RELATION_POPUP_RELATION_LIST_DELETE_BUTTON.replace("${nameContent}",nameContent)));
		alert.waitForConfirmation(MESSAGE_DELETE_RELATION);
		alert.acceptAlert();
	}
	/**
	 * Delete a category that is added to the file in SE
	 * By QuynhPT
	 * date 16/01/2015
	 * @param nameCategory
	 */
	public void deleteCategory(String nameCategory){
		click(By.xpath(ELEMENT_ADD_CATEGORY_POPUP_DELETE_CATEGORY.replace("${nameCategory}",nameCategory)));
		alert.acceptAlert();
	}

	/**
	 * Go to Import Node popup
	 * By QuynhPT
	 * date 16/01/2015
	 */
	public void goToImportNode(){
		click(ELEMENT_ACTIONBAR_IMPORT_BUTTON);
		Utils.pause(2000);
	}

	/**
	 * Open Export Node popup
	 * By QuynhPT
	 * date 16/01/2015
	 */
	public void goToExportNode(){
		click( ELEMENT_ACTIONBAR_EXPORT_BUTTON);
		Utils.pause(2000);
	}

	/**
	 * Select a value for behavior
	 * by quynhpt
	 * date 16/01/2015
	 */
	public enum defineValueBehavior{
		CREATE_NEW,REMOVE_EXISTING,REPLACE_EXISTING,THROW_EXEPTION;
	}
	/**
	 * Select a value for behavior
	 * @param value
	 */
	public void selectBehavior(defineValueBehavior value){
		switch(value){
		case CREATE_NEW:
			click(ELEMENT_IMPORT_NODE_POPUP_BEHAVIOR_CREATE_NEW);
			break;
		case REMOVE_EXISTING:
			click(ELEMENT_IMPORT_NODE_POPUP_BEHAVIOR_REMOVE_EXISTING);
			break;
		case REPLACE_EXISTING:
			click(ELEMENT_IMPORT_NODE_POPUP_BEHAVIOR_REPLACE_EXISTING);
			break;
		case THROW_EXEPTION:
			click(ELEMENT_IMPORT_NODE_POPUP_BEHAVIOR_THROW_EXCEPTION);
			break;
		}
	}
	/**
	 * Import a Node 
	 * By QuynhPt
	 * date 16/01/2015
	 * @param linkFile
	 * @param behavior
	 * @param version
	 * @param linkVersion
	 */
	public void importNode(String linkFile, String behavior, boolean version,String linkVersion){
		//Verify that the popup is shown
		waitForAndGetElement(ELEMENT_IMPORT_NODE_POPUP_TITLE);

		//upload the file
		WebElement upload = waitForAndGetElement(ELEMENT_IMPORT_NODE_POPUP_UPLOAD_BUTTON, DEFAULT_TIMEOUT,1,2);

		((JavascriptExecutor)driver).executeScript("arguments[0].style.display = 'block';", upload);
		upload.sendKeys(getAbsoluteFilePath(linkFile));
		String[] nameFile = linkFile.split("/");
		waitForAndGetElement(ELEMENT_IMPORT_NODE_POPUP_UPLOAD_FILE_LABEL.replace("${fileName}", nameFile[1]));

		//select a value for behavior
		select(ELEMENT_IMPORT_NODE_POPUP_BEHAVIOR, behavior);

		if(version){
			WebElement uploadVersion = waitForAndGetElement(ELEMENT_IMPORT_NODE_POPUP_VERSION_HISTORY_BUTTON, DEFAULT_TIMEOUT,1,2);
			((JavascriptExecutor)driver).executeScript("arguments[0].style.display = 'block';", uploadVersion);
			uploadVersion.sendKeys(getAbsoluteFilePath(linkVersion));
			String[] namefile = linkVersion.split("/");
			waitForAndGetElement(ELEMENT_IMPORT_NODE_POPUP_UPLOAD_FILE_LABEL.replace("${fileName}", namefile[1]));
		}
		switchToParentWindow();
		click(ELEMENT_IMPORT_MODE_POPUP_IMPORT_BUTTON);

		waitForMessage("Imported successfully.");
		click(button.ELEMENT_OK_BUTTON);
		Utils.pause(2000);

	}

	/**
	 * Export node
	 * By QuynhPT
	 * date 16/01/2015
	 * @param systemView
	 * @param zip
	 */
	public void exportNode(boolean systemView, boolean zip) {
		//Verify that the popup is shown
		waitForAndGetElement(ELEMENT_EXPORT_NODE_POPUP_TITLE);
		//select doc view type
		if (!systemView)
			click(ELEMENT_EXPORT_NODE_POPUP_DOC_VIEW, 2);
		//select zip type
		if (zip)
			click(ELEMENT_EXPORT_NODE_POPUP_ZIP, 2);

		click(ELEMENT_EXPORT_NODE_POPUP_EXPORT_BUTTON);
		waitForElementNotPresent(ELEMENT_EXPORT_NODE_POPUP_EXPORT_BUTTON);
	}
	/**
	 * Open Properties popup
	 * By QuynhPT
	 * date 19/01/2015
	 */
	public void goToProperties(){
		waitForAndGetElement(ELEMENT_ACTIONBAR_PROPERTIES);
		click(ELEMENT_ACTIONBAR_PROPERTIES);
	}

	/**
	 * Go to Properties popup
	 * By QuynhPT
	 * @param property
	 * @param value
	 */
	public void addProperty(String property, String value){
		waitForAndGetElement(ELEMENT_VIEWPROPERTIES_PROPERTIES_TAB);
		click(ELEMENT_VIEWPROPERTIES_ADD_PROPERTY_TAB);
		Utils.pause(1000);
		select(ELEMENT_VIEWPROPERTIES_ADD_PROPERTY_INPUT, property);
		type(ELEMENT_VIEWPROPERTIES_VALUE_INPUT,value,true);
		button.save();

		//Check if a property is added successfully.
		waitForAndGetElement(ELEMENT_VIEWPROPERTIES_PROPERTY.replace("{$property}", property).replace("{$value}", value));
		button.close();
	}
	/**
	 * Open Manage Pulishtation popup
	 * By QuynhPT
	 */
	public void goToManagePublishtation(){
		waitForAndGetElement(ELEMENT_ACTIONBAR_MANAGER_PUBLISHTATION);
		click(ELEMENT_ACTIONBAR_MANAGER_PUBLISHTATION);
		Utils.pause(2000);
	}

	/**
	 * Manage Publication popup
	 * By thunt
	 * Update QuynhPT
	 * @param state
	 * @param date
	 */
	public void managePublication(String state, String...date){
		By bState = By.xpath(ELEMENT_MANAGEPUBLICATION_STATE.replace("{$state}", state));
		String date1 = (String) (date.length > 0 ? date[0]:"");
		String date2 = (String) (date.length > 1 ? date[1]:"");
		//select State
		click(bState);
		waitForAndGetElement(ELEMENT_MANAGEPUBLICATION_CURRENT_SPECIFIC_STATUS.replace("${status}", state));
		if (state.equals("Staged") & (date.length > 0)){
			click(ELEMENT_MANAGEPUBLICATION_SCHEDULE_TAB);
			if((date1!=""&& date1!= null))
				type(ELEMENT_MANAGEPUBLICATION_PUB_FROM_INPUT,date1,true);
			if((date2!="" && date2!= null))
				type(ELEMENT_MANAGEPUBLICATION_PUB_TO_INPUT,date2,true);
			button.save();
			if(date.length > 2){
				waitForMessage(MSG_INVALID_DATE_TIME);
				button.ok();
				button.close();
				return;
			}
			waitForMessage("Your new publication schedule was saved successfully.");
			button.ok();
			click(ELEMENT_MANAGEPUBLICATION_REVISION_TAB);
		}
		waitForAndGetElement(ELEMENT_MANAGEPUBLICATION_HISTORY_TAB);
		waitForAndGetElement(ELEMENT_MANAGEPUBLICATION_REVISION_TAB);

		click(ELEMENT_MANAGEPUBLICATION_HISTORY_TAB);
		waitForAndGetElement(ELEMENT_MANAGEPUBLICATION_HISTORY_ITEM.replace("${state}", state));

		button.close();

	}

	/**
	 * Add a Translation
	 */
	public void addTranslation() {
		info("Click on Add Tranlation button");
		click(ELEMENT_ACTIONBAR_ADDTRANSLATION);
	}



	/**
	 * Add a document translation
	 * @param path
	 * @param content
	 */
	public void addDocumentTranslation(String path, String content) {
		addTranslation();
		waitForAndGetElement(ELEMENT_ADDTRANSLATION_SELECTDOC);
		click(ELEMENT_ADDTRANSLATION_SELECTDOC);
		Utils.pause(2000);
		String[] arrayPath = path.split("/");
		for (String arrayElement : arrayPath) {
			click(ELEMENT_SELECT_DOCUMENT_NODE_FOLDER.replace("${node}", arrayElement));
		}

		if (!content.isEmpty()) {
			waitForAndGetElement(ELEMENT_SELECT_DOCUMENT_NODE_FILE.replace("${content}", content));
			click(ELEMENT_SELECT_DOCUMENT_NODE_FILE.replace("${content}",content));
		}
		click(ELEMENT_SAVE_BTN);
		Utils.pause(2000);
	}


	/**
	 * Go to publication
	 */
	public void goToPublication() {
		click(ELEMENT_ACTIONBAR_MORE);
		click(ELEMENT_ACTIONBAR_PUBLICATION);
	}

	/**
	 * Add a category for a node
	 * @param node
	 * @param category
	 */
	public void addCategoryForNode(String node, String category){
		info("Click on More menu");
		click(ELEMENT_ACTIONBAR_MORE);
		Utils.pause(2000);
		click(ELEMENT_ACTIONBAR_CATEGORY);
		Utils.pause(2000);
		click(ELEMENT_CATEGORY_CHANGE_FORM_SELECT_CATEGORY);
		Utils.pause(2000);
		select(ELEMENT_CATEGORY_SELECT_CATEGORY_TREE,category);
		Utils.pause(2000);
		click(ELEMENT_CATEGORY_ADD_ROOT_NODE);
		Utils.pause(2000);
	}
	/**
	 * Go to the publication status' form
	 */
	public void changeStatusPulication(String status) {
		waitForAndGetElement(ELEMENT_PUBLICATION_STATUS.replace("${status}", status));
		click((ELEMENT_PUBLICATION_STATUS).replace("${status}", status));
		Utils.pause(2000);
	}

	/**
	 * Go to root drive as Site Management, Collaboration tabs... of sidebar
	 */
	public void goToRootDrive(){
		waitForAndGetElement(ELEMENT_SIDE_BAR_MAINTAB);
		click(ELEMENT_SIDE_BAR_MAINTAB);
		Utils.pause(2000);
	}
	/**
	 * Open View Metadata popup
	 */
	public void viewMetadata(){
		info("View Metadata");
		info("Click on More link");
		click(ELEMENT_ACTIONBAR_MORE);
		info("Click on Metadata link");
		click(ELEMENT_ACTIONBAR_METADATA); 
		info("Verify that View metadata popup is shown");
		waitForAndGetElement(ELEMENT_METADATA_POPUP);
		info("Close the popup");
		click(ELEMENT_METADATA_POPUP_CANCEL);
		info("Metadata popup is shown successfully");
	}
	/**
	 * Add/Edit a comment
	 * @param content
	 */
	public void addEditComment(String content, boolean isAdd){
		info("Add/Edit a comment");
		if(isAdd==true){
			info("Click on Add comment on action bar");
			click(ELEMENT_ACTIONBAR_ADDCOMMENT);
		}else {
			info("Click on Edit comment button on action bar");
			click(ELEMENT_SITEEXPLORER_COMMENT_EDIT);
		}
		info("Refresh the page");
		this.driver.navigate().refresh();
		info("Input a content to the frame");
		inputDataToCKEditor(ELEMENT_FILEFORM_BLANK_CONTENT,content);
		info("Switch to parent window");
		switchToParentWindow();
		info("Click on Save button");
		click(ELEMENT_SITEEXPLORER_COMMENT_SAVE);
		info("Finish adding/Editing the Comment");
	}

	/**
	 * Delete a file or node in SE by clicking a checkbox of that file
	 * @param file
	 */
	public void selectAndDeleteByCheckBox(String file){
		waitForAndGetElement(ELEMENT_PERSONAL_DOCUMENT_FILE_CHECKBOX.replace("${file}",file));
		click(ELEMENT_PERSONAL_DOCUMENT_FILE_CHECKBOX.replace("${file}",file));
		click(ELEMENT_ACTIONBAR_DELETE);
		click(ELEMENT_SITEEXPLORER_CONFIRMBOX_DELETE);
		waitForElementNotPresent(ELEMENT_PERSONAL_DOCUMENT_FILE_CHECKBOX.replace("${file}",file));
	}
	
	/**
	 * Open drive area
	 */
	public void openDrives() {
		Utils.pause(500);
		if (waitForAndGetElement(ELEMENT_SHOW_DRIVES, 3000, 0) != null)
			click(ELEMENT_SHOW_DRIVES);
	    else
			click(By.xpath("//*[@title = 'Show Drives']"));
		Utils.pause(1000);
	}
	
	/**
	 * Go to a drive
	 * @param nameDrive
	 */
	public void selectADrive(String nameDrive){
		info("Go to a folder of a drive");
		waitForAndGetElement(ELEMENT_SELECTED_DRIVE.replace("${nameDrive}", nameDrive));
		click(ELEMENT_SELECTED_DRIVE.replace("${nameDrive}", nameDrive));
		Utils.pause(2000);
	}
	
	/**
	 * Go to a folder
	 * @param path
	 */
	public void goToAFolder(String path){
		info("Go to a folder of a drive");
		Utils.pause(1000);
		WebElement pathInput = waitForAndGetElement(ELEMENT_SITE_PATH,2000,1,2);
		pathInput.clear();
		pathInput.sendKeys(path);
		
		Actions action = new Actions(this.driver);
		action.moveToElement(pathInput).sendKeys(Keys.ENTER).build().perform();
		action.moveToElement(pathInput).release();
		Utils.pause(2000);
	}
	
	/**
	 * Open Web view type
	 */
	public void clickWebView() {
		info("Select a view type");
		waitForAndGetElement(ELEMENT_WEB_VIEW);
		click(ELEMENT_WEB_VIEW);
		Utils.pause(3000);
	}
	
	/**
	 * Open Admin view type
	 */
	public void clickAdminView() {
		info("Select a view type");
		waitForAndGetElement(ELEMENT_ADMIN_VIEW_ICON);
		click(ELEMENT_ADMIN_VIEW_ICON);
		Utils.pause(2000);
	}
	
	/**
	 * Select File Explorer tree on left panel
	 */
	public void selectFileExplorer(){
		info("Select File Explorer");
		WebElement el = (new WebDriverWait(driver, 30))
				  .until(ExpectedConditions.presenceOfElementLocated(ELEMENT_FILE_EXPLORER_ICON));
		el.click();
		Utils.pause(3000);
	}
	
	/**
	 * Click on Delete button
	 */
	public void clickDeleteButton(){
		info("click on Delete button");
		waitElementAndTryGetElement(ELEMENT_DELETE_ALL_BUTTON);
		WebElement el = waitForAndGetElement(ELEMENT_DELETE_ALL_BUTTON);
		/*WebElement el = (new WebDriverWait(driver, 30))
				  .until(ExpectedConditions.presenceOfElementLocated(ELEMENT_DELETE_ALL_BUTTON));*/
		el.click();
		dialog.deleteInDialog();
		Utils.pause(3000);
	}
	
	/**
	 * Open a file from right panel
	 * @param filename
	 */
	public void selectAFile(String filename){
		info("Waiting the file:"+filename+" is shown");
		waitForAndGetElement(ELEMENT_FILE_TITLE_RIGHT_PANEL.replace("${fileName}", filename),3000,1);
		info("Select the file");
		click(ELEMENT_FILE_TITLE_RIGHT_PANEL.replace("${fileName}", filename));
		Utils.pause(3000);
		info("The document is opened");
	}
	
	/**
	 * Select all files in folder under admin view
	 */
	public void selectAllFiles() {
		info("Select all file");
		WebElement el = (new WebDriverWait(driver, 30))
				  .until(ExpectedConditions.presenceOfElementLocated(ELEMENT_SITE_EXPLORER_ALL_CHECKBOX));
		if (waitForAndGetElement(ELEMENT_DOCUMENT_LIST_ROW_CONTENT, 5000, 0) != null) {
			info("check on the checkbox");
			el.click();
			Utils.pause(3000);
			info("Click on Delete button");
			clickDeleteButton();
		}
		
	}
	
	/**
	 * Select a new content in list
	 * @param nameContent
	 */
	public void selectAContentType(String nameContent){
		info("Select a content");
		WebElement el = (new WebDriverWait(driver, 30))
				  .until(ExpectedConditions.presenceOfElementLocated(By.xpath(ELEMENT_SITE_EXPLORER_CONTENT_NAME.replace("${nameContent}", nameContent))));
		el.click();
		Utils.pause(3000);
	}
	/**
	 * Delete all files in a folder under Admin view
	 */
	public void deleteAllFiles(){
		info("Select Admin view type");
		clickAdminView();
		info("Select All checkbox");
		selectAllFiles();
	}
}

package org.exoplatform.selenium.platform.ecms;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.Dialog;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.PlatformBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * 
 * By quynhpt
 * Date: 09/12/2014
 */
public class SitesExplorer extends PlatformBase {
	

	public SitesExplorer(WebDriver dr, String...plfVersion) {
		this.driver=dr;
		this.plfVersion = plfVersion.length>0?plfVersion[0]:"4.0";
		this.button= new Button(driver);
		this.dialog = new Dialog(driver);
	}

	// View icons
	public final By ELEMENT_LIST_VIEW_ICON = By.xpath("//*[@data-original-title = 'List']");
	public final By ELEMENT_ADMIN_VIEW_ICON = By.xpath("//*[@data-original-title = 'Admin']");
	public final By ELEMENT_ICONS_VIEW = By.xpath("//*[@data-original-title = 'Icons']");
	public final By ELEMENT_WEB_VIEW = By.xpath("//*[@data-original-title = 'Web']");
	public final By ELEMENT_CATEGORIES_VIEW = By.xpath("//*[@data-original-title = 'Categories']");
	
	//left panel
	public final By ELEMENT_FILE_EXPLORER = By.xpath("//*[@data-original-title = 'File Explorer']");
	public final By ELEMENT_FILE_EXPLORER_ICON = By.xpath(".//i[@class='uiIconEcmsExplorerMini uiIconEcmsLightGray']");
	//context menu
	public final By ELEMENT_CONTEXT_MENU_DELETE_LINK = By.xpath("//*[@class='uiIconEcmsDelete']");
	
	
	public final String ELEMENT_SELECTED_DRIVE= ".//a[@data-original-title='${nameDrive}']";
	public final String ELEMENT_NODE_ADMIN_VIEW = "//*[contains(@class, 'columnText')]//*[contains(text(), '${nodeName}')]";
	
	//Site path
	public final By ELEMENT_SITE_PATH= By.xpath(".//*[@id='address']");

	
	// View's content
	public String ELEMENT_CHECKBOX_FILE = ".//span[text()='${fileName}']/../../../div[@class='columnCheckbox']/span";
    public String ELEMENT_FILE_TITLE_WEB_VIEW=".//*[@id='UIDocumentInfo']//span[text()='${fileName}']";
    public String ELEMENT_FILE_TITLE_RIGHT_PANEL=".//*[@class='nodeGroup']//span[text()='${fileName}']";
   
    //View detail a content in SE
   // public String ELEMENT_DOCUMENTS_TITLE_IN_SE= ".//ul[@class='nodeGroup']//span[@class='nodeName'][text()='${nameFile}']";
    public By ELEMENT_CONTENT_THUMBNAIL = By.xpath(".//*[@class='iconContainer']/i");
    public String ELEMENT_CONTENT_NAME = ".//*[@id='UIDocumentContainer']//span[text()='${nameFile}']";
    public String ELEMENT_WEBCONTENT_NAME = ".//*[@id='UIDocumentContainer']//h6[text()='${nameFile}']";
    public By ELEMENT_CONTENT_MESSAGE_NOT_AVAILABLE = By.xpath(".//h4[text()='The preview of this document is not available.']");
    public By ELEMENT_CONTENT_MESSAGE_TOO_MANY_PAGES = By.xpath(".//h4[text()='The preview is not available for content with over 99 pages.']");
    public By ELEMENT_CONTENT_MESSAGE_OVER_SIZE= By.xpath(".//h4[text()='The preview is not available for content larger than 5 MB.']");
    public By ELEMENT_CONTENT_DOWNLOAD_BUTTON= By.xpath(".//*[@class='btn btn-primary']");
    public By ELEMENT_CONTENT_OPEN_DESKTOP=By.xpath(".//*[@class='btn']");
    
    public By ELEMENT_SITE_EXPLORER_ALL_CHECKBOX= By.xpath("//input[@type='checkbox' and @name= 'UIFileViewCheckBox']");
	
    //Add new content
    public String ELEMENT_SITE_EXPLORER_CONTENT_NAME= ".//*[@id='UISelectDocumentForm']//i[@data-original-title='${nameContent}']";
    /* ================***================== */

	/**
	 * Go to a drive
	 * @param nameDrive
	 */
	public void openADrive(String nameDrive){
		info("Go to a folder of a drive");
		waitForAndGetElement(ELEMENT_SELECTED_DRIVE.replace("${nameDrive}", nameDrive));
		click(ELEMENT_SELECTED_DRIVE.replace("${nameDrive}", nameDrive));
		//return new SitesExplorer(driver,this.plfVersion);
	}
	/**
	 * Go to a folder
	 * @param path
	 */
	public void goToAFolder(String path){
		info("Go to a folder of a drive");
		Utils.pause(1000);
		WebElement pathInput = waitForAndGetElement(ELEMENT_SITE_PATH,2000,1,2);
		//type(pathInput,path,true);
		pathInput.clear();
		pathInput.sendKeys(path);
		
		Actions action = new Actions(driver);
		action.moveToElement(pathInput).sendKeys(Keys.ENTER).build().perform();
		action.moveToElement(pathInput).release();
		
		//return new SitesExplorer(driver,this.plfVersion); 
	}
	/**
	 * Open a file from right panel
	 * @param filename
	 * @return SitesExplorer
	 */
	public void openDocumentFromRighPanel(String filename){
		waitForAndGetElement(ELEMENT_FILE_TITLE_RIGHT_PANEL.replace("${fileName}", filename),3000,0);
		click(ELEMENT_FILE_TITLE_RIGHT_PANEL.replace("${fileName}", filename));
		Utils.pause(3000);
		info("The document is opened");
		//return new SitesExplorer(driver,this.plfVersion); 
	}
	/**
	 * Open Admin view type
	 * @return SiteExplore
	 */
	public void clickAdminView() {
		info("Select a view type");
		waitForAndGetElement(ELEMENT_ADMIN_VIEW_ICON);
		click(ELEMENT_ADMIN_VIEW_ICON);
		Utils.pause(2000);
		//return new SitesExplorer(driver,this.plfVersion);
	}

	/**
	 * Open Web view type
	 * @return SiteExplore
	 */
	public void clickWebView() {
		info("Select a view type");
		waitForAndGetElement(ELEMENT_WEB_VIEW);
		click(ELEMENT_WEB_VIEW);
		//return new SitesExplorer(driver,this.plfVersion);
	}
	
	/**
	 * Open List view type
	 * @return SiteExplore
	 */
	public void clickListView() {
		info("Select a view type");
		waitForAndGetElement(ELEMENT_LIST_VIEW_ICON);
		click(ELEMENT_LIST_VIEW_ICON);
		//return new SitesExplorer(driver,this.plfVersion);
	}
	
	/**
	 * Open Icon view type
	 * @return SiteExplore
	 */
	public void clickIconView() {
		info("Select a view type");
		waitForAndGetElement(ELEMENT_ICONS_VIEW);
		click(ELEMENT_ICONS_VIEW);
		//return new SitesExplorer(driver,this.plfVersion);
	}
	
	/**
	 * Open Categories view type
	 * @return SiteExplore
	 */
	public void clickCategoriesView() {
		info("Select a view type");
		waitForAndGetElement(ELEMENT_CATEGORIES_VIEW);
		click(ELEMENT_CATEGORIES_VIEW);
		//return new SitesExplorer(driver,this.plfVersion);
	}
	/**
	 * Select a file by ticking on the checkbox
	 * @param fileName
	 * @return SiteExplorer
	 */
	public void tickCheckbox(String fileName) {
		info("tick on the check box to select a file");
		String[] parts = fileName.split(".");
		click(ELEMENT_CHECKBOX_FILE.replace("${fileName}", parts[0]));
		//return new SitesExplorer(driver,this.plfVersion);
	}

	/**
	 * Select all files in folder under admin view
	 * @return SitesExplorer
	 */
	public void selectAllFiles() {
		info("Select all file");
		WebElement el = (new WebDriverWait(driver, 30))
				  .until(ExpectedConditions.presenceOfElementLocated(ELEMENT_SITE_EXPLORER_ALL_CHECKBOX));
		el.click();
		Utils.pause(3000);
		//check(el, 2);
		//
		//return new SitesExplorer(driver, this.plfVersion);
	}
	/**
	 * Select File Explorer tree on left panel
	 * @return SitesExplorer page
	 */
	public void selectFileExplorer(){
		info("Select File Explorer");
		WebElement el = (new WebDriverWait(driver, 30))
				  .until(ExpectedConditions.presenceOfElementLocated(ELEMENT_FILE_EXPLORER_ICON));
		el.click();
		Utils.pause(3000);
		//return new SitesExplorer(driver, this.plfVersion);
	}
	/**
	 * Select a new content in list
	 * @param nameContent
	 * @return ManageTemplate page
	 */
	public void selectAContent(String nameContent){
		info("Select a content");
		WebElement el = (new WebDriverWait(driver, 30))
				  .until(ExpectedConditions.presenceOfElementLocated(By.xpath(ELEMENT_SITE_EXPLORER_CONTENT_NAME.replace("${nameContent}", nameContent))));
		el.click();
		Utils.pause(3000);
		//return new ManageTemplate(driver, this.plfVersion);
	}
}

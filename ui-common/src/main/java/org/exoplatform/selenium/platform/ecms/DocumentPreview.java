package org.exoplatform.selenium.platform.ecms;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.PlatformBase;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.exoplatform.selenium.TestLogger.info;

/**
 * By quynhpt
 * data 26/11/2014
 */
public class DocumentPreview  extends PlatformBase {
	
	/**
	 * By ELEMENT_PREVIEW_MODE : Preview mode shadow mask
	 * By ELEMENT_TOOGLE_SIDE_BAR_ICON : Toogle button on the action bar
	 */
	public final By ELEMENT_PREVIEW_MODE=By.xpath(".//*[@id='UIDocumentPreview']");
	public final By ELEMENT_PREVIEW_RATE_VOTE_INFORMATION=By.xpath(".//*[@class='voteRatingInfo']");
	public final By ELEMENT_TOOGLE_SIDE_BAR_ICON = By.id("sidebarToggle");
	
    /**
     * Vote and Rate information
     */
	public final String ELEMENT_VOTE_RATING_INFOR = ".//*[@id='UIDocumentPreview']//div[4]//span[text()='${rate}']";

	/**
	 * thumbnail's view is shown when clicking on Toogle button
	 */
	public final By ELEMENT_THUMBNAIL_VIEW = By.id("thumbnailView");
	
	
	/**
	 * Content of Document (PDF and office files)
	 * ELEMENT_TEXT_CONTENT_DOCUMENT: define a text of a page in document
	 */
	public final String ELEMENT_TEXT_CONTENT_DOCUMENT=".//*[@id='pageContainer${number}']//div[text()='${number}']";
	
	/**
	 * Search panel
	 * By ELEMENT_SEARCH_ICON : Search button
	 * By ELEMENT_SEARCH_PANEL_BAR : Search pane bar
	 * By ELEMENT_SEARCH_PANEL_INPUT_FIELD: Input field
	 * ELEMENT_SEARCH_PANEL_PREVIOUS_ARROW : Previous button
	 * ELEMENT_SEARCH_PANEL_NEXT_ARROW: Next button
	 * ELEMENT_SEARCH_PANEL_HIGHLIGHT_ALL_CHECKBOX: the checkbox of  'Highlight All' function
	 * ELEMENT_SEARCH_PANEL_MATCH_CASE: the checkbox of "Match case" function
	 * ELEMENT_SEARCH_MESSAGE_AREA: the area to display message of a search
	 */
	public final By ELEMENT_SEARCH_ICON= By.xpath(".//*[@id='viewFind']/i");
	public final By ELEMENT_SEARCH_PANEL_BAR= By.id("findbar");
	public final By ELEMENT_SEARCH_PANEL_INPUT_FIELD= By.id("findInput");
	public final By ELEMENT_SEARCH_PANEL_PREVIOUS_ARROW=  By.xpath(".//*[@id='findPrevious']/i");
	public final By ELEMENT_SEARCH_PANEL_NEXT_ARROW = By.xpath(".//*[@id='findNext']/i");
	public final By ELEMENT_SEARCH_PANEL_HIGHLIGHT_ALL_CHECKBOX= By.xpath(".//*[@id='findHighlightAll']");
	public final By ELEMENT_SEARCH_PANEL_HIGHLIGHT_ALL_LABEL=By.xpath(".//span[text()='Highlight all']");
	public final By ELEMENT_SEARCH_PANEL_MATCH_CASE = By.xpath("//input[@type='checkbox' and @id= 'findMatchCase']");
	public final By ELEMENT_SEARCH_PANEL_MATCH_CASE_AREA=By.xpath("//span[text()='Match case']");
	public final By ELEMENT_SEARCH_MESSAGE_AREA= By.id("findMsg");
	public final String ELEMENT_SEARCH_TEXT_HIGHLIGHT=".//span[@class='highlight selected'][text()='${text}']";
	
	/**
	 * By ELEMENT_PREVIOUS_PAGE_ICON : Previous button on Action bar
	 * By ELEMENT_NEXT_PAGE_ICON: Next button on Action bar
	 * By ELEMENT_INPUT_NUMBER_PAGE_FIELD: input field on Action bar
	 */
	public final By ELEMENT_PREVIOUS_PAGE_ICON= By.id("previous");
	public final By ELEMENT_NEXT_PAGE_ICON= By.id("next");
	public final By ELEMENT_INPUT_NUMBER_PAGE_FIELD = By.id("pageNumber");
	
	/**
	 * page's number section
	 * By ELEMENT_CURRENT_PAGE_NUMBER : the field input a number of a page in the document
	 * By ELEMENT_TOTAL_PAGE_NUMBER : the area, display total the number of pages of the document
	 */
	public final By ELEMENT_CURRENT_PAGE_NUMBER=By.id("pageNumber");
	public final By ELEMENT_TOTAL_PAGE_NUMBER=By.id("numPages");
	
	/**
	 * By ELEMENT_ZOOM_OUT_ICON : "-" icon, zoom out button on Action bar
	 * By ELEMENT_ZOOM_IN_ICON : "+" icon, zoom in button on Action bar
	 * ELEMENT_PAGE_CONTAINER : a page in reader form
	 */
	public final By ELEMENT_ZOOM_OUT_ICON= By.xpath(".//*[@id='zoomOut']/i");
	public final By ELEMENT_ZOOM_IN_ICON = By.xpath(".//*[@id='zoomIn']/i");
	public final String ELEMENT_PAGE_CONTAINER=".//*[@id='pageContainer${number}']";

	/**
	 * values of zoom option list
	 * By ELEMENT_ZOOM_OPTION_DROP_BOX : zoom drop box on Action bar
	 * By ELEMENT_ZOOM_AUTOMATIC : "Automatic Zoom" value of the drop  box
	 * By ELEMENT_ZOOM_ACTUAL_SIZE : "Actual size" value of the drop box
	 * By ELEMENT_ZOOM_PAGE_FIT: "Page fit" value of the drop box
	 * By ELEMENT_ZOOM_PAGE_WIDTH: "Page width" value of the drop box
	 * By ELEMENT_ZOOM_50_PERCENT: "50%" value of the drop box
	 * By ELEMENT_ZOOM_75_PERCENT: "75%" value of the drop box
	 * By ELEMENT_ZOOM_100_PERCENT: "100%" value of the drop box
	 * By ELEMENT_ZOOM_125_PERCENT: "125%" value of the drop box
	 * By ELEMENT_ZOOM_150_PERCENT: "150%" value of the drop box
	 * By ELEMENT_ZOOM_200_PERCENT: "200%" value of the drop box
	 */
	public final By ELEMENT_ZOOM_OPTION_DROP_BOX = By.id("scaleSelect");
	public final By ELEMENT_ZOOM_AUTOMATIC = By.id("pageAutoOption");
	public final By ELEMENT_ZOOM_ACTUAL_SIZE= By.id("pageActualOption");
	public final By ELEMENT_ZOOM_PAGE_FIT= By.id("pageFitOption");
	public final By ELEMENT_ZOOM_PAGE_WIDTH= By.id("pageWidthOption");
	public final By ELEMENT_ZOOM_50_PERCENT= By.xpath(".//*[@value='0.5']");
	public final By ELEMENT_ZOOM_75_PERCENT= By.xpath(".//*[@value='0.75']");
	public final By ELEMENT_ZOOM_100_PERCENT= By.xpath(".//*[@value='1']");
	public final By ELEMENT_ZOOM_125_PERCENT= By.xpath(".//*[@value='1.25']");
	public final By ELEMENT_ZOOM_150_PERCENT= By.xpath(".//*[@value='1.5']");
	public final By ELEMENT_ZOOM_200_PERCENT= By.xpath(".//*[@value='2']");
	
	/**
	 * Full-screen mode
	 * By ELEMENT_SWITCH_PRESENTATION_MODE_ICON: "Switch presentation mode" button
	 * By ELEMENT_FULLSCREEN_ALLOW_BUTTON: "Allow" button
	 * By ELEMENT_FULLSCREEN_DENY_BUTTON: "Deny" button
	 * By ELEMENT_FULLSCREEN_REMEMBER_CHECK_BOX: "Remember decision for $HOST" check box
	 */
	public final By ELEMENT_SWITCH_PRESENTATION_MODE_ICON = By.xpath(".//i[@class='uiIconEcmsExpand uiIconLightGray']");
	
	/**
	 * values of tool option list
	 * By ELEMENT_TOOL_OPTION_ICON: Tool option menu button 
	 * By ELEMENT_TOOL_DOWNLOAD: Download button of the drop box
	 * By ELEMENT_TOOL_PRINT: Print button of the drop box
	 * By ELEMENT_TOOL_GO_TO_FIRST_PAGE: "Go to first page" button of the drop box
	 * By ELEMENT_TOOL_GO_TO_LAST_PAGE: "Go to last page" button of the drop box
	 * By ELEMENT_TOOL_ROTATE_CLOCKWISE : "Rotate clockwise" button of the drop box
	 * By ELEMENT_TOOL_ROTATE_COUNTER_CLOCKWISE: "Rotate counter clockwise" button of the drop box
	 * ELEMENT_TOOL_ROTATE_CLOCKWISE_ANGLE: define a angle of the page 
	*/
	public final By ELEMENT_TOOL_OPTION_ICON= By.id("secondaryToolbarToggle");
	public final By ELEMENT_TOOL_DOWNLOAD = By.id("secondaryDownload");
	public final By ELEMENT_TOOL_PRINT = By.id("secondaryPrint");
	public final By ELEMENT_TOOL_GO_TO_FIRST_PAGE = By.id("firstPage");
	public final By ELEMENT_TOOL_GO_TO_LAST_PAGE = By.id("lastPage");
	public final By ELEMENT_TOOL_ROTATE_CLOCKWISE = By.id("pageRotateCw");
	public final By ELEMENT_TOOL_ROTATE_COUNTER_CLOCKWISE = By.id("pageRotateCcw");
	public final String ELEMENT_TOOL_ROTATE_CLOCKWISE_ANGLE="//div[@data-angle='${angle}']";
	
	/**
	 * Comment area
	 */
	public final By ELEMENT_COMMENT_AREA= By.xpath(".//*[@class='uiBox commentArea pull-right']");
	public final By ELEMENT_COMMENT_COLLAPSE_ICON = By.xpath(".//*[@class='uiIconMiniArrowRight uiIconWhite']"); 
	public final By ELEMENT_COMMENT_EXPAND_ICON = By.xpath(".//*[@class='uiIconWhite uiIconMiniArrowLeft']"); 
	public final By ELEMENT_COMMENT_DOCUMENT_ICON = By.xpath(".//*[@class='uiIcon16x16FileDefault uiIcon16x16nt_file uiIcon16x16applicationpdf uiIconLightGray']");
	public final By ELEMENT_COMMENT_DOCUMENT_NAME = By.xpath(".//*[@id='UIDocumentPreview']//*[@class='title']");
	public final By ELEMENT_COMMENT_INPUT_AVATAR = By.xpath(".//*[@class='commentInputBox']//*[@class='avatarXSmall pull-left']");
	public final By ELEEMNT_COMMENT_LIST_AVATAR=By.xpath(".//*[@class='commentList']//*[@class='avatarXSmall pull-left']");
	public final By ELEMENT_COMMENT_INPUT_FIELD = By.xpath(".//*[@id='commentTextAreaPreview']");
	public final By ELEMENT_COMMENT_INPUT_PLACEHOLDER= By.xpath(".//*[@placeholder='Add your comment...']");
	public final By ELEMENT_COMMENT_BOX = By.xpath(".//*[@id='commentTextAreaPreview']");
	public final String ELEMENT_COMMENT_CONTENT = ".//*[@class='cont'][contains(text(),'${text}')]";
	public final By ELEMENT_COMMENT_EMPTY = By.xpath(".//*[text()='No comment yet']");
	public final By ELEMENT_COMMENT_LIST = By.xpath(".//*[@id='UIPreviewCommentArea']//ul[@class='commentList']");
	public final String ELEMENT_COMMENT_DELETE_ICON="i.uiIconLightGray.uiIconClose";
    public final By ELEMENT_COMMENT_TEXT = By.xpath(".//*[@class='cont']");
    
    /**
     * The top of Comment area
     */
    public final By ELEMENT_COMMENT_AREA_TOP_AVATAR = By.xpath(".//*[@id='UIPreviewCommentArea']//div[@class='profile clearfix']//img");
	public final String ELEMENT_COMMENT_AREA_TOP_FULL_NAME = ".//*[@id='UIPreviewCommentArea']//div[@class='rightBlock']//a[text()='${fullName}']";
    public final By ELEMENT_COMMENT_AREA_TOP_DATE_NOTIFICATION= By.xpath(".//*[@id='UIPreviewCommentArea']//p[@class='dateTime']");
    public final String ELEMENT_COMMENT_AREA_TOP_ACTIVITY_TILTE= ".//*[@id='UIPreviewCommentArea']//p[@title='${contentTitle}']";
    public final String ELEMENT_COMMENT_AREA_TOP_ACTIVITY_TITLE_TRUNCATED=".//*[@id='UIPreviewCommentArea']//p[text()='${textTruncated}']";
    public final By ELEMENT_COMMENT_AREA_TOP_COMMENT_ICON=By.xpath(".//*[@id='previewCommentLink']//i[@class='uiIconComment uiIconLightGray']");
    public final By ELEMENT_COMMENT_AREA_TOP_LIKE_ICON = By.xpath(".//*[@id='UIPreviewCommentArea']//i[@class='uiIconThumbUp uiIconLightGray']");
    public final By ELEMENT_COMMENT_AREA_TOP_LIKE_ICON_BLUE= By.xpath(".//*[@id='UIPreviewCommentArea']//i[@class='uiIconThumbUp uiIconBlue']");
    /**
	 * Exit preview mode
	 * ELEMENT_PREVIEW_MODE_CROSS_ICON : "X" icon on the top-right
	 */
	public final By ELEMENT_PREVIEW_MODE_CROSS_ICON= By.xpath(".//a[@title='Close Window']");
	
	/**
	 * Reader Viewer area
	 */
	public final By ELEMENT_PREVIEW_MODE_VIEW_READER= By.id("viewer");
	public final By ELEMENT_PEVIEW_MODE_NOT_AVAIABLE_ICON = By.xpath(".//*[@class='iconContainer']/i");
	public final By ElEMENT_PREVIEW_MODE_NOT_AVAIABLE_MESSAGE= By.xpath(".//*[@id='UIDocumentPreview']//h4[text()='The preview of this document is not available.']");
	public final By ElEMENT_PREVIEW_MODE_NOT_AVAIABLE_MESSAGE_LARGE_FILE= By.xpath(".//*[@id='UIDocumentPreview']//h4[text()='The preview is not available for content larger than 5 MB.']");
	public final By ElEMENT_PREVIEW_MODE_NOT_AVAIABLE_MESSAGE_MANY_PAGES= By.xpath(".//*[@id='UIDocumentPreview']//h4[text()='The preview is not available for content with over 99 pages.']");
	public final By ELEMENT_PREVIEW_MODE_NOT_AVAIABLE_DOWNLOAD_BUTTON= By.xpath(".//*[@class='uiIconDownload uiIconWhite']");
	public final By ELEMENT_PREVIEW_MODE_NOT_AVAIABLE_OPEN_IN_DESKTOP= By.xpath(".//*[@class='btn'][text()='Open on Desktop']");
	public final By ELEMENT_PREVIEW_MODE_READER_AREA= By.id("mainContainer");
	/**
	 * Media area
	 */
	public final By ELEMENT_PREVIEW_MODE_IMAGE= By.xpath(".//*[@data-original-title='']//img");
	public final By ELEMENT_PREVIEW_MODE_DOWNLOAD_LINK = By.xpath(".//*[@class='downloadBtn']");
	
	/**
	 * Webcontent
	 */
	public final String ELEMENT_PREVIEW_MODE_WEBCONTENT_CONTENT=".//*[@id='UIDocumentPreview']//p[text()='${text}']";
	/*--------------------------------------------------------------------------------------------*/
	
	/**
	 * Constructor
	 * @param driver
	 */
	public DocumentPreview (WebDriver driver,String...plfVersion){
		info("Go to Preview mode");
		this.driver = driver;
		this.plfVersion = plfVersion.length>0?plfVersion[0]:"4.0";
	}
	
	/*********************************** READER AREA ********************************************/
	/**
	 * Open or hide thumb-nails of the document
	 */
	public void clickToogleSideBar(){
		info("Show/Hide the thumbnails");
		waitForAndGetElement(ELEMENT_TOOGLE_SIDE_BAR_ICON);
		click(ELEMENT_TOOGLE_SIDE_BAR_ICON);
		//return new DocumentPreview(driver, this.plfVersion);
	}
	
	/**
	 * Open or hide Search panel
	 */
	public void clickSearchIcon(){
		info("Open Search panel");
		waitForAndGetElement(ELEMENT_SEARCH_ICON,3000,0);
		click(ELEMENT_SEARCH_ICON);
		Utils.pause(2000);
		//return new DocumentPreview(driver, this.plfVersion);
	}
	/**
	 * Verify elements of Search panel
	 */
	public void verifySearchPanel(){
		info("Verify input field of search panel");
		waitForAndGetElement(ELEMENT_SEARCH_PANEL_INPUT_FIELD,3000,0);
		info("Verify previous arrow of search panel");
		waitForAndGetElement(ELEMENT_SEARCH_PANEL_PREVIOUS_ARROW,2000,0);
		info("Verify next arrow of search panel");
		waitForAndGetElement(ELEMENT_SEARCH_PANEL_NEXT_ARROW,2000,0);
		info("Verify highlight all checkbox of search panel");
		waitForAndGetElement(ELEMENT_SEARCH_PANEL_HIGHLIGHT_ALL_LABEL,2000,0);
		info("Verify match case of search panel");
		waitForAndGetElement(ELEMENT_SEARCH_PANEL_MATCH_CASE_AREA,2000,0);
		//return new DocumentPreview(driver, this.plfVersion);
	}
	
	/**
	 * Input a text to search on search panel
	 * @param text
	 */
	public void inputTextSearch(String text){
		info("Input a text into search field");
		waitForAndGetElement(ELEMENT_SEARCH_PANEL_INPUT_FIELD,3000,0);
		type(ELEMENT_SEARCH_PANEL_INPUT_FIELD,text,true);
		Utils.pause(2000);
		//return new DocumentPreview(driver, this.plfVersion);
	}
	
	/**
	 * View the results in previous search results page
	 */
	public void clickPreviousSearchPanel(){
		info("Go to the previous search result page");
		WebElement el = (new WebDriverWait(driver, 30))
				  .until(ExpectedConditions.elementToBeClickable(ELEMENT_SEARCH_PANEL_PREVIOUS_ARROW));
		el.click();
		el.click();
		Utils.pause(2000);
		//return new DocumentPreview(this.driver, this.plfVersion);
	}
	
	/**
	 * Check message search results when click on Previous page at the first results page
	 * @param mess
	 */
	public void checkMessageSearch(String mess){
		boolean mesg = (new WebDriverWait(driver, 30))
				  .until(ExpectedConditions.textToBePresentInElementLocated(ELEMENT_SEARCH_MESSAGE_AREA, mess));
		info("mesg.getText():"+mesg);
		if(mesg){
			assert true;
			info("Message is shown");
		}else assert false:"Message is not shown";
		//return new DocumentPreview(driver, this.plfVersion);
	}
	/**
	 * Verify that previous page's number is shown correctly on input number page field
	 * @param currentNum
	 * @param prevNum
	 */
	public void verifyPageNumOfPrevPage(String currentNum,String prevNum){
		int prePg=Integer.parseInt(prevNum);
		int curPg=Integer.parseInt(currentNum);
		if(prePg<curPg){
			assert true;
			info("Previous page is shown correctly");
		}else assert false:"Cannot previous the page";
		//return new DocumentPreview(driver, this.plfVersion);
	}
	/**
	 * View the results in next search results page
	 */
	public void clickNextSearchPanel(){
		info("Go to the next search result page");
		WebElement el = (new WebDriverWait(driver,30))
				  .until(ExpectedConditions.elementToBeClickable(ELEMENT_SEARCH_PANEL_NEXT_ARROW));
		el.click();
		el.click();
		Utils.pause(2000);
		//return new DocumentPreview(driver, this.plfVersion);
	}
	
	/**
	 * Verify that next page's number is shown correctly on input number page field
	 * @param prevNum
	 * @param currentNum
	 */
	public void verifyPageNumOfNextPage(String currentNum, String nextNum){
		int nextPg=Integer.parseInt(nextNum);
		info("nextPg is:"+nextPg);
		int curPg=Integer.parseInt(currentNum);
		info("curPg is:"+curPg);
		if(nextPg>curPg){
			assert true;
			info("Next page is shown correctly");
		}else assert false:"Cannot next the page";
		//return new DocumentPreview(driver, this.plfVersion);
	}
	/**
	 * Verify result message is displayed
	 * @param mesg
	 */
	public void messageSearch(String mesg){
		info("Check message of a search if not found results");
		waitForAndGetElement(ELEMENT_SEARCH_MESSAGE_AREA);
		waitForAndGetElement(ELEMENT_SEARCH_MESSAGE_AREA).getText().contains(mesg);
		//return new DocumentPreview(driver, this.plfVersion);
	}
	
	/**
	 * Tick on Highlight all check box
	 */
	public void tickHighlightAllCheckBox(){
		if(!waitForAndGetElement(ELEMENT_SEARCH_PANEL_HIGHLIGHT_ALL_CHECKBOX).isSelected())
		  info("Tick on High light all check box");
		else info("Un-tick on High light all check box");
		
		click(ELEMENT_SEARCH_PANEL_HIGHLIGHT_ALL_CHECKBOX);
		//return new DocumentPreview(driver, this.plfVersion);
	}
	/**
	 * Tick on Match case check box
	 */
	public void tickMatchCaseCheckBox(){
		WebElement el = this.driver.findElement(ELEMENT_SEARCH_PANEL_MATCH_CASE);
		el.click();
		//return new DocumentPreview(driver, this.plfVersion);
	}
	/**
	 * Go to previous page from reader area
	 */
	public void clickPreviousPage(){
		info("Go to the previous page");
		waitForAndGetElement(ELEMENT_PREVIOUS_PAGE_ICON,3000,0);
		click(ELEMENT_PREVIOUS_PAGE_ICON);
		Utils.pause(3000);
		//return new DocumentPreview(driver, this.plfVersion);
	}
	/**
	 * Scroll down to the last page of the file
	 * @param numLastPage
	 */
	public void scrollDownPage(String numPage){
		int currentNum=1;
        int numPg=Integer.parseInt(numPage);
        
		while(currentNum<numPg){
			currentNum= Integer.parseInt(waitForAndGetElement(ELEMENT_CURRENT_PAGE_NUMBER).getAttribute("value").toString());
			String currentPage=Integer.toString(currentNum);
			info("currentPageNumber:"+currentNum);
			info("scroll to the end of the page:"+currentNum);
			WebElement el = waitForAndGetElement(ELEMENT_TEXT_CONTENT_DOCUMENT.replace("${number}",currentPage));
			JavascriptExecutor jse = (JavascriptExecutor) driver;
		    jse.executeScript("arguments[0].scrollIntoView(true);", el);
		} 
		info("check staying at:"+numPage);
		waitForAndGetElement(ELEMENT_TEXT_CONTENT_DOCUMENT.replace("${number}",numPage));
		//return new DocumentPreview(driver, this.plfVersion);
	}
	/**
	 * Go to next page from reader area
	 */
	public void clickNextPage(){
		info("Go to the next page");
		waitForAndGetElement(ELEMENT_NEXT_PAGE_ICON,3000,0);
		click(ELEMENT_NEXT_PAGE_ICON);
		Utils.pause(3000);
	//	return new DocumentPreview(driver, this.plfVersion);
	}
	
	/**
	 * Scroll up to the first page of the document
	 * @param numPage
	 */
	public void scrollUpPage(String numPage){
		int currentNum=Integer.parseInt(waitForAndGetElement(ELEMENT_CURRENT_PAGE_NUMBER).getAttribute("value").toString());
        int numPg=Integer.parseInt(numPage);
        
		while(currentNum>numPg){
			currentNum= currentNum-1;
			String currentPage=Integer.toString(currentNum);
			info("currentPageNumber:"+currentNum);
			info("scroll to the end of the page:"+currentNum);
			WebElement el = waitForAndGetElement(ELEMENT_TEXT_CONTENT_DOCUMENT.replace("${number}",currentPage));
			JavascriptExecutor jse = (JavascriptExecutor) driver;
		    jse.executeScript("arguments[0].scrollIntoView(true);", el);
		} 
		info("check staying at:"+numPage);
		waitForAndGetElement(ELEMENT_TEXT_CONTENT_DOCUMENT.replace("${number}",numPage));
		//return new DocumentPreview(driver, this.plfVersion);
	} 
	
	/**
	 * Go to a page by inputing the number of the page
	 * @param text
	 */
	public void inputNumPage(String number){
		info("input a text into the comment field");
		WebElement inputField = waitForAndGetElement(ELEMENT_INPUT_NUMBER_PAGE_FIELD,3000,0);
		inputField.sendKeys(number);
		pressEnterKey();
		//type(inputField,number,true);
		//Utils.pause(2000);
		
		/*Actions actions = new Actions(this.driver);
		actions.moveToElement(inputField).sendKeys(Keys.ENTER).build().perform();*/
		//actions.release();
		Utils.pause(2000);
		//return new DocumentPreview(driver, this.plfVersion);
	}
	
	/**
	 * Get current page's number
	 * @return currentNumber
	 */
	public String getCurrentPageNumber(){
		info("get current page number");
		String currentNumber= waitForAndGetElement(ELEMENT_CURRENT_PAGE_NUMBER).getText();
		info("current page number is : "+currentNumber);
		return currentNumber;
	}
	
	/**
	 * Get total page's number
	 * @return totalNumber
	 */
	public String getTotalPageNumber(){
		info("get total page number");
		String totalNumber= waitForAndGetElement(ELEMENT_TOTAL_PAGE_NUMBER,3000,0).getText();
		info("total page number is : "+totalNumber);
		return totalNumber;
	}
	
	/**
	 * Execute zoom out the document
	 */
	public void clickZoomOut(){
		info("Execute zoom out");
		click(ELEMENT_ZOOM_OUT_ICON);
		Utils.pause(3000);
		//return new DocumentPreview(driver, this.plfVersion);
	}
	
	/**
	 * Execute zoom in the document
	 */
	public void clickZoomIn(){
		info("Execute zoom in");
		click(ELEMENT_ZOOM_IN_ICON);
		Utils.pause(3000);
		//return new DocumentPreview(driver, this.plfVersion);
	}
	/**
	 * Verify the size of the page after zoom in and zoom out
	 * @param numDefault
	 * @param numZoomIn
	 * @param numZoomOut
	 * @throws Exception
	 */
	public void verifySizeZoomInOut(int numDefault, int numZoomIn, int numZoomOut) throws Exception{
		if (numDefault<numZoomIn & numZoomIn>numZoomOut){
			assert true;
			info("The zoom in/out is done");
		}
		else {
			assert false: ("The zoom in/out is not worked");
		}
	}
	
	/**
	 * Open Zoom option drop box
	 */
	public void clickZoomOption(){
		info("Open zoom option lis");
	    click(ELEMENT_ZOOM_OPTION_DROP_BOX);
	    //return new DocumentPreview(driver, this.plfVersion);
	}
	
	/**
	 * Select an option of zoom list
	 * @param locator 
	 */
	public void selectAnOptionOfZoomList(Object locator){
		info("Select an option in zoom list");
		click(locator);
		Utils.pause(3000);
		//return new DocumentPreview(driver, this.plfVersion);
	}
	
	/**
	 * Open Tool option drop list
	 */
	public void clickToolOptionIcon(){
		info("Open Tool option list");
		waitForAndGetElement(ELEMENT_TOOL_OPTION_ICON,3000,0);
		click(ELEMENT_TOOL_OPTION_ICON);
		Utils.pause(3000);
		//return new DocumentPreview(driver, this.plfVersion);
	}
	
	/**
	 * Select an option in Tool option drop list
	 * @param locator
	 */
	public void selectAnOptionOfToolOption(Object locator){
		info("Select an option in Tool option list");
		waitForAndGetElement(locator,3000,0);
		click(locator);
		Utils.pause(2000);
		//return new DocumentPreview(driver, this.plfVersion);
	}
	/******************************************* COMMENT AREA ************************************************/
	/**
	 * Hide Comment area
	 */
	public void hideCommentArea(){
	    waitForAndGetElement(ELEMENT_COMMENT_COLLAPSE_ICON);
		click(ELEMENT_COMMENT_COLLAPSE_ICON);
		//return new DocumentPreview(driver, this.plfVersion);
	}
	
	/**
	 * Show Comment area
	 */
	public void showCommentArea(){
	    waitForAndGetElement(ELEMENT_COMMENT_EXPAND_ICON);
		click(ELEMENT_COMMENT_EXPAND_ICON);
		//return new DocumentPreview(driver, this.plfVersion);
	}
	/**
	 * Input a comment into input field
	 * @param text
	 */
	public void inputAComment(String text){
		info("Input a text into input comment field");
		waitForAndGetElement(ELEMENT_COMMENT_INPUT_FIELD);
		type(ELEMENT_COMMENT_INPUT_FIELD,text,true);
		//return new DocumentPreview(driver, this.plfVersion);
	}
	
	/**
	 * press Enter to post a comment to Comment area
	 * 
	 * @param driver
	 * @param text
	 */
	public void postAComment(String text, int number) {
		for(int i=0;i<number;i++){
			inputAComment(text);
			info("Post a comment to Comment area");
			Actions action = new Actions(driver);
			action.sendKeys(Keys.ENTER).build().perform();
			//action.release();
		}
		//return new DocumentPreview(driver, this.plfVersion);

	}
	
	public void deleteAComment(){
			
		Actions action = new Actions(driver);
		WebElement el=waitForAndGetElement(ELEMENT_COMMENT_TEXT);
		action.moveToElement(el).perform();
		
		mouseClickByLocator(ELEMENT_COMMENT_DELETE_ICON);
		Alert alert = driver.switchTo().alert();
		alert.accept();
		
		waitForElementNotPresent(ELEMENT_COMMENT_TEXT);
		//return new DocumentPreview(driver, this.plfVersion);
	}
	
	public void mouseClickByLocator( String cssLocator ) {
	     String locator = cssLocator;
	     WebElement el = this.driver.findElement( By.cssSelector( locator ) );
	     Actions builder = new Actions(this.driver);
	     builder.moveToElement( el ).click( el );
	     builder.perform();
	}

	/******************************************** PREVIEW MODE ******************************************/
	/**
	 * Close Preview mode and Back to Activity stream by pressing ECS
	 * @return
	 * @param driver
	 */
	public void backASByPressECS(){
		pressESCKey();
		Utils.pause(2000);
		//return new ActivityStream(driver, this.plfVersion);	
	}
	
   /**
    * Close Preview mode and Back to Activity stream by clicking Cross (X) icon
    */
   public void backASByClickCrossIcon(){
	   info("Close preview mode by clicking on Cross (X) icon");
	   waitForAndGetElement(ELEMENT_PREVIEW_MODE_CROSS_ICON,3000,0);
	   click(ELEMENT_PREVIEW_MODE_CROSS_ICON);
	   Utils.pause(2000);
	  // return new ActivityStream(driver,this.plfVersion);	
   }
   
   /******************************************** NEW READER FORM ******************************************/
   /**
    * Check properties of shadow mask display
    */
   public void shadowMask(int wd_preview, int wd_br){
	   info("Check shadow Mask");
	        if (wd_preview>=wd_br)
	        	assert true;
	        else 
                assert false: ("The shadow mask doesn't occupie the whole area of the browser window.");
	 //  return new DocumentPreview(driver,this.plfVersion);
   }
   /**
    * Check actions that are shown on Reader form
    */
   public void actionsMenuList(){
	   info("Verify that toogle side bar is shown");
	   waitForAndGetElement(ELEMENT_TOOGLE_SIDE_BAR_ICON,3000,0);
	   info("Verify that Search icon is shown");
	   waitForAndGetElement(ELEMENT_SEARCH_ICON,2000,0);
	   info("Verify that previous page icon is shown");
	   waitForAndGetElement(ELEMENT_PREVIOUS_PAGE_ICON,2000,0);
	   info("Verify that next page icon is shown");
	   waitForAndGetElement(ELEMENT_NEXT_PAGE_ICON,2000,0);
	   info("Verify that editable filed is shown and show current page's number");
	   waitForAndGetElement(ELEMENT_INPUT_NUMBER_PAGE_FIELD,2000,0).getText().contains("1");
	   info("Verify that zoom in icon is shown");
	   waitForAndGetElement(ELEMENT_ZOOM_IN_ICON,2000,0);
	   info("Verify that zoom out icon is shown");
	   waitForAndGetElement(ELEMENT_ZOOM_OUT_ICON,2000,0);
	   info("Verify that zoom option is shown");
	   waitForAndGetElement(ELEMENT_ZOOM_OPTION_DROP_BOX,2000,0);
	   info("Verify that Presentation mode icon is shown");
	   waitForAndGetElement(ELEMENT_SWITCH_PRESENTATION_MODE_ICON,2000,0);
	   info("Verify that Tool option icon is shown");
	   waitForAndGetElement(ELEMENT_TOOL_OPTION_ICON,2000,0);
	   //return new DocumentPreview(driver,this.plfVersion);
   }
   /**
    * Check values of Zoom option list
    */
   public void checkValueOfZoomOption(){
	   info("Verify that first value is Automatic zoom option");
	   waitForAndGetElement(ELEMENT_ZOOM_AUTOMATIC,3000,0);
	   info("Verify that second value is Actual size option");
	   waitForAndGetElement(ELEMENT_ZOOM_ACTUAL_SIZE,2000,0);
	   info("Verify that third value is Page Fit option");
	   waitForAndGetElement(ELEMENT_ZOOM_PAGE_FIT,2000,0);
	   info("Verify that fourth value is Page width option");
	   waitForAndGetElement(ELEMENT_ZOOM_PAGE_WIDTH,2000,0);
	   info("Verify that fiveth value is 50% option");
	   waitForAndGetElement(ELEMENT_ZOOM_50_PERCENT,2000,0);
	   info("Verify that sixth value is 75% option");
	   waitForAndGetElement(ELEMENT_ZOOM_75_PERCENT,2000,0);
	   info("Verify that seventh value is 100% option");
	   waitForAndGetElement(ELEMENT_ZOOM_100_PERCENT,2000,0);
	   info("Verify that eighth value is 125% option");
	   waitForAndGetElement(ELEMENT_ZOOM_125_PERCENT,2000,0);
	   info("Verify that nineth value is 150% option");
	   waitForAndGetElement(ELEMENT_ZOOM_150_PERCENT,2000,0);
	   info("Verify that nineth value is 200% option");
	   waitForAndGetElement(ELEMENT_ZOOM_200_PERCENT,2000,0);
	  // return new DocumentPreview(driver,this.plfVersion);
   }
   
   /**
    * Check values of Tool option list
    */
   public void checkValueOfToolOption(){
	   info("Verify that first value is 'Download' option");
	   waitForAndGetElement(ELEMENT_TOOL_DOWNLOAD,3000,0);
	   info("Verify that second value is 'Print' option");
	   waitForAndGetElement(ELEMENT_TOOL_PRINT,2000,0);
	   info("Verify that third value is 'Go to first page' option");
	   waitForAndGetElement(ELEMENT_TOOL_GO_TO_FIRST_PAGE,2000,0);
	   info("Verify that fourth value is 'Go to last page' option");
	   waitForAndGetElement(ELEMENT_TOOL_GO_TO_LAST_PAGE,2000,0);
	   info("Verify that fiveth value is 'Rotate clockwise' option");
	   waitForAndGetElement(ELEMENT_TOOL_ROTATE_CLOCKWISE,2000,0);
	   info("Verify that sixth value is 'Rotate counter clockwise' option");
	   waitForAndGetElement(ELEMENT_TOOL_ROTATE_COUNTER_CLOCKWISE,2000,0);
	   //return new DocumentPreview(driver,this.plfVersion);
   }
   /**
    * Verify Dimention of Viewport with the page size of document
    * @param wd_viewer
    * @param h_viewer
    * @param wd_page
    * @param h_page
    */
   public void verifyDimensionOfViewPortWithPageSize(int wd_viewer, int h_viewer, int wd_page, int h_page){
	   if (wd_viewer> wd_page & (h_viewer>h_page || h_viewer<h_page)){
		   assert true;
		   info("The small page size is center of the viewport");
	   }else assert false:"The small page size is not center of the viewport";
	   
	  // return new DocumentPreview(driver,this.plfVersion);
   }
}

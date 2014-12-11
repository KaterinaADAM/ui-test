package org.exoplatform.selenium;

import static org.exoplatform.selenium.TestLogger.debug;
import static org.exoplatform.selenium.TestLogger.info;

import java.io.File;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.exoplatform.selenium.platform.ManageLogInOut;
import org.exoplatform.selenium.platform.ecms.ContextMenu;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class TestBase {

	public WebDriver driver;
	public WebDriver newDriver;
	public static String baseUrl;
	protected int DEFAULT_TIMEOUT = 30000; //milliseconds = 30 seconds
	protected int WAIT_INTERVAL = 1000; //milliseconds  
	public int loopCount = 0;	
	protected boolean ieFlag;	 
	protected boolean chromeFlag;

	protected String plfVersion = "";
	public final int ACTION_REPEAT = 5;
	public static boolean firstTimeLogin = false;
	public Actions action;

	/*========System Property====================*/
	protected Boolean isRandom;
	protected Boolean isUseFile;

	protected String jdbcDriver;
	protected String dbUrl;
	protected String user;
	protected String pass;

	protected String sqlWiki;
	protected String sqlAttach;
	protected String sqlUser;

	protected String defaultSheet;

	protected String userDataFilePath;
	protected String wikiRichTextFilePath;
	protected String attachmentFilePath;
	protected String videoLinksFilePath;
	protected String texboxFilePath;
	protected String wikiTemplateFilePath;
	protected String spaceVisibleFilePath;
	protected String spaceRegistrationFilePath;

	protected String siteExpDrivePath;
	protected String siteExpPathPath;
	protected String dataTestFilePath;
	protected String previewContentFilePath;
	protected String previewTextSearchPath;
	protected String previewMesgSearchPath;


	/*========Default System Property=============*/
	public final String DEFAULT_BASEURL="http://localhost:8080/portal";

	public final  Boolean DEFAULT_ISRANDOM = true;
	public final  Boolean DEFAULT_ISUSEFILE = true;

	public final  String DEFAULT_JDBCDRIVER = "com.mysql.jdbc.Driver";
	public final  String DEFAULT_DBURL = "jdbc:mysql://localhost:3306/selenium";
	public final  String DEFAULT_USERMYSQL = "root";
	public final  String DEFAULT_USERPASS = "exo";

	public final  String DEFAULT_SQLWIKI = "select * from wiki order by id asc";
	public final  String DEFAULT_SQLATTACHMENT = "select * from space order by id asc";
	public final  String DEFAULT_SQLUSER = "select type,username,password,email from user order by id asc";

	public final String DEFAULT_SHEET="sheet1";
	public final String DEFAULT_USERFILEURL="DataDriven/" + "user.xls";
	public final String DEFAULT_ATTACHMENTFILEURL="DataDriven/" + "attachment_file.xls";
	public final String DEFAULT_VIDEOLINKSFILEURL="DataDriven/" + "video_links_file.xls";
	public final String DEFAULT_TEXTBOXFILEURL="DataDriven/" + "textbox.xls";
	public final String DEFAULT_WIKITEMPLATEFILEURL="DataDriven/" + "wiki_template.xls";
	public final String DEFAULT_SPACEVISIBLEFILEURL="DataDriven/" + "space_visibility.xls";
	public final String DEFAULT_SPACEREGISTRATIONFILEURL="DataDriven/" + "space_registration.xls";
	public final String DEFAULT_WIKIRICHTEXTFILEURL="DataDriven/" + "wiki_richtext.xls";

	public final String DEFAULT_SITEEXPLORERDRIVE="DataDriven/" + "SE_drive.xls";
	public final String DEFAULT_SITEEXPLORERPATH="DataDriven/" + "SE_path.xls";
	public final String DEFAULT_DATATESTPATH="DataDriven/" + "DataTest_Path.xls";
	public final String DEFAULT_PREVIEWCONTENTFILEPATH="DataDriven/" + "preview_content_file.xls";
	public final String DEFAULT_PREVIEWMESSAGESEARCHPATH="DataDriven/" + "preview_message_search.xls";
	public final String DEFAULT_PREVIEWTEXTSEARCHPATH="DataDriven/" + "preview_text_search.xls";

	/*======= Welcome Screen (Term and Conditions) =====*/
	public final By ELEMENT_FIRSTNAME_ACCOUNT = By.name("firstNameAccount");
	public final By ELEMENT_LASTNAME_ACCOUNT = By.name("lastNameAccount");
	public final By ELEMENT_EMAIL_ACCOUNT = By.name("emailAccount");
	public final By ELEMENT_CONFIRM_PASS_ACCOUNT = By.name("confirmUserPasswordAccount");
	public final By ELEMENT_ROOT_PASS_ACCOUNT = By.name("adminPassword");
	public final By ELEMENT_ROOT_CONFIRM_PASS_ACCOUNT = By.name("confirmAdminPassword");
	public final By ELEMENT_AGREEMENT_CHECKBOX = By.xpath("//*[@id = 'agreement']");
	public final By ELEMENT_INPUT_USERNAME = By.name("username"); 
	public final By ELEMENT_CONTINUE_BUTTON = By.xpath("//button[text()='Continue' and @class='btn active']");
	public final By ELEMENT_START_BUTTON = By.xpath("//button[text()='Start']");
	public final By ELEMENT_SUBMIT_BUTTON = By.xpath("//*[text()='Submit']");
	public final By ELEMENT_INPUT_PASSWORD = By.name("password");
	public final By ELEMENT_ACCOUNT_NAME_LINK = By.xpath("//*[@id='UIUserPlatformToolBarPortlet']/a");
	public final By ELEMENT_PLF_INFORMATION = By.id("platformInfoDiv");

	public final String ELEMENT_TERM_CONDITION_BOX = "//div[@class='header' and text()='Terms and Conditions Agreement']/..";
	public final By ELEMENT_CONTINUE_BUTTON_DISABLE = By.xpath("//button[text()='Continue' and @class='btn inactive']");
	public final By ELEMENT_TERM_CONDITION_CONTENT = By.xpath("//div[@id='AccountSetup' and @class='content']");

	public final By ELEMENT_ACCOUNT_SETUP = By.xpath("//div[@class='header' and text()='Account Setup']");
	public final By ELEMENT_USER_ADMIN = By.id("adminFirstName");
	public final By ELEMENT_SKIP_BUTTON = By.xpath("//button[text()='Skip']");
	public final By ELEMENT_YOUR_ACCOUNT_LABEL = By.xpath("//h5[contains(text(), 'Create your account')]");
	public final By ELEMENT_ADMIN_PASS_LABEL = By.xpath("//h5[contains(text(), 'Admin Password')]");
	public final By ELEMENT_ACCOUNT_ERROR = By.xpath("//*[@class='accountSetupError']");

	public final String DATA_MESSAGE_CONFIRM_DELETE_COMMENT = "Are you sure you want to delete this comment?";
	/*======== End of Term and conditions =====*/	
	/**
	 * Get System Property
	 */
	public void getSystemProperty(){
		baseUrl = System.getProperty("baseUrl");

		jdbcDriver = System.getProperty("jdbcDriver");
		dbUrl = System.getProperty("dbUrl");
		user = System.getProperty("user");
		pass = System.getProperty("pass");
		sqlWiki = System.getProperty("sqlWiki");
		sqlAttach = System.getProperty("sqlAttach");
		sqlUser = System.getProperty("sqlUser");

		defaultSheet = System.getProperty("defaultSheet");

		userDataFilePath = System.getProperty("userDataFilePath");
		wikiRichTextFilePath = System.getProperty("wikiRichTextFilePath");
		attachmentFilePath = System.getProperty("attachmentFilePath");
		videoLinksFilePath = System.getProperty("videoLinksFilePath");
		texboxFilePath = System.getProperty("texboxFilePath");
		wikiTemplateFilePath = System.getProperty("wikiTemplateFilePath");
		spaceVisibleFilePath = System.getProperty("spaceVisibleFilePath");
		spaceRegistrationFilePath = System.getProperty("spaceRegistrationFilePath");

		siteExpDrivePath=System.getProperty("siteExpDrivePath");
		siteExpPathPath=System.getProperty("siteExpPathPath");
		dataTestFilePath = System.getProperty("dataTestPath");
		previewContentFilePath= System.getProperty("previewContentFilePath");
		previewTextSearchPath= System.getProperty("previewTextSearchPath");
		previewMesgSearchPath= System.getProperty("previewMesgSearchPath");

		if (baseUrl==null) baseUrl = DEFAULT_BASEURL;

		if (isRandom==null) isRandom = DEFAULT_ISRANDOM;
		if (isUseFile==null) isUseFile = DEFAULT_ISUSEFILE;

		if (jdbcDriver==null) jdbcDriver = DEFAULT_JDBCDRIVER;
		if (dbUrl==null) dbUrl = DEFAULT_DBURL;
		if (user==null) user = DEFAULT_USERMYSQL;
		if (pass==null) pass = DEFAULT_USERPASS;

		if (sqlWiki==null) sqlWiki = DEFAULT_SQLWIKI;
		if (sqlAttach==null) sqlAttach = DEFAULT_SQLATTACHMENT;
		if (sqlUser==null) sqlUser = DEFAULT_SQLUSER;

		if (defaultSheet==null) defaultSheet = DEFAULT_SHEET;

		if (userDataFilePath==null) userDataFilePath = DEFAULT_USERFILEURL;
		if (wikiRichTextFilePath==null) wikiRichTextFilePath = DEFAULT_WIKIRICHTEXTFILEURL;
		if (attachmentFilePath==null) attachmentFilePath = DEFAULT_ATTACHMENTFILEURL;
		if (videoLinksFilePath==null)videoLinksFilePath = DEFAULT_VIDEOLINKSFILEURL;
		if (wikiTemplateFilePath==null) wikiTemplateFilePath = DEFAULT_WIKITEMPLATEFILEURL;
		if (texboxFilePath==null) texboxFilePath = DEFAULT_TEXTBOXFILEURL;
		if (spaceVisibleFilePath==null) spaceVisibleFilePath = DEFAULT_SPACEVISIBLEFILEURL;
		if (spaceRegistrationFilePath==null) spaceRegistrationFilePath = DEFAULT_SPACEREGISTRATIONFILEURL;

		if (siteExpDrivePath==null) siteExpDrivePath = DEFAULT_SITEEXPLORERDRIVE;
		if (siteExpPathPath==null) siteExpPathPath = DEFAULT_SITEEXPLORERPATH;
		if (dataTestFilePath==null) dataTestFilePath = DEFAULT_DATATESTPATH;
		if (previewContentFilePath==null) previewContentFilePath = DEFAULT_PREVIEWCONTENTFILEPATH;
		if (previewTextSearchPath==null) previewTextSearchPath = DEFAULT_PREVIEWTEXTSEARCHPATH;
		if (previewMesgSearchPath==null) previewMesgSearchPath = DEFAULT_PREVIEWMESSAGESEARCHPATH;
		
		userDataFilePath = getAbsoluteFilePath(userDataFilePath);
		wikiRichTextFilePath = getAbsoluteFilePath(wikiRichTextFilePath);
		attachmentFilePath = getAbsoluteFilePath(attachmentFilePath);
		texboxFilePath = getAbsoluteFilePath(texboxFilePath);
		wikiTemplateFilePath = getAbsoluteFilePath(wikiTemplateFilePath);
		spaceVisibleFilePath = getAbsoluteFilePath(spaceVisibleFilePath);
		spaceRegistrationFilePath = getAbsoluteFilePath(spaceRegistrationFilePath);
		dataTestFilePath = getAbsoluteFilePath(dataTestFilePath);
		previewContentFilePath = getAbsoluteFilePath(previewContentFilePath);
		videoLinksFilePath = getAbsoluteFilePath(videoLinksFilePath);
		siteExpDrivePath = getAbsoluteFilePath(siteExpDrivePath);
		siteExpPathPath = getAbsoluteFilePath(siteExpPathPath);
		previewMesgSearchPath = getAbsoluteFilePath(previewMesgSearchPath);
		previewTextSearchPath = getAbsoluteFilePath(previewTextSearchPath);
	}

	public void initSeleniumTestWithOutTermAndCondition(Object... opParams){
		String browser = System.getProperty("browser");
		if("chrome".equals(browser)){
			driver = new ChromeDriver();
			chromeFlag = true;
		} else if ("iexplorer".equals(browser)){
			driver = new InternetExplorerDriver();
			ieFlag = true;
		} else {
			FirefoxProfile profile = new FirefoxProfile();
			profile.setPreference("plugins.hide_infobar_for_missing_plugin", true);
			profile.setPreference("dom.max_script_run_time", 0);
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			capabilities.setCapability(FirefoxDriver.PROFILE, profile);
			driver = new FirefoxDriver();
		}
		getSystemProperty();
		action = new Actions(driver);
	}
	
	/**
	 * This function returns a absolute path from a relative path
	 * @param relativeFilePath
	 * @return - FQA-2092: Run and check calendar sniff on IE and FF
	 */
	public String getAbsoluteFilePath(String relativeFilePath){
		String fs = File.separator;
		String curDir = System.getProperty("user.dir");
		String absolutePath = curDir + "/src/main/resources/" + relativeFilePath;
		absolutePath=absolutePath.replace("/", fs);
		return absolutePath;
	}

	public void initSeleniumTest(Object... opParams){
		initSeleniumTestWithOutTermAndCondition();
		info("Term and conditions");
		termsAndConditions(opParams);
		info("End of term and conditions");

		if(!firstTimeLogin){
			info("This is not the first time login");
			checkPLFVersion();
		}
		else{
			info("This is the first time login");
			driver.manage().window().maximize();
			driver.navigate().refresh();
			Utils.pause(2000);
			ManageLogInOut acc = new ManageLogInOut(this.driver);
			acc.signOut();
			firstTimeLogin=false;
			checkPLFVersion();
		}
	}

	/**
	 * Check term and conditions
	 * 
	 */
	public void termsAndConditions(Object... opParams){
		Boolean isCreateAccount = (Boolean)(opParams.length>0 ? opParams[0]:true);
		driver.get(baseUrl);
		info("Agreement page");
		if (waitForAndGetElement(ELEMENT_AGREEMENT_CHECKBOX, 3000, 0, 2) != null) {
			info("-- Checking the terms and conditions agreement... --");
			click(ELEMENT_AGREEMENT_CHECKBOX, 2);
			click(ELEMENT_CONTINUE_BUTTON);
			waitForTextNotPresent("terms and conditions agreement");

			info("-- Creating an Admin account: FQA... --");
			if(isCreateAccount==true)
				accountSetup();
			firstTimeLogin = true;
			info("-- Administrator account (FQA) has been created successfully... --");
		}else if (waitForAndGetElement(ELEMENT_ROOT_PASS_ACCOUNT, 3000, 0, 2) != null){
			info("-- Creating an Admin account: FQA... --");
			accountSetup();
			firstTimeLogin = true;
			info("-- Administrator account (FQA) has been created successfully... --");
		} 
		Utils.pause(3000);     
	}

	/**
	 * Verify plf version
	 */
	public void checkPLFVersion(){
		waitForTextNotPresent("terms and conditions agreement");
		try{
			info("Verify platform version");
			String des = driver.findElement(ELEMENT_PLF_INFORMATION).getText();
			if(des.contains("v4.0")){
				this.plfVersion = "4.0";
				info("Platform version 4.0.x");
			}
			else if(des.contains("v4.1")){
				this.plfVersion="4.1";
				info("Platform version 4.1.x");
			}
		}catch(Exception e){
			info("Unknown platform version. Set to default version 4.1.x.");
			this.plfVersion="4.1.x";
		}

	}

	/**
	 * Create new first account
	 */
	public void accountSetupWithoutGreeting(){
		type(ELEMENT_INPUT_USERNAME, "fqa", true);
		type(ELEMENT_FIRSTNAME_ACCOUNT, "FQA", true);
		type(ELEMENT_LASTNAME_ACCOUNT, "VN", true);
		type(ELEMENT_EMAIL_ACCOUNT, "fqa@exoplatform.com", true);	
		type(ELEMENT_INPUT_PASSWORD, "gtngtn", true);
		type(ELEMENT_CONFIRM_PASS_ACCOUNT, "gtngtn", true);	
		type(ELEMENT_ROOT_PASS_ACCOUNT, "gtngtn", true);
		type(ELEMENT_ROOT_CONFIRM_PASS_ACCOUNT, "gtngtn", true);
		click(ELEMENT_SUBMIT_BUTTON);
		waitForTextNotPresent("Create your account");
	}

	/**
	 * Account setup
	 */
	public void accountSetup(){
		accountSetupWithoutGreeting();
		click(ELEMENT_START_BUTTON);
		waitForAndGetElement(ELEMENT_ACCOUNT_NAME_LINK);
	}

	/**
	 * Get element
	 * @param locator
	 * @param opParams
	 * @return
	 */
	public WebElement getElement(Object locator, Object... opParams) {
		By by = locator instanceof By ? (By)locator : By.xpath(locator.toString());
		WebDriver wDriver = (WebDriver) (opParams.length > 0 ? opParams[0]: driver);	
		WebElement elem = null;
		try {
			elem = wDriver.findElement(by);
		} catch (NoSuchElementException e) {

		}
		return elem;
	}

	//return element only in case the element is displayed.
	public WebElement getDisplayedElement(Object locator, Object... opParams) {
		By by = locator instanceof By ? (By)locator : By.xpath(locator.toString());
		WebDriver wDriver = (WebDriver) (opParams.length > 0 ? opParams[0]: driver);	
		WebElement e = null;
		try {
			if(by != null)
				e = wDriver.findElement(by);
			if (e != null){
				if (isDisplay(by)) return e;
			}
		} catch (NoSuchElementException ex) {
		}catch(StaleElementReferenceException ex)
		{
			checkCycling(ex, 10);
			Utils.pause(WAIT_INTERVAL);
			getDisplayedElement(locator);
		}
		finally{
			loopCount=0;
		}
		return null;
	}

	public boolean isElementPresent(Object locator) {
		return getElement(locator) != null;
	}

	public boolean isElementNotPresent(Object locator) {
		return !isElementPresent(locator);
	}

	/**
	 * Get element
	 * @param locator
	 * 					locator of element
	 * @param opParams
	 * 					opPram[0]: timeout
	 * 					opPram[1]: 0,1
	 * 					0: No Assert
	 * 					1: Assert
	 * @return
	 */
	public WebElement waitForAndGetElement(Object locator, Object... opParams) {
		WebElement elem = null;
		int timeout = (Integer) (opParams.length>0 ? opParams[0] : DEFAULT_TIMEOUT);
		int isAssert = (Integer) (opParams.length > 1 ? opParams[1]: 1);
		int notDisplayE = (Integer) (opParams.length > 2 ? opParams[2]: 0);
		WebDriver wDriver = (WebDriver) (opParams.length > 3 ? opParams[3]: driver);	
		for (int tick = 0; tick < timeout/WAIT_INTERVAL; tick++) {
			if (notDisplayE == 2){
				elem = getElement(locator,wDriver);
			}else{
				elem = getDisplayedElement(locator,wDriver);
			}
			
			if (null != elem)
			    return elem;
			Utils.pause(WAIT_INTERVAL);
			
		}
		if (isAssert == 1)
			assert false : ("Timeout after " + timeout
					+ "ms waiting for element present: " + locator);
		info("cannot find element after " + timeout / 1000 + "s.");
		
		return null;
	}
	

	/**
	 * Get element
	 * @param locator
	 * 					locator of element
	 * @param opParams
	 * 					opPram[0]: timeout
	 * 					opPram[1]: 0,1
	 * 					0: No Assert
	 * 					1: Assert
	 * @return
	 */
	public WebElement waitForElementNotPresent(Object locator, int... opParams) {
		WebElement elem = null;
		int timeout = opParams.length > 0 ? opParams[0] : DEFAULT_TIMEOUT;
		int isAssert = opParams.length > 1 ? opParams[1]: 1;
		int notDisplayE = opParams.length > 2 ? opParams[2]: 0;

		for (int tick = 0; tick < timeout/WAIT_INTERVAL; tick++) {
			if (notDisplayE == 2){
				elem = getElement(locator);
				//elem = getDisplayedElement(locator);
			}else{
				elem = getDisplayedElement(locator);
			}
			if (null == elem) return null;
			Utils.pause(WAIT_INTERVAL);
		}

		if (isAssert == 1)
			assert false: ("Timeout after " + timeout + "ms waiting for element not present: " + locator);
		info("Element doesn't disappear after " + timeout/1000 + "s.");
		return elem;
	}

	/**
	 * 
	 * @param text
	 * @param opts
	 * @return
	 */
	public boolean isTextPresent(String text, int...opts) {
		int display = opts.length > 0 ? opts[0] : 1;
		Utils.pause(500);
		String allVisibleTexts = getText(By.xpath("//body"),display);
		return allVisibleTexts.contains(text);
	}

	public String getText(Object locator,int...opts) {
		WebElement element = null;
		int display = opts.length > 0 ? opts[0] : 1;
		try {
			element = waitForAndGetElement(locator,DEFAULT_TIMEOUT,1,display);
			return element.getText();
		} catch (StaleElementReferenceException e) {
			checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);
			Utils.pause(WAIT_INTERVAL);
			return getText(locator);
		} finally {
			loopCount = 0;
		}
	}

	public List<WebElement> getElements(String xpath) {
		try {
			return driver.findElements(By.xpath(xpath));
		} catch (StaleElementReferenceException e) {
			checkCycling(e, 5);
			Utils.pause(1000);
			return getElements(xpath);
		} finally {
			loopCount = 0;
		}
	}

	public boolean isTextNotPresent(String text) {
		return !isTextPresent(text);
	}

	public void dragAndDropToObject(Object sourceLocator, Object targetLocator) {
		info("--Drag and drop to object--");
		Actions action = new Actions(driver);
		try {
			WebElement source = waitForAndGetElement(sourceLocator);
			WebElement target = waitForAndGetElement(targetLocator);

			action.dragAndDrop(source, target).build().perform();

		} catch (StaleElementReferenceException e) {
			checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);
			Utils.pause(WAIT_INTERVAL);
			dragAndDropToObject(sourceLocator, targetLocator);
		}catch (UnhandledAlertException e) {
			try {
				Alert alert = driver.switchTo().alert();
				alert.accept();
				switchToParentWindow();
			} catch (NoAlertPresentException eNoAlert) {
			}
		}

		finally {
			loopCount = 0;
		}
		Utils.pause(1000);
	}

	public void click(Object locator, Object... opParams) {
		int notDisplay = (Integer) (opParams.length > 0 ? opParams[0]: 0);		
		Actions actions = new Actions(driver);
		try {
			WebElement element = waitForAndGetElement(locator, DEFAULT_TIMEOUT, 1, notDisplay);
			if(element.isEnabled())
				actions.click(element).perform();
			else {
				debug("Element is not enabled");
				click(locator, notDisplay);
			}
		} catch (StaleElementReferenceException e) {
			checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);
			Utils.pause(WAIT_INTERVAL);
			click(locator, notDisplay);
		} catch (ElementNotVisibleException e) {
			checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);
			Utils.pause(WAIT_INTERVAL);
			click(locator, notDisplay);
		} finally {
			loopCount = 0;
		}
		Utils.pause(500);
	}

	public void clearCache(){
		Actions actionObject = new Actions(driver);
		try{
			actionObject.sendKeys(Keys.CONTROL).sendKeys(Keys.F5).build().perform();
		} catch(WebDriverException e){	
			debug("Retrying clear cache...");
			actionObject.sendKeys(Keys.CONTROL).sendKeys(Keys.F5).build().perform();
		}
	}

	//Use this function to verify if a check-box is checked (using when creating a portal/publicMode)
	public void check(Object locator, int... opParams) {
		int notDisplayE = opParams.length > 0 ? opParams[0]: 0;
		Actions actions = new Actions(driver);
		try {
			WebElement element = waitForAndGetElement(locator, DEFAULT_TIMEOUT, 1, notDisplayE);

			if (!element.isSelected()) {
				actions.click(element).perform();
			} else {
				info("Element " + locator + " is already checked.");
			}
		} catch (StaleElementReferenceException e) {
			checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);
			Utils.pause(WAIT_INTERVAL);
			check(locator, opParams);
		} finally {
			loopCount = 0;
		}
	}

	public String getValue(Object locator) {
		try {
			return waitForAndGetElement(locator).getAttribute("value");
		} catch (StaleElementReferenceException e) {
			checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);			
			Utils.pause(WAIT_INTERVAL);
			return getValue(locator);
		} finally {
			loopCount = 0;
		}
	}

	public void mouseOver(Object locator, boolean safeToSERE, Object...opParams) {
		WebElement element;
		Actions actions = new Actions(driver);
		int notDisplay = (Integer) (opParams.length > 0 ? opParams[0]: 0);
		try {
			if (safeToSERE) {
				for (int i = 1; i < ACTION_REPEAT; i++){
					element = waitForAndGetElement(locator, 5000, 0, notDisplay);
					if (element == null){
						Utils.pause(WAIT_INTERVAL);
					} else {
						actions.moveToElement(element).perform();
						break;
					}
				}
			} else {
				element = waitForAndGetElement(locator);
				actions.moveToElement(element).perform();
			}
		} catch (StaleElementReferenceException e) {
			checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);
			Utils.pause(WAIT_INTERVAL);
			mouseOver(locator, safeToSERE);
		} finally {
			loopCount = 0;
		}
	}

	public void mouseOverAndClick(Object locator) {
		WebElement element;
		Actions actions = new Actions(driver);
		if (ieFlag) {
			element = getDisplayedElement(locator);
		} else {
			element = waitForAndGetElement(locator);
		}
		actions.moveToElement(element).click(element).build().perform();
	}

	public void waitForTextPresent(String text, int...opts) {
		int waitTime = opts.length > 0 ? opts[0] : DEFAULT_TIMEOUT;
		int display = opts.length > 1 ? opts[1] : 1;
		for (int second = 0;; second++) {
			if (second >= waitTime/WAIT_INTERVAL) {
				Assert.fail("Timeout at waitForTextPresent: " + text);
			}
			if (isTextPresent(text,display)) {
				break;
			}
			Utils.pause(WAIT_INTERVAL);
		}
	}

	public void waitForTextNotPresent(String text,int...wait) {
		int waitTime = wait.length > 0 ? wait[0] : DEFAULT_TIMEOUT;
		for (int second = 0;; second++) {
			if (second >= waitTime/WAIT_INTERVAL) {
				Assert.fail("Timeout at waitForTextNotPresent: " + text);
			}
			if (isTextNotPresent(text)) {
				break;
			}
			Utils.pause(WAIT_INTERVAL);
		}
	}

	public void waitForMessage(String message,int...wait) {
		int waitTime = wait.length > 0 ? wait[0] : DEFAULT_TIMEOUT;
		Utils.pause(500);
		waitForAndGetElement("//*[contains(text(),'"+message+"')]",waitTime);
	}

	public void type(Object locator, String value, boolean validate, Object...opParams) {	
		int notDisplay = (Integer) (opParams.length > 0 ? opParams[0]: 0);
		try {
			for (int loop = 1;; loop++) {
				if (loop >= ACTION_REPEAT) {
					Assert.fail("Timeout at type: " + value + " into " + locator);
				}
				WebElement element = waitForAndGetElement(locator, DEFAULT_TIMEOUT, 1, notDisplay);		
				if (element != null){
					if (validate) element.clear();
					element.click();
					element.sendKeys(value);
					if (!validate || value.equals(getValue(locator))) {
						break;
					}
				}
				info("Repeat action..." + loop + "time(s)");
				Utils.pause(WAIT_INTERVAL);
			}
		} catch (StaleElementReferenceException e) {
			checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);
			Utils.pause(WAIT_INTERVAL);
			type(locator, value, validate, opParams);
		} catch (ElementNotVisibleException e) {
			checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);
			Utils.pause(WAIT_INTERVAL);
			type(locator, value, validate, opParams);
		} finally {
			loopCount = 0;
		}
	}

	// Select option from combo box
	public void select(Object locator, String option, int...display) {
		int isDisplay = display.length > 0 ? display[0] : 1;
		try {
			for (int second = 0;; second++) {
				if (second >= DEFAULT_TIMEOUT/WAIT_INTERVAL) {
					Assert.fail("Timeout at select: " + option + " into " + locator);
				}
				Select select = new Select(waitForAndGetElement(locator,DEFAULT_TIMEOUT,1,isDisplay));
				select.selectByVisibleText(option);
				if (option.equals(select.getFirstSelectedOption().getText())) {
					break;
				}
				Utils.pause(WAIT_INTERVAL);
			}
		} catch (StaleElementReferenceException e) {
			checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);
			Utils.pause(WAIT_INTERVAL);
			select(locator, option);
		} finally {
			loopCount = 0;
		}
	}

	//un-check a checked-box
	public void uncheck(Object locator, int... opParams) {
		int notDisplayE = opParams.length > 0 ? opParams[0]: 0;
		Actions actions = new Actions(driver);
		try {
			WebElement element = waitForAndGetElement(locator, DEFAULT_TIMEOUT, 1, notDisplayE);

			if (element.isSelected()) {
				actions.click(element).perform();
			} else {
				info("Element " + locator + " is already unchecked.");
			}
		} catch (StaleElementReferenceException e) {
			checkCycling(e, 5);
			Utils.pause(1000);
			uncheck(locator, opParams);
		} finally {
			loopCount = 0;
		}
	}
	public ContextMenu rightClickOnElement(Object locator, int...opParams) {
		int display = opParams.length > 0 ? opParams[0]: 0;
		Actions actions = new Actions(driver);
		Utils.pause(500);
		try {
			WebElement element = waitForAndGetElement(locator,DEFAULT_TIMEOUT,1,display);
			actions.contextClick(element).perform();
		} catch (StaleElementReferenceException e) {
			checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);
			Utils.pause(WAIT_INTERVAL);
			rightClickOnElement(locator);
		} catch (ElementNotVisibleException e) {
			checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);
			Utils.pause(WAIT_INTERVAL);
			click(locator);
		} finally {
			loopCount = 0;
		}
		return new ContextMenu(driver,this.plfVersion);
	}


	public void checkCycling(Exception e, int loopCountAllowed) {
		info("Exception:" + e.getClass().getName());
		if (loopCount > loopCountAllowed) {
			Assert.fail("Cycled: " + e.getMessage());
		}
		info("Repeat... " + loopCount + "time(s)");
		loopCount++;
	}

	/**
	 * function to switch to parent windows
	 */
	public void switchToParentWindow (){
		try
		{
			Set<String> availableWindows = driver.getWindowHandles();
			String WindowIdParent= null;
			int counter = 1;
			for (String windowId : availableWindows) {
				if (counter == 1){
					WindowIdParent = windowId;
				}
				counter++;
			}
			driver.switchTo().window(WindowIdParent);
			Utils.pause(1000);
		}
		catch (WebDriverException e)
		{
			e.printStackTrace();
		}
	}

	public boolean isDisplay(Object locator) {
		boolean bool = false;
		WebElement e = getElement(locator);
		try{
			if (e!=null)
				bool = e.isDisplayed();
		}catch(StaleElementReferenceException ex)
		{
			checkCycling(ex, 10);
			Utils.pause(WAIT_INTERVAL);
			isDisplay(locator);
		}
		finally{
			loopCount=0;
		}
		return bool;
	}

	public enum Language{
		en, fr, vi, lo;
	}

	/**
	 * @param object
	 * @param classElement
	 * @return = true: if there is not scroll bar on element
	 *         = false: if there is scroll bar on element
	 */
	public boolean checkExitScrollBar(By object){
		/*WebElement element = waitForAndGetElement(object);
		 JavascriptExecutor jse = (JavascriptExecutor) driver;
		String scrollTopMax = String.valueOf(jse.executeScript("return arguments[0].scrollTopMax;", element));
		info("scrollTopMax: " + scrollTopMax);
		int scroTopMax = Integer.parseInt(scrollTopMax);
		return scroTopMax>0;*/
		WebElement element = waitForAndGetElement(object);
		String scrollHeight = String.valueOf(((JavascriptExecutor)driver).executeScript("return arguments[0].scrollHeight;", element));
		String offsetHeight = String.valueOf(((JavascriptExecutor)driver).executeScript("return arguments[0].offsetHeight;", element));
		info("scrollHeight: " + scrollHeight);
		info("offsetHeight: " + offsetHeight);
		int scroll = Integer.parseInt(scrollHeight);
		int offset = Integer.parseInt(offsetHeight);
		return scroll==offset;
	}
	
	/**
	 *  doubleClickOnElement
	 * @param locator
	 */
	public void doubleClickOnElement(Object locator) {
		Actions actions = new Actions(driver);
		try {
			WebElement element = waitForAndGetElement(locator);
			actions.doubleClick(element).perform();
		} catch (StaleElementReferenceException e) {
			checkCycling(e, 5);
			Utils.pause(1000);
			doubleClickOnElement(locator);
		} finally {
			loopCount = 0;
		}
	}
	
	/**
	 * get a list of random numbers author quynhpt
	 * 
	 * @return sb.toString();
	 */
	public String getRandomNumber() {
		char[] chars = "0123456789".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 6; i++) {
			char c = chars[random.nextInt(chars.length)];
			sb.append(c);
		}
		return sb.toString();
	}
    /**
     * Press ESC key
     */
	public void pressESCKey(){
		info("press ESC key");
		Actions action = new Actions (driver);
		action.sendKeys(Keys.ESCAPE).build().perform();
		action.release();
	}
	/**
	 * Press Enter key
	 */
	public void pressEnterKey(){
		info("press Enter key");
		Actions action = new Actions (driver);
		action.sendKeys(Keys.ENTER).build().perform();
		action.release();
	}
	
	/**
	 *This function will try to get an element. if after timeout, the element is not found.
	 *The function will refresh the page and find the element again.
	 * @param element
	 */
	public void waitElementAndTryGetElement(Object element){
		info("-- Starting finding element --");
		Utils.pause(500);
		for(int repeat=0;; repeat ++){
			if (repeat > 1){
				if(waitForAndGetElement(element,3000,0)!=null);
				break;
			}
			if (waitForAndGetElement(element, 5000, 0) != null){
				info("Element "+element+" is displayed");
				break;
			}
			info("Retry...[" + repeat + "]");
			this.driver.navigate().refresh();
		}
		Utils.pause(2000);
		info("The elemnt is shown successfully");
	}
}
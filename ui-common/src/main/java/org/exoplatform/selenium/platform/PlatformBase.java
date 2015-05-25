package org.exoplatform.selenium.platform;

import java.util.Iterator;
import java.util.Set;

import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.TestBase;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.objectdatabase.user.UserDatabase;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import static org.exoplatform.selenium.TestLogger.info;

public class PlatformBase extends TestBase {

	public static String DATA_USER1 = "john";
	public static String DATA_USER2 = "mary";
	public static String DATA_USER3 = "james";
	public static String DATA_USER4 = "demo";
	public static String USER_ROOT = "root";
	public static String PASS_ROOT = "gtngtn";
	public static String DATA_PASS = "gtn";
	public static String DATA_NAME_USER1 = "John Smith";
	public static String DATA_NAME_USER2 = "Mary Williams";
	public static String DATA_NAME_USER3 = "James Davis";
	public static String DATA_NAME_USER4 = "Jack Miller";
	public static String DATA_NAME_ROOT = "Root Root";
	
	//Gmail
	public final String GMAIL_URL = "https://mail.google.com";
	public final String EMAIL_ADDRESS1 = "exomailtest01@gmail.com";
	public final String EMAIL_ADDRESS2 = "fqaexovn@gmail.com";
	public final String EMAIL_PASS = "exoadmin";
	public final String ELEMENT_MAIL_SUBJECT = ".//span[contains(.,'${subject}')]";
	public final By ELEMENT_DELETE_MAIL = By.xpath("//*[@id=':ro']/div[2]//*[@class='ar9 T-I-J3 J-J5-Ji']");
	public final By ELEMENT_DELETE_MAIL_2 = By.xpath(".//*[@class='iH']//*[@data-tooltip='Delete']//*[@class='ar9 T-I-J3 J-J5-Ji']");
	public final By ELEMENT_GMAIL_INBOX = By.xpath("//a[contains(@title, 'Inbox')]");
	public final By ELEMENT_MAIL_CONTENT = By.xpath("//*[contains(@class, 'adP adO')]/div");
	public final By ELEMENT_GMAIL_USERNAME = By.id("Email");
	public final By ELEMENT_GMAIL_NEXT_BTN=By.id("next");
	public final By ELEMENT_GMAIL_PASS = By.id("Passwd");
	public final By ELEMENT_GMAIL_SIGN_IN = By.id("signIn");
	public final String ELEMENT_GMAIL_TITLE = "//td/div[@class='xS']//div[@class='xT']//span/b[contains(text(),'{$title}')]";
	public final By ELEMENT_GMAIL_COMPOSE = By.xpath("//div[contains(text(),'COMPOSE')]");
	public final By ELEMENT_GMAIL_SHOW_DETAIL = By.xpath("//img[@aria-label='Show details']");
	public final String ELEMENT_GMAIL_TO_FIELD = "//td/span[text()='to:']/../..//span[text()='${to}']";
	public final By ELEMENT_GMAIL_SIGNIN_DIFFERENT_ACC = By.cssSelector("a[id='account-chooser-link']");
	public final By ELEMENT_GMAIL_ADD_ACCOUNT = By.cssSelector("a[id='account-chooser-add-account']");
	public final By ELEMENT_FIRST_MAIL = By.xpath("//tr[1]//span[contains(text(),'Hi')]");
	public final String ELEMENT_GMAIL_CONTENT = ".//span[contains(.,'\"${title}\" page was modified')]";
	public final String ELEMENT_GMAIL_NEWUSER = ".//span[contains(.,'${title} ${title} has joined eXo')]";
	public final By ELEMENT_GMAIL_SIGN_IN_LINK = By.xpath("//a[@id='gmail-sign-in' and contains(text(),'Sign in')]");

	public ManageAlert alert = new ManageAlert(driver);
	
	//page navigation
	public By ELEMENT_NEXT_PAGE=By.xpath("//*[@class='uiIconNextArrow']");
	public By ELEMENT_PREVIOUS_PAGE=By.xpath("//*[@class='uiIconPrevArrow']");
	public By ELEMENT_TOTAL_PAGE=By.xpath("//*[@class='pagesTotalNumber']");
	public By ELEMENT_CURRENT_PAGE=By.xpath("//*[@class='active']/*[contains(@href,'objectId') or contains(@href,'javascript')]");
	public String ELEMENT_ANY_PAGE="//*[contains(@href,'ShowPage') and text()='$page']";
	public String ELEMENT_PAGINATOR_PAGE_LINK = "//*[@class='pagination uiPageIterator clearfix']//a[contains(Text(),'${number}')]";
	
	//frame
	public final By ELEMENT_FILEFORM_BLANK_CONTENT = By.xpath("//iframe[@class='cke_wysiwyg_frame cke_reset']");
	public final By ELEMENT_FILEFORM_BLANK_NAME = By.id("name");
	//Email notification
	public final By ELEMENT_GMAIL_PREVIOUS_EMAIL = By.xpath(".//*[@class='gE hI']");
	public final String ELEMENT_GMAIL_CONTENT_LINK_WIKI = ".//a[contains(@href,'${page}')]";
		
	/**
	 * Available option
	 */
	public enum selectInvitationOption{
		ALWAYS, NEVER, ASK
	}


	/**
	 * Arrow option
	 */
	public enum selectArrowOption{
		NEXT, PREVIOUS, NOW
	}

	/****************************Method*************************************/
	/**
	 * get default user pass from data driven
	 * @param userDataFile
	 * @param userSheet
	 * @param opParams
	 * @throws Exception
	 */
	public static void getDefaultUserPass(String userDataFile, String userSheet, Object... opParams) throws Exception{
		info("Get deault user pass from data driven");
		UserDatabase userData = new UserDatabase();
		userData.setUserData(userDataFile,userSheet,opParams);
		DATA_USER1 = userData.userName.get(0);
		DATA_PASS = userData.password.get(0);
		DATA_NAME_USER1 =userData.fullName.get(0);
		DATA_USER2 = userData.userName.get(1);
		DATA_NAME_USER2 =userData.fullName.get(1);
		USER_ROOT = userData.userName.get(4);
		PASS_ROOT = userData.password.get(4);
		DATA_USER3 = userData.userName.get(2);
		DATA_NAME_USER3 =userData.fullName.get(2);
		DATA_USER4 = userData.userName.get(3);
		DATA_NAME_USER4 =userData.fullName.get(3);
	}

	/**
	 * Type a text to a Frame using for CKEDITOR
	 * By QuynhPT
	 * @param frameLocator
	 * @param content
	 */
	public void inputFrame(By frameLocator,String content){
		info("Finding the frameLocator:"+frameLocator);
		WebElement e = waitForAndGetElement(frameLocator,DEFAULT_TIMEOUT,1,2);
		info("Switch to the frame:"+frameLocator);
		driver.switchTo().frame(e);
		WebElement inputsummary = driver.switchTo().activeElement();
		info("focus on the text area");
		inputsummary.click();
		info("Input the content:"+content);
		inputsummary.clear();
		inputsummary.sendKeys(content);
		info("Back to parent window");
		switchToParentWindow();
	}
	
	/**
	 * Select option from combo box
	 * @param locator
	 * @param option
	 */
	public void selectOption(Object locator, String option) {
		try {
			for (int second = 0;; second++) {
				if (second >= DEFAULT_TIMEOUT / WAIT_INTERVAL) {
					Assert.fail("Timeout at select: " + option + " into "
							+ locator);
				}
			
				Select select = new Select(waitForAndGetElement(locator));
				select.selectByValue(option);
				if (option.equals(select.getFirstSelectedOption().getAttribute(
						"value"))) {
					break;
				}
				Utils.pause(WAIT_INTERVAL);
			}
		} catch (StaleElementReferenceException e) {
			checkCycling(e, DEFAULT_TIMEOUT / WAIT_INTERVAL);
			Utils.pause(WAIT_INTERVAL);
			select(locator, option);
		} finally {
			loopCount = 0;
		}
	}

	/**
	 * Add by @author vuna2
	 * <li> Switch to a new browser/ Popup window</li> 
	 */
	public void switchToNewWindow(){
		Set<String> windowids = driver.getWindowHandles(); 
		Iterator<String> iter= windowids.iterator();
		while(iter.hasNext()) {
			String windowHandle = iter.next(); 
			driver.switchTo().window(windowHandle);
			info("Switch to new windown successfully");
		} 
	}
	
	/**
	 * Switch to new browser window
	 * @param user
	 * @param pass
	 */
	public void switchToNewBrowserWindow(String user, String pass){
		ManageLogInOut magAcc = new ManageLogInOut(driver);
		magAcc = new ManageLogInOut(driver);

		this.openNewBrowser();
		if (user != null){
			if (isElementNotPresent(ELEMENT_INPUT_USERNAME)){
				magAcc.signOut();
			}else{
				info("-- User.logIn: " + user);
			}
			magAcc.signIn(user, pass);
			Utils.pause(1000);
		}
	}
	
	/**
	 * Add by @author vuna2
	 * Open a new browser by Javascript
	 */
	public void openNewBrowser(){
		//Open new browser by Javascript
		((JavascriptExecutor) driver).executeScript("window.open()");
		for(String winHandle : driver.getWindowHandles()){
			driver.switchTo().window(winHandle);
		}
		driver.manage().window().maximize();
		driver.navigate().refresh();
		driver.navigate().to(baseUrl);
	}
	
	/**
	 * function: switch between windows using title windows
	 * @param windowTitle
	 */
	 public void switchBetweenWindowsUsingTitle(String windowTitle) {
         Set<String> windows = driver.getWindowHandles();
         for (String window : windows) {
             driver.switchTo().window(window);
             if (driver.getTitle().contains(windowTitle)) {
                 return;
             }
         }
     }
	
	/**
	 * Go to gmail and login
	 * @param email
	 * @param pass
	 */
	public void goToMail(String email, String pass){	
		((JavascriptExecutor) driver).executeScript("window.open()");
		for(String winHandle : driver.getWindowHandles()){
			driver.switchTo().window(winHandle);
		}
		info("Go to gmail");
		driver.navigate().to(GMAIL_URL);
		driver.manage().window().maximize();

		//login to mail
		if(waitForAndGetElement(ELEMENT_GMAIL_USERNAME, 5000,0) == null){
			if (waitForAndGetElement(ELEMENT_GMAIL_SIGN_IN_LINK,3000,0) != null)
				click(ELEMENT_GMAIL_SIGN_IN_LINK); 
			else{
				click(ELEMENT_GMAIL_SIGNIN_DIFFERENT_ACC);
				click(ELEMENT_GMAIL_ADD_ACCOUNT);
			}
		}
		type(ELEMENT_GMAIL_USERNAME, email, true);
		click(ELEMENT_GMAIL_NEXT_BTN);
		Utils.pause(1000);
		type(ELEMENT_GMAIL_PASS, pass, true);
		click(ELEMENT_GMAIL_SIGN_IN);
		clearCache();
		Utils.pause(2000);
		click(ELEMENT_GMAIL_INBOX);
		Utils.pause(2000);
	}
	
	/**
	 * function: check content of mail then delete mail
	 * @param mail element title of mail
	 * @param content mail content
	 */
	public void checkAndDeleteMail(By mail, String content){
		info("Check and delete mail");
		waitForAndGetElement(mail,300000);
		click(mail);	
		if(waitForAndGetElement(ELEMENT_GMAIL_CONTENT.replace("${content}",content),20000,0) == null )
			click(ELEMENT_FIRST_MAIL);
		assert waitForAndGetElement(ELEMENT_MAIL_CONTENT).getText().contains(content);
		info("Found notify mail");

		info("delete mail");
		if (waitForAndGetElement(ELEMENT_DELETE_MAIL_2, 5000, 0) == null){
			click(ELEMENT_DELETE_MAIL);
			info("Delete 1");
		}else {
			click(ELEMENT_DELETE_MAIL_2);
			info("Delete 2");
		}
		waitForElementNotPresent(mail);
		Utils.pause(1000);
	}
	
	/**
	 * function: check content of mail then delete mail
	 * @param title title of the page
	 * @param  opParams if true check it's present, false check if it's not present
	 */
	public void checkEmailNotification(String title,Object... opParams){
		info("Check and delete mail");
		Boolean checkOrNo = (Boolean)(opParams.length > 0 ? opParams[0]: true);
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		  for(String windowHandle  : driver.getWindowHandles()){
			     driver.switchTo().window(windowHandle);
			     info("driver.title:"+driver.getTitle());
		}
		if (opParams.length > 0) {
			if (checkOrNo == true)
				waitForAndGetElement(ELEMENT_GMAIL_CONTENT.replace("${title}",title),30000,1);
            else 
            	waitForElementNotPresent(ELEMENT_GMAIL_CONTENT.replace("${title}",title),30000,1);
		}
		if (opParams.length > 1)
			driver.close();
	}
	
	
	/**
	 * User pageinator
	 * @param locator
	 * @param exceptionMessage
	 */
	public void usePaginator(Object locator, String exceptionMessage) {
		String page1 = ELEMENT_PAGINATOR_PAGE_LINK.replace("${number}", "1");

		if (waitForAndGetElement(page1, 5000, 0) != null)
			click(page1);
		Utils.pause(500);
		int totalPages = 0;
		if (waitForAndGetElement(ELEMENT_TOTAL_PAGE, 3000, 0) != null){
			totalPages = isElementPresent(ELEMENT_TOTAL_PAGE) ? Integer.valueOf(getText(ELEMENT_TOTAL_PAGE)) : 1;
		}
		info("-- The total pages is: " + totalPages);
		int i = 1;
		while (isElementNotPresent(locator)) {
			if (i == totalPages) {
				info(exceptionMessage);
				break;
			}
			if (waitForAndGetElement(ELEMENT_NEXT_PAGE, 3000, 0) != null){
				click(ELEMENT_NEXT_PAGE);
			}
			Utils.pause(500);
		}
	}
}

package org.exoplatform.selenium.platform;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.Dialog;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;


import static org.exoplatform.selenium.TestLogger.info;

public class ManageLogInOut extends PlatformBase {
	
	Dialog dialog;
	Button button;
	ManageAlert magAlert;
	NavigationToolbar navBar;
	/*
	 * Log in Form
	 */
	public final By ELEMENT_INPUT_USERNAME = By.xpath(".//*[@id='username']"); 
	public final By ELEMENT_INPUT_PASSWORD = By.xpath(".//*[@class='password']");
	public final By ELEMENT_LOGIN_ACME_LINK = By.xpath("//*[@id='AcmeWebSiteLogInLogOut']");
	public final By ELEMENT_LOGIN_CONTAINER = By.className("loginContainer");
	public final String ELEMENT_LOGIN_HEADER_LABEL = "//div[@class='loginHeader introBox']//div[@class='userLoginIcon' and text()='Connect to your account']";
	public final String MESSAGE_LOGIN_FAILED = "//*[contains(text(),'Sign in failed. Wrong username or password.')]";
	public final String ELEMENT_REMEMBER_MY_LOGIN_YES = "//label[@class='iPhoneCheckLabelOn']//span[text()='Yes']";

	//[Create a New Account] Screen (Public Mode)
	public final By ELEMENT_SUBSCRIBE_BUTTON = By.xpath("//button[text()='Subscribe']");
	public final By ELEMENT_RESET_BUTTON = By.xpath(".//*[@id='UIRegisterForm']/div[2]/div/div/a[2]");
	public final By ELEMENT_REGISTER_LINK = By.xpath("//b[contains(text(),'Register')]");
	public final By ELEMENT_INPUT_CONFIRM_PASSWORD_PUBLIC_MODE = By.id("confirmPassword");
	public final By ELEMENT_INPUT_EMAIL_PUBLIC_MODE = By.id("emailAddress");
	public final By ELEMENT_REGISTER_ACCOUNT_LINK = By.xpath("//b[contains(text(),'Register')]");

	public final String MESSAGE_SUCCESSFULLY_REGISTERED_ACCOUNT = "You have successfully registered a new account!";
	public final By MESSAGE_SUCCESSFULLY_REGISTERED_ACCOUNT_FRENCH = By.xpath("//*[contains(text(),'Vous venez de créer un nouveau compte.')]");
	public final String MESSAGE_DUPLICATE_ACCOUNT = "This username already exists, please enter another one.";
	public final String MESSAGE_ALERT_PASSWORD = "Password and Confirm Password must be the same.";
	public final String MESSAGE_INVALID_EMAIL_ADDRESS = "Your email address is invalid. Please enter another one.";

	
	public ManageLogInOut(WebDriver dr,String...plfVersion){
		driver = dr;
		this.plfVersion = plfVersion.length>0?plfVersion[0]:"4.0";
		navBar = new NavigationToolbar(dr, plfVersion);
	}
	
	//Sign-in function for eXoGTN
	public void signIn(String username, String password,Boolean...opParams) {
		Boolean verify = opParams.length > 0 ? opParams[0]: true;
		Boolean maxWin = opParams.length > 1 ? opParams[1]: true;
		if(maxWin){
			driver.manage().window().maximize();
			driver.navigate().refresh();
			Utils.pause(2000);
		}
		if (firstTimeLogin){
			signOut();
			firstTimeLogin = false;
		}
		info("--Sign in as " + username + "--");
		Utils.pause(1000);
		type(ELEMENT_INPUT_USERNAME, username, true);
		type(ELEMENT_INPUT_PASSWORD, password, true);
		click(ELEMENT_SIGN_IN_BUTTON);
		/*if(verify)
			waitForElementNotPresent(ELEMENT_SIGN_IN_BUTTON);*/
		//Utils.pause(2000);
		//return new HomePagePlatform(driver);
	}

	/** Login to acme portal
	 * @author hela
	 * @param username
	 * @param password
	 */
	public void signInAcme(String username, String password) {
		info("--Sign in as " + username + "--");
		click(ELEMENT_LOGIN_ACME_LINK);
		Utils.pause(1000);
		type(ELEMENT_INPUT_USERNAME, username, true);
		type(ELEMENT_INPUT_PASSWORD, password, true);
		click(ELEMENT_ACME_SIGN_IN_BUTTON);
		waitForElementNotPresent(ELEMENT_ACME_SIGN_IN_BUTTON);
	}

	//Sign-out for eXoGTN
	public ManageLogInOut signOut(){
		info("Sign out");
		for(int repeat=0;; repeat ++){
			if (repeat > 1){
				mouseOverAndClick(ELEMENT_ACCOUNT_NAME_LINK);
				break;
			}
			click(ELEMENT_ACCOUNT_NAME_LINK);
			if (waitForAndGetElement(navBar.ELEMENT_SIGN_OUT_LINK, 5000, 0) != null){
				info("Element " + navBar.ELEMENT_SIGN_OUT_LINK + "... is displayed");
				break;
			}
			info("Retry...[" + repeat + "]");
		}
		click(navBar.ELEMENT_SIGN_OUT_LINK);
		Utils.pause(1000);
		if ( ExpectedConditions.alertIsPresent() != null ){
			magAlert = new ManageAlert(driver);
			magAlert.acceptAlert();
		}
		return new ManageLogInOut(driver, this.plfVersion);
	}

	/**
	 * Define a type of user 
	 * Root
	 * John Smith: administrator
	 * James Davis: author
	 * Jack Miller: developer
	 * Mary Williams: publisher 
	 */
	public enum userType {
		ROOT, ADMIN, AUTHOR, DEVELOPER, PUBLISHER, NEW_USER;
	}

	/**
	 * @param user: type: Root, Admin, Author, Developer or Publisher
	 */
	public void userSignIn(userType user){
		if (isElementNotPresent(ELEMENT_INPUT_USERNAME)){
			signOut();
		}else{
			info("-- User.logIn: " + user);
		}
		switch (user) {
		case ROOT:
			signIn(USER_ROOT, PASS_ROOT);
			break;
		case ADMIN:
			signIn(DATA_USER1, DATA_PASS);
			break;	
		case AUTHOR:
			signIn(DATA_USER3, DATA_PASS);
			break;
		case DEVELOPER:
			signIn(DATA_USER4, DATA_PASS);
			break;
		case PUBLISHER:
			signIn(DATA_USER2, DATA_PASS);
			break;
		default:
			break;
		}	
	}
}

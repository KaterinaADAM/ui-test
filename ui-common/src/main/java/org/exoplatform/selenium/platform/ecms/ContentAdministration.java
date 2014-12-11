package org.exoplatform.selenium.platform.ecms;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.PlatformBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContentAdministration extends PlatformBase{

	public ContentAdministration(WebDriver dr, String... plfVersion) {
		this.driver=dr;
		this.plfVersion = plfVersion.length>0?plfVersion[0]:"4.0";
	}
	//Explorer Tab
	public final By ELEMENT_EXPLORER_TAB = By.xpath("//*[text()='Explorer']");
	public final By ELEMENT_DRIVES_TAB = By.className("uiIconEcmsDriveManager");
	public final By ELEMENT_MANAGE_VIEW = By.xpath("//a[contains(text(),'Manage View')]");
	public final By ELEMENT_MANAGEMENT_VIEW = By.className("uiIconEcmsViewManager");
	
	public final String ELEMENT_VIEW_MODE_LINK = "//*[ @data-original-title='${viewName}']";

	/**
	 * Open Manage View area
	 * @return
	 */
	public ManageView goToManageViews(){
		if (waitForAndGetElement(ELEMENT_MANAGEMENT_VIEW, 5000, 0) != null) {
			click(ELEMENT_MANAGEMENT_VIEW);
		} else {
			click(ELEMENT_EXPLORER_TAB);
			click(ELEMENT_MANAGEMENT_VIEW);
		}
		Utils.pause(500);
		return new ManageView(driver,this.plfVersion);
	}

	/**
	 *  Function to go to Manage Driver from main screen
	 * @return ManageDrive
	 */
	public ManageDrive goToManageDrive() {
		info("Go to Manage drive");
		if (waitForAndGetElement(ELEMENT_DRIVES_TAB, 5000, 0) == null) {
			click(ELEMENT_EXPLORER_TAB);
		}
		click(ELEMENT_DRIVES_TAB);
		Utils.pause(500);
		return new ManageDrive(driver);
	}
}

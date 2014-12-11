package org.exoplatform.selenium.platform.ecms;

import org.exoplatform.selenium.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.exoplatform.selenium.TestLogger.info;

public class ManageView extends ContentAdministration{

	public ManageView(WebDriver dr, String plfVersion) {
		super(dr, plfVersion);
	}

	
	public final By ELEMENT_MANAGE_VIEW = By.xpath("//a[contains(text(),'Manage View')]");
	public final By ELEMENT_MANAGEMENT_VIEW = By.className("uiIconEcmsViewManager");
	public final String ELEMENT_EDIT_VIEW = "//*[@data-original-title='${viewName}']/../..//*[@data-original-title='Edit']"; // *[@id='UIViewList']
	public final String ELEMENT_DELETE_VIEW = "//*[@data-original-title='${viewName}']/../..//*[@data-original-title='Delete']";

	public final By ELEMENT_ACTION_TAB = By.xpath("//*[contains(@class, 'popup')]//*[text()='Action']");
	public final String ELEMENT_ACTION_TAB_NAME = "//*[contains(@class, 'popup')]//*[text()='${tabName}']";
	public final String ELEMENT_EDIT_ACTION_ICON = "//*[contains(@class, 'popup')]//*[text()='${tabName}']/../..//*[@data-original-title='Edit']";
	public final By ELEMENT_SAVE_BUTTON_EDIT_VIEW_TAB = By.xpath("//*[text()='Add/Edit Tab']/.. /..//*[text()='Save']");
	
	/**
	 * Add an action to a view
	 * 
	 * @param viewAction
	 */
	public void addMoreActionToAView(String viewAction, String... viewType) {
		String view = viewType.length > 0 ? viewType[0] : "Web";
		String tab = viewType.length > 1 ? viewType[1] : "Authoring";

		goToManageViews();
		click(ELEMENT_EDIT_VIEW.replace("${viewName}", view));
		info("Update view " + view);
		click(ELEMENT_ACTION_TAB);
		click(ELEMENT_EDIT_ACTION_ICON.replace("${tabName}", tab));
		selectCheckBoxList(viewAction);
		click(ELEMENT_SAVE_BUTTON_EDIT_VIEW_TAB);
		button.save();
		Utils.pause(1000);
	}
}

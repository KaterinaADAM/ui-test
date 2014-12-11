package org.exoplatform.selenium.platform.ecms;

import org.exoplatform.selenium.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.exoplatform.selenium.TestLogger.info;

/**
 * 
 * By quynhpt
 * Date: 09/12/2014
 */
public class ManageTemplate extends ActionBar {

	public ManageTemplate(WebDriver dr, String plfVersion) {
		super(dr, plfVersion);
	}

	//Webcontent template
    public final By ELEMENT_WEBCONTENT_SUMMARY_FRAME = By.xpath("//td[contains(@id,'cke_contents_htmlData')]/iframe");
	public final By ELEMENT_WEBCONTENT_SUMMARY_FRAME_41 = By.xpath("//*[@id='cke_exo:summary']//iframe");
	public final By ELEMENT_WEBCONTENT_ADVANCE_TAB = By.xpath("//*[contains(text(),'Advanced')]");
	public final By ELEMENT_WEBCONTENT_CSS_TEXTAREA = By.xpath("//textarea[contains(@id,'ContentCSS')]");
	public final By ELEMENT_WEBCONTENT_JS_TEXTAREA = By.xpath("//textarea[contains(@id,'ContentJS')]");
	public final By ELEMENT_WEBCONTENT_LINK = By.xpath("//*[@class='templateLabel']//*[text()='Web Content']");
	public final String ELEMENT_WEBCONTENT_CONTENT_NAME = ".//*[@ data-original-title='${nameContent}']";
	public final String ELEMENT_WEBCONTENT_CONTENT_NAME_DOCUMENT_VIEW=".//*[@id='UITabContent']//*[contains(text(),'${content}')]";
	public final By ELEMENT_WEBCONTENT_NAME_TEXTBOX = By.id("name");	
	public final By ELEMENT_WEBCONTENT_CONTENT_FRAME = By.xpath("//td[contains(@id,'cke_contents_htmlData')]/iframe");
	public final By ELEMENT_WEBCONTENT_CONTENT_FRAME_41 = By.xpath("//*[@class='cke_contents cke_reset']/iframe");
	public final By ELEMENT_WEBCONTENT_ADD_CONTENT_LINK = By.xpath("//*[@title='Insert Content Link']");
	public final By ELEMENT_WEBCONTENT_ILLUSTRATION_TAB = By.xpath("//*[contains(text(),'Illustration')]");
	public final By ELEMENT_WEBCONTENT_UPLOAD_FRAME = By.xpath("//*[contains(@name,'uploadIFrame')]");
	
	//Content template
	public final By ELEMENT_EDIT_NODE_CHECKBOX = By.id("set_property");
	public final By ELEMENT_REMOVE_NODE_CHECKBOX = By.id("remove");
	public final By ELEMENT_UPLOAD_NAME = By.name("file");
	public final By ELEMENT_UPLOAD_FRAME_EDIT = By.xpath("//iframe[contains(@id,'uploadFrame')]");
	public final By ELEMENT_UPLOAD_REMOVE = By.xpath("//i[@class='uiIconDelete uiIconLightGray']");
	public final By ELEMENT_PIC_FILE_REMOVE = By.xpath("//img[@class='ActionIcon Remove16x16Icon']");
	public final By ELEMENT_PIC_IMGWIDTH = By.id("imageWidth");
	public final By ELEMENT_PIC_IMGHEIGHT = By.id("imageHeight");
	public final By ELEMENT_PIC_LANG = By.name("content-lang");
	public final By ELEMENT_PIC_CONT = By.xpath("//iframe[contains(@title,'Rich text editor, htmlData')]");
	
	//CSS template
	public final By ELEMENT_CSS_FILE_LINK = By.xpath("//*[@class='templateLabel']//*[text()='CSS File']");
	public final By ELEMENT_CSS_NAME = By.id("name");
	public final By ELEMENT_CSS_ACTIVE = By.name("activeCSS");
	public final By ELEMENT_CSS_PRIORITY = By.id("CSSpriority");
	public final By ELEMENT_CSS_DATA = By.id("contentHtml");
	
	/**
	 *  Add new Free layout webcontent
	 * @param name
	 * @param cont
	 * @param img
	 * @param sum
	 * @param css
	 * @param js
	 * @param params
	 * @return
	 */
	public void createNewWebContent(String name, String cont, String img,
			String sum, String css, String js, Object... params) {
		boolean lines = (Boolean) (params.length > 0 ? params[0] : false);
		String optionLang = (String) (params.length > 1 ? params[1] : "");
		By eWebContentSum;
		
		if (this.plfVersion.equalsIgnoreCase("4.0"))
			eWebContentSum = ELEMENT_WEBCONTENT_SUMMARY_FRAME;
		else
			eWebContentSum = ELEMENT_WEBCONTENT_SUMMARY_FRAME_41;
		
		info("-- Creating a new Web Content --");
		Utils.pause(500);
		//click(ELEMENT_WEBCONTENT_LINK);
		type(ELEMENT_WEBCONTENT_NAME_TEXTBOX, name, false);
		if (!optionLang.isEmpty()) {
			selectOption(ELEMENT_PIC_LANG, optionLang);
		}
		if (cont != "") {
			if (this.plfVersion.equalsIgnoreCase("4.0"))
				inputDataToFrame(ELEMENT_WEBCONTENT_CONTENT_FRAME, cont,true);
			else
				inputDataToFrame(ELEMENT_WEBCONTENT_CONTENT_FRAME_41, cont,true);
			switchToParentWindow();
		}
		if (sum != "" || img != "") {
			click(ELEMENT_WEBCONTENT_ILLUSTRATION_TAB);
			Utils.pause(3000);
			if (img != "") {
				WebElement upload = waitForAndGetElement(ELEMENT_UPLOAD_NAME,
						DEFAULT_TIMEOUT, 0, 2);
				((JavascriptExecutor) driver).executeScript(
						"arguments[0].style.display = 'block';", upload);
				upload.sendKeys(getAbsoluteFilePath(img));

				switchToParentWindow();
				String links[] = img.split("/");
				int length = links.length;
				waitForAndGetElement(
						By.xpath("//div[contains(text(),'" + links[length - 1]
								+ "')]"), DEFAULT_TIMEOUT, 1, 2);
			}
			if (!lines) {
				inputDataToFrame(eWebContentSum, sum);
				switchToParentWindow();
			} else {
				if (waitForAndGetElement(ELEMENT_WEBCONTENT_SUMMARY_FRAME_41,
						5000, 0, 1) != null)
					typeMultiLineInCkeContent(
							ELEMENT_WEBCONTENT_SUMMARY_FRAME_41, sum);
				else
					typeMultiLineInCkeContent(ELEMENT_WEBCONTENT_SUMMARY_FRAME,
							sum);
			}
		}
		if (css != "" || js != "") {
			click(ELEMENT_WEBCONTENT_ADVANCE_TAB);
			type(ELEMENT_WEBCONTENT_CSS_TEXTAREA, css, false);
			type(ELEMENT_WEBCONTENT_JS_TEXTAREA, js, false);
		}
		click(button.ELEMENT_SAVE_CLOSE_BUTTON);
		Utils.pause(3000);
		//return new SitesExplorer(driver, this.plfVersion);
	}
	
	/**
	 * Create a new Css file
	 * @param name
	 * @param priority
	 * @param data
	 * @param params
	 */
	public void createNewCssFile(String name, String priority, String data,
			Object... params) {
		Boolean active = (Boolean) (params.length > 0 ? params[0] : false);
		String lang = (String) (params.length > 1 ? params[1] : "");

		info("-- Creating a new Css File --");
		click(ELEMENT_CSS_FILE_LINK);
		type(ELEMENT_CSS_NAME, name, false);
		if (!active) {
			select(ELEMENT_CSS_ACTIVE, "False");
		} else {
			select(ELEMENT_CSS_ACTIVE, "True");
		}
		type(ELEMENT_CSS_PRIORITY, priority, false);
		if (!lang.isEmpty()) {
			select(ELEMENT_PIC_LANG, lang);
		}
		type(ELEMENT_CSS_DATA, data, true);
		click(button.ELEMENT_SAVE_CLOSE_BUTTON);
		Utils.pause(2000);
	}

}

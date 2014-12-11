package org.exoplatform.selenium.platform.ecms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContextMenu extends SitesExplorer{

	public ContextMenu(WebDriver dr, String plfVersion) {
		super(dr, plfVersion);
	}

	public final By ELEMENT_DOWNLOAD_ENTRY = By.xpath(".//*[@class='uiIconEcmsDownload']");
	public final By ELEMENT_DOWNLOAD_ENTRY_PREVIOUS_ENTRY_DELETE= By.xpath(".//*[@class='uiIconEcmsDownload']//../.././preceding-sibling::li[1]//i[@class='uiIconEcmsDelete']");
    public final By ELEMENT_DOWNLOAD_ENTRY_NEXT_ENTRY_LOCK= By.xpath(".//*[@class='uiIconEcmsDownload']//../.././following-sibling::li[1]//i[@class='uiIconEcmsLock']");
}

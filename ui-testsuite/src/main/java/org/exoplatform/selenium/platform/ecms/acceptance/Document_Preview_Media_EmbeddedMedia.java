package org.exoplatform.selenium.platform.ecms.acceptance;

import static org.exoplatform.selenium.TestLogger.info;

import org.testng.annotations.Test;

/** 
* By quynhpt
* data 27/11/2014
*/

public class Document_Preview_Media_EmbeddedMedia extends Document_Preview_TestConfig{
	
	
	/**
	 * MEDIA_06:
	 * The external embedded medias content supported are : 
	 * - YouTube
	 * - Vimeo
	 * - Dailymotion
	 * - Slideshare
	 * - Flickr
	 */
	@Test
	public void test01_CheckPreviewModeForYoutubeLink(){
		info("Declare variables");
		deleteActive=true;
		//get a youtube file
		videoLink = videoLinksFile.getVideoLinksByArrayTypeRandom(1);
		
		info("Go to Activity stream of Intranet Home page");
		hp.goToHomePage();
		
		info("Share the link on activity Stream");
		acStream.addActivity(false, "", true, videoLink);
		
	    info("Verify that video is shown on activity stream");
	    waitForAndGetElement(acStream.ELEMENT_MEDIA_EXTERNAL_EMBEDDED_VIDEO.replace("${link}", videoLink),3000,0);
	    acStream.checkSizeActivityPlayer(acStream.ELEMENT_MEDIA_ACTIVITY_PLAYER_FRAME_EXTERNAL_EMBEDDED_VIDEO.replace("${link}",videoLink));
		
	    info("Open Preview mode");		
		acStream.openPreviewMode(videoLink,true);
		
		info("Verify that the preview is shown");
		waitForAndGetElement(previewMode.ELEMENT_PREVIEW_MODE,3000,0);
		deleteDataTest(videoLink);
		
	}
	
	@Test
	public void test02_CheckPreviewModeForVimeoLink(){
		info("Declare variables");
		deleteActive=true;
		//get a vimeo link
		videoLink = videoLinksFile.getVideoLinksByArrayTypeRandom(2);
		
		info("Go to Activity stream of Intranet Home page");
		hp.goToHomePage();
		
		info("Share the link on activity Stream");
		acStream.addActivity(false,"", true, videoLink);
		
		 info("Verify that video is shown on activity stream");
		 waitForAndGetElement(acStream.ELEMENT_MEDIA_EXTERNAL_EMBEDDED_VIDEO.replace("${link}", videoLink));
		 acStream.checkSizeActivityPlayer(acStream.ELEMENT_MEDIA_ACTIVITY_PLAYER_FRAME_EXTERNAL_EMBEDDED_VIDEO.replace("${link}",videoLink));
		 info("Open Preview mode");		
		acStream.openPreviewMode(videoLink,true);
		
		info("Verify that the preview is shown");
		waitForAndGetElement(previewMode.ELEMENT_PREVIEW_MODE);
		deleteDataTest(videoLink);
	}
	
	@Test
	public void test03_CheckPreviewModeForDailymontionLink(){
		info("Declare variables");
		deleteActive=true;
		//get a dailymotion link
		videoLink = videoLinksFile.getVideoLinksByArrayTypeRandom(5);
		
		info("Go to Activity stream of Intranet Home page");
		hp.goToHomePage();
		
		info("Share the link on activity Stream");
		acStream.addActivity(false, "", true, videoLink);
	
		 info("Verify that video is shown on activity stream");
		 waitForAndGetElement(acStream.ELEMENT_MEDIA_EXTERNAL_EMBEDDED_VIDEO.replace("${link}", videoLink));
		 acStream.checkSizeActivityPlayer(acStream.ELEMENT_MEDIA_ACTIVITY_PLAYER_FRAME_EXTERNAL_EMBEDDED_VIDEO.replace("${link}",videoLink));
		info("Open Preview mode");		
		acStream.openPreviewMode(videoLink,true);
		
		info("Verify that the preview is shown");
		waitForAndGetElement(previewMode.ELEMENT_PREVIEW_MODE);
		deleteDataTest(videoLink);
	}
	
	@Test
	public void test04_CheckPreviewModeForSlideShareLink(){
		info("Declare variables");
		deleteActive=true;
		//get a slideshare link
		videoLink = videoLinksFile.getVideoLinksByArrayTypeRandom(3);
		
		info("Go to Activity stream of Intranet Home page");
		hp.goToHomePage();
		
		info("Share the link on activity Stream");
		acStream.addActivity(false, "", true, videoLink);
		
		 info("Verify that video is shown on activity stream");
		waitForAndGetElement(acStream.ELEMENT_MEDIA_EXTERNAL_EMBEDDED_VIDEO.replace("${link}", videoLink));
		acStream.checkSizeActivityPlayer(acStream.ELEMENT_MEDIA_ACTIVITY_PLAYER_FRAME_EXTERNAL_EMBEDDED_VIDEO.replace("${link}",videoLink));		
		info("Open Preview mode");		
		acStream.openPreviewMode(videoLink,true);
		
		info("Verify that the preview is shown");
		waitForAndGetElement(previewMode.ELEMENT_PREVIEW_MODE);
		deleteDataTest(videoLink);
	}
	
	@Test
	public void test05_CheckPreviewModeForFlickrLink(){
		info("Declare variables");
		deleteActive=true;
		//get a flickr link
		videoLink = videoLinksFile.getVideoLinksByArrayTypeRandom(4);
		
		info("Go to Activity stream of Intranet Home page");
		hp.goToHomePage();
		
		info("Share the link on activity Stream");
		acStream.addActivity(false, "", true, videoLink);
		
		info("Verify that video is shown on activity stream");
		waitForAndGetElement(acStream.ELEMENT_MEDIA_EXTERNAL_EMBEDDED_VIDEO.replace("${link}", videoLink));
		acStream.checkSizeActivityPlayer(acStream.ELEMENT_MEDIA_ACTIVITY_PLAYER_FRAME_EXTERNAL_EMBEDDED_VIDEO.replace("${link}",videoLink));
		info("Open Preview mode");		
		acStream.openPreviewMode(videoLink,true);
		
		info("Verify that the preview is shown");
		waitForAndGetElement(previewMode.ELEMENT_PREVIEW_MODE);
		deleteDataTest(videoLink);
	}
	
	/**
	 * Delete all data test
	 * @param file
	 */
	private void deleteDataTest(String file){
		info("Delete data test");
		info("Close The preview mode");
		previewMode.backASByClickCrossIcon();
		acStream.deleteActivity(file, true,true);
		info("Data test is deleted successfully");
	}
}

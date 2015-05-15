package org.exoplatform.selenium.platform.forum.functional.forum.forumportletsettings;

import org.exoplatform.selenium.platform.forum.ForumBase;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PageEditor;
import org.exoplatform.selenium.platform.forum.ForumManageCategory;
import org.exoplatform.selenium.platform.forum.ForumManageForum;
import org.exoplatform.selenium.platform.forum.ForumManagePoll;
import org.exoplatform.selenium.platform.forum.ForumManagePost;
import org.exoplatform.selenium.platform.forum.ForumManageTopic;

public class Forum_Forum_PortletSettings extends ForumBase {
	
	ManageAccount acc;
	ForumManageCategory fmCat;
	ForumManageForum fmForum;
	ForumManageTopic fmTopic;
	ForumManagePost fmPost;
	ForumManagePoll fmPoll;

	@BeforeMethod
	public void setUpBeforeTest() {
		initSeleniumTest();
		driver.get(baseUrl);
		fmCat = new ForumManageCategory(driver);
		fmForum = new ForumManageForum(driver);
		fmTopic = new ForumManageTopic(driver);
		fmPost = new ForumManagePost(driver);
		acc = new ManageAccount(driver);
		pageE = new PageEditor(driver);
		acc.signIn(DATA_USER1, DATA_PASS);
		button = new Button(driver);
		fmPoll = new ForumManagePoll(driver);
		navTool = new NavigationToolbar(driver);
		goToForums();
	}

	@AfterMethod
	public void afterMethod() {
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	/**
	 * Case ID:106013
	 * Disable/Enable Icons Legend panel in Forum porlet setting.
	 * Step 1: Go to Edit mode
	 * Step 2: Select Icons Legend panel to disable
	 * Step 3: Disable Icons Legend Panel
	 * Step 4: Check Forum application after settings
	 */
	@Test
	public void test01_DisableIconsLegendPanelInForumPorletSetting() {

		info("Disable Icons Legend panel in Forum porlet setting");
		//"Disable Icons Legend panel for forum portlet
		info("Disable Icons Legend for forum portlet");
		navTool.goToEditPageEditor();
		pageE.goToEditPortlet(ELEMENT_FRAME_CONTAIN_PORTLET);
		selectPanel(true,true,true,false);
		click(ELEMENT_FORUM_PORTLET_CLOSE_BUTTON);
		pageE.finishEditLayout();
		//Demo signins and verify Icon Legends
		info("Demo signins and create topic");
		acc.signOut();
		acc.signIn(DATA_USER4, DATA_PASS);
		goToForums();
		waitForElementNotPresent(ELEMENT_FORUM_STATE);
		info("Element not present checked");
		info("Test succeed");
		acc.signOut();
	}

	/**
	 * Case ID:105771
	 * Disable Moderator panel in Forum porlet setting
	 * Step 1: Go to Edit mode
	 * Step 2: Select Moderator panel to disable
	 * Step 3: Disable Moderator Panel
	 * Step 4: Check Forum application after settings
	 */
	@Test
	public void test02_DisableModeratorPanelInForumPorletSetting() {

		info("Test 2: Disable Moderator panel in Forum porlet setting");
		String catName = "Category setting forum 01";
		String description = "Add new category 1 in forum";
		String fmName = "Test 02 new forum";
		info("TC 72576: Disable Moderator Panel");
		navTool.goToEditPageEditor();
		pageE.goToEditPortlet(ELEMENT_FRAME_CONTAIN_PORTLET);
		info("Setting panel for forum portlet");
		selectPanel(true, false);
		click(ELEMENT_FORUM_PORTLET_CLOSE_BUTTON);
		pageE.finishEditLayout();
		goToUserManagement(DATA_USER4);
		settingUserManagementProfile(null, null, null, null, null, true);
		button.save();
		info("Settings saved");
		button.close();
		waitForElementNotPresent(ELEMENT_USER_MANAGEMENT_POPUP);
		info("Element not present checked");
		acc.signOut();
		acc.signIn(DATA_USER4, DATA_PASS);
		goToForums();
		fmCat.addNewCategoryInForum(catName, "1", 0, null, description, 0, null);
		fmForum.quickAddForum(fmName);
		waitForElementNotPresent(ELEMENT_MODERATOR_PANEL);
		info("Element not present checked");
		acc.signOut();
		info("Test succeed");
	}

	/**
	 * Case ID:105877
	 * Disable Poll panel in Forum porlet setting.
	 * Step 1: Go to Edit m
	 * Step 2: Select Poll panel to disable
	 * Step 3: Disable Poll Panel
	 * Step 4: Check Forum application after settings
	 */
	@Test
	public void test03_DisablePollPanelInForumPorletSetting() {
		info("Test 4: Disable Poll panel in Forum porlet setting");
		String catName = "Category setting forum 04";
		String fmName = "Test 04 new forum";
		String topName = "Test 04 topic";
		String pollQuestion = "Poll 02";
		String[] pollOptions = {"Option 01", "Option 02"};

		info("TC 72753: Disable Poll Panel");
		//"Disable poll panel for forum portlet
		info("Disable poll panel for forum portlet");
		navTool.goToEditPageEditor();
		pageE.goToEditPortlet(ELEMENT_FRAME_CONTAIN_PORTLET);
		selectPanel(false);
		click(ELEMENT_FORUM_PORTLET_CLOSE_BUTTON);
		pageE.finishEditLayout();
		//Change role of demo to Admin
		info("Change role of demo to Admin");
		goToUserManagement(DATA_USER4);
		settingUserManagementProfile(null, null, null, null, null, true);
		button.save();
		info("Settings saved");
		button.close();
		waitForElementNotPresent(ELEMENT_USER_MANAGEMENT_POPUP);
		info("Element not present checked");
		//Demo signins and create poll
		info("Demo signins and create poll");
		acc.signOut();
		acc.signIn(DATA_USER4, DATA_PASS);
		goToForums();
		fmTopic.addCategoryForumTopic(catName, fmName, topName,topName);
		waitForAndGetElement(By.linkText(topName)).click();
		info("Element checked");
		fmPoll.addPoll(pollQuestion, pollOptions, "2", true, true,false);
		waitForElementNotPresent(fmPoll.ELEMENT_POLL);
		info("Element not present checked");
		acc.signOut();
		info("Test succeed");
	}

	/**
	 * Case ID:105956
	 * Disable Quick Reply panel in Forum porlet setting
	 * Step 1: Go to Edit mode
	 * Step 2: Select Quick Reply panel to disable
	 * Step 3: Disable Quick Reply Panel
	 * Step 4: Check Forum application after settings
	 */
	@Test
	public void test04_DisableQuickReplyPanelInForumPorletSetting() {
		info("Test 6: Disable Quick Reply panel in Forum porlet setting");
		String catName = "Category setting forum 06";
		String fmName = "Test 06 new forum";
		String topName = "Test 06 topic";

		info("TC 72877: Disable Quick Reply panel in Forum porlet setting");
		//"Disable quick reply panel for forum portlet
		info("Disable quick reply for forum portlet");
		navTool.goToEditPageEditor();
		pageE.goToEditPortlet(ELEMENT_FRAME_CONTAIN_PORTLET);
		selectPanel(true,true,false);
		click(ELEMENT_FORUM_PORTLET_CLOSE_BUTTON);
		pageE.finishEditLayout();
		//Change role of demo to Admin
		info("Change role of demo to Admin");
		goToUserManagement(DATA_USER4);
		settingUserManagementProfile(null, null, null, null, null, true);
		button.save();
		button.close();
		waitForElementNotPresent(ELEMENT_USER_MANAGEMENT_POPUP);
		info("Element not present checked");
		//Demo signins and create poll
		info("Demo signins and create topic");
		acc.signOut();
		acc.signIn(DATA_USER4, DATA_PASS);
		goToForums();
		fmTopic.addCategoryForumTopic(catName, fmName, topName,topName);
		waitForAndGetElement(By.linkText(topName)).click();
		waitForElementNotPresent(fmPost.ELEMENT_POST_QUICK_MESSAGE);
		info("Element not present checked");
		acc.signOut();
		info("Test succeed");
	}

	/**
	 * Case ID:106058
	 * Disable Rules panel in Forum porlet setting.
	 * Step 1: Go to Edit mode
	 * Step 2: Select Rules panel to disable
	 * Step 3: Disable Rules Panel
	 * Step 4: Check Forum application after settings
	 */
	@Test
	public void test05_DisableRulesPanelInForumPorletSetting() {
		info("Test 10: Disable Rules panel in Forum porlet setting");
		String catName = "Category setting forum 10";
		String fmName = "Test 10 new forum";
		String topName = "Test 10 topic";

		info("TC 73047: Disable Rules panel in Forum porlet setting");
		//"Disable Rules panel panel for forum portlet
		info("Disable Rules panel for forum portlet");
		navTool.goToEditPageEditor();
		pageE.goToEditPortlet(ELEMENT_FRAME_CONTAIN_PORTLET);
		selectPanel(true,true,true,true,false);
		click(ELEMENT_FORUM_PORTLET_CLOSE_BUTTON);
		pageE.finishEditLayout();
		//Change role of demo to Admin
		info("Change role of demo to Admin");
		goToUserManagement(DATA_USER4);
		settingUserManagementProfile(null, null, null, null, null, true);
		button.save();
		button.close();
		waitForElementNotPresent(ELEMENT_USER_MANAGEMENT_POPUP);
		info("Element not present checked");
		//Demo signins and create topic
		info("Demo signins and create topic");
		acc.signOut();
		acc.signIn(DATA_USER4, DATA_PASS);
		goToForums();
		fmTopic.addCategoryForumTopic(catName, fmName, topName,topName);
		waitForAndGetElement(By.linkText(topName)).click();
		waitForElementNotPresent(fmForum.ELEMENT_RULE_PANEL);
		info("Element not present checked");
		acc.signOut();
		info("Test succeed");
	}

	/**
	 * Case ID: 106094
	 * Disable Statistic panel in Forum porlet setting
	 * Step 1: Go to Edit mode
	 * Step 2: Select Statistic panel to disable
	 * Step 3: Disable Statistic Panel
	 * Step 4: Check Forum application after settings
	 */
	@Test
	public void test06_DisableStatisticPanelInForumPorletSetting() {
		info("Disable Statistic panel in Forum porlet setting");
		//"Disable Statistic panel for forum portlet
		info("Disable Statistic for forum portlet");
		navTool.goToEditPageEditor();
		pageE.goToEditPortlet(ELEMENT_FRAME_CONTAIN_PORTLET);
		selectPanel(true,true,true,true,true,false);
		click(ELEMENT_FORUM_PORTLET_CLOSE_BUTTON);
		pageE.finishEditLayout();
		//Demo signins and verify Statistic
		info("Demo signins and and verify Statistic");
		acc.signOut();
		acc.signIn(DATA_USER4, DATA_PASS);
		goToForums();
		waitForElementNotPresent(ELEMENT_STATISTIC_PANEL);
		info("Element not present checked");
		acc.signOut();
		info("Test succeed");
	}

	@Test
	/**
	 * Case ID: 106037
	 * Enable The Disabling Icons Legend Panel In Forum Porlet Setting
	 * Step 1: Go to Edit mode
	 * Step 2: Select Icons Legend panel to enable
	 * Step 3: Enable Icons Legend Panel
	 * Step 4: Check Forum application after settings
	 */
	public void test07_EnableTheDisablingIconsLegendPanelInForumPorletSetting() {
		info("Enable Icons Legend panel for forum portlet");
		goToForums();
		navTool.goToEditPageEditor();
		pageE.goToEditPortlet(ELEMENT_FRAME_CONTAIN_PORTLET);
		selectPanel(true,true,true,true);
		click(ELEMENT_FORUM_PORTLET_CLOSE_BUTTON);
		pageE.finishEditLayout();
		//Demo signins and verify Icons Legend panel
		info("Demo signins and verify if Icons Legend displays");
		acc.signOut();
		acc.signIn(DATA_USER4, DATA_PASS);
		goToForums();
		waitForAndGetElement(ELEMENT_FORUM_STATE);
		info("Element checked");
		info("Test succeed");
	}

	/**
	 * Case ID: 105827
	 * Enable the disabling Moderator panel in Forum porlet setting
	 * Step 1: Go to Edit mode
	 * Step 2: Select Moderator panel to enable
	 * Step 3: Enable Moderator Panel
	 * Step 4: Check Forum application after settings
	 */
	@Test
	public void test08_EnableTheDisablingModeratorPanelInForumPorletSetting() {
		String catName = "Category setting forum 10";
		String fmName = "Test 10 new forum";

		info("TC 72673: Enable Moderator Panel");
		goToForums();
		navTool.goToEditPageEditor();
		pageE.goToEditPortlet(ELEMENT_FRAME_CONTAIN_PORTLET);
		info("Setting panel for forum portlet");
		selectPanel(true, true);
		click(ELEMENT_FORUM_PORTLET_CLOSE_BUTTON);
		pageE.finishEditLayout();
		acc.signOut();
		acc.signIn(DATA_USER1, DATA_PASS);
		goToForums();
		click(By.linkText(catName));
		click(By.linkText(fmName));
		waitForAndGetElement(ELEMENT_MODERATOR_PANEL);
		info("Element checked");
		info("Remove data");
		goToForumHome();
		click(By.linkText(catName));
		fmCat.deleteCategoryInForum(catName);
		info("Data deleted");
		goToUserManagement(DATA_USER4);
		settingUserManagementProfile(null, null, null, null, null, false);
		button.save();
		button.close();
		waitForElementNotPresent(ELEMENT_USER_MANAGEMENT_POPUP);
		info("Element not present checked");
		info("Test succeed");
	}

	@Test
	/**
	 * Case ID: 105920
	 * Enable the disabling Poll panel in Forum porlet setting
	 * Step 1: Go to Edit mode
	 * Step 2: Select Poll panel to enable
	 * Step 3: Enable Poll Panel
	 * Step 4: Check Forum application after settings
	 */
	public void test09_EnableTheDisablingPollPanelInForumPorletSetting() {
		String catName = "Category" + getRandomNumber();
		String fmName = "Test 10 new forum";
		String topName = "Test 10 topic";
		String pollQuestion = "Poll 02";
		String[] pollOptions = {"Option 01", "Option 02"};

		info("TC 72822: Enable Poll Panel");
		//Enable poll panel for forum portlet
		info("Enable poll panel for forum portlet");
		goToForums();
		navTool.goToEditPageEditor();
		pageE.goToEditPortlet(ELEMENT_FRAME_CONTAIN_PORTLET);
		selectPanel(true);
		click(ELEMENT_FORUM_PORTLET_CLOSE_BUTTON);
		pageE.finishEditLayout();
		fmTopic.addCategoryForumTopic(catName, fmName, topName,topName);
		goToForumHome();
		click(By.linkText(catName));
		fmCat.deleteCategoryInForum(catName);
		//Demo signins and verify created poll
		info("Demo signins and verify created poll");
		goToForums();
		fmTopic.addCategoryForumTopic(catName, fmName, topName,topName);
		waitForAndGetElement(By.linkText(topName)).click();
		fmPoll.addPoll(pollQuestion, pollOptions, "2", true, true,false);
		waitForAndGetElement(fmPoll.ELEMENT_POLL);
		info("Element checked");
		//********************************Remove data***********************************//
		info("Remove data");
		goToForumHome();
		click(fmCat.ELEMENT_CATEGORY_NAME.replace("${catName}", catName));
		fmCat.deleteCategoryInForum(catName);
		info("Data deleted");
		goToUserManagement(DATA_USER4);
		settingUserManagementProfile(null, null, null, null, null, false);
		button.save();
		button.close();
		waitForElementNotPresent(ELEMENT_USER_MANAGEMENT_POPUP);
		info("Element not present checked");
		info("Test succeed");
	}

	@Test
	/**
	 * Case ID: 105986
	 * Enable the disabling Quick Reply panel in Forum porlet setting
	 * Step 1: Go to Edit mode
	 * Step 2: Select Quick Reply Panel to enable
	 * Step 3: Enable Quick Reply Panel
	 * Step 4: Check Forum application after settings
	 */
	public void test10_EnableTheDisablingQuickReplyPanelInForumPorletSetting() {
		String catName = "Category" + getRandomNumber();
		String fmName = "Test 10 new forum";
		String topName = "Test 10 topic";

		//Enable quick reply panel for forum portlet
		info("Enable quick reply panel for forum portlet");
		goToForums();
		navTool.goToEditPageEditor();
		pageE.goToEditPortlet(ELEMENT_FRAME_CONTAIN_PORTLET);
		selectPanel(true,true,true);
		click(ELEMENT_FORUM_PORTLET_CLOSE_BUTTON);
		pageE.finishEditLayout();
		//Demo signins and verify quick reply panel
		info("Demo signins and verify if quick reply panel displays");
		fmTopic.addCategoryForumTopic(catName, fmName, topName,topName);
		acc.signOut();
		acc.signIn(DATA_USER4, DATA_PASS);
		goToForums();
		click(By.linkText(fmName));
		click(By.linkText(topName));
		waitForAndGetElement(fmPost.ELEMENT_POST_QUICK_MESSAGE);
		info("Element checked");
		//********************************Remove data***********************************//
		info("Remove data");
		acc.signOut();
		acc.signIn(DATA_USER1, DATA_PASS);
		goToForums();
		click(By.linkText(catName));
		fmCat.deleteCategoryInForum(catName);
		info("Data deleted");
		goToUserManagement(DATA_USER4);
		settingUserManagementProfile(null, null, null, null, null, false);
		button.save();
		button.close();
		waitForElementNotPresent(ELEMENT_USER_MANAGEMENT_POPUP);
		info("Element not present checked");
		info("Test succeed");
	}

	@Test
	/**
	 * Case ID: 106078
	 * Enable the disabling Rules panel in Forum porlet setting
	 * Step 1: Go to Edit mode
	 * Step 2: Select Rules Panel to enable
	 * Step 3: Enable Rules Panel
	 * Step 4: Check Forum application after settings
	 */
	public void test11_EnableTheDisablingRulesPanelInForumPorletSetting() {
		String catName = "Category" + getRandomNumber();
		String fmName = "Test 10 new forum";
		String topName = "Test 10 topic";

		info("TC 73082:Enable Rules panel in Forum porlet setting");
		//Enable quick reply panel for forum portlet
		info("Enable Rules panel for forum portlet");
		goToForums();
		navTool.goToEditPageEditor();
		pageE.goToEditPortlet(ELEMENT_FRAME_CONTAIN_PORTLET);
		selectPanel(true,true,true,true,true);
		click(ELEMENT_FORUM_PORTLET_CLOSE_BUTTON);
		pageE.finishEditLayout();
		//Demo signins and verify Rules panel
		info("Demo signins and verify if Rules panel displays");
		fmTopic.addCategoryForumTopic(catName, fmName, topName,topName);
		acc.signOut();
		acc.signIn(DATA_USER4, DATA_PASS);
		goToForums();
		click(By.linkText(catName));
		click(By.linkText(topName));
		waitForAndGetElement(fmForum.ELEMENT_RULE_PANEL);
		info("Element checked");
		info("Remove data");
		acc.signOut();
		acc.signIn(DATA_USER1, DATA_PASS);
		goToForums();
		click(fmCat.ELEMENT_CATEGORY_NAME.replace("${catName}", catName));
		fmCat.deleteCategoryInForum(catName);
		info("Data deleted");
		goToUserManagement(DATA_USER4);
		settingUserManagementProfile(null, null, null, null, null, false);
		button.save();
		button.close();
		waitForElementNotPresent(ELEMENT_USER_MANAGEMENT_POPUP);
		info("Element not present checked");
		info("Test succeed");
	}

	@Test
	/**
	 * Case ID: 106106
	 * Enable the disabling Statistic panel in Forum porlet setting
	 * Step 1: Go to Edit mode
	 * Step 2: Select Statistic Panel to enable
	 * Step 3: Enable Statistic Panel
	 * Step 4: Check Forum application after settings
	 */
	public void test12_EnableTheDisablingStatisticPanelInForumPorletSetting() {
		info("TC 73131: Statistic panel in Forum porlet setting.");
		//Enable Statistic panel for forum portlet
		info("Enable Statistic panel for forum portlet");
		goToForums();
		navTool.goToEditPageEditor();
		pageE.goToEditPortlet(ELEMENT_FRAME_CONTAIN_PORTLET);
		selectPanel(true,true,true,true,true,true);
		click(ELEMENT_FORUM_PORTLET_CLOSE_BUTTON);
		pageE.finishEditLayout();
		//Demo signins and verify Statistic panel
		info("Demo signins and verify if Statistic displays");
		acc.signOut();
		acc.signIn(DATA_USER4, DATA_PASS);
		goToForums();
		waitForAndGetElement(ELEMENT_STATISTIC_PANEL);
		info("Element checked");
		info("Test succeed");
	}

	@Test
	/**
	 * Case ID: 105603
	 * Hide category in Forum portlet settings
	 * Step 1: Go to Edit mode
	 * Step 2: Select category to hide
	 * Step 3: Hide the selected category 
	 * Step 4: Check Forum application after settings
	 */
	public void test13_HideCategoryInForumPortletSettings() {
		String catName1 = "Category" + getRandomNumber();
		String description1 = "Add new category 13 in forum";

		fmCat.addNewCategoryInForum(catName1, "1", 0, null, description1, 0, null);
		navTool.goToEditPageEditor();
		pageE.goToEditPortlet(ELEMENT_FRAME_CONTAIN_PORTLET);
		info("Setting to show/hire category");
		selectDisplayCategoryAndForum(catName1, false);
		click(ELEMENT_FORUM_PORTLET_CLOSE_BUTTON);
		pageE.finishEditLayout();
		acc.signOut();
		acc.signIn(DATA_USER4, DATA_PASS);
		goToForums();
		waitForElementNotPresent(By.linkText(catName1));
		info("Element not present checked");
		acc.signOut();
		acc.signIn(DATA_USER1, DATA_PASS);
		goToForums();
		navTool.goToEditPageEditor();
		pageE.goToEditPortlet(ELEMENT_FRAME_CONTAIN_PORTLET);
		selectDisplayCategoryAndForum(catName1, true);
		click(ELEMENT_FORUM_PORTLET_CLOSE_BUTTON);
		pageE.finishEditLayout();
		acc.signOut();
		acc.signIn(DATA_USER4, DATA_PASS);
		goToForums();
		waitForAndGetElement(By.linkText(catName1));
		info("Element checked");
		acc.signOut();
		acc.signIn(DATA_USER1, DATA_PASS);
		goToForums();
		click(By.linkText(catName1));
		fmCat.deleteCategoryInForum(catName1);
		info("Data deleted");
		info("Test succeed");
	}

	@Test
	/**
	 * Case ID: 105604
	 * Hide Forum of category which showing in Forum portlet settings
	 * Step 1: Go to Edit mode
	 * Step 2: Select forum to hide
	 * Step 3: Hide the selected forum 
	 * Step 4: Check Forum application after settings
	 */
	public void test14_HideForumOfCategoryWhichShowingInForumPortletSettings() {
		info("Test 1: Hide/show Forum of category which showing in Forum portlet settings");
		String catName = "Category" + getRandomNumber();
		String[] fmName={"Test 1 new forum",null,null,null,null};
		String description1 = "Add new category 1 in forum";
		fmCat.addNewCategoryInForum(catName, "1", 0, null, description1, 0, null);
		fmForum.addForum(catName, fmName, false,EMAIL_ADDRESS1 ,EMAIL_ADDRESS2, false, 0, null);
		navTool.goToEditPageEditor();
		pageE.goToEditPortlet(ELEMENT_FRAME_CONTAIN_PORTLET);
		info("Setting to show/hire category");
		selectDisplayCategoryAndForum(catName + "/"+fmName[0], false);
		click(ELEMENT_FORUM_PORTLET_CLOSE_BUTTON);
		pageE.finishEditLayout();
		acc.signOut();
		acc.signIn(DATA_USER4, DATA_PASS);
		goToForums();
		waitForElementNotPresent(By.linkText(fmName[0]));
		info("Element not present checked");
		acc.signOut();
		info("Test succeed");
	}

	@Test
	/**
	 * Case ID: 105694
	 * Show the hidden category in Forum portlet settings
	 * Step 1: Go to Edit mode
	 * Step 2: Select category to show
	 * Step 3: Show the selected category 
	 * Step 4: Check Forum application after settings
	 */
	public void test15_ShowTheHiddenCategoryInForumPortletSettings() {
		String catName = "Category" + getRandomNumber();
		String[] fmName={"Test 1 new forum",null,null,null,null};

		goToForums();
		navTool.goToEditPageEditor();
		pageE.goToEditPortlet(ELEMENT_FRAME_CONTAIN_PORTLET);
		selectDisplayCategoryAndForum(catName + "/"+fmName[0], true);
		click(ELEMENT_FORUM_PORTLET_CLOSE_BUTTON);
		pageE.finishEditLayout();
		acc.signOut();
		acc.signIn(DATA_USER4, DATA_PASS);
		goToForums();
		waitForAndGetElement(By.linkText(catName));
		info("Element checked");
		acc.signOut();
		acc.signIn(DATA_USER1, DATA_PASS);
		goToForums();
		click(fmCat.ELEMENT_CATEGORY_NAME.replace("${catName}", catName));
		fmCat.deleteCategoryInForum(catName);
		info("Data deleted");
		info("Test succeed");
	}

	@Test
	/**
	 * Case ID: 105695
	 * 	Show the hidden forum of category which showing in Forum portlet settings
	 * Step 1: Go to Edit mode
	 * Step 2: Select forum to show
	 * Step 3: Show the selected forum 
	 * Step 4: Check Forum application after settings
	 */
	public void test16_ShowTheHiddenForumOfCategoryWhichShowingInForumPortletSettings() {
		info("Test 1: Hide/show Forum of category which showing in Forum portlet settings");
		String catName = "Category" + getRandomNumber();
		String[] fmName={"Test 1 new forum",null,null,null,null};
		String description1 = "Add new category 1 in forum";
		fmCat.addNewCategoryInForum(catName, "1", 0, null, description1, 0, null);
		fmForum.addForum(catName, fmName, false,EMAIL_ADDRESS1 ,EMAIL_ADDRESS2, false, 0, null);
		navTool.goToEditPageEditor();
		pageE.goToEditPortlet(ELEMENT_FRAME_CONTAIN_PORTLET);
		info("Setting to show/hire category");
		selectDisplayCategoryAndForum(catName + "/"+fmName[0], false);
		click(ELEMENT_FORUM_PORTLET_CLOSE_BUTTON);
		pageE.finishEditLayout();
		acc.signOut();
		acc.signIn(DATA_USER4, DATA_PASS);
		goToForums();
		waitForElementNotPresent(By.linkText(fmName[0]));
		acc.signOut();
		acc.signIn(DATA_USER1, DATA_PASS);
		goToForums();
		navTool.goToEditPageEditor();
		pageE.goToEditPortlet(ELEMENT_FRAME_CONTAIN_PORTLET);
		selectDisplayCategoryAndForum(catName + "/"+fmName[0], true);
		click(ELEMENT_FORUM_PORTLET_CLOSE_BUTTON);
		pageE.finishEditLayout();
		acc.signOut();
		acc.signIn(DATA_USER4, DATA_PASS);
		goToForums();
		waitForAndGetElement(By.linkText(catName));
		info("Element checked");
		acc.signOut();
		acc.signIn(DATA_USER1, DATA_PASS);
		goToForums();
		click(By.linkText(catName));
		fmCat.deleteCategoryInForum(catName);
		info("Data deleted");
		info("Test succeed");
	}

	/**
	 * Case ID:106229.
	 * Show the hidden forum of category which hiding in Forum portlet settings.
	 * Step 1: Go to Edit mode
	 * Step 2: Select forum to show
	 * Step 3: Show the selected forum
	 * Step 4: Check Forum application after settings
	 */
	@Test
	public void test17_ShowTheHiddenForumOfCategoryWhichHidingInForumPortletSettings() {
		info("Test 2: Show the hidden forum of category which hiding in Forum portlet settings");
		String catName = "Test 2 Add new category";
		String[] fmName={"Test 2 new forum",null,null,null,null};
		String description1 = "Add new category 1 in forum";
		fmCat.addNewCategoryInForum(catName, "1", 0, null, description1, 0, null);
		fmForum.addForum(catName, fmName, false,EMAIL_ADDRESS1 ,EMAIL_ADDRESS2, false, 0, null);
		navTool.goToEditPageEditor();
		pageE.goToEditPortlet(ELEMENT_FRAME_CONTAIN_PORTLET);
		info("Setting to hide category");
		selectDisplayCategoryAndForum(catName, false);
		click(ELEMENT_FORUM_PORTLET_CLOSE_BUTTON);
		pageE.finishEditLayout();
		navTool.goToEditPageEditor();
		pageE.goToEditPortlet(ELEMENT_FRAME_CONTAIN_PORTLET);	
		info("Setting to show forum");
		selectDisplayCategoryAndForum(catName + "/"+fmName[0], true);
		click(ELEMENT_FORUM_PORTLET_CLOSE_BUTTON);
		pageE.finishEditLayout();
		acc.signOut();
		acc.signIn(DATA_USER4, DATA_PASS);
		goToForums();
		waitForAndGetElement(By.linkText(catName));
		info("Element checked");
		acc.signOut();
		acc.signIn(DATA_USER1, DATA_PASS);
		goToForums();
		click(By.linkText(catName));
		fmCat.deleteCategoryInForum(catName);
		info("Data deleted");
		info("Test succeed");
	}
}

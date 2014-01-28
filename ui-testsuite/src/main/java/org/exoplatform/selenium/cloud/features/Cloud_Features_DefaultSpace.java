/**
 * Generated by thuntn at 2014/01/28 10:12:22
 *
 * Copyright (C) 2009 eXo Platform SAS.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.exoplatform.selenium.cloud.features;


import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.cloud.CloudBase;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.forum.ForumManageForum;
import org.exoplatform.selenium.platform.forum.ForumManagePost;
import org.exoplatform.selenium.platform.social.ManageMember;
import org.exoplatform.selenium.platform.social.SocialBase;
import org.exoplatform.selenium.platform.social.SpaceManagement;
import org.exoplatform.selenium.platform.wiki.WikiBase;
import org.openqa.selenium.By;
import org.testng.annotations.*;


/**
 * @author thuntn
 *
 */
public class Cloud_Features_DefaultSpace extends SocialBase{

	ManageMember mngMem;
	ManageAccount magAccount;
	SpaceManagement spMng;
	CloudBase cloudBase;
	ForumManagePost post;
	WikiBase wiki;
	ForumManageForum frm;

	@BeforeMethod
	public void beforeMethods() {
		initSeleniumTest();
		driver.get(baseUrl);
		driver.manage().window().maximize();
		magAccount = new ManageAccount(driver);
		mngMem = new ManageMember(driver);
		spMng = new SpaceManagement(driver);
		cloudBase = new CloudBase();
		post = new ForumManagePost(driver);
		wiki = new WikiBase();
		frm = new ForumManageForum(driver);
		
	}

	@AfterMethod
	public void afterMethods() {
		driver.quit();
	}
	/**
	 *<li> Case ID:94237.</li>
	 *<li> Test Case Name: Registration of Default space should be "Open".</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 *<li> Done with OSs and browsers : </li>
	 *<li> Generated by thuntn at 2014/01/28 10:12:22</li>
	 */
	@Test
	public  void test01_RegistrationOfDefaultSpaceShouldBeOpen() {
		info("Test 1: Registration of Default space should be Open");
		/*Step Number: 1
		 *Step Name: 
		 *Step Description: 
			- Connect to Intranet CW
			- Click on "Join Space
		 *Input Data: 

		 *Expected Outcome: 
			- The Space "Getting Started" is displayed on the list 
			- The button "Join" is displayed 
			-> The space is public*/ 
		magAccount.signIn(DATA_USER_JOHN, DATA_PASS);
		click(ELEMENT_JOIN_SPACE_LINK);
		waitForAndGetElement(By.linkText(cloudBase.ELEMENT_DEFAULT_SPACE));
		waitForAndGetElement(mngMem.ELEMENT_ACTION_USER_ON_SPACE.replace("${spaceName}", cloudBase.ELEMENT_DEFAULT_SPACE).replace("${action}", "Join"));


	}



	/**
	 *<li> Case ID:94238.</li>
	 *<li> Test Case Name: Visiblity of Default space should be "Visible".</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 *<li> Done with OSs and browsers : </li>
	 *<li> Generated by thuntn at 2014/01/28 10:12:22</li>
	 */
	@Test
	public  void test02_VisiblityOfDefaultSpaceShouldBeVisible() {
		info("Test 2: Visiblity of Default space should be Visible");
		/*Step Number: 1
		 *Step Name: 
		 *Step Description: 
			- Connect to Intranet CW with "root"
			- Open the default "Getting Started"
			- Go to Space Settings/Access & Edit
		 *Input Data: 

		 *Expected Outcome: 
			- The "Visibility" is set to "Visible"*/ 
		magAccount.signIn("root", "12345");
		click(ELEMENT_JOIN_SPACE_LINK);
		mngMem.gotoEditSpace(cloudBase.ELEMENT_DEFAULT_SPACE);
		click(spMng.ELEMENT_ACCESS_TAB);
		assert waitForAndGetElement(spMng.ELEMENT_ACCESS_EDIT_VISIBLE,DEFAULT_TIMEOUT,1,2).getAttribute("checked") != null : "Fail! The space is not visible";
	}



	/**
	 *<li> Case ID:94239.</li>
	 *<li> Test Case Name: Default space should be displayed with an avatar.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 *<li> Done with OSs and browsers : </li>
	 *<li> Generated by thuntn at 2014/01/28 10:12:22</li>
	 */
	@Test
	public  void test03_DefaultSpaceShouldBeDisplayedWithAnAvatar() {
		info("Test 3: Default space should be displayed with an avatar");
		/*Step Number: 1
		 *Step Name: 
		 *Step Description: 
			- Connect to Intranet CW 
			- Click on "Join Space"
		 *Input Data: 

		 *Expected Outcome: 
			- The Default Space "Getting Started" is displayed with an avatar*/ 
		magAccount.signIn(DATA_USER_JOHN, DATA_PASS);
		click(ELEMENT_JOIN_SPACE_LINK);
		waitForAndGetElement(ELEMENT_SPACE_AVATAR.replace("${space}", cloudBase.ELEMENT_DEFAULT_SPACE).replace("${avatar}", "/rest/jcr/demo/social/production/soc%3Aproviders/soc%3Aspace/soc%3Agetting_started/soc%3Aprofile/soc%3Aavatar/?upd=1390874762479"));


	}



	/**
	 *<li> Case ID:94240.</li>
	 *<li> Test Case Name: Name of Default space should be "Getting Started".</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 *<li> Done with OSs and browsers : </li>
	 *<li> Generated by thuntn at 2014/01/28 10:12:22</li>
	 */
	@Test
	public  void test04_NameOfDefaultSpaceShouldBeGettingStarted() {
		info("Test 4: Name of Default space should be Getting Started");
		/*Step Number: 1
		 *Step Name: 
		 *Step Description: 
			- Connect to Intranet CW 
			- Click on "Join Space"
		 *Input Data: 

		 *Expected Outcome: 
			- A default Spaceis displayed
			- The name of the space is "Getting Started"*/ 
		magAccount.signIn(DATA_USER_JOHN, DATA_PASS);
		click(ELEMENT_JOIN_SPACE_LINK);
		waitForAndGetElement(By.linkText(cloudBase.ELEMENT_DEFAULT_SPACE));
	}



	/**
	 *<li> Case ID:94241.</li>
	 *<li> Test Case Name: Applications should be displayed on default space.</li>
	 *<li> Pre-Condition: User is a member of the default space</li>
	 *<li> Post-Condition: </li>
	 *<li> Done with OSs and browsers : </li>
	 *<li> Generated by thuntn at 2014/01/28 10:12:22</li>
	 */
	@Test
	public  void test05_ApplicationsShouldBeDisplayedOnDefaultSpace() {
		info("Test 5: Applications should be displayed on default space");
		/*Step Number: 1
		 *Step Name: 
		 *Step Description: 
			- Connect to Intranet CW 
			- Open the space "getting Started"
		 *Input Data: 

		 *Expected Outcome: 
			- Applications "Activities, Forums, Wiki, Documents, Agenda, Answer, FAQ and Members are displayed*/ 
		magAccount.signIn(DATA_USER_JOHN, DATA_PASS);
		mngMem.joinOpenSpace(cloudBase.ELEMENT_DEFAULT_SPACE);
		accessSpace(cloudBase.ELEMENT_DEFAULT_SPACE);
		waitForAndGetElement(spMng.ELEMENT_SPACE_MENU_ITEM.replace("${menuItem}","Activity Stream" ));
		waitForAndGetElement(spMng.ELEMENT_SPACE_MENU_ITEM.replace("${menuItem}","Forums" ));
		waitForAndGetElement(spMng.ELEMENT_SPACE_MENU_ITEM.replace("${menuItem}","Wiki" ));
		waitForAndGetElement(spMng.ELEMENT_SPACE_MENU_ITEM.replace("${menuItem}","Documents" ));
		waitForAndGetElement(spMng.ELEMENT_SPACE_MENU_ITEM.replace("${menuItem}","Agenda" ));
		waitForAndGetElement(spMng.ELEMENT_SPACE_MENU_ITEM.replace("${menuItem}","Answer" ));
		waitForAndGetElement(spMng.ELEMENT_SPACE_MENU_ITEM.replace("${menuItem}","FAQ" ));
		waitForAndGetElement(spMng.ELEMENT_SPACE_MENU_ITEM.replace("${menuItem}","Members" ));
		
		goToAllSpaces();
		mngMem.leaveFromSpace(cloudBase.ELEMENT_DEFAULT_SPACE);
	}



	/**
	 *<li> Case ID:94242.</li>
	 *<li> Test Case Name: Default space should be displayed with a description.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 *<li> Done with OSs and browsers : </li>
	 *<li> Generated by thuntn at 2014/01/28 10:12:22</li>
	 */
	@Test
	public  void test06_DefaultSpaceShouldBeDisplayedWithADescription() {
		info("Test 6: Default space should be displayed with a description");
		/*Step Number: 1
		 *Step Name: 
		 *Step Description: 
			- Connect to Intranet CW 
			- Click on "Join Space"
		 *Input Data: 

		 *Expected Outcome: 
			- The Default Space "Getting Started" is displayed
			- A description is displayed on the space: "Get started with your social intranet with a few tips and tricks."*/ 
		magAccount.signIn(DATA_USER_JOHN, DATA_PASS);
		click(ELEMENT_JOIN_SPACE_LINK);
		waitForAndGetElement(ELEMENT_SPACE_DESCRIPTION.replace("${spaceName}", cloudBase.ELEMENT_DEFAULT_SPACE).replace("${description}","Get started with your social intranet with a" ));

	}



	/**
	 *<li> Case ID:94243.</li>
	 *<li> Test Case Name: "Getting Started Guide" should be displayed as a default wiki page.</li>
	 *<li> Pre-Condition: user is member of default space</li>
	 *<li> Post-Condition: </li>
	 *<li> Done with OSs and browsers : </li>
	 *<li> Generated by thuntn at 2014/01/28 10:12:22</li>
	 */
	@Test
	public  void test07_GettingStartedGuideShouldBeDisplayedAsADefaultWikiPage() {
		info("Test 7: Getting Started Guide should be displayed as a default wiki page");
		/*Step Number: 1
		 *Step Name: 
		 *Step Description: 
			- Connect to Intranet CW
			- Open the default space "Getting Started"
			- Open the Wiki application
		 *Input Data: 

		 *Expected Outcome: 
			- The Wiki Home page is replaced by "Getting Started Gadget"
			- The content of the page is: http://int.exoplatform.org/portal/g/:spaces:cloud_workspaces/cloud_workspaces/wiki/Getting_Started_Guide*/ 

		magAccount.signIn(DATA_USER_JOHN, DATA_PASS);
		mngMem.joinOpenSpace(cloudBase.ELEMENT_DEFAULT_SPACE);
		accessSpace(cloudBase.ELEMENT_DEFAULT_SPACE);
		spMng.goToSpaceMenu("Wiki");
		waitForAndGetElement(wiki.ELEMENT_WIKI_HEADER.replace("${header}", "Documents"));
		waitForAndGetElement(wiki.ELEMENT_WIKI_HEADER.replace("${header}", "Spaces"));
		waitForAndGetElement(wiki.ELEMENT_WIKI_HEADER.replace("${header}", "Connections"));
		waitForAndGetElement(wiki.ELEMENT_WIKI_HEADER.replace("${header}", "General"));
		waitForAndGetElement(wiki.ELEMENT_WIKI_HEADER.replace("${header}", "Mobile"));
		
		goToAllSpaces();
		mngMem.leaveFromSpace(cloudBase.ELEMENT_DEFAULT_SPACE);
	}



	/**
	 *<li> Case ID:94244.</li>
	 *<li> Test Case Name: A default Forum should be displayed in the default space.</li>
	 *<li> Pre-Condition: user is member of default space</li>
	 *<li> Post-Condition: </li>
	 *<li> Done with OSs and browsers : </li>
	 *<li> Generated by thuntn at 2014/01/28 10:12:22</li>
	 */
	@Test
	public  void test08_ADefaultForumShouldBeDisplayedInTheDefaultSpace() {
		info("Test 8: A default Forum should be displayed in the default space");
		/*Step Number: 1
		 *Step Name: 
		 *Step Description: 
			- Connect to Intranet CW
			- Open the default space "Getting Started"
			- Open the Forum application
		 *Input Data: 

		 *Expected Outcome: 
			- A default Forum is displayed with a topic name "What do you think about eXo?"
			- The Author of the Topic is "Root"*/
		magAccount.signIn(DATA_USER_JOHN, DATA_PASS);
		mngMem.joinOpenSpace(cloudBase.ELEMENT_DEFAULT_SPACE);
		accessSpace(cloudBase.ELEMENT_DEFAULT_SPACE);
		spMng.goToSpaceMenu("Forums");
		waitForAndGetElement(frm.ELEMENT_TOPIC_LINK.replace("${topic}", "What do you think about eXo?"));
		waitForAndGetElement(frm.ELEMENT_TOPIC_AUTHOR.replace("${topic}", "What do you think about eXo?").replace("${author}", "Support Bot"));

		/*Step number: 2
		 *Step Name: 
		 *Step Description: 
			- Open the Topic
		 *Input Data: 
		
		 *Expected Outcome: 
			- The content of the Topic is displayed to: "Give us your opinion and feature requests at cloud@exoplatform.com!"*/ 

		click(frm.ELEMENT_TOPIC_LINK.replace("${topic}", "What do you think about eXo?"));
		waitForAndGetElement(post.ELEMENT_POST_CONTENT.replace("${postContent}", "Give us your opinion and feature requests at cloud@exoplatform.com!"));
	}



}
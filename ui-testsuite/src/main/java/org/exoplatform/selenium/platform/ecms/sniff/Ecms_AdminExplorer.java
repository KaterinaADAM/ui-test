package org.exoplatform.selenium.platform.ecms.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.platform.HomePagePlatform;
import org.exoplatform.selenium.platform.ManageLogInOut;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.administration.ContentAdministrationManagement;
import org.exoplatform.selenium.platform.administration.ContentAdministrationManagement.mainEcmFunctions;
import org.exoplatform.selenium.platform.administration.ContentAdministrationManagement.specificEcmFunctions;
import org.exoplatform.selenium.platform.objectdatabase.common.TextBoxDatabase;
import org.openqa.selenium.By;
import org.testng.annotations.*;


/**
 * @author rosso
 *
 */
public class Ecms_AdminExplorer extends PlatformBase{


	HomePagePlatform hp;
	ManageLogInOut magAc;
	TextBoxDatabase txData;		
	ContentAdministrationManagement caPage;
	
	@BeforeMethod
	public void beforeMethod(){
		hp.goToPageAdministration();
		caPage.goToSpecificMainFunctions(mainEcmFunctions.EXPLORER);
	}
	
	@BeforeClass
	public void setUpBeforeClass() throws Exception{
		initSeleniumTest();
		getDefaultUserPass(userDataFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		driver.get(baseUrl);
		magAc = new ManageLogInOut(driver);
		magAc.signIn(DATA_USER1, DATA_PASS);
		hp = new HomePagePlatform(driver);
		txData = new TextBoxDatabase();
		caPage= new ContentAdministrationManagement(driver);
		txData.setContentData(texboxFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlContent);
	}	

	@AfterClass
	public void afterClass(){
		magAc.signOut();
		driver.quit();
	}		


	/**
	 *<li> Case ID:116587.</li>
	 *<li> Test Case Name: Add Drive.</li>
	 *<li> Case ID:116623.</li>
	 *<li> Test Case Name: Edit Drive.</li>
	 *<li> Case ID:116624.</li>
	 *<li> Test Case Name: Delete Drive.</li>
	 */
	@Test
	public  void test01_02_03_Add_Edit_Delete_Drive() {
		info("Test 1: Add, edit and delete Drive");
		String title = txData.getContentByArrayTypeRandom(1)+"116587";
		String permission = "any";
		String[] view ={"Admin"};
		String[] newView = {"Web"};
		
		caPage.goToSpecificFunctions(specificEcmFunctions.DRIVES);
		caPage.addDrives(title, permission, view);
		caPage.editDrives(title, newView);
		waitForAndGetElement(By.xpath(caPage.ELEMENT_ECM_EXPLORER_DRIVES_VIEW_OF_VIEWS_LIST.replace("{$name}",title).replace("{$view}",newView[0])));
		// delete drive
		caPage.deleteDrives(title);
		/*Step Number: 1
		 *Step Name: -
		 *Step Description: 
			Step 1:Add Drive
		 *Input Data: 
			- Go to Content Administration/Explorer/ Drives
			- Click on Add Drive button 
			- Put value in required fields
			- Click Save button
		 *Expected Outcome: 
			- A drive is created successfully, when go to Site Explorer , you can see new drive*/ 

	}

	/**
	 *<li> Case ID:116627.</li>
	 *<li> Test Case Name: Add a View.</li>
	 *<li> Case ID:116625.</li>
	 *<li> Test Case Name: Edit a View.</li>
	 *<li> Case ID:116600.</li>
	 *<li> Test Case Name: View a View.</li>
	 *<li> Case ID:116626.</li>
	 *<li> Test Case Name: Delete a View.</li>
	 */
	@Test
	public  void test04_05_06_07_Add_Edit_Delete_View_AView() {
		info("Test 02 : Add, edit, show and delete a View");
		String title = txData.getContentByArrayTypeRandom(1)+"116627";
		String tabName = txData.getContentByArrayTypeRandom(1)+"tab116627";
		String oldPermission = "fqa";
		String[] tab={"Add Category"};
		String permission = "john";
		
		
		hp.goToHomePage();
		hp.goToPageAdministration();
		caPage.goToSpecificFunctions(specificEcmFunctions.VIEW);
		// add a view
		caPage.addView(title, tabName, tab, oldPermission);
		// show a view
		click(By.xpath(caPage.ELEMENT_ECM_EXPLORER_VIEW_SHOW_A_VIEW_LIST.replace("{$name}",title)));
		waitForAndGetElement(By.xpath(caPage.ELEMENT_ECM_EXPLORER_NAME_VIEW_SHOW_VIEW.replace("{$name}",title)));
		click(caPage.ELEMENT_ECM_EXPLORER_GO_TO_ACTION_FORM);
		waitForAndGetElement(By.xpath(caPage.ELEMENT_ECM_EXPLORER_TAB_ICONS_LIST_SHOW_VIEW.replace("{$tab}",tab[0])));
		click(caPage.ELEMENT_ECM_EXPLORER_CLOSE_VIEW_MODE);
		// edit a view
		caPage.editViewPermissionUser(title, "Fqa", permission);
		waitForAndGetElement(By.xpath(caPage.ELEMENT_ECM_EXPLORER_VIEW_PERMISSIONS_LIST.replace("{$name}",title).replace("{$permission}","John")));
		// delete a view
		caPage.deleteView(title);
		
		/*Step Number: 1
		 *Step Name: -
		 *Step Description: 
			Step 1:Add a View
		 *Input Data: 
			- Go to Content Administration/ Explorer/ Views 
			- Click Add View button
			- Put value in required field
			- Click Add Tab, fill name and tick on action for tabs
			- Click Save button
		 *Expected Outcome: 
			- A new view is created successfully*/ 

	}
	
	/**
	 *<li> Case ID:116598.</li>
	 *<li> Test Case Name: Add Tags.</li>
	 *<li> Case ID:116621.</li>
	 *<li> Test Case Name: Edit Tags.</li>
	 *<li> Case ID:116622.</li>
	 *<li> Test Case Name: Delete Tags.</li>
	 */
	@Test
	public  void test08_09_10_Add_Edit_Delete_Tags() {
		info("Test 03: Add, edit and delete Tags");
		String title = txData.getContentByArrayTypeRandom(1)+"116598";
		String occurences = "1..*";
		String html="font-size:10px;";
		String oldHtml = "font-size:12px;";
		
		hp.goToHomePage();
		hp.goToPageAdministration();
		caPage.goToSpecificFunctions(specificEcmFunctions.TAGS);
		caPage.addTags(title, occurences, oldHtml);
		caPage.updateTags(title, null, html);
		waitForAndGetElement(By.xpath(caPage.ELEMENT_ECM_EXPLORER_TAGS_LIST_CHECK_HTML_CONTENT.replace("{$name}",title).replace("{$html}",html)));
		caPage.deleteTags(title);
	}

}
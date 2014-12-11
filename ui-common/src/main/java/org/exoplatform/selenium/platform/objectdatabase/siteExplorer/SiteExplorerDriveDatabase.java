package org.exoplatform.selenium.platform.objectdatabase.siteExplorer;

import static org.exoplatform.selenium.TestLogger.info;

import java.util.ArrayList;
import java.util.Random;

import org.exoplatform.selenium.platform.objectdatabase.common.DatabaseResource;

public class SiteExplorerDriveDatabase {
	public ArrayList<String> siteExDrive;

	public SiteExplorerDriveDatabase(ArrayList<String> siteExDrive){
		this.siteExDrive = siteExDrive;
	}

	public SiteExplorerDriveDatabase() {
		siteExDrive  = new ArrayList<String>();
	}

	public void setSiteExpDriveData(String userDataFile, String userSheet, Object... opParams) throws Exception{
		String[][] testData = DatabaseResource.getDataFromSource(userDataFile,userSheet,opParams);
		for(int i = 0; i<testData.length; i++)
		{	
			siteExDrive.add(testData[i][0]);
			info("siteExDrive is "+ siteExDrive.get(i));
		}
	}
	
	public String getSiteExpDriveByIndex(int index){
		info("name drive is "+ siteExDrive.get(index));
		return siteExDrive.get(index);
	}

	public String getSiteExpDriveRandom(){
		Random randomGenerator = new Random();
		int index = randomGenerator.nextInt(this.siteExDrive.size());
		info("name drive is "+ siteExDrive.get(index));
		return this.siteExDrive.get(index);
	}
}

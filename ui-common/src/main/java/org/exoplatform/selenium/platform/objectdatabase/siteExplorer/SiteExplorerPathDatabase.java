package org.exoplatform.selenium.platform.objectdatabase.siteExplorer;

import static org.exoplatform.selenium.TestLogger.info;

import java.util.ArrayList;
import java.util.Random;

import org.exoplatform.selenium.platform.objectdatabase.common.DatabaseResource;

public class SiteExplorerPathDatabase {
	public ArrayList<String> siteExPath;

	public SiteExplorerPathDatabase(ArrayList<String> spaceReg){
		this.siteExPath = spaceReg;
	}

	public SiteExplorerPathDatabase() {
		siteExPath  = new ArrayList<String>();
	}

	public void setSiteExpPathData(String userDataFile, String userSheet, Object... opParams) throws Exception{
		String[][] testData = DatabaseResource.getDataFromSource(userDataFile,userSheet,opParams);
		for(int i = 0; i<testData.length; i++)
		{	
			siteExPath.add(testData[i][0]);
		}
	}
	
	public String getSiteExpPathByIndex(int index){
		info("path of site explorer is: "+siteExPath.get(index));
		return siteExPath.get(index);
	}

	public String getSiteExpPathRandom(){
		Random randomGenerator = new Random();
		int index = randomGenerator.nextInt(this.siteExPath.size());
		info("path of site explorer is: "+siteExPath.get(index));
		return this.siteExPath.get(index);
	}
}

package org.exoplatform.selenium.platform.objectdatabase.documentpreview;

import java.util.ArrayList;
import org.exoplatform.selenium.platform.objectdatabase.common.DatabaseResource;

public class PreviewMessageSearchDatabase {
	public ArrayList<String> mesgSearch;

	public PreviewMessageSearchDatabase(ArrayList<String> mesgSearch){
		this.mesgSearch = mesgSearch;
	}

	public PreviewMessageSearchDatabase() {
		mesgSearch  = new ArrayList<String>();
	}

	public void setMesgSearchData(String userDataFile, String userSheet, Object... opParams) throws Exception{
		String[][] testData = DatabaseResource.getDataFromSource(userDataFile,userSheet,opParams);
		for(int i = 0; i<testData.length; i++)
		{	
			mesgSearch.add(testData[i][0]);
		}
	}
	
	public String getMesgSearchByIndex(int index){
		return mesgSearch.get(index);
	}

}

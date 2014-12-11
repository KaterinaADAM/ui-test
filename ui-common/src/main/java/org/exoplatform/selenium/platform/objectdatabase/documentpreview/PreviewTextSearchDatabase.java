package org.exoplatform.selenium.platform.objectdatabase.documentpreview;

import static org.exoplatform.selenium.TestLogger.info;

import java.util.ArrayList;
import java.util.Random;

import org.exoplatform.selenium.platform.objectdatabase.common.DatabaseResource;

public class PreviewTextSearchDatabase {
	public ArrayList<Integer> type;
	public ArrayList<String> textSearch;

	public PreviewTextSearchDatabase(ArrayList<Integer> type,ArrayList<String> textSearch){
		this.type=type;
		this.textSearch = textSearch;
	}

	public PreviewTextSearchDatabase() {
		type = new ArrayList<Integer>();
		textSearch  = new ArrayList<String>();
	}

	public void setTextSearchData(String userDataFile, String userSheet, Object... opParams) throws Exception{
		String[][] testData = DatabaseResource.getDataFromSource(userDataFile,userSheet,opParams);
		for(int i = 0; i<testData.length-1; i++)
		{	
			type.add(Integer.valueOf(testData[i][0]));
			textSearch.add(testData[i][1]);
		}
	}
	
	public String getTextSearchByIndex(int index){
		return textSearch.get(index);
	}
	
	
	public Integer getRandomIndexByType(int type){
		ArrayList<String> arrayContent = new ArrayList<String>();
		Random randomGenerator = new Random();
		info("this.type.size : "+ this.type.size());
		for(int i = 0; i<this.type.size(); i++)
		{	
			if(this.type.get(i) == type) {
				arrayContent.add(this.textSearch.get(i));
				info("text searhc of i is :"+ i + " "+ this.textSearch.get(i));
			}
		}
		Integer index = randomGenerator.nextInt(arrayContent.size());
		info("index is: "+ index);
		return index;
	}

}

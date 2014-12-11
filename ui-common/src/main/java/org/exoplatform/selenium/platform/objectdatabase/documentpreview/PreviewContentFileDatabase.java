package org.exoplatform.selenium.platform.objectdatabase.documentpreview;

import java.util.ArrayList;
import java.util.Random;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.platform.objectdatabase.common.DatabaseResource;

public class PreviewContentFileDatabase {
	public ArrayList<Integer> type;
	public ArrayList<String> file;
	public ArrayList<String> firstPg;
	public ArrayList<String> lastPg;
	public ArrayList<String> totalPgNum;
	public ArrayList<String> inputPgNum;
	public ArrayList<String> textInputPgNum;

	public PreviewContentFileDatabase(ArrayList<Integer> type, ArrayList<String> file,ArrayList<String> firstPg,ArrayList<String> lastPg,
			ArrayList<String> totalPgNum,ArrayList<String> inputPgNum,ArrayList<String> textInputPgNum){
		this.type = type;
		this.file = file;
		this.firstPg= firstPg;
		this.lastPg = lastPg;
		this.totalPgNum= totalPgNum;
		this.inputPgNum= inputPgNum;
		this.textInputPgNum= textInputPgNum;
	}

	public PreviewContentFileDatabase() {
		type  = new ArrayList<Integer>();
		file = new ArrayList<String>();
		firstPg  = new ArrayList<String>();
		lastPg  = new ArrayList<String>();
		totalPgNum  = new ArrayList<String>();
		inputPgNum= new ArrayList<String>();
		textInputPgNum= new ArrayList<String>();
	}

	public void setTextContentFileData(String userDataFile, String userSheet,Object... opParams) throws Exception{
		String[][] testData = DatabaseResource.getDataFromSource(userDataFile,userSheet,opParams);
		info("testData.length:"+testData.length);
		for(int i = 0; i<testData.length-1; i++)
		{	
			type.add(Integer.valueOf(testData[i][0]));
			file.add(testData[i][1]);
			firstPg.add(testData[i][2]);
			lastPg.add(testData[i][3]);
			totalPgNum.add(testData[i][4]);
			inputPgNum.add(testData[i][5]);
			textInputPgNum.add(testData[i][6]);
			info("i is:"+i+"_"+testData[i][1]);
		}
	}
	
	/**
	 * Get text content of first page by index
	 * @param index
	 * @return
	 */
	public String getContentFirstPgByIndex(ArrayList<String> nameArray,int index){
		return nameArray.get(index);
	}
	
	
	public Integer getRandomIndexByType(int type){
		ArrayList<String> arrayContent = new ArrayList<String>();
		Random randomGenerator = new Random();
		info("this.type.size : "+ this.type.size());
		for(int i = 0; i<this.type.size(); i++)
		{	
			if(this.type.get(i) == type) {
				arrayContent.add(this.file.get(i));
				info("i is :"+ i);
			}
		}
		Integer index = randomGenerator.nextInt(arrayContent.size());
		info("index is: "+ index);
		return index;
	}
	
}

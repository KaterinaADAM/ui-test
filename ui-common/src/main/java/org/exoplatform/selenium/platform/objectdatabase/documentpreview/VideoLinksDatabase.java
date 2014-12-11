package org.exoplatform.selenium.platform.objectdatabase.documentpreview;

import static org.exoplatform.selenium.TestLogger.info;

import java.util.ArrayList;
import java.util.Random;

import org.exoplatform.selenium.platform.objectdatabase.common.DatabaseResource;

public class VideoLinksDatabase {
	public ArrayList<Integer> type;
	public ArrayList<String> name;

	/**
	 * VideoLinksFileDatabase
	 * @param type
	 * @param attachName
	 */
	public VideoLinksDatabase(ArrayList<Integer> type, ArrayList<String> name){
		this.type = type;
		this.name = name;
	}

	/**
	 * VideoLinksFileDatabase
	 */
	public VideoLinksDatabase(){
		type  = new ArrayList<Integer>();
		name  = new ArrayList<String>();
	}

	/**
	 * setVideoLinksData
	 * @param userDataFile
	 * @param userSheet
	 * @param opParams
	 * @throws Exception
	 */
	public void setVideoLinksData(String userDataFile, String userSheet, Object... opParams) throws Exception{
		String[][] testData = DatabaseResource.getDataFromSource(userDataFile,userSheet,opParams);
		for(int i = 0; i<testData.length-1; i++)
		{	
			info("Integer.valueOf(testData[i][0]):"+Integer.valueOf(testData[i][0]));
			type.add(Integer.valueOf(testData[i][0]));
			name.add(testData[i][1]);
		}
	}

	/**
	 * Get file name by index
	 * @param index
	 * @return
	 */
	public String getVideoLinksByIndex(int index){
		info("AttachFile of site explorer is: "+name.get(index));
		return name.get(index);
	}
	
	/**
	 * get file name random
	 * @return
	 */
	public String getVideoLinksRandom(){
		Random randomGenerator = new Random();
		int index = randomGenerator.nextInt(this.name.size());
		String videoLinks = this.name.get(index);
		info("Video links is: "+videoLinks);
		return videoLinks;
	}

	/**
	 * getArrayAttachFileByType
	 * @param type
	 * @return
	 */
	public ArrayList<String> getArrayVideoLinksByType(int type){
		ArrayList<String> arrayVideoLinks = new ArrayList<String>();
		for(int i = 0; i<this.type.size(); i++)
		{	
			if(this.type.get(i) == type) {
				arrayVideoLinks.add(this.name.get(i));
			}
		}
		return arrayVideoLinks;
	}

	/**
	 * getArrayAttachFileByArrayType
	 * @param type
	 * @return
	 */
	public ArrayList<String> getArrayVideoLinksByArrayType(int...type){
		ArrayList<String> arrayVideoLinks = new ArrayList<String>();
		for (int j = 0; j<type.length; j++){
			for(int i = 0; i<this.type.size(); i++)
			{	
				if(this.type.get(i) == type[j]) {
					arrayVideoLinks.add(this.name.get(i));
				}
			}
		}
		return arrayVideoLinks;
	}

	/**
	 * getAttachFileByArrayTypeRandom
	 * @param type
	 * @return
	 */
	public String getVideoLinksByArrayTypeRandom(int...type){
		ArrayList<String> arrayVideoLinks = new ArrayList<String>();
		Random randomGenerator = new Random();
		for (int j = 0; j<type.length; j++){
			for(int i = 0; i<this.type.size(); i++)
			{	
				if(this.type.get(i) == type[j]) {
					arrayVideoLinks.add(this.name.get(i));
				}
			}
		}
		int index = randomGenerator.nextInt(arrayVideoLinks.size());
		String attachFile = arrayVideoLinks.get(index);
		info("AttachFile of site explorer is: "+attachFile);
		return attachFile;
	}
}

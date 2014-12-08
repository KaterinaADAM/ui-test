package org.exoplatform.selenium.platform.objectdatabase.user;

import java.util.ArrayList;

import org.exoplatform.selenium.platform.objectdatabase.common.DatabaseResource;

public class UserDatabase {
	public ArrayList<Integer> type;
	public ArrayList<String> userName;
	public ArrayList<String> password;
	public ArrayList<String> fullName;

	public UserDatabase(ArrayList<Integer> type, ArrayList<String> userName, ArrayList<String> password, ArrayList<String> fullName){
		this.type = type;
		this.userName = userName;
		this.password = password;
		this.fullName = fullName;
	}

	public UserDatabase(){
		type  = new ArrayList<Integer>();
		userName  = new ArrayList<String>();
		password  = new ArrayList<String>();
		fullName  = new ArrayList<String>();
	}

	public void setUserData(String userDataFile, String userSheet, Object... opParams) throws Exception{
		String[][] testData = DatabaseResource.getDataFromSource(userDataFile,userSheet,opParams);
		for(int i = 0; i<testData.length; i++)
		{	
			type.add(Integer.valueOf(testData[i][0]));
			userName.add(testData[i][1]);
			password.add(testData[i][2]);
			fullName.add(testData[i][3]);
		}
	}
}

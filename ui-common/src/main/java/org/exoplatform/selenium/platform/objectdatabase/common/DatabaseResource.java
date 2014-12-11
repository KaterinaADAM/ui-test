package org.exoplatform.selenium.platform.objectdatabase.common;

public class DatabaseResource {
	public static String[][] getDataFromSource(String userDataFile, String userSheet, Object... opParams) throws Exception{
		Boolean isUseFile = (Boolean)(opParams.length > 0 ? opParams[0]: true);
		String jdbcDriver = (String)(opParams.length > 1 ? opParams[1]: "");
		String dbUrl = (String)(opParams.length > 2 ? opParams[2]: "");
		String user = (String)(opParams.length > 3 ? opParams[3]: "");
		String pass = (String)(opParams.length > 4 ? opParams[4]: "");
		String sqlDb = (String)(opParams.length > 5 ? opParams[5]: "");
		String[][] arrayData = null;
		if(isUseFile){
			ExcelUtils.setExcelFile(userDataFile,userSheet);
			arrayData = ExcelUtils.getData();
		}
		else{
			DatabaseUtils.connectDatabase(jdbcDriver, dbUrl, user, pass);
			arrayData=DatabaseUtils.getData(sqlDb);
		}
		return arrayData;
	}
	
	
}

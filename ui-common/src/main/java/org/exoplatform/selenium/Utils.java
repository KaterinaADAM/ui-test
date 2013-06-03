package org.exoplatform.selenium;

import static org.exoplatform.selenium.TestLogger.*;

import java.awt.AWTException;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

import org.openqa.selenium.WebDriver;


/**
 * Utils.java
 * @author vuna2
 *
 */
public class Utils {
	//public WebDriver driver;
	//
	public static void pause(long timeInMillis) {
		try {
			Thread.sleep(timeInMillis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}	

	/*public static void clearCache(){
		Actions actionObject = new Actions(driver);
		try{
			actionObject.sendKeys(Keys.CONTROL).sendKeys(Keys.F5).build().perform();
		} catch(WebDriverException e){	
			debug("Retrying clear cache...");
			actionObject.sendKeys(Keys.CONTROL).sendKeys(Keys.F5).build().perform();
		}
	}*/

	/**
	 * Capture the screen of the current graphics device
	 * @author vuna2
	 * @param fileName: input an image name (String)
	 * @throws InterruptedException
	 */
	public static void captureScreen(String fileName){
		String path;
		BufferedImage screenCapture;
		//		Thread.sleep(3000);
		pause(3000);
		try {
			Robot robot = new Robot();
			Rectangle screenSize = getScreenSize();
			screenCapture = robot.createScreenCapture(screenSize);
			// Save as PNG
			String curDir = System.getProperty("user.dir");
			path = curDir + "/target/screenshoot/";
			File f = new File(path);
			if (!f.exists()) f.mkdir(); 
			ImageIO.write(screenCapture, "png", new File(path + fileName));

		}catch (AWTException e) {
			error("Failed to capture screenshot");
		}catch(IOException e)
		{
			path = "Failed to capture screenshot: " + e.getMessage();
		}
	}

	/**
	 * 
	 * @return the size of the default screen
	 */
	public static Rectangle getScreenSize() {
		GraphicsEnvironment graphE = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice graphD = graphE.getDefaultScreenDevice();
		DisplayMode displayM = graphD.getDisplayMode();
		return new Rectangle(displayM.getWidth(), displayM.getHeight());
	}

	//
	/**
	 * Simulating keyboard presses 
	 * @author vuna2
	 * @param firstKey: send the first key (type: KeyEvent)
	 * @param secondKey: send the second key (type: KeyEvent) 
	 * @throws InterruptedException 
	 */
	public static void javaSimulateKeyPress(int firstKey, Object... params) throws InterruptedException{
		int secondKey = (Integer) (params.length > 0 ? params[0]: KeyEvent.VK_ENTER); 
		try {
			Robot robot = new Robot();
			// Simulate a key press
			robot.keyPress(firstKey);
			robot.keyPress(secondKey);
			Thread.sleep(3000);
			robot.keyRelease(secondKey);
			robot.keyRelease(firstKey);

		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	/*---- Auxiliary functions ----
	public static void captureScreen(String fileName){
		String path;
		try {
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			//			relativeFilePath = "target/screenshot/" + fileName;
			String curDir = System.getProperty("user.dir");
			path = curDir + "/target/screenshoot/" + fileName;
			FileUtils.copyFile(scrFile, new File(path));
		} catch (IOException e) {
			path = "Failed to capture screenshot: " + e.getMessage();
		}catch(UnknownServerException e)
		{
			error("Failed to capture screenshot");
		}
	}
	 */

	//This function returns a absolute path from a relative path
	public static String getAbsoluteFilePath(String relativeFilePath){
		String curDir = System.getProperty("user.dir");
		String absolutePath = curDir + "/src/main/resources/" + relativeFilePath;
		return absolutePath;
	}

	//InputStream to String
	//Get a File Content
	public static String getFileContent(String filePath){
		String path = getAbsoluteFilePath(filePath);
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			error("Failed to find location of... " + filePath);
		}
		String inputStreamString = new Scanner(fis,"UTF-8").useDelimiter("\\A").next();		
		return inputStreamString;	
	}

	//Get a file name from current Url
	public static String getFileNameFromCurrentUrl(WebDriver driver, Object...params){
		Boolean extension = (Boolean) (params.length > 0 ? params[0] : false);

		String currentUrl = driver.getCurrentUrl();
		File file = new File(currentUrl);
		String fileNameWithExt = file.getName();
		
		if (extension){
			int position = fileNameWithExt.lastIndexOf(".");
			String fileNameWithOutExt = null;
			if (position >= 0) {
				fileNameWithOutExt = fileNameWithExt.substring(0, position);
			}else{
				fileNameWithOutExt = fileNameWithExt;
			}
			return fileNameWithOutExt;
		}else {
			return fileNameWithExt;
		}
	}
}
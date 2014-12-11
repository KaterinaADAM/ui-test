package org.exoplatform.selenium;

import java.util.Set;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
 
@SuppressWarnings("deprecation")
public class DownloadFileControl {
 
	static WebDriver driver;
 
    public DownloadFileControl(WebDriver driverObject) {
		driver = driverObject;
	}
    /**
     * Check download link
     * @param downloadUrl
     * @throws Exception
     */
    public void downloadFile(String downloadUrl) throws Exception {
 
        CookieStore cookieStore = seleniumCookiesToCookieStore();
        @SuppressWarnings("resource")
		DefaultHttpClient httpClient = new DefaultHttpClient();
        httpClient.setCookieStore(cookieStore);
 
        HttpGet httpGet = new HttpGet(downloadUrl);
        System.out.println("Downloding file form: " + downloadUrl);
        HttpResponse response = httpClient.execute(httpGet);
 
        int httpStatusCode;
        httpStatusCode=response.getStatusLine().getStatusCode();
        System.out.println("httpStatusCode:"+httpStatusCode);
        
        if (httpStatusCode==200) {
        	assert true;
            System.out.println("Downloded successfully");
        }
        else {
        	assert false;
            System.out.println("Download failed!");
        }
        Utils.pause(3000);
    }
    /**
     * Add cookies
     * @return cookieStore
     */
    private static CookieStore seleniumCookiesToCookieStore() {
 
        Set<Cookie> seleniumCookies = driver.manage().getCookies();
        CookieStore cookieStore = new BasicCookieStore();
 
        for(Cookie seleniumCookie : seleniumCookies){
            BasicClientCookie basicClientCookie =
                    new BasicClientCookie(seleniumCookie.getName(), seleniumCookie.getValue());
            basicClientCookie.setDomain(seleniumCookie.getDomain());
            basicClientCookie.setExpiryDate(seleniumCookie.getExpiry());
            basicClientCookie.setPath(seleniumCookie.getPath());
            cookieStore.addCookie(basicClientCookie);
        }
 
        return cookieStore;
    }
}


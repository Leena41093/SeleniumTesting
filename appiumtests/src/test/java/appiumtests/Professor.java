package appiumtests;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class Professor {
	
	//WebDriver driver;
	static  AppiumDriver<MobileElement> driver;
	//AndroidDriver driver;
	
	public static void main (String[]args){
		try {
			openStudent();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			e.getCause();
			e.getMessage();
		}
	
	}
   public static void openStudent() throws Exception{
	   DesiredCapabilities cap = new DesiredCapabilities();
//	   cap.setCapability("deviceName","Redmi Y2");
////	   cap.setCapability("deviceName", "Moto G5 Plus");
//	   cap.setCapability("udid","330200f2");
////	   cap.setCapability("udid","ZY223ZMQHL");
//	   cap.setCapability("platformName","Android");
//	   cap.setCapability("platformVersion","8.1.0");
//	   cap.setCapability("appPackage","com.cleverground_professor_app");
//	   cap.setCapability("appActivity","com.cleverground_professor_app.MainActivity");
//	   cap.setCapability("automationName","UiAutomator2");
//////	   cap.setCapability("app", "/storage/emulated/0/Download/app-release.apk");
	   
	   cap.setCapability("deviceName","Galaxy Nexus");
//   cap.setCapability("deviceName", "Moto G5 Plus");
   cap.setCapability("udid","emulator-5554");
//   cap.setCapability("udid","ZY223ZMQHL");
   cap.setCapability("platformName","Android");
   cap.setCapability("platformVersion","5.1,1");
   cap.setCapability("appPackage","com.cleverground_professor_app");
   cap.setCapability("appActivity","com.cleverground_professor_app.MainActivity");
   cap.setCapability("automationName","UiAutomator2");
//      cap.setCapability("adbExecTimeout", 250000);
//   cap.setCapability("newCommandTimeout", 20000);
////   cap.setCapability("app", "/storage/emulated/0/Download/app-release.apk");
	   URL url = new URL("http://127.0.0.1:4723/wd/hub");
	   driver =new AppiumDriver<MobileElement>(url,cap);
	   MobileElement loginId = driver.findElement(By.className("android.widget.EditText"));
	   loginId.click();
//	   loginId.sendKeys("leena4@gmail.com");
	   System.out.println("Application Started...");
   }
}

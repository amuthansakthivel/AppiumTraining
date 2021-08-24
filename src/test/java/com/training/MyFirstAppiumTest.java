package com.training;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class MyFirstAppiumTest {

    //java programs will start from main method
    public static void main(String[] args) throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
       // capabilities.setCapability("app", System.getProperty("user.dir")+"/ApiDemos-debug.apk");
        capabilities.setCapability("appPackage","io.appium.android.apis");
        capabilities.setCapability("appActivity",".ApiDemos");
        AndroidDriver<AndroidElement> driver =
                new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
        //Launch the app in the mobile

        //Locator strategy
        //driver.findElementByAccessibilityId("Views").click(); //content-desc
        //driver.findElementByXPath("//android.widget.TextView[@text='Chronometer']").click(); //Xpath
        //System.out.println(driver.findElementByClassName("android.widget.TextView").getText()); //singular
        List<AndroidElement> elements = driver.findElementsByClassName("android.widget.TextView");//classname
        List<AndroidElement> elements1 = driver.findElementsById("android:id/text1"); //id
        //uiautomator
        for(int i=0;i< elements1.size();i++){
            System.out.println(elements1.get(i).getText());
        }

        //long press //swipe //drag and drop
        //driver.findElementById()

    }

}

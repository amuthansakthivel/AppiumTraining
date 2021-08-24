package com.training;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class BrowserStackTest {
    //remote server

    public static void main(String[] args) throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("app", "bs://b38557fcfe3048b6b46e26dc35ddb4f3c870832f");
        capabilities.setCapability("browserstack.user","amuthansakthivel1");
        capabilities.setCapability("browserstack.key","xET1z5tchDwDZ4Anwepa");
        capabilities.setCapability("device","Google Pixel 3");//lot of devices
        capabilities.setCapability("os_version","9.0"); //lot of version
        capabilities.setCapability("project", "First Java Project"); //to distinguish project
        capabilities.setCapability("build", "Java Android"); //to diff release
        capabilities.setCapability("name", "myFirstBSTest"); //to diff tests
        capabilities.setCapability("appPackage","io.appium.android.apis");
        capabilities.setCapability("appActivity",".ApiDemos");
        AndroidDriver<AndroidElement> driver =
                new AndroidDriver<AndroidElement>(new URL("http://hub.browserstack.com/wd/hub"),capabilities);

        //native android commands

        driver.findElementByAndroidUIAutomator("new UiSelector().description(\"Views\")").click();
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"TextFields\"))").click();

        //scroll to the element and then click

        driver.quit(); //at the end  appium server will wait 60s--> no more request from client -->session
    }

    //click, diff locator strategies, physical,emulator and any other cloud
}

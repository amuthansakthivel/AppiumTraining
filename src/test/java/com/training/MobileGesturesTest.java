package com.training;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class MobileGesturesTest {
    //Mobile Gesture --> tap,tap using coordinates, double tap ,long press, swipe, drag ana drop
    //Extent reports
    //Screenshot
    //recording of our local tests

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

        //click on Views
        //1. tap on views
        AndroidElement views = driver.findElementByAccessibilityId("Views");
        views.click();
        driver.findElementByAccessibilityId("Drag and Drop").click();
        AndroidElement source = driver.findElementById("io.appium.android.apis:id/drag_dot_1");
        AndroidElement target = driver.findElementById("io.appium.android.apis:id/drag_dot_2");

        dragAndDrop(driver,source,target);
        System.out.println(driver.findElementById("io.appium.android.apis:id/drag_result_text").getText());


        driver.quit(); //to call driver.quit even when there is a failure in the test
    }
    private static void dragAndDrop(AndroidDriver<AndroidElement> driver, AndroidElement source, AndroidElement target) {
        AndroidTouchAction ta = new AndroidTouchAction(driver);
        ta.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(source))).
                waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2))).
                moveTo(ElementOption.element(target)).release().perform();
    }


    private static void swipe(AndroidDriver<AndroidElement> driver, AndroidElement source, AndroidElement target) {
        AndroidTouchAction ta = new AndroidTouchAction(driver);
        ta.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(source))).
                moveTo(ElementOption.element(target)).release().perform();
    }

    private static void click(AndroidElement element) {
        element.click();
    }

    private static void tapUsingCoordinates(AndroidDriver<AndroidElement> driver, int x, int y) {
        AndroidTouchAction ta = new AndroidTouchAction(driver);
        ta.tap(PointOption.point(x,y)).perform();
    }

    private static void tapOnElement(AndroidDriver<AndroidElement> driver, AndroidElement element) {
        AndroidTouchAction ta = new AndroidTouchAction(driver);
        ta.tap(TapOptions.tapOptions().withElement(ElementOption.element(element))).perform();
    }

    /*
     tapUsingCoordinates(driver,490,1080);
        click(driver.findElementByAccessibilityId("2. Inline"));//failure
        click(driver.findElementByAccessibilityId("3"));

        AndroidElement source = driver.findElementByAccessibilityId("15");
        AndroidElement target = driver.findElementByAccessibilityId("45");
        swipe(driver,source,target);
        System.out.println(driver.findElementById("android:id/minutes").getText());
     */

}

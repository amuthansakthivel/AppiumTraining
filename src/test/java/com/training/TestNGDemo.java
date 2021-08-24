package com.training;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import org.aspectj.weaver.ast.And;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class TestNGDemo {
    //Testng testcase
    //execute certain things before executing testcase --> open the browser,perform login
    //after executing test -->logout,close the browser

    AndroidDriver<AndroidElement> driver = null; //2 parallel execution

    ThreadLocal<AndroidDriver<AndroidElement>> threadSafe = new ThreadLocal<>();



    @BeforeMethod
    public void setUp(Method m) throws MalformedURLException { //it is way to find the test name at runtime
        DesiredCapabilities capabilities = new DesiredCapabilities();

        if(m.getName().equalsIgnoreCase("swipeTest")){
            capabilities.setCapability("device","Google Pixel 3");//lot of devices
        }else{
            capabilities.setCapability("device","Samsung Galaxy S10e");//lot of devices
        }
        capabilities.setCapability("app", "bs://b38557fcfe3048b6b46e26dc35ddb4f3c870832f");
        capabilities.setCapability("browserstack.user","amuthansakthivel1");
        capabilities.setCapability("browserstack.key","xET1z5tchDwDZ4Anwepa");

        capabilities.setCapability("os_version","9.0"); //lot of version
        capabilities.setCapability("project", "First Java Project"); //to distinguish project
        capabilities.setCapability("build", "Java Android"); //to diff release
        capabilities.setCapability("name", m.getName()); //to diff tests
        capabilities.setCapability("appPackage","io.appium.android.apis");
        capabilities.setCapability("appActivity",".ApiDemos");
        driver = new AndroidDriver<AndroidElement>(new URL("http://hub.browserstack.com/wd/hub"),capabilities);
        threadSafe.set(driver);
    }

    @AfterMethod
    public void close(){
        threadSafe.get().quit();
    }

    @Test
    public void swipeTest(){ //local variable
        //whatever code you are writing inside is a testcase
        AndroidElement views = threadSafe.get().findElementByAccessibilityId("Views");
        views.click();
        threadSafe.get().findElementByAccessibilityId("Date Widgets").click();//1st thread
        threadSafe.get().findElementByAccessibilityId("2. Inline").click(); //2nd thread
        threadSafe.get().findElementByAccessibilityId("3").click();
        AndroidElement source =  threadSafe.get().findElementByAccessibilityId("15");
        AndroidElement target =  threadSafe.get().findElementByAccessibilityId("45");
        swipe(threadSafe.get(),source,target);

    }

    @Test
    public void dragAndDropTest(){ //alphabetically
        //whatever code you are writing inside is a testcase
        AndroidElement views = threadSafe.get().findElementByAccessibilityId("Views");
        views.click();
        threadSafe.get().findElementByAccessibilityId("Drag and Drop").click();
        AndroidElement source = threadSafe.get().findElementById("io.appium.android.apis:id/drag_dot_1");
        AndroidElement target = threadSafe.get().findElementById("io.appium.android.apis:id/drag_dot_2");

        dragAndDrop(threadSafe.get(),source,target);
        System.out.println(threadSafe.get().findElementById("io.appium.android.apis:id/drag_result_text").getText());
    }

    private static void dragAndDrop(AndroidDriver<AndroidElement> driver, AndroidElement source, AndroidElement target) {
        AndroidTouchAction ta = new AndroidTouchAction(driver);
        ta.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(source))).
                waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2))).
                moveTo(ElementOption.element(target)).release().perform();
    }
    private static void swipe(AndroidDriver<AndroidElement> driver, AndroidElement source, AndroidElement target){
        AndroidTouchAction ta = new AndroidTouchAction(driver);
        ta.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(source))).
               moveTo(ElementOption.element(target)).release().perform();
    }



}

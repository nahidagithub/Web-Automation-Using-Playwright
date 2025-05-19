package org.example;

import com.microsoft.playwright.*;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Assertion {
    Playwright playwright;
    BrowserType browserType;
    Browser browser;
    BrowserContext browserContext;
    Page page;
    @BeforeSuite

    public void Start(){
        playwright=Playwright.create();
        browserType=playwright.chromium();
        browser=browserType.launch(new BrowserType.LaunchOptions().setHeadless(false));
        browserContext=browser.newContext();
        page=browser.newPage();
    }
    @Test
    public  void openurl(){
        page.navigate("https://testing-and-learning-hub.vercel.app/WebAutomation/contents/basic.html");
    }
    @Test(dependsOnMethods = "openurl")
    public void hardAssertion(){
        String actualTitle=page.title();
        String expectedTitle="Testing And Learning Hub";
        Assert.assertEquals(actualTitle,expectedTitle);
        System.out.println("Title: " + actualTitle);
    }
    @Test(dependsOnMethods = "openurl")
    public void softAssertion() throws InterruptedException {
        SoftAssert softAssert=new SoftAssert();
        String actualTitle=page.title();
        String expectedTitle="Testing And Learning Hub";
        softAssert.assertEquals(actualTitle,expectedTitle);
        System.out.println("Title: " +actualTitle);
        softAssert.assertAll();

    }
    @AfterSuite
    public  void stop(){
        page.close();
        browser.close();
        playwright.close();
    }

}

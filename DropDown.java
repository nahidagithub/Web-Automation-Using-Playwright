package org.example;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class DropDown {
    Playwright playwright;
    BrowserType browserType;
    Browser browser;
    BrowserContext browserContext;
    Page page;
    @BeforeSuite
    public void start(){
        playwright=Playwright.create();
        browserType=playwright.chromium();
        browser=browserType.launch(new BrowserType.LaunchOptions().setHeadless(false));
        browserContext=browser.newContext();
        page=browser.newPage();


    }
    @Test
    public void  dropDown() throws InterruptedException {
        page.navigate("https://www.tutorialspoint.com/selenium/practice/selenium_automation_practice.php");
        ElementHandle dropbox=page.querySelector("//select[@id='state']");
        dropbox.selectOption(new SelectOption().setIndex(2));
        Thread.sleep(3000);
        dropbox.selectOption(new SelectOption().setValue("Haryana"));
        Thread.sleep(3000);
        dropbox.selectOption(new SelectOption().setLabel("NCR"));
        Thread.sleep(3000);
    }
    @AfterSuite
    public  void  stop(){
        page.close();
        browser.close();
        playwright.close();
    }
}

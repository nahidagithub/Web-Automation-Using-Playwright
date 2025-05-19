package org.example;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.List;

public class Locators {
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
        System.out.println("System version: "+browser.version());
    }
    @Test(priority=0)
    public void locatorById() throws InterruptedException {
        page.navigate("https://practice.expandtesting.com/login");
        ElementHandle id=page.querySelector("#username");
        Thread.sleep(2000);
        id.fill("Nahida");
        Thread.sleep(2000);
    }
    @Test(priority =1)
    public void locatorByName() throws InterruptedException {page.navigate("https://practice.expandtesting.com/login");
     ElementHandle name=page.querySelector("[name='username']");
     name.fill("Nahida");
     Thread.sleep(2000);
    }
    @Test(priority = 2)
    public void  locatorByLinklist() throws InterruptedException {
        page.navigate("https://practice.expandtesting.com/login");
        ElementHandle linklist=page.querySelector("a:has-text(\"Login\")");
        linklist.click();
        Thread.sleep(2000);
    }
    @Test(priority = 3)
    public void locatorByXpath() throws InterruptedException {
        page.navigate("https://practice.expandtesting.com/login");
     ElementHandle xpath=page.querySelector("//input[@id='password']");
     xpath.fill("3456");
     Thread.sleep(2000);
    }
    @Test(priority = 4)
    public void locatorByTagname(){
        page.navigate("https://practice.expandtesting.com/login");
        List<ElementHandle>element=page.querySelectorAll("input");
        System.out.println("Element size : " +element.size());
        for(ElementHandle handle:element){
            String id=handle.getAttribute("id");
            System.out.println("AttributeValue: "+id);
        }

    }


    @AfterSuite
    public void close(){
        page.close();
        browser.close();
        playwright.close();
    }

}

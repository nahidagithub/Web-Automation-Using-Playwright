package org.example;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.List;

public class IFrame {
    Playwright playwright;
    BrowserType browserType;
    Browser browser;
    BrowserContext browserContext;
    Page page;
    @AfterSuite
    public void start(){
        playwright=Playwright.create();
        browserType=playwright.chromium();
        browser=browserType.launch(new BrowserType.LaunchOptions().setHeadless(false));
        browserContext=browser.newContext();
        page=browser.newPage();
    }
    @Test
   public void iFrame() throws InterruptedException {
        page.navigate("https://testing-and-learning-hub.vercel.app/WebAutomation/pages/iframe.html");
        List<ElementHandle> frame1=page.querySelectorAll("iframe");
        System.out.println("Frame size " +frame1.size());

        Frame frame=page.frame("googleIframe");
        ElementHandle element=frame.querySelector("//*[contains(text(),Programmable Search Engine]");
        System.out.println(element.textContent());
        page.mainFrame();

        Frame frame2=page.frame("youtubeIframe");
        ElementHandle element1=frame2.querySelector("//iframe[@id='youtubeIframe']");
        element1.click();
        Thread.sleep(5000);
        page.mainFrame();
   }
    @BeforeSuite
    public  void close(){
        page.close();
        browser.close();
        playwright.close();
    }
}

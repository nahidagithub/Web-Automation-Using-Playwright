package org.example;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class ScrollHandling {
    Playwright playwright;
    BrowserType browserType;
    Browser browser;
    BrowserContext browserContext;
    Page page;
    @BeforeSuite
    public  void  start(){
        playwright=Playwright.create();
        browserType=playwright.chromium();
        browser=browserType.launch(new BrowserType.LaunchOptions().setHeadless(false));
        browserContext=browser.newContext();
        page=browser.newPage();
    }
    @Test
    public void scrollhandling() throws InterruptedException {
        page.navigate("https://www.tutorialspoint.com/selenium/practice/selenium_automation_practice.php");
        String scrollToBottom="window.scrollTo(0,body.document.scrollHeight);";
        page.evaluate(scrollToBottom);
        Thread.sleep(2000);

        String scrollToUp="window.scrollTo(0,0);";
        page.evaluate(scrollToUp);
        Thread.sleep(2000);

        ElementHandle scroll=page.querySelector("//label[normalize-space()='Current Address:']");
        scroll.scrollIntoViewIfNeeded();
        Thread.sleep(2000);
        System.out.println("Text: " + scroll.textContent());
       /* ElementHandle scroll=page.querySelector("//h1[normalize-space()='Student Registration Form']");
        scroll.scrollIntoViewIfNeeded();
        Thread.sleep(2000);
        System.out.println("Text: " + scroll.textContent());

        String scrollToBottom="window.scrollTo(0,document.body.scrollHeight);";
        page.evaluate(scrollToBottom);
        Thread.sleep(2000);

        String scrollToup="window.scrollTo(0,0);";
        page.evaluate(scrollToup);
        Thread.sleep(2000);
*/

    }
    @AfterSuite
    public  void  Stop(){
        page.close();
        browser.close();
        playwright.close();
    }
}

package org.example;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class AlertHandling {
    Playwright playwright;
    BrowserType browserType;
    Browser browser;
    BrowserContext browserContext;
    Page page;
    @BeforeSuite
    public void start(){
        playwright=Playwright.create();
        browserType=playwright.chromium();
        browser=browserType.launch(new BrowserType.LaunchOptions().setHeadless(true));
        browserContext=browser.newContext();
        page=browser.newPage();
    }
    @Test
    public void openurl(){
        page.navigate("https://testing-and-learning-hub.vercel.app/WebAutomation/pages/javascript_alerts.html");
    }
    @Test(dependsOnMethods = "openurl")
    public void alertHandle1(){

        page.onceDialog(dialog -> {
          System.out.println(dialog.type());
          System.out.println(dialog.message());
          dialog.accept();
          System.out.println("You accepted");
        });

        ElementHandle alert=page.querySelector("//button[normalize-space()='Show Alert']");
        alert.click();

    }
    @Test(dependsOnMethods = "openurl")
    public void alertHandle2(){
        page.onceDialog(dialog -> {
            System.out.println(dialog.type());
            System.out.println(dialog.message());
            dialog.accept("Hello");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("You clicked accepted");
        });

        ElementHandle alert2=page.querySelector("//button[normalize-space()='Show Confirm']");
        alert2.click();
    }
    @Test(dependsOnMethods = "openurl")
    public void alert() throws InterruptedException {
     String value="accept";
     page.onceDialog(dialog -> {
         System.out.println(dialog.type());
         System.out.println(dialog.message());
         if(dialog.type().equals("simple")){
             dialog.accept();
             System.out.println("You clicked accept");
         }
         else if(dialog.type().equals("confirm")){
             if(value.equals("accept")){
                 dialog.accept();
                 System.out.println("You clicked accepted");
             }
             else{
                 dialog.dismiss();
             }
         }
         else if (dialog.type().equals("prompt")) {
             if(value.equals("accept")){
                 dialog.accept("hi");
             }
             else{
                 dialog.dismiss();
             }
         }

     });
     
    }
    @AfterSuite
    public void stop(){
        page.close();
        browser.close();
        playwright.close();
    }

}

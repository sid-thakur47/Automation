package com.linkedin.test;

import com.linkedin.base.LinkedInBase;
import com.linkedin.pages.HomePage;
import com.linkedin.pages.Login;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class HomePageTest extends LinkedInBase {
    Login loginTest;
    HomePage homePage;

    //initialize login object
    @BeforeMethod
    public void run() throws AWTException, InterruptedException {
        initialize();
        loginTest = new Login();
        homePage = new HomePage();
        loginTest.linkedInLogin(properties.getProperty("username"), properties.getProperty("password"));
    }

    @Test
    public void searchJob() throws InterruptedException, IOException {
        homePage.search("QA Automation");
        Thread.sleep(1000);
    }

    @AfterMethod
    public void exitBrowser() {
        webDriver.quit();
    }
}

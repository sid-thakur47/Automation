/**********************************************************
 * @Purpose: To automate LinkedIn login page
 * @Author: Siddhesh Thakur
 * @Date: 23/06/2020
 **********************************************************/
package com.linkedin.test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.linkedin.base.LinkedInBase;
import com.linkedin.pages.Login;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest extends LinkedInBase {
    Login login;
    ExtentReports extent;
    ExtentHtmlReporter htmlReporter;

    //initialize login object

    @BeforeMethod
    public void run() {
        initialize();
        login = new Login();
    }

    @BeforeSuite
    public void beforeSuite() {
        htmlReporter = new ExtentHtmlReporter("F:/Automations/LinkedIn/src/test/Reports/extent.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    //verify login
    @Test
    public void loginTest() throws IOException, InterruptedException {
        takeScreenShot("login");
        ExtentTest test = extent.createTest("Login Test", "To check login feature");
        test.pass("Navigated to Login Page");
        login.linkedInLogin(properties.getProperty("username"), properties.getProperty("password")); //get username and password
        test.pass("Navigated to HomePage Page");
        String currentUrl = webDriver.getCurrentUrl(); //get current url
        Assert.assertEquals(currentUrl,"https://www.linkedin.com/feed/");
        takeScreenShot("afterLogin");
    }

    //test for wrong username
    @Test
    public void wrongUserName() throws IOException, InterruptedException {
        ExtentTest test = extent.createTest("Wrong Username", "To check for wrong username");
        test.pass("Navigated to Login page");
        login.linkedInLogin("sidthaku6433", "test");
        test.pass("Invalid Username -On Login page ");
        String actualMessage = login.wrongPassword("username");
        Assert.assertEquals(actualMessage, "Please enter a valid username");
        takeScreenShot("invalidUserName");
    }

    //test for short password
    @Test
    public void shortPassword() throws IOException, InterruptedException {
        ExtentTest test = extent.createTest("Wrong Password", "To check for wrong password");
        login.linkedInLogin("sid@gmail.com", "wrong");
        test.pass("Invalid Password-On Login page ");
        String actualMessage = login.wrongPassword("password");
        Assert.assertEquals(actualMessage, "Welcome Back");
        takeScreenShot("invalidPassword");
    }

    // to close browser
    @AfterMethod
    public void exitBrowser() throws InterruptedException {
        webDriver.quit();
        extent.flush();
    }
}

/**********************************************************
 * @Purpose: To automate LinkedIn login page
 * @Author: Siddhesh Thakur
 * @Date: 23/06/2020
 **********************************************************/
package com.linkedin.test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.linkedin.base.LinkedInBase;
import com.linkedin.pages.Google;
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
    Google google;

    //initialize login object

    @BeforeMethod()
    public void run() {
        initialize();
        login = new Login();
        google = new Google();
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
        ExtentTest test = extent.createTest("Login Test", "To check login feature");
        test.fail("Does not naviagte to Home page");
        login.linkedInLogin(properties.getProperty("username"), properties.getProperty("password")); //get username and password
        takeScreenShot("login");
        test.addScreenCaptureFromPath("login.png");
        String currentUrl = webDriver.getCurrentUrl(); //get current url
        Assert.assertEquals(currentUrl, "https://www.linkedin.com/feed/");
        test.log(Status.INFO, "In login page");
    }

    @Test
    public void wrongUserName() throws IOException, InterruptedException {
        ExtentTest test = extent.createTest("Wrong Username", "To check for wrong username");
        login.linkedInLogin("sidthaku6433", "test");
        String actualMessage = login.wrongPassword("username");
        Assert.assertEquals(actualMessage, "Please enter a valid username");
        takeScreenShot("invalidUserName");
        test.addScreenCaptureFromPath("invalidUserName.png");
        test.log(Status.INFO, "In Login page");
    }

    //test for short password
    @Test
    public void shortPassword() throws IOException, InterruptedException {
        ExtentTest test = extent.createTest("Wrong Password", "To check for wrong password");
        login.linkedInLogin("sid@gmail.com", "wrong");
        String actualMessage = login.wrongPassword("password");
        Assert.assertEquals(actualMessage, "Welcome Back");
        takeScreenShot("invalidPassword");
        test.addScreenCaptureFromPath("invalidPassword.png");
        test.log(Status.INFO, "Invalid Password");
    }

    // to close browser
    @AfterMethod
    public void exitBrowser() {
        webDriver.quit();
        extent.flush();
    }
}

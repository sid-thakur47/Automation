/**********************************************************
 * @Purpose: To automate LinkedIn login page
 * @Author: Siddhesh Thakur
 * @Date: 23/06/2020
 **********************************************************/
package com.linkedin.test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.linkedin.CustomListener.CustomListener;
import com.linkedin.base.LinkedInBase;
import com.linkedin.pages.Login;
import org.testng.Assert;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;

import static org.testng.ITestResult.FAILURE;

@Listeners(CustomListener.class)
public class LoginTest extends LinkedInBase implements ITestListener {

    Login login;
    ExtentReports extent;
    ExtentHtmlReporter htmlReporter;

    //initialize login object

    @BeforeMethod()
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
        login.linkedInLogin(properties.getProperty("username"), properties.getProperty("password")); //get username and password
        String currentUrl = webDriver.getCurrentUrl(); //get current url
        Assert.assertEquals(currentUrl, "https://www.linkedin.com/feed/");
        login.signOut();
    }

    @Test
    public void wrongUserName() throws IOException, InterruptedException {
        login.linkedInLogin("sidthaku6433", "test");
        String actualMessage = login.wrongPassword("username");
        Assert.assertEquals(actualMessage, "Please enter a valid username");
    }

    //test for short password
    @Test
    public void shortPassword() throws IOException, InterruptedException {
        login.linkedInLogin("sid@gmail.com", "wrong");
        String actualMessage = login.wrongPassword("password");
        Assert.assertEquals(actualMessage, "Welcome Back");
    }

    // to close browser
    @AfterMethod
    public void exitBrowser(ITestResult result) throws InterruptedException {
        ExtentTest test = extent.createTest("Login Test", "To check login feature");
        if(result.getStatus() == FAILURE) {
            test.fail(MarkupHelper.createLabel(result.getName() + "Test Failed", ExtentColor.RED));
        } else if(result.getStatus() == ITestResult.SUCCESS) {
            test.pass(MarkupHelper.createLabel(result.getName() + "Test Pass", ExtentColor.GREEN));
        } else if(result.getStatus() == ITestResult.SKIP) {
            test.pass(MarkupHelper.createLabel(result.getName() + "Test Pass", ExtentColor.YELLOW));
        }
        webDriver.quit();
        extent.flush();
        Thread.sleep(1000);
    }
}

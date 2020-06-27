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
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.linkedin.CustomListener.CustomListener;
import com.linkedin.base.LinkedInBase;
import com.linkedin.pages.Login;
import org.apache.commons.mail.EmailException;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;

import static org.testng.ITestResult.FAILURE;

@Listeners(CustomListener.class)
public class LoginTest extends LinkedInBase {

    Login login;
    ExtentReports extent;
    ExtentHtmlReporter htmlReporter;
    ExtentTest test;
    int count = 1;

    //initialize login object
    @BeforeMethod()
    public void run() {
        test = extent.createTest("Login Test", "To check login feature");
        initialize();
        login = new Login();
    }

    //initialize report before every suite
    @BeforeSuite
    public void beforeSuite() {
        htmlReporter = new ExtentHtmlReporter("F:/Automations/LinkedIn/src/test/Reports/extent.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        htmlReporter.config().setDocumentTitle("LinkedIn Login Test Reports");
        htmlReporter.config().setReportName("LinkedIn");
        htmlReporter.config().setTheme(Theme.DARK);
    }

    @Test
    public void loginTest() throws IOException, InterruptedException {
        login.linkedInLogin(properties.getProperty("username"), properties.getProperty("password")); //get username and password
        String currentUrl = webDriver.getCurrentUrl(); //get current url
        Assert.assertEquals(currentUrl, "https://www.linkedin.com/feed/");
    }

    //verify login
    @Test
    public void loginLogoutTest() throws InterruptedException {
        login.linkedInLogin(properties.getProperty("username"), properties.getProperty("password")); //get username and password
        login.signOut();
        String currentUrl = webDriver.getCurrentUrl(); //get current url
        Assert.assertEquals(currentUrl, "https://www.linkedin.com/home");
    }

    @Test
    public void wrongUserName() throws InterruptedException {
        login.linkedInLogin("sidthaku6433", "test");
        String actualMessage = login.wrongPassword("username");
        Assert.assertEquals(actualMessage, "Please enter a valid username");
        System.out.println("TestPass" + count + ".png");
    }

    //test for short password
    @Test
    public void shortPassword() throws InterruptedException {
        login.linkedInLogin("sid@gmail.com", "wrong");
        String actualMessage = login.wrongPassword("password");
        Assert.assertEquals(actualMessage, "Welcome Back");
    }

    //failing test to check it is reported or not
    @Test
    public void failTestCheck() throws InterruptedException {
        login.linkedInLogin("sid@gmail.com", "wrong");
        String actualMessage = login.wrongPassword("password");
        Assert.assertEquals(actualMessage, "Welcome");
    }

    // to close browser and generate test report
    @AfterMethod
    public void exitBrowser(ITestResult result) throws InterruptedException, IOException {
        if(result.getStatus() == FAILURE) {
            test.addScreenCaptureFromPath("TestFail" + count + ".png");
            test.fail(MarkupHelper.createLabel(result.getName() + "Test Failed", ExtentColor.RED));
        } else if(result.getStatus() == ITestResult.SUCCESS) {
            test.addScreenCaptureFromPath("TestPass" + count + ".png");
            test.pass(MarkupHelper.createLabel(result.getName() + "Test Pass", ExtentColor.GREEN));
        }
        webDriver.quit();
        extent.flush();
        Thread.sleep(1000);
        count++;
    }

    //send mail
    @AfterSuite
    public void sendmail() throws EmailException {
        sendEmail("sidthakur258@gmail.com");
    }
}

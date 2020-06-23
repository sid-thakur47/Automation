/**********************************************************
 * @Purpose: To automate LinkedIn login page
 * @Author: Siddhesh Thakur
 * @Date: 23/06/2020
 **********************************************************/
package com.linkedin.test;

import com.linkedin.base.LinkedInBase;
import com.linkedin.pages.Login;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends LinkedInBase {
    Login login;

    //initialize login object

    @BeforeMethod
    public void run() {
        initialize();
        login = new Login();
    }
    // to close browser
    @AfterMethod
    public void exitBrowser() {
        webDriver.quit();
    }
    //verify login
    @Test
    public void loginTest() {
        login.linkedInLogin(properties.getProperty("username"), properties.getProperty("password")); //get username and password
        String currentUrl = webDriver.getCurrentUrl(); //get current url
        Assert.assertEquals("https://www.linkedin.com/feed/", currentUrl);
    }

    //test for wrong username
    @Test
    public void wrongUserName() {
        login.linkedInLogin("sidthaku6433", "test");
        WebElement errorMessageElement = webDriver.findElement(By.id("error-for-username"));
        String actualMessage = errorMessageElement.getText(); //get error message
        Assert.assertEquals(actualMessage, "Please enter a valid username");
    }

    //test for wrong password
    @Test
    public void wrongPassword() {
        login.linkedInLogin("sid@gmail.com", "wrongpassword");
        WebElement errorMessageElement = webDriver.findElement(By.id("error-for-password"));
        String actualMessage = errorMessageElement.getText(); //get error message
        Assert.assertEquals(actualMessage, "Hmm, that's not the right password. Please try again or request a new one.");
    }
}

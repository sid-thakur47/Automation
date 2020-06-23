/**********************************************************
 * @Purpose: To automate LinkedIn logine page
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

    @BeforeMethod
    public void run() {
        initialize();
        login = new Login();
    }

    @AfterMethod
    public void exitBrowser() {
        webDriver.quit();
    }

    @Test
    public void loginTest() {
        login.linkedInLogin(properties.getProperty("username"), properties.getProperty("password"));
        String currentUrl = webDriver.getCurrentUrl();
        Assert.assertEquals("https://www.linkedin.com/feed/", currentUrl);
    }

    @Test
    public void wrongUserName() {
        login.linkedInLogin("sidthaku6433", "test@12345");
        WebElement errorMessageElement = webDriver.findElement(By.id("error-for-username"));
        String actualMessage = errorMessageElement.getText();
        Assert.assertEquals(actualMessage, "Please enter a valid username");
    }

    @Test
    public void wrongPassword() {
        login.linkedInLogin("sidthaku6433@gmail.com", "wrongpassword");
        WebElement errorMessageElement = webDriver.findElement(By.id("error-for-password"));
        String actualMessage = errorMessageElement.getText();
        Assert.assertEquals(actualMessage, "Hmm, that's not the right password. Please try again or request a new one.");
    }
}

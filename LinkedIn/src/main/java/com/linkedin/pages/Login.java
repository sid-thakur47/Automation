/**********************************************************
 * @Purpose: To store methods and locators of login page
 * @Author: Siddhesh Thakur
 * @Date: 23/06/2020
 **********************************************************/
package com.linkedin.pages;

import com.linkedin.base.LinkedInBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login extends LinkedInBase {

    //Web element locators
    @FindBy(id = "username")
    WebElement username;

    @FindBy(id = "password")
    WebElement password;

    @FindBy(className = "login__form_action_container")
    WebElement loginButton;

    @FindBy(xpath = "//h1[contains(text(),'Welcome Back')]")
    WebElement wrongPassword;

    @FindBy(id = "error-for-username")
    WebElement wrongUserName;

    @FindBy(xpath = "//li[6]//div[1]//div[1]//button[1]//div[1]")
    WebElement me;

    @FindBy(xpath = "//a[text()='Sign out']")
    WebElement signOut;

    //initialization of elements
    public Login() {
        PageFactory.initElements(webDriver, this);
    }

    public void linkedInLogin(String username, String password) throws InterruptedException {
        Thread.sleep(500);
        this.username.sendKeys(username);
        Thread.sleep(500);
        this.password.sendKeys(password);
        Thread.sleep(500);
        this.loginButton.click();
        Thread.sleep(500);
    }

    public void signOut() {
        me.click();
        signOut.click();
    }

    public String wrongPassword(String errorOf) {
        if(errorOf.equals("password")) {
            return wrongPassword.getText();
        } else {
            return wrongUserName.getText();
        }
    }
}

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

import java.io.File;

public class Login extends LinkedInBase {

    File destFile = new File("Screenshot.png");

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
    ;

    //initialization of elements
    public Login() {
        PageFactory.initElements(webDriver, this);
    }

    public void linkedInLogin(String username, String password) throws InterruptedException {
        this.username.sendKeys(username);
        this.password.sendKeys(password);
        this.loginButton.click();
        Thread.sleep(1000);
    }

    public String wrongPassword(String errorOf) {
        if(errorOf.equals("password")) {
            return wrongPassword.getText();
        } else {
            return wrongUserName.getText();
        }
    }
}

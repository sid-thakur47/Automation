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

    @FindBy(xpath = "/html[1]/body[1]/div[1]/main[1]/div[2]/form[1]/div[3]/button[1]")
    WebElement loginButton;

    public Login() {
        PageFactory.initElements(webDriver, this);
    }

    //to login in LinkedIn
    public void linkedInLogin(String username, String password) {
        this.username.sendKeys(username);
        this.password.sendKeys(password);
        this.loginButton.click();
    }
}

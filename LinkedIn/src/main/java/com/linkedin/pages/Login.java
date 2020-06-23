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

    //initialization of elements
    public Login() {
        PageFactory.initElements(webDriver, this);
    }

    //Web element locators
    @FindBy(id = "username")
    WebElement username;

    @FindBy(id = "password")
    WebElement password;

    @FindBy(className = "login__form_action_container")
    WebElement loginButton;


    //to login in LinkedIn
    public void linkedInLogin(String username, String password) {
        this.username.sendKeys(username);
        this.password.sendKeys(password);
        this.loginButton.click();
    }
}

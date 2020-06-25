/**********************************************************
 * @Purpose: To store methods and locators of Home page
 * @Author: Siddhesh Thakur
 * @Date: 24/06/2020
 **********************************************************/
package com.linkedin.pages;

import com.linkedin.base.LinkedInBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.awt.*;
import java.awt.event.KeyEvent;

public class HomePage extends LinkedInBase {
    Robot robot = new Robot();

    //Web element locators
    @FindBy(tagName = "input")
    WebElement search;


    //initialization of elements
    public HomePage() throws AWTException {
        PageFactory.initElements(webDriver, this);
    }
    public void search(String searchJob)  {
        this.search.sendKeys(searchJob);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }
}

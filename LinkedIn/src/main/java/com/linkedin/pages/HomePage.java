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
import java.io.IOException;

public class HomePage extends LinkedInBase {
    Robot robot = new Robot();

    //Web element locators
    @FindBy(xpath = "//div[@id='ember41']//input")
    WebElement search;

    @FindBy(xpath = " //button[contains(@class,'share-box-feed-entry__trigger t-16 t-black--light t-bold')]")
    WebElement writePost;

    @FindBy(xpath = "//div[@class='ql-editor ql-blank']")
    WebElement about;

    @FindBy(xpath = "/html[1]/body[1]/div[4]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/button[1]/li-icon[1]/*[local-name()='svg'][1]")
    WebElement addPhoto;

    @FindBy(xpath = "//span[text()='Done']")
    WebElement done;

    @FindBy(xpath = "//span[text()='Post']")
    WebElement post;

    @FindBy(xpath = "//li[6]//div[1]//div[1]//button[1]//div[1]")
    WebElement me;

    @FindBy(xpath = "//button[@id='ember23']//div//li-icon//*[local-name()='svg']")
    WebElement activity;

    @FindBy(xpath = "//li-icon[@class='artdeco-button__icon']")
    WebElement threeDots;

    //initialization of elements
    public HomePage() throws AWTException {
        PageFactory.initElements(webDriver, this);
    }
    public void search(String searchJob) {
        search.sendKeys(searchJob);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    public void addPost() {
        try {
            writePost.click();
            Thread.sleep(1000);
            about.sendKeys("Manual Testing");
            Thread.sleep(1000);
            addPhoto.click();
            Thread.sleep(6000);
            Runtime.getRuntime().exec("C:/Users/Shivani/Desktop/Backup/AddPost.exe");
            done.click();
            Thread.sleep(1000);
            post.click();
            Thread.sleep(5000);
        } catch(InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}

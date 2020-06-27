/**********************************************************
 * @Purpose: Initialization of chrome driver
 * @Author: Siddhesh Thakur
 * @Date: 23/06/2020
 **********************************************************/
package com.linkedin.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LinkedInBase {

    public static WebDriver webDriver;
    public static Properties properties;
    public int count = 0;

    //initialize and load user credentials
    public LinkedInBase() {
        properties = new Properties();
        try {
            FileInputStream input = new FileInputStream("C:/Users/Shivani/Desktop/Backup/Input.csv");
            properties.load(input);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    //initialize chrome driver
    public static void initialize() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        webDriver = new ChromeDriver(options);
        webDriver.manage().window().maximize();
        webDriver.get(properties.getProperty("url"));
    }

    //send mail of test report
    public static void sendEmail(String sendTo) throws EmailException {
        EmailAttachment attachment = new EmailAttachment();
        attachment.setPath("F:/Automations/LinkedIn/src/test/Reports/extent.html");
        attachment.setDisposition(EmailAttachment.ATTACHMENT);
        attachment.setName("Test Report");
        MultiPartEmail email = new MultiPartEmail();
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(584);
        email.setAuthentication(properties.getProperty("email"), properties.getProperty("emailPassword"));
        email.setSSLOnConnect(true);
        email.addTo(sendTo);
        email.setFrom(properties.getProperty("email"), "Siddhesh");
        email.setMsg("Please check the report for current feature");
        email.attach(attachment);
        email.send();
    }

    //to take screenshot
    public void takeScreenShot(String status) {
        count++;
        try {
            File srcFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(srcFile, new File("F:/Automations/LinkedIn/src/test/Reports/" + status + count + ".png"));
        } catch(WebDriverException | IOException e) {
            e.printStackTrace();
        }
    }
}

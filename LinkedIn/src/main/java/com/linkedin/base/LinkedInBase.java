/**********************************************************
 * @Purpose: Initialization of chrome driver
 * @Author: Siddhesh Thakur
 * @Date: 23/06/2020
 **********************************************************/
package com.linkedin.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LinkedInBase {

    public static WebDriver webDriver;
    public static Properties properties;
    public static TakesScreenshot ts;

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
        ts = (TakesScreenshot) webDriver;
    }

    //to take screenshot
    public void takeScreenShot(String location) throws IOException {
        File srcFile = ts.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, new File("F:/Automations/LinkedIn/src/test/Reports/" + location + ".png"));
    }
}

/**********************************************************
 * @Purpose: Initialization of chrome driver
 * @Author: Siddhesh Thakur
 * @Date: 23/06/2020
 **********************************************************/
package com.linkedin.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LinkedInBase {

    public static WebDriver webDriver;
    public static Properties properties;

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
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get(properties.getProperty("url"));
    }
}

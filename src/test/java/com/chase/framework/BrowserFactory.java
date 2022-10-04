package com.chase.framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * @author Lakshmi Pannala
 * Factory class that creates the driver specific to browser
 */
public class BrowserFactory {

    private BrowserFactory() {}

    public static WebDriver getDriver() {
        WebDriver driver = null;

        //Expects the environment variable 'browser' to be set
        String browser = System.getProperty("browser");

        if(browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if(browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver(getChromeOptions());
        } else if(browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        } else {
            //Defaults to Chrome browser
            driver = new ChromeDriver(getChromeOptions());
        }

        return driver;
    }

    private static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");

        return options;
    }
}

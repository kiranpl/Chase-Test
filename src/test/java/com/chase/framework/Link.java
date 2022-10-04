package com.chase.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;

/**
 * @author Lakshmi Pannala
 * Class that encapsulates Links
 */
public class Link {

    public static void click(By link) throws IOException {
        WebElement element = (new WebDriverWait(Browser.getDriver(), Duration.ofSeconds(10)))
                .until(ExpectedConditions.elementToBeClickable(link));
        Browser.getDriver().switchTo().activeElement();
        element.click();
        Helper.takeScreenshot("LinkClicked");
    }

    public static void click(WebElement webElement, By link) throws IOException {
        WebElement element = webElement.findElement(link);
        Browser.getDriver().switchTo().activeElement();
        element.click();
        Helper.takeScreenshot("LinkClicked");
    }

    public static String getAttribute(By link, String name) {
        WebElement element = (new WebDriverWait(Browser.getDriver(), Duration.ofSeconds(10)))
                .until(ExpectedConditions.elementToBeClickable(link));
        return element.getAttribute(name);
    }

    public static String getAttribute(WebElement webElement, By link, String name) {
        WebElement element = webElement.findElement(link);
        return element.getAttribute(name);
    }
}

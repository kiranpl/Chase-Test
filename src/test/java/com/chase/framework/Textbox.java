package com.chase.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;

/**
 * @author Lakshmi Pannala
 * Class that encapsulates Textbox
 */
public class Textbox {
    public static void typeText(By textbox, String text) throws IOException {
        WebElement element = (new WebDriverWait(Browser.getDriver(), Duration.ofSeconds(10)))
                .until(ExpectedConditions.presenceOfElementLocated(textbox));

        element.sendKeys(text);
        Helper.takeScreenshot("TextTyped");
    }
}

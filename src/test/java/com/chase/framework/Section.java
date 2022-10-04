package com.chase.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * @author Lakshmi Pannala
 * Class that encapsulates DIV section
 */
public class Section {

    public static WebElement getElement(By section) {
        WebElement element = (new WebDriverWait(Browser.getDriver(), Duration.ofSeconds(10)))
                .until(ExpectedConditions.presenceOfElementLocated(section));
        return element;
    }

}

package com.chase.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Lakshmi Pannala
 * Class that encapsulates Label
 */
public class Label {
    public static String getText(By label) {
        WebElement element = (new WebDriverWait(Browser.getDriver(), Duration.ofSeconds(10)))
                .until(ExpectedConditions.presenceOfElementLocated(label));
        return element.getText();
    }

    public static String getText(By label, int frameNo) {
        Browser.getDriver().switchTo().frame(1);
        WebElement element = (new WebDriverWait(Browser.getDriver(), Duration.ofSeconds(10)))
                .until(ExpectedConditions.presenceOfElementLocated(label));
        String text = element.getText();
        Browser.getDriver().switchTo().defaultContent();
        return text;
    }

    public static List<String> getTextFromElements(By label) {
        List<String> textFromElements = new ArrayList<>();

        List<WebElement> elements = (new WebDriverWait(Browser.getDriver(), Duration.ofSeconds(10)))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(label));

        for (WebElement element : elements) {
            textFromElements.add(element.getText().toLowerCase());
        }
        return textFromElements;
    }

    public static boolean verifyLabelText(By label, String expected) throws IOException {
        if (Browser.getDriver().findElement(label).getText().contains(expected)) {
            Helper.takeScreenshot("TextVerified");
            return true;
        }

        return false;
    }
}

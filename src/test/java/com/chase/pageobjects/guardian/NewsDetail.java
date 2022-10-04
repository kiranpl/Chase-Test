package com.chase.pageobjects.guardian;

import com.chase.framework.Label;
import org.junit.Assert;
import org.openqa.selenium.By;

import java.io.IOException;

/**
 * @author Lakshmi Pannala
 * Page object class specific to Guardian news details page
 */
public class NewsDetail {
    private By heading = By.xpath("//span[contains(text(),'Support climate')]");
    private By closeButton = By.xpath("//div/span/div/div");
    private By headLine = By.xpath("//h1");
    private By content = By.xpath("//div[@id='maincontent']");
    private String headlineText = null;
    private String contentText = null;

    public void readThroughContent() {
        headlineText = Label.getText(headLine).trim();
        contentText = Label.getText(content).trim();
    }

    public void checkHeadlineAndContent() {
        int occurrences = 0;

        for (String word : headlineText.split(" ")) {
            if (contentText.contains(word))
                occurrences++;
        }

        Assert.assertTrue(occurrences >= 5);
    }

    public String getHeadline() {
        return headlineText;
    }
}

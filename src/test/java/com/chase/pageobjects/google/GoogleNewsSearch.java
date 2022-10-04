package com.chase.pageobjects.google;

import com.chase.framework.Browser;
import com.chase.framework.Button;
import com.chase.framework.Label;
import com.chase.framework.Textbox;
import org.openqa.selenium.By;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Lakshmi Pannala
 * Page object class specific to Google news website
 */
public class GoogleNewsSearch {


    private By acceptButton = By.xpath("//button[contains(@aria-label,'Accept all')]");
    private By searchTextbox = By.xpath("//input[contains(@aria-label,'Search')]");
    private By searchButton = By.xpath("//button[contains(@aria-label,'Search')]");

    private List<String> newsResults = new ArrayList<>();

    public void navigateToHomePage() throws IOException, InterruptedException {
        Browser.navigateTo("https://news.google.co.uk");
        if (Browser.elementExists(acceptButton)) {
            Thread.sleep(1000);
            Button.click(acceptButton);
            Thread.sleep(1000);
            Button.click(acceptButton);
        }
    }

    public void performSearch(String headlineText) throws IOException {
        Textbox.typeText(searchTextbox, headlineText);
        Button.click(searchButton);
    }

    public void extractResults(String headlineText) {
        By searchResults = By.xpath("//c-wiz[@data-n-q='" + headlineText + "']//h3");
        this.newsResults = Label.getTextFromElements(searchResults);
    }

    public List<String> getNewsResults() {
        return newsResults;
    }
}

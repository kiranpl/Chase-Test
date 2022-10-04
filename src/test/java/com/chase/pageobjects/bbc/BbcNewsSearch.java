package com.chase.pageobjects.bbc;

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
 * Class specific to BBC news website
 */
public class BbcNewsSearch {

    private By okButton = By.xpath("//button[contains(text(),'Yes, I agree')]");
    private By searchTextbox = By.xpath("//input[contains(@data-testid,'search')]");
    private By searchButton = By.xpath("//button[contains(@data-testid,'search')]");

    private List<String> newsResults = new ArrayList<>();

    public void navigateToHomePage() throws IOException, InterruptedException {
        Browser.navigateTo("https://www.bbc.co.uk/search");
        if (Browser.elementExists(okButton)) {
            Thread.sleep(1000);
            Button.click(okButton);
        }
    }

    public void performSearch(String headlineText) throws IOException {
        Textbox.typeText(searchTextbox, headlineText);
        Button.click(searchButton);
    }

    public void extractResults(String headlineText) throws InterruptedException {
        By searchResults = By.xpath("//ul/li/div[contains(@class,'Promo')]");
        if (Browser.elementExists(searchResults)) {
            this.newsResults = Label.getTextFromElements(searchResults);
        }
    }

    public List<String> getNewsResults() {
        return this.newsResults;
    }
}

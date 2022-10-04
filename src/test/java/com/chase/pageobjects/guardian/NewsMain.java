package com.chase.pageobjects.guardian;

import com.chase.framework.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.IOException;

/**
 * @author Lakshmi Pannala
 * Page object class specific to Guardian news main page
 */
public class NewsMain {
    private By heading = By.xpath("//p[contains(text(),'your choice')]");
    private By okButton = By.xpath("//button[text()='Yes, I’m happy']");
    private By newsSection = By.xpath("//div[contains(@data-link-name,'card-@1')]");
    private By newsLink = By.xpath("//a[@data-link-name='article' and @tabindex=-1]");

    private String articleLink;

    public void navigateToHomePage() throws IOException, InterruptedException {
        Browser.navigateTo("https://www.theguardian.com/tone/news");

        //Wait until the cookies frame loads
        if (Browser.elementExists(heading, 1)) {
            //Accept the cookies
            Button.click(okButton, 1);
        }
    }

    public void userAccessesNewsUrl() throws IOException {
        WebElement divElement = Section.getElement(newsSection);
        this.articleLink = Link.getAttribute(divElement, newsLink,"href");
        Link.click(divElement, newsLink);
    }

    public String getArticleLink() {
        return this.articleLink;
    }

    public void disposeBrowser() {
        Browser.dispose();
    }
}

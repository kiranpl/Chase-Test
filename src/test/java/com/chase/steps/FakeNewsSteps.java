package com.chase.steps;

import com.chase.pageobjects.bbc.BbcNewsSearch;
import com.chase.pageobjects.google.GoogleNewsSearch;
import com.chase.pageobjects.guardian.NewsDetail;
import com.chase.pageobjects.guardian.NewsMain;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Lakshmi Pannala
 * Class that implements the Fake News BDD scenarios
 */
public class FakeNewsSteps {

    private NewsMain newsMain = new NewsMain();
    private NewsDetail newsDetail = new NewsDetail();
    private GoogleNewsSearch googleNewsSearch = new GoogleNewsSearch();

    private BbcNewsSearch bbcNewsSearch = new BbcNewsSearch();

    private int matchCounter = 0;

    @Given("^the user navigated to Guardian website$")
    public void user_navigated_to_homepage() throws Throwable {
        newsMain.navigateToHomePage();
    }

    @When("^the user accesses the link to the first news URL$")
    public void user_accesses_first_news_url() throws Throwable {
        newsMain.userAccessesNewsUrl();
    }

    @Then("^the URL is found to be valid$")
    public void url_is_found_to_be_valid() throws Throwable {
        Assert.assertTrue(newsMain.getArticleLink().contains("https://www.theguardian.com"));
    }

    @When("^reads through the content of the news$")
    public void reads_through_the_content() throws Throwable {
        newsDetail.readThroughContent();
    }

    @Then("^the headline is found to be in line with the news content$")
    public void headline_inline_with_content() {
        newsDetail.checkHeadlineAndContent();
    }

    @And("^perform Google search of the news and find 2 other sources$")
    public void perform_google_search_of_news() throws Throwable {
        List<String> googleNewsWords;

        String headlineText = newsDetail.getHeadline().toLowerCase();

        googleNewsSearch.navigateToHomePage();
        googleNewsSearch.performSearch(headlineText);
        googleNewsSearch.extractResults(headlineText);

        List<String> newsResults = googleNewsSearch.getNewsResults();

        for (String googleNews : newsResults) {
            List<String> guardianNewsWords = new ArrayList<>(Arrays.asList(headlineText.split(" ")));
            googleNewsWords = new ArrayList<>(Arrays.asList(googleNews.split(" ")));
            googleNewsWords.retainAll(guardianNewsWords);
            if (googleNewsWords.size() >= 2) this.matchCounter++;
        }
    }

    @Then("^the news is found to be valid news in other sources$")
    public void google_news_is_found_valid() {
        Assert.assertTrue(this.matchCounter >= 3);
    }

    @And("^perform search of the news on BBC news website$")
    public void perform_bbc_search_of_news() throws Throwable {
        this.matchCounter = 0;
        List<String> bbcNewsWords;

        String headlineText = newsDetail.getHeadline().toLowerCase();

        bbcNewsSearch.navigateToHomePage();
        bbcNewsSearch.performSearch(headlineText);
        bbcNewsSearch.extractResults(headlineText);

        List<String> newsResults = bbcNewsSearch.getNewsResults();

        for (String bbcNews : newsResults) {
            List<String> guardianNewsWords = new ArrayList<>(Arrays.asList(headlineText.split(" ")));
            bbcNewsWords = new ArrayList<>(Arrays.asList(bbcNews.split(" ")));
            bbcNewsWords.retainAll(guardianNewsWords);
            if (bbcNewsWords.size() >= 2) this.matchCounter++;
        }
    }

    @Then("^the news is found to be valid news in BBC news website$")
    public void bbc_news_is_found_valid() {
        Assert.assertTrue(this.matchCounter > 0);
    }

    @After
    public void TearDown()
    {
        newsMain.disposeBrowser();
    }
}

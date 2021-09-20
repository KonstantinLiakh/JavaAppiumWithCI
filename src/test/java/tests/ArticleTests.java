package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import junit.framework.Assert;
import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.WelcomePageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import lib.ui.factories.WelcomePageObjectFactory;
import org.junit.Test;

@Epic("Tests for articles")
public class ArticleTests extends CoreTestCase {

    @Test
    @DisplayName("Compare article title with expected one")
    @Description("This test compares article title on a page with expected page title")
    @Step("Starting test testCompareArticleTitle")
    @Features(value = {@Feature(value="Search"), @Feature(value="Article")})
    @Severity(value = SeverityLevel.BLOCKER)
    public void testCompareArticleTitle() {

        WelcomePageObject WelcomePageObject = WelcomePageObjectFactory.get(driver);
        WelcomePageObject.clickOnSkipButton();

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleSubstring("Java (programming language)");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

        String article_title = ArticlePageObject.waitForTitleElement1("Java (programming language)");

        Assert.assertEquals(
                "We see unexpected title",
                "Java (programming language)",
                article_title
        );
    }

    @Test
    @DisplayName("Swipe article to the footer")
    @Description("This test opens article, then swipes the content to the footer and compare footer element with expected element")
    @Step("Starting test testSwipeArticle")
    @Features(value = {@Feature(value="Search"), @Feature(value="Article")})
    @Severity(value = SeverityLevel.MINOR)
    public void testSwipeArticle() {

        WelcomePageObject WelcomePageObject = WelcomePageObjectFactory.get(driver);
        WelcomePageObject.clickOnSkipButton();

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleSubstring("Java (programming language)");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement1("Java (programming language)");

        ArticlePageObject.swipeToFooter();
    }

    @Test
    @DisplayName("Check article title without waiting time")
    @Description("This test compares article title on a page with expected page title without waiting time")
    @Step("Starting test testTitleOfArticleImmediately")
    @Features(value = {@Feature(value="Search"), @Feature(value="Article")})
    @Severity(value = SeverityLevel.MINOR)
    public void testTitleOfArticleImmediately() {

        WelcomePageObject WelcomePageObject = WelcomePageObjectFactory.get(driver);
        WelcomePageObject.clickOnSkipButton();

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java (programming language)");
        SearchPageObject.clickByArticleSubstring("Java (programming language)");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.checkTitleElementWithoutWaitingTime("Java (programming language)");
    }
}
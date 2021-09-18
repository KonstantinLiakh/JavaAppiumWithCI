package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.WelcomePageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import lib.ui.factories.WelcomePageObjectFactory;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {

    // ТЕСТ, КОТОРЫЙ ПРОВЕРЯЕТ НАЗВАНИЕ СТАТЬИ
    @Test
    public void testCompareArticleTitle() {

        WelcomePageObject WelcomePageObject = WelcomePageObjectFactory.get(driver);
        WelcomePageObject.clickOnSkipButton();

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleSubstring("Java (programming language)");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

        String article_title = ArticlePageObject.waitForTitleElement1("Java (programming language)");

        assertEquals(
                "We see unexpected title",
                "Java (programming language)",
                article_title
        );
    }

    // ТЕСТ ДЛЯ ПРОВЕРКИ СКРОЛЛИНГА СТРАНИЦЫ ВНИЗ
    @Test
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

    // ТЕСТ, КОТОРЫЙ ПРОВЕРЯЕТ НАЗВАНИЕ СТАТЬИ, НЕ ДОЖИДАЯСЬ САМОГО ЭЛЕМЕНТА С НАЗВАНИЕМ
    @Test
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
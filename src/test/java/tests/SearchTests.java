package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import junit.framework.Assert;
import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.WelcomePageObject;
import lib.ui.factories.SearchPageObjectFactory;
import lib.ui.factories.WelcomePageObjectFactory;
import org.junit.Test;

public class SearchTests extends CoreTestCase {

    @Test
    @DisplayName("Article search")
    @Description("Search for some articles via search field")
    @Step("Starting test testSearchObject")
    @Feature(value="Search")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testSearchObject() {

        WelcomePageObject WelcomePageObject = WelcomePageObjectFactory.get(driver);
        WelcomePageObject.clickOnSkipButton();

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Java (programming language)");
    }

    @Test
    @DisplayName("Cancel search")
    @Description("This test is for searching of some items, then cancel should be cancelled")
    @Step("Starting test testSearchCancel")
    @Feature(value="Search")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testSearchCancel() {

        WelcomePageObject WelcomePageObject = WelcomePageObjectFactory.get(driver);
        WelcomePageObject.clickOnSkipButton();

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appium");
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    @DisplayName("Not empty search")
    @Description("This test is for searching in case there are some items found via search")
    @Step("Starting test testAmountOfNotEmptySearch")
    @Feature(value="Search")
    @Severity(value = SeverityLevel.NORMAL)
    public void testAmountOfNotEmptySearch(){

        WelcomePageObject WelcomePageObject = WelcomePageObjectFactory.get(driver);
        WelcomePageObject.clickOnSkipButton();

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();

        String search_line = "Linkin Park Discography";

        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();

        Assert.assertTrue(
                "We found a few results",
                amount_of_search_results > 0
        );
    }

    @Test
    @DisplayName("Empty search")
    @Description("This test is for searching in case there is not items found via search")
    @Step("Starting test testAmountOfNotEmptySearch")
    @Feature(value="Search")
    @Severity(value = SeverityLevel.NORMAL)
    public void testAmountOfEmptySearch() {

        WelcomePageObject WelcomePageObject = WelcomePageObjectFactory.get(driver);
        WelcomePageObject.clickOnSkipButton();

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();

        String search_line = "liwdqwdawdasdsadasd";
        SearchPageObject.typeSearchLine(search_line);

        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResultForSearch();
    }

    // ТЕСТ ДЛЯ ПРОВЕРКИ ПОИСКА СТАТЬИ ПО НАЗВАНИЮ И ОПИСАНИЮ -  - to refactor
    @Test
    @DisplayName("Check item in the search by title and description")
    @Description("This test is for searching of some element, then check that element by tittle and description")
    @Step("Starting test testSearchObjectWithDescription")
    @Feature(value="Search")
    @Severity(value = SeverityLevel.NORMAL)
    public void testSearchObjectWithDescription() {

        WelcomePageObject WelcomePageObject = WelcomePageObjectFactory.get(driver);
        WelcomePageObject.clickOnSkipButton();

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResultWithDescription("Java", "Indonesian island");
       // SearchPageObject.waitForSearchResultWithDescription("JavaScript", "High-level programming language");
        //SearchPageObject.waitForSearchResultWithDescription("Java (programming language)", "Object-oriented programming language");
    }
}
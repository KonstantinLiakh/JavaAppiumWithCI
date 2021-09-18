package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.WelcomePageObject;
import lib.ui.factories.SearchPageObjectFactory;
import lib.ui.factories.WelcomePageObjectFactory;
import org.junit.Test;

public class SearchTests extends CoreTestCase {

    // ТЕСТ ДЛЯ ПРОВЕРКИ ПОИСКА СТАТЬИ
    @Test
    public void testSearchObject() {

        WelcomePageObject WelcomePageObject = WelcomePageObjectFactory.get(driver);
        WelcomePageObject.clickOnSkipButton();

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Java (programming language)");
    }

    // ТЕСТ ДЛЯ ОТМЕНЫ ПОИСКА
    @Test
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

    //ТЕСТ ДЛЯ ПРОВЕРКИ ПОИСКА ПРИ НАЛИЧИИ РЕЗУЛЬТАТОВ В ПОИСКЕ
    @Test
    public void testAmountOfNotEmptySearch(){

        WelcomePageObject WelcomePageObject = WelcomePageObjectFactory.get(driver);
        WelcomePageObject.clickOnSkipButton();

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();

        String search_line = "Linkin Park Discography";

        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();

        assertTrue(
                "We found a few results",
                amount_of_search_results > 0
        );
    }

    //ТЕСТ ДЛЯ ПРОВЕРКИ ПОИСКА ПРИ ПУСТОМ РЕЗУЛЬТАТЕ ПОИСКА
    @Test
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
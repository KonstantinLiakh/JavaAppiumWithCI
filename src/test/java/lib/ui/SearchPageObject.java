package lib.ui;

import io.qameta.allure.Step;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class SearchPageObject extends MainPageObject {

    protected static String
        SEARCH_INIT_ELEMENT,
        SEARCH_INPUT,
        SEARCH_CANCEL_BUTTON,
        SEARCH_RESULT_SUBSTRING_TPL,
        SEARCH_RESULT_TITLE_AND_DESCRIPTION_TPL,
        SEARCH_RESULT_ELEMENT,
        SEARCH_EMPTY_RESULT_ELEMENT;

    public SearchPageObject(RemoteWebDriver driver) {

        super(driver);
    }
    /* TEMPLATES METHODS */
    private static String getResultSearchElement(String substring){
        return SEARCH_RESULT_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getResultSearchElementWithDescription(String substring1, String substring2){
        return SEARCH_RESULT_TITLE_AND_DESCRIPTION_TPL
                .replace("{SUBSTRING1}", substring1)
                .replace("{SUBSTRING2}", substring2);
    }

    /* TEMPLATES METHODS*/

    @Step("Initializing the search field")
    public void initSearchInput() {
        this.waitForElementPresent(SEARCH_INIT_ELEMENT, "Cannot find 'Search Wikipedia' input after clicking by SEARCH_INIT_ELEMENT", 5 );
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot click on 'Search Wikipedia' input", 5);
    }

    @Step("Waiting for button to cancel search result")
    public void waitForCancelButtonToAppear() {
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Cannot find 'SEARCH_CANCEL_BUTTON' to cancel search",5);
    }

    @Step("Waiting for cancel button to disappear")
    public void waitForCancelButtonToDisappear() {
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "'SEARCH_CANCEL_BUTTON' is still on a page",5);
    }

    @Step("Click on cancel button to cancel search result")
    public void clickCancelSearch() {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot find and click on 'SEARCH_CANCEL_BUTTON'",10);
    }

    @Step("Type '{search_line}' text to the search line")
    public void typeSearchLine(String search_line) {
        this.waitForElementAndSendKeys(SEARCH_INPUT,search_line, "Cannot find and type into search input",5);
    }

    @Step("Waiting for search result to appear")
    public void waitForSearchResult(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(search_result_xpath, "Cannot find SEARCH_RESULT with substring " + substring, 15);
    }

    @Step("Waiting for search result with description to appear")
    public void waitForSearchResultWithDescription(String substring1, String substring2) {
        String search_result_xpath = getResultSearchElementWithDescription(substring1, substring2);
        this.waitForElementPresent(search_result_xpath, "Cannot find SEARCH_RESULT with substrings", 15);
    }

    @Step("Waiting for search result and click on article title by substring")
    public void clickByArticleSubstring(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result_xpath, "Cannot find and click SEARCH_RESULT with substring" + substring, 10);
    }

    @Step("Getting amount of found articles")
    public int getAmountOfFoundArticles() {

        this.waitForElementPresent(
                SEARCH_INIT_ELEMENT,
                "Cannot find anything by the request",
                15
        );

        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }

    @Step("Wait for empty search result element when search done for non existing article")
    public void waitForEmptyResultsLabel() {
        this.waitForElementPresent(SEARCH_EMPTY_RESULT_ELEMENT, "Cannot find empty result by element", 15);

    }

    @Step("Checck there is no result found by the search")
    public void assertThereIsNoResultForSearch() {
        this.assertElementNotPresent(SEARCH_RESULT_ELEMENT, "We found some results by request ");
    }
}
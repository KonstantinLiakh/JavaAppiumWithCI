package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
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

    public void initSearchInput() {
        this.waitForElementPresent(SEARCH_INIT_ELEMENT, "Cannot find 'Search Wikipedia' input after clicking by SEARCH_INIT_ELEMENT", 5 );
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot click on 'Search Wikipedia' input", 5);
    }

    public void waitForCancelButtonToAppear() {
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Cannot find 'SEARCH_CANCEL_BUTTON' to cancel search",5);
    }

    public void waitForCancelButtonToDisappear() {
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "'SEARCH_CANCEL_BUTTON' is still on a page",5);
    }

    public void clickCancelSearch() {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot find and click on 'SEARCH_CANCEL_BUTTON'",10);
    }

    public void typeSearchLine(String search_line) {
        this.waitForElementAndSendKeys(SEARCH_INPUT,search_line, "Cannot find and type into search input",5);
    }

    public void waitForSearchResult(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(search_result_xpath, "Cannot find SEARCH_RESULT with substring " + substring, 15);
    }

    public void waitForSearchResultWithDescription(String substring1, String substring2) {
        String search_result_xpath = getResultSearchElementWithDescription(substring1, substring2);
        this.waitForElementPresent(search_result_xpath, "Cannot find SEARCH_RESULT with substrings", 15);
    }

    public void clickByArticleSubstring(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result_xpath, "Cannot find and click SEARCH_RESULT with substring" + substring, 10);
    }

    public int getAmountOfFoundArticles() {

        this.waitForElementPresent(
                SEARCH_INIT_ELEMENT,
                "Cannot find anything by the request",
                15
        );

        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }

    public void waitForEmptyResultsLabel() {
        this.waitForElementPresent(SEARCH_EMPTY_RESULT_ELEMENT, "Cannot find empty result by element", 15);

    }

    public void assertThereIsNoResultForSearch() {
        this.assertElementNotPresent(SEARCH_RESULT_ELEMENT, "We found some results by request ");
    }
}
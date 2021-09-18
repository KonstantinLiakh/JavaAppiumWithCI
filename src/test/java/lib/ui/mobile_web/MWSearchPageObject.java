package lib.ui.mobile_web;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_ELEMENT = "css:button#searchIcon";
        SEARCH_INPUT = "css:form>input[type='search']";
        SEARCH_CANCEL_BUTTON = "css:div.header-action>button";
        SEARCH_RESULT_SUBSTRING_TPL = "xpath://*[@title = '{SUBSTRING}']";
        //SEARCH_RESULT_TITLE_AND_DESCRIPTION_TPL = "xpath://*[@title = '{SUBSTRING}']/../div/[contains(@class, 'wikipedia-description')][contains(@text, '{SUBSTRING2}')]"; - to refactor
        SEARCH_RESULT_ELEMENT = "css:ul.page-list>li.page-summary";
        SEARCH_EMPTY_RESULT_ELEMENT = "css:p.without-results";
    }

    public MWSearchPageObject (RemoteWebDriver driver) {
        super(driver);
    }
}

package lib.ui;

import io.qameta.allure.Step;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class ArticlePageObject extends MainPageObject{

    protected static String
        TITLE_TPL,
        TITLE_TPL2,
        FOOTER_ELEMENT,
        SAVE_BUTTON,
        CLOSE_DIALOG,
        ADD_TO_MY_LIST_BUTTON,
        OPTION_REMOVE_FROM_NY_LIST_BUTTON,
        MY_LIST_INPUT,
        MY_LIST_OK_BUTTON,
        CLOSE_ARTICLE_BUTTON,
        LIST_CREATED_BY_ME;

    public ArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }

    @Step("Replace title element by substring in xpath")
    private static String getTitleXpath1(String article_name) {
        return TITLE_TPL.replace("{ARTICLE_NAME}", article_name);
    }

    @Step("Waiting and getting the article title")
    public String waitForTitleElement1(String article_name) {

        if (Platform.getInstance().isAndroid()) {
            String title_xpath = getTitleXpath1(article_name);
            return this.waitForElementPresent(title_xpath, "Cannot find article title on a page", 15).getAttribute("text");
        }
        else if (Platform.getInstance().isIOS()) {
            String title_xpath = getTitleXpath1(article_name);
            return this.waitForElementPresent(title_xpath, "Cannot find article title on a page", 15).getAttribute("name");
        }
        else {
            String title_xpath = getTitleXpath1(article_name);
            return this.waitForElementPresent(title_xpath, "Cannot find article title on a page", 15).getText();
        }
    }

    @Step("Clicking by title element without waiting time")
    public void checkTitleElementWithoutWaitingTime(String article_name) {

        String title_xpath = getTitleXpath1(article_name);
        this.assertElementPresent(
                title_xpath,
                "No results found for the desired title"
        );
    }

    @Step("Replace title element by substring in xpath")
    private static String getTitleXpath2(String article_name) {
        return TITLE_TPL2.replace("{ARTICLE_NAME}", article_name);
    }

    @Step("Waiting and getting the article title")
    public String waitForTitleElement2(String article_name) {

        if (Platform.getInstance().isAndroid()) {
            String title_xpath = getTitleXpath1(article_name);
            return this.waitForElementPresent(title_xpath, "Cannot find article title on a page", 15).getAttribute("text");
        }
        else if (Platform.getInstance().isIOS()) {
            String title_xpath = getTitleXpath2(article_name);
            return this.waitForElementPresent(title_xpath, "Cannot find article title on a page", 15).getAttribute("name");
        }
        else {
            String title_xpath = getTitleXpath1(article_name);
            return this.waitForElementPresent(title_xpath, "Cannot find article title on a page", 15).getText();
        }
        //screenshot(this.takeScreenshot("article_title"));
    }

    @Step("Scrolling the article to the footer area")
    public void swipeToFooter() {

        if (Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(
                    FOOTER_ELEMENT,
                    "Cannot find the end of article",
                    20
            );
        }
        else if (Platform.getInstance().isIOS()) {
            this.swipeUpTillElementAppeared(FOOTER_ELEMENT,
                    "Cannot find the end of article",
                    40);
        }
        else {
            this.scrollWebPageTillElementNotVisible(
                    FOOTER_ELEMENT,
                    "Cannot find the end of article",
                    40
            );
        }
    }

    @Step("Adding article to my saved list which not yet added")
    public void addArticleToMyList(String name_of_my_folder){
        this.waitForElementAndClick(
                SAVE_BUTTON,
                "cannot find find button for 'Save' the article",
                5
        );
        this.waitForElementAndClick(
                ADD_TO_MY_LIST_BUTTON,
                "cannot find find button for article to 'ADD TO LIST'",
                5
        );

        this.waitForElementAndSendKeys(
                MY_LIST_INPUT,
                name_of_my_folder,
                "Cannot type text for 'My new list' saved items",
                5
        );
        this.waitForElementAndClick(
                MY_LIST_OK_BUTTON,
                "Cannot press 'OK' button to save articel to my list",
                5
        );
    }

    @Step("Adding article to already created list")
    public void addArticleToAlreadyExistingList() {
        this.waitForElementAndClick(
                SAVE_BUTTON,
                "cannot find find button for 'Save' the article",
                5
        );
        this.waitForElementAndClick(
                ADD_TO_MY_LIST_BUTTON,
                "cannot find find button for article to 'ADD TO LIST'",
                5
        );
        this. waitForElementAndClick(
                LIST_CREATED_BY_ME,
                "Cannot press 'OK' button to save 2nd article to my list",
                5
        );
    }

    @Step("Adding article to my saved list for mobile_web and iOS when no articles saved")
    public void addArticlesToMySavedIOSAndMW() {
        if (Platform.getInstance().isIOS()) {
            this.waitForElementAndClick(SAVE_BUTTON, "Cannot find option button to add article to reading list", 5);
            this.waitForElementAndClick(CLOSE_DIALOG, "Cannot find close dialog button for iOS", 5);
        }
        else {
            //this.removeArticleFromSavedIfItAdded();
            this.waitForElementAndClick(SAVE_BUTTON, "Cannot find option button to add article to reading list", 5);
        }
    }

    @Step("Adding article to my saved list for iOS when some articles already saved")
    public void addArticlesToMySavedIOSWithAlreadySaved() {
        this.waitForElementAndClick(SAVE_BUTTON, "Cannot find option button to add article to reading list", 5);
    }

    public void removeArticleFromSavedIfItAdded() {
        if (this.isElementPresent(OPTION_REMOVE_FROM_NY_LIST_BUTTON)) {
            this.waitForElementAndClick(
                    OPTION_REMOVE_FROM_NY_LIST_BUTTON,
                    "Cannot click button to remove an article from saved",
                    1
            );
            this.waitForElementPresent(
                    SAVE_BUTTON,
                    "Cannot find button to add an article after removing it from this list before",
                    2
            );
        }
        else {
            return;
        }
    }

    @Step("Closing the article")
    public void closeArticle() {
        this.waitForElementAndClick(
                CLOSE_ARTICLE_BUTTON,
                "Cannot close article",
                5
        );
    }
}
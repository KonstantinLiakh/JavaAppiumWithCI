package lib.ui;

import io.qameta.allure.Step;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class MyListsPageObject extends MainPageObject{

    protected static String
        FOLDER_BY_NAME_TPL,
        ARTICLE_BY_TITLE_TPL,
        ARTICLE_DELETE_BUTTON_FOR_IOS,
        REMOVE_FROM_SAVED_BUTTON;

    @Step("Replace saved folder name which added via variable")
    private static String getFolderXpathByName(String name_of_my_folder) {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_my_folder);
    }

    @Step("Replace article title by substring")
    private static String getRemoveButtonByTitle(String article_title) {
        return REMOVE_FROM_SAVED_BUTTON.replace("{TITLE}", article_title);
    }

    @Step("Replace article title by substring")
    private static String getSavedArticleXpathByTitle(String article_title) {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }

    public MyListsPageObject (RemoteWebDriver driver) {
        super(driver);
    }

    @Step("Open my saved folder")
    public void openFolderByName(String name_of_my_folder) {
        String folder_name_xpath = getFolderXpathByName(name_of_my_folder);
        this.waitForElementAndClick(
                folder_name_xpath,
                "Cannot get folder by name" + name_of_my_folder,
                5
        );
    }

    @Step("Waiting and getting of article title")
    public void waitForArticleToAppearByTitle (String article_title) {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementPresent(
                article_xpath,
                "Cannot find saved article" + article_title,
                5
        );
    }

    @Step("Swipe article to left to delete article (method is only for iOS and Android)")
    public void swipeByArticleToDelete(String article_title) {
        this.waitForArticleToAppearByTitle(article_title);
        String article_xpath = getSavedArticleXpathByTitle(article_title);

        if (Platform.getInstance().isIOS() ||Platform.getInstance().isAndroid()) {
            this.swipeElementToLeft(
                    article_xpath,
                    "Cannot find saved article"
            );
        }

        else {
            String remove_locator = getRemoveButtonByTitle(article_title);
            this.waitForElementAndClick(
                    remove_locator,
                    "Cannot click button to remove article from saved",
                    10
            );
        }

        if (Platform.getInstance().isIOS()) {
            this.waitForElementAndClick(ARTICLE_DELETE_BUTTON_FOR_IOS,
                    "Cannot find delete article button for iOS",
                    5
            );
        }

        if (Platform.getInstance().isMW()) {
            driver.navigate().refresh();
        }

        this.waitForArticleToDisappear_ByTitle(article_title);
    }

    @Step("Waiting for article to disappear from my saved list after deleting it")
    public void waitForArticleToDisappear_ByTitle (String article_title) {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementNotPresent(
                article_xpath,
                "Cannot delete saved article, it's still present with title" + article_title,
                5
        );
    }
}
package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import junit.framework.Assert;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.*;
import org.junit.Test;

public class MyListTests extends CoreTestCase {

    private static final String name_of_my_folder = "List_for_test";
    private static final String
            login = "Kliakh90",
            password = "Dermorhend12-";

    @Test
    @DisplayName("Save article to saved")
    @Description("This test saves the article to saved list")
    @Step("Starting test testSaveFirstArticleToMyList")
    @Features(value = {@Feature(value="Search"), @Feature(value="Article"), @Feature(value="SavedArticles")})
    @Severity(value = SeverityLevel.CRITICAL)
    public void testSaveFirstArticleToMyList(){

        WelcomePageObject WelcomePageObject = WelcomePageObjectFactory.get(driver);
        WelcomePageObject.clickOnSkipButton();

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleSubstring("Java (programming language)");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

        String article_title = ArticlePageObject.waitForTitleElement1("Java (programming language)");

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(name_of_my_folder);
            ArticlePageObject.closeArticle();
            SearchPageObject.clickCancelSearch();
        }
        else if (Platform.getInstance().isIOS()) {
            ArticlePageObject.addArticlesToMySavedIOSAndMW();
            ArticlePageObject.closeArticle();
        }
        else {
            ArticlePageObject.addArticlesToMySavedIOSAndMW();
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();

            ArticlePageObject.waitForTitleElement1("Java (programming language)");

            Assert.assertEquals("We are not in the same page after login",
                    article_title,
                    ArticlePageObject.waitForTitleElement1("Java (programming language)")
            );
        }

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.openNavigation();
        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
            MyListsPageObject.openFolderByName(name_of_my_folder);
        }

        MyListsPageObject.swipeByArticleToDelete(article_title);
    }

    @Test
    @DisplayName("Save two articles to saved and delete of of them")
    @Description("This test saves two articles to saved list and then deletes one of articles")
    @Step("Starting test testSaveTwoArticlesAndDeleteOneOfIt")
    @Features(value = {@Feature(value="Search"), @Feature(value="Article"), @Feature(value="SavedArticles")})
    @Severity(value = SeverityLevel.CRITICAL)
    public void testSaveTwoArticlesAndDeleteOneOfIt(){

        WelcomePageObject WelcomePageObject = WelcomePageObjectFactory.get(driver);
        WelcomePageObject.clickOnSkipButton();

        //Save 1st article
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleSubstring("JavaScript");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        String title_before_saving = ArticlePageObject.waitForTitleElement2("JavaScript");

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(name_of_my_folder);
            ArticlePageObject.closeArticle();
        }
        else if (Platform.getInstance().isIOS()) {
            ArticlePageObject.addArticlesToMySavedIOSAndMW();
            ArticlePageObject.closeArticle();
            SearchPageObject.initSearchInput();
        }
        else {
            ArticlePageObject.addArticlesToMySavedIOSAndMW();
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();

            ArticlePageObject.waitForTitleElement2("JavaScript");
            SearchPageObject.initSearchInput();
            SearchPageObject.typeSearchLine("Java");
        }

        //Save 2nd article
        SearchPageObject.clickByArticleSubstring("Java");
        //ArticlePageObject.waitForTitleElement1("Java (programming language)");
        String article_to_be_deleted = ArticlePageObject.waitForTitleElement1("Java");

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToAlreadyExistingList();
            ArticlePageObject.closeArticle();
            SearchPageObject.clickCancelSearch();
        }
        else if (Platform.getInstance().isIOS()) {
            ArticlePageObject.addArticlesToMySavedIOSWithAlreadySaved();
            ArticlePageObject.closeArticle();
        }
        else {
            ArticlePageObject.addArticlesToMySavedIOSAndMW();
        }

        //Navigation to my lists
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.openNavigation();
        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
            MyListsPageObject.openFolderByName(name_of_my_folder);
        }

        MyListsPageObject.swipeByArticleToDelete(article_to_be_deleted);

        SearchPageObject.clickByArticleSubstring("JavaScript");

        String title_after_saving = ArticlePageObject.waitForTitleElement2("JavaScript");

        Assert.assertEquals(
                "Title of the 1st article before saving is not equal to title in saved list folder",
                title_before_saving,
                title_after_saving
        );
    }
}
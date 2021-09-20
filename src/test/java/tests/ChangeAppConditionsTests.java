package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import junit.framework.Assert;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.WelcomePageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import lib.ui.factories.WelcomePageObjectFactory;
import org.junit.Test;

public class ChangeAppConditionsTests extends CoreTestCase {

    @Test
    @DisplayName("Compare article title after device orientation changed")
    @Description("This test compares article title on a page before and after device orientation changed")
    @Step("Starting test testChangeOrientationScreenInSearchResults")
    @Features(value = {@Feature(value="Search"), @Feature(value="Article"), @Feature(value="MobileDeviceSpecific")})
    @Severity(value = SeverityLevel.CRITICAL)
    public void testChangeOrientationScreenInSearchResults(){

        if (Platform.getInstance().isMW()) {
            return;
        }

        WelcomePageObject WelcomePageObject = WelcomePageObjectFactory.get(driver);
        WelcomePageObject.clickOnSkipButton();

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleSubstring("Java (programming language)");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        String title_before_rotation = ArticlePageObject.waitForTitleElement1("Java (programming language)");
        this.rotateScreenLandscape();
        String title_after_rotation = ArticlePageObject.waitForTitleElement1("Java (programming language)");

        Assert.assertEquals(
                "Article title has been changed after rotation",
                title_before_rotation,
                title_after_rotation
        );

        this.rotateScreenPortrait();
        String title_after_second_rotation = ArticlePageObject.waitForTitleElement1("Java (programming language)");

        Assert.assertEquals(
                "Article title has been changed after rotation",
                title_before_rotation,
                title_after_second_rotation
        );
    }

    @Test
    @DisplayName("Compare search element after app recovers from background")
    @Description("This test compares search element before and after app recovers from background")
    @Step("Starting test testArticleTextAfterBackground")
    @Features(value = {@Feature(value="Search"), @Feature(value="MobileDeviceSpecific")})
    @Severity(value = SeverityLevel.CRITICAL)
    public void testArticleTextAfterBackground(){

        if (Platform.getInstance().isMW()) {
            return;
        }

        WelcomePageObject WelcomePageObject = WelcomePageObjectFactory.get(driver);
        WelcomePageObject.clickOnSkipButton();

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Java (programming language)");
        this.backgroundApp(2);
        SearchPageObject.waitForSearchResult("Java (programming language)");
    }

}
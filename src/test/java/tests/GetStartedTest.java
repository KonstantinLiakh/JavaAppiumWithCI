package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.WelcomePageObject;
import lib.ui.factories.WelcomePageObjectFactory;
import org.junit.Test;

public class GetStartedTest extends CoreTestCase {

    @Test
    @DisplayName("Scroll pages on Welcome screen")
    @Description("This test scrolls all pages with welcome information on welcome screen")
    @Step("Starting test testPassThroughWelcome")
    @Feature(value="WelcomeScreen")
    @Severity(value = SeverityLevel.MINOR)
    public void testPassThroughWelcome() {

        if (Platform.getInstance().isAndroid() || Platform.getInstance().isMW()) {
            return;
        }

        WelcomePageObject WelcomePage = WelcomePageObjectFactory.get(driver);

        WelcomePage.waitForMoreLink();
        WelcomePage.clickNextButton();

        WelcomePage.waitForNewWaysToExplore();
        WelcomePage.clickNextButton();

        WelcomePage.waitForAddAndEditPreferredLangText();
        WelcomePage.clickNextButton();

        WelcomePage.waitForLearnMoreAboutDataCollectedText();
        WelcomePage.clickGetStartedButton();
    }
}

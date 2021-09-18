package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.WelcomePageObject;
import lib.ui.factories.WelcomePageObjectFactory;
import org.junit.Test;

public class GetStartedTest extends CoreTestCase {

    // ТЕСТ ДЛЯ ПРОКРУТКИ СТРАНИЦ НА WELCOME ЭКРАНЕ ДЛЯ iOS - done
    @Test
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

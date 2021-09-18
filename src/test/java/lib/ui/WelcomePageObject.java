package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class WelcomePageObject extends MainPageObject{

    protected static String
    SKIP_BUTTON_ON_MAIN_PAGE,
    STEP_LEARN_MORE_LINK,
    STEP_NEW_WAYS_TO_EXPLORE,
    STEP_ADD_AND_EDIT_PREFERRED_LANG,
    STEP_LEARN_MORE_ABOUT_DATA_COLLECTED,
    NEXT_BUTTON,
    GET_STARTED_BUTTON;

    public WelcomePageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public void clickOnSkipButton() {

        if (Platform.getInstance().isAndroid() || Platform.getInstance().isIOS())  {
            this.waitForElementAndClick(SKIP_BUTTON_ON_MAIN_PAGE, "Cannot click on 'SKIP_BUTTON' on main page", 5);
        }
        else {
            return;
        }
    }

    public void waitForMoreLink() {
        this.waitForElementPresent(STEP_LEARN_MORE_LINK,
                "Cannot find 'Learn more about Wikipedia' link",
                10
        );
    }

    public void waitForNewWaysToExplore() {
        this.waitForElementPresent(STEP_NEW_WAYS_TO_EXPLORE,
                "Cannot find 'New ways to explore' text",
                10
        );
    }

    public void waitForAddAndEditPreferredLangText() {
        this.waitForElementPresent(STEP_ADD_AND_EDIT_PREFERRED_LANG,
                "Cannot find 'Add or edit preferred languages' text",
                10
        );
    }

    public void waitForLearnMoreAboutDataCollectedText() {
        this.waitForElementPresent(STEP_LEARN_MORE_ABOUT_DATA_COLLECTED,
                "Cannot find 'Learn more about data collected' text",
                10
        );
    }

    public void clickNextButton() {
        this.waitForElementAndClick(NEXT_BUTTON,
                "Cannot find 'Next' button",
                10
        );
    }

    public void clickGetStartedButton() {
        this.waitForElementAndClick(GET_STARTED_BUTTON,
                "Cannot find 'Get started' button",
                10
        );
    }
}

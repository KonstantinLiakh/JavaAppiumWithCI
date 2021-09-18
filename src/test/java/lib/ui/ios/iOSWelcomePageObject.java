package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.WelcomePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSWelcomePageObject extends WelcomePageObject {

    static {
        SKIP_BUTTON_ON_MAIN_PAGE = "xpath://XCUIElementTypeButton[@name='Skip']";
        STEP_LEARN_MORE_LINK = "xpath://XCUIElementTypeStaticText[@name='Learn more about Wikipedia']";
        STEP_NEW_WAYS_TO_EXPLORE = "id:New ways to explore";
        STEP_ADD_AND_EDIT_PREFERRED_LANG = "xpath://XCUIElementTypeStaticText[@name='Add or edit preferred languages']";
        STEP_LEARN_MORE_ABOUT_DATA_COLLECTED = "xpath://XCUIElementTypeStaticText[@name='Learn more about data collected']";
        NEXT_BUTTON = "xpath://XCUIElementTypeButton[@name='Next']";
        GET_STARTED_BUTTON = "xpath://XCUIElementTypeButton[@name='Get started']";
    }

    public iOSWelcomePageObject (RemoteWebDriver driver) {
        super(driver);
    }
}

package lib.ui.android;

import lib.ui.WelcomePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidWelcomePageObject extends WelcomePageObject {

    static {
        SKIP_BUTTON_ON_MAIN_PAGE = "id:org.wikipedia:id/fragment_onboarding_skip_button";
    }

    public AndroidWelcomePageObject (RemoteWebDriver driver) {
        super(driver);
    }
}

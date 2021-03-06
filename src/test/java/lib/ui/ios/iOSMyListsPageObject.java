package lib.ui.ios;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSMyListsPageObject extends MyListsPageObject {

    static {
        ARTICLE_BY_TITLE_TPL = "xpath://XCUIElementTypeStaticText[contains(@name, '{TITLE}')]";
        ARTICLE_DELETE_BUTTON_FOR_IOS = "id:swipe action delete";
    }

    public iOSMyListsPageObject (RemoteWebDriver driver) {
        super(driver);
    }
}

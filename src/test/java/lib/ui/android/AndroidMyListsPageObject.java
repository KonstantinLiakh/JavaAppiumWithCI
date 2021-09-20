package lib.ui.android;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidMyListsPageObject extends MyListsPageObject {

    static {
        FOLDER_BY_NAME_TPL = "xpath://android.widget.TextView[contains(@text, '{FOLDER_NAME}')]";
        ARTICLE_BY_TITLE_TPL = "xpath://android.widget.TextView[contains(@text, '{TITLE}')]";
    }

    public AndroidMyListsPageObject (RemoteWebDriver driver) {
        super(driver);
    }
}

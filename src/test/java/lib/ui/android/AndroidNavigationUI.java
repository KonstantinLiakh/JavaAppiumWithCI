package lib.ui.android;

import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidNavigationUI extends NavigationUI {
    static {
        MY_LISTS_LINK = "xpath://android.widget.FrameLayout[contains(@content-desc, 'Saved')]";
    }

    public AndroidNavigationUI (RemoteWebDriver driver) {
        super(driver);
    }
}

package lib.ui.android;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_ELEMENT = "xpath://*[contains(@text, 'Search Wikipedia')]";
        SEARCH_INPUT = "xpath://*[contains(@text, 'Search Wikipedia')]";
        SEARCH_CANCEL_BUTTON = "xpath://android.widget.ImageButton";
        SEARCH_RESULT_SUBSTRING_TPL = "xpath://android.widget.TextView[contains(@text, '{SUBSTRING}')]";
        SEARCH_RESULT_TITLE_AND_DESCRIPTION_TPL = "xpath://android.widget.TextView[@index='0' and @text='{SUBSTRING1}']/../android.widget.TextView[@index='1' and @text='{SUBSTRING2}']";
        SEARCH_RESULT_ELEMENT = "xpath://android.view.ViewGroup[@index='0']/android.widget.TextView[@text='Linkin Park discography' and @index='0']";
        SEARCH_EMPTY_RESULT_ELEMENT = "xpath://*[contains(@text, 'No results')]";
    }

    public AndroidSearchPageObject (RemoteWebDriver driver) {
        super(driver);
    }
}
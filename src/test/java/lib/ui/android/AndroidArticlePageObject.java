package lib.ui.android;
import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidArticlePageObject extends ArticlePageObject {

    static {
        TITLE_TPL = "xpath://android.view.View[contains(@text, '{ARTICLE_NAME}')]";
        FOOTER_ELEMENT = "xpath://*[contains(@text, 'View article in browser')]";
        SAVE_BUTTON = "xpath://android.widget.TextView[contains(@text, 'Save')]";
        ADD_TO_MY_LIST_BUTTON = "xpath://android.widget.Button[contains(@text, 'ADD TO LIST')]";
        MY_LIST_INPUT = "id:org.wikipedia:id/text_input";
        MY_LIST_OK_BUTTON = "xpath://android.widget.Button[contains(@text, 'OK')]";
        CLOSE_ARTICLE_BUTTON = "xpath://android.widget.ImageButton[contains(@content-desc, 'Navigate up')]";
        LIST_CREATED_BY_ME = "xpath://android.widget.TextView[contains(@text, 'List')]";
    }

    public AndroidArticlePageObject (RemoteWebDriver driver) {
        super(driver);
    }
}
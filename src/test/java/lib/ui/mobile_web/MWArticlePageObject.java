package lib.ui.mobile_web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject {

    static {
        TITLE_TPL = "xpath://div[contains(@class, 'page-heading')]//h1[contains(text(), '{ARTICLE_NAME}')]";
        FOOTER_ELEMENT = "css:footer";
        SAVE_BUTTON = "css:#page-actions li#page-actions-watch a#ca-watch.mw-ui-icon-wikimedia-star-base20";
        OPTION_REMOVE_FROM_NY_LIST_BUTTON = "css:#page-actions li#page-actions-watch a#ca-watch.mw-ui-icon-wikimedia-unStar-progressive";
    }

    public MWArticlePageObject (RemoteWebDriver driver) { super(driver); }
}

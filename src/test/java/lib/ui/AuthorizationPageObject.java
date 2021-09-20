package lib.ui;

import io.qameta.allure.Step;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthorizationPageObject extends MainPageObject{

    private static final String
            LOGIN_BUTTON = "xpath://div/a[text()='Log in']",
            LOGIN_INPUT = "css:input[name=wpName]",
            PASSWORD_INPUT = "css:input[name=wpPassword]",
            SUBMIT_BUTTON = "css:button#wpLoginAttempt";

    public AuthorizationPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    @Step("Click on login button for mobile_web")
    public void clickAuthButton() {
        this.waitForElementPresent(LOGIN_BUTTON, "Cannot find auth button",5);
        this.waitForElementAndClick(LOGIN_BUTTON, "Cannot find and click auth button",5);
    }

    @Step("Enter login and password for mobile_web")
    public void enterLoginData(String login, String password) {
        this.waitForElementAndSendKeys(LOGIN_INPUT, login, "Cannot find and put login to the login input",5);
        this.waitForElementAndSendKeys(PASSWORD_INPUT, password, "Cannot find and put password to the password input",5);
    }

    @Step("Submit login data for mobile_web")
    public void submitForm() {
        this.waitForElementAndClick(SUBMIT_BUTTON, "Cannot find and click submit auth button",5);
    }
}
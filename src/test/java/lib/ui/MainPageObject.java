package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import junit.framework.Assert;
import lib.Platform;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;

public class MainPageObject {

    protected RemoteWebDriver driver;

    public MainPageObject(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public WebElement waitForElementPresent(String locator, String error_message, long timeoutInSeconds) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebElement waitForElementPresent(String locator, String error_message) {
        return waitForElementPresent(locator, error_message);
    }

    public WebElement waitForElementAndClick(String locator, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(String locator, String value, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    public boolean waitForElementNotPresent(String locator, String error_message, long timeoutInSeconds) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until (
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    public WebElement waitForElementAndClear(String locator, String error_message, long timeoutInSeconds) {
        WebElement element =waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    public void assertElementHasText(WebElement element, String expected_text, String error_message) {
        String actual_text = element.getAttribute("text");
        if (actual_text.toLowerCase().contains(expected_text.toLowerCase())) {
            System.out.println("Test passed! Element text is equal to expected text");
        }
        else {
            Assert.fail("Test failed! Element text is NOT equal to expected text");
        }
    }

    public void swipeUp (long typeOfSwipe){

        if (driver instanceof  AppiumDriver) {

            TouchAction action = new TouchAction((AppiumDriver) driver);
            Dimension size = driver.manage().window().getSize();
            int x = size.width / 2;
            int start_y = (int) (size.height * 0.8);
            int end_y = (int) (size.height * 0.2);

            action
                    .press(PointOption.point(x, start_y))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(typeOfSwipe)))
                    .moveTo(PointOption.point(x, end_y))
                    .release()
                    .perform();
        }
        else {
            System.out.println("Method rotateScreenPortrait() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    public void swipeUpQuick() {
        swipeUp(200);
    }

    public void scrollWebPageUp() {
        if (Platform.getInstance().isMW()) {
            JavascriptExecutor JSExecutor = (JavascriptExecutor) driver;
            JSExecutor.executeScript("window.scrollBy(0, 250)");
        }
        else {
            System.out.println("Method rotateScreenPortrait() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    public void scrollWebPageTillElementNotVisible(String locator, String error_message, int max_swipes) {
        int already_swiped = 0;

        WebElement element = this.waitForElementPresent(locator, error_message);
        while (this.isElementLocatedOnTheScreen(locator)) {
            scrollWebPageUp();
            ++already_swiped;
            if (already_swiped > max_swipes) {
                Assert.assertTrue(error_message, element.isDisplayed());
            }
        }
    }

    public void swipeUpToFindElement(String locator, String error_message, int maxSwipes) {
        By by = this.getLocatorByString(locator);

        int alreadySwiped = 0;

        while (driver.findElements(by).size() == 0) {

            if (alreadySwiped >= maxSwipes) {
                waitForElementPresent(locator, "Cannot find element by swiping up. \n" + error_message, 0);
                return;
            }
            swipeUpQuick();
            ++alreadySwiped;
        }
    }

    public void swipeUpTillElementAppeared(String locator, String error_message, int max_swipes) {
        int alreadySwiped = 0;

        while (!this.isElementLocatedOnTheScreen(locator)) {
            if (alreadySwiped > max_swipes) {
                Assert.assertTrue(error_message, this.isElementLocatedOnTheScreen(locator));
            }
            swipeUpQuick();
            ++alreadySwiped;
        }
    }

    public boolean isElementLocatedOnTheScreen(String locator) {
        int element_location_by_y = this.waitForElementPresent(locator, "Cannot find element by locator", 1).getLocation().getY();

        if (Platform.getInstance().isMW()) {
            JavascriptExecutor JSExecutor = (JavascriptExecutor) driver;
            Object js_result = JSExecutor.executeScript("return window.pageYOffset");
            element_location_by_y -= Integer.parseInt(js_result.toString());
        }
        int screen_size_by_y = driver.manage().window().getSize().getHeight();
        return element_location_by_y < screen_size_by_y;
    }

    public void swipeElementToLeft(String locator, String error_message){

        if (driver instanceof  AppiumDriver) {

            WebElement element = waitForElementPresent(locator, error_message, 10);
            int left_x = element.getLocation().getX();
            int right_x = left_x + element.getSize().getWidth();
            int upper_y = element.getLocation().getY();
            int lower_y = upper_y + element.getSize().getHeight();
            int middle_y = (upper_y + lower_y) / 2;

            TouchAction action = new TouchAction((AppiumDriver) driver);

            action.press(PointOption.point(right_x, lower_y));
            action.waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)));

            action.moveTo(PointOption.point(left_x, lower_y));
            action.release();
            action.perform();
        }
        else {
            System.out.println("Method rotateScreenPortrait() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    public int getAmountOfElements(String locator) {
        By by = this.getLocatorByString(locator);
        List elements = driver.findElements(by);
        return elements.size();
    }

    public boolean isElementPresent(String locator) {
        return getAmountOfElements(locator) >= 0;
    }

    public void tryClickElementWithFewAttempts(String locator, String error_message, int amount_of_attempts) {
        int current_attempts = 0;
        boolean need_more_attempts = true;

        while (need_more_attempts) {
            try {
                this.waitForElementAndClick(locator, error_message, 5);
                need_more_attempts = false;
            }
            catch (Exception e) {
                if (current_attempts > amount_of_attempts) {
                    this.waitForElementAndClick(locator, error_message,5);
                }
            }
            ++ current_attempts;
        }
    }

    public  void assertElementNotPresent(String locator, String error_message) {
        int amountOfElements = getAmountOfElements(locator);
        if (amountOfElements > 0) {
            String default_message = "An element " + locator + " supposed to be not present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

    public String waitForElementAndGetAttribute(String locator, String attribute, String error_message, long timeInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeInSeconds);
        return element.getAttribute(attribute);
    }

    public  void assertElementPresent(String locator, String error_message) {
        int amountOfElements = getAmountOfElements(locator);
        if (amountOfElements <= 0) {
            String default_message = "An element " + locator + " supposed to be presented at the page";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

    private By getLocatorByString(String locator_with_type) {
        String[] exploded_locator = locator_with_type.split(Pattern.quote(":"), 2);
        String by_type = exploded_locator[0];
        String locator = exploded_locator[1];

        if (by_type.equals("xpath")) {
            return By.xpath(locator);
        }
        else if(by_type.equals("id")) {
            return By.id(locator);
        }
        else if(by_type.equals("css")) {
            return By.cssSelector(locator);
        }
        else {
            throw new IllegalArgumentException("Cannot get type of locator. Locator: " + locator_with_type);
        }
    }
}
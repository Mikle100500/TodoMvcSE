package com.todomvcse.core;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.todomvcse.core.CustomConditions.elementHasText;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class ConciseAPI {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        return driver;
    }

    public static void setDriver(WebDriver driver) {
        ConciseAPI.driver = driver;
    }

    public static void open(String url) {
        getDriver().get(url);
    }

    public static void executeJavaScript(String script) {

        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript(script);
    }

    public static WebElement hover(WebElement element) {

        Actions ac = new Actions(getDriver());
        ac.moveToElement(element).perform();
        return element;
    }

    public static void doubleClick(WebElement element) {

        Actions ac = new Actions(getDriver());
        ac.doubleClick(element).perform();
    }

    public static WebElement setValue(WebElement elementToSet, String value) {

       elementToSet.clear();
       elementToSet.sendKeys(value);
       return elementToSet;
    }

    public static WebElement $(By locator) {
        return assertThat(visibilityOfElementLocated(locator));
    }

    public static WebElement $(String cssSelector) {
        return $(By.cssSelector(cssSelector));
    }

    public static WebElement $(ExpectedCondition<WebElement> condition) {
        return assertThat(condition);
    }

    public static <V> V assertThat(ExpectedCondition<V> condition, int timeout) {
        return (new WebDriverWait(driver, timeout)).until(condition);
    }

    public static <V> V assertThat(ExpectedCondition<V> condition) {
        return assertThat(condition, Configuration.timeout);
    }
}

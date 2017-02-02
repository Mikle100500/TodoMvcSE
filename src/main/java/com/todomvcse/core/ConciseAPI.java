package com.todomvcse.core;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    public static Actions actions() {
        return new Actions(getDriver());
    }

    public static String url() {
        return getDriver().getCurrentUrl();
    }

    public static void executeJavaScript(String script) {
        ((JavascriptExecutor) getDriver()).executeScript(script);
    }

    public static WebElement hover(WebElement element) {

        actions().moveToElement(element).perform();
        return element;
    }

    public static WebElement doubleClick(WebElement element) {

        actions().doubleClick(element).perform();
        return element;
    }

    public static WebElement setValue(WebElement elementToBeSet, String value) {

        elementToBeSet.clear();
        elementToBeSet.sendKeys(value);
        return elementToBeSet;
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

    public static WebElement $(ExpectedCondition<WebElement> condition, String cssSelector) {
        return assertThat(condition).findElement(By.cssSelector(cssSelector));
    }

    public static <V> V assertThat(ExpectedCondition<V> condition, int timeout) {
        return (new WebDriverWait(getDriver(), timeout)).until(condition);
    }

    public static <V> V assertThat(ExpectedCondition<V> condition) {
        return assertThat(condition, Configuration.timeout);
    }
}

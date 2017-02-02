package com.todomvcse.core;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.Map;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class ConciseAPI {

    private static Map<Thread, WebDriver> driver = new HashMap<>();

    public static WebDriver getDriver() {
        return driver.get(Thread.currentThread());
    }

    public static void setDriver() {
        driver.put(Thread.currentThread(), new FirefoxDriver());
    }

    public static void open(String url) {
        getDriver().get(url);
    }

    public static Actions action() { return new Actions(getDriver()); }

    public static String url(){
        return getDriver().getCurrentUrl();
    }

    public static void executeJavaScript(String script) {
        ((JavascriptExecutor) getDriver()).executeScript(script);
    }

    public static WebElement hover(WebElement element) {

        action().moveToElement(element).perform();
        return element;
    }

    public static WebElement doubleClick(WebElement element) {

        action().doubleClick(element).perform();
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

    public static WebElement $(ExpectedCondition<WebElement> condition) { return assertThat(condition); }

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

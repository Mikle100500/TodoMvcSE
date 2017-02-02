package com.todomvcse.core;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.Map;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class ConciseAPI {

    private static Map<Thread, WebDriver> drivers = new HashMap<>();

    public static WebDriver getDrivers() {
        return drivers.get(Thread.currentThread());
    }

    public static void setDriver(WebDriver driver) {
        drivers.put(Thread.currentThread(), driver);
    }

    public static void open(String url) {
        getDrivers().get(url);
    }

    public static Actions actions() {
        return new Actions(getDrivers());
    }

    public static String url() {
        return getDrivers().getCurrentUrl();
    }

    public static void executeJavaScript(String script) {
        ((JavascriptExecutor) getDrivers()).executeScript(script);
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
        return (new WebDriverWait(getDrivers(), timeout)).until(condition);
    }

    public static <V> V assertThat(ExpectedCondition<V> condition) {
        return assertThat(condition, Configuration.timeout);
    }
}

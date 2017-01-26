package com.todomvcse.core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.todomvcse.core.CustomConditions.elementHasText;
import static com.todomvcse.core.CustomConditions.waitParentElement;
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

    public static WebElement $(By locator) {
        return assertThat(visibilityOfElementLocated(locator));
    }

    public static WebElement $(String cssSelector) {
        return $(By.cssSelector(cssSelector));
    }

    public static WebElement $(By elementsLocator, String text) {
        return assertThat(elementHasText(elementsLocator, text));
    }

    public static WebElement $(By parentLocator, String text, By innerLocator) {
        return assertThat(waitParentElement(parentLocator, text, innerLocator));
    }

    public static <V> V assertThat(ExpectedCondition<V> condition, int timeout) {
        return (new WebDriverWait(driver, timeout)).until(condition);
    }

    public static <V> V assertThat(ExpectedCondition<V> condition) {
        return assertThat(condition, Configuration.timeout);
    }
}

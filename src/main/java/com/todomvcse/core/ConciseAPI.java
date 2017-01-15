package com.todomvcse.core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

    public static void open(String url){ getDriver().get(url);}

    public static WebElement $(By locator) {
        return assertThat(visibilityOfElementLocated(locator));
    }

    public static WebElement $(String cssSelector) {
        return $(By.cssSelector(cssSelector));
    }

    public static By byText(String text) {
        return By.xpath(String.format("//*[contains(text(), '%s')]", text));
    }

    public static By byTitle(String title) {
        return By.cssSelector(String.format("[title='%s']", title));
    }

    public static  <V> V assertThat(ExpectedCondition<V> condition, int timeout) {
        return (new WebDriverWait(driver, timeout)).until(condition);
    }

    public static  <V> V assertThat(ExpectedCondition<V> condition) {
        return assertThat(condition, Configuration.timeout);
    }
}

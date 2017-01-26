package com.todomvcse.core;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.todomvcse.core.Wrappers.elementExceptionsCatcher;

public class CustomConditions {

    public static ExpectedCondition<WebElement> elementHasText(
            final By elementsLocator
            , final String expectedText) {

        return elementExceptionsCatcher(new ExpectedCondition<WebElement>() {

            private List<WebElement> elements;

            public WebElement apply(WebDriver driver) {
                elements = driver.findElements(elementsLocator);
                for (WebElement element : elements) {
                    if (element.getText().equals(expectedText)) {
                        return element;
                    }
                }
                return null;
            }

            public String toString() {
                return String.format("\nText of elements,"
                                + "\nfound with elementsLocator - %s"
                                + "\nShould be: %s"
                                + "\nActual: text not found\n"
                        , elementsLocator.toString()
                        , expectedText);
            }
        });
    }

    public static ExpectedCondition<List<WebElement>> visibleTextOf(
            final By locator
            , final String... texts) {

        return elementExceptionsCatcher(new ExpectedCondition<List<WebElement>>() {

            private List<String> actualTexts = new ArrayList<String>();
            private List<WebElement> elements;

            public List<WebElement> apply(WebDriver driver) {

                actualTexts.clear();
                elements = driver.findElements(locator);
                for (WebElement element : elements) {
                    if (!element.getText().equals("")) {    // костылёк :) - наш локатор почему-то
                        // при одной таске выгребает еще 2-ю пустую стрингу :(
                        actualTexts.add(element.getText());
                    }
                }

                if (actualTexts.size() != texts.length) {
                    return null;
                }

                for (int i = 0; i < actualTexts.size(); i++) {
                    if (!actualTexts.get(i).contains(texts[i])) {
                        return null;
                    }
                }
                return elements;
            }

            public String toString() {
                return String.format("\nTexts of elements being found with locator - %s"
                                + "\nshould contain: %s"
                                + "\nwhile actual texts: %s\n"
                        , locator.toString()
                        , Arrays.toString(texts)
                        , actualTexts);
            }
        });
    }

    public static ExpectedCondition<WebElement> waitParentElement(
            final By parentLocator
            , final String text
            , final By innerLocator) {

        return elementExceptionsCatcher(new ExpectedCondition<WebElement>() {

            public WebElement apply(WebDriver driver) {

                List<WebElement> parentElements = driver.findElements(parentLocator);

                for (WebElement parentElement : parentElements) {
                    if (parentElement.findElement(innerLocator) != null && parentElement.getText().equals(text)) {
                        return parentElement.findElement(innerLocator);
                    }
                }
                return null;
            }
        });

        // TODO: 26.01.2017 override toString()
    }

    public static ExpectedCondition<List<WebElement>> sizeOfVisible(final By locator) {

        return elementExceptionsCatcher(new ExpectedCondition<List<WebElement>>() {

            public List<WebElement> apply(WebDriver driver) {

                List<WebElement> elements = driver.findElements(locator);
                List<WebElement> visibleElements = new ArrayList<>();

                for (WebElement element : elements) {
                    if (element.isDisplayed()) {
                        visibleElements.add(element);
                    }
                }
                return visibleElements;
            }
        });

        // TODO: 26.01.2017 override toString()
    }
}

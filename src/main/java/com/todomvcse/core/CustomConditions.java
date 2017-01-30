package com.todomvcse.core;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.Arrays;
import java.util.List;

import static com.todomvcse.core.Wrappers.elementExceptionsCatcher;
import static com.todomvcse.helpers.HandleWebElements.getTexts;

public class CustomConditions {

    public static ExpectedCondition<WebElement> elementHasText(final By elementsLocator, final String elementText) {

        return elementExceptionsCatcher(new ExpectedCondition<WebElement>() {

            private List<WebElement> elements;
            private WebElement foundElement;

            public WebElement apply(WebDriver driver) {

                elements = driver.findElements(elementsLocator);
                for (WebElement element : elements) {
                    if (element.getText().equals(elementText)) {
                        foundElement = element;
                        return foundElement;
                    }
                }
                return null;
            }

            public String toString() {
                return String.format("\nText of elements were being found with elementsLocator - %s"
                                + "\nShould be: %s"
                                + "\nActual result is: %s\n"
                        , elementsLocator.toString()
                        , elementText
                        , foundElement);
            }
        });
    }

    public static ExpectedCondition<WebElement> elementHasCssClass(final By elementsLocator, final String cssClass) {

        return elementExceptionsCatcher(new ExpectedCondition<WebElement>() {

            private List<WebElement> elements;
            private WebElement foundElement;

            public WebElement apply(WebDriver driver) {

                elements = driver.findElements(elementsLocator);
                for (WebElement element : elements) {
                    if (element.findElement(By.cssSelector(cssClass)) != null) {
                        foundElement = element;
                        return foundElement;
                    }
                }
                return null;
            }

            public String toString() {
                return String.format("\nWebElements were being found with elementsLocator - %s"
                                + "\nShould contain element with cssClass: %s"
                                + "\nActual result is: %s\n"
                        , elementsLocator.toString()
                        , cssClass
                        , foundElement);
            }
        });
    }

    public static ExpectedCondition<List<WebElement>> exactTextsOfVisible(final By elementsLocator, final String... texts) {

        return elementExceptionsCatcher(new ExpectedCondition<List<WebElement>>() {

            private List<WebElement> elements;
            private List<String> textOfVisible;

            public List<WebElement> apply(WebDriver driver) {

                elements = driver.findElements(elementsLocator);
                textOfVisible = getTexts(elements);

                if (textOfVisible.size() != texts.length) {
                    return null;
                }

                for (int i = 0; i < texts.length; i++) {
                    if (!textOfVisible.get(i).equals(texts[i])) {
                        return null;
                    }
                }
                return elements;
            }

            public String toString() {
                return String.format("\nTexts of elements being found with elementsLocator - %s"
                                + "\nshould contain text(s): %s"
                                + "\nwhile actual text(s): %s\n"
                        , elementsLocator.toString()
                        , Arrays.toString(texts)
                        , textOfVisible.toString()
                );
            }
        });
    }

    public static ExpectedCondition<Boolean> sizeOfVisible(final By locator, final int sizeOfVisibleToBe) {

        return elementExceptionsCatcher(new ExpectedCondition<Boolean>() {

            private List<WebElement> elements;
            private List<String> visibleTexts;

            public Boolean apply(WebDriver driver) {

                elements = driver.findElements(locator);
                visibleTexts = getTexts(elements);

                return visibleTexts.size() == sizeOfVisibleToBe;
            }

            public String toString() {
                return String.format("\nSize of elements being found with locator - %s"
                                + "\nExpected to be: %d"
                                + "\nActual sizeOfVisibleToBe is: %s"
                        , locator.toString()
                        , sizeOfVisibleToBe
                        , visibleTexts.size());
            }
        });
    }
}

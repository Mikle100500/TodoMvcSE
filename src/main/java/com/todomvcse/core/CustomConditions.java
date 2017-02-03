package com.todomvcse.core;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.todomvcse.core.Helpers.getTextsOfVisible;
import static com.todomvcse.core.Helpers.getVisibleElements;
import static com.todomvcse.core.Wrappers.elementExceptionsCatcher;

public class CustomConditions {

    public static ExpectedCondition<WebElement> elementHasText(final By elementsLocator, final String expectedText) {
        return elementExceptionsCatcher(new ExpectedCondition<WebElement>() {

            private List<String> textOfElements = new ArrayList<>();

            public WebElement apply(WebDriver driver) {

                List<WebElement> elements = driver.findElements(elementsLocator);
                textOfElements.clear();

                for (WebElement element : elements) {
                    textOfElements.add(element.getText());
                    if (element.getText().equals(expectedText)) {
                        return element;
                    }
                }
                return null;
            }

            public String toString() {
                return String.format("\nList located by - %s"
                                + "\nContains elements with exact text: %s"
                                + "\nActual texts of list elements: %s\n"
                        , elementsLocator.toString()
                        , expectedText
                        , textOfElements.toString()
                );
            }
        });
    }

    public static ExpectedCondition<WebElement> elementHasCssClass(final By elementsLocator, final String cssClassToBe) {
        return elementExceptionsCatcher(new ExpectedCondition<WebElement>() {

            private List<String> classOfElements = new ArrayList<>();

            public WebElement apply(WebDriver driver) {

                List<WebElement> elements = driver.findElements(elementsLocator);
                classOfElements.clear();

                for (WebElement element : elements) {
                    String[] cssClasses = element.getAttribute("class").split("\\s+");
                    classOfElements.add(element.getAttribute("class"));

                    for (String cssClass : cssClasses) {
                        if (cssClass.equals(cssClassToBe)) {
                            return element;
                        }
                    }
                }
                return null;
            }

            public String toString() {
                return String.format("\nCSS Class of element"
                                + "from list located by - %s"
                                + "\nShould be: %s"
                                + "\nActual css classes are: %s\n"
                        , elementsLocator.toString()
                        , cssClassToBe
                        , classOfElements.toString()
                );
            }
        });
    }

    public static ExpectedCondition<List<WebElement>> exactTextsOfVisible(final By elementsLocator, final String... texts) {
        return elementExceptionsCatcher(new ExpectedCondition<List<WebElement>>() {

            private List<String> textsOfVisible;

            public List<WebElement> apply(WebDriver driver) {

                List<WebElement> elements = driver.findElements(elementsLocator);
                textsOfVisible = getTextsOfVisible(elements);

                if (textsOfVisible.size() != texts.length) {
                    return null;
                }

                for (int i = 0; i < texts.length; i++) {
                    if (!textsOfVisible.get(i).equals(texts[i])) {
                        return null;
                    }
                }
                return elements;
            }

            public String toString() {
                return String.format("\nList found with locator - %s"
                                + "\nshould have text(s): %s"
                                + "\nwhile actual text(s): %s\n"
                        , elementsLocator.toString()
                        , Arrays.toString(texts)
                        , textsOfVisible.toString()
                );
            }
        });
    }

    public static ExpectedCondition<Boolean> sizeOfVisible(final By elementsLocator, final int sizeOfVisibleToBe) {
        return elementExceptionsCatcher(new ExpectedCondition<Boolean>() {

            private List<WebElement> visibleElements;

            public Boolean apply(WebDriver driver) {

                List<WebElement> elements = driver.findElements(elementsLocator);
                visibleElements = getVisibleElements(elements);
                return visibleElements.size() == sizeOfVisibleToBe;
            }

            public String toString() {
                return String.format("\nList found with elementsLocator - %s"
                                + "\nExpected size to be: %d"
                                + "\nActual size is: %s"
                        , elementsLocator.toString()
                        , sizeOfVisibleToBe
                        , visibleElements.size()
                );
            }
        });
    }
}

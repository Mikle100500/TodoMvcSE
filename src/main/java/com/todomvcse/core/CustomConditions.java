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

    public static ExpectedCondition<WebElement> nthElementHasText(final By locator, final int index
            , final String expectedText) {

        return elementExceptionsCatcher(new ExpectedCondition<WebElement>() {

            private WebElement element;
            private String actualText;

            public WebElement apply(WebDriver driver) {

                element = driver.findElements(locator).get(index);
                actualText = element.getText();
                return actualText.contains(expectedText) ? element : null;
            }

            public String toString() {
                return String.format("\nText of %s element,"
                                + "\nfound with locator - %s"
                                + "\nShould be: %s"
                                + "\nActual text is: %s\n"
                        , index
                        , locator.toString()
                        , expectedText
                        , actualText);
            }
        });
    }

    public static ExpectedCondition<List<WebElement>> texts(final By locator, final String... texts) {

        return elementExceptionsCatcher(new ExpectedCondition<List<WebElement>>() {

            private List<String> actualTexts = new ArrayList<String>();
            private List<WebElement> elements;

            public List<WebElement> apply(WebDriver driver) {

                actualTexts.clear();
                elements = driver.findElements(locator);
                for (WebElement element : elements) {
                    actualTexts.add(element.getText());
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
}

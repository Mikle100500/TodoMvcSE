package com.todomvcse.core;

import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;


public class Helpers {

    public static List<String> getTextsOfVisible(List<WebElement> elements) {

        List<String> texts = new ArrayList<>();

        for (WebElement element : elements) {
            if (element.isDisplayed()) {
                texts.add(element.getText());
            }
        }
        return texts;
    }

    public static List<WebElement> getVisibleElements(List<WebElement> elements) {

        List<WebElement> visibleElements = new ArrayList<>();

        for (WebElement element : elements) {
            if (element.isDisplayed()) {
                visibleElements.add(element);
            }
        }
        return visibleElements;
    }
}

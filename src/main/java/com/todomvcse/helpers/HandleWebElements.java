package com.todomvcse.helpers;

import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;


public class HandleWebElements {

    public static List<String> getTexts(List<WebElement> elements){

        List<String> texts = new ArrayList<>();

        for (WebElement element: elements) {
            if (element.isDisplayed()){
                texts.add(element.getText());
            }
        }
        return texts;
    }
}

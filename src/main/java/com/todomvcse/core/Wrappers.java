package com.todomvcse.core;

import com.google.common.base.Function;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class Wrappers {
    public static <V> ExpectedCondition<V> elementExceptionsCatcher(final Function<? super WebDriver, V> condition){
        return new ExpectedCondition<V>() {
            
            public V apply(WebDriver input) {
                try {
                    return condition.apply(input);
                } catch (WebDriverException | IndexOutOfBoundsException e) {
                    return null;
                }
            }

            public String toString(){
                return condition.toString();
            }
        };
    }
}

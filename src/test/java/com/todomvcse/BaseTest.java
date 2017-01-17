package com.todomvcse;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.firefox.FirefoxDriver;

import static com.todomvcse.core.ConciseAPI.getDriver;
import static com.todomvcse.core.ConciseAPI.setDriver;


public class BaseTest {

    @BeforeClass
    public static void setUp() {
        setDriver(new FirefoxDriver());
    }

    @AfterClass
    public static void tearDown() {
        getDriver().quit();
    }
}

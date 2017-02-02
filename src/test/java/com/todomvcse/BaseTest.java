package com.todomvcse;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.firefox.FirefoxDriver;

import static com.todomvcse.core.ConciseAPI.getDrivers;
import static com.todomvcse.core.ConciseAPI.setDriver;


public class BaseTest {

    @Before
    public void setUp() {
       setDriver(new FirefoxDriver());
    }

    @After
    public void tearDown() {
        getDrivers().quit();
    }
}

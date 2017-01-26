package com.todomvcse.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static com.todomvcse.core.ConciseAPI.*;
import static com.todomvcse.core.CustomConditions.*;


public class TaskManagerPage {

    private By tasks = By.cssSelector("#todo-list>li");

    public void create(String taskName) {
        $("#new-todo").sendKeys(taskName + Keys.ENTER);
    }

    public void toggle(String taskName) {
        $(tasks, taskName, By.cssSelector(".toggle")).click();
    }

    public void toggleAll() {
        $("#toggle-all").click();
    }

    public void clearCompleted() {
        $("#clear-completed").click();
    }

    public void delete(String taskName) {

        WebElement focusOnDeletedTask = $(tasks, taskName);
        Actions ac = new Actions(getDriver());
        ac.moveToElement(focusOnDeletedTask).perform();
        focusOnDeletedTask.findElement(By.cssSelector(".destroy")).click();
    }

    public void assertVisibleTasks(String... taskNames) {
        assertThat(visibleTextOf(tasks, taskNames));
    }


    public void assertNoVisibleTasks() {
        assert assertThat(sizeOfVisible(tasks)).size() == 0;
    }

    public void assertItemsLeft(int itemsLeft) {
        assertThat(elementHasText(By.cssSelector("#todo-count>strong"), Integer.toString(itemsLeft)));
    }

    public WebElement startEdit(String oldTaskName, String newTaskName) {

        WebElement focusOnEditTask = $(tasks, oldTaskName);
        Actions ac = new Actions(getDriver());
        ac.doubleClick(focusOnEditTask)
                .sendKeys(Keys.chord(Keys.CONTROL, "a") + Keys.DELETE)
                .sendKeys(newTaskName).perform();
        return focusOnEditTask;
    }

    public void filterAll() {
        $(By.linkText("All")).click();
    }

    public void filterActive() {
        $(By.linkText("Active")).click();
    }

    public void filterCompleted() {
        $(By.linkText("Completed")).click();
    }

}
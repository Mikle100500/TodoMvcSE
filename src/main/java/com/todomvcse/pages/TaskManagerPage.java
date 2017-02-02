package com.todomvcse.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import static com.todomvcse.core.ConciseAPI.*;
import static com.todomvcse.core.CustomConditions.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;


public class TaskManagerPage {

    private By tasks = By.cssSelector("#todo-list>li");

    public void create(String taskName) {
        $("#new-todo").sendKeys(taskName + Keys.ENTER);
    }

    public void toggle(String taskName) {
        $(elementHasText(tasks, taskName), ".toggle").click();
    }

    public void toggleAll() {
        $("#toggle-all").click();
    }

    public void clearCompleted() {
        $("#clear-completed").click();
    }

    public void delete(String taskName) {
        hover($(elementHasText(tasks, taskName))).findElement(By.cssSelector(".destroy")).click();
    }

    public void assertVisibleTasks(String... taskNames) {
        assertThat(exactTextsOfVisible(tasks, taskNames));
    }

    public void assertNoVisibleTasks() {
        assertThat(sizeOfVisible(tasks, 0));
    }

    public void assertItemsLeft(int itemsLeft) {
        assertThat(visibilityOfElementLocated(By.cssSelector("#todo-count>strong")));
    }

    public WebElement startEdit(String oldTaskName, String newTaskName) {

        doubleClick($(elementHasText(tasks, oldTaskName)));
        WebElement editedElement = $(elementHasCssClass(tasks, "editing"), ".edit");
        return setValue(editedElement, newTaskName);
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
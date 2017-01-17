package com.todomvcse.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.todomvcse.core.ConciseAPI.$;


public class TaskManagerPage {

    public List<WebElement> tasks = $$("#todo-list>li");
    public WebElement newTodo = $("#new-todo");

    public void create(String... taskNames) {

        for (String name : taskNames) {
            newTodo.sendKeys(name + Keys.ENTER);
        }
    }

    public void toggle(String taskName) {
        tasks.findBy(exactText(taskName)).find(".toggle").click();
    }

    public void toggleAll() {
        $("#toggle-all").click();
    }

    public void clearCompleted() {
        $("#clear-completed").click();
    }

    public void delete(String taskName) {
        tasks.findBy(exactText(taskName)).hover();
        tasks.findBy(exactText(taskName)).find(".destroy").click();
    }

    public void assertTasks(String... taskNames) {
        tasks.shouldHave(exactTexts(taskNames));
    }

    public void assertVisibleTasks(String... taskNames) {
        tasks.filter(visible).shouldHave(exactTexts(taskNames));
    }

    public void assertNoVisibleTasks() {
        tasks.filter(visible).shouldBe(empty);
    }

    public void assertItemsLeft(int itemsLeft) {
        $("#todo-count>strong").shouldHave(exactText(Integer.toString(itemsLeft)));
    }

    public WebElement startEdit(String oldTaskName, String newTaskName) {
        doubleClick(tasks.find(exactText(oldTaskName)).find(By.tagName("label")));
        return tasks.find(cssClass("editing")).$(".edit").setValue(newTaskName);
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
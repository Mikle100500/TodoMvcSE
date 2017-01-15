package com.todomvcse;

import com.todomvc.pages.testpage.TaskManagerPage;
import org.junit.Test;
import org.openqa.selenium.Keys;

import static com.todomvc.helpers.Preconditions.precondition;


public class AtActiveFilterTest {

    private TaskManagerPage page = new TaskManagerPage();

    @Test
    public void testCreate() {

        precondition().activeTasks("b").atActiveFilter().prepare();

        page.create("a");
        page.assertVisibleTasks("b", "a");
        page.assertItemsLeft(2);
    }

    @Test
    public void testEdit() {

        precondition().activeTasks("b", "a").atActiveFilter().prepare();

        page.startEdit("a", "a edited").pressEnter();
        page.assertVisibleTasks("b", "a edited");
        page.assertItemsLeft(2);
    }

    @Test
    public void testDelete() {

        precondition().activeTasks("a", "b").atActiveFilter().prepare();

        page.delete("a");
        page.assertVisibleTasks("b");
        page.assertItemsLeft(1);
    }

    @Test
    public void testComplete() {

        precondition().activeTasks("a", "b").atActiveFilter().prepare();

        page.toggle("a");
        page.assertVisibleTasks("b");
        page.assertItemsLeft(1);
    }

    @Test
    public void testCompleteAll() {

        precondition().activeTasks("a", "b", "c").atActiveFilter().prepare();

        page.toggleAll();
        page.assertNoVisibleTasks();
        page.assertItemsLeft(0);
    }

    @Test
    public void testClearCompleted() {

        precondition().completedTasks("a").activeTasks("b", "c").atActiveFilter().prepare();

        page.clearCompleted();
        page.assertVisibleTasks("b", "c");
        page.assertItemsLeft(2);
    }

    @Test
    public void testSwitchToAll() {

        precondition().activeTasks("a").completedTasks("b").atActiveFilter().prepare();

        page.filterAll();
        page.assertTasks("a", "b");
        page.assertItemsLeft(1);
    }

    @Test
    public void testSwitchToCompleted() {

        precondition().completedTasks("a", "b").activeTasks("c", "d").atActiveFilter().prepare();

        page.filterCompleted();
        page.assertVisibleTasks("a", "b");
        page.assertItemsLeft(2);
    }

    @Test
    public void testCancelEditByEsc() {

        precondition().activeTasks("a", "b").atActiveFilter().prepare();

        page.startEdit("b", "b edited").sendKeys(Keys.ESCAPE);
        page.assertVisibleTasks("a", "b");
        page.assertItemsLeft(2);
    }

    @Test
    public void testConfirmEditClickOutside() {

        precondition().activeTasks("a").atActiveFilter().prepare();

        page.startEdit("a", "a edited");
        page.newTodo.click();
        page.assertVisibleTasks("a edited");
        page.assertItemsLeft(1);
    }
}

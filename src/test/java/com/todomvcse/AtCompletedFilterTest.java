package com.todomvcse;

import com.todomvc.pages.testpage.TaskManagerPage;
import org.junit.Test;

import static com.todomvc.helpers.Preconditions.precondition;


public class AtCompletedFilterTest {

    private TaskManagerPage page = new TaskManagerPage();

    @Test
    public void testDelete() {

        precondition().activeTasks("a").completedTasks("b").atCompletedFilter().prepare();

        page.delete("b");
        page.assertNoVisibleTasks();
        page.assertItemsLeft(1);
    }

    @Test
    public void testActivate() {

        precondition().completedTasks("a").atCompletedFilter().prepare();

        page.toggle("a");
        page.assertNoVisibleTasks();
        page.assertItemsLeft(1);
    }

    @Test
    public void testActivateAll() {

        precondition().completedTasks("a", "b").atCompletedFilter().prepare();

        page.toggleAll();
        page.assertNoVisibleTasks();
        page.assertItemsLeft(2);
    }

    @Test
    public void testClearCompleted() {

        precondition().completedTasks("a").atCompletedFilter().prepare();

        page.clearCompleted();
        page.assertNoVisibleTasks();
    }

    @Test
    public void testSwitchToAll() {

        precondition().completedTasks("a").activeTasks("b").completedTasks("c").atCompletedFilter().prepare();

        page.filterAll();
        page.assertTasks("a", "b", "c");
        page.assertItemsLeft(1);
    }

    @Test
    public void testSwitchToActive() {

        precondition().completedTasks("a", "b", "c").activeTasks("d", "e").atCompletedFilter().prepare();

        page.filterActive();
        page.assertVisibleTasks("d", "e");
        page.assertItemsLeft(2);
    }

    @Test
    public void testDeleteByEmptying() {

        precondition().completedTasks("a", "b").atCompletedFilter().prepare();

        page.startEdit("a", "").pressEnter();
        page.assertVisibleTasks("b");
        page.assertItemsLeft(0);
    }
}

package com.todomvcse;

import com.todomvc.pages.testpage.TaskManagerPage;
import org.junit.Test;

import static com.todomvc.helpers.Preconditions.precondition;


public class TodoMVCIntegrationTest {

    private TaskManagerPage page = new TaskManagerPage();

    @Test
    public void testTasksCommonFlow() {

        precondition().prepare();

        page.create("a");
        page.assertTasks("a");

        page.filterActive();
        page.assertVisibleTasks("a");

        page.create("b");
        page.toggleAll();
        page.assertNoVisibleTasks();

        page.filterCompleted();
        page.assertVisibleTasks("a", "b");

        page.toggle("b");
        page.assertVisibleTasks("a");

        page.clearCompleted();
        page.assertNoVisibleTasks();

        page.filterAll();
        page.delete("b");
        page.assertNoVisibleTasks();
    }


}